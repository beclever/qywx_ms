package com.grgbanking.core.entity.workorder;

import java.util.List;





//任务下模块所属选项信息
public class WsModuleWorkTaskBean {
	
private String taskId;//	任务Id	String(Y)
	
	private WsOptionsBean serverStatus;//	服务报修状态	数据结构(Y),详见5.1.21

	private WsOptionsBean intallType;//	安装类型	数据结构(Y),详见5.1.21

	private WsSjTypeOptionsBean sjType;//	巡检类型	数据结构（N），数据结构详见5.1.14

	private List<WsSjTypeDetailsBean> sjTypeDetails;//	巡检内容	Array(Y),数组内数据结构详见5.1.20

	private WsOptionsBean installModel;//	安装方式	数据结构(Y),详见5.1.21

	private WsOptionsBean installProperty;//	装机属性	数据结构(Y),详见5.1.21

	private WsOptionsBean operationSystem;//	操作系统	数据结构(Y),详见5.1.21

	private WsOptionsBean ATMCName;//	ATMC名称	数据结构(Y),详见5.1.21

	private WsOptionsBean encryptMode;//	加密方式	数据结构(Y),详见5.1.21

	private WsOptionsBean dayAerageSum;//	日均交易额	数据结构(Y),详见5.1.21

	private WsOptionsBean dayAerageTime;//	日均交易笔数	数据结构(Y),详见5.1.21

	private WsOptionsBean environment;// 环境参数 数据结构(Y),详见5.1.21
	
	private WsOptionsBean environmentDust;// 使用环境（灰尘） 数据结构(Y),详见5.1.21
	
	private WsOptionsBean environmentHumidity;// 使用环境（湿度）数据结构(Y),详见5.1.21
	
	private WsOptionsBean environmentTemperature;// 使用环境（温度） 数据结构(Y),详见5.1.21
	
	private WsOptionsBean environmentPowerSupply;// 使用环境（供电） 数据结构(Y),详见5.1.21
	
	private WsOptionsBean environmentRainDefence;// 使用环境（防雨） 数据结构(Y),详见5.1.21
	
	private WsOptionsBean environmentSunshine;// 使用环境（日晒） 数据结构(Y),详见5.1.21
	
	
    
	 
	 




	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public WsOptionsBean getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(WsOptionsBean serverStatus) {
		this.serverStatus = serverStatus;
	}

	public WsOptionsBean getIntallType() {
		return intallType;
	}

	public void setIntallType(WsOptionsBean intallType) {
		this.intallType = intallType;
	}

	public WsSjTypeOptionsBean getSjType() {
		return sjType;
	}

	public void setSjType(WsSjTypeOptionsBean sjType) {
		this.sjType = sjType;
	}

	public List<WsSjTypeDetailsBean> getSjTypeDetails() {
		return sjTypeDetails;
	}

	public void setSjTypeDetails(List<WsSjTypeDetailsBean> sjTypeDetails) {
		this.sjTypeDetails = sjTypeDetails;
	}

	public WsOptionsBean getInstallModel() {
		return installModel;
	}

	public void setInstallModel(WsOptionsBean installModel) {
		this.installModel = installModel;
	}

	public WsOptionsBean getInstallProperty() {
		return installProperty;
	}

	public void setInstallProperty(WsOptionsBean installProperty) {
		this.installProperty = installProperty;
	}

	public WsOptionsBean getOperationSystem() {
		return operationSystem;
	}

	public void setOperationSystem(WsOptionsBean operationSystem) {
		this.operationSystem = operationSystem;
	}

	public WsOptionsBean getATMCName() {
		return ATMCName;
	}

	public void setATMCName(WsOptionsBean aTMCName) {
		ATMCName = aTMCName;
	}

	public WsOptionsBean getEncryptMode() {
		return encryptMode;
	}

	public void setEncryptMode(WsOptionsBean encryptMode) {
		this.encryptMode = encryptMode;
	}

	public WsOptionsBean getDayAerageSum() {
		return dayAerageSum;
	}

	public void setDayAerageSum(WsOptionsBean dayAerageSum) {
		this.dayAerageSum = dayAerageSum;
	}

	public WsOptionsBean getDayAerageTime() {
		return dayAerageTime;
	}

	public void setDayAerageTime(WsOptionsBean dayAerageTime) {
		this.dayAerageTime = dayAerageTime;
	}

	public WsOptionsBean getEnvironment() {
		return environment;
	}

	public void setEnvironment(WsOptionsBean environment) {
		this.environment = environment;
	}

	public WsOptionsBean getEnvironmentDust() {
		return environmentDust;
	}

	public void setEnvironmentDust(WsOptionsBean environmentDust) {
		this.environmentDust = environmentDust;
	}

	public WsOptionsBean getEnvironmentHumidity() {
		return environmentHumidity;
	}

	public void setEnvironmentHumidity(WsOptionsBean environmentHumidity) {
		this.environmentHumidity = environmentHumidity;
	}

	public WsOptionsBean getEnvironmentTemperature() {
		return environmentTemperature;
	}

	public void setEnvironmentTemperature(WsOptionsBean environmentTemperature) {
		this.environmentTemperature = environmentTemperature;
	}

	public WsOptionsBean getEnvironmentPowerSupply() {
		return environmentPowerSupply;
	}

	public void setEnvironmentPowerSupply(WsOptionsBean environmentPowerSupply) {
		this.environmentPowerSupply = environmentPowerSupply;
	}

	public WsOptionsBean getEnvironmentRainDefence() {
		return environmentRainDefence;
	}

	public void setEnvironmentRainDefence(WsOptionsBean environmentRainDefence) {
		this.environmentRainDefence = environmentRainDefence;
	}

	public WsOptionsBean getEnvironmentSunshine() {
		return environmentSunshine;
	}

	public void setEnvironmentSunshine(WsOptionsBean environmentSunshine) {
		this.environmentSunshine = environmentSunshine;
	}
	
	
	

}
