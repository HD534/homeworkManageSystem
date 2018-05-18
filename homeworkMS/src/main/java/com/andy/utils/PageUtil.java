package com.andy.utils;

public class PageUtil {
	
	public static int getRowFrom(int page,int limit) {
		if(page<1) return -1;
		page = page-1;
		return page*limit;
	}

}
