package com.andy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.andy.service.StudentService;
import com.andy.service.TeacherService;

@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@ResponseBody
	@RequestMapping(value = "/listStudnets")
		//Map<String, Object> map = JSON.parseObject(json, new TypeReference<Map<String, Object>>(){});
	public JSONObject listStudnet(Model model,@RequestBody(required=false) Map<String,Object> paramMap,
			@RequestParam(required=false,value="userName") String userName,
			@RequestParam(required=false,value="userCode") String userCode,int page,int limit) {
		
		if(paramMap!=null) {
			System.out.println(paramMap);
		}else {
			paramMap = new HashMap<>();
		}
		page = page-1;
		paramMap.put("rowFrom", page);
		paramMap.put("limit", limit);
		if(userName!=null) {
			System.out.println("传过来的userName= "+userName);
			paramMap.put("userName", userName);
		}
		if(userCode!=null) {
			System.out.println("传过来的userCode= "+userCode);
			paramMap.put("userCode", userCode.toString());
		}
		
		List<Map<String, String>> lm =  studentService.listStudent(paramMap);
		int count = studentService.listStudentCountNum(paramMap);
		System.out.println(lm.toString());
		JSONObject json = new JSONObject();
		json.put("data", lm);
		json.put("count", count);
		json.put("msg", "success");
		json.put("code", 0);
		//json.put("count", count);
		return json;
	}
	
	
	@RequestMapping(value = "/listStudentInfoForm")
	public String listStudentInfoForm(Model model) {
		return "listStudentInfoForm";
	}
	
	
}
