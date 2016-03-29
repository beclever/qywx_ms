package com.grgbanking.core.entity.ws;

import java.util.List;

import com.grgbanking.core.entity.workorder.WsWorkformTogetherPersonBean;





/**
 * 同行人员返回相关信息
 * date:2013.10.28
 * @author yt
 *
 */

public class WsResultTogetherPersonsBean {

	private Integer status;// 1：成功。0：失败。
	private String errMsg;// 提示错误信息
	private List<WsWorkformTogetherPersonBean> togetherPerson;// 数据结构(Y)，详见5.1.21
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
	public List<WsWorkformTogetherPersonBean> getTogetherPerson() {
		return togetherPerson;
	}
	public void setTogetherPerson(List<WsWorkformTogetherPersonBean> togetherPerson) {
		this.togetherPerson = togetherPerson;
	}
	
	
}
