package com.smile.wechat.util;

/**
  * 系统参数的Key值
  * 参见：wxb_sysconf表
  *
  * @project SmileWechat
  * @author smile
  * @createDate 2016年9月7日
  */
public class SysConfParam {
		
	/**
	 * Web Server Http URL 
	 */
	public static final String WX_WEB_SERVER_OAUTH_URL="WX_WEB_SERVER_OAUTH_URL";
	
	/**
	 * Web服务器上下文 
	 * 如 /wxqy
	 */
	public static final String WX_WEB_SERVER_CONTEXT="WX_WEB_SERVER_CONTEXT";
	
	/**
	 * dopost时间戳校验的秒数 
	 * 如 /9999999
	 */
	public static final String WX_TIMESS="WX_TIMESS";
	
	/**
	 * 关注提示话术
	 */
	public static final String WX_SUBSCRIBE="WX_SUBSCRIBE";
	
	/**
	 * 网络连接超时话术
	 */
	public static final String WX_CONNECTION_TIMEOUT="WX_CONNECTION_TIMEOUT";
	
	/**
	 * 错误提示话术
	 */
	public static final String WX_ERROR_INFO="WX_ERROR_INFO";
	
	/**
	 * 识别语音
	 */
	public static final String WX_VOICE_MSG="WX_VOICE_MSG";
	
	/**
	 * 不支持用户发送语音
	 */
	public static final String WX_VOICE_REQUEST_REPLY="WX_VOICE_REQUEST_REPLY";
	
	/**
	 * 不支持用户发送图片
	 */
	public static final String WX_IMAGE_REQUEST_REPLY="WX_IMAGE_REQUEST_REPLY";
	
	/**
	 * 不支持用户发送视频
	 */
	public static final String WX_VIDEO_REQUEST_REPLY="WX_VIDEO_REQUEST_REPLY";
	
	/**
	 * 显示用户发送的地址位置
	 */
	public static final String WX_LOCATION_MSG="WX_LOCATION_MSG";	
	
	/**
	 * 不支持用户发送链接
	 */
	public static final String WX_LINK_REQUEST_REPLY="WX_LINK_REQUEST_REPLY";

	/**
	 * 支付服务号
	 */
	public static final String APPID = "APPID";

	/**
	 * 支付服务号
	 */
	public static final String MACHID = "MACHID";

	/**
	 * 支付服务号
	 */
	public static final String KEY = "KEY";

	/**
	 * 支付服务号
	 */
	public static final String APPSECRET = "APPSECRET";

	/**
	 * 已关注扫一扫  提示是否解绑
	 */
	public static final String WX_SCAN = "WX_SCAN";

	/**
	 * 扫一扫 解绑成功
	 */
	public static final String WX_SCAN_SUCCESS = "WX_SCAN_SUCCESS";

	public static final String WX_SCAN_SHOP = "WX_SCAN_SHOP";


	public static final String ORDER_SUBMIT_MESSAGE = "ORDER_SUBMIT_MESSAGE";//订单提交短信

	public static final String ORDER_PAY_MESSAGE = "ORDER_PAY_MESSAGE";//订单支付短信

	public static final String ORDER_END_MESSAGE = "ORDER_END_MESSAGE";//订单结束短信
	
	public static final String ORDER_SELF_SUBMIT_MESSAGE = "ORDER_SELF_SUBMIT_MESSAGE";//自提订单
	/**
	 * 已经绑定的用户再去点击绑定
	 */
	public static final String WX_BINDED_ERROR_TIPS = "WX_BINDED_ERROR_TIPS";
	/**
	 *  绑定成功提示
	 */
	//获取用户信息失败提示
	public static final String WX_OBTAIN_DATA_FAIL_TIPS="WX_OBTAIN_DATA_FAIL_TIPS";
	//解除绑定成功
	public static final String WX_UNBIND_SUCCESS_TIPS="WX_UNBIND_SUCCESS_TIPS";
	//解除绑定失败
	public static final String WX_UNBIND_ERROR_TIPS="WX_UNBIND_ERROR_TIPS";
	//解除绑定失败
	public static final String WX_BINDED_CLICK_UNBIND_TIPS="WX_BINDED_CLICK_UNBIND_TIPS";
		
	public static final String WX_BINDED_SUCCESS_TIPS = "WX_BINDED_SUCCESS_TIPS";

	public static final String WX_AUTO_REPLY = "WX_AUTO_REPLY";//自动回复

	public static final String USER_BIND_MESSAGE = "USER_BIND_MESSAGE";//用户绑定短信



}
