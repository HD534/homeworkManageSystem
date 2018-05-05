package com.andy.service;

import java.util.List;
import java.util.Map;

import com.andy.model.Term;

public interface TermService {

	Term selectByTermName(String termName);

	List<Map> listTerm();
	
	Term selectByTermValue(String termValue);

}
