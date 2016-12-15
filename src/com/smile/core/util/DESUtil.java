package com.smile.core.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * DES工具类
 * 
 * @author 
 * @createTime 
 * @history 1.修改时间,修改;修改内容：
 * 
 */
public class DESUtil {
	
	private final static String DES_KEY = "azxHw3Jx0QqpeomrVROrMmmN";
	
    public static String desCrypto(String datasource) throws Exception {            
    	try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(DES_KEY.getBytes());
			//创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			//Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			//用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			//现在，获取数据并加密
			//正式执行加密操作
			return new String(Hex.encodeHex(cipher.doFinal(datasource.getBytes())));
		} catch (Exception e) {
			throw new Exception("加密失败");
		} 
    }
    
	public static String decrypt(String src) throws Exception{
        try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(DES_KEY.getBytes());
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			String s = new String(cipher.doFinal(Hex.decodeHex(src.toCharArray())));
			if(doField(s)){
				return s;
			}
			else{
				throw new Exception("参数被篡改");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("解密失败");
		}
    }
    
  //core.js版本链接路径更新
	public static int getV(){
		return   (int) Math.ceil((System.currentTimeMillis()/(60*60*24*1000))*1.00);
	}
    
    public static void main(String[] args) throws Exception{
        //待加密内容
		String params = "xacbank_test:a9f4e59967794ccb86e4ff3db84ea15g:0:0:oNGNhuCq-yGIgbnz21ZVyrASzSbc";
		System.out.println(DESUtil.desCrypto(params));
		//String str = "a9f4e59602594ccb86e4ff3db84ea15c:oXg8EuBSxyKN5yaNhaGcFIm7kEi8:23";
    	String str="a9f4e59602594ccb86e4ff3db84ea15k";
        String result=desCrypto(str);
        System.out.println(result);
        //result=decrypt("bb59d5c6914fd2b70aff4047bd333b683cc246f042d20ba29dd453ca157d0a9015517bddba8292bc");//测试
        result=decrypt("6bb51333621fe566cc2b09b304320f0b0ed6a4e8dc3c2328fd0f78dbbf618f1981d313730a9b17c4a9526b1bba5ad9c8fd706a7066a65435b2891012b842a5680efa659ea988e361c92f5b1ec35dd121");//开发
        //生产result=decrypt("bb59d5c6914fd2b70aff4047bd333b683cc246f042d20ba2cafb7d465afcc30a15517bddba8292bc");
        System.out.println(result);
    }
    
    public static String decrypt(String src,String key) throws Exception{
        try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(key.getBytes());
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作
			return new String(cipher.doFinal(Hex.decodeHex(src.toCharArray())));
		} catch (Exception e) {
			throw new Exception("解密失败");
		}
    }
    
    public static boolean doField(String str) {
		if (str.indexOf("<") == -1 && str.indexOf(">") == -1
				&& str.indexOf("script") == -1 && str.indexOf("style") == -1) {
			return true;
		}
		return false;
	}
}
