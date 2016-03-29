package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.bean.equipment.EquipmentConfig;
import com.grgbanking.css.bean.equipment.EquipmentConfigBean;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;


/**
 * 当前配置数据访问对象
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-7-23 下午03:26:26
 */
public interface EquipmentConfigDAO<T, ID> extends CssBaseDAO<T, ID> {
	/**
	 * 根据设备序列号查询设备当前配置列表,只查一级模块。一级模块再带出子模块。
	 * @param serialNumber
	 * @return
	 */
	public List<EquipmentConfig> getEquipmentConfigByEquipmentSerialNumber(String serialNumber);
	
	/**
	 * 根据设备序列号查询设备当前配置列表,只查一级模块。一级模块再带出子模块。
	 * @param serialNumber
	 * @return
	 */
	public List<EquipmentConfig> getEquipmentConfigByEquipmentSerialNumberForEdit(String serialNumber);
	
	/**
	 * 分页查询配置列表
	 * @param queryPage
	 * @param equipmentConfigBean
	 * @return
	 */
	public List<EquipmentConfig> getEquipmentConfigList(Page queryPage, EquipmentConfigBean equipmentConfigBean);
	
	/**
	 * 
	 * @param serialNumber
	 * @return
	 */
	public List<EquipmentConfig> getEquipmentConfigForCheck(String serialNumber);
	
	/**
	 * 根据设备查找设备的所有配置信息
	 * @param equipmentId
	 * @return
	 */
	public List<EquipmentConfig> getAllConfigByEquipment(Integer equipmentId);
	
	/**
	 * 备件支持入库、其他入库用到此方法：判断条码编码是否已存在
	 * @param serialNumber
	 * @return
	 */
	public EquipmentConfig getEquipmentConfigBySerialNumber(String serialNumber);
	
	/**
	 * 查找相关的条码的数量
	 * @param serialNumber
	 * @return
	 */
	public Integer getEQConfigBySerialNumber(String serialNumber);
	
	/**
	 * 查询设备序列号出现的次数（用于报废和退机）
	 * @param serialNumber
	 * @return
	 */
	public Integer getLikeEquipmengetSerialNumber(String serialNumber);

	public EquipmentConfig findConfigByRecordId(String recordId);
	
	public EquipmentConfig getEquipmentConfigByEquipmentId(Integer equipmentId,String moduleType);
	
}
