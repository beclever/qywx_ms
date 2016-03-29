package com.grgbanking.css.bean.vequipment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 由工单产生的历史遗留问题
 * @author yzhua
 *
 */
@Entity
@Table(name = "v_ems_eq_history_problem")
public class VEquipmentHistoryProblem {
	
	private Integer historyProblemId;
	private Integer equipmentId; //对应设备ID
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
	
	@Id
	@Column(name = "HISTORY_PROBLEM_ID")
	public Integer getHistoryProblemId() {
		return this.historyProblemId;
	}

	public void setHistoryProblemId(Integer historyProblemId) {
		this.historyProblemId = historyProblemId;
	}

	@Column(name = "EQUIPMENT_ID")
	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	@Column(name = "CREATE_PO_NUMBER")
	public String getCreatePoNumber() {
		return createPoNumber;
	}

	public void setCreatePoNumber(String createPoNumber) {
		this.createPoNumber = createPoNumber;
	}

	@Column(name = "SOLVE_PO_NUMBER")
	public String getSolvePoNumber() {
		return solvePoNumber;
	}

	public void setSolvePoNumber(String solvePoNumber) {
		this.solvePoNumber = solvePoNumber;
	}

	@Column(name = "PROBLEM_LEVEL")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "RECORD_TIME")
	public Date getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	@Column(name = "RECORD_PERSON")
	public String getRecordPerson() {
		return this.recordPerson;
	}

	public void setRecordPerson(String recordPerson) {
		this.recordPerson = recordPerson;
	}

	@Column(name = "SOLVE_TIME")
	public Date getSolveTime() {
		return this.solveTime;
	}

	public void setSolveTime(Date solveTime) {
		this.solveTime = solveTime;
	}

	@Column(name = "SOLVE_PERSON")
	public String getSolvePerson() {
		return this.solvePerson;
	}

	public void setSolvePerson(String solvePerson) {
		this.solvePerson = solvePerson;
	}

	@Column(name = "SOLVE_REMARK")
	public String getSolveRemark() {
		return this.solveRemark;
	}

	public void setSolveRemark(String solveRemark) {
		this.solveRemark = solveRemark;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
