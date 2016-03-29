package com.grgbanking.core.entity.workorder;


//巡检类型选项信息
public class WsSjTypeOptionsBean {

	private WsOptionsBean firstType;// 第一个选项 N 数据结构(Y)，详见5.1.21

	private WsOptionsBean secondType;// 第二个选项 Y 数据结构(Y)，详见5.1.21构详见5.1.21

	private String explain;// 说明 Y String(N)

	public WsOptionsBean getFirstType() {
		return firstType;
	}

	public void setFirstType(WsOptionsBean firstType) {
		this.firstType = firstType;
	}

	public WsOptionsBean getSecondType() {
		return secondType;
	}

	public void setSecondType(WsOptionsBean secondType) {
		this.secondType = secondType;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

}
