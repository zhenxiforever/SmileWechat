package com.smile.wechat.service.impl.wechat;

import javax.servlet.http.HttpServletRequest;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.request.impl.TextRequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;

/**
 * 文本消息服务对象
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月28日
 */
public class TextRequestServiceImpl extends AbstractRequestService {
	
	@Override
	public ResponseMessage handle(HttpServletRequest request,RequestMessage requestMessage) throws Exception {
		TextRequestMessage textRequestMessage=(TextRequestMessage)requestMessage;
		return handle(textRequestMessage, "返回文本消息");
	}	
}
