package com.grgbanking.core.entity.stockbean;

public class WsAocStockInfo {

	private String storeId;//仓库id
	
	private String storeName; //仓库名称

	private String materialCode;//物料编码

    private String materialName;//物料名称

    private String moduleLevel;//模块级别

    private String materialType;//模块类型

    private String hasSerialNumber;//是否管控条码：Y-管控；N-不管控

    private int stockNum;//库存数量

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
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

	public String getModuleLevel() {
		return moduleLevel;
	}

	public void setModuleLevel(String moduleLevel) {
		this.moduleLevel = moduleLevel;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getHasSerialNumber() {
		return hasSerialNumber;
	}

	public void setHasSerialNumber(String hasSerialNumber) {
		this.hasSerialNumber = hasSerialNumber;
	}

	public int getStockNum() {
		return stockNum;
	}

	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}
	
}
