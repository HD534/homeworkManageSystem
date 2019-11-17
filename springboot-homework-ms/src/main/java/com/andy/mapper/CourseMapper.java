package com.andy.mapper;

import com.andy.model.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(String courseid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String courseid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKeyWithBLOBs(Course record);

    int updateByPrimaryKey(Course record);
    
    List<Map> listCourse(Map map);
    
    List<Map> listCourseByUserType(Map map);
    
    int selectNum(Map map);
    
    int insertCourseInstitute(Map map);
    
    int insertCourseTerm(Map map);
    
    int insertTeacherCourse(Map map);
    
    int assignClassCourse(Map map);
    
    List<Map> listClassCourse(Map map);
    
    int listClassCourseNum(Map map);

	List<Map> listCourseByTypeAndTerm(Map paramMap);
	
	List<Map> listCourseByParamMap(Map paramMap);

	Map checkClassCourse(Map map);
    
}