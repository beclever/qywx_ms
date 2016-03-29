package com.grgbanking.css.bean.equipment;

/**
 * 树形表格BEAN
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-7-23 上午09:09:02
 */
public class EquipmentConfigTreeBean {
	private Integer equipmentConfigId;//当前配置ID
	private String nodeId; 
	private String nodeClass; 
	private String nodeType;//file or folder
	private Integer sparepartId;//物料ID
	private String moduleName;//模块名称
	private String serialNumber;//模块条码编码
	private String moduleModel;//模块型号
	private String materialCode;//物料编码
	private String hardwareVersion;//硬件版本
	private String softwareVersion;//软件版本
	private String moduleType;//模块类型
	private String bgcolor;
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeClass() {
		return nodeClass;
	}
	public void setNodeClass(String nodeClass) {
		this.nodeClass = nodeClass;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getModuleModel() {
		return moduleModel;
	}
	public void setModuleModel(String moduleModel) {
		this.moduleModel = moduleModel;
	}
	public String getHardwareVersion() {
		return hardwareVersion;
	}
	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	public Integer getEquipmentConfigId() {
		return equipmentConfigId;
	}
	public void setEquipmentConfigId(Integer equipmentConfigId) {
		this.equipmentConfigId = equipmentConfigId;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public Integer getSparepartId() {
		return sparepartId;
	}
	public void setSparepartId(Integer sparepartId) {
		this.sparepartId = sparepartId;
	}
	
}
