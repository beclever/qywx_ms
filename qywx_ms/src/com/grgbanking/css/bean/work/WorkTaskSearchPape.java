package com.grgbanking.css.bean.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WorkTaskSearchPape {

	@Id
	@Column(name = "task_id")
	private Integer taskId;
	
	@Column(name = "workform_id")
	private Integer workformId;

	@Column(name = "equipment_id")
	private Integer equipmentId;
	
	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;
	
	@Column(name = "EQUIPMENT_STATUS")
	private String equipmentStatus;
	
	@Column(name = "EQUIPMENT_MODEL")
	private String equipmentModel;
	
	@Column(name = "DEPARTMENT_ID")
	private Integer departmentId;
	
	@Column(name = "CUSTOMER_ID")
	private Integer customerId;
	
	@Column(name = "BRANCH_NAME")
	private String branchName;

	@Column(name ="PROCESS_NO")
	private String processNo;
	
	@Column(name = "task_type")
	private String taskType;

	@Column(name = "engineer_name")
	private String engineerName;

	@Column(name = "status")
	private String status;

	@Column(name = "PLAN_START_DATE")
	private Date planStartDate;

	@Column(name = "PLAN_END_DATE")
	private Date planEndDate;

	@Column(name = "UPGRADE_CODE")
	private String upgradeCode;
	
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@Column(name = "APPOINTMENT_TIME")
	private Date appointmentTime;
	@Column(name = "REPORT_TIME")
	private Date reportTime;
	@Column(name ="TASK_SOURCE")
	private String taskSource;

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

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getUpgradeCode() {
		return upgradeCode;
	}

	public void setUpgradeCode(String upgradeCode) {
		this.upgradeCode = upgradeCode;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getProcessNo() {
		return processNo;
	}

	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getTaskSource() {
		return taskSource;
	}

	public void setTaskSource(String taskSource) {
		this.taskSource = taskSource;
	}

	public String getEquipmentModel() {
		return equipmentModel;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	

}
