package com.andy.mapper;

import com.andy.model.Term;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
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