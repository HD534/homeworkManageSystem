package com.andy.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.andy.model.Student;
import com.andy.model.User;
import com.andy.utils.MapUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-mybatis.xml")
public class TestSpring {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	
	@Test
	public void testNormal() {
		
		//System.out.println(sqlSession.selectOne("selectByTermName", "2018春季"));
		assertNotNull(jdbcTemplate);
		assertNotNull(sqlSession);
		
		
		
	}
	
	@Test
	public void testMapToObject() {
		Map<String, Object> map = new HashMap<>();
	    map.put("userName", "ttt");
	    map.put("userCode", "1111");
	    map.put("classId", "222");
	    
	    try {
	    	User u = (User) MapUtil.mapToObject(map, User.class);
	    	System.out.println(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}
}
