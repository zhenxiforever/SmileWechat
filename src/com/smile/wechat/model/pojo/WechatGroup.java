package com.smile.wechat.model.pojo;

/**
 * 微信用户分组 实体类
 * 
 * @project wechat-model
 * @author smile
 * @createDate 2016年6月19日
 */
public class WechatGroup {
	private int id;
	private String name;//分组名字，UTF8编码
	private int count;//分组内用户数量
	
	public WechatGroup(int id, String name,int count) {
		this.id = id;
		this.name = name;
		this.count = count;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
