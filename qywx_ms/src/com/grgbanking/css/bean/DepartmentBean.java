package com.grgbanking.css.bean;

import java.util.Date;
import java.util.List;

/**
 * 
 * Product:Grgbanking Service Of Customer System. Version:2.0 Copyright 2010 by
 * Grgbanking All Rights Reserved. Author: Administrator Date: 2010-6-3
 * 下午04:54:55
 */
public class DepartmentBean {

	private Integer departmentId;// 主键ID
	private String departmentIds;

	private String departmentCode;// 部门编号

	private String departmentName;// 部门名称

	private Date createDate;// 创建日期

	private Integer parentId;// 上级部门
	private String parentIds;

	private String grade;// 服务站等级

	private Integer province;// 省

	private Integer city;// 市
	
	private String provinceName;
	private String cityName;

	private String address;// 地址

	private Integer createUserId; // 创建人

	private String departmentType;// 部门类型

	// 移动电话
	private String mobilePhone;
	// 办公电话
	private String officePhone;
	// 传真
	private String fax;
	// 邮编
	private String post;
	//E_MAIL
	private String email;
	// 负责地区
	private String manageDistrict;
	// 更新日期
	protected Date modifyDate;
	// 更新人
	protected Integer modifyUserId;

	// 建立时间(输入时间)
	private Date setupDate;
	// 版本号
	protected Integer version;

	// 部门负责人UserID
	private Integer directorUserId;
	private String directorUserName;
	
	//上级部门名称
	private String parentName;
	
	//开始时间(建立时间查询)
	private String startCreateDate;
	//结束时间(建立时间查询)
	private String endCreateDate;
	//子部门集合
	private List<DepartmentBean> children;
	
	// 营业时间
	private String businessDate;
	// 房租信息
	private String rentMessage;
	
	//是否省平台
	private String provincePlatform;//Y,N
	
	//指向的省平台的ID
	private Integer provincePlatformId;
	private String provincePlatformName;
	
	private Integer centerPlatformId;//中心站的id
	
	//网点数量
	private Integer toTalGrade;
	
	private Integer childrenNumber;	// 子节点数
	
	private String hrIsSyn;//是否同步(Y不同步)
	
	public Integer getChildrenNumber() {
		return childrenNumber;
	}

	public void setChildrenNumber(Integer childrenNumber) {
		this.childrenNumber = childrenNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	

	public String getRentMessage() {
		return rentMessage;
	}

	public void setRentMessage(String rentMessage) {
		this.rentMessage = rentMessage;
	}

	public String getStartCreateDate() {
		return startCreateDate;
	}

	public void setStartCreateDate(String startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	public String getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getDirectorUserId() {
		return directorUserId;
	}

	public void setDirectorUserId(Integer directorUserId) {
		this.directorUserId = directorUserId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
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

	public String getManageDistrict() {
		return manageDistrict;
	}

	public void setManageDistrict(String manageDistrict) {
		this.manageDistrict = manageDistrict;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}


	public String getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public List<DepartmentBean> getChildren() {
		return children;
	}

	public void setChildren(List<DepartmentBean> children) {
		this.children = children;
	}

	public String getProvincePlatform() {
		return provincePlatform;
	}

	public void setProvincePlatform(String provincePlatform) {
		this.provincePlatform = provincePlatform;
	}

	public String getDirectorUserName() {
		return directorUserName;
	}

	public void setDirectorUserName(String directorUserName) {
		this.directorUserName = directorUserName;
	}

	public Integer getProvincePlatformId() {
		return provincePlatformId;
	}

	public void setProvincePlatformId(Integer provincePlatformId) {
		this.provincePlatformId = provincePlatformId;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getProvincePlatformName() {
		return provincePlatformName;
	}

	public void setProvincePlatformName(String provincePlatformName) {
		this.provincePlatformName = provincePlatformName;
	}

	public Integer getToTalGrade() {
		return toTalGrade;
	}

	public void setToTalGrade(Integer toTalGrade) {
		this.toTalGrade = toTalGrade;
	}

	public Integer getCenterPlatformId() {
		return centerPlatformId;
	}

	public void setCenterPlatformId(Integer centerPlatformId) {
		this.centerPlatformId = centerPlatformId;
	}

	public String getHrIsSyn() {
		return hrIsSyn;
	}

	public void setHrIsSyn(String hrIsSyn) {
		this.hrIsSyn = hrIsSyn;
	}
}
