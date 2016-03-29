/**
 * TaskReplaceItem.java
 * Product:Grgbanking
 * Version:1.0
 * Copyright 2009 by DNE
 * All Rights Reserved.
 */
package com.grgbanking.css.bean.work;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.grgbanking.css.bean.Sparepart;

/**
 * 服务替换备件明细实体类
 * @ClassName com.grgbanking.services.pojo.TaskReplaceItem
 * @Author Zhang Zhi
 * @Version 1.0
 * @Date 2009-7-16 上午11:56:17
 */
@Entity
@Table(name = "V_SERVICES_TASK_REPLACE")
public class TaskReplace{
	private Integer replaceId;
	private Integer oldEquipmentConfigId;
	private String oldSerialNumber;//替换前备件序列号
	private Sparepart oldSparepart;//替换前物料
	private String oldHardwareVersion;
	private String oldSoftwareVersion;
	
	private String newSerialNumber;//替换后备件序列号
	private Sparepart newSparepart;//替换前物料
	private String newHardwareVersion;//替换后硬件版本
	private String newSoftwareVersion;//替换后软件版本
	
	private String replaceType;//替换类型(新增，替换)
	private Integer replaceAmount;//替换数量
	private Integer checkContentId;
	private String checkContent;//保养检查内容
	private String checkContentCode;//保养检查内容编码
	private WorkTask workTask;//关联的任务信息
	private Date createDate;
	private Integer borrowId;//针对零备件，与借用单关联。
	private Integer borrowDetailId;//针对一二级模块，与借用单关联。
	private String stopModify;//由已完成的工单退回来后，替换件不可以修改。
	@Id  
	@SequenceGenerator(name = "SERVICES_TASK_REPLACEITEM_SEQ_GENERATOR", sequenceName = "SEQ_SERVICES_TASK_REPLACE", initialValue = 1, allocationSize = 1)   
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICES_TASK_REPLACEITEM_SEQ_GENERATOR")   
	@Column(name="REPLACE_ID")
	public Integer getReplaceId() {
		return replaceId;
	}
	public void setReplaceId(Integer replaceId) {
		this.replaceId = replaceId;
	}
	
	@Column(name="OLD_SERIAL_NUMBER")
	public String getOldSerialNumber() {
		return oldSerialNumber;
	}
	public void setOldSerialNumber(String oldSerialNumber) {
		this.oldSerialNumber = oldSerialNumber;
	}
	
	@Column(name="NEW_SERIAL_NUMBER")
	public String getNewSerialNumber() {
		return newSerialNumber;
	}
	public void setNewSerialNumber(String newSerialNumber) {
		this.newSerialNumber = newSerialNumber;
	}
	
	@Column(name="REPLACE_TYPE")
	public String getReplaceType() {
		return replaceType;
	}
	public void setReplaceType(String replaceType) {
		this.replaceType = replaceType;
	}
	
	@Column(name="QUANTITY")
	public Integer getReplaceAmount() {
		return replaceAmount;
	}
	public void setReplaceAmount(Integer replaceAmount) {
		this.replaceAmount = replaceAmount;
	}
	
	@Column(name="MAINTAIN_CHECK_CONTENT")
	public String getCheckContent() {
		return checkContent;
	}
	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
	
	@Column(name="MAINTAIN_CHECK_CODE")
	public String getCheckContentCode() {
		return checkContentCode;
	}
	public void setCheckContentCode(String checkContentCode) {
		this.checkContentCode = checkContentCode;
	}
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.LAZY )
    @JoinColumn(name="TASK_ID")
	public WorkTask getWorkTask() {
		return workTask;
	}
	public void setWorkTask(WorkTask workTask) {
		this.workTask = workTask;
	}
	
	@Column(name="new_hardware_version")
	public String getNewHardwareVersion() {
		return newHardwareVersion;
	}
	public void setNewHardwareVersion(String newHardwareVersion) {
		this.newHardwareVersion = newHardwareVersion;
	}
	@Column(name="new_software_version")
	public String getNewSoftwareVersion() {
		return newSoftwareVersion;
	}
	public void setNewSoftwareVersion(String newSoftwareVersion) {
		this.newSoftwareVersion = newSoftwareVersion;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OLD_SPAREPART_ID")
	public Sparepart getOldSparepart() {
		return oldSparepart;
	}
	public void setOldSparepart(Sparepart oldSparepart) {
		this.oldSparepart = oldSparepart;
	}
	
	@Column(name="OLD_HARDWARE_VERSION")
	public String getOldHardwareVersion() {
		return oldHardwareVersion;
	}
	public void setOldHardwareVersion(String oldHardwareVersion) {
		this.oldHardwareVersion = oldHardwareVersion;
	}
	@Column(name="OLD_SOFTWARE_VERSION")
	public String getOldSoftwareVersion() {
		return oldSoftwareVersion;
	}
	
	public void setOldSoftwareVersion(String oldSoftwareVersion) {
		this.oldSoftwareVersion = oldSoftwareVersion;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NEW_SPAREPART_ID")
	public Sparepart getNewSparepart() {
		return newSparepart;
	}
	public void setNewSparepart(Sparepart newSparepart) {
		this.newSparepart = newSparepart;
	}
	public Integer getOldEquipmentConfigId() {
		return oldEquipmentConfigId;
	}
	@Column(name="OLD_EQUIPMENT_CONFIG_ID")
	public void setOldEquipmentConfigId(Integer oldEquipmentConfigId) {
		this.oldEquipmentConfigId = oldEquipmentConfigId;
	}
	@Column(name="CHECK_CONTENT_ID")
	public Integer getCheckContentId() {
		return checkContentId;
	}
	public void setCheckContentId(Integer checkContentId) {
		this.checkContentId = checkContentId;
	}
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="BORROW_ID")
	public Integer getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	@Column(name="BORROW_DETAIL_ID")
	public Integer getBorrowDetailId() {
		return borrowDetailId;
	}
	public void setBorrowDetailId(Integer borrowDetailId) {
		this.borrowDetailId = borrowDetailId;
	}
	@Column(name="STOP_MODIFY")
	public String getStopModify() {
		return stopModify;
	}
	public void setStopModify(String stopModify) {
		this.stopModify = stopModify;
	}
	
}
