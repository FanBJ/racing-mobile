package com.hy.utils.order;

public class AddOrderBean {
	private String orderNo;//订单编号
	private String totalPrice;//总价,以分为单位,正整数!
	private String orderDesc;//订单描述
	
	private String addURL;//订单发起的URL地址
	private String openid;
	private String attach;//附加参数
	private String errMsg;//添加失败错误信息

	
	
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAddURL() {
		return addURL;
	}

	public void setAddURL(String addURL) {
		this.addURL = addURL;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

}
