package com.hy.racing.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hy.core.base.ResultBaseController;
import com.hy.racing.entity.Userinfo;
import com.hy.racing.userinfo.services.IUserInfoServices;
import com.hy.utils.CarUtils;
import com.hy.utils.http.HttpUtil;
import com.hy.utils.pay.wx.CommonUtil;
import com.hy.utils.pay.wx.Token;
import com.hy.utils.pay.wx.WxUserinfo;

@Controller
@RequestMapping("/login")
public class LoginController extends ResultBaseController {
	@Autowired
	private IUserInfoServices userinfoServices;
	private static Map<String, String> redirectMap = new HashMap<>();
	static {
		redirectMap.put("1", "http://x3.logacg.com");
		redirectMap.put("2", "http://x3.logacg.com/#/rank");
	}

	/**
	 * 获取用户信息,并且转向
	 * 
	 * @return
	 */
	@RequestMapping("/auth")
	public Object auth(HttpServletRequest request, String code, String state) {
		Token token = CommonUtil.getToken(code, state);
		if (null == token || token.getOpenid() == null) {
			return "error";
		}

		Userinfo user = null;
		try {
			//调用微信,获取微信用户信息
			WxUserinfo wx = CommonUtil.getWxUserinfo(token.getAccess_token(), token.getOpenid());
			//通过unionid查询数据库用户
			user = userinfoServices.getUserByUnionid(wx.getUnionid());
			
			if(null==user){
				user = userinfoServices.getUserByOpenid(wx.getOpenid());
				
				if(user != null){
					userinfoServices.updateUserUnionid(user.getId(), wx.getUnionid());
				}
			}
			if (user == null) {//如果用户不存在
				user = new Userinfo();
				user.setId(0);
			} else {
				//查询排名信息
				userinfoServices.getUserGrade(user);
			}

			BeanUtils.copyProperties(wx, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String url = redirectMap.get(state);
		if (null == url) {
			url = redirectMap.get("1");
		}
		String redirectUrl = "redirect:" + url + "?uid=" + user.getId() + "&openid=" + user.getOpenid() + "&unionid="
				+ user.getUnionid() + "&username=" + HttpUtil.encode(user.getUsername()) + "&sex=" + user.getSex()
				+ "&area=" + user.getArea() + "&nickname=" + HttpUtil.encode(user.getNickname()) + "&city="
				+ HttpUtil.encode(user.getCity()) + "&province=" + HttpUtil.encode(user.getProvince()) + "&country="
				+ HttpUtil.encode(user.getCountry()) + "&headimgurl=" + HttpUtil.encode(user.getHeadimgurl())
				+ "&ranking=" + user.getRanking() + "&bestTime=" + CarUtils.getShowTime(user.getBestTime())+ "&from=B";
//		if (StringUtils.isBlank(state) || state.equals("1")) {
//			redirectUrl += "&from=A";
//		} else {
//			redirectUrl += "&from=B";
//		}
		return redirectUrl;
	}
}
