package com.smile.wechat.model.pojo;


import java.io.Serializable;
import java.util.Date;

/**
 * 二维码对象
 * 
 */
public class Qrcode implements Serializable {

	private static final long serialVersionUID = -6768469400646260667L;

	private String qrcodeid;
	private String accountid;	
	private String promotion_channel;
	private int action_type;
	private int expire_seconds;
	private int scene_id;
	private String ticket;
	private int onlinestat;
	private int authstat;
	private Date submit_date;
	private Date create_time;
	private String accountname;
	
	private String shopId;
	private String shopname;

	public Date getSubmit_date() {
		return submit_date;
	}

	public void setSubmit_date(Date submit_date) {
		this.submit_date = submit_date;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getOnlinestat() {
		return onlinestat;
	}

	public void setOnlinestat(int onlinestat) {
		this.onlinestat = onlinestat;
	}

	public int getAuthstat() {
		return authstat;
	}

	public void setAuthstat(int authstat) {
		this.authstat = authstat;
	}

	public String getQrcodeid() {
		return qrcodeid;
	}
	
	public void setQrcodeid(String qrcodeid) {
		this.qrcodeid = qrcodeid;
	}

	public String getPromotion_channel() {
		return promotion_channel;
	}

	public void setPromotion_channel(String promotion_channel) {
		this.promotion_channel = promotion_channel;
	}

	public int getAction_type() {
		return action_type;
	}

	public void setAction_type(int action_type) {
		this.action_type = action_type;
	}

	public int getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	public int getScene_id() {
		return scene_id;
	}

	public void setScene_id(int scene_id) {
		this.scene_id = scene_id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	/**
	 * 设置属性：服务号ID
	 * @param accountid
	 */
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	/**
	 * 获取属性：服务号ID
	 * 
	 * @return
	 */
	public String getAccountid() {
		return accountid;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccountname() {
		return accountname;
	} 
	
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

}
