package com.smile.wechat.controller;

import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smile.core.helper.SystemParameterHelper;
import com.smile.core.pojo.Result;
import com.smile.core.util.PropsUtil;
import com.smile.core.util.UUIDUtil;
import com.smile.wechat.model.pojo.PayNotice;
import com.smile.wechat.model.pojo.WeixinPayRequestDomain;
import com.smile.wechat.service.WXPayService;
import com.smile.wechat.util.JsonUtil;
import com.smile.wechat.util.PropsParam;
import com.smile.wechat.util.SSLNetProvider;
import com.smile.wechat.util.SysConfParam;
import com.smile.wechat.util.WXPayUtil;

/**
 * 微信支付控制器
 * 
 * @author lwgang
 * @createTime 2015-03-12
 * @history 1.修改时间,修改;修改内容：
 * 
 */
@Controller
public class WXPayController {

	private static Logger logger = Logger.getLogger(WXPayController.class);
	
	@Autowired
	private WXPayService wxPayService;
	
	/**
	 * 调用微信统一支付接口
	 * @param request
	 * @return
	 */
	private Map<String, String> unifiedorder(HttpServletRequest request,WeixinPayRequestDomain domain) {
		Map<String, String> resultMap = null;
		try {
	
			//通知地址(微信付款成功，微信返回通知地址，就是下面的wxNotify)
			String notify_url = SystemParameterHelper.getValue(SysConfParam.WX_WEB_SERVER_OAUTH_URL)
							  + SystemParameterHelper.getValue(SysConfParam.WX_WEB_SERVER_CONTEXT)+"/WECHAT/WXPay/notify";			
			
			String spbill_create_ip = request.getRemoteAddr();
		

			SortedMap<String, String> requestParams = new TreeMap<String, String>();

			requestParams.put("appid", domain.getAppid());
			requestParams.put("mch_id", domain.getMch_id());
			requestParams.put("device_info", domain.getDevice_info()); 
			requestParams.put("nonce_str", domain.getNonce_str());
			requestParams.put("body", domain.getBody());
			requestParams.put("detail", domain.getDetail());
			requestParams.put("attach", domain.getAttach());
			requestParams.put("out_trade_no", domain.getOut_trade_no());
			requestParams.put("fee_type", domain.getFee_type());
			requestParams.put("total_fee", domain.getTotal_fee());
			requestParams.put("spbill_create_ip", spbill_create_ip);
			requestParams.put("time_start", domain.getTime_start()); 
			requestParams.put("time_expire", domain.getTime_expire()); 
			requestParams.put("goods_tag", domain.getGoods_tag());			
			requestParams.put("notify_url", notify_url);
			requestParams.put("trade_type", domain.getTrade_type());			
			requestParams.put("product_id", domain.getProduct_id()); 
			requestParams.put("openid", domain.getOpenid());
			
			
			WXPayUtil wxPayUtil = new WXPayUtil();
			//创建MD5签名
			String sign = wxPayUtil.createSign(requestParams, domain.getKey());

			logger.info("-------------wxpay unifiedorder sign:" + sign);
			requestParams.put("sign", sign);

			String requestData = wxPayUtil.parseMapToXML(requestParams);
			String url = PropsUtil.getProperty(PropsParam.WEIXIN_PAY_UNIFIEDORDER);
			//调用微信统一支付接口
			logger.info("-------------wxpay unifiedorder url:" + url);
			SSLNetProvider provider = new SSLNetProvider();	
			String resultData = provider.doPost(url, requestData);	

			//转换结果为map对象返回
			resultMap = wxPayUtil.parseXMLToMap(resultData);
		} catch (Exception e) {
			logger.error("Exception", e);
			
		}
		return resultMap;
	}

