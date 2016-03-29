package com.grgbanking.core.entity.equipment;



/**
 * 
 * 设备历史服务信息
 * @author yt
 *
 */
public class WsHistoryServerInfoBean {

	private String taskType;// String（Y） 服务类型
	private String engineerName;// String（Y） 工程师
	private String finishTime;// String（Y） 完成时间
	private String workContent;// String（Y） 工作描述
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getEngineerName() {
		return engineerName;
	}
	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getWorkContent() {
		return workContent;
	}
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	
	
	
}
