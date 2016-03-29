package com.grgbanking.core.entity.stockbean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 备件拒接/接收 单个/多个备件
 * 
 * @author G0210181
 * 
 */
public class WsSparepartParam {

	
    private String materialCode;  //物料编码

    private String serialNumber;  //条码 

    private int sparepartNum;  //数量

    //private String dockingNo;   // 交接人
    
//    public String getDockingNo() {
//		return dockingNo;
//	}
//
//	public void setDockingNo(String dockingNo) {
//		this.dockingNo = dockingNo;
//	}

	public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public int getSparepartNum() {
		return sparepartNum;
	}

	public void setSparepartNum(int sparepartNum) {
		this.sparepartNum = sparepartNum;
	}


}
