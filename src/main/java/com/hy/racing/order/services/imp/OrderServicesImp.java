package com.hy.racing.order.services.imp;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.core.services.BaseServices;
import com.hy.racing.entity.Orderinfo;
import com.hy.racing.entity.Userinfo;
import com.hy.racing.order.services.IOrderServices;
import com.hy.racing.userinfo.services.IUserInfoServices;
import com.hy.utils.db.DBUtil;
import com.hy.utils.pay.wx.WxNotify;
import com.hy.utils.pay.wx.XMLUtil;

@Service
public class OrderServicesImp extends BaseServices implements IOrderServices {

	@Autowired
	IUserInfoServices userServices;
	
	@Override
	public int queryUserAddOrderInfo(Integer uid) {

		return 0;
	}

	@Override
	public int queryRegisterFee(Integer uid) {

		return 0;
	}

	@Override
	public int addRegisterFee(Integer uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateWxPaySuccess(String notifyXml) {
		try {
			Map<String, Object> map = XMLUtil.doXMLParse(notifyXml);
			WxNotify wn = DBUtil.convertMapToObject(map, WxNotify.class);
			if (null != wn && wn.isPayOk()) {// 判断是否已支付成功
				int total = Integer.parseInt(wn.getTotal_fee());// 支付总价,以分为单位

				System.out.println("附加信息:"+wn.getAttach());
				Orderinfo order = getOrderByNo(wn.getOut_trade_no());// 查询订单信息
				if (null != order) {
					return true;//已经支付过了.
				}
				
				
				order = new Orderinfo();
				order.setOrderNo(wn.getOut_trade_no());
				order.setOrderTime(new Date());
				order.setOrderType(1);
				order.setPayType("wx_mp_pay");
				order.setStatus(1);
				order.setTotalPrice(Float.valueOf(total*100));
				order.setUserId(1);
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Orderinfo getOrderByNo(String orderNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
