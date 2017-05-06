package com.hy.racing.game.services.imp;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hy.core.services.BaseServices;
import com.hy.racing.entity.Gameinfo;
import com.hy.racing.game.bean.GameRankBean;
import com.hy.racing.game.services.IGameServices;
import com.hy.utils.date.DateUtil;
import com.hy.utils.db.DBUtil;
@Service
public class GameServicesImp extends BaseServices implements IGameServices {

	@Override
	public List<Gameinfo> getAllLogByCarId(Integer carId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addGrade(Gameinfo game) {
		if(null==game || game.getCarId()==null || game.getRoundId()==null || StringUtils.isBlank(game.getSpeed())){
			return false;
		}
		
		game.setStatus(1);
		game.setLogtime(DateUtil.nowDate());
		return addObj(game);
	}

	@Override
	public List<Gameinfo> findGameLogByGroupId(Integer groupId) {
		String sql = "select  a.* from gameinfo a join carinfo b on a.car_id=b.id where b.cargroup_id=?";
		return null;
	}

	@Override
	public List<Gameinfo> getTotalRank() {
		return null;
	}

	@Override
	public List<GameRankBean> findCarRank() {
		String sql = "select c.id 'uid',c.username,b.id 'cid',b.brand,b.cartype,b.displacement,MIN(speed) 'speed' from gameinfo a join carinfo b on a.car_id = b.id join userinfo c on b.user_id = c.id group by b.id order by speed";
		return DBUtil.converListMapToListObj(sqlDao.findToMap(sql), GameRankBean.class);
	}

}
