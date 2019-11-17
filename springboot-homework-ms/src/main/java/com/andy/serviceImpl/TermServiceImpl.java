package com.andy.serviceImpl;

import com.andy.mapper.TermMapper;
import com.andy.model.Term;
import com.andy.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TermServiceImpl implements TermService {
	
	@Autowired
	TermMapper termMapper;

	@Override
	public Term selectByTermName(String termName) {
		// TODO Auto-generated method stub
		return termMapper.selectByTermName(termName);
	}

	@Override
	public List<Map> listTerm() {
		// TODO Auto-generated method stub
		return termMapper.listTerm();
	}

	@Override
	public Term selectByTermValue(String termValue) {
		// TODO Auto-generated method stub
		return termMapper.selectByTermValue(termValue);
	}

	@Override
	public int insertTerm(Term termRecord){
		return termMapper.insert(termRecord);
	}

}
