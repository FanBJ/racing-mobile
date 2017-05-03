package com.hy.core.servlet.cache;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.hy.core.services.cache.ICacheService;

public class CacheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(CacheServlet.class);
	@Autowired
	private ICacheService cacheServiceImp;

	private ServletConfig config;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		// 设置在Servlet可以通过 @Autowired装载Bean
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
		init();
	}

	@Override
	public void init() throws ServletException {
		log.info("缓存初始化开始");
		cacheServiceImp.loadDictInfo(config.getServletContext());
		
		cacheServiceImp.loadSysParam(config.getServletContext());
		log.info("缓存初始化完成");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 判断访问来源是否正常

		//重新加载字典
		log.info("缓存重新加载开始");
		cacheServiceImp.loadDictInfo(config.getServletContext());
		
		cacheServiceImp.loadSysParam(config.getServletContext());
		log.info("缓存重新加载完成");
		resp.getWriter().write("1");// 告知客户端已重新加载成功
	}

}
