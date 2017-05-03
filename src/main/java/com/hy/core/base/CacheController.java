package com.hy.core.base;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.core.entity.SysDictInfo;
import com.hy.utils.cache.CacheUtils;

/**
 * 获取缓存信息
 * 
 * @author FBJ
 * 
 */
@Controller
@RequestMapping("/cache")
public class CacheController extends BaseController {
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/getAllCacheList.json")
	@ResponseBody
	public Map<String, List<SysDictInfo>> getAllCacheToList(){
		return CacheUtils.getAllDictInfosList();
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/getAllCacheMap.json")
	@ResponseBody
	public Map<String, Object> getAllCacheToMap(){
		
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/findCacheByTableName.json")
	@ResponseBody
	public Map<String, Object> findCacheByTableName(String tableName){
		
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/findCacheByTableNameAndFieldName.json")
	@ResponseBody
	public Map<String, Object> findCacheByTableNameAndFieldName(String tableName){
		
		return null;
	}
}
