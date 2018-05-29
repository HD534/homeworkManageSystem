package com.andy.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.andy.utils.PageUtil;
import com.andy.utils.UUIDUtils;
import com.andy.utils.UserValidater;
import com.alibaba.fastjson.JSONObject;
import com.andy.common.JsonResult;
import com.andy.model.User;
import com.andy.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	/*@RequestMapping(value="/login",method=RequestMethod.POST)
	public String doLogin(@RequestBody User user,Model model, HttpSession session) {
		System.out.println(user.toString());
		return "login";
		
	}*/
	

	// @ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@Validated User user, Errors error) {
		if (error.hasFieldErrors())
			return "register";
		System.out.println(user.toString());

		return "index";
	}

	@RequestMapping(value = "/registerForm", method = RequestMethod.GET)
	public String registerForm(Model model) {
		return "register";
	}

	@RequestMapping(value = "/DashBoard")
	public String testLayui(Model model) {
		return "DashBoard";
	}

	@ResponseBody
	@RequestMapping(value = "/checkUserName")
	public String checkUserName(String username, ModelAndView mv) {
		System.out.println(username);
		int ret = userService.checkUserName(username);
		return ret + "";
	}

	@ResponseBody
	@RequestMapping(value = "/login")
	public int doLogin(@RequestBody User user, Model model, HttpSession session) {
		System.out.println("接收到的 :"+user.toString());
		//User u = userService.selectByUserCode(user.getUserCode());
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("userCode", user.getUserCode());
		paramMap.put("userType", user.getUserType());
		
		User u = userService.selectByUserCodeAndType(paramMap);
		if (u == null) {
			System.out.println("-----------");
			System.out.println("usercode is wrong");
			System.out.println("-----------");
			return -2;
		} else {
			if (u.getPassword().equals(user.getPassword())) {
				System.out.println("userType = "+u.getUserType());
				session.setAttribute("userType", u.getUserType());
				session.setAttribute("userId",u.getUserId());
				session.setAttribute("userName",u.getUserName());
				session.setAttribute("userCode",u.getUserCode());
				return 0;
			} else {
				System.out.println("-----------");
				System.out.println("password is wrong");
				System.out.println("-----------");
				return -1;
			}
		}
		
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/login")
	public int doLogin(@RequestParam String userName,@RequestParam String userType,@RequestParam String password, Model model, HttpSession session) {
		System.out.println("接收到的 :"+userName);
		User u = userService.getUserByUserName(userName, password);
		if (u == null) {
			System.out.println("-----------");
			System.out.println("username is wrong");
			System.out.println("-----------");
			return -2;
		} else {
			if (u.getPassword().equals(password)) {
				// session addAttribute
				session.setAttribute("userType", u.getUserType());
				session.setAttribute("userId",u.getUserId());
				session.setAttribute("userName",u.getUserName());
				System.out.println("数据库查到的  "+u.getUserName());
				return 0;
			} else {
				System.out.println("-----------");
				System.out.println("password is wrong");
				System.out.println("-----------");
				return -1;
			}
		}
		
	}*/
	
	@RequestMapping(value = "/logout")
	public void logout(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception{
		session.invalidate();
		response.setHeader("Pragma", "No-cache");  
		response.setHeader("Cache-Control", "no-cache");  
		response.setDateHeader("Expires", 0);  
		request.getRequestDispatcher("mainLogin").forward(request, response);
	}
	
	@RequestMapping(value = "/")
	public String welcomePage() {
		return "mainLogin";
	}




	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		return "mainLogin";
	}

	@RequestMapping(value = "/layuiLogin")
	public String layuiLogin() {
		return "mainLogin";
	}

	@RequestMapping(value = "/layuiUpload")
	public String layuiUpload() {
		return "layuiUpload";
	}
	
	@RequestMapping(value = "/mainLogin")
	public String mainLogin() {
		return "mainLogin";
	}
	
	@RequestMapping(value = "/testAddForm")
	public String testAddForm() {
		return "testAddForm";
	}
	
	@RequestMapping(value = "/addForm")
	public String addForm() {
		return "addForm";
	}
	
	@RequestMapping(value = "/listUserForm")
	public String listUserForm() {
		return "user/listUserForm";
	}
	
	
	
	@RequestMapping(value = "/index")
	public String index() {
		
		return "testView/index";
	}
	
	@RequestMapping(value = "/userInfoForm")
	public String userInfoForm() {
		return "user/userInfoForm";
	}
	
	@RequestMapping(value = "/addUserForm")
	public String addUserForm() {
		return "user/addUserForm";
	}
	
	@RequestMapping(value = "/editUserForm")
	public String editUserForm() {
		return "user/editUserForm";
	}
	
	@RequestMapping(value = "/editUserPasswordForm")
	public String editUserPasswordForm() {
		return "user/editUserPasswordForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserInfo")
	public JsonResult getUserInfo(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		String userType = (String) session.getAttribute("userType");
		Map paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("userType", userType);
		Map userInfo = userService.getUserInfoByUserId(paramMap);
		return JsonResult.createBySuccessData(userInfo);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserInfoByUserId")
	public JsonResult getUserInfoByUserId(HttpSession session,String userId) {
		Map paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		Map userInfo = userService.getUserInfoByUserId(paramMap);
		return JsonResult.createBySuccessData(userInfo);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/listUser")
	public JSONObject listUser(HttpSession session,int page,int limit,
			@RequestParam(required=false,value="userName") String userName ,
			@RequestParam(required=false,value="userCode") String userCode ,
			@RequestParam(required=false,value="userType") String userType,
			@RequestParam(required=false,value="email") String email) {
		//获取当前用户id
		String userId = (String) session.getAttribute("userId");
 		Map paramMap = new HashMap<>();
 		JSONObject json = new JSONObject();
 		//分页操作
		int rowFrom = PageUtil.getRowFrom(page, limit);
		if(rowFrom<0) {
			json.put("code", 1);
			return json;
		}
		paramMap.put("rowFrom", rowFrom);
		paramMap.put("limit", limit);
		//添加查询条件
		if(userName!=null&&!userName.equals(""))  paramMap.put("userName", userName);
		if(userCode!=null&&!userCode.equals(""))  paramMap.put("userCode", userCode);
		if(userType!=null&&!userType.equals(""))  paramMap.put("userType", userType);
		if(email!=null&&!email.equals(""))  paramMap.put("email", email);
		List<Map> userInfolist = userService.listUser(paramMap);
		int count = userService.listUserCountNum(paramMap);
		json.put("data", userInfolist);
		json.put("count", count);
		json.put("msg", "success");
		json.put("code", 0);
		return json;
	}
	
	//code 0 ：成功   1：失败   2：已存在
//	@ResponseBody
//	@RequestMapping(value = "/addUser")
//	public JsonResult addNewUser(HttpSession session,@RequestParam(value="user") User user,
//			@RequestParam(required=false,value="classId")String classId) {
//		
//		return userService.addNewUser(user, classId);
//		
//	}
	//code 0 ：成功   1：失败   2：已存在
	@ResponseBody
	@RequestMapping(value = "/addUser")
	public JsonResult addNewUser(HttpSession session,@RequestBody Map paramMap) {
		
		System.out.println(paramMap);
		
		
		return userService.addNewUser(paramMap);
		
	}
	//code 0 ：成功   1：失败   
	@ResponseBody
	@RequestMapping(value = "/editUser")
	public JsonResult editUser(HttpSession session,@RequestBody Map paramMap) {
		
		System.out.println(paramMap);
		
		
		return userService.editUser(paramMap);
		
	}
	
	//code 0 ：成功   1：失败   
	@ResponseBody
	@RequestMapping(value = "/editUserPassword")
	public JsonResult editUserPassword(HttpSession session,@RequestBody Map paramMap,
			HttpServletRequest request,HttpServletResponse response) {
		
		if(paramMap==null||paramMap.isEmpty()) return JsonResult.createByError();
		
		String userId = (String) session.getAttribute("userId");
		paramMap.put("userId", userId);
		System.out.println(paramMap);
		JsonResult js = userService.editUserPassword(paramMap);
		
		if(js.getcode()==0) session.invalidate();
			
		return js;
		
		
	}
	
}
