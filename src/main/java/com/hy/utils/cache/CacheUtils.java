package com.hy.utils.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import com.hy.core.entity.CommonSysParam;
import com.hy.core.entity.SysDictInfo;
import com.hy.core.services.cache.ICacheService;
import com.hy.utils.DeclareFinalVariable;


/**
 * <pre>
 * 自定义缓存设置
 * 保存数据字典中的所有值，提供相应的查询方法
 * </pre>
 * 
 * @author FanBingjiang
 */
@SuppressWarnings("unchecked")
@Component
public class CacheUtils {
	private static List<SysDictInfo> dictList;
	private static Map<String, SysDictInfo> dictMap;
	private static SysDictInfo dict;
	private static ServletContext sc = null;

	/**
	 * 读取ServletContext对象
	 * 
	 * @return
	 */
	private static ServletContext getServletContext() {
		if (sc == null) {
			sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		}
		return sc;
	}

	/**
	 * 获取所有字典内信息，以List形式
	 * 
	 * @return
	 */
	public static Map<String, List<SysDictInfo>> getAllDictInfosList() {
		return (Map<String, List<SysDictInfo>>) getServletContext().getAttribute(DeclareFinalVariable.DICT_CACHE_LIST);
	}

	/**
	 * 获取所有字典内信息，以Map形式
	 * 
	 * @return
	 */
	public static Map<String, Map<String, SysDictInfo>> getAllDictInfosMap() {
		return (Map<String, Map<String, SysDictInfo>>) getServletContext().getAttribute(DeclareFinalVariable.DICT_CACHE_MAP);
	}

	/**
	 * <pre>
	 * 获取字典某一个table_name中的所有字典信息
	 * 以List形式返回
	 * </pre>
	 * 
	 * @param tableName
	 * @param true 降序 flase 升序
	 * @return
	 */
	public static List<SysDictInfo> findFieldVlsListOrderSelected(String tableName, boolean isOrder) {
		List<SysDictInfo> list = orderSelect(getAllDictInfosList().get(tableName), isOrder);
		return list;
	}

	public static List<SysDictInfo> orderSelect(List<SysDictInfo> list, boolean isOrder) {
		if (null != list && list.size() > 0) {
			Map<Integer, SysDictInfo> map = new HashMap<Integer, SysDictInfo>();
			Integer[] select = new Integer[list.size()];
			for (int i = 0; i < list.size(); i++) {
				SysDictInfo sd = list.get(i);
				select[i] = sd.getSelected();
				map.put(sd.getSelected(), sd);
			}
			List<SysDictInfo> listAffter = new ArrayList<SysDictInfo>();
			Arrays.sort(select);
			if (isOrder) {// 降序
				for (int i = select.length - 1; i >= 0; i--) {
					listAffter.add(map.get(select[i]));
				}
			} else {// 升序
				for (int i = 0; i < select.length; i++) {
					listAffter.add(map.get(select[i]));
				}
			}
			return listAffter;
		}
		return null;

	}

	/**
	 * <pre>
	 * 获取字典某一个table_name中的所有字典信息
	 * 以List形式返回
	 * </pre>
	 * 
	 * @param tableName
	 * @return
	 */
	public static List<SysDictInfo> findFieldVlsList(String tableName) {
		return getAllDictInfosList().get(tableName);
	}

	/**
	 * <pre>
	 * 获取字典某一个table_name中，field_name的所有字典信息
	 * 以List形式返回
	 * </pre>
	 * 
	 * @param tableName
	 * @return
	 */
	public static List<SysDictInfo> findFieldVlsList(String tableName, String fieldName) {
		List<SysDictInfo> all = getAllDictInfosList().get(tableName);
		if (null != all && all.size() > 0) {
			List<SysDictInfo> result = new ArrayList<SysDictInfo>();
			for (SysDictInfo dict : all) {
				if (fieldName.equals(dict.getFieldName())) {
					result.add(dict);
				}
			}
			return result;
		}

		return getAllDictInfosList().get(tableName);
	}

	/**
	 * <pre>
	 * 获取字典某一个table_name中的所有字典信息
	 * 以Map形式返回
	 * </pre>
	 * 
	 * @param tableName
	 * @return
	 */
	public static Map<String, SysDictInfo> findFieldVlsMap(String tableName) {
		return getAllDictInfosMap().get(tableName);
	}

	/**
	 * 通过表名获取一个字典信息
	 * 
	 * @param tableName
	 *            表名
	 * @return
	 */
	public static SysDictInfo getSysDictInfo(String tableName) {
		dictList = findFieldVlsList(tableName);
		if (dictList != null && dictList.size() > 0) {
			return dictList.get(0);
		}
		return null;
	}

