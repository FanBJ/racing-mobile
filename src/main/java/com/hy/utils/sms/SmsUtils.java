package com.hy.utils.sms;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import open189.sign.ParamsSign;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.hy.utils.cache.CacheUtils;
import com.hy.utils.date.DateUtil;
import com.hy.utils.http.HttpUtil;

/**
 * 短信发送接口
 */
public class SmsUtils {
	private static Logger logger = Logger.getLogger(SmsUtils.class);

	private static String app_id = CacheUtils.getFieldVal("短信接口", "app_id");
	private static String sms_url = CacheUtils.getFieldVal("短信接口", "send_url");
	private static String app_secret = CacheUtils.getFieldVal("短信接口", "app_secret");
	private static String access_token_url = CacheUtils.getFieldVal("短信接口", "access_token_url");
	// 通用模板ID
	private static String common_temp_id = CacheUtils.getFieldVal("短信接口", "common_temp_id");
	// 订单提醒模板ID
	private static String order_temp_id = CacheUtils.getFieldVal("短信接口", "order_temp_id");
	// AccessToken值，这个值每过一段时间会过期一次，所以每次发短信都建立一个新的。
	private static String access_token;
	// 通用有效时间
	private static String commTimeDesc = CacheUtils.getFieldVal("短信接口", "common_time");
	// 注册有效时间
	private static String regTimeDesc = CacheUtils.getFieldVal("短信接口", "reg_time");
	// 登录有效时间
	private static String loginTimeDesc = CacheUtils.getFieldVal("短信接口", "login_time");
	// 找回密码有效时间
	private static String forgetPwdTimeDesc = CacheUtils.getFieldVal("短信接口", "forget_pwd_time");
	// 修改用户支付密码有效时间
	private static String updateUserPayPwdTimeDesc = CacheUtils.getFieldVal("短信接口", "update_user_pay_pwd_time");
	// 修改用户登录账号（号码）有效时间
	private static String updateUseTelTimeDesc = CacheUtils.getFieldVal("短信接口", "update_user_tel_time");

	/**
	 * 获取access_token
	 * 
	 * @param app_id
	 * @param app_secret
	 * @return @
	 */
	private static String getAccessToken() {
		Map<String, String> postHeaders = new HashMap<String, String>();
		postHeaders.put("grant_type", "client_credentials");
		postHeaders.put("app_id", app_id);
		postHeaders.put("app_secret", app_secret);

		String resJson = HttpUtil.post(access_token_url, HttpUtil.createLinkString(postHeaders, false, false, "UTF-8"));
		JSONObject json = new JSONObject(resJson);
		access_token = json.get("access_token") + "";
		return access_token;
	}

	/**
	 * 公共模板短信发送
	 * 
	 * @param accessToken
	 * @param temp_id
	 *            模板ID
	 * @param code
	 *            验证码
	 * @param timestamp
	 *            时间,yyyy-MM-dd HH:mm:ss格式
	 * @param tel
	 *            手机号
	 * @param timeDesc
	 *            过期时间描述,如:2分钟
	 * @param func
	 *            功能描述,如:注册
	 * @return 验证码 @
	 */
	public static String sendCommonTempMsg(String tel, String accessToken, String temp_id, String code, String timestamp, String timeDesc, String func) {
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		paramsMap.put("app_id", app_id);
		paramsMap.put("access_token", accessToken);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("acceptor_tel", tel);
		paramsMap.put("template_id", temp_id);
		paramsMap.put("template_param", "{\"code\":\"" + code + "\",\"time\":\"" + timeDesc + "\"}");
		paramsMap.put("sign", ParamsSign.value(paramsMap, app_secret));

		String resJson = HttpUtil.post(sms_url, HttpUtil.createLinkString(paramsMap, false, false, "UTF-8"));
		if(resJson==null){
			logger.error("发送短信失败：返回null了" );
			return null;
		}
		JSONObject json = new JSONObject(resJson);
		if ("0".equals(json.get("res_code").toString())) {// 0代表成功，其他不正常
			return code;
		} else {
			logger.error("发送短信失败：" + json.toString());
			return null;
		}
	}

