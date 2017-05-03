package com.hy.core.dao;

import java.util.List;
import java.util.Map;

/**
 * SQL
 * 
 * @author FanBingjiang
 */
public interface ISqlDao extends IBaseDao {
	/**
	 * 查询一条实体记录
	 * 
	 * @param sql
	 *            语句
	 * @param c
	 *            实体类Class对象
	 * @param params
	 *            参数列表
	 * @return
	 */
	<E> E findObj(String sql, Class<E> c, Object[] params);

	<E> E findObj(String sql, Class<E> c, Object param);

	<E> E findObj(String sql, Class<E> c);

	/**
	 * 查询一条实体记录
	 * 
	 * @param name
	 *            名称
	 * @param c
	 *            实体类Class对象
	 * @param params
	 *            参数列表
	 * @return
	 */
	<E> E findObjByNamed(String name, Class<E> c, Object[] params);

	<E> E findObjByNamed(String name, Class<E> c, Object param);

	<E> E findObjByNamed(String name, Class<E> c);

	/**
	 * 把查询出来的数据转换行Map对象
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数列表
	 * @return
	 */
	Map<String, Object> findObjToMap(String sql, Object[] params);

	Map<String, Object> findObjToMap(String sql, Object param);

	Map<String, Object> findObjToMap(String sql);

	/**
	 * 把命名查询出来的数据转换行Map对象
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数列表
	 * @return
	 */
	Map<String, Object> findObjToMapByNamed(String name, Object[] params);

	Map<String, Object> findObjToMapByNamed(String name, Object param);

	Map<String, Object> findObjToMapByNamed(String name);

	/**
	 * QBC查询
	 * 
	 * @param sql
	 *            语句
	 * @param c
	 *            实体类Class对象
	 * @param start
	 *            开始条数位置，第一条填0
	 * @param length
	 *            每页显示的数据行数
	 * @param params
	 *            参数列表
	 * @return
	 */
	<E> List<E> find(String sql, Class<E> c, int start, int length, Object[] params);

	<E> List<E> find(String sql, Class<E> c, int start, int length, Object param);

	<E> List<E> find(String sql, Class<E> c, int start, int length);

	<E> List<E> find(String sql, Class<E> c, Object[] params);

	<E> List<E> find(String sql, Class<E> c, Object param);

	<E> List<E> find(String str, Class<E> c);

	/**
	 * 查询,并且把数据转换成Map对象
	 * 
	 * @param sql
	 *            语句
	 * @param start
	 *            开始条数位置，第一条填0
	 * @param length
	 *            每页显示的数据行数
	 * @param params
	 *            参数列表
	 * @return
	 */
	List<Map<String, Object>> findToMap(String sql, int start, int length, Object[] params);

	List<Map<String, Object>> findToMap(String sql, int start, int length, Object param);

	List<Map<String, Object>> findToMap(String sql, int start, int length);

	List<Map<String, Object>> findToMap(String sql);

	List<Map<String, Object>> findToMap(String sql, Object param);

	List<Map<String, Object>> findToMap(String sql, Object[] params);

	/**
	 * 通过命名查询
	 * 
	 * @param name
	 *            名称
	 * @param start
	 *            开始条数位置，第一条填0
	 * @param length
	 *            每页显示的数据行数
	 * @param params
	 *            参数列表
	 * @return
	 */
	List<Map<String, Object>> findToMapByNamed(String name, int start, int length, Object[] params);

	List<Map<String, Object>> findToMapByNamed(String name, int start, int length, Object param);

	List<Map<String, Object>> findToMapByNamed(String name, int start, int length);

	List<Map<String, Object>> findToMapByNamed(String name, Object[] params);

	List<Map<String, Object>> findToMapByNamed(String name, Object param);

	List<Map<String, Object>> findToMapByNamed(String name);
}
