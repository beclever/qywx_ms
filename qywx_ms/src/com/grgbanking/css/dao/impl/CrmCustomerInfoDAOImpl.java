package com.grgbanking.css.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.grgbanking.common.utils.ArrayTransitionUtils;
import com.grgbanking.common.utils.DateUtil;
import com.grgbanking.common.utils.ListUtils;
import com.grgbanking.common.utils.StringUtilsExtends;
import com.grgbanking.css.bean.CrmCustomer;
import com.grgbanking.css.bean.CustomerBean;
import com.grgbanking.css.bean.equipment.EquipmentInfo;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.common.UserContext;
import com.grgbanking.css.dao.CrmCustomerInfoDAO;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.HqlHelper;

@Repository("crmCustomerInfoDAO")
public class CrmCustomerInfoDAOImpl extends CssBaseDAOImpl<CrmCustomer,Integer> implements
		CrmCustomerInfoDAO<CrmCustomer,Integer> {

	public List<CrmCustomer> getAllCustomer() {
		HqlHelper hqlHelper=new HqlHelper(CrmCustomer.class);
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
		return this.find(hqlHelper);
	}
	
	public List<Integer> getCrmCustomerIds(String customerName) {
		List<Integer> customerIdList=new ArrayList<Integer>();
		String sql="select t.id from v_crm_customer_info t where t.customer_name like '%"+ customerName +"%'";
		List list=this.findBySQL(sql);
		for (Object obj : list) {
			customerIdList.add(Integer.valueOf(obj.toString()));
		}
		return customerIdList;
	}

	public List<CrmCustomer> getCustomerByDepartment(List<Integer> departmentIdList) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addIn("department.departmentId", departmentIdList);
		hqlHelper.addEqual("customer.deleted", CommonConstants.FLAG_N);
		return this.find("select distinct a.customer "+hqlHelper.getHQL(),hqlHelper.getParams().toArray());
	}

	public List<Object[]> getCustomerByRegionAndProvince(Integer region,
			Integer province) {
		String sql = "select t.id,t.customer_name,t.parent_custimer_id from v_crm_customer_info t ";
		if(null != region || null != province ){
			sql += " start with t.deleted='N' ";
			if(null != region) sql += " and t.sub_company= "+region;
			if(null != province) sql += " and t.province_name ="+province;
			sql += " connect by prior t.parent_custimer_id=t.id ";
			sql += " group by t.id,t.customer_name,t.parent_custimer_id having count(*)>=1";
		}
		
		return this.findBySQL(sql);
	}

	public CrmCustomer getCustomerByCode(String customerCode) {
		HqlHelper hqlHelper=new HqlHelper(CrmCustomer.class);
		hqlHelper.addEqualIgnoreCase("customerCode",customerCode);
		List<CrmCustomer> list=this.find(hqlHelper);
		if(list.size()>0){
			return list.get(0);
		}
		return null; 
	}

	public CrmCustomer getUppeCustomerById(Integer custometId,
			String customerLevel) {
		String sql="select t.id,t.customer_name,t.parent_custimer_id,t.customer_level_temp from v_crm_customer_info t " +
				" where t.customer_level_temp='"+customerLevel+"' start with t.id="+custometId+" connect by prior t.parent_custimer_id=t.id ";
		List<Object[]> list=this.findBySQL(sql);
		if(list!=null && list.size()>0){
			CrmCustomer customer=new CrmCustomer();
			Object[] ob=list.get(0);
			customer.setCustomerName(ob[1].toString());
			customer.setCustomerId(Integer.parseInt(ob[0].toString()));
			return customer;
		}
		return null;
	}

	public String getCurstomerNames(String customerIds) {
		if(StringUtilsExtends.isBlankOrEmpty(customerIds))
			return null;
		HqlHelper hqlHelper = new HqlHelper(CrmCustomer.class);
		hqlHelper.addIn("customerId", ArrayTransitionUtils.stringToIntegerArray(customerIds, ","));
		List<CrmCustomer> list = find(hqlHelper);
		StringBuffer customerNames = new StringBuffer(); 
		if(ListUtils.isNotEmpty(list)){
			for (CrmCustomer customer : list) {
				customerNames.append(customer.getCustomerName());
				customerNames.append(",");
			}
		}
		return ArrayTransitionUtils.sliceOffSeparator(customerNames.toString());
	}

	public List<CrmCustomer> getCrmCustomerList(Page queryPage,CustomerBean customerBean) {
		HqlHelper hqlHelper=new HqlHelper(CrmCustomer.class);
		hqlHelper.addLikeIgnoreCase("customerName",customerBean.getCustomerName());
		hqlHelper.addLikeIgnoreCase("customerCode", customerBean.getCustomerCode());
		hqlHelper.addEqual("customerType",customerBean.getCustomerType());
		hqlHelper.addEqual("province.provinceId", customerBean.getProvince());
		hqlHelper.addEqual("subCompany.departmentId",customerBean.getRegion());
		//hqlHelper.addEqual("customerProperty", crmCustomerBean.getCustomerProperty());
		//hqlHelper.addEqual("customerBelong", crmCustomerBean.getCustomerBelong());
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
		hqlHelper.setQueryPage(queryPage);
		return this.find(hqlHelper);
	}

	public List<Object[]> getCustomerListForRevisit(Page queryPage,
			CustomerBean customerBean) {
		String sql="select t.id,t.customer_name,t.customer_type,t.sub_company,t.province_name,r.last_revisit_time,r.has_revisit " +
				" from v_crm_customer_info t " +
				" left join V_customer_revisit r on t.id=r.customer_id where t.deteled='"+CommonConstants.FLAG_N+"' ";
		if(StringUtils.isNotEmpty(customerBean.getCustomerName())){
			sql+=" and t.customer_name like '%"+customerBean.getCustomerName()+"%' ";
		}
		if(StringUtils.isNotEmpty(customerBean.getCustomerType())){
			sql+=" and t.customer_type='"+customerBean.getCustomerType()+"' ";
		}
		if(customerBean.getProvince()!=null){
			sql+=" and t.t.province_name='"+customerBean.getProvince()+"' ";
		}
		if(customerBean.getRegion()!=null){
			sql+=" and t.sub_company="+customerBean.getRegion()+" ";
		}
		if(StringUtils.isNotEmpty(customerBean.getHasRevisit())){
			sql+=" and r.has_revisit='"+customerBean.getHasRevisit()+"' ";
		}
		return this.findBySQL(sql,queryPage);
	}

	public List<Object[]> getWorkTaskObjectCustomerList(String upgradeCode,
			Integer parentCustomerId, String[] statusArray) {
		String sql="select distinct c.id,c.customer_name,c.parent_custimer_id from v_crm_customer_info c ";
		if(parentCustomerId!=null){
			if(parentCustomerId.intValue()==0){
				sql+=" where c.parent_custimer_id is null ";
			}else{
				sql+=" where c.parent_custimer_id="+parentCustomerId+" ";
			}
		}
		String str="";
		if(statusArray!=null){
			if(statusArray.length==1){
				str+=" ( e.equipment_status='"+statusArray[0]+"'";
			}else{
				str+=" ( e.equipment_status='"+statusArray[0]+"'";
				for(int i=1;i<statusArray.length;i++){
					str+=" or e.equipment_status='"+statusArray[i]+"'";
				}
			}
			str+=" ) and ";
		}
		
		String sqCus="  select distinct e.customer_id from V_services_task t " +
				" left join V_EMS_EQUIPMENT_INFO e on t.equipment_id=e.equipment_id " +
				" where "+str+" t.task_source='计划任务' and t.upgrade_code='"+upgradeCode+"' ";
		
		sql+=" start with c.id in("+sqCus+")  connect by prior  c.parent_custimer_id=c.id ";
		return this.findBySQL(sql);
	}

	public List<Object[]> getWorkTaskCustomerCountList(String upgradeCode,
			Integer customerId, String[] statusArray) {
		String str="";
		if(statusArray!=null){
			if(statusArray.length==1){
				str+=" ( e.equipment_status='"+statusArray[0]+"'";
			}else{
				str+=" ( e.equipment_status='"+statusArray[0]+"'";
				for(int i=1;i<statusArray.length;i++){
					str+=" or e.equipment_status='"+statusArray[i]+"'";
				}
			}
			str+=" ) and ";
		}
		String sql="select t.status,count(distinct t.equipment_id) from V_SERVICES_TASK t " +
				" left join V_EMS_EQUIPMENT_INFO e on t.equipment_id=e.equipment_id  where " +
				" "+str+"  t.task_source='计划任务' and t.upgrade_code='"+upgradeCode+"' " +
			    " and e.customer_id in (select c.id from v_crm_customer_info c start with c.id="+customerId+" " +
			    " connect by prior c.id=c.parent_custimer_id )group by t.status ";
		return this.findBySQL(sql);
	}

	public List<Object[]> getCustomerForReportUpper(String customerlevel,
			Integer upperCustomerId, String startDate, String endDate,Integer regionId) {
			String str="";
			if(regionId!=null){
				str+=" and t.sub_company="+regionId;
			}
			String sql="select distinct c.id,c.customer_name,c.parent_custimer_id,c.customer_level_temp,c.sub_company,c.province_name " 
				+" from v_crm_customer_info c where c.customer_level_temp='"+customerlevel+"'  ";
			if(upperCustomerId!=null){
				sql+=" and c.parent_custimer_id="+upperCustomerId+" ";
			}
			sql+=" start with c.id in( " 
				+"  select distinct t.id from v_crm_customer_info t where t.id in( " 
					+" select distinct customer_id from (select  qc.customer_id from V_quality_revisit_customer qc where to_char(qc.create_date,'yyyy-MM-dd') between '" + startDate + "' and '" + endDate + "'"
					+" union "
					+" select te.customer_id from V_quality_revisit_equipment qe left join V_EMS_EQUIPMENT_INFO te on te.equipment_id=qe.equipment_id  where to_char(qe.create_date,'yyyy-MM-dd') between '" + startDate + "' and '" + endDate + "'"
					+" union "
					+" select te.customer_id from V_quality_revisit_workform qw left join V_services_workform sw on sw.workform_id=qw.workform_id "
					+" left join V_EMS_EQUIPMENT_INFO te on te.equipment_id=sw.equipment_id  where to_char(qw.create_date,'yyyy-MM-dd') between '" + startDate + "' and '" + endDate + "' ))"
				+" "+str 
				+" ) connect by prior c.parent_custimer_id = c.id";
			return this.findBySQL(sql);
	}

	public List<Object[]> getCustomerForReportByCustomerId(Integer customerId,
			String startDate, String endDate) {
			String sql ="select t.id,t.customer_name,t.parent_custimer_id,t.customer_level_temp,t.sub_company,t.province_name "
				 +" from v_crm_customer_info t,v_crm_customer_info ci "
				 +" where t.parent_custimer_id=ci.id and t.id in "
				 +" ( "
				 +" select distinct customer_id from (select  qc.customer_id from V_quality_revisit_customer qc where to_char(qc.create_date,'yyyy-MM-dd') between '" + startDate + "' and '" + endDate + "'"
				 +" union "
				 +" select te.customer_id from V_quality_revisit_equipment qe left join V_EMS_EQUIPMENT_INFO te on te.equipment_id=qe.equipment_id  where to_char(qe.create_date,'yyyy-MM-dd') between '" + startDate + "' and '" + endDate + "'"
				 +" union "
				 +" select te.customer_id from V_quality_revisit_workform qw left join V_services_workform sw on sw.workform_id=qw.workform_id "
				 +" left join V_EMS_EQUIPMENT_INFO te on te.equipment_id=sw.equipment_id  where to_char(qw.create_date,'yyyy-MM-dd') between '" + startDate + "' and '" + endDate + "'"
				 +" ) "
				 +" ) "
				 +" and t.id="+customerId+"";
			return this.findBySQL(sql);
	}

	public List<Object[]> getCustomerForReport(Integer regionId) {
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);//-1才是上个月
		String dateStr=DateUtil.formatDate(calendar.getTime(), "yyyy-MM");//yyyy-MM,上个月
		String sql = "select t.id,t.customer_name,t.parent_custimer_id,ci.customer_name,t.customer_level_temp "
					 +" from v_crm_customer_info t left join v_crm_customer_info ci on t.parent_custimer_id=ci.id "
					 +" where  t.id in "
					 +" ( "
					 +" select distinct customer_id from (select  qc.customer_id from V_quality_revisit_customer qc where to_char(qc.create_date,'yyyy-MM')='"+dateStr+"' "
					 +" union "
					 +" select te.customer_id from V_quality_revisit_equipment qe left join V_EMS_EQUIPMENT_INFO te on te.equipment_id=qe.equipment_id  where to_char(qe.create_date,'yyyy-MM')='"+dateStr+"' "
					 +" union "
					 +" select te.customer_id from V_quality_revisit_workform qw left join V_services_workform sw on sw.workform_id=qw.workform_id "
					 +" left join V_EMS_EQUIPMENT_INFO te on te.equipment_id=sw.equipment_id  where to_char(qw.create_date,'yyyy-MM')='"+dateStr+"' "
					 +" ) "
					 +" ) "
					 +" and t.sub_company="+regionId+"";
		return this.findBySQL(sql);
	}

	public List<Object[]> getCustomerForReport2(Integer regionId,
			String startDate, String endDate) {
		String sql = "select t.id,t.customer_name,t.parent_custimer_id,ci.customer_name,t.customer_level_temp "
			 +" from v_crm_customer_info t,v_crm_customer_info ci "
			 +" where t.parent_custimer_id=ci.id and t.id in "
			 +" ( "
			 +" select distinct customer_id from (select  qc.customer_id from V_quality_revisit_customer qc where to_char(qc.create_date,'yyyy-MM-dd') between '" + startDate + "' and '" + endDate + "'"
			 +" union "
			 +" select te.customer_id from V_quality_revisit_equipment qe left join V_EMS_EQUIPMENT_INFO te on te.equipment_id=qe.equipment_id  where to_char(qe.create_date,'yyyy-MM-dd') between '" + startDate + "' and '" + endDate + "'"
			 +" union "
			 +" select te.customer_id from V_quality_revisit_workform qw left join V_services_workform sw on sw.workform_id=qw.workform_id "
			 +" left join V_EMS_EQUIPMENT_INFO te on te.equipment_id=sw.equipment_id  where to_char(qw.create_date,'yyyy-MM-dd') between '" + startDate + "' and '" + endDate + "'"
			 +" ) "
			 +" ) "
			 +" and t.sub_company="+regionId+"";
		return this.findBySQL(sql);
	}

	public List<CrmCustomer> getAllRootCustomer() {
		HqlHelper hqlHepler=new HqlHelper(CrmCustomer.class);
		hqlHepler.addIsNull("upperCustomer");
		return this.find(hqlHepler);
	}

	public List<Integer> getChildCustomer(Integer customerId,
			String customerLevel) {
		List<Integer> customerList=new ArrayList<Integer>();
		String sql="select t.id from v_crm_customer_info t where t.customer_level_temp= '"+ customerLevel +"' start with t.parent_custimer_id="+customerId
			+" connect by prior t.id=t.parent_custimer_id";
		List list=this.findBySQL(sql);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			BigDecimal obj = (BigDecimal) iterator.next();
			customerList.add(obj.intValue());
		}
		customerList.add(customerId);
		return customerList;
	}

	public List<Object[]> queryChildrenbyCustomerId(Integer customerId) {
		if(customerId==null||customerId.intValue()==0){
			return null;
		}
		//CrmCustomer customer=(CrmCustomer) this.get(customerId);
		String sql="";
		if("C".equals(UserContext.getUser().getDepartmentType())){
			sql="select * from(select t.id,t.customer_name from v_crm_customer_info t inner join ( select distinct e.customer_id from V_EMS_EQUIPMENT_INFO e " +
			" inner join (select d.department_id from V_base_department d start with d.department_id="+UserContext.getUser().getDepartmentId()+" connect by prior d.department_id=d.parent_id " +
			" ) dep on dep.department_id= e.department_id ) ed on t.id=ed.customer_id ) a " +
			" inner join (select * from v_crm_customer_info cc start with cc.parent_custimer_id="+customerId+" connect by prior cc.id=cc.parent_custimer_id ) b on a.id=b.id";
		}else{
			sql="select t.id,t.customer_name from v_crm_customer_info t start with t.parent_custimer_id="+customerId+" connect by prior t.id=t.parent_custimer_id";
		}
		return this.findBySQL(sql);
		/*List list = this.findBySQL(sql);
		if(list!=null&&list.size()>0){
			Object[] resultObj=new Object[]{customer.getCustomerId(),customer.getCustomerName()};
			list.add(resultObj);
			return list;
		}else{
			List<Object[]> resultList=new ArrayList<Object[]>();
			Object[] resultObj=new Object[]{customer.getCustomerId(),customer.getCustomerName()};
			resultList.add(resultObj);
			return resultList;
		}*/
	}

	public Integer[] getDepartmenidbyCustomerId(Integer[] sustomers) {
		String sql="select distinct t.department_id from V_EMS_EQUIPMENT_INFO t inner join (select c.* from v_crm_customer_info c " +
				" start with c.id in ("+StringUtils.join(sustomers,',')+") connect by prior c.id=c.parent_custimer_id ) d on t.customer_id=d.id ";
		List list=this.findBySQL(sql);
		if(list!=null && list.size()>0){
			Integer[] arr=new Integer[list.size()];
			for(int i=0;i<list.size();i++){
				arr[i]=Integer.parseInt(list.get(i).toString());
			}
			return arr;
		}
		return null;
	}

	@Override
	public List<CrmCustomer> getCustomerByCustomerName(
			List<Integer> departmentIdList, String name) {
		if(departmentIdList!=null&&departmentIdList.size()>0){
			HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
			hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
			hqlHelper.addIn("department.departmentId", departmentIdList);
			hqlHelper.addEqual("customer.deleted", CommonConstants.FLAG_N);
			if(StringUtils.isNotBlank(name)){
				hqlHelper.addLike("customer.customerName", name);
			}
			return this.find("select distinct a.customer "+hqlHelper.getHQL(),hqlHelper.getParams().toArray());
		}else{
			HqlHelper hqlHelper=new HqlHelper(CrmCustomer.class);
			hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
			if(StringUtils.isNotBlank(name)){
				hqlHelper.addLike("customerName", name);
			}
			return this.find(hqlHelper);
		}
	}

}
