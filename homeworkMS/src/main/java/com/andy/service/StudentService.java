package com.andy.service;

import java.util.List;
import java.util.Map;

import com.andy.model.Student;

public interface StudentService {
	
	int insertStudentClass(Map map);
	
	int insert(Student record);
	
	List<Map<String,String>> listStudent(Map map);

	int listStudentCountNum(Map map);

}
