package com.andy.mapper;

import java.util.List;
import java.util.Map;

import com.andy.model.Homework;

public interface HomeworkMapper {
	int deleteByPrimaryKey(String homeworkid);

	int insert(Homework record);

	int insertSelective(Homework record);

	Homework selectByPrimaryKey(String homeworkid);

	int updateByPrimaryKeySelective(Homework record);

	int updateByPrimaryKeyWithBLOBs(Homework record);

	int updateByPrimaryKey(Homework record);

	int insertTeacherHomeworkFile(Map map);

	int insertCourseHomework(Map map);
	
	int insertStudentHomeworkFile(Map map);
	
	List<Map> listHomework(Map map);
	
	List<Map> listStudentHomework(Map map);
	
	int insertStudentHomeworkScore(Map map);

	Map checkStudentHomeworkScore(Map map);

	int updateStudentHomeworkScore(Map map);
}