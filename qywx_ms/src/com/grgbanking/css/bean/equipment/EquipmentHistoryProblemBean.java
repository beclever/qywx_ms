package com.grgbanking.css.bean.equipment;

import java.util.Date;

/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: yzh
 * Date: 2011-5-25 下午06:55:38
 */
public class EquipmentHistoryProblemBean {
	
	private Integer historyProblemId;
	private Integer equipmentId; //对应设备ID
	private String department;	//部门
	private String createPoNumber; //产生此问题的工单
	private String solvePoNumber;//解决此问题的工单
	private String level;//问题等级
	private String description;//描述
	private Date recordTime;//记录时间
	private String recordPerson;//记录人
	private Date solveTime;//解决时间
	private String solvePerson;//解决人
	private String solveRemark;//解决备注
	private String status;//状态(已解决，未解决)
	private String startDate;
	private String endDate ;
	
	public Integer getHistoryProblemId() {
		return historyProblemId;
	}
	public void setHistoryProblemId(Integer historyProblemId) {
		this.historyProblemId = historyProblemId;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCreatePoNumber() {
		return createPoNumber;
	}
	public void setCreatePoNumber(String createPoNumber) {
		this.createPoNumber = createPoNumber;
	}
	public String getSolvePoNumber() {
		return solvePoNumber;
	}
	public void setSolvePoNumber(String solvePoNumber) {
		this.solvePoNumber = solvePoNumber;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getRecordPerson() {
		return recordPerson;
	}
	public void setRecordPerson(String recordPerson) {
		this.recordPerson = recordPerson;
	}
	public Date getSolveTime() {
		return solveTime;
	}
	public void setSolveTime(Date solveTime) {
		this.solveTime = solveTime;
	}
	public String getSolvePerson() {
		return solvePerson;
	}
	public void setSolvePerson(String solvePerson) {
		this.solvePerson = solvePerson;
	}
	public String getSolveRemark() {
		return solveRemark;
	}
	public void setSolveRemark(String solveRemark) {
		this.solveRemark = solveRemark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
