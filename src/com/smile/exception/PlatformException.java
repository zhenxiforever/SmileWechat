package com.smile.exception;

/**
 * 平台系统异常封装
 * 
 * @project SmileWechat
 * @author smile
 * @createDate 2016年9月7日
 */
public class PlatformException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9106767966896205972L;

	/**
	 * 错误码
	 */
	private String errcode;
	
	/**
	 * 错误信息
	 */
	private String errmsg;
	
	public PlatformException() {
		super();
	}

	public PlatformException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlatformException(String message) {
		super(message);
	}

	public PlatformException(Throwable cause) {
		super(cause);
	}

	/**
	 * 错误码
	 */
	public String getErrcode() {
		return errcode;
	}

	/**
	 * 错误码
	 */
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	/**
	 * 错误信息
	 */
	public String getErrmsg() {
		return errmsg;
	}

	/**
	 * 错误信息
	 */
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
