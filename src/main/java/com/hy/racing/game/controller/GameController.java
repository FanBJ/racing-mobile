package com.hy.racing.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.core.base.ResultBaseController;
import com.hy.racing.entity.Gameinfo;
import com.hy.racing.game.services.IGameServices;

@Controller
@RequestMapping("/game")
public class GameController extends ResultBaseController {
	@Autowired
	private IGameServices gameServices;

	@RequestMapping("/addGameLog")
	@ResponseBody
	public boolean addGameLog(Gameinfo game) {
		return gameServices.addGrade(game);
	}
}
