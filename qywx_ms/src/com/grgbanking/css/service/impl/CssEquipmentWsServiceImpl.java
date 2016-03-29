package com.grgbanking.css.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grgbanking.common.utils.DateUtil;
import com.grgbanking.common.utils.DateUtils;
import com.grgbanking.common.utils.StringUtilsExtends;
import com.grgbanking.core.dao.UserDao;
import com.grgbanking.core.entity.DataDictionaryBean;
import com.grgbanking.core.entity.equipment.WsDeviceLocationBean;
import com.grgbanking.core.entity.equipment.WsEquipmentConfigBean;
import com.grgbanking.core.entity.equipment.WsEquipmentHistoryProblemBean;
import com.grgbanking.core.entity.equipment.WsEquipmentInfoBean;
import com.grgbanking.core.entity.equipment.WsEquipmentInfoDetailBean;
import com.grgbanking.core.entity.equipment.WsHistoryServerInfoBean;
import com.grgbanking.core.entity.equipment.WsResultEquipmentBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultEquipmentConfigBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentDetailBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryProblemBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryServerBean;
import com.grgbanking.css.bean.CrmCustomer;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.bean.Department;
import com.grgbanking.css.bean.equipment.EquipmentBean;
import com.grgbanking.css.bean.equipment.EquipmentConfig;
import com.grgbanking.css.bean.equipment.EquipmentHistoryProblem;
import com.grgbanking.css.bean.equipment.EquipmentInfo;
import com.grgbanking.css.bean.equipment.EquipmentSearchPape;
import com.grgbanking.css.bean.work.WorkFormBean;
import com.grgbanking.css.bean.work.WorkTask;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.dao.CrmCustomerInfoDAO;
import com.grgbanking.css.dao.CssUserDAO;
import com.grgbanking.css.dao.DepartmentDAO;
import com.grgbanking.css.dao.EquipmentConfigDAO;
import com.grgbanking.css.dao.EquipmentDAO;
import com.grgbanking.css.dao.EquipmentHistoryProblemDAO;
import com.grgbanking.css.dao.WorkTaskDao;
import com.grgbanking.css.load.DataDictionaryLoad;
import com.grgbanking.css.service.CssEquipmentWsService;
import com.grgbanking.css.service.EquipmentContactService;
import com.grgbanking.css.util.DataDictionaryConstants;

