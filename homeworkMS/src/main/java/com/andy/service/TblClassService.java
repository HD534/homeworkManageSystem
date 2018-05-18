package com.andy.service;

import java.util.List;
import java.util.Map;

import com.andy.common.JsonResult;
import com.andy.model.TblClass;

public interface TblClassService {

	TblClass selectByClassName(String ClassName);

	int insert(TblClass record);

	List<Map> listClassByInstitute(Map map);

	List<Map> listClass(Map map);

	int listClassCountNum(Map map);

	int addNewClass(Map paramMap);

}
