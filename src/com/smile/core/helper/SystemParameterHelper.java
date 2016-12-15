package com.smile.core.helper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.smile.core.pojo.model.SystemParameter;

/**
 * 系统参数帮助类
 * 
 * @project SmileWechat
 * @author smile
 * @createDate 2016年9月7日
 */
public class SystemParameterHelper {

	private static Map<String, String> param_map = new ConcurrentHashMap<String, String>();

	/**
	 * 从缓存中获取key对应的value
	 * 
	 * @param key
	 * @return
	 */
	public static final String getValue(String key) {
		return param_map.get(key);
	}

	/**
	 * 缓存参数
	 * 
	 * @param parameters
	 */
	public static void putAll(String key, String value) {
		if (!param_map.containsKey(key))
			param_map.put(key, value);
	}

	/**
	 * 缓存系统参数列表
	 * 
	 * @param parameters
	 */
	public static void putAll(List<SystemParameter> parameters) {

		for (SystemParameter parameter : parameters)
			param_map.put(parameter.getKey(), parameter.getValue());
	}

}
