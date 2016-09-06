package com.smile.wechat.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 微信菜单 类
 * 
 * @project wechat-model
 * @author smile
 * @createDate 2016年6月19日
 */
public class WechatMenu implements Serializable {

	private static final long serialVersionUID = -1704877922813020038L;
	private String id;
	private String name;
	private String parent_id;
	private String type;
	private String content;
	private int post_flag;
	private int delete_flag;
	private String accountid;
	
	private List<WechatMenu> sub_button=new ArrayList<WechatMenu>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPost_flag() {
		return post_flag;
	}
	public void setPost_flag(int post_flag) {
		this.post_flag = post_flag;
	}
	public int getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	} 
	
	public List<WechatMenu> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<WechatMenu> sub_button) {
		this.sub_button = sub_button;
	}
}
