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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.andy.common.JsonResult;
import com.andy.model.User;
import com.andy.service.TblClassService;
import com.andy.service.UserService;
import com.andy.utils.PageUtil;

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
	@RequestMapping(value = "/addClassForm")
	public String addClassForm(Model model) {
		return "tblClass/addClassForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listClassByInstitute")
	public JSONObject listClassByInstitute(Model model,@RequestBody(required=false) Map<String,Object>  paramMap,
			@RequestParam(required=false,value="className") String className ,
			@RequestParam(required=false,value="instituteId") String instituteId,
			HttpSession session) {
		if(paramMap==null) {
			paramMap = new HashMap<>();
		}
		
		paramMap.put("userType", session.getAttribute("userType"));
		if(className!=null&&!className.equals(""))  paramMap.put("className", className);
		if(instituteId!=null&&!instituteId.equals(""))  paramMap.put("instituteId", instituteId);
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
	public JSONObject listClass(Model model,
			@RequestParam(required=false,value="className") String className ,
			@RequestParam(required=false,value="instituteId") String instituteId,
			int page,int limit,HttpSession session) {
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
		User u = userService.selectByUserId((String) session.getAttribute("userId"));
		paramMap.put("instituteId", u.getInstituteId());
		if(className!=null&&!className.equals(""))  paramMap.put("className", className);
		if(instituteId!=null&&!instituteId.equals(""))  paramMap.put("instituteId", instituteId);
		System.out.println(paramMap.toString());
		List<Map> lm = classService.listClass(paramMap);
		int count = classService.listClassCountNum(paramMap);
		System.out.println(lm.toString());
		json.put("data", lm);
		json.put("count", count);
		json.put("msg", "success");
		json.put("code", 0);
		return json;
	}
	
	//code 0 ：成功   1：失败   2：已存在
	@ResponseBody
	@RequestMapping(value = "/addClass")
	public JsonResult addClass(HttpSession session,@RequestBody Map paramMap) {
		
		System.out.println(paramMap);
		
		if( classService.addNewClass(paramMap)==1) {
			return JsonResult.createBySuccess();
		}else {
			return JsonResult.createByError();
		}
		
	}
}