	/**
	 * 生成预订单
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/WXPay/preorder")
	public void preorder(HttpServletRequest request,
			HttpServletResponse response) {
		Result result = new Result();
		JSONObject json = new JSONObject();		
		String appid = SystemParameterHelper.getValue(SysConfParam.APPID);
		String mch_id = SystemParameterHelper.getValue(SysConfParam.MACHID);
		String key = SystemParameterHelper.getValue(SysConfParam.KEY);
		String nonce_str = UUIDUtil.getUUID();				
		String openid = request.getParameter("openid");	
		String trade_type = request.getParameter("trade_type");//取值如下：JSAPI，NATIVE，APP	
		String body = request.getParameter("body");// 商品描述
		String total_fee = request.getParameter("total_fee");//订单总金额，只能为整数 单位分
		String out_trade_no = request.getParameter("out_trade_no");//商户系统内部的订单号,32个字符内、可包含字母,
		String device_info = request.getParameter("device_info");//微信支付分配的终端设备号，商户自定义
		String detail = request.getParameter("detail");//商品名称明细列表
		String attach = request.getParameter("attach");//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
		String goods_tag = request.getParameter("goods_tag");
		String product_id = request.getParameter("product_id");
		//构造微信支付请求模型对象
		WeixinPayRequestDomain domain = new WeixinPayRequestDomain();
		domain.setAppid(appid);
		domain.setMch_id(mch_id);
		domain.setKey(key);
		domain.setNonce_str(nonce_str);
		domain.setOut_trade_no(out_trade_no);
		domain.setDevice_info(device_info);
		domain.setOpenid(openid);
		domain.setTrade_type(trade_type);
		domain.setBody(body);
		domain.setTotal_fee(total_fee);
		domain.setDetail(detail);
		domain.setAttach(attach);
		domain.setGoods_tag(goods_tag);
		domain.setProduct_id(product_id);
		domain.setFee_type("CNY");

		try {
			//调用微信统一支付接口
			Map<String, String> resultMap  = unifiedorder(request,domain);
			WXPayUtil wxPayUtil = new WXPayUtil();
	
			if (resultMap.containsKey("return_code")
					&& "FAIL".equals(resultMap.get("return_code"))) {
				logger.info("-------------wxpay unifiedorder return_code:"
						+ resultMap.get("return_code") + ";return_msg:"
						+ resultMap.get("return_msg"));
				result.setCode(-1);
				result.setMessage(resultMap.get("return_msg"));
			} else if (resultMap.containsKey("result_code")
					&& "FAIL".equals(resultMap.get("result_code"))) {
				logger.info("-------------wxpay unifiedorder result_code:"
						+ resultMap.get("result_code") + ";err_code:"
						+ resultMap.get("err_code") + ";err_code_des:"
						+ resultMap.get("err_code_des"));
				result.setCode(-2);
				result.setMessage(resultMap.get("err_code_des"));
			} else {
				//成功
				String timestamp = Long
						.toString(System.currentTimeMillis() / 1000);
				String nonce_str2 = UUID.randomUUID().toString();
				String packageParams = "prepay_id="
						+ (resultMap.containsKey("prepay_id") ? resultMap
								.get("prepay_id") : "");
				String signType = "MD5";
				String codeURL = resultMap.containsKey("code_url") ? resultMap
						.get("code_url") : "";
				logger.info("-------------wxpay jsp api pay code_url:"
						+ codeURL);

				SortedMap<String, String> signParams = new TreeMap<String, String>();

				signParams.put("appId", appid);
				signParams.put("timeStamp", timestamp);
				signParams.put("nonceStr", nonce_str2);
				signParams.put("package", packageParams);
				signParams.put("signType", signType);
				
				//创建MD5签名
				String paySign = wxPayUtil.createSign(signParams, key);

				logger.info("-------------wxpay jsp api pay sign:" + paySign);

				//构造JS API H5需要的参数的json格式数据
				json.put("appId", appid);
				json.put("timeStamp", timestamp);

				json.put("nonceStr", nonce_str2);
				json.put("packageParams", packageParams);
				json.put("signType", signType);
				json.put("paySign", paySign);
				json.put("codeURL", codeURL);

				result.setCode(0);
				result.getData().put("json", json.toString());
			}
		}

		catch (Exception e) {
			logger.error("Exception", e);
			result.setCode(-9999);
			result.setMessage(e.getMessage());
		}
		JsonUtil.output(response, result);
	}

	/**
	 * 微信付款成功，微信返回通知
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/WXPay/notify")
	public void wxNotify(HttpServletRequest request,
			HttpServletResponse response) {
		WXPayUtil wxPayUtil = new WXPayUtil();
		PayNotice notice = new PayNotice();
		String xml = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String line = in.readLine();
			while (line != null) {
				xml += line;
				line = in.readLine();
			}
			Map<String, String> resultParams = wxPayUtil.parseXMLToMap(xml);
			if (resultParams.containsKey("return_code")
					&& "FAIL".equals(resultParams.get("return_code"))) {
				logger.info("-------------wxpay notify result_code:"
						+ resultParams.get("result_code") + ";return_msg:"
						+ resultParams.get("return_msg"));
			}else{
				notice = (PayNotice) wxPayUtil.converMap(PayNotice.class, resultParams);
				//支付成功，改变订单状态
				String orderId = notice.getOut_trade_no();
				wxPayService.updateOrderPayStatus(orderId,1);
				Set<String> set = resultParams.keySet();
				for(String key :set){
					if(key.contains("coupon_batch_id_")){
						notice.setCoupon_batch_id_index(resultParams.get(key));
					}
					if(key.contains("coupon_id_")){
						notice.setCoupon_id_index(resultParams.get(key));
					}
					if(key.contains("coupon_fee_")){
						notice.setCoupon_fee_index(resultParams.get(key));
					}
				}
				notice.setPayLocation(notice.getAttach());
				wxPayService.savePayNotice(notice);
				logger.info("-------------wxpay notify result_code:"
						+ notice.getResult_code() + ";appid:"
						+ notice.getAppid()+";openid:"+notice.getOpenid()+";bank_type:"+notice.getBank_type());
			}
			SortedMap<String, String> returnMap = new TreeMap<String, String>();
			returnMap.put("return_code", resultParams.get("result_code"));
			returnMap.put("return_msg", "ok");
			String returnXml = wxPayUtil.parseMapToXML(returnMap);
			response.getWriter().write(returnXml);
		} catch (Exception e) {
			logger.error(e);
			return;
		}
		logger.info("--------------------wx pay notify data:" + xml);
		
		//TODO:这里可以对付款相关数据进行相应逻辑处理，如保存等，具体微信返回的数据为xml，参数参见《【微信支付】微信公众号支付接口文档V3.3.7.pdf》4.2节
		
	}

	/**
	 * 微信Native回调
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/WXPay/native_notify")
	public void wxNativeNotify(HttpServletRequest request,
			HttpServletResponse response) {
		
		WXPayUtil wxPayUtil = new WXPayUtil();
		String xml = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String line = in.readLine();
			while (line != null) {
				xml += line;
				line = in.readLine();
			}
		} catch (Exception e) {
			logger.error(e);
			return;
		}
		
		//xml为微信的请求参数值，具体参见《【微信支付】微信公众号支付接口文档V3.3.7.pdf》4.6节
		logger.info("--------------------wx pay native_notify data:" + xml);

		try {
			Map<String, String> requestMap = wxPayUtil.parseXMLToMap(xml);			
		
			String appid = requestMap.get("appid");
			String openid = requestMap.get("openid");
			String mch_id = requestMap.get("mch_id");
			//String is_subscribe = requestMap.get("is_subscribe");
			String nonce_str = requestMap.get("nonce_str");
			String product_id = requestMap.get("product_id");
			//String sign = requestMap.get("sign");
			
			String trade_type ="NATIVE";
			String key = SystemParameterHelper.getValue(SysConfParam.KEY);
			
			String body = "静态Native商品链接";// 商品描述
			
			//构造微信支付请求模型对象
			WeixinPayRequestDomain domain = new WeixinPayRequestDomain();
			domain.setAppid(appid);
			domain.setMch_id(mch_id);
			domain.setKey(key);
			domain.setNonce_str(nonce_str);
			
			domain.setOpenid(openid);
			domain.setTrade_type(trade_type);
			domain.setBody(body);
			domain.setProduct_id(product_id);
			
			//调用微信统一支付接口
			Map<String, String> resultMap  = unifiedorder(request,domain);
			
			SortedMap<String, String> responseMap = new TreeMap<String, String>();
			
			if (resultMap.containsKey("return_code")
					&& "FAIL".equals(resultMap.get("return_code"))) {
				logger.info("-------------wxpay unifiedorder return_code:"
						+ resultMap.get("return_code") + ";return_msg:"
						+ resultMap.get("return_msg"));
				
				responseMap.put("return_code", resultMap.get("return_code"));
				responseMap.put("return_msg", resultMap.get("return_msg"));
				
			} else if (resultMap.containsKey("result_code")
					&& "FAIL".equals(resultMap.get("result_code"))) {
				logger.info("-------------wxpay unifiedorder result_code:"
						+ resultMap.get("result_code") + ";err_code:"
						+ resultMap.get("err_code") + ";err_code_des:"
						+ resultMap.get("err_code_des"));
				
				responseMap.put("result_code", resultMap.get("result_code"));
				responseMap.put("err_code_des", resultMap.get("err_code_des"));
				
			} else {
				
				String prepay_id = resultMap.containsKey("prepay_id")? resultMap.get("prepay_id") : "";
				
				responseMap.put("return_code", "SUCCESS");
				responseMap.put("result_code", "SUCCESS");
				
				responseMap.put("appid", appid);
				responseMap.put("mch_id", mch_id);
				responseMap.put("nonce_str", nonce_str);
				responseMap.put("prepay_id", prepay_id);
				
				//创建MD5签名
				String paySign = wxPayUtil.createSign(responseMap, key);
				responseMap.put("sign", paySign);	
				
				String responseXml = wxPayUtil.parseMapToXML(responseMap);

				logger.info("responseXml:" + responseXml);
				
				response.getWriter().print(responseXml);
				response.getWriter().close();
			}
			
		} catch (Exception e) {
			logger.error("Exception:", e);
		}
		
	}
	
	
	@RequestMapping("/test")
	public void test() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException, ParseException {
		PayNotice message = new PayNotice();
		Map<String, String> map = new HashMap<String, String>();
		map.put("coupon_batch_id_index", "coupon");
		map.put("trade_type", "fewaf");
		map.put("time_end", "20141030133525");
		map.put("coupon_count", "1");
		Set<String> set = map.keySet();
		for(String key :set){
			if(key.contains("coupon_batch_id_")){
				System.out.println("fewfew");
			}
		}
		message = (PayNotice) WXPayUtil.converMap(PayNotice.class, map);
		wxPayService.savePayNotice(message);
		System.out.println(message.getCoupon_count());
	}


}
