package com.andy.mapper;

import com.andy.model.User;

public interface UserMapper {

	User selectByUserName(String username);

	int deleteByPrimaryKey(Integer userid);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userid);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}