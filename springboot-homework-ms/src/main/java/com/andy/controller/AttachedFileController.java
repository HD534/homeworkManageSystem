package com.andy.controller;

import com.alibaba.fastjson.JSONObject;
import com.andy.model.AttachedFile;
import com.andy.service.AttachedFileService;
import com.andy.utils.PageUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.andy.commom.JsonObjectForTable;

@Controller
public class AttachedFileController<T> {
	
	@Autowired
	AttachedFileService attachedFileService;
	
	@ResponseBody
	@RequestMapping(value = "/file/listFiles")
	public JSONObject listFiles(Model model, int page, int limit) {
//		List<AttachedFile> AttachedFiles =  attachedFileService.listAttachedFile();
		List<AttachedFile> AttachedFiles =  attachedFileService.listAttachedFileByPage(page, limit);
		int count = attachedFileService.selectCountNum();
		JSONObject json = new JSONObject();
		json.put("data", AttachedFiles);
		json.put("msg", "success");
		json.put("code", 0);
		json.put("count", count);
		return json;
		/*JsonResult<List<AttachedFile>> j = JsonResult.createBySuccessData(AttachedFiles).add("count", count);
		//JsonObjectForTable.createBySuccessData(AttachedFiles);
		System.out.println(j.toString());
		return j;*/
	}


	
	@RequestMapping(value = "/attachFiles/downloadZip")
	public ResponseEntity<byte[]> downloadZipFile(HttpServletRequest request,
                                                  @RequestParam(value="fileIds") String[] fileIds, Model model, @RequestParam(value="destFileName") String destFileName) throws Exception {
		List<String> fileIdList = Arrays.asList(fileIds);
		File zipFile = attachedFileService.downloadZipFile(fileIdList,destFileName);
		
		HttpHeaders headers = new HttpHeaders();
		//下载显示文件名，解决中文乱码的问题
		String downloadFilename = new String(zipFile.getName().getBytes("UTF-8"),"iso-8859-1");
		//通知浏览器以attachment（下载方式）打开图片
		headers.setContentDispositionFormData("attachment", downloadFilename);
		//application/octet-stream : 二进制流数据（最常见的文件下载）
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 201 httpStatus.Created
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(zipFile),headers, HttpStatus.CREATED);
	}
	
	@ResponseBody
	@RequestMapping(value = "/file/listFilesbyPage1")
	public JSONObject listFilesByPage1(Model model, int page, int limit) {
//		List<AttachedFile> AttachedFiles =  attachedFileService.listAttachedFile();
		Map<String,Object> paramMap = new HashMap<>();
		JSONObject json = new JSONObject();
		int rowFrom = PageUtil.getRowFrom(page, limit);
		if(rowFrom<0) {
			json.put("code", 1);
			return json;
		}
		paramMap.put("rowFrom", rowFrom);
		paramMap.put("limit", limit);
		List<AttachedFile> AttachedFiles = attachedFileService.listAttachedFileByPage1(paramMap);
		//List<AttachedFile> AttachedFiles =  attachedFileService.listAttachedFileByPage(page, limit);
		int count = attachedFileService.selectCountNum();
		json.put("data", AttachedFiles);
		json.put("msg", "success");
		json.put("code", 0);
		json.put("count", count);
		return json;
		/*JsonResult<List<AttachedFile>> j = JsonResult.createBySuccessData(AttachedFiles).add("count", count);
		//JsonObjectForTable.createBySuccessData(AttachedFiles);
		System.out.println(j.toString());
		return j;*/
	}
	


}
