package com.andy.service;

import com.andy.model.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface TeacherService {
	
	 void listTeachers();
	
	 void listStudentsByCourse(String courseId);
	
	 List<Map> listStudentsByConditions(Map map);
	 
	 List<Map> uploadStudentInfo(MultipartFile uploadStuFile);
	 
	 List<Map> listCourseByPage(Map map);
	 
	 Teacher selectTeacherByUserId(String userId);
	 
	 Map selectCourseByCourseAndTerm(Map map);
	 
	 List<Map> listTeacherByInstitute(Map map);
	 
	 List<Map> listTeacherByInstituteId(Map map);

}
