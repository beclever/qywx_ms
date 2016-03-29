package com.grgbanking.css.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.grgbanking.css.common.CssBaseEntity;

/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: liqiu
 * Date: 2010-7-18 上午11:40:17
 */

@Entity
@Table(name="V_CUSTOMER_PERSON")
public class CustomerPerson extends CssBaseEntity{

	private static final long serialVersionUID = 1L;

	private Integer personId;  //记录个人信息编号	
	
	private String personName; //客户姓名
	
	private String personGender; //客户性别
	
	private String personPost; //客户职务
	
	private String personDept; //客户所属部门
	
	private String personWork; //客户主管工作
	
	private String personOfficePhone; //客户办公电话
	
	private String personMobilePhone; //客户移动电话
	
	private String personEmail;//客户邮箱
	
	private String personOfficeaddr;//客户办公地址
	
	private CrmCustomer customer; //个人信息所属客户
	
	public CustomerPerson(){
		
	}
	
	public CustomerPerson(CustomerPersonBean personBean){
		this.personName = personBean.getPersonName();
		this.personGender = personBean.getPersonGender();
		this.personDept = personBean.getPersonDept();
		this.personPost = personBean.getPersonPost();
		this.personWork = personBean.getPersonWork();
		this.personOfficePhone = personBean.getPersonOfficePhone();
		this.personMobilePhone = personBean.getPersonMobilePhone();
		this.personEmail = personBean.getPersonEmail();
		this.personOfficeaddr = personBean.getPersonOfficeaddr();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_SEQ_CUSTOMER_PERSON")
	@SequenceGenerator(name = "GENERATOR_SEQ_CUSTOMER_PERSON", sequenceName = "SEQ_CUSTOMER_PERSON", initialValue = 1, allocationSize = 1)
	@Column(name="PERSON_ID")
	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	@Column(name="PERSON_NAME")
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	@Column(name="PERSON_GENDER")
	public String getPersonGender() {
		return personGender;
	}

	public void setPersonGender(String personGender) {
		this.personGender = personGender;
	}

	@Column(name="PERSON_POST")
	public String getPersonPost() {
		return personPost;
	}

	public void setPersonPost(String personPost) {
		this.personPost = personPost;
	}

	@Column(name="PERSON_DEPT")
	public String getPersonDept() {
		return personDept;
	}

	public void setPersonDept(String personDept) {
		this.personDept = personDept;
	}

	@Column(name="PERSON_WORK")
	public String getPersonWork() {
		return personWork;
	}

	public void setPersonWork(String personWork) {
		this.personWork = personWork;
	}

	@Column(name="PERSON_OFFICEPHONE")
	public String getPersonOfficePhone() {
		return personOfficePhone;
	}

	public void setPersonOfficePhone(String personOfficePhone) {
		this.personOfficePhone = personOfficePhone;
	}

	@Column(name="PERSON_MOBILEPHONE")
	public String getPersonMobilePhone() {
		return personMobilePhone;
	}

	public void setPersonMobilePhone(String personMobilePhone) {
		this.personMobilePhone = personMobilePhone;
	}

	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="CUSTOMER_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	public CrmCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CrmCustomer customer) {
		this.customer = customer;
	}

	@Column(name="PERSON_EMAIL")
	public String getPersonEmail() {
		return personEmail;
	}

	

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	@Column(name="PERSON_OFFICEADDR")
	
		public String getPersonOfficeaddr() {
		return personOfficeaddr;
	}

	public void setPersonOfficeaddr(String personOfficeaddr) {
		this.personOfficeaddr = personOfficeaddr;
	}
}
