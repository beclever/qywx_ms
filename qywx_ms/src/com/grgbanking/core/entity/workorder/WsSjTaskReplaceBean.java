package com.grgbanking.core.entity.workorder;

//提交工单任务所属模块信息
public class WsSjTaskReplaceBean {

	private String operationSystem;// 操作系统 String(N)
	private String osVersion;// 系统版本 String(N)
	private String atmCName;// ATMC名称 String(N)
	private String atmCVersion;// ATMC版本 String(N)
	private String atmCSpVersion;// 跨平台SP String(N)
	private String operationSystemId;// 操作系统ID String(N)
    private String atmCNameId;// ATMC名称ID String(N)

	public String getOperationSystem() {
		return operationSystem;
	}

	public void setOperationSystem(String operationSystem) {
		this.operationSystem = operationSystem;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAtmCName() {
		return atmCName;
	}

	public void setAtmCName(String atmCName) {
		if (!"请选择".equals(atmCName))
			this.atmCName = atmCName;
	}

	public String getAtmCVersion() {
		return atmCVersion;
	}

	public void setAtmCVersion(String atmCVersion) {
		this.atmCVersion = atmCVersion;
	}

	public String getAtmCSpVersion() {
		return atmCSpVersion;
	}

	public void setAtmCSpVersion(String atmCSpVersion) {
		this.atmCSpVersion = atmCSpVersion;
	}

    public String getOperationSystemId() {
        return operationSystemId;
    }

    public void setOperationSystemId(String operationSystemId) {
        this.operationSystemId = operationSystemId;
    }

    public String getAtmCNameId() {
        return atmCNameId;
    }

    public void setAtmCNameId(String atmCNameId) {
        this.atmCNameId = atmCNameId;
    }

}
