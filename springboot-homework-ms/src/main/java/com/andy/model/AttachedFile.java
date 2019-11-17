package com.andy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AttachedFile {
    private String fileId;

    private String fileName;

    private String fileRealName;

    private String filePath;

    private String fileRealPath;

    private String fileType;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    private String fileUploader;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileRealPath() {
		return fileRealPath;
	}

	public void setFileRealPath(String fileRealPath) {
		this.fileRealPath = fileRealPath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFileUploader() {
		return fileUploader;
	}

	public void setFileUploader(String userId) {
		this.fileUploader = userId;
	}

	@Override
	public String toString() {
		return "AttachedFile [fileId=" + fileId + ", fileName=" + fileName + ", fileRealName=" + fileRealName
				+ ", filePath=" + filePath + ", fileRealPath=" + fileRealPath + ", fileType=" + fileType
				+ ", createTime=" + createTime + ", fileUploader=" + fileUploader + "]";
	}

	

}