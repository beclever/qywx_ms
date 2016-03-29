package com.grgbanking.css.dao.vequipment;

import java.util.List;

import com.grgbanking.css.bean.vequipment.VEquipmentContact;
import com.grgbanking.css.common.CssBaseDAO;


public interface VEquipmentContactDAO<T, ID> extends CssBaseDAO<T, ID> {
	
	public List<VEquipmentContact> getEqtManager(Integer equipmentId);
	
//	public List<VEquipmentContact> getByBean(VEquipmentContactBean bean);
//	
//	public List<VEquipmentContact> list(Integer equipmentId);
//	
//	public Integer validate(EquipmentContactBean equipmentContactBean);
//	
//	public Integer validateMobliePhone(Integer equipmentId, String mobliePhone);
//	public Integer validateTelephone(Integer equipmentId, String telephone);

}
