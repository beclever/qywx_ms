package com.grgbanking.core.entity.workorder;



//原模块
public class WsWorkFormReplaceBean {

	
	
	private String borrowId;// 借用ID String(N)
	private String sparepartId;// 新备件ID String(N)
	private String serialNumber;// 物料条码 String(N)
	private String materialCode;// 物料编号 String(N)
	private String materialName;// 物料名称 String(N)
	private String hardwareVersion;// 旧硬件版本 String(N)
	private String softwareVersion;// 旧软件版本 String(N)
	private String equipmentConfigId;// 设备备件ID String(Y)
	private String moduleType;//模块类型
	
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getSparepartId() {
		return sparepartId;
	}
	public void setSparepartId(String sparepartId) {
		this.sparepartId = sparepartId;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getHardwareVersion() {
		return hardwareVersion;
	}
	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	public String getEquipmentConfigId() {
		return equipmentConfigId;
	}
	public void setEquipmentConfigId(String equipmentConfigId) {
		this.equipmentConfigId = equipmentConfigId;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	
	

}
