package com.andy.service;

import com.andy.model.Term;

import java.util.List;
import java.util.Map;

public interface TermService {

	Term selectByTermName(String termName);

	List<Map> listTerm();
	
	Term selectByTermValue(String termValue);

    int insertTerm(Term termRecord);
}
