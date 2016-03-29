package com.grgbanking.core.entity.stockbean;

/**
 * 改造后的备件借用列表实体bean 
 * @author sghong
 * @company grgbanking 
 * @date 2014-12-25 下午03:25:13
 */
public class WsAocBorrowDetailBean {
	
    private String materialCode;//物料编码

    private String materialName;//物料名称
    
    private int sparepartNum;//实际数量；
    
    private String serialNumber;//多个条码  逗号分隔
  
    private Long storeId;  //仓库id
    
    private String storeName;  //仓库名称
    
    private String borrowName;//借用人
    
    private String hasSerial;  //是否管控条码  Y:管控  其它不管控

	public String getHasSerial() {
		return hasSerial;
	}

	public void setHasSerial(String hasSerial) {
		this.hasSerial = hasSerial;
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

	public int getSparepartNum() {
		return sparepartNum;
	}

	public void setSparepartNum(int sparepartNum) {
		this.sparepartNum = sparepartNum;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}
    
}
