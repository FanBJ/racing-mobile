package com.hy.utils.pay.wx;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import com.hy.utils.security.Coder;

public class PayCommonUtil {
	public static String CreateNoncestr(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < length; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res;
	}

	/**
	 * @Description：sign签名
	 * @param characterEncoding
	 *            编码格式
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String createSign(String characterEncoding, SortedMap<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, Object>> es = parameters.entrySet();
		Iterator<Entry<String, Object>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			String k = entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + ConfigUtil.API_KEY);
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}

	/**
	 * @Description：JS-SDK使用权限签名算法
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String createSignatureSHA1(SortedMap<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, Object>> es = parameters.entrySet();
		Iterator<Entry<String, Object>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			String k = entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		String sign = Coder.encode("SHA1", sb.toString().substring(0, sb.toString().length() - 1));
		return sign;
	}

	/**
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters
	 *            请求参数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getRequestXml(SortedMap<String, Object> param) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set<Entry<String, Object>> es = param.entrySet();
		Iterator<Entry<String, Object>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * @Description：返回给微信的参数
	 * @param return_code
	 *            返回编码
	 * @param return_msg
	 *            返回信息
	 * @return
	 */
	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
	}
}
