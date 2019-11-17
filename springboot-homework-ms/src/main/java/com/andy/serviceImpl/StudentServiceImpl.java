package com.andy.serviceImpl;

import com.andy.mapper.StudentMapper;
import com.andy.model.Student;
import com.andy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentMapper studentMapper;
	

	@Override
	public int insertStudentClass(Map map) {
		// TODO Auto-generated method stub
		return studentMapper.insertStudentClass(map);
	}

	@Override
	public int insert(Student record) {
		// TODO Auto-generated method stub
		return studentMapper.insert(record);
	}

	@Override
	public List<Map<String,String>> listStudent(Map map) {
		// TODO Auto-generated method stub
		return studentMapper.listStudent(map);
	}

	@Override
	public int listStudentCountNum(Map map) {
		// TODO Auto-generated method stub
		return studentMapper.listStudentCountNum(map);
	}



}
