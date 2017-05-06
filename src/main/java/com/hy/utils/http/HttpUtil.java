package com.hy.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;

/**
 * HTTP请求工具类
 * 
 * @author FanBingjiang
 */
public class HttpUtil {

	public static String encoding;

	private static final HttpConnectionManager connectionManager;

	private static final HttpClient client;

	static {

		HttpConnectionManagerParams params = loadHttpConfFromFile();

		connectionManager = new MultiThreadedHttpConnectionManager();

		connectionManager.setParams(params);

		client = new HttpClient(connectionManager);
	}

	private static HttpConnectionManagerParams loadHttpConfFromFile() {
		encoding = "utf-8";

		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(5000);
		params.setSoTimeout(5000);
		return params;
	}

	public static String post(String url, String encoding, String content) {
		try {
			byte[] resp = post(url, content.getBytes(encoding));
			if (null == resp)
				return null;
			return new String(resp, encoding);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static String post(String url, String content) {
		return post(url, encoding, content);
	}

	public static byte[] post(String url, byte[] content) {
		try {
			byte[] ret = post(url, new ByteArrayRequestEntity(content));
			return ret;
		} catch (Exception e) {
			return null;
		}
	}

	public static byte[] post(String url, RequestEntity requestEntity)
			throws Exception {

		PostMethod method = new PostMethod(url);
		method.addRequestHeader("Connection", "Keep-Alive");
		method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(0, false));
		method.setRequestEntity(requestEntity);
		method.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			return method.getResponseBody();

		} catch (SocketTimeoutException e) {
			return null;
		} catch (Exception e) {
			return null;
		} finally {
			method.releaseConnection();
		}
	}

	/**
	 * 把请求要素按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param para
	 *            请求要素
	 * @param sort
	 *            是否需要根据key值作升序排列
	 * @param encode
	 *            是否需要URL编码
	 * @param charset
	 *            URL编码格式
	 * @return 拼接成的字符串
	 */
	public static String createLinkString(Map<String, String> para,
			boolean sort, boolean encode, String charset) {

		List<String> keys = new ArrayList<String>(para.keySet());

		if (sort)
			Collections.sort(keys);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = para.get(key);

			if (encode) {
				try {
					value = URLEncoder.encode(value, charset);
				} catch (UnsupportedEncodingException e) {
				}
			}

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				sb.append(key).append("=").append(value);
			} else {
				sb.append(key).append("=").append(value).append("&");
			}
		}
		return sb.toString();
	}

	/**
	 * 把请求要素按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param para
	 *            请求要素
	 * @param sort
	 *            是否需要根据key值作升序排列
	 * @param encode
	 *            是否需要URL编码
	 * @param charset
	 *            URL编码格式
	 * @return 拼接成的字符串
	 */
	public static String createLinkString2(Map<String, String> para,
			boolean sort, boolean encode, String charset) {

		List<String> keys = new ArrayList<String>(para.keySet());

		if (sort)
			Collections.sort(keys);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = para.get(key);

			if (encode) {
				try {
					value = URLEncoder.encode(value, charset);
				} catch (UnsupportedEncodingException e) {
				}
			}

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				sb.append(key).append("#x=a_").append(value);
			} else {
				sb.append(key).append("#x=a_").append(value).append("#x&a_");
			}
		}
		return sb.toString();
	}

	/**
	 * 把参数转为Map
	 * 
	 * @param args
	 * @return
	 */
	public static Map<String, String[]> deLinkString(String args) {
		if (null != args) {
			Map<String, String[]> params = new HashMap<String, String[]>();
			String[] str = args.split("#x&a_");// 分割&

			for (String s : str) {
				String[] kv = s.split("#x=a_");// 分割=
				if (kv.length == 1 && kv[0].length() > 1) {// 参数为json
					params.put("json", new String[] { kv[0].toString() });
				} else {
					params.put(kv[0], new String[] { kv[1] });
				}
			}
			return params;
		}
		return null;
	}

	public static String doGet(String url) {
		String response = null;
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");
		HttpMethod method = new GetMethod(url);
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			}
		} catch (URIException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return response;
	}
	
	public static String encode(String content){
		if(StringUtils.isBlank(content)){
			return "";
		}
		try {
			return URLEncoder.encode(content,"utf-8");
		} catch (UnsupportedEncodingException e) {
			return content;
		}
	}

}
