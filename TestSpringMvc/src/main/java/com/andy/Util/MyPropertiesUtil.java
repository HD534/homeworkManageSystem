package com.andy.Util;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public class MyPropertiesUtil {
	
	public static String getProValueByKey(String queryKey) {
		Properties props = new Properties();
		String rtn = null;
		try {  
            props=PropertiesLoaderUtils.loadAllProperties("upload.properties");  
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

}
