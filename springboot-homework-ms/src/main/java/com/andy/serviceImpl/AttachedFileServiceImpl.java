package com.andy.serviceImpl;

import com.andy.common.JsonResult;
import com.andy.mapper.AttachedFileMapper;
import com.andy.model.AttachedFile;
import com.andy.service.AttachedFileService;
import com.andy.utils.DateUtils;
import com.andy.utils.MyPropertiesUtil;
import com.andy.utils.UUIDUtils;
import com.andy.utils.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AttachedFileServiceImpl implements AttachedFileService {

	@Autowired
	private AttachedFileMapper attachedFileMapper;

	@Override
	public List<AttachedFile> listAttachedFile() {
		List<AttachedFile> AttachedFiles = attachedFileMapper.listAttachedFile();
		return AttachedFiles;
	}

	@Override
	public int insertOneFile(AttachedFile file) {
		return attachedFileMapper.insertSelective(file);
	}

	@Override
	public AttachedFile selectFileByFileId(String fileId) {
		return attachedFileMapper.selectByPrimaryKey(fileId);
	}

	@Override
	public int deleteFileByFileId(String fileId) {
		return attachedFileMapper.deleteFileByFileId(fileId);
	}

	@Override
	public int selectCountNum() {
		// TODO Auto-generated method stub
		return attachedFileMapper.selectCountNum();
	}

	@Override
	public List<AttachedFile> listAttachedFileByPage(int page, int limit) {
		int rowFrom = limit * (page - 1);
		return attachedFileMapper.listAttachedFileByPage(rowFrom, limit);
	}

	@Override
	public File downloadZipFile(List<String> fileIdList,String descFileName) throws Exception {
		ArrayList<String> fileNameList = new ArrayList<String>();
		ArrayList<String> filePathList = new ArrayList<String>();

		// 循环获取要下载的文件的文件名和文件路径
		for (String fileId : fileIdList) {
			AttachedFile downloadFile = selectFileByFileId(fileId);
			fileNameList.add(downloadFile.getFileRealName());

			filePathList.add(
					DateUtils.dateToString(downloadFile.getCreateTime()) + File.separator + downloadFile.getFileName());
		}

		// 获取下载文件的总压缩包
		//下载文件的zip文件名
		return ZipUtil.getZip(filePathList, fileNameList, descFileName);

	}

	@Override
	public List<AttachedFile> listAttachedFileByPage1(Map map) {
		// TODO Auto-generated method stub
		return attachedFileMapper.listAttachedFileByPage1(map);
	}

	@Override
	public JsonResult saveOneFile(MultipartFile file, String userId) {

		if (file.isEmpty()) return JsonResult.createByError();
		String realPath = MyPropertiesUtil.getUploadPath();
		String originalFilename = file.getOriginalFilename();// 原名
		// 文件类型
		String fileType = originalFilename.substring(originalFilename.lastIndexOf('.'), originalFilename.length());
		// 经过处理后的文件名
		String fileEditName = UUIDUtils.getUUID() + fileType;
		// 文件名加上日期
		String resultFilePath = DateUtils.getCurrentDateStr() + File.separator + fileEditName;

		System.out.println("resultFilePath= " + resultFilePath);

		// 创建文件路径
		File filepath = new File(realPath, resultFilePath);

		// 判断路径是否存在 不存在新建一个
		if (!filepath.getParentFile().exists()) {
			filepath.getParentFile().mkdirs();
		}
		try {

			file.transferTo(new File(realPath + File.separator + resultFilePath));

			AttachedFile afile = new AttachedFile();
			String fileId = UUIDUtils.getUUID();
			afile.setCreateTime(new Date()); // 创建时间
			afile.setFileId(fileId); // 文件id
			afile.setFileName(fileEditName); // 文件名
			afile.setFilePath(resultFilePath); // 文件路径 以备下载
			afile.setFileRealName(originalFilename); // 文件原始名
			afile.setFileRealPath(filepath.toString()); // 文件真实缓存路径
			afile.setFileType(fileType); // 文件类型
			afile.setFileUploader(userId); // 文件上传人
			insertOneFile(afile);

			System.out.println(afile.toString());

			// 返回值添加
			return JsonResult.createBySuccessData(fileId);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return JsonResult.createByError();
		}

	}

}
