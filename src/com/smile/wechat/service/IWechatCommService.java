package com.smile.wechat.service;

import java.util.List;

import com.smile.exception.WechatException;
import com.smile.wechat.model.pojo.NewsItem;
import com.smile.wechat.model.pojo.ResultMsgFromWechat;
import com.smile.wechat.model.pojo.TradeResultFromWechat;
import com.smile.wechat.model.pojo.WechatMenu;
import com.smile.wechat.model.pojo.WechatUser;
import com.smile.wechat.model.pojo.WechatUserOpenid;

/**
 * 微信 服务 接口
 * 
 * @project wechat-interface
 * @author smile
 * @createDate 2016年6月19日
 */
public interface IWechatCommService {

	/**
	 * 获取微信用户信息
	 * @param accountId ：公众号accountId
	 * @param openid  ；微信用户openid
	 * @return ：微信用户信息
	 * @throws Exception
	 * @author smile
	 * @date 2016年6月19日
	 * @return
	 */
	public WechatUser getUserInfo(String accountId,String openid) throws WechatException;
	 
	/**
	 * 创建 微信 用户 分组信息
	 * @param accountId  ：公众号accountid
	 * @param group_name ：分组名称
	 * @throws WechatException
	 * @author smile
	 * @date 2016年6月19日
	 * @return
	 */
	public int createWXGroup(String accountId, String group_name) throws WechatException;
	 
	/**
	 * 更新微信分组信息
	 * @param accountId ：公众号accountiId
	 * @param group_id ：分组id
	 * @param group_name ：分组名称
	 * @throws WechatException
	 * @author smile
	 * @date 2016年6月19日
	 * @return
	 */
	public ResultMsgFromWechat updateWXGroup(String accountId, int group_id,String group_name) throws WechatException;
	 
	/**
	 * 更新微信分组用户信息
	 * @param accountId ：公众号accountid
	 * @param to_group_id ：新分组id
	 * @param open_id_list ：需分组的openid
	 * @throws WechatException
	 * @author smile
	 * @date 2016年6月19日
	 * @return
	 */
	public ResultMsgFromWechat updateWXGroupMember(String accountId, int to_group_id,String... open_id_list) throws WechatException;
	 
	/**
	 * 创建 微信二维码
	 * @param accountId  ：公众号accountid
	 * @param action_type ：类型
	 * @param expire_seconds ：有效时间
	 * @param scene_id
	 * @return
	 * @throws WechatException
	 * @author smile
	 * @date 2016年6月19日
	 * @return
	 */
	public String createQrcode(String accountId, int action_type,int expire_seconds,int scene_id) throws WechatException;
	 
	/**
	 * 发送用户文字信息
	 * @author smile  
	 * @date 2016年6月19日
	 * @param accountId ：公众号accountid
	 * @param openid  ：用户openid
	 * @param content ：发送内容
	 * @throws WechatException void
	 */
	public ResultMsgFromWechat sendCustomTextMsg(String accountId,String openid,String content) throws WechatException;
	 
	/**
	 * 发送用户图文消息
	 * @author smile
	 * @date 2016年6月19日
	 * @param accountId ：公众号accountid
	 * @param open_id  ：发送的用户openid
	 * @param newsItems ：图文消息列表
	 * @throws WechatException void
	 */
	public ResultMsgFromWechat sendCustomMultiGraphicMsg(String accountId,String open_id,List<NewsItem> newsItems) throws WechatException;
	 
	/**
	 * 创建 公众号菜单
	 * @author smile
	 * @date 2016年6月19日
	 * @param accountId  ：公众号accountid
	 * @param menu_list ：菜单list
	 * @throws WechatException void
	 */
	public ResultMsgFromWechat createWXMenu(String accountId,List<WechatMenu> menu_list) throws WechatException;
	 
	/**
	 * 获取用户openid  list
	 * @author smile
	 * @date 2016年6月19日
	 * @param accountId ：公众号accountid
	 * @param next_openid ：下一个开始openid
	 * @return UserOpenid
	 * @throws WechatException 
	 */
	public WechatUserOpenid getWXUserOpenId(String accountId,String next_openid) throws WechatException;
	 
	/**
	 * 发送模版推送信息 
	 * @author smile
	 * @date 2016年6月19日
	 * @param json
	 * @param accountId
	 * @return
	 * @throws WechatException TradeResultFromWechat
	 */
	public TradeResultFromWechat sendMsgTrade(String json,String accountId) throws WechatException;

}
