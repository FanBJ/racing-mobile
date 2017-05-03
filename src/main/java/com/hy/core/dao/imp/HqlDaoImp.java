package com.hy.core.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hy.core.dao.IHqlDao;


@SuppressWarnings("unchecked")
@Repository
public class HqlDaoImp implements IHqlDao {
	private Object[] null_obj = null;// 空参数
	private Query query;
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <E> E findObj(String str, Object param) {
		if (param == null) return findObj(str, null_obj);
		return findObj(str, new Object[] { param });
	}

	@Override
	public <E> E findObj(String str, Object[] params) {
		Query query = getSession().createQuery(str);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setMaxResults(1);
		return (E) query.uniqueResult();
	}

	@Override
	public <E> E findObjByNamed(String name, Object[] params) {
		Query query = getSession().getNamedQuery(name);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setMaxResults(1);
		return (E) query.uniqueResult();
	}

	@Override
	public <E> E findObjByNamed(String name, Object param) {
		if (null == param) return findObjByNamed(name, null_obj);
		return findObjByNamed(name, new Object[] { param });
	}

	@Override
	public int insertOrUpdateOrDel(String str, Object param) {
		if (null == param) return insertOrUpdateOrDel(str, null_obj);
		return insertOrUpdateOrDel(str, new Object[] { param });
	}

	@Override
	public int insertOrUpdateOrDel(String str, Object[] params) {
		Query query = getSession().createQuery(str);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}

	@Override
	public int insertOrUpdateOrDelByNamed(String name, Object[] params) {
		Query query = getSession().getNamedQuery(name);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}

	@Override
	public int insertOrUpdateOrDelByNamed(String name, Object param) {
		if (null == param) return insertOrUpdateOrDelByNamed(name, null_obj);
		return insertOrUpdateOrDelByNamed(name, new Object[] { param });
	}

	@Override
	public <E> List<E> find(String str) {
		return find(str, 0, 0, null_obj);
	}

	@Override
	public <E> List<E> find(String str, Object param) {
		if (param == null)
			return find(str, 0, 0, null_obj);
		else return find(str, 0, 0, new Object[] { param });
	}

	@Override
	public <E> List<E> find(String str, Object[] params) {
		return find(str, 0, 0, params);
	}

