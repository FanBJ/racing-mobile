package com.hy.utils.json;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.utils.page.PageObj;

/**
 * JSON(JavaScript Object Natation)格式通用工具
 * 
 * @author Administrator
 * @createDate 2011-7-4 下午12:26:16
 * @since jdk1.6.0_26
 * @version 1.0
 */
public class JsonUtil {

	/**
	 * 将String 对象编码为JSON 格式只需处理好特殊字
	 * 
	 * @param s
	 *            String 对象
	 * @return JSON格式
	 */
	static String string2Json(final String s) {
		final StringBuilder sb = new StringBuilder(s.length() + 20);
		sb.append('"');
		for (int i = 0; i < s.length(); i++) {
			final char c = s.charAt(i);
			switch (c) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		sb.append('"');
		return sb.toString();
	}

	/**
	 * Number 表示JSON格式
	 * 
	 * @param number
	 *            Number
	 * @return JSON格式
	 */
	static String number2Json(final Number number) {
		return "\"" + number.toString() + "\"";
	}

	/**
	 * 将Boolean 表示为JSON格式
	 * 
	 * @param bool
	 *            Boolean
	 * @return JSON格式
	 */
	static String boolean2Json(final Boolean bool) {
		return bool.toString();
	}

	/**
	 * 将Collection 编码为JSON格式(List，Set)
	 * 
	 * @param c
	 * @return
	 */
	static String collection2Json(final Collection<Object> c) {
		final Object[] arrObj = c.toArray();
		return toJson(arrObj);
	}

	/**
	 * Map<String, Object>编码为JSON格式
	 * 
	 * @param map
	 * @return
	 */
	static String map2Json(final Map<String, Object> map) {
		if (map.isEmpty()) {
			return "{}";
		}
		final StringBuilder sb = new StringBuilder(map.size() << 4);// 4次方
		sb.append("{");
		final Set<String> keys = map.keySet();
		for (final String key : keys) {
			final Object value = map.get(key);
			sb.append('"');
			sb.append(key);// 不能包含特殊字符
			sb.append('"');
			sb.append(':');
			sb.append(toJson(value));// 循环引用的对象会引发无限递归
			sb.append(",");
		}
		// 将最后的','变为'}'
		sb.setCharAt(sb.length() - 1, '}');
		return sb.toString();
	}

	/**
	 * 将数组编码为 JSON 格式
	 * 
	 * @param array
	 *            数组
	 * @return
	 */
	static String array2Json(final Object[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);// 4次方
		sb.append('[');
		for (final Object o : array) {
			sb.append(toJson(o));
			sb.append(',');
		}
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	static String intArray2Json(final int[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);// 4次方
		sb.append('[');
		for (final int o : array) {
			sb.append(Integer.toBinaryString(o));
			sb.append(',');
		}
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	static String longArray2Json(final long[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final long o : array) {
			sb.append(Long.toString(o));
			sb.append(',');
		}
		// set last "," to "]":
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	static String booleanArray2Json(final boolean[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final boolean o : array) {
			sb.append(Boolean.toString(o));
			sb.append(',');
		}
		// set last "," to "]":
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	static String floatArray2Json(final float[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final float o : array) {
			sb.append(Float.toString(o));
			sb.append(',');
		}
		// set last "," to "]":
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	static String doubleArray2Json(final double[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final double o : array) {
			sb.append(Double.toString(o));
			sb.append(',');
		}
		// set last "," to "]":
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	static String shortArray2Json(final short[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final short o : array) {
			sb.append(Short.toString(o));
			sb.append(',');
		}
		// set last "," to "]":
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	static String byteArray2Json(final byte[] array) {
		if (array.length == 0) {
			return "[]";
		}
		final StringBuilder sb = new StringBuilder(array.length << 4);
		sb.append('[');
		for (final byte o : array) {
			sb.append(Byte.toString(o));
			sb.append(',');
		}
		// set last "," to "]":
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	static String object2Json(final Object bean) {
		// 数据
		if (bean == null) {
			return "{}";
		}
		final Method[] methods = bean.getClass().getMethods();// 方法数组
		final StringBuilder sb = new StringBuilder(methods.length << 4);// 4次方
		sb.append('{');

		for (final Method method : methods) {
			try {
				final String name = method.getName();
				String key = "";
				if (name.startsWith("get")) {
					key = name.substring(3);

					// 防止死循
					final String[] arrs = { "Class" };
					boolean b1 = false;
					for (final String s : arrs) {
						if (s.equals(key)) {
							b1 = true;
							continue;
						}
					}
					if (b1) {
						continue;// 防止死循
					}
				} else if (name.startsWith("is")) {
					key = name.substring(2);
				}
				if (key.length() > 0 && Character.isUpperCase(key.charAt(0)) && method.getParameterTypes().length == 0) {
					if (key.length() == 1) {
						key = key.toLowerCase();
					} else if (!Character.isUpperCase(key.charAt(1))) {
						key = key.substring(0, 1).toLowerCase() + key.substring(1);
					}
					final Object elementObj = method.invoke(bean, new Object[0]);
					sb.append('"');
					sb.append(key);
					sb.append('"');
					sb.append(':');
					sb.append(toJson(elementObj));
					sb.append(",");
				}
			} catch (Exception ex) {
				throw new RuntimeException("在将bean封装成JSON格式时异常：" + ex.getMessage(), ex);
			}
		}
		if (sb.length() == 1) {
			return bean.toString();
		} else {
			sb.setCharAt(sb.length() - 1, '}');
			return sb.toString();
		}
	}

	private static ObjectMapper objMapper = new ObjectMapper();

	public static JavaType getCollectionType(Class<?> collectionClass, Class<?> elementClasses) {
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	public static <E> List<E> jsonToList(String json, Class<E> c) {
		try {
			return objMapper.readValue(json, getCollectionType(List.class, c));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <E> PageObj<E> jsonToPageObj(String json, Class<E> c) {
		try {
			return objMapper.readValue(json, getCollectionType(PageObj.class, c));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String listToJson(List<?> list) {
		try {

			return objMapper.writeValueAsString(list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <E> E jsonToObj(String data, Class<E> c) {
		try {
			return objMapper.readValue(data, c);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 如果对象为Null,返回"null". 如果集合为空集合,返回"[]".
	 */
	public static String toJson(Object object) {
		try {
			return objMapper.writeValueAsString(object);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
