package com.andy.mapper;

import com.andy.model.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeacherMapper {
    int deleteByPrimaryKey(String teacherid);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String teacherid);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    List<Map> listStudentsByCondition(Map map);
    
    Teacher selectTeacherByUserId(String userId);
    
    int insertTeacherCourse(Map map);
    
    Map selectCourseByCourseAndTerm(Map map);
    
    List<Map> listTeacherByInstitute(Map map);
    
    List<Map> listTeacherByInstituteId(Map map);
}