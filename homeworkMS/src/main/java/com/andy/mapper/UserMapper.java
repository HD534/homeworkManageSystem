package com.andy.mapper;

import java.util.List;
import java.util.Map;

import com.andy.model.User;

public interface UserMapper {

	User selectByUserCode(String userCode);

	User selectByUserName(String userName);

	User selectByUserCodeAndType(Map map);

	int deleteByPrimaryKey(String userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(String userId);

	Map getUserInfoByUserId(Map map);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<Map> listUser(Map map);
	
	int listUserCountNum(Map map);

	int insertUserStudent(Map stuMap);

	int insertUserTeacher(Map teaMap);

	int updateUserPassword(Map paramMap);
}