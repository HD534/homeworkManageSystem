package com.andy.service;

import com.andy.common.JsonResult;
import com.andy.model.AttachedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface AttachedFileService {

	List<AttachedFile> listAttachedFile();
	
	List<AttachedFile> listAttachedFileByPage1(Map map);
	
	List<AttachedFile> listAttachedFileByPage(int page, int limit);

	int insertOneFile(AttachedFile file);

	AttachedFile selectFileByFileId(String fileId);

	int deleteFileByFileId(String fileId);

	int selectCountNum();

	File downloadZipFile(List<String> fileIdList, String descFileName) throws Exception;

	JsonResult saveOneFile(MultipartFile file, String userId);
	
}
