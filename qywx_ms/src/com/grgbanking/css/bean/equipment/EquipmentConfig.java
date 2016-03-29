package com.grgbanking.css.bean.equipment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.grgbanking.css.bean.Sparepart;
import com.grgbanking.css.common.CssBaseEntity;

/**
 * 整机当前配置信息
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-7-23 上午08:43:02
 */
@Entity
@Table(name = "V_EMS_EQUIPMENT_CONFIG")
public class EquipmentConfig extends CssBaseEntity  implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer equipmentConfigId;
	private Sparepart sparepart; //关联物料
	private String serialNumber;//条码编码
	private Date warrantyStartDate; //保修开始日期
	private Date warrantyEndDate; //保修结束日期
	private Date madeDate; //制造日期
	private EquipmentConfig parent;//对于二级模块，它有上一级模块。
	
	private EquipmentInfo equipment; //与此模块关联的整机
	private String hardwareVersion; //硬件版本
	private String softwareVersion; //软件版本
	private String factorySerialNumber;//厂商序列号
	private Long recordId;//SAP对应主键
	
	private List<EquipmentConfig> childrenList=new ArrayList<EquipmentConfig>(); //子模块
	
	private Integer materialMonth;
	
	@Id
	@SequenceGenerator(name="SEQ_EQUIPMENT_CONFIG_GENERATOR",sequenceName="SEQ_EQUIPMENT_CONFIG",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_EQUIPMENT_CONFIG_GENERATOR")
	@Column(name = "EQUIPMENT_CONFIG_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Integer getEquipmentConfigId() {
		return equipmentConfigId;
	}

	public void setEquipmentConfigId(Integer equipmentConfigId) {
		this.equipmentConfigId = equipmentConfigId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPAREPART_ID")
	public Sparepart getSparepart() {
		return sparepart;
	}

	public void setSparepart(Sparepart sparepart) {
		this.sparepart = sparepart;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public EquipmentConfig getParent() {
		return parent;
	}

	public void setParent(EquipmentConfig parent) {
		this.parent = parent;
	}


	@Column(name = "SERIAL_NUMBER")
	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "WARRANTY_START_DATE")
	public Date getWarrantyStartDate() {
		return this.warrantyStartDate;
	}

	public void setWarrantyStartDate(Date warrantyStartDate) {
		this.warrantyStartDate = warrantyStartDate;
	}

	@Column(name = "WARRANTY_END_DATE")
	public Date getWarrantyEndDate() {
		return this.warrantyEndDate;
	}

	public void setWarrantyEndDate(Date warrantyEndDate) {
		this.warrantyEndDate = warrantyEndDate;
	}

	@Column(name = "MADE_DATE")
	public Date getMadeDate() {
		return this.madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "EQUIPMENT_ID")
	public EquipmentInfo getEquipment() {
		return equipment;
	}

	public void setEquipment(EquipmentInfo equipment) {
		this.equipment = equipment;
	}

	@Column(name = "HARDWARE_VERSION")
	public String getHardwareVersion() {
		return this.hardwareVersion;
	}

	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	@Column(name = "SOFTWARE_VERSION")
	public String getSoftwareVersion() {
		return this.softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	@Column(name = "FACTORY_SERIAL_NUMBER")
	public String getFactorySerialNumber() {
		return factorySerialNumber;
	}

	public void setFactorySerialNumber(String factorySerialNumber) {
		this.factorySerialNumber = factorySerialNumber;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.ALL,org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "PARENT_ID")
	public List<EquipmentConfig> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<EquipmentConfig> childrenList) {
		this.childrenList = childrenList;
	}
	@Column(name = "RECORD_ID")
	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	@Column(name = "MATERIAL_MONTH")
	public Integer getMaterialMonth() {
		return materialMonth;
	}

	public void setMaterialMonth(Integer materialMonth) {
		this.materialMonth = materialMonth;
	}
	
}