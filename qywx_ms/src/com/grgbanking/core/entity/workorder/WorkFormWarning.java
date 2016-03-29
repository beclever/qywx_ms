package com.grgbanking.core.entity.workorder;

import java.util.Date;

public class WorkFormWarning {
	
	private Long workformwarningId;
	private Long workformId;
	private String warningType;
	private Long minTimes;
	private String receiveName;
	private String receiveMobliephone;
	private String receiveMessage;
	private Date createDate;
	private String warningRecode;
	private String satisfactionType;
	private Long receiveUserId;
	private Long isrevisit;
	
	
	public Long getWorkformwarningId() {
		return workformwarningId;
	}
	public void setWorkformwarningId(Long workformwarningId) {
		this.workformwarningId = workformwarningId;
	}
	public Long getWorkformId() {
		return workformId;
	}
	public void setWorkformId(Long workformId) {
		this.workformId = workformId;
	}
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	public Long getMinTimes() {
		return minTimes;
	}
	public void setMinTimes(Long minTimes) {
		this.minTimes = minTimes;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getReceiveMobliephone() {
		return receiveMobliephone;
	}
	public void setReceiveMobliephone(String receiveMobliephone) {
		this.receiveMobliephone = receiveMobliephone;
	}
	public String getReceiveMessage() {
		return receiveMessage;
	}
	public void setReceiveMessage(String receiveMessage) {
		this.receiveMessage = receiveMessage;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getWarningRecode() {
		return warningRecode;
	}
	public void setWarningRecode(String warningRecode) {
		this.warningRecode = warningRecode;
	}
	public String getSatisfactionType() {
		return satisfactionType;
	}
	public void setSatisfactionType(String satisfactionType) {
		this.satisfactionType = satisfactionType;
	}
	public Long getReceiveUserId() {
		return receiveUserId;
	}
	public void setReceiveUserId(Long receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	public Long getIsrevisit() {
		return isrevisit;
	}
	public void setIsrevisit(Long isrevisit) {
		this.isrevisit = isrevisit;
	}
	

}
