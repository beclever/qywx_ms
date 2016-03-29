package com.grgbanking.css.dao;

import java.util.List;
import java.util.Map;

import com.grgbanking.css.bean.DataDictionary;
import com.grgbanking.css.bean.DataDictionaryBean;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;


/**
 * 数据字典DAO访问对象
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-31 下午05:30:47
 */
public interface DataDictionaryDAO<T, ID> extends CssBaseDAO<T, ID> {

	/**
	 * 根据条件查询数据字典列表
	 * @param queryPage
	 * @param dataDictionaryBean
	 * @return
	 */
	public List<DataDictionary> findDataDictionaryList(Page queryPage,DataDictionary dataDictionaryBean);
	
	/**
	 * 根据条件查询数据字典列表，此数据进行封装后返回数据字典管理页面
	 * @param queryPage
	 * @param dataDictionaryBean 此参数为DataDictionary的封装Bean
	 * @return
	 */
	public List<DataDictionary> getDataDictionaryList(Page queryPage, DataDictionaryBean dataDictionaryBean);
	
	/**
	 * 返回所有查到的数据字典列表
	 */
	public List<DataDictionary> getAllDataDictionaryList(Integer...integers);
	
	/**
	 * 根据值查询出数据字典列表
	 * @param strings
	 * @return
	 */
//	public List<DataDictionary> getDataToValues(String...strings);

	/**
	 * @param key
	 * @return
	 */
	public List<DataDictionary> getListByKey(String key);
	
	/**
	 * <value,text>
	 * @param key
	 * @return
	 */
	public Map<String,String> getMap(String key);
	
	/**
	 * 小于value的值
	 * @param key
	 * @param value
	 * @return
	 */
	public DataDictionary getDataDictionaryByLessValue(String key,String value);
}
