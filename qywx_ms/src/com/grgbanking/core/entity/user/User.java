package com.grgbanking.core.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_SYS_USER")
public class User {
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

}
