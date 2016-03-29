package com.grgbanking.core.entity.stockbean;




/**
 * 备件替换
 * 
 */
public class WsAocWorkformRepeatBean {

	 //工单号
    private String workformCode;
    //员工号
    private String employeeNo;
    //员工名
    private String employeeName;
    //替换回物料编码
    private String repeatMaterialCode;
    //替换回物料名称
    private String repeatMaterialName;
    //替换回条码
    private String repeatSerialNumber;
    //替换回数量
    private int sparepartNum;
	public String getWorkformCode() {
		return workformCode;
	}
	public void setWorkformCode(String workformCode) {
		this.workformCode = workformCode;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getRepeatMaterialCode() {
		return repeatMaterialCode;
	}
	public void setRepeatMaterialCode(String repeatMaterialCode) {
		this.repeatMaterialCode = repeatMaterialCode;
	}
	public String getRepeatMaterialName() {
		return repeatMaterialName;
	}
	public void setRepeatMaterialName(String repeatMaterialName) {
		this.repeatMaterialName = repeatMaterialName;
	}
	public String getRepeatSerialNumber() {
		return repeatSerialNumber;
	}
	public void setRepeatSerialNumber(String repeatSerialNumber) {
		this.repeatSerialNumber = repeatSerialNumber;
	}
	public int getSparepartNum() {
		return sparepartNum;
	}
	public void setSparepartNum(int sparepartNum) {
		this.sparepartNum = sparepartNum;
	}
    
    
    
}
