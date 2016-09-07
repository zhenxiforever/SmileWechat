package com.smile.wechat.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.smile.exception.WechatException;
import com.sun.net.ssl.internal.www.protocol.https.Handler;
import com.sun.net.ssl.internal.www.protocol.https.HttpsURLConnectionOldImpl;

/**
 * SSLNet 工具类
 * 
 * @project common-util
 * @author smile
 * @createDate 2016年6月20日
 */
public class SSLNetProvider {
	
	private static Logger logger = Logger.getLogger(SSLNetProvider.class);

	private class TrustAnyTrustManager implements X509TrustManager {
	 
	   public void checkClientTrusted(X509Certificate[] chain, String authType)
	      throws CertificateException {
	   }
	 
	   public void checkServerTrusted(X509Certificate[] chain, String authType)
	      throws CertificateException {
	   }
	 
	   public X509Certificate[] getAcceptedIssuers() {
	     return new X509Certificate[] {};
	   }
	}

	/**
	 * doGet方式请求
	 * @param url
	 * @return
	 * @throws WechatException
	 */
	@SuppressWarnings("deprecation")
	public String doGet(String url)throws WechatException{
		logger.info("doGet url="+url);
	    InputStream in = null;
	    HttpsURLConnectionOldImpl conn=null;
	    try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new SecureRandom());
			URL console = new URL(null,url,new Handler());
			conn = (HttpsURLConnectionOldImpl) console.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			in = conn.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String ret = null;
			String str_return = "";
			while ((ret=br.readLine())!=null) {
				str_return = str_return+ ret+"\n";
			}
			logger.info("result="+str_return);
			return str_return;
	    } catch (ConnectException e) {
	    	logger.error("ConnectException",e);
	    	throw new WechatException("链接微信服务器失败");
	    } catch (IOException e) {
	    	logger.error("IOException",e);
	    	throw new WechatException("从微信服务器读取数据失败");
	    } catch (Exception e) {
	    	logger.error("Exception",e);
	    	throw new WechatException("从微信服务器读取数据失败");
	    } finally {
	    	if(in!=null){
	    		try {
	    	    	in.close();
	    		} catch (Exception e) {
	    		     logger.error("Exception",e);
	    		}
	    	}
	    	if(conn!=null){
	    		try {
					conn.disconnect();
				} catch (Exception e) {
					logger.error("Exception",e);
				}
	    	}
	    }
	}
	
	/**
	 * doPost方式请求
	 * @param url
	 * @param data
	 * @return
	 * @throws WechatException
	 */
	@SuppressWarnings("deprecation")
	public String doPost(String url,String data) throws WechatException{
		logger.info("doPost url="+url);
    	logger.info("data="+data);
	    InputStream is = null;
	    OutputStream out=null;
	    HttpsURLConnectionOldImpl conn=null;
	    try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new SecureRandom());
			URL console = new URL(null,url,new Handler());
			conn = (HttpsURLConnectionOldImpl) console.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			//conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            out=conn.getOutputStream();
			DataOutputStream httpOut = new DataOutputStream(out);
	        httpOut.write(data.getBytes("UTF-8"));
	        httpOut.flush();
			is = conn.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String ret = null;
			String str_return = "";
			while ((ret=br.readLine())!=null) {
				str_return = str_return+ ret;
			}
			logger.info("result="+str_return);
			return str_return;
	    } catch (ConnectException e) {
	    	logger.error("ConnectException",e);
	    	throw new WechatException("链接微信服务器失败");
	    } catch (IOException e) {
	    	logger.error("IOException",e);
	    	throw new WechatException("从微信服务器读取数据失败");
	    } catch (Exception e) {
	    	logger.error("Exception",e);
	    	throw new WechatException("从微信服务器读取数据失败");
	    }finally {
	    	if(out!=null){
	    		try {
	    			out.close();
	    		} catch (Exception e) {
	    			logger.error("Exception",e);
	    		}
	    	}
	    	if(is!=null){
	    		try {
	    	    	is.close();
	    		} catch (Exception e) {
	    			logger.error("Exception",e);
	    		}
	    	}
	    	if(conn!=null){
	    		try {
					conn.disconnect();
				} catch (Exception e) {
					logger.error("Exception",e);
				}
	    	}
	    }
	}
	
	/**
     * 文件上传到微信服务器
     * @param fileType 媒体文件类型:图片(image)、语音(voice)、视频(video),普通文件(file) 
     * @param filePath 媒体文件路径
     * @return JSONObject
     * @throws Exception
     */
    public JSONObject upload(String url, String fileType, String filePath) throws Exception {  
        String result = null;  
        File file = new File(filePath);  
        if (!file.exists() || !file.isFile()) {  
            throw new IOException("文件不存在");  
        }  
 
        URL urlObj = new URL(url);  
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();  
        con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式  
        con.setDoInput(true);  
        con.setDoOutput(true);  
        con.setUseCaches(false); // post方式不能使用缓存  
        // 设置请求头信息  
        con.setRequestProperty("Connection", "Keep-Alive");  
        con.setRequestProperty("Charset", "UTF-8");  
        // 设置边界  
        String BOUNDARY = "----------" + System.currentTimeMillis();  
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);  
        // 请求正文信息  
        // 第一部分：  
        StringBuilder sb = new StringBuilder();  
        sb.append("--"); // 必须多两道线  
        sb.append(BOUNDARY);  
        sb.append("\r\n");  
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ file.getName() + "\"\r\n");  
        sb.append("Content-Type:application/octet-stream\r\n\r\n");  
        byte[] head = sb.toString().getBytes("utf-8");  
        // 获得输出流  
        OutputStream out = new DataOutputStream(con.getOutputStream());  
        // 输出表头  
        out.write(head);  
        // 文件正文部分  
        // 把文件已流文件的方式 推入到url中  
        DataInputStream in = new DataInputStream(new FileInputStream(file));  
        int bytes = 0;  
        byte[] bufferOut = new byte[1024];  
        while ((bytes = in.read(bufferOut)) != -1) {  
        	out.write(bufferOut, 0, bytes);  
        }  
        in.close();  
        // 结尾部分  
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
        out.write(foot);  
        out.flush();  
        out.close();  
        StringBuffer buffer = new StringBuffer();  
        BufferedReader reader = null;  
	    try {  
	        // 定义BufferedReader输入流来读取URL的响应  
	        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
	        String line = null;  
	        while ((line = reader.readLine()) != null) {  
	        //System.out.println(line);  
	        buffer.append(line);  
	        }  
	        if(result==null){  
	        result = buffer.toString();  
	        }  
	    } catch (IOException e) {  
	        System.out.println("发送POST请求出现异常！" + e);  
	        e.printStackTrace();  
	        throw new IOException("数据读取异常");  
        } finally {  
	        if(reader!=null){  
	        	reader.close();  
	        }  
        }  
        JSONObject jsonObj =new JSONObject(result);  
        return jsonObj;  
    }

}
