package com.hy.core.services.cache.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.hy.core.entity.CommonSysParam;
import com.hy.core.entity.SysDictInfo;
import com.hy.core.services.BaseServices;
import com.hy.core.services.cache.ICacheService;

@Service
public class CacheServiceImp extends BaseServices implements ICacheService {
	@Override
	public void loadDictInfo(ServletContext application) {
		List<SysDictInfo> dictList = hqlDao.findByNamed("loadDictInfo");
		// 以List信息来存放
		Map<String, List<SysDictInfo>> resultList = new HashMap<String, List<SysDictInfo>>();
		// 以Map信息来存放
		Map<String, Map<String, SysDictInfo>> resultMap = new HashMap<String, Map<String, SysDictInfo>>();

		if (null != dictList && dictList.size() > 0) {

			List<SysDictInfo> tempList = null;
			Map<String, SysDictInfo> tempMap = null;
			for (SysDictInfo sysDictInfo : dictList) {
				String tempTableName = sysDictInfo.getTableName().trim();
				String tempFieldName = sysDictInfo.getFieldName();

				if (resultList.containsKey(tempTableName)) {
					tempList = resultList.get(tempTableName);
					tempList.add(sysDictInfo);

					if (tempFieldName != null) {
						tempMap = resultMap.get(tempTableName);
						tempMap.put(tempFieldName.trim(), sysDictInfo);
					}
				} else {
					tempList = new ArrayList<SysDictInfo>();
					tempList.add(sysDictInfo);
					resultList.put(tempTableName, tempList);

					if (tempFieldName != null) {
						tempMap = new HashMap<String, SysDictInfo>();
						tempMap.put(tempFieldName.trim(), sysDictInfo);
						resultMap.put(tempTableName, tempMap);
					}
				}
			}
		}

		// 加载到application中
		application.setAttribute(DICT_CACHE_LIST, resultList);
		application.setAttribute(DICT_CACHE_MAP, resultMap);
	}

	@Override
	public void updateDictInfo(SysDictInfo dictInfo) {
		hqlDao.updateObj(dictInfo);
	}

	@Override
	public boolean updateDictValByTablenameAndFieldname(String tableName, String fieldName, String fieldValue) {
		return sqlDao.insertOrUpdateOrDel("updateDictValByTablenameAndFieldname", new Object[] { fieldValue, tableName, fieldName }) > 0;
	}

	@Override
	public boolean updateDictValByTablename(String tableName, String fieldValue) {
		return sqlDao.insertOrUpdateOrDel("updateDictValByTablename", new Object[] { fieldValue, tableName }) > 0;
	}

	@Override
	public boolean updateDictValByFieldname(String fieldName, String fieldValue) {
		return sqlDao.insertOrUpdateOrDel("updateDictValByFieldname", new Object[] { fieldValue, fieldName }) > 0;
	}

	@Override
	public void loadSysParam(ServletContext application) {
		List<CommonSysParam> sysParamList = hqlDao.findByNamed("loadSysParam");
		if (null != sysParamList && sysParamList.size() > 0) {
			Map<String, CommonSysParam> sysParamMap = new HashMap<String, CommonSysParam>(sysParamList.size());
			
			for (CommonSysParam commonSysParam : sysParamList) {
				sysParamMap.put(commonSysParam.getParamname(), commonSysParam);
			}
			
			application.setAttribute(SYS_PARAM_CACHE_MAP, sysParamMap);
		}

	}

}
