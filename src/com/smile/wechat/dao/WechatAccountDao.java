package com.smile.wechat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smile.wechat.model.pojo.WechatAccount;

/**
 * wechat 账号 操作db  dao
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月27日
 */
public interface WechatAccountDao {

	/**
	 * 获取 wechat账号列表
	 * @author smile
	 * @date 2016年6月27日
	 * @return List<WechatAccount>  微信账号信息list
	 */
	public List<WechatAccount> getWechatAccountList();
	
	/**
	 * 根据微信accountid 获取微信账号信息
	 * @author smile
	 * @date 2016年6月27日
	 * @param accountid
	 * @return WechatAccount 微信账号信息
	 */
	public WechatAccount getWechatAccountById(@Param("id")String accountid);
}
