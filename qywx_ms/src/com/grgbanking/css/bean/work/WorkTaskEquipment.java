package com.grgbanking.css.bean.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 任务设备信息
 * @author yzhua
 */
@Entity
@Table(name = "V_SERVICES_TASK_EQUIPMENT")
public class WorkTaskEquipment {
	
	
	private Integer id;
	private Integer taskId;
	private Date createDate;// 创建日期
	private String deleted;
	private Integer customerId;  //服务客户
	private String branchName;//网点名称
	private String installType;//安装类型
	private String installModel;//安装方式
	private Integer provinceId;//省份ID
	private Integer cityId;//城市ID
	private String installAddress;//安装地址
	private String atmManager;//ATM管理员
	private String branchPrincipal;//网点负责人
	private String atmManagerTel;//ATM管理员电话
	private String branchPrincipalTel;//网点负责人电话
	private String atmNumber;//ATM号
	private String encryptType;//加密方式
	private String bankNumber;//银行号
	private String bankTerminalNumber;//银行终端号
	private String atmcName;//ATMC名称
	private String atmcVersion;//ATMC版本
	private String atmcSpVersion;//SP版本(跨平台)
	private String localIp;//本地ID
	private String pip;//P端IP
	private String gateway;//网关
	private String subnetHideAddress;//子网掩码
	private String netProtocol;//网络协议
	private String dailyDealCount;//日均交易量
	private String dailyDealMoney;//日均交易金额
	private String trafficLine;//乘车路线
	private String referenceCharge;//参考费用
	private String environmentDust;//环境
	private String environmentHumidity;
	private String environmentTemperature;
	private String environmentPowerSupply;
	private String environmentRainDefence;
	private String environmentSunshine;
	private String operateSystem;//操作系统
	private String osVersion;//操作系统版本
	private String installProperty;//装机属性
	private Integer equipmentCharge;//设备负责人
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_SEQ_SERVICES_TASK_EQUIPMENT")
	@SequenceGenerator(name = "GENERATOR_SEQ_SERVICES_TASK_EQUIPMENT", sequenceName = "SEQ_SERVICES_TASK_EQUIPMENT", initialValue = 1, allocationSize = 1)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "TASK_ID")
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "DELETED")
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	} 
	@Column(name = "CUSTOMER_ID")
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	@Column(name = "BRANCH_NAME")
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Column(name = "INSTALL_TYPE")	
	public String getInstallType() {
		return installType;
	}
	public void setInstallType(String installType) {
		this.installType = installType;
	}
	@Column(name = "INSTALL_MODEL")	
	public String getInstallModel() {
		return installModel;
	}
	public void setInstallModel(String installModel) {
		this.installModel = installModel;
	}
	@Column(name = "PROVINCE_ID")
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	@Column(name = "CITY_ID")
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	@Column(name = "INSTALL_ADDRESS")
	public String getInstallAddress() {
		return installAddress;
	}
	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}
	@Column(name = "ATM_MANAGER")
	public String getAtmManager() {
		return atmManager;
	}
	public void setAtmManager(String atmManager) {
		this.atmManager = atmManager;
	}
	@Column(name = "BRANCH_PRINCIPAL")
	public String getBranchPrincipal() {
		return branchPrincipal;
	}
	public void setBranchPrincipal(String branchPrincipal) {
		this.branchPrincipal = branchPrincipal;
	}
	@Column(name = "ATM_MANAGER_TEL")
	public String getAtmManagerTel() {
		return atmManagerTel;
	}
	public void setAtmManagerTel(String atmManagerTel) {
		this.atmManagerTel = atmManagerTel;
	}
	@Column(name = "BRANCH_PRINCIPAL_TEL")
	public String getBranchPrincipalTel() {
		return branchPrincipalTel;
	}
	public void setBranchPrincipalTel(String branchPrincipalTel) {
		this.branchPrincipalTel = branchPrincipalTel;
	}
	@Column(name = "ATM_NUMBER")
	public String getAtmNumber() {
		return atmNumber;
	}
	public void setAtmNumber(String atmNumber) {
		this.atmNumber = atmNumber;
	}
	@Column(name = "ENCRYPT_TYPE")
	public String getEncryptType() {
		return encryptType;
	}
	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}
	@Column(name = "BANK_NUMBER")
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	@Column(name = "BANK_TERMINAL_NUMBER")
	public String getBankTerminalNumber() {
		return bankTerminalNumber;
	}
	public void setBankTerminalNumber(String bankTerminalNumber) {
		this.bankTerminalNumber = bankTerminalNumber;
	}
	@Column(name = "ATMC_NAME")
	public String getAtmcName() {
		return atmcName;
	}
	public void setAtmcName(String atmcName) {
		this.atmcName = atmcName;
	}
	@Column(name = "ATMC_VERSION")
	public String getAtmcVersion() {
		return atmcVersion;
	}
	public void setAtmcVersion(String atmcVersion) {
		this.atmcVersion = atmcVersion;
	}
	@Column(name = "ATMC_SP_VERSION")
	public String getAtmcSpVersion() {
		return atmcSpVersion;
	}
	public void setAtmcSpVersion(String atmcSpVersion) {
		this.atmcSpVersion = atmcSpVersion;
	}
	@Column(name = "LOCAL_IP")
	public String getLocalIp() {
		return localIp;
	}
	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}
	@Column(name = "PIP")
	public String getPip() {
		return pip;
	}
	public void setPip(String pip) {
		this.pip = pip;
	}
	@Column(name = "GATEWAY")
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	@Column(name = "SUBNET_HIDE_ADDRESS")
	public String getSubnetHideAddress() {
		return subnetHideAddress;
	}
	public void setSubnetHideAddress(String subnetHideAddress) {
		this.subnetHideAddress = subnetHideAddress;
	}
	@Column(name = "NET_PROTOCOL")
	public String getNetProtocol() {
		return netProtocol;
	}
	public void setNetProtocol(String netProtocol) {
		this.netProtocol = netProtocol;
	}
	@Column(name = "DAILY_DEAL_COUNT")
	public String getDailyDealCount() {
		return dailyDealCount;
	}
	public void setDailyDealCount(String dailyDealCount) {
		this.dailyDealCount = dailyDealCount;
	}
	@Column(name = "DAILY_DEAL_MONEY")
	public String getDailyDealMoney() {
		return dailyDealMoney;
	}
	public void setDailyDealMoney(String dailyDealMoney) {
		this.dailyDealMoney = dailyDealMoney;
	}
	@Column(name = "TRAFFIC_LINE")
	public String getTrafficLine() {
		return trafficLine;
	}
	public void setTrafficLine(String trafficLine) {
		this.trafficLine = trafficLine;
	}
	@Column(name = "REFERENCE_CHARGE")
	public String getReferenceCharge() {
		return referenceCharge;
	}
	public void setReferenceCharge(String referenceCharge) {
		this.referenceCharge = referenceCharge;
	}
	@Column(name = "ENVIRONMENT_DUST")
	public String getEnvironmentDust() {
		return environmentDust;
	}
	public void setEnvironmentDust(String environmentDust) {
		this.environmentDust = environmentDust;
	}
	@Column(name = "ENVIRONMENT_HUMIDITY")
	public String getEnvironmentHumidity() {
		return environmentHumidity;
	}
	public void setEnvironmentHumidity(String environmentHumidity) {
		this.environmentHumidity = environmentHumidity;
	}
	@Column(name = "ENVIRONMENT_TEMPERATURE")
	public String getEnvironmentTemperature() {
		return environmentTemperature;
	}
	public void setEnvironmentTemperature(String environmentTemperature) {
		this.environmentTemperature = environmentTemperature;
	}
	@Column(name = "ENVIRONMENT_POWER_SUPPLY")
	public String getEnvironmentPowerSupply() {
		return environmentPowerSupply;
	}
	public void setEnvironmentPowerSupply(String environmentPowerSupply) {
		this.environmentPowerSupply = environmentPowerSupply;
	}
	@Column(name = "ENVIRONMENT_RAIN_DEFENCE")
	public String getEnvironmentRainDefence() {
		return environmentRainDefence;
	}
	public void setEnvironmentRainDefence(String environmentRainDefence) {
		this.environmentRainDefence = environmentRainDefence;
	}
	@Column(name = "ENVIRONMENT_SUNSHINE")
	public String getEnvironmentSunshine() {
		return environmentSunshine;
	}
	public void setEnvironmentSunshine(String environmentSunshine) {
		this.environmentSunshine = environmentSunshine;
	}
	@Column(name = "OPERATE_SYSTEM")
	public String getOperateSystem() {
		return operateSystem;
	}
	public void setOperateSystem(String operateSystem) {
		this.operateSystem = operateSystem;
	}
	@Column(name = "OS_VERSION")
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	@Column(name = "INSTALL_PROPERTY")
	public String getInstallProperty() {
		return installProperty;
	}
	public void setInstallProperty(String installProperty) {
		this.installProperty = installProperty;
	}
	@Column(name = "USER_ID")
	public Integer getEquipmentCharge() {
		return equipmentCharge;
	}

	public void setEquipmentCharge(Integer equipmentCharge) {
		this.equipmentCharge = equipmentCharge;
	}
	

}
