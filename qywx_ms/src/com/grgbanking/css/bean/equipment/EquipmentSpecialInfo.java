package com.grgbanking.css.bean.equipment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 设备特殊问题
 */
@Entity
@Table(name = "V_EQUIPMENT_SPECIAL_INFO")
public class EquipmentSpecialInfo implements java.io.Serializable {

	// Fields
	
	private Integer specialId;
	private String specialType;//问题类型
	private String description;//问题描述
	private Date solveTime;//解决时间
	private String solveUser;//解决人姓名
	private Integer solveUserId;//解决人ID
	private Date createDate;//创建时间
	
	@Id  
	@SequenceGenerator(name = "SEQ_EQUIPMENT_SPECIAL_INFO_GENERATOR", sequenceName = "SEQ_EQUIPMENT_SPECIAL_INFO", initialValue = 1, allocationSize = 1)   
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EQUIPMENT_SPECIAL_INFO_GENERATOR")   
	@Column(name="SPECIAL_ID")
	public Integer getSpecialId() {
		return specialId;
	}

	public void setSpecialId(Integer specialId) {
		this.specialId = specialId;
	}

	@Column(name="SOLVE_USER")
	public String getSolveUser() {
		return solveUser;
	}

	public void setSolveUser(String solveUser) {
		this.solveUser = solveUser;
	}

	@Column(name="SOLVE_USER_ID")
	public Integer getSolveUserId() {
		return solveUserId;
	}

	public void setSolveUserId(Integer solveUserId) {
		this.solveUserId = solveUserId;
	}

	@Column(name = "SPECIAL_TYPE")
	public String getSpecialType() {
		return this.specialType;
	}

	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "SOLVE_TIME")
	public Date getSolveTime() {
		return this.solveTime;
	}

	public void setSolveTime(Date solveTime) {
		this.solveTime = solveTime;
	}
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}