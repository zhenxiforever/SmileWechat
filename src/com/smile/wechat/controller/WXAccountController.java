package com.company.weixin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.weixin.help.SystemParameterHelper;
import com.company.weixin.pojo.WeixinAccount;
import com.company.weixin.service.WeixinAccountService;
import com.company.weixin.util.AccreditUrlUtil;
import com.company.weixin.util.JsonUtil;
import com.company.weixin.util.Result;
import com.company.weixin.util.SysConfParam;

/**
 * 微信服务号控制器
 */
@Controller
public class WXAccountController {
	
	private final static Logger Log = Logger.getLogger(WXAccountController.class);

	@Autowired
	private WeixinAccountService weixinAccountService;

	/**
	 * 获取公众账号头像图片地址
	 * @param request
	 * @param response
	 */
	@RequestMapping("/WXAccount/getheadimage")
	public void getAccountHeadImage(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String accountid = request.getParameter("accountid");

			WeixinAccount wa = weixinAccountService.getWeixinAccountById(accountid);
			if (wa != null) {
				response.getWriter().print(
						SystemParameterHelper.getValue(SysConfParam.WX_WEB_SERVER_OAUTH_URL)
								+ wa.getPicurl());
				Log.info("/WXAccount/getheadimage="
						+ SystemParameterHelper.getValue(SysConfParam.WX_WEB_SERVER_OAUTH_URL)
						+ wa.getPicurl());
				response.getWriter().flush();
				response.getWriter().close();
			}
		} catch (Exception e) {
			Log.error("Exception:", e);
		}
	}
	
	/**
	 * 获取腾讯回调地址
	 * @param request
	 * @param response
	 */
	@RequestMapping("/WXAccount/getWXBaseAuthURL")
	public void getWXBaseAuthURL(HttpServletRequest request,
			HttpServletResponse response) {
		Result result = new Result();
		try {
			String accountId = request.getParameter("accountId");
			String paramsUrl = request.getParameter("paramsUrl");
			String params = request.getParameter("params");
			String url = AccreditUrlUtil.getWXBaseAuthURL(paramsUrl,accountId,params);
			result.getData().put("url", url);
			result.setCode(0);
			result.setMessage("getWXBaseAuthURL success");
		} catch (Exception e) {
			Log.error("Exception:", e);
			result.setCode(-1);
			result.setMessage("getWXBaseAuthURL error");
		}
		JsonUtil.output(response, result);
	}
}
