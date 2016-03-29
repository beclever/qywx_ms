package com.grgbanking.css.bean.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "V_SERVICES_TASK_CONTACT")
public class WorkTaskContact {

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

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_SEQ_SERVICES_TASK_CONTACT")
	@SequenceGenerator(name = "GENERATOR_SEQ_SERVICES_TASK_CONTACT", sequenceName = "SEQ_SERVICES_TASK_CONTACT", initialValue = 1, allocationSize = 1)
	@Column(name = "TASK_CONTACT_ID")
	public Integer getTaskContactId() {
		return taskContactId;
	}

	public void setTaskContactId(Integer taskContactId) {
		this.taskContactId = taskContactId;
	}

	@Column(name = "TASK_ID")
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@Column(name = "CONTACT_NAME")
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Column(name = "MOBLIE_PHONE")
	public String getMobliePhone() {
		return mobliePhone;
	}

	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "TASK_CONTACT_TYPE")
	public String getTaskContactType() {
		return taskContactType;
	}

	public void setTaskContactType(String taskContactType) {
		this.taskContactType = taskContactType;
	}

	@Column(name = "DELETED")
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	@Column(name = "CREATE_USER_ID")
	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "WORKFORM_ID")
	public Integer getWorkformId() {
		return workformId;
	}

	public void setWorkformId(Integer workformId) {
		this.workformId = workformId;
	}

	@Column(name = "IS_MANAGER")
	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

}
