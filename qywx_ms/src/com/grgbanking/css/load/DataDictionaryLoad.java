package com.grgbanking.css.load;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.grgbanking.core.entity.DataDictionaryBean;
import com.grgbanking.css.bean.DataDictionary;
import com.grgbanking.css.util.FrameworkConstants;

/**
 * 数据字典加载器
 * 由于数据字典在系统中用的非常频繁，为了减去反复查询数据库开销，在应用启动时加载所有数据字典表数据
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-31 下午08:31:49
 */
public class DataDictionaryLoad {
	
	private static List<DataDictionary> list = new ArrayList<DataDictionary>();
	
	/**
	 * 初始化数据字典加载
	 * @param event
	 */
	public static void init(ServletContext servletContext){
		CommonService commonService = (CommonService) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("commonService");
		list = commonService.findDataDictionary(null, new DataDictionary());
		servletContext.setAttribute(FrameworkConstants.APPLICATION_KEY_DATA_DICTIONARY_LIST, list);
	}
	
	
	/**
	 * 根据KEY获取一组数据字典列表
	 * @param key
	 * @return
	 */
	public static List<DataDictionary> getListByKey(String key){
		List<DataDictionary> resultList=new ArrayList<DataDictionary>();
		for (DataDictionary dataDictionary : list) {
			if(dataDictionary.getKey().equals(key)){
				resultList.add(dataDictionary);
			}
		}
		return resultList;
	}
	
	/**
	 * 根据KEY获取一组数据字典列表
	 * @param key
	 * @return
	 */
	public static List<JSONObject> getJSONListByKey(String key){
		List<JSONObject> resultList=new ArrayList<JSONObject>();
		JSONObject jsonObject = null;
		for (DataDictionary dataDictionary : list) {
			if(dataDictionary.getKey().equals(key)){
				jsonObject = new JSONObject();
				jsonObject.element("value", dataDictionary.getValue());
				jsonObject.element("text", dataDictionary.getText());
				resultList.add(jsonObject);
			}
		}
		return resultList;
	}
	
	/**
	 * 根据KEY获取一组数据字典列表
	 * @param key
	 * @return
	 */
	public static List<DataDictionaryBean> getDataDictionaryBeanListByKey(String key){
		List<DataDictionaryBean> resultList=new ArrayList<DataDictionaryBean>();
		DataDictionaryBean dataDictionaryBean = null;
		for (DataDictionary dataDictionary : list) {
			if(dataDictionary.getKey().equals(key)){
				dataDictionaryBean = new DataDictionaryBean(dataDictionary.getValue(), dataDictionary.getText());
				resultList.add(dataDictionaryBean);
			}
		}
		return resultList;
	}


	/**
	 * 用于级联数据字典查询，根据上级ID，查询子列表
	 * @param parentId
	 * @return 
	 */
	public static List<DataDictionary> getListByParent(Integer parentId) {
		List<DataDictionary> resultList=new ArrayList<DataDictionary>();
		for (DataDictionary dataDictionary : list) {
			if(dataDictionary.getParentId()!=null&&dataDictionary.getParentId().intValue()==parentId.intValue()){
				resultList.add(dataDictionary);
			}
		}
		return resultList;
	}
	
	public static String getText(String key,String value){
		if(StringUtils.isBlank(value)){
			return null;
		}
		
		
		List<DataDictionary> list=getListByKey(key);
		for (DataDictionary dataDictionary : list) {
			if(dataDictionary.getValue().equals(value.trim())){
				return dataDictionary.getText();
			}
		}
		return null;
	}
	
	public static String getTextByParent(String parentKey,String parentValue,String value){
		if(StringUtils.isBlank(value)){
			return null;
		}
		DataDictionary parentDD = get(parentKey, parentValue);
		if(parentDD == null){
			return null;
		}
		List<DataDictionary> list=getListByParent(parentDD.getDataDictionaryId());
		for (DataDictionary dataDictionary : list) {
			if(dataDictionary.getValue().equals(value.trim())){
				return dataDictionary.getText();
			}
		}
		return null;
	}
	
	public static DataDictionary get(String key,String value){
		if(StringUtils.isEmpty(value)){
			return null;
		}
		List<DataDictionary> list=getListByKey(key);
		for (DataDictionary dataDictionary : list) {
			if(dataDictionary.getValue().equals(value)){
				return dataDictionary;
			}
		}
		return null;
	}
	
	public static String getValueByText(String key,String text){
		if(StringUtils.isEmpty(text)){
			return "";
		}
		
		List<DataDictionary> list = getListByKey(key);
		
		for (DataDictionary dataDictionary : list) {
			if(dataDictionary.getText().equalsIgnoreCase(text.toString())){
				return dataDictionary.getValue();
			}
		}
		
		return "";
	}

}
