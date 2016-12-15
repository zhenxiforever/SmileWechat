package com.smile.wechat.model.message.request;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Node;

import com.smile.wechat.model.message.Message;
import com.smile.wechat.model.pojo.WechatUser;


/**
 * 请求类型消息的抽象类，请求类型的消息必须实现该抽象类。
 * 
 * @author 
 * @createTime 
 * @history 1.修改时间,修改;修改内容：
 * 
 */
public abstract class RequestMessage extends Message {

	private static Log log = LogFactory.getLog(RequestMessage.class);

	private static final long USERINFO_UPDATE_TIME = 7 * 24 * 60 * 60 * 1000L;
	protected WechatUser currentUserInfo;
	protected String localAddr;
	
	/**
	 * 构造方法
	 * 
	 * @param accountId
	 * @param xmlDoc
	 */
	public RequestMessage(String accountId, Document xmlDoc) {
		this.accountId = accountId;
		this.toUserName = xmlDoc.selectSingleNode("/xml/ToUserName").getText();
		this.fromUserName = xmlDoc.selectSingleNode("/xml/FromUserName")
				.getText();
		Node nd = xmlDoc.selectSingleNode("/xml/MsgId");
		if (nd != null) {
			this.msgId = nd.getText();
		}
		this.msgType = xmlDoc.selectSingleNode("/xml/MsgType").getText();
		this.content = xmlDoc.selectSingleNode("/xml/Content") == null ? ""
				: xmlDoc.selectSingleNode("/xml/Content").getText();
		this.PicUrl = xmlDoc.selectSingleNode("/xml/PicUrl") == null ? ""
				: xmlDoc.selectSingleNode("/xml/PicUrl").getText();
		this.MediaId = xmlDoc.selectSingleNode("/xml/MediaId") == null ? ""
				: xmlDoc.selectSingleNode("/xml/MediaId").getText();

		try {
			
//			WeixinAccountDao weixinAccountDao = (WeixinAccountDao) DatabaseHelper
//					.getBean(WeixinAccountDao.class);
//			currentUserInfo=new WxUserInfo();
//			currentUserInfo.setOpenId(fromUserName);
//			WxbUser user = weixinAccountDao.queryWxUserByOpenID(accountId, fromUserName);
			Node node = xmlDoc.selectSingleNode("/xml/Event");
			if (node == null || !"subscribe".equals(node.getText())) {// 非关注事件
//				if (user == null) {
//					log.info("user is null");
//					user = new WxbUser();
//					user.setOpenid(fromUserName);
//					user.setAccountid(accountId);
//					weixinAccountDao.saveWxbUser(user);
//					UpdateWXBUserhreadPool.updateUserInfo(accountId,
//							fromUserName);
//					currentUserInfo.setUser(user);
//				} else if (System.currentTimeMillis()
//						- user.getUpdatetime().getTime() > USERINFO_UPDATE_TIME
//						|| user.getUpdatetime() == null
//						|| user.getIssubscribe() == 0) {// 超过7天，更新一次用户信息
//					log.info("-----------------time is null");
//					UpdateWXBUserhreadPool.updateUserInfo(accountId,
//							fromUserName);
//					currentUserInfo.setUser(user);
//				} else {
//					currentUserInfo.setUser(user);
//				}
			} else {
//				if (user == null) {
//					user = new WxbUser();
//					user.setOpenid(fromUserName);
//					user.setAccountid(accountId);
//					weixinAccountDao.saveWxbUser(user);
//				}
//				currentUserInfo.setUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**
	 * 取得当前用户信息对象
	 * 
	 * @return
	 */
	public WechatUser getCurrentUserInfo() {
		return this.currentUserInfo;
	}

	/**
	 * 获取属性：请求的地址(IP)
	 * 
	 * @return
	 */
	public String getLocalAddr() {
		return localAddr;
	}

	/**
	 * 设置属性：请求的地址(IP)
	 * 
	 * @param localAddr
	 */
	public void setLocalAddr(String localAddr) {
		this.localAddr = localAddr;
	}

	/**
	 * 获取消息渠道
	 */
	@Override
	protected int getUserSend() {
		return 1;
	}

	/**
	 * 转换为xml字符串
	 */
	@Override
	public final String toString() {
		StringBuilder out = new StringBuilder();
		out.append("\n<xml>\n");
		out.append("	<ToUserName>").append(this.toUserName).append("</UserName>\n");
		out.append("	<FromUserName>").append(this.fromUserName).append("</FromUserName>\n");
		out.append("	<MsgId>").append(this.msgId).append("</MsgId>\n");
		out.append("	<MsgType>").append(this.msgType).append("</MsgType>\n");
		out.append("	<PicUrl>").append(this.PicUrl).append("</PicUrl>\n");
		out.append("	<CreateTime>").append(this.getCreateTime().getTime()).append("</CreateTime>\n");
		out.append("	<MediaId>").append(this.MediaId).append("</MediaId>\n");
		this.toString(out);
		out.append("</xml>\n");
		return out.toString();
	}

	/**
	 * 转换为xml字符串
	 * 
	 * @param out
	 */
	protected abstract void toString(StringBuilder out);

}
