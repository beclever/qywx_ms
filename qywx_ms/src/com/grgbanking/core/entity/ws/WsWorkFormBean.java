package com.grgbanking.core.entity.ws;

import java.util.Date;
import java.util.List;

import com.grgbanking.core.entity.workorder.WsWorkTaskBean;

public class WsWorkFormBean {

	private String serialNumber;// 设备序列号
	private Date appointmentDate;// 预约上门时间
	private Date reportTime;// 报修时间
	private String reportTelephone;// 报修电话
	private String engineerName;// 工程师名称(用工号)
	private String followUserName;// 跟随人员（用逗号隔开,用工号）
	private List<WsWorkTaskBean> workTaskList;// 任务列表
	/**
	 * 任务等级(常规、紧急)
	 */
	private String taskLevel;
	private String clientType;// 提交来源-SA
	private String createUserCode;// 创建人编号
	private String sptRemark;//备件信息
	private String advice;// 调度建议
	
	private String repairsManName;//报修人
	private String repairsMoblie;// 手机


	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportTelephone() {
		return reportTelephone;
	}

	public void setReportTelephone(String reportTelephone) {
		this.reportTelephone = reportTelephone;
	}
	

	
	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}

	public String getFollowUserName() {
		return followUserName;
	}

	public void setFollowUserName(String followUserName) {
		this.followUserName = followUserName;
	}

	
	public String getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(String taskLevel) {
		this.taskLevel = taskLevel;
	}

	public List<WsWorkTaskBean> getWorkTaskList() {
		return workTaskList;
	}

	public void setWorkTaskList(List<WsWorkTaskBean> workTaskList) {
		this.workTaskList = workTaskList;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}
	public String getSptRemark() {
		return sptRemark;
	}
	public void setSptRemark(String sptRemark) {
		this.sptRemark = sptRemark;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getRepairsManName() {
		return repairsManName;
	}

	public void setRepairsManName(String repairsManName) {
		this.repairsManName = repairsManName;
	}

	public String getRepairsMoblie() {
		return repairsMoblie;
	}

	public void setRepairsMoblie(String repairsMoblie) {
		this.repairsMoblie = repairsMoblie;
	}

	
	

}
