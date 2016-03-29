package com.grgbanking.core.entity.equipment;

import java.util.List;


public class WsEquipmentImageBean {

	private List<CssPhoneImageBean> phoneImageBeanList;

	private List<byte[]> files;
	
	private String returnResult;//false-失败，true-成功
	private String msg;

	public List<CssPhoneImageBean> getPhoneImageBeanList() {
		return phoneImageBeanList;
	}

	public void setPhoneImageBeanList(List<CssPhoneImageBean> phoneImageBeanList) {
		this.phoneImageBeanList = phoneImageBeanList;
	}
	
	public List<byte[]> getFiles() {
		return files;
	}

	public void setFiles(List<byte[]> files) {
		this.files = files;
	}

	public String getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
