package com.smile.wechat.service;

import java.util.List;

import com.smile.wechat.model.pojo.WechatMenu;

/**
 * 微信公众号菜单 对象服务类
 * 
 * @project wechat-interface
 * @author smile
 * @createDate 2016年6月28日
 */
public interface IWechatMenuService {

	/**
	 * 根据服务号ID获取菜单列表
	 * @author smile
	 * @date 2016年6月28日
	 * @param accountid
	 * @return List<WechatMenu>
	 */
	public List<WechatMenu> getMenu(String accountid);

}
