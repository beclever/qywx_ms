package com.grgbanking.core.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_SYS_USER")
public class WxUser {
	/**
	 * 主键
	 */
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	/**
	 * 登录帐号
	 */
	@Column(name = "LOGIN_NAME")
	private String loginName;

	/**
	 * 用户编码
	 */
	@Column(name = "USER_CODE")
	private String userCode;
	/**
	 * 姓名
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * 电话
	 */
	@Column(name = "MOBILEPHONE")
	private String mobilePhone;
	
	//private String device;

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/*public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}*/

}
