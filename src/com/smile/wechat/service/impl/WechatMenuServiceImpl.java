package com.smile.wechat.service.impl;

import java.util.List;

import com.smile.wechat.dao.WechatMenuDao;
import com.smile.wechat.model.pojo.WechatMenu;
import com.smile.wechat.service.IWechatMenuService;

/**
 * 微信菜单服务接口实现
 * 
 * @project wechatCore
 * @author smile
 * @createDate 2016年6月28日
 */
public class WechatMenuServiceImpl implements IWechatMenuService{
	
	private WechatMenuDao wechatMenuDao;

	public void setWechatMenuDao(WechatMenuDao wechatMenuDao) {
		this.wechatMenuDao = wechatMenuDao;
	}

	@Override
	public List<WechatMenu> getMenu(String accountid) {
		String parent_id=null;
		List<WechatMenu> first_menu_list=wechatMenuDao.queryMenuListByParentId(accountid,parent_id);
		for(WechatMenu wechatMenu:first_menu_list){
			List<WechatMenu> second_menu_list=wechatMenuDao.queryMenuListByParentId(accountid,wechatMenu.getId());
			wechatMenu.setSub_button(second_menu_list);
		}
		return first_menu_list;
	}

}
