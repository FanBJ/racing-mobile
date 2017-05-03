package com.hy.utils.pay.wx;

import java.util.HashMap;
import java.util.Map;

import com.hy.utils.cache.CacheUtils;

public class ConfigUtil {
	
	public final static String APPID_APP = "wxcaf010f765c81aa2";// APP
	/**
	 * 服务号相关信息
	 */
	public final static String APPID_WXCHAT = "wx2837f84748486a27";// 服务号的应用号
	
	public final static String APP_SECRECT = "99778669dd38e9394305fe8bb0d59c07";// 服务号的应用密码
	public final static String MCH_ID = "1280030601";// 商户号
	public final static String API_KEY = "cb02675191046b0ef2b7ff702fe47b45";// API密钥
	public final static String SIGN_TYPE = "MD5";// 签名加密方式
	public final static String TOKEN = "881919158bb651fd9e5afcf7h4cf1f3e";//自定义token
	/**
	 * 服务号相关信息
	 */
	private static Map<String,PayInfo> payInfo = new HashMap<String,PayInfo>();
	public static enum payType{
		WEB,APP
	}
	
	static{
		PayInfo web = new PayInfo();
		web.setAppid("wx2837f84748486a27");
		web.setMchId("1280030601");
		web.setApiKey("64eb90dae2156ec69a88d2e6d7cbadcc");
		web.setAppSecrect("99778669dd38e9394305fe8bb0d59c07");
		web.setSignType("MD5");
		
		PayInfo app = new PayInfo();
		app.setApiKey("734dfc221901686aaf2c688b345caa70");
		app.setAppid("wxcaf010f765c81aa2");
		app.setAppSecrect("3d6480070b43cffa72e4db585ec3fd3a");
		app.setMchId("1313427901");
		app.setSignType("MD5");
		
		payInfo.put("1280030601", web);
		payInfo.put("1313427901", app);
	}
	
	public static PayInfo getPayInfo(String mchId){
		return payInfo.get(mchId);
	}
	
	
	// 微信支付统一接口的回调action
	public final static String WX_NOTIFY_URL = CacheUtils.getFieldVal("order.pay", "wx_notify_url");

	// 支付宝异步回调
	public final static String ALI_NOTIFY_URL = CacheUtils.getFieldVal("order.pay", "alipay_notify_url");
	// 国际支付宝异步回调
	public final static String GLOBAL_ALI_NOTIFY_URL = CacheUtils.getFieldVal("order.pay", "global_alipay_notify_url");
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
