package com.smile.core.util;

import java.security.*;

/**
 * MD5加密工具类
 * 
 * @project SmileWechat
 * @author smile
 * @createDate 2016年9月7日
 */
public class MD5Util {
	private static MessageDigest digest = null;

	public MD5Util() {
	}

	public synchronized static final String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. ");
				nsae.printStackTrace();
			}
		}
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}
}