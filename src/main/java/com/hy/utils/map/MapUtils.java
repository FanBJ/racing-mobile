package com.hy.utils.map;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
	public static Map<String, Object> putOneKey(String key, Object val) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, val);
		return map;
	}

	public static Map<String,Object> getValue(Object thisObj) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Method[] m = thisObj.getClass().getMethods();
			for (int i = 0; i < m.length; i++) {
				String method = m[i].getName();
				if (method.startsWith("get")) {
					try {
						Object value = m[i].invoke(thisObj);
						if (value != null) {
							String key = method.substring(3);
							key = key.substring(0, 1).toUpperCase() + key.substring(1);
							map.put(key, value);
						}
					} catch (Exception e) {
						System.out.println("error:" + method);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
