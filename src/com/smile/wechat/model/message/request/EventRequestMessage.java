package com.smile.wechat.model.message.request;

import org.dom4j.Document;
import org.dom4j.Node;

/**
 * 事件类型请求消息抽象类，事件请求类型的消息必须实现该抽象类。
 * 
 * @author 
 * @createTime 
 * @history 1.修改时间,修改;修改内容：
 * 
 */
public  abstract class EventRequestMessage extends RequestMessage{

	protected String event;
	protected String eventKey;
	protected String ticket;

	/**
	 * 构造方法
	 * @param account_id
	 * @param xmlDoc
	 */
	public EventRequestMessage(String account_id,Document xmlDoc) {
		super(account_id,xmlDoc);
		event = xmlDoc.selectSingleNode("/xml/Event").getText();
		Node node=xmlDoc.selectSingleNode("/xml/EventKey");
		if(node!=null)
			eventKey=node.getText();
		node=xmlDoc.selectSingleNode("/xml/Ticket");
		if(node!=null)
			ticket=node.getText();
	}

	/**
	 * 获取属性：事件类型
	 * @return
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * 获取属性： 事件KEY值，与自定义菜单接口中KEY值对应
	 * @return
	 */
	public String getEventKey() {
		return eventKey;
	}
	
	/**
	 * 获取属性： ticket
	 * @return
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * 转换为xml字符串
	 * @param out
	 */
	@Override
	protected void toString(StringBuilder out) {
		out.append("	<Event>").append(this.event).append("</Event>\n");
		out.append("	<EventKey>").append(this.eventKey).append("</EventKey>\n");
		out.append("	<Ticket>").append(this.ticket).append("</Ticket>\n");
	}	
}
