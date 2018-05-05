package com.andy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.andy.service.TermService;

@Controller
public class TermController {
	
	@Autowired
	TermService termService;
	
	@ResponseBody
	@RequestMapping(value = "/getTermList")
	public JSONObject getTermList() {
		List<Map> termList = termService.listTerm();
		JSONObject json = new JSONObject();
		json.put("data", termList);
		json.put("msg", "success");
		json.put("code", 0);
		return json;
	}

}
