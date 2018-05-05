package com.andy.common;

import com.andy.common.ResultCode;

@SuppressWarnings("rawtypes")
public class JsonObjectForTable<T> extends JsonResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int count;
	
	
	private JsonObjectForTable() {
		
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
		
	
	public static <T> JsonObjectForTable createBySuccessDataCount(T data,int count) {
		JsonObjectForTable j = (JsonObjectForTable) new JsonObjectForTable().createBySuccessData(data);
		j.setCount(count);
		return j;
	}

}
