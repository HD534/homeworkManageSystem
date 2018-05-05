package com.andy.mapper;

import java.util.List;
import java.util.Map;

import com.andy.model.Term;

public interface TermMapper {
    int deleteByPrimaryKey(String termid);

    int insert(Term record);

    int insertSelective(Term record);

    Term selectByPrimaryKey(String termid);
    
    Term selectByTermName(String termName);
    
    Term selectByTermValue(String termValue);
    
    List<Map> listTerm();

    int updateByPrimaryKeySelective(Term record);

    int updateByPrimaryKey(Term record);
    
  
}