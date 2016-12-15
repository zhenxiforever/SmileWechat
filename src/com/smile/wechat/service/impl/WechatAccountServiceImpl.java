package com.smile.wechat.service.impl;

import java.util.List;

import com.smile.wechat.dao.WechatAccountDao;
import com.smile.wechat.model.pojo.WechatAccount;
import com.smile.wechat.schedule.WechatAccountSchedule;
import com.smile.wechat.service.IWechatAccountService;

/**
 * 微信服务号 账号信息 服务类
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月20日
 */
public class WechatAccountServiceImpl implements IWechatAccountService{

	private WechatAccountDao wechatAccountDao;

	public void setWechatAccountDao(WechatAccountDao wechatAccountDao) {
		this.wechatAccountDao = wechatAccountDao;
	}
	
	@Override
	public void loadAccountData() {
		List<WechatAccount> list=wechatAccountDao.getWechatAccountList();
		for(WechatAccount account:list){
			WechatAccountSchedule.put(account);
		}
	}

	@Override
	public WechatAccount getWechatAccountById(String accountId) {
		WechatAccount account = wechatAccountDao.getWechatAccountById(accountId);
		return account;
	}

}
