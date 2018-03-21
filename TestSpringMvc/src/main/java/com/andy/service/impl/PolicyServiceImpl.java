package com.andy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.mapper.impl.PolicyMapperImpl;
import com.andy.model.Policy;
import com.andy.service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyMapperImpl policyMapper;

	public Policy selectPolicy(Long policyid) {
		return policyMapper.selectByPrimaryKey(policyid);
	}

	@Override
	public List<Policy> listPolicy() {
		// TODO Auto-generated method stub
		return policyMapper.listPolicy();
	}

}
