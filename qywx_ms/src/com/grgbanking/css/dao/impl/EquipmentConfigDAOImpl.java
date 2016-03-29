package com.grgbanking.css.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.grgbanking.common.utils.StringUtilsExtends;
import com.grgbanking.css.bean.Department;
import com.grgbanking.css.bean.equipment.EquipmentConfig;
import com.grgbanking.css.bean.equipment.EquipmentConfigBean;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.dao.DepartmentDAO;
import com.grgbanking.css.dao.EquipmentConfigDAO;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.CommonUtils;
import com.grgbanking.css.util.HqlHelper;

/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-7-23 下午03:26:57
 */
@Repository("equipmentConfigDAO")
public class EquipmentConfigDAOImpl extends CssBaseDAOImpl<EquipmentConfig, Integer> implements
		EquipmentConfigDAO<EquipmentConfig, Integer> {
	
	@Autowired
	private DepartmentDAO<Department, Integer> departmentDAO;
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentConfigDAO#getEquipmentConfigByEquipmentSerialNumber(java.lang.String)
	 */
	public List<EquipmentConfig> getEquipmentConfigByEquipmentSerialNumber(String serialNumber) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentConfig.class);
		hqlHelper.addEqual("equipment.serialNumber", serialNumber);
		hqlHelper.addIsNull("parent");
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentConfigDAO#getEquipmentConfigList(com.grgbanking.framework.core.Page, com.grgbanking.soc.bean.equipment.EquipmentConfigBean)
	 */
	public List<EquipmentConfig> getEquipmentConfigList(Page queryPage,
			EquipmentConfigBean equipmentConfigBean) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentConfig.class);
		//设备序列号多选查询，与单选的模糊查询
		if(StringUtils.isNotBlank(equipmentConfigBean.getEquipmentSerialNumber())){
			String[] serialNumList = equipmentConfigBean.getEquipmentSerialNumber().split(",");
			if(serialNumList.length>1){
				hqlHelper.addIn("equipment.serialNumber", equipmentConfigBean.getEquipmentSerialNumber()!=null?serialNumList:null);//设备序列号
			}else{
				hqlHelper.addEqual("equipment.serialNumber", equipmentConfigBean.getEquipmentSerialNumber());
			}
		}else{
			hqlHelper.addEqual("equipment.serialNumber", null);
		}
		hqlHelper.addIn("equipment.equipmentStatus", equipmentConfigBean.getEquipmentStatus()!=null?equipmentConfigBean.getEquipmentStatus().split(","):null);
		hqlHelper.addDateBetween("equipment.installDate", equipmentConfigBean.getBeginInstallDate(), equipmentConfigBean.getEndInstallDate());//安装日期
		hqlHelper.addLikeIgnoreCase("serialNumber", CommonUtils.removeBlankSpace(equipmentConfigBean.getSerialNumber()));
		hqlHelper.addIn("sparepart.moduleType", equipmentConfigBean.getModuleType()!=null?equipmentConfigBean.getModuleType().split(","):null);//模块类型：多选
		hqlHelper.addLikeIgnoreCase("sparepart.materialCode", equipmentConfigBean.getMaterialSerial());
		hqlHelper.addLikeIgnoreCase("sparepart.materialName", equipmentConfigBean.getMaterialName());
		//服务站多选
		List<Integer> departlist = new ArrayList<Integer>();
		
		if(StringUtils.isNotBlank(equipmentConfigBean.getRegionDeptIds())){
			String[] ids = equipmentConfigBean.getRegionDeptIds().split(",");
			if(ids.length>0){
				for(String id:ids){
					if (StringUtils.isNotBlank(id)) {
						departlist.add(Integer.valueOf(id));
					}
				}
			}
		}
		hqlHelper.addIn("equipment.department.departmentId", departlist);
		if(equipmentConfigBean.getDepartmentId()!=null){
			hqlHelper.addIn("equipment.department.departmentId", this.getChildDepartmentId(equipmentConfigBean.getDepartmentId()));
		}
		//客户多选
		if(StringUtils.isNotBlank(equipmentConfigBean.getCustomerIds())){
			String[] customerIdArray =equipmentConfigBean.getCustomerIds().split(",");
			List<Integer> customerIdList=new ArrayList<Integer>();
			for (String string : customerIdArray) {
				if(StringUtils.isNotBlank(string)){
					customerIdList.add(Integer.valueOf(string));
				}
			}
			hqlHelper.addIn("equipment.customer.customerId",customerIdList);//多选客户
		}
		hqlHelper.addEqual("equipment.provinceId", equipmentConfigBean.getProvince());
		hqlHelper.addEqual("equipment.cityId", equipmentConfigBean.getCity());
		hqlHelper.addEqual("sparepart.materialModel", equipmentConfigBean.getMaterialModel());
		hqlHelper.addIn("sparepart.moduleLevel",equipmentConfigBean.getModuleLevel()!=null?equipmentConfigBean.getModuleLevel().split(","):null);
		hqlHelper.addEqual("sparepart.manufacturer",equipmentConfigBean.getManufacturer());
		hqlHelper.addEqual("sparepart.materialCode",equipmentConfigBean.getMaterialCode());
		hqlHelper.addEqual("equipment.department.departmentName", equipmentConfigBean.getDepartmentName());
		hqlHelper.setQueryPage(queryPage);
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentConfigDAO#getEquipmentConfigForCheck(java.lang.String)
	 */
	public List<EquipmentConfig> getEquipmentConfigForCheck(String serialNumber) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentConfig.class);
		hqlHelper.addEqual("equipment.serialNumber", serialNumber);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentConfigDAO#getEquipmentConfigByEquipmentSerialNumberForEdit(java.lang.String)
	 */
	public List<EquipmentConfig> getEquipmentConfigByEquipmentSerialNumberForEdit(String serialNumber) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentConfig.class);
		hqlHelper.addEqual("serialNumber", serialNumber);
		hqlHelper.addIsNull("parent");
		return this.find(hqlHelper);
	}
	
	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentConfigDAO#getEquipmentConfigBySerialNumber(java.lang.String)
	 */
	public EquipmentConfig getEquipmentConfigBySerialNumber(String serialNumber) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentConfig.class);
		hqlHelper.addEqual("serialNumber", serialNumber);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		List<EquipmentConfig>  list = this.find(hqlHelper);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentConfigDAO#getAllConfigByEquipment(java.lang.Integer)
	 */
	public List<EquipmentConfig> getAllConfigByEquipment(Integer equipmentId) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentConfig.class);
		hqlHelper.addEqual("equipment.equipmentId", equipmentId);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentConfigDAO#getEQConfigBySerialNumber(java.lang.String)
	 */
	public Integer getEQConfigBySerialNumber(String serialNumber) {
		String sql = " select count(*) from V_equipment_config t where t.serial_number='"+serialNumber+"' ";
		return this.countBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentConfigDAO#getEquipmengetSerialNumberLike(java.lang.String)
	 */
	public Integer getLikeEquipmengetSerialNumber(String serialNumber) {
		String sql = " select count(*) from V_EMS_EQUIPMENT_INFO t where t.serial_number like'"+serialNumber+"%' ";
		return this.countBySQL(sql);
	}

	public EquipmentConfig findConfigByRecordId(String recordId) {
		if(StringUtilsExtends.isBlankOrEmpty(recordId))
			return null;
		while(recordId.startsWith("0")) {
			recordId = recordId.replaceFirst("0", "");
		}
		HqlHelper hqlHelper=new HqlHelper(EquipmentConfig.class);
		hqlHelper.addEqual("recordId", Long.parseLong(recordId));
		return listFirstResult(this.find(hqlHelper));
	}

	public EquipmentConfig getEquipmentConfigByEquipmentId(Integer equipmentId,
			String moduleType) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentConfig.class);
		hqlHelper.addEqual("equipment.equipmentId", equipmentId);
		hqlHelper.addEqual("sparepart.moduleType", moduleType);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addOrderBy("parent", "desc");//默认取第一级
		List<EquipmentConfig>  list = this.find(hqlHelper);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
