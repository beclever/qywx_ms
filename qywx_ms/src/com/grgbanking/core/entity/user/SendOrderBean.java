/**   
 * 文件名：GpsInfo.java</br>
 * 描述： </br>
 * 开发人员：谭明 </br>
 * 创建时间： 2013-9-18下午02:44:11
 */ 

package com.grgbanking.core.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/** 
 * 类名: GpsInfo</br> 
 * 包名：com.grgbanking.user.bean </br> 
 * 描述: </br>GPS信息对象
 * 发布版本号：</br>
 * 开发人员： 谭明</br>
 * 创建时间： 2013-9-18下午02:44:11 
 */

@Entity
@Table(name = "T_MOBILE_SEND_ORDER")
public class SendOrderBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5165209639471651493L;

	public SendOrderBean() {
	}

	public SendOrderBean(Long id, String isSend, String userCode,
			String serialNumber, String equipmentModel, String customsName,
			String branchName, String installAddress, String branchPrincipal,
			String branchPrincipalTel, Date reportTime, Date appointmentDate,
			String taskType, String taskTypeName, String engineerId,
			String engineerName, String taskContent, Date createTime,
			String deviceStatus, String tempSerialNumber
			) {
		super();
		this.id = id;
		this.isSend = isSend;
		this.userCode = userCode;
		this.serialNumber = serialNumber;
		this.equipmentModel = equipmentModel;
		this.customsName = customsName;
		this.branchName = branchName;
		this.installAddress = installAddress;
		this.branchPrincipal = branchPrincipal;
		this.branchPrincipalTel = branchPrincipalTel;
		this.reportTime = reportTime;
		this.appointmentDate = appointmentDate;
		this.taskType = taskType;
		this.taskTypeName = taskTypeName;
		this.engineerId = engineerId;
		this.engineerName = engineerName;
		this.taskContent = taskContent;
		this.createTime = createTime;
		this.deviceStatus = deviceStatus;
		this.tempSerialNumber = tempSerialNumber;
	}

	/**
	 * ID
	 */
	@Id
	@SequenceGenerator(name = "SEQ_MOBILE_SEND_ORDER_ID", sequenceName = "SEQ_MOBILE_SEND_ORDER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOBILE_SEND_ORDER_ID")
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	/**
	 * 判断工单是否派出,进行逻辑删除//"1"位一派出的工单,"0"位未派出的工单
	 */
	@Column(name = "IS_SEND")
	private String isSend;
	/**
	 * 新建工单人的code
	 */
	@Column(name = "USER_CODE")
	private String userCode;
	/**
	 * 设备序列号	
	 */
	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;
	/**
	 * 整机型号
	 */
	@Column(name = "EQUIPMENT_MODEL")
	private String equipmentModel;
	/**
	 * 客户名称
	 */
	@Column(name = "CUSTOMS_NAME")
	private String customsName;
	/**
	 * 网点名称
	 */
	@Column(name = "BRANCH_NAME")
	private String branchName;
	
	/**
	 * 设备地址
	 */
	@Column(name = "INSTALL_ADDRESS")
	private String installAddress;
	
	/**
	 * 报修联系人
	 */
	@Column(name = "BRANCH_PRINCIPAL")
	private String branchPrincipal;
	
	/**
	 * 联系人电话
	 */
	@Column(name = "BRANCH_PRINCIPAL_TEL")
	private String branchPrincipalTel;
	
	/**
	 * 报修时间
	 */
	@Column(name = "REPORT_TIME")
	private Date reportTime;
	
	/**
	 * 预约上门时间
	 */
	@Column(name = "APPOINTMENT_DATE")
	private Date appointmentDate;
	
	/**
	 * 任务类型code
	 */
	@Column(name = "TASK_TYPE")
	private String taskType;
	
	/**
	 * 任务类型
	 */
	@Column(name = "TASK_TYPE_NAME")
	private String taskTypeName;
	
	/**
	 * 工程师(id)
	 */
	@Column(name = "ENGINEER_ID")
	private String engineerId;
	
	/**
	 * 工程师
	 */
	@Column(name = "ENGINEER_NAME")
	private String engineerName;
	
	/**
	 * 任务内容
	 */
	@Column(name = "TASK_CONTENT")
	private String taskContent;
	
	/**
	 * 工单创建时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	/**
	 * 设备状态
	 */
	@Column(name = "DEVICE_STATUS")
	private String deviceStatus;

	/**
	 * 临时设备序列号
	 */
	@Column(name = "TEMP_SERIAL_NUMBER")
	private String tempSerialNumber;
	
	@Transient
	private String createTimeString;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getEquipmentModel() {
		return equipmentModel;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}

	public String getCustomsName() {
		return customsName;
	}

	public void setCustomsName(String customsName) {
		this.customsName = customsName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getInstallAddress() {
		return installAddress;
	}

	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}

	public String getBranchPrincipal() {
		return branchPrincipal;
	}

	public void setBranchPrincipal(String branchPrincipal) {
		this.branchPrincipal = branchPrincipal;
	}

	public String getBranchPrincipalTel() {
		return branchPrincipalTel;
	}

	public void setBranchPrincipalTel(String branchPrincipalTel) {
		this.branchPrincipalTel = branchPrincipalTel;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

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

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(String engineerId) {
		this.engineerId = engineerId;
	}

	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getCreateTimeString() {
		return createTimeString;
	}

	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}

	public String getTempSerialNumber() {
		return tempSerialNumber;
	}

	public void setTempSerialNumber(String tempSerialNumber) {
		this.tempSerialNumber = tempSerialNumber;
	}

	@Override
	public String toString() {
		return "SendOrderBean [id=" + id + ", isSend=" + isSend + ", userCode="
				+ userCode + ", serialNumber=" + serialNumber
				+ ", equipmentModel=" + equipmentModel + ", customsName="
				+ customsName + ", branchName=" + branchName
				+ ", installAddress=" + installAddress + ", branchPrincipal="
				+ branchPrincipal + ", branchPrincipalTel="
				+ branchPrincipalTel + ", reportTime=" + reportTime
				+ ", appointmentDate=" + appointmentDate + ", taskType="
				+ taskType + ", taskTypeName=" + taskTypeName + ", engineerId="
				+ engineerId + ", engineerName=" + engineerName
				+ ", taskContent=" + taskContent + ", createTime=" + createTime
				+ ", deviceStatus=" + deviceStatus + ", tempSerialNumber="
				+ tempSerialNumber + ", createTimeString=" + createTimeString
				+ "]";
	}

	


	
}
