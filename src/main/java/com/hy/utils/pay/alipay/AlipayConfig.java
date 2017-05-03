package com.hy.utils.pay.alipay;

public class AlipayConfig {
	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088411293003575";
	// 商户的私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMgCNmxSRpcPSZnhT7kkTuOh58bhr8KQ8e80jLcSROrjqpvnWGZrZkpbpWmjj6sQuq6slkHSMW4BBXCF5Z9ROCs/A5JXf8VbQAesls9V87sWTP/3eEKceR9FboFCiBNycxty7cH4Q+33rxjDOvdGUD3nr+PPa8TNRjz/YBmaDuSXAgMBAAECgYEAga9dlnLqrnwNHyfGx6dRN6v9WylvC1tsY8vRCctgwGUKdLhWy2IMG169LHgPab4ZLbVeYCNoRYmH+2P2dYz2O32JICG3Zp0pfulupyadJU2UjV0k0mlrXJNu6I0NAXLEsaXewpsnstePnv1yCygCRjck8DT6eP4TcVH+/wQselECQQD4uFkJBixDypc71tHTRvjljlPLO8Kr4mtzdn90GSPMrt0AqLqM83XsmQj1+FhwznwobkHrFj+taxBc8sEKY5fNAkEAzdzfC1Fp1UAJ5l9hOvuhV1Nop0PdPnL6VjgVG9ZV6B5FDsfT07De/gvv/JTfqwa/60/OPnFREguvsTWEl6YB8wJAVFPMfC7Hm0R4+iTfh06s3MfgySs92aEu5TrsA6TyaldvPuX2Ogvhk5JfY2YMNlML7XlUiCxG8rk2Fw2HPcp+kQJBAJotkJLOb5/57TAvQQfGy2g2nMuhBQMvY5PNPunxVbeZbAkM2fXSor/LuvoFmZpiXLxksceMYLZOMFBdD6fv+ccCQBMtPLEa+tzT1WipL7zUsZnlOMXZBOjJAO2XYnfRnjsNUEuZGbJrO23k+lEaWq62j1YR95b2MNRnQn5o9AApH2s=";

	// 支付宝的公钥，无需修改该值
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	public static String ali_seller="xlwg@xinglico.com";
	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
