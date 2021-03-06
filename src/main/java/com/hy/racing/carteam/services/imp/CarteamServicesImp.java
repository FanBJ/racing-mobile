package com.hy.racing.carteam.services.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hy.core.services.BaseServices;
import com.hy.racing.carteam.services.ICarteamServices;
import com.hy.racing.entity.Carteam;
@Service
public class CarteamServicesImp extends BaseServices implements ICarteamServices {

	@Override
	public Carteam getCarteamByCode(String code) {
		String hql = "from Carteam where code=?";
		return hqlDao.findObj(hql,code);
	}

	@Override
	public boolean isExistCarteamByCode(String code) {
		String sql = "from carteam where code=?";
		return sqlDao.listCount(sql, code) == 1;
	}

	@Override
	public List<Carteam> getAllTeam() {
		return hqlDao.find("from Carteam");
	}

}
