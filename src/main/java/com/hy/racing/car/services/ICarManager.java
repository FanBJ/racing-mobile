package com.hy.racing.car.services;

import java.util.List;

import com.hy.racing.entity.Carinfo;

public interface ICarManager {
	int addCarinfo(Carinfo car);
	int delCarinfo(Integer id);
	int updateCarinfo(Carinfo car);
	List<Carinfo> getAll(Integer uid);
}
