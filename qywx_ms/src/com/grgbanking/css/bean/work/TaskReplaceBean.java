/**
 * TaskReplaceItem.java
 * Product:Grgbanking
 * Version:1.0
 * Copyright 2009 by DNE
 * All Rights Reserved.
 */
package com.grgbanking.css.bean.work;


import com.grgbanking.css.common.CssBaseEntity;

/**
 * 服务替换备件明细VO
 * @ClassName com.grgbanking.services.pojo.TaskReplaceItem
 * @Author Zhang Zhi
 * @Version 1.0
 * @Date 2009-7-16 上午11:56:17
 */
public class TaskReplaceBean extends CssBaseEntity {
	private Integer replaceId;
	private Integer oldEquipmentConfigId;
	private String oldSerialNumber;//替换前备件序列号
	private String oldMaterialName;
	private String oldModuleType;
	private String oldMaterialCode;
	private String oldHardwareVersion;
	private String oldSoftwareVersion;
	
	private String newSerialNumber;//替换后备件序列号
	private String newHardwareVersion;//替换后硬件版本
	private String newSoftwareVersion;//替换后软件版本
	private String newMaterialName;
	private String newModuleType;
	private String newMaterialCode;
	
	private String replaceType;//替换类型
	private Integer replaceAmount;//替换数量
	private String checkContent;//保养检查内容
	private String checkContentCode;//保养检查内容编码
	private Integer checkContentId;
	private String type;//判断是一二级模块替换或零备件替换
	private Integer borrowId;//针对非一二级模块，与借用单关联。
	private Integer borrowDetailId;
	private String stopModify;//由已完成的工单退回来后，替换件不可以修改。
	public Integer getReplaceId() {
		return replaceId;
	}
	public void setReplaceId(Integer replaceId) {
		this.replaceId = replaceId;
	}
	public String getOldSerialNumber() {
		return oldSerialNumber;
	}
	public void setOldSerialNumber(String oldSerialNumber) {
		this.oldSerialNumber = oldSerialNumber;
	}
	public String getOldMaterialName() {
		return oldMaterialName;
	}
	public void setOldMaterialName(String oldMaterialName) {
		this.oldMaterialName = oldMaterialName;
	}
	public String getOldModuleType() {
		return oldModuleType;
	}
	public void setOldModuleType(String oldModuleType) {
		this.oldModuleType = oldModuleType;
	}
	public String getOldMaterialCode() {
		return oldMaterialCode;
	}
	public void setOldMaterialCode(String oldMaterialCode) {
		this.oldMaterialCode = oldMaterialCode;
	}
	public String getOldHardwareVersion() {
		return oldHardwareVersion;
	}
	public void setOldHardwareVersion(String oldHardwareVersion) {
		this.oldHardwareVersion = oldHardwareVersion;
	}
	public String getOldSoftwareVersion() {
		return oldSoftwareVersion;
	}
	public void setOldSoftwareVersion(String oldSoftwareVersion) {
		this.oldSoftwareVersion = oldSoftwareVersion;
	}
	public String getNewSerialNumber() {
		return newSerialNumber;
	}
	public void setNewSerialNumber(String newSerialNumber) {
		this.newSerialNumber = newSerialNumber;
	}
	public String getNewHardwareVersion() {
		return newHardwareVersion;
	}
	public void setNewHardwareVersion(String newHardwareVersion) {
		this.newHardwareVersion = newHardwareVersion;
	}
	public String getNewSoftwareVersion() {
		return newSoftwareVersion;
	}
	public void setNewSoftwareVersion(String newSoftwareVersion) {
		this.newSoftwareVersion = newSoftwareVersion;
	}
	public String getNewMaterialName() {
		return newMaterialName;
	}
	public void setNewMaterialName(String newMaterialName) {
		this.newMaterialName = newMaterialName;
	}
	public String getNewModuleType() {
		return newModuleType;
	}
	public void setNewModuleType(String newModuleType) {
		this.newModuleType = newModuleType;
	}
	public String getNewMaterialCode() {
		return newMaterialCode;
	}
	public void setNewMaterialCode(String newMaterialCode) {
		this.newMaterialCode = newMaterialCode;
	}
	public String getReplaceType() {
		return replaceType;
	}
	public void setReplaceType(String replaceType) {
		this.replaceType = replaceType;
	}
	public Integer getReplaceAmount() {
		return replaceAmount;
	}
	public void setReplaceAmount(Integer replaceAmount) {
		this.replaceAmount = replaceAmount;
	}
	public String getCheckContent() {
		return checkContent;
	}
	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
	public String getCheckContentCode() {
		return checkContentCode;
	}
	public void setCheckContentCode(String checkContentCode) {
		this.checkContentCode = checkContentCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getOldEquipmentConfigId() {
		return oldEquipmentConfigId;
	}
	public void setOldEquipmentConfigId(Integer oldEquipmentConfigId) {
		this.oldEquipmentConfigId = oldEquipmentConfigId;
	}
	public Integer getCheckContentId() {
		return checkContentId;
	}
	public void setCheckContentId(Integer checkContentId) {
		this.checkContentId = checkContentId;
	}
	public Integer getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	public Integer getBorrowDetailId() {
		return borrowDetailId;
	}
	public void setBorrowDetailId(Integer borrowDetailId) {
		this.borrowDetailId = borrowDetailId;
	}
	public String getStopModify() {
		return stopModify;
	}
	public void setStopModify(String stopModify) {
		this.stopModify = stopModify;
	}
}
