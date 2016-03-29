package com.grgbanking.css.bean.work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grgbanking.css.bean.RepairPlace;
import com.grgbanking.css.bean.equipment.EquipmentInstallBean;

/**
 *服务（任务）信息
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: Administrator
 * Date: 2010-7-29 下午03:14:52
 */
public class WorkTaskBean {
	
	/**
	 * 主键ID
	 */
	private Integer taskId;
	/**
	 * 对应的工单ID
	 */
	private Integer workformId;
	
	/**
	 * 设备ID
	 */
	private Integer equipmentId;
	/**
	 * 服务内容
	 */
	private String taskContent;
	/**
	 * 预约时间
	 */
	private Date appointmentTime;
	/**
	 * 服务类型，如维修、安装、移机、升级、保养、巡检、其他
	 */
	private String taskType;
	/**
	 * 派遣工程师ID
	 */
	private Integer engineerId;
	/**
	 * 派遣工程师姓名
	 */
	private String engineerName;
	/**
	 * 工作耗时
	 */
	private Integer costTime;
	/**
	 * 工作描述
	 */
	private String description;
	/**
	 * 状态（待处理，处理中，已完成）
	 */
	private String status;
	/**
	 * 服务单号
	 */
	private String processNo;
	/**
	 * 升级项目编码
	 */
	private String upgradeCode;
	/**
	 * 升级项目名称
	 */
	private String upgradeName;
	/**
	 * 工作完成情况
	 */
	private String workStatus;
	/**
	 * 服务保修类型
	 */
	private String warrantyType;
	/**
	 * 电话处理单号
	 */
	private String TelProcessNo;
	/**
	 * 报修时间
	 */
	private Date reportTime;
	/**
	 * 报修电话
	 */
	private String reportTelephone;
	/**
	 * 设备信息所属部门id
	 */
	private Integer equipmentDeptId;
	
	/**
	 * 计划开始时间
	 */
	private Date planStartDate;
	/**
	 * 计划完成时间
	 */
	private Date planEndDate;
	/**
	 * 新操作系统版本
	 */
	private String newOsVersion;
	/**
	 * 新操作系统
	 */
	private String newOperateSystem;
	/**
	 * 新ATMC名称
	 */
	private String newAtmcName;
	/**
	 * 新ATMC版本
	 */
	private String newAtmcVersion;
	/**
	 * 新跨平台PS版本
	 */
	private String newAtmcPsVersion;
	/**
	 * 操作系统版本
	 */
	private String osVersion;
	/**
	 * 操作系统
	 */
	private String operateSystem;
	/**
	 * ATMC名称
	 */
	private String atmcName;
	/**
	 * ATMC版本
	 */
	private String atmcVersion;
	/**
	 * 跨平台PS版本
	 */
	private String atmcPsVersion;
	/**
	 * 来源
	 */
	private String taskSource;
	
	/**
	 * 来源(查询条件)
	 */
	private String[] taskSourceArray;
	
	private String[] equipmentStatusArray;//设备状态
	
	private String[] statusArray;
	
	
	// 创建日期
	protected Date createDate;
	// 更新日期
	protected Date modifyDate;
	// 创建人
	protected Integer createUserId;
	// 更新人
	protected Integer modifyUserId;
	// 版本号
	protected Integer version;
	// 是否删除
	protected String deleted;
	
	private String serialNumber;//设备序列号
	private String branchName;//网点名称
	private String installAddress;//安装地址
	private String customerName;//客户名称
	private String departmentName;//服务站名称
	private Integer departmentId;//服务站Id
	private String appointmentTimeStart;//预约时间
	private String appointmentTimeEnd;//预约时间
	private String departmentIds;
	private String customerIds;
	private String planStartDateStart;
	private String planStartDateEnd;
	private String planEndDateStart;
	private String planEndDateEnd;
	private EquipmentInstallBean equipmentInstallBean;//安装任务：设备安装信息
	private Integer telProcessId;//电话处理ID
	
	private Date workFinishDate;
	private Date workStartDate;
	
