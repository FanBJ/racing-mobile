package com.hy.racing.order.services;

import com.hy.racing.entity.Orderinfo;

public interface IOrderServices {
	/**
	 * 报名费
	 */
	int ORDER_TYPE_APPLY_FEE = 1;
	/**
	 * 刷圈费
	 */
	int ORDER_TYPE_ROUND_FEE = 2;
	/**
	 * 查询用户下单时，单圈应该付多少钱
	 * <br/>
	 * 周一至周四180元/节
	 * 周五至周日200元/节
	 * @param uid
	 * @return
	 */
	int queryUserAddOrderInfo(Integer uid);
	
	/**
	 * 查询用户应该支付多少报名费
	 * @param uid
	 * @return
	 */
	int queryRegisterFee(Integer uid);
	
	/**
	 * 添加报名费
	 * @param uid
	 * @return
	 */
	int addRegisterFee(Integer uid);
	
	boolean updateWxPaySuccess(String notifyXml);
	
	Orderinfo getOrderByNo(String orderNo);
}
