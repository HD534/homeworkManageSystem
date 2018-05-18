package com.andy.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.common.JsonResult;
import com.andy.mapper.HomeworkMapper;
import com.andy.model.Homework;
import com.andy.service.HomeworkService;
import com.andy.utils.DateUtils;
import com.andy.utils.UUIDUtils;

@Service
public class HomeworkServiceImpl implements HomeworkService {
	
	@Autowired
	HomeworkMapper homeworkMapper;

	@Override
	public List<Map> listHomework(Map map) {
		// TODO Auto-generated method stub
		return homeworkMapper.listHomework(map);
	}

	@Override
	public int insertHomework(Homework homework) {
		// TODO Auto-generated method stub
		return homeworkMapper.insertSelective(homework);
	}
	
	@Override
	public JsonResult insertHomework(Map map) {
		
		if(map==null||map.size()==0) return JsonResult.createByError();
		
		//获取DueDate
		String dueDateStr =  (String) map.get("homeworkDueDate");
		Date dueDate = DateUtils.stringToDate(dueDateStr);
		if(dueDate==null) return JsonResult.createByError();
		Date today = new Date(); 
		String homeworkName = (String) map.get("homeworkName");
		String homeworkDesc = (String) map.get("homeworkDesc");
		//String termValue = (String) map.get("termValue");
		String courseId =  (String) map.get("courseId");
		//String userId =  (String) map.get("userId");
		String homeworkId = UUIDUtils.getUUID();
		//创建homework
		Homework homework = new Homework();
		homework.setCreateDate(today);
		homework.setDueDate(dueDate);
		homework.setHomeworkDesc(homeworkDesc);
		homework.setHomeworkId(homeworkId);
		homework.setHomeworkName(homeworkName);
		homework.setHomeworkType("1");
		homework.setPublishDate(today);
		System.out.println(homework.toString());
		//插入homework表
		int insert = insertHomework(homework);
		map.put("homeworkId", homeworkId);
		//插入TeacherhomeworkFile表
		int insertTeacherHomeworkFile = insertTeacherHomeworkFile(map);
		//插入CourseHomework表
		int insertCourseHomework = insertCourseHomework(map);
		if(insert==0||insertTeacherHomeworkFile==0||insertCourseHomework==0) return JsonResult.createByError();
		return JsonResult.createBySuccess();
		
	}

	@Override
	public int insertTeacherHomeworkFile(Map map) {
		// TODO Auto-generated method stub
		String teacherHomeworkFileId = UUIDUtils.getUUID();
		map.put("teacherId", map.get("userId"));
		map.put("teacherHomeworkFileId", teacherHomeworkFileId);
		
		return homeworkMapper.insertTeacherHomeworkFile(map);
	}

	@Override
	public int insertCourseHomework(Map map) {
		// TODO Auto-generated method stub
		String courseHomeworkId = UUIDUtils.getUUID();
		map.put("courseHomeworkId", courseHomeworkId);
		map.put("createDate", DateUtils.getCurrentDateStr());
		return homeworkMapper.insertCourseHomework(map);
	}

	@Override
	public JsonResult uploadHomework(Map paramMap) {
		// TODO Auto-generated method stub
		int insertStudentHomeworkFile = insertStudentHomeworkFile(paramMap);
		if(insertStudentHomeworkFile==0) return JsonResult.createByError();
		return JsonResult.createBySuccess();
	}

	@Override
	public int insertStudentHomeworkFile(Map map) {
		// TODO Auto-generated method stub
		String studentHomeworkFileId = UUIDUtils.getUUID();
		map.put("studentHomeworkFileId", studentHomeworkFileId);
		map.put("studentId", map.get("userId"));
		return homeworkMapper.insertStudentHomeworkFile(map);
		
	}

	@Override
	public List<Map> listStudentHomework(Map map) {
		// TODO Auto-generated method stub
		return homeworkMapper.listStudentHomework(map);
	}

	@Override
	public int insertStudentHomeworkScore(Map map) {
		// TODO Auto-generated method stub
		//先查数据库是否有数据，有的话update，没有就insert
		if(checkStudentHomeworkScore(map).size()==0) {
			//插入数据
			String studentHomeworkScoreId = UUIDUtils.getUUID();
			map.put("studentHomeworkScoreId", studentHomeworkScoreId);
			Date createDate = new Date();
			map.put("createDate", createDate);
			map.put("creator", map.get("currentUserId"));
			return homeworkMapper.insertStudentHomeworkScore(map);
		}
		Date updateDate = new Date();
		map.put("updateDate", updateDate);
		map.put("updater", map.get("currentUserId"));
		return updateStudentHomeworkScore(map);
	}
	
	@Override
	public Map checkStudentHomeworkScore(Map map) {
		// TODO Auto-generated method stub
		return homeworkMapper.checkStudentHomeworkScore(map);
	}
	
	@Override
	public int updateStudentHomeworkScore(Map map) {
		// TODO Auto-generated method stub
		return homeworkMapper.updateStudentHomeworkScore(map);
	}

	@Override
	public int listHomeworkCount(Map map) {
		// TODO Auto-generated method stub
		return homeworkMapper.listHomeworkCount(map);
	}

	@Override
	public int listStudentHomeworkCount(Map map) {
		// TODO Auto-generated method stub
		return homeworkMapper.listStudentHomeworkCount(map);
	}

	@Override
	public List<Map> listHomeworkScoreInfo(Map map) {
		// TODO Auto-generated method stub
		return homeworkMapper.listHomeworkScoreInfo(map);
	}

	@Override
	public int listHomeworkScoreCount(Map map) {
		// TODO Auto-generated method stub
		return homeworkMapper.listHomeworkScoreCount(map);
	}
	

}
