package com.hy.racing.userinfo.services;

import java.util.List;

import com.hy.racing.entity.Userinfo;

public interface IUserInfoServices {
	Userinfo getUserById(Integer id);
	Userinfo getUserByOpenid(String openid);
	int addUserinfo(Userinfo user);
	
	List<Userinfo> findUser(Userinfo user);
}
