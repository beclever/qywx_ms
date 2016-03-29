package com.grgbanking.webservice.equipment;

import javax.jws.WebService;

import com.grgbanking.core.entity.equipment.WsEquipmentInfoSaveBean;
import com.grgbanking.core.entity.equipment.WsResultEquipmentBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultEquipmentConfigBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentDetailBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryProblemBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryServerBean;
import com.grgbanking.webservice.bean.WsResultBean;

@WebService
public interface SocEquipmentWsService {
	
	//5.2.1.	设备查询及查看详细信息接口
	public WsResultEquipmentBean finddevices(String loginCode,String serialNumber,String branchName,String installAddress,String ATMNumber,WsPage page);
	
	//5.2.2.	查看设备详细信息接口
	public WsResultEquipmentDetailBean getDeviceDetail(String loginCode,String serialNumber);
	
	//5.2.3.	查看设备配置信息接口
	public WsResultEquipmentConfigBean getDeviceSparepartsInfo(String loginCode,String serialNumber);
	
	//历史服务信息
	public WsResultEquipmentHistoryServerBean getDeviceServiceHistoryInfo(String loginCode,String serialNumber);
	
	//设备历史遗留问题
	public WsResultEquipmentHistoryProblemBean getDeviceLegacyProblemInfo(String loginCode,String serialNumber);
	
	//修改设备档案接口
	public WsResultBean doUpdateEquipmentInfo(WsEquipmentInfoSaveBean wsEquipmentInfoSaveBean);
   
	//保存
	public String doSaveEquipmentContact(Integer equipmentId,String contactName,String mobliePhone,String createUserCode);

	
	//批量修改设备责任人
	/**
	 * [{serialNumber:1111,userCode:'GZ2225'}]
	 */
	public WsResultBean doUpdateEquipmentInfoDutyMan(String loginCode,String jsonList);
	
	/**
	 * 取得设备联系人
	 * @param serialNumber
	 * @return
	 */
	public String getEquipmentContact(String serialNumber);
	
	/**
	 * 更新字段：设备状态更改为“在用”的最有一次修改的时间
	 * @Title: doUpdateEquipmentInfoLastUsingDate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param equipmentId
	 * @return
	 */
	public WsResultBean doUpdateEquipmentInfoLastUsingDate(Integer equipmentId);

	/**
	 * 根据参数更新设备信息信息
	 * @param jsonString {"longitude":"15.551","latitude":"52.24","modifyUserId":"255","equipmentId":"255"}
	 * @param updateType 更新类型 0-更新经纬度 1-更新设备负责人和电话
	 * @return WsResultBean
	 */
	public WsResultBean doUpdateEquipmentByParam(String jsonString, int updateType);

}