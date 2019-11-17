package com.andy.controller;

import com.alibaba.fastjson.JSONObject;
import com.andy.common.JsonResult;
import com.andy.service.AttachedFileService;
import com.andy.service.HomeworkService;
import com.andy.utils.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeworkController {
	
	@Autowired
	HomeworkService homeworkSevice;
	

	@Autowired
	AttachedFileService attachedFileService;


	@ResponseBody
	@RequestMapping(value = "/listHomework")
	public JSONObject listHomework(HttpSession session, int page,int limit,
			@RequestParam(required=false,value="termValue") String termValue ,
			@RequestParam(required=false,value="userName") String userName,
			@RequestParam(required=false,value="instituteId") String instituteId,
			@RequestParam(required=false,value="courseName") String courseName,
			@RequestParam(required=false,value="className") String className,
			@RequestParam(required=false,value="courseId") String courseId) {
		
		String userId = (String) session.getAttribute("userId");
		String userType = (String) session.getAttribute("userType");
		Map paramMap= new HashMap<>();
		JSONObject json = new JSONObject();
		int rowFrom = PageUtil.getRowFrom(page, limit);
		if(rowFrom<0) {
			json.put("code", 1);
			return json;
		}
		paramMap.put("rowFrom", rowFrom);
		paramMap.put("limit", limit);
		paramMap.put("userId", userId);
		paramMap.put("userType", userType);
		if(termValue!=null&&!termValue.equals("")) paramMap.put("termValue", termValue);
		if(userName!=null&&!userName.equals("")) paramMap.put("userName", userName);
		if(instituteId!=null&&!instituteId.equals("")) paramMap.put("instituteId", instituteId);
		if(courseName!=null&&!courseName.equals("")) paramMap.put("courseName", courseName);
		if(className!=null&&!className.equals("")) paramMap.put("className", className);
		if(courseId!=null&&!courseId.equals("")) paramMap.put("courseId", courseId);
		List<Map> homeworkList = homeworkSevice.listHomework(paramMap);
		json.put("data", homeworkList);
		json.put("msg", "success");
		json.put("code", 0);
		json.put("count", homeworkSevice.listHomeworkCount(paramMap));
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listStudentHomework")
	public JSONObject listStudentHomework(HttpSession session, int page,int limit,
			@RequestParam(required=false,value="termValue") String termValue ,
			@RequestParam(required=false,value="userName") String userName,
			@RequestParam(required=false,value="homeworkName") String homeworkName,
			@RequestParam(value="homeworkId") String homeworkId,
			@RequestParam(required=false,value="courseName") String courseName,
			@RequestParam(required=false,value="className") String className,
			@RequestParam(required=false,value="userCode") String userCode,
			@RequestParam(required=false,value="courseId") String courseId) {
		
		String userId = (String) session.getAttribute("userId");
		String userType = (String) session.getAttribute("userType");
		Map paramMap= new HashMap<>();
		JSONObject json = new JSONObject();
		int rowFrom = PageUtil.getRowFrom(page, limit);
		if(rowFrom<0) {
			json.put("code", 1);
			return json;
		}
		paramMap.put("rowFrom", rowFrom);
		paramMap.put("limit", limit);
		paramMap.put("userId", userId);
		paramMap.put("userType", userType);
		if(termValue!=null&&!termValue.equals("")) paramMap.put("termValue", termValue);
		if(userName!=null&&!userName.equals("")) paramMap.put("userName", userName);
		if(courseName!=null&&!courseName.equals("")) paramMap.put("courseName", courseName);
		if(homeworkName!=null&&!homeworkName.equals("")) paramMap.put("homeworkName", homeworkName);
		if(userCode!=null&&!userCode.equals("")) paramMap.put("userCode", userCode);
		if(courseId!=null&&!courseId.equals("")) paramMap.put("courseId", courseId);
		if(homeworkId!=null&&!homeworkId.equals("")) paramMap.put("homeworkId", homeworkId);
		if(className!=null&&!className.equals("")) paramMap.put("className", className);
		
		List<Map> homeworkList = homeworkSevice.listStudentHomework(paramMap);
		json.put("data", homeworkList);
		json.put("msg", "success");
		json.put("code", 0);
		json.put("count", homeworkSevice.listStudentHomeworkCount(paramMap));
		return json;
	}

	@ResponseBody
	@PostMapping("/addHomework")
	public JsonResult addHomework( HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		//System.out.println(request.getParameter("username"));
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断request中是否有multipartResolver类型数据，有就表示有文件类型
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			//获得文件名，这里是迭代器封装
			/*Iterator iter = multiRequest.getFileNames();*/
			String homeworkDesc = (String) multiRequest.getParameter("homeworkDesc");
			//String termValue = (String) multiRequest.getParameter("termValue");
			String courseId = (String) multiRequest.getParameter("courseId");
			String homeworkName = (String) multiRequest.getParameter("homeworkName");
			String homeworkDueDate = (String) multiRequest.getParameter("homeworkDueDate");
			//参数错误
			if(StringUtils.isEmpty(courseId)||StringUtils.isEmpty(homeworkName)||StringUtils.isEmpty(homeworkDueDate))
				return JsonResult.createByErrorCode(-1);
			if(homeworkDesc==null) homeworkDesc="";
			String userId = (String) session.getAttribute("userId");
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("homeworkDesc", homeworkDesc);
			//if(termValue!=null&&!termValue.equals(""))paramMap.put("termValue", termValue);
			paramMap.put("courseId", courseId);
			paramMap.put("homeworkName", homeworkName);
			paramMap.put("homeworkDueDate", homeworkDueDate);
			paramMap.put("userId", userId);
			MultipartFile file = multiRequest.getFile("file");
			System.out.println(file.getOriginalFilename());
			
			JsonResult fileJr = attachedFileService.saveOneFile(file, userId);
			if(fileJr.getcode()==1) return fileJr;
			String fileId = (String) fileJr.getData();
			paramMap.put("fileId", fileId);
			return homeworkSevice.insertHomework(paramMap);

		}else {
			return JsonResult.createByError();
		}

	}
	
	@ResponseBody
	@RequestMapping("/uploadHomework")
	public JsonResult uploadHomework( HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		System.out.println(request.getParameter("username"));
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断request中是否有multipartResolver类型数据，有就表示有文件类型
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			
			String homeworkDesc = (String) multiRequest.getParameter("homeworkDesc");
			
			String homeworkId = (String) multiRequest.getParameter("homeworkId");
			String homeworkName = (String) multiRequest.getParameter("homeworkName");
			//参数错误
			if(homeworkName==null||homeworkId==null) return JsonResult.createByErrorCode(-1);
			if(homeworkDesc==null) homeworkDesc="";
			String userId = (String) session.getAttribute("userId");
			Map paramMap = new HashMap();
			paramMap.put("homeworkDesc", homeworkDesc);
			paramMap.put("homeworkName", homeworkName);
			paramMap.put("homeworkId", homeworkId);
			paramMap.put("userId", userId);
			MultipartFile file = multiRequest.getFile("file");
			System.out.println(file.getOriginalFilename());
			
			JsonResult fileJr = attachedFileService.saveOneFile(file, userId);
			if(fileJr.getcode()==1) return fileJr;
			String fileId = (String) fileJr.getData();
			paramMap.put("fileId", fileId);
			return homeworkSevice.uploadHomework(paramMap);
			
		}
		return JsonResult.createBySuccess();
		
	}
	
	//插入学生作业的批改情况数据
	@ResponseBody
	@RequestMapping(value = "/insertStudentHomeworkScore", method = RequestMethod.POST)
	public JsonResult insertStudentHomeworkScore(Model model, @RequestBody Map paramMap, HttpSession session) {
		
		if(paramMap==null||paramMap.size()<1) {
			return  JsonResult.createByError();
		}
		
		String studentId = (String) paramMap.get("studentId");
		String homeworkId = (String) paramMap.get("homeworkId");
		System.out.println(paramMap.get("score").equals(Integer.class));
		
		String sc = paramMap.get("score").toString();
		int score = Integer.parseInt(sc);
		
		//参数错误
		if(studentId==null||homeworkId==null) return  JsonResult.createByError();
		
		String comment = (String) paramMap.get("comment");
		String currentUserId = (String) session.getAttribute("userId");
		paramMap.put("currentUserId", currentUserId);
		
		int ret = homeworkSevice.insertStudentHomeworkScore(paramMap);
		//int ret = 1;//homeworkSevice.insertStudentHomeworkScore(paramMap);
		
		return ret == 1?JsonResult.createBySuccess():JsonResult.createByError();
	}
	
	@ResponseBody
	@RequestMapping(value = "/listHomeworkScoreInfo")
	public JSONObject listHomeworkScoreInfo(HttpSession session, int page,int limit,
			@RequestParam(required=false,value="termValue") String termValue ,
			@RequestParam(required=false,value="userName") String userName,
			@RequestParam(required=false,value="homeworkName") String homeworkName,
			@RequestParam(required=false,value="courseName") String courseName,
			@RequestParam(required=false,value="className") String className,
			@RequestParam(required=false,value="userCode") String userCode,
			@RequestParam(required=false,value="instituteId") String instituteId) {
		
		String userId = (String) session.getAttribute("userId");
		String userType = (String) session.getAttribute("userType");
		Map paramMap= new HashMap<>();
		JSONObject json = new JSONObject();
		int rowFrom = PageUtil.getRowFrom(page, limit);
		if(rowFrom<0) {
			json.put("code", 1);
			return json;
		}
		paramMap.put("rowFrom", rowFrom);
		paramMap.put("limit", limit);
		paramMap.put("userId", userId);
		paramMap.put("userType", userType);
		if(termValue!=null&&!termValue.equals("")) paramMap.put("termValue", termValue);
		if(userName!=null&&!userName.equals("")) paramMap.put("userName", userName);
		if(courseName!=null&&!courseName.equals("")) paramMap.put("courseName", courseName);
		if(homeworkName!=null&&!homeworkName.equals("")) paramMap.put("homeworkName", homeworkName);
		if(className!=null&&!className.equals("")) paramMap.put("className", className);
		if(userCode!=null&&!userCode.equals("")) paramMap.put("userCode", userCode);
		if(instituteId!=null&&!instituteId.equals("")) paramMap.put("instituteId", instituteId);
		
		List<Map> homeworkList = homeworkSevice.listHomeworkScoreInfo(paramMap);
		int count = homeworkSevice.listHomeworkScoreCount(paramMap);
		json.put("data", homeworkList);
		json.put("msg", "success");
		json.put("code", 0);
		json.put("count", count);
		return json;
	}
	
	
	
}
