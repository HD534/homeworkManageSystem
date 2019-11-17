package com.andy.controller;

import com.alibaba.fastjson.JSONObject;
import com.andy.common.JsonResult;
import com.andy.service.CourseService;
import com.andy.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {
	
	@Autowired
	CourseService courseService;



	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/addCourse",method=RequestMethod.POST) public String
	 * addCourse(Model model, String courseName, String courseDesc, String
	 * instituteName) { System.out.println(courseName); return "sss"; }
	 */
	@ResponseBody
	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public JsonResult<Integer> addCourse(Model model, @RequestBody Map paramMap, HttpSession session) {
		
		if(paramMap==null||paramMap.size()<1) {
			return  JsonResult.createByError();
		}
		
		//如果是管理员添加课程，则教师是已被选定，即已存在teacherId，如果是教师自己添加课程，则把当前userId添加在map里面。
		if(!paramMap.containsKey("teacherId")) {
			String userId = (String) session.getAttribute("userId");
			paramMap.put("teacherId", userId);
		}
		
		int ret = courseService.insertCourseInfo(paramMap);
		
		if(ret == 0) {
			return JsonResult.createBySuccess();
		}else if(ret == 2){
			return  JsonResult.createByErrorCode(ret);
		}
		
		return  JsonResult.createByError();
		
	}
	
/*	@ResponseBody
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
*/	
	@ResponseBody
	@RequestMapping(value = "/listCourseByUserType")
	public JSONObject listCourseByPage(Model model,
                                       int page, int limit, HttpSession session,
                                       @RequestParam(required=false,value="className") String className ,
                                       @RequestParam(required=false,value="instituteId") String instituteId,
                                       @RequestParam(required=false,value="courseName") String courseName) {
		Map paramMap = new HashMap<>();
		JSONObject json = new JSONObject();
		int rowFrom = PageUtil.getRowFrom(page, limit);
		if(rowFrom<0) {
			json.put("code", 1);
			return json;
		}
		paramMap.put("rowFrom", rowFrom);
		paramMap.put("limit", limit);
		paramMap.put("userType", session.getAttribute("userType"));
		paramMap.put("userId", session.getAttribute("userId"));
		if(courseName!=null&&!courseName.equals(""))  paramMap.put("courseName", courseName);
		if(className!=null&&!className.equals(""))  paramMap.put("className", className);
		if(instituteId!=null&&!instituteId.equals(""))  paramMap.put("instituteId", instituteId);
		System.out.println(paramMap.toString());
		List<Map> lm = courseService.listCourseByUserType(paramMap);
		int count = courseService.selectNum(paramMap);
		System.out.println(lm.toString());
		json.put("data", lm);
		json.put("msg", "success");
		json.put("code", 0);
		json.put("count", count);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/assignClassCourse")
	public JSONObject assignClassCourse(Model model, @RequestBody Map<String,Object>  paramMap, HttpSession session) {
		String courseId = (String) paramMap.get("courseId");
		String classId = (String) paramMap.get("classId");
		
		System.out.println("-------------------------------");
		System.out.println("classid:  "+classId+"courseId:  "+courseId);
		System.out.println("-------------------------------");
		int ret = courseService.assignClassCourse(courseId, classId);
		JSONObject json = new JSONObject();
		if(ret==1) {
			json.put("data", ret);
			json.put("msg", "success");
			json.put("code", 0);
		}else {
			json.put("msg", "success");
			json.put("code", 1);
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listClassCourse")
	public JSONObject listClassCourse(HttpSession session,
			@RequestParam(required=false,value="className") String className ,
			@RequestParam(required=false,value="instituteId") String instituteId,
			@RequestParam(required=false,value="courseName") String courseName) {
		String userId = (String) session.getAttribute("userId");
		String userType = (String) session.getAttribute("userType");
		Map paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("userType", userType);
		List<Map> classCourseList = courseService.listClassCourse(paramMap);
		int count = courseService.listClassCourseNum(paramMap);
		JSONObject json = new JSONObject();
		json.put("data", classCourseList);
		json.put("msg", "success");
		json.put("code", 0);
		json.put("count", count);
		return json;
		
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
