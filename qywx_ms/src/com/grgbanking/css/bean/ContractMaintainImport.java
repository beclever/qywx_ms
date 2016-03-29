package com.grgbanking.css.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContractMaintainImport implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String contractNumber;

	private Date startDate;

	private Date endDate;

	private String equipSerial;

	private String pmCount;

	private String synchState;

	private Date createTime;

	private Date updateTime;
	
	private String deleteed;
	
	private String clientName;
	
	private Long clientId;
	
	private Long subClientId;
    private String subClientName;

    private String equipmentModel;

    private String branch;

    private String start_time;
    private String end_time;
	private String equipmentType;

    private BigDecimal maintainAmount;
    
    private int maintainMonths;
    
    private String singlePrice;

	private List<Long> ids = new ArrayList<Long>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEquipSerial() {
		return equipSerial;
	}

	public void setEquipSerial(String equipSerial) {
		this.equipSerial = equipSerial;
	}

	public String getPmCount() {
		return pmCount;
	}

	public void setPmCount(String pmCount) {
		this.pmCount = pmCount;
	}

	public String getSynchState() {
		return synchState;
	}

	public void setSynchState(String synchState) {
		this.synchState = synchState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the ids
	 */
	public List<Long> getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getDeleteed() {
		return deleteed;
	}

	public void setDeleteed(String deleteed) {
		this.deleteed = deleteed;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getSubClientName() {
		return subClientName;
	}

	public void setSubClientName(String subClientName) {
		this.subClientName = subClientName;
	}

	public String getEquipmentModel() {
		return equipmentModel;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String startTime) {
		start_time = startTime;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String endTime) {
		end_time = endTime;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public BigDecimal getMaintainAmount() {
		return maintainAmount;
	}

	public void setMaintainAmount(BigDecimal maintainAmount) {
		this.maintainAmount = maintainAmount;
	}

	public int getMaintainMonths() {
		return maintainMonths;
	}

	public void setMaintainMonths(int maintainMonths) {
		this.maintainMonths = maintainMonths;
	}

	public String getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(String singlePrice) {
		this.singlePrice = singlePrice;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getSubClientId() {
		return subClientId;
	}

	public void setSubClientId(Long subClientId) {
		this.subClientId = subClientId;
	}
	

}