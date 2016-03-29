package com.grgbanking.core.entity.workorder;

import java.util.List;

//待处理工单中所属任务详细信息
public class WsPendingWorkTaskJsonListBean {

	private String taskId;// 任务ID String(Y)
	private String taskType;// 任务类型,{WX,BY等} String(Y)
	private String taskTypeText;// 任务类型
	private String taskContent;// 任务内容 String(Y)
	private Boolean isRemove;// 是否能移除 String(Y)
	private String taskSource;// 任务来源
	private WsSubmitWorkTaskBean InstallAndRemoveDeviceModel;// 数据结构（N），数据结构详见5.1.16
																// 安装开通，安装辅助，移机开通，移机辅助所属任务记录
	private WsModuleWorkTaskBean taskAttributeOption;// 数据结构（Y），数据结构详见5.1.11
														// 任务属性选项
	private WsSjTaskReplaceBean beforSjModel;// 数据结构(Y)，数组内数据结构详见5.1.18 升级前模块记录
	private WsSjTaskReplaceBean afterSjModel;

	private List<WsWorkTaskRepairPlaceBean> deviceHitch;// Array(Y) 故障部位
	private List<WsWorkTaskReplaceBean> importModel;// Array(Y) 主要模块替换
	private List<WsWorkTaskReplaceBean> subModel;// Array(Y) 零备件替换

	private String workIsFinish;// String(Y) 工作完成情况
	private String workRemark;// String(Y) 工作描述
	private String costTime;// String(Y) 工作耗时
	private String equipmentConfigId; // 设备ID String(Y)
	private String createTypes; // 复用该字段用于存放openid

	public List<WsWorkTaskRepairPlaceBean> getDeviceHitch() {
		return deviceHitch;
	}

	public void setDeviceHitch(List<WsWorkTaskRepairPlaceBean> deviceHitch) {
		this.deviceHitch = deviceHitch;
	}

	public List<WsWorkTaskReplaceBean> getImportModel() {
		return importModel;
	}

	public void setImportModel(List<WsWorkTaskReplaceBean> importModel) {
		this.importModel = importModel;
	}

	public List<WsWorkTaskReplaceBean> getSubModel() {
		return subModel;
	}

	public void setSubModel(List<WsWorkTaskReplaceBean> subModel) {
		this.subModel = subModel;
	}

	public WsSjTaskReplaceBean getAfterSjModel() {
		return afterSjModel;
	}

	public void setAfterSjModel(WsSjTaskReplaceBean afterSjModel) {
		this.afterSjModel = afterSjModel;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public Boolean getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(Boolean isRemove) {
		this.isRemove = isRemove;
	}

	public WsSubmitWorkTaskBean getInstallAndRemoveDeviceModel() {
		return InstallAndRemoveDeviceModel;
	}

	public void setInstallAndRemoveDeviceModel(
			WsSubmitWorkTaskBean installAndRemoveDeviceModel) {
		InstallAndRemoveDeviceModel = installAndRemoveDeviceModel;
	}

	public WsModuleWorkTaskBean getTaskAttributeOption() {
		return taskAttributeOption;
	}

	public void setTaskAttributeOption(WsModuleWorkTaskBean taskAttributeOption) {
		this.taskAttributeOption = taskAttributeOption;
	}

	public WsSjTaskReplaceBean getBeforSjModel() {
		return beforSjModel;
	}

	public void setBeforSjModel(WsSjTaskReplaceBean beforSjModel) {
		this.beforSjModel = beforSjModel;
	}
	public String getTaskTypeText() {
		return taskTypeText;
	}

	public void setTaskTypeText(String taskTypeText) {
		this.taskTypeText = taskTypeText;
	}

	public String getWorkIsFinish() {
		return workIsFinish;
	}

	public void setWorkIsFinish(String workIsFinish) {
		this.workIsFinish = workIsFinish;
	}

	public String getWorkRemark() {
		return workRemark;
	}

	public void setWorkRemark(String workRemark) {
		this.workRemark = workRemark;
	}

	public String getCostTime() {
		return costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

	public String getTaskSource() {
		return taskSource;
	}

	public void setTaskSource(String taskSource) {
		this.taskSource = taskSource;
	}

	public String getEquipmentConfigId() {
		return equipmentConfigId;
	}

	public void setEquipmentConfigId(String equipmentConfigId) {
		this.equipmentConfigId = equipmentConfigId;
	}
	
	public boolean isSameTaskType(Object obj){
		return this.taskId.equalsIgnoreCase(((WsPendingWorkTaskJsonListBean)obj).getTaskId());
	}

	public String getCreateTypes() {
		return createTypes;
	}

	public void setCreateTypes(String createTypes) {
		this.createTypes = createTypes;
	}
}
