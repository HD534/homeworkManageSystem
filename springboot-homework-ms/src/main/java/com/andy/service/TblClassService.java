package com.andy.service;

import com.andy.model.TblClass;

import java.util.List;
import java.util.Map;

public interface TblClassService {

	TblClass selectByClassName(String ClassName);

	int insert(TblClass record);

	List<Map> listClassByInstitute(Map map);

	List<Map> listClass(Map map);

	int listClassCountNum(Map map);

	int addNewClass(Map paramMap);

    int insertClassInstitute(Map classInstituteMap);
}
