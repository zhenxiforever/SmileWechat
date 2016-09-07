package com.smile.wechat.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smile.core.util.PropsUtil;
import com.smile.exception.WechatException;
import com.smile.wechat.model.pojo.NewsItem;
import com.smile.wechat.model.pojo.TradeResultFromWechat;
import com.smile.wechat.model.pojo.WechatAccount;
import com.smile.wechat.model.pojo.WechatGroup;
import com.smile.wechat.model.pojo.WechatMenu;
import com.smile.wechat.model.pojo.WechatUser;
import com.smile.wechat.model.pojo.WechatUserOpenid;

/**
 * 调用 微信服务接口 工具类
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月19日
 */
public class WechatUtil {
	private WechatAccount wechatAccount;

	private Object lock = new Object();

	private String access_token;

	public WechatUtil(WechatAccount wechatAccount) {
		this.wechatAccount = wechatAccount;
	}

	public WechatAccount getWechatAccount() {
		return wechatAccount;
	}

	public void setWechatAccount(WechatAccount wechatAccount) {
		access_token = null;
		this.wechatAccount = wechatAccount;
	}

	/**
	 * 获取access token
	 * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token。
	 * 开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。
	 * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
	 * 
	 * @throws WechatException
	 * @throws JSONException
	 */
	public void getClient_credential() throws WechatException, JSONException {
		String url = type2uri(PropsParam.WEIXIN_TOKEN_SUFFIX);
		url = String.format(url, wechatAccount.getAppid(), wechatAccount.getAppsecret());
		SSLNetProvider provider = new SSLNetProvider();
		String result = provider.doGet(url).replace("null", "");
		JSONObject json = new JSONObject(result);
		if (JsonUtil.isRetSuccess(json)) {
			access_token = json.optString("access_token");
		} else {
			throw new WechatException("[errcode:" + json.optString("errcode")
					+ "]:" + json.optString("errmsg"));
		}
	}

	/**
	 * 判断access_token是否超时
	 * 40001:获取access_token时AppSecret错误，或者access_token无效。请比对AppSecret的正确性。
	 * 42001:access_token超时，请检查access_token的有效期。
	 * 
	 * @param json
	 * @return
	 */
	private boolean checkAccess_token(JSONObject json) {
		int ret_code = json.optInt("errcode", -1);
		if (ret_code == 42001 || ret_code == 40001)
			return false;
		return true;
	}
	
	/**
	 * 获取用户基本信息，返回null代表此用户没有关注该公众号，拉取不到其余信息。
	 * 
	 * @param open_id
	 * @return
	 * @throws WechatException
	 * @throws JSONException
	 */
	public WechatUser getWXUserInfo(String openId) throws WechatException,
			JSONException {
		JSONObject json = doGetJson(PropsParam.WEIXIN_USER_INFO_SUFFIX,
				access_token, openId);
		if (json.optInt("subscribe") == 1) {
			WechatUser user = new WechatUser();
			user.setIs_subscribe(json.optInt("subscribe"));
			user.setOpenid(json.optString("openid"));
			user.setNickname(json.optString("nickname"));
			user.setSex(json.optInt("sex"));
			user.setLanguage(json.optString("language"));
			user.setCity(json.optString("city"));
			user.setProvince(json.optString("province"));
			user.setCountry(json.optString("country"));
			user.setImage_url(json.optString("headimgurl"));
			user.setSubscribe_time(json.optLong("subscribe_time"));
			return user;
		}
		return null;
	}
	
	/**
	 * 获取关注者列表
	 * 关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
	 * 
	 * @param next_openid 第一个拉取的OPENID，不填默认从头开始拉取
	 * @return
	 * @throws WechatException
	 * @throws JSONException
	 */
	public WechatUserOpenid getWXUserOpenId(String next_openid) throws WechatException,
			JSONException {
		WechatUserOpenid userGet = new WechatUserOpenid();
		JSONObject json = doGetJson(PropsParam.WEIXIN_USER_GET_SUFFIX,
				access_token, next_openid);
		next_openid = json.optString("next_openid");
		userGet.setNext_openid(next_openid);
		json = json.optJSONObject("data");
		if (json != null) {
			JSONArray array = json.optJSONArray("openid");
			for (int i = 0; i < array.length(); i++) {
				userGet.addOpenid(array.getString(i));
			}
		}
		return userGet;
	}

