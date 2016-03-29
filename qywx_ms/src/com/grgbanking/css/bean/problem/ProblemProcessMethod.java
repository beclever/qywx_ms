/**
 * ProblemProcessMethod.java
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
 * 故障处理方法实体类
 * @ClassName com.grgbanking.services.pojo.ProblemProcessMethod
 * @Author Zhang Zhi
 * @Version 1.0
 * @Date 2009-7-17 下午03:27:58
 */
@Entity
@Table(name="V_SERVICES_PROBLEM_METHOD")
public class ProblemProcessMethod {
	@Id
	@Column(name="PROBLEM_METHOD_ID")
	private Integer methodId;
	@Column(name="METHOD_CODE")
	private String methodCode;
	@Column(name="METHOD_REMARK")
	private String methodRemark;
	@Column(name="PARENT_ID")
	private Integer parentId;
	public Integer getMethodId() {
		return methodId;
	}
	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}
	public String getMethodCode() {
		return methodCode;
	}
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}
	public String getMethodRemark() {
		return methodRemark;
	}
	public void setMethodRemark(String methodRemark) {
		this.methodRemark = methodRemark;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
