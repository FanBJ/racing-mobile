package com.hy.core.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.hy.racing.login.bean.UserInfo;
import com.hy.racing.userinfo.services.IUserInfoServices;
import com.hy.utils.http.HttpUtil;
import com.hy.utils.json.JsonUtil;
import com.hy.utils.security.Des3;

/**
 * 安全过滤器，在访问到接口之前截拦下来判断是否允许访问
 * 
 * @author FBJ
 * 
 */
@WebFilter(asyncSupported = true)
public class SecurityFilter implements Filter {
	private Logger log = Logger.getLogger(SecurityFilter.class);
	private IUserInfoServices usersService;
   private String [] ports={"main","goods","search","sys","addJPush"};
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		/*
		 * 
		 * 来源判断，防止恶意访问
		 */
		String params = req.getParameter("params");

		if (null != params && params.trim().length() > 0) {
			// 最终请求参数
			HashMap<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
			try {
				// 解密所有参数
				final String deParams = Des3.decode(params.trim());

				m.putAll(HttpUtil.deLinkString(deParams));// 把解密之后的参数加入到请求中
				// 验证用户
				if (m.get("uId")!=null&&m.get("uId")[0] != null&&!"".equals(m.get("uId")[0])&&!"0".equals(m.get("uId")[0])) {
					boolean isverify=true;
					String requestURI = req.getRequestURI();
					String[] split = requestURI.split("/");
					for (int i = 0; i < split.length; i++) {
						for (String port : ports) {
							String item = split[i];
							if (item.equals(port)) {
								isverify=false;
								break;
							}
						}
						
					}
					
					if (isverify) {
						//验证未通过
						if (!verifyLogon(req, res, m)) {
							return;
						}
					}
					
				}

				ParameterRequestWrapper wrapRequest = new ParameterRequestWrapper(req, m);
				filterChain.doFilter(wrapRequest, res);
			} catch (Exception e) {
				e.printStackTrace();
				// 请求出错，考虑是否加入处理机制
			}
		} else {
			//
			filterChain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// 可初始化日志对象等
	}
    private boolean verifyLogon(HttpServletRequest req,HttpServletResponse res,HashMap<String, String[]> m) throws Exception {
    	ServletContext sc = req.getSession().getServletContext();
		XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
		if (cxt != null&& cxt.getBean("UserInfoServicesImp") != null&& usersService == null)
			usersService = (IUserInfoServices) cxt.getBean("UserInfoServicesImp");
		String uids = m.get("uId")[0];
		UserInfo userById = usersService.getUserById(Integer.valueOf(uids));
		if (userById == null) {
			res.sendRedirect("/phone/user/LogonFailure");
			return false;
		}
		return true;
    }

}
