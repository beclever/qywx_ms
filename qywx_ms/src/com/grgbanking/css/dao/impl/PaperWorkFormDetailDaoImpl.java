/**
 * 
 */
package com.grgbanking.css.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.work.PaperWorkFormBean;
import com.grgbanking.css.bean.work.PaperWorkFormDetail;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.PaperWorkFormDetailDao;
import com.grgbanking.css.exception.ServiceException;
import com.grgbanking.css.util.HqlHelper;

/**
 * Copyright (c) 2011 GRG Banking Equipment Co.,Ltd.
 * 
 * @createDate 2012-10-15
 * @author luowei
 * @description
 * 
 */
@Repository("paperWorkFormDetailDao")
public class PaperWorkFormDetailDaoImpl extends
		CssBaseDAOImpl<PaperWorkFormDetail, Long> implements
		PaperWorkFormDetailDao<PaperWorkFormDetail, Long> {
	
	/*public void update(PaperWorkFormDetail entity){
		entity.setBindingSource("0");
		entity.setModifyDate(new Date());
		super.update(entity);
	}*/
	
//	public List<Map> searchPaperWorkFormList(
//			PaperWorkFormBean bean, Page queryPage) {
//		StringBuffer sql = new StringBuffer()
//			.append("select pf.id, pf.form_id, pf.paper_form_code, bu.name, pf.use_status, pf.abandoned_reason ")
//			.append("  from T_PAPER_FORM_DETAIL pf, t_base_user bu ")
//			.append("where pf.USER_ID = bu.USER_ID and bu.user_id = "+ UserContext.getUser().getUserId());
//		
//		if(StringUtils.isNotEmpty(bean.getUseStatus()))
//			sql.append("   and pf.use_status = '" + bean.getUseStatus() + "'");
//		
//		if(StringUtils.isNotEmpty(bean.getPaperFormCode()))
//			sql.append("   and pf.paper_form_code like '%"+bean.getPaperFormCode()+"%' and pf.form_id is not null");
//		
//		if(StringUtils.isNotEmpty(bean.getOwnerUserName()))
//			sql.append("   and bu.name like '"+bean.getOwnerUserName()+"%'");
//		
//		if(StringUtils.isNotEmpty(bean.getStartCode()))
//			sql.append("   and pf.paper_form_code >= '"+bean.getStartCode()+"'");
//		
//		if(StringUtils.isNotEmpty(bean.getEndCode()))
//			sql.append("   and pf.paper_form_code <= '"+bean.getEndCode()+"'");
//		
//		if(StringUtils.isNotEmpty(bean.getAbandonedWorkflowId()))
//			sql.append("   and pf.abandoned_apply_workflow_id = '"+bean.getAbandonedWorkflowId()+"'");
//		
//		if(StringUtils.isNotEmpty(bean.getDepartmentId()))
//			sql.append("   and bu.department_id = '" + bean.getDepartmentId() + "'");
//		
//		sql.append(" order by pf.id asc");
//		return findMapResultBySql(sql.toString(), queryPage);
//	}
//
//	public List<Map> searchPaperWorkFormNotApplyAbandonedList(
//			PaperWorkFormBean bean, Page queryPage) {
//		StringBuffer sql = new StringBuffer()
//			.append("select pf.id, pf.form_id, pf.paper_form_code, bu.name, pf.use_status, pf.abandoned_reason ")
//			.append("  from T_PAPER_FORM_DETAIL pf, t_base_user bu ")
//			.append(" where pf.USER_ID = bu.USER_ID and pf.abandoned_apply_workflow_id is null  and bu.user_id = "+ UserContext.getUser().getUserId());
//	
//		if(StringUtils.isNotEmpty(bean.getUseStatus()))
//			sql.append("   and pf.use_status = '" + bean.getUseStatus() + "'");
//		
//		if(StringUtils.isNotEmpty(bean.getOwnerUserName()))
//			sql.append("   and bu.name like '"+bean.getOwnerUserName()+"%'");
//		
//		if(StringUtils.isNotEmpty(bean.getStartCode()))
//			sql.append("   and pf.paper_form_code >= '"+bean.getStartCode()+"'");
//		
//		if(StringUtils.isNotEmpty(bean.getEndCode()))
//			sql.append("   and pf.paper_form_code <= '"+bean.getEndCode()+"'");
//		
//		if(StringUtils.isNotEmpty(bean.getDepartmentId()))
//			sql.append("   and bu.department_id = '" + bean.getDepartmentId() + "'");
//		
//		sql.append(" order by pf.id asc");
//		return findMapResultBySql(sql.toString(), queryPage);
//	}
//
	public Map generateNextPaperWorkFormCode(PaperWorkFormBean bean, Integer userId) {
		
		StringBuffer sql = new StringBuffer()
			.append("select paper_form_code,id ")
			.append("  from (select t.paper_form_code,t.id")
			.append("          from V_PAPER_FORM_DETAIL t")
			.append("         where t.user_id = '"+userId+"' and t.use_status = '1' ");
		if(StringUtils.isNotEmpty(bean.getPaperFormCode())){
			PaperWorkFormDetail detail = findByPaperFormCode(bean.getPaperFormCode());
			if(null != detail)
				sql.append("           and t.id > "+detail.getId()+" ");
		}
		sql.append("         order by t.id asc)")
			.append(" where rownum <= 1");
		List<Map> list = findMapResultBySql(sql.toString());
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	public PaperWorkFormDetail findByPaperFormCode(String paperFormCode) {
		return (PaperWorkFormDetail) findUniqueBy(PaperWorkFormDetail.class, "paperWorkFormCode", paperFormCode);
	}
	
	public PaperWorkFormDetail findByWorkFormId(String paperFormCode, Long workFormId) {
		HqlHelper hqlHelper = new HqlHelper(PaperWorkFormDetail.class);
		if (null != workFormId)
			hqlHelper.addEqual("workFormId", workFormId.toString());
		hqlHelper.addEqual("paperWorkFormCode", paperFormCode);
		List<PaperWorkFormDetail> list = find(hqlHelper);
		if (null != list && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<PaperWorkFormDetail> findByWorkFormId(String workFormId) {
		if(StringUtils.isEmpty(workFormId))
			throw new ServiceException("系统找不到对应的工单，请重新登录后再次尝试，若问题依旧出现，请联系系统管理员");
		HqlHelper hqlHelper = new HqlHelper(PaperWorkFormDetail.class);
		hqlHelper.addEqual("workFormId", workFormId);
		return find(hqlHelper);
	}

//	public String findSinglePaperWorkFormCode(String abandonedWorkflowId) {
//		PaperWorkFormDetail detail = (PaperWorkFormDetail)findUniqueBy(PaperWorkFormDetail.class, "paperWorkFormAbandoneId", abandonedWorkflowId);
//		if(detail!=null) 
//			return detail.getPaperWorkFormCode();
//		return null;
//	}
//
//	public List<Map> searchPaperWorkFormDetailList(PaperWorkFormBean bean,
//			Page queryPage) {
//		StringBuffer sql = new StringBuffer() 
//			.append("select pf.id, pf.form_id, pf.paper_form_code, bu.name, pf.use_status, pf.abandoned_reason ")
//			.append("  from T_PAPER_FORM_DETAIL pf, t_base_user bu ")
//			.append("where pf.USER_ID = bu.USER_ID ");
//		
//		if(StringUtils.isNotEmpty(bean.getUseStatus()))
//			sql.append("   and pf.use_status = '" + bean.getUseStatus() + "'");
//		
//		if(StringUtils.isNotEmpty(bean.getOwnerUserName()))
//			sql.append("   and bu.name like '"+bean.getOwnerUserName()+"%'");
//		
//		if(StringUtils.isNotEmpty(bean.getStartCode())){
//			String startCode = String.format("%07d", Long.parseLong( bean.getStartCode().replaceFirst("^0*", "")));
//			sql.append("   and pf.paper_form_code >= '"+startCode+"'");
//		}
//		
//		if(StringUtils.isNotEmpty(bean.getEndCode())){
//			String endCode = String.format("%07d", Long.parseLong(bean.getEndCode().replaceFirst("^0*", "")));
//			sql.append("   and pf.paper_form_code <= '"+endCode+"'");
//		}
//		
//		if(StringUtils.isNotEmpty(bean.getAbandonedWorkflowId()))
//			sql.append("   and pf.abandoned_apply_workflow_id = '"+bean.getAbandonedWorkflowId()+"'");
//		
//		if(StringUtils.isNotEmpty(bean.getDepartmentId()))
//			sql.append("   and bu.department_id = '" + bean.getDepartmentId() + "'");
//		
//		if(StringUtils.isNotEmpty(bean.getDepartmentIds()))
//			sql.append("   and bu.department_id in (" + bean.getDepartmentIds() + ")");
//		
//		if(StringUtils.isNotEmpty(bean.getPaperFormCode()))
//			sql.append("   and pf.paper_form_code like '%"+bean.getPaperFormCode()+"%' and pf.form_id is not null");
//		
//		sql.append(" order by pf.paper_form_code, pf.id asc");
//		return findMapResultBySql(sql.toString(), queryPage);
//	}
//
//	public int querySelfPaperNum(Integer userId) {
//		StringBuffer sql = new StringBuffer()
//			.append("select count(*)")
//			.append(" from T_PAPER_FORM_DETAIL t ")
//			.append("where t.user_id = '"+userId+"'  and t.use_status in('1','3')  ");
//		return countBySQL(sql.toString());
//	}
//
//	public List<PaperWorkFormDetail> searchPaperWorkFormList(
//			String abandonedWorkflowId) {
//		if(StringUtils.isEmpty(abandonedWorkflowId))
//			throw new ServiceException("系统找不到对应的纸质工单，请重新登录后再次尝试，若问题依旧出现，请联系系统管理员");
//		HqlHelper hqlHelper = new HqlHelper(PaperWorkFormDetail.class);
//		hqlHelper.addEqual("paperWorkFormAbandoneId", abandonedWorkflowId);
//		return find(hqlHelper);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.grgbanking.soc.dao.services.pworkform.PaperWorkFormDetailDao#searchPaperWorkFormQueryList(com.grgbanking.soc.bean.services.PaperWorkFormBean, com.grgbanking.framework.core.Page)
//	 */
//	public List<Map> searchPaperWorkFormQueryList(PaperWorkFormBean bean,
//			Page queryPage) {
//		StringBuffer sql = new StringBuffer()
//		.append("select pf.id, pf.form_id, pf.paper_form_code, bu.name, pf.use_status, pf.abandoned_reason ")
//		.append("  from T_PAPER_FORM_DETAIL pf, t_base_user bu ")
//		.append("where pf.USER_ID = bu.USER_ID ");
//	
//	if(StringUtils.isNotEmpty(bean.getUseStatus()))
//		sql.append("   and pf.use_status = '" + bean.getUseStatus() + "'");
//	
//	if(StringUtils.isNotEmpty(bean.getPaperFormCode()))
//		sql.append("   and pf.paper_form_code like '%"+bean.getPaperFormCode()+"%' and pf.form_id is not null");
//	
//	if(StringUtils.isNotEmpty(bean.getOwnerUserName()))
//		sql.append("   and bu.name like '"+bean.getOwnerUserName()+"%'");
//	
//	if(StringUtils.isNotEmpty(bean.getStartCode()))
//		sql.append("   and pf.paper_form_code >= '"+bean.getStartCode()+"'");
//	
//	if(StringUtils.isNotEmpty(bean.getEndCode()))
//		sql.append("   and pf.paper_form_code <= '"+bean.getEndCode()+"'");
//	
//	if(StringUtils.isNotEmpty(bean.getAbandonedWorkflowId()))
//		sql.append("   and pf.abandoned_apply_workflow_id = '"+bean.getAbandonedWorkflowId()+"'");
//	
//	if(StringUtils.isNotEmpty(bean.getDepartmentIds()))
//		sql.append("   and bu.department_id in (" + bean.getDepartmentIds() + ")");
//	
//	if(StringUtils.isNotEmpty(bean.getAbandondReason()))
//		sql.append("   and pf.abandoned_reason = ").append(bean.getAbandondReason());
//	
//	sql.append(" order by pf.id asc");
//	return findMapResultBySql(sql.toString(), queryPage);
//	}
//
//	public List<Map> searchPaperWorkFormReportList(PaperWorkFormBean bean,
//			Page queryPage) {
//		StringBuffer sql = new StringBuffer();
//		sql.append("select t1.*,very_satisfy,satisfy,normal,no_satisfy,very_no_satisfy,satisfy_degree from")
//		.append("( ")
//		.append("select t.department_id, bd.department_name, all_count,left_count,abandon_count,used_count,ready_lose_count,ready_stained_count ")
//		.append("  from (select bu.department_id, ")
//		.append("               count(*) as all_count, ")
//		.append("               sum(decode(t.use_status, '1', 1, 0)) left_count, ")
//		.append("               sum(decode(t.use_status, '0', 1, 0)) abandon_count, ")
//		.append("               sum(decode(t.use_status, '2', 1, 0)) used_count, ")
//		.append("               sum((case when t.use_status='3' and t.abandoned_reason='1' then 1 else 0 end)) ready_lose_count, ")
//		.append("               sum((case when t.use_status='3' and t.abandoned_reason='2' then 1 else 0 end)) ready_stained_count ")
//		.append("          from T_PAPER_FORM_DETAIL t, t_base_user bu ")
//		.append("         where t.USER_ID = bu.USER_ID ")
//		.append("         group by  bu.department_id) t, ")
//		.append("       t_base_department bd ")
//		.append(" where t.department_id = bd.department_id ")
//		.append(") t1 left join ( ")
//		.append("select t.*, round((very_satisfy+satisfy*0.9+normal*0.6+no_satisfy*0.4)*100/(very_satisfy+satisfy+normal+no_satisfy+very_no_satisfy),2) satisfy_degree from (")
//		.append("select t.department_id, ")
//		.append("       sum(decode(t.satisfied_type, '1', 1, 0)) very_satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '2', 1, 0)) satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '3', 1, 0)) normal, ")
//		.append("       sum(decode(t.satisfied_type, '4', 1, 0)) no_satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '5', 1, 0)) very_no_satisfy, ")
//		.append("       sum((case when t.satisfied_type='1' and 1=0 then 1 else 0 end)) goodLocation ")
//		.append(" from t_services_workform t where t.satisfied_type is not null group by t.department_id ")
//		.append(" ) t")
//		.append(" ) t2 on t1.department_id=t2.department_id ");
//		if(StringUtils.isNotEmpty(bean.getDepartmentIds())){
//			sql.append(" where t1.department_id in ("+bean.getDepartmentIds()+") ");
//		}
//		
//		return findMapResultBySql(sql.toString(), queryPage);
//	}
//	public List<Map> searchPaperWorkFormReportList(PaperWorkFormBean bean) {
//		StringBuffer sql = new StringBuffer();
//		sql.append("select t1.*,very_satisfy,satisfy,normal,no_satisfy,very_no_satisfy,satisfy_degree from")
//		.append("( ")
//		.append("select t.department_id, bd.department_name, all_count,left_count,abandon_count,used_count,ready_lose_count,ready_stained_count ")
//		.append("  from (select bu.department_id, ")
//		.append("               count(*) as all_count, ")
//		.append("               sum(decode(t.use_status, '1', 1, 0)) left_count, ")
//		.append("               sum(decode(t.use_status, '0', 1, 0)) abandon_count, ")
//		.append("               sum(decode(t.use_status, '2', 1, 0)) used_count, ")
//		.append("               sum((case when t.use_status='3' and t.abandoned_reason='1' then 1 else 0 end)) ready_lose_count, ")
//		.append("               sum((case when t.use_status='3' and t.abandoned_reason='2' then 1 else 0 end)) ready_stained_count ")
//		.append("          from T_PAPER_FORM_DETAIL t, t_base_user bu ")
//		.append("         where t.USER_ID = bu.USER_ID ")
//		.append("         group by  bu.department_id) t, ")
//		.append("       t_base_department bd ")
//		.append(" where t.department_id = bd.department_id ")
//		.append(") t1 left join ( ")
//		.append("select t.*, round((very_satisfy+satisfy*0.9+normal*0.6+no_satisfy*0.4)*100/(very_satisfy+satisfy+normal+no_satisfy+very_no_satisfy),2) satisfy_degree from (")
//		.append("select t.department_id, ")
//		.append("       sum(decode(t.satisfied_type, '1', 1, 0)) very_satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '2', 1, 0)) satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '3', 1, 0)) normal, ")
//		.append("       sum(decode(t.satisfied_type, '4', 1, 0)) no_satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '5', 1, 0)) very_no_satisfy, ")
//		.append("       sum((case when t.satisfied_type='1' and 1=0 then 1 else 0 end)) goodLocation ")
//		.append(" from t_services_workform t where t.satisfied_type is not null group by t.department_id ")
//		.append(" ) t")
//		.append(" ) t2 on t1.department_id=t2.department_id ");
//		if(StringUtils.isNotEmpty(bean.getDepartmentIds())){
//			sql.append(" where t1.department_id in ("+bean.getDepartmentIds()+") ");
//		}
//		
//		return findMapResultBySql(sql.toString());
//	}
//	
//	public List<Map> searchProSatisfyReportList(PaperWorkFormBean bean,
//			Page queryPage) {
//		StringBuffer sql = new StringBuffer();
//		sql
//		.append("select t.*, round((very_satisfy+satisfy*0.9+normal*0.6+no_satisfy*0.4)*100/(very_satisfy+satisfy+normal+no_satisfy+very_no_satisfy),2) satisfy_degree,d.DEPARTMENT_NAME,d.DEPARTMENT_NAME2,d.DEPARTMENT_NAME1 from (")
//		.append("select t.department_id, ")
//		.append("       sum(decode(t.satisfied_type, '1', 1, 0)) very_satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '2', 1, 0)) satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '3', 1, 0)) normal, ")
//		.append("       sum(decode(t.satisfied_type, '4', 1, 0)) no_satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '5', 1, 0)) very_no_satisfy, ")
//		.append("       sum((case when t.satisfied_type='1' and 1=0 then 1 else 0 end)) goodLocation ")
//		.append(" from t_services_workform t where t.satisfied_type is not null ");
//		if(StringUtils.isNotEmpty(bean.getCreateDateStart())){
//			sql.append(" and to_char(t.work_start_date,'yyyy-MM-dd')>='"+bean.getCreateDateStart()+"'");
//		}
//		if(StringUtils.isNotEmpty(bean.getCreateDateEnd())){
//			sql.append(" and to_char(t.work_start_date,'yyyy-MM-dd')<='"+bean.getCreateDateEnd()+"'");
//		}
//		if(StringUtils.isNotEmpty(bean.getFinishDateStart())){
//			sql.append(" and to_char(t.work_finish_date,'yyyy-MM-dd')>='"+bean.getFinishDateStart()+"'");
//		}
//		if(StringUtils.isNotEmpty(bean.getFinishDateEnd())){
//			sql.append(" and to_char(t.work_finish_date,'yyyy-MM-dd')<='"+bean.getFinishDateEnd()+"'");
//		}
//		if(StringUtils.isNotEmpty(bean.getLastDateStart())){
//			sql.append(" and to_char(t.last_audit_date,'yyyy-MM-dd')>='"+bean.getLastDateStart()+"'");
//		}
//		if(StringUtils.isNotEmpty(bean.getLastDateEnd())){
//			sql.append(" and to_char(t.last_audit_date,'yyyy-MM-dd')<='"+bean.getLastDateEnd()+"'");
//		}
//		sql.append(" group by t.department_id ")
//		.append(" ) t left join ( SELECT c.department_id,c.department_name DEPARTMENT_NAME,d.department_name DEPARTMENT_NAME2,b.department_name DEPARTMENT_NAME1 " +
//				"FROM t_base_department c left join t_base_department d on c.parent_id=d.department_id" +
//				"     left join t_base_department b on d.parent_id=b.department_id " +
//				"where c.department_type='C' ) d on t.department_id=d.department_id ");
//		
//		
//		
//		if(StringUtils.isNotEmpty(bean.getDepartmentIds())){
//		   //sql.append(" where t.department_id in ("+bean.getDepartmentIds()+") ");
//			String[] deStrings=bean.getDepartmentIds().split(",");
//			String dString="";
//			for(int i=801;i<deStrings.length;i++){
//				dString+=deStrings[i]+",";
//				if(i==deStrings.length-1){
//					sql.append(" where (t.department_id in ("+dString.substring(0,dString.length()-1)+")) ");
//					dString="";
//				}
//				if(i==600){
//					//sql.append(" or (t.department_id in ("+dString.substring(0,dString.length()-1)+")) ");
//					dString="";
//				}
//			}
//			//sql.append(" or (t.department_id in ("+dString.substring(0,dString.length()-1)+")) ");
//		}
//		sql.append(" order by d.DEPARTMENT_NAME1,d.DEPARTMENT_NAME2 ");
//		return findMapResultBySql(sql.toString(), queryPage);
//	}
//
//	public List<Map> searchCusSatisfyReportList(PaperWorkFormBean bean,
//			Page queryPage) {
//		StringBuffer customerSQL = new StringBuffer();
//		customerSQL.append("select id,name1,name2,name3 from ( ")
//				.append("select t1.id,t1.customer_name name1,'' name2,'' name3 ")
//				.append("from mv_crm_customer_info t1  ")
//				.append("where t1.customer_level_temp in ('1') ")
//				.append("union ")
//				.append("select t2.id,t1.customer_name name1,t2.customer_name name2,'' name3 ")
//				.append("from mv_crm_customer_info t1 right join mv_crm_customer_info t2 on t1.id=t2.parent_custimer_id ")
//				.append("where t2.customer_level_temp in ('2') ")
//				.append("union ")
//				.append("select t3.id,t1.customer_name name1,t2.customer_name name2,t3.customer_name name3 ")
//				.append("from mv_crm_customer_info t1 right join mv_crm_customer_info t2 on t1.id=t2.parent_custimer_id ")
//				.append("     right join mv_crm_customer_info t3 on t2.id=t3.parent_custimer_id ")
//				.append("where t3.customer_level_temp in ('3') ")
//				.append("union ")
//				.append("select t4.id,t1.customer_name name1,t2.customer_name name2,t3.customer_name name3 ")
//				.append("from mv_crm_customer_info t1 right join mv_crm_customer_info t2 on t1.id=t2.parent_custimer_id ")
//				.append("     right join mv_crm_customer_info t3 on t2.id=t3.parent_custimer_id ")
//				.append("     right join mv_crm_customer_info t4 on t3.id=t4.parent_custimer_id ")
//				.append("where t4.customer_level_temp in ('4') ")
//				.append("union ")
//				.append("select t5.id,t1.customer_name name1,t2.customer_name name2,t3.customer_name name3 ")
//				.append("from mv_crm_customer_info t1 right join mv_crm_customer_info t2 on t1.id=t2.parent_custimer_id ")
//				.append("     right join mv_crm_customer_info t3 on t2.id=t3.parent_custimer_id ")
//				.append("     right join mv_crm_customer_info t4 on t3.id=t4.parent_custimer_id ")
//				.append("     right join mv_crm_customer_info t5 on t4.id=t5.parent_custimer_id ")
//				.append("where t5.customer_level_temp in ('5') ")
//				.append(")  ")
//				.append("order by name1,name2,name3 ");
//		
//		
//		StringBuffer sql = new StringBuffer();
//		sql
//		.append("select t.*, round((very_satisfy+satisfy*0.9+normal*0.6+no_satisfy*0.4)*100/(very_satisfy+satisfy+normal+no_satisfy+very_no_satisfy),2) satisfy_degree from (")
//		.append("select c.id,name1,name2,name3, ")
//		.append("       sum(decode(t.satisfied_type, '1', 1, 0)) very_satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '2', 1, 0)) satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '3', 1, 0)) normal, ")
//		.append("       sum(decode(t.satisfied_type, '4', 1, 0)) no_satisfy, ")
//		.append("       sum(decode(t.satisfied_type, '5', 1, 0)) very_no_satisfy, ")
//		.append("       sum((case when t.satisfied_type='1' and 1=0 then 1 else 0 end)) goodLocation ")
//		.append("		from t_services_workform t ")
//		.append("		left join v_ems_equipment_info e on t.equipment_id=e.equipment_id ")
//		.append("		left join ("+customerSQL.toString()+") c on e.customer_id=c.id ")
//		.append("		where t.satisfied_type is not null ");
//		if(StringUtils.isNotEmpty(bean.getCreateDateStart())){
//			sql.append(" and to_char(t.work_start_date,'yyyy-MM-dd')>='"+bean.getCreateDateStart()+"'");
//		}
//		if(StringUtils.isNotEmpty(bean.getCreateDateEnd())){
//			sql.append(" and to_char(t.work_start_date,'yyyy-MM-dd')<='"+bean.getCreateDateEnd()+"'");
//		}
//		if(StringUtils.isNotEmpty(bean.getFinishDateStart())){
//			sql.append(" and to_char(t.work_finish_date,'yyyy-MM-dd')>='"+bean.getFinishDateStart()+"'");
//		}
//		if(StringUtils.isNotEmpty(bean.getFinishDateEnd())){
//			sql.append(" and to_char(t.work_finish_date,'yyyy-MM-dd')<='"+bean.getFinishDateEnd()+"'");
//		}
//		if(StringUtils.isNotEmpty(bean.getLastDateStart())){
//			sql.append(" and to_char(t.last_audit_date,'yyyy-MM-dd')>='"+bean.getLastDateStart()+"'");
//		}
//		if(StringUtils.isNotEmpty(bean.getLastDateEnd())){
//			sql.append(" and to_char(t.last_audit_date,'yyyy-MM-dd')<='"+bean.getLastDateEnd()+"'");
//		}
//		if(StringUtils.isNotEmpty(bean.getCustomerIds())){
//			sql.append(" and c.id in ("+bean.getCustomerIds()+") ");
//		}
//		if(StringUtils.isNotEmpty(bean.getDepartmentIds())){
//			sql.append(" and t.department_id in ("+bean.getDepartmentIds()+") ");
//		}
//		sql.append(" group by c.id,name1,name2,name3 ) t order by t.name1,t.name2,t.name3 ");
//		return findMapResultBySql(sql.toString(), queryPage);
//	}
//
//	public List<PaperWorkFormDetail> findByAbandonedId(String abandonedWorkflowId) {
//		if(StringUtilsExtends.isBlankOrEmpty(abandonedWorkflowId)) {
//			return new ArrayList<PaperWorkFormDetail>();
//		}
//		HqlHelper hqlhelper = new HqlHelper(PaperWorkFormDetail.class);
//		hqlhelper.addEqualIgnoreCase("paperWorkFormAbandoneId", abandonedWorkflowId);
//		return find(hqlhelper);
//	}
//	
}
