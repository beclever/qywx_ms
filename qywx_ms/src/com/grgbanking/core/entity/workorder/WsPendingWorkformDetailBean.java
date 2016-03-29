package com.grgbanking.core.entity.workorder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grgbanking.core.entity.equipment.WsDeviceLocationBean;
import com.grgbanking.core.entity.equipment.WsEquipmentHistoryProblemBean;


public class WsPendingWorkformDetailBean {

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
	private String engineerMobilephone;// 工程师电话 diaString(Y)
	private String installAddress;// 安装地址 String(Y)
	private String atmManager;// ATM管理员 String(Y)
	private String atmManagerTel;// ATM管理员联系方式 String(Y)
	private String acceptTime;// 接收时间 String(Y)
	private String leaveTime;// 出发时间 String(Y)
	private String arriveTime;// 到达时间 String(Y)
	private String startWorkTime;// 开始工作时间 String(Y)
	private String finishWorkTime;// 工作完成时间 String(Y)
	// 报修人与接待 人
	private Integer repairsId;
	private Integer receiveId;
    private String repairsManName;
    private String receiveManName;
    private String repairsMoblie;// 手机
    private String receiveMoblie;
    private String repairsTelephone;// 电话
    private String receiveTelephone;
    
	private WsDeviceLocationBean leaveLocation;// 数据结构(Y)，详见5.1.3 出发点信息
	private WsDeviceLocationBean arriveLocation;// 数据结构(Y)，详见5.1.3 到达点信息
	private WsDeviceLocationBean startWorkLocation;// 数据结构(Y)，详见5.1.3 开始工作点信息
	private WsDeviceLocationBean finishWorkLocation;// 数据结构(Y)，详见5.1.3 工作完成点信息
	private WsDeviceLocationBean acceptLocation;//接收位置	数据结构(Y)，详见5.1.3
	private WsWorkformTogetherPersonBean togetherPerson;// 数据结构(Y)，详见5.1.21 同行人员
	private Integer status;// 状态：当前工单所处状态，1：未接收，2：已接收未出发
	
	private String workFormStatus;	//工单状态
	private List<WsPendingWorkTaskJsonListBean> taskJsonList;// Array(Y)，数组内数据结构详见5.1.10
	// 所属任务信息（WX:维修，BY：保养等共11种），
	private List<WsTaskReplaceOptionsBean> mainReplaceModelJson;// Array
	// (Y)，数据结构详见5.1.19
	// 任务属性：主要模块替换选项
	private List<WsTaskReplacePartsOptionsBean> taskReplaceJson;// Array
	// (Y)，数组内数据结构详见5.1.13
	// 任务属性：零备件替换选项
	private List<WsWorkFormFeeBean> workFormFeeJson;// 数据结构(Y)，详见5.1.21 任务属性：工单费用选项
	private List<WsEquipmentHistoryProblemBean> equipmentHistoryProblemResultJson;// 数据结构(Y)，详见5.1.21
	// 任务属性：设备历史遗留问题选项
	//private List<WsEquipmentHistoryProblemBean> equipmentHistoryProblemResultRecord;// Array(Y),数组内结构详见5.1.18
	// 设备历史遗留问题记录

	private String iniPaperId;// 初始纸质工单主键ID
	private String iniPaperCode;// 初始纸质工单号
	private String intPaperStatus;	//初始纸质工单状态

	private WsOptionsBean degreeSatisfaction;// 数据结构(Y)，详见5.1.21 客户评价选项
	private WsOptionsBean abandonReason;// 数据结构(Y)，详见5.1.21 废弃原因
	
	private String sptRemark;//工单备件备注
	
	private String advice;	//调度建议
	
	private WsOptionsBean feeType;//	费用项目 	数据结构(Y),详见5.1.21	
	private WsOptionsBean followUser;//	同行人员	数据结构(Y),详见5.1.21
	private WsOptionsBean historyLevel;//	遗留问题级别 

	private String dispatchName;//调度主任
	private String dispatchTel;//调度主任电话
	
