package com.smile.wechat.model.pojo;


/**
 * 微信支付请求模型对象
 * 
 * @author 
 * @createTime
 * @history 1.修改时间,修改;修改内容：
 * 
 */
public class WeixinPayRequestDomain {
	
	private String appid;//公众账号ID	
	private String mch_id;//商户号	
	private String device_info;//设备号	
	private String nonce_str;//随机字符串	
	private String sign;//签名	
	private String body;//商品描述	
	private String detail;//商品详情	
	private String attach;//附加数据	
	private String out_trade_no;//商户订单号	
	private String fee_type;//货币类型	
	private String total_fee;//总金额	
	private String spbill_create_ip;//终端IP	
	private String time_start;//交易起始时间	
	private String time_expire;//交易结束时间	
	private String goods_tag;//商品标记	
	private String notify_url;//通知地址	
	private String trade_type;//交易类型	
	private String product_id;//商品ID	
	private String openid;//用户标识	
	private String key; //API秘钥
	
	/**
	 * 设置属性：公众账号ID 
	 * @param 
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	/**
	 * 获取属性：公众账号ID
	 * @return
	 */
	public String getAppid() {
		return appid;
	}
	
	/**
	 * 获取属性：商户号
	 * @return
	 */
	public String getMch_id() {
		return mch_id;
	}
	
	/**
	 * 设置属性：商户号 
	 * @param 
	 */
	public void setMch_id(String mchId) {
		mch_id = mchId;
	}
	
	/**
	 * 获取属性：设备号
	 * @return
	 */
	public String getDevice_info() {
		return device_info;
	}
	
	/**
	 * 设置属性：设备号
	 * @param 
	 */
	public void setDevice_info(String deviceInfo) {
		device_info = deviceInfo;
	}
	
	/**
	 * 获取属性：随机字符串
	 * @return
	 */
	public String getNonce_str() {
		return nonce_str;
	}
	
	/**
	 * 设置属性：随机字符串
	 * @param 
	 */
	public void setNonce_str(String nonceStr) {
		nonce_str = nonceStr;
	}
	
	/**
	 * 获取属性：签名
	 * @return
	 */
	public String getSign() {
		return sign;
	}
	
	/**
	 * 设置属性：签名
	 * @param 
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	/**
	 * 获取属性：商品描述
	 * @return
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * 设置属性：商品描述 
	 * @param 
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * 获取属性：商品详情
	 * @return
	 */
	public String getDetail() {
		return detail;
	}
	
	/**
	 * 设置属性：商品详情 
	 * @param 
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	/**
	 * 获取属性：附加数据
	 * @return
	 */
	public String getAttach() {
		return attach;
	}
	
	/**
	 * 设置属性：附加数据
	 * @param 
	 */
	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	/**
	 * 获取属性：商户订单号
	 * @return
	 */
	public String getOut_trade_no() {
		return out_trade_no;
	}
	
	/**
	 * 设置属性：商户订单号 
	 * @param 
	 */
	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}
	
	/**
	 * 获取属性：货币类型
	 * @return
	 */
	public String getFee_type() {
		return fee_type;
	}
	
	/**
	 * 设置属性：货币类型 
	 * @param 
	 */
	public void setFee_type(String feeType) {
		fee_type = feeType;
	}
	
	/**
	 * 获取属性：总金额
	 * @return
	 */
	public String getTotal_fee() {
		return total_fee;
	}
	
	/**
	 * 设置属性：总金额 
	 * @param 
	 */
	public void setTotal_fee(String totalFee) {
		total_fee = totalFee;
	}
	
	/**
	 * 获取属性：终端IP
	 * @return
	 */
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	
	/**
	 * 设置属性：终端IP 
	 * @param 
	 */
	public void setSpbill_create_ip(String spbillCreateIp) {
		spbill_create_ip = spbillCreateIp;
	}
	
	/**
	 * 获取属性：交易起始时间
	 * @return
	 */
	public String getTime_start() {
		return time_start;
	}
	
	/**
	 * 设置属性：交易起始时间 
	 * @param 
	 */
	public void setTime_start(String timeStart) {
		time_start = timeStart;
	}
	
	/**
	 * 获取属性：交易结束时间
	 * @return
	 */
	public String getTime_expire() {
		return time_expire;
	}
	
	/**
	 * 设置属性：交易结束时间 
	 * @param 
	 */
	public void setTime_expire(String timeExpire) {
		time_expire = timeExpire;
	}
	
	/**
	 * 获取属性：商品标记
	 * @return
	 */
	public String getGoods_tag() {
		return goods_tag;
	}
	
	/**
	 * 设置属性：商品标记 
	 * @param 
	 */
	public void setGoods_tag(String goodsTag) {
		goods_tag = goodsTag;
	}
	
	/**
	 * 获取属性：通知地址
	 * @return
	 */
	public String getNotify_url() {
		return notify_url;
	}
	
	/**
	 * 设置属性：通知地址 
	 * @param 
	 */
	public void setNotify_url(String notifyUrl) {
		notify_url = notifyUrl;
	}
	
	/**
	 * 获取属性：交易类型
	 * @return
	 */
	public String getTrade_type() {
		return trade_type;
	}
	
	/**
	 * 设置属性：交易类型
	 * @return
	 */
	public void setTrade_type(String tradeType) {
		trade_type = tradeType;
	}
	
	/**
	 * 获取属性：商品ID
	 * @return
	 */
	public String getProduct_id() {
		return product_id;
	}
	
	/**
	 * 设置属性：商品ID
	 * @return
	 */
	public void setProduct_id(String productId) {
		product_id = productId;
	}
	
	/**
	 * 获取属性：用户标识Openid
	 * @return
	 */
	public String getOpenid() {
		return openid;
	}
	
	/**
	 * 设置属性：用户标识Openid
	 * @return
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 设置属性：API秘钥
	 * @return
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取属性：API秘钥
	 * @return
	 */
	public String getKey() {
		return key;
	}

	
}
