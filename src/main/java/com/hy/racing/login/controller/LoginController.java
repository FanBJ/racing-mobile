package com.hy.racing.login.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.core.base.ResultBaseController;
import com.hy.racing.entity.Userinfo;
import com.hy.racing.userinfo.services.IUserInfoServices;
import com.hy.utils.CarUtils;
import com.hy.utils.http.HttpUtil;
import com.hy.utils.json.JsonUtil;
import com.hy.utils.pay.wx.CommonUtil;
import com.hy.utils.pay.wx.Token;
import com.hy.utils.pay.wx.WxUserinfo;

@Controller
@RequestMapping("/login")
public class LoginController extends ResultBaseController {
	@Autowired
	private IUserInfoServices userinfoServices;

	/**
	 * 获取用户信息,并且转向
	 * 
	 * @return
	 */
	@RequestMapping("/auth")
	public Object auth(HttpServletRequest request, String code, String state) {
		Token token = CommonUtil.getToken(code);
		if (null == token || token.getOpenid() == null) {
			return "error";
		}
		//System.out.println("token===" + JsonUtil.toJson(token));

		Userinfo user = userinfoServices.getUserByOpenid(token.getOpenid());
		if (user == null) {
			user = new Userinfo();
			user.setId(0);
		}else{
			userinfoServices.getUserGrade(user);
		}
		try {
			WxUserinfo wx = CommonUtil.getWxUserinfo(token.getAccess_token(), token.getOpenid());
			BeanUtils.copyProperties(wx, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:http://x3.logacg.com?uid=0&openid="+user.getOpenid()+"&username="
		+HttpUtil.encode(user.getUsername())+"&sex="+user.getSex()+"&area="+user.getArea()
		+"&nickname="+HttpUtil.encode(user.getNickname())+"&city="+HttpUtil.encode(user.getCity())
		+"&province="+HttpUtil.encode(user.getProvince())+"&country="+HttpUtil.encode(user.getCountry())
		+"&headimgurl="+HttpUtil.encode(user.getHeadimgurl())+"&ranking="+user.getRanking()+"&bestTime="+CarUtils.getShowTime(user.getBestTime());
	}
}
