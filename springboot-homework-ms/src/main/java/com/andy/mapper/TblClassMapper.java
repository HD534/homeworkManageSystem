package com.andy.mapper;

import com.andy.model.TblClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
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

	int insertClassInstitute(Map classInstituteMap);
}