package com.andy.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
    private String userId;

    private String userCode;

    private String userName;

    private String password;

    private String userType;

    private Integer age;

    private String sex;

    private String email;
    
    private String phone;

    private String instituteId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userCode=" + userCode + ", userName=" + userName + ", password=" + password
				+ ", userType=" + userType + ", age=" + age + ", sex=" + sex + ", email=" + email + ", phone=" + phone
				+ ", instituteId=" + instituteId + ", createDate=" + createDate + "]";
	}

    
}