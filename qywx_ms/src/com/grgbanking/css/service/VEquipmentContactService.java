package com.grgbanking.css.service;

import java.util.List;

import com.grgbanking.css.bean.vequipment.VEquipmentContactBean;

import net.sf.json.JSONObject;


public interface VEquipmentContactService {
	
	
	public List<VEquipmentContactBean> getEqtManager(Integer equipmentId);
	
	
	/**
	 * 是否拒收短信
	 * @param equipmentId
	 * @param mobliePhone
	 */
//	public boolean isRefuseSms(Integer equipmentId, String mobliePhone);
//	
//	
//	/**
//	 * 根据设备得到联系人列表
//	 * 
//	 * @param equipmentId
//	 * @return
//	 */
//	public List<JSONObject> list(Integer equipmentId);
//	
//	
//	public boolean validate(EquipmentContactBean equipmentContactBean);
//	
//	public Integer validateMobliePhone(Integer equipmentId, String mobliePhone);
//	
//	public Integer validateTelephone(Integer equipmentId, String telephone);
//	
//	
//	/**
//	 * 新增或者修改
//	 * 
//	 * @param equipmentContact
//	 * @return
//	 */
//	public void doSaveOrUpdate(EquipmentContactBean equipmentContactBean);
//	
//	public EquipmentContactBean getById(Integer equipmentContactId);


}
