package com.andy.utils;

import org.springframework.stereotype.Component;

@Component
public class FileUploadUtil {
	
	private String fileUploadBasePath;
/*
	public Set<String> saveFile(MultipartHttpServletRequest multiRequest,FileTypeEnum type) throws IOException {

		String filePath = fileUploadBasePath+type.getCode();
		File dir = new File(filePath);
		if(!dir.exists()) dir.mkdirs();
		
		Iterator<String> filesNames = multiRequest.getFileNames(); // 获得所有的文件名
		Set<String> resultFilePaths = new HashSet<String>();
		while (filesNames.hasNext()) { // 迭代，对单个文件进行操作
			String fileName = filesNames.next();
			MultipartFile file = multiRequest.getFile(fileName);
			String originalFilename = file.getOriginalFilename();
			if (!file.isEmpty()) {
			
				String resultFilePath = DateUtils.getCurrentDateStr() +File.separator + GeneratorKey.genaraId() 
				+ originalFilename.substring(originalFilename.lastIndexOf('.'), originalFilename.length());
				
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath, resultFilePath));
				resultFilePaths.add(type.getCode()+File.separator+resultFilePath);
			}
		}

		return resultFilePaths;
	}

	public boolean deleteFile(String picPath, FileTypeEnum faq) throws IOException{
		return FileUtils.deleteQuietly(new File(fileUploadBasePath + picPath));
	}*/
	

}
