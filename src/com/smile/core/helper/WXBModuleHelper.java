package com.smile.core.helper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.smile.wechat.model.pojo.WXBModule;

/**
 * 业务逻辑模块帮助类
 * 
 * @author lwgang
 * @createTime 2014-11-25
 * @history 1.修改时间,修改;修改内容：
 * 
 */
public class WXBModuleHelper {

	private static Map<String, WXBModule> module_map = new ConcurrentHashMap<String, WXBModule>();
	
	/**
	 * 缓存业务逻辑模块
	 * @param module
	 */
	public static void put(WXBModule module){
		module_map.put(module.getModule_id(), module);
	}
	
	/**
	 * 从缓存中获取业务逻辑模块
	 * @param module_id
	 * @return
	 */
	public static WXBModule getModule(String module_id){
		return module_map.get(module_id);
	}
}
