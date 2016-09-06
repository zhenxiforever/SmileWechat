package com.smile.wechat.module.imp;

import com.smile.wechat.model.message.request.RequestMessage;
import com.smile.wechat.model.message.response.ResponseMessage;
import com.smile.wechat.model.message.response.impl.TextResponseMessage;
import com.smile.wechat.module.Module;

public class DemoModule extends Module {

	private static final long serialVersionUID = 1L;

	/**
	 * 同步请求消息处理
	 */
	@Override
	public ResponseMessage syncRequest(RequestMessage requestMessage) {
		TextResponseMessage textResponseMessage = new TextResponseMessage(
				requestMessage);

		textResponseMessage.setEndFlag(true);
		return textResponseMessage;
	}

	@Override
	public String getOperate() {
		return "模块示例";
	}

}
