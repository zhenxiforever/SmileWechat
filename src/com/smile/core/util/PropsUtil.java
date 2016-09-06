package com.smile.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * 配置文件工具类
 * 
 */
public class PropsUtil {
	
	private static final Logger log = Logger.getLogger(PropsUtil.class);

	private static Properties props = new Properties();
	static{
		InputStream in = PropsUtil.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			props.load(in);
		} catch (IOException e) {
			log.error("IOException:",e);
		}
	}
	
	public static String getProperty(String key){
		return props.getProperty(key);
	}
}
