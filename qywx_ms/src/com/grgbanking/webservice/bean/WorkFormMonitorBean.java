package com.grgbanking.webservice.bean;

import java.util.Date;

public class WorkFormMonitorBean {
	private Integer workformId;

	private String poNumbers;

	private String engineerName;

	private String status;
	
	private Date createDateStart;

	private Date createDateEnd;

	private Date appmDateStart;

	private Date appmDateEnd;

	private String saleContractNo;

	private String departmentIds;

	private String customerIds;

	private String eqtBranchName;// 网点名称

	private Integer regionNameId;// 区域ID

	private Integer departmentId;// 服务站ID

	private String warningService;// 服务预警

	private String warningCtrl;// 操作预警

	private String warningCtrlLevel;// 操作预警等级

	private String modifyDateType;

	private String satisfiedTypeSms;//短信预警项目满意度
	
	private String isExport;// 是否导出
    
    private String serialNumber;
    
    private String engineerId;
    
    private String createType;

	public String getPoNumbers() {
		return poNumbers;
	}

	public void setPoNumbers(String poNumbers) {
		this.poNumbers = poNumbers;
	}

	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public Date getAppmDateStart() {
		return appmDateStart;
	}

	public void setAppmDateStart(Date appmDateStart) {
		this.appmDateStart = appmDateStart;
	}

	public Date getAppmDateEnd() {
		return appmDateEnd;
	}

	public void setAppmDateEnd(Date appmDateEnd) {
		this.appmDateEnd = appmDateEnd;
	}

	public String getSaleContractNo() {
		return saleContractNo;
	}

	public void setSaleContractNo(String saleContractNo) {
		this.saleContractNo = saleContractNo;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

	public String getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}

	public Integer getWorkformId() {
		return workformId;
	}

	public void setWorkformId(Integer workformId) {
		this.workformId = workformId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getRegionNameId() {
		return regionNameId;
	}

	public void setRegionNameId(Integer regionNameId) {
		this.regionNameId = regionNameId;
	}

	public String getEqtBranchName() {
		return eqtBranchName;
	}

	public void setEqtBranchName(String eqtBranchName) {
		this.eqtBranchName = eqtBranchName;
	}

	public String getWarningService() {
		return warningService;
	}

	public void setWarningService(String warningService) {
		this.warningService = warningService;
	}

	public String getWarningCtrl() {
		return warningCtrl;
	}

	public void setWarningCtrl(String warningCtrl) {
		this.warningCtrl = warningCtrl;
	}

	public String getWarningCtrlLevel() {
		return warningCtrlLevel;
	}

	public void setWarningCtrlLevel(String warningCtrlLevel) {
		this.warningCtrlLevel = warningCtrlLevel;
	}

	public String getModifyDateType() {
		return modifyDateType;
	}

	public void setModifyDateType(String modifyDateType) {
		this.modifyDateType = modifyDateType;
	}

	public String getIsExport() {
		return isExport;
	}

	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}

	public String getSatisfiedTypeSms() {
		return satisfiedTypeSms;
	}

	public void setSatisfiedTypeSms(String satisfiedTypeSms) {
		this.satisfiedTypeSms = satisfiedTypeSms;
	}

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }

    public String getCreateType() {
        return createType;
    }

    public void setCreateType(String createType) {
        this.createType = createType;
    }

}
