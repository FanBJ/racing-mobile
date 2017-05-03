package com.hy.utils.order;

import java.util.List;

import com.hy.core.entity.SysDictInfo;
import com.hy.utils.cache.CacheUtils;

public class User {
    public static boolean isTestUser(Integer uId) {
    	if (uId==null||uId==0) {
    		return false;
		}
    	List<SysDictInfo> testUserId = CacheUtils.findFieldVlsList("test_user_id");
		if(testUserId!=null&&testUserId.size()>0){
			for (int i = 0; i < testUserId.size(); i++) {
				if(testUserId.get(i).getFieldName().equals(String.valueOf(uId))){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isStaffUser(Integer uId) {
		if (uId==null||uId==0) {
    		return false;
		}
		List<SysDictInfo> testUserId = CacheUtils.findFieldVlsList("staff_user_id");
		if(testUserId!=null&&testUserId.size()>0){
			for (int i = 0; i < testUserId.size(); i++) {
				if(testUserId.get(i).getFieldName().equals(String.valueOf(uId))){
					return true;
				}
			}
		}
		return false;
	}
}
