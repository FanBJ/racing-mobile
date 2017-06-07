package com.hy.utils.pay.wx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.hy.utils.cache.CacheUtils;
import com.hy.utils.json.JacksonUtils;

/**
 * 通用工具类
 * 
 * @author 李欣桦
 * @date 2014-11-21下午9:10:30
 */
public class CommonUtil {
	/**
	 * 发送https请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return 返回微信服务器响应的信息
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			System.out.println("连接超时：{}" + ce);
		} catch (Exception e) {
			System.out.println("https请求异常：{}" + e);
		}
		return null;
	}

	/**
	 * 获取接口访问凭证
	 * 
	 * @return
	 */
	public static Token getToken(String code,String state) {
		String url = null;
		if("3".equals(state)){
			url = ConfigUtil.GET_TOKEN_BY_CODE.replace("APPID", ConfigUtil.APPID_JC).replace("SECRET", ConfigUtil.APP_SECRECT_JC).replace("CODE", code);
		}else{
			url = ConfigUtil.GET_TOKEN_BY_CODE.replace("APPID", ConfigUtil.APPID).replace("SECRET", ConfigUtil.APP_SECRECT).replace("CODE", code);
		}
		
		Token token = null;
		// 发起GET请求获取凭证
		try {
			token = JacksonUtils.fromJson(httpsRequest(url, "GET", null), Token.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * access_token是公众号的全局唯一票据
	 * 
	 * @return
	 */
	public static Token getAccessToken() {
		Token token = (Token) CacheUtils.get("access_token");
		if (token != null && token.getAccess_token() != null) {// 缓存中已存在，从里面取
			long l = System.currentTimeMillis() - token.getCreateTime().getTime();
			if ((l / 1000) <= 7000) {
				return token;
			}
		}

		String url = ConfigUtil.TOKEN_URL.replace("APPID", ConfigUtil.APPID).replace("APPSECRET", ConfigUtil.APP_SECRECT);
		// 发起GET请求获取凭证
		try {
			token = JacksonUtils.fromJson(httpsRequest(url, "GET", null), Token.class);
			token.setCreateTime(new Date());
			// 存入缓存
			CacheUtils.put("access_token", token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * jsapi_ticket是公众号用于调用微信JS接口的临时票据。正常情况下，jsapi_ticket的有效期为7200秒，
	 * 通过access_token来获取
	 * 
	 * @return
	 */
	public static Token getTicket() {
		Token access = getAccessToken();
		if (null == access || access.getAccess_token() == null) {
			return null;
		}
		Token token = (Token) CacheUtils.get("jsapi_ticket");
		if (token != null && token.getTicket() != null) {// 缓存中已存在，从里面取
			long l = System.currentTimeMillis() - token.getCreateTime().getTime();
			if ((l / 1000) <= 7000) {
				return token;
			}
		}

		String url = ConfigUtil.TICKET_URL.replace("ACCESS_TOKEN", access.getAccess_token());
		// 发起GET请求获取凭证
		try {
			token = JacksonUtils.fromJson(httpsRequest(url, "POST", null), Token.class);
			token.setCreateTime(new Date());
			// 存入缓存
			CacheUtils.put("jsapi_ticket", token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	public static WxUserinfo getWxUserinfo(String token, String openid) throws Exception {
		String url = ConfigUtil.GET_USERINFO.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		return JacksonUtils.fromJson(httpsRequest(url, "GET", null), WxUserinfo.class);
	}

	
	public static String getTimestamp() {
		return ((long) System.currentTimeMillis() / 1000) + "";
	}

	public static String getNonceStr() {
		return PayCommonUtil.CreateNoncestr(16);
	}
	
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}