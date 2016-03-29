package com.grgbanking.css.bean;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="V_CRM_CUSTOMER_INFO")
public class CrmCustomer {
	
	@Id
	@Column(name="id")
	private Integer customerId; 	//主键ID
	@Column(name="CUSTOMER_NAME")
	private String customerName;   //客户名称
	@Column(name="customer_level_temp")
	private String customerLevel; //客户级别
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,CascadeType.MERGE })
	@JoinColumn(name = "parent_custimer_id")
	private CrmCustomer upperCustomer; //客户所属上级客户
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,CascadeType.MERGE })
	@JoinColumn(name = "sub_company")
	private Department subCompany; //所属分公司
	@Column(name="customer_type")
	private String customerType;  //客户类型
	@Column(name="customer_sub_type")
	private String customerSubType;  //客户子类型
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "province_name")
	private Province province;  //所属省份
	@Column(name="customer_code")
	private String customerCode; //客户编号
	@Column(name="register_address")
	private String registerAddress; //注册地址
	@Column(name="open_bank")
	private String bankName; //开户银行
	@Column(name="contact_phone")
	private String customerTel; //联系电话
	@Column(name="fax_number")
	private String customerFax; //传真
	@Column(name="account_name")
	private String accountName; //帐户名称
	@Column(name="tax_number")
	private String taxNumber;//税务登记证号
	@Column(name="create_time")
	private Date createDate;
	@Column(name="deteled")
	private String deleted;
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="customer")
	private List<CustomerPerson> personList;
	
	
	
	
	//private String customerProperty; //客户属性
	//private String customerBelong; //客户所属银行
	
	
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
	public String getCustomerLevel() {
		return customerLevel;
	}
	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerSubType() {
		return customerSubType;
	}
	public void setCustomerSubType(String customerSubType) {
		this.customerSubType = customerSubType;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public CrmCustomer getUpperCustomer() {
		return upperCustomer;
	}
	public void setUpperCustomer(CrmCustomer upperCustomer) {
		this.upperCustomer = upperCustomer;
	}
	public Department getSubCompany() {
		return subCompany;
	}
	public void setSubCompany(Department subCompany) {
		this.subCompany = subCompany;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getTaxNumber() {
		return taxNumber;
	}
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
	public List<CustomerPerson> getPersonList() {
		return personList;
	}
	public void setPersonList(List<CustomerPerson> personList) {
		this.personList = personList;
	}
	

}
