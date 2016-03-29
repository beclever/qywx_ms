package com.grgbanking.css.bean;

import java.util.List;

/**
 * 
 * Product:Grgbanking Service Of Customer System. 
 * Version:2.0 Copyright 2010 by
 * Grgbanking All Rights Reserved. 
 * Author: liqiu 
 * Date: 2010-7-16 上午11:44:47
 */
public class CustomerBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer customerId; // 主键ID

	private String customerName; // 客户名称

	private String customerType; // 客户类型

	private String customerProperty; // 客户属性

	private String customerBelong; // 客户所属银行

	private Integer region; // 用记所在区域ID

	private String regionName; //所属区域
	
	private Integer province; // 所属省份

	private String provinceName; //所在省份的ID
	
	private Integer upperCustomer; //上级客户的ID;
	
	private String upperCustomerName; // 客户所属上级客户
	
	private String customerCode; // 客户编号

	private String companyName; // 公司名称

	private String registerAddress; // 注册地址

	private String certificate; // 税务登记证编号

	private String bankName; // 开户银行

	private String bankAccount; // 开户帐号

	private String customerTel; // 联系电话

	private String customerFax; // 传真
	
	private  List<CustomerPerson> personList; //客户所属个人信息记录集
	
	private String hasRevisit;  //是否回访
	
	private String lastRevisitTime;  //回访客户的最后时间
	
	private Integer countRevisit;  //今年回访次数
	
	private String createDate;//回访单创建的年份
	
	private String customerLevel; //客户级别
	
	private String bankLevel;
	
	
	public String getBankLevel() {
		return bankLevel;
	}

	public void setBankLevel(String bankLevel) {
		this.bankLevel = bankLevel;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerProperty() {
		return customerProperty;
	}

	public void setCustomerProperty(String customerProperty) {
		this.customerProperty = customerProperty;
	}

	public String getCustomerBelong() {
		return customerBelong;
	}

	public void setCustomerBelong(String customerBelong) {
		this.customerBelong = customerBelong;
	}


	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getUpperCustomer() {
		return upperCustomer;
	}

	public void setUpperCustomer(Integer upperCustomer) {
		this.upperCustomer = upperCustomer;
	}

	public String getUpperCustomerName() {
		return upperCustomerName;
	}

	public void setUpperCustomerName(String upperCustomerName) {
		this.upperCustomerName = upperCustomerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public String getCustomerFax() {
		return customerFax;
	}

	public void setCustomerFax(String customerFax) {
		this.customerFax = customerFax;
	}

	public List<CustomerPerson> getPersonList() {
		return personList;
	}

	public void setPersonList(List<CustomerPerson> personList) {
		this.personList = personList;
	}

	public String getHasRevisit() {
		return hasRevisit;
	}

	public void setHasRevisit(String hasRevisit) {
		this.hasRevisit = hasRevisit;
	}

	public Integer getCountRevisit() {
		return countRevisit;
	}

	public void setCountRevisit(Integer countRevisit) {
		this.countRevisit = countRevisit;
	}

	public String getLastRevisitTime() {
		return lastRevisitTime;
	}

	public void setLastRevisitTime(String lastRevisitTime) {
		this.lastRevisitTime = lastRevisitTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}
	
}
