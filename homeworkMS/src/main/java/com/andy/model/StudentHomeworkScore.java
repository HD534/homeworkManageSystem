package com.andy.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudentHomeworkScore {
    private String studentHomeworkScoreId;

    private String studentId;

    private String homeworkId;

    private String creator;

    private Integer score;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

    private String updater;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updateDate;

    private String comment;

	public String getStudentHomeworkScoreId() {
		return studentHomeworkScoreId;
	}

	public void setStudentHomeworkScoreId(String studentHomeworkScoreId) {
		this.studentHomeworkScoreId = studentHomeworkScoreId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(String homeworkId) {
		this.homeworkId = homeworkId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

    
}