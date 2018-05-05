package com.andy.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.mapper.InstituteMapper;
import com.andy.model.Institute;
import com.andy.service.InstituteService;

@Service
public class InstituteServiceImpl implements InstituteService{
	
	@Autowired
	InstituteMapper instituteMapper;
	
	@Override
	public Institute selectByInstituteName(String instituteName) {
		// TODO Auto-generated method stub
		return instituteMapper.selectByInstituteName(instituteName);
	}

	@Override
	public int insertInstitute(Institute record) {
		// TODO Auto-generated method stub
		return instituteMapper.insert(record);
	}

	@Override
	public List<Map> selectUserInstitute(Map map) {
		// TODO Auto-generated method stub
		return instituteMapper.selectUserInstitute(map);
	}

	@Override
	public List<Institute> listInstitute() {
		// TODO Auto-generated method stub
		return instituteMapper.listInstitute();
	}

	@Override
	public Institute selectByInstituteId(String instituteId) {
		// TODO Auto-generated method stub
		return instituteMapper.selectByInstituteId(instituteId);
	}
	
	

}
