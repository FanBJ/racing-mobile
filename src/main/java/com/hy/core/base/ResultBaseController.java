package com.hy.core.base;

import java.util.HashMap;
import java.util.Map;

import com.hy.utils.json.JsonUtil;
import com.hy.utils.security.Des3;

public class ResultBaseController extends BaseController {
	private static final String STATE = "state";
	private static final String MSG = "msg";
	private static final String DATA = "data";
	private static final boolean ISENCRYPT = true;// 默认加密

	/**
	 * 返回至终端的信息
	 * 
	 * @param visitState
	 *            该值对应1＝访问正常，0＝访问不正常，通常的正常与不正常是指系统是否报错等
	 * @param infoState
	 *            设置调用之后，数据信息的状态，可自定义，可选择Resultinfo中对应的常量值
	 * @param msg
	 *            提示信息，提示给终端的信息
	 * @param data
	 *            返回至终端的数据
	 * @param isEncrypt
	 *            是否需要加密
	 */
	protected Map<String, Object> setResultMap(int infoState, String msg, Object data, boolean isEncrypt) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.clear();
		resultMap.put(STATE, infoState);
		resultMap.put(MSG, msg);
		if (isEncrypt) {
			try {
				resultMap.put(DATA, Des3.encode(JsonUtil.toJson(data)));
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put(DATA, data);
			}
		} else {
			resultMap.put(DATA, data);
		}

		return resultMap;
	}

	protected Map<String,Object> setResultMap(int infoState, String msg, boolean isEncrypt) {
		return setResultMap(infoState, msg, "", isEncrypt);
	}

	protected Map<String,Object> setResultMap(int infoState, String msg) {
		return setResultMap(infoState, msg, "", ISENCRYPT);
	}

	protected Map<String,Object> setResultMap(int infoState, Object data, boolean isEncrypt) {
		return setResultMap(infoState, ResultInfo.getZhMsg(infoState), data, isEncrypt);
	}

	protected Map<String,Object> setResultMap(int infoState, Object data) {
		return setResultMap(infoState, ResultInfo.getZhMsg(infoState), data, ISENCRYPT);
	}

	protected Map<String,Object> setResultMap(int infoState, boolean isEncrypt) {
		return setResultMap(infoState, ResultInfo.getZhMsg(infoState), "", isEncrypt);
	}

	protected Map<String,Object> setResultMap(int infoState) {
		return setResultMap(infoState, ResultInfo.getZhMsg(infoState), "", ISENCRYPT);
	}

	protected Map<String,Object> setResultMap(Object data, boolean isEncrypt) {
		return setResultMap(ResultInfo.SUCCESS, ResultInfo.getZhMsg(ResultInfo.SUCCESS), data, isEncrypt);
	}

	protected Map<String,Object> setResultMap(Object data) {
		return setResultMap(ResultInfo.SUCCESS,ResultInfo.getZhMsg(ResultInfo.SUCCESS), data, ISENCRYPT);
	}
}
