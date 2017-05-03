package com.hy.utils.security;

public class PasswordUtils {
	static String source = "0123456789abcdefghijklmnopqrstuvwxyz";
	static int max = 35;
	
	/**
	 * 通过编码获取数字
	 * 
	 * @param result
	 * @return
	 */
	public static int back(String result) {
		int count = 0;
		if (null != result && result.length() > 0) {
			char[] arr = result.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				count += (enDec(arr[i])) * Math.pow(36, arr.length - (i + 1));
			}
		}
		return count;
	}

	/**
	 * 通过数字返回相应的编码
	 * 
	 * @param num
	 * @return
	 */
	public static String getCode(int num) {
		char[] arr = source.toCharArray();
		int len = source.length();
		String result = "";

		if (num > 35) {
			int shang = num / len;
			int yu = num % len;
			result = arr[yu] + "";

			while (shang > 0) {
				result = arr[shang % 36] + result;
				shang = shang / 36;
			}
		} else {
			result = arr[num] + "";
		}

		return result;
	}

	// 把字符转成对应的数值
	public static int enDec(char c) {
		if (c >= 48 && c <= 57) {
			return c - 48;
		} else {
			// c - 97 + 10
			return c - 87;
		}
	}

	// 把数值转成字符
	public static char deDec(int num) {
		if (num <= 9) {
			return (char) (48 + num);
		} else {
			// num + 97 - 10
			return (char) (num + 87);
		}
	}
}
