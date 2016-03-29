package com.grgbanking.core.entity.stockbean;


public class WsBorrowDetailInfoBean {
	private String borrowId;	//借用单号
	
	private String materialCode;// 物料编码
	
	private String materialName ;
	
	private int sparepartNum;// 实际数量；

	private Long storeId; // 仓库id

	private String storeName; // 仓库名称

	private String borrowName;// 借用人姓名
	
	private String serialNumber;//多个条码  逗号分隔
	
    private String hasSerial;  //是否管控条码  Y:管控  其它不管控
    
    private String status;		//借用状态（如：已借用）
    
    private String borrowTime; 		//借用时间

	/**
	 * @return the borrowId
	 */
	public String getBorrowId() {
		return borrowId;
	}

	/**
	 * @param borrowId the borrowId to set
	 */
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	/**
	 * @return the materialCode
	 */
	public String getMaterialCode() {
		return materialCode;
	}

	/**
	 * @param materialCode the materialCode to set
	 */
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	/**
	 * @return the materialName
	 */
	public String getMaterialName() {
		return materialName;
	}

	/**
	 * @param materialName the materialName to set
	 */
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	/**
	 * @return the sparepartNum
	 */
	public int getSparepartNum() {
		return sparepartNum;
	}

	/**
	 * @param sparepartNum the sparepartNum to set
	 */
	public void setSparepartNum(int sparepartNum) {
		this.sparepartNum = sparepartNum;
	}

	/**
	 * @return the storeId
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the borrowName
	 */
	public String getBorrowName() {
		return borrowName;
	}

	/**
	 * @param borrowName the borrowName to set
	 */
	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the hasSerial
	 */
	public String getHasSerial() {
		return hasSerial;
	}

	/**
	 * @param hasSerial the hasSerial to set
	 */
	public void setHasSerial(String hasSerial) {
		this.hasSerial = hasSerial;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the borrowTime
	 */
	public String getBorrowTime() {
		return borrowTime;
	}

	/**
	 * @param borrowTime the borrowTime to set
	 */
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	
}

