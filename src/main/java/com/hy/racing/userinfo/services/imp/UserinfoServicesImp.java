package com.hy.racing.userinfo.services.imp;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hy.core.base.ResultInfo;
import com.hy.core.services.BaseServices;
import com.hy.racing.entity.Userinfo;
import com.hy.racing.userinfo.services.IUserInfoServices;
import com.hy.utils.date.DateUtil;

@Service
public class UserinfoServicesImp extends BaseServices implements IUserInfoServices {

	@Override
	public Userinfo getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Userinfo getUserByOpenid(String openid) {
		String hql = "from Userinfo where openid=?";
		return hqlDao.findObj(hql,openid);
	}

	@Override
	public int addUserinfo(Userinfo user) {
		Userinfo u = getUserByOpenid(user.getOpenid());
		if (null != u) {
			return ResultInfo.REG_TEL_EXIST;
		}
		user.setStatus(1);
		user.setRegtime(DateUtil.nowDate());
		boolean bool = addObj(user);
		return bool ? ResultInfo.REG_OK : ResultInfo.COMMON_ADD_FAIL;
	}

	@Override
	public List<Userinfo> findUser(Userinfo user) {
		return hqlDao.find(user);
	}

}
