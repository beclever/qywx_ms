package com.grgbanking.css.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aha.sdk.common.easyquery.DateUtils;
import com.grgbanking.common.utils.DateUtil;
import com.grgbanking.core.entity.workorder.WsOptionsBean;
import com.grgbanking.core.entity.workorder.WsPendingWorkformBean;
import com.grgbanking.core.entity.workorder.WsProblemPartBean;
import com.grgbanking.core.entity.workorder.WsResultCustomerBean;
import com.grgbanking.core.entity.workorder.WsResultProblemPartBean;
import com.grgbanking.core.entity.workorder.WsSubmitWorkTaskBean;
import com.grgbanking.core.entity.workorder.WsWorkFormReplaceBean;
import com.grgbanking.core.entity.workorder.WsWorkformTogetherPersonBean;
import com.grgbanking.core.entity.ws.WsDeviceInfoWorkformBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultDeviceInfoWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultFillWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultPendingWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultTogetherPersonsBean;
import com.grgbanking.core.entity.ws.WsResultTotalPendingWorkBean;
import com.grgbanking.core.entity.ws.WsResultWorkFormModuleReplaceOldBean;
import com.grgbanking.css.bean.CrmCustomer;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.bean.Department;
import com.grgbanking.css.bean.problem.ProblemCode;
import com.grgbanking.css.bean.problem.ProblemPart;
import com.grgbanking.css.bean.problem.ProblemProcessMethod;
import com.grgbanking.css.bean.problem.ProblemReason;
import com.grgbanking.css.bean.vequipment.VEquipmentConfig;
import com.grgbanking.css.bean.vequipment.VEquipmentHistoryProblem;
import com.grgbanking.css.bean.vequipment.VEquipmentInfo;
import com.grgbanking.css.bean.work.PaperWorkFormDetail;
import com.grgbanking.css.bean.work.WorkForm;
import com.grgbanking.css.bean.work.WorkFormBean;
import com.grgbanking.css.bean.work.WorkFormComment;
import com.grgbanking.css.bean.work.WorkFormControl;
import com.grgbanking.css.bean.work.WorkFormControlBean;
import com.grgbanking.css.bean.work.WorkFormFee;
import com.grgbanking.css.bean.work.WorkTaskEquipment;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.common.PhoneImage;
import com.grgbanking.css.dao.CrmCustomerInfoDAO;
import com.grgbanking.css.dao.CssUserDAO;
import com.grgbanking.css.dao.DepartmentDAO;
import com.grgbanking.css.dao.PaperWorkFormDetailDao;
import com.grgbanking.css.dao.PhoneImageDAO;
import com.grgbanking.css.dao.WorkFormCommentDAO;
import com.grgbanking.css.dao.WorkFormControlDAO;
import com.grgbanking.css.dao.WorkformDAO;
import com.grgbanking.css.dao.WorkformFeeDAO;
import com.grgbanking.css.dao.problem.ProblemCodeDAO;
import com.grgbanking.css.dao.problem.ProblemPartDAO;
import com.grgbanking.css.dao.problem.ProblemProcessMethodDAO;
import com.grgbanking.css.dao.problem.ProblemReasonDAO;
import com.grgbanking.css.dao.vequipment.VEquipmentConfigDao;
import com.grgbanking.css.dao.vequipment.VEquipmentHistoryProblemDao;
import com.grgbanking.css.dao.vequipment.VEquipmentInfoDao;
import com.grgbanking.css.load.DataDictionaryLoad;
import com.grgbanking.css.service.CssWorkFormWsService;
import com.grgbanking.css.service.VEquipmentContactService;
import com.grgbanking.css.service.WorkTaskContactService;
import com.grgbanking.css.util.DataDictionaryConstants;
import com.grgbanking.css.util.ServicesConstants;


