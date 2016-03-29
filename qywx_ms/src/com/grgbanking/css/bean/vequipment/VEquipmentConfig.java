package com.grgbanking.css.bean.vequipment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.grgbanking.css.bean.Sparepart;
import com.grgbanking.css.common.CssBaseEntity;

/**
 * 整机当前配置信息
 * @author yzhua
 *
 */
@Entity
@Table(name = "v_ems_equipment_config")
public class VEquipmentConfig extends CssBaseEntity {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -1582103169471392393L;
	
	private Integer equipmentConfigId;
	private Sparepart sparepart; //关联物料
	private String serialNumber;//条码编码
	private Date warrantyStartDate; //保修开始日期
	private Date warrantyEndDate; //保修结束日期
	private Date madeDate; //制造日期
	private VEquipmentConfig parent;//对于二级模块，它有上一级模块。
	
	private VEquipmentInfo equipment; //与此模块关联的整机
	private String hardwareVersion; //硬件版本
	private String softwareVersion; //软件版本
	private String factorySerialNumber;//厂商序列号
	private Long recordId;//SAP对应主键
	
	private List<VEquipmentConfig> childrenList=new ArrayList<VEquipmentConfig>(); //子模块
	private Integer materialMonth;
	
	@Id
	@Column(name = "EQUIPMENT_CONFIG_ID")
	public Integer getEquipmentConfigId() {
		return equipmentConfigId;
	}
	public void setEquipmentConfigId(Integer equipmentConfigId) {
		this.equipmentConfigId = equipmentConfigId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPAREPART_ID")
	public Sparepart getSparepart() {
		return sparepart;
	}
	public void setSparepart(Sparepart sparepart) {
		this.sparepart = sparepart;
	}
	@Column(name = "SERIAL_NUMBER")
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	@Column(name = "WARRANTY_START_DATE")
	public Date getWarrantyStartDate() {
		return warrantyStartDate;
	}
	public void setWarrantyStartDate(Date warrantyStartDate) {
		this.warrantyStartDate = warrantyStartDate;
	}
	@Column(name = "WARRANTY_END_DATE")
	public Date getWarrantyEndDate() {
		return warrantyEndDate;
	}
	public void setWarrantyEndDate(Date warrantyEndDate) {
		this.warrantyEndDate = warrantyEndDate;
	}
	@Column(name = "MADE_DATE")
	public Date getMadeDate() {
		return madeDate;
	}
	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public VEquipmentConfig getParent() {
		return parent;
	}
	public void setParent(VEquipmentConfig parent) {
		this.parent = parent;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "EQUIPMENT_ID")
	public VEquipmentInfo getEquipment() {
		return equipment;
	}
	public void setEquipment(VEquipmentInfo equipment) {
		this.equipment = equipment;
	}
	@Column(name = "HARDWARE_VERSION")
	public String getHardwareVersion() {
		return hardwareVersion;
	}
	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}
	@Column(name = "SOFTWARE_VERSION")
	public String getSoftwareVersion() {
		return softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	@Column(name = "FACTORY_SERIAL_NUMBER")
	public String getFactorySerialNumber() {
		return factorySerialNumber;
	}
	public void setFactorySerialNumber(String factorySerialNumber) {
		this.factorySerialNumber = factorySerialNumber;
	}
	@Column(name = "RECORD_ID")
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.ALL,org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "PARENT_ID")
	public List<VEquipmentConfig> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(List<VEquipmentConfig> childrenList) {
		this.childrenList = childrenList;
	}
	@Column(name = "MATERIAL_MONTH")
	public Integer getMaterialMonth() {
		return materialMonth;
	}
	public void setMaterialMonth(Integer materialMonth) {
		this.materialMonth = materialMonth;
	}
	
	
	

}
