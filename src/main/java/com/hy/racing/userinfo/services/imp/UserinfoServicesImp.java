package com.hy.racing.userinfo.services.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.core.base.ResultInfo;
import com.hy.core.services.BaseServices;
import com.hy.racing.entity.Userinfo;
import com.hy.racing.game.bean.GameRankBean;
import com.hy.racing.game.services.IGameServices;
import com.hy.racing.userinfo.services.IUserInfoServices;
import com.hy.utils.date.DateUtil;

@Service
public class UserinfoServicesImp extends BaseServices implements IUserInfoServices {
	@Autowired
	private IGameServices gameServices;

	@Override
	public Userinfo getUserById(Integer id) {
		String hql = "from Userinfo where id=?";
		Userinfo user = hqlDao.findObj(hql, id);
		if (null == user) {
			user = new Userinfo();
			user.setId(0);
		}
		return user;
	}

	@Override
	public Userinfo getUserByOpenid(String openid) {
		String hql = "from Userinfo where openid=?";
		return hqlDao.findObj(hql, openid);
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

	@Override
	public void getUserGrade(Userinfo user) {
		String sql = "select MIN(speed) speed from gameinfo a join carinfo b on a.car_id = b.id join userinfo c on b.user_id = c.id where c.id = "
				+ user.getId();
		Map<String, Object> map = sqlDao.findObjToMap(sql);
		user.setBestTime(map.get("speed") == null ? "00'00\"000" : map.get("speed") + "");
		user.setRanking(0);
		List<GameRankBean> rankList = gameServices.findCarRank();
		if (null != rankList && rankList.size() > 0) {
			for (int i = 0; i < rankList.size(); i++) {
				GameRankBean gameRankBean = rankList.get(i);
				if (gameRankBean.getUid().intValue() == user.getId().intValue()) {
					user.setRanking(i + 1);
					break;
				}
			}
		}
	}

}
