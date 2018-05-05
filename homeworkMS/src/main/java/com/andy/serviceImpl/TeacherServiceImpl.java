package com.andy.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.andy.utils.DateUtils;
import com.andy.utils.ExcelUtil;
import com.andy.utils.UUIDUtils;
import com.andy.mapper.CourseMapper;
import com.andy.mapper.TeacherMapper;
import com.andy.model.Institute;
import com.andy.model.Student;
import com.andy.model.TblClass;
import com.andy.model.Teacher;
import com.andy.model.User;
import com.andy.service.CourseService;
import com.andy.service.InstituteService;
import com.andy.service.StudentService;
import com.andy.service.TblClassService;
import com.andy.service.TeacherService;
import com.andy.service.UserService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherMapper teacherMapper;
	
	@Autowired
	UserService userService;

	@Autowired
	StudentService studentService;

	@Autowired
	InstituteService instituteService;
	
	@Autowired
	TblClassService tblClassService;
	
	@Autowired
	CourseService courseService;

	@Override
	public void listTeachers() {
		// TODO Auto-generated method stub

	}

	@Override
	public void listStudentsByCourse(String courseId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Map> listStudentsByConditions(Map map) {
		// TODO Auto-generated method stub
		System.out.println(map.toString());
		List<Map> lm = teacherMapper.listStudentsByCondition(map);
		return lm;
	}

	@Override
	public List<Map> uploadStudentInfo(MultipartFile uploadStuFile) {
		// TODO Auto-generated method stub
		List<Map<String, String>> lm = ExcelUtil.readExcelByFile(uploadStuFile);
		System.out.println("list size"+lm.size());
		List<Map> stuInfoInsertFailed = new ArrayList<Map>();
		for (Map<String, String> map1 : lm) {
			// 插入学生数据，先查询是否有学生数据,如果有就不插入
			User u = userService.selectByUserCode(map1.get("usercode"));
			if(u!=null) {
				continue;
			}else {
				String userId = "";
				String studentId= "";
				String instituteId = "";
				String classId = "";
				// 查询institute是否存在，如果不存在就插入一条新的学院信息纪录，并拿到instituteId，如果存在就获取instituteId
				Institute in = instituteService.selectByInstituteName(map1.get("institute"));
				if(in==null) {
					instituteId = UUIDUtils.getUUID();
					Institute institute = new Institute();
					institute.setInstituteId(instituteId);
					institute.setInstituteName(map1.get("institute"));
					institute.setCreateDate(new Date());
					instituteService.insertInstitute(institute);
				}else {
					instituteId = in.getInstituteId();
					// 查询班级是否存在，如果不存在就插入一条新的班级信息记录，并返回tblclassId，如果存在就获取tblclassid
					TblClass cl = tblClassService.selectByClassName(map1.get("tblclass"));
					if(cl==null) {
						classId = UUIDUtils.getUUID();
						TblClass tblclass = new TblClass();
						tblclass.setClassId(classId);
						tblclass.setClassName(map1.get("tblclass"));
						tblclass.setCreateDate(new Date());
						System.out.println(tblclass.toString());
						tblClassService.insert(tblclass);
					}else {
						// 插入 user 表
						classId = cl.getClassId();
						userId = UUIDUtils.getUUID();
						studentId = UUIDUtils.getUUID();
						User user = new User();
						user.setUserId(userId);
						user.setUserCode(map1.get("usercode"));
						user.setUserName(map1.get("username"));
						user.setInstituteId(instituteId);
						user.setPassword(map1.get("usercode"));
						user.setUserType("2");
						user.setCreateDate(new Date());
						user.setSex(map1.get("sex"));
						userService.insertSelective(user);
						// 插入学生表 student ，再插入学生班级数据 studentclass
						Student stu = new Student();
						System.out.println(user.toString());
						System.out.println(stu.toString());
						stu.setStudentId(studentId);
						stu.setUserId(userId);
						int insertStu = studentService.insert(stu);
						Map<String,String> stuClassMap = new HashMap<>();
						stuClassMap.put("studentId", studentId);
						stuClassMap.put("classId", classId);
						stuClassMap.put("studentClassId", UUIDUtils.getUUID());
						int insetStudentClass = studentService.insertStudentClass(stuClassMap);
						System.out.println("成功插入学生数据 ："+insertStu+"  "+insetStudentClass);
						if(insertStu==0||insetStudentClass==0) {
							Map<String,String> stuInfoFailed = new HashMap<>();
							stuInfoFailed.put("studentId", studentId);
							stuInfoInsertFailed.add(stuInfoFailed);
						}
					}
					// 两条数据插入在同一事务中，如果出错就回滚并记录出错的数据
					
				}
				for (Entry<String, String> entry : map1.entrySet()) {

					System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				}
			}
			
		}

		return stuInfoInsertFailed;
	}

	@Override
	public List<Map> listCourseByPage(Map map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teacher selectTeacherByUserId(String userId) {
		// TODO Auto-generated method stub
		return teacherMapper.selectTeacherByUserId(userId);
	}

	@Override
	public Map selectCourseByCourseAndTerm(Map map) {
		// TODO Auto-generated method stub
		return teacherMapper.selectCourseByCourseAndTerm(map);
	}

	@Override
	public List<Map> listTeacherByInstitute(Map map) {
		// TODO Auto-generated method stub
		return teacherMapper.listTeacherByInstitute(map);
	}

	@Override
	public List<Map> listTeacherByInstituteId(Map map) {
		// TODO Auto-generated method stub
		return teacherMapper.listTeacherByInstituteId(map);
	}


}
