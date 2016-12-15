package com.smile.wechat.util;

/**
 * 微信 配置文件 常量
 * 
 * @project SmileWechat
 * @author smile
 * @createDate 2016年9月7日
 */
public class PropsParam {

		// 微信服务器前缀
		public static final String WEIXIN_SERVER_PREFIX = "weixin_server_prefix";
		
		// 获取access token
		public static final String WEIXIN_TOKEN_SUFFIX = "weixin_token_suffix";
		
		// 获取用户基本信息
		public static final String WEIXIN_USER_INFO_SUFFIX = "weixin_user_info_suffix";
			
		// 获取帐号的关注者列表
		public static final String WEIXIN_USER_GET_SUFFIX = "weixin_user_get_suffix";
		
		// 客服接口-发消息
		public static final String WEIXIN_MESSAGE_CUSTOM_SEND_SUFFIX = "weixin_message_custom_send_suffix";
			
		// 自定义菜单创建接口
		public static final String WEIXIN_MENU_CREATE_SUFFIX = "weixin_menu_create_suffix";
		
		// 查询分组
		public static final String WEIXIN_GROUPS_GET_SUFFIX = "weixin_groups_get_suffix";
		
		// 创建分组
		public static final String WEIXIN_GROUPS_CREATE_SUFFIX = "weixin_groups_create_suffix";
		
		// 修改分组名
		public static final String WEIXIN_GROUPS_UPDATE_SUFFIX = "weixin_groups_update_suffix";
		
		// 移动用户分组
		public static final String WEIXIN_GROUPS_MEMBERS_UPDATE_SUFFIX = "weixin_groups_members_update_suffix";
		
		// 创建二维码ticket
		public static final String WEIXIN_QRCODE_CREATE_SUFFIX = "weixin_qrcode_create_suffix";
		
		// 通过ticket换取二维码
		public static final String WEIXIN_QRCODE_SHOW_URL = "weixin_qrcode_show_url";
		
		// 发送模板类信息
		public static final String WEIXIN_SEND_TEMPLATE_MSG_SUFFIX = "weixin_send_template_msg_suffix";
		
		// 上传媒体文件
		public static final String WEIXIN_MEDIA_UPLOAD = "weixin_media_upload";

		// 网页授权获取用户基本信息
		public static final String WEIXIN_CONNECT_OAUTH2_AUTHORIZE = "weixin_connect_oauth2_authorize";
		
		
		//网页授权获取用户基本信息
		public static final String WEIXIN_CONNECT_OAUTH2_AUTHORIZE_BASE="weixin_connect_oauth2_authorize_base";
		
		public static final String WEIXIN_CONNECT_OAUTH2_AUTHORIZE_INFO="weixin_connect_oauth2_authorize_info";
		
		//网页授权获取openid
		public static final String WEIXIN_CONNECT_OAUTH2_ACCESS_TOKEN="weixin_connect_oauth2_access_token";
		
		//网页授权获取userinfo
		public static final String WEIXIN_CONNECT_OAUTH2_USERINFO="weixin_connect_oauth2_userinfo";	
		
		//Wechat Comm URL
		public static final String WECHAT_COMM_CENTER_HOST="wechat_comm_host";	

		//微信支付-统一支付
		public static final String WEIXIN_PAY_UNIFIEDORDER="weixin_pay_unifiedorder";
}
