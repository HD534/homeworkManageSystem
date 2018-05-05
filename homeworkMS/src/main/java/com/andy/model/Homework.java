package com.andy.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Homework {
    private String homeworkId;

    private String homeworkName;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date DueDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publishDate;

    private String homeworkType;

    private String homeworkDesc;

	public String getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(String homeworkId) {
		this.homeworkId = homeworkId;
	}

	public String getHomeworkName() {
		return homeworkName;
	}

	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getHomeworkType() {
		return homeworkType;
	}

	public void setHomeworkType(String homeworkType) {
		this.homeworkType = homeworkType;
	}

	public String getHomeworkDesc() {
		return homeworkDesc;
	}

	public void setHomeworkDesc(String homeworkDesc) {
		this.homeworkDesc = homeworkDesc;
	}

	@Override
	public String toString() {
		return "Homework [homeworkId=" + homeworkId + ", homeworkName=" + homeworkName + ", createDate=" + createDate
				+ ", DueDate=" + DueDate + ", publishDate=" + publishDate + ", homeworkType=" + homeworkType
				+ ", homeworkDesc=" + homeworkDesc + "]";
	}
	
}