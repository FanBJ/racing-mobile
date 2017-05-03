package com.hy.core.dao.imp;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hy.core.dao.ISqlDao;

@SuppressWarnings("unchecked")
@Repository
public class SqlDaoImp implements ISqlDao {
	private Object[] null_obj = null;// 空参数
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <E> E findObjByNamed(String name, Class<E> c, Object[] params) {
		SQLQuery query = (SQLQuery) getSession().getNamedQuery(name);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return (E) query.addEntity(c).uniqueResult();
	}

	@Override
	public <E> E findObjByNamed(String name, Class<E> c, Object param) {
		return findObjByNamed(name, c, new Object[] { param });
	}

	@Override
	public <E> E findObjByNamed(String name, Class<E> c) {
		return findObjByNamed(name, c, null_obj);
	}

	@Override
	public int insertOrUpdateOrDel(String str, Object[] params) {
		SQLQuery query = getSession().createSQLQuery(str);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}

	@Override
	public int insertOrUpdateOrDel(String str, Object param) {
		return insertOrUpdateOrDel(str, new Object[] { param });
	}

	@Override
	public int insertOrUpdateOrDel(String str) {
		return insertOrUpdateOrDel(str, null_obj);
	}

	@Override
	public int insertOrUpdateOrDelByNamed(String name, Object[] params) {
		SQLQuery query = (SQLQuery) getSession().getNamedQuery(name);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}

	@Override
	public int insertOrUpdateOrDelByNamed(String name, Object param) {
		return insertOrUpdateOrDelByNamed(name, new Object[] { param });
	}

	@Override
	public int insertOrUpdateOrDelByNamed(String name) {
		return insertOrUpdateOrDelByNamed(name, null_obj);
	}

	@Override
	public <E> List<E> find(String str) {
		return find(str, 0, 0, null_obj);
	}

	@Override
	public <E> List<E> find(String str, Object param) {
		if (param == null)
			return find(str, 0, 0, null_obj);
		else
			return find(str, 0, 0, new Object[] { param });
	}

	@Override
	public <E> List<E> find(String str, Object[] params) {
		return find(str, 0, 0, params);
	}

