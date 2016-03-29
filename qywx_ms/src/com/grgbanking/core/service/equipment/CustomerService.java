package com.grgbanking.core.service.equipment;

import com.grgbanking.css.bean.equipment.EquipmentContact;

public interface CustomerService {

	public String doCreateCustomer(String loginName,
			EquipmentContact equipmentContact);

	public String updateByPrimaryKey(String loginName,
			EquipmentContact equipmentContact);

	public String doDeleteCustomer(String loginName, String equipmentcontactId);
}
