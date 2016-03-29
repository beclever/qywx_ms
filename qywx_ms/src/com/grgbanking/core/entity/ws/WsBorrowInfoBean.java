/**
 * 
 */
package com.grgbanking.core.entity.ws;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Copyright 2012 by Grgbanking
 * All Rights Reserved.
 * Author: zjwei2 
 * Date: 2013-4-23 上午10:15:52
 */
public class WsBorrowInfoBean {
	
	private String materialId; //物料ID
	
	private String moduleType;//模块类型
	
	private String materialCode; //物料编码
	
	private String materialName;//物料名称
	
	private String serialNumber; //条码（无条码则为空字符串）
	
	private int sparepartNum; //管控条码的物料为：1；不管控条码的为实际数量；
	
	private String moduleLevel;//模块级别
	
	private String materialType; //物料类型
	
	private String softwareVersion; //软件版本
	
	private String harewareVersion; //硬件版本

	/**
	 * @return the materialId
	 */
	public String getMaterialId() {
		return materialId;
	}

	/**
	 * @param materialId the materialId to set
	 */
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
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
	 * @return the moduleLevel
	 */
	public String getModuleLevel() {
		return moduleLevel;
	}

	/**
	 * @param moduleLevel the moduleLevel to set
	 */
	public void setModuleLevel(String moduleLevel) {
		this.moduleLevel = moduleLevel;
	}

	/**
	 * @return the materialType
	 */
	public String getMaterialType() {
		return materialType;
	}

	/**
	 * @param materialType the materialType to set
	 */
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	/**
	 * @return the softwareVersion
	 */
	public String getSoftwareVersion() {
		return softwareVersion;
	}

	/**
	 * @param softwareVersion the softwareVersion to set
	 */
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	/**
	 * @return the harewareVersion
	 */
	public String getHarewareVersion() {
		return harewareVersion;
	}

	/**
	 * @param harewareVersion the harewareVersion to set
	 */
	public void setHarewareVersion(String harewareVersion) {
		this.harewareVersion = harewareVersion;
	}
	
	

    public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
	
}
