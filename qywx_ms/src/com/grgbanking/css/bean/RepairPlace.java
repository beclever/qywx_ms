/**
 * RepairPlace.java
 * Product:Grgbanking
 * Version:1.0
 * Copyright 2009 by DNE
 * All Rights Reserved.
 */
package com.grgbanking.css.bean;

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

import com.grgbanking.css.bean.work.WorkTask;

/**
 * 维修故障部位实体类
 * @ClassName com.grgbanking.services.pojo.RepairPlace
 * @Author Zhang Zhi
 * @Version 1.0
 * @Date 2009-7-20 下午03:13:34
 */
@Entity
@Table(name = "V_SERVICES_TASK_REPAIR_PLACE")
public class RepairPlace{
	private Integer repairPlaceId;
	private Integer problemCodeId;
	private String problemCode;//故障代码
	private String problemRemark;//故障描述
	private Integer problemReasonId;
	private String reasonCode;//故障原因代码
	private String reasonRemark;//故障原因描述
	private Integer problemMethodId;
	private String methodCode;//处理方法代码
	private String methodRemark;//处理方法描述
	private Integer problemPartId;
	private String problemPartCode;//故障部位代码
	private String problemPartRemark;//故障部位描述
	private WorkTask workTask;
	protected Date createDate;
	
	
	@Id  
	@SequenceGenerator(name = "SERVICES_REPAIR_PLACE_ID_SEQ_GENERATOR", sequenceName = "SEQ_SERVICES_TASK_REPAIR_PLACE", initialValue = 1, allocationSize = 1)   
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICES_REPAIR_PLACE_ID_SEQ_GENERATOR")   
	@Column(name="REPAIR_PLACE_ID")
	public Integer getRepairPlaceId() {
		return repairPlaceId;
	}
	public void setRepairPlaceId(Integer repairPlaceId) {
		this.repairPlaceId = repairPlaceId;
	}
	@Column(name="TROUBLE_CODE")
	public String getProblemCode() {
		return problemCode;
	}
	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}
	@Column(name="TROUBLE_REMARK")
	public String getProblemRemark() {
		return problemRemark;
	}
	public void setProblemRemark(String problemRemark) {
		this.problemRemark = problemRemark;
	}
	@Column(name="TROUBLE_REASON_CODE")
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	@Column(name="TROUBLE_REASON_REMARK")
	public String getReasonRemark() {
		return reasonRemark;
	}
	public void setReasonRemark(String reasonRemark) {
		this.reasonRemark = reasonRemark;
	}
	@Column(name="PROCESS_CODE")
	public String getMethodCode() {
		return methodCode;
	}
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}
	@Column(name="PROCESS_REMARK")
	public String getMethodRemark() {
		return methodRemark;
	}
	public void setMethodRemark(String methodRemark) {
		this.methodRemark = methodRemark;
	}
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.LAZY )
    @JoinColumn(name="TASK_ID")
	public WorkTask getWorkTask() {
		return workTask;
	}
	public void setWorkTask(WorkTask workTask) {
		this.workTask = workTask;
	}
	@Column(name="PROBLEM_PART_CODE")
	public String getProblemPartCode() {
		return problemPartCode;
	}
	public void setProblemPartCode(String problemPartCode) {
		this.problemPartCode = problemPartCode;
	}
	@Column(name="PROBLEM_PART_REMARK")
	public String getProblemPartRemark() {
		return problemPartRemark;
	}
	public void setProblemPartRemark(String problemPartRemark) {
		this.problemPartRemark = problemPartRemark;
	}
	
	@Column(name="PROBLEM_CODE_ID")
	public Integer getProblemCodeId() {
		return problemCodeId;
	}
	public void setProblemCodeId(Integer problemCodeId) {
		this.problemCodeId = problemCodeId;
	}
	@Column(name="PROBLEM_REASON_ID")
	public Integer getProblemReasonId() {
		return problemReasonId;
	}
	public void setProblemReasonId(Integer problemReasonId) {
		this.problemReasonId = problemReasonId;
	}
	@Column(name="PROBLEM_METHOD_ID")
	public Integer getProblemMethodId() {
		return problemMethodId;
	}
	public void setProblemMethodId(Integer problemMethodId) {
		this.problemMethodId = problemMethodId;
	}
	@Column(name="PROBLEM_PART_ID")
	public Integer getProblemPartId() {
		return problemPartId;
	}
	public void setProblemPartId(Integer problemPartId) {
		this.problemPartId = problemPartId;
	}
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
