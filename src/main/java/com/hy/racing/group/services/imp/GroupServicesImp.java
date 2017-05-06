package com.hy.racing.group.services.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hy.core.services.BaseServices;
import com.hy.racing.entity.Cargroup;
import com.hy.racing.group.services.IGroupServices;
@Service
public class GroupServicesImp extends BaseServices implements IGroupServices {

	@Override
	public List<Cargroup> getAllGroup() {
		return hqlDao.find("from Cargroup where status=1");
	}

}
