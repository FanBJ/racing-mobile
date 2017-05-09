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
import com.hy.utils.CarUtils;
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

		if (!StringUtils.isBlank(car.getTeamCode())) {
			Carteam team = carteamServices.getCarteamByCode(car.getTeamCode().toLowerCase());
			if (team == null) {
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
	public Carinfo updateCarinfo(Carinfo car) {
		Carinfo target = hqlDao.get(Carinfo.class, car.getId());
		target.setBrand(car.getBrand());
		target.setCargroupId(car.getCargroupId());
		target.setCartype(car.getCartype());
		target.setCode(car.getCode());
		target.setDisplacement(car.getDisplacement());
		target.setIschange(car.getIschange());
		target.setStatus(car.getStatus());
		target.setTeamId(car.getTeamId());
		updateObj(target);
		return target;
	}

	@Override
	public List<Carinfo> getAll(Integer uid) {
		List<Carinfo> carList = DBUtil.converListMapToListObj(sqlDao.findToMap(
				"select a.id,a.cargroup_id 'cargroupId',a.user_id 'uid',a.code,a.brand,a.cartype,a.displacement,a.ischange,b.name 'groupname' from carinfo a join cargroup b on a.cargroup_id = b.id where a.user_id="
						+ uid),
				Carinfo.class);
		if (null != carList) {
			String sql1 = "select MIN(speed) 'bestGrade' from gameinfo where car_id=?";
			String sql2 = "select speed 'newGrade' from gameinfo where car_id=? order by logtime desc limit 1";
			for (Carinfo car : carList) {
				Map<String, Object> map = sqlDao.findObjToMap(sql1, car.getId());
				if (null == map) {
					car.setBestGrade(CarUtils.getShowTime(null));
				} else if (map.get("bestGrade") == null) {
					car.setBestGrade(CarUtils.getShowTime(null));
				} else {
					car.setBestGrade(CarUtils.getShowTime((String) map.get("bestGrade")));
				}

				map = sqlDao.findObjToMap(sql2, car.getId());
				if (null == map) {
					car.setNewGrade(CarUtils.getShowTime(null));
				} else if (map.get("newGrade") == null) {
					car.setNewGrade(CarUtils.getShowTime(null));
				} else {
					car.setNewGrade(CarUtils.getShowTime((String) map.get("newGrade")));
				}
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
		List<Carinfo> carList = null;
		String sql = "select a.*,a.cargroup_id 'cargroupId',a.team_id 'teamId',b.username,b.tel,c.`name` groupname,d.`name` teamname from carinfo a join userinfo b on a.user_id = b.id join cargroup c on a.cargroup_id = c.id join carteam d on d.id = a.team_id where 1=1";
		if (!StringUtils.isBlank(tel)) {
			sql += " and b.tel =?";
			carList = DBUtil.converListMapToListObj(sqlDao.findToMap(sql, tel), Carinfo.class);
		}else{
			carList = DBUtil.converListMapToListObj(sqlDao.findToMap(sql), Carinfo.class);
		}
		
		if (null != carList && carList.size() > 0) {
			StringBuffer sf = new StringBuffer();
			for (int i = 0; i < carList.size(); i++) {
				sf.append(i != carList.size() - 1 ? carList.get(i).getId() + "," : carList.get(i).getId());
			}
			// 添加上场次数
			sql = "select a.id,count(b.id) 'runCount' from carinfo a left join gameinfo b on a.id = b.car_id where a.id in ("
					+ sf.toString() + ") group by a.id";
			List<Map<String, Object>> map = sqlDao.findToMap(sql);
			for (Map<String, Object> count : map) {
				for (Carinfo car : carList) {
					if (count.get("id").equals(car.getId())) {
						car.setRunCount(Integer.valueOf(count.get("runCount") + ""));
						break;
					}
				}
			}
		}

		return carList;
	}

}
