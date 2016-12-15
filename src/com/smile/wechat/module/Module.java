package com.smile.wechat.module;

import java.io.Serializable;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;

/**
 * 业务逻辑模块抽象类，所有功能模块必须实现
 * 
 * 
 */
public abstract class Module implements Serializable{

	private static final long serialVersionUID = -8722390275945202027L;

	/**
	 * 同步请求消息处理
	 * @param requestMessage
	 * @return
	 */
	public abstract ResponseMessage syncRequest(RequestMessage requestMessage);
	
	protected String exception;	

	/**
	 * 获取属性：异常信息 
	 * @return
	 */
	public String getException() {
		return exception;
	}	

	/**
	 * 获取操作名称
	 * @return
	 */
	public abstract String getOperate();
}
