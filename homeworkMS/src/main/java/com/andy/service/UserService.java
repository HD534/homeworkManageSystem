package com.andy.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.andy.common.JsonResult;
import com.andy.model.User;

public interface UserService {

	@Transactional(readOnly = true)
	public int checkUserName(String username);

	@Transactional(readOnly = true)
	public int doRegister(User user);

	@Transactional(readOnly = true)
	public User getUserByUserName(String username, String password);

	@Transactional(readOnly = true)
	User getByUserName(String username);

	@Transactional(readOnly = true)
	User selectByUserCode(String userCode);

	@Transactional(readOnly = true)
	User selectByUserId(String userId);

	@Transactional(readOnly = true)
	User selectByUserCodeAndType(Map map);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	int insertSelective(User record);

	@Transactional(readOnly = true)
	Map getUserInfoByUserId(Map map);

	@Transactional(readOnly = true)
	List<Map> listUser(Map map);

	@Transactional(readOnly = true)
	int listUserCountNum(Map map);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	int insertUserStudent(Map stuMap);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	int insertUserTeacher(Map teaMap);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	JsonResult addNewUser(User user, String classId);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	JsonResult addNewUser(Map paramMap);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	JsonResult editUser(Map paramMap);

	JsonResult editUserPassword(Map paramMap);

	int updateUserPassword(Map paramMap);
}
