package com.grgbanking.css.bean.work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grgbanking.css.bean.equipment.EquipmentHistoryProblem;
import com.grgbanking.css.common.CssBaseEntity;
import com.grgbanking.css.common.PhoneImage;

/**
 * 工单信息 Product:Grgbanking Service Of Customer System. Version:2.0 Copyright
 * 2010 by Grgbanking All Rights Reserved. Author: Administrator Date: 2010-7-29
 * 下午11:43:09
 */
public class WorkFormBean extends CssBaseEntity {

	/**
	 * 工单ID
	 */
	private Integer workFormId;

	/**
	 * PO单号
	 */
	private String poNumber;
	/**
	 * 设备ID
	 */
	private Integer equipmentId;
	/**
	 * 预约上门时间
	 */
	private Date appointmentDate;
	/**
	 * 到达现场时间
	 */
	private Date arriveTime;
	/**
	 * 工作开始时间
	 */
	private Date workStartDate;
	/**
	 * 工作完成时间
	 */
	private Date workFinishDate;
	/**
	 * 报修时间
	 */
	private Date reportTime;
	/**
	 * 报修电话
	 */
	private String reportTelephone;
	/**
	 * 总工作耗时
	 */
	private Integer costTime;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 任务状态
	 */
	private String taskStatus;
	/**
	 * 工程师名称
	 */
	private String engineerName;
	/**
	 * 工程师ID
	 */
	private Integer engineerId;

	private String serialNumber;// 设备序列号
	private String customerName;// 客户名称
	private String branchName;// 网点名称
	private String equipmentModel;// 设备型号
	private String atmManager;// ATM管理员
	private String installAddress;// 安装地址
	private String manufacturer;// 制造商
	private String saleContractNo;// 销售合同号
	private String saleProperty;// 销售属性
	private String maintainContractNo;// 维保合同号
	private String warrantyType;// 服务保修类型
	private String equipmentType;// 整机类型
	private Integer regionNameId;// 区域ID
	private Integer departmentId;// 服务站ID
	private String departmentIds;// 多选用
	private String departmentId0;//服务站模糊查询
	private Integer customerId;// 客户ID
	private String customerIds;// 多选用
	private String city;// 市
	private List<WorkTaskBean> workTaskList;
	// 库管员审核时，需要补全替换前条码
	private List<TaskReplaceBean> taskReplaceList = new ArrayList<TaskReplaceBean>();

	private List<EquipmentHistoryProblem> equipmentHistoryProblemList = new ArrayList<EquipmentHistoryProblem>();// 设备历史遗留问题

	private List<WorkFormFee> workFormFeeList = new ArrayList<WorkFormFee>();// 工单费用列表

	private List<WorkFormComment> commentList = new ArrayList<WorkFormComment>();// 审批意见
	
	private List<PhoneImage> phoneImageList=new ArrayList<PhoneImage>();//附件列表

	private String comments;// 备注

	private String problemPartRemark; // 故障部位
	private String processRemark;// 处理方法

	private String problemPartIds;// 故障部位(多选用)
	private String problemReasonIds;// 故障原因(多选用)
	private String problemMethodIds;// 处理方法(多选用)

	/**
	 * 跟随人员（用逗号隔开）
	 */
	private String followUserName;// 同行人员姓名
	private String followUserId;// 同行人员 ID

	private String[] statusArray;// 查询条件

	private String hasRevisit; // 是否回访
	private Date lastRevisitTime; // 回访工单的最后时间

	private String taskType;// 任务类型
	private String province;// 省份
	private String workFinishDateStart;
	private String workFinishDateEnd;
	private String createDateStart;
	private String createDateEnd;
	private String staionName;// 服务站
	private String regionName;// 区域
	private String equipmentStatus;// 设备状态
	private String installModel;// 安装方式
	private Date warrantyEndDate;// 到保日期
	private String atmManagerTel;// ATM管理员电话
	private Integer provinceId;// 省份ID
	private Integer cityId;// 市ID
	private String warrantyEndDateStart;// 到保日期开始时间
	private String warrantyEndDateEnd;// 到保日期结束时间
	private String installDateStart;// 安装时间
	private String installDateEnd;

	private String lastAuditDateStart;
	private String lastAuditDateEnd;
	private String createType;

	private String taskLevel;// 任务等级

	private String equipmentChargeName;// 设备负责人

	// by luow 2012-10-18 新增客户满意度字段
	private String satisfiedType;// 客户满意度

	private String satisfiedTypeSms;// 客户短信满意度 by wwb 2014-06-24

	// by luow 2012-10-23 新增纸质工单编号查询条件
	private String paperFormCode;

	// by luow 2012-10-23 新增工单编号集合，“,”分隔
	private String workFormIds;

	private String modifyDateType;

	private String stopReplace;// 工单退回后，是否可以录备件