@Service("cssEquipmentWsService")
public class CssEquipmentWsServiceImpl implements CssEquipmentWsService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private CssUserDAO<CssUser, Integer> userDAO;
	@Resource(name = "userDao")
	private UserDao amsUserDao;
	@Autowired
	private EquipmentDAO<EquipmentInfo, Integer> equipmentDAO;
	@Autowired
	private DepartmentDAO<Department, Integer> departmentDAO;
	@Autowired
	private CrmCustomerInfoDAO<CrmCustomer, Integer> customerDAO;
	@Autowired
	private EquipmentHistoryProblemDAO<EquipmentHistoryProblem, Integer> equipmentHistoryProblemDAO;
	@Autowired
	private WorkTaskDao<WorkTask, Integer> workTaskDao;
	// @Autowired
	// private WorkformDAO<WorkForm, Integer> workformDAO;
	@Autowired
	private EquipmentConfigDAO<EquipmentConfig, Integer> equipmentConfigDAO;
	@Autowired
	private EquipmentContactService equipmentContactService;

	// @Autowired
	// private EquipmentService equipmentService;

	public WsResultEquipmentBean finddevices(String userId,
			String serialNumber, String customerName, String branchName,
			String installAddress, String ATMNumber, WsPage page) {
		String inteface = " finddevices(String userId,String serialNumber,String customerName, String branchName, String installAddress,String ATMNumber, WsPage page)";
		String visitor = userId;
		String params = serialNumber + "," + customerName + "," + branchName
				+ "," + installAddress + "," + ATMNumber;
		logger.info("[{}] visited the interface [{}]. param : [{}]"
				+ new String[] { visitor, inteface, params });

		if (StringUtils.isBlank(userId)) {
			WsResultEquipmentBean wsResultEquipmentBean = new WsResultEquipmentBean();
			wsResultEquipmentBean.setErrMsg("用户不存在，请检查。");
			wsResultEquipmentBean.setStatus(0);
			return wsResultEquipmentBean;
		}
		CssUser user = userDAO.getUserByLoginName(userId);
		if (user == null) {
			WsResultEquipmentBean wsResultEquipmentBean = new WsResultEquipmentBean();
			wsResultEquipmentBean.setErrMsg("用户不存在，请检查。");
			wsResultEquipmentBean.setStatus(0);
			return wsResultEquipmentBean;
		}
		EquipmentBean equipmentBean = new EquipmentBean();
		if (StringUtils.isNotBlank(customerName)
				&& !"银行".equals(customerName.trim())) {
			List<Integer> customerIdList = customerDAO
					.getCrmCustomerIds(customerName);
			if (customerIdList != null && customerIdList.size() > 0) {
				equipmentBean.setCustomerIds(StringUtils.join(customerIdList,
						","));
			}
		}
		List<Integer> depIdList = departmentDAO.getSAModeDepartmentIdAll(user
				.getDepartment().getDepartmentId());
		if (depIdList != null && depIdList.size() > 0) {
			equipmentBean.setDepartmentIds(StringUtils.join(depIdList, ","));
		}
		// else{
		// equipmentBean.setDepartmentId(user.getDepartment().getDepartmentId());
		// }
		equipmentBean.setDepartmentId(user.getDepartment().getDepartmentId());
		equipmentBean.setSerialNumber(serialNumber);
		equipmentBean.setBranchName(branchName);
		equipmentBean.setInstallAddress(installAddress);
		equipmentBean.setAtmNumber(ATMNumber);
		Page queryPage = new Page();
		if (page != null) {
			queryPage.setOrder(page.getOrder());
			queryPage.setPage(page.getPage());
			// queryPage.setPageCount();
			queryPage.setRecordCount(page.getRecordCount());
			if (page.getRows() != null) {
				queryPage.setRows(page.getRows());
			} else {
				queryPage.setRows(20);
			}
			queryPage.setSort(page.getSort());
		} else {
			queryPage.setRows(20);
		}
		List<EquipmentSearchPape> equipmentList = equipmentDAO
				.queryEquipmentListNew(queryPage, equipmentBean);
		if (queryPage.getRecordCount() != null && page != null) {
			if (page.getPage() > queryPage.getPageCount()) {
				WsResultEquipmentBean wsResultEquipmentBean = new WsResultEquipmentBean();
				wsResultEquipmentBean.setStatus(1);
				wsResultEquipmentBean.setPage(null);
				wsResultEquipmentBean.setEquipmentInfo(null);
				return wsResultEquipmentBean;
			}
		}
		List<WsEquipmentInfoBean> listBean = new ArrayList<WsEquipmentInfoBean>();
		for (EquipmentSearchPape equipmentSearchPape : equipmentList) {
			WsEquipmentInfoBean bean = new WsEquipmentInfoBean();
			bean.setBranchName(equipmentSearchPape.getBranchName());
			if (equipmentSearchPape.getDepartmentId() != null) {
				Department department = departmentDAO.get(equipmentSearchPape
						.getDepartmentId());
				if (department != null) {
					bean.setDepartmentName(department.getDepartmentName());
				}
			}
			if (equipmentSearchPape.getCustomerId() != null) {
				CrmCustomer customer = customerDAO.get(equipmentSearchPape
						.getCustomerId());
				if (customer != null) {
					bean.setCustomsName(customer.getCustomerName());
				}
			}
			bean.setEquipmentId(equipmentSearchPape.getEquipmentId().toString());
			bean.setEquipmentModel(DataDictionaryLoad.getText(
					DataDictionaryConstants.EQUIPMENT_MODEL,
					equipmentSearchPape.getEquipmentModel()));
			bean.setEquipmentStatus(DataDictionaryLoad.getText(
					DataDictionaryConstants.EQUIPMENT_STATUS,
					equipmentSearchPape.getEquipmentStatus()));
			bean.setInstallDate(DateUtil.formatDate(equipmentSearchPape
					.getInstallDate()));
			bean.setSerialNumber(equipmentSearchPape.getSerialNumber());
			bean.setWarrantyStatus(DataDictionaryLoad.getText(
					DataDictionaryConstants.EQUIPMENT_WARRANTY_STATUS,
					equipmentSearchPape.getWarrantyStatus()));
			EquipmentInfo equipmentInfo = equipmentDAO.get(equipmentSearchPape
					.getEquipmentId());
			if (equipmentInfo != null) {
				bean.setAtmNumber(equipmentInfo.getAtmNumber());
				bean.setInstallAddress(equipmentInfo.getInstallAddress());
				bean.setEquipmentType(DataDictionaryLoad.getText(
						DataDictionaryConstants.EQUIPMENT_TYPE,
						equipmentInfo.getEquipmentType()));
			}
			listBean.add(bean);
		}
		WsResultEquipmentBean wsResultEquipmentBean = new WsResultEquipmentBean();
		wsResultEquipmentBean.setStatus(1);
		WsPage wsPage = new WsPage();
		wsPage.setOrder(queryPage.getOrder());
		wsPage.setPage(queryPage.getPage());
		wsPage.setRecordCount(queryPage.getRecordCount());
		wsPage.setRows(queryPage.getRows());
		wsPage.setSort(queryPage.getSort());
		wsResultEquipmentBean.setPage(wsPage);
		wsResultEquipmentBean.setEquipmentInfo(listBean);
		return wsResultEquipmentBean;
	}

	public WsResultEquipmentDetailBean getDeviceDetail(String userId,
			String serialNumber) {
		String inteface = "  getDeviceDetail(String userId,String serialNumber)";
		String visitor = userId;
		String params = serialNumber;
		logger.info("[{}] visited the interface [{}]. param : [{}]"
				+ new String[] { visitor, inteface, params });

		if (StringUtils.isNotBlank(userId)) {
			CssUser user = userDAO.getUserByLoginName(userId);
			if (user == null) {
				WsResultEquipmentDetailBean wsResultEquipmentDetailBean = new WsResultEquipmentDetailBean();
				wsResultEquipmentDetailBean.setErrMsg("用户不存在，请检查。");
				wsResultEquipmentDetailBean.setStatus(0);
				return wsResultEquipmentDetailBean;
			}
		}

		if (StringUtils.isNotBlank(serialNumber)) {

			EquipmentInfo equipmentInfo = equipmentDAO
					.getEquipmentInfo(serialNumber);
			// 如果序列号查不到设备就当作Atm查询
			if (null == equipmentInfo) {
				List<EquipmentInfo> infos = equipmentDAO
						.queryEquipmentByAtmNumber(serialNumber);
				if (null != infos && infos.size() > 0) {
					equipmentInfo = infos.get(0);
				}
			}

			if (equipmentInfo == null) {
				WsResultEquipmentDetailBean wsResultEquipmentDetailBean = new WsResultEquipmentDetailBean();
				wsResultEquipmentDetailBean.setErrMsg("设备" + serialNumber
						+ "不存在，请检查。");
				wsResultEquipmentDetailBean.setStatus(0);
				return wsResultEquipmentDetailBean;
			}
			WsEquipmentInfoDetailBean wsEquipmentInfoDetailBean = new WsEquipmentInfoDetailBean();
			wsEquipmentInfoDetailBean.setATMCName(DataDictionaryLoad.getText(
					DataDictionaryConstants.EQUIPMENT_ATMC_NAME,
					equipmentInfo.getAtmcName()));
			wsEquipmentInfoDetailBean.setAtmcSpVersion(equipmentInfo
					.getAtmcSpVersion());
			wsEquipmentInfoDetailBean.setATMCVersion(equipmentInfo
					.getAtmcVersion());
			wsEquipmentInfoDetailBean.setAtmManager(equipmentInfo
					.getAtmManager());
			wsEquipmentInfoDetailBean.setAtmManagerTel(equipmentInfo
					.getAtmManagerTel());
			wsEquipmentInfoDetailBean
					.setATMNumber(equipmentInfo.getAtmNumber());
			wsEquipmentInfoDetailBean.setBankNumber(equipmentInfo
					.getBankNumber());
			wsEquipmentInfoDetailBean.setBankTerminalNumber(equipmentInfo
					.getBankTerminalNumber());
			wsEquipmentInfoDetailBean.setBranchName(equipmentInfo
					.getBranchName());
			if (equipmentInfo.getEquipmentCharge() != null) {
				CssUser usCharge = userDAO.get(equipmentInfo
						.getEquipmentCharge());
				if (usCharge != null) {
					wsEquipmentInfoDetailBean.setChief(usCharge.getName());
					wsEquipmentInfoDetailBean.setChiefUserId(usCharge
							.getUserId());
					wsEquipmentInfoDetailBean.setChiefPhone(usCharge
							.getMobilephone());
				}
				/*
				 * UserInfo userInfo =
				 * amsUserDao.get(equipmentInfo.getEquipmentCharge());
				 * if(userInfo!=null){
				 * wsEquipmentInfoDetailBean.setChief(userInfo.getUserName());
				 * wsEquipmentInfoDetailBean
				 * .setChiefUserId(userInfo.getId().intValue());
				 * wsEquipmentInfoDetailBean
				 * .setChiefPhone(userInfo.getMobilePhone()); }
				 */
			}
			if (equipmentInfo.getEquipmentCharge2() != null) {
				CssUser usCharge = userDAO.get(equipmentInfo
						.getEquipmentCharge2());
				if (usCharge != null) {
					wsEquipmentInfoDetailBean.setChief2(usCharge.getName());
					wsEquipmentInfoDetailBean.setChiefUserId2(usCharge
							.getUserId());
					wsEquipmentInfoDetailBean.setChiefPhone2(usCharge
							.getMobilephone());
				}
				/*
				 * UserInfo userInfo =
				 * amsUserDao.get(equipmentInfo.getEquipmentCharge2());
				 * if(userInfo!=null){
				 * wsEquipmentInfoDetailBean.setChief2(userInfo.getUserName());
				 * wsEquipmentInfoDetailBean
				 * .setChiefUserId2(userInfo.getId().intValue());
				 * wsEquipmentInfoDetailBean
				 * .setChiefPhone2(userInfo.getMobilePhone()); }
				 */
			}
			if (StringUtilsExtends.isNotBlankAndEmpty(equipmentInfo
					.getBranchPrincipal())) {
				wsEquipmentInfoDetailBean.setBranchPrincipal(equipmentInfo
						.getBranchPrincipal().trim());
			}
			if (StringUtilsExtends.isNotBlankAndEmpty(equipmentInfo
					.getBranchPrincipalTel())) {
				wsEquipmentInfoDetailBean.setBranchPrincipalTel(equipmentInfo
						.getBranchPrincipalTel().trim());
			}
			if (StringUtilsExtends.isNotBlankAndEmpty(equipmentInfo
					.getEquipmentArea())) {
				wsEquipmentInfoDetailBean.setEquipmentArea(equipmentInfo
						.getEquipmentArea());
			}
			wsEquipmentInfoDetailBean.setConsultWay(equipmentInfo
					.getReferenceCharge());
			wsEquipmentInfoDetailBean.setCustomsName(equipmentInfo
					.getCustomer() != null ? equipmentInfo.getCustomer()
					.getCustomerName() : "");
			WsDeviceLocationBean wsDeviceLocationBean = new WsDeviceLocationBean();
			wsDeviceLocationBean
					.setLatitude(equipmentInfo.getLatitude() != null ? equipmentInfo
							.getLatitude().toString() : "");
			wsDeviceLocationBean.setLocation(DataDictionaryLoad.getText(
					DataDictionaryConstants.EQUIPMENT_AREA,
					equipmentInfo.getEquipmentArea()));
			wsDeviceLocationBean
					.setLongitude(equipmentInfo.getLongitude() != null ? equipmentInfo
							.getLongitude().toString() : "");
			wsEquipmentInfoDetailBean.setDeviceLocation(wsDeviceLocationBean);
			wsEquipmentInfoDetailBean.setDeviceStatus(DataDictionaryLoad
					.getText(DataDictionaryConstants.EQUIPMENT_STATUS,
							equipmentInfo.getEquipmentStatus()));
			// 设备历史遗留问题
			List<EquipmentHistoryProblem> equipmentHistoryProblemList = equipmentHistoryProblemDAO
					.findByEquipmentId(equipmentInfo.getEquipmentId(), "");
			List<WsEquipmentHistoryProblemBean> eHistoryProblemList = new ArrayList<WsEquipmentHistoryProblemBean>();
			if (equipmentHistoryProblemList != null
					&& equipmentHistoryProblemList.size() > 0) {
				for (EquipmentHistoryProblem equipmentHistoryProblem : equipmentHistoryProblemList) {
					WsEquipmentHistoryProblemBean wsEquipmentHistoryProblemBean = new WsEquipmentHistoryProblemBean();
					wsEquipmentHistoryProblemBean
							.setDealContent(equipmentHistoryProblem
									.getDescription());
					wsEquipmentHistoryProblemBean
							.setHistoryProblemId(equipmentHistoryProblem
									.getHistoryProblemId().toString());
					wsEquipmentHistoryProblemBean
							.setLevelId(equipmentHistoryProblem.getLevel());
					wsEquipmentHistoryProblemBean
							.setSubmitProblemUserName(equipmentHistoryProblem
									.getRecordPerson());
					wsEquipmentHistoryProblemBean
							.setStatus(equipmentHistoryProblem.getStatus());
					eHistoryProblemList.add(wsEquipmentHistoryProblemBean);
				}
			}
			wsEquipmentInfoDetailBean.seteHistoryProblem(eHistoryProblemList);
			wsEquipmentInfoDetailBean.setEncryptModel(DataDictionaryLoad
					.getText(DataDictionaryConstants.EQUIPMENT_ENCRYPT_TYPE,
							equipmentInfo.getEncryptType()));
			wsEquipmentInfoDetailBean.setEquipmentId(equipmentInfo
					.getEquipmentId().toString());
			wsEquipmentInfoDetailBean.setEquipmentModel(DataDictionaryLoad
					.getText(DataDictionaryConstants.EQUIPMENT_MODEL,
							equipmentInfo.getEquipmentModel()));
			wsEquipmentInfoDetailBean.setEquipmentType(DataDictionaryLoad
					.getText(DataDictionaryConstants.EQUIPMENT_TYPE,
							equipmentInfo.getEquipmentType()));
			wsEquipmentInfoDetailBean.setGateway(equipmentInfo.getGateway());
			// 历史服务信息
			WorkFormBean workformBean = new WorkFormBean();
			workformBean.setEquipmentId(equipmentInfo.getEquipmentId());
			//workformBean.setStatus(ServicesConstants.STATUS_WORKFORM_COMPLETED);
			List<WorkTask> workTaskList = workTaskDao.queryWorkFormList(null,
					workformBean);
			List<WsHistoryServerInfoBean> historyServerInfoList = new ArrayList<WsHistoryServerInfoBean>();
			if (workTaskList != null && workTaskList.size() > 0) {
				for (WorkTask WorkTask : workTaskList) {
					WsHistoryServerInfoBean wsHistoryServerInfoBean = new WsHistoryServerInfoBean();
					wsHistoryServerInfoBean.setEngineerName(WorkTask
							.getEngineerName());
					if (WorkTask.getWorkForm() != null) {
						wsHistoryServerInfoBean.setFinishTime(DateUtil
								.formatDate(WorkTask.getWorkForm()
										.getWorkFinishDate()));
					}
					wsHistoryServerInfoBean.setTaskType(DataDictionaryLoad
							.getText(
									DataDictionaryConstants.SERVICES_TASK_TYPE,
									WorkTask.getTaskType()));
					wsHistoryServerInfoBean.setWorkContent(WorkTask
							.getDescription());
					historyServerInfoList.add(wsHistoryServerInfoBean);
				}
			}
			wsEquipmentInfoDetailBean
					.setHistoryServerInfo(historyServerInfoList);
			wsEquipmentInfoDetailBean.setInstallAddress(equipmentInfo
					.getInstallAddress());
			wsEquipmentInfoDetailBean.setInstallDate(DateUtil
					.formatDate(equipmentInfo.getInstallDate()));
			wsEquipmentInfoDetailBean.setInstallModel(DataDictionaryLoad
					.getText(DataDictionaryConstants.EQUIPMENT_INSTALL_MODEL,
							equipmentInfo.getInstallModel()));
			wsEquipmentInfoDetailBean.setInstallType(DataDictionaryLoad
					.getText(DataDictionaryConstants.EQUIPMENT_INSTALL_TYPE,
							equipmentInfo.getInstallType()));
			wsEquipmentInfoDetailBean.setLocalIP(equipmentInfo.getLocalIp());
			wsEquipmentInfoDetailBean.setManufcturer(DataDictionaryLoad
					.getText(DataDictionaryConstants.EQUIPMENT_MANUFACTURER,
							equipmentInfo.getManufacturer()));
			wsEquipmentInfoDetailBean.setNetProtocol(equipmentInfo
					.getNetProtocol());
			wsEquipmentInfoDetailBean.setOperationSystem(DataDictionaryLoad
					.getText(DataDictionaryConstants.EQUIPMENT_OS,
							equipmentInfo.getOperateSystem()));
			wsEquipmentInfoDetailBean
					.setOsVersion(equipmentInfo.getOsVersion());
			wsEquipmentInfoDetailBean.setPip(equipmentInfo.getPip());
			wsEquipmentInfoDetailBean.setReferenceCharge(equipmentInfo
					.getTrafficLine());
			wsEquipmentInfoDetailBean.setRepairContractNumber(equipmentInfo
					.getMaintainContractNo());
			wsEquipmentInfoDetailBean.setRepairEndDate(DateUtil
					.formatDate(equipmentInfo.getWarrantyEndDate()));
			wsEquipmentInfoDetailBean.setRepairStartDate(DateUtil
					.formatDate(equipmentInfo.getWarrantyStartDate()));
			wsEquipmentInfoDetailBean.setRepairType(DataDictionaryLoad.getText(
					DataDictionaryConstants.EQUIPMENT_WARRANTY_STATUS,
					equipmentInfo.getWarrantyStatus()));
			wsEquipmentInfoDetailBean.setSalesContractNumber(equipmentInfo
					.getSaleContractNo());
			wsEquipmentInfoDetailBean.setSalesProperty(DataDictionaryLoad
					.getText(DataDictionaryConstants.EQUIPMENT_SALE_PROPERTY,
							equipmentInfo.getSaleProperty()));
			wsEquipmentInfoDetailBean.setSerialNumber(equipmentInfo
					.getSerialNumber());
			wsEquipmentInfoDetailBean.setSubnet_hide_address(equipmentInfo
					.getSubnetHideAddress());

			wsEquipmentInfoDetailBean.setSparepartsInfo(null);

			/* 设置五个属性的字典列表start */
			// 设备负责人
			wsEquipmentInfoDetailBean.setChiefList(getStationPerson(String
					.valueOf(equipmentInfo.getDepartment().getDepartmentId())));
			// 设备位置
			wsEquipmentInfoDetailBean
					.setEquipmentAreaList(DataDictionaryLoad
							.getDataDictionaryBeanListByKey(DataDictionaryConstants.EQUIPMENT_AREA));
			// 安装类型
			wsEquipmentInfoDetailBean
					.setInstallTypeList(DataDictionaryLoad
							.getDataDictionaryBeanListByKey(DataDictionaryConstants.EQUIPMENT_INSTALL_TYPE));
			// 安装方式
			wsEquipmentInfoDetailBean
					.setInstallModelList(DataDictionaryLoad
							.getDataDictionaryBeanListByKey(DataDictionaryConstants.EQUIPMENT_INSTALL_MODEL));
			// 网络连接协议
			wsEquipmentInfoDetailBean
					.setNetProtocolList(DataDictionaryLoad
							.getDataDictionaryBeanListByKey(DataDictionaryConstants.EQUIPMENT_NETPROTOCOL));
			/* 设置五个属性的字典列表end */

			WsResultEquipmentDetailBean wsResultEquipmentDetailBean = new WsResultEquipmentDetailBean();
			wsResultEquipmentDetailBean.setStatus(1);
			wsResultEquipmentDetailBean
					.setWsEquipmentInfoDetailBean(wsEquipmentInfoDetailBean);
			//logger.info(JSONObject.fromObject(wsResultEquipmentDetailBean).toString());
			return wsResultEquipmentDetailBean;
		} else {
			WsResultEquipmentDetailBean wsResultEquipmentDetailBean = new WsResultEquipmentDetailBean();
			wsResultEquipmentDetailBean.setErrMsg("设备为空，请检查。");
			wsResultEquipmentDetailBean.setStatus(0);
			return wsResultEquipmentDetailBean;
		}
	}

	// 设备配置子级添加
	private List<WsEquipmentConfigBean> getChildrenConfigList(
			List<EquipmentConfig> childrenList) {
		List<WsEquipmentConfigBean> resultList = new ArrayList<WsEquipmentConfigBean>();
		for (EquipmentConfig equipmentConfig : childrenList) {
			WsEquipmentConfigBean wsEquipmentConfigBean = new WsEquipmentConfigBean();
			wsEquipmentConfigBean.setParentId(equipmentConfig.getParent()
					.getEquipmentConfigId());
			wsEquipmentConfigBean.setSparepartId(equipmentConfig
					.getEquipmentConfigId());
			wsEquipmentConfigBean.setSparepartName(equipmentConfig
					.getSparepart().getMaterialName());
			wsEquipmentConfigBean.setMaterialCode(equipmentConfig
					.getSparepart().getMaterialCode());
			wsEquipmentConfigBean.setBarCode(equipmentConfig.getSparepart()
					.getBarCode());
			// wsEquipmentConfigBean.setSparepartType(DataDictionaryLoad.getText(DataDictionaryConstants.EQUIPMENT_CONFIG_NOW_MODULE_TYPE,equipmentConfig.getSparepart().getModuleType()));
			if (equipmentConfig.getChildrenList() != null
					&& equipmentConfig.getChildrenList().size() > 0) {
				wsEquipmentConfigBean
						.setChildren(getChildrenConfigList(equipmentConfig
								.getChildrenList()));
			}
			resultList.add(wsEquipmentConfigBean);
		}
		return resultList;
	}

	public WsResultEquipmentConfigBean getDeviceSparepartsInfo(
			String loginCode, String serialNumber) {
		String inteface = " getDeviceSparepartsInfo(String loginCode, String serialNumber)";
		String visitor = loginCode;
		String params = serialNumber;
		logger.info("[{}] visited the interface [{}]. param : [{}]"
				+ new String[] { visitor, inteface, params });

		if (StringUtils.isNotBlank(loginCode)) {
			CssUser user = userDAO.getUserByLoginName(loginCode);
			if (user == null) {
				WsResultEquipmentConfigBean wsResultEquipmentConfigBean = new WsResultEquipmentConfigBean();
				wsResultEquipmentConfigBean.setErrMsg("用户不存在，请检查。");
				wsResultEquipmentConfigBean.setStatus(0);
				return wsResultEquipmentConfigBean;
			}
		}

		if (StringUtils.isNotBlank(serialNumber)) {
			// 设备部件配置信息
			List<EquipmentConfig> equipmentConfigList = equipmentConfigDAO
					.getEquipmentConfigByEquipmentSerialNumber(serialNumber);
			List<WsEquipmentConfigBean> sparepartsInfoList = new ArrayList<WsEquipmentConfigBean>();
			for (EquipmentConfig equipmentConfig : equipmentConfigList) {
				WsEquipmentConfigBean wsEquipmentConfigBean = new WsEquipmentConfigBean();
				wsEquipmentConfigBean.setParentId(null);
				wsEquipmentConfigBean.setSparepartId(equipmentConfig
						.getEquipmentConfigId());
				wsEquipmentConfigBean.setSparepartName(equipmentConfig
						.getSparepart().getMaterialName());
				wsEquipmentConfigBean.setMaterialCode(equipmentConfig
						.getSparepart().getMaterialCode());
				wsEquipmentConfigBean.setBarCode(equipmentConfig.getSparepart()
						.getBarCode());
				if (equipmentConfig.getSparepart().getModuleType() != null) {
					wsEquipmentConfigBean
							.setSparepartType(DataDictionaryLoad
									.getText(
											DataDictionaryConstants.EQUIPMENT_CONFIG_NOW_MODULE_TYPE,
											equipmentConfig.getSparepart()
													.getModuleType()));
				} else {
					wsEquipmentConfigBean.setSparepartType("其它");
				}
				if (equipmentConfig.getChildrenList() != null
						&& equipmentConfig.getChildrenList().size() > 0) {
					wsEquipmentConfigBean
							.setChildren(getChildrenConfigList(equipmentConfig
									.getChildrenList()));
				}

				sparepartsInfoList.add(wsEquipmentConfigBean);

			}
			WsResultEquipmentConfigBean wsResultEquipmentConfigBean = new WsResultEquipmentConfigBean();
			wsResultEquipmentConfigBean.setStatus(1);
			wsResultEquipmentConfigBean.setSparepartsInfo(sparepartsInfoList);
			return wsResultEquipmentConfigBean;
		} else {
			WsResultEquipmentConfigBean wsResultEquipmentConfigBean = new WsResultEquipmentConfigBean();
			wsResultEquipmentConfigBean.setErrMsg("设备为空，请检查。");
			wsResultEquipmentConfigBean.setStatus(0);
			return wsResultEquipmentConfigBean;
		}
	}

	private List<DataDictionaryBean> getStationPerson(String stationId) {
		List<DataDictionaryBean> userList = new ArrayList<DataDictionaryBean>();
		if (StringUtilsExtends.isNotBlankAndEmpty(stationId)) {
			List<CssUser> listUser = userDAO.getUserListByDepartmentId(Integer
					.valueOf(stationId.trim()));
			if (listUser.size() > 0) {
				DataDictionaryBean dataDictionaryBean = null;
				for (CssUser user : listUser) {
					dataDictionaryBean = new DataDictionaryBean(
							String.valueOf(user.getUserId()), user.getName());
					userList.add(dataDictionaryBean);
				}
			}
			return userList;
		}
		return null;
	}

	public WsResultEquipmentHistoryServerBean getDeviceServiceHistoryInfo(
			String loginCode, String serialNumber) {

		String inteface = " getDeviceServiceHistoryInfo(String loginCode,String serialNumber) ";
		String visitor = loginCode;
		String params = serialNumber;
		logger.info("[{}] visited the interface [{}]. param : [{}]"
				+ new String[] { visitor, inteface, params });

		if (StringUtils.isNotBlank(serialNumber)) {

			WorkFormBean workformBean = new WorkFormBean();
			workformBean.setSerialNumber(serialNumber);
			//workformBean.setStatus(ServicesConstants.STATUS_WORKFORM_COMPLETED);
			List<WorkTask> workTaskList = workTaskDao.queryWorkFormList(null,
					workformBean);
			List<WsHistoryServerInfoBean> historyServerInfoList = new ArrayList<WsHistoryServerInfoBean>();
			if (workTaskList != null && workTaskList.size() > 0) {
				for (WorkTask WorkTask : workTaskList) {
					WsHistoryServerInfoBean wsHistoryServerInfoBean = new WsHistoryServerInfoBean();
					wsHistoryServerInfoBean.setEngineerName(WorkTask
							.getEngineerName());
					if (WorkTask.getWorkForm() != null) {
						wsHistoryServerInfoBean.setFinishTime(DateUtil
								.formatDate(WorkTask.getWorkForm()
										.getWorkFinishDate()));
					}
					wsHistoryServerInfoBean.setTaskType(DataDictionaryLoad
							.getText(
									DataDictionaryConstants.SERVICES_TASK_TYPE,
									WorkTask.getTaskType()));
					wsHistoryServerInfoBean.setWorkContent(WorkTask
							.getDescription());
					historyServerInfoList.add(wsHistoryServerInfoBean);
				}
			}
			WsResultEquipmentHistoryServerBean wsResultEquipmentHistoryServerBean = new WsResultEquipmentHistoryServerBean();
			wsResultEquipmentHistoryServerBean.setStatus(1);
			wsResultEquipmentHistoryServerBean
					.setHistoryServerInfo(historyServerInfoList);
			return wsResultEquipmentHistoryServerBean;

		} else {
			WsResultEquipmentHistoryServerBean wsResultEquipmentHistoryServerBean = new WsResultEquipmentHistoryServerBean();
			wsResultEquipmentHistoryServerBean.setErrMsg("设备为空，请检查。");
			wsResultEquipmentHistoryServerBean.setStatus(0);
			return wsResultEquipmentHistoryServerBean;
		}

	}

	public WsResultEquipmentHistoryProblemBean getDeviceLegacyProblemInfo(
			String loginCode, String serialNumber) {

		String inteface = " getDeviceLegacyProblemInfo(String loginCode,String serialNumber)";
		String visitor = loginCode;
		String params = serialNumber;
		logger.info("[{}] visited the interface [{}]. param : [{}]"
				+ new String[] { visitor, inteface, params });

		if (StringUtils.isNotBlank(serialNumber)) {
			EquipmentInfo equipmentInfo = equipmentDAO
					.getEquipmentInfo(serialNumber);
			if (equipmentInfo == null) {
				WsResultEquipmentHistoryProblemBean wsResultEquipmentHistoryProblemBean = new WsResultEquipmentHistoryProblemBean();
				wsResultEquipmentHistoryProblemBean.setErrMsg("设备"
						+ serialNumber + "不存在，请检查。");
				wsResultEquipmentHistoryProblemBean.setStatus(0);
				return wsResultEquipmentHistoryProblemBean;
			}
			List<EquipmentHistoryProblem> equipmentHistoryProblemList = equipmentHistoryProblemDAO
					.findByEquipmentId(equipmentInfo.getEquipmentId(), "");
			List<WsEquipmentHistoryProblemBean> eHistoryProblemList = new ArrayList<WsEquipmentHistoryProblemBean>();
			if (equipmentHistoryProblemList != null
					&& equipmentHistoryProblemList.size() > 0) {
				for (EquipmentHistoryProblem equipmentHistoryProblem : equipmentHistoryProblemList) {
					WsEquipmentHistoryProblemBean wsEquipmentHistoryProblemBean = new WsEquipmentHistoryProblemBean();
					wsEquipmentHistoryProblemBean
							.setLevelId(equipmentHistoryProblem.getLevel());
					wsEquipmentHistoryProblemBean
							.setStatus(equipmentHistoryProblem.getStatus());
					wsEquipmentHistoryProblemBean
							.setRecordPerson(equipmentHistoryProblem
									.getRecordPerson());
					wsEquipmentHistoryProblemBean
							.setDescription(equipmentHistoryProblem
									.getDescription());
					wsEquipmentHistoryProblemBean.setRecordTime(DateUtils
							.date2Str(equipmentHistoryProblem.getRecordTime(),"yyyy-MM-dd"));
					wsEquipmentHistoryProblemBean.setSolveTime(DateUtils
							.date2Str(equipmentHistoryProblem.getSolveTime(),"yyyy-MM-dd"));
					eHistoryProblemList.add(wsEquipmentHistoryProblemBean);
				}
			}
			WsResultEquipmentHistoryProblemBean wsResultEquipmentHistoryProblemBean = new WsResultEquipmentHistoryProblemBean();
			wsResultEquipmentHistoryProblemBean.setStatus(1);
			wsResultEquipmentHistoryProblemBean
					.seteHistoryProblem(eHistoryProblemList);
			return wsResultEquipmentHistoryProblemBean;
		} else {
			WsResultEquipmentHistoryProblemBean wsResultEquipmentHistoryProblemBean = new WsResultEquipmentHistoryProblemBean();
			wsResultEquipmentHistoryProblemBean.setErrMsg("设备为空，请检查。");
			wsResultEquipmentHistoryProblemBean.setStatus(0);
			return wsResultEquipmentHistoryProblemBean;
		}

	}

	/*
	 * public WsResultBean doUpdateEquipmentInfo(WsEquipmentInfoSaveBean
	 * wsEquipmentInfoSaveBean){ String equipmentId =
	 * wsEquipmentInfoSaveBean.getEquipmentId();// 设备编号 String(Y)
	 * if(StringUtilsExtends.isBlankOrEmpty(equipmentId)){ return
	 * WsResultBeanUtils.returnError("equipmentId参数传递为空！"); }else{ equipmentId =
	 * equipmentId.trim(); } String serialNumber =
	 * wsEquipmentInfoSaveBean.getSerialNumber();// 设备序列号 String(Y)
	 * if(StringUtilsExtends.isBlankOrEmpty(serialNumber)){ return
	 * WsResultBeanUtils.returnError("serialNumber参数传递为空！"); }else{ serialNumber
	 * = serialNumber.trim(); } EquipmentInfo equipmentInfo =
	 * equipmentDAO.get(Integer.valueOf(equipmentId.trim())); if(equipmentInfo
	 * == null){ return WsResultBeanUtils.returnError(new
	 * StringBuilder("根据参数equipmentId【").append(equipmentId)
	 * .append("】和serialNumber【"
	 * ).append(serialNumber).append("】无法找到设备档案信息！").toString()); }
	 * if(!serialNumber.equals(equipmentInfo.getSerialNumber())){ return
	 * WsResultBeanUtils.returnError(new
	 * StringBuilder("根据参数equipmentId【").append(equipmentId)
	 * .append("】和serialNumber【"
	 * ).append(serialNumber).append("】无法找到设备档案信息！").toString()); }
	 * logger.info(JSONObject.fromObject(wsEquipmentInfoSaveBean).toString());
	 *//**
	 * WsEquipmentInfoSaveBean wsEquipmentInfoSaveBeanFromDB = new
	 * WsEquipmentInfoSaveBean(equipmentInfo.getSerialNumber().toString(),
	 * equipmentInfo.getEquipmentId().toString(),
	 * equipmentInfo.getEquipmentCharge() == null ? null :
	 * equipmentInfo.getEquipmentCharge().toString(),
	 * equipmentInfo.getInstallAddress(), equipmentInfo.getBranchName(),
	 * equipmentInfo.getBranchPrincipal(),
	 * equipmentInfo.getBranchPrincipalTel(), equipmentInfo.getInstallType(),
	 * equipmentInfo.getInstallModel(), equipmentInfo.getAtmManager(),
	 * equipmentInfo.getAtmManagerTel(), equipmentInfo.getAtmNumber(),
	 * equipmentInfo.getBankNumber(), equipmentInfo.getBankTerminalNumber(),
	 * equipmentInfo.getNetProtocol(), equipmentInfo.getLocalIp(),
	 * equipmentInfo.getPip(), equipmentInfo.getGateway(),
	 * equipmentInfo.getSubnetHideAddress(), equipmentInfo.getEquipmentArea(),
	 * wsEquipmentInfoSaveBean.getUserId(),
	 * wsEquipmentInfoSaveBean.getUserName());
	 * 
	 * String differences = CommonUtils.compare(wsEquipmentInfoSaveBeanFromDB,
	 * wsEquipmentInfoSaveBean, null, null, "userId", "userName");
	 */
	/*
	 * StringBuffer content=new StringBuffer("");
	 * //doCompareStringLog("设备负责人",equipmentInfo
	 * .getEquipmentCharge().toString()
	 * ,wsEquipmentInfoSaveBean.getChief().toString(),content); Integer
	 * chiefInt=null; if(wsEquipmentInfoSaveBean.getChief()!=null){
	 * chiefInt=Integer.parseInt(wsEquipmentInfoSaveBean.getChief()); }
	 * if(equipmentInfo
	 * .getEquipmentCharge()==null||equipmentInfo.getEquipmentCharge
	 * ().intValue()!=chiefInt.intValue()){
	 * if(equipmentInfo.getEquipmentCharge()
	 * !=null||!wsEquipmentInfoSaveBean.getChief().equals("")){
	 * content.append("设备负责人【"); if(equipmentInfo.getEquipmentCharge()!=null){
	 * CssUser user = userDAO.get(equipmentInfo.getEquipmentCharge());
	 * if(null!=user){ content.append(user.getName()); } }
	 * if(StringUtils.isNotBlank(wsEquipmentInfoSaveBean.getChief())){ CssUser
	 * user = userDAO.get(Integer.parseInt(wsEquipmentInfoSaveBean.getChief()));
	 * if(null!=user){ content.append("->"+user.getName()+"】;"); } }else{
	 * content.append("->】;"); } } }
	 * doCompareStringLog("安装地址",equipmentInfo.getInstallAddress
	 * (),wsEquipmentInfoSaveBean.getInstallAddress(),content);
	 * doCompareStringLog
	 * ("网点名称",equipmentInfo.getBranchName(),wsEquipmentInfoSaveBean
	 * .getBranchName(),content);
	 * doCompareStringLog("网点负责人",equipmentInfo.getBranchPrincipal
	 * (),wsEquipmentInfoSaveBean.getBranchPrincipal(),content);
	 * doCompareStringLog
	 * ("网点负责人电话",equipmentInfo.getBranchPrincipalTel(),wsEquipmentInfoSaveBean
	 * .getBranchPrincipalTel(),content);
	 * doCompareStringLog("安装类型",equipmentInfo
	 * .getInstallType(),wsEquipmentInfoSaveBean
	 * .getInstallType(),content,DataDictionaryConstants
	 * .EQUIPMENT_INSTALL_TYPE);
	 * doCompareStringLog("安装方式",equipmentInfo.getInstallModel
	 * (),wsEquipmentInfoSaveBean
	 * .getInstallModel(),content,DataDictionaryConstants
	 * .EQUIPMENT_INSTALL_MODEL);
	 * doCompareStringLog("ATM管理员",equipmentInfo.getAtmManager
	 * (),wsEquipmentInfoSaveBean.getAtmManager(),content);
	 * doCompareStringLog("ATM管理员电话"
	 * ,equipmentInfo.getAtmManagerTel(),wsEquipmentInfoSaveBean
	 * .getAtmManagerTel(),content);
	 * doCompareStringLog("ATM号",equipmentInfo.getAtmNumber
	 * (),wsEquipmentInfoSaveBean.getATMNumber(),content);
	 * doCompareStringLog("银行号"
	 * ,equipmentInfo.getBankNumber(),wsEquipmentInfoSaveBean
	 * .getBankNumber(),content);
	 * doCompareStringLog("银行终端号",equipmentInfo.getBankTerminalNumber
	 * (),wsEquipmentInfoSaveBean.getBankTerminalNumber(),content);
	 * doCompareStringLog
	 * ("网络协议",equipmentInfo.getNetProtocol(),wsEquipmentInfoSaveBean
	 * .getNetProtocol(),content);
	 * doCompareStringLog("本机IP",equipmentInfo.getLocalIp
	 * (),wsEquipmentInfoSaveBean.getLocalIP(),content);
	 * doCompareStringLog("P端IP"
	 * ,equipmentInfo.getPip(),wsEquipmentInfoSaveBean.getPip(),content);
	 * doCompareStringLog
	 * ("网关",equipmentInfo.getGateway(),wsEquipmentInfoSaveBean
	 * .getGateway(),content);
	 * doCompareStringLog("子网掩码",equipmentInfo.getSubnetHideAddress
	 * (),wsEquipmentInfoSaveBean.getSubnet_hide_address(),content);
	 * doCompareStringLog
	 * ("设备位置",equipmentInfo.getEquipmentArea(),wsEquipmentInfoSaveBean
	 * .getEquipmentArea(),content,DataDictionaryConstants.EQUIPMENT_AREA);
	 * 
	 * //if(StringUtilsExtends.isNotBlankAndEmpty(differences)){
	 * if(content!=null && content.length()>1){ EquipmentLog equipmentLog=new
	 * EquipmentLog(); equipmentLog.setContent("SA同步修改设备档案："+content);
	 * equipmentLog.setCreateDate(new Date());
	 * equipmentLog.setUsername(wsEquipmentInfoSaveBean.getUserName());
	 * equipmentLog
	 * .setUserId(StringUtilsExtends.isBlankOrEmpty(wsEquipmentInfoSaveBean
	 * .getUserId()) ? null :
	 * Integer.valueOf(wsEquipmentInfoSaveBean.getUserId()));
	 * equipmentInfo.getEquipmentLoglist().add(equipmentLog);
	 * equipmentInfo.setEquipmentCharge
	 * (StringUtilsExtends.isBlankOrEmpty(wsEquipmentInfoSaveBean.getChief()) ?
	 * null : Integer.valueOf(wsEquipmentInfoSaveBean.getChief()));// 设备负责人
	 * String(N)
	 * equipmentInfo.setInstallAddress(wsEquipmentInfoSaveBean.getInstallAddress
	 * ());// 安装地址 String(N)
	 * equipmentInfo.setBranchName(wsEquipmentInfoSaveBean.getBranchName());//
	 * 网点名称 String(Y)
	 * equipmentInfo.setBranchPrincipal(wsEquipmentInfoSaveBean.getBranchPrincipal
	 * ());//网点负责人String(Y)
	 * equipmentInfo.setBranchPrincipalTel(wsEquipmentInfoSaveBean
	 * .getBranchPrincipalTel());//网点负责人电话String(Y)
	 * equipmentInfo.setInstallType(wsEquipmentInfoSaveBean.getInstallType());//
	 * 安装类型 String(Y)
	 * equipmentInfo.setInstallModel(wsEquipmentInfoSaveBean.getInstallModel
	 * ());// 安装方式 String(Y)
	 * equipmentInfo.setAtmManager(wsEquipmentInfoSaveBean.getAtmManager());//
	 * ATM管理员 String(Y)
	 * equipmentInfo.setAtmManagerTel(wsEquipmentInfoSaveBean.getAtmManagerTel
	 * ());// ATM管理员联系方式 String(Y)
	 * equipmentInfo.setAtmNumber(wsEquipmentInfoSaveBean.getATMNumber());//
	 * ATM号 String(Y)
	 * equipmentInfo.setBankNumber(wsEquipmentInfoSaveBean.getBankNumber());//
	 * 银行号 String(Y)
	 * equipmentInfo.setBankTerminalNumber(wsEquipmentInfoSaveBean.
	 * getBankTerminalNumber());// 支持终端号 String(Y)
	 * equipmentInfo.setNetProtocol(wsEquipmentInfoSaveBean.getNetProtocol());//
	 * 网络连接协议 String(Y)
	 * equipmentInfo.setLocalIp(wsEquipmentInfoSaveBean.getLocalIP());// 本机IP地址
	 * String(Y) equipmentInfo.setPip(wsEquipmentInfoSaveBean.getPip());//
	 * P端IP地址 String(Y)
	 * equipmentInfo.setGateway(wsEquipmentInfoSaveBean.getGateway());// 网关
	 * String(N) equipmentInfo.setSubnetHideAddress(wsEquipmentInfoSaveBean.
	 * getSubnet_hide_address());// 子网掩码 String(N)
	 * equipmentInfo.setEquipmentArea
	 * (wsEquipmentInfoSaveBean.getEquipmentArea());//设备位置
	 * equipmentDAO.update(equipmentInfo); } return
	 * WsResultBeanUtils.returnSuccess("操作成功！"); }
	 */

	/*
	 * private void doCompareStringLog(String fieldName, String entityField,
	 * String beanField,StringBuffer content) {
	 * if(entityField==null||!entityField.equals(beanField)){
	 * if(entityField!=null||!beanField.equals("")){
	 * content.append(fieldName+"【"); if(entityField!=null){
	 * content.append(entityField); } content.append("->"+beanField+"】;"); } }
	 * 
	 * 
	 * } private void doCompareStringLog(String fieldName, String entityField,
	 * String beanField,StringBuffer content,String key) {
	 * if(entityField==null||!entityField.equals(beanField)){
	 * if(entityField!=null||!beanField.equals("")){
	 * content.append(fieldName+"【"); if(entityField!=null){
	 * content.append(DataDictionaryLoad.getText(key,entityField)); }
	 * content.append("->"+DataDictionaryLoad.getText(key,beanField)+"】;"); } }
	 * 
	 * }
	 */

	/*
	 * public String doSaveEquipmentContact(Integer equipmentId, String
	 * contactName, String mobliePhone, String createUserCode) { return
	 * equipmentContactService.doSave(equipmentId, contactName, mobliePhone,
	 * createUserCode); }
	 */

	/*
	 * public WsResultBean doUpdateEquipmentInfoDutyMan(String loginCode, String
	 * jsonList) { CssUser ctrlUser = userDAO.getUserByLoginName(loginCode);
	 * WsResultBean resultBean = new WsResultBean(); if (ctrlUser == null) {
	 * resultBean.setErrorMessage("当前登录用户找不到，请检查。");
	 * resultBean.setReturnResult(false); return resultBean; } JSONArray array =
	 * JSONArray.fromObject(jsonList); if (null == array) { throw new
	 * RuntimeException("需要修改的信息为空！"); } JSONObject json = null; String
	 * serialNumber = ""; String dutyUserCode = ""; if (null != array &&
	 * array.size() > 0) { for (int i = 0; i < array.size(); i++) { json =
	 * array.getJSONObject(i); serialNumber = json.getString("serialNumber");
	 * dutyUserCode = json.getString("userCode");
	 * equipmentService.doUpdateDutyMan(serialNumber, dutyUserCode, ctrlUser); }
	 * } resultBean.setErrorMessage("操作成功！"); resultBean.setReturnResult(true);
	 * return resultBean; }
	 */

	@Override
	public String getEquipmentContact(String serialNumber) {
		EquipmentInfo equipmentInfo = equipmentDAO
				.getEquipmentBySerialNumber(serialNumber);
		List<JSONObject> list = equipmentContactService.list(equipmentInfo
				.getEquipmentId());
		if (null != list) {
			return list.toString();
		} else {
			return null;
		}
	}

}
