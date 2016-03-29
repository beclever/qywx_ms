package com.grgbanking.core.entity.stockbean;

import java.util.Date;

/**
 * Copyright (c) 2013 GRG Banking Equipment Co.,Ltd.
 *
 * @createDate: 2013-12-31
 * @author: lwen
 * @description 库存明细查询  
 */
public class WsAocSparepartInfoBean {
	
//	private Long id;
	
//	private Long materialId;// 物料ID
	
	private Long storeId;// 仓库ID
	
	private String storeName;// 仓库NAME
	
	private String materialCode;// 物料编号
	
	private String locationType;// 仓位
	
	private String serialNumber; //条码
	
	private String status;
	
	private String softwareVersion;
	
	private String harewareVersion;

	private Date createDate;
	
	private Date usedDate;//被使用时间，含被使用和被报废2013-4-7
	
	private String usedCode;//被使用业务编号或工单号2013-4-7
	
	private Integer days;//在库时长

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

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	public String getUsedCode() {
		return usedCode;
	}

	public void setUsedCode(String usedCode) {
		this.usedCode = usedCode;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
    
}
