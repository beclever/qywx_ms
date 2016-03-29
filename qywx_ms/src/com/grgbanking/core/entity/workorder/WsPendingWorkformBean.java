package com.grgbanking.core.entity.workorder;

import com.grgbanking.core.entity.equipment.WsDeviceLocationBean;


/**
 * 5.1.11. 待处理工单列表信息&工单信息查询
 */
public class WsPendingWorkformBean {

	private String poNumber;// 工单编号(列表显示) String(Y)
	private String taskLevel;// 工单优先级（紧急，常规） String(Y)
	private String serialNumber;// 设备序列号 String(Y)
	private String customerName;// 客户名称(列表显示) String(Y)
	private String branchName;// 网点名称 String(Y)
	private String branchId;// 网点ID String(Y)
	private String taskContent;// 任务内容 String(Y)
	private String appointmentDate;// 预约时间 String(Y)
	private String reportTime;// 报修时间 String(Y)
	private String reportTel;// 报修电话 String(Y)
	private String workformId;// 工单ID(不显示，后期查询，提交数据要用) String(Y)
	private String equipmentId;// 设备ID String(Y)
	private String equipmentModel;// 设备型号 String(Y)
	private WsDeviceLocationBean deviceLocation;// 设备所在地gps信息数据结构(Y)
	private String createDate;// 派单时间 String(Y)
	private String engineerName;// 工程师 String(Y)
	private String installAddress;// 安装地址 String(Y)
	private String atmManager;// ATM管理员 String(Y)
	private String atmManagerTel;// ATM管理员联系方式 String(Y)
	private String acceptTime;// 接收时间 String(Y)
	private String leaveTime;// 出发时间 String(Y)
	private String arriveTime;// 到达时间 String(Y)
	private String startWorkTime;// 开始工作时间 String(Y)
	private String finishWorkTime;// 工作完成时间 String(Y)
	private String workFormStatus;	//工单状态
	// private String taskStatus;// 任务状态

	/*public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}*/
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getTaskLevel() {
		return taskLevel;
	}
	public void setTaskLevel(String taskLevel) {
		this.taskLevel = taskLevel;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getTaskContent() {
		return taskContent;
	}
	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public String getReportTel() {
		return reportTel;
	}
	public void setReportTel(String reportTel) {
		this.reportTel = reportTel;
	}
	public String getWorkformId() {
		return workformId;
	}
	public void setWorkformId(String workformId) {
		this.workformId = workformId;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentModel() {
		return equipmentModel;
	}
	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	public WsDeviceLocationBean getDeviceLocation() {
		return deviceLocation;
	}
	public void setDeviceLocation(WsDeviceLocationBean deviceLocation) {
		this.deviceLocation = deviceLocation;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getEngineerName() {
		return engineerName;
	}
	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	public String getInstallAddress() {
		return installAddress;
	}
	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}
	public String getAtmManager() {
		return atmManager;
	}
	public void setAtmManager(String atmManager) {
		this.atmManager = atmManager;
	}
	public String getAtmManagerTel() {
		return atmManagerTel;
	}
	public void setAtmManagerTel(String atmManagerTel) {
		this.atmManagerTel = atmManagerTel;
	}
	public String getAcceptTime() {
		if(acceptTime==null){
			return "";
		}
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getLeaveTime() {
		if(leaveTime==null){
			return "";
		}
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getArriveTime() {
		if(arriveTime==null){
			return "";
		}
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getStartWorkTime() {
		if(startWorkTime==null){
			return "";
		}
		return startWorkTime;
	}
	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}
	public String getFinishWorkTime() {
		if(finishWorkTime==null){
			return "";
		}
		return finishWorkTime;
	}
	public void setFinishWorkTime(String finishWorkTime) {
		this.finishWorkTime = finishWorkTime;
	}
	public String getWorkFormStatus() {
		return workFormStatus;
	}
	public void setWorkFormStatus(String workFormStatus) {
		this.workFormStatus = workFormStatus;
	}

    public String getWorkFormStatusResult(){
        String result="未接受";
        if ("处理中".equals(workFormStatus)) { // 处理中
            if (null!=finishWorkTime && !"".equals(finishWorkTime)) {
                result = "已完成";
            } else if (null!=startWorkTime && !"".equals(startWorkTime)) {
                result = "已开始";
            } else if (null!=arriveTime && !"".equals(arriveTime)) {
                result = "已到达";
            } else if (null!=leaveTime && !"".equals(leaveTime)) {
                result = "已出发";
            } else if (null!=acceptTime && !"".equals(acceptTime)) {
                result = "已接受";
            }
        } else if ("已退回".equals(workFormStatus)) { // 已退回
            result = "已退回";
        }
        return result;
    }
	
}
