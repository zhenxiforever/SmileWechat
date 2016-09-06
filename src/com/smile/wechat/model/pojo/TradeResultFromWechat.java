package com.smile.wechat.model.pojo;

public class TradeResultFromWechat extends ResultMsgFromWechat{

		private String msgId;
		
		public TradeResultFromWechat(){
			super();
		}
		
		public TradeResultFromWechat(String msgId){
			super();
			this.msgId = msgId;
		}

		public String getMsgId() {
			return msgId;
		}

		public void setMsgId(String msgId) {
			this.msgId = msgId;
		}
}
