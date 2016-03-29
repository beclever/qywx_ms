package com.grgbanking.core.service.equipment;

import com.grgbanking.core.entity.equipment.WsEquipmentInfoSaveBean;
import com.grgbanking.core.entity.equipment.WsResultEquipmentBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultEquipmentConfigBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentDetailBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryProblemBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryServerBean;
import com.grgbanking.webservice.bean.WsResultBean;

/**
 * 设备管理(接口) 2013.10.9
 * 
 * @author yt modify:2013.10.30
 * 
 */

public interface WebEquipmentService {

	/**
	 * 设备查询
	 * 
	 * @param userId
	 * @param serialNumber
	 * @param branchName
	 * @param installAddress
	 * @param ATMNumber
	 * @param page
	 * @return
	 */
	public WsResultEquipmentBean finddevices(String userId,
			String serialNumber, String customerName, String branchName,
			String installAddress, String ATMNumber, WsPage page);

	// 设备详细信息
	public WsResultEquipmentDetailBean getDeviceDetail(String userId,
			String serialNumber);

	// 设备配置信息
	// 查看设备配置信息接口
	public WsResultEquipmentConfigBean getDeviceSparepartsInfo(
			String loginCode, String serialNumber);

	// 设备历史
	public WsResultEquipmentHistoryServerBean getDeviceHistoryServer(
			String serialNumber);

	// 设备历史遗留问题
	public WsResultEquipmentHistoryProblemBean getDeviceHistoryProblem(
			String serialNumber);

	// 保存设备配置详细信息
	// public WsResultBean doUpdateEquipmentInfo(WsEquipmentInfoSaveBean
	// wsEquipment);
	public WsResultBean doUpdateEquipmentInfo(String loginName,
			WsEquipmentInfoSaveBean wsEquipmentInfoSaveBean, String clientType);

	/**
	 * 取得设备联系人
	 * 
	 * @param serialNumber
	 * @return
	 */
	public String getEquipmentContact(String serialNumber);
}
