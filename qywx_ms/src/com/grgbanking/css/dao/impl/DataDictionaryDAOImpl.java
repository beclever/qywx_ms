package com.grgbanking.css.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.DataDictionary;
import com.grgbanking.css.bean.DataDictionaryBean;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.dao.DataDictionaryDAO;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.HqlHelper;

/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-31 下午05:32:57
 */
@Repository("dataDictionaryDAO")
public class DataDictionaryDAOImpl extends CssBaseDAOImpl<DataDictionary, Integer> implements
		DataDictionaryDAO<DataDictionary, Integer> {

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.common.DataDictionaryDAO#findDataDictionaryList(com.grgbanking.framework.core.Page, com.grgbanking.soc.entity.common.DataDictionary)
	 */
	public List<DataDictionary> findDataDictionaryList(Page queryPage,
			DataDictionary dataDictionaryBean) {
		HqlHelper hqlHelper=new HqlHelper(DataDictionary.class);
		hqlHelper.addEqualIgnoreCase("key", dataDictionaryBean.getKey());
		hqlHelper.addLikeIgnoreCase("text", dataDictionaryBean.getText());
		hqlHelper.addLikeIgnoreCase("description", dataDictionaryBean.getDescription());
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addOrderBy("key", "asc");
		hqlHelper.addOrderBy("orderby", "asc");
		hqlHelper.setQueryPage(queryPage);
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.common.DataDictionaryDAO#getDataDictionaryList(com.grgbanking.framework.core.Page, com.grgbanking.soc.bean.common.DataDictionaryBean)
	 */
	public List<DataDictionary> getDataDictionaryList(Page queryPage, DataDictionaryBean dataDictionaryBean) {
		
		HqlHelper or=new HqlHelper();
		or.addLikeIgnoreCase("text", dataDictionaryBean.getText());
		or.addLikeIgnoreCase("description", dataDictionaryBean.getText());
		or.addLikeIgnoreCase("key", dataDictionaryBean.getKey());
		
		HqlHelper hqlHelper=new HqlHelper(DataDictionary.class);
		hqlHelper.addOr(or);
		hqlHelper.setQueryPage(queryPage);
		hqlHelper.addOrderBy("key", "asc");
		hqlHelper.addOrderBy("orderby", "asc");
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.common.DataDictionaryDAO#getAllDataDictionaryList(com.grgbanking.soc.bean.common.DataDictionaryBean)
	 */
	public List<DataDictionary> getAllDataDictionaryList(
			Integer...integers) {
		HqlHelper hqlHelper=new HqlHelper(DataDictionary.class);
		hqlHelper.addIn("dataDictionaryId", integers);
		return this.find(hqlHelper);
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.common.DataDictionaryDAO#getListByKey(java.lang.String)
	 */
	public List<DataDictionary> getListByKey(String key) {
		HqlHelper hqlHelper=new HqlHelper(DataDictionary.class);
		hqlHelper.addEqual("key",key);
		hqlHelper.addOrderBy("orderby", "asc");
		hqlHelper.addEqual("deleted", "N");
		return this.find(hqlHelper);
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.common.DataDictionaryDAO#getMap(java.lang.String)
	 */
	public Map<String, String> getMap(String key) {
		HqlHelper hqlHelper=new HqlHelper(DataDictionary.class);
		hqlHelper.addEqual("key",key);
		List<DataDictionary> list= this.find(hqlHelper);
		Map<String,String> map=new HashMap<String, String>();
		for (DataDictionary dataDictionary : list) {
			map.put(dataDictionary.getValue(), dataDictionary.getText());
		}
		return map;
	}

	public DataDictionary getDataDictionaryByLessValue(String key, String value) {
		HqlHelper hqlHelper=new HqlHelper(DataDictionary.class);
		hqlHelper.addEqualIgnoreCase("key",key);
		hqlHelper.addLessThan("value", value);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addOrderBy("value", "desc");
		List<DataDictionary> list=this.find(hqlHelper);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}
