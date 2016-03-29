package com.grgbanking.css.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.grgbanking.common.utils.ArrayTransitionUtils;
import com.grgbanking.common.utils.StringUtilsExtends;
import com.grgbanking.css.bean.Department;
import com.grgbanking.css.bean.equipment.EquipmentContact;
import com.grgbanking.css.bean.equipment.EquipmentContactBean;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.dao.DepartmentDAO;
import com.grgbanking.css.dao.EquipmentContactDAO;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.HqlHelper;

@Repository("equipmentContactDAO")
public class EquipmentContactDAOImpl extends
		CssBaseDAOImpl<EquipmentContact, Integer> implements
		EquipmentContactDAO<EquipmentContact, Integer> {
	@Autowired
	private DepartmentDAO<Department, Integer> departmentDAO;

	public List<EquipmentContact> list(Integer equipmentId) {
		HqlHelper hqlHelper = new HqlHelper(EquipmentContact.class);
		hqlHelper.addEqual("equipment.equipmentId", equipmentId);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		return find(hqlHelper);
	}

	public EquipmentContact getEquipmentContactManager(Integer equipmentId) {
		List<EquipmentContact> list = list(equipmentId);
		if (list != null && list.size() > 0) {
			for (EquipmentContact contact : list) {
				if (contact.getIsManager().equals(CommonConstants.FLAG_Y)) {
					return contact;
				}
			}
		}
		return null;
	}

	/*
	 * public void doUpdateContactIsManager(Integer equipmentId, String
	 * mobliePhone) { if (null != equipmentId &&
	 * StringUtilsExtends.isNotBlankAndEmpty(mobliePhone)) { String sql =
	 * "update V_EMS_EQUIPMENT_CONTACT t set t.is_manager='Y', create_date = sysdate where t.equipment_id ="
	 * + equipmentId + " and t.moblie_phone = '" + mobliePhone + "' ";
	 * this.executeSql(sql); } }
	 */

	public Integer validate(EquipmentContactBean equipmentContactBean) {
		String sql = "select count(1) from  V_EMS_EQUIPMENT_CONTACT t where t.deleted = 'N' and  t.equipment_id= "
				+ equipmentContactBean.getEquipmentId();
		if (StringUtilsExtends.isNotBlankAndEmpty(equipmentContactBean
				.getMobliePhone())) {
			sql += " and t.moblie_phone='"
					+ equipmentContactBean.getMobliePhone() + "'";
		}
		if (StringUtilsExtends.isNotBlankAndEmpty(equipmentContactBean
				.getTelephone())) {
			sql += " and t.telephone='" + equipmentContactBean.getTelephone()
					+ "'";
		}
		if (null != equipmentContactBean.getEquipmentContactId()) {
			sql += " and t.equipment_contact_id <> "
					+ equipmentContactBean.getEquipmentContactId();
		}

		return this.countBySQL(sql);
	}

	/*
	 * public void saveCtrlLog(StringBuffer sb, Integer equipmentId) {
	 * CssUserBean user = UserContext.getUser(); String sql =
	 * " insert into V_equipment_info_log(equipment_log_id,equipment_id,content,reference_no,user_id,user_name,create_date) "
	 * + " values(seq_equipment_info_log.nextval," + equipmentId + ",'" +
	 * sb.toString() + "',null," + user.getUserId() + ",'" + user.getName() +
	 * "',sysdate) "; this.executeSql(sql); }
	 */

	public Integer validateEquipmentId(Integer equipmentId) {
		String sql = "select count(1) from V_EMS_EQUIPMENT_CONTACT t where t.deleted = 'N' and  t.equipment_id= "
				+ equipmentId + " and t.is_manager = 'Y'";
		return this.countBySQL(sql);
	}

	public Integer validateMobliePhone(Integer equipmentId, String mobliePhone) {
		String sql = "select count(1) from V_EMS_EQUIPMENT_CONTACT t where t.deleted = 'N' and  t.equipment_id= "
				+ equipmentId + " and t.moblie_phone='" + mobliePhone + "'";
		return this.countBySQL(sql);
	}

	public Integer validateTelephone(Integer equipmentId, String telephone) {
		String sql = "select count(1) from V_EMS_EQUIPMENT_CONTACT t where t.deleted = 'N' and  t.equipment_id= "
				+ equipmentId + " and t.telephone='" + telephone + "'";
		return this.countBySQL(sql);
	}

	public Integer validateContactName(Integer equipmentId, String contactName) {
		String sql = "select count(1) from V_EMS_EQUIPMENT_CONTACT t where t.deleted = 'N' and  t.equipment_id= "
				+ equipmentId + " and t.contact_name='" + contactName + "'";
		return this.countBySQL(sql);
	}

	public Integer validateLike7Telephone(Integer equipmentId, String telephone) {
		if (telephone.length() > 7) {
			String sql = "select count(1) from V_EMS_EQUIPMENT_CONTACT t where t.deleted = 'N' and  t.equipment_id= "
					+ equipmentId
					+ "and t.telephone like '%"
					+ telephone.substring(telephone.length() - 7,
							telephone.length()) + "'";
			return this.countBySQL(sql);
		}
		return 0;
	}

	public List<EquipmentContact> getEqtManager(Integer equipmentId) {
		HqlHelper hqlHelper = new HqlHelper(EquipmentContact.class);
		hqlHelper.addEqual("equipment.equipmentId", equipmentId);
		hqlHelper.addEqual("isManager", CommonConstants.FLAG_Y);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addOrderBy("createDate", "desc");
		return find(hqlHelper);
	}

	public List<EquipmentContact> manager(Page page, EquipmentContactBean bean) {

		HqlHelper hqlHelper = new HqlHelper(EquipmentContact.class);
		if (StringUtils.isNotBlank(bean.getSerialNumber()))
			hqlHelper.addIn("equipment.serialNumber", bean.getSerialNumber()
					.split(","));
		hqlHelper.addEqual("equipment.equipmentId", bean.getEquipmentId());

		if (bean.getDepartmentId() != null) {
			List<Integer> depIdList = departmentDAO.getChildDepartmentId(bean
					.getDepartmentId());
			hqlHelper.addIn("equipment.department.departmentId", depIdList);
			bean.setDepartmentId(null);
		}
		hqlHelper.addIn(
				"equipment.department.departmentId",
				ArrayTransitionUtils.stringToIntegerArray(
						bean.getDepartmentIds(), ","));
		hqlHelper.addLike("contactName", bean.getContactName());
		hqlHelper.addLike("mobliePhone", bean.getMobliePhone());
		hqlHelper.addEqual("telephone", bean.getTelephone());
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addIn("equipment.equipmentType",
				bean.getEquipmentType() != null ? bean.getEquipmentType()
						.split(",") : null);
		hqlHelper.addIn("equipment.equipmentModel",
				bean.getEquipmentModel() != null ? bean.getEquipmentModel()
						.split(",") : null);
		hqlHelper.addIn("equipment.equipmentStatus",
				bean.getEquipmentStatus() != null ? bean.getEquipmentStatus()
						.split(",") : null);
		hqlHelper.addIn(
				"equipment.saleProperty",
				bean.getSaleProperty() != null ? bean.getSaleProperty().split(
						",") : null);
		hqlHelper.addIn("equipment.warrantyStatus",
				bean.getWarrantyStatus() != null ? bean.getWarrantyStatus()
						.split(",") : null);
		hqlHelper.addOrderBy("equipment.department.departmentId", "asc");
		hqlHelper.setQueryPage(page);
		return find(hqlHelper);
	}

	@Override
	public List<EquipmentContact> getEquipmentContact(Integer equipmentId) {
		HqlHelper hqlHelper = new HqlHelper(EquipmentContact.class);
		hqlHelper.addEqual("equipment.equipmentId", equipmentId);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		return find(hqlHelper);
	}

	@Override
	public List<EquipmentContact> getByBean(EquipmentContactBean bean) {
		HqlHelper hqlHelper = new HqlHelper(EquipmentContact.class);
		hqlHelper.addEqual("equipment.equipmentId", bean.getEquipmentId());
		hqlHelper.addEqual("isManager", bean.getIsManager());
		hqlHelper.addEqual("isRefuseSms", bean.getIsRefuseSms());
		hqlHelper.addLike("contactName", bean.getContactName());
		hqlHelper.addLike("mobliePhone", bean.getMobliePhone());
		hqlHelper.addEqual("telephone", bean.getTelephone());
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		return find(hqlHelper);
	}

	@Override
	public EquipmentContact getEquipmentContactByMobliePhone(
			Integer equipmentId, String mobliePhone) {
		List<EquipmentContact> list = list(equipmentId);
		if (list != null && list.size() > 0) {
			for (EquipmentContact contact : list) {
				if (null != contact.getMobliePhone()
						&& contact.getMobliePhone().equals(mobliePhone)) {
					return contact;
				}
			}
		}
		return null;
	}

	@Override
	public EquipmentContact getEquipmentContactByContactName(
			Integer equipmentId, String contactName) {
		List<EquipmentContact> list = list(equipmentId);
		if (list != null && list.size() > 0) {
			for (EquipmentContact contact : list) {
				if (contact.getContactName().equals(contactName)) {
					return contact;
				}
			}
		}
		return null;
	}

	@Override
	public Integer validateContactId(Integer equipmentcontactId,
			boolean isManager) {
		EquipmentContact contact = this.get(equipmentcontactId);
		String sql = "select count(*) from V_EMS_EQUIPMENT_CONTACT t where t.deleted = 'N' and  t.equipment_id= "
				+ contact.getEquipment().getEquipmentId();
		if (isManager) {
			sql += "and t.is_manager = 'Y'";
		}
		return this.countBySQL(sql);
	}

}
