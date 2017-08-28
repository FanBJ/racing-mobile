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
		List<Cargroup> result =hqlDao.find("from Cargroup where status=1");
		if(null!=result){
			Cargroup cg = new Cargroup();
			cg.setDescription("女子组");
			cg.setName("女子组");
			cg.setId(GIRL_GROUP_ID);
			result.add(0, cg);
		}
		return result;
	}

}
