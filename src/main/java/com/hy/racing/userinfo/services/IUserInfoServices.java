package com.hy.racing.userinfo.services;

import com.hy.racing.entity.Userinfo;

public interface IUserInfoServices {
	Userinfo getUserById(Integer id);
	Userinfo getUserByOpenid(String openid);
	int addUserinfo(Userinfo user);
}
