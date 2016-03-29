package com.grgbanking.css.bean.equipment;

import java.util.Date;

import com.grgbanking.css.common.CssBaseEntity;

/**
 * 整机当前配置信息
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-7-23 上午08:43:02
 */
public class EquipmentConfigBean extends CssBaseEntity  implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer equipmentConfigId;
	private Integer sparepartId;//关联物料ID
	private String materialName;//物料名称
	private String materialSerial;   //物料编码
	private String serialNumber;//条码编码
	private String moduleType;   //模块类型
	private String materialModel;   //模块型号
	private String moduleLevel;    //模块等级
	private String materialStandard;  //规格
	private Date warrantyStartDate; //保修开始日期
	private Date warrantyEndDate; //保修结束日期
	private Date madeDate; //制造日期
	private Integer equipmentId; //与此模块关联的整机id
	private String equipmentSerialNumber;  //与此模块关联的整机序列号
	private String equipmentStatus;     //设备状态
	private String hardwareVersion; //硬件版本
	private String softwareVersion; //软件版本
	private Integer consumePeriod;   //消耗周期
	private String factorySerialNumber;//厂商序列号
	private String factoryCountry;  //制造国家
	private String factoryName;   //制造商
	private Integer customerId;   //客户ID
	private String customerIds;
	private Integer regionDeptId;   //服务站ID
	private String regionDeptIds;
	private Integer province;  //省份
	private Integer city;    //城市
	private String materialCode;//制造商
	private String manufacturer;//物料编码
	private String departmentName;//服务站
	private Integer departmentId;//
	private String BeginInstallDate;//整机安装日期
	private String EndInstallDate;//整机安装日期
	
	public Integer getEquipmentConfigId() {
		return equipmentConfigId;
	}
	public void setEquipmentConfigId(Integer equipmentConfigId) {
		this.equipmentConfigId = equipmentConfigId;
	}
	public Integer getSparepartId() {
		return sparepartId;
	}
	public void setSparepartId(Integer sparepartId) {
		this.sparepartId = sparepartId;
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
	public Date getWarrantyStartDate() {
		return warrantyStartDate;
	}
	public void setWarrantyStartDate(Date warrantyStartDate) {
		this.warrantyStartDate = warrantyStartDate;
	}
	public Date getWarrantyEndDate() {
		return warrantyEndDate;
	}
	public void setWarrantyEndDate(Date warrantyEndDate) {
		this.warrantyEndDate = warrantyEndDate;
	}
	public Date getMadeDate() {
		return madeDate;
	}
	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getHardwareVersion() {
		return hardwareVersion;
	}
	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	public String getFactorySerialNumber() {
		return factorySerialNumber;
	}
	public void setFactorySerialNumber(String factorySerialNumber) {
		this.factorySerialNumber = factorySerialNumber;
	}
	public String getEquipmentSerialNumber() {
		return equipmentSerialNumber;
	}
	public void setEquipmentSerialNumber(String equipmentSerialNumber) {
		this.equipmentSerialNumber = equipmentSerialNumber;
	}
	public String getMaterialSerial() {
		return materialSerial;
	}
	public void setMaterialSerial(String materialSerial) {
		this.materialSerial = materialSerial;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getEquipmentStatus() {
		return equipmentStatus;
	}
	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getMaterialModel() {
		return materialModel;
	}
	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}
	public String getModuleLevel() {
		return moduleLevel;
	}
	public void setModuleLevel(String moduleLevel) {
		this.moduleLevel = moduleLevel;
	}
	public String getMaterialStandard() {
		return materialStandard;
	}
	public void setMaterialStandard(String materialStandard) {
		this.materialStandard = materialStandard;
	}
	public Integer getConsumePeriod() {
		return consumePeriod;
	}
	public void setConsumePeriod(Integer consumePeriod) {
		this.consumePeriod = consumePeriod;
	}
	public String getFactoryCountry() {
		return factoryCountry;
	}
	public void setFactoryCountry(String factoryCountry) {
		this.factoryCountry = factoryCountry;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getRegionDeptId() {
		return regionDeptId;
	}
	public void setRegionDeptId(Integer regionDeptId) {
		this.regionDeptId = regionDeptId;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getCustomerIds() {
		return customerIds;
	}
	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}
	public String getRegionDeptIds() {
		return regionDeptIds;
	}
	public void setRegionDeptIds(String regionDeptIds) {
		this.regionDeptIds = regionDeptIds;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public void setBeginInstallDate(String beginInstallDate) {
		BeginInstallDate = beginInstallDate;
	}
	public String getBeginInstallDate() {
		return BeginInstallDate;
	}
	public void setEndInstallDate(String endInstallDate) {
		EndInstallDate = endInstallDate;
	}
	public String getEndInstallDate() {
		return EndInstallDate;
	}

}