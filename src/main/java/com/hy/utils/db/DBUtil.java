package com.hy.utils.db;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;

/**
 * Map与Object相互转换的工具
 */
public class DBUtil {
	/**
	 * 把List(Map) 转换为 List(E)
	 * 
	 * @param list
	 * @param c
	 *            要转换的实体类
	 * @return
	 */
	public static <E> List<E> converListMapToListObj(List<Map<String, Object>> list, Class<E> c) {
		if (null == list || list.size() == 0)
			return null;
		List<E> result = new ArrayList<E>();
		for (Map<String, Object> map : list) {
			result.add(convertMapToObject(map, c));
		}
		return result;
	}

	/**
	 * 把Map转换成实体对象
	 * 
	 * @param map
	 * @param c
	 * @return
	 */
	public static <E> E convertMapToObject(Map<String, Object> map, Class<E> c) {
		E obj = null;
		// 不区分大小写
		Map<String, Object> map2 = new CaseInsensitiveMap(map);
		try {
			obj = c.newInstance();
			Method[] ms = c.getMethods();
			if (ms.length == 0) {
				// 该对象没有方法
				return null;
			}

			for (Method m : ms) {
				if ("set".equals(m.getName().substring(0, 3))) {
					String mn = m.getName().substring(3).toLowerCase();
					Object value = map2.get(mn);

					if (null != value) {
						try {
							if (value instanceof Date) {
								m.invoke(obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value));
							} else {
								if (m.getParameterTypes()[0].getSimpleName().equals("Integer")) {
									m.invoke(obj, Integer.parseInt(value + ""));
								} else if (m.getParameterTypes()[0].getSimpleName().equals("Long")) {
									m.invoke(obj, Long.parseLong(value + ""));
								} else if (m.getParameterTypes()[0].getSimpleName().equals("String")) {
									m.invoke(obj, String.valueOf(value));
								}else{
									m.invoke(obj, value);
								}
							}
						} catch (Exception e) {
							System.out.println("\n\n" + m.getName() + " 方法调用失败,数据类型不匹配：方法参数类型为 (" + m.getParameterTypes()[0].getSimpleName() + ") Map对象的值类型为：(" + value.getClass() + ")\n\n");
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			map2 = null;
		}
		return obj;
	}

	public static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://119.1.109.103:3306/shopping?useUnicode=true&characterEncoding=utf-8";
		String user = "shopping";
		String pwd = "Vinord@2014ShoppingPwd";

		// String url = "jdbc:mysql://localhost:3306/shopping";
		// String user = "test";
		// String pwd = "test";
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void cls(Connection conn, PreparedStatement pst, ResultSet rs) {
		try {
			if (null != rs)
				rs.close();
			if (null != pst)
				pst.close();
			if (null != conn)
				conn.close();
		} catch (SQLException e) {
		}
	}

}
