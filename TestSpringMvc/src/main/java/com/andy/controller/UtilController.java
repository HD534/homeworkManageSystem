package com.andy.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.andy.Util.DateUtils;
import com.andy.Util.MyPropertiesUtil;
import com.andy.Util.UUIDUtils;
import com.andy.bean.JsonResult;

@Controller
public class UtilController {
	
	@ResponseBody
	@RequestMapping(value = "/util/upload", method = RequestMethod.POST)
	public <T> JsonResult<T> upload(HttpServletRequest request, @RequestParam("desc") String desc,
			@RequestParam("file") MultipartFile file,Model model)  {
		
		String path = request.getServletContext().getRealPath("/files/");
		path = MyPropertiesUtil.getProValueByKey("uploadPath");
		System.out.println(desc);
		if(!file.isEmpty()) {
			
			System.out.println("servlet:"+request.getServletContext().getContextPath());
			System.out.println(path);
			String filename = file.getOriginalFilename();
			File filepath = new File(path,filename);
			
			//判断路径是否存在 不存在新建一个
			if(!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			try {
				file.transferTo(new File(path+File.separator+filename));
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return  JsonResult.createByErrorMsg(e.toString());
			}
			
		}
		return  JsonResult.createBySuccess();
	}

	@RequestMapping(value = "/upload")
	public String uploadForm() {
		return "upload";
	}
	

	@RequestMapping(value = "/util/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,
			@RequestParam("filename")String filename, Model model) throws Exception {
		
		
		String path = MyPropertiesUtil.getProValueByKey("uploadPath");
		
		File file = new File(path+File.separator+filename);
		HttpHeaders headers = new HttpHeaders();
		//下载显示文件名，解决中文乱码的问题
		String downloadFilename = new String(filename.getBytes("UTF-8"),"iso-8859-1");
		//通知浏览器以attachment（下载方式）打开图片
		headers.setContentDispositionFormData("attachment", downloadFilename);
		//application/octet-stream : 二进制流数据（最常见的文件下载）
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 201 httpStatus.Created
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}
/*	
	@RequestMapping(value="/uploadFile",method=RequestMethod.POST)
	public Msg uploadPic(MultipartHttpServletRequest multiRequest){
		try {
			Set<String> resultFilePaths = fileUploadUtil.saveFile(multiRequest, FileTypeEnum.FAQ);
			return outer().data(resultFilePaths);
		} catch (Exception e) {
			String msg = getErrorMsg(e);
			logger.error("上传失败：{}",msg);
			return outer().failureMsg(msg);
		}
	}*/
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/util/uploadMul", method = RequestMethod.POST)
	public  JsonResult uploadMul(HttpServletRequest request, 
			MultipartHttpServletRequest multiRequest,Model model)  {
		
		String realPath = MyPropertiesUtil.getProValueByKey("uploadPath");
		System.out.println(realPath);
		
		Iterator<String> filesNames = multiRequest.getFileNames(); // 获得所有的文件名
		Set<String> resultFilePaths = new HashSet<String>();
		while (filesNames.hasNext()) { // 迭代，对单个文件进行操作
			String fileName = filesNames.next();
			MultipartFile file = multiRequest.getFile(fileName);
			
			if(!file.isEmpty()) {
				
				String originalFilename = file.getOriginalFilename();
				
				String resultFilePath = DateUtils.getCurrentDateStr() +File.separator + UUIDUtils.getUUID() 
				+ originalFilename.substring(originalFilename.lastIndexOf('.'), originalFilename.length());
				
				System.out.println("resultFilePath= "+resultFilePath);
				
				String filename = file.getOriginalFilename();
				File filepath = new File(realPath,resultFilePath);
				
				//判断路径是否存在 不存在新建一个
				if(!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				try {
					file.transferTo(new File(realPath+File.separator+resultFilePath));
					resultFilePaths.add(filepath.getAbsolutePath());
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					return  JsonResult.createByErrorMsg(e.toString());
				}
				
			}
		}
		return  JsonResult.createBySuccessData(resultFilePaths);
	}

}
