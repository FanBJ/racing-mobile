package com.hy.utils.order.customs.payOrder.aliPay;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	/**
	 * 沙箱环境
	 */
	public static String partner ="2088101122136241";
    public static String key = "760bdzec6y9goq7ctyx96ezkz78287de";
    public static String url ="https://openapi.alipaydev.com/gateway.do?trade_no=TRADE_NO&merchant_customs_code=MERCHANT_CUSTOMS_CODE&sign_type=SIGN_TYPE&merchant_customs_name=MERCHANT_CUSTOMS_NAME&sign=SIGN&amount=AMOUNT&_input_charset=_INPUT_CHARSET&customs_place=CUSTOMS_PLACE&service=SERVICE&out_request_no=OUT_REQUEST_NO&partner=PARTNER";
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	/*// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088411293003575";
	// 商户的私钥
	public static String key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMgCNmxSRpcPSZnhT7kkTuOh58bhr8KQ8e80jLcSROrjqpvnWGZrZkpbpWmjj6sQuq6slkHSMW4BBXCF5Z9ROCs/A5JXf8VbQAesls9V87sWTP/3eEKceR9FboFCiBNycxty7cH4Q+33rxjDOvdGUD3nr+PPa8TNRjz/YBmaDuSXAgMBAAECgYEAga9dlnLqrnwNHyfGx6dRN6v9WylvC1tsY8vRCctgwGUKdLhWy2IMG169LHgPab4ZLbVeYCNoRYmH+2P2dYz2O32JICG3Zp0pfulupyadJU2UjV0k0mlrXJNu6I0NAXLEsaXewpsnstePnv1yCygCRjck8DT6eP4TcVH+/wQselECQQD4uFkJBixDypc71tHTRvjljlPLO8Kr4mtzdn90GSPMrt0AqLqM83XsmQj1+FhwznwobkHrFj+taxBc8sEKY5fNAkEAzdzfC1Fp1UAJ5l9hOvuhV1Nop0PdPnL6VjgVG9ZV6B5FDsfT07De/gvv/JTfqwa/60/OPnFREguvsTWEl6YB8wJAVFPMfC7Hm0R4+iTfh06s3MfgySs92aEu5TrsA6TyaldvPuX2Ogvhk5JfY2YMNlML7XlUiCxG8rk2Fw2HPcp+kQJBAJotkJLOb5/57TAvQQfGy2g2nMuhBQMvY5PNPunxVbeZbAkM2fXSor/LuvoFmZpiXLxksceMYLZOMFBdD6fv+ccCQBMtPLEa+tzT1WipL7zUsZnlOMXZBOjJAO2XYnfRnjsNUEuZGbJrO23k+lEaWq62j1YR95b2MNRnQn5o9AApH2s=";
	*/
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	//推送url
    		
	/*public static String url = "https://mapi.alipay.com/gateway.do?trade_no=TRADE_NO&merchant_customs_code=MERCHANT_CUSTOMS_CODE&sign_type=SIGN_TYPE&merchant_customs_name=MERCHANT_CUSTOMS_NAME&sign=SIGN&amount=AMOUNT&_input_charset=_INPUT_CHARSET&customs_place=CUSTOMS_PLACE&service=SERVICE&out_request_no=OUT_REQUEST_NO&partner=PARTNER";
	*/
    		// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	// 签名方式 不需修改
	public static String sign_type = "MD5";
	// 海关编号
	public static String customs_place = "CHONGQING";
	// 接口名称
	public static String service = "alipay.acquire.customs";

	//https://openapi.alipaydev.com/gateway.do?
}
