package com.hy.utils.order;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.JDOMException;

import com.hy.utils.ip.IPUtils;
import com.hy.utils.pay.wx.CommonUtil;
import com.hy.utils.pay.wx.ConfigUtil;
import com.hy.utils.pay.wx.PayCommonUtil;
import com.hy.utils.pay.wx.XMLUtil;

/**
 * 订单相关
 * 
 * @author fbj
 * 
 */
public class OrderUtils {
	/**
	 * 统一下单接口
	 * 
	 * @param request
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static SortedMap<String, Object> wxUnifyAddOrder(HttpServletRequest request, AddOrderBean order) throws JDOMException, IOException {
		SortedMap<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put("appid", ConfigUtil.APPID);
		if(!StringUtils.isBlank(order.getAttach())){//附加参数
			parameters.put("attach", order.getAttach());
		}
		parameters.put("mch_id", ConfigUtil.MCH_ID);// 商号
		parameters.put("nonce_str", PayCommonUtil.CreateNoncestr(32));// 随机值
		parameters.put("body", order.getOrderDesc());// 订单描述
		parameters.put("out_trade_no", order.getOrderNo());// 订单编号
		parameters.put("total_fee", order.getTotalPrice());// 总价,以分为单位,正整数
		parameters.put("spbill_create_ip", IPUtils.getRemoteHost(request));
		parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
		parameters.put("trade_type", "JSAPI");
		parameters.put("openid", order.getOpenid());

		// 生成统一下单签名
		String sign = PayCommonUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		// 生成统一下单请求的XML文件格式内容
		String requestXML = PayCommonUtil.getRequestXml(parameters);
		// 请求统一下单
		String result = CommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
		// 解析统一下单返回的信息，以Map形式存储便于取值
		Map<String, Object> map = XMLUtil.doXMLParse(result);

		// 将返回的值传入到支付jsp页面，在支付jsp页面调用支付接口
		// wx.config中的参数
		/**
		 * 获取预支付prepay_id之后，需要再次进行签名，参与签名的参数有：appId、timeStamp、nonceStr、package、
		 * signType
		 */
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appId", ConfigUtil.APPID);
		// 时间戳以秒为单位,最好转为字符串,不然可能报签名错误
		params.put("timeStamp", ((long) System.currentTimeMillis() / 1000) + "");
		params.put("nonceStr", PayCommonUtil.CreateNoncestr(32));

		// 微信支付最新接口中，要求package的值的固定格式为prepay_id=...
		params.put("package", "prepay_id=" + map.get("prepay_id"));
		// 微信支付新版本签名算法使用MD5，不是SHA1
		params.put("signType", "MD5");
		// 生成chooseWXPay的签名
		String paySign = PayCommonUtil.createSign("UTF-8", params);
		params.put("paySign", paySign);
		// 预支付单号
		params.put("packageValue", "prepay_id=" + map.get("prepay_id"));
		// 支付成功跳转地址
		params.put("sendUrl", ConfigUtil.SUCCESS_URL);
		// 获取用户的微信客户端版本号，用于前端支付之前进行版本判断，微信版本低于5.0无法使用微信支付
		String userAgent = request.getHeader("user-agent");
		char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger") + 15);
		params.put("agent", new String(new char[] { agent }));

		// 生成wx.config中的signature值
		SortedMap<String, Object> configParams = new TreeMap<String, Object>();
		configParams.put("noncestr", params.get("nonceStr"));
		configParams.put("timestamp", params.get("timeStamp"));
		configParams.put("url", order.getAddURL());
		configParams.put("jsapi_ticket", CommonUtil.getTicket().getTicket());

		// JS-SDK使用权限签名算法
		String signature = PayCommonUtil.createSignatureSHA1(configParams);
		// 添加并返回
		params.put("signature", signature);

		return params;
	}

	private static List<String> olist = new ArrayList<String>(500);
	/**
	 * 获取订单编号
	 * 
	 * @param args
	 * @return
	 */
	public static synchronized String getOrderNo() {
		while (true) {
			String str = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			if (!olist.contains(str)) {
				if (olist.size() > 500) {
					olist.clear();
				}
				olist.add(str);
				return str;
			} else {
				continue;
			}
		}
	}
}
