package com.smile.core.util;

import java.util.Collection;

/**
 * String 工具类
 * 
 * @project SmileWechat
 * @author smile
 * @createDate 2016年9月7日
 */
public class StringUtils {

	/**
	 * 是否为空，集合判empty，字符串不为空字符串，其他类型不为null
	 * @author smile
	 * @date 2016年9月7日
	 * @param obj
	 * @return boolean
	 */
	public static boolean isBlankOrEmpty(Object obj){
		if(obj == null){
			return true;
		}else if(obj instanceof Collection) {
			@SuppressWarnings("rawtypes")
			Collection collection = (Collection) obj;
			return collection.isEmpty();
		}else if(obj instanceof String){
			String str = (String) obj;
			return "".equals(str.trim());
		}
		return false;
	}
}
