package com.grgbanking.core.entity.ws;

public class WsResultTotalPendingWorkBean {

	
	private Integer status;// 1：成功。0：失败。
	private String errMsg;// 提示错误信息
	private Integer total;// int(Y) 工单总数
	
	
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
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	
	
}
