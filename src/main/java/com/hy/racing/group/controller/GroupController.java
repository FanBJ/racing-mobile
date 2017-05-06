package com.hy.racing.group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.core.base.ResultBaseController;
import com.hy.racing.entity.Carinfo;
import com.hy.racing.group.services.IGroupServices;
@Controller
@RequestMapping("/group")
public class GroupController extends ResultBaseController {
	@Autowired
	private IGroupServices groupServices;
	
	@RequestMapping("/getAllGroup")
	@ResponseBody
	public Object getAllGroup(){
		return setResultMap(groupServices.getAllGroup(),false);
	}
}
