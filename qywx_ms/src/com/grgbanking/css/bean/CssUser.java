/**
 * User.java
 * Product:Grgbanking
 * Version:1.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 */
package com.grgbanking.css.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.grgbanking.css.common.CssBaseEntity;

/**
 * 用户信息实体类
 * 
 * @ClassName com.grgbanking.entity.User
 * @Author Administrator
 * @Version 1.0
 * @Date 2009-12-3 上午12:09:18
 */
@Entity
@Table(name = "V_BASE_USER")
public class CssUser extends CssBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9003885420495669025L;

	private Integer userId;

	/* 登录密码 */
	private String password;

	/* 登录用户名 */
	private String loginName;

	/* 用户姓名 */
	private String name;

	/* 用户所对应的ROLE */
	private List<CssRole> roles;

	/* 用户所对应的DEPARTMENT */
	private Department department;
	
	/*发的帖子*/
	//private Set<CssTopic> topics=new HashSet<CssTopic>(0);
	
	/*回复的帖子*/
	//private Set<TopicResponse> topicResponses=new HashSet<TopicResponse>(0);
	
	/*默认皮肤*/
	private String defaultStyle;
	

	private String sex;// 性别
	private String email;// 电子邮箱
	private String phone;// 联系电话
	private String mobilephone;// 移动电话
	
	private Department belongDepartment;
	
	private String beforeSupportRoles;//支援之前权限
	
	private String userCode;
	
	private String engineerGradeH68N;//H68N工程师等级

	/* 主键ID */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_SEQ_BASE_USER")
	@SequenceGenerator(name = "GENERATOR_SEQ_BASE_USER", sequenceName = "CSSTEST.SEQ_BASE_USER", initialValue = 1, allocationSize = 1)
	@Column(name = "USER_ID")
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the loginName
	 */
	@Column(name = "LOGIN_NAME")
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the name
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(targetEntity = CssRole.class, fetch = FetchType.LAZY)
	@JoinTable(name = "T_BASE_ROLE_USER", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@Fetch(FetchMode.SUBSELECT)
	public List<CssRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<CssRole> roles) {
		this.roles = roles;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPARTMENT_ID")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
		
	/*@OneToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},fetch=FetchType.LAZY,mappedBy="author")
	public Set<CssTopic> getTopics() {
		return topics;
	}
	public void setTopics(Set<CssTopic> topics) {
		this.topics = topics;
	}*/
	
	/*@OneToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},fetch=FetchType.LAZY,mappedBy="author")	
	public Set<TopicResponse> getTopicResponses() {
		return topicResponses;
	}
	public void setTopicResponses(Set<TopicResponse> topicResponses) {
		this.topicResponses = topicResponses;
	}*/

	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "MOBILEPHONE")
	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name="DEFAULT_STYLE")
	public String getDefaultStyle() {
		return defaultStyle;
	}

	public void setDefaultStyle(String defaultStyle) {
		this.defaultStyle = defaultStyle;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "BELONG_DEPARTMENT_ID")
	public Department getBelongDepartment() {
		return belongDepartment;
	}

	public void setBelongDepartment(Department belongDepartment) {
		this.belongDepartment = belongDepartment;
	}

	@Column(name="BEFORE_SUPPORT_ROLES")
	public String getBeforeSupportRoles() {
		return beforeSupportRoles;
	}
	public void setBeforeSupportRoles(String beforeSupportRoles) {
		this.beforeSupportRoles = beforeSupportRoles;
	}
	@Column(name="USER_CODE")
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name="ENGINEER_GRADE_H68N")
	public String getEngineerGradeH68N() {
		return engineerGradeH68N;
	}
	public void setEngineerGradeH68N(String engineerGradeH68N) {
		this.engineerGradeH68N = engineerGradeH68N;
	}
	


}
