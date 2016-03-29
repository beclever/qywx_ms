package com.grgbanking.core.entity.ws;

import java.util.List;

import com.grgbanking.core.entity.equipment.WsEquipmentHistoryProblemBean;





/**
 * 
 * @author yt
 *历史遗留问题信息
 */
public class WsResultEquipmentHistoryProblemBean {

	private Integer status;// int(Y) 1：成功。0：失败。
	private String errMsg;// String(Y) 提示错误信息
	private List<WsEquipmentHistoryProblemBean> eHistoryProblem;
	
	
	
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
	public List<WsEquipmentHistoryProblemBean> geteHistoryProblem() {
		return eHistoryProblem;
	}
	public void seteHistoryProblem(
			List<WsEquipmentHistoryProblemBean> eHistoryProblem) {
		this.eHistoryProblem = eHistoryProblem;
	}
	
	
	

	
}
