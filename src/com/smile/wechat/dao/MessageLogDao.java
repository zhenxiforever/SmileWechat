package com.smile.wechat.dao;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;

/**
 * 日志消息 处理dao
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月27日
 */
public interface MessageLogDao {

	public void saveMainMessage(RequestMessage requestMessage);

	public void saveTextMessage(RequestMessage requestMessage);

	public void saveVoiceMessage(RequestMessage requestMessage);

	public void saveVideoMessage(RequestMessage requestMessage);

	public void saveImageMessage(RequestMessage requestMessage);

	public void saveLocationMessage(RequestMessage requestMessage);

	public void saveEventMessage(RequestMessage requestMessage);

	public void saveMainMessage(ResponseMessage responseMessage);

	public void saveNewsMessage(ResponseMessage responseMessage, Object data);

	public void saveTextMessage(ResponseMessage responseMessage);

}
