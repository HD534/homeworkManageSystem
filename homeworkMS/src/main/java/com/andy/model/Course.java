package com.andy.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Course {
    private String courseId;

    private String courseName;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

    private String courseDesc;
    

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}


	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", createDate=" + createDate
				+ ", courseDesc=" + courseDesc +  "]";
	}
	

    
}