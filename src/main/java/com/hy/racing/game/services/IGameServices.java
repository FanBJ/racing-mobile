package com.hy.racing.game.services;

import java.util.List;

import com.hy.racing.entity.Gameinfo;

public interface IGameServices {
	List<Gameinfo> getAllLogByCarId(Integer carId);
	boolean addGrade(Gameinfo game);
}