	private String poNumber;//工单号
	
	private String sjType; //巡检类型
	private String sjTypeDetails;//巡检类型详细
	private String sjTypeExplain;//巡检说明
	private String sjContentSoftware;//巡检内容软件检查
	private String sjContentHardware;//巡检内容硬件检查
	private String sjContentEnvironment;//巡检内容环境检查
	private String sjContentOther;//巡检内容其它
	private Integer createType;//创建来源
	
	private String equipmentChargeName;//设备负责人
	
	private String workFinishDateStart;
	private String workFinishDateEnd;
	
	//报修人与接待 人
	private Integer repairsContactId;
	private Integer receiveContactId;
	private String repairsManName;
	private String receiveManName;
	private String repairsMoblie;//手机
	private String receiveMoblie;
	private String repairsTelephone;//电话
	private String receiveTelephone;
	
	private String acceptStyle;//采取处理方式(SA,CSS)
	
	private Integer loginUserId;//手机接口调用时临时放的loginName
	
	
	//故障部位
	private List<RepairPlace> repairPlaceList=new ArrayList<RepairPlace>();
	//替换件
	private List<TaskReplaceBean> replaceList;
	
	private String taskLevel;//任务等级
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getAppointmentTimeStart() {
		return appointmentTimeStart;
	}
	public void setappointmentTimeStart(String appointmentTimeStart) {
		this.appointmentTimeStart = appointmentTimeStart;
	}
	public String getAppointmentTimeEnd() {
		return appointmentTimeEnd;
	}
	public void setappointmentTimeEnd(String appointmentTimeEnd) {
		this.appointmentTimeEnd = appointmentTimeEnd;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getInstallAddress() {
		return installAddress;
	}
	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public Integer getWorkformId() {
		return workformId;
	}
	public void setWorkformId(Integer workformId) {
		this.workformId = workformId;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getTaskContent() {
		return taskContent;
	}
	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	public Date getAppointmentTime() {
		return appointmentTime;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public Integer getEngineerId() {
		return engineerId;
	}
	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}
	public String getEngineerName() {
		return engineerName;
	}
	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	public Integer getCostTime() {
		return costTime;
	}
	public void setCostTime(Integer costTime) {
		this.costTime = costTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProcessNo() {
		return processNo;
	}
	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}
	public String getUpgradeCode() {
		return upgradeCode;
	}
	public void setUpgradeCode(String upgradeCode) {
		this.upgradeCode = upgradeCode;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public String getWarrantyType() {
		return warrantyType;
	}
	public void setWarrantyType(String warrantyType) {
		this.warrantyType = warrantyType;
	}
	public String getTelProcessNo() {
		return TelProcessNo;
	}
	public void setTelProcessNo(String telProcessNo) {
		TelProcessNo = telProcessNo;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public String getReportTelephone() {
		return reportTelephone;
	}
	public void setReportTelephone(String reportTelephone) {
		this.reportTelephone = reportTelephone;
	}
	public Integer getEquipmentDeptId() {
		return equipmentDeptId;
	}
	public void setEquipmentDeptId(Integer equipmentDeptId) {
		this.equipmentDeptId = equipmentDeptId;
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
	public String getNewOsVersion() {
		return newOsVersion;
	}
	public void setNewOsVersion(String newOsVersion) {
		this.newOsVersion = newOsVersion;
	}
	public String getNewOperateSystem() {
		return newOperateSystem;
	}
	public void setNewOperateSystem(String newOperateSystem) {
		this.newOperateSystem = newOperateSystem;
	}
	public String getNewAtmcName() {
		return newAtmcName;
	}
	public void setNewAtmcName(String newAtmcName) {
		this.newAtmcName = newAtmcName;
	}
	public String getNewAtmcVersion() {
		return newAtmcVersion;
	}
	public void setNewAtmcVersion(String newAtmcVersion) {
		this.newAtmcVersion = newAtmcVersion;
	}
	public String getNewAtmcPsVersion() {
		return newAtmcPsVersion;
	}
	public void setNewAtmcPsVersion(String newAtmcPsVersion) {
		this.newAtmcPsVersion = newAtmcPsVersion;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getOperateSystem() {
		return operateSystem;
	}
	public void setOperateSystem(String operateSystem) {
		this.operateSystem = operateSystem;
	}
	public String getAtmcName() {
		return atmcName;
	}
	public void setAtmcName(String atmcName) {
		this.atmcName = atmcName;
	}
	public String getAtmcVersion() {
		return atmcVersion;
	}
	public void setAtmcVersion(String atmcVersion) {
		this.atmcVersion = atmcVersion;
	}
	public String getAtmcPsVersion() {
		return atmcPsVersion;
	}
	public void setAtmcPsVersion(String atmcPsVersion) {
		this.atmcPsVersion = atmcPsVersion;
	}
	public String getTaskSource() {
		return taskSource;
	}
	public void setTaskSource(String taskSource) {
		this.taskSource = taskSource;
	}
	public String[] getTaskSourceArray() {
		return taskSourceArray;
	}
	public void setTaskSourceArray(String[] taskSourceArray) {
		this.taskSourceArray = taskSourceArray;
	}
	public String getUpgradeName() {
		return upgradeName;
	}
	public void setUpgradeName(String upgradeName) {
		this.upgradeName = upgradeName;
	}
	public List<RepairPlace> getRepairPlaceList() {
		return repairPlaceList;
	}
	public void setRepairPlaceList(List<RepairPlace> repairPlaceList) {
		this.repairPlaceList = repairPlaceList;
	}
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public void setAppointmentTimeStart(String appointmentTimeStart) {
		this.appointmentTimeStart = appointmentTimeStart;
	}
	public void setAppointmentTimeEnd(String appointmentTimeEnd) {
		this.appointmentTimeEnd = appointmentTimeEnd;
	}
	public List<TaskReplaceBean> getReplaceList() {
		return replaceList;
	}
	public void setReplaceList(List<TaskReplaceBean> replaceList) {
		this.replaceList = replaceList;
	}
	public EquipmentInstallBean getEquipmentInstallBean() {
		return equipmentInstallBean;
	}
	public void setEquipmentInstallBean(EquipmentInstallBean equipmentInstallBean) {
		this.equipmentInstallBean = equipmentInstallBean;
	}
	public String[] getEquipmentStatusArray() {
		return equipmentStatusArray;
	}
	public void setEquipmentStatusArray(String[] equipmentStatusArray) {
		this.equipmentStatusArray = equipmentStatusArray;
	}
	public String getPlanStartDateStart() {
		return planStartDateStart;
	}
	public void setPlanStartDateStart(String planStartDateStart) {
		this.planStartDateStart = planStartDateStart;
	}
	public String getPlanStartDateEnd() {
		return planStartDateEnd;
	}
	public void setPlanStartDateEnd(String planStartDateEnd) {
		this.planStartDateEnd = planStartDateEnd;
	}
	public String getPlanEndDateStart() {
		return planEndDateStart;
	}
	public void setPlanEndDateStart(String planEndDateStart) {
		this.planEndDateStart = planEndDateStart;
	}
	public String getPlanEndDateEnd() {
		return planEndDateEnd;
	}
	public void setPlanEndDateEnd(String planEndDateEnd) {
		this.planEndDateEnd = planEndDateEnd;
	}
	public String[] getStatusArray() {
		return statusArray;
	}
	public void setStatusArray(String[] statusArray) {
		this.statusArray = statusArray;
	}
	public Integer getTelProcessId() {
		return telProcessId;
	}
	public void setTelProcessId(Integer telProcessId) {
		this.telProcessId = telProcessId;
	}
	public String getDepartmentIds() {
		return departmentIds;
	}
	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}
	public String getCustomerIds() {
		return customerIds;
	}
	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}
	public Date getWorkFinishDate() {
		return workFinishDate;
	}
	public void setWorkFinishDate(Date workFinishDate) {
		this.workFinishDate = workFinishDate;
	}
	public Date getWorkStartDate() {
		return workStartDate;
	}
	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getSjType() {
		return sjType;
	}
	public void setSjType(String sjType) {
		this.sjType = sjType;
	}
	public String getSjTypeDetails() {
		return sjTypeDetails;
	}
	public void setSjTypeDetails(String sjTypeDetails) {
		this.sjTypeDetails = sjTypeDetails;
	}
	public String getSjTypeExplain() {
		return sjTypeExplain;
	}
	public void setSjTypeExplain(String sjTypeExplain) {
		this.sjTypeExplain = sjTypeExplain;
	}
	public String getSjContentSoftware() {
		return sjContentSoftware;
	}
	public void setSjContentSoftware(String sjContentSoftware) {
		this.sjContentSoftware = sjContentSoftware;
	}
	public String getSjContentHardware() {
		return sjContentHardware;
	}
	public void setSjContentHardware(String sjContentHardware) {
		this.sjContentHardware = sjContentHardware;
	}
	public String getSjContentEnvironment() {
		return sjContentEnvironment;
	}
	public void setSjContentEnvironment(String sjContentEnvironment) {
		this.sjContentEnvironment = sjContentEnvironment;
	}
	public String getSjContentOther() {
		return sjContentOther;
	}
	public void setSjContentOther(String sjContentOther) {
		this.sjContentOther = sjContentOther;
	}
	public Integer getCreateType() {
		return createType;
	}
	public void setCreateType(Integer createType) {
		this.createType = createType;
	}
	public String getTaskLevel() {
		return taskLevel;
	}
	public void setTaskLevel(String taskLevel) {
		this.taskLevel = taskLevel;
	}
	public String getEquipmentChargeName() {
		return equipmentChargeName;
	}
	public void setEquipmentChargeName(String equipmentChargeName) {
		this.equipmentChargeName = equipmentChargeName;
	}
	public String getWorkFinishDateStart() {
		return workFinishDateStart;
	}
	public void setWorkFinishDateStart(String workFinishDateStart) {
		this.workFinishDateStart = workFinishDateStart;
	}
	public String getWorkFinishDateEnd() {
		return workFinishDateEnd;
	}
	public void setWorkFinishDateEnd(String workFinishDateEnd) {
		this.workFinishDateEnd = workFinishDateEnd;
	}
	public String getRepairsManName() {
		return repairsManName;
	}
	public void setRepairsManName(String repairsManName) {
		this.repairsManName = repairsManName;
	}
	public String getReceiveManName() {
		return receiveManName;
	}
	public void setReceiveManName(String receiveManName) {
		this.receiveManName = receiveManName;
	}
	public String getRepairsMoblie() {
		return repairsMoblie;
	}
	public void setRepairsMoblie(String repairsMoblie) {
		this.repairsMoblie = repairsMoblie;
	}
	public String getReceiveMoblie() {
		return receiveMoblie;
	}
	public void setReceiveMoblie(String receiveMoblie) {
		this.receiveMoblie = receiveMoblie;
	}
	public String getRepairsTelephone() {
		return repairsTelephone;
	}
	public void setRepairsTelephone(String repairsTelephone) {
		this.repairsTelephone = repairsTelephone;
	}
	public String getReceiveTelephone() {
		return receiveTelephone;
	}
	public void setReceiveTelephone(String receiveTelephone) {
		this.receiveTelephone = receiveTelephone;
	}
	public Integer getRepairsContactId() {
		return repairsContactId;
	}
	public void setRepairsContactId(Integer repairsContactId) {
		this.repairsContactId = repairsContactId;
	}
	public Integer getReceiveContactId() {
		return receiveContactId;
	}
	public void setReceiveContactId(Integer receiveContactId) {
		this.receiveContactId = receiveContactId;
	}
	public String getAcceptStyle() {
		return acceptStyle;
	}
	public void setAcceptStyle(String acceptStyle) {
		this.acceptStyle = acceptStyle;
	}
	public Integer getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(Integer loginUserId) {
		this.loginUserId = loginUserId;
	}
 
	
}
