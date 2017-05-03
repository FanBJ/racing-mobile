package com.hy.core.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;

/**
 * HQL
 * 
 * @author FanBingjiang
 */
public interface IHqlDao extends IBaseDao {
	/**
	 * 获取QBC查询对象<br/>
	 * 主要是针对一般查询无法满足业务需求时调来。<br/>
	 * 一般不建议直接调用该方法
	 * 
	 * @param c
	 * @return
	 */
	Criteria getCriteria(Class<?> c);

	/**
	 * 通过主键查询实体
	 * 
	 * @param c
	 *            实体类
	 * @param id
	 *            主键
	 * @return
	 */
	<E> E get(Class<E> c, Serializable id);

	/**
	 * 添加一条记录
	 * 
	 * @param obj
	 *            实体类
	 * @return
	 */
	Serializable addObj(Object obj);

	/**
	 * 更新一条记录 注:实体类的主键值一定要设置!
	 * 
	 * @param obj
	 *            实体类
	 * @return
	 */
	void updateObj(Object obj);

	/**
	 * 删除一条记录 注:非空值要填写,否则要报错
	 * 
	 * @param obj
	 * @return
	 */
	void delObj(Object obj);

	/**
	 * QBC模糊查询
	 * 
	 * @param e
	 *            实体类
	 * @return
	 */
	<E> List<E> find(E e);

	/**
	 * QBC模糊查询
	 * 
	 * @param e
	 *            实体类
	 * @param orderProperty
	 *            排序的属性名
	 * @param isDesc
	 *            是否倒序，true=desc,false=asc
	 * @return
	 */
	<E> List<E> find(E e, boolean isDesc, String orderProperty);

	/**
	 * QBC模糊查询
	 * 
	 * @param e
	 *            实体类
	 * @param orderProperty
	 *            排序的属性名
	 * @param isDesc
	 *            是否倒序，true=desc,false=asc
	 * @return
	 */
	<E> List<E> find(E e, boolean[] isDesc, String[] orderProperty);

	/**
	 * QBC模糊查询,带分页
	 * 
	 * @param e
	 *            实体类
	 * @param page
	 *            当前页码,注:不能小于1
	 * @param rows
	 *            每页显示记录条数,注:不能小于1
	 * @return
	 */
	<E> List<E> find(E e, int page, int rows);

	/**
	 * QBC模糊查询,带分页
	 * 
	 * @param e
	 *            实体类
	 * @param page
	 *            当前页码,注:不能小于1
	 * @param rows
	 *            每页显示记录条数,注:不能小于1
	 * @param orderProperty
	 *            排序的属性名
	 * @param isDesc
	 *            是否倒序，true=desc,false=asc
	 * @return
	 */
	<E> List<E> find(E e, int page, int rows, boolean isDesc, String orderProperty);

	/**
	 * QBC模糊查询,带分页
	 * 
	 * @param e
	 *            实体类
	 * @param page
	 *            当前页码,注:不能小于1
	 * @param rows
	 *            每页显示记录条数,注:不能小于1
	 * @param orderProperty
	 *            排序的属性名
	 * @param isDesc
	 *            是否倒序，true=desc,false=asc
	 * @return
	 */
	<E> List<E> find(E e, int page, int rows, boolean[] isDesc, String[] orderProperty);

	/**
	 * QBC查询数据总和
	 * 
	 * @param e
	 * @return
	 */
	int listCount(Object e);

	/**
	 * 命名查询数据总和
	 * 
	 * @param name
	 *            命名名称
	 * @return
	 */
	int listCountByNamedQuery(String name);

	/**
	 * 命名查询数据总和,带参数
	 * 
	 * @param name
	 *            命名名称
	 * @param params
	 *            参数列表
	 * @return
	 */
	int listCountByNamedQuery(String name, Object[] params);
}
