package com.hy.racing.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.core.base.ResultBaseController;
import com.hy.racing.car.services.ICarManager;
import com.hy.racing.entity.Carinfo;
import com.hy.utils.json.JsonUtil;
@Controller
@RequestMapping("/car")
public class CarinfoController extends ResultBaseController {
	@Autowired
	private ICarManager carServices;
	
	@RequestMapping("/addCar")
	@ResponseBody
	public Object addCar(Carinfo car){
		return setResultMap(carServices.addCarinfo(car),false);
	}
	
	@RequestMapping("/getMyCarList")
	@ResponseBody
	public Object getMyCarList(@RequestParam("uid")Integer uid){
		return setResultMap(carServices.getAll(uid),false);
	}
	
	@RequestMapping("/findCarByTel")
	@ResponseBody
	public Object findCarByTel(String tel){
		return setResultMap(carServices.findCarByTel(tel),false);
	}
	
	@RequestMapping("/updateCar")
	@ResponseBody
	public Object updateCar(Carinfo car){
		return setResultMap(carServices.updateCarinfo(car),false);
	}
}