@Service("cssWorkFormWsService")
public class CssWorkFormWsServiceImpl implements CssWorkFormWsService {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private WorkformDAO<WorkForm, Integer> workformDAO;
//	@Autowired
//	private WorkTaskDao<WorkTask, Integer> workTaskDao;
	@Autowired
	private CssUserDAO<CssUser, Integer> userDAO;
	@Autowired
	private VEquipmentInfoDao<VEquipmentInfo,Integer> vEquipmentInfoDao;
	@Autowired
	private VEquipmentHistoryProblemDao<VEquipmentHistoryProblem,Integer> vEquipmentHistoryProblemDao;
	@Autowired
	private VEquipmentConfigDao<VEquipmentConfig, Integer> vEquipmentConfigDao;
	@Autowired
	private PaperWorkFormDetailDao<PaperWorkFormDetail, Long> paperWorkFormDetailDao;
	@Autowired
	private WorkformFeeDAO<WorkFormFee,Integer> workformFeeDAO;
	@Autowired
	private WorkFormControlDAO<WorkFormControl,Integer> workFormControlDAO;
//	@Autowired
//	private SparepartDAO<Sparepart, Integer> sparepartDAO;
	@Autowired
	private ProblemCodeDAO<ProblemCode, Integer> problemCodeDAO;
	@Autowired
	private ProblemPartDAO<ProblemPart, Integer> problemPartDAO;
	@Autowired
	private ProblemReasonDAO<ProblemReason, Integer> problemReasonDAO;
	@Autowired
	private ProblemProcessMethodDAO<ProblemProcessMethod, Integer> problemProcessMethodDAO;
	@Autowired
	private CrmCustomerInfoDAO<CrmCustomer,Integer> crmCustomerInfoDAO;
	@Autowired
	private DepartmentDAO<Department, Integer> departmentDAO;
//	@Autowired
//	private RevisitWorkformDAO<RevisitWorkform, Integer> revisitWorkformDAO;
//	@Autowired
//	private WorkTaskRemoveDao<WorkTaskRemove,Integer> workTaskRemoveDao;
	@Autowired
	private WorkFormCommentDAO<WorkFormComment, Integer> workFormCommentDAO;
	@Autowired
	private CrmCustomerInfoDAO<CrmCustomer, Integer> customerDAO;
	@Autowired
	private VEquipmentContactService vequipmentContactService;
	@Autowired
	private PhoneImageDAO<PhoneImage, Integer> phoneImageDAO;
//	@Autowired
//	private WorkTaskEquipmentDao<WorkTaskEquipment,Integer> workTaskEquipmentDao;
	@Autowired
	private WorkTaskContactService workTaskContactService;
	
	
	public WsResultPendingWorkFormBean getPendingWorkForm(String loginCode,WsPage wsPage) {
		
		String inteface = " getPendingWorkForm(String loginCode,WsPage wsPage)";
		String visitor = loginCode ;
		logger.info("[{}] visited the interface [{}]. "+new String[] {visitor,inteface});
		
		if(StringUtils.isBlank(loginCode)){
			WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
			wsResultWorkFormBean.setErrMsg("用户不存在，请检查。");
			wsResultWorkFormBean.setStatus(0);
	        return wsResultWorkFormBean;
		}
		CssUser user=userDAO.getUserByLoginName(loginCode);
		if(user==null){
			WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
			wsResultWorkFormBean.setErrMsg("用户不存在，请检查。");
			wsResultWorkFormBean.setStatus(0);
			return wsResultWorkFormBean;
		}
		
		
		Page queryPage=new Page();
		if(wsPage!=null){
			queryPage.setOrder(wsPage.getOrder());
			queryPage.setPage(wsPage.getPage());
			//queryPage.setPageCount();
			queryPage.setRecordCount(wsPage.getRecordCount());
			if(wsPage.getRows()!=null){
				queryPage.setRows(wsPage.getRows());
			}else{
				queryPage.setRows(20);
			}
			queryPage.setSort(wsPage.getSort());
		}else{
			queryPage.setRows(20);
		}
		WorkFormBean workformBean=new WorkFormBean();
		workformBean.setEngineerId(user.getUserId());
		workformBean.setStatusArray(new String[]{ServicesConstants.STATUS_WORKFORM_INPROCESS,ServicesConstants.STATUS_WORKFORM_BACK});
		workformBean.setCreateType("ALL"); // 原字段只有SA数据，现在增加QYWX和MP，所以查询逻辑变更
		List<WorkForm> workformList = workformDAO.queryWorkFormList(queryPage,workformBean);
		if(queryPage.getRecordCount()!=null && wsPage!=null){
			if(wsPage.getPage()>queryPage.getPageCount()){
				WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
				wsResultWorkFormBean.setStatus(1);
				wsResultWorkFormBean.setWsPage(null);
				wsResultWorkFormBean.setPendingWorkform(null);
				return wsResultWorkFormBean;
			}
		}
		List<WsPendingWorkformBean> pendinglist=new ArrayList<WsPendingWorkformBean>();
		for (WorkForm workForm : workformList) {
			
			WsPendingWorkformBean wsPendingWorkformBean=new WsPendingWorkformBean();
			wsPendingWorkformBean.setAppointmentDate(DateUtils.formatDate(workForm.getAppointmentDate(),"yyyy-MM-dd HH:mm:ss"));
			
			if(null!=workForm.getEquipment()){
				//wsPendingWorkformBean.setAtmManager(workForm.getEquipment().getAtmManager());
				//wsPendingWorkformBean.setAtmManagerTel(workForm.getEquipment().getAtmManagerTel());
				//wsPendingWorkformBean.setBranchId(branchId);
				wsPendingWorkformBean.setBranchName(workForm.getEquipment().getBranchName());
				if(null!=workForm.getEquipment().getCustomer()){
					wsPendingWorkformBean.setCustomerName(workForm.getEquipment().getCustomer().getCustomerName());
				}
				
				wsPendingWorkformBean.setEquipmentId(workForm.getEquipment().getEquipmentId()!=null?workForm.getEquipment().getEquipmentId().toString():"");
				//wsPendingWorkformBean.setInstallAddress(workForm.getEquipment().getInstallAddress());
				//wsPendingWorkformBean.setSerialNumber(workForm.getEquipment().getSerialNumber());
			}
			
			//wsPendingWorkformBean.setEngineerName(workForm.getEngineerName());
			wsPendingWorkformBean.setPoNumber(workForm.getPoNumber());
			//wsPendingWorkformBean.setReportTel(workForm.getReportTelephone());
			//wsPendingWorkformBean.setReportTime(DateUtils.formatDate(workForm.getReportTime(),"yyyy-MM-dd HH:mm:ss"));
			//wsPendingWorkformBean.setTaskContent(taskContent);
			wsPendingWorkformBean.setTaskLevel(workForm.getTaskLevel());
			wsPendingWorkformBean.setWorkformId(workForm.getWorkFormId().toString());
			
			wsPendingWorkformBean.setArriveTime(DateUtils.formatDate(workForm.getArriveTime(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setStartWorkTime(DateUtils.formatDate(workForm.getWorkStartDate(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setFinishWorkTime(DateUtils.formatDate(workForm.getWorkFinishDate(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setWorkFormStatus(workForm.getStatus());
			WorkFormControlBean workFormControlBean=new WorkFormControlBean();
			workFormControlBean.setWorkFormId(workForm.getWorkFormId());
			List<WorkFormControl> contolList=workFormControlDAO.getWorkFormContext(workFormControlBean);
			if(contolList!=null && contolList.size()>0){
				for(WorkFormControl workFormControl:contolList){
					if(workFormControl.getTimeType()==1){
						wsPendingWorkformBean.setLeaveTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==5){
						wsPendingWorkformBean.setAcceptTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}
					/*else if(workFormControl.getTimeType()==2){
						//wsPendingWorkformBean.setArriveTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==3){
						//wsPendingWorkformBean.setStartWorkTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==4){
						//wsPendingWorkformBean.setFinishWorkTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}*/
					
				}
			}
			
			pendinglist.add(wsPendingWorkformBean);
		}
		WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
		wsResultWorkFormBean.setStatus(1);
		WsPage resultPage=new WsPage();
		resultPage.setOrder(queryPage.getOrder());
		resultPage.setPage(queryPage.getPage());
		resultPage.setRecordCount(queryPage.getRecordCount());
		resultPage.setRows(queryPage.getRows());
		resultPage.setSort(queryPage.getSort());
		wsResultWorkFormBean.setWsPage(resultPage);
		wsResultWorkFormBean.setPendingWorkform(pendinglist);
		return wsResultWorkFormBean;
	}
	
	public WsResultDeviceInfoWorkFormBean queryDeviceInfo(String serialNumber,
			String workformId) {
		String inteface = " queryDeviceInfo(String serialNumber,String workformId) ";
		String params = serialNumber+","+workformId;
		logger.info(" visited the interface [{}]. param : [{}]"+new String[] {inteface,params});
		
		
		if(StringUtils.isBlank(serialNumber)){
			WsResultDeviceInfoWorkFormBean wsResultDeviceInfoWorkFormBean=new WsResultDeviceInfoWorkFormBean();
			wsResultDeviceInfoWorkFormBean.setErrMsg("设备为空，请检查。");
			wsResultDeviceInfoWorkFormBean.setStatus(0);
	        return wsResultDeviceInfoWorkFormBean;
		}
		if(StringUtils.isBlank(workformId)){
			WsResultDeviceInfoWorkFormBean wsResultDeviceInfoWorkFormBean=new WsResultDeviceInfoWorkFormBean();
			wsResultDeviceInfoWorkFormBean.setErrMsg("工单号为空，请检查。");
			wsResultDeviceInfoWorkFormBean.setStatus(0);
	        return wsResultDeviceInfoWorkFormBean;
		}
		
		VEquipmentInfo equipmentInfo=vEquipmentInfoDao.getEquipmentBySerialNumber(serialNumber);
		if(equipmentInfo==null){
			WsResultDeviceInfoWorkFormBean wsResultDeviceInfoWorkFormBean=new WsResultDeviceInfoWorkFormBean();
			wsResultDeviceInfoWorkFormBean.setErrMsg("设备不存在，请检查。");
			wsResultDeviceInfoWorkFormBean.setStatus(0);
			return wsResultDeviceInfoWorkFormBean;
		}
		
		WsDeviceInfoWorkformBean wsDeviceInfoWorkformBean=new WsDeviceInfoWorkformBean();
		wsDeviceInfoWorkformBean.setBranchName(equipmentInfo.getBranchName());
		wsDeviceInfoWorkformBean.setCustomsName(equipmentInfo.getCustomer()!=null?equipmentInfo.getCustomer().getCustomerName():"");
		wsDeviceInfoWorkformBean.setDepartmentName(equipmentInfo.getDepartment()!=null?equipmentInfo.getDepartment().getDepartmentName():"");
		wsDeviceInfoWorkformBean.setEquipmentId(equipmentInfo.getEquipmentId().toString());
		wsDeviceInfoWorkformBean.setEquipmentModel(DataDictionaryLoad.getText(DataDictionaryConstants.EQUIPMENT_MODEL, equipmentInfo.getEquipmentModel()));
		wsDeviceInfoWorkformBean.setEquipmentStatus(DataDictionaryLoad.getText(DataDictionaryConstants.EQUIPMENT_STATUS, equipmentInfo.getEquipmentStatus()));
		wsDeviceInfoWorkformBean.setEquipmentType(DataDictionaryLoad.getText(DataDictionaryConstants.EQUIPMENT_TYPE,equipmentInfo.getEquipmentType()));
		wsDeviceInfoWorkformBean.setInstallAddress(equipmentInfo.getInstallAddress());
		wsDeviceInfoWorkformBean.setInstallDate(DateUtils.formatDate(equipmentInfo.getInstallDate()));
		wsDeviceInfoWorkformBean.setSerialNumber(serialNumber);
		wsDeviceInfoWorkformBean.setWarrantyStatus(DataDictionaryLoad.getText(DataDictionaryConstants.EQUIPMENT_WARRANTY_STATUS, equipmentInfo.getWarrantyStatus()));
		wsDeviceInfoWorkformBean.setAtmManager(equipmentInfo.getAtmManager());
		wsDeviceInfoWorkformBean.setAtmManagerTel(equipmentInfo.getAtmManagerTel());
		
		WsResultDeviceInfoWorkFormBean wsResultDeviceInfoWorkFormBean=new WsResultDeviceInfoWorkFormBean();
		wsResultDeviceInfoWorkFormBean.setStatus(1);
		wsResultDeviceInfoWorkFormBean.setDeviceInfo(wsDeviceInfoWorkformBean);
		return wsResultDeviceInfoWorkFormBean;
	}

	public WsResultTotalPendingWorkBean queryTotalOfPendingWorkForm(
			String loginCode) {
		
		String inteface = "ueryTotalOfPendingWorkForm(String loginCode)";
		String visitor = loginCode ;
		logger.info(" visited the interface [{}]."+new String[] {visitor,inteface});
		
		
		if(StringUtils.isBlank(loginCode)){
			WsResultTotalPendingWorkBean wsResultTotalPendingWorkBean=new WsResultTotalPendingWorkBean();
			wsResultTotalPendingWorkBean.setErrMsg("用户不存在，请检查。");
			wsResultTotalPendingWorkBean.setStatus(0);
	        return wsResultTotalPendingWorkBean;
		}
		CssUser user=userDAO.getUserByLoginName(loginCode);
		if(user==null){
			WsResultTotalPendingWorkBean wsResultTotalPendingWorkBean=new WsResultTotalPendingWorkBean();
			wsResultTotalPendingWorkBean.setErrMsg("用户不存在，请检查。");
			wsResultTotalPendingWorkBean.setStatus(0);
			return wsResultTotalPendingWorkBean;
		}
		
		
		
		WorkFormBean workformBean=new WorkFormBean();
		workformBean.setEngineerId(user.getUserId());
		workformBean.setStatusArray(new String[]{ServicesConstants.STATUS_WORKFORM_INPROCESS,ServicesConstants.STATUS_WORKFORM_BACK});
		workformBean.setCreateType("SA");
		List<WorkForm> workformList = workformDAO.queryWorkFormList(null,workformBean);
		
		WsResultTotalPendingWorkBean wsResultTotalPendingWorkBean=new WsResultTotalPendingWorkBean();
		wsResultTotalPendingWorkBean.setStatus(1);
		wsResultTotalPendingWorkBean.setTotal(workformList.size());
		return wsResultTotalPendingWorkBean;
	}

	public WsResultTogetherPersonsBean dropdownTogetherPersons(
			String workformId, String loginCode) {
		
		String inteface = " dropdownTogetherPersons(String workformId, String loginCode)";
		String visitor = loginCode ;
		String params = workformId;
		logger.info("[{}] visited the interface [{}]. param : [{}]"+new String[] {visitor,inteface,params});
		
		
		if(StringUtils.isBlank(workformId)){
			WsResultTogetherPersonsBean wsResultTogetherPersonsBean=new WsResultTogetherPersonsBean();
			wsResultTogetherPersonsBean.setErrMsg("工单号为空，请检查。");
			wsResultTogetherPersonsBean.setStatus(0);
	        return wsResultTogetherPersonsBean;
		}
		
		/*if(StringUtils.isBlank(loginCode)){
			WsResultTogetherPersonsBean wsResultTogetherPersonsBean=new WsResultTogetherPersonsBean();
			wsResultTogetherPersonsBean.setErrMsg("用户不存在，请检查。");
			wsResultTogetherPersonsBean.setStatus(0);
	        return wsResultTogetherPersonsBean;
		}
		User user=userDAO.getUserByLoginName(loginCode);
		if(user==null){
			WsResultTogetherPersonsBean wsResultTogetherPersonsBean=new WsResultTogetherPersonsBean();
			wsResultTogetherPersonsBean.setErrMsg("用户不存在，请检查。");
			wsResultTogetherPersonsBean.setStatus(0);
			return wsResultTogetherPersonsBean;
		}*/
		
		WorkForm workForm= workformDAO.get(Integer.parseInt(workformId));
		if(workForm==null){
			WsResultTogetherPersonsBean wsResultTogetherPersonsBean=new WsResultTogetherPersonsBean();
			wsResultTogetherPersonsBean.setErrMsg("工单不存在，请检查。");
			wsResultTogetherPersonsBean.setStatus(0);
			return wsResultTogetherPersonsBean;
		}
		
		//if(workForm.getFollowUserName()!=null){
			/*String[] strName=workForm.getFollowUserName().split(",");
			String[] strUserId=workForm.getFollowUserId().split(",");
			List<WsWorkformTogetherPersonBean> togetherList=new ArrayList<WsWorkformTogetherPersonBean>();
			for(int i=0;i<strName.length;i++){
				WsWorkformTogetherPersonBean wsWorkformTogetherPersonBean=new WsWorkformTogetherPersonBean();
				wsWorkformTogetherPersonBean.setName(strName[i]);
				wsWorkformTogetherPersonBean.setUserId(strUserId[i]);
				togetherList.add(wsWorkformTogetherPersonBean);
			}
			WsResultTogetherPersonsBean wsResultTogetherPersonsBean=new WsResultTogetherPersonsBean();
			wsResultTogetherPersonsBean.setStatus(1);
			wsResultTogetherPersonsBean.setTogetherPerson(togetherList);
			return wsResultTogetherPersonsBean;*/
			
		//}
		
		if(workForm.getDepartment()!=null){
			List<WsWorkformTogetherPersonBean> togetherList=new ArrayList<WsWorkformTogetherPersonBean>();
			List<Integer> depIdList=departmentDAO.getSAModeDepartmentIdAll(workForm.getDepartment().getDepartmentId());
			List<CssUser> userList=new ArrayList<CssUser>();
			if(depIdList!=null && depIdList.size()>0){
				userList=userDAO.getUserListByDepartmentId(depIdList);
			}else{
				userList=userDAO.getUserListByDepartmentId(workForm.getDepartment().getDepartmentId());
			}
			for(CssUser user:userList){
				WsWorkformTogetherPersonBean wsWorkformTogetherPersonBean=new WsWorkformTogetherPersonBean();
				wsWorkformTogetherPersonBean.setName(user.getName());
				wsWorkformTogetherPersonBean.setUserId(user.getUserId().toString());
				togetherList.add(wsWorkformTogetherPersonBean);
			}
			WsResultTogetherPersonsBean wsResultTogetherPersonsBean=new WsResultTogetherPersonsBean();
			wsResultTogetherPersonsBean.setStatus(1);
			wsResultTogetherPersonsBean.setTogetherPerson(togetherList);
			return wsResultTogetherPersonsBean;
		}else{
			WsResultTogetherPersonsBean wsResultTogetherPersonsBean=new WsResultTogetherPersonsBean();
			wsResultTogetherPersonsBean.setErrMsg("无同行人员。");
			wsResultTogetherPersonsBean.setStatus(0);
			return wsResultTogetherPersonsBean;
		}
		
		
		
		
	}

	public WsResultWorkFormModuleReplaceOldBean queryWorkFormModuleReplaceOld(String loginCode,String serialNumber) {
		
		String inteface = " queryWorkFormModuleReplaceOld(String loginCode,String serialNumber) ";
		String visitor = loginCode ;
		String params = serialNumber;
		logger.info("[{}] visited the interface [{}]. param : [{}]"+new String[] {visitor,inteface,params});
		
		
		if(StringUtils.isBlank(serialNumber)){
			WsResultWorkFormModuleReplaceOldBean wsResultWorkFormModuleReplaceBean=new WsResultWorkFormModuleReplaceOldBean();
			wsResultWorkFormModuleReplaceBean.setErrMsg("设备序列号不存在，请检查。");
			wsResultWorkFormModuleReplaceBean.setStatus(0);
	        return wsResultWorkFormModuleReplaceBean;
		}
		/*User user=userDAO.getUserByLoginName(loginCode);
		if(user==null){
			WsResultWorkFormModuleReplaceOldBean wsResultWorkFormModuleReplaceBean=new WsResultWorkFormModuleReplaceOldBean();
			wsResultWorkFormModuleReplaceBean.setErrMsg("用户不存在，请检查。");
			wsResultWorkFormModuleReplaceBean.setStatus(0);
			return wsResultWorkFormModuleReplaceBean;
		}*/
		
		List<VEquipmentConfig> equipmentConfigList=vEquipmentConfigDao.getEquipmentConfigForCheck(serialNumber);
		List<WsWorkFormReplaceBean> configList=new ArrayList<WsWorkFormReplaceBean>();
		for (VEquipmentConfig equipmentConfig : equipmentConfigList) {
			WsWorkFormReplaceBean wsWorkFormReplaceBean=new WsWorkFormReplaceBean();
			wsWorkFormReplaceBean.setHardwareVersion(equipmentConfig.getHardwareVersion());
			if(equipmentConfig.getSparepart()!=null){
				wsWorkFormReplaceBean.setMaterialCode(equipmentConfig.getSparepart().getMaterialCode());
				wsWorkFormReplaceBean.setMaterialName(equipmentConfig.getSparepart().getMaterialName());
				wsWorkFormReplaceBean.setSparepartId(equipmentConfig.getSparepart().getSparepartId().toString());
				wsWorkFormReplaceBean.setModuleType(equipmentConfig.getSparepart().getModuleType());
			}
			wsWorkFormReplaceBean.setSerialNumber(equipmentConfig.getSerialNumber());
			wsWorkFormReplaceBean.setSoftwareVersion(equipmentConfig.getSoftwareVersion());
			wsWorkFormReplaceBean.setEquipmentConfigId(equipmentConfig.getEquipmentConfigId().toString());
			configList.add(wsWorkFormReplaceBean);
		}
		
		WsResultWorkFormModuleReplaceOldBean wsResultWorkFormModuleReplaceBean=new WsResultWorkFormModuleReplaceOldBean();
		wsResultWorkFormModuleReplaceBean.setStatus(1);
		wsResultWorkFormModuleReplaceBean.setOldWorkFormReplaceList(configList);
		return wsResultWorkFormModuleReplaceBean;
	}

	
	private WorkTaskEquipment getCopyByWorkTaskEquipment(WorkTaskEquipment workTaskEquipment,WsSubmitWorkTaskBean wsSubmitWorkTaskBean){
		workTaskEquipment.setAtmcName(wsSubmitWorkTaskBean.getATMCId());
		workTaskEquipment.setAtmcSpVersion(wsSubmitWorkTaskBean.getATMCSpVersion());//
		workTaskEquipment.setAtmcVersion(wsSubmitWorkTaskBean.getATMCVersion());
		workTaskEquipment.setAtmManager(wsSubmitWorkTaskBean.getATMManager());
		workTaskEquipment.setAtmManagerTel(wsSubmitWorkTaskBean.getATMManagerTel());
		workTaskEquipment.setAtmNumber(wsSubmitWorkTaskBean.getATMNumber());
		workTaskEquipment.setBankNumber(wsSubmitWorkTaskBean.getBankNumber());//
		workTaskEquipment.setBankTerminalNumber(wsSubmitWorkTaskBean.getBankTerminalNumber());//
		workTaskEquipment.setBranchName(wsSubmitWorkTaskBean.getBranchName());
		workTaskEquipment.setBranchPrincipal(wsSubmitWorkTaskBean.getBranchPrincipal());//
		workTaskEquipment.setBranchPrincipalTel(wsSubmitWorkTaskBean.getBranchPrincipalTel());//
		if(StringUtils.isNotBlank(wsSubmitWorkTaskBean.getCityId())){
			workTaskEquipment.setCityId(Integer.parseInt(wsSubmitWorkTaskBean.getCityId()));
		}
		workTaskEquipment.setTrafficLine(wsSubmitWorkTaskBean.getConsultWay());//
		if(StringUtils.isNotBlank(wsSubmitWorkTaskBean.getCustomerNameId())){
			workTaskEquipment.setCustomerId(Integer.parseInt(wsSubmitWorkTaskBean.getCustomerNameId()));
		}
		workTaskEquipment.setDailyDealMoney(wsSubmitWorkTaskBean.getDailyAverageAmountId());//
		workTaskEquipment.setDailyDealCount(wsSubmitWorkTaskBean.getDailyAverageNumId());//
		workTaskEquipment.setEnvironmentDust(wsSubmitWorkTaskBean.getDustId());//
		workTaskEquipment.setEncryptType(wsSubmitWorkTaskBean.getEncryptModelId());//
		workTaskEquipment.setGateway(wsSubmitWorkTaskBean.getGateway());//
		workTaskEquipment.setEnvironmentHumidity(wsSubmitWorkTaskBean.getHumidityId());
		workTaskEquipment.setInstallAddress(wsSubmitWorkTaskBean.getInstallAddress());
		workTaskEquipment.setInstallModel(wsSubmitWorkTaskBean.getInstallModelId());
		workTaskEquipment.setInstallProperty(wsSubmitWorkTaskBean.getInstallPropertyId());
		workTaskEquipment.setInstallType(wsSubmitWorkTaskBean.getInstallTypeId());
		workTaskEquipment.setLocalIp(wsSubmitWorkTaskBean.getLocalIP());//
		workTaskEquipment.setNetProtocol(wsSubmitWorkTaskBean.getNetProtocol());//
		workTaskEquipment.setOperateSystem(wsSubmitWorkTaskBean.getOperationSystemId());//
		workTaskEquipment.setOsVersion(wsSubmitWorkTaskBean.getOsVersion());
		workTaskEquipment.setPip(wsSubmitWorkTaskBean.getPip());
		workTaskEquipment.setEnvironmentPowerSupply(wsSubmitWorkTaskBean.getPowerSupplyId());
		if(wsSubmitWorkTaskBean.getProvinceId()!=null){
			workTaskEquipment.setProvinceId(Integer.parseInt(wsSubmitWorkTaskBean.getProvinceId()));
		}
		workTaskEquipment.setReferenceCharge(wsSubmitWorkTaskBean.getReferenceCharge());
		workTaskEquipment.setEnvironmentSunshine(wsSubmitWorkTaskBean.getSolarizationId());
		workTaskEquipment.setSubnetHideAddress(wsSubmitWorkTaskBean.getSubnetMask());
		workTaskEquipment.setEnvironmentTemperature(wsSubmitWorkTaskBean.getTemperatureId());
		workTaskEquipment.setEnvironmentRainDefence(wsSubmitWorkTaskBean.getWaterproofId());
		
		
		//设备责任人
		if(StringUtils.isNotBlank(wsSubmitWorkTaskBean.getEquipmentChargeName())){
			CssUser charger=userDAO.getUserByChargeName(wsSubmitWorkTaskBean.getEquipmentChargeName());
			if(null!=charger)
				workTaskEquipment.setEquipmentCharge(charger.getUserId());
		}
		return workTaskEquipment;
	}

	public WsResultCustomerBean selectCustomerList(String loginName) {
		String inteface = " selectCustomerList(String loginName) ";
		String visitor = loginName ;
		logger.info("[{}] visited the interface [{}]."+new String[] {visitor,inteface});
		if(StringUtils.isBlank(loginName)){
			WsResultCustomerBean wsResultCustomerBean=new WsResultCustomerBean();
			wsResultCustomerBean.setErrMsg("用户为空，请检查。");
			wsResultCustomerBean.setStatus(0);
	        return wsResultCustomerBean;
		}
		CssUser user=userDAO.getUserByLoginName(loginName);
		if(user==null){
			WsResultCustomerBean wsResultCustomerBean=new WsResultCustomerBean();
			wsResultCustomerBean.setErrMsg("用户不存在，请检查。");
			wsResultCustomerBean.setStatus(0);
			return wsResultCustomerBean;
		}
		List<Integer> depIdList=departmentDAO.getSAModeDepartmentIdAll(user.getDepartment().getDepartmentId());
		List<Integer> departmentIdList=new ArrayList<Integer>();
		if(depIdList!=null && depIdList.size()>0){
			departmentIdList.addAll(depIdList);
		}else{
			departmentIdList = departmentDAO.getChildDepartmentId(user.getDepartment().getDepartmentId());
			departmentIdList.add(user.getDepartment().getDepartmentId());
		}
		List<CrmCustomer> customerList = crmCustomerInfoDAO.getCustomerByDepartment(departmentIdList);
		List<WsOptionsBean> optionsList=new ArrayList<WsOptionsBean>(); 
		for (CrmCustomer customerInfo : customerList) {
			WsOptionsBean optionsBean=new WsOptionsBean();
			optionsBean.setDefaultId(customerInfo.getCustomerId().toString());
			optionsBean.setDefaultName(customerInfo.getCustomerName());
			optionsList.add(optionsBean);
		}
		
		WsResultCustomerBean wsResultCustomerBean=new WsResultCustomerBean();
		wsResultCustomerBean.setStatus(1);
		wsResultCustomerBean.setCustomerList(optionsList);
		return wsResultCustomerBean;
	}

	public WsResultProblemPartBean dropdownProblemPartOption(String keyword,
			String problemRelation, Integer parentLayer, String type) {
		//type 1:故障部位，2：故障描述，3：故障原因，4：处理方法
		String inteface = " dropdownProblemPartOption(String keyword,String problemRelation, Integer parentLayer, String type)";
		String params = keyword+","+problemRelation+","+parentLayer+","+type;
		logger.info(" visited the interface [{}]. param : [{}]"+new String[] {inteface,params});
		
		if(StringUtils.isBlank(type)){
			WsResultProblemPartBean wsResultProblemPartBean=new WsResultProblemPartBean();
			wsResultProblemPartBean.setErrMsg("故障部位类型不能为空，请检查。");
			wsResultProblemPartBean.setStatus(0);
	        return wsResultProblemPartBean;
		}
		List<WsProblemPartBean> partlist=new ArrayList<WsProblemPartBean>();
		if("1".equals(type)){
			if(StringUtils.isBlank(problemRelation)){
				WsResultProblemPartBean wsResultProblemPartBean=new WsResultProblemPartBean();
				wsResultProblemPartBean.setErrMsg("故障部位设备型号不能为空，请检查。");
				wsResultProblemPartBean.setStatus(0);
		        return wsResultProblemPartBean;
			}
			/*if(parentLayer==null){
				parentLayer=0;
			}*/
			List<Object[]> objPartList=problemPartDAO.getProblemPartModelNameList(problemRelation,parentLayer,keyword);
			if(objPartList!=null && objPartList.size()>0){
				 for(int i=0;i<objPartList.size();i++){
					Object[] ob=objPartList.get(i);
					WsProblemPartBean wsProblemPartBean=new WsProblemPartBean();
					wsProblemPartBean.setDescription(ob[1]!=null?ob[1].toString():"");
					wsProblemPartBean.setProblemCode(ob[2]!=null?ob[2].toString():"");
					wsProblemPartBean.setProblemId(ob[0].toString());
					if(ob[4]!=null){
						if(Integer.parseInt(ob[4].toString())>1){
							wsProblemPartBean.setLastLayer(0);
						}else{
							wsProblemPartBean.setLastLayer(1);
						}
					}
					partlist.add(wsProblemPartBean);
				 }
			}
			
		}else if("2".equals(type)){
			if(StringUtils.isBlank(problemRelation)){
				WsResultProblemPartBean wsResultProblemPartBean=new WsResultProblemPartBean();
				wsResultProblemPartBean.setErrMsg("故障部位不能为空，请检查。");
				wsResultProblemPartBean.setStatus(0);
		        return wsResultProblemPartBean;
			}
			/*if(parentLayer==null){
				parentLayer=0;
			}*/
			 List<Object[]> objPartList=problemCodeDAO.getgetProblemCodeList(Integer.parseInt(problemRelation),parentLayer,keyword);
			 if(objPartList!=null && objPartList.size()>0){
				 for(int i=0;i<objPartList.size();i++){
					Object[] ob=objPartList.get(i);
					WsProblemPartBean wsProblemPartBean=new WsProblemPartBean();
					wsProblemPartBean.setDescription(ob[2]!=null?ob[2].toString():"");
					wsProblemPartBean.setProblemCode(ob[1]!=null?ob[1].toString():"");
					wsProblemPartBean.setProblemId(ob[0].toString());
					if(ob[4]!=null){
						if(Integer.parseInt(ob[4].toString())>1){
							wsProblemPartBean.setLastLayer(0);
						}else{
							wsProblemPartBean.setLastLayer(1);
						}
					}
					partlist.add(wsProblemPartBean);
				 }
			}
		}else if("3".equals(type)){
			/*if(parentLayer==null){
				parentLayer=0;
			}*/
			List<Object[]> objPartList=problemReasonDAO.getProblemReasonList(parentLayer,keyword);
			if(objPartList!=null && objPartList.size()>0){
				 for(int i=0;i<objPartList.size();i++){
					Object[] ob=objPartList.get(i);
					WsProblemPartBean wsProblemPartBean=new WsProblemPartBean();
					wsProblemPartBean.setDescription(ob[2]!=null?ob[2].toString():"");
					wsProblemPartBean.setProblemCode(ob[1]!=null?ob[1].toString():"");
					wsProblemPartBean.setProblemId(ob[0].toString());
					if(ob[4]!=null){
						if(Integer.parseInt(ob[4].toString())>1){
							wsProblemPartBean.setLastLayer(0);
						}else{
							wsProblemPartBean.setLastLayer(1);
						}
					}
					partlist.add(wsProblemPartBean);
				 }
			}
		}else if("4".equals(type)){
			/*if(parentLayer==null){
				parentLayer=0;
			}*/
			List<Object[]> objPartList=problemProcessMethodDAO.getProblemProcessMethodList(parentLayer,keyword);
			if(objPartList!=null && objPartList.size()>0){
				 for(int i=0;i<objPartList.size();i++){
					Object[] ob=objPartList.get(i);
					WsProblemPartBean wsProblemPartBean=new WsProblemPartBean();
					wsProblemPartBean.setDescription(ob[2]!=null?ob[2].toString():"");
					wsProblemPartBean.setProblemCode(ob[1]!=null?ob[1].toString():"");
					wsProblemPartBean.setProblemId(ob[0].toString());
					if(ob[4]!=null){
						if(Integer.parseInt(ob[4].toString())>1){
							wsProblemPartBean.setLastLayer(0);
						}else{
							wsProblemPartBean.setLastLayer(1);
						}
					}
					partlist.add(wsProblemPartBean);
				 }
			}
		}
		
		WsResultProblemPartBean wsResultProblemPartBean=new WsResultProblemPartBean();
		wsResultProblemPartBean.setStatus(1);
		wsResultProblemPartBean.setRows(partlist);
        return wsResultProblemPartBean;
	}
	
	public WsResultPendingWorkFormBean getCheckPendingWorkForm(
			String loginName, String status, WsPage wsPage, String clientType) {
		String inteface = " getCheckPendingWorkForm(String loginName,WsPage wsPage)";
		String visitor = loginName ;
		logger.info("[{}] visited the interface [{}]. "+new String[] {visitor,inteface});
		
		if(StringUtils.isBlank(loginName)){
			WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
			wsResultWorkFormBean.setErrMsg("用户不存在，请检查。");
			wsResultWorkFormBean.setStatus(0);
	        return wsResultWorkFormBean;
		}
		if(StringUtils.isBlank(status)){
			WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
			wsResultWorkFormBean.setErrMsg("状态为空，请检查。");
			wsResultWorkFormBean.setStatus(0);
	        return wsResultWorkFormBean;
		}
		
		String[] strStatus=status.split(",");
		if(strStatus.length<=0){
			WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
			wsResultWorkFormBean.setErrMsg("状态为空，请检查。");
			wsResultWorkFormBean.setStatus(0);
	        return wsResultWorkFormBean;
		}
		for(int i=0;i<strStatus.length;i++){
			if(!ServicesConstants.STATUS_WORKFORM_DIRECTOR.equals(strStatus[i]) && !ServicesConstants.STATUS_WORKFORM_STOCK.equals(strStatus[i])){
				WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
				wsResultWorkFormBean.setErrMsg("状态不正确，请检查。");
				wsResultWorkFormBean.setStatus(0);
		        return wsResultWorkFormBean;
			}
		}
		
		CssUser user=userDAO.getUserByLoginName(loginName);
		if(user==null){
			WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
			wsResultWorkFormBean.setErrMsg("用户不存在，请检查。");
			wsResultWorkFormBean.setStatus(0);
			return wsResultWorkFormBean;
		}
		
		
		Page queryPage=new Page();
		if(wsPage!=null){
			queryPage.setOrder(wsPage.getOrder());
			queryPage.setPage(wsPage.getPage());
			//queryPage.setPageCount();
			queryPage.setRecordCount(wsPage.getRecordCount());
			if(wsPage.getRows()!=null){
				queryPage.setRows(wsPage.getRows());
			}else{
				queryPage.setRows(20);
			}
			queryPage.setSort(wsPage.getSort());
		}else{
			queryPage.setRows(20);
		}
		WorkFormBean workformBean=new WorkFormBean();
		workformBean.setEquipmentDepartmentId(user.getDepartment().getDepartmentId());
		workformBean.setStatusArray(strStatus);
		workformBean.setCreateType("ALL"); // 20150902 复用字段
		List<WorkForm> workformList = workformDAO.queryWorkFormList(queryPage,workformBean);
		if(queryPage.getRecordCount()!=null && wsPage!=null){
			if(wsPage.getPage()>queryPage.getPageCount()){
				WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
				wsResultWorkFormBean.setStatus(1);
				wsResultWorkFormBean.setWsPage(null);
				wsResultWorkFormBean.setPendingWorkform(null);
				return wsResultWorkFormBean;
			}
		}
		List<WsPendingWorkformBean> pendinglist=new ArrayList<WsPendingWorkformBean>();
		for (WorkForm workForm : workformList) {
			
			WsPendingWorkformBean wsPendingWorkformBean=new WsPendingWorkformBean();
			wsPendingWorkformBean.setAppointmentDate(DateUtils.formatDate(workForm.getAppointmentDate(),"yyyy-MM-dd HH:mm:ss"));
			
			if(null!=workForm.getEquipment()){
				wsPendingWorkformBean.setAtmManager(workForm.getEquipment().getAtmManager());
				wsPendingWorkformBean.setAtmManagerTel(workForm.getEquipment().getAtmManagerTel());
				//wsPendingWorkformBean.setBranchId(branchId);
				wsPendingWorkformBean.setBranchName(workForm.getEquipment().getBranchName());
				if(null!=workForm.getEquipment().getCustomer()){
					wsPendingWorkformBean.setCustomerName(workForm.getEquipment().getCustomer().getCustomerName());
				}
				
				wsPendingWorkformBean.setEquipmentId(workForm.getEquipment().getEquipmentId()!=null?workForm.getEquipment().getEquipmentId().toString():"");
				wsPendingWorkformBean.setInstallAddress(workForm.getEquipment().getInstallAddress());
				wsPendingWorkformBean.setSerialNumber(workForm.getEquipment().getSerialNumber());
			}
			
			wsPendingWorkformBean.setEngineerName(workForm.getEngineerName());
			wsPendingWorkformBean.setPoNumber(workForm.getPoNumber());
			wsPendingWorkformBean.setReportTel(workForm.getReportTelephone());
			wsPendingWorkformBean.setReportTime(DateUtils.formatDate(workForm.getReportTime(),"yyyy-MM-dd HH:mm:ss"));
			//wsPendingWorkformBean.setTaskContent(taskContent);
			wsPendingWorkformBean.setTaskLevel(workForm.getTaskLevel());
			wsPendingWorkformBean.setWorkformId(workForm.getWorkFormId().toString());
			
			wsPendingWorkformBean.setArriveTime(DateUtils.formatDate(workForm.getArriveTime(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setStartWorkTime(DateUtils.formatDate(workForm.getWorkStartDate(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setFinishWorkTime(DateUtils.formatDate(workForm.getWorkFinishDate(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setWorkFormStatus(workForm.getStatus());
			WorkFormControlBean workFormControlBean=new WorkFormControlBean();
			workFormControlBean.setWorkFormId(workForm.getWorkFormId());
			List<WorkFormControl> contolList=workFormControlDAO.getWorkFormContext(workFormControlBean);
			if(contolList!=null && contolList.size()>0){
				for(WorkFormControl workFormControl:contolList){
					if(workFormControl.getTimeType()==1){
						wsPendingWorkformBean.setLeaveTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==5){
						wsPendingWorkformBean.setAcceptTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}
					/*else if(workFormControl.getTimeType()==2){
						//wsPendingWorkformBean.setArriveTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==3){
						//wsPendingWorkformBean.setStartWorkTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==4){
						//wsPendingWorkformBean.setFinishWorkTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}*/
					
				}
			}
			
			pendinglist.add(wsPendingWorkformBean);
		}
		WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
		wsResultWorkFormBean.setStatus(1);
		WsPage resultPage=new WsPage();
		resultPage.setOrder(queryPage.getOrder());
		resultPage.setPage(queryPage.getPage());
		resultPage.setRecordCount(queryPage.getRecordCount());
		resultPage.setRows(queryPage.getRows());
		resultPage.setSort(queryPage.getSort());
		wsResultWorkFormBean.setWsPage(resultPage);
		wsResultWorkFormBean.setPendingWorkform(pendinglist);
		return wsResultWorkFormBean;
	}

	public WsResultTotalPendingWorkBean queryTotalOfCheckPendingWorkForm(
			String loginName, String status, String clientType) {
		String inteface = "ueryTotalOfPendingWorkForm(String loginCode)";
		String visitor = loginName ;
		logger.info(" visited the interface [{}]."+new String[] {visitor,inteface});
		
		
		if(StringUtils.isBlank(loginName)){
			WsResultTotalPendingWorkBean wsResultTotalPendingWorkBean=new WsResultTotalPendingWorkBean();
			wsResultTotalPendingWorkBean.setErrMsg("用户不存在，请检查。");
			wsResultTotalPendingWorkBean.setStatus(0);
	        return wsResultTotalPendingWorkBean;
		}
		
		if(StringUtils.isBlank(status)){
			WsResultTotalPendingWorkBean wsResultTotalPendingWorkBean=new WsResultTotalPendingWorkBean();
			wsResultTotalPendingWorkBean.setErrMsg("状态为空，请检查。");
			wsResultTotalPendingWorkBean.setStatus(0);
	        return wsResultTotalPendingWorkBean;
		}
		
		String[] strStatus=status.split(",");
		if(strStatus.length<=0){
			WsResultTotalPendingWorkBean wsResultTotalPendingWorkBean=new WsResultTotalPendingWorkBean();
			wsResultTotalPendingWorkBean.setErrMsg("状态为空，请检查。");
			wsResultTotalPendingWorkBean.setStatus(0);
	        return wsResultTotalPendingWorkBean;
		}
		for(int i=0;i<strStatus.length;i++){
			if(!ServicesConstants.STATUS_WORKFORM_DIRECTOR.equals(strStatus[i]) && !ServicesConstants.STATUS_WORKFORM_STOCK.equals(strStatus[i])){
				WsResultTotalPendingWorkBean wsResultTotalPendingWorkBean=new WsResultTotalPendingWorkBean();
				wsResultTotalPendingWorkBean.setErrMsg("状态不正确，请检查。");
				wsResultTotalPendingWorkBean.setStatus(0);
		        return wsResultTotalPendingWorkBean;
			}
		}
		
		CssUser user=userDAO.getUserByLoginName(loginName);
		if(user==null){
			WsResultTotalPendingWorkBean wsResultTotalPendingWorkBean=new WsResultTotalPendingWorkBean();
			wsResultTotalPendingWorkBean.setErrMsg("用户不存在，请检查。");
			wsResultTotalPendingWorkBean.setStatus(0);
			return wsResultTotalPendingWorkBean;
		}
		
		
		
		WorkFormBean workformBean=new WorkFormBean();
		workformBean.setEquipmentDepartmentId(user.getDepartment().getDepartmentId());
		workformBean.setStatusArray(strStatus);
		workformBean.setCreateType("SA");
		List<WorkForm> workformList = workformDAO.queryWorkFormList(null,workformBean);
		
		WsResultTotalPendingWorkBean wsResultTotalPendingWorkBean=new WsResultTotalPendingWorkBean();
		wsResultTotalPendingWorkBean.setStatus(1);
		wsResultTotalPendingWorkBean.setTotal(workformList.size());
		return wsResultTotalPendingWorkBean;
	}

	/**
	 * 查询条件：
		设备序列号、网点名称（只显示本服务站网点）、工单状态、任务状态、工程师（只显示本服务站工程师）
		说明：
		1、	本页默认显示本服务站当天的所有工单；
		2、	若按设备序列号查询，则查询该设备一个月内的工单情况，包含临时任务和计划任务；
		3、	若按网点查询，则查询该网点一个月内所有设备的工单情况，包含临时任务和计划任务；
		4、	若按工单状态查询，则查询该服务站所有工单对应的状态。
		5、	若按任务完成状态查询，则查询该服务站所有工单对应的任务完成状态。
		6、	若按工程师查询，则查询该工程师一个月内所有的工单情况。
		7、      如果createUserCode 不为空，只查询当前账号
	 */
	public WsResultPendingWorkFormBean getWorkForm(String loginCode, WsPage wsPage, String serialNumber, String branchName, String workFormStatus,
			String taskStatus, String engineerUserCode,String createUserCode) {
		String inteface = " getPendingWorkForm(String loginCode,WsPage wsPage)";
		String visitor = loginCode ;
		logger.info("[{}] visited the interface [{}]. "+new String[] {visitor,inteface});
		
		if(StringUtils.isBlank(loginCode)){
			WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
			wsResultWorkFormBean.setErrMsg("用户不存在，请检查。");
			wsResultWorkFormBean.setStatus(0);
	        return wsResultWorkFormBean;
		}
		CssUser user=userDAO.getUserByLoginName(loginCode);
	 
		if(user==null){
			WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
			wsResultWorkFormBean.setErrMsg("用户不存在，请检查。");
			wsResultWorkFormBean.setStatus(0);
			return wsResultWorkFormBean;
		}
		
		
		Page queryPage=new Page();
		if(wsPage!=null){
			queryPage.setOrder(wsPage.getOrder());
			queryPage.setPage(wsPage.getPage());
			//queryPage.setPageCount();
			queryPage.setRecordCount(wsPage.getRecordCount());
			if(wsPage.getRows()!=null){
				queryPage.setRows(wsPage.getRows());
			}else{
				queryPage.setRows(20);
			}
			queryPage.setSort(wsPage.getSort());
		}else{
			queryPage.setRows(20);
		}
		
		WorkFormBean workformBean=new WorkFormBean();
		workformBean.setDepartmentId(user.getDepartment().getDepartmentId());
		
		//1、	本页默认显示本服务站7天内的所有工单；
		
		if (StringUtils.isBlank(serialNumber) && StringUtils.isBlank(branchName) && StringUtils.isBlank(engineerUserCode)) {
			workformBean.setCreateDateStart(DateUtils.formatDate(DateUtil.addDay(new Date(),-7)));
			workformBean.setCreateDateEnd(DateUtils.formatDate(new Date()));
		}
		
		//4、	若按工单状态查询，则查询该服务站所有工单对应的状态。
		//2、	若按设备序列号查询，则查询该设备一个月内的工单情况，包含临时任务和计划任务；
		//3、	若按网点查询，则查询该网点一个月内所有设备的工单情况，包含临时任务和计划任务；
		//6、	若按工程师查询，则查询该工程师一个月内所有的工单情况。
		
		if (StringUtils.isNotBlank(workFormStatus)||StringUtils.isNotBlank(taskStatus)||StringUtils.isNotBlank(serialNumber)||StringUtils.isNotBlank(branchName)||StringUtils.isNotBlank(engineerUserCode)){
			workformBean.setCreateDateStart(DateUtil.addDay(new Date(), -30, "yyyy-MM-dd"));
			workformBean.setCreateDateEnd(DateUtils.formatDate(new Date()));
			workformBean.setTaskStatus(taskStatus);
			workformBean.setStatus(workFormStatus);
			workformBean.setSerialNumber(serialNumber);
			workformBean.setBranchName(branchName);
			if(StringUtils.isNotBlank(engineerUserCode)){
				CssUser engineer = userDAO.getUserByUserCode(engineerUserCode);
				workformBean.setEngineerId(engineer.getUserId());
			}
		}
		/*创建人的工号*/
		if(StringUtils.isNotBlank(createUserCode)){
			CssUser createUser = userDAO.getUserByUserCode(engineerUserCode);
			workformBean.setCreateUserId(createUser.getUserId());
		}
		
		//workformBean.setStatusArray(new String[]{ServicesConstants.STATUS_WORKFORM_INPROCESS,ServicesConstants.STATUS_WORKFORM_BACK});
		List<WorkForm> workformList = workformDAO.queryWorkFormListBySQL(queryPage, workformBean);
		
		if(queryPage.getRecordCount()!=null && wsPage!=null){
			if(wsPage.getPage()>queryPage.getPageCount()){
				WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
				wsResultWorkFormBean.setStatus(1);
				wsResultWorkFormBean.setWsPage(null);
				wsResultWorkFormBean.setPendingWorkform(null);
				return wsResultWorkFormBean;
			}
		}
		List<WsPendingWorkformBean> pendinglist=new ArrayList<WsPendingWorkformBean>();
		for (WorkForm workForm : workformList) {
			
			WsPendingWorkformBean wsPendingWorkformBean=new WsPendingWorkformBean();
			wsPendingWorkformBean.setAppointmentDate(DateUtils.formatDate(workForm.getAppointmentDate(),"yyyy-MM-dd HH:mm:ss"));
			
			if(null!=workForm.getEquipment()){
				wsPendingWorkformBean.setAtmManager(workForm.getEquipment().getAtmManager());
				wsPendingWorkformBean.setAtmManagerTel(workForm.getEquipment().getAtmManagerTel());
				//wsPendingWorkformBean.setBranchId(branchId);
				wsPendingWorkformBean.setBranchName(workForm.getEquipment().getBranchName());
				if(null!=workForm.getEquipment().getCustomer()){
					wsPendingWorkformBean.setCustomerName(workForm.getEquipment().getCustomer().getCustomerName());
				}
				
				wsPendingWorkformBean.setEquipmentId(workForm.getEquipment().getEquipmentId()!=null?workForm.getEquipment().getEquipmentId().toString():"");
				wsPendingWorkformBean.setInstallAddress(workForm.getEquipment().getInstallAddress());
				wsPendingWorkformBean.setSerialNumber(workForm.getEquipment().getSerialNumber());
			}
			
			wsPendingWorkformBean.setEngineerName(workForm.getEngineerName());
			wsPendingWorkformBean.setPoNumber(workForm.getPoNumber());
			wsPendingWorkformBean.setReportTel(workForm.getReportTelephone());
			wsPendingWorkformBean.setReportTime(DateUtils.formatDate(workForm.getReportTime(),"yyyy-MM-dd HH:mm:ss"));
			
			//wsPendingWorkformBean.setTaskContent(taskContent);
			wsPendingWorkformBean.setTaskLevel(workForm.getTaskLevel());
			wsPendingWorkformBean.setWorkformId(workForm.getWorkFormId().toString());
			wsPendingWorkformBean.setCreateDate(DateUtils.formatDate(workForm.getCreateDate()));
		    
			
			wsPendingWorkformBean.setArriveTime(DateUtils.formatDate(workForm.getArriveTime(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setStartWorkTime(DateUtils.formatDate(workForm.getWorkStartDate(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setFinishWorkTime(DateUtils.formatDate(workForm.getWorkFinishDate(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setWorkFormStatus(workForm.getStatus());
			WorkFormControlBean workFormControlBean=new WorkFormControlBean();
			workFormControlBean.setWorkFormId(workForm.getWorkFormId());
			List<WorkFormControl> contolList=workFormControlDAO.getWorkFormContext(workFormControlBean);
			if(contolList!=null && contolList.size()>0){
				for(WorkFormControl workFormControl:contolList){
					if(workFormControl.getTimeType()==1){
						wsPendingWorkformBean.setLeaveTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==5){
						wsPendingWorkformBean.setAcceptTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}
					/*else if(workFormControl.getTimeType()==2){
						//wsPendingWorkformBean.setArriveTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==3){
						//wsPendingWorkformBean.setStartWorkTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==4){
						//wsPendingWorkformBean.setFinishWorkTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}*/
					
				}
			}
			pendinglist.add(wsPendingWorkformBean);
		}
		WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
		wsResultWorkFormBean.setStatus(1);
		WsPage resultPage=new WsPage();
		resultPage.setOrder(queryPage.getOrder());
		resultPage.setPage(queryPage.getPage());
		resultPage.setRecordCount(queryPage.getRecordCount());
		resultPage.setRows(queryPage.getRows());
		resultPage.setSort(queryPage.getSort());
		wsResultWorkFormBean.setWsPage(resultPage);
		wsResultWorkFormBean.setPendingWorkform(pendinglist);
		return wsResultWorkFormBean;
	}

	@Override
	public String getTaskType() {
		List<JSONObject> list = DataDictionaryLoad.getJSONListByKey(DataDictionaryConstants.SERVICE_TASK_TYPE);
		if (null != list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.element("value", "YCSJ");
			jsonObject.element("text", "远程分发");
			list.remove(jsonObject);
			jsonObject = new JSONObject();
			jsonObject.element("value", "PH");
			jsonObject.element("text", "配合辅助");
			list.remove(jsonObject);
			return list.toString();
		} else {
			return null;
		}
	}
	
	
	@Override
	public WsResultPendingWorkFormBean getWorkFormByCreatUser(String loginCode,
			WsPage wsPage, String serialNumber, String branchName,
			String workFormStatus, String taskStatus, String engineerUserCode) {
		String inteface = " getPendingWorkForm(String loginCode,WsPage wsPage)";
		String visitor = loginCode ;
		logger.info("[{}] visited the interface [{}]. "+new String[] {visitor,inteface});
		
		if(StringUtils.isBlank(loginCode)){
			WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
			wsResultWorkFormBean.setErrMsg("用户不存在，请检查。");
			wsResultWorkFormBean.setStatus(0);
	        return wsResultWorkFormBean;
		}
		CssUser user=userDAO.getUserByLoginName(loginCode);
	 
		if(user==null){
			WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
			wsResultWorkFormBean.setErrMsg("用户不存在，请检查。");
			wsResultWorkFormBean.setStatus(0);
			return wsResultWorkFormBean;
		}
		
		
		Page queryPage=new Page();
		if(wsPage!=null){
			queryPage.setOrder(wsPage.getOrder());
			queryPage.setPage(wsPage.getPage());
			//queryPage.setPageCount();
			queryPage.setRecordCount(wsPage.getRecordCount());
			if(wsPage.getRows()!=null){
				queryPage.setRows(wsPage.getRows());
			}else{
				queryPage.setRows(20);
			}
			queryPage.setSort(wsPage.getSort());
		}else{
			queryPage.setRows(20);
		}
		
		WorkFormBean workformBean=new WorkFormBean();
		workformBean.setCreateUserId(user.getUserId());
		
		//1、	本页默认显示7天内的所有工单；
		
		if (StringUtils.isBlank(serialNumber) && StringUtils.isBlank(branchName) && StringUtils.isBlank(engineerUserCode)) {
			workformBean.setCreateDateStart(DateUtils.formatDate(DateUtil.addDay(new Date(),-7)));
			workformBean.setCreateDateEnd(DateUtils.formatDate(new Date()));
		}
		
		//4、	若按工单状态查询，则查询登录人派过的所有工单对应的状态。
		//2、	若按设备序列号查询，则查询该设备一个月内的工单情况，包含临时任务和计划任务；
		//3、	若按网点查询，则查询该网点一个月内所有设备的工单情况，包含临时任务和计划任务；
		//6、	若按工程师查询，则查询该工程师一个月内所有的工单情况。
		
		if (StringUtils.isNotBlank(workFormStatus)||StringUtils.isNotBlank(taskStatus)||StringUtils.isNotBlank(serialNumber)||StringUtils.isNotBlank(branchName)||StringUtils.isNotBlank(engineerUserCode)){
			workformBean.setCreateDateStart(DateUtil.addDay(new Date(), -30, "yyyy-MM-dd"));
			workformBean.setCreateDateEnd(DateUtils.formatDate(new Date()));
			workformBean.setTaskStatus(taskStatus);
			workformBean.setStatus(workFormStatus);
			workformBean.setSerialNumber(serialNumber);
			workformBean.setBranchName(branchName);
			if(StringUtils.isNotBlank(engineerUserCode)){
				CssUser engineer = userDAO.getUserByUserCode(engineerUserCode);
				workformBean.setEngineerId(engineer.getUserId());
			}
		}
		
		//workformBean.setStatusArray(new String[]{ServicesConstants.STATUS_WORKFORM_INPROCESS,ServicesConstants.STATUS_WORKFORM_BACK});
		List<WorkForm> workformList = workformDAO.queryWorkFormListByCreatUser(queryPage, workformBean);
		
		if(queryPage.getRecordCount()!=null && wsPage!=null){
			if(wsPage.getPage()>queryPage.getPageCount()){
				WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
				wsResultWorkFormBean.setStatus(1);
				wsResultWorkFormBean.setWsPage(null);
				wsResultWorkFormBean.setPendingWorkform(null);
				return wsResultWorkFormBean;
			}
		}
		List<WsPendingWorkformBean> pendinglist=new ArrayList<WsPendingWorkformBean>();
		for (WorkForm workForm : workformList) {
			
			WsPendingWorkformBean wsPendingWorkformBean=new WsPendingWorkformBean();
			wsPendingWorkformBean.setAppointmentDate(DateUtils.formatDate(workForm.getAppointmentDate(),"yyyy-MM-dd HH:mm:ss"));
			
			if(null!=workForm.getEquipment()){
				wsPendingWorkformBean.setAtmManager(workForm.getEquipment().getAtmManager());
				wsPendingWorkformBean.setAtmManagerTel(workForm.getEquipment().getAtmManagerTel());
				//wsPendingWorkformBean.setBranchId(branchId);
				wsPendingWorkformBean.setBranchName(workForm.getEquipment().getBranchName());
				if(null!=workForm.getEquipment().getCustomer()){
					wsPendingWorkformBean.setCustomerName(workForm.getEquipment().getCustomer().getCustomerName());
				}
				
				wsPendingWorkformBean.setEquipmentId(workForm.getEquipment().getEquipmentId()!=null?workForm.getEquipment().getEquipmentId().toString():"");
				wsPendingWorkformBean.setInstallAddress(workForm.getEquipment().getInstallAddress());
				wsPendingWorkformBean.setSerialNumber(workForm.getEquipment().getSerialNumber());
			}
			
			wsPendingWorkformBean.setEngineerName(workForm.getEngineerName());
			wsPendingWorkformBean.setPoNumber(workForm.getPoNumber());
			wsPendingWorkformBean.setReportTel(workForm.getReportTelephone());
			wsPendingWorkformBean.setReportTime(DateUtils.formatDate(workForm.getReportTime(),"yyyy-MM-dd HH:mm:ss"));
			
			//wsPendingWorkformBean.setTaskContent(taskContent);
			wsPendingWorkformBean.setTaskLevel(workForm.getTaskLevel());
			wsPendingWorkformBean.setWorkformId(workForm.getWorkFormId().toString());
			wsPendingWorkformBean.setCreateDate(DateUtils.formatDate(workForm.getCreateDate()));
		    
			
			wsPendingWorkformBean.setArriveTime(DateUtils.formatDate(workForm.getArriveTime(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setStartWorkTime(DateUtils.formatDate(workForm.getWorkStartDate(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setFinishWorkTime(DateUtils.formatDate(workForm.getWorkFinishDate(),"yyyy-MM-dd HH:mm:ss"));
			wsPendingWorkformBean.setWorkFormStatus(workForm.getStatus());
			WorkFormControlBean workFormControlBean=new WorkFormControlBean();
			workFormControlBean.setWorkFormId(workForm.getWorkFormId());
			List<WorkFormControl> contolList=workFormControlDAO.getWorkFormContext(workFormControlBean);
			if(contolList!=null && contolList.size()>0){
				for(WorkFormControl workFormControl:contolList){
					if(workFormControl.getTimeType()==1){
						wsPendingWorkformBean.setLeaveTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==5){
						wsPendingWorkformBean.setAcceptTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}
					/*else if(workFormControl.getTimeType()==2){
						//wsPendingWorkformBean.setArriveTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==3){
						//wsPendingWorkformBean.setStartWorkTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}else if(workFormControl.getTimeType()==4){
						//wsPendingWorkformBean.setFinishWorkTime(DateUtils.formatDate(workFormControl.getDateTime(),"yyyy-MM-dd HH:mm:ss"));
					}*/
					
				}
			}
			pendinglist.add(wsPendingWorkformBean);
		}
		WsResultPendingWorkFormBean wsResultWorkFormBean=new WsResultPendingWorkFormBean();
		wsResultWorkFormBean.setStatus(1);
		WsPage resultPage=new WsPage();
		resultPage.setOrder(queryPage.getOrder());
		resultPage.setPage(queryPage.getPage());
		resultPage.setRecordCount(queryPage.getRecordCount());
		resultPage.setRows(queryPage.getRows());
		resultPage.setSort(queryPage.getSort());
		wsResultWorkFormBean.setWsPage(resultPage);
		wsResultWorkFormBean.setPendingWorkform(pendinglist);
		return wsResultWorkFormBean;
	}
}
