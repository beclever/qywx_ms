package com.grgbanking.css.bean.work;

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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.grgbanking.css.bean.RepairPlace;
import com.grgbanking.css.bean.equipment.EquipmentInfo;
import com.grgbanking.css.common.CssBaseEntity;

/**
 * 服务（任务）信息表 Product:Grgbanking Service Of Customer System. Version:2.0
 * Copyright 2010 by Grgbanking All Rights Reserved. Author: Administrator Date:
 * 2010-7-29 下午02:07:27
 */
@Entity
@Table(name = "V_SERVICES_TASK")
public class WorkTask extends CssBaseEntity {

	/**
	 * 主键ID
	 */
	private Integer taskId;
	/**
	 * 对应的工单ID
	 */
	private WorkForm workForm;

	/**
	 * 设备ID
	 */
	private EquipmentInfo equipment;
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
	 * 报修人
	 */
	private String reportName;
	/**
	 * 报修电话
	 */
	private String reportTelephone;
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

	private List<RepairPlace> repairPlaceList = new ArrayList<RepairPlace>();

	private List<TaskReplace> replaceList = new ArrayList<TaskReplace>();

	private List<WorkTaskContact> workTaskContactList = new ArrayList<WorkTaskContact>();

	private String sjType; // 巡检类型
	private String sjTypeDetails;// 巡检类型详细
	private String sjTypeExplain;// 巡检说明
	private String sjContentSoftware;// 巡检内容软件检查
	private String sjContentHardware;// 巡检内容硬件检查
	private String sjContentEnvironment;// 巡检内容环境检查
	private String sjContentOther;// 巡检内容其它
	private String createTypes;// 创建来源
	private String acceptStyle;// 采取处理方式(SA,CSS)

	private String taskLevel;// 任务等级

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_SEQ_SERVICES_TASK")
	@SequenceGenerator(name = "GENERATOR_SEQ_SERVICES_TASK", sequenceName = "SEQ_SERVICES_TASK", initialValue = 1, allocationSize = 1)
	@Column(name = "TASK_ID")
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKFORM_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	public WorkForm getWorkForm() {
		return workForm;
	}

	public void setWorkForm(WorkForm workForm) {
		this.workForm = workForm;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "EQUIPMENT_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	public EquipmentInfo getEquipment() {
		return equipment;
	}

	public void setEquipment(EquipmentInfo equipment) {
		this.equipment = equipment;
	}

