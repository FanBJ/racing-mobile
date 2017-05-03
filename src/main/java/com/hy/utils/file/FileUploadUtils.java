package com.hy.utils.file;

import com.hy.utils.cache.CacheUtils;

public class FileUploadUtils {
	public static final String VISIT_ROOT_PATH = CacheUtils.getFieldVal("sys.path", "web_visit_root");
	public static final String[] allowImgsType = { "jpg", "bmp", "png", "gif" };
	public static final String VISIT_PAGE_PATH = CacheUtils.getSysParamVal("web_visit_root");

	/**
	 * 获取远程访问文件的根目录
	 * 
	 * @return
	 */
	public static String getRemoteFileRootURL() {
		return VISIT_ROOT_PATH;
	}

	public static String getRemoteFileRootpage() {
		return VISIT_PAGE_PATH;
	}
	
}
