package com.andy.service;

import java.util.List;
import java.util.Map;

import com.andy.common.JsonResult;
import com.andy.model.Homework;

public interface HomeworkService {
	
	List<Map> listHomework(Map map);
	
	List<Map> listStudentHomework(Map map);

	int insertHomework(Homework homework);

	JsonResult insertHomework(Map map);
	
	int insertTeacherHomeworkFile(Map map);

	int insertCourseHomework(Map map);
	
	int insertStudentHomeworkFile(Map map);

	JsonResult uploadHomework(Map paramMap);
	
	int insertStudentHomeworkScore(Map map);

	Map checkStudentHomeworkScore(Map map);

	int updateStudentHomeworkScore(Map map);
}