	@Column(name = "TASK_CONTENT")
	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	@Column(name = "TASK_TYPE")
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	@Column(name = "ENGINEER_ID")
	public Integer getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}

	@Column(name = "ENGINEER_NAME")
	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}

	@Column(name = "COST_TIME")
	public Integer getCostTime() {
		return costTime;
	}

	public void setCostTime(Integer costTime) {
		this.costTime = costTime;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PROCESS_NO")
	public String getProcessNo() {
		return processNo;
	}

	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}

	@Column(name = "UPGRADE_CODE")
	public String getUpgradeCode() {
		return upgradeCode;
	}

	public void setUpgradeCode(String upgradeCode) {
		this.upgradeCode = upgradeCode;
	}

	@Column(name = "WORK_STATUS")
	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	@Column(name = "WARRANTY_TYPE")
	public String getWarrantyType() {
		return warrantyType;
	}

	public void setWarrantyType(String warrantyType) {
		this.warrantyType = warrantyType;
	}

	@Column(name = "TEL_PROCESS_NO")
	public String getTelProcessNo() {
		return TelProcessNo;
	}

	public void setTelProcessNo(String telProcessNo) {
		TelProcessNo = telProcessNo;
	}

	@Column(name = "REPORT_TIME")
	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	@Column(name = "REPORT_TELEPHONE")
	public String getReportTelephone() {
		return reportTelephone;
	}

	public void setReportTelephone(String reportTelephone) {
		this.reportTelephone = reportTelephone;
	}

	@Column(name = "PLAN_START_DATE")
	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	@Column(name = "PLAN_END_DATE")
	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	@Column(name = "NEW_OS_VERSION")
	public String getNewOsVersion() {
		return newOsVersion;
	}

	public void setNewOsVersion(String newOsVersion) {
		this.newOsVersion = newOsVersion;
	}

	@Column(name = "NEW_OPERATE_SYSTEM")
	public String getNewOperateSystem() {
		return newOperateSystem;
	}

	public void setNewOperateSystem(String newOperateSystem) {
		this.newOperateSystem = newOperateSystem;
	}

	@Column(name = "NEW_ATMC_NAME")
	public String getNewAtmcName() {
		return newAtmcName;
	}

	public void setNewAtmcName(String newAtmcName) {
		this.newAtmcName = newAtmcName;
	}

	@Column(name = "NEW_ATMC_VERSION")
	public String getNewAtmcVersion() {
		return newAtmcVersion;
	}

	public void setNewAtmcVersion(String newAtmcVersion) {
		this.newAtmcVersion = newAtmcVersion;
	}

	@Column(name = "NEW_ATMC_PS_VERSION")
	public String getNewAtmcPsVersion() {
		return newAtmcPsVersion;
	}

	public void setNewAtmcPsVersion(String newAtmcPsVersion) {
		this.newAtmcPsVersion = newAtmcPsVersion;
	}

	@Column(name = "OS_VERSION")
	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	@Column(name = "OPERATE_SYSTEM")
	public String getOperateSystem() {
		return operateSystem;
	}

	public void setOperateSystem(String operateSystem) {
		this.operateSystem = operateSystem;
	}

	@Column(name = "ATMC_NAME")
	public String getAtmcName() {
		return atmcName;
	}

	public void setAtmcName(String atmcName) {
		this.atmcName = atmcName;
	}

	@Column(name = "ATMC_VERSION")
	public String getAtmcVersion() {
		return atmcVersion;
	}

	public void setAtmcVersion(String atmcVersion) {
		this.atmcVersion = atmcVersion;
	}

	@Column(name = "ATMC_PS_VERSION")
	public String getAtmcPsVersion() {
		return atmcPsVersion;
	}

	public void setAtmcPsVersion(String atmcPsVersion) {
		this.atmcPsVersion = atmcPsVersion;
	}

	@Column(name = "TASK_SOURCE")
	public String getTaskSource() {
		return taskSource;
	}

	public void setTaskSource(String taskSource) {
		this.taskSource = taskSource;
	}

	@Column(name = "APPOINTMENT_TIME")
	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "TASK_ID")
	public List<RepairPlace> getRepairPlaceList() {
		return repairPlaceList;
	}

	public void setRepairPlaceList(List<RepairPlace> repairPlaceList) {
		this.repairPlaceList = repairPlaceList;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "TASK_ID")
	public List<TaskReplace> getReplaceList() {
		return replaceList;
	}

	public void setReplaceList(List<TaskReplace> replaceList) {
		this.replaceList = replaceList;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "TASK_ID")
	public List<WorkTaskContact> getWorkTaskContactList() {
		return workTaskContactList;
	}

	public void setWorkTaskContactList(List<WorkTaskContact> workTaskContactList) {
		this.workTaskContactList = workTaskContactList;
	}

	@Column(name = "SJ_TYPE")
	public String getSjType() {
		return sjType;
	}

	public void setSjType(String sjType) {
		this.sjType = sjType;
	}

	@Column(name = "SJ_TYPE_DETAILS")
	public String getSjTypeDetails() {
		return sjTypeDetails;
	}

	public void setSjTypeDetails(String sjTypeDetails) {
		this.sjTypeDetails = sjTypeDetails;
	}

	@Column(name = "SJ_TYPE_EXPLAIN")
	public String getSjTypeExplain() {
		return sjTypeExplain;
	}

	public void setSjTypeExplain(String sjTypeExplain) {
		this.sjTypeExplain = sjTypeExplain;
	}

	@Column(name = "SJ_CONTENT_SOFTWARE")
	public String getSjContentSoftware() {
		return sjContentSoftware;
	}

	public void setSjContentSoftware(String sjContentSoftware) {
		this.sjContentSoftware = sjContentSoftware;
	}

	@Column(name = "SJ_CONTENT_HARDWARE")
	public String getSjContentHardware() {
		return sjContentHardware;
	}

	public void setSjContentHardware(String sjContentHardware) {
		this.sjContentHardware = sjContentHardware;
	}

	@Column(name = "SJ_CONTENT_ENVIRONMENT")
	public String getSjContentEnvironment() {
		return sjContentEnvironment;
	}

	public void setSjContentEnvironment(String sjContentEnvironment) {
		this.sjContentEnvironment = sjContentEnvironment;
	}

	@Column(name = "SJ_CONTENT_OTHER")
	public String getSjContentOther() {
		return sjContentOther;
	}

	public void setSjContentOther(String sjContentOther) {
		this.sjContentOther = sjContentOther;
	}

	public void setCreateTypes(String createTypes) {
		this.createTypes = createTypes;
	}

	public void setTaskLevel(String taskLevel) {
		this.taskLevel = taskLevel;
	}

	@Column(name = "TASK_LEVEL")
	public String getTaskLevel() {
		return taskLevel;
	}

	@Column(name = "CREATE_TYPES")
	public String getCreateTypes() {
		return createTypes;
	}

	@Column(name = "ACCEPT_STYLE")
	public String getAcceptStyle() {
		return acceptStyle;
	}

	public void setAcceptStyle(String acceptStyle) {
		this.acceptStyle = acceptStyle;
	}

	@Column(name = "REPORT_NAME")
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

}
