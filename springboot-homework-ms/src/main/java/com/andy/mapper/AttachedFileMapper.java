package com.andy.mapper;

import com.andy.model.AttachedFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttachedFileMapper {
	
	int insertOneFile(AttachedFile file);
	
	int insertSelective(AttachedFile file);
	
	AttachedFile selectFileByFileId(String fileId);
	
	AttachedFile selectByPrimaryKey(String fileId);
	
	int deleteFileByFileId(String fileId);
	
	List<AttachedFile> listAttachedFile();
	
	List<AttachedFile> listAttachedFileByPage(@Param("rowFrom") int rowFrom, @Param("limit") int limit);
	
	List<AttachedFile> listAttachedFileByPage1(Map map);

	int selectCountNum();
	
}
