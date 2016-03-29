package com.grgbanking.css.bean;


/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: liqiu
 * Date: 2010-7-20 上午10:19:28
 */
public class CustomerPersonBean {

	private Integer personId;  //记录个人信息编号	
	
	private String personName; //客户姓名
	
	private String personGender; //客户性别
	
	private String personPost; //客户职务
	
	private String personDept; //客户所属部门
	
	private String personWork; //客户主管工作
	
	private String personOfficePhone; //客户办公电话
	
	private String personMobilePhone; //客户移动电话
	
	private Integer customer; //个人信息所属客户
	
	private String customerName; //所属客户姓名
	
	private String personEmail;//客户邮箱
	
	private String personOfficeaddr;//客户办公地址

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonGender() {
		return personGender;
	}

	public void setPersonGender(String personGender) {
		this.personGender = personGender;
	}

	public String getPersonPost() {
		return personPost;
	}

	public void setPersonPost(String personPost) {
		this.personPost = personPost;
	}

	public String getPersonDept() {
		return personDept;
	}

	public void setPersonDept(String personDept) {
		this.personDept = personDept;
	}

	public String getPersonWork() {
		return personWork;
	}

	public void setPersonWork(String personWork) {
		this.personWork = personWork;
	}

	public String getPersonOfficePhone() {
		return personOfficePhone;
	}

	public void setPersonOfficePhone(String personOfficePhone) {
		this.personOfficePhone = personOfficePhone;
	}

	public String getPersonMobilePhone() {
		return personMobilePhone;
	}

	public void setPersonMobilePhone(String personMobilePhone) {
		this.personMobilePhone = personMobilePhone;
	}

	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getPersonOfficeaddr() {
		return personOfficeaddr;
	}

	public void setPersonOfficeaddr(String personOfficeaddr) {
		this.personOfficeaddr = personOfficeaddr;
	}
	
	
}
