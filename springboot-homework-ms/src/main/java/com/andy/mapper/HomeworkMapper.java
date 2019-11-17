package com.andy.mapper;

import com.andy.model.Homework;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
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
	
	int listHomeworkCount(Map map);
	
	List<Map> listStudentHomework(Map map);
	
	int listStudentHomeworkCount(Map map);

	int insertStudentHomeworkScore(Map map);

	Map checkStudentHomeworkScore(Map map);

	int updateStudentHomeworkScore(Map map);
	
	List<Map> listHomeworkScoreInfo(Map map);

	int listHomeworkScoreCount(Map map);

}