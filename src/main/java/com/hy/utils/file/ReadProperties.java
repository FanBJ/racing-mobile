package com.hy.utils.file;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取config.properties配置文件中的信息
 */
public class ReadProperties {
	private static String CONFIG_PROPERTIES = "config.properties";

	private static Properties config = new Properties();

	/**
	 * 读取properties配置文件信息
	 */
	static {
		try {
			config.load(ReadProperties.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据key得到value的值
	 */
	public static String getConfig(String key) {
		return config.getProperty(key);
	}
}
