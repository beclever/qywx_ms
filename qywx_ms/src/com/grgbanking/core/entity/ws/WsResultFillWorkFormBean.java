package com.grgbanking.core.entity.ws;

import com.grgbanking.core.entity.workorder.WsPendingWorkformDetailBean;


public class WsResultFillWorkFormBean {

	
	private Integer status;// 1：成功。0：失败。
	private String errMsg;// 提示错误信息

	private WsPendingWorkformDetailBean wsPendingWorkformDetailBean;

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

	public WsPendingWorkformDetailBean getWsPendingWorkformDetailBean() {
		return wsPendingWorkformDetailBean;
	}

	public void setWsPendingWorkformDetailBean(
			WsPendingWorkformDetailBean wsPendingWorkformDetailBean) {
		this.wsPendingWorkformDetailBean = wsPendingWorkformDetailBean;
	}
	
	
}
