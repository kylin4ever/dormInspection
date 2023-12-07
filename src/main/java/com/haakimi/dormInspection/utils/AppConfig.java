package com.haakimi.dormInspection.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class AppConfig {
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	private static final Properties properties = new Properties();

	private static final Map<String,String> propertiesCache = new HashMap<>();

	public static void addProperties(Properties properties) {
		AppConfig.properties.putAll(properties);
	}

	public static void addProperty(String key, String value) {
		properties.put(key, value);
	}

	public static void removeProperty(String key) {
		properties.remove(key);
	}

	public static String getProperty(String key) {
		if(propertiesCache.containsKey(key)){
			return propertiesCache.get(key);
		}
		String propertyValue = getProperty(key, null);
		propertiesCache.put(key,propertyValue);
		return propertyValue;
	}

	public static String getProperty(String key, String defaultValue) {
		try {
			return properties.getProperty(key, defaultValue);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static String getPropertyEncoding(String key, String encoding) {
		return getPropertyEncoding(key, encoding, null);
	}

	public static String getPropertyEncoding(String key, String encoding, String defaultValue) {
		String str = getProperty(key, defaultValue);
		try {
			// 进行编码转换，解决问题
			str = new String(str.getBytes("ISO8859-1"), encoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return str;
	}

}