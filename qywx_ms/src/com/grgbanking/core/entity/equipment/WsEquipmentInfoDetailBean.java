package com.grgbanking.core.entity.equipment;

import java.util.List;

import com.grgbanking.core.entity.DataDictionaryBean;

//5.1.4.    设备详细信息
public class WsEquipmentInfoDetailBean {

  // 基本信息
  private String serialNumber;// 设备序列号 String(Y)
  private String equipmentType;// 设备类型 String(N)
  private String equipmentId;// 设备ID String(N)
  private String equipmentModel;// 设备型号 String(N)
  private String manufcturer;// 制造商 String(N)
  private String chief;// 设备负责人 String(N)
  private Integer chiefUserId;// 设备负责人 String(N)
  private String chiefPhone;// 设备负责人电话 String(N)
  private String chief2;// 设备第二负责人 String(N)
  private Integer chiefUserId2;// 设备设备第二负责人 String(N)
  private String chiefPhone2;// 设备设备第二负责人电话 String(N)
  private WsDeviceLocationBean deviceLocation;// gps信息
  // 数据结构(Y)
  private String gateway;// 网关 String(N)
  private String subnet_hide_address;// 子网掩码 String(N)
  private List<WsHistoryServerInfoBean> historyServerInfo;// 历史服务信息 Array(Y)
  private List<WsEquipmentHistoryProblemBean> eHistoryProblem;// 设备历史遗留问题Array(Y)
  private List<WsEquipmentConfigBean> sparepartsInfo;//设备部件配置信息Array(Y)
  // 安装信息
  private String installAddress;// 安装地址 String(N)
  private String customsName;// 客户名称 String(Y)
  private String branchName;// 网点名称 String(Y)
  private String atmManager;// ATM管理员 String(Y)
  private String atmManagerTel;// ATM管理员联系方式 String(Y)
  private String branchPrincipal;//网点负责人String(Y)
  private String branchPrincipalTel;//网点负责人电话String(Y)
  private String installDate;// 安装开通日期 String(Y)
  private String installType;// 安装类型 String(Y)
  private String installModel;// 安装方式 String(Y)
  private String deviceStatus;// 设备状态 String(Y)
  private String operationSystem;// 操作系统 String(Y)
  private String osVersion;// 操作系统版本 String(Y)
  private String ATMCName;// ATMC名称 String(Y)
  private String ATMCVersion;// ATMC版本 String(Y)
  private String atmcSpVersion;// ATMC跨平台SP String(Y)
  private String encryptModel;// 加密方式 String(Y)
  private String ATMNumber;// ATM号 String(Y)
  private String bankNumber;// 银行号 String(Y)
  private String bankTerminalNumber;// 支持终端号 String(Y)
  private String netProtocol;// 网络连接协议 String(Y)
  private String LocalIP;// 本机IP地址 String(Y)
  private String pip;// P端IP地址 String(Y)
  private String consultWay;// 参考费用 String(Y)
  private String referenceCharge;// 参考路线 String(Y)
  // 销售信息
  private String salesProperty;// 销售属性 String(Y)
  private String salesContractNumber;// 销售合同号 String(Y)
  private String repairContractNumber;// 维保合同号 String(Y)
  private String repairStartDate;// 保修开始日期 String(Y)
  private String repairEndDate;// 保修结束日期 String(Y)
  private String repairType;// 保修类型 String(Y)
  
  private String equipmentArea;//设备位置
  
  private List<DataDictionaryBean> chiefList;//设备负责人
  private List<DataDictionaryBean> equipmentAreaList;//设备位置
  private List<DataDictionaryBean> installTypeList;//安装类型
  private List<DataDictionaryBean> installModelList;//安装方式
  private List<DataDictionaryBean> netProtocolList;//网络连接协议
  
  
  public String getSerialNumber() {
      return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber;
  }

  public String getEquipmentType() {
      return equipmentType;
  }

  public void setEquipmentType(String equipmentType) {
      this.equipmentType = equipmentType;
  }

  public String getEquipmentId() {
      return equipmentId;
  }

  public void setEquipmentId(String equipmentId) {
      this.equipmentId = equipmentId;
  }

  public String getEquipmentModel() {
      return equipmentModel;
  }

  public void setEquipmentModel(String equipmentModel) {
      this.equipmentModel = equipmentModel;
  }

  public String getManufcturer() {
      return manufcturer;
  }

  public void setManufcturer(String manufcturer) {
      this.manufcturer = manufcturer;
  }

  public String getChief() {
      return chief;
  }

  public void setChief(String chief) {
      this.chief = chief;
  }

  public String getChiefPhone() {
      return chiefPhone;
  }

  public void setChiefPhone(String chiefPhone) {
      this.chiefPhone = chiefPhone;
  }

  public WsDeviceLocationBean getDeviceLocation() {
      return deviceLocation;
  }

