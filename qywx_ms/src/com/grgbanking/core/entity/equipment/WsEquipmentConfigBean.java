package com.grgbanking.core.entity.equipment;

import java.util.List;


/**
 * 设备部件配置信息
 */

public class WsEquipmentConfigBean {

	
	private Integer sparepartId;// Int（Y） 部件ID
	private Integer parentId;// Int（Y） 父部件ID
	private String sparepartName;// String（Y） 部件名称
	private String sparepartType;// String（Y） 部件类别
	private List<WsEquipmentConfigBean> children;// Array (Y） 设备部件配置信息
	private String materialCode;//物料编码
	private String barCode;//条码
	
	public Integer getSparepartId() {
		return sparepartId;
	}
	public void setSparepartId(Integer sparepartId) {
		this.sparepartId = sparepartId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getSparepartName() {
		return sparepartName;
	}
	public void setSparepartName(String sparepartName) {
		this.sparepartName = sparepartName;
	}
	public String getSparepartType() {
		return sparepartType;
	}
	public void setSparepartType(String sparepartType) {
		this.sparepartType = sparepartType;
	}
	public List<WsEquipmentConfigBean> getChildren() {
		return children;
	}
	public void setChildren(List<WsEquipmentConfigBean> children) {
		this.children = children;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
}
