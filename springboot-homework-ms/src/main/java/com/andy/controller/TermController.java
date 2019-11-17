package com.andy.controller;

import com.alibaba.fastjson.JSONObject;
import com.andy.common.JsonResult;
import com.andy.model.Term;
import com.andy.service.TermService;
import com.andy.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

	//code 0 ：成功   1：失败   2：已存在
	@PostMapping("addTerm")
	public @ResponseBody JsonResult insertTerm(@RequestBody Term term){
		if (term==null || StringUtils.isEmpty(term.getTermName()) || StringUtils.isEmpty(term.getTermValue())){
			return JsonResult.createByErrorCode(1);
		}
		if (termService.selectByTermValue(term.getTermValue())!=null||termService.selectByTermName(term.getTermName())!=null){
			return JsonResult.createByErrorCode(2);
		}
		term.setCreateDate(new Date());
		term.setTermId(UUIDUtils.getUUID());
		termService.insertTerm(term);
		return JsonResult.createBySuccess();
	}

}
