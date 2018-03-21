package com.andy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.andy.Util.UserValidater;
import com.andy.model.User;
import com.andy.service.UserService;
import com.andy.service.impl.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	//@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@Validated User user,Errors error) {
		if(error.hasFieldErrors()) return "register";
		System.out.println(user.toString());
		
		return "index";
	}
	
	

	@RequestMapping(value = "/registerForm", method = RequestMethod.GET)
	public String registerForm(Model model) {
		User user = new User("name1","pass1",11,"男");
		model.addAttribute("user", user);
		return "register";
		
	}
	
	@RequestMapping(value = "/testLayui")
	public String testLayui(Model model){
		return "testLayui";
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkUserName")
	public String checkUserName(String username,ModelAndView mv){
		System.out.println(username);
		int ret = userService.checkUserName(username);
		return ret+"";
	}
	
	@ResponseBody
	@RequestMapping(value = "/login" ,method = RequestMethod.POST)
	public String doLogin(String username,String password,Model model,HttpSession session) {
		System.out.println(username+" -- "+password);
		int ret = userService.doLogin(username, password);
		//session addAttribute
		User u = new User();
		u.setUsername(username);
		session.setAttribute("user", u);
		return ret+"";
	}
	
	@RequestMapping(value = "/loginForm")
	public String doLogin(Model model) {
		return "loginForm";
	}
	
	@RequestMapping(value = "/layuiLogin")
	public String layuiLogin(Model model) {
		return "layuiLogin";
	}
	
	@RequestMapping(value = "/layuiUpload")
	public String layuiUpload(Model model) {
		return "layuiUpload";
	}
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.setValidator(new UserValidater());
	}
	
	
	
}
