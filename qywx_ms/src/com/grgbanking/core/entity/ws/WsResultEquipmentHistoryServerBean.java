package com.grgbanking.core.entity.ws;

import java.util.List;

import com.grgbanking.core.entity.equipment.WsHistoryServerInfoBean;



/**
 * 设备历史服务信息(信息)
 * @author yt
 *
 */
public class WsResultEquipmentHistoryServerBean {

	
	private Integer status;// int(Y) 1：成功。0：失败。
	private String errMsg;// String(Y) 提示错误信息
	private List<WsHistoryServerInfoBean> historyServerInfo;
	
	
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
	public List<WsHistoryServerInfoBean> getHistoryServerInfo() {
		return historyServerInfo;
	}
	public void setHistoryServerInfo(List<WsHistoryServerInfoBean> historyServerInfo) {
		this.historyServerInfo = historyServerInfo;
	}
	
	
	
	
}
