package com.smile.wechat.model.message;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.request.impl.ClickEventRequestMessage;
import com.smile.wechat.model.message.request.impl.ImageRequestMessage;
import com.smile.wechat.model.message.request.impl.LinkRequestMessage;
import com.smile.wechat.model.message.request.impl.LocationEventRequestMessage;
import com.smile.wechat.model.message.request.impl.LocationRequestMessage;
import com.smile.wechat.model.message.request.impl.ScanEventRequestMessage;
import com.smile.wechat.model.message.request.impl.SubscribeEventRequestMessage;
import com.smile.wechat.model.message.request.impl.TextRequestMessage;
import com.smile.wechat.model.message.request.impl.VideoRequestMessage;
import com.smile.wechat.model.message.request.impl.ViewEventRequestMessage;
import com.smile.wechat.model.message.request.impl.VoiceRequestMessage;

/**
 * 微信服务器发送的XML到对象的转化
 * 
 * @project wechat-model
 * @author smile
 * @createDate 2016年6月27日
 */
public class MessageConverter {
	
	
	/**
	 * 将xml转换为请求消息对象
	 * @param accountid
	 * @param xml
	 * @return
	 */
	public static RequestMessage convertMessage(String accountid,String xml){
		RequestMessage returnRequestMessage = null;
		
		try {
			Document doc = DocumentHelper.parseText(xml);
			String MsgType = ((Element)doc.selectNodes("/xml/MsgType").get(0)).getText();
			//判断消息类型
			if(MsgType.equalsIgnoreCase("event")){
				//事件
				String event = ((Element) doc.selectNodes("/xml/Event").get(0)).getText();
				
				//判断事件消息类型
				if(event.equalsIgnoreCase("click")){
					returnRequestMessage =  new ClickEventRequestMessage(accountid,doc);
				}else if(event.equalsIgnoreCase("view")){
					returnRequestMessage =  new ViewEventRequestMessage(accountid,doc);
				}else if(event.equalsIgnoreCase("subscribe") || event.equalsIgnoreCase("unsubscribe")){
					returnRequestMessage =  new SubscribeEventRequestMessage(accountid,doc);
				}else if(event.equalsIgnoreCase("scan")){
					returnRequestMessage = new ScanEventRequestMessage(accountid, doc);
				}else if(event.equalsIgnoreCase("location")){
					returnRequestMessage = new LocationEventRequestMessage(accountid, doc);
				}
			}else{
				//普通消息
				String openId = ((Element)doc.selectNodes("/xml/FromUserName").get(0)).getText();
			
					//正常接受普通消息
					if(MsgType.equalsIgnoreCase("text")){
						returnRequestMessage = new TextRequestMessage(accountid,doc);
					}else if(MsgType.equalsIgnoreCase("voice")){
						returnRequestMessage=new VoiceRequestMessage(accountid,doc);
					}else if(MsgType.equalsIgnoreCase("location")){
						returnRequestMessage = new LocationRequestMessage(accountid,doc);
					}else if(MsgType.equalsIgnoreCase("image")){
						returnRequestMessage = new ImageRequestMessage(accountid,doc);
					}else if(MsgType.equalsIgnoreCase("link")){
						returnRequestMessage = new LinkRequestMessage(accountid,doc);
					}else if(MsgType.equalsIgnoreCase("video")){
						returnRequestMessage = new VideoRequestMessage(accountid,doc);
					}
				
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return returnRequestMessage;
	}
}
