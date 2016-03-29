package com.grgbanking.core.entity.equipment;

import java.util.Date;

/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: Administrator
 * Date: 2012-6-15 上午9:30:59
 */
public class CssPhoneImageBean {
	
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
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getWorkformId() {
		return workformId;
	}
	public void setWorkformId(Integer workformId) {
		this.workformId = workformId;
	}
	
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getImagesContent() {
		return imagesContent;
	}
	public void setImagesContent(String imagesContent) {
		this.imagesContent = imagesContent;
	}
	
	public String getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getImagesAddress() {
		return imagesAddress;
	}
	public void setImagesAddress(String imagesAddress) {
		this.imagesAddress = imagesAddress;
	}
	
	public Integer getImagesType() {
		return imagesType;
	}
	public void setImagesType(Integer imagesType) {
		this.imagesType = imagesType;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
