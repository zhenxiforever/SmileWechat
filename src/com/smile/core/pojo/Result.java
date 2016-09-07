package com.smile.core.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.smile.annotation.JsonObject;
import com.smile.annotation.JsonObjectProperty;
import com.smile.annotation.JsonProperty;

/**
 * 调用接口响应结果类
 * 
 */
@JsonObject
public class Result implements Serializable {

	private static final long serialVersionUID = -6963503022738848863L;

	private int code;
	private String message;
	
	//其它数据项
	private Map<String,Object> data = new HashMap<String,Object>();
	
	/**
	 * 获取属性：返回码
	 * 
	 * @return
	 */
	@JsonProperty(name="retcode")
	public int getCode() {
		return code;
	}
	
	/**
	 * 设置属性： 返回码
	 * 
	 * @param
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * 获取属性：返回信息
	 * 
	 * @return
	 */
	@JsonProperty(name="retmsg")
	public String getMessage() {
		return message;
	}
	
	/**
	 * 设置属性：返回信息
	 * 
	 * @param
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 获取属性：其它数据项
	 * 
	 * @return
	 */
	@JsonObjectProperty
	public Map<String, Object> getData() {
		return data;
	}
	
}
