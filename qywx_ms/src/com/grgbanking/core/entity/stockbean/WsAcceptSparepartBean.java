package com.grgbanking.core.entity.stockbean;

public class WsAcceptSparepartBean {

//  private Long borrowId;//borrowInfoId单个借用备件记录id

  private Long storeId;
  private String storeName;//仓库名称
  private String materialCode;//物料编码
  private String materialName;//物料名称
  private int sparepartNum;//实际数量；
  private String serialNumber;  //条码  英文逗号分隔
  private String dockingName;  //交接人 
 // private String dockingNo;  //交接人员工号
  private String hasSerial;  //是否管控条码  Y:管控  其它不管控
  
	
  

  
//	public String getDockingNo() {
//		return dockingNo;
//	}
//
//	public void setDockingNo(String dockingNo) {
//		this.dockingNo = dockingNo;
//	}

	public String getHasSerial() {
		return hasSerial;
	}

	public void setHasSerial(String hasSerial) {
		this.hasSerial = hasSerial;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public int getSparepartNum() {
		return sparepartNum;
	}

	public void setSparepartNum(int sparepartNum) {
		this.sparepartNum = sparepartNum;
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


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	public String getDockingName() {
		return dockingName;
	}

	public void setDockingName(String dockingName) {
		this.dockingName = dockingName;
	}

}
