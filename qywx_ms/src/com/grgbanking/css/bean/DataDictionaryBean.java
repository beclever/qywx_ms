package com.grgbanking.css.bean;

/**
 * 数据字典实体对应的BEAN
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved. 
 * Author: ChenFei
 * Date: 2010-6-22 上午09:48:09
 */
public class DataDictionaryBean {
	/*主键ID*/
	private Integer dictionaryId;
	
	/*数据字典名称*/
	private String text;
	
	/*数据字典值*/
	private String value;
	
	/*数据字典KEY*/
	private String key;
	
	/*数据字典描述信息*/
	private String description;
	
	/*优先显示顺序*/
	private Integer orderby;//不能用sort,与分页组件排序的冲突
	
	/*上级数据字典ID*/
	private Integer parentId;

	public Integer getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrderby() {
		return orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	} 
	
	
}
