package com.grgbanking.core.entity.stockbean;

/**
 * 
 * date:2013.10.14
 * @author yt
 * 备件归还列表
 *
 */
public class WsAocBorrowInfoDetailBean {
	

    private Long borrowId;//borrowInfoId单个借用备件记录id
    
    private String borrowApplyCode;//借用单号

    private Long storeId;//仓库Id

    private String storeName;//仓库名称

    private String materialCode;//物料编码

  

	private String materialName;//物料名称

    private String serialNumber;//条码（无条码则为空字符串）

    private String borrowDate;//借用日期（yyyy-MM-dd HH:mm:ss）

    
    
    
	public Long getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	
	
	  public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getBorrowApplyCode() {
			return borrowApplyCode;
		}

		public void setBorrowApplyCode(String borrowApplyCode) {
			this.borrowApplyCode = borrowApplyCode;
		}

}
