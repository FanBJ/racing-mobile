package com.hy.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Test {
	static Map<String, Object> rightMap = new HashMap<String, Object>();
	static Map<Integer, Map<String, Object>> userMap = new HashMap<Integer, Map<String, Object>>();

	static {
		rightMap.put("menu_01", new T(1, "角色管理", "#"));
		rightMap.put("menu_02", new T(2, "角色列表", "/role/list.json"));
		rightMap.put("menu_03", new T(3, "删除角色", "/role/del.json"));
		rightMap.put("menu_04", new T(4, "角色权限", "/role/right.json"));

		userMap.put(1, rightMap);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String strUrl = "http://t.im/?url=http://d.cnxxs.cn";

		String url = getUrl(strUrl);
		System.out.println(url);
		
	}

	int full_num_length = 9;

	public static String fill(int num) {
		String str = num + "";
		while (str.length() < 9) {
			str = "0" + str;
		}
		return str;
	}

	public static String getUrl(String strUrl) {
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			URL url = new URL(strUrl);
			isr = new InputStreamReader(url.openStream(), "utf-8");
			br = new BufferedReader(isr);
			String src = null;
			while ((src = br.readLine()) != null) {
				if (src.indexOf("shortUrl") != -1) {
					int a = src.indexOf("shortUrl") + 10;
					int b = "</strong>".length();
					int c = src.length();
					return src.substring(a, c - b);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr) isr.close();
				if (null != br) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static boolean isHasRight(Integer id, String rightName) {
		if (null != userMap.get(id)) { return rightMap.containsKey(rightName); }
		return false;
	}
}

class T {
	private int id;
	private String name;
	private String url;

	public T() {
	}

	public T(int id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}