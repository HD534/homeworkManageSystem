package com.andy.utils;

import java.util.UUID;

import org.junit.Test;

public class UUIDUtils {
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", ""); 
	}
	
	
	@Test
	public void test4() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		System.out.println(uuid.length());
		System.out.println(UUID.randomUUID().toString().replace("-", "")); 
	}

}
