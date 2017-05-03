package com.hy.utils.pay.wx;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.hy.utils.db.DBUtil;
import com.hy.utils.json.JacksonUtils;

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

	public static String CreateNoncestr() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < 32; i++) {
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
	public static String createSign(String characterEncoding, SortedMap<String, Object> parameters,String apiKey) {
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
		sb.append("key=" + apiKey);
		System.out.println("参数拼接：" + sb.toString());
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}

	/**
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String getRequestXml(SortedMap<String, Object> param) {
		StringBuffer sf = new StringBuffer();
		sf.append("<xml>");
		for (Entry<String, Object> ele : param.entrySet()) {
			sf.append("<" + ele.getKey() + ">");
			sf.append("<![CDATA[" + ele.getValue() + "]]>");
			sf.append("</" + ele.getKey() + ">");
		}
		sf.append("</xml>");
		return sf.toString();
	}

	/**
	 * 获取微信订单信息
	 * 
	 * @param orderNo
	 * @return
	 */
	public static WxOrderQueryBean queryWxOrderInfo(String orderNo, String mchId) {
		PayInfo pi = ConfigUtil.getPayInfo(mchId);
		if (null == pi)
			return null;

		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", pi.getAppid());
		params.put("mch_id", mchId);
		params.put("nonce_str", CreateNoncestr(16));
		params.put("out_trade_no", orderNo);
		String sign = createSign("UTF-8", params,pi.getApiKey());
		String xml = "<xml>" + "<appid>" + pi.getAppid() + "</appid>" + "<mch_id>" + mchId + "</mch_id>" + "<nonce_str>" + params.get("nonce_str") + "</nonce_str>" + "<out_trade_no>" + orderNo + "</out_trade_no>" + "<sign>" + sign + "</sign>" + "</xml>";
		try {
			WxOrderQueryBean wb = DBUtil.convertMapToObject(XMLUtil.doXMLParse(CommonUtil.httpsRequest(ConfigUtil.CHECK_ORDER_URL, "POST", xml)), WxOrderQueryBean.class);
			return wb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
