package com.smile.wechat.dao;

import org.apache.ibatis.annotations.Param;

import com.smile.wechat.model.pojo.Qrcode;
import com.smile.wechat.model.pojo.User;

/**
 * 用户 操作dao
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月28日
 */
public interface WechatUserDao {

	/**
	 * 根据公众号id以及openid获取用户信息
	 * @author smile
	 * @date 2016年6月28日
	 * @param accountid
	 * @param open_id
	 * @return User
	 */
	public User queryUserByOpenID(@Param("schema")String schema, @Param("openid")String openid);

	/**
	 * 保存用户信息
	 * @author smile
	 * @date 2016年6月28日
	 * @param user void
	 */
	public void saveUser(@Param("schema")String schema,User user);

	/**
	 * 根据公众号id，更新数据库中用户信息
	 * @author smile
	 * @date 2016年6月28日
	 * @param accountId
	 * @param user void
	 */
	public void updateUser(@Param("schema")String schema, User user);

	/**
	 * 更新用户关注状态
	 * @author smile
	 * @date 2016年6月28日
	 * @param accountId
	 * @param openId
	 * @param issubscribe void
	 */
	public void updateSubscribeFlag(@Param("schema")String schema, @Param("openid")String openId, @Param("issubscribe")int issubscribe);

	/**
	 * 获取二维码
	 * @author smile
	 * @date 2016年6月28日
	 * @param accountid
	 * @param ticket
	 * @return Qrcode
	 */
	public Qrcode getQrcodeByTicket(@Param("schema")String schema, @Param("ticket")String ticket);

}
