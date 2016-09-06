package com.smile.wechat.service.impl.wechat;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.smile.wechat.model.message.request.EventRequestMessage;
import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.model.message.response.impl.TextResponseMessage;
import com.smile.wechat.model.pojo.WechatUser;
import com.smile.wechat.service.IWechatCommService;

/**
 * 关注事件请求服务
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月28日
 */
public class SubscribeEventRequestServiceImpl extends AbstractRequestService {

	private static Logger logger = Logger.getLogger(SubscribeEventRequestServiceImpl.class);

	/*private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}*/
	private IWechatCommService wechatCommonService;

	@Override
	public ResponseMessage handle(HttpServletRequest request,RequestMessage requestMessage)
			throws Exception {
		EventRequestMessage eventRequestMessage = (EventRequestMessage) requestMessage;
		TextResponseMessage textResponseMessage = new TextResponseMessage(requestMessage);
		WechatUser user = null;
		try {
			logger.info("subscribe:accountid:"+ requestMessage.getAccountId()
							+ "-------openid:"+ requestMessage.getFromUserName());
			user = wechatCommonService.getUserInfo(requestMessage.getAccountId(),requestMessage.getFromUserName());
			if (user != null) {
//				user.setIssubscribe(1);
			} else {
				throw new Exception("微信用户信息获取失败");
			}
		} catch (Exception e) {
			logger.error("Exception:", e);
			user = new WechatUser();
			user.setOpenid(requestMessage.getFromUserName());
//			user.setIssubscribe(1);
		}
		
		try {
			if (eventRequestMessage.getTicket() != null) {
				logger.info("subscribe by ticket=" + eventRequestMessage.getTicket());
				//扫码关注
//				user.setSubscribetype(1);
//				Qrcode qrcode = userService.getQrcodeByTicket(requestMessage.getAccountId(), eventRequestMessage.getTicket());
			}else{
				//搜索关注
//				user.setSubscribetype(2);
			}
		} catch (Exception e) {
			logger.error("Exception:", e);
		}
//		user.setAccountid(requestMessage.getAccountId()); 
//		user.setSubscribetime(new Date());
//		userService.saveOrUpdateUserInfo(requestMessage.getAccountId(), user);
		textResponseMessage.setContent("关注成功了...");
		return textResponseMessage;
	}
}
