package com.hy.core.dao;

import java.util.List;

/**
 * 基础数据访问层
 * 
 * @author FanBingjiang
 */
public interface IBaseDao {
	/**
	 * 查询一条记录
	 * 
	 * @param str
	 *            查询语句
	 * @param params
	 *            参数列表
	 * @return
	 */
	public <E> E findObj(String str, Object[] params);

	public <E> E findObj(String str, Object params);

	public <E> E findObj(String str);

	/**
	 * 通过命名查询查询一条记录
	 * 
	 * @param name
	 *            名称
	 * @param params
	 *            参数列表
	 * @return
	 */
	public <E> E findObjByNamed(String name, Object[] params);

	public <E> E findObjByNamed(String name, Object params);

	public <E> E findObjByNamed(String name);

	/**
	 * 增、删、改通用的方法
	 * 
	 * @param str
	 *            语句
	 * @param params
	 *            参数列表
	 * @return 成功影响行数
	 */
	public int insertOrUpdateOrDel(String str, Object param);

	public int insertOrUpdateOrDel(String str, Object[] param);

	public int insertOrUpdateOrDel(String str);

	/**
	 * 通过命名查询，增、删、改通用的方法
	 * 
	 * @param name
	 *            名称
	 * @param params
	 *            参数列表
	 * @return 成功影响行数
	 */
	public int insertOrUpdateOrDelByNamed(String name, Object param);

	public int insertOrUpdateOrDelByNamed(String name);

	public int insertOrUpdateOrDelByNamed(String name, Object[] params);

	/**
	 * 查询数据集
	 * 
	 * @param str
	 *            语句
	 * @return
	 */
	public <E> List<E> find(String str);

	/**
	 * 查询数据集
	 * 
	 * @param str
	 *            语句
	 * @param param
	 *            参数
	 * @return
	 */
	public <E> List<E> find(String str, Object param);

	/**
	 * 查询数据集
	 * 
	 * @param str
	 *            语句
	 * @param params
	 *            参数列表
	 * @return
	 */
	public <E> List<E> find(String str, Object[] params);

	/**
	 * 查询数据集
	 * 
	 * @param str
	 *            语句
	 * @param start
	 *            开始条数位置，第一条填0
	 * @param length
	 *            每页显示的多少行记录
	 * @param params
	 *            参数列表
	 * @return
	 */
	public <E> List<E> find(String str, int start, int length, Object[] params);

	public <E> List<E> find(String str, int start, int length, Object param);

	public <E> List<E> find(String str, int start, int length);

	/**
	 * 通过命名查询数据集
	 * 
	 * @param name
	 *            命名
	 * @param start
	 *            开始条数位置，第一条填0
	 * @param length
	 *            每页显示的多少行记录
	 * @param params
	 *            参数列表
	 * @return
	 */
	public <E> List<E> findByNamed(String name, int start, int length, Object[] params);

	public <E> List<E> findByNamed(String name, int start, int length, Object params);

	public <E> List<E> findByNamed(String name, int start, int length);

	/**
	 * 查询数据集
	 * 
	 * @param name
	 *            语句
	 * @return
	 */
	public <E> List<E> findByNamed(String name);

	/**
	 * 通过命名查询数据集
	 * 
	 * @param name
	 *            命名
	 * @param param
	 *            参数
	 * @return
	 */
	public <E> List<E> findByNamed(String name, Object param);

	/**
	 * 通过命名查询数据集
	 * 
	 * @param name
	 *            命名
	 * @param params
	 *            参数列表
	 * @return
	 */
	public <E> List<E> findByNamed(String name, Object[] params);

	/**
	 * 命名查询数据总和,带参数
	 * 
	 * @param name
	 *            命名名称
	 * @param params
	 *            参数列表
	 * @return
	 */
	int listCountByNamedQuery(String name);

	int listCountByNamedQuery(String name, Object param);

	int listCountByNamedQuery(String name, Object[] params);

	/**
	 * 查询数据总行数
	 * 
	 * @param str 语句
	 * @param params 参数列表
	 * @return
	 */
	int listCount(String str, Object[] params);

	int listCount(String str);

	int listCount(String str, Object param);

}
