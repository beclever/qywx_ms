package com.grgbanking.css.bean.work;

import java.util.Date;

/**
 * Copyright (c) 2011 GRG Banking Equipment Co.,Ltd.
 * 
 * @createDate 2012-10-15
 * @author luowei
 * @description
 * 
 */
public class PaperWorkFormBean {

	/* ====查询条件 开始==== */
	private String ownerUserName;

	private String useStatus;

	private String startCode;
	
	private String endCode;
	
	private String stationIds;
	
	private String applyUserName;
	
	private Integer queryUserId;//工程师自查流程
	
	private Date startDate;
	
	private Date endDate;

	/* ====查询条件 结束==== */
	
	private String abandonedWorkflowId;//废弃流程主键
	
	private String abandonedWorkflowIds;//申请废弃流程集合(用于批量提交)
	
	private String paperFormCode;//纸质工单编号
	
	private String poNumber;//工单编号
	
	private String workFormId;//工单编号
	
	private String departmentId;//部门主键
	
	private String paperFormIds;//纸质工单集合
	
	private Long paperformId;//纸质工单编号
	
	private String reason;//废弃原因
	
	private String status;//废弃流程状态
	
	private String note;//废弃流程发起时备注
	
	private String departmentIds;//部门主键
	
	private String abandondReason;//废弃原因
	
	//用于满意度报表
	private String createDateStart;
	private String createDateEnd;
	private String finishDateStart;
	private String finishDateEnd;
	private String lastDateStart;
	private String lastDateEnd;
	private String customerIds;
	private String customerName;
	

	public String getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Long getPaperformId() {
		return paperformId;
	}

	public void setPaperformId(Long paperformId) {
		this.paperformId = paperformId;
	}

	public String getAbandondReason() {
		return abandondReason;
	}

	public void setAbandondReason(String abandondReason) {
		this.abandondReason = abandondReason;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getStartCode() {
		return startCode;
	}

	public void setStartCode(String startCode) {
		this.startCode = startCode;
	}

	public String getEndCode() {
		return endCode;
	}

	public void setEndCode(String endCode) {
		this.endCode = endCode;
	}

	public String getAbandonedWorkflowId() {
		return abandonedWorkflowId;
	}

	public void setAbandonedWorkflowId(String abandonedWorkflowId) {
		this.abandonedWorkflowId = abandonedWorkflowId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getPaperFormIds() {
		return paperFormIds;
	}

	public void setPaperFormIds(String paperFormIds) {
		this.paperFormIds = paperFormIds;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaperFormCode() {
		return paperFormCode;
	}

	public void setPaperFormCode(String paperFormCode) {
		this.paperFormCode = paperFormCode;
	}

	public String getWorkFormId() {
		return workFormId;
	}

	public void setWorkFormId(String workFormId) {
		this.workFormId = workFormId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStationIds() {
		return stationIds;
	}

	public void setStationIds(String stationIds) {
		this.stationIds = stationIds;
	}

	public String getAbandonedWorkflowIds() {
		return abandonedWorkflowIds;
	}

	public void setAbandonedWorkflowIds(String abandonedWorkflowIds) {
		this.abandonedWorkflowIds = abandonedWorkflowIds;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public Integer getQueryUserId() {
		return queryUserId;
	}

	public void setQueryUserId(Integer queryUserId) {
		this.queryUserId = queryUserId;
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

	public String getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getFinishDateStart() {
		return finishDateStart;
	}

	public void setFinishDateStart(String finishDateStart) {
		this.finishDateStart = finishDateStart;
	}

	public String getFinishDateEnd() {
		return finishDateEnd;
	}

	public void setFinishDateEnd(String finishDateEnd) {
		this.finishDateEnd = finishDateEnd;
	}

	public String getLastDateStart() {
		return lastDateStart;
	}

	public void setLastDateStart(String lastDateStart) {
		this.lastDateStart = lastDateStart;
	}

	public String getLastDateEnd() {
		return lastDateEnd;
	}

	public void setLastDateEnd(String lastDateEnd) {
		this.lastDateEnd = lastDateEnd;
	}
	
}
