package com.grgbanking.webservice.service;

import javax.jws.WebService;

import com.grgbanking.core.entity.ws.WsResultBean;

@WebService
public interface EmsEquipmentContactWsService {
	
	
	/** 2
	 * 新增设备联系人
	 * @param loginName 
	 * @param jsonstr 元素:equipmentId,contactName,mobliePhone,
	 * telephone,positionVal,sex,isManager,isRefuseSms,remark
	 * @param clientType 请求发送端类型：PHONE、CSS、MA等
	 * @return
	 */
	public WsResultBean doSaveEquipmentContact(String loginName,String jsonstr,String clientType);
	
	/**
	 * 修改设备联系人
	 * @param loginName
	 * @param jsonstr 元素:equipmentContactId,equipmentId,contactName,
	 * mobliePhone,telephone,positionVal,sex,isManager,isRefuseSms,remark
	 * @param clientType
	 * @return
	 */
	public WsResultBean doUpdateEquipmentContact(String loginName,String jsonstr,String clientType);
	
	/**
	 * 删除设备联系人
	 * @param loginName
	 * @param equipmentContactIds 数组联系人ID
	 * @param clientType
	 * @return
	 */
	public WsResultBean doDeteleEquipmentContact(String loginName,Long[] equipmentContactIds,String clientType);

}
