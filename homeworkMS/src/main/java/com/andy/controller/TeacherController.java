package com.andy.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.andy.utils.MyPropertiesUtil;
import com.andy.common.JsonResult;
import com.andy.service.CourseService;
import com.andy.service.TeacherService;

@Controller
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	CourseService courseService;
	
	@ResponseBody
	@RequestMapping(value = "/listStudnetByCondition")
		//Map<String, Object> map = JSON.parseObject(json, new TypeReference<Map<String, Object>>(){});
	public List<Map> listStudnet(Model model,@RequestBody Map<String,Object>   paramMap) {
		
		System.out.println(paramMap.toString());
		List<Map> lm = teacherService.listStudentsByConditions(paramMap);
		System.out.println(lm.toString());
		return lm;
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadStudentInfo" )
	public JsonResult uploadStudentInfo(HttpServletRequest request,
			@RequestParam("file") MultipartFile file,Model model)  {
		
		List<Map> lm = teacherService.uploadStudentInfo(file);
		
		return JsonResult.createBySuccessData(lm);
	}
	
	@RequestMapping(value = "/uploadStudentInfoForm" )
	public String uploadStudentInfoForm(Model model)  {
		return "uploadStudentInfoForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listCourse")
	public JSONObject listCourse(Model model,@RequestBody(required=false) Map<String,Object>  paramMap,
			int page,int limit,HttpSession session) {
		if(paramMap==null) {
			paramMap = new HashMap<>();
		}
		page = page-1;
		paramMap.put("rowFrom", page);
		paramMap.put("limit", limit);
		paramMap.put("userType", session.getAttribute("userType"));
		paramMap.put("userId", session.getAttribute("userId"));
		
		System.out.println(paramMap.toString());
		List<Map> lm = courseService.listCourseByUserType(paramMap);
		int count = courseService.selectNum(paramMap);
		System.out.println(lm.toString());
		JSONObject json = new JSONObject();
		json.put("data", lm);
		json.put("msg", "success");
		json.put("code", 0);
		json.put("count", count);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listTeacherByInstitute")
	public JsonResult  listTeacherByInstitute(Model model,@RequestBody Map<String,Object>  paramMap) {
		
		System.out.println(paramMap.toString());
		List<Map> lm = teacherService.listTeacherByInstitute(paramMap);
		if(lm!=null) return JsonResult.createBySuccessData(lm);
		return JsonResult.createBySuccess();
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/listTeacherByInstituteId")
	public JsonResult  listTeacherByInstituteId(Model model,@RequestBody Map<String,Object>  paramMap) {
		
		System.out.println(paramMap.toString());
		List<Map> lm = teacherService.listTeacherByInstituteId(paramMap);
		if(lm!=null) return JsonResult.createBySuccessData(lm);
		return JsonResult.createBySuccess();
		
	}
	
	

	
}
