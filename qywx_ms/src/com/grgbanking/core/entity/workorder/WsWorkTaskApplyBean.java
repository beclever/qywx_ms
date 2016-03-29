package com.grgbanking.core.entity.workorder;

public class WsWorkTaskApplyBean {

	private String taskId;// 任务ID String(Y)
	private String taskNo;// 任务编号,{WX,BY等} String(Y)
	private WsSubmitWorkTaskBean taskAttribute;// 任务属性 提交工单任务属性

	private WsWorkTaskModelBean taskModel;// 任务模块 任务所属模块信息

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public WsSubmitWorkTaskBean getTaskAttribute() {
		return taskAttribute;
	}

	public void setTaskAttribute(WsSubmitWorkTaskBean taskAttribute) {
		this.taskAttribute = taskAttribute;
	}

	public WsWorkTaskModelBean getTaskModel() {
		return taskModel;
	}

	public void setTaskModel(WsWorkTaskModelBean taskModel) {
		this.taskModel = taskModel;
	}

}
