package com.grgbanking.core.entity.equipment;



/**
 * date :2013.10.10
 * 设备列表信息
 * @author yt
 *
 */
public class WsEquipmentInfoBean {

	private String serialNumber;// 设备序列号 String(Y)
	private String equipmentType;// 设备类型 String(Y)
	private String equipmentId;// 设备ID String(Y)
	private String equipmentModel;// 设备型号 String(Y)
	private String equipmentStatus;// 设备状态 String(Y)
	private String warrantyStatus;// 维保状态 String(Y)
	private String installDate;// 安装日期 String(Y)
	private String installAddress;// 安装地址 String(Y)
	private String departmentName;// 服务站名称 String(Y)
	private String customsName;// 客户名称 String(Y)
	private String branchName;// 网点名称 String(Y)
	private String atmNumber;//ATM号
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
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
	public String getEquipmentStatus() {
		return equipmentStatus;
	}
	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	public String getWarrantyStatus() {
		return warrantyStatus;
	}
	public void setWarrantyStatus(String warrantyStatus) {
		this.warrantyStatus = warrantyStatus;
	}
	public String getInstallDate() {
		return installDate;
	}
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
	public String getInstallAddress() {
		return installAddress;
	}
	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	public String getAtmNumber() {
		return atmNumber;
	}
	public void setAtmNumber(String atmNumber) {
		this.atmNumber = atmNumber;
	}
}
