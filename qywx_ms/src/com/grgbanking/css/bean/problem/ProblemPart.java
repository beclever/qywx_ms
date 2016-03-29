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
 * 维修故障部位实体类
 * @ClassName com.grgbanking.services.pojo.ProblemPart
 * @Author Zhang Zhi
 * @Version 1.0
 * @Date 2009-7-17 下午03:17:37
 */
@Entity
@Table(name="V_SERVICES_PROBLEM_PART")
public class ProblemPart {
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="VALUE")
	private String value;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="PARENT_ID")
	private Integer parentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}
