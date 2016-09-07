package com.smile.wechat.service;

import java.util.List;

import com.smile.wechat.model.message.MessageLogData;

/**
 * 消息日志服务
 * 
 * @project wechat-interface
 * @author smile
 * @createDate 2016年6月19日
 */
public interface IMessageLogService {
	
	/**
	 * 保存消息
	 * @param list
	 */
	public void saveMessage(List<MessageLogData> logData);
	
}
