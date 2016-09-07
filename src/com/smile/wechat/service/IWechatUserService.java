package com.smile.wechat.service;

import com.smile.wechat.model.pojo.Qrcode;
import com.smile.wechat.model.pojo.User;

/**
 * 用户信息 对象服务
 * 
 * @project wechat-interface
 * @author smile
 * @createDate 2016年6月28日
 */
public interface IWechatUserService {

	/**
	 * 根据 帐户accountid ，openid获取用户信息
	 * @author smile
	 * @date 2016年6月27日
	 * @param accountid
	 * @param open_id
	 * @return User
	 */
	public User queryUserByOpenID(String accountid, String open_id);

	/**
	 * 保存或更新 公众号下用户信息
	 * @author smile
	 * @date 2016年6月28日
	 * @param accountId
	 * @param user void
	 */
	public void saveOrUpdateUserInfo(String accountId, User user);

	/**
	 * 更新用户关注状态
	 * @author smile
	 * @date 2016年6月28日
	 * @param accountId
	 * @param openId
	 * @param issubscribe void
	 */
	public void updateSubscribeFlag(String accountId, String openId, int issubscribe);

	/**
	 * 根据ticket获取二维码信息
	 * @author smile
	 * @date 2016年6月28日
	 * @param accountid
	 * @param ticket
	 * @return Qrcode
	 */
	public Qrcode getQrcodeByTicket(String accountid, String ticket);

}
