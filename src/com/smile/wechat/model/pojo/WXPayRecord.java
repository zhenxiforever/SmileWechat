package com.smile.wechat.model.pojo;

import java.util.Date;

/**
 * 交易记录表
 * @author lhyan3
 * 2015年4月16日下午3:42:19
 */
public class WXPayRecord {
	
	private String id;
	
	private String openid;
	
	private String device_info;//设备号
	
	private String payLocation;//支付路径 1正常 2店铺付款扫码
	
	private String orderId;
	
	private int total_fee;//以分为单位
	
	private String trade_type;//交易类型
	
	private String body;//商品描述
	
	private String product_id;//trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
	
	private Date recordTime;//交易时间
	
	private String flag;//1成功 -1失败

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

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getPayLocation() {
		return payLocation;
	}

	public void setPayLocation(String payLocation) {
		this.payLocation = payLocation;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getTotal_fee() {
		return total_fee;
	}
	
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
