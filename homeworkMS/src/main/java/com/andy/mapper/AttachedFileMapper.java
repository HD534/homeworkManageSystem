package com.andy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.andy.model.AttachedFile;

public interface AttachedFileMapper {
	
	int insertOneFile(AttachedFile file);
	
	int insertSelective(AttachedFile file);
	
	AttachedFile selectFileByFileId(String fileId);
	
	AttachedFile selectByPrimaryKey(String fileId);
	
	int deleteFileByFileId(String fileId);
	
	List<AttachedFile> listAttachedFile();
	
	List<AttachedFile> listAttachedFileByPage(@Param("rowFrom") int rowFrom,@Param("limit") int limit);
	
	List<AttachedFile> listAttachedFileByPage1(Map map);

	int selectCountNum();
	
}
