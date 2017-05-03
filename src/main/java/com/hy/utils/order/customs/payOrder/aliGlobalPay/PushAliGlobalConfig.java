package com.hy.utils.order.customs.payOrder.aliGlobalPay;

public class PushAliGlobalConfig {
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		// 合作身份者ID，以2088开头由16位纯数字组成的字符串
		/**
		 * 测试
		 * 		public static String partner = "2088101122136241";
		// 商户的私钥
		public static String private_key = "760bdzec6y9goq7ctyx96ezkz78287de";
		
		// 支付宝的公钥，无需修改该值
		public static String ali_public_key  = "760bdzec6y9goq7ctyx96ezkz78287de";
		 */


		//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
		public static String ali_seller="ecomint@xingliecom.com";
		/**
		 * 正式
		 * 
		 * */
		public static String partner = "2088521096290653";
		// 原始商户的私钥
		public static String key = "xrix47j4t84hrx7wry7slc6rx44cp0ag";
		public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMVLY4l3Qw9JJjL8Zocfmt4HqKeK33IBDA8pz29C2REnLnfF3w5tn1zgSvKqnShuytVarrN7A06Qwvap5EE7sxj//M+d9w+s3fvzlGo5y2jWzGw00r+JsZwap2c9TlFogsmFNL4jwXCztIZPZVJWlI5VPO3f4j21W+sIsmuPSTJhAgMBAAECgYAENWV8KzUdAFx2PfgZ7cyp69PnLwkxL2CIekXYg76JpSJSn7/wWNpLauVuY8FSeXqOLo8HeRpK9L+T72/VDGb3mfIbGHdsY8ulij0eVR64WMqBWS3p+zZ4Mh0aKyq1Uxwn6leTjfzkXDArLYl+wkfBcJWtz+M8Uk/tK/iSMvkLOQJBAPCGuQl45zGp2yDeFf4kE9hEK0fCU3/1z49CFWv0YcWp8okGt1VfsBpioNYAfmjeB+k2Rr7nrlLMBxH9FVA/cPMCQQDR/KxEMRdpQFXy0g74cH5395B1EsXtFaKvyCbrMY9ZVBL08rcL0/RoJbKe/jomYrU88VaizRu8nG/inMSRNsRbAkBPdgKdWX7vjlDhS91yJ1MTCHpP66gQRxH6HvfawVKnrZx9nmVk5AbWwKyn5mPcmzV72z2GBh42JkdTrwuihkL3AkEAqQT3aAAXtvk49TVNJX3u3gNfvEw7EiU7CMBTSzZeCunYRF9lJNkbPgUx7DX3elQzS3V6ZZowvDUy/bVvpu7tRwJAdJwTZeq4wnzY6DiG3eAI4HDeSNHAmLZPa/H4dkDDbKXb06p8Zo02V3RKoqVkHXx0wE3ydhokRwuMrmyZkrvGYg==";
		public static String url ="https://mapi.alipay.com/gateway.do?trade_no=TRADE_NO&merchant_customs_code=MERCHANT_CUSTOMS_CODE&sign_type=SIGN_TYPE&merchant_customs_name=MERCHANT_CUSTOMS_NAME&sign=SIGN&amount=AMOUNT&_input_charset=_INPUT_CHARSET&customs_place=CUSTOMS_PLACE&service=SERVICE&out_request_no=OUT_REQUEST_NO&partner=PARTNER";
			
 		// 字符编码格式 目前支持 gbk 或 utf-8
		public static String input_charset = "utf-8";
		// 签名方式 不需修改
		public static String sign_type = "MD5";
		// 海关编号
		public static String customs_place = "ZONGSHU";
		// 接口名称
		public static String service = "alipay.acquire.customs";
		// 调试用，创建TXT日志文件夹路径
		public static String log_path = "D:\\";

}
