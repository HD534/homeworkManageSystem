package com.andy.controller;

import com.alibaba.fastjson.JSONObject;
import com.andy.service.StudentService;
import com.andy.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@ResponseBody
	@RequestMapping(value = "/listStudents")
	public JSONObject listStudent(Model model,
                                  @RequestParam(required=false,value="userName") String userName,
                                  @RequestParam(required=false,value="instituteId") String instituteId,
                                  @RequestParam(required=false,value="className") String className,
                                  @RequestParam(required=false,value="userCode") String userCode, int page, int limit) {
		
		Map	paramMap = new HashMap<>();
		JSONObject json = new JSONObject();
		int rowFrom = PageUtil.getRowFrom(page, limit);
		if(rowFrom<0) {
			json.put("code", 1);
			return json;
		}
		paramMap.put("rowFrom", rowFrom);
		paramMap.put("limit", limit);
		
		if(className!=null&&!className.equals(""))  paramMap.put("className", className);
		if(instituteId!=null&&!instituteId.equals(""))  paramMap.put("instituteId", instituteId);
		if(userName!=null&&!userName.equals(""))  paramMap.put("userName", userName);
		if(userCode!=null&&!userCode.equals(""))  paramMap.put("userCode", userCode);
		
		List<Map<String, String>> lm =  studentService.listStudent(paramMap);
		int count = studentService.listStudentCountNum(paramMap);
		System.out.println(lm.toString());
		json.put("data", lm);
		json.put("count", count);
		json.put("msg", "success");
		json.put("code", 0);
		//json.put("count", count);
		return json;
	}


	
}
