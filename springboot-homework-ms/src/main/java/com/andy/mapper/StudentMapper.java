package com.andy.mapper;

import com.andy.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(String studentid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String studentid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
	int insertStudentClass(Map map);
	
	List<Map<String,String>> listStudent(Map map);
	
	int listStudentCountNum(Map map);
}