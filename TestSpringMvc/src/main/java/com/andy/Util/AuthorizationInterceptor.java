package com.andy.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.andy.model.User;

public class AuthorizationInterceptor implements HandlerInterceptor {
	
	private static final String[] IGNORE_URI = {"/loginForm","/login","register","registerForm","layuiLogin"};
	private static final String[] REGISTER_URI = {"register","registerForm"};

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion");

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle");

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle");
		Boolean flag = false;
		String servletPath = request.getServletPath();
		//注册直接通过
		for (String s : REGISTER_URI) {
			if(servletPath.contains(s)) {
				return true;
			}
		}
		//判断是否需要拦截
		for (String s : IGNORE_URI) {
			if(servletPath.contains(s)) {
				flag = true;
				break;
			}
		}
		
		if(!flag) {
			User user = (User) request.getSession().getAttribute("user");
			if(user==null) {
				System.out.println("not login,intercpt the request");
				request.setAttribute("intercptMessage", "please login!");
				response.sendRedirect("loginForm");
				//request.getRequestDispatcher("layuiLogin").forward(request, response);
			}else {
				System.out.println("request pass");
				flag = true;
			}
		}
		return flag;
	}

}
