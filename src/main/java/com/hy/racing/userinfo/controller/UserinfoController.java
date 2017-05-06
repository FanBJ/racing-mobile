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
		return setResultMap(userServices.getUserById(userId), false);
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
