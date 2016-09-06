package com.smile.wechat.service.impl.wechat;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.model.message.response.impl.TextResponseMessage;

/**
 * 图片消息对象服务
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月27日
 */
public class ImageRequestServiceImpl extends AbstractRequestService  {
	
	@Override
	public ResponseMessage handle(HttpServletRequest request,RequestMessage requestMessage) throws JSONException {
		TextResponseMessage responseMessage = new TextResponseMessage(requestMessage);
		responseMessage.setContent("图片默认回复");
		return responseMessage;
	}

}
