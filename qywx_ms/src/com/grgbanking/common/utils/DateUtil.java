package com.grgbanking.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


public class DateUtil {

	public static final String FORMAT_1 = "yyyy";
	public static final String FORMAT_2 = "yyyy-MM";
	public static final String FORMAT_3 = "yyyy-MM-dd";
	public static final String FORMAT_4 = "yyyy-MM-dd HH";
	public static final String FORMAT_5 = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_6 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 按照指定的格式，将日期类型对象转换成字符串，例如：yyyy-MM-dd,yyyy/MM/dd,yyyy/MM/dd hh:mm:ss
	 * 如果传入的日期为null,则返回空值
	 * 
	 * @param date
	 *            日期类型对象
	 * @param format
	 *            需转换的格式
	 * @return 日期格式字符串
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(date);
	}

	/**
	 * 将日期类型对象转换成yyyy-MM-dd类型字符串 如果传入的日期为null,则返回空值
	 * 
	 * @param date
	 *            日期类型对象
	 * @return 日期格式字符串
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		return formater.format(date);
	}

	/**
	 * 将日期类型对象转换成yyyy-MM-dd HH:mm:ss类型字符串 如果传入的日期为null,则返回空值
	 * 
	 * @param date
	 *            日期类型对象
	 * @return 日期格式字符串
	 */
	public static String formatTime(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formater.format(date);
	}

	/**
	 * 按照指定的格式，将字符串解析成日期类型对象，例如：yyyy-MM-dd,yyyy/MM/dd,yyyy/MM/dd hh:mm:ss
	 * 
	 * @param dateStr
	 *            日期格式的字符串
	 * @param format
	 *            字符串的格式
	 * @return 日期类型对象
	 */
	public static Date parseDate(String dateStr, String format) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			return formater.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将字符串（yyyy-MM-dd）解析成日期
	 * 
	 * @param dateStr
	 *            日期格式的字符串
	 * @return 日期类型对象
	 */
	public static Date parseDate(String dateStr) {
		if (dateStr.indexOf("/") != -1) {
			dateStr = dateStr.replaceAll("/", "-");
		}
		return parseDate(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 将字符串解析成对应日期格式的日期
	 * 
	 * @param value
	 *            日期格式字符串
	 * @return 日期类型对象
	 */
	public static Date parse(String value) {
		if (null == value || value.length() == 0) {
			return null;
		}
		value = value.trim().replaceAll("/", "-");
		if (value.length() == FORMAT_1.length()) {
			return parseDate(value, FORMAT_1);
		} else if (value.length() == FORMAT_2.length()) {
			return parseDate(value, FORMAT_2);
		} else if (value.length() == FORMAT_3.length()) {
			return parseDate(value, FORMAT_3);
		} else if (value.length() == FORMAT_4.length()) {
			return parseDate(value, FORMAT_4);
		} else if (value.length() == FORMAT_5.length()) {
			return parseDate(value, FORMAT_5);
		} else if (value.length() == FORMAT_6.length()) {
			return parseDate(value, FORMAT_6);
		} else {
			throw new RuntimeException("解析日期格式出错，与指定格式不匹配.");
		}
	}
	
	/**
	 * 根据指定的格式（例如：yyyy-MM-dd）获取当天的时间字符串
	 * 
	 * @return 日期字符串
	 */
	public static String getDate(String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String strDate = sdf.format(date);
		return strDate;
	}
	
	/**
	 * 
	 * @createDate 2011-1-21
	 * @author GuaBin
	 * @param date 日期
	 * @param days 增加或减少的天数，如1或-1
	 * @param format 日期格式
	 * @return String
	 * @description
	 * 对日期增加或减少天数，并按照format格式返回字符串类型日期
	 */
	public static String addDay(Date date,int days,String format){
		Date temp = DateUtil.addDay(date, days);
		return DateUtil.formatDate(temp,format);
	}
	
	/**
	 * 对指定的日期增加或减少指定的天数
	 * @param date 需要修改的日期对象
	 * @param amount 需要修改的数量，如果需要增加一天，amount=1,如果减少一天，amount=-1;
	 * @return 修改后日期类型对象
	 */
	public static Date addDay(Date date,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return cal.getTime();
	}
	
	/**
	 * 对指定的日期增加或减少指定的月数
	 * @param date 需要修改的日期对象
	 * @param amount 需要修改的数量，如果需要增加一个月，amount=1,如果减少一个月，amount=-1;
	 * @return 修改后日期类型对象
	 */
	public static Date addMonth(Date date,int amount) {
		if(amount == 0){
			return date;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, amount);
		return cal.getTime();
	}
	
	/**
	 * 
	 * @createDate 2011-1-21
	 * @author GuaBin
	 * @param date 字符串类型的日期
	 * @param months 增加或减少的月数，如1或-1
	 * @param format 日期格式
	 * @return String
	 * @description
	 * 对字符串类型的日期增加或减少月数，并返回字符串类型日期
	 */
	public static String addMonth(String date,int months,String format){
		Date temp = DateUtil.parseDate(date,format);
		temp = DateUtil.addMonth(temp, months);
		return DateUtil.formatDate(temp,format);
	}
	
	/**
	 * 
	 * @createDate 2011-1-21
	 * @author GuaBin
	 * @param date 日期
	 * @param months 增加或减少的月数，如1或-1
	 * @param format 日期格式
	 * @return String
	 * @description
	 * 对日期增加或减少月数，并按照format格式返回字符串类型日期
	 */
	public static String addMonth(Date date,int months,String format){
		Date temp = DateUtil.addMonth(date, months);
		return DateUtil.formatDate(temp,format);
	}
}
