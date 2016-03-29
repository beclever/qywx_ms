package com.grgbanking.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：qywx_ms   
 *
 * 类描述：文件读取工具
 * 类名称：com.grgbanking.common.utils.PropUtils     
 * 创建人：TXH 
 * 创建时间：2015-7-22 下午2:50:35   
 * 修改人：
 * 修改时间：2015-7-22 下午2:50:35   
 * 修改备注：   
 * @version   V1.0
 */
public class PropUtils {
	private static Properties properties = null;

	public static String deployUrl = null;
	public static String deployPath = null;

	static {
		properties = loadPropertyFile("config.properties");
	}

	/**
	 * 获取是否允许发送邮件
	 * 
	 * @return
	 */
	public static boolean getEnableEmail() {
		try {
			return Boolean.parseBoolean(properties.getProperty("mail.enable"));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取邮件配置
	 * 
	 * @return
	 */
	public static Properties getEmailProperties() {
		Properties prop = new Properties();

		prop.put("mail.enable", properties.get("mail.enable"));
		prop.put("mail.user", properties.get("mail.user"));
		prop.put("mail.pass", properties.get("mail.pass"));
		prop.put("mail.smtp.host", properties.get("mail.smtp.host"));
		prop.put("mail.protocal", properties.get("mail.protocal"));

		return prop;
	}

	/**
	 * 加载配置文件
	 * 
	 * @param fullFile
	 * @return
	 */
	@SuppressWarnings("unused")
	public static Properties loadPropertyFile(String fullFile) {
		String webRootPath = null;
		if (null == fullFile || fullFile.equals(""))
			throw new IllegalArgumentException(
					"Properties file path can not be null : " + fullFile);
		InputStream inputStream = null;
		Properties p = null;
		try {
			inputStream = PropUtils.class.getClassLoader().getResourceAsStream(
					fullFile);
			p = new Properties();
			p.load(inputStream);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Properties file not found: "
					+ fullFile);
		} catch (IOException e) {
			throw new IllegalArgumentException(
					"Properties file can not be loading: " + fullFile);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	public static String get(String key) {
        String str = properties.getProperty(key);
	    if (str != null) {
            return str.trim();
        } else {
            return null;
        }
	}

	public static String get(String key, String defaultValue) {
		String value = get(key);
		return (value != null) ? value : defaultValue;
	}

	public static Integer getInt(String key) {
		String value = get(key);
		return (value != null) ? Integer.parseInt(value) : null;
	}

	public static Integer getInt(String key, Integer defaultValue) {
		String value = get(key);
		return (value != null) ? Integer.parseInt(value) : defaultValue;
	}

	public static Long getLong(String key) {
		String value = get(key);
		return (value != null) ? Long.parseLong(value) : null;
	}

	public static Long getLong(String key, Long defaultValue) {
		String value = get(key);
		return (value != null) ? Long.parseLong(value) : defaultValue;
	}

	public static Boolean getBoolean(String key) {
		String value = get(key);
		return (value != null) ? Boolean.parseBoolean(value) : null;
	}

	public static Boolean getBoolean(String key, Boolean defaultValue) {
		String value = get(key);
		return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
	}

	public static boolean containsKey(String key) {
		return properties.containsKey(key);
	}
}
