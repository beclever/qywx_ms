package com.grgbanking.core.entity.equipment;

/**
 * 
* @ClassName: WsEquipmentInfoSaveBean 
* @Description: 保存设备详细信息实体类 
* @author boonLay
* @date 2014-3-24 下午03:00:05
 */
public class WsEquipmentInfoSaveBean {
	private String serialNumber;// 设备序列号 String(Y)
	private String equipmentId;// 设备ID String(N)
	private String chief;// 设备负责人 String(N)
	private String installAddress;// 安装地址 String(N)
	private String branchName;// 网点名称 String(Y)
	private String branchPrincipal;//网点负责人String(Y)
	private String branchPrincipalTel;//网点负责人电话String(Y)
	private String installType;// 安装类型 String(Y)
	private String installModel;// 安装方式 String(Y)
	private String atmManager;// ATM管理员 String(Y)
	private String atmManagerTel;// ATM管理员联系方式 String(Y)
	private String ATMNumber;// ATM号 String(Y)
	private String bankNumber;// 银行号 String(Y)
	private String bankTerminalNumber;// 支持终端号 String(Y)
	private String netProtocol;// 网络连接协议 String(Y)
	private String LocalIP;// 本机IP地址 String(Y)
	private String pip;// P端IP地址 String(Y)
	private String gateway;// 网关 String(N)
	private String subnet_hide_address;// 子网掩码 String(N)

	private String equipmentArea;//设备位置
	
	private Long userId;		//用户ID
	private String userName;	//用户名

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getChief() {
		return chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	public String getInstallAddress() {
		return installAddress;
	}

	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchPrincipal() {
		return branchPrincipal;
	}

	public void setBranchPrincipal(String branchPrincipal) {
		this.branchPrincipal = branchPrincipal;
	}

	public String getBranchPrincipalTel() {
		return branchPrincipalTel;
	}

	public void setBranchPrincipalTel(String branchPrincipalTel) {
		this.branchPrincipalTel = branchPrincipalTel;
	}

	public String getInstallType() {
		return installType;
	}

	public void setInstallType(String installType) {
		this.installType = installType;
	}

	public String getInstallModel() {
		return installModel;
	}

	public void setInstallModel(String installModel) {
		this.installModel = installModel;
	}

	public String getAtmManager() {
		return atmManager;
	}

	public void setAtmManager(String atmManager) {
		this.atmManager = atmManager;
	}

	public String getAtmManagerTel() {
		return atmManagerTel;
	}

	public void setAtmManagerTel(String atmManagerTel) {
		this.atmManagerTel = atmManagerTel;
	}

	public String getATMNumber() {
		return ATMNumber;
	}

	public void setATMNumber(String aTMNumber) {
		ATMNumber = aTMNumber;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getBankTerminalNumber() {
		return bankTerminalNumber;
	}

	public void setBankTerminalNumber(String bankTerminalNumber) {
		this.bankTerminalNumber = bankTerminalNumber;
	}

	public String getNetProtocol() {
		return netProtocol;
	}

	public void setNetProtocol(String netProtocol) {
		this.netProtocol = netProtocol;
	}

	public String getLocalIP() {
		return LocalIP;
	}

	public void setLocalIP(String localIP) {
		LocalIP = localIP;
	}

	public String getPip() {
		return pip;
	}

	public void setPip(String pip) {
		this.pip = pip;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getSubnet_hide_address() {
		return subnet_hide_address;
	}

	public void setSubnet_hide_address(String subnetHideAddress) {
		subnet_hide_address = subnetHideAddress;
	}

	public String getEquipmentArea() {
		return equipmentArea;
	}

	public void setEquipmentArea(String equipmentArea) {
		this.equipmentArea = equipmentArea;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
