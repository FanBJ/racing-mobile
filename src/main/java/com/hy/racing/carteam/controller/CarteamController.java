package com.hy.racing.carteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.core.base.ResultBaseController;
import com.hy.racing.carteam.services.ICarteamServices;
@Controller
@RequestMapping("/carteam")
public class CarteamController extends ResultBaseController {
	@Autowired
	private ICarteamServices carteamServices;
	
	@RequestMapping("/getAllTeam")
	@ResponseBody
	public Object getAllTeam(){
		return setResultMap(carteamServices.getAllTeam(),false);
	}
}
