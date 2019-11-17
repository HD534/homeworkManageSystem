package com.andy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Institute {
	
    private String instituteId;

    private String instituteName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

    
}