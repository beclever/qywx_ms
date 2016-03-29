package com.grgbanking.css.bean;

import java.util.Date;
import java.util.List;

import com.grgbanking.css.bean.equipment.EquipmentInfo;

/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: Administrator
 * Date: 2010-7-28 下午09:37:15
 */
public class UpgradePlanBean {

	private Integer upgradeId; // 升级计划ID
	private String upgradeCode;  //升级项目代码（编号）
	private String subject; // 升级主题
	private String upgradeType;    //升级类型
	/*计划开始时间*/
	private Date beginDate;
	/*计划完成时间*/
	private Date endDate;

	/*拟制人*/
	private String preparedBy;
	private String status; // 状态
	private Date publishDate; // 发布日期
	private Date shipDateStart; // 发货开始时间
	private Date shipDateEnd; // 发货结束时间
	private String equipmentBatchNumber; // 设备生产批次
	private String equipmentModels; // 升级设备型号
	private String equipmentModelDictValues;
	private String customerIds; // 升级客户IDS
	private String customerNames;
	private String departmentIds; // 服务站IDS
	private String departmentNames;
	private String equipmentTypes; // 升级设备类型
	private String equipmentTypeDictIds;
	private String equipmentTypeDictValues;
	private String serialNumbers; // 升级设备序列号
	private String manufacturer; // 制造商
	private Date publishStartDate;   //用于查询计划发布时间段
	private Date publishEndDate;
	private Date planStartDate;   //用于查询计划完成时间段
	private Date planEndDate;
	private List<EquipmentInfo> equipmentList; // 设备集合
	private String type;    //类型
	
	private String equipmentStatus;//设备状态
	private String warrantyStatus;//维保状态
	
	private String bulletinCode;//升级通告编码
	
	private String result;//完成结果
	
	private String sparepartName;//一级模块
	private String hardwareVersion;//硬件版本
	private String atmcName;//ATMC名称
	
	private String projectType;//项目类别
	private String isStop;//暂停标志
	
	//===========版本信息==============
	private String modelType;//模块类型
	private String key;//键值
	private String value;//版本
	private String targetVersion;//目标版本
	private String issueCause; //发布原因
	//===========版本信息==============
	
	public String getEquipmentModelDictValues() {
		return equipmentModelDictValues;
	}

	public String getTargetVersion() {
		return targetVersion;
	}

	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}

	public String getEquipmentTypeDictIds() {
		return equipmentTypeDictIds;
	}

	public void setEquipmentTypeDictIds(String equipmentTypeDictIds) {
		this.equipmentTypeDictIds = equipmentTypeDictIds;
	}

	public void setEquipmentModelDictValues(String equipmentModelDictValues) {
		this.equipmentModelDictValues = equipmentModelDictValues;
	}


	public String getEquipmentTypeDictValues() {
		return equipmentTypeDictValues;
	}

	public void setEquipmentTypeDictValues(String equipmentTypeDictValues) {
		this.equipmentTypeDictValues = equipmentTypeDictValues;
	}

	public String getCustomerNames() {
		return customerNames;
	}

	public void setCustomerNames(String customerNames) {
		this.customerNames = customerNames;
	}

	public String getDepartmentNames() {
		return departmentNames;
	}

	public void setDepartmentNames(String departmentNames) {
		this.departmentNames = departmentNames;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	private Integer departmentId;
	
	public UpgradePlanBean() { }

	
	public String toString(){
		return " upgradeId ="+upgradeId+", upgradeCode="+upgradeCode+", subject="+subject+", upgradeType="+upgradeType
				+", beginDate="+beginDate+", endDate="+endDate+", status="+status+", publishStartDate="+publishStartDate+", publishEndDate ="+publishEndDate;
	}
	
	
	public Integer getUpgradeId() {
		return upgradeId;
	}

	public void setUpgradeId(Integer upgradeId) {
		this.upgradeId = upgradeId;
	}

	public String getUpgradeCode() {
		return upgradeCode;
	}

	public void setUpgradeCode(String upgradeCode) {
		this.upgradeCode = upgradeCode;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUpgradeType() {
		return upgradeType;
	}

	public void setUpgradeType(String upgradeType) {
		this.upgradeType = upgradeType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getShipDateStart() {
		return shipDateStart;
	}

	public void setShipDateStart(Date shipDateStart) {
		this.shipDateStart = shipDateStart;
	}

	public Date getShipDateEnd() {
		return shipDateEnd;
	}

	public void setShipDateEnd(Date shipDateEnd) {
		this.shipDateEnd = shipDateEnd;
	}

	public String getEquipmentBatchNumber() {
		return equipmentBatchNumber;
	}

	public void setEquipmentBatchNumber(String equipmentBatchNumber) {
		this.equipmentBatchNumber = equipmentBatchNumber;
	}

	public String getEquipmentModels() {
		return equipmentModels;
	}

	public void setEquipmentModels(String equipmentModels) {
		this.equipmentModels = equipmentModels;
	}

	public String getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

	public String getEquipmentTypes() {
		return equipmentTypes;
	}

	public void setEquipmentTypes(String equipmentTypes) {
		this.equipmentTypes = equipmentTypes;
	}

	public String getSerialNumbers() {
		return serialNumbers;
	}

	public void setSerialNumbers(String serialNumbers) {
		this.serialNumbers = serialNumbers;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Date getPublishStartDate() {
		return publishStartDate;
	}

	public void setPublishStartDate(Date publishStartDate) {
		this.publishStartDate = publishStartDate;
	}

	public Date getPublishEndDate() {
		return publishEndDate;
	}

	public void setPublishEndDate(Date publishEndDate) {
		this.publishEndDate = publishEndDate;
	}

	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public List<EquipmentInfo> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<EquipmentInfo> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public String getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public String getWarrantyStatus() {
		return warrantyStatus;
	}

	public void setWarrantyStatus(String warrantyStatus) {
		this.warrantyStatus = warrantyStatus;
	}

	public String getBulletinCode() {
		return bulletinCode;
	}

	public void setBulletinCode(String bulletinCode) {
		this.bulletinCode = bulletinCode;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getSparepartName() {
		return sparepartName;
	}

	public void setSparepartName(String sparepartName) {
		this.sparepartName = sparepartName;
	}

	public String getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	public String getAtmcName() {
		return atmcName;
	}

	public void setAtmcName(String atmcName) {
		this.atmcName = atmcName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getIssueCause() {
		return issueCause;
	}

	public void setIssueCause(String issueCause) {
		this.issueCause = issueCause;
	}
	
	
}
