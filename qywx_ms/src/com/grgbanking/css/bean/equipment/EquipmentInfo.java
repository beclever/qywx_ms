package com.grgbanking.css.bean.equipment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.grgbanking.css.bean.CrmCustomer;
import com.grgbanking.css.bean.Department;
import com.grgbanking.css.common.CssBaseEntity;

/**
 * 设备基本信息实体类 
 * @author zhangzhi  2009-6-12
 */
@Entity
@Table(name="V_CSS_EQUIPEMNT_INFO ")
public class EquipmentInfo extends CssBaseEntity implements java.io.Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer equipmentId;
	private String equipmentType;//设备类型
	private String manufacturer;//制造商
	private String country;//国家
	private Date productDate;//生产日期
	private String equipmentModel;//设备型号
	private String barCode;//条码编码
	private String equipmentStyle;//设备款式
	private String equipmentStatus;//设备状态
	private Department department;//服务站
	private String serialNumber;//序列号
	private String materialCode;//整机物料编码
	private CrmCustomer customer;  //服务客户
	private String branchName;//网点名称
	private Date installDate;//安装日期
	private Date moveDate;//移机日期
	private String installType;//安装类型
	private String installModel;//安装方式
	private Integer provinceId;//省份ID
	private Integer cityId;//城市ID
	private String installAddress;//安装地址
	private String atmManager;//ATM管理员
	private String branchPrincipal;//网点负责人
	private String atmManagerTel;//ATM管理员电话
	private String branchPrincipalTel;//网点负责人电话
	private String atmNumber;//ATM号
	private String encryptType;//加密方式
	private String bankNumber;//银行号
	private String bankTerminalNumber;//银行终端号
	private String atmcName;//ATMC名称
	private String atmcVersion;//ATMC版本
	private String atmcSpVersion;//SP版本(跨平台)
	private String localIp;//本地ID
	private String pip;//P端IP
	private String gateway;//网关
	private String subnetHideAddress;//子网掩码
	private String netProtocol;//网络协议
	private String dailyDealCount;//日均交易量
	private String dailyDealMoney;//日均交易金额
	private String acceptReportStatus;//验收报告状态
	private Date lastMaintainDate;//最后保养日期
	private Date lastRepairDate;//最后维修日期
	private String trafficLine;//乘车路线
	private String referenceCharge;//参考费用
	private String environmentDust;//环境
	private String environmentHumidity;
	private String environmentTemperature;
	private String environmentPowerSupply;
	private String environmentRainDefence;
	private String environmentSunshine;
	private String saleProperty;//销售属性
	private String saleContractNo;//销售合同号
	private String maintainContractNo;//维保合同号
	private String saleCustomerName;
	private String warrantyStatus;//保修类型（默认为免保，如果签了维保合同，则与维保合同关联）
	private Date warrantyStartDate;//保修开始日期（新机安装日期）
	private Date warrantyEndDate;//到保日期（在销售保修期内，根据销售合同有效期算出。在维保期内，根据维保合同有效期算出）
	private Date warrantyStatusExp;//丢单日期
	private Date shipDate; //发货日期
	private String batchNumber;//生产批次号
	private Integer warrantyMonth;   //免保期限
	private Integer continueWarrantyMonth;//续保期限
