package com.hy.racing.order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.core.base.ResultBaseController;
import com.hy.core.base.ResultInfo;
import com.hy.racing.entity.Userinfo;
import com.hy.racing.userinfo.services.IUserInfoServices;
import com.hy.utils.order.AddOrderBean;
import com.hy.utils.order.OrderUtils;
import com.hy.utils.pay.wx.PayCommonUtil;

@Controller
@RequestMapping("/order")
public class OrderController extends ResultBaseController {
	@Autowired
	IUserInfoServices userServices;

	@RequestMapping("/getWXPayConfig")
	@ResponseBody
	public Map<String, Object> getWXPayConfig(HttpServletRequest request, Integer uid)
			throws JDOMException, IOException {
		Userinfo user = userServices.getUserById(uid);
		if (user.getId().intValue() == 0) {
			return setResultMap(ResultInfo.LOGIN_NOT_FOUND);
		}

		AddOrderBean orderBean = new AddOrderBean();
		orderBean.setOpenid(user.getOpenid());
		orderBean.setAttach(uid+"");
		orderBean.setOrderDesc("");
		
		// 调用微信统一下单接口
		SortedMap<String, Object> params = OrderUtils.wxUnifyAddOrder(request, orderBean);
		return params;
	}
	
	@RequestMapping("/wxnotify")
	@ResponseBody
	public String wxnotify(HttpServletRequest request) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

//			if (orderBiz.updateWxPaySuccess(sb.toString())) {
//				return PayCommonUtil.setXML("SUCCESS", "OK");
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return PayCommonUtil.setXML("FAIL", "验证失败");
	}
	
	@RequestMapping("/wxsuccess")
	public String wxsuccess(HttpServletRequest request) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4ebd19d51022b37b&redirect_uri=http%3A%2F%2Fc.eeout.com%2Fphone%2Flogin%2Fauth&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
		return "redirect:"+url;
	}
}
