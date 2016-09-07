package com.smile.wechat.service.impl;

import java.util.List;

import com.smile.wechat.dao.MessageLogDao;
import com.smile.wechat.model.message.MessageLogData;
import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.request.impl.ClickEventRequestMessage;
import com.smile.wechat.model.message.request.impl.ImageRequestMessage;
import com.smile.wechat.model.message.request.impl.LocationRequestMessage;
import com.smile.wechat.model.message.request.impl.ScanEventRequestMessage;
import com.smile.wechat.model.message.request.impl.SubscribeEventRequestMessage;
import com.smile.wechat.model.message.request.impl.TextRequestMessage;
import com.smile.wechat.model.message.request.impl.VideoRequestMessage;
import com.smile.wechat.model.message.request.impl.ViewEventRequestMessage;
import com.smile.wechat.model.message.request.impl.VoiceRequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.model.message.response.impl.NewsResponseMessage;
import com.smile.wechat.model.message.response.impl.TextResponseMessage;
import com.smile.wechat.service.IMessageLogService;

/**
 * 消息日志服务实现类
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月27日
 */
public class MessageLogServiceImpl implements IMessageLogService/*,TimingLoading*/ {

	private MessageLogDao messageLogDao = null;
	
	public void setMessageLogDao(MessageLogDao dao){
		this.messageLogDao = dao;
	}


	/**
	 * 定时保存日志数据
	 */
	public void loadData() {
//		MessageLogHelper.saveMessage();
	}
	
	/**
	 * 保存消息
	 * @param list
	 */
	public void saveMessage(List<MessageLogData> logData){
		if (logData != null){
			int size = logData.size();
			for(int k = 0 ; k < size ; k++){
				MessageLogData md = logData.get(k);
				RequestMessage requestMessage = md.requestMessage;
				ResponseMessage responseMessage = md.responseMessage;
				if (requestMessage != null) {
					this.messageLogDao.saveMainMessage(requestMessage);
					if (requestMessage instanceof TextRequestMessage) {
						this.messageLogDao.saveTextMessage(requestMessage);
					} else if (requestMessage instanceof ImageRequestMessage) {
						this.messageLogDao.saveImageMessage(requestMessage);
					} else if (requestMessage instanceof VoiceRequestMessage) {
						this.messageLogDao.saveVoiceMessage(requestMessage);
					} else if (requestMessage instanceof VideoRequestMessage) {
						this.messageLogDao.saveVideoMessage(requestMessage);
					} else if (requestMessage instanceof LocationRequestMessage) {
						this.messageLogDao.saveLocationMessage(requestMessage);
					} else if (requestMessage instanceof SubscribeEventRequestMessage
							|| requestMessage instanceof ClickEventRequestMessage
							|| requestMessage instanceof ViewEventRequestMessage
							|| requestMessage instanceof ScanEventRequestMessage
						) {
						this.messageLogDao.saveEventMessage(requestMessage);
					}
				}
				
				if (responseMessage != null){
					this.messageLogDao.saveMainMessage(responseMessage);
					if (responseMessage instanceof TextResponseMessage){
						this.messageLogDao.saveTextMessage(responseMessage);
					}else if (responseMessage instanceof NewsResponseMessage){
						for(Object data:responseMessage.getData()){
							messageLogDao.saveNewsMessage(responseMessage, data);
						}
					}
				}
			}
		}
		
	}
	


}
