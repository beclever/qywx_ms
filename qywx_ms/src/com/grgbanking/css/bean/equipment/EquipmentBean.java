package com.grgbanking.css.bean.equipment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grgbanking.css.bean.ContractMaintainImport;
import com.grgbanking.css.bean.CssUserBean;
import com.grgbanking.css.common.CssBaseEntity;
import com.grgbanking.css.common.PhoneImage;

/**
 * 设备信息值对象
 * 
 * Product:Grgbanking Service Of Customer System. Version:2.0 Copyright 2010 by
 * Grgbanking All Rights Reserved. Author: zhangzhi Date: 2010-7-18 下午09:39:29
 */
public class EquipmentBean extends CssBaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer equipmentId;
	private String equipmentType;// 设备类型
	private String manufacturer;// 制造商
	private String country;// 国家
	private Date productDate;// 生产日期
	private String equipmentModel;// 设备型号
	private String barCode;// 条码编码
	private String equipmentStyle;// 设备款式
	private String equipmentStatus;// 设备状态
	private String serialNumber;// 序列号
	private Integer customerId;// 客户ID
	private String customerIds;
	private String customerName;// 客户名称
	private Integer departmentId;// 服务站ID
	private String departmentIds;
	private String departmentId0;//服务站模糊查询
	private String departmentName;// 服务站名称
	private String materialCode;// 整机物料编码
	private String branchName;// 网点名称
	private Date installDate;// 安装日期
	private Date moveDate;// 移机日期
	private String installType;// 安装类型
	private String installModel;// 安装方式
	private Integer provinceId;// 省份ID
	private String provinceName;
	private Integer cityId;// 城市ID
	private String cityName;
	private String installAddress;// 安装地址
	private String atmManager;// ATM管理员
	private String branchPrincipal;// 网点负责人
	private String atmManagerTel;// ATM管理员电话
	private String branchPrincipalTel;// 网点负责人电话
	private String atmNumber;// ATM号
	private String encryptType;// 加密方式
	private String bankNumber;// 银行号
	private String bankTerminalNumber;// 银行终端号
	private String atmcName;// ATMC名称
	private String atmcVersion;// ATMC版本
	private String atmcSpVersion;// SP版本(跨平台)
	private String localIp;// 本地ID
	private String pip;// P端IP
	private String gateway;// 网关
	private String subnetHideAddress;// 子网掩码
	private String netProtocol;// 网络协议
	private String dailyDealCount;// 日均交易量
	private String dailyDealMoney;// 日均交易金额
	private String acceptReportStatus;// 验收报告状态
	private Date lastMaintainDate;// 最后保养日期
	private Date lastRepairDate;// 最后维修日期
	private String trafficLine;// 乘车路线
	private String referenceCharge;// 参考费用
	private String environmentDust;// 环境
	private String environmentHumidity;
	private String environmentTemperature;
	private String environmentPowerSupply;
	private String environmentRainDefence;
	private String environmentSunshine;
	private String saleProperty;// 销售属性
	private String saleContractNo;// 销售合同号
	private String maintainContractNo;// 维保合同号
	private String maintainCustomerName;// 维保客户名称
	private String saleCustomerName;
	private String warrantyStatus;// 保修类型（默认为免保，如果签了维保合同，则与维保合同关联）
	private String concern;// 服务站主任重点关注
	private Date warrantyStartDate;// 保修开始日期（新机安装日期）
	private Date warrantyEndDate;// 到保日期（在销售保修期内，根据销售合同有效期算出。在维保期内，根据维保合同有效期算出）
	private Date shipDate; // 发货日期
	private String batchNumber;// 生产批次号
	private Integer warrantyMonth; // 免保期限
	private Integer continueWarrantyMonth;// 续保期限
	// private List<EquipmentSpecialInfo> specialInfoList;//设备特殊信息

	private String operateSystem;// 操作系统
	private String osVersion;// 操作系统版本
	private String installProperty;// 装机属性
	private Integer pm;// PM次数
	private double openRate;// 开通率
	private double repairTime;// 修复时间
	private double responseTime;// 响应时间
	private String expiredWarrantyStatus;// 过保期后设备维修状态自动改为配置的状态
	private String spNumber;

	private Integer singleCustomerId;// 查询条件，只查此客户，不需查询其下面的子客户

	private Double longitude;// 经度（设备地理位置）
	private Double latitude;// 纬度（设备地理位置）

	private List<EquipmentLog> equipmentLoglist = new ArrayList<EquipmentLog>(); // 设备日志

	private List<EquipmentSpecialInfo> equipmentSpecialList = new ArrayList<EquipmentSpecialInfo>();// 设备特殊信息

	private List<EquipmentConfigTreeBean> equipmentConfigTree;// 设备当配置树

	private List<EquipmentContactBean> equipmentContactBeanList = new ArrayList<EquipmentContactBean>();// 设备联系人列表

	private String hasRevisit; // 是否回访
	private String lastRevisitTime; // 回访客户的最后时间
	private String regionName;// 区域名称
	private Integer countRevisit;// 回访的次数
	private String beginShipDate;// 开始发货日期
	private String endShipDate;// 结束发货日期
	private String beginInstallDate;// 开始安装日期
	private String endInstallDate;// 结束安装日期
	private String beginWarrantyEndDate;// 开始 维保结束时间
	private String endWarrantyEndDate;// 结束 维保结束时间

	private String beginLastMaintainDate;// 开始保养时间
	private String endLastMaintainDate;// 最后保养时间

	private String beginLastRevisitTime;
	private String endLastRevisitTime;

	private String customerTel;// 客户电话
	private String warrantyDateStr;

	private String serviceArticle;// 服务条款

	private Integer equipmentCharge;// 设备负责人
	private Integer equipmentCharge2;//设备第二负责人
	private String equipmentArea;// 设备位置

	private String equipmentChargeName;// 设备负责人
	private String equipmentChargeName2;//设备第二负责人
	private List<CssUserBean> userList;// 用于回显服务站人员信息

	private List<EquipmentHistoryProblem> equipmentHistoryProblemList;

	private String remarks;

	private List<PhoneImage> equipmentImgList = new ArrayList<PhoneImage>();// 设备图片
	private List<PhoneImage> branchImgList = new ArrayList<PhoneImage>();// 网点图片
	private List<PhoneImage> departmentImgList = new ArrayList<PhoneImage>();// 服务站图片

	// 设备状态更改日期
	private String changeTime;

	private String bankPassReq;
	
	private String customerTerminalName;//销售合同终端客户名称
	private String customerSigningName;//维保合同签约客户
	
	
	/**以下是设备联系人的记录信息 */
	private String equipmentContactId;//用作删除的ids
	private Integer[] equipmentContactIds;// 主键
	private String[] contactNames;// 联系人名称
	private String[] mobliePhones;// 移动电话
	private String[] telephones;// 固定电话号码
	private String[] positionVals;// 岗位职级
	private String[] isManagers;// 是否当前管理员
	private String[] isRefuseSmes;// 是否拒收短信
	private String[] remarkes;// 备注
	private String[] sexs;//性别
	//以上设备联系人列表
	
	// 最新维保信息日期
	private List<ContractMaintainImport> contractMaintainImport;

	private String userRole;
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	private String pAoc;
	
	public String getpAoc() {
		return pAoc;
	}

	public void setpAoc(String pAoc) {
		this.pAoc = pAoc;
	}

	public List<ContractMaintainImport> getContractMaintainImport() {
		return contractMaintainImport;
	}

	public void setContractMaintainImport(List<ContractMaintainImport> contractMaintainImport) {
		this.contractMaintainImport = contractMaintainImport;
	}

	public String getBankPassReq() {
		return bankPassReq;
	}

	public void setBankPassReq(String bankPassReq) {
		this.bankPassReq = bankPassReq;
	}

	public String getSpNumber() {
		return spNumber;
	}

	public void setSpNumber(String spNumber) {
		this.spNumber = spNumber;
	}

	public List<PhoneImage> getDepartmentImgList() {
		return departmentImgList;
	}

	public void setDepartmentImgList(List<PhoneImage> departmentImgList) {
		this.departmentImgList = departmentImgList;
	}

	public List<PhoneImage> getBranchImgList() {
		return branchImgList;
	}

	public void setBranchImgList(List<PhoneImage> branchImgList) {
		this.branchImgList = branchImgList;
	}

	public List<PhoneImage> getEquipmentImgList() {
		return equipmentImgList;
	}

	public void setEquipmentImgList(List<PhoneImage> equipmentImgList) {
		this.equipmentImgList = equipmentImgList;
	}

	public String toString() {
		return "serialNumber =" + serialNumber + ",equipmentModel=" + equipmentModel + ",saleContractNo="
				+ saleContractNo + ",saleProperty=" + saleProperty + ",maintainContractNo=" + maintainContractNo
				+ ",warrantyStatus=" + warrantyStatus + ",installModel=" + installModel + ",equipmentStatus="
				+ equipmentStatus + ",equipmentType=" + equipmentType + ",atmcName=" + atmcName + ",manufacturer="
				+ manufacturer + ",departmentId=" + departmentId + ",customerIds=" + customerIds + ",beginShipDate="
				+ beginShipDate + ",beginInstallDate=" + beginInstallDate + ",beginWarrantyEndDate="
				+ beginWarrantyEndDate + ",beginLastRevisitTime =" + beginLastRevisitTime;
	}

	public String getBeginLastMaintainDate() {
		return beginLastMaintainDate;
	}

	public void setBeginLastMaintainDate(String beginLastMaintainDate) {
		this.beginLastMaintainDate = beginLastMaintainDate;
	}

	public String getEndLastMaintainDate() {
		return endLastMaintainDate;
	}

	public void setEndLastMaintainDate(String endLastMaintainDate) {
		this.endLastMaintainDate = endLastMaintainDate;
	}

	public String getBeginLastRevisitTime() {
		return beginLastRevisitTime;
	}

	public void setBeginLastRevisitTime(String beginLastRevisitTime) {
		this.beginLastRevisitTime = beginLastRevisitTime;
	}

	public String getEndLastRevisitTime() {
		return endLastRevisitTime;
	}

	public void setEndLastRevisitTime(String endLastRevisitTime) {
		this.endLastRevisitTime = endLastRevisitTime;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getEquipmentModel() {
		return equipmentModel;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getEquipmentStyle() {
		return equipmentStyle;
	}

	public void setEquipmentStyle(String equipmentStyle) {
		this.equipmentStyle = equipmentStyle;
	}

	public String getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public Date getMoveDate() {
		return moveDate;
	}

	public void setMoveDate(Date moveDate) {
		this.moveDate = moveDate;
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

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getInstallAddress() {
		return installAddress;
	}

	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}

	public String getAtmManager() {
		return atmManager;
	}

	public void setAtmManager(String atmManager) {
		this.atmManager = atmManager;
	}

	public String getBranchPrincipal() {
		return branchPrincipal;
	}

	public void setBranchPrincipal(String branchPrincipal) {
		this.branchPrincipal = branchPrincipal;
	}

	public String getAtmManagerTel() {
		return atmManagerTel;
	}

	public void setAtmManagerTel(String atmManagerTel) {
		this.atmManagerTel = atmManagerTel;
	}

	public String getBranchPrincipalTel() {
		return branchPrincipalTel;
	}

	public void setBranchPrincipalTel(String branchPrincipalTel) {
		this.branchPrincipalTel = branchPrincipalTel;
	}

	public String getAtmNumber() {
		return atmNumber;
	}

	public void setAtmNumber(String atmNumber) {
		this.atmNumber = atmNumber;
	}

	public String getEncryptType() {
		return encryptType;
	}

	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
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

	public String getAtmcName() {
		return atmcName;
	}

	public void setAtmcName(String atmcName) {
		this.atmcName = atmcName;
	}

	public String getAtmcVersion() {
		return atmcVersion;
	}

	public void setAtmcVersion(String atmcVersion) {
		this.atmcVersion = atmcVersion;
	}

	public String getAtmcSpVersion() {
		return atmcSpVersion;
	}

	public void setAtmcSpVersion(String atmcSpVersion) {
		this.atmcSpVersion = atmcSpVersion;
	}

	public String getLocalIp() {
		return localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
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

	public String getSubnetHideAddress() {
		return subnetHideAddress;
	}

	public void setSubnetHideAddress(String subnetHideAddress) {
		this.subnetHideAddress = subnetHideAddress;
	}

	public String getNetProtocol() {
		return netProtocol;
	}

	public void setNetProtocol(String netProtocol) {
		this.netProtocol = netProtocol;
	}

	public String getDailyDealCount() {
		return dailyDealCount;
	}

	public void setDailyDealCount(String dailyDealCount) {
		this.dailyDealCount = dailyDealCount;
	}

	public String getDailyDealMoney() {
		return dailyDealMoney;
	}

	public void setDailyDealMoney(String dailyDealMoney) {
		this.dailyDealMoney = dailyDealMoney;
	}

	public String getAcceptReportStatus() {
		return acceptReportStatus;
	}

	public void setAcceptReportStatus(String acceptReportStatus) {
		this.acceptReportStatus = acceptReportStatus;
	}

	public Date getLastMaintainDate() {
		return lastMaintainDate;
	}

	public void setLastMaintainDate(Date lastMaintainDate) {
		this.lastMaintainDate = lastMaintainDate;
	}

	public Date getLastRepairDate() {
		return lastRepairDate;
	}

	public void setLastRepairDate(Date lastRepairDate) {
		this.lastRepairDate = lastRepairDate;
	}

	public String getTrafficLine() {
		return trafficLine;
	}

	public void setTrafficLine(String trafficLine) {
		this.trafficLine = trafficLine;
	}

	public String getReferenceCharge() {
		return referenceCharge;
	}

	public void setReferenceCharge(String referenceCharge) {
		this.referenceCharge = referenceCharge;
	}

	public String getEnvironmentDust() {
		return environmentDust;
	}

	public void setEnvironmentDust(String environmentDust) {
		this.environmentDust = environmentDust;
	}

	public String getEnvironmentHumidity() {
		return environmentHumidity;
	}

	public void setEnvironmentHumidity(String environmentHumidity) {
		this.environmentHumidity = environmentHumidity;
	}

	public String getEnvironmentTemperature() {
		return environmentTemperature;
	}

	public void setEnvironmentTemperature(String environmentTemperature) {
		this.environmentTemperature = environmentTemperature;
	}

	public String getEnvironmentPowerSupply() {
		return environmentPowerSupply;
	}

	public void setEnvironmentPowerSupply(String environmentPowerSupply) {
		this.environmentPowerSupply = environmentPowerSupply;
	}

	public String getEnvironmentRainDefence() {
		return environmentRainDefence;
	}

	public void setEnvironmentRainDefence(String environmentRainDefence) {
		this.environmentRainDefence = environmentRainDefence;
	}

	public String getEnvironmentSunshine() {
		return environmentSunshine;
	}

	public void setEnvironmentSunshine(String environmentSunshine) {
		this.environmentSunshine = environmentSunshine;
	}

	public String getSaleProperty() {
		return saleProperty;
	}

	public void setSaleProperty(String saleProperty) {
		this.saleProperty = saleProperty;
	}

	public String getSaleContractNo() {
		return saleContractNo;
	}

	public void setSaleContractNo(String saleContractNo) {
		this.saleContractNo = saleContractNo;
	}

	public String getSaleCustomerName() {
		return saleCustomerName;
	}

	public void setSaleCustomerName(String saleCustomerName) {
		this.saleCustomerName = saleCustomerName;
	}

	public String getWarrantyStatus() {
		return warrantyStatus;
	}

	public void setWarrantyStatus(String warrantyStatus) {
		this.warrantyStatus = warrantyStatus;
	}

	public String getConcern() {
		return concern;
	}

	public void setConcern(String concern) {
		this.concern = concern;
	}

	public Date getWarrantyStartDate() {
		return warrantyStartDate;
	}

	public void setWarrantyStartDate(Date warrantyStartDate) {
		this.warrantyStartDate = warrantyStartDate;
	}

	public Date getWarrantyEndDate() {
		return warrantyEndDate;
	}

	public void setWarrantyEndDate(Date warrantyEndDate) {
		this.warrantyEndDate = warrantyEndDate;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Integer getWarrantyMonth() {
		return warrantyMonth;
	}

	public void setWarrantyMonth(Integer warrantyMonth) {
		this.warrantyMonth = warrantyMonth;
	}

	public String getOperateSystem() {
		return operateSystem;
	}

	public void setOperateSystem(String operateSystem) {
		this.operateSystem = operateSystem;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getInstallProperty() {
		return installProperty;
	}

	public void setInstallProperty(String installProperty) {
		this.installProperty = installProperty;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getMaintainContractNo() {
		return maintainContractNo;
	}

	public void setMaintainContractNo(String maintainContractNo) {
		this.maintainContractNo = maintainContractNo;
	}

	public Integer getPm() {
		return pm;
	}

	public void setPm(Integer pm) {
		this.pm = pm;
	}

	public double getOpenRate() {
		return openRate;
	}

	public void setOpenRate(double openRate) {
		this.openRate = openRate;
	}

	public double getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(double repairTime) {
		this.repairTime = repairTime;
	}

	public double getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(double responseTime) {
		this.responseTime = responseTime;
	}

	public Integer getContinueWarrantyMonth() {
		return continueWarrantyMonth;
	}

	public void setContinueWarrantyMonth(Integer continueWarrantyMonth) {
		this.continueWarrantyMonth = continueWarrantyMonth;
	}

	public String getMaintainCustomerName() {
		return maintainCustomerName;
	}

	public void setMaintainCustomerName(String maintainCustomerName) {
		this.maintainCustomerName = maintainCustomerName;
	}

	public List<EquipmentLog> getEquipmentLoglist() {
		return equipmentLoglist;
	}

	public void setEquipmentLoglist(List<EquipmentLog> equipmentLoglist) {
		this.equipmentLoglist = equipmentLoglist;
	}

	public List<EquipmentSpecialInfo> getEquipmentSpecialList() {
		return equipmentSpecialList;
	}

	public void setEquipmentSpecialList(List<EquipmentSpecialInfo> equipmentSpecialList) {
		this.equipmentSpecialList = equipmentSpecialList;
	}

	public List<EquipmentConfigTreeBean> getEquipmentConfigTree() {
		return equipmentConfigTree;
	}

	public void setEquipmentConfigTree(List<EquipmentConfigTreeBean> equipmentConfigTree) {
		this.equipmentConfigTree = equipmentConfigTree;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getHasRevisit() {
		return hasRevisit;
	}

	public void setHasRevisit(String hasRevisit) {
		this.hasRevisit = hasRevisit;
	}

	public String getLastRevisitTime() {
		return lastRevisitTime;
	}

	public void setLastRevisitTime(String lastRevisitTime) {
		this.lastRevisitTime = lastRevisitTime;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getCountRevisit() {
		return countRevisit;
	}

	public void setCountRevisit(Integer countRevisit) {
		this.countRevisit = countRevisit;
	}

	public String getBeginShipDate() {
		return beginShipDate;
	}

	public void setBeginShipDate(String beginShipDate) {
		this.beginShipDate = beginShipDate;
	}

	public String getEndShipDate() {
		return endShipDate;
	}

	public void setEndShipDate(String endShipDate) {
		this.endShipDate = endShipDate;
	}

	public String getBeginInstallDate() {
		return beginInstallDate;
	}

	public void setBeginInstallDate(String beginInstallDate) {
		this.beginInstallDate = beginInstallDate;
	}

	public String getEndInstallDate() {
		return endInstallDate;
	}

	public void setEndInstallDate(String endInstallDate) {
		this.endInstallDate = endInstallDate;
	}

	public String getBeginWarrantyEndDate() {
		return beginWarrantyEndDate;
	}

	public void setBeginWarrantyEndDate(String beginWarrantyEndDate) {
		this.beginWarrantyEndDate = beginWarrantyEndDate;
	}

	public String getEndWarrantyEndDate() {
		return endWarrantyEndDate;
	}

	public void setEndWarrantyEndDate(String endWarrantyEndDate) {
		this.endWarrantyEndDate = endWarrantyEndDate;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public Integer getSingleCustomerId() {
		return singleCustomerId;
	}

	public void setSingleCustomerId(Integer singleCustomerId) {
		this.singleCustomerId = singleCustomerId;
	}

	public String getExpiredWarrantyStatus() {
		return expiredWarrantyStatus;
	}

	public void setExpiredWarrantyStatus(String expiredWarrantyStatus) {
		this.expiredWarrantyStatus = expiredWarrantyStatus;
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

	public String getServiceArticle() {
		return serviceArticle;
	}

	public void setServiceArticle(String serviceArticle) {
		this.serviceArticle = serviceArticle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getEquipmentCharge() {
		return equipmentCharge;
	}

	public void setEquipmentCharge(Integer equipmentCharge) {
		this.equipmentCharge = equipmentCharge;
	}

	public String getEquipmentArea() {
		return equipmentArea;
	}

	public void setEquipmentArea(String equipmentArea) {
		this.equipmentArea = equipmentArea;
	}

	public String getEquipmentChargeName() {
		return equipmentChargeName;
	}

	public void setEquipmentChargeName(String equipmentChargeName) {
		this.equipmentChargeName = equipmentChargeName;
	}

	public List<CssUserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<CssUserBean> userList) {
		this.userList = userList;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<EquipmentHistoryProblem> getEquipmentHistoryProblemList() {
		return equipmentHistoryProblemList;
	}

	public void setEquipmentHistoryProblemList(List<EquipmentHistoryProblem> equipmentHistoryProblemList) {
		this.equipmentHistoryProblemList = equipmentHistoryProblemList;
	}

	public String getWarrantyDateStr() {
		return warrantyDateStr;
	}

	public void setWarrantyDateStr(String warrantyDateStr) {
		this.warrantyDateStr = warrantyDateStr;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public List<EquipmentContactBean> getEquipmentContactBeanList() {
		return equipmentContactBeanList;
	}

	public void setEquipmentContactBeanList(List<EquipmentContactBean> equipmentContactBeanList) {
		this.equipmentContactBeanList = equipmentContactBeanList;
	}

	public Integer[] getEquipmentContactIds() {
		return equipmentContactIds;
	}

	public void setEquipmentContactIds(Integer[] equipmentContactIds) {
		this.equipmentContactIds = equipmentContactIds;
	}

	public String[] getContactNames() {
		return contactNames;
	}

	public void setContactNames(String[] contactNames) {
		this.contactNames = contactNames;
	}

	public String[] getMobliePhones() {
		return mobliePhones;
	}

	public void setMobliePhones(String[] mobliePhones) {
		this.mobliePhones = mobliePhones;
	}

	public String[] getTelephones() {
		return telephones;
	}

	public void setTelephones(String[] telephones) {
		this.telephones = telephones;
	}

	public String[] getPositionVals() {
		return positionVals;
	}

	public void setPositionVals(String[] positionVals) {
		this.positionVals = positionVals;
	}

	public String[] getIsManagers() {
		return isManagers;
	}

	public void setIsManagers(String[] isManagers) {
		this.isManagers = isManagers;
	}

	public String[] getIsRefuseSmes() {
		return isRefuseSmes;
	}

	public void setIsRefuseSmes(String[] isRefuseSmes) {
		this.isRefuseSmes = isRefuseSmes;
	}

	public String[] getRemarkes() {
		return remarkes;
	}

	public void setRemarkes(String[] remarkes) {
		this.remarkes = remarkes;
	}

	public String getEquipmentContactId() {
		return equipmentContactId;
	}

	public void setEquipmentContactId(String equipmentContactId) {
		this.equipmentContactId = equipmentContactId;
	}

	public String getCustomerTerminalName() {
		return customerTerminalName;
	}

	public void setCustomerTerminalName(String customerTerminalName) {
		this.customerTerminalName = customerTerminalName;
	}

	public String getCustomerSigningName() {
		return customerSigningName;
	}

	public void setCustomerSigningName(String customerSigningName) {
		this.customerSigningName = customerSigningName;
	}

	public Integer getEquipmentCharge2() {
		return equipmentCharge2;
	}

	public void setEquipmentCharge2(Integer equipmentCharge2) {
		this.equipmentCharge2 = equipmentCharge2;
	}

	public String getEquipmentChargeName2() {
		return equipmentChargeName2;
	}

	public void setEquipmentChargeName2(String equipmentChargeName2) {
		this.equipmentChargeName2 = equipmentChargeName2;
	}

	public String[] getSexs() {
		return sexs;
	}

	public void setSexs(String[] sexs) {
		this.sexs = sexs;
	}

	public String getDepartmentId0() {
		return departmentId0;
	}

	public void setDepartmentId0(String departmentId0) {
		this.departmentId0 = departmentId0;
	}
	
}