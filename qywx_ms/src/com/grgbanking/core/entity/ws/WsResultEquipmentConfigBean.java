package com.grgbanking.core.entity.ws;

import java.util.List;

import com.grgbanking.core.entity.equipment.WsEquipmentConfigBean;



//返回设备配置信息
public class WsResultEquipmentConfigBean {

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

	public List<WsEquipmentConfigBean> getSparepartsInfo() {
		return sparepartsInfo;
	}

	public void setSparepartsInfo(List<WsEquipmentConfigBean> sparepartsInfo) {
		this.sparepartsInfo = sparepartsInfo;
	}

	private Integer status;// int(Y) 1：成功。0：失败。
	private String errMsg;// String(Y) 提示错误信息

	private List<WsEquipmentConfigBean> sparepartsInfo;
	
}
