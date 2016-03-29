package com.grgbanking.core.entity.ws;

public class WsResultUploadWorkFormTimeBean {
	private Integer status;// int(Y) 1：成功。0：失败。
	private String errMsg;// String(Y) 提示错误信息
	private String time;// String(Y) 服务器返回时间格式YYYY-MM-dd hh:mm:ss
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
