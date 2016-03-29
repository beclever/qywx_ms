package com.grgbanking.css.bean.equipment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 设备日志信息实体类
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-7-21 下午03:15:16
 */
@Entity
@Table(name="V_EQUIPMENT_INFO_LOG")
public class EquipmentLog {
	private Integer equipmentLogId;
	private String content;
	private Integer userId;
	private String username;
	private String referenceNo;
	private Date createDate;
	
	@Id  	
	@SequenceGenerator(name = "SEQ_EQUIPMENT_INFO_LOG_GENERATOR", sequenceName = "SEQ_EQUIPMENT_INFO_LOG", initialValue = 1, allocationSize = 1)   
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EQUIPMENT_INFO_LOG_GENERATOR")   
	@Column(name="EQUIPMENT_LOG_ID")
	public Integer getEquipmentLogId() {
		return equipmentLogId;
	}
	public void setEquipmentLogId(Integer equipmentLogId) {
		this.equipmentLogId = equipmentLogId;
	}
	
	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="USER_ID")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name="USER_NAME")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="REFERENCE_NO")
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