	private String workformBackContent;//工单退回原因

	private String taskStatus;// 任务状态 MP2.0工单查询功能说明

	public WsOptionsBean getHistoryLevel() {
		return historyLevel;
	}

	public void setHistoryLevel(WsOptionsBean historyLevel) {
		this.historyLevel = historyLevel;
	}

	public WsOptionsBean getFeeType() {
		return feeType;
	}

	public void setFeeType(WsOptionsBean feeType) {
		this.feeType = feeType;
	}

	public WsOptionsBean getFollowUser() {
		return followUser;
	}

	public void setFollowUser(WsOptionsBean followUser) {
		this.followUser = followUser;
	}

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
		if(acceptTime ==""){
			acceptTime="";
		}
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getLeaveTime() {
		if(leaveTime ==""){
			leaveTime ="";
		}
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getStartWorkTime() {
		return startWorkTime;
	}

	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	public String getFinishWorkTime() {
		return finishWorkTime;
	}

	public void setFinishWorkTime(String finishWorkTime) {
		this.finishWorkTime = finishWorkTime;
	}
	
	public Integer getRepairsId() {
        return repairsId;
    }

    public void setRepairsId(Integer repairsId) {
        this.repairsId = repairsId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public String getRepairsManName() {
        return repairsManName;
    }

    public void setRepairsManName(String repairsManName) {
        this.repairsManName = repairsManName;
    }

    public String getReceiveManName() {
        return receiveManName;
    }

    public void setReceiveManName(String receiveManName) {
        this.receiveManName = receiveManName;
    }

    public String getRepairsMoblie() {
        return repairsMoblie;
    }

    public void setRepairsMoblie(String repairsMoblie) {
        this.repairsMoblie = repairsMoblie;
    }

    public String getReceiveMoblie() {
        return receiveMoblie;
    }

    public void setReceiveMoblie(String receiveMoblie) {
        this.receiveMoblie = receiveMoblie;
    }

    public String getRepairsTelephone() {
        return repairsTelephone;
    }

    public void setRepairsTelephone(String repairsTelephone) {
        this.repairsTelephone = repairsTelephone;
    }

    public String getReceiveTelephone() {
        return receiveTelephone;
    }

    public void setReceiveTelephone(String receiveTelephone) {
        this.receiveTelephone = receiveTelephone;
    }

    public WsDeviceLocationBean getLeaveLocation() {
		return leaveLocation;
	}

	public void setLeaveLocation(WsDeviceLocationBean leaveLocation) {
		this.leaveLocation = leaveLocation;
	}

	public WsDeviceLocationBean getArriveLocation() {
		return arriveLocation;
	}

	public void setArriveLocation(WsDeviceLocationBean arriveLocation) {
		this.arriveLocation = arriveLocation;
	}

	public WsDeviceLocationBean getStartWorkLocation() {
		return startWorkLocation;
	}

	public void setStartWorkLocation(WsDeviceLocationBean startWorkLocation) {
		this.startWorkLocation = startWorkLocation;
	}

	public WsDeviceLocationBean getFinishWorkLocation() {
		return finishWorkLocation;
	}

	public void setFinishWorkLocation(WsDeviceLocationBean finishWorkLocation) {
		this.finishWorkLocation = finishWorkLocation;
	}

	
	public WsWorkformTogetherPersonBean getTogetherPerson() {
		return togetherPerson;
	}

	public void setTogetherPerson(WsWorkformTogetherPersonBean togetherPerson) {
		this.togetherPerson = togetherPerson;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	public String getWorkFormStatus() {
		return workFormStatus;
	}

	public void setWorkFormStatus(String workFormStatus) {
		this.workFormStatus = workFormStatus;
	}

	public List<WsPendingWorkTaskJsonListBean> getTaskJsonList() {
		return taskJsonList;
	}
	
	public void removeTaskJsonFromList(WsPendingWorkTaskJsonListBean bean){
		taskJsonList.remove(bean);
	}

	public void setTaskJsonList(List<WsPendingWorkTaskJsonListBean> taskJsonList) {
		this.taskJsonList = taskJsonList;
	}

	public List<WsTaskReplaceOptionsBean> getMainReplaceModelJson() {
		return mainReplaceModelJson;
	}

	public void setMainReplaceModelJson(
			List<WsTaskReplaceOptionsBean> mainReplaceModelJson) {
		this.mainReplaceModelJson = mainReplaceModelJson;
	}

	public List<WsTaskReplacePartsOptionsBean> getTaskReplaceJson() {
		return taskReplaceJson;
	}

	public void setTaskReplaceJson(
			List<WsTaskReplacePartsOptionsBean> taskReplaceJson) {
		this.taskReplaceJson = taskReplaceJson;
	}

	
	public List<WsEquipmentHistoryProblemBean> getEquipmentHistoryProblemResultJson() {
		return equipmentHistoryProblemResultJson;
	}

	public void setEquipmentHistoryProblemResultJson(
			List<WsEquipmentHistoryProblemBean> equipmentHistoryProblemResultJson) {
		this.equipmentHistoryProblemResultJson = equipmentHistoryProblemResultJson;
	}
	
	public List<WsWorkFormFeeBean> getWorkFormFeeJson() {
		return workFormFeeJson;
	}

	public void setWorkFormFeeJson(List<WsWorkFormFeeBean> workFormFeeJson) {
		this.workFormFeeJson = workFormFeeJson;
	}

	public String getIniPaperId() {
		return iniPaperId;
	}

	public void setIniPaperId(String iniPaperId) {
		this.iniPaperId = iniPaperId;
	}

	public String getIniPaperCode() {
		return iniPaperCode;
	}

	public void setIniPaperCode(String iniPaperCode) {
		this.iniPaperCode = iniPaperCode;
	}

	public WsOptionsBean getDegreeSatisfaction() {
		return degreeSatisfaction;
	}

	public void setDegreeSatisfaction(WsOptionsBean degreeSatisfaction) {
		this.degreeSatisfaction = degreeSatisfaction;
	}

	public WsOptionsBean getAbandonReason() {
		return abandonReason;
	}

	public void setAbandonReason(WsOptionsBean abandonReason) {
		this.abandonReason = abandonReason;
	}

	public String getSptRemark() {
		return sptRemark;
	}

	public void setSptRemark(String sptRemark) {
		this.sptRemark = sptRemark;
	}

	public WsDeviceLocationBean getAcceptLocation() {
		return acceptLocation;
	}

	public void setAcceptLocation(WsDeviceLocationBean acceptLocation) {
		this.acceptLocation = acceptLocation;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getDispatchName() {
		return dispatchName;
	}

	public void setDispatchName(String dispatchName) {
		this.dispatchName = dispatchName;
	}

	public String getDispatchTel() {
		return dispatchTel;
	}

	public void setDispatchTel(String dispatchTel) {
		this.dispatchTel = dispatchTel;
	}
	
	
	public String getWorkformBackContent() {
		return workformBackContent;
	}

	public void setWorkformBackContent(String workformBackContent) {
		this.workformBackContent = workformBackContent;
	}
	
	

	public String getIntPaperStatus() {
		return intPaperStatus;
	}

	public void setIntPaperStatus(String intPaperStatus) {
		this.intPaperStatus = intPaperStatus;
	}

	public Map<String,WsPendingWorkTaskJsonListBean> getTaskJsonMap(){
		Map<String,WsPendingWorkTaskJsonListBean> result = new HashMap<String,WsPendingWorkTaskJsonListBean>();
		if(taskJsonList!=null){
			for(WsPendingWorkTaskJsonListBean wpwtjlb:taskJsonList){
				result.put(wpwtjlb.getTaskId(), wpwtjlb);
			}
		}
		return result;	
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getEngineerMobilephone() {
		return engineerMobilephone;
	}

	public void setEngineerMobilephone(String engineerMobilephone) {
		this.engineerMobilephone = engineerMobilephone;
	}
	
}
