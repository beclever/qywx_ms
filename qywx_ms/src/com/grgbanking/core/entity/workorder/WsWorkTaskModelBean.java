package com.grgbanking.core.entity.workorder;
import java.util.List;

public class WsWorkTaskModelBean {

	private List<WsWorkTaskReplaceBean> importModel;// Array(Y) 主要模块替换

	private List<WsWorkTaskReplaceBean> subModel;// Array(Y) 零备件替换

	private List<WsWorkTaskRepairPlaceBean> deviceHitch;// Array(Y) 故障部位

	private WsSjTaskReplaceBean beforSjModel;// Array(Y) 升级前模块

	private WsSjTaskReplaceBean afterSjModel;// Array(Y) 升级后模块

	public List<WsWorkTaskReplaceBean> getImportModel() {
		return importModel;
	}

	public void setImportModel(List<WsWorkTaskReplaceBean> importModel) {
		this.importModel = importModel;
	}

	public List<WsWorkTaskReplaceBean> getSubModel() {
		return subModel;
	}

	public void setSubModel(List<WsWorkTaskReplaceBean> subModel) {
		this.subModel = subModel;
	}

	public List<WsWorkTaskRepairPlaceBean> getDeviceHitch() {
		return deviceHitch;
	}

	public void setDeviceHitch(List<WsWorkTaskRepairPlaceBean> deviceHitch) {
		this.deviceHitch = deviceHitch;
	}

	public WsSjTaskReplaceBean getBeforSjModel() {
		return beforSjModel;
	}

	public void setBeforSjModel(WsSjTaskReplaceBean beforSjModel) {
		this.beforSjModel = beforSjModel;
	}

	public WsSjTaskReplaceBean getAfterSjModel() {
		return afterSjModel;
	}

	public void setAfterSjModel(WsSjTaskReplaceBean afterSjModel) {
		this.afterSjModel = afterSjModel;
	}

	

}
