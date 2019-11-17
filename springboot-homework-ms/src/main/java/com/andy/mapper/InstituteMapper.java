package com.andy.mapper;

import com.andy.model.Institute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InstituteMapper {
    int deleteByPrimaryKey(String instituteid);

    int insert(Institute record);

    int insertSelective(Institute record);

    Institute selectByPrimaryKey(String instituteid);
    
    Institute selectByInstituteId(String instituteId);

    int updateByPrimaryKeySelective(Institute record);

    int updateByPrimaryKey(Institute record);
    
    Institute selectByInstituteName(String instituteName);
    
    List<Map> selectUserInstitute(Map map);
    
    List<Institute> listInstitute();
}