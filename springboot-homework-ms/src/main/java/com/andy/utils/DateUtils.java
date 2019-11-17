package com.andy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String getCurrentDateStr() {
		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(currentTime);
	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
	public static Date stringToDate(String dateStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void getDateByFormat(String format) {
		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		
		System.out.println(dateFormat.format(currentTime));
	}
	
	
//	@Test
//	public void test1() {
//		Date date = new Date();
//		System.out.println(date);
//	}
//
//	@Test
//	public void test2() {
//		Date currentTime = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//		System.out.println(dateFormat.format(currentTime));
//	}
//
//
//
//	@Test
//	public void test3() {
//		//getDateByFormat("yyyy-MM-dd");
//		System.out.println(new Date());
//		System.out.println(dateToString(new Date()));
//
//	}

	
	
	

}
