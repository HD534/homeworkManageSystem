package com.andy.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MyPropertiesUtil {
	
	public static String getProValueByKey(String queryKey) {
		Properties props = new Properties();
		String rtn = null;
		try {  
            props= PropertiesLoaderUtils.loadAllProperties("upload.properties");
            for(Object key:props.keySet()){  
            	if(key!=null&&key.equals(queryKey)){
            		rtn =  props.get(key).toString();
            		break;
            	}
            } 
        } catch (Exception e) {  
        	e.printStackTrace();
        }
		return rtn;
		
	}


	private static String tempPath;
	private static String uploadPath;

	@Value("${homework.tempPath}")
	public void setTempPath(String tempPath) {
		MyPropertiesUtil.tempPath = tempPath;
	}

	@Value("${homework.uploadPath}")
	public void setUploadPath(String uploadPath) {
		MyPropertiesUtil.uploadPath = uploadPath;
	}

	public static String getUploadPath() {
		return uploadPath;
	}

	public static String getTempPath() {
		return tempPath;
	}
}