	/**
	 * 通过表名和属性名获取一个字典信息
	 * 
	 * @param tableName
	 *            表名
	 * @return
	 */
	public static SysDictInfo getSysDictInfo(String tableName, String fieldName) {
		dictMap = findFieldVlsMap(tableName);
		return dictMap != null ? dictMap.get(fieldName) : null;
	}

	/**
	 * 通过表名和描述名获取一个字典信息
	 * 
	 * @param tableName
	 *            表名
	 * @return
	 */
	public static SysDictInfo getSysDictInfoByTableAndDesc(String tableName, String fieldDesc) {
		SysDictInfo s = null;
		List<SysDictInfo> list = findFieldVlsList(tableName);
		for (SysDictInfo sysDictInfo : list) {
			if (null == sysDictInfo.getFieldDesc()) {
				continue;
			}
			if (sysDictInfo.getFieldDesc().trim().equals(fieldDesc.trim())) {
				s = sysDictInfo;
				break;
			}
		}
		return s;
	}

	/**
	 * 通过表名和值名获取一个字典信息
	 * 
	 * @param tableName
	 *            表名
	 * @return
	 */
	public static SysDictInfo getSysDictInfoByTableAndFieldVal(String tableName, String fieldVal) {
		SysDictInfo s = null;
		List<SysDictInfo> list = findFieldVlsList(tableName);
		for (SysDictInfo sysDictInfo : list) {
			if (null == sysDictInfo.getFieldVal()) {
				continue;
			}
			if (sysDictInfo.getFieldVal().trim().equals(fieldVal.trim())) {
				s = sysDictInfo;
				break;
			}
		}
		return s;
	}

	/**
	 * 通过表名获取一个字典属性的值
	 * 
	 * @param tableName
	 *            表名
	 * @param fieldName
	 *            属性名
	 * @return
	 */
	public static String getFieldVal(String tableName) {
		dict = getSysDictInfo(tableName);
		return dict != null ? dict.getFieldVal() : null;
	}

	/**
	 * 通过表名和属性名获取一个字典属性的值
	 * 
	 * @param tableName
	 *            表名
	 * @param fieldName
	 *            属性名
	 * @return
	 */
	public static String getFieldVal(String tableName, String fieldName) {
		dictMap = findFieldVlsMap(tableName);
		if (null != dictMap && dictMap.get(fieldName) != null) {
			return dictMap.get(fieldName).getFieldVal();
		}
		return null;
	}

	/**
	 * 通过表名和属性名获取一个字典属性的值
	 * 
	 * @param tableName
	 *            表名
	 * @param fieldName
	 *            属性名
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static String getFieldVal(String tableName, String fieldName, String defaultValue) {
		String val = getFieldVal(tableName, fieldName);
		return val == null ? defaultValue : val;
	}

	/**
	 * 修改缓存中的一个值
	 * 
	 * @param tableName
	 * @param fieldName
	 * @param fieldValue
	 */
	public static void updateFieldVal(String tableName, String fieldName, String fieldValue) {
		Map<String, List<SysDictInfo>> allList = getAllDictInfosList();
		Map<String, Map<String, SysDictInfo>> allMap = getAllDictInfosMap();

		// 修改List中的对象
		List<SysDictInfo> fieldList = allList.get(tableName);
		for (SysDictInfo sdi : fieldList) {
			if (sdi.getFieldName().equals(fieldName)) {// 再找到对应的字段名
				sdi.setFieldVal(fieldValue);// 修改
				getServletContext().setAttribute(DeclareFinalVariable.DICT_CACHE_LIST, allList);// 重新存入
				break;
			}
		}

		// 修改Map中的对象
		Map<String, SysDictInfo> fieldMap = allMap.get(tableName);
		SysDictInfo sdi = fieldMap.get(fieldName);
		if (null != sdi) {
			sdi.setFieldVal(fieldValue);// 修改
			getServletContext().setAttribute(DeclareFinalVariable.DICT_CACHE_MAP, allMap);// 重新存入
		}
	}

	// 获取系统参数的值
	public static String getSysParamVal(String key) {
		return getSysParamVal(key, null);
	}

	// 获取系统参数的值
	public static String getSysParamVal(String key, String defaultVal) {
		CommonSysParam val = getSysParamObj(key);

		if (null != val) {
			return val.getParamval() != null ? val.getParamval() : defaultVal;
		}
		return defaultVal;
	}

	// 获取系统参数的对象
	public static CommonSysParam getSysParamObj(String key) {
		Map<String, CommonSysParam> sysParamMap = (Map<String, CommonSysParam>) getServletContext().getAttribute(ICacheService.SYS_PARAM_CACHE_MAP);
		if (null != sysParamMap) {
			return sysParamMap.get(key);
		}
		return null;
	}

}
