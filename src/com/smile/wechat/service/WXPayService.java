package com.smile.wechat.service;

import com.smile.wechat.model.pojo.PayNotice;
import com.smile.wechat.model.pojo.WXPayRecord;

public interface WXPayService {

	/**
	 * 
	 * lhyan3
	 * 2015年3月14日下午3:59:25
	 * TODO
	 * @param notice
	 */
	void savePayNotice(PayNotice notice);
	
	/**
	 * 保存支付记录
	 * lhyan3
	 * 2015年4月16日下午3:43:25
	 * TODO
	 * @param record
	 */
	public void savePayRecord(WXPayRecord record);

	/**
	 * 改变订单状态和支付状态
	 * lhyan3
	 * 2015年4月30日上午9:44:08
	 * TODO
	 * @param orderId
	 * @param pay
	 */
	void updateOrderPayStatus(String orderId, int pay);
	

}
