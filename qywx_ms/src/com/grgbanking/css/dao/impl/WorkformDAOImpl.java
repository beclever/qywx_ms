package com.grgbanking.css.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.grgbanking.common.utils.DateUtil;
import com.grgbanking.css.bean.work.WorkForm;
import com.grgbanking.css.bean.work.WorkFormBean;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.dao.WorkformDAO;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.HqlHelper;

/**
 * 工单DAO Product:Grgbanking Service Of Customer System. Version:2.0 Copyright
 * 2010 by Grgbanking All Rights Reserved. Author: zhangzhi Date: 2010-8-19
 * 下午03:58:33
 */
@Repository("workformDAO")
public class WorkformDAOImpl extends CssBaseDAOImpl<WorkForm, Integer> implements
		WorkformDAO<WorkForm, Integer> {

	
	public List<WorkForm> queryWorkFormListBySQL(Page queryPage,WorkFormBean bean) {
		String sql = "select  t.* from V_services_workform t,v_ems_equipment_info a where t.equipment_id = a.equipment_id ";
		if (null != bean.getDepartmentId()) {
			sql += " and t.department_id =" + bean.getDepartmentId();
		}
		if (null != bean.getCreateDateStart()) {
			sql += " and t.create_date>= to_date('" + bean.getCreateDateStart() + "','yyyy-mm-dd')";
		}
		if (null != bean.getCreateDateEnd()) {
			sql += " and t.create_date < to_date('" +DateUtil.formatDate(DateUtil.addDay(DateUtil.parseDate(bean.getCreateDateEnd()),1)) + "','yyyy-mm-dd')";
		}
		if (StringUtils.isNotBlank(bean.getStatus())) {
			sql += " and t.status ='" + bean.getStatus() + "'";
		}
		if (StringUtils.isNotBlank(bean.getSerialNumber())) {
			sql += " and a.serial_number ='" + bean.getSerialNumber() + "'";
		}
		if (StringUtils.isNotBlank(bean.getBranchName())) {
			sql += " and a.branch_name like '%" + bean.getBranchName() + "%'";
		}
		if (null != bean.getEngineerId()) {
			sql += " and t.engineer_id =" + bean.getEngineerId();
		}
		if(StringUtils.isNotBlank(bean.getTaskStatus())){
			if("已完成".equals(bean.getTaskStatus())){
				sql += " and not exists (select * from V_services_task k where  k.workform_id = t.workform_id  and k.work_status is null )";
			}else{
				sql += " and exists (select * from V_services_task k where  k.workform_id = t.workform_id  and k.work_status is null )";
			}
		}
		sql+=" order by t.create_date desc ";
		return this.findEntityListBySQL(sql, WorkForm.class, queryPage);
	}
	
	public List<WorkForm> queryWorkFormListByCreatUser(Page queryPage,WorkFormBean bean) {
		String sql = "select  t.* from v_services_workform t" 
				   + " left join v_ems_equipment_info a on t.equipment_id = a.equipment_id "
				   + " where t.create_user_id = "+bean.getCreateUserId();
		if (null != bean.getCreateDateStart()) {
			sql += " and t.create_date>= to_date('" + bean.getCreateDateStart() + "','yyyy-mm-dd')";
		}
		if (null != bean.getCreateDateEnd()) {
			sql += " and t.create_date < to_date('" +DateUtil.formatDate(DateUtil.addDay(DateUtil.parseDate(bean.getCreateDateEnd()),1)) + "','yyyy-mm-dd')";
		}
		if (StringUtils.isNotBlank(bean.getStatus())) {
			sql += " and t.status ='" + bean.getStatus() + "'";
		}
		if (StringUtils.isNotBlank(bean.getSerialNumber())) {
			sql += " and a.serial_number ='" + bean.getSerialNumber() + "'";
		}
		if (StringUtils.isNotBlank(bean.getBranchName())) {
			sql += " and a.branch_name like '%" + bean.getBranchName() + "%'";
		}
		if (null != bean.getEngineerId()) {
			sql += " and t.engineer_id =" + bean.getEngineerId();
		}
		if(StringUtils.isNotBlank(bean.getTaskStatus())){
			if("已完成".equals(bean.getTaskStatus())){
				sql += " and not exists (select * from v_services_task k where  k.workform_id = t.workform_id  and k.work_status is null )";
			}else{
				sql += " and exists (select * from v_services_task k where  k.workform_id = t.workform_id  and k.work_status is null )";
			}
		}
		sql+=" order by t.create_date desc ";
		return this.findEntityListBySQL(sql, WorkForm.class, queryPage);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#queryWorkFormList
	 * (com.grgbanking.framework.core.Page,
	 * com.grgbanking.soc.bean.services.WorkFormBean)
	 */
	public List<WorkForm> queryWorkFormList(Page queryPage,
			WorkFormBean workformBean) {
		HqlHelper hqlHelper = new HqlHelper(WorkForm.class);
		hqlHelper.addEqual("engineerId", workformBean.getEngineerId());
		hqlHelper.addEqual("status", workformBean.getStatus());
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addIn("status", workformBean.getStatusArray());
		hqlHelper.addLike("equipment.branchName", workformBean.getBranchName());
		
		 
		hqlHelper.addEqual("hasRevisit", workformBean.getHasRevisit());
		if (CommonConstants.DEPT_TYPE_HEAD.equals(workformBean.getComments())) {
			hqlHelper.addIsNotNull("equipment.customer");
			hqlHelper.addIsNotNull("department");
		}
		hqlHelper.addEqual("equipment.equipmentId",
				workformBean.getEquipmentId());
		hqlHelper.addEqual("equipment.serialNumber",
				workformBean.getSerialNumber());
		hqlHelper.addLike("poNumber", workformBean.getPoNumber());
		hqlHelper.addDateBetween("workFinishDate",
				workformBean.getWorkFinishDateStart(),
				workformBean.getWorkFinishDateEnd());
		hqlHelper.addDateBetween("createDate",
				workformBean.getCreateDateStart(),
				workformBean.getCreateDateEnd());
		hqlHelper.addEqual("equipment.customer.province.provinceName",
				workformBean.getProvince());
		// hqlHelper.addEqual("equipment.customer.customerName",
		// workformBean.getCustomerName());
		hqlHelper.addEqual("engineerId", workformBean.getEngineerId());
		hqlHelper.addEqual("department.departmentName",
				workformBean.getStaionName());
		hqlHelper.addEqual("department.departmentId",
				workformBean.getDepartmentId());
		hqlHelper.addEqual("hasRevisit", workformBean.getHasRevisit());
		hqlHelper
				.addEqual("equipment.provinceId", workformBean.getProvinceId());
		hqlHelper.addEqual("equipment.cityId", workformBean.getCityId());
		hqlHelper.addIn("engineerName",
				workformBean.getEngineerName() != null ? workformBean
						.getEngineerName().split(",") : null);// 工程师：多选
		if (StringUtils.isNotBlank(workformBean.getDepartmentIds())) {
			String[] departmentIdArray = workformBean.getDepartmentIds().split(
					",");
			List<Integer> departmentIdList = new ArrayList<Integer>();
			for (String string : departmentIdArray) {
				if (StringUtils.isNotBlank(string)) {
					departmentIdList.add(Integer.valueOf(string));
				}
			}
			hqlHelper.addIn("department.departmentId", departmentIdList);
		}

		// 客户多选
		if (StringUtils.isNotBlank(workformBean.getCustomerIds())) {
			String[] customerIdArray = workformBean.getCustomerIds().split(",");
			List<Integer> customerIdList = new ArrayList<Integer>();
			for (String string : customerIdArray) {
				if (StringUtils.isNotBlank(string)) {
					customerIdList.add(Integer.valueOf(string));
				}
			}
			hqlHelper.addIn("equipment.customer.customerId", customerIdList);// 多选客户
		}
		// by luow 2012-10-23 新增通过工单编号查询
		if(StringUtils.isNotEmpty(workformBean.getWorkFormIds())){
			hqlHelper.addIn("workFormId", workformBean.getWorkFormIds().split(","));
		}
		hqlHelper.addEqual("equipment.department.departmentId",workformBean.getEquipmentDepartmentId());
		
		if("CSS".equals(workformBean.getCreateType())){
			HqlHelper hqlHelper1 = new HqlHelper();
			hqlHelper1.addIsNull("createType");//12-31去sa工单
			hqlHelper1.addEqual("createType",workformBean.getCreateType());
			hqlHelper.addOr(hqlHelper1);
		} else if("ALL".equals(workformBean.getCreateType())) { // 目前该值有 QYWX SA MP 和null，出去null数据即可
			hqlHelper.addIsNotNull("createType");
		} else {
			hqlHelper.addEqual("createType", workformBean.getCreateType());
		}
		hqlHelper.addOrderBy("reportTime", "desc");
		hqlHelper.setQueryPage(queryPage);
		return this.find(hqlHelper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#queryWorkFormList
	 * (com.grgbanking.framework.core.Page,
	 * com.grgbanking.soc.bean.services.WorkFormBean)
	 */
//	public List<WorkForm> queryWorkFormListForRevisit(Page queryPage,
//			WorkFormBean workformBean) {
//		HqlHelper hqlHelper = new HqlHelper(WorkForm.class);
//		hqlHelper.addEqual("engineerId", workformBean.getEngineerId());
//		hqlHelper.addEqual("status", workformBean.getStatus());
//		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
//		hqlHelper.addIn("status", workformBean.getStatusArray());
//
//		hqlHelper.addEqual("hasRevisit", workformBean.getHasRevisit());
//		hqlHelper.addIsNotNull("equipment.customer");
//		if (CommonConstants.DEPT_TYPE_HEAD.equals(workformBean.getComments())) {
//			hqlHelper.addIsNotNull("department");
//		}
//		hqlHelper.addEqual("equipment.equipmentId",
//				workformBean.getEquipmentId());
//		hqlHelper.addLike("equipment.serialNumber",
//				workformBean.getSerialNumber());
//		if(StringUtils.isNotEmpty(workformBean.getPoNumber()) && workformBean.getPoNumber().contains(",")){
//			hqlHelper.addIn("poNumber", workformBean.getPoNumber().split(","));
//		}else
//			hqlHelper.addLike("poNumber", workformBean.getPoNumber());
//		hqlHelper.addDateBetween("workFinishDate",
//				workformBean.getWorkFinishDateStart(),
//				workformBean.getWorkFinishDateEnd());
//		hqlHelper.addDateBetween("createDate",
//				workformBean.getCreateDateStart(),
//				workformBean.getCreateDateEnd());
//		hqlHelper.addEqual(
//				"equipment.customer.province.provinceId",
//				StringUtils.isNotBlank(workformBean.getProvince()) ? Integer
//						.valueOf(workformBean.getProvince()) : null);
//		hqlHelper.addEqual("equipment.customer.customerName",
//				workformBean.getCustomerName());
//		// hqlHelper.addEqual("department.departmentName",
//		// workformBean.getStaionName());
//		if (workformBean.getRegionNameId() != null) {
//			hqlHelper.addIn("department.departmentId", this
//					.getChildDepartmentId(Integer.valueOf(workformBean
//							.getRegionNameId())));
//		}
////		if(workformBean.getCustomerIds()!=null){
////			hqlHelper.addIn("",workformBean.getCustomerIds().split(","));
////		}
//		
//		hqlHelper.addEqual("hasRevisit", workformBean.getHasRevisit());
//		if(StringUtils.isNotBlank(workformBean.getSatisfiedTypeSms())){
//			if("NULL".equals(workformBean.getSatisfiedTypeSms())){
//				hqlHelper.addIsNull("satisfiedTypeSms");
//			}else{
//				hqlHelper.addEqual("satisfiedTypeSms", workformBean.getSatisfiedTypeSms());
//			}
//		}
//
//		hqlHelper.addOrderBy("modifyDate", "desc");
//		hqlHelper.setQueryPage(queryPage);
//		return this.find(hqlHelper);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#getSumSelectWorkform
//	 * (java.lang.String, java.lang.Integer)
//	 */
//	public Integer getSumSelectWorkform(String status, String status1,
//			Integer departmentId, String userId) {
//		String hql;
//		if (status1 != null) {
//			hql = "select count(*) from WorkForm where (status='" + status
//					+ "' or status='" + status1 + "') and deleted='"
//					+ CommonConstants.FLAG_N + "' ";
//		} else {
//			hql = "select count(*) from WorkForm where status='" + status
//					+ "' and deleted='" + CommonConstants.FLAG_N + "'  ";
//		}
//		if (departmentId != null) {
//			hql += " and department.departmentId=" + departmentId;
//		}
//		if (userId != null) {
//			hql = hql + " and engineerId=" + userId;
//		}
//		hql += " and (createType is null or createType='CSS') ";//12-31加and createType is null去SA工单
//		return this.countByHQL(hql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#getWorkformByPoNumber
//	 * (java.lang.String)
//	 */
//	public WorkForm getWorkformByPoNumber(String poNumber) {
//		if (StringUtils.isEmpty(poNumber)) {
//			return null;
//		}
//		HqlHelper hqlHelper = new HqlHelper(WorkForm.class);
//		hqlHelper.addEqual("poNumber", poNumber.trim());
//		List<WorkForm> list = this.find(hqlHelper);
//		if (list != null && list.size() > 0) {
//			return list.get(0);
//		}
//		return null;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#getEffectWorkform
//	 * (java.lang.Integer, java.lang.String)
//	 */
//	public int getEffectWorkform(Integer departmentId, String datestr) {
//		String sql = "select count(*) from t_services_workform t where t.department_id in ("
//				+ StringUtils
//						.join(this.getChildDepartmentId(departmentId), ",")
//				+ ") and to_char(t.work_finish_date,'yyyy-MM-dd')=? ";
//		return this.countBySQL(sql, datestr);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#getEmptyWorkform
//	 * (java.lang.Integer, java.lang.String)
//	 */
//	public int getEmptyWorkform(Integer departmentId, String datestr) {
//		String sql = "select count(*) from t_services_workform t where t.department_id in ("
//				+ StringUtils
//						.join(this.getChildDepartmentId(departmentId), ",")
//				+ ") and to_char(t.create_date,'yyyy-MM-dd')=? "
//				+ " and t.work_finish_date is null";
//		return this.countBySQL(sql, datestr);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.grgbanking.soc.dao.services.workform.WorkformDAO#
//	 * getAllWorkformByEquipment(java.lang.Integer)
//	 */
//	public List<WorkForm> getAllWorkformByEquipment(Integer equipmentId) {
//		HqlHelper hqlHelper = new HqlHelper(WorkForm.class);
//		hqlHelper.addEqual("equipment.equipmentId", equipmentId);
//		hqlHelper.addNotEqual("status",
//				ServicesConstants.STATUS_WORKFORM_COMPLETED);
//		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
//		return this.find(hqlHelper);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#getWorkformBySMID
//	 * (java.lang.String, java.lang.Integer)
//	 */
//	public WorkForm getWorkformBySMID(String smid, Integer departmentId) {
//		HqlHelper hqlHelper = new HqlHelper(WorkForm.class);
//		hqlHelper.addEndWith("poNumber", smid);
//		hqlHelper.addNotEqual("status",
//				ServicesConstants.STATUS_WORKFORM_COMPLETED);
//		hqlHelper.addEqual("department.departmentId", departmentId);
//		hqlHelper.addOrderBy("createDate", "desc");
//		List<WorkForm> workformList = this.find(hqlHelper);
//		if (workformList != null && workformList.size() > 0) {
//			return workformList.get(0);
//		}
//		return null;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#queryCCbankreportData
//	 * (java.lang.Integer, int, java.lang.String, java.lang.String)
//	 */
//	public Object[] queryCCbankreportData(Integer equipmentId, int days,
//			String workFinishDateStart, String workFinishDateEnd) {
//		String sql = "select count(*),avg(w.arrive_time-w.report_time)*24,avg(w.cost_time)/60,avg(("
//				+ days
//				+ "-(w.work_finish_date-w.report_time))/"
//				+ days
//				+ ") from t_services_task t inner join t_services_workform w on t.workform_id=w.workform_id"
//				+ "	where w.equipment_id="
//				+ equipmentId
//				+ " and t.task_type='WX' and to_char(w.work_finish_date,'yyyy-MM-dd')>='"
//				+ workFinishDateStart
//				+ "' and to_char(w.work_finish_date,'yyyy-MM-dd')<='"
//				+ workFinishDateEnd + "'";
//		return (Object[]) this.findBySQL(sql).get(0);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#getSerialNumberWorkform
//	 * (java.lang.String)
//	 */
//	public WorkForm getSerialNumberWorkform(String SerialNumber) {
//		HqlHelper hqlHelper = new HqlHelper(WorkForm.class);
//		hqlHelper.addLike("equipment.serialNumber", SerialNumber);
//		hqlHelper.addOrderBy("workFinishDate", "desc");
//		List<WorkForm> workformList = this.find(hqlHelper);
//		if (workformList != null && workformList.size() > 0) {
//			return workformList.get(0);
//		}
//		return null;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.grgbanking.soc.dao.services.workform.WorkformDAO#
//	 * getSelectMovementStatistics(java.lang.Integer, java.lang.Integer)
//	 */
//	public List<Object[]> getSelectMovementStatistics(Integer rowBegin,
//			Integer rowEnd,String type) {
//		String sql = "";
//		
//		if (UserContext.getUser().getDepartmentType().equals("A")) {
//			sql = "select x.* from(select a.*,rownum nums from( "
//				+ "select t.workform_id,t.po_number,t.department_name,t.problem_part_remark,t.process_remark from t_services_wx_9250 t where t.type='"+type+"' "
//				+ " order by t.work_finish_date desc) a) x where x.nums between "
//				+ rowBegin + " and " + rowEnd + " ";
//			
//		} else {
//			sql = "select x.* from(select a.*,rownum nums from( "
//				+ "select t.workform_id,t.po_number,t.department_name,t.problem_part_remark,t.process_remark from t_services_wx_9250 t  "
//				+ " where t.region_id="+UserContext.getUser().getDepartmentId()+" and t.type='"+type+"'  order by t.work_finish_date desc) a) x where x.nums between "
//				+ rowBegin + " and " + rowEnd + " ";
//		}
//		return this.findBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.grgbanking.soc.dao.services.workform.WorkformDAO#
//	 * getTotalMovementStatistics()
//	 */
//	public Integer getTotalMovementStatistics(String type) {
//		String sql = "";
//		if (UserContext.getUser().getDepartmentType().equals("A")) {
//			sql = " select count(*) from t_services_wx_9250 t where t.type='"+type+"' ";
//			
//		}else{
//			sql = " select count(*) from t_services_wx_9250 t where t.type='"+type+"' "
//			+ " and t.region_id="
//			+ UserContext.getUser().getDepartmentId() + " ";
//		}
//		return this.countBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.grgbanking.soc.dao.services.workform.WorkformDAO#
//	 * getSelectMovementEquipment(java.lang.Integer, java.lang.Integer)
//	 */
//	public List<Object[]> getSelectMovementEquipment(Integer rowBegin,
//			Integer rowEnd) {
//		String sql = "select x.* from(select a.*,rownum nums from( "
//				+ " select e.serial_number,d.department_name,c.customer_name,e.equipment_id,count(*) num "
//				+ " from t_services_task t left join t_services_workform w on t.workform_id=w.workform_id "
//				+ " left join v_ems_equipment_info e on e.equipment_id=w.equipment_id left join t_base_department d on e.department_id=d.department_id "
//				+ " left join t_customer_Info c on e.customer_id=c.customer_id  where t.task_type='WX' and t.status='已完成' and e.equipment_model in('H68N','H68NL') "
//				+ " and to_char(W.work_finish_date,'yyyy-MM-dd')>=to_char(add_months(sysdate,-1),'yyyy-MM-dd') "
//				+ " group by e.serial_number,d.department_name,c.customer_name,e.equipment_id  having count(*)>=2 "
//				+ "  order by num desc  ) a ) x where x.nums between "
//				+ rowBegin + " and " + rowEnd + " ";
//		return this.findBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.grgbanking.soc.dao.services.workform.WorkformDAO#
//	 * getTotalMovementEquipment()
//	 */
//	public Integer getTotalMovementEquipment() {
//		String sql = " select count(*) from  (select count(*) "
//				+ "  from t_services_task t left join t_services_workform w on t.workform_id=w.workform_id "
//				+ "  left join v_ems_equipment_info e on e.equipment_id=w.equipment_id left join t_base_department d on e.department_id=d.department_id "
//				+ " left join t_customer_Info c on e.customer_id=c.customer_id where t.task_type='WX' and t.status='已完成' and e.equipment_model in('H68N','H68NL') "
//				+ " and to_char(W.work_finish_date,'yyyy-MM-dd')>=to_char(add_months(sysdate,-1),'yyyy-MM-dd') "
//				+ "  group by e.serial_number having count(*)>=2 ) ";
//		return this.countBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.grgbanking.soc.dao.services.workform.WorkformDAO#
//	 * getInprocessWorkformBySerialNumberForAOC(java.lang.String)
//	 */
//	public WorkForm getInprocessWorkformBySerialNumberForAOC(String serialNumber) {
//		HqlHelper hqlHelper = new HqlHelper(WorkForm.class);
//		hqlHelper.addEqual("equipment.serialNumber", serialNumber);
//		hqlHelper.addEqual("status",
//				ServicesConstants.STATUS_WORKFORM_INPROCESS);
//		hqlHelper.addIsNull("workFinishDate");
//		List<WorkForm> list = this.find(hqlHelper);
//		if (list != null && list.size() > 0) {
//			return list.get(0);
//		} else {
//			return null;
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.grgbanking.soc.dao.services.workform.WorkformDAO#
//	 * getSelectWorkFormListH68N(java.lang.Integer, java.lang.Integer)
//	 */
//	public List<Object[]> getSelectWorkFormListH68N(Integer rowBegin,
//			Integer rowEnd) {
//		String sql = "";
//		if (UserContext.getUser().getDepartmentType().equals("B")) {
//			sql = " select x.* from(select a.*,rownum nums from(  "
//					+ " select y.workform_id,y.department_name,y.engineer_name,y.po_number,y.task_type,y.create_date,depart from ( "
//					+ " select w.workform_id,d.department_name,w.engineer_name,w.po_number,t.task_type,w.create_date,w.department_id,w.follow_username,w.engineer_id,work_finish_date, "
//					+ " (select q.department_id from t_base_department q where q.department_type='B' start with q.department_id=d.department_id "
//					+ " connect by prior  q.parent_id=q.department_id ) depart "
//					+ " from t_services_workform w "
//					+ " left join t_services_task t on w.workform_id=t.workform_id "
//					+ " left join v_ems_equipment_info e on w.equipment_id=e.equipment_id "
//					+ " left join t_base_department d on w.department_id=d.department_id "
//					+ " where to_char(w.work_finish_date,'yyyy-MM-dd')>=to_char(add_months(sysdate,-1),'yyyy-MM-dd') "
//					+ " and e.equipment_model in('H68N','H68NL') ) y left join t_base_user u on y.department_id=u.department_id "
//					+ " where  u.engineer_grade_h68n is not null and y.engineer_id!=u.user_id and y.follow_username not like '%'||u.name||'%' and depart="
//					+ UserContext.getUser().getDepartmentId()
//					+ " "
//					+ " order by y.work_finish_date desc  )a ) x  where x.nums between "
//					+ rowBegin + " and " + rowEnd + " ";
//		} else {
//			sql = " select x.* from(select a.*,rownum nums from(  "
//					+ " select y.workform_id,y.department_name,y.engineer_name,y.po_number,y.task_type,y.create_date from ( "
//					+ " select w.workform_id,d.department_name,w.engineer_name,w.po_number,t.task_type,w.create_date,w.department_id,w.follow_username,w.engineer_id,work_finish_date from t_services_workform w "
//					+ " left join t_services_task t on w.workform_id=t.workform_id "
//					+ " left join v_ems_equipment_info e on w.equipment_id=e.equipment_id "
//					+ " left join t_base_department d on w.department_id=d.department_id "
//					+ " where to_char(w.work_finish_date,'yyyy-MM-dd')>=to_char(add_months(sysdate,-1),'yyyy-MM-dd') "
//					+ " and e.equipment_model in('H68N','H68NL') ) y left join t_base_user u on y.department_id=u.department_id "
//					+ " where  u.engineer_grade_h68n is not null and y.engineer_id!=u.user_id and y.follow_username not like '%'||u.name||'%' "
//					+ " order by y.work_finish_date desc  )a ) x  where x.nums between "
//					+ rowBegin + " and " + rowEnd + " ";
//		}
//
//		return this.findBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#getTotalWorkFormListH68N
//	 * ()
//	 */
//	public Integer getTotalWorkFormListH68N() {
//		String sql = "";
//		if (UserContext.getUser().getDepartmentType().equals("B")) {
//			sql = " select count(*) from ( "
//					+ " select w.workform_id,w.department_id,w.follow_username,w.engineer_id,work_finish_date, "
//					+ " (select q.department_id from t_base_department q  where q.department_type='B' start with q.department_id=d.department_id  "
//					+ "  connect by prior  q.parent_id=q.department_id  ) depart  from t_services_workform w "
//					+ " left join t_services_task t on w.workform_id=t.workform_id "
//					+ " left join v_ems_equipment_info e on w.equipment_id=e.equipment_id "
//					+ " left join t_base_department d on w.department_id=d.department_id "
//					+ " where to_char(w.work_finish_date,'yyyy-MM-dd')>=to_char(add_months(sysdate,-1),'yyyy-MM-dd') "
//					+ " and e.equipment_model in('H68N','H68NL') ) y left join t_base_user u on y.department_id=u.department_id "
//					+ " where u.engineer_grade_h68n is not null and y.engineer_id!=u.user_id and y.follow_username not like '%'||u.name||'%' "
//					+ " and depart=" + UserContext.getUser().getDepartmentId()
//					+ " ";
//		} else {
//			sql = " select count(*) from ( "
//					+ " select w.workform_id,w.department_id,w.follow_username,w.engineer_id,work_finish_date from t_services_workform w "
//					+ " left join t_services_task t on w.workform_id=t.workform_id "
//					+ " left join v_ems_equipment_info e on w.equipment_id=e.equipment_id "
//					+ " left join t_base_department d on w.department_id=d.department_id "
//					+ " where to_char(w.work_finish_date,'yyyy-MM-dd')>=to_char(add_months(sysdate,-1),'yyyy-MM-dd') "
//					+ " and e.equipment_model in('H68N','H68NL') ) y left join t_base_user u on y.department_id=u.department_id "
//					+ " where u.engineer_grade_h68n is not null and y.engineer_id!=u.user_id and y.follow_username not like '%'||u.name||'%' ";
//		}
//
//		return this.countBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#getTotalWorkFormPhone
//	 * (java.lang.Integer, java.lang.String)
//	 */
//	public List<Object[]> getTotalWorkFormPhone(Integer departmentId,
//			String dateStart, String dateEnd) {
//		String sql1 = "select t.status,count(*) from t_services_workform t  ";
//		String sql2 = " where t.create_date >= to_date('" + dateStart
//				+ "','yyyy-MM-dd') and t.create_date < to_date('" + dateEnd
//				+ "','yyyy-MM-dd') ";
//		if (departmentId != null) {
//			sql1 += " inner join (select d.department_id from t_base_department d start with d.department_id="
//					+ departmentId
//					+ " "
//					+ " connect by prior d.department_id=d.parent_id) de on t.department_id=de.department_id ";
//		}
//		return this.findBySQL(sql1 + sql2 + " group by t.status ");
//	}
//
//	// 每天总工单数
//	public Integer getDailyWorkForm(Integer deptId) {
//		String sql;
//		sql = "select count(*) from T_SERVICES_TASK t ,t_services_workform w ";
//		sql += "inner join ( select d.* from t_base_department d start with d.department_id='"
//				+ deptId + "' connect by prior d.department_id=d.parent_id";
//		sql += " ) de on w.department_id=de.department_id";
//		sql += " where t.workform_id = w.workform_id";
//		sql += " and to_char(w.create_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-mm-dd')";
//
//		return this.countBySQL(sql);
//	}
//
//	// 每天录入的工单
//	public Integer getDailyEnterWorkForm(Integer deptId) {
//		String sql;
//		sql = "select count(*) from T_SERVICES_TASK t ,t_services_workform w ";
//		sql += " inner join (select d.* from t_base_department d start with d.department_id='"
//				+ deptId + "' connect by prior d.department_id=d.parent_id";
//		sql += " ) de on w.department_id=de.department_id ";
//		sql += " where t.workform_id = w.workform_id";
//		sql += " and to_char(w.create_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-mm-dd')";
//		sql += " and w.status in('" + ServicesConstants.STATUS_WORKFORM_STOCK
//				+ "','" + ServicesConstants.STATUS_WORKFORM_DIRECTOR + "','"
//				+ ServicesConstants.STATUS_WORKFORM_BACK + "','"
//				+ ServicesConstants.STATUS_WORKFORM_COMPLETED + "')";
//
//		return this.countBySQL(sql);
//	}
//
//	// 当天出勤的工程师
//	public Integer getDailyServiceEngineer(Integer deptId) {
//		// 主工程师
//		String sqlMain;
//		sqlMain = " select w.engineer_id from T_SERVICES_TASK t ,t_services_workform w ";
//		sqlMain += " inner join (select d.* from t_base_department d start with d.department_id='"
//				+ deptId + "' connect by prior d.department_id=d.parent_id ";
//		sqlMain += " ) de on w.department_id=de.department_id where t.workform_id = w.workform_id ";
//		sqlMain += " and to_char(w.create_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-mm-dd') group by w.engineer_id";
//		List<Map> mainEngineers = this.findMapResultBySql(sqlMain);
//
//		// 随从工程师人数
//		String sqlFollow;
//		sqlFollow = " select w.follow_userid from t_services_workform w ";
//		sqlFollow += " inner join (select d.* from t_base_department d start with d.department_id='"
//				+ deptId + "' connect by prior d.department_id=d.parent_id ";
//		sqlFollow += " ) de on w.department_id=de.department_id ";
//		sqlFollow += " where to_char(w.create_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-mm-dd')";
//		sqlFollow += " and w.follow_userid is not null ";
//		sqlFollow += " group by w.engineer_id,w.follow_userid";
//		List serviceEngineers = new ArrayList();
//		List<Map> follEngineer = this.findMapResultBySql(sqlFollow);
//		for (Map engineer : follEngineer) {
//			String[] followEngineers = engineer.get("FOLLOW_USERID").toString()
//					.split(",");
//			for (int i = 0; i < followEngineers.length; i++) {
//				if (!followEngineers[i].equals("")) {
//					serviceEngineers.add(followEngineers[i]);
//				}
//			}
//		}
//		Integer followEngineers = serviceEngineers.size();
//
//		return mainEngineers.size() + followEngineers;
//	}
//
//	// 总工程师
//	public Integer getTotalEngineer(Integer deptId) {
//		String sql = " select count(u.user_id) from  t_base_user u "
//				+ " inner join (select d.* from t_base_department d start with d.department_id='"
//				+ deptId
//				+ "' connect by prior d.department_id=d.parent_id ) de"
//				+ " on u.department_id=de.department_id where de.department_type ='C' and u.deleted ='N'";
//		return this.countBySQL(sql);
//	}
//
//	// 统计工单总量（1,机芯类型，2,区域 3,设备状态 4,工单状态 5,工单完成时间:截至到报表统计前一个月 6,厂商 ）
//	public Integer sumWorkformNumByModel(Integer departmentId,
//			String materialName, String taskType, String previousDate) {
//		String sql;
//		sql = " select count(*)";
//		sql += " from t_services_task task, v_ems_equipment_info e, t_services_workform w ,t_equipment_config c, t_sparepart s ";
//		sql += " where task.equipment_id=e.equipment_id and e.equipment_id=c.equipment_id and c.sparepart_id=s.sparepart_id and task.workform_id=w.workform_id ";
//		sql += " and s.material_name like '" + materialName + "%' ";
//		sql += " and s.module_type='B000'";
//		sql += " and e.manufacturer in ('GRGBANKING')";
//		sql += " and w.department_id in ("
//				+ StringUtils
//						.join(this.getChildDepartmentId(departmentId), ",")
//				+ ")";
//		sql += " and e.equipment_status in ('"
//				+ EquipmentConstants.EQUIPMENT_STATUS_USE + "')";
//		sql += " and w.status in ('"
//				+ ServicesConstants.STATUS_WORKFORM_DIRECTOR + "','"
//				+ ServicesConstants.STATUS_WORKFORM_STOCK + "','"
//				+ ServicesConstants.STATUS_WORKFORM_COMPLETED + "')";
//		sql += " and to_char(w.work_finish_date, 'yyyy-MM')='" + previousDate
//				+ "'";
//		sql += " and w.po_number is not null";
//		if (taskType != null) {
//			sql += " and task.task_type='" + taskType + "'";
//		}
//
//		return this.countBySQL(sql);
//	}
//
//	// 根据设备安装时间统计工单总数（1,机芯 2,厂商 3,设备状态 4,任务类型 5,统计日期）
//	public Integer sumWorkformNumByInstallDate(String materialName,
//			String taskType, String installDate, String previousDate) {
//		String sql;
//		sql = " select count(*)";
//		sql += " from t_services_task task, v_ems_equipment_info e, t_services_workform w ,t_equipment_config c, t_sparepart s ";
//		sql += " where task.equipment_id=e.equipment_id and e.equipment_id=c.equipment_id and c.sparepart_id=s.sparepart_id and task.workform_id=w.workform_id ";
//		sql += " and s.material_name like '" + materialName + "%'";
//		sql += " and s.module_type='B000'";
//		sql += " and e.manufacturer in ('GRGBANKING')";
//		sql += " and e.equipment_status in ('"
//				+ EquipmentConstants.EQUIPMENT_STATUS_USE + "') ";
//		sql += " and w.status in ('"
//				+ ServicesConstants.STATUS_WORKFORM_DIRECTOR + "','"
//				+ ServicesConstants.STATUS_WORKFORM_STOCK + "','"
//				+ ServicesConstants.STATUS_WORKFORM_COMPLETED + "')";
//		sql += " and w.po_number is not null";
//		if (taskType != null) {
//			sql += " and task.task_type='" + taskType + "'";
//		}
//		if (installDate != null) {
//			if (installDate.equals(">36")) {
//				sql += " and months_between(to_date('" + previousDate
//						+ "','yyyy-MM'),e.install_date)" + installDate;
//			} else {
//				sql += " and months_between(to_date('" + previousDate
//						+ "','yyyy-MM'),e.install_date)<= " + installDate;
//			}
//		}
//
//		return this.countBySQL(sql);
//	}
//
//	// 统一月份区域工单总数（1,厂商 2,设备状态 3,工单状态 3,工单完成时间:指定月份 4,任务类型）
//	public Integer countWorkformsByRegion(Integer departmentId,
//			String currentMonth, String taskType) {
//		String sql;
//		sql = " select count(*)";
//		sql += " from t_services_task task, v_ems_equipment_info e, t_services_workform w";
//		sql += " where task.equipment_id=e.equipment_id  and task.workform_id=w.workform_id ";
//		sql += " and e.manufacturer in ('GRGBANKING')";
//		sql += " and w.department_id in ("
//				+ StringUtils
//						.join(this.getChildDepartmentId(departmentId), ",")
//				+ ")";
//		sql += " and e.equipment_status in ('"
//				+ EquipmentConstants.EQUIPMENT_STATUS_USE + "') ";
//		sql += " and w.status in ('"
//				+ ServicesConstants.STATUS_WORKFORM_DIRECTOR + "','"
//				+ ServicesConstants.STATUS_WORKFORM_STOCK + "','"
//				+ ServicesConstants.STATUS_WORKFORM_COMPLETED + "')";
//		sql += " and to_char(w.work_finish_date, 'yyyy-MM')='" + currentMonth
//				+ "'";
//		sql += " and w.po_number is not null";
//		if (taskType != null) {
//			sql += " and task.task_type='" + taskType + "'";
//		}
//
//		return this.countBySQL(sql);
//	}
//
//	// 统计设备安装时间的工单总数
//	public Integer countWorkformsByInstallDate(String currentDate,
//			String installDate, String taskType) {
//		String sql;
//		sql = " select count(*)";
//		sql += " from t_services_task task, v_ems_equipment_info e, t_services_workform w ";
//		sql += " where task.equipment_id=e.equipment_id and  task.workform_id=w.workform_id ";
//		sql += " and e.manufacturer in ('GRGBANKING') ";
//		sql += " and e.equipment_status in ('"
//				+ EquipmentConstants.EQUIPMENT_STATUS_USE + "') ";
//		sql += " and w.status in ('"
//				+ ServicesConstants.STATUS_WORKFORM_DIRECTOR + "','"
//				+ ServicesConstants.STATUS_WORKFORM_STOCK + "','"
//				+ ServicesConstants.STATUS_WORKFORM_COMPLETED + "')";
//		sql += " and w.po_number is not null";
//		if (taskType != null) {
//			sql += " and task.task_type='" + taskType + "'";
//		}
//		if (installDate.equals(">36")) {
//			sql += " and months_between(to_date('" + currentDate
//					+ "','yyyy-MM'),e.install_date)" + installDate;
//		} else {
//			sql += " and months_between(to_date('" + currentDate
//					+ "','yyyy-MM'),e.install_date)<=" + installDate;
//		}
//
//		return this.countBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#countWorkFormByFillDate
//	 * (java.lang.String, java.lang.String)
//	 */
//	public Integer countWorkFormByFillDate(Integer departmentId,
//			String fillDateStart, String fillDateEnd) {
//		String sql = " select count(*) from t_services_workform t where t.department_id="
//				+ departmentId
//				+ " and t.status<>'"
//				+ ServicesConstants.STATUS_WORKFORM_COMPLETED + "' ";
//		if (fillDateStart != null) {
//			sql += " and to_char(t.fill_date,'yyyy-MM-dd')<='" + fillDateStart
//					+ "' ";
//		}
//		if (fillDateEnd != null) {
//			sql += " and to_char(t.fill_date,'yyyy-MM-dd')>'" + fillDateEnd
//					+ "' ";
//		}
//		return this.countBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#countWorkFormByPhone
//	 * (java.lang.Integer)
//	 */
//	public Integer countWorkFormByPhone(Integer departmentId) {
//		String sql = " select count(distinct t.workform_id) from t_services_workform t inner join v_ma_phone_info m on t.engineer_name=m.USER_NAME "
//				+ " where t.department_id="
//				+ departmentId
//				+ " and to_char(t.fill_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-MM-dd') ";
//		return this.countBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.grgbanking.soc.dao.services.workform.WorkformDAO#
//	 * countWorkFormByPhoneActual(java.lang.Integer)
//	 */
//	public Integer countWorkFormByPhoneActual(Integer departmentId) {
//		/*
//		 * String
//		 * sql="select count(*) from t_services_workform t where t.department_id="
//		 * +departmentId+" " +
//		 * " and to_char(t.fill_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-mm-dd') and t.submit_type='phone' "
//		 * ;
//		 */
//		// String
//		// sql=" select count(t.workform_id) from t_services_workform t inner join v_ma_phone_info m on t.engineer_name=m.USER_NAME "
//		// +
//		// " where t.department_id="+departmentId+" and to_char(t.fill_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-mm-dd') and t.submit_type='phone' ";
//		String sql = "select count(distinct w.workform_id) from t_services_workform_comment c inner join t_services_workform w on c.work_id=w.workform_id "
//				+ " inner join v_ma_phone_info m on w.engineer_name=m.USER_NAME  where  (c.remark='【手机提交工单】' or w.create_type='SA') and to_char(w.fill_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-MM-dd') "
//				+ " and w.department_id="
//				+ departmentId;
//		return this.countBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.grgbanking.soc.dao.services.workform.WorkformDAO#
//	 * countWorkFormByFillDateTotal(java.lang.Integer)
//	 */
//	public Integer countWorkFormByFillDateTotal(Integer departmentId) {
//		String sql = " select count(*) from t_services_workform t where t.department_id="
//				+ departmentId
//				+ " "
//				+ " and to_char(t.fill_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-mm-dd') and to_char(t.fill_date,'yyyy-MM-dd')=to_char(t.work_start_date,'yyyy-mm-dd') ";
//		return this.countBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#getDelayWorkFromBooks
//	 * ()
//	 */
//	public List<Object[]> getDelayWorkFromBooks() {
//		String sql = "select d2.department_name regionname,d1.department_name areaname,d.department_name,t.po_number, "
//				+ " t.engineer_name,to_char(t.create_date,'yyyy-MM-dd hh24:mi'),to_char(t.fill_date,'yyyy-MM-dd hh24:mi'), "
//				+ " to_char(t.work_start_date,'yyyy-MM-dd hh24:mi'),to_char(t.work_finish_date,'yyyy-MM-dd hh24:mi') "
//				+ " from t_services_workform t left join t_base_department d on t.department_id=d.department_id left join t_base_department d1 on d1.department_id=d.parent_id "
//				+ " left join t_base_department d2 on d2.department_id=d1.parent_id where to_char(t.fill_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-mm-dd') "
//				+ " and to_char(t.fill_date,'yyyy-MM-dd')<>to_char(t.work_start_date,'yyyy-MM-dd') order by d2.department_id,d1.department_id,d.department_id ";
//		return this.findBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#getUnPhoneWorkFromBooks
//	 * ()
//	 */
//	public List<Object[]> getUnPhoneWorkFromBooks() {
//		String sql = " select d2.department_name regionname,d1.department_name areaname,d.department_name,t.po_number,t.engineer_name, "
//				+ " to_char(t.fill_date,'yyyy-MM-dd hh24:mi'),to_char(t.work_start_date,'yyyy-MM-dd hh24:mi'), "
//				+ " to_char(t.work_finish_date,'yyyy-MM-dd hh24:mi'),e.install_address from t_services_workform t "
//				+ " inner join v_ma_phone_info m on t.engineer_name=m.USER_NAME left join t_base_department d on t.department_id=d.department_id "
//				+ " left join t_base_department d1 on d1.department_id=d.parent_id left join t_base_department d2 on d2.department_id=d1.parent_id "
//				+ " left join v_ems_equipment_info e on t.equipment_id=e.equipment_id where to_char(t.fill_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-MM-dd') "
//				+ " and (t.create_type is null or t.create_type='CSS') and (t.workform_id NOT in(select distinct w.workform_id from  t_services_workform w  inner join v_ma_phone_info m on w.engineer_name=m.USER_NAME  "
//				+ " inner join t_services_workform_comment c  on w.workform_id=c.work_id where  c.remark='【手机提交工单】' and to_char(w.fill_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-MM-dd') ) ) order by d2.department_id,d1.department_id,d.department_id ";
//
//		return this.findBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * com.grgbanking.soc.dao.services.workform.WorkformDAO#geWorkFormCountFillDate
//	 * (java.lang.Integer)
//	 */
//	public Integer geWorkFormCountFillDate(Integer deptId) {
//		String sql = "select count(*) from t_services_workform w where w.department_id="
//				+ deptId
//				+ " and to_char(w.fill_date,'yyyy-MM-dd')=to_char(sysdate-1,'yyyy-MM-dd')";
//		return this.countBySQL(sql);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.grgbanking.soc.dao.services.workform.WorkformDAO#
//	 * getAuditDetaiWorkFromBooks()
//	 */
//	public List<Object[]> getAuditDetaiWorkFromBooks(String fillDate) {
//		String sql = " select d2.department_name regionname,d1.department_name areaname,d.department_name,t.po_number,t.engineer_name, "
//				+ "e.serial_number,t.status,to_char(t.fill_date,'yyyy-MM-dd') from t_services_workform t "
//				+ " left join v_ems_equipment_info e on t.equipment_id=e.equipment_id left join t_base_department d on t.department_id=d.department_id "
//				+ " left join t_base_department d1 on d.parent_id=d1.department_id left join t_base_department d2 on d1.parent_id=d2.department_id "
//				+ " where  t.status<>'已完成' and to_char(t.fill_date,'yyyy-MM-dd')<='"
//				+ fillDate
//				+ "' "
//				+ " order by d2.department_id,d1.department_id,d.department_id ";
//		return this.findBySQL(sql);
//	}
//
//	public List<Integer> getCheckWorkformDepartmentId(Integer departmentId) {
//		List<Integer> departmentIdList=new ArrayList<Integer>();
//		String sql="select t.check_department_id,t.be_check_department_id from t_base_check_department t " +
//				" where t.check_department_id="+departmentId+" ";
//		List<Object[]> list=this.findBySQL(sql);
//		for (Object[] obj : list) {
//			departmentIdList.add(((BigDecimal)obj[1]).intValue());
//		}
//		return departmentIdList;
//	}
//
//	public List<Object[]> queryCheckWorkFormList(String statusDirector,String statusStorekeeper,
//			Integer departmentId,List<Integer> departmentIds) {
//		String sql="select t.workform_id,t.po_number,t.equipment_id,t.engineer_name,t.create_date,t.status,t.department_id from t_services_workform t " +
//				" where t.deleted='N' and (t.create_type is null or t.create_type='CSS') ";//12-31加and t.create_type去SA工单
//		if(StringUtils.isBlank(statusDirector) && StringUtils.isBlank(statusStorekeeper)){
//			//无状态处理
//			sql+=" and 1>1 ";
//		}
//		if(departmentIds!=null && departmentIds.size()>0){
//			//仓库管理员审核多个服务站
//			if(StringUtils.isNotBlank(statusDirector) && StringUtils.isNotBlank(statusStorekeeper)){
//				sql+=" and ( (t.department_id="+departmentId+" and t.status='"+statusDirector+"') " +
//					" or ( t.department_id in("+ArrayTransitionUtils.integerListToInString(departmentIds)+") and t.status='"+statusStorekeeper+"') ) " ;
//			}else if(StringUtils.isNotBlank(statusDirector) && !StringUtils.isNotBlank(statusStorekeeper)){
//				sql+=" and t.department_id="+departmentId+" and t.status='"+statusDirector+"' ";
//			}else if(!StringUtils.isNotBlank(statusDirector) && StringUtils.isNotBlank(statusStorekeeper)){
//				sql+=" and t.department_id in("+ArrayTransitionUtils.integerListToInString(departmentIds)+") and t.status='"+statusStorekeeper+"' ";
//			}
//		}else{
//			sql+=" and t.department_id="+departmentId+" ";
//			if(StringUtils.isNotBlank(statusDirector) && StringUtils.isNotBlank(statusStorekeeper)){
//				sql+=" and t.status in('"+statusDirector+"','"+statusStorekeeper+"') ";
//			}else{
//				if(StringUtils.isNotBlank(statusDirector)){
//					sql+=" and t.status='"+statusDirector+"' ";
//				}else if(StringUtils.isNotBlank(statusStorekeeper)){
//					sql+=" and t.status='"+statusStorekeeper+"' ";
//				}
//			}
//		}
//		sql+=" order by t.modify_date desc ";
//		return this.findBySQL(sql);
//	}
//
//	public Integer getSumSelectCheckWorkform(String statusDirector,
//			String statusStorekeeper, Integer departmentId,
//			List<Integer> departmentIds) {
//		String sql="select count(*) from t_services_workform t " +
//		" where t.deleted='N' and (t.create_type is null or t.create_type='CSS' ) ";//12-31加and t.create_type去SA工单
//		if(StringUtils.isBlank(statusDirector) && StringUtils.isBlank(statusStorekeeper)){
//			//无状态处理
//			sql+=" and 1>1 ";
//		}
//		if(departmentIds!=null && departmentIds.size()>0){
//			//仓库管理员审核多个服务站
//			if(StringUtils.isNotBlank(statusDirector) && StringUtils.isNotBlank(statusStorekeeper)){
//				sql+=" and ( (t.department_id="+departmentId+" and t.status='"+statusDirector+"') " +
//					" or ( t.department_id in("+ArrayTransitionUtils.integerListToInString(departmentIds)+") and t.status='"+statusStorekeeper+"') ) " ;
//			}else if(StringUtils.isNotBlank(statusDirector) && !StringUtils.isNotBlank(statusStorekeeper)){
//				sql+=" and t.department_id="+departmentId+" and t.status='"+statusDirector+"' ";
//			}else if(!StringUtils.isNotBlank(statusDirector) && StringUtils.isNotBlank(statusStorekeeper)){
//				sql+=" and t.department_id in("+ArrayTransitionUtils.integerListToInString(departmentIds)+") and t.status='"+statusStorekeeper+"' ";
//			}
//		}else{
//			sql+=" and t.department_id="+departmentId+" ";
//			if(StringUtils.isNotBlank(statusDirector) && StringUtils.isNotBlank(statusStorekeeper)){
//				sql+=" and t.status in('"+statusDirector+"','"+statusStorekeeper+"') ";
//			}else{
//				if(StringUtils.isNotBlank(statusDirector)){
//					sql+=" and t.status='"+statusDirector+"' ";
//				}else if(StringUtils.isNotBlank(statusStorekeeper)){
//					sql+=" and t.status='"+statusStorekeeper+"' ";
//				}
//			}
//		}
//		return this.countBySQL(sql);
//	}
//	
//	public List<Object[]> queryWorkFormByBranchName(String branchName, Page queryPage,Integer customerId){
//		String sql = "select t.*, s.task_type "+
//					 " from (select e.equipment_model, "+
//					 "              e.serial_number, "+
//					 "              w.po_number, "+
//					 "              w.work_finish_date, "+
//					 "              w.workform_id, "+
//					 "              r.contact_name repairsManName , "+
//					 "              nvl(r.moblie_phone,r.telephone) repairsMoblie , "+
//					 "              c.contact_name receiveManName , "+
//					 "              nvl(c.moblie_phone,c.telephone) receiveMoblie "+
//					 "         from t_services_workform w "+
//					 "         left join v_ems_equipment_info e on w.equipment_id = e.equipment_id "+
//					 "         left join t_services_task_contact r on w.workform_id = r.workform_id and r.task_contact_type=1"+
//					 "         left join t_services_task_contact c on w.workform_id = c.workform_id and c.task_contact_type=2"+
//					 "        where 1=1 ";
//		//插入同行的逻辑
//		if(null!=customerId){
//			sql+=" and e.customer_id in ("
//				+" select a.id from mv_crm_customer_info a start with a.id = "+customerId+" connect by prior a.parent_custimer_id = a.id "
//				+" union all"
//				+" select a.id from mv_crm_customer_info a start with a.id = "+customerId+" connect by prior a.id = a.parent_custimer_id "
//				+") ";
//		}			 
//		
//		sql+=" and e.branch_name = '"+branchName+"' "+
//					 "  and e.deleted = 'N' "+
//					 "  and w.deleted = 'N' " +
//					 "	and w.work_finish_date is not null	" +
//					 " 	order by w.work_finish_date desc) t "+
//					 " left join t_services_task s on t.workform_id = s.workform_id" +
//					 (queryPage != null?"":" where rownum<=3 ") 
//					 ;
//		
//		return this.findBySQL(sql, queryPage, null);
//	}
//
//	public List<Object[]> getWorkFormSatisfiedList(Page page,WorkFormBean workformBean) {
//		String sqlwhere=" ";
//		if(workformBean.getDepartmentId()!=null){
//			sqlwhere+=" and t.department_id in ("+StringUtils.join(this.getChildDepartmentId(workformBean.getDepartmentId()),",")+")";
//		}
//		if(StringUtils.isNotEmpty(workformBean.getWorkFinishDateStart())){
//			sqlwhere+=" and to_char(t.work_finish_date,'yyyy-MM-dd')>='"+workformBean.getWorkFinishDateStart()+"' ";
//		}
//		if(StringUtils.isNotEmpty(workformBean.getWorkFinishDateEnd())){
//			sqlwhere+=" and to_char(t.work_finish_date,'yyyy-MM-dd')<='"+workformBean.getWorkFinishDateEnd()+"' ";
//		}
//		String sql="select d2.department_name region_name,d1.department_name area_name,d.department_name, " +
//				"u.name,s.satis1,s.satis2,s.satis3,s.satis4,s.satis5,s.satis6 from ( " +
//				"select w.engineer_id,w.engineer_name,sum(decode(w.satisfied_type,1,w.mu)) satis1, " +
//				"sum(decode(w.satisfied_type,2,w.mu)) satis2,sum(decode(w.satisfied_type,3,w.mu)) satis3, " +
//				"sum(decode(w.satisfied_type,4,w.mu)) satis4,sum(decode(w.satisfied_type,5,w.mu)) satis5, " +
//				"sum(decode(w.satisfied_type,null,w.mu)) satis6 from ( select t.engineer_id,t.engineer_name,t.satisfied_type,count(*) mu " +
//				"from t_services_workform t where 1=1 "+sqlwhere+" " +
//				"group by t.engineer_id,t.engineer_name,t.satisfied_type " +
//				") w group by  w.engineer_id,w.engineer_name " +
//				") s left join t_base_user u on s.engineer_id=u.user_id left join t_base_department d on u.department_id=d.department_id " +
//				"left join t_base_department d1 on d.parent_id=d1.department_id  " +
//				"left join t_base_department d2 on d1.parent_id=d2.department_id " +
//				"order by d2.department_name,d1.department_name,d.department_name ";
//		
//		return this.findBySQL(sql,page);
//	}
//	
//	@Override
//	public List<WorkForm> getAllWorkformByReportTelephone(Page queryPage,
//			List<WorkTask> workTaskList) {
//		HqlHelper hqlHelper = new HqlHelper(WorkForm.class);
//		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
//		hqlHelper.addIsNull("satisfiedTypeSms");
//		hqlHelper.addDateBetween("workFinishDate",DateUtils.addDay(new Date(),-7,"yyyy-MM-dd"),DateUtils.formatDate(new Date()));
//		String workFormIds="";
//		if(workTaskList!=null){
//			for(WorkTask workTask:workTaskList){
//				if(workTask.getWorkForm()!=null){
//					workFormIds+=workTask.getWorkForm().getWorkFormId()+",";
//				}
//			}
//		}
//		hqlHelper.addIn("workFormId",workFormIds.equals("")?null:ArrayTransitionUtils.stringToIntegerArray(workFormIds.substring(0,workFormIds.length()-1), ","));
//		hqlHelper.addOrderBy("workFinishDate", "desc");
//		if(queryPage.getPage()!=null&&queryPage.getPage()>0){
//			hqlHelper.setQueryPage(queryPage);
//		}
//		return this.find(hqlHelper);
//	}
}
