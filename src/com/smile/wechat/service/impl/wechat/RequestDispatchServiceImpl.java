package com.smile.wechat.service.impl.wechat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.service.wechat.IRequestDispatchService;
import com.smile.wechat.service.wechat.IRequestService;

/**
 * 请求消息对象分发接口实现
 * 
 */
public class RequestDispatchServiceImpl implements IRequestDispatchService {

	/**
	 * 普通消息类型
	 * 	1 文本消息
		2 图片消息
		3 语音消息
		4 视频消息
		5 小视频消息
		6 地理位置消息
		7 链接消息
		8 事件消息
	 */
	private Map<String, IRequestService> componentMap;
	
	/**
	 * 设置请求消息对象处理服务的组件Map
	 * 具体参见：applicationContext.xml中<bean id="requestDispatchService">一节
	 * @param componentMap
	 */
	public void setComponentMap(Map<String, IRequestService> componentMap) {
		this.componentMap = componentMap;
	}
	
	@Override
	public ResponseMessage dispatch(HttpServletRequest request,RequestMessage requestMessage) throws Exception {
		
		IRequestService requestService = componentMap.get(requestMessage.getMsgType());
		if(requestService!=null){
			ResponseMessage responseMessage=requestService.handle(request,requestMessage);
			if(responseMessage != null && responseMessage.isEndFlag()) {
				requestMessage.getCurrentUserInfo();//.reset();
			}
			return responseMessage;
		}
		return null;
	}

}
