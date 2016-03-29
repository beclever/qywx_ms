package com.grgbanking.css.bean.equipment;

import java.util.Date;

/**
 * 设备基本信息封装bean
 * 
 * @author zhangzhi 2009-6-12
 */
public class EquipmentInstallBean implements java.io.Serializable {

	private String branchName;
	private Date installDate;
	private Date moveDate;
	private String installType;// 安装方式
	private String installModel;
	private Integer provinceId;// 安装省份
	private Integer cityId;// 安装城市
	private String provinceName;
	private String cityName;
	private String installAddress;// 安装地址
	private String atmManager;// ATM管理员
	private String branchPrincipal;// 网点负责人
	private String atmManagerTel;
	private String branchPrincipalTel;// 网点负责人电话
	private String atmNumber;// ATM号
	private String encryptType;// 加密方式
	private String bankNumber;// 银行号
	private String bankTerminalNumber;// 支行终端号
	private String atmcName;// ATMC名称
	private String atmcVersion;// ATMC版本
	private String atmcSpVersion;// SP版本(跨平台)
	private String localIp;// 本机IP地址
	private String pip;// P端IP地址
	private String gateway;// 网关
	private String subnetHideAddress;// 子网掩码
	private String netProtocol;// 网络连接协议
	private String dailyDealCount;// 日均交易笔数
	private String dailyDealMoney;// 日均交易金额
	private String acceptReportStatus;// 验收报告状态
	private Date lastMaintainDate;// 最后保养日期
	private String trafficLine;// 乘车路线
	private Double referenceCharge;// 参考路费
	private String environmentDust;// 灰尘
	private String environmentHumidity;// 湿度
	private String environmentTemperature;// 温度
	private String environmentPowerSupply;// 供电
	private String environmentRainDefence;// 防雨
	private String environmentSunshine;// 日晒
	private Integer customerId;// 客户ID
	private String customerName;
	private String operateSystem;// 操作系统
	private String osVersion;// 操作系统版本
	private String installProperty;// 装机属性
	private Integer equipmentCharge;// 设备负责人

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public Date getMoveDate() {
		return moveDate;
	}

	public void setMoveDate(Date moveDate) {
		this.moveDate = moveDate;
	}

	public String getInstallType() {
		return installType;
	}

	public void setInstallType(String installType) {
		this.installType = installType;
	}

	public String getInstallModel() {
		return installModel;
	}

	public void setInstallModel(String installModel) {
		this.installModel = installModel;
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

	public String getBranchPrincipal() {
		return branchPrincipal;
	}

	public void setBranchPrincipal(String branchPrincipal) {
		this.branchPrincipal = branchPrincipal;
	}

	public String getAtmManagerTel() {
		return atmManagerTel;
	}

	public void setAtmManagerTel(String atmManagerTel) {
		this.atmManagerTel = atmManagerTel;
	}

	public String getBranchPrincipalTel() {
		return branchPrincipalTel;
	}

	public void setBranchPrincipalTel(String branchPrincipalTel) {
		this.branchPrincipalTel = branchPrincipalTel;
	}

	public String getAtmNumber() {
		return atmNumber;
	}

	public void setAtmNumber(String atmNumber) {
		this.atmNumber = atmNumber;
	}

	public String getEncryptType() {
		return encryptType;
	}

	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getBankTerminalNumber() {
		return bankTerminalNumber;
	}

	public void setBankTerminalNumber(String bankTerminalNumber) {
		this.bankTerminalNumber = bankTerminalNumber;
	}

	public String getAtmcName() {
		return atmcName;
	}

	public void setAtmcName(String atmcName) {
		this.atmcName = atmcName;
	}

	public String getLocalIp() {
		return localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	public String getPip() {
		return pip;
	}

	public void setPip(String pip) {
		this.pip = pip;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getSubnetHideAddress() {
		return subnetHideAddress;
	}

	public void setSubnetHideAddress(String subnetHideAddress) {
		this.subnetHideAddress = subnetHideAddress;
	}

	public String getNetProtocol() {
		return netProtocol;
	}

	public void setNetProtocol(String netProtocol) {
		this.netProtocol = netProtocol;
	}

	public String getDailyDealCount() {
		return dailyDealCount;
	}

	public void setDailyDealCount(String dailyDealCount) {
		this.dailyDealCount = dailyDealCount;
	}

	public String getDailyDealMoney() {
		return dailyDealMoney;
	}

	public void setDailyDealMoney(String dailyDealMoney) {
		this.dailyDealMoney = dailyDealMoney;
	}

	public String getAcceptReportStatus() {
		return acceptReportStatus;
	}

	public void setAcceptReportStatus(String acceptReportStatus) {
		this.acceptReportStatus = acceptReportStatus;
	}

	public Date getLastMaintainDate() {
		return lastMaintainDate;
	}

	public void setLastMaintainDate(Date lastMaintainDate) {
		this.lastMaintainDate = lastMaintainDate;
	}

	public String getTrafficLine() {
		return trafficLine;
	}

	public void setTrafficLine(String trafficLine) {
		this.trafficLine = trafficLine;
	}

	public Double getReferenceCharge() {
		return referenceCharge;
	}

	public void setReferenceCharge(Double referenceCharge) {
		this.referenceCharge = referenceCharge;
	}

	public String getEnvironmentDust() {
		return environmentDust;
	}

	public void setEnvironmentDust(String environmentDust) {
		this.environmentDust = environmentDust;
	}

	public String getEnvironmentHumidity() {
		return environmentHumidity;
	}

	public void setEnvironmentHumidity(String environmentHumidity) {
		this.environmentHumidity = environmentHumidity;
	}

	public String getEnvironmentTemperature() {
		return environmentTemperature;
	}

	public void setEnvironmentTemperature(String environmentTemperature) {
		this.environmentTemperature = environmentTemperature;
	}

	public String getEnvironmentPowerSupply() {
		return environmentPowerSupply;
	}

	public void setEnvironmentPowerSupply(String environmentPowerSupply) {
		this.environmentPowerSupply = environmentPowerSupply;
	}

	public String getEnvironmentRainDefence() {
		return environmentRainDefence;
	}

	public void setEnvironmentRainDefence(String environmentRainDefence) {
		this.environmentRainDefence = environmentRainDefence;
	}

	public String getEnvironmentSunshine() {
		return environmentSunshine;
	}

	public void setEnvironmentSunshine(String environmentSunshine) {
		this.environmentSunshine = environmentSunshine;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOperateSystem() {
		return operateSystem;
	}

	public void setOperateSystem(String operateSystem) {
		this.operateSystem = operateSystem;
	}

	public String getAtmcVersion() {
		return atmcVersion;
	}

	public void setAtmcVersion(String atmcVersion) {
		this.atmcVersion = atmcVersion;
	}

	public String getAtmcSpVersion() {
		return atmcSpVersion;
	}

	public void setAtmcSpVersion(String atmcSpVersion) {
		this.atmcSpVersion = atmcSpVersion;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	/**
	 * @return the installProperty
	 */
	public String getInstallProperty() {
		return installProperty;
	}

	/**
	 * @param installProperty
	 *            the installProperty to set
	 */
	public void setInstallProperty(String installProperty) {
		this.installProperty = installProperty;
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

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getEquipmentCharge() {
		return equipmentCharge;
	}

	public void setEquipmentCharge(Integer equipmentCharge) {
		this.equipmentCharge = equipmentCharge;
	}

}