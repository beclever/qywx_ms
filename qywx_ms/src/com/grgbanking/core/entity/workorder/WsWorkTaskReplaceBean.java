package com.grgbanking.core.entity.workorder;
//5.1.21.	工单任务-主要模块替换/零备件替换
public class WsWorkTaskReplaceBean {

	private String oldSerialName;// 旧物料名称 String(N)
	private String newSerialName;// 新物料名称 String(N)
	private String oldMaterialCode;// 旧物料编码 String(N)
	private String newMaterialCode;// 新物料编码 String(N)
	private String oldSerialNumber;// 旧条码编码 String(N)
	private String newSerialNumber;// 新条码编码 String(N)
	private String oldSparepartId;// 旧备件ID String(N)
	private String newSparepartId;// 新备件ID String(N)
	private String quantity;// 数量 String(N)
	private String oldHardwareVersion;// 旧硬件版本 String(N)
	private String oldSoftwareVersion;// 旧软件版本 String(N)
	private String newHardwareVersion;// 新软件版本 String(N)
	private String newSoftwareVersion;// 新硬件版本 String(N)
	private String useMethod;// 使用方法 String(N)
	private String materialCode;// 物料代号 String(N)
	private String borrowId;// 借用ID String(N)
	private String borrowDetailId;// 借用详细ID String(N)
	private String equipmentConfigId;// 设备备件ID String(Y)
	private Boolean isModify;// 条码编码是否可修改 Boolean(Y)

	public String getOldSerialName() {
		return oldSerialName;
	}

	public void setOldSerialName(String oldSerialName) {
		this.oldSerialName = oldSerialName;
	}

	public String getNewSerialName() {
		return newSerialName;
	}

	public void setNewSerialName(String newSerialName) {
		this.newSerialName = newSerialName;
	}

	public String getOldMaterialCode() {
		return oldMaterialCode;
	}

	public void setOldMaterialCode(String oldMaterialCode) {
		this.oldMaterialCode = oldMaterialCode;
	}

	public String getNewMaterialCode() {
		return newMaterialCode;
	}

	public void setNewMaterialCode(String newMaterialCode) {
		this.newMaterialCode = newMaterialCode;
	}

	public String getOldSerialNumber() {
		return oldSerialNumber;
	}

	public void setOldSerialNumber(String oldSerialNumber) {
		this.oldSerialNumber = oldSerialNumber;
	}

	public String getNewSerialNumber() {
		return newSerialNumber;
	}

	public void setNewSerialNumber(String newSerialNumber) {
		this.newSerialNumber = newSerialNumber;
	}

	public String getOldSparepartId() {
		return oldSparepartId;
	}

	public void setOldSparepartId(String oldSparepartId) {
		this.oldSparepartId = oldSparepartId;
	}

	public String getNewSparepartId() {
		return newSparepartId;
	}

	public void setNewSparepartId(String newSparepartId) {
		this.newSparepartId = newSparepartId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getOldHardwareVersion() {
		return oldHardwareVersion;
	}

	public void setOldHardwareVersion(String oldHardwareVersion) {
		this.oldHardwareVersion = oldHardwareVersion;
	}

	public String getOldSoftwareVersion() {
		return oldSoftwareVersion;
	}

	public void setOldSoftwareVersion(String oldSoftwareVersion) {
		this.oldSoftwareVersion = oldSoftwareVersion;
	}

	public String getNewHardwareVersion() {
		return newHardwareVersion;
	}

	public void setNewHardwareVersion(String newHardwareVersion) {
		this.newHardwareVersion = newHardwareVersion;
	}

	public String getNewSoftwareVersion() {
		return newSoftwareVersion;
	}

	public void setNewSoftwareVersion(String newSoftwareVersion) {
		this.newSoftwareVersion = newSoftwareVersion;
	}

	public String getUseMethod() {
		return useMethod;
	}

	public void setUseMethod(String useMethod) {
		this.useMethod = useMethod;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public String getBorrowDetailId() {
		return borrowDetailId;
	}

	public void setBorrowDetailId(String borrowDetailId) {
		this.borrowDetailId = borrowDetailId;
	}

	public String getEquipmentConfigId() {
		return equipmentConfigId;
	}

	public void setEquipmentConfigId(String equipmentConfigId) {
		this.equipmentConfigId = equipmentConfigId;
	}

	public Boolean getIsModify() {
		return isModify;
	}

	public void setIsModify(Boolean isModify) {
		this.isModify = isModify;
	}

}
