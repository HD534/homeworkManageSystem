package com.andy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TblClass {
    private String classId;

    private String className;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "TblClass [classId=" + classId + ", className=" + className + ", createDate=" + createDate + "]";
	}

    
}