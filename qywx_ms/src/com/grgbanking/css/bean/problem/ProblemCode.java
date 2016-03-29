/**
 * ProblemCode.java
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
 * 故障代码实体类
 * @ClassName com.grgbanking.services.pojo.ProblemCode
 * @Author Zhang Zhi
 * @Version 1.0
 * @Date 2009-7-17 下午03:17:37
 */
@Entity
@Table(name="V_SERVICES_PROBLEM_CODE")
public class ProblemCode {
	@Id
	@Column(name="PROBLEM_CODE_ID")
	private Integer problemCodeId;
	
	@Column(name="PROBLEM_CODE")
	private String problemCode;
	
	@Column(name="PROBLEM_REMARK")
	private String problemRemark;
	
	@Column(name="PARENT_ID")
	private Integer parentId;
	
	public Integer getProblemCodeId() {
		return problemCodeId;
	}

	public void setProblemCodeId(Integer problemCodeId) {
		this.problemCodeId = problemCodeId;
	}

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	public String getProblemRemark() {
		return problemRemark;
	}

	public void setProblemRemark(String problemRemark) {
		this.problemRemark = problemRemark;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}
