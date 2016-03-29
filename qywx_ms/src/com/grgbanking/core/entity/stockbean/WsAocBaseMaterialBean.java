package com.grgbanking.core.entity.stockbean;




/**
 * 零件（原物料）
 * @author yt
 *
 */
public class WsAocBaseMaterialBean {

	
	 private String materialCode;//物料编码

	    private String materialName;//物料名称

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
}
