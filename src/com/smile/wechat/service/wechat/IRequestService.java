package com.smile.wechat.service.wechat;

import javax.servlet.http.HttpServletRequest;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;

/**
 * 请求消息 对象服务接口
 * 
 * @project wechat-interface
 * @author smile
 * @createDate 2016年6月19日
 */
public interface IRequestService {

	/**
	 * 请求消息处理
	 * @param requestMessage 请求消息对象
	 * @return 响应消息对象
	 * @throws Exception
	 */
	public ResponseMessage handle(HttpServletRequest request,RequestMessage requestMessage) throws Exception;

}
