package com.smile.wechat.service.impl.wechat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.smile.core.util.StringUtils;
import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.request.impl.VoiceRequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.model.message.response.impl.TextResponseMessage;

/**
 * 语音请求对象服务
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月28日
 */
public class VoiceRequestServiceImpl extends AbstractRequestService {

	private static Logger logger = Logger.getLogger(VoiceRequestServiceImpl.class);

	@Override
	public ResponseMessage handle(HttpServletRequest request,RequestMessage requestMessage)
			throws Exception {
		VoiceRequestMessage voiceRequestMessage = (VoiceRequestMessage) requestMessage;
		ResponseMessage responseMessage;
		if (StringUtils.isBlankOrEmpty(voiceRequestMessage.getRecognition())) {
			TextResponseMessage textResponseMessage = new TextResponseMessage(
					requestMessage);
			textResponseMessage.setContent("语音消息回复");
			responseMessage = textResponseMessage;
		} else {
			responseMessage = handle(requestMessage, voiceRequestMessage
					.getRecognition());
			
			logger.info("-----------------------语音解析结果："+ voiceRequestMessage
					.getRecognition());
			
			if (responseMessage != null) {
				if (responseMessage instanceof TextResponseMessage) {
					TextResponseMessage textResponseMessage = (TextResponseMessage) responseMessage;
					String voicemsg = "";
					voicemsg = voicemsg.replace("{voice}", voiceRequestMessage
							.getRecognition());
					logger.info("-----------------------回复的内容voicemsg："+ voicemsg);
					textResponseMessage.setContent(voicemsg);
				}
			}
		}
		return responseMessage;
	}

}
