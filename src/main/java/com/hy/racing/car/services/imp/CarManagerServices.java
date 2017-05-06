package com.hy.racing.car.services.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.core.base.ResultInfo;
import com.hy.core.services.BaseServices;
import com.hy.racing.car.services.ICarManager;
import com.hy.racing.carteam.services.ICarteamServices;
import com.hy.racing.entity.Carinfo;
import com.hy.racing.entity.Carteam;
import com.hy.utils.cache.CacheUtils;
import com.hy.utils.date.DateUtil;
import com.hy.utils.db.DBUtil;

@Service
public class CarManagerServices extends BaseServices implements ICarManager {
	@Autowired
	private ICarteamServices carteamServices;

	@Override
	public int addCarinfo(Carinfo car) {
		if (null == car || StringUtils.isBlank(car.getCode())) {
			// 没有数据
			return ResultInfo.ADD_CAR_CODE_DATA_ERROR;
		}

		int hasCarCode = isExistCarByCode(car.getCode().toUpperCase());
		if (hasCarCode > 0) {
			// 车牌已存在
			return ResultInfo.ADD_CAR_CODE_EXIST;
		}

		if (null != car.getTeamCode()) {
			Carteam team = carteamServices.getCarteamByCode(car.getTeamCode().toLowerCase());
			if (team==null) {
				// 车队编号没有找到
				return ResultInfo.ADD_CAR_TEAMCODE_NOT_EXIST;
			}
			car.setTeamId(team.getId());
		} else {
			// 添加默认车队
			car.setTeamId(Integer.valueOf(CacheUtils.getSysParamVal("default_carteam")));
		}

		car.setRegtime(DateUtil.nowDate());
		car.setStatus(1);
		// 添加
		return addObj(car) ? ResultInfo.COMMON_ADD_SUC : ResultInfo.COMMON_ADD_FAIL;
	}

	@Override
	public int delCarinfo(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCarinfo(Carinfo car) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Carinfo> getAll(Integer uid) {
		List<Carinfo> carList = hqlDao.find("from Carinfo where uid=" + uid);
		if (null != carList) {
			String sql = "select MAX(logtime) 'newGrade',MIN(speed) 'bestGrade' from gameinfo where car_id=?";

			for (Carinfo car : carList) {
				Map<String, Object> map = sqlDao.findObjToMap(sql, car.getId());
				car.setNewGrade((String) map.get("newGrade"));
				car.setBestGrade((String) map.get("bestGrade"));
			}
		}
		return carList;
	}

	@Override
	public int isExistCarByCode(String code) {
		String sql = "from carinfo where code = ?";
		return sqlDao.listCount(sql, code);
	}

	@Override
	public List<Carinfo> findCarByTel(String tel) {
		String sql = "select a.*,b.username,b.tel,c.`name` groupname,d.`name` teamname from carinfo a join userinfo b on a.user_id = b.id join cargroup c on a.cargroup_id = c.id join carteam d on d.id = a.team_id where 1=1";
		if (!StringUtils.isBlank(tel)) {
			sql += " and b.tel =?";
			return DBUtil.converListMapToListObj(sqlDao.findToMap(sql, tel), Carinfo.class);
		}
		return DBUtil.converListMapToListObj(sqlDao.findToMap(sql), Carinfo.class);
	}

}
