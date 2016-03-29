package com.grgbanking.core.entity.workorder;


//原/新模块选项信息
public class WsTaskReplaceOptionsBean {

	
	private String oldSerialNumber;// 旧物料编码 String(N)
	private String newSerialNumber;// 新物料编码 String(N)
	private String oldSerialName;// 旧物料名称 String(N)
	private String newSerialName;// 新物料名称 String(N)
	private String oldSparepartId;// 旧备件ID String(N)
	private String newSparepartId;// 新备件ID String(N)
	private String materialCode;// 物料代号 String(N)
	private String borrowId;// 借用ID String(N)
	private String borrowDetailId;// 借用详细ID String(N)
	private String oldModelType;// 旧模块类型 String(N)
	private String newModelType;// 新模块类型 String(N)
	
	
	
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
	public String getOldModelType() {
		return oldModelType;
	}
	public void setOldModelType(String oldModelType) {
		this.oldModelType = oldModelType;
	}
	public String getNewModelType() {
		return newModelType;
	}
	public void setNewModelType(String newModelType) {
		this.newModelType = newModelType;
	}
	
	
	
	
	
}
