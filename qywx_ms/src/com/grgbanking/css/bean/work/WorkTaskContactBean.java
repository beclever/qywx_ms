package com.grgbanking.css.bean.work;

import java.util.Date;

public class WorkTaskContactBean {
	private Integer taskContactId;
	private Integer taskId;
	private Integer workformId;
	private String contactName;
	private String mobliePhone;
	private String telephone;
	private String taskContactType;
	private String deleted;
	private Integer createUserId;
	private Date createDate;
	private String isManager;// 是否当前管理员

	public Integer getTaskContactId() {
		return taskContactId;
	}

	public void setTaskContactId(Integer taskContactId) {
		this.taskContactId = taskContactId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getWorkformId() {
		return workformId;
	}

	public void setWorkformId(Integer workformId) {
		this.workformId = workformId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobliePhone() {
		return mobliePhone;
	}

	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTaskContactType() {
		return taskContactType;
	}

	public void setTaskContactType(String taskContactType) {
		this.taskContactType = taskContactType;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

}
