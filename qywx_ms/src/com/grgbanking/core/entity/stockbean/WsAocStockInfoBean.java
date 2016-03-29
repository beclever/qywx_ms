package com.grgbanking.core.entity.stockbean;

public class WsAocStockInfoBean {

	private Long storeId;//仓库id
	
	private String storeName; //仓库名称

	private String materialCode;//物料编码

    private String materialName;//物料名称

/*    private String locationType;//仓件位
    private String serialNumber;//条码
    private String harewareVersion;//硬件版本
	private String softwareVersion;//软件版本
	private String createDate;//入库时间
	private String usedDate;//使用时间
	private String usedCode;//使用时间
    private int days;	//库长时间
*/    
    private String moduleLevel;//模块级别

    private String materialType;//模块类型

    private String hasSerialNumber;//是否管控条码：Y-管控；N-不管控

    private int stockNum;//库存数量

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
