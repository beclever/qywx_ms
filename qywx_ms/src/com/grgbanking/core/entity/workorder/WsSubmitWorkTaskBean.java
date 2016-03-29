package com.grgbanking.core.entity.workorder;

import java.util.List;

//提交工单任务属性
public class WsSubmitWorkTaskBean {
	
	private String workIsFinish;//	String(Y)	工作完成情况
	private String serverStatus;//	String(Y)	服务保修状态ID
	private String serverStatusName;//String(Y)	服务保修状态
	private String firstType;//	String(Y)	巡检类型第一个选项内容
	private String secondType;//	String(Y)	巡检类型第二个选项内容
	private String explain;//	String(Y)	巡检类型说明内容
	private List<WsSjTypeDetailsBean> sjTypeDetails;//	Array(Y),数组内数据结构详见5.1.20	巡检内容
	private String costTime;//	String(Y)	工作耗时
	private String workRemark;//	String(Y)	工作描述
	private String customerName;//	String(Y)	客户名称
	private String customerNameId;//	String(Y)	客户名称ID
	private String installType;//	String(Y)	安装类型
	private String installTypeId;//	String(Y)	安装类型ID
	private String installModel;//	String(Y)	安装方式
	private String installModelId;//	String(Y)	安装方式ID
	private String branchName;//	String(Y)	网点名称
	private String province;//	String(Y)	省份
	private String provinceId;//	String(Y)	省份ID
	private String city;//	String(Y)	城市
	private String cityId;//	String(Y)	城市ID
	private String installProperty;//	String(Y)	装机属性
	private String installPropertyId;//	String(Y)	装机属性ID
	private String ATMManager;//	String(Y)	ATM管理员名字
	private String ATMManagerTel;//	String(Y)	ATM管理员电话
	private String branchPrincipal;//	String(Y)	网点负责人(不用)
	private String branchPrincipalTel;//	String(Y)	网点负责人电话
	private String operationSystem;//	String(Y)	操作系统名称
	private String operationSystemId;//	String(Y)	操作系统Id
	private String osVersion;//	String(Y)	操作系统版本
	private String ATMCName;//	String(Y)	ATMC名称
	private String ATMCId;//	String(Y)	ATMC名称Id
	private String ATMCVersion;//	String(Y)	ATMC版本
	private String ATMCSpVersion;//	String(Y)	ATMC跨平台SP
	private String encryptModel;//	String(Y)	加密方式
	private String encryptModelId;//	String(Y)	加密方式ID
	private String ATMNumber;//	String(Y)	ATM号
	private String bankNumber;//	String(Y)	银行号
	private String bankTerminalNumber;//	String(Y)	支行终端号
	private String netProtocol;//	String(Y)	网络连接协议
	private String LocalIP;//	String(Y)	本机IP地址
	private String pip;//	String(Y)	P端IP地址
	private String gateway;//	String(Y)	网关
	private String subnetMask;//	String(Y)	子网掩码
	private String dailyAverageAmount;//	String(Y)	日均交易金额
	private String dailyAverageAmountId;//	String(Y)	日均交易金额ID
	private String dailyAverageNum;//	String(Y)	日均交易笔数
	private String dailyAverageNumId;//	String(Y)	日均交易笔数ID
	private String dust;//	String(Y)	灰尘
	private String dustId;//	String(Y)	灰尘ID
	private String temperature;//	String(Y)	温度
	private String temperatureId;//	String(Y)	温度ID
	private String humidity;//	String(Y)	湿度
	private String humidityId;//	String(Y)	湿度ID
	private String powerSupply;//	String(Y)	供电
	private String powerSupplyId;//	String(Y)	供电ID
	private String waterproof;//	String(Y)	防雨
	private String waterproofId;//	String(Y)	防雨ID
	private String solarization;//	String(Y)	日晒
	private String solarizationId;//	String(Y)	日晒ID
	private String installAddress;//	String(Y)	安装地址
	private String consultWay;//	String(Y)	参考路线
	private String referenceCharge;// String(Y) 参考费用
	private String equipmentChargeName;// 安装开通增加【设备负责人】

	public String getWorkIsFinish() {
		return workIsFinish;
	}

	public void setWorkIsFinish(String workIsFinish) {
		this.workIsFinish = workIsFinish;
	}

	public String getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(String serverStatus) {
		this.serverStatus = serverStatus;
	}

	public String getServerStatusName() {
		return serverStatusName;
	}

	public void setServerStatusName(String serverStatusName) {
		this.serverStatusName = serverStatusName;
	}

	public String getFirstType() {
		return firstType;
	}

	public void setFirstType(String firstType) {
		this.firstType = firstType;
	}

	public String getSecondType() {
		return secondType;
	}

