package com.grgbanking.webservice.service;

import javax.jws.WebService;

import com.grgbanking.core.entity.equipment.WsEquipmentInfoSaveBean;
import com.grgbanking.webservice.bean.WsResultBean;

/**
 * 	设备查询及查看详细信息接口
 * @author yt
 *
 */
@WebService
public interface EmsEquipmentWsService {
	
	//设备查询及查看详细信息接口
	//public WsResultEquipmentBean finddevices(String userId,String serialNumber,String branchName,String installAddress,String ATMNumber,WsPage page);
	
	//查看详细设备信息
	//public WsResultEquipmentDetailBean getDeviceDetail(String userId,String serialNumber);
	
   //查看设备配置信息接口
	//public WsResultEquipmentConfigBean getDeviceSparepartsInfo(String loginCode,String serialNumber);
	
	
	//历史服务信息
	//public WsResultEquipmentHistoryServerBean getDeviceServiceHistoryInfo(String loginCode,String serialNumber);
	
	
	//设备历史遗留问题
	//public WsResultEquipmentHistoryProblemBean getDeviceLegacyProblemInfo(String loginCode,String serialNumber);

	//保存设备配置详细信息//css客服已经不提供这个接口
	//public WsResultBean doUpdateEquipmentInfo(WsEquipmentInfoSaveBean wsEquipment);
	//修改设备档案接口
		//接口改造后代码
	public WsResultBean doUpdateEquipmentInfo(String loginName,WsEquipmentInfoSaveBean wsEquipmentInfoSaveBean,String clientType);
		//接口改造前代码
	//public WsResultBean doUpdateEquipmentInfo(WsEquipmentInfoSaveBean wsEquipmentInfoSaveBean);
	/**
	 * 取得设备联系人
	 * @param serialNumber
	 * @return
	 */
	//public String getEquipmentContact(String serialNumber);

}
