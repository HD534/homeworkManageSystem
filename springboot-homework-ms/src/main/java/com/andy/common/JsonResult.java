package com.andy.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JsonResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private T data;
	
	private  Map<String,Object> map = new HashMap<>();
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public void put(Object key, Object obj) {
		
	}
	
	public Object get(Object key) {
		return null;
	}
	
	protected JsonResult() {
		
	}
	
	protected JsonResult(int code) {
		this.code = code;
	}

	protected JsonResult(int code, T data) {
		this.code = code;
		this.data = data;
	}

	protected JsonResult(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	protected JsonResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	public int getcode() {
		return code;
	}

	public T getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

	/**
	 * @return
	 */
	public static <T> JsonResult<T> createBySuccess() {
		return new JsonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc());
	}

	/**
	 * @param msg
	 * @return
	 */
	public static <T> JsonResult<T> createBySuccessMsg(String msg) {
		return new JsonResult<T>(ResultCode.SUCCESS.getCode(), msg);
	}

	/**
	 * @param successCode
	 * @return
	 */
	public static <T> JsonResult<T> createBySuccessCode(int successCode) {
		return new JsonResult<T>(successCode, ResultCode.SUCCESS.getDesc());
	}

	/**
	 * @param data
	 * @return
	 */
	public static <T> JsonResult<T> createBySuccessData(T data) {
		return new JsonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), data);
	}

	/**
	 * @param successCode
	 * @param msg
	 * @return
	 */
	public static <T> JsonResult<T> createBySuccess(int successCode,String msg) {
		return new JsonResult<T>(successCode, msg);
	}
	
	/**
	 * @param successCode
	 * @param data
	 * @return
	 */
	public static <T> JsonResult<T> createBySuccess(int successCode, T data) {
		return new JsonResult<T>(successCode,ResultCode.SUCCESS.getDesc(),data);
	}
	
	/**
	 * @param successCode
	 * @param msg
	 * @param data
	 * @return
	 */
	public static <T> JsonResult<T> createBySuccess(int successCode,String msg, T data) {
		return new JsonResult<T>(successCode,msg,data);
	}
	
	public JsonResult add(String key,Object obj) {
		this.map.put(key, obj);
		return this;
	}

	/**
	 * @return
	 */
	public static <T> JsonResult<T> createByError() {
		return new JsonResult<T>(ResultCode.ERROR.getCode(), ResultCode.ERROR.getDesc());
	}


	/**
	 * @param msg
	 * @return
	 */
	public static <T> JsonResult<T> createByErrorMsg(String msg) {
		return new JsonResult<T>(ResultCode.ERROR.getCode(), msg);
	}


	/**
	 * @param errorCode
	 * @return
	 */
	public static <T> JsonResult<T> createByErrorCode(int errorCode) {
		return new JsonResult<T>(errorCode, ResultCode.ERROR.getDesc());
	}


	/**
	 * @param data
	 * @return
	 */
	public static <T> JsonResult<T> createByErrorData(T data) {
		return new JsonResult<T>(ResultCode.ERROR.getCode(), ResultCode.ERROR.getDesc(), data);
	}


	/**
	 * @param errorCode
	 * @param msg
	 * @return
	 */
	public static <T> JsonResult<T> createByError(int errorCode,String msg) {
		return new JsonResult<T>(errorCode, msg);
	}
	

	/**
	 * @param errorCode
	 * @param data
	 * @return
	 */
	public static <T> JsonResult<T> createByError(int errorCode, T data) {
		return new JsonResult<T>(errorCode,ResultCode.ERROR.getDesc(),data);
	}
	

	/**
	 * @param errorCode
	 * @param msg
	 * @param data
	 * @return
	 */
	public static <T> JsonResult<T> createByError(int errorCode,String msg, T data) {
		return new JsonResult<T>(errorCode,msg,data);
	}
}
