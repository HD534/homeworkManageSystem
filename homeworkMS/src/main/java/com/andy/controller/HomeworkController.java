package com.andy.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.andy.common.JsonResult;
import com.andy.service.AttachedFileService;
import com.andy.service.HomeworkService;
import com.andy.utils.DateUtils;
import com.andy.utils.UUIDUtils;

@Controller
public class HomeworkController {
	
	@Autowired
	HomeworkService homeworkSevice;
	

	@Autowired
	AttachedFileService attachedFileService;

	@RequestMapping(value = "/listHomeworkForm")
	public String listHomeworkForm() {
		return "homework/listHomeworkForm";
	}
	
	@RequestMapping(value = "/listCourseHomeworkForm")
	public String listCourseHomeworkForm() {
		return "homework/listCourseHomeworkForm";
	}

	@RequestMapping(value = "/addHomeworkForm")
	public String addHomeworkForm() {
		return "homework/addHomeworkForm";
	}
	
	@RequestMapping(value = "/addCourseHomeworkForm")
	public String addCourseHomeworkForm() {
		return "homework/addCourseHomeworkForm";
	}
	
	@RequestMapping(value = "/uploadCourseHomeworkForm")
	public String uploadCourseHomeworkForm() {
		return "homework/uploadCourseHomeworkForm";
	}
	@RequestMapping(value = "/listStudentHomeworkForm")
	public String listStudentHomeworkForm() {
		return "homework/listStudentHomeworkForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listHomework")
	public JsonResult listHomework(HttpSession session, int page,int limit,
			@RequestParam(required=false,value="termValue") String termValue ,
			@RequestParam(required=false,value="userName") String userName,
			@RequestParam(required=false,value="instituteId") String instituteId,
			@RequestParam(required=false,value="courseName") String courseName,
			@RequestParam(required=false,value="courseId") String courseId) {
		
		String userId = (String) session.getAttribute("userId");
		String userType = (String) session.getAttribute("userType");
		page = page-1;
		Map paramMap= new HashMap<>();
		paramMap.put("rowFrom", page);
		paramMap.put("limit", limit);
		paramMap.put("userId", userId);
		paramMap.put("userType", userType);
		if(termValue!=null&&!termValue.equals("")) paramMap.put("termValue", termValue);
		if(userName!=null&&!userName.equals("")) paramMap.put("userName", userName);
		if(instituteId!=null&&!instituteId.equals("")) paramMap.put("instituteId", instituteId);
		if(courseName!=null&&!courseName.equals("")) paramMap.put("courseName", courseName);
		if(courseId!=null&&!courseId.equals("")) paramMap.put("courseId", courseId);
		List<Map> homeworkList = homeworkSevice.listHomework(paramMap);
		return JsonResult.createBySuccessData(homeworkList);
	}
	@ResponseBody
	@RequestMapping(value = "/listStudentHomework")
	public JsonResult listStudentHomework(HttpSession session, int page,int limit,
			@RequestParam(required=false,value="termValue") String termValue ,
			@RequestParam(required=false,value="userName") String userName,
			@RequestParam(required=false,value="homeworkName") String homeworkName,
			@RequestParam(value="homeworkId") String homeworkId,
			@RequestParam(required=false,value="courseName") String courseName,
			@RequestParam(required=false,value="userCode") String userCode,
			@RequestParam(required=false,value="courseId") String courseId) {
		
		String userId = (String) session.getAttribute("userId");
		String userType = (String) session.getAttribute("userType");
		page = page-1;
		Map paramMap= new HashMap<>();
		paramMap.put("rowFrom", page);
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
		
		List<Map> homeworkList = homeworkSevice.listStudentHomework(paramMap);
		return JsonResult.createBySuccessData(homeworkList);
	}

	@ResponseBody
	@RequestMapping("/addHomework")
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
			if(courseId==null||homeworkName==null||homeworkDueDate==null) return JsonResult.createByErrorCode(-1);
			if(homeworkDesc==null) homeworkDesc="";
			String userId = (String) session.getAttribute("userId");
			Map paramMap = new HashMap();
			paramMap.put("homeworkDesc", homeworkDesc);
			//if(termValue!=null&&!termValue.equals(""))paramMap.put("termValue", termValue);
			if(courseId!=null&&!courseId.equals(""))paramMap.put("courseId", courseId);
			if(homeworkName!=null&&!homeworkName.equals(""))paramMap.put("homeworkName", homeworkName);
			if(homeworkDueDate!=null&&!homeworkDueDate.equals(""))paramMap.put("homeworkDueDate", homeworkDueDate);
			if(userId!=null&&!userId.equals(""))paramMap.put("userId", userId);
			MultipartFile file = multiRequest.getFile("file");
			System.out.println(file.getOriginalFilename());
			
			JsonResult fileJr = attachedFileService.saveOneFile(file, userId);
			if(fileJr.getcode()==1) return fileJr;
			String fileId = (String) fileJr.getData();
			paramMap.put("fileId", fileId);
			return homeworkSevice.insertHomework(paramMap);
			
		/*	while(iter.hasNext()){
				//这是前台name字段的名字，比如这里是filename
				String filename = (String) iter.next();
				
				//根据name字段来获得MultipartFile
				file = multiRequest.getFile(filename);
				
				//如果file不为空，就表示是一个MultipartFile文件
				//这里可不判断，因为上面已经判断了有文件类型
				if(file != null){
					System.out.println(file.getOriginalFilename());
					
				}
			}*/
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
	public JsonResult insertStudentHomeworkScore(Model model, @RequestBody Map paramMap,HttpSession session) {
		
		if(paramMap==null||paramMap.size()<1) {
			return  JsonResult.createByError();
		}
		
		String studentId = (String) paramMap.get("studentId");
		String homeworkId = (String) paramMap.get("homeworkId");
		
		int score = Integer.parseInt((String)paramMap.get("score"));
		
		//参数错误
		if(studentId==null||homeworkId==null) return  JsonResult.createByError();
		
		String comment = (String) paramMap.get("comment");
		String currentUserId = (String) session.getAttribute("userId");
		paramMap.put("currentUserId", currentUserId);
		
		int ret = homeworkSevice.insertStudentHomeworkScore(paramMap);
		//int ret = 1;//homeworkSevice.insertStudentHomeworkScore(paramMap);
		
		return ret == 1?JsonResult.createBySuccess():JsonResult.createByError();
		
		
		
		
	}
	
	
	
	
}
