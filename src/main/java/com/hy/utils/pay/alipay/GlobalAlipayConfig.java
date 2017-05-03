package com.hy.utils.pay.alipay;

public class GlobalAlipayConfig {
	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088021017666931";
	// 商户的私钥
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIZPrS1VN0D7bg4zmsYeGe8Wr/7TcseJGxxmGBQMbJaHJmdzO0UCzLE7vwRBKm9U8gdxTh9aLx7B0YiL0a5q1982SEDMS1p1v6hw5Khn67xOEZtIXM9f1rEW9hf3UYKqlHQvp/xxwfB5lRKPV9ipkFyqdX2IkP/pko8FsdlTr3GBAgMBAAECgYASFXhDuk2OOkw/LUORmUv0nSFlNiMmKpez4ysqx+F3Awtwslswl4fn9Rtq/zsvckiEKAVUw1fE1GPZ9rlsuoYGG0QoeJaWokKbEQ5P3o2baAW8aOawfHv0bTtqdkqc+I6VsG0Jwb+qRXXLKE9SGfY4PF05A7jIh3tHvavXtACWgQJBAP/0DxNI+yH42G+eBcQV0sf0HT3Q3709ygARm2Pe5rIf+GncVXbvGfkjmd+ecwhYXOd/inHyCyT2fr0bTCloxmUCQQCGVfFLfPDTZHtqm1KojPpNruMKsFWqIKbDzq26/RMszh9thUOtuh6Iy/hkr5Q+Vlb0PQvlIfdQOEkeeZ2l+U7tAkAU7/D/wduRNtr1nnaYSLS6WJS/xsNWpqRMhyQv8j7q2USn3Dc9iGeWgrwGl96PEzNeWIeJtKQGvQc3DvGW/ZQlAkBuGe5kooh3mfmVTKyYvMNB/D7VueTcRVqpTbISee3HYUFc8EaiEtOH5zU0wLtNxFIMaCRhrk8XDqrPMXpGWGmZAkEAxKk2hPptPRAsJvvRVrhy/tVAtMPkCmlvr0rKWEWPZWaQYzItWl8CwNnmaU5L8ovqniBct5SYW8CR6jHoUublog==";

	// 支付宝的公钥，无需修改该值
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
