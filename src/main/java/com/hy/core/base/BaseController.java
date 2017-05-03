package com.hy.core.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hy.utils.ip.IPUtils;


@Controller
@RequestMapping("/base")
public class BaseController {
	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
		return IPUtils.getRemoteHost(request);
	}
}
