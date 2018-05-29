package com.andy.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.common.JsonResult;
import com.andy.mapper.UserMapper;
import com.andy.model.User;
import com.andy.service.UserService;
import com.andy.utils.MapUtil;
import com.andy.utils.UUIDUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public int checkUserName(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doRegister(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUserByUserName(String username, String password) {
		return userMapper.selectByUserName(username);
	}

	@Override
	public User getByUserName(String username) {
		return userMapper.selectByUserName(username);
	}

	@Override
	public User selectByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return userMapper.selectByUserCode(userCode);
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.insertSelective(record);
	}

	@Override
	public User selectByUserCodeAndType(Map map) {
		// TODO Auto-generated method stub
		return userMapper.selectByUserCodeAndType(map);
	}

	@Override
	public User selectByUserId(String userId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public Map getUserInfoByUserId(Map map) {
		// TODO Auto-generated method stub
		return userMapper.getUserInfoByUserId(map);
	}

	@Override
	public List<Map> listUser(Map map) {
		// TODO Auto-generated method stub
		return userMapper.listUser(map);
	}

	@Override
	public int listUserCountNum(Map map) {
		// TODO Auto-generated method stub
		return userMapper.listUserCountNum(map);
	}

	@Override
	public int insertUserStudent(Map stuMap) {
		// TODO Auto-generated method stub
		return userMapper.insertUserStudent(stuMap);
	}

	@Override
	public int insertUserTeacher(Map teaMap) {
		// TODO Auto-generated method stub
		return userMapper.insertUserTeacher(teaMap);
	}

	@Override
	public JsonResult addNewUser(Map paramMap) {

		System.out.println(paramMap);
		if (paramMap == null)
			return JsonResult.createByError();

		try {
			// map转换成User对象
			User user = (User) MapUtil.mapToObject(paramMap, User.class);
			if (user == null)
				return JsonResult.createByError();
			System.out.println(user);

			// 查询user是否存在
			Map map = new HashMap<>();
			map.put("userCode", user.getUserCode());
			map.put("userType", user.getUserType());
			User u = selectByUserCodeAndType(map);
			if (u != null)
				return JsonResult.createByErrorCode(2);

			String userId = UUIDUtils.getUUID();
			user.setUserId(userId);

			String classId = (String) paramMap.get("classId");

			System.out.println(classId);

			// 是学生 插入user表 student表
			if (classId != null) {
				// 插入user表
				int insertUser = insertSelective(user);
				// 插入student表
				String studentId = UUIDUtils.getUUID();
				Map stuMap = new HashMap<>();
				stuMap.put("studentId", studentId);
				stuMap.put("userId", userId);

				int insertUserStudent = insertUserStudent(stuMap);

				if (insertUserStudent != 1) {
					return JsonResult.createByError();
				}

			} else {
				if (user.getUserType().equals("1")) {
					// 插入user表
					int insertUser = insertSelective(user);
					// 插入teacher表
					String teacherId = UUIDUtils.getUUID();
					Map teaMap = new HashMap<>();
					teaMap.put("teacherId", teacherId);
					teaMap.put("userId", userId);
					int insertTeaStudent = insertUserTeacher(teaMap);
					if (insertTeaStudent != 1) {
						return JsonResult.createByError();
					}
				} else {
					// 管理员角色
					// 插入user表
					int insertUser = insertSelective(user);
					if (insertUser != 1) {
						return JsonResult.createByError();
					}
				}
			}

			return JsonResult.createBySuccess();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.createByError();
		}

	}

	@Override
	public JsonResult addNewUser(User user, String classId) {

		System.out.println(user);
		if (classId != null)
			System.out.println("classId" + classId);

		// 查询user是否存在
		Map map = new HashMap<>();
		map.put("userCode", user.getUserCode());
		map.put("userType", user.getUserType());
		User u = selectByUserCodeAndType(map);
		if (u != null)
			return JsonResult.createByErrorCode(2);

		String userId = UUIDUtils.getUUID();
		user.setUserId(userId);

		// 是学生 插入user表 student表
		if (classId != null) {
			// 插入user表
			int insertUser = insertSelective(user);
			// 插入student表
			String studentId = UUIDUtils.getUUID();
			Map stuMap = new HashMap<>();
			stuMap.put("studentId", studentId);
			stuMap.put("userId", userId);

			int insertUserStudent = insertUserStudent(stuMap);

			if (insertUserStudent != 1) {
				return JsonResult.createByError();
			}

		} else {
			if (user.getUserType().equals("1")) {
				int insertUser = insertSelective(user);
				// 插入teacher表
				String teacherId = UUIDUtils.getUUID();
				Map teaMap = new HashMap<>();
				teaMap.put("teacherId", teacherId);
				teaMap.put("userId", userId);
				int insertTeaStudent = insertUserTeacher(teaMap);
				if (insertTeaStudent != 1) {
					return JsonResult.createByError();
				}
			} else {
				// 管理员角色
				int insertUser = insertSelective(user);
				if (insertUser != 1) {
					return JsonResult.createByError();
				}
			}
		}

		return JsonResult.createBySuccess();

	}

	@Override
	public JsonResult editUser(Map paramMap) {
		System.out.println(paramMap);
		// map转换成User对象
		try {
			User user = (User) MapUtil.mapToObject(paramMap, User.class);
			userMapper.updateByPrimaryKeySelective(user);
			System.out.println(user);
			return JsonResult.createBySuccess();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonResult.createByError();
		}

	}

	@Override
	public JsonResult editUserPassword(Map paramMap) {
		// TODO Auto-generated method stub
		String oldPassword = (String) paramMap.get("userOldPassword");
		String userId = (String) paramMap.get("userId");
		User u = selectByUserId(userId);
		if(u==null) return JsonResult.createByError();
		
		//旧密码正确
		if(u.getPassword().equals(oldPassword)) {
			int updatePass = updateUserPassword(paramMap);
			if(updatePass==1) {
				return JsonResult.createBySuccess();
			}else {
				return JsonResult.createByError();
			}
		}else {
			return JsonResult.createByErrorCode(2);
		}
	}
	
	@Override
	public int updateUserPassword(Map paramMap) {
		
		return userMapper.updateUserPassword(paramMap);
		
	}

}
