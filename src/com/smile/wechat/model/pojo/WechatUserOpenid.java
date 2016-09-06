package com.smile.wechat.model.pojo;

import java.util.ArrayList;
import java.util.List;

public class WechatUserOpenid {
	private String next_openid;
	
	private List<String> openid_list=new ArrayList<String>();

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	
	public List<String> getOpenid_list() {
		return openid_list;
	}

	public void setOpenid_list(List<String> openid_list) {
		this.openid_list = openid_list;
	}

	public void addOpenid(String openid){
		openid_list.add(openid);
	}
}
