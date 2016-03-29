package com.grgbanking.css.dao.vequipment.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.vequipment.VEquipmentConfig;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.vequipment.VEquipmentConfigDao;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.HqlHelper;


@Repository("vEquipmentConfigDao")
public class VEquipmentConfigDaoImpl extends CssBaseDAOImpl<VEquipmentConfig,Integer> implements
		VEquipmentConfigDao<VEquipmentConfig,Integer> {

	public List<VEquipmentConfig> getEquipmentConfigByEquipmentSerialNumber(
			String serialNumber) {
		HqlHelper hqlHelper=new HqlHelper(VEquipmentConfig.class);
		hqlHelper.addEqual("equipment.serialNumber", serialNumber);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addIsNull("parent");
		return this.find(hqlHelper);
	}

	public List<VEquipmentConfig> getEquipmentConfigForCheck(String serialNumber) {
		HqlHelper hqlHelper=new HqlHelper(VEquipmentConfig.class);
		hqlHelper.addEqual("equipment.serialNumber", serialNumber);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		return this.find(hqlHelper);
	}

}
