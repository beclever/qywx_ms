package com.grgbanking.core.entity.workorder;

import java.util.List;

public class WsResultProblemPartBean {

	private Integer status;// 1：成功。0：失败。
	private String errMsg;// 提示错误信息
	private Integer total;// Int(Y) 记录总数
	private List<WsProblemPartBean> rows;// Array(Y) 返回该父节点下的数据，故障部位字段属性

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

	public List<WsProblemPartBean> getRows() {
		return rows;
	}

	public void setRows(List<WsProblemPartBean> rows) {
		this.rows = rows;
	}

}
