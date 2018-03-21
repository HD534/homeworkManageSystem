package com.andy.bean;

import java.io.Serializable;

import com.andy.common.ResultCode;

/**
 * @author ASNPHTJ
 *
 * @param <T>
 */
/**
 * @author ASNPHTJ
 *
 * @param <T>
 */
/**
 * @author ASNPHTJ
 *
 * @param <T>
 */
public class JsonResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private T data;

	private JsonResult(int code) {
		this.code = code;
	}

	private JsonResult(int code, T data) {
		this.code = code;
		this.data = data;
	}

	private JsonResult(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	private JsonResult(int code, String msg) {
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
