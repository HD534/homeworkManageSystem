package com.andy.mapper;

import java.util.List;

import com.andy.model.Policy;

public interface PolicyMapper {

	List<Policy> listPolicy();

	int deleteByPrimaryKey(Long policyid);

	int insert(Policy record);

	int insertSelective(Policy record);

	Policy selectByPrimaryKey(Long policyid);

	int updateByPrimaryKeySelective(Policy record);

	int updateByPrimaryKey(Policy record);
}