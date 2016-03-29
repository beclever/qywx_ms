package com.grgbanking.css.service;

import com.grgbanking.core.entity.equipment.WsResultEquipmentBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultEquipmentConfigBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentDetailBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryProblemBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryServerBean;

/**
 * 设备查询及查看详细信息接口
 * 
 * @author yt
 * 
 */
public interface CssEquipmentWsService {

	// 设备查询及查看详细信息接口
	public WsResultEquipmentBean finddevices(String userId,
			String serialNumber, String customerName, String branchName,
			String installAddress, String ATMNumber, WsPage page);

	// 查看详细设备信息
	public WsResultEquipmentDetailBean getDeviceDetail(String userId,
			String serialNumber);

	// 查看设备配置信息接口
	public WsResultEquipmentConfigBean getDeviceSparepartsInfo(
			String loginCode, String serialNumber);

	// 历史服务信息
	public WsResultEquipmentHistoryServerBean getDeviceServiceHistoryInfo(
			String loginCode, String serialNumber);

	// 设备历史遗留问题
	public WsResultEquipmentHistoryProblemBean getDeviceLegacyProblemInfo(
			String loginCode, String serialNumber);

	// 保存设备配置详细信息
	// public WsResultBean doUpdateEquipmentInfo(WsEquipmentInfoSaveBean
	// wsEquipment);

	/**
	 * 取得设备联系人
	 * 
	 * @param serialNumber
	 * @return
	 */
	public String getEquipmentContact(String serialNumber);

}
