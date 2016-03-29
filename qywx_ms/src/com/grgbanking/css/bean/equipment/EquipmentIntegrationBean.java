package com.grgbanking.css.bean.equipment;

/**
 * 设备综合报表查询条件
 * @ClassName com.grgbanking.report.bean.EquipmentIntegrationBean
 * @Author Sin
 * @Version 1.0
 * @Date 2010-2-1 下午02:48:50
 */
public class EquipmentIntegrationBean {
	private Integer customerId;
	private Integer stationId;
	private String workFinishDateStart;
	private String workFinishDateEnd;
	private String saleContractNo;
	private String maintainContractNo;
	private String equipmentType;
	private Integer[] stationIds;
	private String customerIds;
	
	public String getCustomerIds() {
		return customerIds;
	}
	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}
	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the stationId
	 */
	public Integer getStationId() {
		return stationId;
	}
	/**
	 * @param stationId the stationId to set
	 */
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	/**
	 * @return the workFinishDateStart
	 */
	public String getWorkFinishDateStart() {
		return workFinishDateStart;
	}
	/**
	 * @param workFinishDateStart the workFinishDateStart to set
	 */
	public void setWorkFinishDateStart(String workFinishDateStart) {
		this.workFinishDateStart = workFinishDateStart;
	}
	/**
	 * @return the workFinishDateEnd
	 */
	public String getWorkFinishDateEnd() {
		return workFinishDateEnd;
	}
	/**
	 * @param workFinishDateEnd the workFinishDateEnd to set
	 */
	public void setWorkFinishDateEnd(String workFinishDateEnd) {
		this.workFinishDateEnd = workFinishDateEnd;
	}
	/**
	 * @return the saleContractNo
	 */
	public String getSaleContractNo() {
		return saleContractNo;
	}
	/**
	 * @param saleContractNo the saleContractNo to set
	 */
	public void setSaleContractNo(String saleContractNo) {
		this.saleContractNo = saleContractNo;
	}
	/**
	 * @return the maintainContractNo
	 */
	public String getMaintainContractNo() {
		return maintainContractNo;
	}
	/**
	 * @param maintainContractNo the maintainContractNo to set
	 */
	public void setMaintainContractNo(String maintainContractNo) {
		this.maintainContractNo = maintainContractNo;
	}
	/**
	 * @return the equipmentType
	 */
	public String getEquipmentType() {
		return equipmentType;
	}
	/**
	 * @param equipmentType the equipmentType to set
	 */
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public Integer[] getStationIds() {
		return stationIds;
	}
	public void setStationIds(Integer[] stationIds) {
		this.stationIds = stationIds;
	}
	
}
