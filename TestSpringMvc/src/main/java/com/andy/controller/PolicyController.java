package com.andy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.andy.model.Policy;
import com.andy.service.PolicyService;

@Controller
public class PolicyController {

	@Autowired
	PolicyService policyService;

	@ResponseBody
	@RequestMapping(value = "/selectPolicy", method = RequestMethod.GET)
	public JSONObject selectPolicy(Long policyId) {
		Policy p = policyService.selectPolicy(1L);
		
		/*ModelAndView mv = new ModelAndView("policy", "command", p);
		ModelMap model = mv.getModelMap();*/
		JSONObject json= new JSONObject();
		json.put("policy", p);
		return json;
	}
	
	
	@RequestMapping(value = "/index")
	public String index() {
		
		return "index";
	}
	
	//加上responseBody会返回内容
	@ResponseBody
	@RequestMapping(value = "/listPolicy", method = RequestMethod.GET)
	public JSONObject listPolicy() {
		List<Policy> lp = policyService.listPolicy();
		JSONObject json= new JSONObject();
		json.put("policy", lp);
		return json;
	}
	
}
