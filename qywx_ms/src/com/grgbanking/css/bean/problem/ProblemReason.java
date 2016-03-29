/**
 * ProblemReason.java
 * Product:Grgbanking
 * Version:1.0
 * Copyright 2009 by DNE
 * All Rights Reserved.
 */
package com.grgbanking.css.bean.problem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 故障原因实体类
 * @ClassName com.grgbanking.services.pojo.ProblemReason
 * @Author Zhang Zhi
 * @Version 1.0
 * @Date 2009-7-17 下午03:25:44
 */
@Entity
@Table(name="V_SERVICES_PROBLEM_REASON")
public class ProblemReason {
	@Id
	@Column(name="PROBLEM_REASON_ID")
	private Integer problemReasonId;
	@Column(name="REASON_CODE")
	private String reasonCode;
	@Column(name="REASON_REMARK")
	private String reasonRemark;
	@Column(name="PARENT_ID")
	private Integer parentId;
	public Integer getProblemReasonId() {
		return problemReasonId;
	}
	public void setProblemReasonId(Integer problemReasonId) {
		this.problemReasonId = problemReasonId;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getReasonRemark() {
		return reasonRemark;
	}
	public void setReasonRemark(String reasonRemark) {
		this.reasonRemark = reasonRemark;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}
