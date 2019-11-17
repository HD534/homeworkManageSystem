package com.andy.mapper;

import com.andy.model.StudentHomeworkScore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentHomeworkScoreMapper {
    int deleteByPrimaryKey(String studenthomeworkscoreid);

    int insert(StudentHomeworkScore record);

    int insertSelective(StudentHomeworkScore record);

    StudentHomeworkScore selectByPrimaryKey(String studenthomeworkscoreid);

    int updateByPrimaryKeySelective(StudentHomeworkScore record);

    int updateByPrimaryKeyWithBLOBs(StudentHomeworkScore record);

    int updateByPrimaryKey(StudentHomeworkScore record);
}