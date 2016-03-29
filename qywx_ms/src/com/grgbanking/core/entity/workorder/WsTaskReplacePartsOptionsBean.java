package com.grgbanking.core.entity.workorder;


//5.1.13.	零备件替换选项信息
public class WsTaskReplacePartsOptionsBean {
	
	private String oldSerialNumber;// 旧物料编码 String(N)
	private String newSerialNumber;// 新物料编码 String(N)
	private String oldSerialName;// 旧物料名称 String(N)
	private String newSerialName;// 新物料名称 String(N)
	// private String oldSerialNumber;// 旧物料编码 String(N)
	private String newSparepartId;// 新备件ID String(N)
	private String quantity;// 数量 String(N)
	private String materialCode;// 物料代号 String(N)
	private String borrowId;// 借用ID String(N)
	private String borrowDetailId;// 借用详细ID String(N)
	private String isReplace;// 是否能替换或者新增1：新增 2：替换，3：新增替换都行 String(Y)
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
	public String getIsReplace() {
		return isReplace;
	}
	public void setIsReplace(String isReplace) {
		this.isReplace = isReplace;
	}
	
	
	

}
