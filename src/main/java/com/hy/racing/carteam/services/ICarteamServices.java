package com.hy.racing.carteam.services;

import java.util.List;

import com.hy.racing.entity.Carteam;

public interface ICarteamServices {
	public Carteam getCarteamByCode(String code);
	public boolean isExistCarteamByCode(String code);
	
	public List<Carteam> getAllTeam();
}
