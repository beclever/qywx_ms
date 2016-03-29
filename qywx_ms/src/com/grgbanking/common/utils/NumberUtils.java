
package com.grgbanking.common.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
/**
 * 数字类型数据处理工具
 * Copyright 2012 by Grgbanking
 * All Rights Reserved.
 * Author: zhang zhi
 * Date: 2012-4-18 下午03:40:59
 */
public class NumberUtils {
	/**
	 * 将指点的数字对象按照指点的规则格式化, 例如:###.00,
	 * @param format String 格式
	 * @param obj Double 需转换的Double类型数据
	 * @return 格式化后的字符串
	 */
	public static String format(String format, double obj) {
		DecimalFormat formater = new DecimalFormat(format);
		return formater.format(obj);
	}
	
	/**
	 * 将指点的数字对象按照指点的规则格式化, 例如:###.00,
	 * @param format String
	 * @param obj Float 需转换的Float类型数据
	 * @return 格式化后的字符串
	 */
	public static String format(String format, float obj) {
		DecimalFormat formater = new DecimalFormat(format);
		return formater.format(obj);
	}

	/**
	 * 获取指定位数的随机数
	 * @param size int 位数
	 * @return 指定位数的随机数 long
	 */
	public static long getRandom(int size) {
		Double value = (Math.random() * Math.pow(10, size));
		return value.longValue();
	}
	
	/**
	 * 判断是否为整数
	 * @param value
	 * @return
	 */
	public static boolean isInteger(String value){
		try {
			Integer.valueOf(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * 判断是否为数字
	 * @param value
	 * @return
	 */
	public static boolean isNumeric(String value){
		try {
			Double.valueOf(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static Long createLong(String longValue){
		Long result;
		try {
			result = Long.parseLong(longValue);
		}
		catch (NumberFormatException e){
			return null;
		}
		return result;
	}
	
	public static Double createDouble(String doubleValue){
		Double result;
		try {
			result = Double.parseDouble(doubleValue);
		}
		catch (NumberFormatException e){
			return 0D;
		}
		return result;
	}
	
	public static Integer createInteger(String intValue){
		Integer result;
		try {
			result = Integer.parseInt(intValue);
		}
		catch (NumberFormatException e){
			return null;
		}
		return result;
	}
	
	public static Short createShort(String shortValue){
		Short result;
		try {
			result = Short.parseShort(shortValue);
		}
		catch (NumberFormatException e){
			return null;
		}
		return result;
	}
	
	public static String formatInteger(Integer integerValue, String defaultValue){
		if (integerValue == null){
			return defaultValue;
		}
		else{
			return String.valueOf(integerValue);
		}
	}
	
	public static String formatLong(Long longValue, String defaultValue){
		if (longValue == null){
			return defaultValue;
		}
		else{
			return String.valueOf(longValue);
		}
	}
	
	public static String formatShort(Short shortValue, String defaultValue){
		if (shortValue == null){
			return defaultValue;
		}
		else{
			return String.valueOf(shortValue);
		}
		
	}
	
   public static String percent(double p1,double p2) {
	   String str;
	   double p3=p1/p2;
	   str= String.valueOf(p3*100);
	   return str;
   }

}
