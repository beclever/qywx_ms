package com.grgbanking.core.service.equipment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.entity.equipment.WsEquipmentInfoSaveBean;
import com.grgbanking.core.entity.equipment.WsResultEquipmentBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultEquipmentConfigBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentDetailBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryProblemBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryServerBean;
import com.grgbanking.core.service.WebService;
import com.grgbanking.core.service.equipment.WebEquipmentService;
import com.grgbanking.css.service.CssEquipmentWsService;
import com.grgbanking.webservice.bean.WsResultBean;
import com.grgbanking.webservice.service.EmsEquipmentWsService;

@Service("webEquipmentService")
public class WebEquipmentServiceImp extends WebService<EmsEquipmentWsService>
		implements WebEquipmentService {

	@Autowired
	private CssEquipmentWsService cssEquipmentWsService;

	public WebEquipmentServiceImp() {
		super(EmsEquipmentWsService.class, WsConsts.SocEquipmentWsServiceUrl);
	}

	public WsResultEquipmentBean finddevices(String userId,
			String serialNumber, String customerName, String branchName,
			String installAddress, String ATMNumber, WsPage page) {
		WsResultEquipmentBean result = cssEquipmentWsService.finddevices(
				userId, serialNumber, customerName, branchName, installAddress,
				ATMNumber, page);
		return result;
	}

	public WsResultEquipmentDetailBean getDeviceDetail(String userId,
			String serialNumber) {
		WsResultEquipmentDetailBean result = cssEquipmentWsService
				.getDeviceDetail(userId, serialNumber);
		return result;
	}

	public WsResultEquipmentConfigBean getDeviceSparepartsInfo(
			String loginCode, String serialNumber) {
		WsResultEquipmentConfigBean result = cssEquipmentWsService
				.getDeviceSparepartsInfo(loginCode, serialNumber);
		return result;
	}

	public WsResultEquipmentHistoryProblemBean getDeviceHistoryProblem(
			String serialNumber) {
		WsResultEquipmentHistoryProblemBean result = cssEquipmentWsService
				.getDeviceLegacyProblemInfo("", serialNumber);
		return result;
	}

	public WsResultEquipmentHistoryServerBean getDeviceHistoryServer(
			String serialNumber) {
		WsResultEquipmentHistoryServerBean results = cssEquipmentWsService
				.getDeviceServiceHistoryInfo("", serialNumber);
		return results;
	}

	// 保存设备配置详细信息
	// 接口改造后的代码
	public WsResultBean doUpdateEquipmentInfo(String loginName,
			WsEquipmentInfoSaveBean wsEquipmentInfoSaveBean, String clientType) {
		WsResultBean results = service.doUpdateEquipmentInfo(loginName,
				wsEquipmentInfoSaveBean, clientType);
		return results;
	}

	// 接口改造前代码
	/*
	 * public WsResultBean doUpdateEquipmentInfo(WsEquipmentInfoSaveBean
	 * wsEquipmentInfoSaveBean) { WsResultBean results =
	 * service.doUpdateEquipmentInfo(wsEquipmentInfoSaveBean); return results; }
	 */

	/**
	 * 取得设备联系人
	 * 
	 * @param serialNumber
	 * @return
	 */
	public String getEquipmentContact(String serialNumber) {
		return cssEquipmentWsService.getEquipmentContact(serialNumber);
	}

}
