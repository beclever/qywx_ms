package com.grgbanking.css.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.grgbanking.css.common.CssBaseEntity;

/**
 * 
 * 下拉框数据字典表
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-31 下午04:59:05
 */
@Entity
@Table(name="V_BASE_DATA_DICTIONARY")
public class DataDictionary extends CssBaseEntity{
	
	private Integer dataDictionaryId;
	// 标识（根据该标识得到一组数据）
	private String key;
	// 显示的文本
	private String text;
	// 值
	private String value;
	// 排序
	private Integer orderby;
	//如果级联的就需要有上级
	private Integer parentId;
	//描述信息
	private String description;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="GENERATOR_SEQ_BASE_DATA_DICTIONARY")    
	@SequenceGenerator(name="GENERATOR_SEQ_BASE_DATA_DICTIONARY", sequenceName="SEQ_BASE_DATA_DICTIONARY",initialValue=1,allocationSize=1)
	@Column(name="ID")
	public Integer getDataDictionaryId() {
		return dataDictionaryId;
	}
	public void setDataDictionaryId(Integer dataDictionaryId) {
		this.dataDictionaryId = dataDictionaryId;
	}
	@Column(name="KEY")
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Column(name="TEXT")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Column(name="VALUE")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name="ORDER_BY")
	public Integer getOrderby() {
		return orderby;
	}
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	@Column(name="PARENT_ID")
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
