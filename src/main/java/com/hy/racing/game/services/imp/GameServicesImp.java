package com.hy.racing.game.services.imp;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hy.core.services.BaseServices;
import com.hy.racing.entity.Gameinfo;
import com.hy.racing.game.services.IGameServices;
import com.hy.utils.date.DateUtil;
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
		// TODO Auto-generated method stub
		return null;
	}

}
