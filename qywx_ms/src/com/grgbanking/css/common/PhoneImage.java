package com.grgbanking.css.common;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * Product:Grgbanking Service Of Customer System. Version:2.0 Copyright 2010 by
 * Grgbanking All Rights Reserved. Author: jeff steve . lin Date: 2012-6-14
 * 上午11:07:43
 */
@Entity
@Table(name="V_SERVICES_IMAGES")
public class PhoneImage {

	private Integer id;// 递增字段 （主键）
	private Integer workformId;// 工单ID（工单附件）
	private Integer equipmentId;// 设备ID（网点）
	private Integer departmentId;// 服务站
	private String imagesContent;// 中文描述
	private String imagesUrl;// 图片路径
	private String longitude;// 经度
	private String latitude;// 纬度
	private String imagesAddress;// 图片上传中文地址
	private Integer imagesType;// 图片类型,1.网点(与customer挂钩)2.工单（与workformId挂钩)3.服务站(与departmentId挂钩)
	private Integer customerId; // 服务客户 网点ID
	private Integer userId;// 创建人
	private Date createDate;// 创建时间
	private Blob imagesBlob;//二进制形式存图片
	
	
	@Id
	@SequenceGenerator(name = "SEQ_SERVICES_IMAGES", sequenceName = "SEQ_SERVICES_IMAGES", initialValue = 83500, allocationSize = 1)   
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVICES_IMAGES")   
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "WORKFORM_ID")
	public Integer getWorkformId() {
		return workformId;
	}
	public void setWorkformId(Integer workformId) {
		this.workformId = workformId;
	}
	
	@Column(name = "EQUIPMENT_ID")
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	@Column(name = "DEPARTMENT_ID")
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	@Column(name = "IMAGES_CONTENT")
	public String getImagesContent() {
		return imagesContent;
	}
	public void setImagesContent(String imagesContent) {
		this.imagesContent = imagesContent;
	}
	
	@Column(name = "IMAGES_URL")
	public String getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	
	@Column(name = "LONGITUDE")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Column(name = "LATITUDE")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Column(name = "IMAGES_ADDRESS")
	public String getImagesAddress() {
		return imagesAddress;
	}
	public void setImagesAddress(String imagesAddress) {
		this.imagesAddress = imagesAddress;
	}
	
	@Column(name = "IMAGES_TYPE")
	public Integer getImagesType() {
		return imagesType;
	}
	public void setImagesType(Integer imagesType) {
		this.imagesType = imagesType;
	}
	
	@Column(name = "CUSTOMER_ID")
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	@Column(name = "CREATE_USER_ID")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "IMAGES_BLOB")
	public Blob getImagesBlob() {
		return imagesBlob;
	}
	public void setImagesBlob(Blob imagesBlob) {
		this.imagesBlob = imagesBlob;
	}
	

}
