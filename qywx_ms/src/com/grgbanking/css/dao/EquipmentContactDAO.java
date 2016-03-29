package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.bean.equipment.EquipmentContact;
import com.grgbanking.css.bean.equipment.EquipmentContactBean;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;

public interface EquipmentContactDAO<T, ID> extends CssBaseDAO<T, ID> {

	/**
	 * 管理列表
	 * 
	 * @param page
	 * @param bean
	 * @return
	 */
	public List<EquipmentContact> manager(Page page, EquipmentContactBean bean);

	/**
	 * 
	 * @param equipmentContactBean
	 * @return
	 */
	public List<EquipmentContact> list(Integer equipmentId);

	public EquipmentContact getEquipmentContactManager(Integer equipmentId);
	
	public EquipmentContact getEquipmentContactByMobliePhone(
			Integer equipmentId, String mobliePhone);

	public EquipmentContact getEquipmentContactByContactName(
			Integer equipmentId, String contactName);

	// public void doUpdateContactIsManager(Integer equipmentId, String
	// mobliePhone);

	public Integer validate(EquipmentContactBean equipmentContactBean);

	// public void saveCtrlLog(StringBuffer sb, Integer equipmentId);

	public Integer validateEquipmentId(Integer equipmentId);

	public Integer validateMobliePhone(Integer equipmentId, String mobliePhone);

	public Integer validateTelephone(Integer equipmentId, String telephone);

	public Integer validateContactName(Integer equipmentId, String contactName);
	
	public Integer validateContactId(Integer equipmentcontactId, boolean isManager);

	/**
	 * 验证后7后的固话是否有记录
	 * 
	 * @param equipmentId
	 * @param telephone
	 * @return
	 */
	public Integer validateLike7Telephone(Integer equipmentId, String telephone);

	public List<EquipmentContact> getEqtManager(Integer equipmentId);

	public List<EquipmentContact> getEquipmentContact(Integer equipmentId);

	public List<EquipmentContact> getByBean(EquipmentContactBean bean);
}