	public void setSecondType(String secondType) {
		this.secondType = secondType;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	

	public List<WsSjTypeDetailsBean> getSjTypeDetails() {
		return sjTypeDetails;
	}

	public void setSjTypeDetails(List<WsSjTypeDetailsBean> sjTypeDetails) {
		this.sjTypeDetails = sjTypeDetails;
	}

	public String getCostTime() {
		return costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

	public String getWorkRemark() {
		return workRemark;
	}

	public void setWorkRemark(String workRemark) {
		this.workRemark = workRemark;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNameId() {
		return customerNameId;
	}

	public void setCustomerNameId(String customerNameId) {
		this.customerNameId = customerNameId;
	}

	public String getInstallType() {
		return installType;
	}

	public void setInstallType(String installType) {
		this.installType = installType;
	}

	public String getInstallTypeId() {
		return installTypeId;
	}

	public void setInstallTypeId(String installTypeId) {
		this.installTypeId = installTypeId;
	}

	public String getInstallModel() {
		return installModel;
	}

	public void setInstallModel(String installModel) {
		this.installModel = installModel;
	}

	public String getInstallModelId() {
		return installModelId;
	}

	public void setInstallModelId(String installModelId) {
		this.installModelId = installModelId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getInstallProperty() {
		return installProperty;
	}

	public void setInstallProperty(String installProperty) {
		this.installProperty = installProperty;
	}

	public String getInstallPropertyId() {
		return installPropertyId;
	}

	public void setInstallPropertyId(String installPropertyId) {
		this.installPropertyId = installPropertyId;
	}

	public String getATMManager() {
		return ATMManager;
	}

	public void setATMManager(String aTMManager) {
		ATMManager = aTMManager;
	}

	public String getATMManagerTel() {
		return ATMManagerTel;
	}

	public void setATMManagerTel(String aTMManagerTel) {
		ATMManagerTel = aTMManagerTel;
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

	public String getOperationSystem() {
		return operationSystem;
	}

	public void setOperationSystem(String operationSystem) {
		this.operationSystem = operationSystem;
	}

	public String getOperationSystemId() {
		return operationSystemId;
	}

	public void setOperationSystemId(String operationSystemId) {
		this.operationSystemId = operationSystemId;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getATMCName() {
		return ATMCName;
	}

	public void setATMCName(String aTMCName) {
		ATMCName = aTMCName;
	}

	public String getATMCId() {
		return ATMCId;
	}

	public void setATMCId(String aTMCId) {
		ATMCId = aTMCId;
	}

	public String getATMCVersion() {
		return ATMCVersion;
	}

	public void setATMCVersion(String aTMCVersion) {
		ATMCVersion = aTMCVersion;
	}

	public String getATMCSpVersion() {
		return ATMCSpVersion;
	}

	public void setATMCSpVersion(String aTMCSpVersion) {
		ATMCSpVersion = aTMCSpVersion;
	}

	public String getEncryptModel() {
		return encryptModel;
	}

	public void setEncryptModel(String encryptModel) {
		this.encryptModel = encryptModel;
	}

	public String getEncryptModelId() {
		return encryptModelId;
	}

	public void setEncryptModelId(String encryptModelId) {
		this.encryptModelId = encryptModelId;
	}

	public String getATMNumber() {
		return ATMNumber;
	}

	public void setATMNumber(String aTMNumber) {
		ATMNumber = aTMNumber;
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

	public String getNetProtocol() {
		return netProtocol;
	}

	public void setNetProtocol(String netProtocol) {
		this.netProtocol = netProtocol;
	}

	public String getLocalIP() {
		return LocalIP;
	}

	public void setLocalIP(String localIP) {
		LocalIP = localIP;
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

	public String getSubnetMask() {
		return subnetMask;
	}

	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}

	public String getDailyAverageAmount() {
		return dailyAverageAmount;
	}

	public void setDailyAverageAmount(String dailyAverageAmount) {
		this.dailyAverageAmount = dailyAverageAmount;
	}

	public String getDailyAverageAmountId() {
		return dailyAverageAmountId;
	}

	public void setDailyAverageAmountId(String dailyAverageAmountId) {
		this.dailyAverageAmountId = dailyAverageAmountId;
	}

	public String getDailyAverageNum() {
		return dailyAverageNum;
	}

	public void setDailyAverageNum(String dailyAverageNum) {
		this.dailyAverageNum = dailyAverageNum;
	}

	public String getDailyAverageNumId() {
		return dailyAverageNumId;
	}

	public void setDailyAverageNumId(String dailyAverageNumId) {
		this.dailyAverageNumId = dailyAverageNumId;
	}

	public String getDust() {
		return dust;
	}

	public void setDust(String dust) {
		this.dust = dust;
	}

	public String getDustId() {
		return dustId;
	}

	public void setDustId(String dustId) {
		this.dustId = dustId;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTemperatureId() {
		return temperatureId;
	}

	public void setTemperatureId(String temperatureId) {
		this.temperatureId = temperatureId;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getHumidityId() {
		return humidityId;
	}

	public void setHumidityId(String humidityId) {
		this.humidityId = humidityId;
	}

	public String getPowerSupply() {
		return powerSupply;
	}

	public void setPowerSupply(String powerSupply) {
		this.powerSupply = powerSupply;
	}

	public String getPowerSupplyId() {
		return powerSupplyId;
	}

	public void setPowerSupplyId(String powerSupplyId) {
		this.powerSupplyId = powerSupplyId;
	}

	public String getWaterproof() {
		return waterproof;
	}

	public void setWaterproof(String waterproof) {
		this.waterproof = waterproof;
	}

	public String getWaterproofId() {
		return waterproofId;
	}

	public void setWaterproofId(String waterproofId) {
		this.waterproofId = waterproofId;
	}

	public String getSolarization() {
		return solarization;
	}

	public void setSolarization(String solarization) {
		this.solarization = solarization;
	}

	public String getSolarizationId() {
		return solarizationId;
	}

	public void setSolarizationId(String solarizationId) {
		this.solarizationId = solarizationId;
	}

	public String getInstallAddress() {
		return installAddress;
	}

	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}

	public String getConsultWay() {
		return consultWay;
	}

	public void setConsultWay(String consultWay) {
		this.consultWay = consultWay;
	}

	public String getReferenceCharge() {
		return referenceCharge;
	}

	public void setReferenceCharge(String referenceCharge) {
		this.referenceCharge = referenceCharge;
	}

	public String getEquipmentChargeName() {
		return equipmentChargeName;
	}

	public void setEquipmentChargeName(String equipmentChargeName) {
		this.equipmentChargeName = equipmentChargeName;
	}


}
