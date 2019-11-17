package com.andy.serviceImpl;

import com.andy.common.BusinessName;
import com.andy.mapper.TeacherMapper;
import com.andy.model.*;
import com.andy.service.*;
import com.andy.utils.ExcelUtil;
import com.andy.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.Map.Entry;

@Service
@Transactional
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
		System.out.println(map.toString());
		return teacherMapper.listStudentsByCondition(map);
	}

	@Override
	public List<Map> uploadStudentInfo(MultipartFile uploadStuFile) {
		List<Map<String, String>> lm = ExcelUtil.readExcelByFile(uploadStuFile);
		List<Map> stuInfoInsertFailed = new ArrayList<Map>();
		for (Map<String, String> map : lm) {
			// 插入学生数据，先查询是否有学生数据,如果有就不插入
			User u = userService.selectByUserCode(map.get(BusinessName.USERCODE.getDescEnglish()));
			if (u != null) {
				continue;
			}

			String userId = "";
			String studentId = "";
			String instituteId = "";
			String classId = "";
			// 查询institute是否存在，如果不存在就插入一条新的学院信息纪录，并拿到instituteId，如果存在就获取instituteId
			Institute in = instituteService.selectByInstituteName(map.get((BusinessName.INSTITUTE.getDescEnglish())));
			if (in == null) {
				instituteId = UUIDUtils.getUUID();
				Institute institute = getInstitute(map, instituteId);
				instituteService.insertInstitute(institute);
				in = institute;
			}
			instituteId = in.getInstituteId();
			// 查询班级是否存在，如果不存在就插入一条新的班级信息记录，并返回tblclassId，如果存在就获取tblclassid
			TblClass cl = tblClassService.selectByClassName(map.get((BusinessName.TBLCLASS.getDescEnglish())));
			if (cl == null) {
				classId = UUIDUtils.getUUID();
				TblClass tblclass = getTblClass(map, classId);
				tblClassService.insert(tblclass);

				tblClassService.insertClassInstitute(getClassInstituteMap(classId,instituteId));
				cl = tblclass;
			}
			// 插入 user 表
			classId = cl.getClassId();
			userId = UUIDUtils.getUUID();
			studentId = UUIDUtils.getUUID();
			User user = getUser(map, userId, instituteId);
			userService.insertSelective(user);
			// 插入学生表 student ，再插入学生班级数据 studentclass
			Student stu = new Student();
			System.out.println(user.toString());
			System.out.println(stu.toString());
			stu.setStudentId(studentId);
			stu.setUserId(userId);
			int insertStu = studentService.insert(stu);
			Map<String, String> stuClassMap = getStudentClassMap(studentId, classId);
			int insetStudentClass = studentService.insertStudentClass(stuClassMap);
			System.out.println("成功插入学生数据 ：" + insertStu + "  " + insetStudentClass);
			if (insertStu == 0 || insetStudentClass == 0) {
				Map<String, String> stuInfoFailed = new HashMap<>();
				stuInfoFailed.put("studentId", studentId);
				stuInfoInsertFailed.add(stuInfoFailed);
			}

			// 两条数据插入在同一事务中，如果出错就回滚并记录出错的数据

		}

		return stuInfoInsertFailed;
	}

	private User getUser(Map<String, String> map, String userId, String instituteId) {
		User user = new User();
		user.setUserId(userId);
		user.setUserCode(map.get(BusinessName.USERCODE.getDescEnglish()));
		user.setUserName(map.get(BusinessName.USERNAME.getDescEnglish()));
		user.setInstituteId(instituteId);
		user.setPassword(map.get(BusinessName.USERCODE.getDescEnglish()));
		user.setUserType("2");
		user.setCreateDate(new Date());
		user.setSex(map.get(BusinessName.SEX.getDescEnglish()));
		return user;
	}

	private Map<String,String> getClassInstituteMap(String classId,String instituteId){
		Map<String,String> classInstituteMap = new HashMap<>();
		classInstituteMap.put("classInstituteId",UUIDUtils.getUUID());
		classInstituteMap.put("classId",classId);
		classInstituteMap.put("instituteId",instituteId);
		return classInstituteMap;
	}

	private Map<String, String> getStudentClassMap(String studentId, String classId) {
		Map<String, String> stuClassMap = new HashMap<>();
		stuClassMap.put("studentId", studentId);
		stuClassMap.put("classId", classId);
		stuClassMap.put("studentClassId", UUIDUtils.getUUID());
		return stuClassMap;
	}

	private TblClass getTblClass(Map<String, String> map1, String classId) {
		TblClass tblclass = new TblClass();
		tblclass.setClassId(classId);
		tblclass.setClassName(map1.get(BusinessName.TBLCLASS.getDescEnglish()));
		tblclass.setCreateDate(new Date());
		System.out.println(tblclass.toString());
		return tblclass;
	}

	private Institute getInstitute(Map<String, String> map1, String instituteId) {
		Institute institute = new Institute();
		institute.setInstituteId(instituteId);
		institute.setInstituteName(map1.get(BusinessName.INSTITUTE.getDescEnglish()));
		institute.setCreateDate(new Date());
		return institute;
	}

	@Override
	public List<Map> listCourseByPage(Map map) {
		return null;
	}

	@Override
	public Teacher selectTeacherByUserId(String userId) {
		return teacherMapper.selectTeacherByUserId(userId);
	}

	@Override
	public Map selectCourseByCourseAndTerm(Map map) {
		return teacherMapper.selectCourseByCourseAndTerm(map);
	}

	@Override
	public List<Map> listTeacherByInstitute(Map map) {
		return teacherMapper.listTeacherByInstitute(map);
	}

	@Override
	public List<Map> listTeacherByInstituteId(Map map) {
		return teacherMapper.listTeacherByInstituteId(map);
	}


}

