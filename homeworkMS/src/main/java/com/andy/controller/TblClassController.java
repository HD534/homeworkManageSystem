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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.andy.model.User;
import com.andy.service.TblClassService;
import com.andy.service.UserService;

@Controller
public class TblClassController {
	
	@Autowired 
	TblClassService classService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/listClassForm")
	public String listClassForm(Model model) {
		return "tblClass/listClassForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listClassByInstitute")
	public JSONObject listClassByInstitute(Model model,@RequestBody(required=false) Map<String,Object>  paramMap,
			HttpSession session) {
		if(paramMap==null) {
			paramMap = new HashMap<>();
		}
		
		paramMap.put("userType", session.getAttribute("userType"));
		
		System.out.println(paramMap.toString());
		List<Map> lm = classService.listClassByInstitute(paramMap);
		System.out.println(lm.toString());
		JSONObject json = new JSONObject();
		json.put("data", lm);
		json.put("msg", "success");
		json.put("code", 0);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listClass")
	public JSONObject listClass(Model model,@RequestBody(required=false) Map<String,Object>  paramMap,
			int page,int limit,HttpSession session) {
		if(paramMap==null) {
			paramMap = new HashMap<>();
		}
		page = page-1;
		paramMap.put("rowFrom", page);
		paramMap.put("limit", limit);
		paramMap.put("userType", session.getAttribute("userType"));
		User u = userService.selectByUserId((String) session.getAttribute("userId"));
		paramMap.put("instituteId", u.getInstituteId());
		System.out.println(paramMap.toString());
		List<Map> lm = classService.listClass(paramMap);
		int count = classService.listClassCountNum(paramMap);
		System.out.println(lm.toString());
		JSONObject json = new JSONObject();
		json.put("data", lm);
		json.put("count", count);
		json.put("msg", "success");
		json.put("code", 0);
		return json;
	}
}