  public void setDeviceLocation(WsDeviceLocationBean deviceLocation) {
      this.deviceLocation = deviceLocation;
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

  public List<WsHistoryServerInfoBean> getHistoryServerInfo() {
      return historyServerInfo;
  }

  public void setHistoryServerInfo(
          List<WsHistoryServerInfoBean> historyServerInfo) {
      this.historyServerInfo = historyServerInfo;
  }

  public List<WsEquipmentHistoryProblemBean> geteHistoryProblem() {
      return eHistoryProblem;
  }

  public void seteHistoryProblem(
          List<WsEquipmentHistoryProblemBean> eHistoryProblem) {
      this.eHistoryProblem = eHistoryProblem;
  }

  public String getInstallAddress() {
      return installAddress;
  }

  public void setInstallAddress(String installAddress) {
      this.installAddress = installAddress;
  }

  public String getCustomsName() {
      return customsName;
  }

  public void setCustomsName(String customsName) {
      this.customsName = customsName;
  }

  public String getBranchName() {
      return branchName;
  }

  public void setBranchName(String branchName) {
      this.branchName = branchName;
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

  public String getInstallDate() {
      return installDate;
  }

  public void setInstallDate(String installDate) {
      this.installDate = installDate;
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

  public String getDeviceStatus() {
      return deviceStatus;
  }

  public void setDeviceStatus(String deviceStatus) {
      this.deviceStatus = deviceStatus;
  }

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

  public String getATMCName() {
      return ATMCName;
  }

  public void setATMCName(String aTMCName) {
      ATMCName = aTMCName;
  }

  public String getATMCVersion() {
      return ATMCVersion;
  }

  public void setATMCVersion(String aTMCVersion) {
      ATMCVersion = aTMCVersion;
  }

  public String getAtmcSpVersion() {
      return atmcSpVersion;
  }

  public void setAtmcSpVersion(String atmcSpVersion) {
      this.atmcSpVersion = atmcSpVersion;
  }

  public String getEncryptModel() {
      return encryptModel;
  }

  public void setEncryptModel(String encryptModel) {
      this.encryptModel = encryptModel;
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

  public List<WsEquipmentConfigBean> getSparepartsInfo() {
      return sparepartsInfo;
  }

  public void setSparepartsInfo(List<WsEquipmentConfigBean> sparepartsInfo) {
      this.sparepartsInfo = sparepartsInfo;
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

  public String getConsultWay() {
      return consultWay;
  }

  public void setConsultWay(String consultWay) {
      this.consultWay = consultWay;
  }

  public String getReferenceCharge() {
      return referenceCharge;
  }

  public void setReferenceCharge(String referenceCharge) {
      this.referenceCharge = referenceCharge;
  }

  public String getSalesProperty() {
      return salesProperty;
  }

  public void setSalesProperty(String salesProperty) {
      this.salesProperty = salesProperty;
  }

  public String getSalesContractNumber() {
      return salesContractNumber;
  }

  public void setSalesContractNumber(String salesContractNumber) {
      this.salesContractNumber = salesContractNumber;
  }

  public String getRepairContractNumber() {
      return repairContractNumber;
  }

  public void setRepairContractNumber(String repairContractNumber) {
      this.repairContractNumber = repairContractNumber;
  }

  public String getRepairStartDate() {
      return repairStartDate;
  }

  public void setRepairStartDate(String repairStartDate) {
      this.repairStartDate = repairStartDate;
  }

  public String getRepairEndDate() {
      return repairEndDate;
  }

  public void setRepairEndDate(String repairEndDate) {
      this.repairEndDate = repairEndDate;
  }

  public String getRepairType() {
      return repairType;
  }

  public void setRepairType(String repairType) {
      this.repairType = repairType;
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

  public String getEquipmentArea() {
      return equipmentArea;
  }

  public void setEquipmentArea(String equipmentArea) {
      this.equipmentArea = equipmentArea;
  }

  public List<DataDictionaryBean> getChiefList() {
      return chiefList;
  }

  public void setChiefList(List<DataDictionaryBean> chiefList) {
      this.chiefList = chiefList;
  }

  public List<DataDictionaryBean> getEquipmentAreaList() {
      return equipmentAreaList;
  }

  public void setEquipmentAreaList(List<DataDictionaryBean> equipmentAreaList) {
      this.equipmentAreaList = equipmentAreaList;
  }

  public List<DataDictionaryBean> getInstallTypeList() {
      return installTypeList;
  }

  public void setInstallTypeList(List<DataDictionaryBean> installTypeList) {
      this.installTypeList = installTypeList;
  }

  public List<DataDictionaryBean> getInstallModelList() {
      return installModelList;
  }

  public void setInstallModelList(List<DataDictionaryBean> installModelList) {
      this.installModelList = installModelList;
  }

  public List<DataDictionaryBean> getNetProtocolList() {
      return netProtocolList;
  }

  public void setNetProtocolList(List<DataDictionaryBean> netProtocolList) {
      this.netProtocolList = netProtocolList;
  }

  public Integer getChiefUserId() {
      return chiefUserId;
  }

  public void setChiefUserId(Integer chiefUserId) {
      this.chiefUserId = chiefUserId;
  }

  public String getChief2() {
      return chief2;
  }

  public void setChief2(String chief2) {
      this.chief2 = chief2;
  }

  public Integer getChiefUserId2() {
      return chiefUserId2;
  }

  public void setChiefUserId2(Integer chiefUserId2) {
      this.chiefUserId2 = chiefUserId2;
  }

  public String getChiefPhone2() {
      return chiefPhone2;
  }

  public void setChiefPhone2(String chiefPhone2) {
      this.chiefPhone2 = chiefPhone2;
  }
  
}