package com.grgbanking.css.dao.vequipment;

import java.util.List;

import com.grgbanking.css.bean.vequipment.VEquipmentConfig;
import com.grgbanking.css.common.CssBaseDAO;


public interface VEquipmentConfigDao<T, ID> extends CssBaseDAO<T, ID> {
	
	/**
	 * 根据设备序列号查询设备当前配置列表,只查一级模块。一级模块再带出子模块。
	 * @param serialNumber
	 * @return
	 */
	public List<VEquipmentConfig> getEquipmentConfigByEquipmentSerialNumber(String serialNumber);
	
	
	public List<VEquipmentConfig> getEquipmentConfigForCheck(String serialNumber);

}
