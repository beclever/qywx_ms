package com.grgbanking.core.entity.equipment;

/**
 * 工单任务-设备历史遗留问题
 * 
 * @author yt
 * 
 */

public class WsEquipmentHistoryProblemBean {
	private String levelId;// 问题级别ID String(N)
	private String problemContent;// 问题内容 String(N)
	private String problemRemark;// 问题描述
	private String historyProblemId;// 设备遗留ID String(N)
	private String dealContent;// 处理内容 String(N)
	private String status;// 状态 String(N)
	private String submitProblemUserName;// 问题提交人姓名 String(N)

	public String getSubmitProblemUserName() {
		return submitProblemUserName;
	}

	public void setSubmitProblemUserName(String submitProblemUserName) {
		this.submitProblemUserName = submitProblemUserName;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getProblemContent() {
		return problemContent;
	}

	public void setProblemContent(String problemContent) {
		this.problemContent = problemContent;
	}

	public String getHistoryProblemId() {
		return historyProblemId;
	}

	public void setHistoryProblemId(String historyProblemId) {
		this.historyProblemId = historyProblemId;
	}

	public String getDealContent() {
		return dealContent;
	}

	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProblemRemark() {
		return problemRemark;
	}

	public void setProblemRemark(String problemRemark) {
		this.problemRemark = problemRemark;
	}

	private String description;// 描述
	private String recordPerson;// 提交人
	private String recordTime;// 创建时间
	private String solveTime;// 解决时间

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecordPerson() {
		return recordPerson;
	}

	public void setRecordPerson(String recordPerson) {
		this.recordPerson = recordPerson;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getSolveTime() {
		return solveTime;
	}

	public void setSolveTime(String solveTime) {
		this.solveTime = solveTime;
	}

}
