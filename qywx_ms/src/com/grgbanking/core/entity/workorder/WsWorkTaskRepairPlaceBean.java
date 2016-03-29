package com.grgbanking.core.entity.workorder;

//5.1.23.	工单任务-故障部位
public class WsWorkTaskRepairPlaceBean {

	private String problemPartId;// 故障部位ID String(N)
	private String problemCodeId;// 故障描述ID String(N)
	private String problemReasonId;// 故障原因ID String(N)
	private String problemMethodId;// 处理方法ID String(N)
	private String problemPartCode;// 故障部位CODE String(N)
	private String troubleCode;// 故障描述CODE String(N)
	private String troubleReasonCode;// 故障原因CODE String(N)
	private String processCode;// 处理方法CODE String(N)
	private String problemPartRemark;// 故障部位名称 String(N)
	private String troubleRemark;// 故障描述名称 String(N)
	private String troubleReasonRemark;// 故障原因名称 String(N)
	private String processRemark;// 处理方法名称 String(N)

	public String getProblemPartId() {
		return problemPartId;
	}

	public void setProblemPartId(String problemPartId) {
		this.problemPartId = problemPartId;
	}

	public String getProblemCodeId() {
		return problemCodeId;
	}

	public void setProblemCodeId(String problemCodeId) {
		this.problemCodeId = problemCodeId;
	}

	public String getProblemReasonId() {
		return problemReasonId;
	}

	public void setProblemReasonId(String problemReasonId) {
		this.problemReasonId = problemReasonId;
	}

	public String getProblemMethodId() {
		return problemMethodId;
	}

	public void setProblemMethodId(String problemMethodId) {
		this.problemMethodId = problemMethodId;
	}

	public String getProblemPartCode() {
		return problemPartCode;
	}

	public void setProblemPartCode(String problemPartCode) {
		this.problemPartCode = problemPartCode;
	}

	public String getTroubleCode() {
		return troubleCode;
	}

	public void setTroubleCode(String troubleCode) {
		this.troubleCode = troubleCode;
	}

	public String getTroubleReasonCode() {
		return troubleReasonCode;
	}

	public void setTroubleReasonCode(String troubleReasonCode) {
		this.troubleReasonCode = troubleReasonCode;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getProblemPartRemark() {
		return problemPartRemark;
	}

	public void setProblemPartRemark(String problemPartRemark) {
		this.problemPartRemark = problemPartRemark;
	}

	public String getTroubleRemark() {
		return troubleRemark;
	}

	public void setTroubleRemark(String troubleRemark) {
		this.troubleRemark = troubleRemark;
	}

	public String getTroubleReasonRemark() {
		return troubleReasonRemark;
	}

	public void setTroubleReasonRemark(String troubleReasonRemark) {
		this.troubleReasonRemark = troubleReasonRemark;
	}

	public String getProcessRemark() {
		return processRemark;
	}

	public void setProcessRemark(String processRemark) {
		this.processRemark = processRemark;
	}

}
