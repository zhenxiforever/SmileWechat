package com.smile.wechat.service.impl;

import com.smile.wechat.dao.WechatUserDao;
import com.smile.wechat.model.pojo.Qrcode;
import com.smile.wechat.model.pojo.User;
import com.smile.wechat.service.IWechatUserService;

/**
 * 关注用户 接口服务
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月28日
 */
public class WechatUserServiceImpl implements IWechatUserService {
	
	private WechatUserDao wechatUserDao;

	public void setWechatUserDao(WechatUserDao wechatUserDao) {
		this.wechatUserDao = wechatUserDao;
	}

	@Override
	public User queryUserByOpenID(String accountid, String open_id) {
		return wechatUserDao.queryUserByOpenID(accountid, open_id);
	}
	
	@Override
	public void saveOrUpdateUserInfo(String accountId,User user) {
		User dbUser=wechatUserDao.queryUserByOpenID(accountId, user.getOpenid());
		if(dbUser==null){
			user.setAccountid(accountId); 
			wechatUserDao.saveUser(accountId,user);
		}
		else{
			user.setId(dbUser.getId());
			user.setQrcodeid(dbUser.getQrcodeid());
			user.setAccountid(accountId); 
			wechatUserDao.updateUser(accountId, user);
		}
	}	
	
	@Override
	public void updateSubscribeFlag(String accountId, String openId,int issubscribe) {
		wechatUserDao.updateSubscribeFlag(accountId, openId, issubscribe);
	}
	
	@Override
	public Qrcode getQrcodeByTicket(String accountid, String ticket) {
		return wechatUserDao.getQrcodeByTicket(accountid, ticket);
	}

}
