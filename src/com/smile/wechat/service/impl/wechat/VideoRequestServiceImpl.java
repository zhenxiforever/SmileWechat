package com.smile.wechat.service.impl.wechat;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.model.message.response.impl.TextResponseMessage;

/**
 * 视屏消息事件请求服务
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月28日
 */
public class VideoRequestServiceImpl extends AbstractRequestService {

	@Override
	public ResponseMessage handle(HttpServletRequest request,RequestMessage requestMessage)
			throws JSONException {
		TextResponseMessage responseMessage = new TextResponseMessage(
				requestMessage);
		responseMessage.setContent("视频消息回复");
		return responseMessage;
	}

}
