package com.smile.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * HttpNet工具类：模拟http请求
 * 
 * @author 
 * @createTime 
 * @history  1.修改时间,修改;修改内容：
 * 
 */
public class HttpNetProvider {

	private static Logger logger = Logger.getLogger(HttpNetProvider.class);

	/**
	 * doGet请求
	 * @param url
	 * @return
	 */
	public static byte[] doGetBytes(String url) {
		InputStream is = null;
		ByteArrayOutputStream os = null;
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) httpUrl
					.openConnection();
			conn.setDoInput(true);
			is = conn.getInputStream();
			os = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int length = -1;
			while ((length = is.read(b, 0, 1024)) != -1) {
				os.write(b, 0, length);
			}
			conn.disconnect();
			return os.toByteArray();
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		} finally {
			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
					logger.error("IOException", e);
				}
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					logger.error("IOException", e);
				}
		}
		return null;
	}

	/**
	 * doPost请求
	 * @param url
	 * @param data
	 * @return
	 */
	public static byte[] doPostBytes(String url, byte[] data) {
		InputStream is = null;
		OutputStream os = null;
		ByteArrayOutputStream bos = null;
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) httpUrl
					.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			os = conn.getOutputStream();
			os.write(data);
			os.flush();
			is = conn.getInputStream();
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int length = -1;
			while ((length = is.read(b, 0, 1024)) != -1) {
				bos.write(b, 0, length);
			}
			conn.disconnect();
			return bos.toByteArray();
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("IOException", e);
				}
			}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					logger.error("IOException", e);
				}
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					logger.error("IOException", e);
				}
		}
		return null;
	}

	/**
	 * doPost请求(重载)
	 * @param url
	 * @param data
	 * @param requestContentType 请求类型(如application/xml application/json)
	 * @return
	 */
	public static byte[] doPostBytes(String url, byte[] data,
			String requestContentType) {
		InputStream is = null;
		OutputStream os = null;
		ByteArrayOutputStream bos = null;
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) httpUrl
					.openConnection();
			conn.setRequestProperty("content-type", requestContentType);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			os = conn.getOutputStream();
			os.write(data);
			os.flush();
			is = conn.getInputStream();
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int length = -1;
			while ((length = is.read(b, 0, 1024)) != -1) {
				bos.write(b, 0, length);
			}
			conn.disconnect();
			return bos.toByteArray();
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("IOException", e);
				}
			}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					logger.error("IOException", e);
				}
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					logger.error("IOException", e);
				}
		}
		return null;
	}

	/**
	 * doPost请求(重载)
	 * @param url
	 * @param data
	 * @return
	 */
	public static byte[] doPostBytes(String url, String data) {
		try {
			return doPostBytes(url, data.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException", e);
		}
		return null;
	}

	/**
	 * doPost请求(重载)
	 * @param url
	 * @param data
	 * @param requestContentType 请求类型(如application/xml application/json)
	 * @return
	 */
	public static byte[] doPostBytes(String url, String data,
			String requestContentType) {
		try {
			return doPostBytes(url, data.getBytes("utf-8"), requestContentType);
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException", e);
		}
		return null;
	}

	public static void main(String[] args) throws Exception {		
		String url = "http://211.156.179.94:7080/wxim/WECHAT/singleChat/getHistoryMsg";
		String params = "{\"sendId\":\"zqsheng\",\"receiveId\":\"lping\"}\"";
		byte[] data=doPostBytes(url,params);
		String result = new String(data);
		System.out.println(result);
		
//		String url = "http://211.156.179.94:7080/wxim/WECHAT/singleChat/getAllNoReadMsg";
//		String params = "{\"receiveId\":\"lping\"}\"";
//		byte[] data=doPostBytes(url,params);
//		String result = new String(data);
//		System.out.println(result);
		
	}
}
