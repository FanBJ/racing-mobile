package com.hy.core.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.hy.core.dao.IHqlDao;
import com.hy.core.dao.ISqlDao;

/**
 * <pre>
 * 基础业务类
 * 公共接口实现、成员变量声明等
 * </pre>
 * 
 * @author FanBingjiang
 */
public class BaseServices {

	/**
	 * jdbc查询接口
	 */
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	/**
	 * HQL查询接口
	 */
	@Autowired
	protected IHqlDao hqlDao;

	/**
	 * SQL查询接口
	 */
	@Autowired
	protected ISqlDao sqlDao;

	protected boolean addObj(Serializable obj) {
		Assert.notNull(obj);
		return hqlDao.addObj(obj) != null;
	}

	protected void updateObj(Serializable obj) {
		Assert.notNull(obj);
		hqlDao.updateObj(obj);
	}

	protected void delObj(Serializable obj) {
		Assert.notNull(obj);
		hqlDao.delObj(obj);
	}

	protected <E> E getObj(Class<E> c, Serializable id) {
		Assert.notNull(id);
		return hqlDao.get(c, id);
	}
	protected Integer getCount(String sqlCount) {
		Assert.notNull(sqlCount);
		return jdbcTemplate.queryForInt(sqlCount);
	}
	protected Integer getCount(String sqlCount, Object[] args) {
		Assert.notNull(sqlCount);
		return jdbcTemplate.queryForInt(sqlCount, args);
	}
	protected Integer getCount(String sqlCount, Object args) {
		Assert.notNull(sqlCount);
		return jdbcTemplate.queryForInt(sqlCount, args);
	}
}
