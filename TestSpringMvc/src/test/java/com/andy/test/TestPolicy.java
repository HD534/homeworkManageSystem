package com.andy.test;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.andy.model.Policy;
import com.andy.service.PolicyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-mybatis.xml")
public class TestPolicy {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	PolicyService policyService;
	
	@Test
	public void testNormal() {
		
		assertNotNull(jdbcTemplate);
		assertNotNull(sqlSession);
		Policy p = policyService.selectPolicy(1L);
		
		System.out.println(p.toString());
		
	}
}
