package com.grgbanking.css.bean.equipment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 设备查询实体
 * @author Administrator
 *
 */
@Entity
public class EquipmentSearchPape {
	
	@Id
	@Column(name="EQUIPMENT_ID")
	private Integer equipmentId;
	@Column(name = "EQUIPMENT_MODEL")
	private String equipmentModel;//设备型号
	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;//序列号
	@Column(name="DEPARTMENT_ID")
	private Integer departmentId;
	@Column(name="CUSTOMER_ID")
	private Integer customerId;
	@Column(name = "BRANCH_NAME")
	private String branchName;//网点名称
	@Column(name = "INSTALL_DATE")
	private Date installDate;//安装日期
	@Column(name = "EQUIPMENT_STATUS")
	private String equipmentStatus;//设备状态
	@Column(name = "WARRANTY_STATUS")
	private String warrantyStatus;//保修类型
	@Column(name = "ACCEPT_REPORT_STATUS")
	private String acceptReportStatus;//验收报告状态
	@Column(name = "SHIP_DATE")
	private Date shipDate; //发货日期
	@Column(name="SALE_PROPERTY")
	private String saleProperty;//销售属性
	
	
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentModel() {
		return equipmentModel;
	}
	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Date getInstallDate() {
		return installDate;
	}
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
	public String getEquipmentStatus() {
		return equipmentStatus;
	}
	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	public String getWarrantyStatus() {
		return warrantyStatus;
	}
	public void setWarrantyStatus(String warrantyStatus) {
		this.warrantyStatus = warrantyStatus;
	}
	public String getAcceptReportStatus() {
		return acceptReportStatus;
	}
	public void setAcceptReportStatus(String acceptReportStatus) {
		this.acceptReportStatus = acceptReportStatus;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	public String getSaleProperty() {
		return saleProperty;
	}
	public void setSaleProperty(String saleProperty) {
		this.saleProperty = saleProperty;
	}

}
