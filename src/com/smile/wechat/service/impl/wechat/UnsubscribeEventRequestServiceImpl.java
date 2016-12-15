package com.smile.wechat.service.impl.wechat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.model.message.response.impl.TextResponseMessage;

/**
 * 取消关注事件请求服务
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月28日
 */
public class UnsubscribeEventRequestServiceImpl extends AbstractRequestService {

	private static Logger logger = Logger.getLogger(UnsubscribeEventRequestServiceImpl.class);

//	private UserService userService;
//
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}

	@Override
	public ResponseMessage handle(HttpServletRequest request,RequestMessage requestMessage) {
		TextResponseMessage textResponseMessage = new TextResponseMessage(requestMessage);
		try {
			String openId = requestMessage.getFromUserName();
			String accountId = requestMessage.getAccountId();
			logger.info("-------UnsubscribeEvent:accountId:"+accountId+";openId:"+openId);
//			userService.updateSubscribeFlag(accountId,openId,-1);//取消关注
//			MemcachedUtil.removeUserInfo(openId);//取消关注时，也清除memcached数据
			textResponseMessage.setContent("取消关注成功");
		} catch (Exception e) {
			logger.error("Exception:", e);
		}
		return textResponseMessage;
	}

}
