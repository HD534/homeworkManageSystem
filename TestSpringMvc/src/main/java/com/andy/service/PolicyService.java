package com.andy.service;


import java.util.List;

import com.andy.model.Policy;


public interface PolicyService {
	
	public Policy selectPolicy(Long policyid);
	
	public List<Policy>listPolicy();

}
