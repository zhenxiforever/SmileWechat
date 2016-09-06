package com.smile.wechat.model.pojo;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -111044443714167441L;

	private String id;
	
	private String openid;
	private String accountid;	//服务号应用
	private String nickname;//用户的昵称
	private int groupid;//分组ID
	private String country;//国籍
	private String province;//省份
	private String city;//城市
	private int sex;//性别
	private String imageurl;//头像URL
	private String language;//语言
	private int issubscribe;//关注标识:1关注 ;-1取消关注
	private String qrcodeid;//二维码ID
	private Date subscribetime;//关注时间
	private String custname;//真实姓名
	private Date updatetime;//更新时间
	private String mobilephone;//手机号码
	private int isband;//默认是0,0未绑定，1已绑定
	private int subscribetype;//关注方式 1扫码 2搜索
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getIssubscribe() {
		return issubscribe;
	}

	public void setIssubscribe(int issubscribe) {
		this.issubscribe = issubscribe;
	}

	public String getQrcodeid() {
		return qrcodeid;
	}

	public void setQrcodeid(String qrcodeid) {
		this.qrcodeid = qrcodeid;
	}

	public Date getSubscribetime() {
		return subscribetime;
	}

	public void setSubscribetime(Date subscribetime) {
		this.subscribetime = subscribetime;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public int getIsband() {
		return isband;
	}

	public void setIsband(int isband) {
		this.isband = isband;
	}

	public int getSubscribetype() {
		return subscribetype;
	}

	public void setSubscribetype(int subscribetype) {
		this.subscribetype = subscribetype;
	}
}
