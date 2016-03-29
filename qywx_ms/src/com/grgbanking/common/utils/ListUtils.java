/**
 * 
 */
package com.grgbanking.common.utils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * Copyright (c) 2011 GRG Banking Equipment Co.,Ltd. 
 * @createDate 2012-11-9
 * @author luowei 
 * @description 
 * 
 */
@SuppressWarnings("all")
public class ListUtils extends org.apache.commons.collections.ListUtils{
	
	/**
	 * @author:luowei
	 * @createDate 2012-11-9
	 * @param list
	 * @return boolean
	 * @description
	 * 判断List是否为空
	 */
	public static boolean isNotEmpty(List list){
		if(null != list && list.size()>0)
			return true;
		else
			return false;
	}
	
	/**
	 * @author:luowei
	 * @createDate 2012-12-14
	 * @param list
	 * @return List
	 * @description
	 * 去重复,且保持LIST原排序结果
	 */
	public static List distinctList(List list){
		List newlist = new ArrayList();
		Set set = new LinkedHashSet();
		set.addAll(list);
		newlist.addAll(set);
		return newlist;
	}

	/**
	 * @author:luowei
	 * @createDate 2012-11-9
	 * @param args void
	 * @description
	 * 
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		System.out.print(isNotEmpty(list)+"\n");
		list.add("123");
		System.out.print(isNotEmpty(list)+"\n");
	}

}
