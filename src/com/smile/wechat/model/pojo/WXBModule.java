package com.smile.wechat.model.pojo;

import java.io.Serializable;

/**
 * 业务逻辑模块
 * 
 * @author 
 * @createTime 
 * @history 1.修改时间,修改;修改内容：
 * 
 */
public class WXBModule implements Serializable{

	private static final long serialVersionUID = -2659251941643096657L;
	private String module_id;	
	private String module_name;	
	private String module_path;	

	/**
	 * 获取属性：模块ID
	 * 
	 * @return
	 */
	public String getModule_id() {
		return module_id;
	}

	/**
	 * 设置属性：模块ID
	 * 
	 * @param
	 */
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	/**
	 * 获取属性：模块名称
	 * 
	 * @return
	 */
	public String getModule_name() {
		return module_name;
	}

	/**
	 * 设置属性：模块名称
	 * 
	 * @param
	 */
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	/**
	 * 获取属性：模块路径
	 * 
	 * @return
	 */
	public String getModule_path() {
		return module_path;
	}

	/**
	 * 设置属性：模块路径
	 * 
	 * @param
	 */
	public void setModule_path(String module_path) {
		this.module_path = module_path;
	}
	
}
