package com.andy.service;

import java.util.List;
import java.util.Map;

public interface CourseService {
	
	List<Map> listCourse(Map map);
	
	List<Map> listCourseByUserType(Map map);
	
	int selectNum(Map map);
	
	int insertCourseInfo(Map map);

	int assignClassCourse(String courseId, String classId);
	
	int checkClassCourse(Map map);
	
	List<Map> listClassCourse(Map map);

	List<Map> listCourseByTypeAndTerm(Map paramMap);

	List<Map> listCourseByParamMap(Map paramMap);

	int listClassCourseNum(Map paramMap);

}
