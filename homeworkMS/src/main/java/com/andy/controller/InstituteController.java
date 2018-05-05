package com.andy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.andy.model.Institute;
import com.andy.service.InstituteService;

@Controller
public class InstituteController {
	
	@Autowired
	InstituteService instituteService;
	
	@ResponseBody
	@RequestMapping(value = "/listInstitute")
	public JSONObject listInstitute() {
		List<Institute> li = instituteService.listInstitute();
		JSONObject json = new JSONObject();
		json.put("data", li);
		json.put("msg", "success");
		json.put("code", 0);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserInstitute")
	public JSONObject getUserInstitute(HttpSession session) {
		Map<String,String> map = new HashMap<>();
		map.put("userId", (String) session.getAttribute("userId"));
		map.put("userType", (String) session.getAttribute("userType"));
		List<Map> ins = instituteService.selectUserInstitute(map);
		JSONObject json = new JSONObject();
		json.put("data", ins);
		json.put("msg", "success");
		json.put("code", 0);
		return json;
	}

}
