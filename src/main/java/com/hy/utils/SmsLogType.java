package com.hy.utils;

/**
 * 发送短信类型，分别有：注册/登录/忘记密码/更新密码/更新手机号
 * 
 * @author FBJ
 * 
 */
public enum SmsLogType {
	 STORE("store"),APP_BINDING("app_binding"), APP_REG("app_reg"), APP_LOGIN("app_login"), APP_FORGET_PWD("app_forget_pwd"), APP_UPDATE_PWD("app_update_pwd"), APP_UPDATE_ACCOUNT("app_update_account");
	
	private SmsLogType(String t) {
		this.type = t;
	}

	private String type;

	public String getValue() {
		return type;
	}
}
