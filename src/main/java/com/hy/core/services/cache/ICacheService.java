package com.hy.core.services.cache;

import javax.servlet.ServletContext;

import com.hy.core.entity.SysDictInfo;

/**
 * 缓存业务,该业务会在系统启动时加载到内存中.
 * 
 * @author FanBingjiang
 */
public interface ICacheService {
	/**
	 * 数据字典在application中List的key名称
	 */
	String DICT_CACHE_LIST = "dictCacheList";
	/**
	 * 数据字典在application中Map的key名称
	 */
	String DICT_CACHE_MAP = "dictCacheMap";
	/**
	 * 系统参数在application中Map的key名称
	 */
	String SYS_PARAM_CACHE_MAP = "sysParamCacheMap";
	
	/**
	 * 加载系统参数到application中
	 * 
	 * @param application
	 */
	public void loadSysParam(ServletContext application);

	/**
	 * 加载数据字典到application中
	 * 
	 * @param application
	 */
	public void loadDictInfo(ServletContext application);

	/**
	 * 更新数据字典一条数据
	 * 
	 * @param dictInfo
	 * @return
	 */
	public void updateDictInfo(SysDictInfo dictInfo);

	/**
	 * 通过数据字典的表名和字段名，修改fieldValue的值值
	 * 
	 * @param tableName
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public boolean updateDictValByTablenameAndFieldname(String tableName, String fieldName, String fieldValue);

	/**
	 * 通过数据字典表名修改，fieldValue的值
	 * 
	 * @param tableName
	 *            表名
	 * @param value
	 *            fieldValue值
	 * @return
	 */
	public boolean updateDictValByTablename(String tableName, String fieldValue);

	/**
	 * 通过数据字典“字段名”修改，fieldValue的值
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public boolean updateDictValByFieldname(String fieldName, String fieldValue);

}
