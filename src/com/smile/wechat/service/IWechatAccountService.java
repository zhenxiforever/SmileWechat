package com.smile.wechat.service;

import com.smile.wechat.model.pojo.WechatAccount;

/**
 * 微信 服务号account service
 * 
 * @project wechat-interface
 * @author smile
 * @createDate 2016年6月20日
 */
public interface IWechatAccountService {
	
	/**
	 * 加载 服务号 信息
	 * @author smile
	 * @date 2016年6月20日 void
	 */
	public void loadAccountData();
	
	/**
	 * 根据accountId获取 服务号信息
	 * @author smile
	 * @date 2016年6月20日
	 * @param accountId
	 * @return WechatAccount
	 */
	public WechatAccount getWechatAccountById(String accountId);
}
