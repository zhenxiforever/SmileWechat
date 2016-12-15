package com.smile.exception;

/**
 * 微信异常封装
 * 
 * @project SmileWechat
 * @author smile
 * @createDate 2016年9月7日
 */
public class WechatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6612663000417327940L;

	/**
	 * 错误码
	 */
	private String errcode;
	
	/**
	 * 错误信息
	 */
	private String errmsg;
	
	public WechatException() {
		super();
	}

	public WechatException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		this.errmsg = arg0;
	}

	public WechatException(String arg0) {
		super(arg0);
		this.errmsg = arg0;
	}

	public WechatException(Throwable arg0) {
		super(arg0);
	}

	public WechatException(String errcode,String errmsg){
		this.errcode = errcode;
		this.errmsg  = errmsg;
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
