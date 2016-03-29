package com.grgbanking.core.entity.workorder;

//5.1.15.	故障部位字段属性
public class WsProblemPartBean {
	private String problemId;// String(Y) ID
	private String description;// String(Y) 描述
	private String problemCode;// String(Y) 代码
	private Integer lastLayer;// int (Y) 是否有最后一层，0：有，1：没有

	public String getProblemId() {
		return problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	public Integer getLastLayer() {
		return lastLayer;
	}

	public void setLastLayer(Integer lastLayer) {
		this.lastLayer = lastLayer;
	}

}
