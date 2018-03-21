package com.andy.Util;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.andy.model.User;

public class UserValidater implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", null,"用户名不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null,"密码不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age",null,"年龄不能为空");
		ValidationUtils.rejectIfEmpty(errors, "age",null,"性别不能为空");
	}
	
	
	

}
