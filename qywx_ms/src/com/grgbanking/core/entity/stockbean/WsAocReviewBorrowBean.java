package com.grgbanking.core.entity.stockbean;


/**
 * Copyright (c) 2013 GRG Banking Equipment Co.,Ltd.
 *
 * @createDate: 2013-04-22
 * @author: guabin
 * @description
 * 待审批借用备件信息
 */
public class WsAocReviewBorrowBean {

    private String applyId;

    private String applyNo;	// 借用单号
    
    private String materialCode;

    private String materialName;

    private String moduleLevel;//模块级别

    private String materialType;//模块类型

    private String hasSerialNumber;

    private String borrowerName;//借用人姓名

    private int borrowNum;//借用数量

    private String serialNumbers;//条码，逗号隔开

    private int availableNum;//可用\可借用数量

    private String applyTime; //借用时间
    
    private String applyStoreName; // 申请人仓库名称
    
    private String applyStoreId; // 申请人仓库ID
    
    private String applyNote; // 申请备注
    
	public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getModuleLevel() {
        return moduleLevel;
    }

    public void setModuleLevel(String moduleLevel) {
        this.moduleLevel = moduleLevel;
    }

    public String getHasSerialNumber() {
        return hasSerialNumber;
    }

    public void setHasSerialNumber(String hasSerialNumber) {
        this.hasSerialNumber = hasSerialNumber;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public int getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(int borrowNum) {
        this.borrowNum = borrowNum;
    }

    public String getSerialNumbers() {
        return serialNumbers;
    }

    public void setSerialNumbers(String serialNumbers) {
        this.serialNumbers = serialNumbers;
    }

    public int getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(int availableNum) {
        this.availableNum = availableNum;
    }

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getApplyStoreName() {
		return applyStoreName;
	}

	public void setApplyStoreName(String applyStoreName) {
		this.applyStoreName = applyStoreName;
	}

	public String getApplyStoreId() {
		return applyStoreId;
	}

	public void setApplyStoreId(String applyStoreId) {
		this.applyStoreId = applyStoreId;
	}

	public String getApplyNote() {
		return applyNote;
	}

	public void setApplyNote(String applyNote) {
		this.applyNote = applyNote;
	}
	
}
