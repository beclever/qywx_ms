/**
 * WorkFormFee.java
 * Product:Grgbanking
 * Version:1.0
 * Copyright 2009 by DNE
 * All Rights Reserved.
 */
package com.grgbanking.css.bean.work;

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

/**
 * 工单处理费用
 * @ClassName com.grgbanking.services.pojo.WorkFormFee
 * @Author Sin
 * @Version 1.0
 * @Date 2009-7-27 下午02:33:01
 */
@Entity
@Table(name = "V_SERVICES_WORKFORM_FEE")
public class WorkFormFee {
	private Integer workFormFeeId;
	private String claimItem;
	private String username;
	private Integer userId;
	private Double feeMoney;
	private WorkForm workForm;
	private String feeRemark;
	private String status;
	//private String taskLevelReason;
	
	@Id  
	@SequenceGenerator(name = "SEQ_SERVICES_WORKFORM_FEE_GENERATOR", sequenceName = "SEQ_SERVICES_WORKFORM_FEE", initialValue = 1, allocationSize = 1)   
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVICES_WORKFORM_FEE_GENERATOR")   
	@Column(name="FEE_ID")
	public Integer getWorkFormFeeId() {
		return workFormFeeId;
	}
	public void setWorkFormFeeId(Integer workFormFeeId) {
		this.workFormFeeId = workFormFeeId;
	}
	
	@Column(name="MONEY")
	public Double getFeeMoney() {
		return feeMoney;
	}
	public void setFeeMoney(Double feeMoney) {
		this.feeMoney = feeMoney;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.LAZY )
    @JoinColumn(name="WORKWORK_ID")
	public WorkForm getWorkForm() {
		return workForm;
	}
	public void setWorkForm(WorkForm workForm) {
		this.workForm = workForm;
	}
	@Column(name="description")
	public String getFeeRemark() {
		return feeRemark;
	}
	public void setFeeRemark(String feeRemark) {
		this.feeRemark = feeRemark;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="CLAIM_ITEM")
	public String getClaimItem() {
		return claimItem;
	}
	public void setClaimItem(String claimItem) {
		this.claimItem = claimItem;
	}
	@Column(name="USER_NAME")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="USER_ID")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
