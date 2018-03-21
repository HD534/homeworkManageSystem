package com.andy.service;

import com.andy.model.User;

public interface UserService {
	
	public int checkUserName(String username);
	
	public int doLogin(String username,String password);
	
	public int doRegister(User user);

}
