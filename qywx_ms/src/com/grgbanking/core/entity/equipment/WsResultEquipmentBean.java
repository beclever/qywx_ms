package com.grgbanking.core.entity.equipment;

import java.util.List;

import com.grgbanking.core.entity.ws.WsPage;



/**
 * date:2013.10.10
 * 接口返回信息
 * @author yt
 *
 */
public class WsResultEquipmentBean {

	
	private Integer status;// int(Y) 1：成功。0：失败。
	private String errMsg;// String(Y) 提示错误信息
	private WsPage page;// Page对象 分页提交及返回信息
	private List<WsEquipmentInfoBean> equipmentInfo;// Array（Y） 设备列表信息
	
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
	public WsPage getPage() {
		return page;
	}
	public void setPage(WsPage page) {
		this.page = page;
	}
	public List<WsEquipmentInfoBean> getEquipmentInfo() {
		return equipmentInfo;
	}
	public void setEquipmentInfo(List<WsEquipmentInfoBean> equipmentInfo) {
		this.equipmentInfo = equipmentInfo;
	}
	
}
