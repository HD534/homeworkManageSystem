package com.andy.serviceImpl;

import com.andy.mapper.TblClassMapper;
import com.andy.model.TblClass;
import com.andy.service.TblClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TblClassServiceImpl implements TblClassService{
	
	@Autowired
	TblClassMapper tblClassMapper;
	
	@Override
	public TblClass selectByClassName(String ClassName) {
		// TODO Auto-generated method stub
		return tblClassMapper.selectByClassName(ClassName);
	}

	@Override
	public int insert(TblClass record) {
		// TODO Auto-generated method stub
		return tblClassMapper.insert(record);
	}

	@Override
	public List<Map> listClassByInstitute(Map map) {
		// TODO Auto-generated method stub
		return tblClassMapper.listClassByInstitute(map);
	}

	@Override
	public List<Map> listClass(Map map) {
		// TODO Auto-generated method stub
		return tblClassMapper.listClass(map);
	}

	@Override
	public int listClassCountNum(Map map) {
		// TODO Auto-generated method stub
		return tblClassMapper.listClassCountNum(map);
	}

	@Override
	public int addNewClass(Map paramMap) {
		// TODO Auto-generated method stub
		return tblClassMapper.addNewClass(paramMap);
	}

	@Override
	public int insertClassInstitute(Map classInstituteMap){
		return tblClassMapper.insertClassInstitute(classInstituteMap);
	}

}
