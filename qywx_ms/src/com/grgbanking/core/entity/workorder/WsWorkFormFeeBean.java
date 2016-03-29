package com.grgbanking.core.entity.workorder;

public class WsWorkFormFeeBean {

	private String feeId;// 费用ID String(N)
	private String feeName;// 费用名称 String(N)
	private String total;// 金额 String(N)
	private String togetherPersonId;// 同行人ID String(N)
	private String togetherPersonName;// 同行人名称 String(N)
	private String remark;// 备注 String(N)

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTogetherPersonId() {
		return togetherPersonId;
	}

	public void setTogetherPersonId(String togetherPersonId) {
		this.togetherPersonId = togetherPersonId;
	}

	public String getTogetherPersonName() {
		return togetherPersonName;
	}

	public void setTogetherPersonName(String togetherPersonName) {
		this.togetherPersonName = togetherPersonName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