	@Override
	public <E> E findObj(String sql, Class<E> c, Object[] params) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return (E) query.addEntity(c).uniqueResult();
	}

	@Override
	public <E> List<E> find(String sql, Class<E> c, Object param) {
		return find(sql, c, 0, 0, new Object[] { param });
	}

	@Override
	public <E> List<E> find(String sql, Class<E> c, Object[] params) {
		return find(sql, c, 0, 0, params);
	}

	@Override
	public <E> List<E> find(String sql, Class<E> c, int page, int rows, Object[] params) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		if (rows > 0 && page > 0) {
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
		}
		return query.addEntity(c).list();
	}

	@Override
	public List<Map<String, Object>> findToMap(String sql) {
		return findToMap(sql, 0, 0);
	}

	@Override
	public List<Map<String, Object>> findToMap(String sql, Object param) {
		return findToMap(sql, 0, 0, new Object[] { param });
	}

	@Override
	public List<Map<String, Object>> findToMap(String sql, Object[] params) {
		return findToMap(sql, 0, 0, params);
	}

	@Override
	public List<Map<String, Object>> findToMap(String sql, int page, int rows, Object[] params) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		if (rows > 0 && page > 0) {
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
		}

		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<Map<String, Object>> findToMapByNamed(String name) {
		return findToMapByNamed(name, 0, 0, null_obj);
	}

	@Override
	public List<Map<String, Object>> findToMapByNamed(String name, Object param) {
		return findToMapByNamed(name, 0, 0, new Object[] { param });
	}

	@Override
	public List<Map<String, Object>> findToMapByNamed(String name, Object[] params) {
		return findToMapByNamed(name, 0, 0, params);
	}

	@Override
	public List<Map<String, Object>> findToMapByNamed(String name, int page, int rows, Object[] params) {
		SQLQuery query = (SQLQuery) getSession().getNamedQuery(name);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		if (rows > 0 && page > 0) {
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
		}

		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public <E> List<E> findByNamed(String name) {
		return findByNamed(name, 0, 0, null_obj);
	}

	@Override
	public <E> List<E> findByNamed(String name, Object param) {
		return findByNamed(name, 0, 0, new Object[] { param });
	}

	@Override
	public <E> List<E> findByNamed(String name, Object[] params) {
		return findByNamed(name, 0, 0, params);
	}

	@Override
	public Map<String, Object> findObjToMap(String sql, Object[] params) {
		SQLQuery query = getSession().createSQLQuery(sql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return (Map<String, Object>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
	}

	@Override
	public <E> E findObj(String str, Object[] params) {
		SQLQuery query = getSession().createSQLQuery(str);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return (E) query.uniqueResult();
	}

	@Override
	public <E> E findObj(String str) {
		return findObj(str, new Object[] {});
	}

	@Override
	public <E> E findObj(String str, Object param) {
		return findObj(str, new Object[] { param });
	}

	@Override
	public <E> E findObjByNamed(String name, Object param) {
		return findObjByNamed(name, new Object[] { param });
	}

	@Override
	public <E> E findObjByNamed(String name) {
		return findObjByNamed(name, null_obj);
	}

	@Override
	public <E> E findObjByNamed(String name, Object[] params) {
		SQLQuery query = (SQLQuery) getSession().getNamedQuery(name);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return (E) query.uniqueResult();
	}

	@Override
	public <E> List<E> find(String str, int page, int rows, Object[] params) {
		SQLQuery query = getSession().createSQLQuery(str);
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
		return find(str, page, rows, new Object[] { param });
	}

	@Override
	public <E> List<E> find(String str, int page, int rows) {
		return find(str, page, rows, null_obj);
	}

	@Override
	public <E> List<E> findByNamed(String name, int page, int rows, Object param) {
		return findByNamed(name, page, rows, new Object[] { param });
	}

	@Override
	public <E> List<E> findByNamed(String name, int page, int rows, Object[] params) {
		SQLQuery query =  (SQLQuery) getSession().getNamedQuery(name);
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
	public <E> List<E> findByNamed(String name, int page, int rows) {
		return findByNamed(name, page, rows, null_obj);
	}

	@Override
	public <E> E findObj(String sql, Class<E> c, Object param) {
		return findObj(sql, c, new Object[] { param });
	}

	@Override
	public <E> E findObj(String sql, Class<E> c) {
		return findObj(sql, c, null_obj);
	}

	@Override
	public Map<String, Object> findObjToMap(String sql, Object param) {
		return findObjToMap(sql, new Object[] { param });
	}

	@Override
	public Map<String, Object> findObjToMap(String sql) {
		return findObjToMap(sql, null_obj);
	}

	@Override
	public <E> List<E> find(String sql, Class<E> c, int page, int rows, Object param) {
		return find(sql, c, page, rows, new Object[] { param });
	}

	@Override
	public <E> List<E> find(String sql, Class<E> c, int page, int rows) {
		return find(sql, c, page, rows, null_obj);
	}

	@Override
	public List<Map<String, Object>> findToMap(String sql, int page, int rows, Object param) {
		return findToMap(sql, page, rows, new Object[] { param });
	}

	@Override
	public List<Map<String, Object>> findToMap(String sql, int page, int rows) {
		return findToMap(sql, page, rows, null_obj);
	}

	@Override
	public List<Map<String, Object>> findToMapByNamed(String name, int page, int rows, Object param) {
		return findToMapByNamed(name, page, rows, new Object[] { param });
	}

	@Override
	public List<Map<String, Object>> findToMapByNamed(String name, int page, int rows) {
		return findToMapByNamed(name, page, rows, null_obj);
	}

	@Override
	public int listCount(String sql) {
		return listCount(sql, null);
	}

	@Override
	public int listCount(String sql, Object param) {
		return listCount(sql, new Object[] { param });
	}

	@Override
	public int listCount(String sql, Object[] params) {
		Map<String, Object> obj = findObjToMap("select count(*) count " + sql.substring(sql.toLowerCase().indexOf("from")), params);
		return Integer.parseInt(obj.get("count") + "");
	}

	@Override
	public int listCountByNamedQuery(String name) {
		return listCountByNamedQuery(name, null);
	}

	@Override
	public int listCountByNamedQuery(String name, Object[] params) {
		return listCount(getSession().getNamedQuery(name).getQueryString(), params);
	}

	@Override
	public int listCountByNamedQuery(String name, Object param) {
		return listCount(getSession().getNamedQuery(name).getQueryString(), new Object[] { param });
	}

	@Override
	public Map<String, Object> findObjToMapByNamed(String name, Object[] params) {
		SQLQuery query  = (SQLQuery) getSession().getNamedQuery(name);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return (Map<String, Object>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
	}

	@Override
	public Map<String, Object> findObjToMapByNamed(String name, Object param) {
		return findObjToMapByNamed(name, new Object[] { param });
	}

	@Override
	public Map<String, Object> findObjToMapByNamed(String name) {
		return findObjToMapByNamed(name, null_obj);
	}

	@Override
	public <E> List<E> find(String str, Class<E> c) {
		return find(str, c, null_obj);
	}

}
