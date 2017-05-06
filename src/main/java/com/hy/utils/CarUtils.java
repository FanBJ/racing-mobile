package com.hy.utils;

import org.apache.commons.lang3.StringUtils;

public class CarUtils {
	public static String getShowTime(String time) {
		if (StringUtils.isBlank(time)) {
			return "00'00\"000";
		} else {
			String[] s = time.split(":");
			try {
				return s[0] + "'" + s[1] + "\"" + s[2];
			} catch (Exception e) {
				return time;
			}

		}
	}
	
	public static void main(String[] args) {
		System.out.println(getShowTime(null));
	}
}
