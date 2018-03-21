package com.andy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andy.mapper.UserMapper;
import com.andy.mapper.impl.UserMapperImpl;
import com.andy.model.User;
import com.andy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	/*
	 * return 0 if the username not exist
	 * return 1 if the username exist
	 */
	@Transactional(readOnly=true)
	@Override
	public int checkUserName(String username) {
		User user = userMapper.selectByUserName(username);
		int ret = 0;
		if(user==null) {
			System.out.println("---------");
			System.out.println("username not exist");
			System.out.println("---------");
			return ret;
		}else {
			System.out.println("---------");
			System.out.println("username already exist");
			System.out.println("---------");
			ret = 1;
			return ret;
		}
	}
	
	//@Transactional(readOnly=true)
	@Override
	public int doLogin(String username,String password) {
		User u = userMapper.selectByUserName(username);
		
		System.out.println("server receive : "+username+" -- "+password);
		if(u==null) {
			System.out.println("-----------");
			System.out.println("username is wrong");
			System.out.println("-----------");
			return -2;
		}else {
			if(u.getPassword().equals(password)) {
				return 0;
			}else {
				System.out.println("-----------");
				System.out.println("password is wrong");
				System.out.println("-----------");
				return -1;
			}
		}
	}
	
	@Override
	/*
	 * return 1 if register successes
	 * return -1 if register failed
	 * return 0 if the username exist 
	 */
	public int doRegister(User user) {
		// TODO Auto-generated method stub
		if(checkUserName(user.getUsername())==0) {
			System.out.println("start insert record");
			try{
				userMapper.insertSelective(user);
				return 1;
			}catch(Exception e) {
				e.printStackTrace();
				return -1;
			}
		}else {
			return 0;
		}
	}
	
	
/*	
	 * return -1 : username already exist.
	 * return 0  : insert record success
	 * 
	@Override
	public int doRegister(User user) {
		if(checkUserName(user.getUsername())) return -1;
		userMapper.insert(user);
		return 0;
	}*/

}
