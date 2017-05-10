package com.hy.racing.game.services.imp;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hy.core.services.BaseServices;
import com.hy.racing.entity.Gameinfo;
import com.hy.racing.game.bean.GameRankBean;
import com.hy.racing.game.services.IGameServices;
import com.hy.utils.cache.CacheUtils;
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
		if (null == game || game.getCarId() == null || game.getRoundId() == null
				|| StringUtils.isBlank(game.getSpeed())) {
			return false;
		}

		game.setStatus(1);
		game.setLogtime(DateUtil.nowDate());
		return addObj(game);
	}

	@Override
	public List<GameRankBean> findGameLogByGroupId(Integer groupId) {
		int team_game_show_rowcount = Integer.valueOf(CacheUtils.getSysParamVal("team_game_show_rowcount"));
		String sql = "select a.id 'gid',d.id 'uid',a.car_id 'cid',if(d.sex='1','男','女') 'sex',c.`name` 'groupname',b.displacement,d.username,e.`name` 'teamname',b.brand,b.cartype,min(a.speed) 'speed' from gameinfo a join carinfo b on a.car_id = b.id join cargroup c on c.id = b.cargroup_id join userinfo d on b.user_id = d.id join carteam e on b.team_id = e.id where c.id = ? group by a.car_id order by a.speed limit ?";
		return DBUtil.converListMapToListObj(sqlDao.findToMap(sql, new Object[] { groupId, team_game_show_rowcount }),
				GameRankBean.class);
	}

	@Override
	public List<Gameinfo> getTotalRank() {
		return null;
	}

	@Override
	public List<GameRankBean> findCarRank() {
		String sql = "select a.id 'gid',c.id 'uid',c.username,d.`name` 'groupname',e.`name` 'teamname',if(c.sex='1','男','女') 'sex',b.id 'cid',b.brand,b.cartype,b.displacement,MIN(speed) 'speed' from gameinfo a join carinfo b on a.car_id = b.id join userinfo c on b.user_id = c.id join cargroup d on d.id = b.cargroup_id join carteam e on b.team_id = e.id group by b.id order by speed";
		return DBUtil.converListMapToListObj(sqlDao.findToMap(sql), GameRankBean.class);
	}

}
