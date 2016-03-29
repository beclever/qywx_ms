package com.grgbanking.css.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 省份实体类
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-6-22 下午04:33:20
 */
@Entity
@Table(name = "V_BASE_PROVINCE")
public class Province implements Serializable {
	private Integer provinceId;
	private String provinceName;
	private Integer regionId;
	private Integer orderBy;
	
	private Integer leftLogo;//地图横坐标
	private Integer topLogo;//地图纵坐标
	private String location;//所在位置
	
	
	@Id
	@Column(name="PROVINCE_ID")
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	@Column(name="PROVINCE_NAME")
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	@Column(name="ORDER_BY")
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	@Column(name="REGION_ID")
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	@Column(name="LEFT")
	public Integer getLeftLogo() {
		return leftLogo;
	}
	public void setLeftLogo(Integer leftLogo) {
		this.leftLogo = leftLogo;
	}
	@Column(name="TOP")
	public Integer getTopLogo() {
		return topLogo;
	}
	public void setTopLogo(Integer topLogo) {
		this.topLogo = topLogo;
	}
	@Column(name="LOCATION")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
