package com.grgbanking.core.entity.stockbean;


/**
 * 新模块，新零备件
 * @author yt
 *
 */

public class WsAocBorrowInfoBean {
	 private String materialId;//物料编码ID
		
	    private String materialCode;//物料编码

	    private String materialName;//物料名称

	    private String moduleLevel;//模块级别
	    
	    private String materialType;//物料类型

	    private String moduleType;//模块类型
	    
	    private int sparepartNum;//实际数量；
	    
	    private String serialNumber;//条码
	    
	    private String softwareVersion;//软件版本

	    private String harewareVersion;//硬件版本

		public String getMaterialId() {
			return materialId;
		}

		public void setMaterialId(String materialId) {
			this.materialId = materialId;
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

		public String getModuleType() {
			return moduleType;
		}

		public void setModuleType(String moduleType) {
			this.moduleType = moduleType;
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

		public String getSoftwareVersion() {
			return softwareVersion;
		}

		public void setSoftwareVersion(String softwareVersion) {
			this.softwareVersion = softwareVersion;
		}

		public String getHarewareVersion() {
			return harewareVersion;
		}

		public void setHarewareVersion(String harewareVersion) {
			this.harewareVersion = harewareVersion;
		}
	    
	    
}