	// 报修人与接待 人
	private Integer repairsContactId;
	private Integer receiveContactId;
	private String repairsManName;
	private String receiveManName;
	private String repairsMoblie;// 手机
	private String receiveMoblie;
	private String repairsTelephone;// 电话
	private String receiveTelephone;
	private String isManager;// 是否管理员
	
	private Integer equipmentDepartmentId;// 设备中服务站ID
	
	private PhoneImage phoneImage;

	public Integer getWorkFormId() {
		return workFormId;
	}

	public void setWorkFormId(Integer workFormId) {
		this.workFormId = workFormId;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public Date getWorkStartDate() {
		return workStartDate;
	}

	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}

	public Date getWorkFinishDate() {
		return workFinishDate;
	}

	public void setWorkFinishDate(Date workFinishDate) {
		this.workFinishDate = workFinishDate;
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

	public Integer getCostTime() {
		return costTime;
	}

	public void setCostTime(Integer costTime) {
		this.costTime = costTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}

	public Integer getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}

	public String[] getStatusArray() {
		return statusArray;
	}

	public void setStatusArray(String[] statusArray) {
		this.statusArray = statusArray;
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

	public String getEquipmentModel() {
		return equipmentModel;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}

	public String getAtmManager() {
		return atmManager;
	}

	public void setAtmManager(String atmManager) {
		this.atmManager = atmManager;
	}

	public String getInstallAddress() {
		return installAddress;
	}

	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}

	public List<WorkTaskBean> getWorkTaskList() {
		return workTaskList;
	}

	public void setWorkTaskList(List<WorkTaskBean> workTaskList) {
		this.workTaskList = workTaskList;
	}

	public List<EquipmentHistoryProblem> getEquipmentHistoryProblemList() {
		return equipmentHistoryProblemList;
	}

	public void setEquipmentHistoryProblemList(List<EquipmentHistoryProblem> equipmentHistoryProblemList) {
		this.equipmentHistoryProblemList = equipmentHistoryProblemList;
	}

	public List<WorkFormFee> getWorkFormFeeList() {
		return workFormFeeList;
	}

	public void setWorkFormFeeList(List<WorkFormFee> workFormFeeList) {
		this.workFormFeeList = workFormFeeList;
	}