	@Override
	public <E> List<E> find(String str, int page, int rows, Object[] params) {
		Query query = getSession().createQuery(str);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		if (rows > 0 && page > 0) {
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	@Override
	public <E> List<E> find(String str, int page, int rows, Object param) {
		if (param == null) return find(str, page, rows, null_obj);
		return find(str, page, rows, new Object[] { param });
	}

	@Override
	public Criteria getCriteria(Class<?> c) {
		return getSession().createCriteria(c);
	}

	@Override
	public <E> E get(Class<E> c, Serializable id) {
		return (E) getSession().get(c, id);
	}

	@Override
	public Serializable addObj(Object obj) {
		return getSession().save(obj);
	}

	@Override
	public void updateObj(Object obj) {
		getSession().update(obj);
	}

	@Override
	public void delObj(Object obj) {
		getSession().delete(obj);
	}

	@Override
	public <E> List<E> find(E e) {
		return find(e, 0, 0, null, null);
	}

	@Override
	public <E> List<E> find(E e, boolean isDesc, String orderProperty) {
		return find(e, 0, 0, new boolean[] { isDesc }, new String[] { orderProperty });
	}

	@Override
	public <E> List<E> find(E e, boolean[] isDesc, String[] orderProperty) {
		return find(e, 0, 0, isDesc, orderProperty);
	}

	@Override
	public <E> List<E> find(E e, int page, int rows) {
		return find(e, page, rows, null, null);
	}

	@Override
	public <E> List<E> find(E e, int page, int rows, boolean isDesc, String orderProperty) {
		return find(e, page, rows, new boolean[] { isDesc }, new String[] { orderProperty });
	}

	@Override
	public <E> List<E> find(E e, int page, int rows, boolean[] isDesc, String[] orderProperty) {
		Criteria c = getCriteria(e.getClass());
		if (page > 0 && rows > 0) {
			c.setFirstResult((page - 1) * rows);
			c.setMaxResults(rows);
		}
		if (!ArrayUtils.isEmpty(isDesc) && !ArrayUtils.isEmpty(orderProperty) && isDesc.length == orderProperty.length) {
			for (int i = 0; i < isDesc.length; i++) {
				if (isDesc[i]) {
					c.addOrder(Order.desc(orderProperty[i]));
				} else {
					c.addOrder(Order.asc(orderProperty[i]));
				}
			}
		}
		Example ex = Example.create(e);
		ex.enableLike(MatchMode.ANYWHERE);// 匹配前后模糊查询
		ex.excludeNone();// 空与null不做查询条件
		ex.ignoreCase();// 忽略大小写
		ex.excludeZeroes();// 忽略0
		c.add(ex);
		return c.list();
	}

	@Override
	public <E> List<E> findByNamed(String name, int page, int rows, Object[] params) {
		Query query = getSession().getNamedQuery(name);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		if (rows > 0 && page > 0) {
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
		}
		return query.list();
	}

	@Override
	public <E> List<E> findByNamed(String name, int page, int rows, Object param) {
		return findByNamed(name, page, rows, new Object[] { param });
	}

	@Override
	public <E> List<E> findByNamed(String name) {
		return findByNamed(name, 0, 0, null_obj);
	}

	@Override
	public <E> List<E> findByNamed(String name, Object param) {
		if (null == param) return findByNamed(name, 0, 0, null_obj);
		return findByNamed(name, 0, 0, new Object[] { param });
	}

	@Override
	public <E> List<E> findByNamed(String name, Object[] params) {
		return findByNamed(name, 0, 0, params);
	}

	@Override
	public <E> E findObj(String str) {
		return findObj(str, null_obj);
	}

	@Override
	public <E> E findObjByNamed(String name) {
		return findObjByNamed(name, null_obj);
	}

	@Override
	public <E> List<E> find(String str, int page, int rows) {
		return find(str, page, rows, null_obj);
	}

	@Override
	public <E> List<E> findByNamed(String name, int page, int rows) {
		return findByNamed(name, page, rows, null_obj);
	}

	@Override
	public int insertOrUpdateOrDel(String str) {
		return insertOrUpdateOrDel(str, null_obj);
	}

	@Override
	public int insertOrUpdateOrDelByNamed(String name) {
		return insertOrUpdateOrDelByNamed(name, null_obj);
	}

	@Override
	public int listCount(String hql) {
		return listCount(hql, null_obj);
	}

	@Override
	public int listCount(String hql, Object param) {
		if (null == param) return listCount(hql, null_obj);
		return listCount(hql, new Object[] { param });
	}

	@Override
	public int listCount(String hql, Object[] params) {
		Object obj = findObj("select count(*) " + hql.substring(hql.indexOf("from")), params);
		return null != obj ? Integer.parseInt(obj + "") : 0;
	}

	@Override
	public int listCount(Object e) {
		Criteria c = getCriteria(e.getClass());

		Example ex = Example.create(e);
		ex.enableLike(MatchMode.ANYWHERE);// 匹配前后模糊查询
		ex.excludeNone();// 空与null不做查询条件
		ex.ignoreCase();// 忽略大小写
		ex.excludeZeroes();// 忽略0
		c.add(ex);

		c.setProjection(Projections.rowCount());

		return ((Long) c.uniqueResult()).intValue();
	}

	@Override
	public int listCountByNamedQuery(String name) {
		return listCountByNamedQuery(name,null);
	}

	@Override
	public int listCountByNamedQuery(String name, Object[] params) {
		List<Object> list = findByNamedQuery(name, params);
		return null != list ? list.size() : 0;
	}
	
	@Override
	public int listCountByNamedQuery(String name, Object param) {
		return listCountByNamedQuery(name, new Object[] { param });
	}

	public <E> List<E> findByNamedQuery(String name, Object[] params) {
		return findByNamed(name, params);
	}

}
