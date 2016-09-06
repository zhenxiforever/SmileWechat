package com.smile.wechat.service.impl.wechat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.smile.wechat.model.message.request.EventRequestMessage;
import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.model.message.response.impl.TextResponseMessage;
import com.smile.wechat.model.pojo.WechatUser;

/**
 * 扫一扫 消息对象服务
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月27日
 */
public class ScanEventRequestServiceImpl extends AbstractRequestService{

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
			logger.info("ScanEvent:accountId:"+ requestMessage.getAccountId()
							+ "-------openid:"+ requestMessage.getFromUserName());
			user = wechatCommonService.getUserInfo(requestMessage.getAccountId(),
					requestMessage.getFromUserName());
			if (user == null) 
				throw new Exception("微信用户信息获取失败");
		} catch (Exception e) {
			logger.error("Exception:", e);
		}
		try {
			logger.info("-----------------ticket=" + eventRequestMessage.getTicket());
			//是否是系统生成的二维码
			String accountId = requestMessage.getAccountId();
			if (eventRequestMessage.getTicket() != null) {
				//获取二维码的基本信息
//				Qrcode qrcode = userService.getQrcodeByTicket(accountId, eventRequestMessage.getTicket());
			}
		} catch (Exception e) {
			logger.error("Exception:", e);
		}
		logger.info("扫一扫："+textResponseMessage.getContent());
		return textResponseMessage;
	}

}
