package com.grgbanking.core.entity.ws;

import java.util.List;

import com.grgbanking.core.entity.workorder.WsPendingWorkformBean;


public class WsResultPendingWorkFormBean {

	
	private Integer status;// 1：成功。0：失败。
	private String errMsg;// 提示错误信息
	private WsPage wsPage;
	private List<WsPendingWorkformBean> pendingWorkform;// 待处理工单列表信息
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
	public WsPage getWsPage() {
		return wsPage;
	}
	public void setWsPage(WsPage wsPage) {
		this.wsPage = wsPage;
	}
	public List<WsPendingWorkformBean> getPendingWorkform() {
		return pendingWorkform;
	}
	public void setPendingWorkform(List<WsPendingWorkformBean> pendingWorkform) {
		this.pendingWorkform = pendingWorkform;
	}
	
	
	
	
}
