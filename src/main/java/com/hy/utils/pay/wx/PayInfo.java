package com.hy.utils.pay.wx;

public class PayInfo {
	private String mchId;
	private String appid;
	private String appSecrect;
	private String apiKey;
	private String signType;
	private String token;

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppSecrect() {
		return appSecrect;
	}

	public void setAppSecrect(String appSecrect) {
		this.appSecrect = appSecrect;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
