package com.smile.wechat.service.impl.wechat;

import org.apache.log4j.Logger;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.model.message.response.impl.TextResponseMessage;
import com.smile.wechat.service.wechat.IRequestService;

/**
 * 请求消息对象分发接口的抽象类实现
 * 核心功能：提供统一请求消息处理
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月27日
 */
public abstract class AbstractRequestService implements IRequestService{
	
	private static Logger logger = Logger.getLogger(AbstractRequestService.class);

	/**
	 * 业务组件处理接口
	 * @param requestMessage
	 * @param content
	 * @return
	 * @throws Exception 
	 */
	public ResponseMessage handle(RequestMessage requestMessage,String content) throws Exception {
		ResponseMessage responseMessage=null;
		if(content!=null){
			if(content.startsWith("module")){//进入组件处理
				responseMessage=moduleProcess(requestMessage, content);
			}else{
				TextResponseMessage textResponseMessage = new TextResponseMessage(requestMessage);
				textResponseMessage.setContent(content);
				responseMessage=textResponseMessage;
			}
		}
		return responseMessage;
	}

	/**
	 * 业务组件处理
	 * @param requestMessage
	 * @param content
	 * @return
	 */
	private ResponseMessage moduleProcess(RequestMessage requestMessage,String content){
		String module_id = content.split(":")[1];
		logger.info("Module Key:"+content);
//		return ModuleController.moduleHandle(module_id, requestMessage);
		return null;
	}	
}
