package com.andy.service;

import com.andy.model.Institute;

import java.util.List;
import java.util.Map;

public interface InstituteService {

	Institute selectByInstituteName(String instituteName);
	
	Institute selectByInstituteId(String instituteId);

	int insertInstitute(Institute record);

	List<Map> selectUserInstitute(Map map);

	List<Institute> listInstitute();

}
