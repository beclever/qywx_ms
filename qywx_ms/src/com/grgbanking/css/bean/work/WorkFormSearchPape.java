package com.grgbanking.css.bean.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 工单查询实体
 * 
 * @author Administrator
 * 
 */
@Entity
public class WorkFormSearchPape {

	@Id
	@Column(name = "task_id")
	private Integer taskId;

	@Column(name = "workform_id")
	private Integer workformId;
	
	@Column(name = "equipment_id")
	private Integer equipmentId;

	@Column(name = "po_number")
	private String poNumber;
	
	@Column(name = "task_type")
	private String taskType;

	@Column(name = "cost_time")
	private Integer costTime;

	@Column(name = "work_finish_date")
	private Date workFinishDate;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "engineer_name")
	private String engineerName;

	@Column(name = "status")
	private String status;

	@Column(name = "SATISFIED_TYPE")
	private String satisfiedType;// 客户满意度
	
	@Column(name = "department_id")
	private Integer departmentId;
	

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

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Integer getCostTime() {
		return costTime;
	}

	public void setCostTime(Integer costTime) {
		this.costTime = costTime;
	}

	public Date getWorkFinishDate() {
		return workFinishDate;
	}

	public void setWorkFinishDate(Date workFinishDate) {
		this.workFinishDate = workFinishDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getSatisfiedType() {
		return satisfiedType;
	}

	public void setSatisfiedType(String satisfiedType) {
		this.satisfiedType = satisfiedType;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	

}
