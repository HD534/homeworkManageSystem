package com.andy.service;

import com.andy.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
	
	int insertStudentClass(Map map);
	
	int insert(Student record);
	
	List<Map<String,String>> listStudent(Map map);

	int listStudentCountNum(Map map);

}
