package com.grgbanking.css.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.grgbanking.common.utils.ArrayTransitionUtils;
import com.grgbanking.common.utils.DateUtil;
import com.grgbanking.common.utils.ListUtils;
import com.grgbanking.common.utils.StringUtilsExtends;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.bean.work.WorkFormBean;
import com.grgbanking.css.bean.work.WorkFormRevisitPage;
import com.grgbanking.css.bean.work.WorkFormSearchPape;
import com.grgbanking.css.bean.work.WorkTask;
import com.grgbanking.css.bean.work.WorkTaskBean;
import com.grgbanking.css.bean.work.WorkTaskSearchPape;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.dao.CssUserDAO;
import com.grgbanking.css.dao.WorkTaskDao;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.EquipmentConstants;
import com.grgbanking.css.util.HqlHelper;
import com.grgbanking.css.util.ServicesConstants;


/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: Administrator
 * Date: 2010-7-29 下午06:56:27
 */
@Repository("workTaskDao")
public class WorkTaskDaoImpl extends CssBaseDAOImpl<WorkTask, Integer> implements
WorkTaskDao<WorkTask, Integer> {
	
	@Autowired
	private CssUserDAO<CssUser, Integer> userDAO;

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getAllWorkTaskJsonList(com.grgbanking.framework.core.Page, com.grgbanking.soc.bean.services.WorkTaskBean)
	 */
	public List<WorkTask> getAllWorkTaskJsonList(Page queryPage,WorkTaskBean workTaskBean) {
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		
		if(workTaskBean.getProcessNo()!=null){
			String[] processList =  workTaskBean.getProcessNo().split(",");
			if(processList.length>1){
				hqlHelper.addIn("processNo", workTaskBean.getProcessNo()!=null?processList:null);//设备序列号
			}else{
				hqlHelper.addEqual("processNo", workTaskBean.getProcessNo());
			}
		}
		
		//部门多选
		if(StringUtils.isNotBlank(workTaskBean.getDepartmentIds())){
			String[] departmentIdArray =workTaskBean.getDepartmentIds().split(",");
			List<Integer> departmentIdList=new ArrayList<Integer>();
			for (String string : departmentIdArray) {
				if(StringUtils.isNotBlank(string)){
					departmentIdList.add(Integer.valueOf(string));
				}
			}
			hqlHelper.addIn("equipment.department.departmentId",departmentIdList);
		}
		if(workTaskBean.getDepartmentId()!=null){
			hqlHelper.addIn("equipment.department.departmentId",this.getChildDepartmentId(workTaskBean.getDepartmentId()));
		}
		
		
		hqlHelper.addIn("equipment.equipmentStatus", workTaskBean.getEquipmentStatusArray());
		
		//客户多选
		if(StringUtils.isNotBlank(workTaskBean.getCustomerIds())){
			String[] customerIdArray =workTaskBean.getCustomerIds().split(",");
			List<Integer> customerIdList=new ArrayList<Integer>();
			for (String string : customerIdArray) {
				if(StringUtils.isNotBlank(string)){
					customerIdList.add(Integer.valueOf(string));
				}
			}
			hqlHelper.addIn("equipment.customer.customerId", customerIdList);//多选客户
		}
		
		//升级项目代码
		if(StringUtils.isNotBlank(workTaskBean.getUpgradeName())){
			List<Object[]> upgradeCodeList=this.findBySQL("select t.upgrade_code from V_services_upgrade t where upper(t.subject) like '%"+workTaskBean.getUpgradeName().trim().toString().toUpperCase()+"%'");
			if(upgradeCodeList==null||upgradeCodeList.size()==0){
				return new ArrayList<WorkTask>();
			}
			hqlHelper.addIn("upgradeCode", upgradeCodeList);
		}
		hqlHelper.addLike("upgradeCode", workTaskBean.getUpgradeCode());
		hqlHelper.addEqual("equipment.equipmentId", workTaskBean.getEquipmentId());
		hqlHelper.addLike("equipment.branchName", workTaskBean.getBranchName());
		hqlHelper.addEqual("reportTime", workTaskBean.getReportTime());
		hqlHelper.addDateBetween("appointmentTime",workTaskBean.getAppointmentTimeStart(),workTaskBean.getAppointmentTimeEnd());
		hqlHelper.addIn("taskType",workTaskBean.getTaskType()!=null?workTaskBean.getTaskType().split(","):null);
		hqlHelper.addIn("taskSource",workTaskBean.getTaskSource()!=null?workTaskBean.getTaskSource().split(","):null);
		hqlHelper.addIn("status", workTaskBean.getStatusArray());
		hqlHelper.addIn("status", workTaskBean.getStatus()!=null?workTaskBean.getStatus().split(","):null);
		hqlHelper.addEqual("engineerId", workTaskBean.getEngineerId());
		hqlHelper.addIn("taskSource", workTaskBean.getTaskSourceArray());
		hqlHelper.addEqual("upgradeCode", workTaskBean.getUpgradeCode());
		hqlHelper.addEqual("createType", workTaskBean.getCreateType());
		
		if(StringUtils.isNotBlank(workTaskBean.getEquipmentChargeName())){
			CssUser us=userDAO.getUserByChargeName(workTaskBean.getEquipmentChargeName());
			if(us!=null){
				hqlHelper.addEqual("equipment.equipmentCharge", us.getUserId());
			}else{
				hqlHelper.addEqual("equipment.equipmentCharge", 11111);
			}
		}
		
		
		//设备序列号多选
		if(workTaskBean.getSerialNumber()!=null){
			String[] serialNumList =  workTaskBean.getSerialNumber().split(",");
			if(serialNumList.length>1){
				hqlHelper.addIn("equipment.serialNumber", workTaskBean.getSerialNumber()!=null?serialNumList:null);//设备序列号
			}else{
				hqlHelper.addEqual("equipment.serialNumber", workTaskBean.getSerialNumber());
			}
		}
		hqlHelper.addDateBetween("planStartDate", workTaskBean.getPlanStartDateStart(), workTaskBean.getPlanStartDateEnd());
		hqlHelper.addDateBetween("planEndDate", workTaskBean.getPlanEndDateStart(), workTaskBean.getPlanEndDateEnd());
		hqlHelper.addDateBetween("workForm.workFinishDate", workTaskBean.getWorkFinishDateStart(), workTaskBean.getWorkFinishDateEnd());
		
		if("CSS".equals(workTaskBean.getAcceptStyle())){
			HqlHelper hqlHelper1 = new HqlHelper();
			hqlHelper1.addIsNull("acceptStyle");//12-31去sa工单
			hqlHelper1.addEqual("acceptStyle",workTaskBean.getAcceptStyle());
			hqlHelper.addOr(hqlHelper1);
		}
		
		hqlHelper.setQueryPage(queryPage);
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#updateCancelStatus(java.lang.Integer[])
	 */
	/*public void updateCancelStatus(String code) {
		//只取消未处理的升级计划
		String sql = "update V_SERVICES_TASK set STATUS='" + ServicesConstants.STATUS_TASK_PROCESS_CANCEL + "' where UPGRADE_CODE = '" + code + "' and STATUS in ('"+ServicesConstants.STATUS_TASK_PROCESS_PENDING+"','"+ServicesConstants.STATUS_TASK_PROCESS_UNALLOCATED+"')"; 
		this.executeSql(sql);
	}*/

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#contTask(java.lang.String)
	 */
	public int countTask(String upgradeCode, String equipmentStatus, Integer departmentId) {
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addEqual("upgradeCode", upgradeCode);
		hqlHelper.addNotEqual("status", ServicesConstants.STATUS_TASK_PROCESS_CANCEL);
		hqlHelper.addEqual("equipment.equipmentStatus", equipmentStatus);
		if(departmentId!=null){
			//hqlHelper.addEqual("equipment.department.departmentId", departmentId);
			hqlHelper.addIn("equipment.department.departmentId", this.getChildDepartmentId(departmentId));
		}
		return this.countByHQL("select count(*) "+hqlHelper.getHQL(), hqlHelper.getParams().toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#contTaskFinish(java.lang.String)
	 */
	public int countTaskFinish(String upgradeCode, String equipmentStatus, Integer departmentId) {
//		return this.countByHQL("select count(*) from WorkTask t where t.upgradeCode = '" + upgradeCode
//				+ "' and t.status = '" + ServicesConstants.STATUS_TASK_PROCESS_COMPLETED
//				+ "' and t.equipment.equipmentStatus = '" + EquipmentConstants.EQUIPMENT_STATUS_USE + "' order by t.taskId asc");  
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addEqual("upgradeCode", upgradeCode);
		hqlHelper.addEqual("status", ServicesConstants.STATUS_TASK_PROCESS_COMPLETED);
		hqlHelper.addEqual("equipment.equipmentStatus", EquipmentConstants.EQUIPMENT_STATUS_USE);
		if(departmentId!=null){
			hqlHelper.addIn("equipment.department.departmentId", this.getChildDepartmentId(departmentId));
		}
		return this.countByHQL("select count(*) "+hqlHelper.getHQL(), hqlHelper.getParams().toArray());
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#deleteTask(com.grgbanking.soc.entity.equipment.EquipmentInfo)
	 */
	/*public void deleteTask(EquipmentInfo equipment) {
		String sql = "update V_SERVICES_TASK set STATUS='" + ServicesConstants.STATUS_TASK_PROCESS_CANCEL + "' where EQUIPMENT_ID = " + equipment.getEquipmentId(); 
		this.executeSql(sql);
	}*/

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#queryTaskEquipment(java.lang.String)
	 */
	/*public List<WorkTask> queryTaskEquipment(String upgradeCode, String status, String serialNumber) {
		checkArgument(isNotBlankAndEmpty(upgradeCode), "upgradeCode为空！");
		checkArgument(isNotBlankAndEmpty(serialNumber), "serialNumber为空！");
		String hql = "from WorkTask t where  t.upgradeCode = ? and t.equipment.serialNumber=?";
		if(isNotBlankAndEmpty(status)){
			hql += " and t.status=?";
			return this.find(hql, new Object[]{upgradeCode, serialNumber, status}); 
		}else{
			return this.find(hql, new Object[]{upgradeCode, serialNumber}); 
		}
	}*/

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getALLListWorktaskBYDate(java.lang.String)
	 */
	public List<WorkTask> getALLListWorktaskBYDate(String serialNumber) {
		//String hql=" from WorkTask where deleted='"+CommonConstants.FLAG_N+"' and isPlan='"+CommonConstants.FLAG_N+"' and taskType='"+ServicesConstants.TASK_TYPE_MAINTAIN+"' and status!='"+ServicesConstants.STATUS_TASK_PROCESS_COMPLETED+"' or status!='"+ServicesConstants.STATUS_TASK_PROCESS_CANCEL+"' and equipmentId.serialNumber='"+serialNumber+"'";
		String hql="from WorkTask where deleted='"+CommonConstants.FLAG_N+"' and taskSource='"+ServicesConstants.SOURCE_TASK_SCHEDULED_TASKS+"' and taskType='"+ServicesConstants.TASK_TYPE_MAINTAIN+"' and equipment.serialNumber='"+serialNumber+"'  and status!='"+ServicesConstants.STATUS_TASK_PROCESS_COMPLETED+"' and status!='"+ServicesConstants.STATUS_TASK_PROCESS_CANCEL+"'  order by planStartDate ";
		return this.find(hql);
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#queryUnProcessTaskForDispatch(java.lang.String)
	 */
	public List<WorkTask> queryUnProcessTaskForDispatch(String serialNumber) {
		String hql="from WorkTask w where w.equipment.serialNumber=? and w.deleted=? and w.status in (?,?)";
		return this.find(hql, new Object[]{serialNumber,CommonConstants.FLAG_N,ServicesConstants.STATUS_TASK_PROCESS_UNALLOCATED,ServicesConstants.STATUS_TASK_PROCESS_PENDING});
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#queryUnProcessTaskCountForStationView(java.lang.Integer)
	 */
	public int queryUnProcessTaskCountForStationView(Integer equipmentId) {
		String sql="select count(*) from V_services_task t where t.status=? and t.equipment_id=? and (t.task_source<>? or ( to_char(t.plan_start_date,'yyyy-MM-dd')<=? and t.task_source=? ))";
		return this.countBySQL(sql, new Object[]{ServicesConstants.STATUS_TASK_PROCESS_UNALLOCATED,equipmentId,ServicesConstants.SOURCE_TASK_SCHEDULED_TASKS,ServicesConstants.SOURCE_TASK_SCHEDULED_TASKS,DateUtil.getDate("yyyy-MM-dd")});
	}
	
	public List<WorkTask> queryUnAssignTaskForDispatch(String serialNumber) {
		String hql="from WorkTask w where w.equipment.serialNumber=? and w.deleted=? and w.status=? and (w.taskSource<>? or (w.taskSource=? and to_char(w.planStartDate,'yyyy-MM-dd')<?))";
		return this.find(hql, new Object[]{serialNumber,CommonConstants.FLAG_N,ServicesConstants.STATUS_TASK_PROCESS_UNALLOCATED,ServicesConstants.SOURCE_TASK_SCHEDULED_TASKS,ServicesConstants.SOURCE_TASK_SCHEDULED_TASKS,DateUtil.getDate("yyyy-MM-dd")});
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#queryWorkFormList(com.grgbanking.framework.core.Page, com.grgbanking.soc.bean.services.WorkFormBean)
	 */
	public List<WorkTask> queryWorkFormList(Page queryPage,WorkFormBean workformBean) {
		//有任务工单
		HqlHelper hqlHelper1=new HqlHelper(WorkTask.class);
		//工单号的查询：多个工单号与单个工单号的查询方法，单个工单号支持不分大小写模糊查询
		if(workformBean.getPoNumber()!=null){
			String[] poNumList =  workformBean.getPoNumber().split(",");
			if(poNumList.length>1){
				hqlHelper1.addIn("workForm.poNumber", poNumList);
			}else{
				hqlHelper1.addEqual("workForm.poNumber", workformBean.getPoNumber());
			}
		}else{
			hqlHelper1.addEqual("workForm.poNumber", null);
		}
		
		//设备序列号查询：可进行多个查询，与单个序列号的模糊查询
		if(workformBean.getSerialNumber()!=null){
			String[] serialNumList =  workformBean.getSerialNumber().split(",");
			if(serialNumList.length>1){
				hqlHelper1.addIn("equipment.serialNumber", serialNumList);
			}else{
				hqlHelper1.addEqual("equipment.serialNumber", workformBean.getSerialNumber());
			}
		}else{
			hqlHelper1.addEqual("equipment.serialNumber", null);
		}
		hqlHelper1.addEqual("equipment.equipmentId", workformBean.getEquipmentId());
		if(StringUtils.isNotBlank(workformBean.getManufacturer())){
			hqlHelper1.addIn("equipment.manufacturer",workformBean.getManufacturer().split(",") );
		}
		
		if(StringUtils.isNotBlank(workformBean.getEquipmentModel())){
			hqlHelper1.addIn("equipment.equipmentModel",workformBean.getEquipmentModel().split(",") );
		}
		hqlHelper1.addLikeIgnoreCase("equipment.saleContractNo", workformBean.getSaleContractNo());
		hqlHelper1.addIn("equipment.saleProperty", workformBean.getSaleProperty()!=null?workformBean.getSaleProperty().split(","):null);
		hqlHelper1.addLikeIgnoreCase("equipment.maintainContractNo", workformBean.getMaintainContractNo());
		hqlHelper1.addIn("warrantyType", workformBean.getWarrantyType()!=null?workformBean.getWarrantyType().split(","):null);//服务保修类型:多选
		if(StringUtils.isNotBlank(workformBean.getEquipmentType())){
			hqlHelper1.addIn("equipment.equipmentType",workformBean.getEquipmentType().split(",") );
		}
		
		if(workformBean.getDepartmentId()!=null){
			hqlHelper1.addIn("workForm.department.departmentId", this.getChildDepartmentId(workformBean.getDepartmentId()));
			hqlHelper1.addEqual("workForm.department.parent.departmentName", workformBean.getRegionName());
		}
		//服务站多选
		if(StringUtils.isNotBlank(workformBean.getDepartmentIds())){
			String[] departmentIdArray =workformBean.getDepartmentIds().split(",");
			List<Integer> departmentIdList=new ArrayList<Integer>();
			for (String string : departmentIdArray) {
				if(StringUtils.isNotBlank(string)){
					departmentIdList.add(Integer.valueOf(string));
				}
			}
			hqlHelper1.addIn("workForm.department.departmentId", departmentIdList);
		}
		
		if(workformBean.getDepartmentId()!=null){
			hqlHelper1.addIn("workForm.department.departmentId", this.getChildDepartmentId(workformBean.getDepartmentId()));
		}
		
		//客户多选
		if(StringUtils.isNotBlank(workformBean.getCustomerIds())){
			String[] customerIdArray =workformBean.getCustomerIds().split(",");
			List<Integer> customerIdList=new ArrayList<Integer>();
			for (String string : customerIdArray) {
				if(StringUtils.isNotBlank(string)){
					customerIdList.add(Integer.valueOf(string));
				}
			}
			hqlHelper1.addIn("equipment.customer.customerId",customerIdList);//多选客户
		}
		hqlHelper1.addIn("equipment.equipmentStatus", workformBean.getEquipmentStatus()!=null?workformBean.getEquipmentStatus().split(","):null);//设备状态:多选
		hqlHelper1.addIn("taskType", workformBean.getTaskType()!=null?workformBean.getTaskType().split(","):null);//服务类型：多选
		hqlHelper1.addIn("equipment.installModel", workformBean.getInstallModel()!=null?workformBean.getInstallModel().split(","):null);
		hqlHelper1.addIn("workForm.status", workformBean.getStatus()!=null?workformBean.getStatus().split(","):null);//工单状态：多选
		hqlHelper1.addDateBetween("workForm.createDate", workformBean.getCreateDateStart(), workformBean.getCreateDateEnd());
		hqlHelper1.addDateBetween("workForm.workFinishDate", workformBean.getWorkFinishDateStart(), workformBean.getWorkFinishDateEnd());
		hqlHelper1.addDateBetween("workForm.lastAuditDate", workformBean.getLastAuditDateStart(), workformBean.getLastAuditDateEnd());
		hqlHelper1.addIn("workForm.engineerName", workformBean.getEngineerName()!=null?workformBean.getEngineerName().split(","):null);//工程师：多选
		hqlHelper1.addEqual("equipment.provinceId",workformBean.getProvinceId());
		hqlHelper1.addEqual("equipment.cityId", workformBean.getCityId());
		hqlHelper1.addEqual("workForm.costTime",workformBean.getCostTime());
		hqlHelper1.addDateBetween("equipment.installDate",workformBean.getInstallDateStart(),workformBean.getInstallDateEnd());
		hqlHelper1.addEqual("workForm.satisfiedType",workformBean.getSatisfiedType());
		
		if(StringUtils.isNotBlank(workformBean.getHasRevisit())){
			if(workformBean.getHasRevisit().equals(CommonConstants.FLAG_Y)){
				hqlHelper1.addEqual("workForm.hasRevisit",workformBean.getHasRevisit());
			}else{
				HqlHelper or1=new HqlHelper();
				or1.addIsNull("workForm.hasRevisit");
				or1.addEqual("workForm.hasRevisit",workformBean.getHasRevisit());
				hqlHelper1.addOr(or1);
			}
		}
		hqlHelper1.addDateBetween("equipment.warrantyEndDate", workformBean.getWarrantyEndDateStart(), workformBean.getWarrantyEndDateEnd());//到保日期
		hqlHelper1.addIsNotNull("workForm.poNumber");
		
		hqlHelper1.addLikeIgnoreCase("equipment.branchName",workformBean.getBranchName());
		
		if(StringUtils.isNotBlank(workformBean.getEquipmentChargeName())){
			CssUser us=userDAO.getUserByChargeName(workformBean.getEquipmentChargeName());
			if(us!=null){
				hqlHelper1.addEqual("equipment.equipmentCharge",us.getUserId());
			}else{
				hqlHelper1.addEqual("equipment.equipmentCharge",111111);
			}
		}
		
		// by luow 2012-10-23 新增通过工单编号查询
		if(StringUtils.isNotEmpty(workformBean.getWorkFormIds()))
			hqlHelper1.addIn("workForm.workFormId", ArrayTransitionUtils.stringToIntegerArray(workformBean.getWorkFormIds(), ","));
		// by luow 2013-3-18 新增通过客户满意度查询
		if(StringUtils.isNotEmpty(workformBean.getSatisfiedType()))
			hqlHelper1.addEqual("workForm.satisfiedType", workformBean.getSatisfiedType());
		
		if(queryPage==null){
			hqlHelper1.addOrderBy("workForm.workFinishDate", "asc");
		}else{
			hqlHelper1.setQueryPage(queryPage);
		}
		return this.find(hqlHelper1);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getSumSelectWorktask(java.lang.String, java.lang.Integer)
	 */
	public Integer getSumSelectWorktask(String status,String taskSource, Integer departmentId,Integer userId) {
		String sql;
		if(taskSource!=null){
			sql="select count(*) from V_services_task t left join V_EMS_EQUIPMENT_INFO e on t.equipment_id=e.equipment_id where t.status='"+status+"' and t.deleted='"+CommonConstants.FLAG_N+"' and t.task_source='"+taskSource+"' and e.equipment_status='"+EquipmentConstants.EQUIPMENT_STATUS_USE+"' ";
			if(userId!=null){
				sql+=" and t.engineer_id="+userId;
			}
		}else{
			sql="select count(*) from V_services_task t left join V_EMS_EQUIPMENT_INFO e on t.equipment_id=e.equipment_id where t.status='"+status+"' and t.deleted='"+CommonConstants.FLAG_N+"' and t.task_source not in('"+ServicesConstants.SOURCE_TASK_SCHEDULED_TASKS+"','"+ServicesConstants.SOURCE_TASK_AUTO_UPGRADE+"') and e.equipment_status='"+EquipmentConstants.EQUIPMENT_STATUS_USE+"' ";
			if(userId!=null){
				sql+=" and t.engineer_id="+userId;
			}
			sql+=" and (t.accept_style='CSS' or t.accept_style is null) ";
		}
		if(departmentId!=null){
			sql+=" and e.department_id="+departmentId;
		}
		
		return this.countBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getAllWorkTaskList(com.grgbanking.soc.bean.services.WorkTaskBean)
	 * select t.task_type,t.status, count(*) from t_services_task t where t.status<>'已完成' group by t.task_type,t.status
	 */
	public List<WorkTask> getAllWorkTaskList(Integer deptId,String status, String taskSource) {
		String hql="from WorkTask w where w.equipment.department.departmentId=? and w.deleted=? and w.status=? and w.taskSource=? and to_char(w.planStartDate,'yyyy-MM-dd')<?)";
		return this.find(hql, new Object[]{deptId
										,CommonConstants.FLAG_N
										,status
										,taskSource
										,DateUtil.getDate("yyyy-MM-dd")});
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getCountNotComplete(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getCountNotComplete(Integer deptId) {
		String hql="select w.taskType,w.status, count(*) from WorkTask w where w.status<>? and w.status<>? and w.deleted=? and w.equipment.department.departmentId=? group by w.taskType,w.status";
		return this.find(null,hql, new Object[]{ServicesConstants.STATUS_TASK_PROCESS_COMPLETED,ServicesConstants.STATUS_TASK_PROCESS_CANCEL,CommonConstants.FLAG_N,deptId});
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getUserListByDepartmentId(java.lang.Integer)
	 */
	public List<Object[]> getUserListByDepartmentId(Integer departmentId) {
		String sql="select  u.name,u.user_id,nvl(t.numwt,0),nvl(w.numwf,0) from (select name,user_id from V_base_user where deleted='N' and department_id="+departmentId+") u,"
				 +"(select s.engineer_id as sid ,count(s.task_id) as numwt "
                 +" from V_services_task s where s.engineer_id is not null and s.workform_id is null and s.status<>'"+ServicesConstants.STATUS_TASK_PROCESS_COMPLETED+"' and s.status<>'"+ServicesConstants.STATUS_TASK_PROCESS_CANCEL+"' and s.deleted='N' group by s.engineer_id)t,"
                 +" (select sw.engineer_id as wid , count(*) as numwf "
                 +" from V_services_workform sw where sw.engineer_id is not null and sw.status<>'"+ServicesConstants.STATUS_WORKFORM_BACK+"' and sw.status<>'"+ServicesConstants.STATUS_TASK_PROCESS_COMPLETED+"'  and sw.deleted='N' group by sw.engineer_id)w "
                 +" where u.user_id=t.sid(+) and u.user_id=w.wid(+)";
		return this.findBySQL(sql);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#queryWorktaskGroupbyTaskType(java.lang.Integer, java.lang.String)
	 */
	public List<Object[]> queryWorktaskGroupbyTaskType(Integer departmentId,
			String datestr) {
		String sql="select t.task_type,count(*) from V_services_task t left join V_services_workform w on t.workform_id=w.workform_id "
			+" where w.department_id in ("+StringUtils.join(this.getChildDepartmentId(departmentId),",")+") and to_char(w.work_finish_date,'yyyy-MM-dd')=? "
			+" group by t.task_type";
		return this.findBySQL(sql, datestr);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getALLWorktaskListByEquipment(java.lang.Integer)
	 */
	public List<WorkTask> getALLWorktaskListByEquipment(Integer equipmentId) {
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addEqual("equipment.equipmentId",equipmentId);
		 hqlHelper.addNotEqual("status", ServicesConstants.STATUS_TASK_PROCESS_COMPLETED);
		 hqlHelper.addNotEqual("status", ServicesConstants.STATUS_TASK_PROCESS_CANCEL);
		 hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getCustomerByDepartmentId(java.lang.Integer)
	 */
	public List<Object[]> getCustomerByDepartmentId(Integer departmentId) {
		String sql="select distinct t.customer_id,c.customer_name"
				+" from V_EMS_EQUIPMENT_INFO t inner join mv_crm_customer_info c on c.id=t.customer_id"
				+" where t.department_id="+departmentId
				+" order by c.customer_name";
		return this.findBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getWorkTaskByIds(java.util.List)
	 */
	public List<WorkTask> getWorkTaskByIds(List<Integer> taskIds) {
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addIn("taskId", taskIds);
//		hqlHelper.addEqual("taskType", ServicesConstants.TASK_TYPE_REPAIR);
		return this.find(hqlHelper);
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getNotCompletedWX(java.lang.Integer)
	 */
	public int getNotCompletedWX(Integer equipmentId) {
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addEqual("equipment.equipmentId", equipmentId);
		hqlHelper.addEqual("taskType", ServicesConstants.TASK_TYPE_REPAIR);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addNotIn("status", new String[]{ServicesConstants.STATUS_TASK_PROCESS_COMPLETED,ServicesConstants.STATUS_TASK_PROCESS_CANCEL});
		return this.countByHQL("select count(*) "+hqlHelper.getHQL(), hqlHelper.getParams().toArray());
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getSumSelectStatusWorktask(java.lang.String, java.lang.String)
	 */
	public Integer getSumSelectStatusWorktask(String status, String status1,String taskType,Integer departmentId) {
		//String hql="select count(*) from WorkTask where status in('"+status+"','"+status1+"') and deleted='"+CommonConstants.FLAG_N+"' and taskType='"+taskType+"' and equipment.equipmentStatus='"+EquipmentConstants.EQUIPMENT_STATUS_USE+"' and planStartDate<sysdate  and equipment.department.departmentId="+departmentId;
		String sql="select count(*) from V_services_task t left join V_EMS_EQUIPMENT_INFO e on t.equipment_id=e.equipment_id " +
				" where t.deleted='"+CommonConstants.FLAG_N+"' and t.status in('"+status+"','"+status1+"') and t.task_type='"+taskType+"' " +
				" and t.plan_start_date<sysdate and e.equipment_status='"+EquipmentConstants.EQUIPMENT_STATUS_USE+"' and e.department_id="+departmentId;
		return this.countBySQL(sql);
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#queryBYTaskEndDate(java.util.Date)
	 */
	public Date queryBYTaskEndDate(Date lastMaintainDate,Integer equipmentId) {
		String sql="select to_char(t.plan_end_date,'yyyy-MM-dd') from V_services_task t left join V_services_workform w "
					+" on t.workform_id=w.workform_id "
					+" where t.task_type='BY' and t.equipment_id=? and  to_char(w.work_finish_date,'yyyy-MM-dd')=? ";
		List<Object[]> list=this.findBySQL(sql, new Object[]{equipmentId,DateUtil.formatDate(lastMaintainDate)});
		if(list!=null&&list.size()>0){
			return list.get(0)==null?null:DateUtil.parseDate(list.get(0).toString());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#queryThisYearPMtimes(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public int queryThisYearPMtimes(Integer equipmentId, String fromDate,String toDate) {
		String sql="select count(*) from V_services_task t left join V_services_workform w "
			+" on t.workform_id=w.workform_id "
			+" where t.task_type='BY' and t.equipment_id=? and  to_char(w.work_finish_date,'yyyy-MM-dd')>=? and to_char(w.work_finish_date,'yyyy-MM-dd')<=?";
		int count1= this.countBySQL(sql, new Object[]{equipmentId,fromDate,toDate});//已完成的保养
		
		String sql2="select count(*) from V_services_task t where t.equipment_id=?  and to_char(t.plan_end_date,'yyyy-MM-dd')>=? and to_char(t.plan_end_date,'yyyy-MM-dd')<=? and t.task_type='BY' AND T.STATUS='已取消'";
		int count2= this.countBySQL(sql2, new Object[]{equipmentId,fromDate,toDate});//已完成的保养
		
		return count1+count2;
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#queryUnFinishBY(java.lang.String)
	 */
	public boolean queryHasUnFinishBY(String serialNumber) {
		String sql="select count(*) from V_services_task t left join V_EMS_EQUIPMENT_INFO e "
			+ " on e.equipment_id=t.equipment_id "
			+ " where t.task_type='BY' and t.status in (?,?) and e.serial_number=? "
			+" and t.task_source='"+ServicesConstants.SOURCE_TASK_SCHEDULED_TASKS+"' and t.plan_start_date<=sysdate";
		int count= this.countBySQL(sql, new Object[]{ServicesConstants.STATUS_TASK_PROCESS_UNALLOCATED,ServicesConstants.STATUS_TASK_PROCESS_PENDING,serialNumber});
		if(count>0){
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getSelectReportTimeWXList(java.lang.String)
	 */
	public Integer getSelectReportTimeWXList(String equipmentId) {
		String sql="select count(*) from V_services_task t left join V_services_workform w on w.workform_id=t.workform_id where w.work_finish_date>add_months(sysdate,-1)" +
				"  and t.task_type='"+ServicesConstants.TASK_TYPE_REPAIR+"' and w.equipment_id="+equipmentId;
				//and w.status='"+ServicesConstants.STATUS_WORKFORM_COMPLETED+"'
		return this.countBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getQueryYearEorkTask(java.lang.Integer)
	 */
	public List<Object[]> getQueryYearEorkTask(Integer equipmentId,String taskType) {
		String sql="select t.task_id,t.workform_id,t.equipment_id,to_char(w.work_start_date,'yyyy-MM-dd hh:mi'),to_char(w.work_finish_date,'yyyy-MM-dd hh:mi'),t.cost_time,t.engineer_name,t.description,t.task_type from V_services_task t left join V_services_workform w on w.workform_id=t.workform_id "
			+ " where t.equipment_id="+equipmentId+" and to_char(w.work_finish_date,'yyyy')=to_char(sysdate,'yyyy')  " ;
		if(taskType!=null){
			sql+=" and t.task_type='"+taskType+"' ";
		}
		sql+=" order by w.work_finish_date";
		return this.findBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getSelectProcessNo(java.lang.String)
	 */
	public WorkTask getSelectProcessNo(String processNo) {
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addEqual("processNo", processNo);
		List<WorkTask> list=find(hqlHelper);
		if(list.size()>0 && list!=null){
			return list.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#unAsignTaskForSMS(java.lang.String, java.lang.Integer)
	 */
	public List<WorkTask> unAsignTaskForSMS(String smid, Integer departmentId) {
		String hql="from WorkTask w where w.processNo like '%"+smid+"' and w.status='未分配' and w.taskSource='"+ServicesConstants.SOURCE_TASK_MONITORING_CENTER+"' and w.equipment.department.departmentId="+departmentId;
		return this.find(hql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#etSelectEquipmentMovement(java.lang.Integer)
	 */
	public List<WorkTask> getSelectEquipmentMovement(Integer equipmentId) {
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addEqual("status", ServicesConstants.STATUS_TASK_PROCESS_COMPLETED);
		hqlHelper.addEqual("equipment.equipmentId", equipmentId);
		hqlHelper.addEqual("taskType", "WX");
		Date da=new Date();
		Calendar  g = Calendar.getInstance();
        g.add(Calendar.MONTH,-1);              
        Date da2 = g.getTime();   
		String datEnt=DateUtil.formatDate(da);
		String dateStart=DateUtil.formatDate(da2);
		hqlHelper.addDateBetween("workForm.workFinishDate",dateStart,datEnt);
		hqlHelper.addOrderBy("workForm.workFinishDate", "desc");
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getMobileDateString(java.lang.Integer, java.lang.String)
	 */
	public String getMobileDateString(Integer workId, Integer type) {
		String sql="select to_char(t.date_time,'hh24:mi'),t.workform_address from V_workform_control t where t.workform_id="+workId+" and t.time_type="+type+" ";
		List<Object[]> list=this.findBySQL(sql);
		if(list!=null && list.size()>0){
			Object[] ob=list.get(0);
			String str="";
			if(ob[0]!=null){
				str+=ob[0].toString();
			}else{
				str+="";
			}
			if(ob[1]!=null){
				str+=",,"+ob[1].toString();
			}else{
				str+=",,"+"";
			}
			return str;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#queryUnTaskForDispatch(java.lang.String)
	 */
	public List<Object[]> queryUnTaskForDispatch(String serialNumber) {
		String sql="select w.task_id,w.equipment_id,w.appointment_time,w.task_type,w.engineer_name,w.status," +
				" w.upgrade_code,w.plan_start_date,w.plan_end_date,w.task_source,e.serial_number " +
				" from V_services_task w left join V_EMS_EQUIPMENT_INFO e on w.equipment_id=e.equipment_id " +
				" where w.status in('"+ServicesConstants.STATUS_TASK_PROCESS_UNALLOCATED+"','"+ServicesConstants.STATUS_TASK_PROCESS_PENDING+"') and w.deleted='N' and w.task_type<>'BY' " +
				" and e.serial_number='"+serialNumber+"' union " +
				" select * from(select t.task_id,t.equipment_id,t.appointment_time,t.task_type,t.engineer_name,t.status,t.upgrade_code,t.plan_start_date,t.plan_end_date,t.task_source,e1.serial_number " +
				" from V_services_task t left join V_EMS_EQUIPMENT_INFO e1 on t.equipment_id=e1.equipment_id " +
				" where t.task_type='BY' and t.deleted='N' and t.status in('"+ServicesConstants.STATUS_TASK_PROCESS_UNALLOCATED+"','"+ServicesConstants.STATUS_TASK_PROCESS_PENDING+"') and t.task_source='"+ServicesConstants.SOURCE_TASK_SCHEDULED_TASKS+"' " +
				" and e1.serial_number='"+serialNumber+"' order by t.plan_start_date ) where rownum=1 " +
				" union select ta.task_id,ta.equipment_id,ta.appointment_time,ta.task_type,ta.engineer_name,ta.status,ta.upgrade_code,ta.plan_start_date,ta.plan_end_date,ta.task_source,eq.serial_number " +
				" from V_services_task ta left join V_EMS_EQUIPMENT_INFO eq on ta.equipment_id=eq.equipment_id " +
				" where ta.status in('"+ServicesConstants.STATUS_TASK_PROCESS_UNALLOCATED+"','"+ServicesConstants.STATUS_TASK_PROCESS_PENDING+"') and ta.deleted='N' and ta.task_type='BY' " +
				" and ta.task_source<>'"+ServicesConstants.SOURCE_TASK_SCHEDULED_TASKS+"'  and eq.serial_number='"+serialNumber+"' ";
		return this.findBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.worktask.WorkTaskDao#getSelectLastBYDate(java.lang.String, java.lang.Integer)
	 */
	public Date getSelectLastBYDate(String taskType, Integer equipmentId,String taskSource) {
		String sql="select w.work_finish_date,t.task_id from V_services_task t left join V_services_workform w on t.workform_id=w.workform_id " +
				" where t.task_type='"+taskType+"' and  w.equipment_id="+equipmentId+" and t.task_source='"+taskSource+"'  order by w.work_finish_date desc ";
		List<Object[]> list=this.findBySQL(sql);
		if(list!=null && list.size()>0){
			if(list.get(0)!=null){
				Object[] ob=list.get(0);
				if(ob[0]!=null){
					return DateUtil.parseDate(ob[0].toString());
				}else{
					return new Date();
				}
			}
		}
		return null;
	}

	public boolean hasAutoUpgradeWorktask(Integer equipmentId) {
		Assert.notNull(equipmentId,"出现异常情况，查询自动升级任务时，传入参数：【设备ID】为空!");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.task_id");
		sql.append("  from V_services_task t");
		sql.append(" where t.task_source = ?");
		sql.append(" and t.equipment_id = ?");
		sql.append(" and t.deleted = ?");
		sql.append(" and (t.status > ? or t.status < ?)");
		sql.append(" and rownum = 1");
		Object[] queryParam = {ServicesConstants.SOURCE_TASK_AUTO_UPGRADE,equipmentId,CommonConstants.FLAG_N,
				ServicesConstants.STATUS_TASK_PROCESS_COMPLETED,ServicesConstants.STATUS_TASK_PROCESS_COMPLETED};
		List<Object[]> list = findBySQL(sql.toString(),queryParam);
		if(ListUtils.isNotEmpty(list))
			return true;
		return false;
	}
	
	public List<WorkFormRevisitPage> findWorkFormListForRevisit(WorkFormBean bean,Page page){
		
		
		String sql = " select t.task_id,t.workform_id,t.task_type,w.po_number,w.engineer_name,w.create_date,w.status,w.work_finish_date,w.cost_time," +
				" w.equipment_id,w.department_id,w.has_revisit,w.last_revisit_time from V_services_task t, V_services_workform w  " +
				" where t.modify_date >= to_date('"+ DateUtil.addDay(new Date(),-10,"yyyy-MM-dd") +"','yyyy-MM-dd') and t.create_date <= sysdate" +
				" and w.create_date >= to_date('"+ DateUtil.addDay(new Date(),-10,"yyyy-MM-dd") +"','yyyy-MM-dd') and w.create_date <= sysdate" +
				" and t.workform_id = w.workform_id ";
		
		
		
//		//工单号的查询：多个工单号与单个工单号的查询方法，单个工单号支持不分大小写模糊查询
		if(StringUtils.isNotEmpty(bean.getPoNumber())){
			sql += " and w.po_number in ("+ ArrayTransitionUtils.StringToInString(bean.getPoNumber(), ",") +")";
		}
		//服务保修类型:多选
		if(StringUtils.isNotEmpty(bean.getWarrantyType())){
			sql += " and t.WARRANTY_TYPE in ("+ ArrayTransitionUtils.StringToInString(bean.getWarrantyType(), ",") +")";
		}
		//服务站
		if(StringUtils.isNotEmpty(bean.getDepartmentIds())){
			sql += " and w.DEPARTMENT_ID in ("+ ArrayTransitionUtils.StringToInString(bean.getDepartmentIds(), ",") +")";
		}
		//服务类型
		if(StringUtils.isNotEmpty(bean.getTaskType())){
			sql += " and t.TASK_TYPE in ("+ ArrayTransitionUtils.StringToInString(bean.getTaskType(), ",") +")";
		}
		//工单状态
		if(StringUtils.isNotEmpty(bean.getStatus())){
			sql += " and w.STATUS in ("+ ArrayTransitionUtils.StringToInString(bean.getStatus(), ",") +")";
		}
		//工程师：多选
		if(StringUtils.isNotEmpty(bean.getEngineerName())){
			sql += " and w.ENGINEER_NAME in ("+ ArrayTransitionUtils.StringToInString(bean.getEngineerName(), ",") +")";
		}
		//工作完成日期
		if(StringUtils.isNotEmpty(bean.getWorkFinishDateStart())){
			sql += " and w.WORK_FINISH_DATE >= to_date('"+ bean.getWorkFinishDateStart() +"', 'yyyy-MM-dd')";
		}
		//工作完成日期
		if(StringUtils.isNotEmpty(bean.getWorkFinishDateEnd())){
			sql += " and w.WORK_FINISH_DATE <= to_date('"+ bean.getWorkFinishDateEnd() +"', 'yyyy-MM-dd')";
		}
		//工单创建日期
		if(StringUtils.isNotEmpty(bean.getCreateDateStart())){
			sql += " and w.CREATE_DATE >= to_date('"+ bean.getCreateDateStart() +"', 'yyyy-MM-dd')";
		}
		//工单创建日期
		if(StringUtils.isNotEmpty(bean.getCreateDateEnd())){
			sql += " and w.CREATE_DATE <= to_date('"+ bean.getCreateDateEnd() +"', 'yyyy-MM-dd')";
		}
		//是否回访
		if(StringUtils.isNotBlank(bean.getHasRevisit())){
			if(CommonConstants.FLAG_Y.equals(bean.getHasRevisit())){
				sql += " and w.HAS_REVISIT = '"+ CommonConstants.FLAG_Y +"'";
			}else{
				sql += " and (w.HAS_REVISIT = '"+ CommonConstants.FLAG_N +"' or w.HAS_REVISIT is null)";
			}
		}
		
//#########################################设备相关查询条件组装###########开始################################
		boolean hasEquipmentCondition = false;//是否已经加入设备信息查询条件
		String equipmentSql = "select eq.EQUIPMENT_ID from  V_EMS_EQUIPMENT_INFO eq where 1=1 ";
		
		if(StringUtils.isNotEmpty(bean.getSerialNumber())){
			equipmentSql += " and eq.SERIAL_NUMBER in("+ ArrayTransitionUtils.StringToInString(bean.getSerialNumber(), ",") +")";
			hasEquipmentCondition = true;
		}
		//设备制造商
		if(StringUtils.isNotEmpty(bean.getManufacturer())){
			equipmentSql += " and eq.MANUFACTURER in("+ ArrayTransitionUtils.StringToInString(bean.getManufacturer(), ",") +")";
			hasEquipmentCondition = true;
		}
		//设备型号
		if(StringUtils.isNotEmpty(bean.getEquipmentModel())){
			equipmentSql += " and eq.EQUIPMENT_MODEL in("+ ArrayTransitionUtils.StringToInString(bean.getEquipmentModel(), ",") +")";
			hasEquipmentCondition = true;
		}
		//销售合同号
		if(StringUtils.isNotEmpty(bean.getSaleContractNo())){
			equipmentSql += " and eq.SALE_CONTRACT_NUMBER like '%"+bean.getSaleContractNo()+"%' ";
			hasEquipmentCondition = true;
		}
		//销售属性
		if(StringUtils.isNotEmpty(bean.getSaleProperty())){
			equipmentSql += " and eq.SALE_PROPERTY in("+ ArrayTransitionUtils.StringToInString(bean.getSaleProperty(), ",") +")";
			hasEquipmentCondition = true;
		}
		//维保合同号
		if(StringUtils.isNotEmpty(bean.getMaintainContractNo())){
			equipmentSql += " and eq.MAINTAIN_CONTRACT_NO like '%"+bean.getMaintainContractNo()+"%' ";
			hasEquipmentCondition = true;
		}
		//设备类型
		if(StringUtils.isNotEmpty(bean.getEquipmentType())){
			equipmentSql += " and eq.EQUIPMENT_TYPE in("+ ArrayTransitionUtils.StringToInString(bean.getEquipmentType(), ",") +")";
			hasEquipmentCondition = true;
		}
		//设备状态
		if(StringUtils.isNotEmpty(bean.getEquipmentStatus())){
			equipmentSql += " and eq.EQUIPMENT_STATUS in("+ ArrayTransitionUtils.StringToInString(bean.getEquipmentStatus(), ",") +")";
			hasEquipmentCondition = true;
		}
		//安装方式
		if(StringUtils.isNotEmpty(bean.getInstallModel())){
			equipmentSql += " and eq.INSTALL_MODEL in("+ ArrayTransitionUtils.StringToInString(bean.getInstallModel(), ",") +")";
			hasEquipmentCondition = true;
		}
		//设备所在省
		if(null != bean.getProvinceId()){
			equipmentSql += " and eq.PROVINCE_ID = '"+bean.getProvinceId()+"' ";
			hasEquipmentCondition = true;
		}		
		//设备所在市
		if(null != bean.getCityId()){
			equipmentSql += " and eq.CITY_ID = '"+bean.getCityId()+"' ";
			hasEquipmentCondition = true;
		}	
		//安装日期
		if(StringUtils.isNotEmpty(bean.getInstallDateStart())){
			equipmentSql += " and eq.INSTALL_DATE >= to_date('"+ bean.getInstallDateStart() +"', 'yyyy-MM-dd')";
			hasEquipmentCondition = true;
		}
		//安装日期
		if(StringUtils.isNotEmpty(bean.getInstallDateEnd())){
			equipmentSql += " and eq.INSTALL_DATE <= to_date('"+ bean.getInstallDateEnd() +"', 'yyyy-MM-dd')";
			hasEquipmentCondition = true;
		}
		//网点名称 
		if(StringUtils.isNotEmpty(bean.getBranchName())){
			equipmentSql += " and eq.BRANCH_NAME like '%"+bean.getBranchName()+"%' ";
			hasEquipmentCondition = true;
		}
		//到保日期
		if(StringUtils.isNotEmpty(bean.getWarrantyEndDateStart())){
			equipmentSql += " and eq.WARRANTY_END_DATE >= to_date('"+ bean.getWarrantyEndDateStart() +"', 'yyyy-MM-dd')";
			hasEquipmentCondition = true;
		}
		//到保日期
		if(StringUtils.isNotEmpty(bean.getWarrantyEndDateEnd())){
			equipmentSql += " and eq.WARRANTY_END_DATE <= to_date('"+ bean.getWarrantyEndDateEnd() +"', 'yyyy-MM-dd')";
			hasEquipmentCondition = true;
		}
		//客户多选
		if(StringUtils.isNotEmpty(bean.getCustomerIds())){
			equipmentSql += " and eq.CUSTOMER_ID in("+ ArrayTransitionUtils.StringToInString(bean.getCustomerIds(), ",") +")";
			hasEquipmentCondition = true;
		}
//###############################设备相关查询条件组装########################结束	####################################
		
		if(hasEquipmentCondition){
			sql += " and t.equipment_id in (" + equipmentSql + ")";
		}
		if(StringUtils.isNotEmpty(page.getSort()) && StringUtils.isNotEmpty(page.getOrder())){
			if("poNumber".equals(page.getSort())){
				sql+=" order by w.po_number "+page.getOrder();
			}
		}
		return this.findEntityListBySQL(sql, WorkFormRevisitPage.class, page);
	}

	public List<Integer> queryEquipmentListByUpgradeCode(String upgradeCode) {
		String sql = "select t.equipment_id,count(t.equipment_id) from V_services_task t where t.upgrade_code = ? group by t.equipment_id";
		List<Object[]> findBySQL = findBySQL(sql, upgradeCode);
		List<Integer> equipmentIdList = new ArrayList<Integer>();
		for (Object[] objects : findBySQL) {
			equipmentIdList.add(Integer.parseInt(""+objects[0]));
		}
		return equipmentIdList;
	}
	
	public List<WorkFormSearchPape> queryWorkFormListBYSQL(Page queryPage,WorkFormBean workFormBean) {
		/*String exSql="";
		if(exportSql){
			//导出加条件
			exSql=",p.province_name,cit.city_name,e.equipment_status,e.equipment_type,e.equipment_model,e.atm_number " +
					" ,t.description,t.warranty_type,t.process_no,t.task_source,to_char(w.report_time,'yyyy-MM-dd hh24:mi') report_time, " +
					" to_char(w.appointment_date,'yyyy-MM-dd hh24:mi') appointment_date,to_char(w.arrive_time,'yyyy-MM-dd hh24:mi') arrive_time " +
					" ,to_char(w.work_start_date,'yyyy-MM-dd hh24:mi') work_start_date,t.work_status,w.follow_username,e.install_date,w.last_audit_date,e.install_model,u.name " +
					" ,e.equipment_area,e.atm_manager,e.atm_manager_tel,e.install_address,t.task_level ";
		}*/
		
		/*String sqlMain ="select t.task_id,t.workform_id,w.po_number,pf.paper_form_code,e.serial_number,t.task_type,c.customer_name, " +
				 " e.branch_name,d2.department_name region_name,d1.department_name area_name,d.department_name,w.cost_time, " +
				 " w.work_finish_date,w.create_date,w.engineer_name,w.status,w.satisfied_type " +
				 " from t_services_task t inner join t_services_workform w on t.workform_id=w.workform_id " +
				 " inner join V_EMS_EQUIPMENT_INFO e on t.equipment_id=e.equipment_id left join t_paper_form_detail pf on t.workform_id=pf.form_id " +
				 " left join t_customer_info c on e.customer_id=c.customer_id left join t_base_department d on e.department_id=d.department_id " +
				 " left join t_base_department d1 on d.parent_id=d1.department_id left join t_base_department d2 on d1.parent_id=d2.department_id ";
		*/
		/*//if(exportSql){
			//导出加条件
			sqlMain+=" left join t_base_province p on e.province_id=p.province_id left join t_base_city cit on e.city_id=cit.city_id" +
					" left join t_base_user u on e.user_id=u.user_id ";
		//}
*/		
		String sql="select t.task_id,t.workform_id,w.po_number,t.task_type,w.cost_time,w.work_finish_date, " +
				" w.create_date,w.engineer_name,w.status,w.satisfied_type,w.department_id,t.equipment_id " +
				" from V_services_task t inner join V_services_workform w on (t.workform_id = w.workform_id and w.deleted='N')" ;
		
		if("1".equals(workFormBean.getModifyDateType())){
			sql+=" where t.modify_date>= to_date('"+ DateUtil.addMonth(new Date(),-3,"yyyy-MM-dd")+"','yyyy-MM-dd') ";
		}else if("2".equals(workFormBean.getModifyDateType())){
			sql+=" where t.modify_date>= to_date('"+ DateUtil.addMonth(new Date(),-6,"yyyy-MM-dd")+"','yyyy-MM-dd') ";
		}else if("3".equals(workFormBean.getModifyDateType())){
			sql+=" where t.modify_date>= to_date('"+ DateUtil.addMonth(new Date(),-12,"yyyy-MM-dd")+"','yyyy-MM-dd') ";
		}else if("4".equals(workFormBean.getModifyDateType())){
			sql+=" where t.modify_date>= to_date('"+ DateUtil.addMonth(new Date(),-24,"yyyy-MM-dd")+"','yyyy-MM-dd') ";
		}else{
			sql+=" where 1=1 ";
		}
		
		//==工单
		//工单号的查询：多个工单号与单个工单号的查询方法，单个工单号支持不分大小写模糊查询
		if(StringUtils.isNotEmpty(workFormBean.getPoNumber())){
			sql += " and w.po_number in ("+ ArrayTransitionUtils.StringToInString(workFormBean.getPoNumber(), ",") +")";
		}
		//服务站
		if(StringUtils.isNotEmpty(workFormBean.getDepartmentIds())){
			sql += " and w.DEPARTMENT_ID in ("+ ArrayTransitionUtils.StringToInString(workFormBean.getDepartmentIds(), ",") +")";
		}
		//工单状态
		if(StringUtils.isNotEmpty(workFormBean.getStatus())){
			sql += " and w.STATUS in ("+ ArrayTransitionUtils.StringToInString(workFormBean.getStatus(), ",") +")";
		}
		//工单创建日期
		if(StringUtils.isNotEmpty(workFormBean.getCreateDateStart())){
			String startStr=workFormBean.getCreateDateStart()+" 00:00:00";
			sql += " and w.CREATE_DATE >= to_date('"+startStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		//工单创建日期
		if(StringUtils.isNotEmpty(workFormBean.getCreateDateEnd())){
			String endStr=workFormBean.getCreateDateEnd()+" 23:59:59";
			sql += " and w.CREATE_DATE <= to_date('"+endStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		//工作完成日期
		if(StringUtils.isNotEmpty(workFormBean.getWorkFinishDateStart())){
			String startStr=workFormBean.getWorkFinishDateStart()+" 00:00:00";
			sql += " and w.WORK_FINISH_DATE >= to_date('"+startStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		//工作完成日期
		if(StringUtils.isNotEmpty(workFormBean.getWorkFinishDateEnd())){
			String endStr=workFormBean.getWorkFinishDateEnd()+" 23:59:59";
			sql += " and w.WORK_FINISH_DATE <= to_date('"+endStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		//工程师：多选
		if(StringUtils.isNotEmpty(workFormBean.getEngineerName())){
			sql += " and w.ENGINEER_NAME in ("+ ArrayTransitionUtils.StringToInString(workFormBean.getEngineerName(), ",") +")";
		}
		//工单满意度
		if(StringUtils.isNotEmpty(workFormBean.getSatisfiedType())){
			sql += " and w.satisfied_type ='"+workFormBean.getSatisfiedType()+"' ";
		}
		//最后审核时间
		if(StringUtils.isNotEmpty(workFormBean.getLastAuditDateStart())){
			String startStr=workFormBean.getLastAuditDateStart()+" 00:00:00";
			sql += " and w.LAST_AUDIT_DATE >= to_date('"+startStr +"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		//最后审核时间
		if(StringUtils.isNotEmpty(workFormBean.getLastAuditDateEnd())){
			String endStr=workFormBean.getLastAuditDateEnd()+" 23:59:59";
			sql += " and w.LAST_AUDIT_DATE <= to_date('"+endStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		
		
		//end工单
		
		//默认查询服务站
		if(workFormBean.getDepartmentId()!=null){
			//sql += " and w.DEPARTMENT_ID="+workFormBean.getDepartmentId()+" ";
			List<Integer> depList=this.getChildDepartmentId(workFormBean.getDepartmentId());
			sql += " and w.DEPARTMENT_ID  in("+ArrayTransitionUtils.integerListToInString(depList)+") ";
		}
		if(workFormBean.getRegionNameId()!=null){
			List<Integer> depList=this.getChildDepartmentId(workFormBean.getRegionNameId());
			sql += " and w.DEPARTMENT_ID  in("+ArrayTransitionUtils.integerListToInString(depList)+") ";
		}
		//服务站模糊查询
		if(StringUtils.isNotEmpty(workFormBean.getDepartmentId0())){
			sql += " and w.department_id in (select department_id from V_base_department where department_name like '%"+workFormBean.getDepartmentId0()+"%')";
		}
		
		//服务保修类型:多选
		if(StringUtils.isNotEmpty(workFormBean.getWarrantyType())){
			sql += " and t.WARRANTY_TYPE in ("+ ArrayTransitionUtils.StringToInString(workFormBean.getWarrantyType(), ",") +")";
		}
		//服务类型
		if(StringUtils.isNotEmpty(workFormBean.getTaskType())){
			sql += " and t.TASK_TYPE in ("+ ArrayTransitionUtils.StringToInString(workFormBean.getTaskType(), ",") +")";
		}
		
		
		//start=====设备查询条件 
		boolean equipmentCondition = false;
		String equipmentSql = "select e.equipment_id from  V_EMS_EQUIPMENT_INFO e where 1=1 ";
		//设备序列号
		if(StringUtils.isNotEmpty(workFormBean.getSerialNumber())){
			if(workFormBean.getSerialNumber().length()<600){
				equipmentSql+= " and e.SERIAL_NUMBER in("+ ArrayTransitionUtils.StringToInString(workFormBean.getSerialNumber(), ",") +")";
			}else{
				equipmentSql+=generateSerialNumberSql(workFormBean.getSerialNumber());
			}
			equipmentCondition=true;
		}
		//设备制造商
		if(StringUtils.isNotEmpty(workFormBean.getManufacturer())){
			equipmentSql+= " and e.MANUFACTURER in("+ ArrayTransitionUtils.StringToInString(workFormBean.getManufacturer(), ",") +")";
			equipmentCondition=true;
		}
		//设备类型
		if(StringUtils.isNotEmpty(workFormBean.getEquipmentType())){
			equipmentSql+= " and e.EQUIPMENT_TYPE in("+ ArrayTransitionUtils.StringToInString(workFormBean.getEquipmentType(), ",") +")";
			equipmentCondition=true;
		}
		//设备型号
		if(StringUtils.isNotEmpty(workFormBean.getEquipmentModel())){
			equipmentSql+= " and e.EQUIPMENT_MODEL in("+ ArrayTransitionUtils.StringToInString(workFormBean.getEquipmentModel(), ",") +")";
			equipmentCondition=true;
		}
		//销售合同号
		if(StringUtils.isNotEmpty(workFormBean.getSaleContractNo())){
			equipmentSql += " and e.SALE_CONTRACT_NUMBER like '%"+workFormBean.getSaleContractNo()+"%' ";
			equipmentCondition=true;
		}
		//销售属性
		if(StringUtils.isNotEmpty(workFormBean.getSaleProperty())){
			equipmentSql += " and e.SALE_PROPERTY in("+ ArrayTransitionUtils.StringToInString(workFormBean.getSaleProperty(), ",") +")";
			equipmentCondition=true;
		}
		//维保合同号
		if(StringUtils.isNotEmpty(workFormBean.getMaintainContractNo())){
			equipmentSql += " and e.MAINTAIN_CONTRACT_NO like '%"+workFormBean.getMaintainContractNo()+"%' ";
			equipmentCondition=true;
		}
		//设备所在省
		if(null != workFormBean.getProvinceId()){
			equipmentSql += " and e.PROVINCE_ID = '"+workFormBean.getProvinceId()+"' ";
			equipmentCondition=true;
		}		
		//设备所在市
		if(null != workFormBean.getCityId()){
			equipmentSql += " and e.CITY_ID = '"+workFormBean.getCityId()+"' ";
			equipmentCondition=true;
		}
		//客户多选
		if(StringUtils.isNotEmpty(workFormBean.getCustomerIds())){
			equipmentSql += " and e.CUSTOMER_ID in("+ ArrayTransitionUtils.StringToInString(workFormBean.getCustomerIds(), ",") +")";
			equipmentCondition=true;
		}
		//设备状态
		if(StringUtils.isNotEmpty(workFormBean.getEquipmentStatus())){
			equipmentSql += " and e.EQUIPMENT_STATUS in("+ ArrayTransitionUtils.StringToInString(workFormBean.getEquipmentStatus(), ",") +")";
			equipmentCondition=true;
		}
		//安装方式
		if(StringUtils.isNotEmpty(workFormBean.getInstallModel())){
			equipmentSql += " and e.INSTALL_MODEL in("+ ArrayTransitionUtils.StringToInString(workFormBean.getInstallModel(), ",") +")";
			equipmentCondition=true;
		}
		//网点名称 
		if(StringUtils.isNotEmpty(workFormBean.getBranchName())){
			equipmentSql += " and e.BRANCH_NAME like '%"+workFormBean.getBranchName()+"%' ";
			equipmentCondition=true;
		}
		//设备负责人 
		if(StringUtils.isNotEmpty(workFormBean.getEquipmentChargeName())){
			String userSql=" select u.user_id from V_base_user u where u.name like '%"+workFormBean.getEquipmentChargeName()+"%' ";
			equipmentSql += " and e.user_id in("+userSql+") ";
			equipmentCondition=true;
		}
		//安装日期
		if(StringUtils.isNotEmpty(workFormBean.getInstallDateStart())){
			String startStr=workFormBean.getInstallDateStart()+" 00:00:00";
			equipmentSql += " and e.INSTALL_DATE >= to_date('"+startStr+"', 'yyyy-MM-dd HH24:mi:ss')";
			equipmentCondition=true;
		}
		//安装日期
		if(StringUtils.isNotEmpty(workFormBean.getInstallDateEnd())){
			String endStr=workFormBean.getInstallDateEnd()+" 23:59:59";
			equipmentSql += " and e.INSTALL_DATE <= to_date('"+endStr+"', 'yyyy-MM-dd HH24:mi:ss')";
			equipmentCondition=true;
		}
		
		if(equipmentCondition){
			sql += " and t.equipment_id in (" + equipmentSql + ")";
		}
		
		//end=======设备
		
		
		
		//==纸质工单编号
		//纸质工单编号
		if(StringUtils.isNotEmpty(workFormBean.getPaperFormCode())){
			String paperFormSql= " select pf.form_id from V_paper_form_detail pf where pf.paper_form_code like '%"+workFormBean.getPaperFormCode()+"%' ";
			sql +=" and  to_char(t.workform_id) in("+paperFormSql+") "; 
			
		}
		//==end 纸质工单编号
		
		
		//故障查询
		boolean repairCondition = false;
		String repairSql = "select trp.task_id from  V_services_task_repair_place trp where 1=1 ";
		//故障部位代码
		if(StringUtils.isNotEmpty(workFormBean.getProblemPartIds())){
			repairSql += " and trp.PROBLEM_PART_CODE in("+ ArrayTransitionUtils.StringToInString(workFormBean.getProblemPartIds(), ",") +")";
			repairCondition=true;
		}
		//故障原因代码
		if(StringUtils.isNotEmpty(workFormBean.getProblemReasonIds())){
			repairSql += " and trp.TROUBLE_REASON_CODE in("+ ArrayTransitionUtils.StringToInString(workFormBean.getProblemReasonIds(), ",") +")";
			repairCondition=true;
		}
		//处理方法代码
		if(StringUtils.isNotEmpty(workFormBean.getProblemMethodIds())){
			repairSql += " and trp.PROCESS_CODE in("+ ArrayTransitionUtils.StringToInString(workFormBean.getProblemMethodIds(), ",") +")";
			repairCondition=true;
		}
		
		if(repairCondition){
			//故障查询
			sql += " and t.task_id in (" + repairSql + ")";
		}
		
		sql += " and t.deleted = 'N'  ";
		if(queryPage.getSort()!=null){
			if("workForm.workFinishDate".equals(queryPage.getSort())){
				sql+=" order by w.work_finish_date "+queryPage.getOrder();
			}else if("workForm.createDate".equals(queryPage.getSort())){
				sql+=" order by w.create_date "+queryPage.getOrder();
			}else{
				sql+=" order by t.task_id "+queryPage.getOrder();
			}
		}
		
		return this.findEntityListBySQL(sql,WorkFormSearchPape.class, queryPage);
	}
	
	private String generateSerialNumberSql(String serialNumbers) {
		String serialNums[] = serialNumbers.replaceAll(" ","").split(",");
		StringBuffer serialNumberSql = new StringBuffer();
		double loopNum = Math.ceil(serialNums.length/100f);
		List<String[]> bufferStrlist = new ArrayList<String[]>();
		for(int i=0;i<loopNum;i++){
			bufferStrlist.add(getBufferStr(serialNums,i,100));
		}
		serialNumberSql.append("and (") ;
		for (String[] buffer : bufferStrlist) {
			serialNumberSql.append(" serial_number in ("+ArrayTransitionUtils.stringArrayToInString(buffer)+") ");
			serialNumberSql.append(" or ");
		}
		serialNumberSql.replace(serialNumberSql.lastIndexOf("or"), serialNumberSql.lastIndexOf("or") + 2, "");
		serialNumberSql.append(" )");
		return serialNumberSql.toString();
	}
	private String[] getBufferStr(String[] strArray, int turns, int bufferLength) {
		int length = queryBufferLength(strArray,turns,bufferLength);
		String[] buffer = new String[length];
		for (int i = 0; i < length; i++) {
			buffer[i] = strArray[turns*bufferLength+i].trim();
		}
		return buffer;
	}
	private int queryBufferLength(String[] strArray, int turns, int bufferLength) {
		int remainder = strArray.length - bufferLength*turns ;
		return remainder > bufferLength ? bufferLength :  remainder;
	}

	public Integer findSparepartUseNumber(String taskIds, String materialCode,Integer userId,String serialNumber) {
		String sql = "select nvl(sum(t.quantity),0) count" +
						"  from V_SERVICES_TASK_REPLACE t, V_services_task task, V_SPAREPART s" + 
						" where t.task_id = task.task_id" + 
						"   and t.new_sparepart_id = s.sparepart_id" + 
						"   and s.material_code = ?" + 
						"   and task.status in (?,?,?)" + 
						"   and task.engineer_id = ?" +
						"   and task.task_id not in ("+taskIds+")";
		if(StringUtilsExtends.isNotBlankAndEmpty(serialNumber))
			sql += " and t.new_serial_number = '"+serialNumber+"'";
		return countBySQLAddScalar(sql,materialCode,ServicesConstants.STATUS_TASK_PROCESS_TREATMENT,ServicesConstants.STATUS_TASK_PROCESS_AUDIT,ServicesConstants.STATUS_TASK_PROCESS_PENDING,userId);
	}

	public List<Integer> findWorkformTaskIdList(Integer workformId) {
		String sql = "select t.task_id,t.deleted from V_services_task t where t.workform_id = ? and t.deleted = ?";
		List<Object[]> queryList = findBySQL(sql, workformId,CommonConstants.FLAG_N);
		List<Integer> result = new ArrayList<Integer>();
		for (Object[] objects : queryList) {
			result.add(Integer.valueOf(objects[0].toString()));
		}
		return result;
	}

	public int queryEquipmentCount(Integer departmentId, String dateStr,
			String taskType) {
		String sql="select count(distinct t.equipment_id) from V_services_task t " +
				" left join V_services_workform w on t.workform_id=w.workform_id " +
				" where to_char(w.work_finish_date,'yyyy-MM')='"+dateStr+"' ";
		if(taskType!=null){
			sql+=" and t.task_type='"+taskType+"' ";
		}
		if(departmentId!=null){
			sql+=" and w.department_id="+departmentId+" ";
		}
		return this.countBySQL(sql);
	}

	public List<WorkTaskSearchPape> getAllWorkTaskJsonListBySQL(Page queryPage,
			WorkTaskBean workTaskBean) {
		
		String taskSql="select t.task_id,t.workform_id,t.process_no,t.task_type,t.equipment_id,t.engineer_name, " +
		" t.plan_start_date,t.plan_end_date,t.status,t.upgrade_code,t.create_date,t.appointment_time,t.report_time,t.task_source" +
		" from V_services_task t where t.deleted='N' and t.status<>'已暂停' ";
		
		if(StringUtils.isNotEmpty(workTaskBean.getProcessNo())){
			taskSql+=" and t.process_no in ("+ ArrayTransitionUtils.StringToInString(workTaskBean.getProcessNo(), ",") +")";
		}
		
		//升级项目代码
		if(StringUtils.isNotBlank(workTaskBean.getUpgradeName())){
			String upgradeCodeSql="select t.upgrade_code from V_services_upgrade t where upper(t.subject) like '%"+workTaskBean.getUpgradeName().trim().toString().toUpperCase()+"%'";
			taskSql+=" and t.upgrade_code in("+upgradeCodeSql+") ";
		}
		if(StringUtils.isNotBlank(workTaskBean.getUpgradeCode())){
			taskSql+=" and t.upgrade_code= '"+workTaskBean.getUpgradeCode()+"' ";
		}
		if(workTaskBean.getEquipmentId()!=null){
			taskSql+=" and t.equipment_id="+workTaskBean.getEquipmentId();
		}
		if(workTaskBean.getReportTime()!=null){
			String startStr=DateUtil.formatDate(workTaskBean.getReportTime())+" 00:00:00";
			String endStr=DateUtil.formatDate(workTaskBean.getReportTime())+" 23:59:59";
			taskSql+=" and t.report_time>=to_date('"+startStr+"','yyyy-MM-dd HH24:mi:ss') and t.report_time<=to_date('"+endStr+"','yyyy-MM-dd HH24:mi:ss') ";
		}
		
		if(StringUtils.isNotBlank(workTaskBean.getAppointmentTimeStart())){
			String startStr=workTaskBean.getAppointmentTimeStart()+" 00:00:00";
			taskSql+=" and t.appointment_time >= to_date('"+startStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		if(StringUtils.isNotBlank(workTaskBean.getAppointmentTimeEnd())){
			String endStr=workTaskBean.getAppointmentTimeEnd()+" 23:59:59";
			taskSql+=" and t.appointment_time <=to_date('"+endStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		if(StringUtils.isNotBlank(workTaskBean.getTaskType())){
			taskSql+=" and t.task_type in ("+ ArrayTransitionUtils.StringToInString(workTaskBean.getTaskType(),",") +")";
		}
		if(StringUtils.isNotBlank(workTaskBean.getTaskSource())){
			taskSql+=" and t.task_source in ("+ ArrayTransitionUtils.StringToInString(workTaskBean.getTaskSource(),",") +")";
		}
		if(workTaskBean.getTaskSourceArray()!=null){
			taskSql+=" and t.task_source in ("+ArrayTransitionUtils.stringArrayToInString(workTaskBean.getTaskSourceArray())+")";
		}
		
		if(workTaskBean.getStatusArray()!=null){
			taskSql+=" and t.status in ("+ArrayTransitionUtils.stringArrayToInString(workTaskBean.getStatusArray())+")";
		}
		if(StringUtils.isNotBlank(workTaskBean.getStatus())){
			taskSql+=" and t.status in ("+ ArrayTransitionUtils.StringToInString(workTaskBean.getStatus(),",") +")";
		}
		
		if(workTaskBean.getEngineerId()!=null){
			taskSql+=" and t.engineer_id ="+workTaskBean.getEngineerId();
		}
		
		if(workTaskBean.getCreateType()!=null){
			taskSql+=" and t.create_type ="+workTaskBean.getCreateType();
		}
		
		
		
		if(StringUtils.isNotBlank(workTaskBean.getPlanStartDateStart())){
			String startStr=workTaskBean.getPlanStartDateStart()+" 00:00:00";
			taskSql+=" and t.plan_start_date >= to_date('"+startStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		if(StringUtils.isNotBlank(workTaskBean.getPlanStartDateEnd())){
			String endStr=workTaskBean.getPlanStartDateEnd()+" 23:59:59";
			taskSql+=" and t.plan_start_date <=to_date('"+endStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		
		if(StringUtils.isNotBlank(workTaskBean.getPlanEndDateStart())){
			String startStr=workTaskBean.getPlanEndDateStart()+" 00:00:00";
			taskSql+=" and t.plan_end_date >= to_date('"+startStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		if(StringUtils.isNotBlank(workTaskBean.getPlanEndDateEnd())){
			String endStr=workTaskBean.getPlanEndDateEnd()+" 23:59:59";
			taskSql+=" and t.plan_end_date <=to_date('"+endStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		
		boolean workFormCondition = false;
		String workFormSql = "select w.workform_id from V_services_workform w where 1=1 ";
		
		if(StringUtils.isNotBlank(workTaskBean.getWorkFinishDateStart())){
			String startStr=workTaskBean.getWorkFinishDateStart()+" 00:00:00";
			workFormSql+=" and w.work_finish_date >= to_date('"+startStr+"', 'yyyy-MM-dd HH24:mi:ss')";
			workFormCondition=true;
		}
		if(StringUtils.isNotBlank(workTaskBean.getWorkFinishDateEnd())){
			String endStr=workTaskBean.getWorkFinishDateEnd()+" 23:59:59";
			workFormSql+=" and w.work_finish_date <=to_date('"+endStr+"', 'yyyy-MM-dd HH24:mi:ss')";
			workFormCondition=true;
		}
		
		if(workFormCondition){
			taskSql+=" and t.workform_id in("+workFormSql+") ";
		}
		
		String sql="select ta.task_id,ta.workform_id,ta.process_no,ta.task_type,ta.equipment_id,e.serial_number,e.equipment_status,e.equipment_model," +
		" e.customer_id,e.branch_name,ta.engineer_name,ta.plan_start_date,ta.plan_end_date,ta.status,ta.upgrade_code," +
		" e.department_id,ta.create_date,ta.appointment_time,ta.report_time,ta.task_source " +
		" from ("+taskSql+") ta left join V_EMS_EQUIPMENT_INFO e on  ta.equipment_id=e.equipment_id where 1=1 ";
		
		
		if(StringUtils.isNotEmpty(workTaskBean.getDepartmentIds())){
			sql+=" and e.department_id in ("+ ArrayTransitionUtils.StringToInString(workTaskBean.getDepartmentIds(), ",") +")";
		}
		
		if(workTaskBean.getDepartmentId()!=null){
			List<Integer> depList=this.getChildDepartmentId(workTaskBean.getDepartmentId());
			sql+=" and e.department_id in("+ArrayTransitionUtils.integerListToInString(depList)+") ";
		}
		
		if(workTaskBean.getEquipmentStatusArray()!=null){
			sql+=" and e.equipment_status in ("+ArrayTransitionUtils.stringArrayToInString(workTaskBean.getEquipmentStatusArray())+")";
		}
		
		//客户多选
		if(StringUtils.isNotBlank(workTaskBean.getCustomerIds())){
			sql+=" and e.customer_id in("+ ArrayTransitionUtils.StringToInString(workTaskBean.getCustomerIds(),",") +")";
		}
		
		
		if(StringUtils.isNotBlank(workTaskBean.getBranchName())){
			sql+=" and e.branch_name like '%"+workTaskBean.getBranchName()+"%' ";
		}
		if(StringUtils.isNotBlank(workTaskBean.getEquipmentChargeName())){
			String usSql="select u.user_id from V_base_user u where u.name='"+workTaskBean.getEquipmentChargeName()+"' ";
			sql+=" and e.user_id in("+usSql+") ";
		}
		
		//设备序列号多选
		if(StringUtils.isNotBlank(workTaskBean.getSerialNumber())){
			if(workTaskBean.getSerialNumber().length()<600){
				sql+= " and e.serial_number in("+ ArrayTransitionUtils.StringToInString(workTaskBean.getSerialNumber(),",") +")";
			}else{
				sql+=generateSerialNumberSql(workTaskBean.getSerialNumber());
			}
		}
		
		if(queryPage.getSort()!=null){
			if("equipment_serialNumber".equals(queryPage.getSort())){
				sql+=" order by e.serial_number "+queryPage.getOrder();
			}else if("equipment.department.departmentName".equals(queryPage.getSort())){
				sql+=" order by e.department_id "+queryPage.getOrder();
			}else if("equipment.equipmentStatus".equals(queryPage.getSort())){
				sql+=" order by e.equipment_status "+queryPage.getOrder();
			}else if("equipment.customer.customerName".equals(queryPage.getSort())){
				sql+=" order by e.customer_id "+queryPage.getOrder();
			}else if("equipment.branchName".equals(queryPage.getSort())){
				sql+=" order by e.branch_name "+queryPage.getOrder();
			}else if("engineerName".equals(queryPage.getSort())){
				sql+=" order by ta.engineer_name "+queryPage.getOrder();
			}else if("planStartDate".equals(queryPage.getSort())){
				sql+=" order by ta.plan_start_date "+queryPage.getOrder();
			}else if("planEndDate".equals(queryPage.getSort())){
				sql+=" order by ta.plan_end_date "+queryPage.getOrder();
			}else if("status".equals(queryPage.getSort())){
				sql+=" order by ta.status "+queryPage.getOrder();
			}else{
				sql+=" order by ta.task_id "+queryPage.getOrder();
			}
		}
		
		return this.findEntityListBySQL(sql,WorkTaskSearchPape.class,queryPage);
	}

	public List<WorkTask> getEquipmentWorktaskBYList(Integer equipmentId,
			String status) {
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addEqual("equipment.equipmentId",equipmentId);
		hqlHelper.addEqual("status",status);
		hqlHelper.addEqual("taskType","BY");
		hqlHelper.addIsNull("upgradeCode");
		return this.find(hqlHelper);
	}

	public List<WorkTask> findWorkformTaskList(Integer workformId) {
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addEqual("workForm.workFormId",workformId);
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
		return this.find(hqlHelper);
	}

	@Override
	public List<WorkTask> getWorktaskBYReportTelephone(String reportTelephone) {
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addEqual("reportTelephone",reportTelephone);
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
		return this.find(hqlHelper);
	}

	@Override
	public int getCompletedAZKT(Integer equipmentId) {
		HqlHelper hqlHelper=new HqlHelper(WorkTask.class);
		hqlHelper.addEqual("equipment.equipmentId", equipmentId);
		hqlHelper.addEqual("taskType", ServicesConstants.TASK_TYPE_INSTALL_KT);
		hqlHelper.addEqual("workForm.status", ServicesConstants.STATUS_TASK_PROCESS_COMPLETED);
		hqlHelper.addEqual("workForm.deleted", CommonConstants.FLAG_N);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		return this.countByHQL("select count(*) "+hqlHelper.getHQL(), hqlHelper.getParams().toArray());
	}
}
