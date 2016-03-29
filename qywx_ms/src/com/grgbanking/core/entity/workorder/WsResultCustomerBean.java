package com.grgbanking.core.entity.workorder;

import java.util.List;

public class WsResultCustomerBean {

	private Integer status;// int(Y) 1：成功。0：失败。
	private String errMsg;// String(Y) 提示错误信息
	private List<WsOptionsBean> customerList;

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

	public List<WsOptionsBean> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<WsOptionsBean> customerList) {
		this.customerList = customerList;
	}

}
