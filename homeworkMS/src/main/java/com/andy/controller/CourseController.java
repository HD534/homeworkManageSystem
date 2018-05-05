package com.andy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.andy.common.JsonResult;
import com.andy.model.Course;
import com.andy.service.CourseService;
import com.andy.utils.JsonResultUtil;

@Controller
public class CourseController {
	
	@Autowired
	CourseService courseService;

	@RequestMapping(value = "/listCourseInfoForm")
	public String listCourseInfoForm() {
		return "course/listCourseInfoForm";
	}

	@RequestMapping(value = "/addCourseForm")
	public String addCourseForm(Model model) {
		return "course/addCourseForm";
	}
	
	@RequestMapping(value = "/assignCourseForm")
	public String assignCourseForm(Model model) {
		return "course/assignCourseForm";
	}
	
	@RequestMapping(value = "/classCourseForm")
	public String classCourseForm(Model model) {
		return "tblClass/classCourseForm";
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/addCourse",method=RequestMethod.POST) public String
	 * addCourse(Model model, String courseName, String courseDesc, String
	 * instituteName) { System.out.println(courseName); return "sss"; }
	 */
	@ResponseBody
	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public JsonResult<Integer> addCourse(Model model, @RequestBody Map paramMap,HttpSession session) {
		
		if(paramMap==null||paramMap.size()<1) {
			return  JsonResult.createByError();
		}
		
		//如果是管理员添加课程，则教师已经选定，不用再去数据库查询
		if(paramMap.containsKey("teacherName")) {
			String teacherName = (String) paramMap.get("teacherName");
		}
		
		String userId = (String) session.getAttribute("userId");
		
		paramMap.put("userId", userId);
		
		int ret = courseService.insertCourseInfo(paramMap);
		
		if(ret == 0) {
			return JsonResult.createBySuccess();
		}else if(ret == 2){
			return  JsonResult.createByErrorCode(ret);
		}
		
		return  JsonResult.createByError();
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/listCourseByUserType")
	public JSONObject listCourseByPage(Model model,@RequestBody(required=false) Map<String,Object>  paramMap,
			int page,int limit,HttpSession session,@RequestBody(required=false) String courseName) {
		if(paramMap==null) {
			paramMap = new HashMap<>();
		}
		page = page-1;
		paramMap.put("rowFrom", page);
		paramMap.put("limit", limit);
		paramMap.put("userType", session.getAttribute("userType"));
		paramMap.put("userId", session.getAttribute("userId"));
		if(courseName!=null) paramMap.put("courseName", courseName);
		
		System.out.println(paramMap.toString());
		List<Map> lm = courseService.listCourse(paramMap);
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
	@RequestMapping(value = "/assignClassCourse")
	public JSONObject assignClassCourse(Model model,@RequestBody Map<String,Object>  paramMap,HttpSession session) {
		String courseId = (String) paramMap.get("courseId");
		String classId = (String) paramMap.get("classId");
		
		System.out.println("-------------------------------");
		System.out.println("classid:  "+classId+"courseId:  "+courseId);
		System.out.println("-------------------------------");
		int ret = courseService.assignClassCourse(courseId, classId);
		JSONObject json = new JSONObject();
		json.put("data", ret);
		json.put("msg", "success");
		json.put("code", 0);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listClassCourse")
	public JsonResult listClassCourse(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		String userType = (String) session.getAttribute("userType");
		Map paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("userType", userType);
		List<Map> userInfoList = courseService.listClassCourse(paramMap);
		return JsonResult.createBySuccessData(userInfoList);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/listCourseByTypeAndTerm")
	public JsonResult listCourseByTypeAndTerm(HttpSession session,@RequestBody(required=false) Map<String,Object> paramMap) {
		String userId = (String) session.getAttribute("userId");
		String userType = (String) session.getAttribute("userType");
		if(paramMap==null||paramMap.size()==0) return JsonResult.createByError();
		paramMap.put("userId", userId);
		paramMap.put("userType", userType);
		List<Map> courseList = courseService.listCourseByParamMap(paramMap);
		return JsonResult.createBySuccessData(courseList);
	}

}
