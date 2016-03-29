package com.grgbanking.css.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.grgbanking.css.common.CssBaseEntity;

/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: ChenFei
 * Date: 2010-7-16 下午03:25:00
 */
@Entity
@Table(name="V_SPAREPART")
public class Sparepart extends CssBaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/*物料id*/
	private Integer sparepartId;
	/*物料编码*/
	private String materialCode;
	/*物料名称*/
	private String materialName;
	/*物料型号*/
	private String materialModel;
	/*制造商*/
	private String manufacturer;
	/*制造国家*/
	private String country;
	/*条码*/
	private String barCode;
	/*模块级别，分为一级模块，二级模块*/
	private String moduleLevel;
	/*物料类型*/
	private String materialType;
	/*模块类型*/
	private String moduleType;
	/*备注*/
	private String remark;
	/*单位，如“个”、“条”*/
	private String unit;
	/*所属部位*/
	private String belongPart;
	/*消耗周期*/
	private Integer consumePeriod;
	/*SAP对应主键*/
	private Integer recordId;
	/*成本价*/
	private String price;
	/*替代物料*/
	private String replaceMaterial;
	/*物料采购状态有效的起始日期*/
	private Date startDate;
	/*物料采购状态(是否能再采购)*/
	private String materialStatus;
	/*价格标志*/
	private String priceFlag;
	/*免保起始日期*/
	private Date warrantyStartDate; 
	/*备件免保期限*/
	private String materialMonth;
	/*免保结束日期*/
	private Date warrantyEndDate;
	/*历史总进货数,用于计算成本价*/
	private Integer totalQuantity;
	/*物料规格*/
	private String materialStandard;
	/*平均维修时间*/
	private Double maintainTime;
	/*物料图片保存路径*/
	private String materialPic;
	/*采购周期*/
	private Integer purchaseCycle;
	/*管控状态*/
	private String controlStatus;
	private String priceText;
	
	@Id  
	@SequenceGenerator(name = "SPAREPART_ID_SEQ_GENERATOR", sequenceName = "SEQ_SPAREPART", initialValue = 1, allocationSize = 1)   
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPAREPART_ID_SEQ_GENERATOR")   
	@Column(name="SPAREPART_ID")
	public Integer getSparepartId() {
		return sparepartId;
	}
	
	public void setSparepartId(Integer sparepartId) {
		this.sparepartId = sparepartId;
	}
	
	@Column(name = "MATERIAL_CODE")
	public String getMaterialCode() {
		return materialCode;
	}
	
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	@Column(name = "MATERIAL_NAME")
	public String getMaterialName() {
		return materialName;
	}
	
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	@Column(name = "MATERIAL_MODEL")
	public String getMaterialModel() {
		return materialModel;
	}
	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}
	
	@Column(name = "MANUFACTURER")
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name = "BAR_CODE")
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	@Column(name = "MODULE_LEVEL")
	public String getModuleLevel() {
		return moduleLevel;
	}
	public void setModuleLevel(String moduleLevel) {
		this.moduleLevel = moduleLevel;
	}
	
	@Column(name = "MATERIAL_TYPE")
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	
	@Column(name = "MODULE_TYPE")
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "UNIT")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Column(name = "BELONG_PART")
	public String getBelongPart() {
		return belongPart;
	}
	public void setBelongPart(String belongPart) {
		this.belongPart = belongPart;
	}
	
	@Column(name = "CONSUME_PERIOD")
	public Integer getConsumePeriod() {
		return consumePeriod;
	}
	public void setConsumePeriod(Integer consumePeriod) {
		this.consumePeriod = consumePeriod;
	}
	
	@Column(name = "RECORD_ID")
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	
	@Column(name = "PRICE")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Column(name = "REPLACE_MATERIAL")
	public String getReplaceMaterial() {
		return replaceMaterial;
	}
	public void setReplaceMaterial(String replaceMaterial) {
		this.replaceMaterial = replaceMaterial;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name = "MATERIAL_STATUS")
	public String getMaterialStatus() {
		return materialStatus;
	}
	public void setMaterialStatus(String materialStatus) {
		this.materialStatus = materialStatus;
	}
	
	@Column(name = "PRICE_FLAG")
	public String getPriceFlag() {
		return priceFlag;
	}
	public void setPriceFlag(String priceFlag) {
		this.priceFlag = priceFlag;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "WARRANTY_START_DATE")
	public Date getWarrantyStartDate() {
		return warrantyStartDate;
	}
	public void setWarrantyStartDate(Date warrantyStartDate) {
		this.warrantyStartDate = warrantyStartDate;
	}
	
	@Column(name = "MATERIAL_MONTH")
	public String getMaterialMonth() {
		return materialMonth;
	}
	public void setMaterialMonth(String materialMonth) {
		this.materialMonth = materialMonth;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "WARRANTY_END_DATE")
	public Date getWarrantyEndDate() {
		return warrantyEndDate;
	}
	public void setWarrantyEndDate(Date warrantyEndDate) {
		this.warrantyEndDate = warrantyEndDate;
	}
	
	@Column(name = "TOTAL_QUANTITY")
	public Integer getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
	@Column(name = "MATERIAL_STANDARD")
	public String getMaterialStandard() {
		return materialStandard;
	}
	public void setMaterialStandard(String materialStandard) {
		this.materialStandard = materialStandard;
	}
	
	@Column(name = "MAINTAIN_TIME")
	public Double getMaintainTime() {
		return maintainTime;
	}
	public void setMaintainTime(Double maintainTime) {
		this.maintainTime = maintainTime;
	}
	
	@Column(name = "MATERIAL_PIC")
	public String getMaterialPic() {
		return materialPic;
	}
	public void setMaterialPic(String materialPic) {
		this.materialPic = materialPic;
	}
	@Column(name = "PURCHASE_CYCLE")
	public Integer getPurchaseCycle() {
		return purchaseCycle;
	}

	public void setPurchaseCycle(Integer purchaseCycle) {
		this.purchaseCycle = purchaseCycle;
	}
	@Column(name = "CONTROL_STATUS")
	public String getControlStatus() {
		return controlStatus;
	}
	public void setControlStatus(String controlStatus) {
		this.controlStatus = controlStatus;
	}
	@Column(name = "PRICE_TEXT")
	public String getPriceText() {
		return priceText;
	}

	public void setPriceText(String priceText) {
		this.priceText = priceText;
	}
	
	
	
	

}
