package com.grgbanking.core.entity.workorder;

public class WsWorkTaskBean {

	/**
	 * 服务内容
	 */
	private String taskContent;

	/**
	 * 服务类型，如维修、安装、移机、升级、保养、巡检、其他
	 */
	private String taskType;
	
	/**
	 * 服务单号
	 */
	private String processNo;

	

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getProcessNo() {
		return processNo;
	}

	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}


}