//	private List<EquipmentSpecialInfo> specialInfoList;//设备特殊信息
	
	private String operateSystem;//操作系统
	private String osVersion;//操作系统版本
	private String installProperty;//装机属性
	
	private Integer pm;//PM次数
	private Double openRate;//开通率
	private Double repairTime;//修复时间
	private Double responseTime;//响应时间
	private String expiredWarrantyStatus;//过保期后设备维修状态自动改为配置的状态
	
	private List<EquipmentLog> equipmentLoglist=new ArrayList<EquipmentLog>(); //设备日志
	private List<EquipmentSpecialInfo> equipmentSpecialList=new ArrayList<EquipmentSpecialInfo>();//设备特殊信息
	private List<EquipmentContact> equipmentContactList=new ArrayList<EquipmentContact>();//设备联系人列表
	
	private String hasRevisit;  //是否回访
	private Date lastRevisitTime;  //回访客户的最后时间
	private Integer countRevisit;//回访的次数
	
	private Integer equipmentCharge;//设备负责人
	private Integer equipmentCharge2;//设备第二负责人
	private String equipmentArea;//设备位置
	private String recordId;
	private String remarks;//备注
	private Double longitude;//经度（设备地理位置）
	private Double latitude;//纬度（设备地理位置）
	
	
	private String spNumber;
	
	private String bankPassReq;
	
	private Integer customerTerminalId;//终端客户名称
	
	private Date lastUsingDate;//状态变成在用的时间


	@Id  
	@SequenceGenerator(name = "EQUIPMENT_ID_SEQ_GENERATOR", sequenceName = "SEQ_EQUIPMENT_INFO", initialValue = 1, allocationSize = 1)   
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EQUIPMENT_ID_SEQ_GENERATOR")   
	@Column(name="EQUIPMENT_ID")
	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	@Column(name = "EQUIPMENT_TYPE")
	public String getEquipmentType() {
		return this.equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	@Column(name = "MANUFACTURER")
	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "COUNTRY")
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRODUCT_DATE")
	public Date getProductDate() {
		return this.productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	@Column(name = "EQUIPMENT_MODEL")
	public String getEquipmentModel() {
		return this.equipmentModel;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}

	@Column(name = "BAR_CODE")
	public String getBarCode() {
		return this.barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	@Column(name = "EQUIPMENT_STYLE")
	public String getEquipmentStyle() {
		return this.equipmentStyle;
	}

	public void setEquipmentStyle(String equipmentStyle) {
		this.equipmentStyle = equipmentStyle;
	}

	@Column(name = "EQUIPMENT_STATUS")
	public String getEquipmentStatus() {
		return this.equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}


	@Column(name = "BRANCH_NAME")
	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Column(name = "INSTALL_DATE")
	public Date getInstallDate() {
		return this.installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	@Column(name = "MOVE_DATE")
	public Date getMoveDate() {
		return this.moveDate;
	}

	public void setMoveDate(Date moveDate) {
		this.moveDate = moveDate;
	}

	@Column(name = "INSTALL_TYPE")
	public String getInstallType() {
		return this.installType;
	}

	public void setInstallType(String installType) {
		this.installType = installType;
	}

	@Column(name = "INSTALL_MODEL")
	public String getInstallModel() {
		return this.installModel;
	}

	public void setInstallModel(String installModel) {
		this.installModel = installModel;
	}

	@Column(name = "PROVINCE_ID")
	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "CITY_ID")
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "INSTALL_ADDRESS")
	public String getInstallAddress() {
		return this.installAddress;
	}

	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}

	@Column(name = "ATM_MANAGER")
	public String getAtmManager() {
		return this.atmManager;
	}

	public void setAtmManager(String atmManager) {
		this.atmManager = atmManager;
	}

	@Column(name = "BRANCH_PRINCIPAL")
	public String getBranchPrincipal() {
		return this.branchPrincipal;
	}

	public void setBranchPrincipal(String branchPrincipal) {
		this.branchPrincipal = branchPrincipal;
	}

	@Column(name = "ATM_MANAGER_TEL")
	public String getAtmManagerTel() {
		return this.atmManagerTel;
	}

	public void setAtmManagerTel(String atmManagerTel) {
		this.atmManagerTel = atmManagerTel;
	}

	@Column(name = "BRANCH_PRINCIPAL_TEL")
	public String getBranchPrincipalTel() {
		return this.branchPrincipalTel;
	}

	public void setBranchPrincipalTel(String branchPrincipalTel) {
		this.branchPrincipalTel = branchPrincipalTel;
	}

	@Column(name = "ATM_NUMBER")
	public String getAtmNumber() {
		return this.atmNumber;
	}

	public void setAtmNumber(String atmNumber) {
		this.atmNumber = atmNumber;
	}

	@Column(name = "ENCRYPT_TYPE")
	public String getEncryptType() {
		return this.encryptType;
	}

	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}

	@Column(name = "BANK_NUMBER")
	public String getBankNumber() {
		return this.bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	@Column(name = "BANK_TERMINAL_NUMBER")
	public String getBankTerminalNumber() {
		return this.bankTerminalNumber;
	}

	public void setBankTerminalNumber(String bankTerminalNumber) {
		this.bankTerminalNumber = bankTerminalNumber;
	}

	@Column(name = "LOCAL_IP")
	public String getLocalIp() {
		return this.localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	@Column(name = "PIP")
	public String getPip() {
		return this.pip;
	}

	public void setPip(String pip) {
		this.pip = pip;
	}

	@Column(name = "GATEWAY")
	public String getGateway() {
		return this.gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	@Column(name = "SUBNET_HIDE_ADDRESS")
	public String getSubnetHideAddress() {
		return this.subnetHideAddress;
	}

	public void setSubnetHideAddress(String subnetHideAddress) {
		this.subnetHideAddress = subnetHideAddress;
	}

	@Column(name = "NET_PROTOCOL")
	public String getNetProtocol() {
		return this.netProtocol;
	}

	public void setNetProtocol(String netProtocol) {
		this.netProtocol = netProtocol;
	}

	@Column(name = "DAILY_DEAL_COUNT")
	public String getDailyDealCount() {
		return this.dailyDealCount;
	}

	public void setDailyDealCount(String dailyDealCount) {
		this.dailyDealCount = dailyDealCount;
	}

	@Column(name = "DAILY_DEAL_MONEY")
	public String getDailyDealMoney() {
		return this.dailyDealMoney;
	}

	public void setDailyDealMoney(String dailyDealMoney) {
		this.dailyDealMoney = dailyDealMoney;
	}

	@Column(name = "ACCEPT_REPORT_STATUS")
	public String getAcceptReportStatus() {
		return this.acceptReportStatus;
	}

	public void setAcceptReportStatus(String acceptReportStatus) {
		this.acceptReportStatus = acceptReportStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_MAINTAIN_DATE")
	public Date getLastMaintainDate() {
		return this.lastMaintainDate;
	}

	public void setLastMaintainDate(Date lastMaintainDate) {
		this.lastMaintainDate = lastMaintainDate;
	}

	@Column(name = "TRAFFIC_LINE")
	public String getTrafficLine() {
		return this.trafficLine;
	}

	public void setTrafficLine(String trafficLine) {
		this.trafficLine = trafficLine;
	}

	@Column(name = "REFERENCE_CHARGE")
	public String getReferenceCharge() {
		return referenceCharge;
	}

	public void setReferenceCharge(String referenceCharge) {
		this.referenceCharge = referenceCharge;
	}

	@Column(name = "ENVIRONMENT_DUST")
	public String getEnvironmentDust() {
		return this.environmentDust;
	}

	public void setEnvironmentDust(String environmentDust) {
		this.environmentDust = environmentDust;
	}

	@Column(name = "ENVIRONMENT_HUMIDITY")
	public String getEnvironmentHumidity() {
		return this.environmentHumidity;
	}

	public void setEnvironmentHumidity(String environmentHumidity) {
		this.environmentHumidity = environmentHumidity;
	}

	@Column(name = "ENVIRONMENT_TEMPERATURE")
	public String getEnvironmentTemperature() {
		return this.environmentTemperature;
	}

	public void setEnvironmentTemperature(String environmentTemperature) {
		this.environmentTemperature = environmentTemperature;
	}

	@Column(name = "ENVIRONMENT_POWER_SUPPLY")
	public String getEnvironmentPowerSupply() {
		return this.environmentPowerSupply;
	}

	public void setEnvironmentPowerSupply(String environmentPowerSupply) {
		this.environmentPowerSupply = environmentPowerSupply;
	}

	@Column(name = "ENVIRONMENT_RAIN_DEFENCE")
	public String getEnvironmentRainDefence() {
		return this.environmentRainDefence;
	}

	public void setEnvironmentRainDefence(String environmentRainDefence) {
		this.environmentRainDefence = environmentRainDefence;
	}

	@Column(name = "ENVIRONMENT_SUNSHINE")
	public String getEnvironmentSunshine() {
		return this.environmentSunshine;
	}

	public void setEnvironmentSunshine(String environmentSunshine) {
		this.environmentSunshine = environmentSunshine;
	}

	@Column(name = "WARRANTY_START_DATE")
	public Date getWarrantyStartDate() {
		return this.warrantyStartDate;
	}

	public void setWarrantyStartDate(Date warrantyStartDate) {
		this.warrantyStartDate = warrantyStartDate;
	}

	@Column(name = "WARRANTY_END_DATE")
	public Date getWarrantyEndDate() {
		return this.warrantyEndDate;
	}

	public void setWarrantyEndDate(Date warrantyEndDate) {
		this.warrantyEndDate = warrantyEndDate;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.LAZY )
    @JoinColumn(name="DEPARTMENT_ID")
    @NotFound(action=NotFoundAction.IGNORE)
	public Department getDepartment() {
		return this.department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.LAZY )
    @JoinColumn(name="CUSTOMER_ID")
    @NotFound(action=NotFoundAction.IGNORE)
	public CrmCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(CrmCustomer customer) {
		this.customer = customer;
	}
	@Column(name = "SERIAL_NUMBER")
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	@Column(name = "SHIP_DATE")
	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	
	
	/**
	 * @return the saleContractNo
	 */
	@Column(name="SALE_CONTRACT_NUMBER")
	public String getSaleContractNo() {
		return saleContractNo;
	}

	/**
	 * @param saleContractNo the saleContractNo to set
	 */
	public void setSaleContractNo(String saleContractNo) {
		this.saleContractNo = saleContractNo;
	}

	
	@Column(name = "WARRANTY_STATUS")
	public String getWarrantyStatus() {
		return warrantyStatus;
	}

	public void setWarrantyStatus(String warrantyStatus) {
		this.warrantyStatus = warrantyStatus;
	}

	@Column(name="MATERIAL_CODE")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	@Column(name = "BATCH_NUMBER")
	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	@Column(name="WARRANTY_MONTH")
	public Integer getWarrantyMonth() {
		return warrantyMonth;
	}

	public void setWarrantyMonth(Integer warrantyMonth) {
		this.warrantyMonth = warrantyMonth;
	}

	@Column(name="OPERATE_SYSTEM")
	public String getOperateSystem() {
		return operateSystem;
	}

	public void setOperateSystem(String operateSystem) {
		this.operateSystem = operateSystem;
	}

	@Column(name="ATMC_NAME")
	public String getAtmcName() {
		return atmcName;
	}

	public void setAtmcName(String atmcName) {
		this.atmcName = atmcName;
	}

	@Column(name="ATMC_VERSION")
	public String getAtmcVersion() {
		return atmcVersion;
	}

	public void setAtmcVersion(String atmcVersion) {
		this.atmcVersion = atmcVersion;
	}

	@Column(name="ATMC_SP_VERSION")
	public String getAtmcSpVersion() {
		return atmcSpVersion;
	}

	public void setAtmcSpVersion(String atmcSpVersion) {
		this.atmcSpVersion = atmcSpVersion;
	}
	
	@Column(name="OS_VERSION")
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	/**
	 * @return the saleProperty
	 */
	@Column(name="SALE_PROPERTY")
	public String getSaleProperty() {
		return saleProperty;
	}

	/**
	 * @return the saleCustomerName
	 */
	@Column(name="SAP_CUSTOMER_NAME")
	public String getSaleCustomerName() {
		return saleCustomerName;
	}

	/**
	 * @param saleCustomerName the saleCustomerName to set
	 */
	public void setSaleCustomerName(String saleCustomerName) {
		this.saleCustomerName = saleCustomerName;
	}

	/**
	 * @param saleProperty the saleProperty to set
	 */
	public void setSaleProperty(String saleProperty) {
		this.saleProperty = saleProperty;
	}

	/**
	 * @return the installProperty
	 */
	@Column(name="INSTALL_PROPERTY")
	public String getInstallProperty() {
		return installProperty;
	}

	/**
	 * @param installProperty the installProperty to set
	 */
	public void setInstallProperty(String installProperty) {
		this.installProperty = installProperty;
	}

	@Column(name="LAST_REPAIR_DATE")
	public Date getLastRepairDate() {
		return lastRepairDate;
	}

	public void setLastRepairDate(Date lastRepairDate) {
		this.lastRepairDate = lastRepairDate;
	}

	@Column(name="PM")
	public Integer getPm() {
		return pm;
	}

	public void setPm(Integer pm) {
		this.pm = pm;
	}

	@Column(name="OPEN_RATE")
	public Double getOpenRate() {
		return openRate;
	}

	public void setOpenRate(Double openRate) {
		this.openRate = openRate;
	}

	@Column(name="REPAIR_TIME")
	public Double getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(Double repairTime) {
		this.repairTime = repairTime;
	}

	@Column(name="RESPONSE_TIME")
	public Double getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Double responseTime) {
		this.responseTime = responseTime;
	}

	@Column(name="MAINTAIN_CONTRACT_NO")
	public String getMaintainContractNo() {
		return maintainContractNo;
	}

	public void setMaintainContractNo(String maintainContractNo) {
		this.maintainContractNo = maintainContractNo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "EQUIPMENT_ID")
	@OrderBy("createDate asc")
	public List<EquipmentLog> getEquipmentLoglist() {
		return equipmentLoglist;
	}

	public void setEquipmentLoglist(List<EquipmentLog> equipmentLoglist) {
		this.equipmentLoglist = equipmentLoglist;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "EQUIPMENT_ID")
	@OrderBy("createDate asc")
	public List<EquipmentSpecialInfo> getEquipmentSpecialList() {
		return equipmentSpecialList;
	}

	public void setEquipmentSpecialList(
			List<EquipmentSpecialInfo> equipmentSpecialList) {
		this.equipmentSpecialList = equipmentSpecialList;
	}

	@Column(name="CONTINUE_WARRANTY_MONTH")
	public Integer getContinueWarrantyMonth() {
		return continueWarrantyMonth;
	}

	public void setContinueWarrantyMonth(Integer continueWarrantyMonth) {
		this.continueWarrantyMonth = continueWarrantyMonth;
	}
	
	@Column(name = "HAS_REVISIT")
	public String getHasRevisit() {
		return hasRevisit;
	}
	public void setHasRevisit(String hasRevisit) {
		this.hasRevisit = hasRevisit;
	}

	@Column(name = "LAST_REVISIT_TIME")
	public Date getLastRevisitTime() {
		return lastRevisitTime;
	}
	public void setLastRevisitTime(Date lastRevisitTime) {
		this.lastRevisitTime = lastRevisitTime;
	}

	@Column(name = "COUNT_REVISIT")
	public Integer getCountRevisit() {
		return countRevisit;
	}

	public void setCountRevisit(Integer countRevisit) {
		this.countRevisit = countRevisit;
	}
	@Column(name = "EXPIRED_WARRANTY_STATUS")
	public String getExpiredWarrantyStatus() {
		return expiredWarrantyStatus;
	}

	public void setExpiredWarrantyStatus(String expiredWarrantyStatus) {
		this.expiredWarrantyStatus = expiredWarrantyStatus;
	}

	@Column(name = "USER_ID")
	public Integer getEquipmentCharge() {
		return equipmentCharge;
	}

	public void setEquipmentCharge(Integer equipmentCharge) {
		this.equipmentCharge = equipmentCharge;
	}

	@Column(name = "USER_ID_SECOND")
	public Integer getEquipmentCharge2() {
		return equipmentCharge2;
	}

	public void setEquipmentCharge2(Integer equipmentCharge2) {
		this.equipmentCharge2 = equipmentCharge2;
	}

	@Column(name = "EQUIPMENT_AREA")
	public String getEquipmentArea() {
		return equipmentArea;
	}

	public void setEquipmentArea(String equipmentArea) {
		this.equipmentArea = equipmentArea;
	}

	@Column(name = "RECORD_ID")
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "LONGITUDE")
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Column(name = "LATITUDE")
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "EQUIPMENT_ID")
	@OrderBy("isManager DESC")
	public List<EquipmentContact> getEquipmentContactList() {
		return equipmentContactList;
	}

	public void setEquipmentContactList(List<EquipmentContact> equipmentContactList) {
		this.equipmentContactList = equipmentContactList;
	}

	@Column(name = "CUSTOMER_TERMINAL_ID")
	public Integer getCustomerTerminalId() {
		return customerTerminalId;
	}

	public void setCustomerTerminalId(Integer customerTerminalId) {
		this.customerTerminalId = customerTerminalId;
	}
	@Column(name = "WARRANTY_STATUS_EXP")
	public Date getWarrantyStatusExp() {
		return warrantyStatusExp;
	}

	public void setWarrantyStatusExp(Date warrantyStatusExp) {
		this.warrantyStatusExp = warrantyStatusExp;
	}
	@Column(name = "LAST_USING_DATE")
	public Date getLastUsingDate() {
		return lastUsingDate;
	}

	public void setLastUsingDate(Date lastUsingDate) {
		this.lastUsingDate = lastUsingDate;
	}
	
	@Column(name = "BANK_PASS_REQ")
	public String getBankPassReq() {
		return bankPassReq;
	}

	public void setBankPassReq(String bankPassReq) {
		this.bankPassReq = bankPassReq;
	}
	@Column(name = "SP_NUMBER")
	public String getSpNumber() {
		return spNumber;
	}

	public void setSpNumber(String spNumber) {
		this.spNumber = spNumber;
	}
	
	
	
	
	
}