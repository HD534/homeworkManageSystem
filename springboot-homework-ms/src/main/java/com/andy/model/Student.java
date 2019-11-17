package com.andy.model;

public class Student {
    private String studentId;

    private String userId;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", userId=" + userId + "]";
	}

   
}