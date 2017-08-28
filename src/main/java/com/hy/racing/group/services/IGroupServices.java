package com.hy.racing.group.services;

import java.util.List;

import com.hy.racing.entity.Cargroup;

public interface IGroupServices {
	int GIRL_GROUP_ID = 10000000;
	int MAN_GROUP_ID = 10000001;

	public List<Cargroup> getAllGroup();
}
