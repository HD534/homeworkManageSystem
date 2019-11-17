package com.andy.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class MapUtil {
	
	public static Object mapToObject(Map map,Class<?> beanClass) throws Exception {
		 if (map == null)  
	            return null;  
	  
	        Object obj = beanClass.newInstance();  
	  
	        BeanUtils.populate(obj, map);  
	  
	        return obj;  
	}

}
