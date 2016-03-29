package com.grgbanking.core.entity.ws;

import java.util.List;

import com.grgbanking.core.entity.workorder.WsWorkFormReplaceBean;




/**
 * 原模块 
 * @author yt
 *
 */
public class WsResultWorkFormModuleReplaceOldBean {

	private Integer status;// 1：成功。0：失败。
	private String errMsg;// 提示错误信息
	private List<WsWorkFormReplaceBean> oldWorkFormReplaceList;
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
	public List<WsWorkFormReplaceBean> getOldWorkFormReplaceList() {
		return oldWorkFormReplaceList;
	}
	public void setOldWorkFormReplaceList(
			List<WsWorkFormReplaceBean> oldWorkFormReplaceList) {
		this.oldWorkFormReplaceList = oldWorkFormReplaceList;
	}

	
	
	
}
