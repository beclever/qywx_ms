package com.grgbanking.css.dao.vequipment.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.vequipment.VEquipmentContact;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.vequipment.VEquipmentContactDAO;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.HqlHelper;


@Repository("vEquipmentContactDAO")
public class VEquipmentContactDAOImpl extends CssBaseDAOImpl<VEquipmentContact,Integer> implements
		VEquipmentContactDAO<VEquipmentContact,Integer> {

	public List<VEquipmentContact> getEqtManager(Integer equipmentId) {
		HqlHelper hqlHelper = new HqlHelper(VEquipmentContact.class);
		hqlHelper.addEqual("vequipmentId", equipmentId);
		hqlHelper.addEqual("isManager", CommonConstants.FLAG_Y);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addOrderBy("createDate", "desc");
		return find(hqlHelper);
	}
	
	
//	public List<VEquipmentContact> getByBean(VEquipmentContactBean bean) {
//		HqlHelper hqlHelper = new HqlHelper(VEquipmentContact.class);
//		hqlHelper.addEqual("vequipmentId", bean.getEquipmentId());
//		hqlHelper.addEqual("isManager", bean.getIsManager());
//		hqlHelper.addEqual("isRefuseSms", bean.getIsRefuseSms());
//		hqlHelper.addLike("contactName", bean.getContactName());
//		hqlHelper.addLike("mobliePhone", bean.getMobliePhone());
//		hqlHelper.addEqual("telephone", bean.getTelephone());
//		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
//		return find(hqlHelper);
//	}
//
//
//	public List<VEquipmentContact> list(Integer equipmentId) {
//		HqlHelper hqlHelper = new HqlHelper(VEquipmentContact.class);
//		hqlHelper.addEqual("vequipmentId", equipmentId);
//		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
//		return find(hqlHelper);
//	}
//
//
//	public Integer validate(EquipmentContactBean equipmentContactBean) {
//		String sql = "select count(1) from  v_ems_equipment_contact t where t.deleted = 'N' and  t.equipment_id= "
//			+ equipmentContactBean.getEquipmentId();
//	if (StringUtilsExtends.isNotBlankAndEmpty(equipmentContactBean.getMobliePhone())) {
//		sql += " and t.moblie_phone='" + equipmentContactBean.getMobliePhone() + "'";
//	}
//	if (StringUtilsExtends.isNotBlankAndEmpty(equipmentContactBean.getTelephone())) {
//		sql += " and t.telephone='" + equipmentContactBean.getTelephone() + "'";
//	}
//	if (null != equipmentContactBean.getEquipmentContactId()) {
//		sql += " and t.equipment_contact_id <> " + equipmentContactBean.getEquipmentContactId();
//	}
//
//	return this.countBySQL(sql);
//	}
//
//
//	public Integer validateMobliePhone(Integer equipmentId, String mobliePhone) {
//		String sql = "select count(1) from  v_ems_equipment_contact t where t.deleted = 'N' and  t.equipment_id= "
//			+ equipmentId + " and t.moblie_phone='" + mobliePhone + "'";
//	return this.countBySQL(sql);
//	}
//
//
//	public Integer validateTelephone(Integer equipmentId, String telephone) {
//		String sql = "select count(1) from  v_ems_equipment_contact t where t.deleted = 'N' and  t.equipment_id= "
//			+ equipmentId + " and t.telephone='" + telephone + "'";
//		return this.countBySQL(sql);
//	}
//
}
