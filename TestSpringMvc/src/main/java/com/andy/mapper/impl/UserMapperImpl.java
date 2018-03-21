package com.andy.mapper.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.andy.mapper.UserMapper;
import com.andy.model.User;

@Repository
public class UserMapperImpl implements UserMapper{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int deleteByPrimaryKey(Integer userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		try {
			sqlSession.insert("insert", record);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		try {
			sqlSession.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}

	@Override
	public User selectByPrimaryKey(Integer userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectByUserName(String username) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectByUserName", username);
	}

}