	/**
	 * 发送订单提醒
	 * 
	 * @param tel
	 * @param accessToken
	 * @param timestamp
	 * @param shopName
	 *            店铺名称
	 * @param function
	 * @param orderNo
	 *            订单编号
	 * @return @
	 */
	public static boolean sendOrderTempMsg(String tel, String accessToken, String timestamp, String shopName, String function, String orderNo) {
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		paramsMap.put("app_id", app_id);
		paramsMap.put("access_token", accessToken);
		paramsMap.put("timestamp", timestamp);
		paramsMap.put("acceptor_tel", tel);
		paramsMap.put("template_id", order_temp_id);
		paramsMap.put("template_param", "{\"shopName\":\"" + shopName + "\",\"time\":\"" + timestamp + "\",\"function\":\"" + function + "\",\"orderNo\":\"" + orderNo + "\"}");
		paramsMap.put("sign", ParamsSign.value(paramsMap, app_secret));

		String resJson = HttpUtil.post(sms_url, HttpUtil.createLinkString(paramsMap, false, false, "UTF-8"));
		JSONObject json = new JSONObject(resJson);

		if ("0".equals(json.get("res_code").toString())) {// 0代表成功，其他不正常
			return true;
		} else {
			logger.error("发送短信失败：" + json.toString());
			return false;
		}
	}

	public static String sendCommonTempMsg(String tel) {
		return sendCommonTempMsg(tel, getAccessToken(), common_temp_id, getRandomNum(4), DateUtil.nowDate(), commTimeDesc, "");
	}

	public static String sendCommonTempMsg(String tel, String timeDesc, String code) {
		return sendCommonTempMsg(tel, getAccessToken(), common_temp_id, code, DateUtil.nowDate(), timeDesc, "");
	}

	/**
	 * 发送手机注册时的验证码
	 * 
	 * @param tel
	 * @return @
	 */
	public static String sendRegValiCode(String tel) {
		return sendCommonTempMsg(tel, regTimeDesc, getRandomNum(4));
	}

	/**
	 * 发送手机注册时的验证码
	 * 
	 * @param tel
	 * @return @
	 */
	public static String sendRegValiCode(String tel, String code) {
		return sendCommonTempMsg(tel, regTimeDesc, code);
	}

	/**
	 * 发送订单信息
	 * 
	 * @param tel
	 * @return @
	 */
	public static boolean sendShopOrder(String tel, String shopName, String title, String orderNo) {
		return sendOrderTempMsg(tel, getAccessToken(), DateUtil.nowDate(DateUtil.DF), shopName, title, orderNo);
	}

	/**
	 * 发送修改支付密码 验证码
	 * 
	 * @param tel
	 * @return @
	 */
	public static String sendUpdatePayPwdValiCode(String tel) {
		return sendCommonTempMsg(tel, updateUserPayPwdTimeDesc, "修改支付密码");
	}

	/**
	 * 发送修改手机登录账号 验证码
	 * 
	 * @param tel
	 * @return @
	 */
	public static String sendUpdateUserTelValiCode(String tel) {
		return sendCommonTempMsg(tel, updateUseTelTimeDesc, "修改登录账号");
	}

	/**
	 * 发送店铺后台登录验证码
	 * 
	 * @param tel
	 * @return @
	 */
	public static String sendLoginValiCode(String tel) {
		return sendCommonTempMsg(tel, loginTimeDesc, "登录");
	}

	/**
	 * 发送手机用户忘记密码，找回功能时
	 * 
	 * @param tel
	 * @return
	 */
	public static String sendPhoneUserForgetPwdValiCode(String tel) {
		return sendCommonTempMsg(tel, forgetPwdTimeDesc, "找回密码");
	}

	/**
	 * 获取随机数
	 * 
	 * @param length
	 *            长度
	 * @return
	 */
	public static String getRandomNum(int length) {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < length; i++) {
			result += random.nextInt(10);
		}
		return result;
	}

}
