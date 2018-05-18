package com.andy.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.mapper.CourseMapper;
import com.andy.model.Course;
import com.andy.model.Institute;
import com.andy.model.Teacher;
import com.andy.model.Term;
import com.andy.service.CourseService;
import com.andy.service.InstituteService;
import com.andy.service.TeacherService;
import com.andy.service.TermService;
import com.andy.utils.UUIDUtils;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseMapper courseMapper;

	@Autowired
	TermService termService;

	@Autowired
	InstituteService instituteService;

	@Autowired
	TeacherService teacherService;

	@Override
	public List<Map> listCourse(Map map) {
		// TODO Auto-generated method stub
		return courseMapper.listCourse(map);
	}

	@Override
	public List<Map> listCourseByUserType(Map map) {
		// TODO Auto-generated method stub
		return courseMapper.listCourseByUserType(map);
	}

	@Override
	public int selectNum(Map map) {
		// TODO Auto-generated method stub
		return courseMapper.selectNum(map);
	}

	/*
	 * 返回0成功，返回1失败，返回2已存在
	 */
	@Override
	public int insertCourseInfo(Map map) {

		// 先查询此学期此学院此教师是否已存在此门课程
		// 如果存在，则返回2
		if (checkCourseInfo(map)) {
			return 2;
		}
		System.out.println("插入课程前的查询结果：" + map.toString());

		// 插入课程信息
		String courseName = (String) map.get("courseName");
		String courseDesc = (String) map.get("courseDesc");
		String instituteId = (String) map.get("instituteId");
		String termValue = (String) map.get("termValue");
		String userId = (String) map.get("userId");
		// 获取教师信息 一定存在
		Teacher teacher = teacherService.selectTeacherByUserId(userId);
		// 先查询学期id 一定存在
		// Term term = termService.selectByTermName(termName);
		Term term = termService.selectByTermValue(termValue);
		String termId = term.getTermId();

		// 插入课程信息 包括 courseId uuid ,courseName ,courseDesc ,createDate
		String courseId = UUIDUtils.getUUID();
		int insertCourse = insertCourse(courseDesc, courseId, courseName);

		// 插入CourseInstitute courseId ,instituteId
		int insertCourseInstitute = insertCourseInstitute(courseId, instituteId);

		// 插入teacherCourse teacherId courseId
		int insertTeacherCourse = insertTeacherCourse(courseId, teacher.getTeacherId());

		// 插入courseTerm courseId, termId
		int insertCourseTerm = insertCourseTerm(courseId, termId);
		// 可能存在逻辑问题
		if (insertCourseTerm == 0 || insertCourseInstitute == 0 || insertCourse == 0 || insertTeacherCourse == 0) {
			return 1;
		}
		return 0;
	}

	// 在插入课程信息前先查询是否已经存在
	public boolean checkCourseInfo(Map map) {
		// 查询教师是否在本学期已经本学期已经有这门课程
		Map courseByCourseAndTerm = teacherService.selectCourseByCourseAndTerm(map);
		// 返回查询内容是否存在，如果存在说明已经有课程
		return (courseByCourseAndTerm != null);
	}

	// 插入课程信息
	public int insertCourse(String courseDesc, String courseId, String courseName) {
		Course course = new Course();
		course.setCourseDesc(courseDesc);
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		course.setCreateDate(new Date());
		System.out.println("插入的课程信息：" + course);
		return courseMapper.insert(course);
	}

	// 插入insertCourseInstitute
	public int insertCourseInstitute(String courseId, String instituteId) {
		Map<String, Object> courseInstitute = new HashMap<>();
		courseInstitute.put("courseInstituteId", UUIDUtils.getUUID());
		courseInstitute.put("courseId", courseId);
		courseInstitute.put("instituteId", instituteId);
		System.out.println("插入的courseInstitute信息：" + courseInstitute);
		return courseMapper.insertCourseInstitute(courseInstitute);
	}

	// 插入teacherCourse teacherId courseId
	public int insertTeacherCourse(String courseId, String teacherId) {
		Map<String, Object> teacherCourse = new HashMap<>();
		teacherCourse.put("teacherCourseId", UUIDUtils.getUUID());
		teacherCourse.put("courseId", courseId);
		teacherCourse.put("teacherId", teacherId);
		System.out.println("插入的teacherCourse信息：" + teacherCourse);
		return courseMapper.insertTeacherCourse(teacherCourse);
	}

	// 插入courseTerm courseId, termId\
	public int insertCourseTerm(String courseId, String termId) {

		Map<String, Object> courseTerm = new HashMap<>();
		courseTerm.put("courseTermId", UUIDUtils.getUUID());
		courseTerm.put("courseId", courseId);
		courseTerm.put("termId", termId);
		System.out.println("插入的courseTerm信息：" + courseTerm);
		return courseMapper.insertCourseTerm(courseTerm);
	}

	@Override
	public int assignClassCourse(String courseId, String classId) {
		Map map = new HashMap<>();
		map.put("courseId", courseId);
		map.put("classId", classId);
		if (checkClassCourse(map) == 1) {
			return 0;
		}else {
			Map<String, Object> courseClass = new HashMap<>();
			courseClass.put("courseClassId", UUIDUtils.getUUID());
			courseClass.put("courseId", courseId);
			courseClass.put("classId", classId);
			System.out.println("插入的courseTerm信息：" + courseClass);
			return courseMapper.assignClassCourse(courseClass);
		}

	}

	@Override
	public List<Map> listClassCourse(Map map) {
		// TODO Auto-generated method stub
		return courseMapper.listClassCourse(map);
	}

	@Override
	public List<Map> listCourseByTypeAndTerm(Map paramMap) {
		// TODO Auto-generated method stub
		return courseMapper.listCourseByTypeAndTerm(paramMap);
	}

	@Override
	public List<Map> listCourseByParamMap(Map paramMap) {
		// TODO Auto-generated method stub
		return courseMapper.listCourseByParamMap(paramMap);
	}

	@Override
	public int listClassCourseNum(Map paramMap) {
		// TODO Auto-generated method stub
		return courseMapper.listClassCourseNum(paramMap);
	}

	@Override
	public int checkClassCourse(Map map) {
		// TODO Auto-generated method stub
		return courseMapper.checkClassCourse(map);
	}

}
