package com.grgbanking.css.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.grgbanking.css.common.CssBaseEntity;

/**
 * 部门实体类 Product:Grgbanking Service Of Customer System. Version:2.0 Copyright
 * 2010 by Grgbanking All Rights Reserved. Author: zhang zhi Date: 2010-4-21
 * 上午11:53:28
 */
@Entity
@Table(name = "V_BASE_DEPARTMENT")
public class Department extends CssBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7125050223903204674L;

	private Integer departmentId;// 主键ID

	private String departmentCode;// 部门编号

	private String departmentName;// 部门名称

	private String departmentType;// 部门类型

	private Department parent;// 上级部门

	// 移动电话
	private String mobilePhone;
	// 办公电话
	private String officePhone;
	// 传真
	private String fax;
	// 邮编
	private String post;
	// 邮箱
	private String email;
	// 地址
	private String address;
	// 负责地区
	private String manageDistrict;
	// 服务站等级
	private String grade;
	// 省
	private Integer province;
	// 市
	private Integer city;
	// 部门负责人UserID
	private CssUser directorUserId;
	// 建立时间(输入时间)
	private Date setupDate;
	// 营业时间
	private String businessDate;
	// 房租信息
	private String rentMessage;
	//是否省平台
	private String provincePlatform;//Y,N
	//指向的省平台ID
	private Integer provincePlatformId;
	private Integer centerPlatformId;//中心站的id
	// 此部门的子部门
	private List<Department> children = new ArrayList<Department>(0);
	
	private Integer hrDeptId;//人资部门id记录 2013-10-12 
	
	private String hrIsSyn;//是否同步(Y不同步)

	/**
	 * @return the departmentId
	 */
	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_SEQ_BASE_DEPARTMENT")
	@SequenceGenerator(name = "GENERATOR_SEQ_BASE_DEPARTMENT", sequenceName = "SEQ_BASE_DEPARTMENT", initialValue = 1, allocationSize = 1)*/ //2013-10-9取消自增长，由hr同步数据
	@Column(name = "DEPARTMENT_ID")
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "DEPARTMENT_NAME")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "DEPARTMENT_TYPE")
	public String getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	@Column(name = "DEPARTMENT_CODE")
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "PARENT_ID")
	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	@Column(name = "MOBILEPHONE")
	public String getMobilePhone() {
		return mobilePhone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "OFFICEPHONE")
	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "POST")
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Column(name = "E_MAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "MANAGERDISTRICT")
	public String getManageDistrict() {
		return manageDistrict;
	}

	public void setManageDistrict(String manageDistrict) {
		this.manageDistrict = manageDistrict;
	}

	@Column(name = "GRADE")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "PROVINCE")
	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@Column(name = "CITY")
	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "DIRECTOR_ID")
	public CssUser getDirectorUserId() {
		return directorUserId;
	}

	public void setDirectorUserId(CssUser directorUserId) {
		this.directorUserId = directorUserId;
	}

	@Column(name = "SETUPDATE")
	public Date getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	@OrderBy("departmentType desc")
	public List<Department> getChildren() {
		return children;
	}

	public void setChildren(List<Department> children) {
		this.children = children;
	}

	@Column(name = "BUSINESSDATE")
	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	@Column(name = "RENTMESSAGE")
	public String getRentMessage() {
		return rentMessage;
	}

	public void setRentMessage(String rentMessage) {
		this.rentMessage = rentMessage;
	}
	@Column(name = "PROVINCE_PLATFORM")
	public String getProvincePlatform() {
		return provincePlatform;
	}

	public void setProvincePlatform(String provincePlatform) {
		this.provincePlatform = provincePlatform;
	}

	
	@Column(name="PROVINCE_PLATFORM_ID")
	public Integer getProvincePlatformId() {
		return provincePlatformId;
	}

	public void setProvincePlatformId(Integer provincePlatformId) {
		this.provincePlatformId = provincePlatformId;
	}
	@Column(name="CENTER_PLATFORM_ID")
	public Integer getCenterPlatformId() {
		return centerPlatformId;
	}

	public void setCenterPlatformId(Integer centerPlatformId) {
		this.centerPlatformId = centerPlatformId;
	}

	@Column(name="HR_DEPT_ID")
	public Integer getHrDeptId() {
		return hrDeptId;
	}
	public void setHrDeptId(Integer hrDeptId) {
		this.hrDeptId = hrDeptId;
	}

	@Column(name="HR_IS_SYN")
	public String getHrIsSyn() {
		return hrIsSyn;
	}

	public void setHrIsSyn(String hrIsSyn) {
		this.hrIsSyn = hrIsSyn;
	}
	
	
}

