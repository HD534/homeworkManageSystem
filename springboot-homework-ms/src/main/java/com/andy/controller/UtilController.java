package com.andy.controller;

import com.andy.common.JsonResult;
import com.andy.model.AttachedFile;
import com.andy.service.AttachedFileService;
import com.andy.utils.DateUtils;
import com.andy.utils.MyPropertiesUtil;
import com.andy.utils.UUIDUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Controller
public class UtilController {
	
	@Autowired
	AttachedFileService attachedFileService;

	
	@ResponseBody
	@RequestMapping(value = "/util/upload", method = RequestMethod.POST)
	public <T> JsonResult<T> upload(HttpServletRequest request, @RequestParam("desc") String desc,
                                    @RequestParam("file") MultipartFile file, Model model)  {
		
		String path = request.getServletContext().getRealPath("/files/");
		path = MyPropertiesUtil.getUploadPath();
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
                                           @RequestParam("fileId")String fileId, Model model) throws Exception {
		
		AttachedFile downloadFile = attachedFileService.selectFileByFileId(fileId);
		
		String filePath = downloadFile.getFilePath();
		String path = MyPropertiesUtil.getUploadPath();
		
		File file = new File(path+File.separator+filePath);
		HttpHeaders headers = new HttpHeaders();
		//下载显示文件名，解决中文乱码的问题
		String downloadFilename = new String(downloadFile.getFileRealName().getBytes("UTF-8"),"iso-8859-1");
		//通知浏览器以attachment（下载方式）打开图片
		headers.setContentDispositionFormData("attachment", downloadFilename);
		//application/octet-stream : 二进制流数据（最常见的文件下载）
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 201 httpStatus.Created
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
	}

	@RequestMapping(value="/util/excelTemplate",method=RequestMethod.GET)
	public ResponseEntity<byte[]> getExcelTemplate() throws IOException {
		String downloadFileName = "excelTemplate.xlsx";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment",downloadFileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		File file = ResourceUtils.getFile("classpath:"+downloadFileName);

		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/util/uploadMul", method = RequestMethod.POST)
	public  JsonResult uploadMul(HttpServletRequest request, HttpSession session,
                                 MultipartHttpServletRequest multiRequest, Model model)  {
		
		String realPath = MyPropertiesUtil.getUploadPath();
		
		Iterator<String> filesNames = multiRequest.getFileNames(); // 获得所有的文件名
		
		Set<String> resultFilePaths = new HashSet<String>(); //返回值对象
		
		while (filesNames.hasNext()) { // 迭代，对单个文件进行操作
			String fileName = filesNames.next(); 
			MultipartFile file = multiRequest.getFile(fileName);
			
			if(!file.isEmpty()) {
				
				String originalFilename = file.getOriginalFilename();//原名
				//文件类型
				String fileType = originalFilename.substring(originalFilename.lastIndexOf('.'), originalFilename.length());
				//经过处理后的文件名
				String fileEditName =  UUIDUtils.getUUID() + fileType;
				//文件名加上日期 
				String resultFilePath = DateUtils.getCurrentDateStr() +File.separator +fileEditName;
				
				System.out.println("resultFilePath= "+resultFilePath);
				
				//创建文件路径
				File filepath = new File(realPath,resultFilePath);
				
				//判断路径是否存在 不存在新建一个
				if(!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				try {
					
					file.transferTo(new File(realPath+File.separator+resultFilePath));
					
					AttachedFile afile = new AttachedFile();
					afile.setCreateTime(new Date()); //创建时间
					afile.setFileId(UUIDUtils.getUUID()); //文件id
					afile.setFileName(fileEditName); //文件名
					afile.setFilePath(resultFilePath); //文件路径 以备下载
					afile.setFileRealName(originalFilename); //文件原始名
					afile.setFileRealPath(filepath.toString()); //文件真实缓存路径
					afile.setFileType(fileType); //文件类型
					String userId = (String) session.getAttribute("userId");
					afile.setFileUploader(userId);  //文件上传人
					attachedFileService.insertOneFile(afile);
					
					System.out.println(afile.toString());
					
					//返回值添加
					resultFilePaths.add(resultFilePath);
					
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					return  JsonResult.createByErrorMsg(e.toString());
				}
				
			}
		}
		return  JsonResult.createBySuccessData(resultFilePaths);
	}
	
	

}
