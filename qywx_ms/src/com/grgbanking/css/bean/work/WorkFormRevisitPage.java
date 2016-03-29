package com.grgbanking.css.bean.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * 
 * Copyright (c) 2011 GRG Banking Equipment Co.,Ltd. 
 * @createDate 2013-4-2
 * @author guabin 
 * @description 
 * 回访专用工单查询返回实体
 */


@Entity
public class WorkFormRevisitPage {

	@Id
	@Column(name ="task_id")
	private Integer taskId;
	
	@Column(name ="workform_id")
	private Integer workformId;
	
	@Column(name ="task_type")
	private String taskType;
	
	@Column(name ="po_number")
	private String poNumber;
	
	@Column(name ="engineer_name")
	private String engineerName;
	
	@Column(name ="CREATE_DATE")
	private Date createDate;
	
	@Column(name ="status")
	private String status;
	
	@Column(name ="work_finish_date")
	private Date workFinishDate;
	
	@Column(name ="cost_time")
	private Integer costTime;
	
	@Column(name ="equipment_id")
	private Integer equipmentId;
	
	@Column(name ="department_id")
	private Integer departmentId;
	
	@Column(name ="has_revisit")
	private String hasRevisit;
	
	@Column(name ="last_revisit_time")
	private Date lastRevisitTime;

	public Integer getWorkformId() {
		return workformId;
	}

	public void setWorkformId(Integer workformId) {
		this.workformId = workformId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getWorkFinishDate() {
		return workFinishDate;
	}

	public void setWorkFinishDate(Date workFinishDate) {
		this.workFinishDate = workFinishDate;
	}

	public Integer getCostTime() {
		return costTime;
	}

	public void setCostTime(Integer costTime) {
		this.costTime = costTime;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getHasRevisit() {
		return hasRevisit;
	}

	public void setHasRevisit(String hasRevisit) {
		this.hasRevisit = hasRevisit;
	}

	public Date getLastRevisitTime() {
		return lastRevisitTime;
	}

	public void setLastRevisitTime(Date lastRevisitTime) {
		this.lastRevisitTime = lastRevisitTime;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

}
