package com.grgbanking.core.entity.stockbean;


/**
 * 备件申请
 * @author yt
 *
 */
public class WsAocBorrowApplyInfoParam {

	
	private Long storeId;//仓库Id
	private String materialCode;//物料编码
    private int borrowNum;//借用数量
    private String borrowRemark; //借用备注

    public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public int getBorrowNum() {
		return borrowNum;
	}

	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}

	public String getBorrowRemark() {
		return borrowRemark;
	}

	public void setBorrowRemark(String borrowRemark) {
		this.borrowRemark = borrowRemark;
	}
	
//	 public String toString(){
//	        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
//	    }
//	
    
    
	
}
