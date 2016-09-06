package com.smile.wechat.model.message.request.impl;

import org.dom4j.Document;
import org.dom4j.Node;

import com.smile.wechat.model.message.request.RequestMessage;

/**
 * 春雨医生消息
 * @author lhyan3
 * 2015年7月31日下午2:43:08
 */
public class SpringDoctorRequestMessage extends RequestMessage{
	

	private String format;//语音消息格式
	private String recognition;//语音识别后消息内容
	private String thumbMediaId;//视频缩略图id
	private String type;//消息类型
	
	
	public SpringDoctorRequestMessage(String accountId, Document xmlDoc) {
		super(accountId, xmlDoc);
		if("text".equalsIgnoreCase(this.msgType)){
			//文本消息
			this.type = "text";
		}else if("image".equalsIgnoreCase(this.msgType)){
			//图片消息
			this.type = "image";
		}else if("voice".equalsIgnoreCase(this.msgType)){
			//语音消息
			this.type = "voice";
			this.format = xmlDoc.selectSingleNode("/xml/Format").getText();
			Node node=xmlDoc.selectSingleNode("/xml/Recognition");
			if(node!=null)
				recognition=node.getText();
		}else if("video".equalsIgnoreCase(this.msgType)){
			//视频消息
			this.type = "video";
			this.thumbMediaId = xmlDoc.selectSingleNode("/xml/ThumbMediaId").getText();
		}else if("shortvideo".equalsIgnoreCase(this.msgType)){
			//短视频消息
			this.type = "shortvideo";
			this.thumbMediaId = xmlDoc.selectSingleNode("/xml/ThumbMediaId").getText();
		}
		this.msgType = "springDoctor";
	}

	@Override
	protected void toString(StringBuilder out) {
		if("text".equalsIgnoreCase(this.msgType)){
			//文本消息
			out.append("	<Content>").append(this.content).append("</Content>\n");
		}else if("image".equalsIgnoreCase(this.msgType)){
			//图片消息
			out.append("	<picUrl>").append(this.PicUrl).append("</picUrl>\n");
		}else if("voice".equalsIgnoreCase(this.msgType)){
			//语音消息
			out.append("	<MediaId>").append(this.MediaId).append("</MediaId>\n");
			out.append("	<Format>").append(this.format).append("</Format>\n");
		}else if("video".equalsIgnoreCase(this.msgType)){
			//视频消息
			out.append("	<MediaId>").append(this.MediaId).append("</MediaId>\n");
			out.append("	<ThumbMediaId>").append(this.thumbMediaId).append("</ThumbMediaId>\n");
		}else if("shortvideo".equalsIgnoreCase(this.msgType)){
			//短视频消息
			out.append("	<MediaId>").append(this.MediaId).append("</MediaId>\n");
			out.append("	<ThumbMediaId>").append(this.thumbMediaId).append("</ThumbMediaId>\n");
		}
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
