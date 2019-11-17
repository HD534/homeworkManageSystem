package com.andy.controller;

import com.andy.common.JsonResult;
import com.andy.service.CourseService;
import com.andy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	CourseService courseService;
	
	@ResponseBody
	@RequestMapping(value = "/listStudnetByCondition")
		//Map<String, Object> map = JSON.parseObject(json, new TypeReference<Map<String, Object>>(){});
	public List<Map> listStudnet(Model model, @RequestBody Map<String,Object>   paramMap) {
		
		System.out.println(paramMap.toString());
		List<Map> lm = teacherService.listStudentsByConditions(paramMap);
		System.out.println(lm.toString());
		return lm;
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadStudentInfo" )
	public JsonResult uploadStudentInfo(HttpServletRequest request,
                                        @RequestParam("file") MultipartFile file, Model model)  {
		
		List<Map> lm = teacherService.uploadStudentInfo(file);
		
		return JsonResult.createBySuccessData(lm);
	}
	

	@ResponseBody
	@RequestMapping(value = "/listTeacherByInstitute")
	public JsonResult  listTeacherByInstitute(Model model, @RequestBody Map<String,Object>  paramMap) {
		
		System.out.println(paramMap.toString());
		List<Map> lm = teacherService.listTeacherByInstitute(paramMap);
		if(lm!=null) return JsonResult.createBySuccessData(lm);
		return JsonResult.createBySuccess();
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/listTeacherByInstituteId")
	public JsonResult  listTeacherByInstituteId(Model model, @RequestBody Map<String,Object>  paramMap) {
		
		System.out.println(paramMap.toString());
		List<Map> lm = teacherService.listTeacherByInstituteId(paramMap);
		if(lm!=null) return JsonResult.createBySuccessData(lm);
		return JsonResult.createBySuccess();
		
	}
	
	

	
}
