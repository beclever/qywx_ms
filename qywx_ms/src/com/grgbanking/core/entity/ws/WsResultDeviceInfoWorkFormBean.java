package com.grgbanking.core.entity.ws;

public class WsResultDeviceInfoWorkFormBean {

	
	private Integer status;// int(Y) 1：成功。0：失败。
	private String errMsg;// String(Y) 提示错误信息
	private WsDeviceInfoWorkformBean deviceInfo;
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
	public WsDeviceInfoWorkformBean getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(WsDeviceInfoWorkformBean deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
	
	
	
}
