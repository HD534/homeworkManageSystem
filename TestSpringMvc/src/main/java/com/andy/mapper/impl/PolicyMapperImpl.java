package com.andy.mapper.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andy.mapper.PolicyMapper;
import com.andy.model.Policy;

@Repository()
public class PolicyMapperImpl implements PolicyMapper{
	
	@Autowired
	SqlSession sqlSession;
	public int deleteByPrimaryKey(Long policyid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Policy record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Policy record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Policy selectByPrimaryKey(Long policyid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectByPrimaryKey", policyid);
	}

	public int updateByPrimaryKeySelective(Policy record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Policy record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Policy> listPolicy() {
		return sqlSession.selectList("listPolicy");
	}

}
