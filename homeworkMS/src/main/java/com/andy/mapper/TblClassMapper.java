package com.andy.mapper;

import java.util.List;
import java.util.Map;

import com.andy.common.JsonResult;
import com.andy.model.TblClass;

public interface TblClassMapper {
    int deleteByPrimaryKey(String classid);

    int insert(TblClass record);

    int insertSelective(TblClass record);

    TblClass selectByPrimaryKey(String classid);

    int updateByPrimaryKeySelective(TblClass record);

    int updateByPrimaryKey(TblClass record);
    
    TblClass selectByClassName(String ClassName);
    
    List<Map> listClassByInstitute(Map map);

    List<Map> listClass(Map map);
    
    int listClassCountNum(Map map);

	int addNewClass(Map paramMap);
}