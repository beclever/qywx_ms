package com.grgbanking.css.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.grgbanking.css.bean.equipment.EquipmentContact;
import com.grgbanking.css.bean.equipment.EquipmentContactBean;


/**
 * 
 * @author wwbang1
 * @d2014-5-5
 */

public interface EquipmentContactService {

	/**
	 * 管理列表
	 * @param page
	 * @param bean
	 * @return
	 */
	//public List<JSONObject> manager(Page page, EquipmentContactBean bean);

	/**
	 * 根据设备得到联系人列表
	 * 
	 * @param equipmentId
	 * @return
	 */
	public List<JSONObject> list(Integer equipmentId);
	
	/**
	 * 根据设备得到联系人列表
	 * 
	 * @param equipmentId
	 * @return
	 */
	public List<EquipmentContact> listById(Integer equipmentId);

	/**
	 * 新增或者修改
	 * 
	 * @param equipmentContact
	 * @return
	 */
	//public void doSaveOrUpdate(EquipmentContactBean equipmentContactBean);

	/**
	 * 对外的webservice调用
	 * 
	 * @param equipmentId
	 * @param contactName
	 * @param mobliePhone
	 * @param createUserCode
	 */
	//public String doSave(Integer equipmentId, String contactName, String mobliePhone, String createUserCode);

	/**
	 * 删除
	 * @param equipmentContactIds
	 */
	//public void doDetele(Integer[] equipmentContactIds);

	/**
	 * 更新设备的联系人为设备管理员
	 * 
	 * @param equipmentId
	 * @param mobliePhone
	 */
	//public void doUpdateContactIsManager(Integer equipmentId, String mobliePhone);

	/**
	 * 
	 * @param equipmentContactId
	 * @return
	 */
	public EquipmentContactBean getById(Integer equipmentContactId);
	
	/**
	 * 
	 * @param equipmentContactId
	 * @return
	 */
	public EquipmentContact getEquipmentContactById(Integer equipmentContactId);
	
	/**
	 * 
	 * @param equipmentId
	 * @return
	 */
	public EquipmentContact getEquipmentContactManager(Integer equipmentId);
	
	/**
	 * 
	 * @param equipmentId
	 * @param mobliePhone
	 * @return
	 */
	public EquipmentContact getEquipmentContactByMobliePhone(Integer equipmentId, String mobliePhone);
	
	/**
	 * 
	 * @param equipmentId
	 * @param contactName
	 * @return
	 */
	public EquipmentContact getEquipmentContactByContactName(Integer equipmentId, String contactName);

	/**
	 * 
	 * @return
	 */
	//public boolean validate(EquipmentContactBean equipmentContactBean);

	/**
	 * 
	 * @param equipmentId
	 * @return
	 */
	public Integer validateEquipmentId(Integer equipmentId);
	
	/**
	 * 
	 * @param equipmentId
	 * @param mobliePhone
	 * @return
	 */
	public Integer validateMobliePhone(Integer equipmentId, String mobliePhone);

	/**
	 * 
	 * @param equipmentId
	 * @param telephone
	 * @return
	 */
	public Integer validateTelephone(Integer equipmentId, String telephone);
	
	/**
	 * 
	 * @param equipmentId
	 * @param contactName
	 * @return
	 */
	public Integer validateContactName(Integer equipmentId, String contactName);

	public Integer validateContactId(Integer equipmentcontactId, boolean isManager);
	
	/**
	 * 
	 * @param equipmentId
	 * @return
	 */
	//public List<EquipmentContactBean> getEqtManager(Integer equipmentId);
	
	/**
	 * 批量修改是否拒收短信
	 * @param equipmentContactIds
	 * @param isRefuseSms
	 */
	//public void doUpdateContactIsRefuseSms(String[] equipmentContactIds, String isRefuseSms);
	
	/**
	 * 批量导入修改是否拒收短信
	 * @param sheet
	 */
	//public void doImport(Sheet sheet);
	
	
	/**
	 * 是否拒收短信
	 * @param equipmentId
	 * @param mobliePhone
	 */
	//public boolean isRefuseSms(Integer equipmentId, String mobliePhone);
}
