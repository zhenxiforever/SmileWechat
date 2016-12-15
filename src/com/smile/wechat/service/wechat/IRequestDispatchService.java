package com.smile.wechat.service.wechat;

import javax.servlet.http.HttpServletRequest;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;

/**
 * 请求消息对象分发接口
 * 
 * @project wechat-interface
 * @author smile
 * @createDate 2016年6月19日
 */
public interface IRequestDispatchService {
	/**
	 * 请求消息分发处理
	 * @param requestMessage 请求消息对象
	 * @return 响应消息对象
	 * @throws Exception
	 */
	public ResponseMessage dispatch(HttpServletRequest request,RequestMessage requestMessage)throws Exception;

}
