package com.andy.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Term {
    private String termId;

    private String termName;
    
    private String termValue;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTermValue() {
		return termValue;
	}

	public void setTermValue(String termValue) {
		this.termValue = termValue;
	}

	@Override
	public String toString() {
		return "Term [termId=" + termId + ", termName=" + termName + ", termValue=" + termValue + ", createDate="
				+ createDate + "]";
	}

  
}