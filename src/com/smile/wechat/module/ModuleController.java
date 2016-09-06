package com.smile.wechat.module;

import org.apache.log4j.Logger;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.model.message.response.impl.TextResponseMessage;

/**
 * 业务逻辑模块处理控制器
 * 
 */
public class ModuleController {
	private static Logger logger = Logger.getLogger(ModuleController.class);
	
	/**
	 * 业务逻辑模块处理
	 * @param module_id
	 * @param requestMessage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ResponseMessage moduleHandle(String module_id,RequestMessage requestMessage){
		Module module =null;
	
		ResponseMessage responseMessage=null;
		try {
			logger.info("----------module id="+module_id);
			WXBModule module_info=WXBModuleHelper.getModule(module_id);
			if(module_info==null)
				throw new ClassNotFoundException();
		
			//采用反射创建具体的业务逻辑模块实例对象，调用同步请求消息处理
			Class c=Class.forName(module_info.getModule_path());
			module=(Module)c.newInstance();
		
			responseMessage=module.syncRequest(requestMessage);
			
			requestMessage.getCurrentUserInfo().setCurrentModule(module);
		} catch (Exception e) {
			logger.error("Exception:",e);
			TextResponseMessage textResponseMessage = new TextResponseMessage(requestMessage);			
			textResponseMessage.setContent(SystemParameterHelper.getValue(SysConfParam.WX_ERROR_INFO));
			responseMessage=textResponseMessage;			
		}
	
		return responseMessage;
	}	
}
