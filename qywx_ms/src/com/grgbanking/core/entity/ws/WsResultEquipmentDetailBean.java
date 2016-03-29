package com.grgbanking.core.entity.ws;

import com.grgbanking.core.entity.equipment.WsEquipmentInfoDetailBean;



/**
 * 	返回设备详细信息
 * @author yt
 *
 */
public class WsResultEquipmentDetailBean {

	
	
	private Integer status;// int(Y) 1：成功。0：失败。
	private String errMsg;// String(Y) 提示错误信息
	private WsEquipmentInfoDetailBean wsEquipmentInfoDetailBean;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public WsEquipmentInfoDetailBean getWsEquipmentInfoDetailBean() {
		return wsEquipmentInfoDetailBean;
	}
	public void setWsEquipmentInfoDetailBean(
			WsEquipmentInfoDetailBean wsEquipmentInfoDetailBean) {
		this.wsEquipmentInfoDetailBean = wsEquipmentInfoDetailBean;
	}
	
}
