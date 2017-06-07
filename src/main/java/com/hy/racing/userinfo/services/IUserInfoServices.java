package com.hy.racing.userinfo.services;

import java.util.List;

import com.hy.racing.entity.Userinfo;

public interface IUserInfoServices {
	Userinfo getUserById(Integer id);
	Userinfo getUserByOpenid(String openid);
	Userinfo getUserByOpenidFromWx(String openid);
	Userinfo getUserByUnionid(String unionid);
	int addUserinfo(Userinfo user);
	
	List<Userinfo> findUser(Userinfo user);
	
	void getUserGrade(Userinfo user);
	
	int updateUserinfo(Userinfo user);
	
	void updateUserUnionid(Integer id,String unionid);
}
