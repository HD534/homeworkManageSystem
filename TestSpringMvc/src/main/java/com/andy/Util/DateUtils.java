package com.andy.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class DateUtils {
	
	public static String getCurrentDateStr() {
		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(currentTime);
	}
	
	@Test
	public void test1() {
		Date date = new Date();
		System.out.println(date);
	}
	
	@Test
	public void test2() {
		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println(dateFormat.format(currentTime));
	}
	
	private void getDateByFormat(String format) {
		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		
		System.out.println(dateFormat.format(currentTime));
	}
	
	@Test
	public void test3() {
		getDateByFormat("yyyy-MM-dd");
	}

	
	
	

}
