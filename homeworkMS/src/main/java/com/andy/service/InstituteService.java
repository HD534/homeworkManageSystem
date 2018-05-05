package com.andy.service;

import java.util.List;
import java.util.Map;

import com.andy.model.Institute;

public interface InstituteService {

	Institute selectByInstituteName(String instituteName);
	
	Institute selectByInstituteId(String instituteId);

	int insertInstitute(Institute record);

	List<Map> selectUserInstitute(Map map);

	List<Institute> listInstitute();

}
