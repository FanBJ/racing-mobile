package com.hy.racing.userinfo.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.core.base.ResultBaseController;
import com.hy.racing.entity.Userinfo;
import com.hy.racing.userinfo.services.IUserInfoServices;

@Controller
@RequestMapping("/user")
public class UserinfoController extends ResultBaseController {
	@Autowired
	private IUserInfoServices userServices;

	@RequestMapping("/getUserinfoById")
	@ResponseBody
	public Object getUserinfo(@RequestParam("uid") Integer userId, HttpServletRequest request) {
		Userinfo user = new Userinfo();
		user.setId(userId);
		user.setBestTime("01'12\"11");
		user.setCity("贵阳");
		user.setCountry("中国");
		user.setHeadimgurl("http://picture.eeout.com/goods/comb/1469180899520.png");
		user.setNickname("我是昵称");
		user.setOpenid("123asdlkhf23o9dfgu");
		user.setProvince("贵州");
		user.setRanking(10);
		user.setUserID("522411456897721132145");
		user.setSex("1");
		user.setSubscribe(1);
		user.setTel("18984377070");
		user.setUsername("张三");
		return setResultMap(user, false);
	}

	@RequestMapping("/regUser")
	@ResponseBody
	public Object regUser(Userinfo user) {
		return setResultMap(user, userServices.addUserinfo(user), false);
	}

	@RequestMapping("/findUser")
	@ResponseBody
	public Object findUser(Userinfo user) {
		return userServices.findUser(user);
	}
}
