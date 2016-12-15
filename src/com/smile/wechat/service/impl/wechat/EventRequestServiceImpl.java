package com.smile.wechat.service.impl.wechat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.smile.wechat.model.message.request.EventRequestMessage;
import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.service.wechat.IRequestService;

/**
 * 事件 请求服务
 * 事件类型 msgtype 【event】
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月27日
 */
public class EventRequestServiceImpl implements IRequestService {

	/**
	 * 事件类型 Event【】
	 * 	1 关注/取消关注事件
		2 扫描带参数二维码事件
		3 上报地理位置事件
		4 自定义菜单事件
		5 点击菜单拉取消息时的事件推送
		6 点击菜单跳转链接时的事件推送
	 */
	private Map<String, IRequestService> componentMap;

	public void setComponentMap(Map<String, IRequestService> componentMap) {
		this.componentMap = componentMap;
	}

	@Override
	public ResponseMessage handle(HttpServletRequest request,RequestMessage requestMessage)
			throws Exception {
		EventRequestMessage eventRequestMessage = (EventRequestMessage) requestMessage;
		IRequestService requestService = componentMap.get(eventRequestMessage.getEvent().toLowerCase());
		if (requestService != null) {
			ResponseMessage responseMessage = requestService.handle(request,eventRequestMessage);
			return responseMessage;
		}
		return null;
	}
}