	public List<WorkFormComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<WorkFormComment> commentList) {
		this.commentList = commentList;
	}

	public String getHasRevisit() {
		return hasRevisit;
	}

	public void setHasRevisit(String hasRevisit) {
		this.hasRevisit = hasRevisit;
	}

	public Date getLastRevisitTime() {
		return lastRevisitTime;
	}

	public void setLastRevisitTime(Date lastRevisitTime) {
		this.lastRevisitTime = lastRevisitTime;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getWorkFinishDateEnd() {
		return workFinishDateEnd;
	}

	public void setWorkFinishDateEnd(String workFinishDateEnd) {
		this.workFinishDateEnd = workFinishDateEnd;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getStaionName() {
		return staionName;
	}

	public void setStaionName(String staionName) {
		this.staionName = staionName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public String getInstallModel() {
		return installModel;
	}

	public void setInstallModel(String installModel) {
		this.installModel = installModel;
	}

	public Date getWarrantyEndDate() {
		return warrantyEndDate;
	}

	public void setWarrantyEndDate(Date warrantyEndDate) {
		this.warrantyEndDate = warrantyEndDate;
	}

	public String getAtmManagerTel() {
		return atmManagerTel;
	}

	public void setAtmManagerTel(String atmManagerTel) {
		this.atmManagerTel = atmManagerTel;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSaleContractNo() {
		return saleContractNo;
	}

	public void setSaleContractNo(String saleContractNo) {
		this.saleContractNo = saleContractNo;
	}

	public String getSaleProperty() {
		return saleProperty;
	}

	public void setSaleProperty(String saleProperty) {
		this.saleProperty = saleProperty;
	}

	public String getMaintainContractNo() {
		return maintainContractNo;
	}

	public void setMaintainContractNo(String maintainContractNo) {
		this.maintainContractNo = maintainContractNo;
	}

	public String getWarrantyType() {
		return warrantyType;
	}

	public void setWarrantyType(String warrantyType) {
		this.warrantyType = warrantyType;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public Integer getRegionNameId() {
		return regionNameId;
	}

	public void setRegionNameId(Integer regionNameId) {
		this.regionNameId = regionNameId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}

	public String getWorkFinishDateStart() {
		return workFinishDateStart;
	}

	public void setWorkFinishDateStart(String workFinishDateStart) {
		this.workFinishDateStart = workFinishDateStart;
	}

	public String getFollowUserName() {
		return followUserName;
	}

	public void setFollowUserName(String followUserName) {
		this.followUserName = followUserName;
	}

	public String getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(String followUserId) {
		this.followUserId = followUserId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<TaskReplaceBean> getTaskReplaceList() {
		return taskReplaceList;
	}

	public void setTaskReplaceList(List<TaskReplaceBean> taskReplaceList) {
		this.taskReplaceList = taskReplaceList;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

	public String getWarrantyEndDateStart() {
		return warrantyEndDateStart;
	}

	public void setWarrantyEndDateStart(String warrantyEndDateStart) {
		this.warrantyEndDateStart = warrantyEndDateStart;
	}

	public String getWarrantyEndDateEnd() {
		return warrantyEndDateEnd;
	}

	public void setWarrantyEndDateEnd(String warrantyEndDateEnd) {
		this.warrantyEndDateEnd = warrantyEndDateEnd;
	}

	public String getProblemPartRemark() {
		return problemPartRemark;
	}

	public void setProblemPartRemark(String problemPartRemark) {
		this.problemPartRemark = problemPartRemark;
	}

	public String getProcessRemark() {
		return processRemark;
	}

	public void setProcessRemark(String processRemark) {
		this.processRemark = processRemark;
	}

	public String getProblemPartIds() {
		return problemPartIds;
	}

	public void setProblemPartIds(String problemPartIds) {
		this.problemPartIds = problemPartIds;
	}

	public String getProblemReasonIds() {
		return problemReasonIds;
	}

	public void setProblemReasonIds(String problemReasonIds) {
		this.problemReasonIds = problemReasonIds;
	}

	public String getProblemMethodIds() {
		return problemMethodIds;
	}

	public void setProblemMethodIds(String problemMethodIds) {
		this.problemMethodIds = problemMethodIds;
	}

	public String getLastAuditDateStart() {
		return lastAuditDateStart;
	}

	public void setLastAuditDateStart(String lastAuditDateStart) {
		this.lastAuditDateStart = lastAuditDateStart;
	}

	public String getLastAuditDateEnd() {
		return lastAuditDateEnd;
	}

	public void setLastAuditDateEnd(String lastAuditDateEnd) {
		this.lastAuditDateEnd = lastAuditDateEnd;
	}

	public String getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(String taskLevel) {
		this.taskLevel = taskLevel;
	}

	public String getEquipmentChargeName() {
		return equipmentChargeName;
	}

	public void setEquipmentChargeName(String equipmentChargeName) {
		this.equipmentChargeName = equipmentChargeName;
	}

	public String getInstallDateStart() {
		return installDateStart;
	}

	public void setInstallDateStart(String installDateStart) {
		this.installDateStart = installDateStart;
	}

	public String getInstallDateEnd() {
		return installDateEnd;
	}

	public void setInstallDateEnd(String installDateEnd) {
		this.installDateEnd = installDateEnd;
	}

	public String getSatisfiedType() {
		return satisfiedType;
	}

	public void setSatisfiedType(String satisfiedType) {
		this.satisfiedType = satisfiedType;
	}

	public String getPaperFormCode() {
		return paperFormCode;
	}

	public void setPaperFormCode(String paperFormCode) {
		this.paperFormCode = paperFormCode;
	}

	public String getWorkFormIds() {
		return workFormIds;
	}

	public void setWorkFormIds(String workFormIds) {
		this.workFormIds = workFormIds;
	}

	public String getModifyDateType() {
		return modifyDateType;
	}

	public void setModifyDateType(String modifyDateType) {
		this.modifyDateType = modifyDateType;
	}

	public String getStopReplace() {
		return stopReplace;
	}

	public void setStopReplace(String stopReplace) {
		this.stopReplace = stopReplace;
	}

	public String getCreateType() {
		return createType;
	}

	public void setCreateType(String createType) {
		this.createType = createType;
	}

	public Integer getRepairsContactId() {
		return repairsContactId;
	}

	public void setRepairsContactId(Integer repairsContactId) {
		this.repairsContactId = repairsContactId;
	}

	public Integer getReceiveContactId() {
		return receiveContactId;
	}

	public void setReceiveContactId(Integer receiveContactId) {
		this.receiveContactId = receiveContactId;
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

	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

	public String getSatisfiedTypeSms() {
		return satisfiedTypeSms;
	}

	public void setSatisfiedTypeSms(String satisfiedTypeSms) {
		this.satisfiedTypeSms = satisfiedTypeSms;
	}

	public Integer getEquipmentDepartmentId() {
		return equipmentDepartmentId;
	}

	public void setEquipmentDepartmentId(Integer equipmentDepartmentId) {
		this.equipmentDepartmentId = equipmentDepartmentId;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public List<PhoneImage> getPhoneImageList() {
		return phoneImageList;
	}

	public void setPhoneImageList(List<PhoneImage> phoneImageList) {
		this.phoneImageList = phoneImageList;
	}

	public PhoneImage getPhoneImage() {
		return phoneImage;
	}

	public void setPhoneImage(PhoneImage phoneImage) {
		this.phoneImage = phoneImage;
	}

	public String getDepartmentId0() {
		return departmentId0;
	}

	public void setDepartmentId0(String departmentId0) {
		this.departmentId0 = departmentId0;
	}
	

}
