package com.smile.wechat.model.message;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;

/**
 * 消息日志 记录对象
 * 
 * @project wechat-model
 * @author smile
 * @createDate 2016年6月19日
 */
public class MessageLogData {
	public final RequestMessage requestMessage;
	public final ResponseMessage responseMessage;
	
	public MessageLogData(RequestMessage requestMessage,ResponseMessage responseMessage){
		this.requestMessage = requestMessage;
		this.responseMessage = responseMessage;
	}
}
