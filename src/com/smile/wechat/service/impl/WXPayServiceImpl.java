package com.smile.wechat.service.impl;

import com.smile.wechat.dao.WXPayDao;
import com.smile.wechat.model.pojo.PayNotice;
import com.smile.wechat.model.pojo.WXPayRecord;
import com.smile.wechat.service.WXPayService;

public class WXPayServiceImpl implements WXPayService{
	
	private WXPayDao wxPayDao;
	
	
	public void setWxPayDao(WXPayDao wxPayDao) {
		this.wxPayDao = wxPayDao;
	}
	

	@Override
	public void savePayNotice(PayNotice notice) {
		wxPayDao.savePayNotice(notice);
	}

	@Override
	public void savePayRecord(WXPayRecord record) {
		wxPayDao.savePayRecord(record);
	}

	/**
	 * 更新订单状态
	 */
	@Override
	public void updateOrderPayStatus(String orderId, int pay) {
	}

}
