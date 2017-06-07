package com.hy.utils.pay.wx;

import com.hy.utils.cache.CacheUtils;

public class ConfigUtil {
	/**
	 * 服务号相关信息
	 */
	public final static String APPID = "wx4ebd19d51022b37b";// 服务号的应用号
	public final static String APP_SECRECT = "151b0d80a9d4d7e730e047acc5bf485e";// 服务号的应用密码
	public final static String MCH_ID = "1271438801";// 商户号
	public final static String API_KEY = "77d7294148df6b454fc3d19b02ad22a2";// API密钥
	public final static String SIGN_TYPE = "MD5";// 签名加密方式
	public final static String ROOT_URL = CacheUtils.getSysParamVal("visit_web_root");
	
	//骏驰赛车场KEY
	public final static String APPID_JC = "wx6eddba941e891feb";// 服务号的应用号
	public final static String APP_SECRECT_JC = "dbd86d96ba963f4c47eef97039dceb33";// 服务号的应用密码

	// 微信支付统一接口的回调action
	public final static String NOTIFY_URL = ROOT_URL + "/phone/order/wxnotify";
	// 微信支付成功支付后跳转的地址
	public final static String SUCCESS_URL = ROOT_URL + "/phone/order/wxsuccess";
	/**
	 * 微信基础接口地址
	 */
	// 第一步：用户同意授权，获取code
	public final static String GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	// 第二步：通过code获取access_token
	public final static String GET_TOKEN_BY_CODE = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	// 第三步：通过access_token获取用户信息
	public final static String GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	// 获取token接口(GET)
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// oauth2授权接口(GET)
	public final static String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	// 刷新access_token接口（GET）
	public final static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	// 获取Ticket接口
	public final static String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	// 菜单创建接口（POST）
	public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 菜单查询（GET）
	public final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 菜单删除（GET）
	public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	/**
	 * 微信支付接口地址
	 */
	// 微信支付统一接口(POST)
	public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 微信退款接口(POST)
	public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	// 订单查询接口(POST)
	public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	// 关闭订单接口(POST)
	public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	// 退款查询接口(POST)
	public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	// 对账单接口(POST)
	public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	// 短链接转换接口(POST)
	public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	// 接口调用上报接口(POST)
	public final static String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
}