	/**
	 * 发送客服文本消息
	 * 当用户主动发消息给公众号的时候（包括发送信息、点击自定义菜单、订阅事件、扫描二维码事件、支付成功事件、用户维权），
	 * 微信将会把消息数据推送给开发者，
	 * 开发者在一段时间内（目前修改为48小时）可以调用客服消息接口，
	 * 通过POST一个JSON数据包来发送消息给普通用户，在48小时内不限制发送次数。
	 * 
	 * @param open_id
	 * @param content
	 * @throws WechatException
	 * @throws JSONException
	 */
	public void sendCustomTextMsg(String openId, String content)
			throws WechatException, JSONException {
		JSONObject json = new JSONObject();
		json.put("touser", openId);
		json.put("msgtype", "text");
		content = content.replaceAll("&amp;", "&");
		json.put("text", new JSONObject().put("content", content));
		json = doPostJson(PropsParam.WEIXIN_MESSAGE_CUSTOM_SEND_SUFFIX, json);
		if (!JsonUtil.isRetSuccess(json)) {
			throw new WechatException(json.optString("errcode"),json.optString("errmsg"));
		}
	}
	
	/**
	 * 发送客服多图文消息
	 * 
	 * @param open_id
	 * @param newsItems
	 * @throws WechatException
	 * @throws JSONException
	 */
	public void sendCustomMultiGraphicMsg(String openId,
			List<NewsItem> newsItems) throws WechatException, JSONException {
		JSONObject json = new JSONObject();
		json.put("touser", openId);
		json.put("msgtype", "news");
		JSONObject jsonObject = new JSONObject();
		JsonUtil.obj2json("articles", jsonObject, newsItems);
		json.put("news", jsonObject);
		json = doPostJson(PropsParam.WEIXIN_MESSAGE_CUSTOM_SEND_SUFFIX, json);
		if (!JsonUtil.isRetSuccess(json)) {
			throw new WechatException(json.optString("errcode")
					,json.optString("errmsg"));
		}
	}
	
	/**
	 * 创建菜单
	 * 1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
	 * 2、一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。
	 * 3、创建自定义菜单后，由于微信客户端缓存，需要24小时微信客户端才会展现出来。测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
	 * 参数说明：
	 * button	 必须	 一级菜单数组，个数应为1~3个,最多4个汉字(不超过16个字节)
	 * sub_button	 否	 二级菜单数组，个数应为1~5个,最多7个汉字(不超过40个字节)
	 * type	 必须	 菜单的响应动作类型
	 * name	必须	 菜单标题，不超过16个字节，子菜单不超过40个字节
	 * key	 click等点击类型必须	 菜单KEY值，用于消息接口推送，不超过128字节
	 * url	 view类型必须	 网页链接，用户点击菜单可打开链接，不超过256字节
	 * media_id	 media_id类型和view_limited类型必须	 调用新增永久素材接口返回的合法media_id
	 * 
	 * @param menu_list
	 * @throws JSONException
	 * @throws WechatException
	 */
	public void createWXMenu(List<WechatMenu> menu_list) throws JSONException,
			WechatException {
		JSONObject json = new JSONObject();
		JSONArray button_array = new JSONArray();
		for (WechatMenu first_menu : menu_list) {
			JSONObject first_json = createWXMenuJson(first_menu);
			if (first_menu.getSub_button().size() != 0) {
				JSONArray array = new JSONArray();
				for (WechatMenu second_menu : first_menu.getSub_button()) {
					JSONObject second_json = createWXMenuJson(second_menu);
					array.put(second_json);
				}
				first_json.put("sub_button", array);
			}
			button_array.put(first_json);
		}
		json.put("button", button_array);
		json = doPostJson(PropsParam.WEIXIN_MENU_CREATE_SUFFIX, json);
		if (!JsonUtil.isRetSuccess(json)) {
			throw new WechatException(json.optString("errcode"),json.optString("errmsg"));
		}
	}
	
