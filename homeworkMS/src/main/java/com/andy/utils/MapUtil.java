package com.andy.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class MapUtil {
	
	public static Object mapToObject(Map map,Class<?> beanClass) throws Exception {
		 if (map == null)  
	            return null;  
	  
	        Object obj = beanClass.newInstance();  
	  
	        BeanUtils.populate(obj, map);  
	  
	        return obj;  
	}

}
