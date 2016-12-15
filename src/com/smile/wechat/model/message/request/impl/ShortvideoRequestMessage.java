package com.smile.wechat.model.message.request.impl;

import org.dom4j.Document;

import com.smile.wechat.model.message.request.RequestMessage;

public class ShortvideoRequestMessage extends RequestMessage {

	private String mediaId;
	private String thumbMediaId;
	
	
	public ShortvideoRequestMessage(String accountid,Document xmlDoc){
		super(accountid,xmlDoc);
		this.mediaId = xmlDoc.selectSingleNode("/xml/MediaId").getText();
		this.thumbMediaId = xmlDoc.selectSingleNode("/xml/ThumbMediaId").getText();
	}

	/**
	 * 获取属性：视频媒体文件id
	 * @return
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * 获取属性： 视频消息缩略图的媒体id 
	 * @return
	 */
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	
	/**
	 * 转换为xml字符串
	 * @param out
	 */
	@Override
	protected void toString(StringBuilder out) {
		out.append("	<MediaId>").append(this.mediaId).append("</MediaId>\n");
		out.append("	<ThumbMediaId>").append(this.thumbMediaId).append("</ThumbMediaId>\n");
	}
}
