/**
 * 
 */
package com.grgbanking.common.utils;

import org.apache.commons.lang.StringUtils;


/**
 * Copyright (c) 2011 GRG Banking Equipment Co.,Ltd. 
 * @createDate 2012-11-14
 * @author luowei 
 * @description 
 * 
 */
public class StringUtilsExtends extends StringUtils{
	
	/**
	 * @author:luowei
	 * @param strs 
	 * @createDate 2012-11-14
	 * @return boolean
	 * @description
	 * 当为多个时全部不为空则返回true,只要有一个为空则返回false
	 */
	public static boolean isNotBlankAndEmpty(String... strs){
		if(null == strs)
			return false;
		for(String str :strs){
			if(isBlank(str)||isEmpty(str)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @author:luowei
	 * @param strs 
	 * @createDate 2012-11-14
	 * @return boolean
	 * @description
	 * 当为多个时全部为空则返回true,只要有一个不为空则返回false
	 */
	public static boolean isBlankOrEmpty(String... strs){
		if(null == strs)
			return true;
		for(String str :strs){
			if(isNotBlank(str)&&isNotEmpty(str)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @author:wwb
	 * @param str
	 * @param parms
	 * @return
	 */
	public static String replaceStrings(String str,String... parms){
		if(parms==null){
			return str;
		}
		for (int i = 0; i < parms.length; i++) {
			str = str.replace("{"+i+"}", parms[i]);
		}
		return str;
	}

	/**
	 * @author:luowei
	 * @createDate 2012-11-14
	 * @param args void
	 * @description
	 * 
	 */
	public static void main(String[] args) {
		System.out.print(isNotBlankAndEmpty(" ","1"));
		System.out.print(isNotBlankAndEmpty(null," "));
		System.out.print(isNotBlankAndEmpty(""));
		System.out.print(isBlankOrEmpty(null," "));
	}

}
