package com.grgbanking.css.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 当前登录用户基本信息BEAN
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-29 下午12:54:54
 */
public class CssUserBean implements Serializable{
	private Integer userId;//用户ID
	private String loginName;//登录名
	private String name;//姓名
	private String departmentName;//用户所在部门
	private String departmentType;//用户所在部门类型
	private String departmentCode;//用户所在部门编码
	private Integer departmentId;//用记所在部门ID
	private Date loginTime;//登录时间
	private String roleString;//用户所具有的角色代码,多个角色以逗号隔开
	private String roleNameString;//用户所具有的角色名称,多个角色以逗号隔开
	
	private String defaultStyle;//默认皮肤
	private String sex;//性别
	private String email;//电子邮箱
	private String phone;//联系电话
	private String mobilephone;//移动电话
	private String deleted;//是否删除（离职）
	private Date createDate;//创建日期
	private Integer createUserId;//创建人
	private Date modifyDate;//修改日期
	private Integer modifyUserId;//更新人
	private String password;// 登录密码 
	private Integer version;//版本号
	private Integer belongDepartmentId;//当前工作部门
	
	private String provincePlatform;//是否为省平台Y,N
	private Integer provincePlatformId;//所指向的省平台id，没有省平台为null
	
	private Integer centerPlatformId; //平台中心
	
	private String loginIp;//登录人IP
	
	/*已有用户的标记，主要用于用户授权用，1为已选，0为未选*/
	private Integer haveUsers;
	private String employeeNo;
	private String numWT;//未完成任务数量
	private String numWF;//未完成工单数量
	
	private String shortMessage;//系统短消息
	
	private String parentId;//用户所属部门   用于人员树显示
	private String parUserId;//用于人员树显示
	
	private String beforeSupportRoles;//支援之前权限
	
	private String engineerGradeH68N;//H68N工程师等级
	
	private String userCode;
	
	public List<Integer> departmentIds;
	
	
	public List<Integer> getDepartmentIds() {
		return departmentIds;
	}
	public void setDepartmentIds(List<Integer> departmentIds) {
		this.departmentIds = departmentIds;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentType() {
		return departmentType;
	}
	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getRoleString() {
		return roleString;
	}
	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}
	public Integer getHaveUsers() {
		return haveUsers;
	}
	public void setHaveUsers(Integer haveUsers) {
		this.haveUsers = haveUsers;
	}
	public String getRoleNameString() {
		return roleNameString;
	}
	public void setRoleNameString(String roleNameString) {
		this.roleNameString = roleNameString;
	}
	public String getProvincePlatform() {
		return provincePlatform;
	}
	public void setProvincePlatform(String provincePlatform) {
		this.provincePlatform = provincePlatform;
	}
	
	public Date getCurrentTime(){
		return new Date();
	}
	public String getDefaultStyle() {
		return defaultStyle;
	}
	public void setDefaultStyle(String defaultStyle) {
		this.defaultStyle = defaultStyle;
	}
	public Integer getBelongDepartmentId() {
		return belongDepartmentId;
	}
	public void setBelongDepartmentId(Integer belongDepartmentId) {
		this.belongDepartmentId = belongDepartmentId;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Integer getProvincePlatformId() {
		return provincePlatformId;
	}
	public void setProvincePlatformId(Integer provincePlatformId) {
		this.provincePlatformId = provincePlatformId;
	}
	public String getNumWT() {
		return numWT;
	}
	public void setNumWT(String numWT) {
		this.numWT = numWT;
	}
	public String getNumWF() {
		return numWF;
	}
	public void setNumWF(String numWF) {
		this.numWF = numWF;
	}
	public String getShortMessage() {
		return shortMessage;
	}
	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParUserId() {
		return parUserId;
	}
	public void setParUserId(String parUserId) {
		this.parUserId = parUserId;
	}
	public String getBeforeSupportRoles() {
		return beforeSupportRoles;
	}
	public void setBeforeSupportRoles(String beforeSupportRoles) {
		this.beforeSupportRoles = beforeSupportRoles;
	}
	public String getEngineerGradeH68N() {
		return engineerGradeH68N;
	}
	public void setEngineerGradeH68N(String engineerGradeH68N) {
		this.engineerGradeH68N = engineerGradeH68N;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Integer getCenterPlatformId() {
		return centerPlatformId;
	}
	public void setCenterPlatformId(Integer centerPlatformId) {
		this.centerPlatformId = centerPlatformId;
	}
	
}
