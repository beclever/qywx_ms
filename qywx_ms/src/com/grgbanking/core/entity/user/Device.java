package com.grgbanking.core.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_WECHAT_USER_DEVICE_INFO")
public class Device {
	/**
	 * 主键
	 */
	@Id
	@SequenceGenerator(name = "SEQ_WECHAT_USER_DEVICE_INFO_ID", sequenceName = "SEQ_WECHAT_USER_DEVICE_INFO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WECHAT_USER_DEVICE_INFO_ID")
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	@Column(name = "LOGIN_NAME")
	private String loginName;
	@Column(name = "DEVICE_ID")
	private String deviceId;
	@Column(name = "TICKET")
	private String ticket;
	@Column(name = "STATUS")
	private Integer status;
	@Column(name = "CREATE_TIME")
	private Date createTime;
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}