	/**
	 * 构造菜单json对象
	 * 
	 * @param menu
	 * @return
	 * @throws JSONException
	 */
	private JSONObject createWXMenuJson(WechatMenu menu) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("name", menu.getName());
		json.put("type", menu.getType());
		json.put("url", menu.getContent());
		json.put("key", menu.getId());
		return json;
	}
	
	/**
	 * 查询分组
	 * 
	 * @return
	 * @throws WechatException
	 * @throws JSONException
	 */
	public List<WechatGroup> getWXGroups() throws WechatException, JSONException {
		JSONObject json = doGetJson(PropsParam.WEIXIN_GROUPS_GET_SUFFIX,access_token);
		JSONArray array = json.optJSONArray("groups");
		List<WechatGroup> group_list = new ArrayList<WechatGroup>();
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.optJSONObject(i);
			WechatGroup group = new WechatGroup(object.optInt("id"), object.optString("name"),object.optInt("count"));
			group_list.add(group);
		}
		return group_list;
	}

	/**
	 * 创建分组
	 * 
	 * @param name
	 * @return
	 * @throws WechatException
	 * @throws JSONException
	 */
	public int createWXGroup(String name) throws WechatException, JSONException {
		JSONObject json = new JSONObject();
		json.put("group", new JSONObject().put("name", name));
		json = doPostJson(PropsParam.WEIXIN_GROUPS_CREATE_SUFFIX, json);
		if (JsonUtil.isRetSuccess(json)) {
			json = json.optJSONObject("group");
			return json.optInt("id");
		} else {
			throw new WechatException(json.optString("errcode")
					, json.optString("errmsg"));
		}
	}

	/**
	 * 修改分组名
	 * 
	 * @param id
	 * @param name
	 * @return
	 * @throws WechatException
	 * @throws JSONException
	 */
	public int updateWXGroup(int id, String name) throws WechatException,
			JSONException {
		JSONObject json = new JSONObject();
		json.put("group", new JSONObject().put("id", id).put("name", name));
		json = doPostJson(PropsParam.WEIXIN_GROUPS_UPDATE_SUFFIX, json);
		if (JsonUtil.isRetSuccess(json)) {
			return id;
		} else {
			throw new WechatException("[errcode:" + json.optString("errcode")
					+ "]:" + json.optString("errmsg"));
		}
	}

	/**
	 * 移动用户分组
	 * 
	 * @param open_id
	 * @param to_group_id
	 * @throws WechatException
	 * @throws JSONException
	 */
	public void updateWXGroupMember(String open_id, int to_group_id)
			throws WechatException, JSONException {
		JSONObject json = new JSONObject();
		json.put("openid", open_id);
		json.put("to_groupid", to_group_id);
		json = doPostJson(PropsParam.WEIXIN_GROUPS_MEMBERS_UPDATE_SUFFIX, json);
		if (!JsonUtil.isRetSuccess(json)) {
			throw new WechatException("[errcode:" + json.optString("errcode")
					+ "]:" + json.optString("errmsg"));
		}
	}

	/**
	 * 创建带参数二维码
	 * 目前有2种类型的二维码：
	 * 1、临时二维码，有过期时间，最长可设置7天（即604800秒）后过期，能够生成较多数量。主要用于帐号绑定等不要求二维码永久保存的业务场景
	 * 2、永久二维码，无过期时间，目前最多10万个。主要用于适用于帐号绑定、用户来源统计等场景。
	 * 获取带参数的二维码的过程包括两步，首先创建二维码ticket，然后凭借ticket到指定URL换取二维码。
	 * 每次创建二维码ticket需要提供一个开发者自行设定的参数（scene_id）
	 * 
	 * @param action_type 0:临时二维码  1:永久二维码
	 * @param expire_seconds 该二维码有效时间，以秒为单位。 最大不超过604800（即7天）
	 * @param scene_id 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @return
	 * @throws WechatException
	 * @throws JSONException
	 */
	public String createQrcode(int action_type, int expire_seconds, int scene_id)
			throws WechatException, JSONException {
		JSONObject json = new JSONObject();
		if (action_type == 0) {// 临时二维码
			json.put("expire_seconds", expire_seconds);
			json.put("action_name", "QR_SCENE");
		} else {// 永久二维码
			json.put("action_name", "QR_LIMIT_SCENE");
		}
		json.put("action_info", new JSONObject().put("scene", new JSONObject()
				.put("scene_id", scene_id)));
		json = doPostJson(PropsParam.WEIXIN_QRCODE_CREATE_SUFFIX, json);
		if (!JsonUtil.isRetSuccess(json)) {
			throw new WechatException("[errcode:" + json.optString("errcode")
					+ "]:" + json.optString("errmsg"));
		}
		return json.optString("ticket");
	}

	/**
	 * 发送模板信息
	 * @param jsonstr
	 * @throws WechatException
	 * @throws JSONException
	 */
	public TradeResultFromWechat sendTemplateMsg(String jsonstr) throws WechatException,
			JSONException {
		JSONObject json = new JSONObject(jsonstr);
		json = doPostJson(PropsParam.WEIXIN_SEND_TEMPLATE_MSG_SUFFIX, json);
//		if (!JsonUtil.isRetSuccess(json)) {
//			throw new WechatException(json.optString("errcode"),json.optString("errmsg"));
//		}
		TradeResultFromWechat result  = new TradeResultFromWechat();
		result.setResSuccess(JsonUtil.isRetSuccess(json));
		result.setErrCode(json.optString("errcode"));
		result.setErrMsg(json.optString("errmsg"));
		result.setMsgId(json.optString("msgid"));
		return result;
	}

	/**
	 * 上传多媒体
	 * 
	 * @param mediaType 图片(image),语音(voice),视频(video),普通文件(file)
	 * @param mediaPath 媒体文件路径
	 * @return
	 * @throws Exception
	 */
	public JSONObject uploadMedia(String mediaType, String mediaPath)
			throws Exception {
		synchronized (lock) {
			if (access_token == null)
				getClient_credential();			
			String url = PropsUtil.getProperty(PropsParam.WEIXIN_MEDIA_UPLOAD);
			url = String.format(url, access_token, mediaType);
			SSLNetProvider provider = new SSLNetProvider();
			JSONObject json = provider.upload(url, mediaType, mediaPath);
			if (!checkAccess_token(json)) {// access_token过期
				getClient_credential();
				url = PropsUtil.getProperty(PropsParam.WEIXIN_MEDIA_UPLOAD);
				url = String.format(url, access_token, mediaType);
				json = provider.upload(url, mediaType, mediaPath);
			}
			System.out.println("url:" + url);
			return json;
		}
	}
	
	/**
	 * 网络访问公共方法(get方式)
	 * @param suffix
	 * @param objs 第一个参数存放access_token
	 * @return
	 * @throws WechatException
	 * @throws JSONException
	 */
	private JSONObject doGetJson(String suffix, Object... objs)
			throws WechatException, JSONException {
		synchronized (lock) {
			if (access_token == null)
				getClient_credential();
			objs[0] = access_token;
			String url = type2uri(suffix);
			url = String.format(url, objs);
			SSLNetProvider provider = new SSLNetProvider();
			String result = provider.doGet(url);
			JSONObject json = new JSONObject(result);
			if (!checkAccess_token(json)) {//access_token过期
				getClient_credential();
				url = type2uri(suffix);
				objs[0] = access_token;// 更新access_token值
				url = String.format(url, objs);
				result = provider.doGet(url);
				json = new JSONObject(result);
			}
			return json;
		}
	}
	
	/**
	 * 网络访问公共方法(post方式)
	 * @param suffix
	 * @param jsonObject
	 * @param objs 第一个参数存放access_token
	 * @return
	 * @throws WechatException
	 * @throws JSONException
	 */
	private JSONObject doPostJson(String suffix, JSONObject jsonObject)
			throws WechatException, JSONException {
		synchronized (lock) {
			if (access_token == null)
				getClient_credential();
			String url = type2uri(suffix);
			url = String.format(url, access_token);
			String data = jsonObject.toString();
			SSLNetProvider provider = new SSLNetProvider();
			String result = provider.doPost(url, data).replace("null", "");
			JSONObject json = new JSONObject(result);
			if (!checkAccess_token(json)) {// access_token过期
				getClient_credential();
				url = type2uri(suffix);
				url = String.format(url, access_token);
				result = provider.doPost(url, data).replace("null", "");
				json = new JSONObject(result);
			}
			return json;
		}
	}
	
	/**
	 * 获取完整的请求微信服务的URL
	 * @param type
	 * @return
	 */
	private String type2uri(String type) {
		return PropsUtil.getProperty(PropsParam.WEIXIN_SERVER_PREFIX)
				+ PropsUtil.getProperty(type);
	}

}
