package com.andy.Util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Test {
	
	public static void main(String[] args) {
		Properties props = new Properties();
		try {  
            props=PropertiesLoaderUtils.loadAllProperties("upload.properties");  
            for(Object key:props.keySet()){  
                System.out.print(key+":");  
                System.out.println(props.get(key));  
            }  
        } catch (Exception e) {  
        	e.printStackTrace();
        }
	}

}
