package com.grgbanking.css.dao.vequipment.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.vequipment.VEquipmentInfo;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.vequipment.VEquipmentInfoDao;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.HqlHelper;


@Repository("vEquipmentInfoDao")
public class VEquipmentInfoDaoImpl extends CssBaseDAOImpl<VEquipmentInfo, Integer> implements
		VEquipmentInfoDao<VEquipmentInfo, Integer> {

	public VEquipmentInfo getEquipmentBySerialNumber(String serialNumber) {
		if(StringUtils.isEmpty(serialNumber)){
			return null;
		}
		HqlHelper hqlHelper=new HqlHelper(VEquipmentInfo.class);
		hqlHelper.addEqual("serialNumber", serialNumber.trim());
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		List<VEquipmentInfo> list=this.find(hqlHelper);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

//	public List<VEquipmentInfo> queryEquipmentByBranchName(String branchName, Page queryPage,Integer customerId) {
//		String sql = "select e.* from v_ems_equipment_info e  where 1=1 ";
//		//插入同行的逻辑
//		if(null!=customerId){
//			sql+=" and e.customer_id in ("
//				+" select a.id from mv_crm_customer_info a start with a.id = "+customerId+" connect by prior a.parent_custimer_id = a.id "
//				+" union all"
//				+" select a.id from mv_crm_customer_info a start with a.id = "+customerId+" connect by prior a.id = a.parent_custimer_id "
//				+") ";
//		}
//		sql+=" and e.branch_name='"+branchName+"' ";
//		if(queryPage == null){
//			sql += " and rownum<=3";
//		}
//		return this.findEntityListBySQL(sql, VEquipmentInfo.class, queryPage);
//	}
//	
//	public List<Object[]> findEquipmentForRevisit() {
//		String sql = "select i.equipment_id," +
//					 "i.equipment_status," +
//					 "i.equipment_model," +
//					 "i.department_id," +
//					 "i.install_date," +
//					 "i.branch_name," +
//					 "i.manufacturer," +
//					 "i.warranty_status," +
//					 "i.atm_manager_tel," +
//					 "i.atm_manager," +
//					 "i.customer_id,i.equipment_type,i.serial_number,i.province_id from v_ems_equipment_info i ";
//		return this.findBySQL(sql, null,null);
//	}
//
//	public List<VEquipmentInfo> getEquipmentListByCustomerIdAndDeptId(
//			Integer customerId, Integer deptId) {
//		HqlHelper hqleHelper=new HqlHelper(VEquipmentInfo.class);
//		hqleHelper.addEqual("departmentId", deptId);
//		hqleHelper.addEqual("customerId", customerId);
//		hqleHelper.addIn("equipmentStatus", new String[]{EquipmentConstants.EQUIPMENT_STATUS_USE,EquipmentConstants.EQUIPMENT_STATUS_WAIT});
//		return this.find(hqleHelper);
//	}
//
//	public List<Object[]> queryEquipmentSnapshotCountGroupbyStatus(
//			Integer departmentId, String datestr) {
//		String sql="select t.equipment_status,sum(t.quantity) from t_snapshot_equipment t where to_char(t.create_date,'yyyy-MM-dd')=? and t.department_id in ("+StringUtils.join(this.getChildDepartmentId(departmentId),",")+") "
//		+" and t.equipment_status in ('"+EquipmentConstants.EQUIPMENT_STATUS_USE+"','"+EquipmentConstants.EQUIPMENT_STATUS_WAIT+"','"+EquipmentConstants.EQUIPMENT_STATUS_DISABLE+"') "
//		+" group by t.equipment_status ";
//		return this.findBySQL(sql, datestr);
//	}
//	
//	public List<Object[]> getEquipmentByWX(String mobliePhone,
//			String serialNumber, String branchName, String departmentName,Page queryPage) {
//		String sql="select t.equipment_id,t.serial_number,t.branch_name,t.equipment_model,"+
//       " decode(t.install_type, 1, '穿墙', 2, '大堂') install_type,a.department_name,"+
//       " decode(t.install_model,1,'在行',2,'离行',3,'现金清分中心') install_model,t.department_id " +
//       " from v_ems_equipment_info t,t_base_department a" +
//       " where t.department_id = a.department_id  " ;
//      
//		   if(StringUtils.isNotEmpty(mobliePhone)){
//				sql += " and exists (select 1 from v_ems_equipment_contact b where b.equipment_id = t.equipment_id and b.deleted='N' and (b.moblie_phone = '"+mobliePhone+"' or b.telephone = '"+mobliePhone+"')) " ;
//		   }
//	       if(StringUtils.isNotEmpty(serialNumber)){
//	    	   sql += " and t.serial_number='"+serialNumber+"' " ;
//	       }
//	       if(StringUtils.isNotEmpty(branchName)){
//	    	   sql += " and t.branch_name like '%"+branchName+"%' " ;
//	       }
//	       if(StringUtils.isNotEmpty(departmentName)){
//	    	   sql += " and a.department_name like '%"+departmentName+"%'";
//	       }
//	       
//		return this.findBySQL(sql,queryPage,null);
//	}
//	
//	public JSONObject getEquipmentStatusById(String equipmentId) {
//		JSONObject json = new JSONObject();
//		json.element("eptStatus", "正常");
//		String sql = "select text,po_number from (select d.text, a.po_number, t.equipment_id, a.create_date  "
//				+ " from t_services_task t,t_services_workform a,t_base_data_dictionary d"
//				+ " where t.workform_id = a.workform_id and t.task_type = d.value "
//				+ " and d.key = 'SERVICE_TASK_TYPE' and t.deleted = 'N' and t.status not in ('已完成', '已取消') " + " and t.equipment_id = ? "
//				+ " order by a.workform_id desc )where rownum < 2 ";
//		List<Object[]> list = this.findBySQL(sql, equipmentId);
//		if (null != list && list.size() > 0) {
//			json.element("eptStatus", list.get(0)[0]);
//			json.element("poNumber", list.get(0)[1]);
//		}
//		return json;
//	}
//
//	public List<VEquipmentInfo> queryEquipmentByAtmNumber(String atmNumber) {
//		HqlHelper hqlHelper=new HqlHelper(VEquipmentInfo.class);
//		hqlHelper.addEqual("atmNumber", atmNumber);
//		hqlHelper.addEqual("deleted", "N");
//		return this.find(hqlHelper);
//	}
//	
//	
//	public List<VEquipmentInfo> queryEquipmentByAtmNumberLikeCustomerCode(String atmNumber, String customerCode) {
//		HqlHelper hqlHelper=new HqlHelper(VEquipmentInfo.class);
//		hqlHelper.addEqual("atmNumber", atmNumber);
//		hqlHelper.addLike("customer.customerCode", customerCode);
//		hqlHelper.addEqual("deleted", "N");
//		return this.find(hqlHelper);
//	}
//
//	public VEquipmentInfo getEquipmentBySerialNumberDepartmentName(String serialNumber, Integer departmentId) {
//		HqlHelper hqlHelper=new HqlHelper(VEquipmentInfo.class);
//		hqlHelper.addEqual("serialNumber", serialNumber);
//		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
//		if(departmentId!=null){
//			hqlHelper.addIn("department.departmentId",this.getChildDepartmentId(departmentId));
//		}
//		List<VEquipmentInfo> list=this.find(hqlHelper);
//		if(list.size()>0 && list!=null){
//			return list.get(0);
//		}
//		return null;
//	}
//
//	public void deleteEquipmentBYtask(Integer equipmentId, String status, String remark) {
//		//2013-6-19去除深度保养t.upgrade_code is null
//		//13-8-15增加删除日志
//		String dsql="insert into t_services_task_by_log t(t.by_log_id,t.equipment_id,t.task_id,t.process_no,t.plan_start_date,t.plan_end_date,t.create_date,t.operate_status,t.operate_remark) " +
//				" select SEQ_SERVICES_TASK_BY_LOG.NEXTVAL,s.equipment_id,s.task_id,s.process_no,s.plan_start_date,s.plan_end_date,sysdate,'delete','"+remark+"' " +
//				" from t_services_task s where s.equipment_id="+equipmentId+" and s.status='"+status+"' and s.task_type='BY' and s.upgrade_code is null ";
//		this.executeSql(dsql);
//		String sql="delete from t_services_task t where t.task_type='BY' and t.upgrade_code is null and t.status='"+status+"' and t.equipment_id="+equipmentId;
//		this.executeSql(sql);
//	}
//
//	public List<VEquipmentInfo> queryEquipmentBySerialNumberAtmNumber(
//			String serialNumber, String atmNumber) {
//		HqlHelper hqlHelper=new HqlHelper(VEquipmentInfo.class);
//		hqlHelper.addEqual("atmNumber", atmNumber);
//		hqlHelper.addLike("serialNumber", serialNumber);
//		hqlHelper.addEqual("deleted", "N");
//		return this.find(hqlHelper);
//	}
//
//	@Override
//	public Integer getSumEquipmentStatus(String equipmentStatus,
//			Integer departmentId, String departmentType, String manufacturer) {
//		String hql;
//		List<Integer> departlist = null;
//		if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
//			if(equipmentStatus==null){
//				hql="select count(*) from VEquipmentInfo where 1=1";
//			}else{
//				hql="select count(*) from VEquipmentInfo where equipmentStatus='"+equipmentStatus+"'";
//			}
//		}else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
//			if(equipmentStatus==null){
//				hql="select count(*) from VEquipmentInfo where department.departmentId="+departmentId;
//			}else{
//				hql="select count(*) from VEquipmentInfo where equipmentStatus='"+equipmentStatus+"' and department.departmentId="+departmentId;
//			}
//		}
//		else{
//			departlist = this.getChildDepartmentId(departmentId);
//			if(equipmentStatus==null){
//				hql="select count(*) from VEquipmentInfo where department.departmentId in ("+StringUtils.join(departlist,",") +")";
//			}else{
//				hql="select count(*) from VEquipmentInfo where equipmentStatus='"+equipmentStatus+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
//			}
//		}
//		if(manufacturer!=null){
//			hql+=" and manufacturer='"+manufacturer+"' ";
//		}
//		return this.countByHQL(hql);
//	}
//
//	@Override
//	public Integer getSumEquipmentType(String equipmentType,
//			Integer departmentId, String departmentType, String manufacturer) {
//		String hql;
//		List<Integer> departlist = null;
//		if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
//			hql="select count(*) from VEquipmentInfo where equipmentType='"+equipmentType+"'";
//		}else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
//			hql="select count(*) from VEquipmentInfo where equipmentType='"+equipmentType+"' and department.departmentId="+departmentId;
//		}else{
//			departlist = this.getChildDepartmentId(departmentId);
//			hql="select count(*) from VEquipmentInfo where equipmentType='"+equipmentType+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
//		}
//		if(manufacturer!=null){
//			hql+=" and manufacturer='"+manufacturer+"' ";
//		}
//		return this.countByHQL(hql);
//	}
//
//	@Override
//	public Integer getSumWarrantyStatus(String warrantyStatus,
//			Integer departmentId, String departmentType, String manufacturer) {
//		String hql;
//		List<Integer> departlist = null;
//		if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
//			hql="select count(*) from VEquipmentInfo where warrantyStatus='"+warrantyStatus+"'";
//		}else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
//			hql="select count(*) from VEquipmentInfo where warrantyStatus='"+warrantyStatus+"' and department.departmentId="+departmentId;
//		}else{
//			departlist = this.getChildDepartmentId(departmentId);
//			hql="select count(*) from VEquipmentInfo where warrantyStatus='"+warrantyStatus+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
//		}
//		if(manufacturer!=null){
//			hql+=" and manufacturer='"+manufacturer+"' ";
//		}
//		return this.countByHQL(hql);
//	}
//
//	@Override
//	public Integer getSumInstallDate(String installDate, Integer departmentId,
//			String departmentType, String manufacturer) {
//		Date currentTime = new Date();
//	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//	    String dateString = formatter.format(currentTime);
//	    Integer dateInt=Integer.parseInt(dateString.substring(0, 4));
//	    String hql;
//	    if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
//	    	if(installDate=="1"){
//				String dateStart=(dateInt-1)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"'";
//			}else if(installDate=="2"){
//				String dateStart=(dateInt-2)+dateString.substring(4,10);
//				String dateEnd=(dateInt-1)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"'";
//			}else if(installDate=="3"){
//				String dateStart=(dateInt-3)+dateString.substring(4,10);
//				String dateEnd=(dateInt-2)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"'";
//			}else if(installDate=="4"){
//				String dateStart=(dateInt-4)+dateString.substring(4,10);
//				String dateEnd=(dateInt-3)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"'";
//			}
//			else{
//				String dateStart=(dateInt-4)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')<'"+dateStart+"'";
//			}
//	    }else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
//	    	if(installDate=="1"){
//				String dateStart=(dateInt-1)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and department.departmentId="+departmentId;
//			}else if(installDate=="2"){
//				String dateStart=(dateInt-2)+dateString.substring(4,10);
//				String dateEnd=(dateInt-1)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId="+departmentId;
//			}else if(installDate=="3"){
//				String dateStart=(dateInt-3)+dateString.substring(4,10);
//				String dateEnd=(dateInt-2)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId="+departmentId;
//			}else if(installDate=="4"){
//				String dateStart=(dateInt-4)+dateString.substring(4,10);
//				String dateEnd=(dateInt-3)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId="+departmentId;
//			}
//			else{
//				String dateStart=(dateInt-4)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')<'"+dateStart+"' and department.departmentId="+departmentId;
//			}
//	    }else{
//	    	List<Integer> departlist = null;
//	    	if(installDate=="1"){
//	    		departlist = this.getChildDepartmentId(departmentId);
//				String dateStart=(dateInt-1)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
//			}else if(installDate=="2"){
//				departlist = this.getChildDepartmentId(departmentId);
//				String dateStart=(dateInt-2)+dateString.substring(4,10);
//				String dateEnd=(dateInt-1)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
//			}else if(installDate=="3"){
//				departlist = this.getChildDepartmentId(departmentId);
//				String dateStart=(dateInt-3)+dateString.substring(4,10);
//				String dateEnd=(dateInt-2)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId in("+StringUtils.join(departlist,",") +")";
//			}
//			else if(installDate=="4"){
//				departlist = this.getChildDepartmentId(departmentId);
//				String dateStart=(dateInt-4)+dateString.substring(4,10);
//				String dateEnd=(dateInt-3)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId in("+StringUtils.join(departlist,",") +")";
//			}
//			else{
//				departlist = this.getChildDepartmentId(departmentId);
//				String dateStart=(dateInt-4)+dateString.substring(4,10);
//				hql="select count(*) from VEquipmentInfo where to_char(installDate,'yyyy-MM-dd')<'"+dateStart+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
//			}
//	    }
//	    
//	    if(manufacturer!=null){
//			hql+=" and manufacturer='"+manufacturer+"' ";
//		}
//	    
//		return this.countByHQL(hql);
//	}
//	
//	public Integer getSumEquipmentStatusNoManufacturer(String equipmentStatus,
//			Integer departmentId, String departmentType, String manufacturer) {
//		String hql;
//		List<Integer> departlist = null;
//		if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
//			if(equipmentStatus==null){
//				hql="select count(*) from VEquipmentInfo where 1=1";
//			}else{
//				hql="select count(*) from VEquipmentInfo where equipmentStatus='"+equipmentStatus+"'";
//			}
//		}else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
//			if(equipmentStatus==null){
//				hql="select count(*) from VEquipmentInfo where department.departmentId="+departmentId;
//			}else{
//				hql="select count(*) from VEquipmentInfo where equipmentStatus='"+equipmentStatus+"' and department.departmentId="+departmentId;
//			}
//		}
//		else{
//			departlist = this.getChildDepartmentId(departmentId);
//			if(equipmentStatus==null){
//				hql="select count(*) from VEquipmentInfo where department.departmentId in ("+StringUtils.join(departlist,",") +")";
//			}else{
//				hql="select count(*) from VEquipmentInfo where equipmentStatus='"+equipmentStatus+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
//			}
//		}
//		if(manufacturer!=null){
//			hql+=" and manufacturer!='"+manufacturer+"' ";
//		}
//		return this.countByHQL(hql);
//	}
//	
//	public Integer getSumEquipmentTypeNoManufacturer(String equipmentType,
//			Integer departmentId, String departmentType, String manufacturer) {
//		String hql;
//		List<Integer> departlist = null;
//		if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
//			hql="select count(*) from VEquipmentInfo where equipmentType='"+equipmentType+"'";
//		}else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
//			hql="select count(*) from VEquipmentInfo where equipmentType='"+equipmentType+"' and department.departmentId="+departmentId;
//		}else{
//			departlist = this.getChildDepartmentId(departmentId);
//			hql="select count(*) from VEquipmentInfo where equipmentType='"+equipmentType+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
//		}
//		if(manufacturer!=null){
//			hql+=" and manufacturer!='"+manufacturer+"' ";
//		}
//		return this.countByHQL(hql);
//	}
}
