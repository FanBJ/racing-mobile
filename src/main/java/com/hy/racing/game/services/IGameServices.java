package com.hy.racing.game.services;

import java.util.List;

import com.hy.racing.entity.Gameinfo;

public interface IGameServices {
	List<Gameinfo> getAllLogByCarId(Integer carId);
	boolean addGrade(Gameinfo game);
	
	/**
	 * 组别排行
	 * @param groupId
	 * @return
	 */
	List<Gameinfo> findGameLogByGroupId(Integer groupId);
	/**
	 * 总排行
	 * @return
	 */
	List<Gameinfo> getTotalRank();
}
