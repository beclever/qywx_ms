package com.grgbanking.css.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.grgbanking.common.utils.ArrayTransitionUtils;
import com.grgbanking.common.utils.DateUtil;
import com.grgbanking.common.utils.ListUtils;
import com.grgbanking.common.utils.StringUtilsExtends;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.bean.Department;
import com.grgbanking.css.bean.UpgradePlanBean;
import com.grgbanking.css.bean.equipment.EquipmentBean;
import com.grgbanking.css.bean.equipment.EquipmentInfo;
import com.grgbanking.css.bean.equipment.EquipmentIntegrationBean;
import com.grgbanking.css.bean.equipment.EquipmentSearchPape;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.dao.CssUserDAO;
import com.grgbanking.css.dao.DepartmentDAO;
import com.grgbanking.css.dao.EquipmentDAO;
import com.grgbanking.css.exception.ServiceException;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.CommonUtils;
import com.grgbanking.css.util.EquipmentConstants;
import com.grgbanking.css.util.HqlHelper;
import com.grgbanking.css.util.ServicesConstants;


/**
 * 设备管理数据访问层
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-7-17 下午08:30:22
 */
@Repository("equipmentDAO")
public class EquipmentDAOImpl extends CssBaseDAOImpl<EquipmentInfo, Integer> implements
		EquipmentDAO<EquipmentInfo, Integer> {
	
	@Autowired
	private DepartmentDAO<Department, Integer> departmentDAO;
	@Autowired
	private CssUserDAO<CssUser, Integer> userDAO;

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#queryEquipmentList(com.grgbanking.framework.core.Page, com.grgbanking.soc.bean.equipment.EquipmentBean)
	 */
	public List<EquipmentInfo> queryEquipmentList(Page queryPage,EquipmentBean equipmentBean) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
		if(equipmentBean.getSerialNumber()!=null){
			String[] serialNumList =  equipmentBean.getSerialNumber().split(",");
			if(serialNumList.length>1){
				hqlHelper.addIn("serialNumber", serialNumList);//设备序列号
			}else{
				hqlHelper.addEqual("serialNumber", equipmentBean.getSerialNumber());
			}
		}else{
			hqlHelper.addEqual("serialNumber", null);
		}
		
		//客户多选
		if(StringUtils.isNotBlank(equipmentBean.getCustomerIds())){
			String[] customerIdArray =equipmentBean.getCustomerIds().split(",");
			List<Integer> customerIdList=new ArrayList<Integer>();
			for (String string : customerIdArray) {
				if(StringUtils.isNotBlank(string)){
					customerIdList.add(Integer.valueOf(string));
				}
			}
			hqlHelper.addIn("customer.customerId", customerIdList);//多选客户
		}
		hqlHelper.addEqual("customer.customerId", equipmentBean.getSingleCustomerId());//用于服务站管理视图查询功能
		if(StringUtils.isNotBlank(equipmentBean.getEquipmentModel())){
			hqlHelper.addIn("equipmentModel", equipmentBean.getEquipmentModel().split(","));//设备型号
		}
		hqlHelper.addLike("saleContractNo", equipmentBean.getSaleContractNo());//销售合同号
		hqlHelper.addIn("saleProperty",equipmentBean.getSaleProperty()!=null?equipmentBean.getSaleProperty().split(","):null);//销售属性:多个
		hqlHelper.addLikeIgnoreCase("branchName",equipmentBean.getBranchName());//网点
		hqlHelper.addLikeIgnoreCase("maintainContractNo", equipmentBean.getMaintainContractNo());//维保合同号
		hqlHelper.addIn("warrantyStatus", equipmentBean.getWarrantyStatus()!=null?equipmentBean.getWarrantyStatus().split(","):null);//维保状态
		hqlHelper.addEqual("installModel", equipmentBean.getInstallModel());//安装方式
		hqlHelper.addIn("equipmentStatus", equipmentBean.getEquipmentStatus()!=null?equipmentBean.getEquipmentStatus().split(","):null);//设备状态:多个
		hqlHelper.addDateBetween("shipDate", equipmentBean.getBeginShipDate(), equipmentBean.getEndShipDate());//发货日期
		hqlHelper.addDateBetween("installDate", equipmentBean.getBeginInstallDate(), equipmentBean.getEndInstallDate());//安装日期
		//hqlHelper.addEqual("equipmentType", equipmentBean.getEquipmentType());//整机类型
		if(StringUtils.isNotBlank(equipmentBean.getEquipmentType())){
			hqlHelper.addIn("equipmentType", equipmentBean.getEquipmentType().split(","));
		}
		hqlHelper.addEqual("atmcName", equipmentBean.getAtmcName());//ATMC名称
		//hqlHelper.addEqual("manufacturer", equipmentBean.getManufacturer());
		if(StringUtils.isNotBlank(equipmentBean.getManufacturer())){
			hqlHelper.addIn("manufacturer", equipmentBean.getManufacturer().split(","));
		}
		if(StringUtils.isNotBlank(equipmentBean.getEquipmentModel())){
			hqlHelper.addIn("equipmentModel", equipmentBean.getEquipmentModel().split(","));
		}
		hqlHelper.addDateBetween("warrantyEndDate", equipmentBean.getBeginWarrantyEndDate(), equipmentBean.getEndWarrantyEndDate());//到保日期
		hqlHelper.addEqual("provinceId", equipmentBean.getProvinceId());//安装省
		hqlHelper.addIn("installType",  equipmentBean.getInstallType()!=null?equipmentBean.getInstallType().split(","):null);   //安装方式 
		hqlHelper.addEqual("cityId", equipmentBean.getCityId());//安装城市
		hqlHelper.addLike("customer.customerTel", equipmentBean.getCustomerTel());//客户电话
		if(StringUtils.isNotBlank(equipmentBean.getDepartmentIds())){
			String[] ids = equipmentBean.getDepartmentIds().split(",");
			Integer[] departmentIds=new Integer[ids.length];
			for (int i = 0; i < ids.length; i++) {
				departmentIds[i]=Integer.valueOf(ids[i]);
			}
			hqlHelper.addIn("department.departmentId", departmentIds);//所属服务站
		}else if(equipmentBean.getDepartmentId()!=null){
			hqlHelper.addIn("department.departmentId", this.getChildDepartmentId(equipmentBean.getDepartmentId()));
		}
		//hqlHelper.addIn("department.departmentName", equipmentBean.getDepartmentName()!=null?equipmentBean.getDepartmentName().split(","):null);//所属服务站
		hqlHelper.addEqual("hasRevisit", equipmentBean.getHasRevisit());//是否回访
		//hqlHelper.addIn("customer.customerId", this.getAllCustomerByParentId(equipmentBean.getCustomerId()));//客户
		hqlHelper.addLike("installAddress", equipmentBean.getInstallAddress());//安装地址
		hqlHelper.addIn("acceptReportStatus",  equipmentBean.getAcceptReportStatus()!=null?equipmentBean.getAcceptReportStatus().split(","):null);//验收报告状态：多选
		hqlHelper.addEqual("provinceId", equipmentBean.getProvinceId());//省份
		hqlHelper.addEqual("cityId", equipmentBean.getCityId());//城市
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N );
		if(StringUtils.isNotBlank(equipmentBean.getEquipmentChargeName())){
			CssUser us=userDAO.getUserByChargeName(equipmentBean.getEquipmentChargeName());
			if(us!=null){
				hqlHelper.addEqual("equipmentCharge",us.getUserId());
			}else{
				hqlHelper.addEqual("equipmentCharge",111111);
			}
		}
		if(StringUtils.isNotEmpty(equipmentBean.getWarrantyDateStr())){
			if(equipmentBean.getWarrantyDateStr().equals("1")){
				hqlHelper.addIsNotNull("warrantyEndDate");
			}else if(equipmentBean.getWarrantyDateStr().equals("2")) {
				hqlHelper.addIsNull("warrantyEndDate");
			}
		}
		
		if(StringUtils.isNotBlank(equipmentBean.getAtmNumber())){
			String[] atmList =  equipmentBean.getAtmNumber().split(",");
			if(atmList.length>1){
				hqlHelper.addIn("atmNumber", atmList);//设备序列号
			}else{
				hqlHelper.addEqual("atmNumber", equipmentBean.getAtmNumber());
			}
		}
		
		hqlHelper.setQueryPage(queryPage);
		return this.find(hqlHelper);
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentBySerialNumber(java.lang.String)
	 */
	public EquipmentInfo getEquipmentBySerialNumber(String serialNumber) {
		if(StringUtils.isEmpty(serialNumber)){
			return null;
		}
		return (EquipmentInfo) this.findUniqueBy(EquipmentInfo.class, "serialNumber", serialNumber.trim());
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentByCustomerId(java.lang.Integer)
	 */
	public List getEquipmentByCustomerId(Integer id) {
		return  this.getHibernateTemplate().find("select e.equipmentType,e.equipmentModel,e.serialNumber from EquipmentInfo e where e.customer.customerId = "+id);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentInfo(java.lang.String)
	 */
	public EquipmentInfo getEquipmentInfo(String serialNumber) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
		hqlHelper.addEqual("serialNumber", serialNumber.trim());
		hqlHelper.addEqual("deleted", "N");
		List<EquipmentInfo> list=this.find(hqlHelper);
		if(list.size()>0){
			return (EquipmentInfo)list.get(0);
		}else{
			return null;
		}
	}
	
	
	public List<EquipmentInfo> getEquipmentList(UpgradePlanBean upgradePlan){
		HqlHelper hqlHelper = new HqlHelper(EquipmentInfo.class);
		hqlHelper.addIn("equipmentModel", upgradePlan.getEquipmentModels() == null ? null : upgradePlan.getEquipmentModels().split(","));
		hqlHelper.addIn("equipmentType", upgradePlan.getEquipmentTypes() == null ? null : upgradePlan.getEquipmentTypes().split(","));
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getDepartmentIds())){
			Integer[] departmentIds =  ArrayTransitionUtils.stringToIntegerArray(upgradePlan.getDepartmentIds(), ",");
			hqlHelper.addIn("department.departmentId", departmentIds); 
		}
			
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getDepartmentIds())){
			Integer[] customerIds =  ArrayTransitionUtils.stringToIntegerArray(upgradePlan.getDepartmentIds(), ",");
			hqlHelper.addIn("customer.customerId", customerIds); 
		}
		hqlHelper.addIn("serialNumber", upgradePlan.getSerialNumbers() == null ? null : upgradePlan.getSerialNumbers().split("\n"));
		hqlHelper.addEqual("manufacturer", upgradePlan.getManufacturer()); 
		hqlHelper.addEqual("batchNumber", upgradePlan.getEquipmentBatchNumber());
		hqlHelper.addGreatEqualThan("shipDate", upgradePlan.getShipDateStart());
		hqlHelper.addLessEqualThan("shipDate", upgradePlan.getShipDateEnd());
		hqlHelper.addIn("equipmentStatus", upgradePlan.getEquipmentStatus() == null ? null : CommonUtils.removeBlankSpace(upgradePlan.getEquipmentStatus()).split(","));
		hqlHelper.addIn("warrantyStatus", upgradePlan.getWarrantyStatus() == null ? null : CommonUtils.removeBlankSpace(upgradePlan.getWarrantyStatus()).split(","));
		int count=this.countByHQL("select count(*) "+hqlHelper.getHQL(), hqlHelper.getParams().toArray());
		if(count>20000){
			throw new ServiceException("已选中设备数量超过两万台，请分批升级！");
		}
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentByDepmentId(java.lang.Integer)
	 */
	public Integer countEquipmentByDepmentId(Integer id) {
		Department dep = this.departmentDAO.get(id);
		List<Integer> departlist = null;
		if (CommonConstants.DEPT_TYPE_REGION.equals(dep.getDepartmentType())) {
			departlist = this.getChildDepartmentId(id);
		} else if (CommonConstants.DEPT_TYPE_STATION.equals(dep.getDepartmentType())) {
			departlist = new ArrayList<Integer>();
			departlist.add(id);
		}
		String equpmentString = "";
		if (departlist!=null)
			equpmentString += " and e.department.departmentId in ("+ StringUtils.join(departlist,",") +")";
		return this.countByHQL("select count(*) from EquipmentInfo e where e.equipmentStatus='"+ EquipmentConstants.EQUIPMENT_STATUS_USE  +"'"+equpmentString); 
	}
	
	public Integer getSumEquipmentStatus(String equipmentStatus,Integer departmentId,String departmentType,String manufacturer){
		String hql;
		List<Integer> departlist = null;
		if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
			if(equipmentStatus==null){
				hql="select count(*) from EquipmentInfo where 1=1";
			}else{
				hql="select count(*) from EquipmentInfo where equipmentStatus='"+equipmentStatus+"'";
			}
		}else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
			if(equipmentStatus==null){
				hql="select count(*) from EquipmentInfo where department.departmentId="+departmentId;
			}else{
				hql="select count(*) from EquipmentInfo where equipmentStatus='"+equipmentStatus+"' and department.departmentId="+departmentId;
			}
		}
		else{
			departlist = this.getChildDepartmentId(departmentId);
			if(equipmentStatus==null){
				hql="select count(*) from EquipmentInfo where department.departmentId in ("+StringUtils.join(departlist,",") +")";
			}else{
				hql="select count(*) from EquipmentInfo where equipmentStatus='"+equipmentStatus+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
			}
		}
		if(manufacturer!=null){
			hql+=" and manufacturer='"+manufacturer+"' ";
		}
		return this.countByHQL(hql);
	}
	
	public Integer getSumEquipmentType(String equipmentType,Integer departmentId,String departmentType,String manufacturer){
		String hql;
		List<Integer> departlist = null;
		if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
			hql="select count(*) from EquipmentInfo where equipmentType='"+equipmentType+"'";
		}else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
			hql="select count(*) from EquipmentInfo where equipmentType='"+equipmentType+"' and department.departmentId="+departmentId;
		}else{
			departlist = this.getChildDepartmentId(departmentId);
			hql="select count(*) from EquipmentInfo where equipmentType='"+equipmentType+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
		}
		if(manufacturer!=null){
			hql+=" and manufacturer='"+manufacturer+"' ";
		}
		return this.countByHQL(hql);
	}
	
	public Integer getSumWarrantyStatus(String warrantyStatus,Integer departmentId,String departmentType,String manufacturer){
		String hql;
		List<Integer> departlist = null;
		if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
			hql="select count(*) from EquipmentInfo where warrantyStatus='"+warrantyStatus+"'";
		}else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
			hql="select count(*) from EquipmentInfo where warrantyStatus='"+warrantyStatus+"' and department.departmentId="+departmentId;
		}else{
			departlist = this.getChildDepartmentId(departmentId);
			hql="select count(*) from EquipmentInfo where warrantyStatus='"+warrantyStatus+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
		}
		if(manufacturer!=null){
			hql+=" and manufacturer='"+manufacturer+"' ";
		}
		return this.countByHQL(hql);
	}
	
	public Integer getSumInstallDate(String installDate,Integer departmentId,String departmentType,String manufacturer){
		Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    Integer dateInt=Integer.parseInt(dateString.substring(0, 4));
	    String hql;
	    if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
	    	if(installDate=="1"){
				String dateStart=(dateInt-1)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"'";
			}else if(installDate=="2"){
				String dateStart=(dateInt-2)+dateString.substring(4,10);
				String dateEnd=(dateInt-1)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"'";
			}else if(installDate=="3"){
				String dateStart=(dateInt-3)+dateString.substring(4,10);
				String dateEnd=(dateInt-2)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"'";
			}else if(installDate=="4"){
				String dateStart=(dateInt-4)+dateString.substring(4,10);
				String dateEnd=(dateInt-3)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"'";
			}
			else{
				String dateStart=(dateInt-4)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')<'"+dateStart+"'";
			}
	    }else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
	    	if(installDate=="1"){
				String dateStart=(dateInt-1)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and department.departmentId="+departmentId;
			}else if(installDate=="2"){
				String dateStart=(dateInt-2)+dateString.substring(4,10);
				String dateEnd=(dateInt-1)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId="+departmentId;
			}else if(installDate=="3"){
				String dateStart=(dateInt-3)+dateString.substring(4,10);
				String dateEnd=(dateInt-2)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId="+departmentId;
			}else if(installDate=="4"){
				String dateStart=(dateInt-4)+dateString.substring(4,10);
				String dateEnd=(dateInt-3)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId="+departmentId;
			}
			else{
				String dateStart=(dateInt-4)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')<'"+dateStart+"' and department.departmentId="+departmentId;
			}
	    }else{
	    	List<Integer> departlist = null;
	    	if(installDate=="1"){
	    		departlist = this.getChildDepartmentId(departmentId);
				String dateStart=(dateInt-1)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
			}else if(installDate=="2"){
				departlist = this.getChildDepartmentId(departmentId);
				String dateStart=(dateInt-2)+dateString.substring(4,10);
				String dateEnd=(dateInt-1)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
			}else if(installDate=="3"){
				departlist = this.getChildDepartmentId(departmentId);
				String dateStart=(dateInt-3)+dateString.substring(4,10);
				String dateEnd=(dateInt-2)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId in("+StringUtils.join(departlist,",") +")";
			}
			else if(installDate=="4"){
				departlist = this.getChildDepartmentId(departmentId);
				String dateStart=(dateInt-4)+dateString.substring(4,10);
				String dateEnd=(dateInt-3)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')>='"+dateStart+"' and to_char(installDate,'yyyy-MM-dd')<'"+dateEnd+"' and department.departmentId in("+StringUtils.join(departlist,",") +")";
			}
			else{
				departlist = this.getChildDepartmentId(departmentId);
				String dateStart=(dateInt-4)+dateString.substring(4,10);
				hql="select count(*) from EquipmentInfo where to_char(installDate,'yyyy-MM-dd')<'"+dateStart+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
			}
	    }
	    
	    if(manufacturer!=null){
			hql+=" and manufacturer='"+manufacturer+"' ";
		}
	    
		return this.countByHQL(hql);
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#queryEquipmentCustomerChartObjects(java.lang.Integer)
	 */
	public List<Object[]> queryEquipmentCustomerChartObjects(Integer departmentId) {
//		String sql="select c.customer_name,t.equipment_status,count(*) from V_CSS_EQUIPEMNT_INFO t "
//			+" left join t_customer_info c on t.customer_id=c.customer_id "
//			+" where t.department_id=? and t.equipment_status is not null and t.equipment_status not in(?,?)"
//			+" group by c.customer_name,t.equipment_status";
		String sql="select c.customer_name,t.equipment_status,count(*) from V_CSS_EQUIPEMNT_INFO t "
			+" left join v_crm_customer_info c on t.customer_id=c.id "
			+" where  t.equipment_status is not null and c.customer_name is not null and t.equipment_status not in(?,?) and t.deleted='N' "
			+" group by c.customer_name,t.equipment_status";
		return this.findBySQL(sql, new Object[]{EquipmentConstants.EQUIPMENT_STATUS_EXIT,EquipmentConstants.EQUIPMENT_STATUS_DISCARD});
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentSnapshotGroupByStatus(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<Object[]> getEquipmentSnapshotGroupByStatus(
			Integer departmentId) {
		List<Object> param=new ArrayList<Object>();
		param.add(EquipmentConstants.EQUIPMENT_STATUS_DISCARD);
		param.add(EquipmentConstants.EQUIPMENT_STATUS_EXIT);
		param.add(departmentId);
		String sql="select t.equipment_status,count(*) from V_CSS_EQUIPEMNT_INFO t where " 
				+" t.equipment_status not in (?,?) and t.equipment_status is not null "
				+" and t.department_id=? and t.warranty_status<>'E' and t.deleted='N' "
				+" group by t.equipment_status";
		return this.findBySQL(sql, param.toArray());
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentSnapshotGroupByEquipmentType(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<Object[]> getEquipmentSnapshotGroupByEquipmentType(
			Integer departmentId) {
		List<Object> param=new ArrayList<Object>();
		param.add(EquipmentConstants.EQUIPMENT_STATUS_DISCARD);
		param.add(EquipmentConstants.EQUIPMENT_STATUS_EXIT);
		param.add(departmentId);
		String sql="select t.equipment_type,count(*) from V_CSS_EQUIPEMNT_INFO t where " 
				+" t.equipment_status not in (?,?) "
				+" and t.department_id=? and t.warranty_status<>'E' and t.deleted='N' "
				+" group by t.equipment_type";
		return this.findBySQL(sql, param.toArray());
	}

	public List<Object[]> getEquipmentSnapshotGroupByWarrantyStatus(Integer departmentId,String equipmentType, String equipmentStatus) {
		List<Object> param=new ArrayList<Object>();
		param.add(EquipmentConstants.EQUIPMENT_STATUS_DISCARD);
		param.add(EquipmentConstants.EQUIPMENT_STATUS_EXIT);
		param.add(departmentId);
		param.add(equipmentType);
		param.add(equipmentStatus);
		String sql="select t.warranty_status,count(*) from V_CSS_EQUIPEMNT_INFO t where " 
				+"  t.equipment_status not in (?,?) "
				+" and t.department_id=? "
				+" and t.equipment_type=? "
				+" and t.equipment_status=? and t.deleted='N' "
				+" group by t.warranty_status";
		return this.findBySQL(sql, param.toArray());
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getOtherTypeEquipmentGroupByWarrantyStatus(java.lang.Integer, java.lang.String)
	 */
	public List<Object[]> getOtherTypeEquipmentGroupByWarrantyStatus(
			Integer departmentId, String equipmentStatus) {
		List<Object> param=new ArrayList<Object>();
		param.add(EquipmentConstants.EQUIPMENT_STATUS_DISCARD);
		param.add(EquipmentConstants.EQUIPMENT_STATUS_EXIT);
		param.add(departmentId);
		param.add(EquipmentConstants.EQUIPMENT_TYPE_ATM);
		param.add(EquipmentConstants.EQUIPMENT_TYPE_TELLERMACHINE);
		param.add(equipmentStatus);
		String sql="select t.warranty_status,count(*) from V_CSS_EQUIPEMNT_INFO t where " 
				+"  t.equipment_status not in (?,?) "
				+" and t.department_id=? "
				+" and t.equipment_type not in (?,?) "
				+" and t.equipment_status=? and t.deleted='N' "
				+" group by t.warranty_status";
		return this.findBySQL(sql, param.toArray());
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentSnapshotGroupByWarrantyStatus(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<Object[]> getEquipmentSnapshotGroupByWarrantyStatus(Integer departmentId) {
		List<Object> param=new ArrayList<Object>();
		param.add(EquipmentConstants.EQUIPMENT_STATUS_DISCARD);
		param.add(EquipmentConstants.EQUIPMENT_STATUS_EXIT);
		param.add(departmentId);
		String sql="select t.warranty_status,count(*) from V_CSS_EQUIPEMNT_INFO t where " 
				+" t.equipment_status not in (?,?) "
				+" and t.department_id=? and t.deleted='N' and t.warranty_status is not null "
				+" group by t.warranty_status";
		return this.findBySQL(sql, param.toArray());
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentSnapshotGroupBySaleProperty(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<Object[]> getEquipmentSnapshotGroupBySaleProperty(
			Integer departmentId) {
		List<Object> param=new ArrayList<Object>();
		param.add(EquipmentConstants.EQUIPMENT_STATUS_DISCARD);
		param.add(EquipmentConstants.EQUIPMENT_STATUS_EXIT);
		param.add(departmentId);
		String sql="select t.sale_property,count(*) from V_CSS_EQUIPEMNT_INFO t where " 
				+" t.equipment_status not in (?,?) "
				+" and t.department_id=? and t.deleted='N' and t.warranty_status<>'E' and t.sale_property is not null "
				+" group by t.sale_property";
		return this.findBySQL(sql, param.toArray());
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#queryCustomerObjectsGroupByCustomer(java.lang.Integer)
	 */
	public List<Object[]> queryCustomerObjectsGroupByCustomer(
			Integer departmentId,String equipmentStatus) {
		String eq="";
		if(StringUtils.isNotBlank(equipmentStatus)){
			eq=" and e.equipment_status='"+equipmentStatus+"'";
		}
		String sql="select c.id,c.customer_name from V_CSS_EQUIPEMNT_INFO e "
			+" left join v_crm_customer_info c on e.customer_id=c.id "
			+" where c.customer_name is not null  and e.deleted='N' and  e.department_id=? "
			+ eq
			+ " and e.equipment_status not in (?,?) "
			+" group by c.id,c.customer_name";
		return this.findBySQL(sql, new Object[]{departmentId,EquipmentConstants.EQUIPMENT_STATUS_DISCARD,EquipmentConstants.EQUIPMENT_STATUS_EXIT});
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#queryOpenRateFromEquipmentRunReportData(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public double queryOpenRateFromEquipmentRunReportData(Integer customerId,Integer departmentId, String Yearmonth) {
		String sql="select avg(t.open_rate) from V_CSS_EQUIPEMNT_INFO e "
				+" left join t_report_month_equipmentrun t on e.serial_number=t.serial_number "
				+" where e.customer_id=? and e.department_id=? and to_char(add_months(t.create_date,-1),'yyyy-MM')=?";
		List list=this.findBySQL(sql,new Object[]{customerId,departmentId,Yearmonth});
		if(list!=null&&list.size()>0){
			if(list.get(0)!=null&&StringUtils.isNotBlank(list.get(0).toString())){
				return Double.valueOf(list.get(0).toString());
			}
			return 1;
		}
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#queryRepairHoursFromEquipmentRunReportData(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public double queryRepairHoursFromEquipmentRunReportData(Integer customerId, Integer departmentId, String Yearmonth) {
		String sql="select avg(t.repair_hours) from V_CSS_EQUIPEMNT_INFO e "
			+" left join t_report_month_equipmentrun t on e.serial_number=t.serial_number "
			+" where e.customer_id=? and e.department_id=? and to_char(add_months(t.create_date,-1),'yyyy-MM')=? and t.repair_hours>0";
		List list=this.findBySQL(sql,new Object[]{customerId,departmentId,Yearmonth});
		if(list!=null&&list.size()>0){
			if(list.get(0)!=null&&StringUtils.isNotBlank(list.get(0).toString())){
				return Double.valueOf(list.get(0).toString());
			}
			return 0;
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#queryResponseHoursFromEquipmentRunReportData(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public double queryResponseHoursFromEquipmentRunReportData(Integer customerId, Integer departmentId, String Yearmonth) {
		String sql="select avg(t.response_hours) from V_CSS_EQUIPEMNT_INFO e "
			+" left join t_report_month_equipmentrun t on e.serial_number=t.serial_number "
			+" where e.customer_id=? and e.department_id=? and to_char(add_months(t.create_date,-1),'yyyy-MM')=? and t.response_hours>0";
		List list=this.findBySQL(sql,new Object[]{customerId,departmentId,Yearmonth});
		if(list!=null&&list.size()>0){
			if(list.get(0)!=null&&StringUtils.isNotBlank(list.get(0).toString())){
				return Double.valueOf(list.get(0).toString());
			}
			return 0;
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#queryEquipmentListByStationId(java.lang.Integer)
	 */
	public List<EquipmentInfo> queryEquipmentListByStationId(Integer departmentId) {
		HqlHelper hqleHelper=new HqlHelper(EquipmentInfo.class);
		hqleHelper.addEqual("department.departmentId", departmentId);
		hqleHelper.addNotIn("equipmentStatus", new String[]{EquipmentConstants.EQUIPMENT_STATUS_DISCARD,EquipmentConstants.EQUIPMENT_STATUS_EXIT});
		return this.find(hqleHelper);
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentByRootCustomerId(java.lang.Integer)
	 */
	public int getEquipmentByRootCustomerId(Integer customerId) {
		List<Integer> customerIdList=this.getAllCustomerByParentId(customerId);
		HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
		hqlHelper.addIn("customer.customerId", customerIdList);
		hqlHelper.addNotIn("equipmentStatus", new String[]{EquipmentConstants.EQUIPMENT_STATUS_DISCARD,EquipmentConstants.EQUIPMENT_STATUS_EXIT});
		return this.countByHQL("select count(*) "+hqlHelper.getHQL(), hqlHelper.getParams().toArray());
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#queryEquipmentSnapshotCountGroupbyStatus(java.lang.Integer, java.lang.String)
	 */
	public List<Object[]> queryEquipmentSnapshotCountGroupbyStatus(
			Integer departmentId, String datestr) {
		String sql="select t.equipment_status,sum(t.quantity) from t_snapshot_equipment t where to_char(t.create_date,'yyyy-MM-dd')=? and t.department_id in ("+StringUtils.join(this.getChildDepartmentId(departmentId),",")+") "
				+" and t.equipment_status in ('"+EquipmentConstants.EQUIPMENT_STATUS_USE+"','"+EquipmentConstants.EQUIPMENT_STATUS_WAIT+"','"+EquipmentConstants.EQUIPMENT_STATUS_DISABLE+"') "
				+" group by t.equipment_status ";
		return this.findBySQL(sql, datestr);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentListByCustomerIdAndDeptId(java.lang.Integer, java.lang.Integer)
	 */
	public List<EquipmentInfo> getEquipmentListByCustomerIdAndDeptId(Integer customerId, Integer deptId) {
		HqlHelper hqleHelper=new HqlHelper(EquipmentInfo.class);
		hqleHelper.addEqual("department.departmentId", deptId);
		hqleHelper.addEqual("customer.customerId", customerId);
		hqleHelper.addIn("equipmentStatus", new String[]{EquipmentConstants.EQUIPMENT_STATUS_USE,EquipmentConstants.EQUIPMENT_STATUS_WAIT});
		return this.find(hqleHelper);
	}

	public List<Integer> getEquipmentListByDeptId(Integer deptId){
		List<Integer> lint =new ArrayList<Integer>();
		HqlHelper hqleHelper=new HqlHelper(EquipmentInfo.class);
		hqleHelper.addEqual("department.departmentId",deptId);
		List<EquipmentInfo> eilist = this.find(hqleHelper);
		for(EquipmentInfo einfo:eilist){
			lint.add(einfo.getEquipmentId());
		}
		return lint ;
	}
	
	public List<Object[]> queryNeedGeneralBYTaskEquipment1(Page page) {
		String sql="select equipment_id,pm,warranty_start_date,warranty_end_date,lastTaskEndDate from ("
				+" select t.equipment_id,t.pm,t.warranty_start_date,t.warranty_end_date,(select count(*) from t_services_task ta where ta.task_source='"+ServicesConstants.SOURCE_TASK_SCHEDULED_TASKS+"' and ta.status='"+ServicesConstants.STATUS_TASK_PROCESS_UNALLOCATED+"' "
				+" and ta.task_type='BY' and ta.upgrade_code is null and ta.equipment_id=t.equipment_id) bycount,  "
				+ " (select edate from (select Ta.EQUIPMENT_ID,MAX(Ta.PLAN_END_DATE) edate from t_services_task ta where ta.task_source='计划任务' and ta.task_type='BY' "
				+ " and ta.status not in ('未分配','已取消') and ta.upgrade_code is null "
				+ " GROUP BY Ta.EQUIPMENT_ID) where equipment_id=t.equipment_id) lastTaskEndDate "
				+" from V_CSS_EQUIPEMNT_INFO t where  "
				+" t.warranty_end_date is not null and t.warranty_end_date-30>sysdate and t.equipment_status in('"+EquipmentConstants.EQUIPMENT_STATUS_USE+"','"+EquipmentConstants.EQUIPMENT_STATUS_DISABLE+"') " 
				+" and t.equipment_model not in('CM100V','CM100A','CM200V','CM200S')"//清分机取消生成保养，流水号：110403
				+" and t.warranty_status in ('A','B') and t.sale_property not in ('04','07') "
				+" ) where bycount=0";
		return this.findBySQL(sql,page);
	}

	public List<Object[]> queryNeedGeneralBYTaskEquipment2(Page page) {
		String sql="select equipment_id,pm,lastTaskEndDate from ( "
				+" select t.equipment_id,t.pm,(select count(*) from t_services_task ta where ta.task_source='"+ServicesConstants.SOURCE_TASK_SCHEDULED_TASKS+"' and ta.status='"+ServicesConstants.STATUS_TASK_PROCESS_UNALLOCATED+"' "
				+" and ta.task_type='BY' and ta.upgrade_code is null and ta.equipment_id=t.equipment_id) bycount," 
				+ " (select edate from (select Ta.EQUIPMENT_ID,MAX(Ta.PLAN_END_DATE) edate from t_services_task ta where ta.task_source='计划任务' and ta.task_type='BY' "
				+ " and ta.status not in ('未分配','已取消') and ta.upgrade_code is null "
				+ " GROUP BY Ta.EQUIPMENT_ID) where equipment_id=t.equipment_id) lastTaskEndDate "
				+" from V_CSS_EQUIPEMNT_INFO t where "
				+" ( t.warranty_start_date is null or t.warranty_end_date is null or t.warranty_status='C') and t.equipment_status in('1','3') "
				+" and t.equipment_model not in('CM100V','CM100A','CM200V','CM200S') "
				+" and t.warranty_status in ('A','B','C') and t.sale_property not in ('04','07') "
				+" ) where bycount=0";
		
		return this.findBySQL(sql,page);
	}
	
	/**
	 * 设备运行综合报表,设备分布
	 * @param customerId
	 * @param integrationBean 
	 * @return
	 */
	public List<Object[]> queryIntegrationEquipment(String customerId, EquipmentIntegrationBean integrationBean) {
//		List customerids=getAllCustomerByParentId(Integer.valueOf(customerId));
//		String customerStr=customerId;
//		if(customerids!=null&&customerids.size()>0){
//			customerStr=customerId+","+StringUtils.join(customerids, ",");
//		}
		
		StringBuffer where=new StringBuffer("");
		if(integrationBean.getStationId()!=null){
			where.append(" and t.department_id in ("+StringUtils.join(this.getChildDepartmentId(integrationBean.getStationId()),",")+") ");
		}
		if(StringUtils.isNotEmpty(integrationBean.getEquipmentType())){
			where.append(" and t.equipment_type='"+integrationBean.getEquipmentType()+"'");
		}
		if(StringUtils.isNotEmpty(integrationBean.getMaintainContractNo())){
			where.append(" and upper(t.maintain_contract_number) like '%"+integrationBean.getMaintainContractNo().toUpperCase()+"%'");
		}
		if(StringUtils.isNotEmpty(integrationBean.getSaleContractNo())){
			where.append(" and upper(t.sale_contract_number) like '%"+integrationBean.getSaleContractNo().toUpperCase()+"%'");
		}
		
		if(StringUtils.isNotEmpty(integrationBean.getWorkFinishDateEnd())){
			where.append(" and to_char(t.install_Date,'yyyy-MM-dd')<='"+integrationBean.getWorkFinishDateEnd()+"'");
		}
		
		String sql="select t.equipment_status,count(*) from V_CSS_EQUIPEMNT_INFO t "
				+""
				+" where t.equipment_status in (1,2,3) and t.customer_id in ("+customerId+") and t.deleted='N' " 
				+" and t.warranty_status not in ('"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_LOSE+"')"
//				+" and to_char(t.install_Date,'yyyy-MM-dd')<='"+integrationBean.getWorkFinishDateEnd()+"' "
				+where.toString()
				+" group by t.equipment_status";
		return this.findBySQL(sql);
	}

	public double queryAvgInusingEquipment(String yearmonth,Integer departmentId, String[] equipmentTypeArray) {
		
		//计算选中的月有多少天
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(StringUtils.substringBefore(yearmonth, "-")));
		cal.set(Calendar.MONTH, Integer.valueOf(StringUtils.substringAfter(yearmonth, "-"))-1);
		int days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);//上个月天数
		
		String sql="select sum(t.quantity)/"+days+" from t_snapshot_equipment t where to_char(t.create_date,'yyyy-MM')='"+yearmonth+"' "
				+" and t.department_id="+departmentId+" and t.equipment_status='1' ";
		if(equipmentTypeArray!=null){
			sql=sql+" and t.equipment_type in ("+StringUtils.join(equipmentTypeArray,",")+")";
		}
		//sql+=" and t.equipment_model not in ('H68N','H68NL','H68NCENL') ";
		List list=this.findBySQL(sql);
		if(list!=null&&list.size()>0&&list.get(0)!=null){
			return Double.valueOf(list.get(0).toString());
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#queryEquipmentByContractNo(java.lang.String)
	 */
	public List<EquipmentInfo> queryEquipmentByContractNo(String saleContractNo) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
		hqlHelper.addLikeIgnoreCase("saleContractNo", saleContractNo);
		return this.find(hqlHelper);
	}
	
	public List<EquipmentInfo> queryEquipmentByAccurateContractNo(String saleContractNo,String equipmentModel) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
		hqlHelper.addEqual("saleContractNo", saleContractNo);
		hqlHelper.addEqual("equipmentModel", equipmentModel);
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N );
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentBySerialNumberDepartmentName(java.lang.String, java.lang.Integer)
	 */
	public EquipmentInfo getEquipmentBySerialNumberDepartmentName(
			String serialNumber, Integer departmentId) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
		hqlHelper.addEqual("serialNumber", serialNumber);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		if(departmentId!=null){
			hqlHelper.addIn("department.departmentId",this.getChildDepartmentId(departmentId));
		}
		List<EquipmentInfo> list=this.find(hqlHelper);
		if(list.size()>0 && list!=null){
			return list.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getSumEquipmentStatusNoManufacturer(java.lang.String, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public Integer getSumEquipmentStatusNoManufacturer(String equipmentStatus,
			Integer departmentId, String departmentType, String manufacturer) {
		String hql;
		List<Integer> departlist = null;
		if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
			if(equipmentStatus==null){
				hql="select count(*) from EquipmentInfo where 1=1";
			}else{
				hql="select count(*) from EquipmentInfo where equipmentStatus='"+equipmentStatus+"'";
			}
		}else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
			if(equipmentStatus==null){
				hql="select count(*) from EquipmentInfo where department.departmentId="+departmentId;
			}else{
				hql="select count(*) from EquipmentInfo where equipmentStatus='"+equipmentStatus+"' and department.departmentId="+departmentId;
			}
		}
		else{
			departlist = this.getChildDepartmentId(departmentId);
			if(equipmentStatus==null){
				hql="select count(*) from EquipmentInfo where department.departmentId in ("+StringUtils.join(departlist,",") +")";
			}else{
				hql="select count(*) from EquipmentInfo where equipmentStatus='"+equipmentStatus+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
			}
		}
		if(manufacturer!=null){
			hql+=" and manufacturer!='"+manufacturer+"' ";
		}
		return this.countByHQL(hql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getSumEquipmentTypeNoManufacturer(java.lang.String, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public Integer getSumEquipmentTypeNoManufacturer(String equipmentType,
			Integer departmentId, String departmentType, String manufacturer) {
		String hql;
		List<Integer> departlist = null;
		if(departmentType.equals(CommonConstants.DEPT_TYPE_HEAD)){
			hql="select count(*) from EquipmentInfo where equipmentType='"+equipmentType+"'";
		}else if(departmentType.equals(CommonConstants.DEPT_TYPE_STATION)){
			hql="select count(*) from EquipmentInfo where equipmentType='"+equipmentType+"' and department.departmentId="+departmentId;
		}else{
			departlist = this.getChildDepartmentId(departmentId);
			hql="select count(*) from EquipmentInfo where equipmentType='"+equipmentType+"' and department.departmentId in ("+StringUtils.join(departlist,",") +")";
		}
		if(manufacturer!=null){
			hql+=" and manufacturer!='"+manufacturer+"' ";
		}
		return this.countByHQL(hql);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#deleteEquipmentBYtask(java.lang.Integer, java.lang.String)
	 */
	/*public void deleteEquipmentBYtask(Integer equipmentId, String status,String remark) {
		//2013-6-19去除深度保养t.upgrade_code is null
		//13-8-15增加删除日志
		String dsql="insert into t_services_task_by_log t(t.by_log_id,t.equipment_id,t.task_id,t.process_no,t.plan_start_date,t.plan_end_date,t.create_date,t.operate_status,t.operate_remark) " +
				" select SEQ_SERVICES_TASK_BY_LOG.NEXTVAL,s.equipment_id,s.task_id,s.process_no,s.plan_start_date,s.plan_end_date,sysdate,'delete','"+remark+"' " +
				" from t_services_task s where s.equipment_id="+equipmentId+" and s.status='"+status+"' and s.task_type='BY' and s.upgrade_code is null ";
		this.executeSql(dsql);
		String sql="delete from t_services_task t where t.task_type='BY' and t.upgrade_code is null and t.status='"+status+"' and t.equipment_id="+equipmentId;
		this.executeSql(sql);
	}*/

	/* (cfei)查找设备数量
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#countEquipmentByDeptId(java.lang.Integer, java.lang.String)
	 */
	public Integer countEquipmentByDeptId(Integer deptId, String equipmentType) {
//		HqlHelper hql = new HqlHelper(EquipmentInfo.class);
		String sql = "";
		if(EquipmentConstants.EQUIPMENT_TYPE_TELLERMACHINE.equals(equipmentType)){
//			hql.addEqual("equipmentType", equipmentType);
			sql = "select count(*) from V_CSS_EQUIPEMNT_INFO t where t.department_id in ("+StringUtils.join(this.getChildDepartmentId(deptId),",")+") and t.equipment_type = '"+equipmentType+"' " +
					" and t.equipment_status='"+EquipmentConstants.EQUIPMENT_STATUS_USE+"'  " +
							" and t.warranty_status in ('"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_INNER+"','"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_ALREADY+"','"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_NOT+"')";
		}else{
//			hql.addNotEqual("equipmentType", EquipmentConstants.EQUIPMENT_TYPE_TELLERMACHINE);
			sql = "select count(*) from V_CSS_EQUIPEMNT_INFO t where t.department_id in ("+StringUtils.join(this.getChildDepartmentId(deptId),",")+") and t.equipment_type != '"+EquipmentConstants.EQUIPMENT_TYPE_TELLERMACHINE+"' " +
					" and t.equipment_status='"+EquipmentConstants.EQUIPMENT_STATUS_USE+"' " +
							" and t.warranty_status in ('"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_INNER+"','"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_ALREADY+"','"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_NOT+"')";
		}
//		hql.addIn("department", this.getChildDepartmentId(deptId));
		return this.countBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getCountByEquipment(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public Integer getCountByEquipment(String[] equipmentModelArray,
			String warrantyStatus, Integer departmentId) {
		String sql=" select count(*) from V_CSS_EQUIPEMNT_INFO t where 1=1 ";
		if(warrantyStatus!=null){
			sql+=" and t.equipment_status='"+warrantyStatus+"'";
		}
		if(departmentId!=null){
			sql+=" and t.department_id="+departmentId;
		}
		if(equipmentModelArray!=null){
			sql+=" and t.equipment_model in ('"+StringUtils.join(equipmentModelArray,"','")+"')";
		}
		return this.countBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentTempList(com.grgbanking.framework.core.Page, java.lang.Integer)
	 */
	public List<Object[]> getEquipmentTempList(Page page,Integer departmentId) {
		
		String sql="select d.department_name,t.*  from t_temp_temp t left join t_base_department d on t.region=d.department_id " +
				" left join V_CSS_EQUIPEMNT_INFO e on t.equipment_id=e.equipment_id " +
				" where e.department_id in ("+StringUtils.join(this.getChildDepartmentId(departmentId),",")+") order by d.department_id,t.province,t.equipment_id ";
		return this.findBySQL(sql,page);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#queryIntegrationEquipmentType(java.lang.String, com.grgbanking.soc.bean.equipment.EquipmentIntegrationBean)
	 */
	public List<Object[]> queryIntegrationEquipmentType(String customerId,
			EquipmentIntegrationBean integrationBean) {
//		List customerids=getAllCustomerByParentId(Integer.valueOf(customerId));
//		String customerStr=customerId;
//		if(customerids!=null&&customerids.size()>0){
//			customerStr=customerId+","+StringUtils.join(customerids, ",");
//		}
		
		StringBuffer where=new StringBuffer("");
		if(integrationBean.getStationId()!=null){
			where.append(" and t.department_id in ("+StringUtils.join(this.getChildDepartmentId(integrationBean.getStationId()),",")+") ");
		}
		if(StringUtils.isNotEmpty(integrationBean.getEquipmentType())){
			where.append(" and t.equipment_type='"+integrationBean.getEquipmentType()+"'");
		}
		if(StringUtils.isNotEmpty(integrationBean.getMaintainContractNo())){
			where.append(" and upper(t.maintain_contract_number) like '%"+integrationBean.getMaintainContractNo().toUpperCase()+"%'");
		}
		if(StringUtils.isNotEmpty(integrationBean.getSaleContractNo())){
			where.append(" and upper(t.sale_contract_number) like '%"+integrationBean.getSaleContractNo().toUpperCase()+"%'");
		}
//		if(StringUtils.isNotEmpty(integrationBean.getWorkFinishDateEnd())){
//			where.append(" and to_char(t.install_Date,'yyyy-MM-dd')<='"+integrationBean.getWorkFinishDateEnd()+"' ");
//		}
		
		String sql="select t.equipment_status,t.equipment_type,count(*),d.department_name  from V_CSS_EQUIPEMNT_INFO t "
				+" left join t_base_department d on d.department_id=t.department_id"
				+" where t.equipment_status in (1,2,3) and t.customer_id in ("+customerId+")"
				+" and t.warranty_status not in ('"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_LOSE+"') "
				+where.toString()+" and t.equipment_type in('A100','A300') "
				+" group by t.equipment_status,t.equipment_type,d.department_name";
		return this.findBySQL(sql);
	}

	// 统计设备总量（1,机芯类型，2,区域   3,设备维保状态   4,设备状态    5,设备安装时间:截至到报表统计前一个月  6,厂商  ）
	public Integer sumEquipmentNumByModel(Integer departmentId,String materialName,String previousMonth){
		String sql;
		sql =" select count(distinct e.equipment_id)";
		sql +=" from V_CSS_EQUIPEMNT_INFO e left join t_equipment_config c on e.equipment_id=c.equipment_id ";
		sql +=" left join t_sparepart s on c.sparepart_id=s.sparepart_id ";
		sql +=" and s.material_name like '"+materialName+"%'";
		sql +=" and s.module_type='B000'"; 
		sql +=" and e.department_id in ("+StringUtils.join(this.getChildDepartmentId(departmentId),",")+")";
		sql +=" and e.warranty_status in ('"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_INNER+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_NOT+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_ALREADY+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_REFUSE+"') ";
		sql +=" and e.equipment_status in ('"+EquipmentConstants.EQUIPMENT_STATUS_USE+"')";
		sql +=" and to_char(e.install_date, 'yyyy-MM')<='"+previousMonth+"' ";
		sql +=" and e.manufacturer in ('GRGBANKING')"; 
		sql +=" and e.deleted='N'";
		
		return this.countBySQL(sql);
	}

	//统计故障部位（1,设备厂商  2,区域  3,任务类型   4,工单状态   5,工单完成时间：统计时间的前一个月 6,故障编号代码：）
	public Integer sumProblemEquipmentPartByModel(String material,Integer departmentId,String partCode,String previousMonth){
		String sql;
		sql ="select count(*)";
		sql +=" from t_services_task_repair_place repair, t_services_task task, V_CSS_EQUIPEMNT_INFO e, t_services_workform w,t_equipment_config c, t_sparepart s ";
		sql +=" where e.equipment_id=c.equipment_id";
        sql +=" and c.sparepart_id=s.sparepart_id ";
		sql +=" and repair.task_id=task.task_id "; 
		sql +=" and w.equipment_id=e.equipment_id ";
		sql +=" and task.workform_id=w.workform_id ";
		sql +=" and s.material_name like '"+material+"%'";
		sql +=" and s.module_type='B000' ";
		sql +=" and e.manufacturer in ('GRGBANKING')";
		sql +=" and w.department_id in ("+StringUtils.join(this.getChildDepartmentId(departmentId),",")+")";
		sql +=" and e.equipment_status in ('"+EquipmentConstants.EQUIPMENT_STATUS_USE+"')"; 
		sql +=" and task.task_type in ('"+ServicesConstants.TASK_TYPE_REPAIR+"')"; 
		sql +=" and w.status in ('"+ServicesConstants.STATUS_WORKFORM_DIRECTOR+"','"+ServicesConstants.STATUS_WORKFORM_STOCK+"','"+ServicesConstants.STATUS_WORKFORM_COMPLETED+"')"; 
		sql +=" and to_char(w.work_finish_date, 'yyyy-MM') ='"+previousMonth+"'";
		sql +=" and w.po_number is not null";
		sql +=" and repair.problem_part_code in ("+partCode+")";
	
		return this.countBySQL(sql);
	}
	
	//根据安装时间统计设备数量(1,机芯   2,设备维保状态  3,设备状态  4,设备安装时间：到统计的前一个月   5,厂商   6,)
	public Integer sumEquipmentNumByInstallDate(String materialName,String installDate,String previousMonth){
		String sql;
		sql =" select count(*)";
		sql +=" from V_CSS_EQUIPEMNT_INFO e ,t_equipment_config c, t_sparepart s ";
		sql +=" where e.equipment_id=c.equipment_id and c.sparepart_id=s.sparepart_id ";
		sql +=" and s.material_name like '"+materialName+"%'";
		sql +=" and s.module_type='B000'"; 
		sql +=" and e.warranty_status in ('"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_INNER+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_NOT+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_ALREADY+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_REFUSE+"') ";
		sql +=" and e.equipment_status in ('"+EquipmentConstants.EQUIPMENT_STATUS_USE+"')";
		sql +=" and to_char(e.install_date, 'yyyy-MM')<='"+previousMonth+"' ";
		sql +=" and e.manufacturer in ('GRGBANKING')"; 
		sql +=" and e.deleted='N'";
		if(installDate != null){
	      if(installDate.equals(">36")){
	    	sql +=" and months_between(to_date('"+previousMonth+"','yyyy-MM'),e.install_date)"+ installDate;}
	      else{
	    	sql +=" and months_between(to_date('"+previousMonth+"','yyyy-MM'),e.install_date)<= "+ installDate;}
	    }
		
		return this.countBySQL(sql);
	}
	
	//根据安装时间统计设备故障数量(1,机芯   2,设备维保状态  3,设备状态  4,任务类型  5,工单状态   6,故障部件代码   7,统计时间  8,设计安装时间)
	public Integer sumProblemEquipmentPartByInstallDate(String material,String partCode,String installDate,String preMonth){
		String sql;
		sql =" select count(*)";
		sql +=" from t_services_task_repair_place repair, t_services_task task, V_CSS_EQUIPEMNT_INFO e, t_services_workform w, t_equipment_config c, t_sparepart s ";
		sql +=" where e.equipment_id=c.equipment_id";
        sql +=" and c.sparepart_id=s.sparepart_id ";
		sql +=" and repair.task_id=task.task_id ";
		sql +=" and w.equipment_id=e.equipment_id ";
		sql +=" and task.workform_id=w.workform_id ";
		sql +=" and s.material_name like '"+material+"%'";
		sql +=" and s.module_type='B000' ";
		sql +=" and e.manufacturer in ('GRGBANKING')";
		sql +=" and e.equipment_status in ('"+EquipmentConstants.EQUIPMENT_STATUS_USE+"') ";
		sql +=" and task.task_type in ('"+ServicesConstants.TASK_TYPE_REPAIR+"') ";
		sql +=" and w.status in ('"+ServicesConstants.STATUS_WORKFORM_DIRECTOR+"','"+ServicesConstants.STATUS_WORKFORM_STOCK+"','"+ServicesConstants.STATUS_WORKFORM_COMPLETED+"')"; 
		sql +=" and w.po_number is not null";
		sql +=" and repair.problem_part_code in ("+partCode+")";
		 if(installDate != null){
	    	if(installDate.equals(">36")){
		    	sql +=" and months_between(to_date('"+preMonth+"','yyyy-MM'),e.install_date)"+ installDate;}
	    	else{
			    sql +=" and months_between(to_date('"+preMonth+"','yyyy-MM'),e.install_date)<= "+ installDate;}
		 }
		
		return this.countBySQL(sql);
	}

	//统计月份区域设备量（1,设备维保状态  2,设备状态   3,设备安装时间   4,厂商）
	public Integer countEquipmentsByRegion(Integer departmentId,String currentMonth){
		String sql;
		sql =" select count(*)";
		sql +=" from V_CSS_EQUIPEMNT_INFO e";
		sql +=" where e.department_id in ("+StringUtils.join(this.getChildDepartmentId(departmentId),",")+")";
		sql +=" and e.warranty_status in ('"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_INNER+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_NOT+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_ALREADY+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_REFUSE+"') ";
		sql +=" and e.equipment_status in ('"+EquipmentConstants.EQUIPMENT_STATUS_USE+"')";
		sql +=" and to_char(e.install_date, 'yyyy-MM')<='"+currentMonth+"' ";
		sql +=" and e.manufacturer in ('GRGBANKING')"; 
		sql +=" and e.deleted='N'";
		
        return this.countBySQL(sql);
	}

	//根据设备安装时间统计设备数量（1,设备维保状态   2,设备状态   3,厂商   4,统计时间   5,安装时间）
	public Integer countEquipmentsByInstallDate(String month,String installDate) {
		String sql;
		sql =" select count(*)";
		sql +=" from V_CSS_EQUIPEMNT_INFO e ";
		sql +=" where e.warranty_status in ('"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_INNER+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_NOT+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_ALREADY+"', '"+EquipmentConstants.EQUIPMENT_WARRANTY_STATUS_REFUSE+"') ";
		sql +=" and e.equipment_status in ('"+EquipmentConstants.EQUIPMENT_STATUS_USE+"')";
		sql +=" and e.manufacturer in ('GRGBANKING') ";
		sql +=" and e.deleted='N'";
		if (installDate.equals(">36")) {
			sql += " and months_between(to_date('" + month+ "','yyyy-MM'),e.install_date)" + installDate;
		} else {
			sql += " and months_between(to_date('" + month+ "','yyyy-MM'),e.install_date)<=" + installDate;
		}
			
		return this.countBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.EquipmentDAO#getEquipmentIdList(com.grgbanking.soc.bean.services.UpgradePlanBean)
	 */
	public List<Integer> getEquipmentIdList(UpgradePlanBean upgradePlan) {
		
		String sqlTitle="select t.equipment_id,count(t.equipment_id) from V_CSS_EQUIPEMNT_INFO t ";
		String sql="";
		String sqlLevel="";
		if(StringUtils.isNotBlank(upgradePlan.getSparepartName()) || StringUtils.isNotBlank(upgradePlan.getHardwareVersion())){
			sql+=" left join t_equipment_config c on t.equipment_id=c.equipment_id left join t_sparepart s on c.sparepart_id=s.sparepart_id ";
			sqlLevel=" and module_level='A' ";
		}
		sql+=" where 1=1 ";
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getEquipmentModels())){
			String strModels=ArrayTransitionUtils.StringToInString(upgradePlan.getEquipmentModels(), ",");
			sql+=" and t.equipment_model in ("+strModels+") ";
		}
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getEquipmentTypes())){
			String strType=ArrayTransitionUtils.StringToInString(upgradePlan.getEquipmentTypes(), ",");
			sql+=" and t.equipment_type in ("+strType+") ";
		}
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getDepartmentIds())){
			String departmentIds = ArrayTransitionUtils.StringToInString(upgradePlan.getDepartmentIds(), ",");
			sql+=" and t.department_id in ("+departmentIds+") ";
		}
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getCustomerIds())){
			String customerIds = ArrayTransitionUtils.StringToInString(upgradePlan.getCustomerIds(), ",");
			sql+=" and t.customer_id in ("+customerIds+") ";
		}
		
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getSerialNumbers())){
			sql += generateSerialNumberSql(upgradePlan.getSerialNumbers());
		}
		
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getManufacturer())){
			sql+=" and t.manufacturer='"+upgradePlan.getManufacturer()+"' ";
		}
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getEquipmentBatchNumber())){
			sql+=" and t.batch_number='"+upgradePlan.getEquipmentBatchNumber()+"' ";
		}
		if(upgradePlan.getShipDateStart()!=null){
			sql+=" and to_char(t.ship_date,'yyyy-MM-dd')>='"+DateUtil.formatDate(upgradePlan.getShipDateStart())+"' ";
		}
		if(upgradePlan.getShipDateEnd()!=null){
			sql+=" and to_char(t.ship_date,'yyyy-MM-dd')<='"+DateUtil.formatDate(upgradePlan.getShipDateEnd())+"' ";
		}
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getEquipmentStatus())){
			String streq = ArrayTransitionUtils.StringToInString(upgradePlan.getEquipmentStatus(), ",");
			sql+=" and t.equipment_status in ("+streq+") ";
		}
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getWarrantyStatus())){
			String strwa=ArrayTransitionUtils.StringToInString(upgradePlan.getWarrantyStatus(), ",");
			sql+=" and t.warranty_status in ("+strwa+") ";
		}
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getAtmcName())){
			sql+=" and t.atmc_name='"+upgradePlan.getAtmcName()+"' ";
		}
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getSparepartName())){
			sql+=" and s.material_name like'%"+upgradePlan.getSparepartName()+"%' ";
		}
		if(StringUtilsExtends.isNotBlankAndEmpty(upgradePlan.getHardwareVersion())){
			sql+=" and c.hardware_version like'%"+upgradePlan.getHardwareVersion()+"%' ";
		}
		
		if(!StringUtilsExtends.isBlankOrEmpty(upgradePlan.getModelType(),upgradePlan.getKey(),upgradePlan.getValue())){
			sql += getVersionSql(upgradePlan.getModelType(),upgradePlan.getKey(),upgradePlan.getValue());
		}
		
		String sqlfooter = " group by t.equipment_id ";
		
		int count = this.countBySQL("select count(*) from ("+sqlTitle+sql+sqlLevel+sqlfooter+" )");
		if(count>60000){
			throw new ServiceException("已选中设备数量超过六万台，请分批升级！");
		}
		
		List<Object[]> list= findBySQL(sqlTitle+sql+sqlLevel+sqlfooter);
		List<Integer> equipmentIdList=new ArrayList<Integer>();
		if(ListUtils.isNotEmpty(list)){
			for (Object[] obj : list) {
				equipmentIdList.add(Integer.valueOf(obj[0].toString()));
			}
		}
		
		return equipmentIdList;
	}

	private String generateSerialNumberSql(String serialNumbers) {
		String serialNums[] = serialNumbers.replaceAll("\r\n", "\n").split("\n");
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

	private String getVersionSql(String modelType, String key, String value) {
		String sql = " and t.equipment_id in (";
		sql += " select v.equipment_id from T_EQUIPMENT_MODEL_VERSION v where 1=1 ";
		if(StringUtilsExtends.isNotBlankAndEmpty(modelType)){
			sql += " and v.model_type = '"+modelType+"' ";
		}
		if(StringUtilsExtends.isNotBlankAndEmpty(key))
			sql += " and v.key = '"+key+"'";
		
		if(StringUtilsExtends.isNotBlankAndEmpty(value)) {
			sql += " and v.value = '"+value+"'";
		}
		sql +=") ";
		return sql;
	}

	public List<Map> querySerialNumberAndEquipmentModelList(EquipmentBean equipmentBean,
			Page page) {
		String sql = "select t.serial_number,t.equipment_model" + 
				"  from V_CSS_EQUIPEMNT_INFO t where 1=1 ";
		if(StringUtilsExtends.isNotBlankAndEmpty(equipmentBean.getSerialNumber()))
			sql += "   and t.serial_number in ("+ArrayTransitionUtils.StringToInString(equipmentBean.getSerialNumber(), ",")+")";
		if(StringUtilsExtends.isNotBlankAndEmpty(equipmentBean.getDepartmentIds()))
			sql += "   and t.department_id in ("+ArrayTransitionUtils.sliceOffSeparator(equipmentBean.getDepartmentIds(), ",")+")"; 
		if(StringUtilsExtends.isNotBlankAndEmpty(equipmentBean.getEquipmentType()))
			sql += "   and t.equipment_type in ("+ArrayTransitionUtils.StringToInString(equipmentBean.getEquipmentType(), ",")+")";
		if(StringUtilsExtends.isNotBlankAndEmpty(equipmentBean.getEquipmentModel()))
			sql += "   and t.equipment_model in ("+ArrayTransitionUtils.StringToInString(equipmentBean.getEquipmentModel(), ",")+")";
		List<Map> mapResult = findMapResultBySql(sql, page);
		List<Map> result = new ArrayList<Map>();
		for (Map queryMap : mapResult) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("serialNumber", MapUtils.getString(queryMap, "SERIAL_NUMBER"));
			map.put("equipmentModel", MapUtils.getString(queryMap, "EQUIPMENT_MODEL"));
			result.add(map);
		}
		return result;
	}
	
	public Integer getSumEquipmentDailyServices(String equipmentStatus,
			Integer departmentId) {
		String sql="select count(t.equipment_id) from V_CSS_EQUIPEMNT_INFO t inner join ( " +
				" select d.department_id from t_base_department d start with d.department_id="+departmentId+" " +
				" connect by prior d.department_id=d.parent_id ) s on t.department_id=s.department_id " +
				" where t.equipment_status='"+equipmentStatus+"'  and t.warranty_status<>'E' and t.deleted='N' ";
		return this.countBySQL(sql);
	}

	public List<EquipmentSearchPape> queryEquipmentListNew(Page queryPage,
			EquipmentBean equipmentBean) {
		String sql="select e.equipment_id,e.equipment_model,e.serial_number,e.department_id,e.customer_id " +
				" ,e.equipment_status,e.warranty_status,e.install_date,e.accept_report_status,e.ship_date,e.branch_name, " +
				" e.sale_property,e.equipment_type,e.install_address,e.atm_number from V_CSS_EQUIPEMNT_INFO e where e.deleted='N' ";
		
		//设备序列号
		if(StringUtils.isNotEmpty(equipmentBean.getSerialNumber())){
			if(equipmentBean.getSerialNumber().length()<600){
				sql+= " and e.SERIAL_NUMBER in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getSerialNumber(),",") +")";
			}else{
				sql+=generateSerialNumberQuery("serial_number",equipmentBean.getSerialNumber());
			}
		}
		//网点名称
		if(StringUtils.isNotEmpty(equipmentBean.getBranchName())){
			sql+=" and e.branch_name like '%"+equipmentBean.getBranchName()+"%' ";
		}
		//安装地址
		if(StringUtils.isNotEmpty(equipmentBean.getInstallAddress())){
			sql+=" and e.install_address like '%"+equipmentBean.getInstallAddress()+"%' ";
		}
		//设备制造商
		if(StringUtils.isNotEmpty(equipmentBean.getManufacturer())){
			sql+= " and e.manufacturer in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getManufacturer(), ",") +")";
		}
		//设备类型
		if(StringUtils.isNotEmpty(equipmentBean.getEquipmentType())){
			sql+= " and e.equipment_type in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getEquipmentType(), ",") +")";
		}
		//设备型号
		if(StringUtils.isNotEmpty(equipmentBean.getEquipmentModel())){
			sql+= " and e.equipment_model in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getEquipmentModel(), ",") +")";
		}
		//销售合同号
		if(StringUtils.isNotEmpty(equipmentBean.getSaleContractNo())){
			sql+=" and sale_contract_number like '%"+equipmentBean.getSaleContractNo()+"%' ";
		}
		//销售属性
		if(StringUtils.isNotEmpty(equipmentBean.getSaleProperty())){
			sql += " and e.sale_property in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getSaleProperty(), ",") +")";
		}
		//维保合同号
		if(StringUtils.isNotEmpty(equipmentBean.getMaintainContractNo())){
			if(equipmentBean.getMaintainContractNo().length()<600){
				sql+= " and e.maintain_contract_no in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getMaintainContractNo(), ",") +")";
			}else{
				sql+=generateMaintainContractNoSql(equipmentBean.getMaintainContractNo());
			}
		}
		//维保状态
		if(StringUtils.isNotEmpty(equipmentBean.getWarrantyStatus())){
			sql += " and e.warranty_status in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getWarrantyStatus(), ",") +")";
		}
		//服务站
		if(StringUtils.isNotEmpty(equipmentBean.getDepartmentIds())){
			sql += " and e.department_id in ("+ ArrayTransitionUtils.StringToInString(equipmentBean.getDepartmentIds(), ",") +")";
		}
		//服务站模糊查询
		if(StringUtils.isNotEmpty(equipmentBean.getDepartmentId0())){
			sql += " and e.department_id in (select department_id from t_base_department where department_name like '%"+equipmentBean.getDepartmentId0()+"%')";
		}
		//客户
		if(StringUtils.isNotEmpty(equipmentBean.getCustomerIds())){
			if(equipmentBean.getCustomerIds().length()<600){
				sql += " and e.customer_id in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getCustomerIds(), ",") +")";
			}else{
				sql+=generateCustomerIdQuery("customer_id",equipmentBean.getCustomerIds());
			}
		}
		//设备状态
		if(StringUtils.isNotEmpty(equipmentBean.getEquipmentStatus())){
			sql += " and e.equipment_status in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getEquipmentStatus(), ",") +")";
		}
		//安装类型
		if(StringUtils.isNotEmpty(equipmentBean.getInstallType())){
			sql += " and e.install_type in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getInstallType(), ",") +")";
		}
		//安装方式
		if(StringUtils.isNotEmpty(equipmentBean.getInstallModel())){
			sql += " and e.install_model in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getInstallModel(), ",") +")";
		}
		//安装日期start
		if(StringUtils.isNotEmpty(equipmentBean.getBeginInstallDate())){
			String startStr=equipmentBean.getBeginInstallDate()+" 00:00:00";
			sql += " and e.install_date >= to_date('"+startStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		//安装日期end
		if(StringUtils.isNotEmpty(equipmentBean.getEndInstallDate())){
			String endStr=equipmentBean.getEndInstallDate()+" 23:59:59";
			sql += " and e.install_date <= to_date('"+endStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		//到保日期start
		if(StringUtils.isNotEmpty(equipmentBean.getBeginWarrantyEndDate())){
			String startStr=equipmentBean.getBeginWarrantyEndDate()+" 00:00:00";
			sql += " and e.warranty_end_date >= to_date('"+startStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		//到保日期end
		if(StringUtils.isNotEmpty(equipmentBean.getEndWarrantyEndDate())){
			String endStr=equipmentBean.getEndWarrantyEndDate()+" 23:59:59";
			sql += " and e.warranty_end_date <= to_date('"+endStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		//发货日期start
		if(StringUtils.isNotEmpty(equipmentBean.getBeginShipDate())){
			String startStr=equipmentBean.getBeginShipDate()+" 00:00:00";
			sql += " and e.ship_date >= to_date('"+startStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		//发货日期end
		if(StringUtils.isNotEmpty(equipmentBean.getEndShipDate())){
			String endStr=equipmentBean.getEndShipDate()+" 23:59:59";
			sql += " and e.ship_date <= to_date('"+endStr+"', 'yyyy-MM-dd HH24:mi:ss')";
		}
		//验收报告状态
		if(StringUtils.isNotEmpty(equipmentBean.getAcceptReportStatus())){
			sql += " and e.accept_report_status in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getAcceptReportStatus(), ",") +")";
			
		}
		//省
		if(null != equipmentBean.getProvinceId()){
			sql += " and e.province_id = '"+equipmentBean.getProvinceId()+"' ";
		}		
		//市
		if(null != equipmentBean.getCityId()){
			sql += " and e.city_id = '"+equipmentBean.getCityId()+"' ";
		}
		//ATM号
		if(StringUtils.isNotEmpty(equipmentBean.getAtmNumber())){
			if(equipmentBean.getAtmNumber().indexOf(",") == -1){
				sql+= " and e.atm_number like '%" + equipmentBean.getAtmNumber().trim() + "%'";
			}else if(equipmentBean.getAtmNumber().length()<600){
				sql+= " and e.atm_number in("+ ArrayTransitionUtils.StringToInString(equipmentBean.getAtmNumber(),",") +")";
			}else{
				sql+=generateSerialNumberQuery("atm_number",equipmentBean.getAtmNumber());
			}
		}
		//终端号
		if(StringUtils.isNotEmpty(equipmentBean.getBankTerminalNumber())){
			sql+= " and e.BANK_TERMINAL_NUMBER like '%" + equipmentBean.getBankTerminalNumber().trim() + "%'";
		}
		
		//设备负责人
		if(StringUtils.isNotBlank(equipmentBean.getEquipmentChargeName())){
			String userSql=" select u.user_id from t_base_user u where u.name like '%"+equipmentBean.getEquipmentChargeName()+"%' ";
			sql += " and e.user_id in("+userSql+") ";
		}
		//保修日期状态
		if(StringUtils.isNotEmpty(equipmentBean.getWarrantyDateStr())){
			if(equipmentBean.getWarrantyDateStr().equals("1")){
				sql+=" and e.warranty_end_date is not null ";
			}else if(equipmentBean.getWarrantyDateStr().equals("2")) {
				sql+=" and e.warranty_end_date is null ";
			}
		}
		
		//默认服务站
		if(equipmentBean.getDepartmentId()!=null){
			//List<Integer> depList=this.getChildDepartmentId(equipmentBean.getDepartmentId());
			//XXX sql += " and e.department_id in("+ArrayTransitionUtils.integerListToInString(depList)+") ";
			sql += " and e.department_id = "+equipmentBean.getDepartmentId()+" ";
		}
		
		if(queryPage.getSort()!=null){
			if("serialNumber".equals(queryPage.getSort())){
				sql+=" order by e.serial_number "+queryPage.getOrder();
			}else if("department.departmentName".equals(queryPage.getSort())){
				sql+=" order by e.department_id "+queryPage.getOrder();
			}else if("customer.customerName".equals(queryPage.getSort())){
				sql+=" order by e.customer_id "+queryPage.getOrder();
			}else if("equipmentStatus".equals(queryPage.getSort())){
				sql+=" order by e.equipment_status "+queryPage.getOrder();
			}else if("installDate".equals(queryPage.getSort())){
				sql+=" order by e.install_date "+queryPage.getOrder();
			}else if("equipmentModel".equals(queryPage.getSort())){
				sql+=" order by e.equipment_model "+queryPage.getOrder();
			}else if("branchName".equals(queryPage.getSort())){
				sql+=" order by e.branch_name "+queryPage.getOrder();
			}else if("warrantyStatus".equals(queryPage.getSort())){
				sql+=" order by e.warranty_status "+queryPage.getOrder();
			}else if("acceptReportStatus".equals(queryPage.getSort())){
				sql+=" order by e.accept_report_status "+queryPage.getOrder();
			}else{
				sql+=" order by e.equipment_id "+queryPage.getOrder();
			}
			
		}
		
		return this.findEntityListBySQL(sql,EquipmentSearchPape.class,queryPage);
	}
	
	private String generateSerialNumberQuery(String field,String serialNumbers) {
		String serialNums[] =serialNumbers.replaceAll(" ","").split(",");
		StringBuffer serialNumberSql = new StringBuffer();
		double loopNum = Math.ceil(serialNums.length/100f);
		List<String[]> bufferStrlist = new ArrayList<String[]>();
		for(int i=0;i<loopNum;i++){
			bufferStrlist.add(getBufferStr(serialNums,i,100));
		}
		serialNumberSql.append("and (") ;
		for (String[] buffer : bufferStrlist) {
			serialNumberSql.append(" "+field+" in ("+ArrayTransitionUtils.stringArrayToInString(buffer)+") ");
			serialNumberSql.append(" or ");
		}
		serialNumberSql.replace(serialNumberSql.lastIndexOf("or"), serialNumberSql.lastIndexOf("or") + 2, "");
		serialNumberSql.append(" )");
		return serialNumberSql.toString();
	}
	
	private String generateCustomerIdQuery(String field,String serialNumbers) {
		String serialNums[] =serialNumbers.replaceAll(" ","").split(",");
		StringBuffer serialNumberSql = new StringBuffer();
		double loopNum = Math.ceil(serialNums.length/100f);
		List<String[]> bufferStrlist = new ArrayList<String[]>();
		for(int i=0;i<loopNum;i++){
			bufferStrlist.add(getBufferStr(serialNums,i,100));
		}
		serialNumberSql.append("and (") ;
		for (String[] buffer : bufferStrlist) {
			serialNumberSql.append(" "+field+" in ("+ArrayTransitionUtils.stringArrayToInString(buffer)+") ");
			serialNumberSql.append(" or ");
		}
		serialNumberSql.replace(serialNumberSql.lastIndexOf("or"), serialNumberSql.lastIndexOf("or") + 2, "");
		serialNumberSql.append(" )");
		return serialNumberSql.toString();
	}
	
	private String generateMaintainContractNoSql(String serialNumbers) {
		String serialNums[] = serialNumbers.replaceAll("\r\n", "\n").split("\n");
		StringBuffer serialNumberSql = new StringBuffer();
		double loopNum = Math.ceil(serialNums.length/100f);
		List<String[]> bufferStrlist = new ArrayList<String[]>();
		for(int i=0;i<loopNum;i++){
			bufferStrlist.add(getBufferStr(serialNums,i,100));
		}
		serialNumberSql.append("and (") ;
		for (String[] buffer : bufferStrlist) {
			serialNumberSql.append(" maintain_contract_no in ("+ArrayTransitionUtils.stringArrayToInString(buffer)+") ");
			serialNumberSql.append(" or ");
		}
		serialNumberSql.replace(serialNumberSql.lastIndexOf("or"), serialNumberSql.lastIndexOf("or") + 2, "");
		serialNumberSql.append(" )");
		return serialNumberSql.toString();
	}

	public List<EquipmentInfo> queryEquipmentLikeSerialNumber(String serialNumber) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
		hqlHelper.addLike("serialNumber", serialNumber);
		hqlHelper.addEqual("deleted", "N");
		hqlHelper.addOrderBy("serialNumber","desc");
		return this.find(hqlHelper);
	}

	public Integer getCountByEquipment(String[] equipmentModelArray,
			String warrantyStatus, Integer departmentId, String installDateStr) {
		String sql=" select count(*) from V_CSS_EQUIPEMNT_INFO t where 1=1 ";
		if(equipmentModelArray!=null){
			sql+=" and t.equipment_model in ('"+StringUtils.join(equipmentModelArray,"','")+"')";
		}
		if(installDateStr!=null){
			sql+=" and to_char(t.install_date,'yyyy-MM')<='"+installDateStr+"' ";
		}
		if(warrantyStatus!=null){
			sql+=" and t.equipment_status='"+warrantyStatus+"'";
		}
		if(departmentId!=null){
			sql+=" and t.department_id="+departmentId;
		}
		return this.countBySQL(sql);
	}

	public EquipmentInfo findEquipmentByRecordId(String recordId) {
		if(StringUtilsExtends.isBlankOrEmpty(recordId))
			return null;
		HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
		hqlHelper.addEqual("recordId", recordId);
		return listFirstResult(this.find(hqlHelper));
	}

	public List<Object[]> findEquipmentForRevisit() {
		String sql = "select i.equipment_id," +
					 "i.equipment_status," +
					 "i.equipment_model," +
					 "i.department_id," +
					 "i.install_date," +
					 "i.branch_name," +
					 "i.manufacturer," +
					 "i.warranty_status," +
					 "i.atm_manager_tel," +
					 "i.atm_manager," +
					 "i.customer_id,i.equipment_type,i.serial_number,i.province_id from V_CSS_EQUIPEMNT_INFO i ";
		return this.findBySQL(sql, null,null);
//		return this.
	}

	public List<EquipmentInfo> queryEquipmentByAtmNumber(String atmNumber) {
		String sql="select e.* from V_CSS_EQUIPEMNT_INFO e  where deleted = 'N' ";
		if(StringUtils.isNotBlank(atmNumber)){
			sql+=" and lower(atm_number) = '" + atmNumber.toLowerCase() + "' ";
		}
		return this.findEntityListBySQL(sql, EquipmentInfo.class, null);
	}
	
	public List<EquipmentInfo> queryEquipmentByAtmNumberLikeCustomerCode(String atmNumber,
			String customerCode) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
		hqlHelper.addEqual("atmNumber", atmNumber);
		hqlHelper.addLike("customer.customerCode", customerCode);
		hqlHelper.addEqual("deleted", "N");
		return this.find(hqlHelper);
	}

	public List<EquipmentInfo> queryEquipmentByBranchName(String branchName, Page queryPage,Integer customerId) {
		String sql = "select e.* from V_CSS_EQUIPEMNT_INFO e  where 1=1 ";
		//插入同行的逻辑
		if(null!=customerId){
			sql+=" and e.customer_id in ("
				+" select a.id from v_crm_customer_info a start with a.id = "+customerId+" connect by prior a.parent_custimer_id = a.id "
				+" union all"
				+" select a.id from v_crm_customer_info a start with a.id = "+customerId+" connect by prior a.id = a.parent_custimer_id "
				+") ";
		}
		sql+=" and e.branch_name='"+branchName+"' ";
		if(queryPage == null){
			sql += " and rownum<=3";
		}
		return this.findEntityListBySQL(sql, EquipmentInfo.class, queryPage);
	}
	
	public List<EquipmentInfo> queryNotAdviceContNO(Page queryPage) {
		String sql = "select * from V_CSS_EQUIPEMNT_INFO t where "
			+"SALE_CONTRACT_NUMBER is null "
			+"and SALE_PROPERTY='01' "
			+"and MAINTAIN_CONTRACT_NO is null "
			+"and MANUFACTURER = 'GRGBANKING' "
			+"and EQUIPMENT_STATUS in (1,2)";
		
		return this.findEntityListBySQL(sql, EquipmentInfo.class, queryPage);
	}

	public List<Object[]> getContractMaintainLastBySerialNumber(
			String serialNumber) {
		String sql="select t.contract_number,t.start_date,t.end_date from v_crm_contractmaintain t where " +
				" t.equip_serial=? and t.deleteed='N' order by t.end_date desc";
		return this.findBySQL(sql,serialNumber);
	}

	public List<EquipmentInfo> queryEquipmentBySerialNumberAtmNumber(String serialNumber, String atmNumber) {
		HqlHelper hqlHelper=new HqlHelper(EquipmentInfo.class);
		hqlHelper.addEqual("atmNumber", atmNumber);
		hqlHelper.addLike("serialNumber", serialNumber);
		hqlHelper.addEqual("deleted", "N");
		return this.find(hqlHelper);
	}

	@Override
	public List<Object[]> getEquipmentByWX(String mobliePhone,
			String serialNumber, String branchName, String departmentName,Page queryPage) {
		// TODO Auto-generated method stub
		String sql="select t.equipment_id,t.serial_number,t.branch_name,t.equipment_model,"+
       " decode(t.install_type, 1, '穿墙', 2, '大堂') install_type,a.department_name,"+
       " decode(t.install_model,1,'在行',2,'离行',3,'现金清分中心') install_model,t.department_id " +
       " from V_CSS_EQUIPEMNT_INFO t,t_base_department a" +
       " where t.department_id = a.department_id  " ;
      
		   if(StringUtils.isNotEmpty(mobliePhone)){
				sql += " and exists (select 1 from t_equipment_contact b where b.equipment_id = t.equipment_id and b.deleted='N' and (b.moblie_phone = '"+mobliePhone+"' or b.telephone = '"+mobliePhone+"')) " ;
		   }
	       if(StringUtils.isNotEmpty(serialNumber)){
	    	   sql += " and t.serial_number='"+serialNumber+"' " ;
	       }
	       if(StringUtils.isNotEmpty(branchName)){
	    	   sql += " and t.branch_name like '%"+branchName+"%' " ;
	       }
	       if(StringUtils.isNotEmpty(departmentName)){
	    	   sql += " and a.department_name like '%"+departmentName+"%'";
	       }
	       
		return this.findBySQL(sql,queryPage,null);
	}

	@Override
	public JSONObject getEquipmentStatusById(String equipmentId) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.element("eptStatus", "正常");
		String sql = "select text,po_number from (select d.text, a.po_number, t.equipment_id, a.create_date  "
				+ " from t_services_task t,t_services_workform a,t_base_data_dictionary d"
				+ " where t.workform_id = a.workform_id and t.task_type = d.value "
				+ " and d.key = 'SERVICE_TASK_TYPE' and t.deleted = 'N' and t.status not in ('已完成', '已取消') " + " and t.equipment_id = ? "
				+ " order by a.workform_id desc )where rownum < 2 ";
		List<Object[]> list = this.findBySQL(sql, equipmentId);
		if (null != list && list.size() > 0) {
			json.element("eptStatus", list.get(0)[0]);
			json.element("poNumber", list.get(0)[1]);
		}
		return json;
	}

}
