package com.grgbanking.css.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.grgbanking.common.utils.ArrayTransitionUtils;
import com.grgbanking.common.utils.ListUtils;
import com.grgbanking.common.utils.StringUtilsExtends;
import com.grgbanking.css.bean.CssUserBean;
import com.grgbanking.css.bean.Department;
import com.grgbanking.css.bean.DepartmentBean;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.common.UserContext;
import com.grgbanking.css.dao.DepartmentDAO;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.HqlHelper;


/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: Administrator
 * Date: 2010-6-4 上午09:45:08
 */
@Repository("departmentDAO")
public class DepartmentDAOImpl extends CssBaseDAOImpl<Department, Integer> implements DepartmentDAO<Department, Integer> {

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getDepartmentList(com.grgbanking.framework.core.Page, com.grgbanking.soc.bean.stationmanagement.DepartmentBean)
	 */
	public List<Department> getDepartmentList(Page queryPage,
			DepartmentBean departmentBean) {
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addLikeIgnoreCase("departmentCode", departmentBean.getDepartmentCode());
		hqlHelper.addLikeIgnoreCase("departmentName", departmentBean.getDepartmentName());
		
		//部门多选
		if(StringUtils.isNotBlank(departmentBean.getDepartmentIds())){
			String[] departmentIdArray =departmentBean.getDepartmentIds().split(",");
			List<Integer> departmentIdList=new ArrayList<Integer>();
			for (String string : departmentIdArray) {
				if(StringUtils.isNotBlank(string)){
					departmentIdList.add(Integer.valueOf(string));
				}
			}
			hqlHelper.addIn("departmentId",departmentIdList);
		}else{
			hqlHelper.addEqual("departmentId", departmentBean.getDepartmentId());
		}
		
		//上级部门多选
		if(StringUtils.isNotBlank(departmentBean.getParentIds())){
			String[] departmentIdArray1 =departmentBean.getParentIds().split(",");
			List<Integer> departmentIdList1=new ArrayList<Integer>();
			for (String string : departmentIdArray1) {
				if(StringUtils.isNotBlank(string)){
					departmentIdList1.add(Integer.valueOf(string));
				}
			}
			hqlHelper.addIn("parent.departmentId",departmentIdList1);
		}else{
			hqlHelper.addEqual("parent.departmentId", departmentBean.getParentIds());
		}
		hqlHelper.addEqual("departmentType",departmentBean.getDepartmentType());
		hqlHelper.addEqual("province",departmentBean.getProvince());
		hqlHelper.addEqual("city", departmentBean.getCity());
		hqlHelper.addIn("grade", departmentBean.getGrade()!=null?departmentBean.getGrade().split(","):null);
		//hqlHelper.addLikeIgnoreCase("parent.departmentName", departmentBean.getParentName());
		hqlHelper.addEqual("directorUserId", departmentBean.getDirectorUserId());
		hqlHelper.addDateBetween("setupDate", departmentBean.getStartCreateDate(), departmentBean.getEndCreateDate());
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
		hqlHelper.setQueryPage(queryPage);
		return this.find(hqlHelper);
	}



	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getSelectDepartmentName(java.lang.String)
	 */
	public int getSelectDepartmentName(String departmentName,Integer departmentId) {
	
		String hql="select d.departmentId from Department d where departmentName='"+departmentName+"'";
		if(departmentId!=0)
		{
			hql+=" and d.departmentId!="+departmentId;
		}
		List list=new ArrayList();
		list=this.find(hql);
		int num=list.size();
		if(num>0)
		{
			num=Integer.parseInt(list.get(0).toString());
		}
		
		return num;
	}



	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getSelectDepartmentCode(java.lang.String)
	 */
	public int getSelectDepartmentCode(String departmentCode,Integer departmentId) {
		String hql="select d.departmentCode from Department d where d.departmentCode='"+departmentCode+"'";
		if(departmentId!=0)
		{
			hql+=" and departmentId!="+departmentId;
		}
		return this.find(hql).size();
	}

    public int getSelectParedId(Integer departmentId)
    {
    	String hql="select departmentName from Department where parent.departmentId="+departmentId;
    	return this.find(hql).size();
    }



	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getAllDepartment()
	 */
	public List<Department> getAllDepartment() {
		return this.find();
	}


	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getAllRegion()
	 */
	public List<Department> getAllRegion() {
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addEqual("departmentType", CommonConstants.DEPT_TYPE_REGION);
		hqlHelper.addOrderBy("departmentName","desc");
		return this.find(hqlHelper);
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getAllRegion()
	 */
	public List<Department> getAllRegionAndYw() {
		HqlHelper or = new HqlHelper();
		or.addEqual("departmentId", 20);
		HqlHelper or2 = new HqlHelper();
		or2.addEqual("deleted", CommonConstants.FLAG_N);
		or2.addEqual("departmentType", CommonConstants.DEPT_TYPE_REGION);
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addArrayOR(or,or2);
		hqlHelper.addOrderBy("departmentType","asc");
		return this.find(hqlHelper);
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getChildDepartmentId(java.lang.Integer)
	 */
	public List<Integer> getChildDepartmentId(Integer departmentId) {
		List<Integer> equipmentIdList=new ArrayList<Integer>();
		String sql="select t.department_id from V_base_department t start with t.parent_id=?"
			+" connect by prior t.department_id=t.parent_id";
		List list=this.findBySQL(sql,departmentId);
		for (Object obj : list) {
			equipmentIdList.add(Integer.valueOf(obj.toString()));
		}
		return equipmentIdList;
	}


	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getAllRootDepartment()
	 */
	public List<Department> getAllRootDepartment() {
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addIsNull("parent");
		return this.find(hqlHelper);
	}



	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getDepartmentByName(java.lang.String)
	 */
	public Department getDepartmentByName(String name) {
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addEqual("departmentName", name);
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
		List<Department> list=this.find(hqlHelper);
		if(list.size()>0 && list!=null){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.UpgradePlanDAO#getDepartmentString(java.lang.String)
	 */
	public String getDepartmentNames(String departmentIds) {
		if(StringUtilsExtends.isBlankOrEmpty(departmentIds))
			return null;
		HqlHelper hqlHelper = new HqlHelper(Department.class);
		hqlHelper.addIn("departmentId", ArrayTransitionUtils.stringToIntegerArray(departmentIds, ","));
		List<Department> list = find(hqlHelper);
		StringBuffer departmentNames = new StringBuffer(); 
		if(ListUtils.isNotEmpty(list)){
			for (Department department : list) {
				departmentNames.append(department.getDepartmentName());
				departmentNames.append(",");
			}
		}
		return ArrayTransitionUtils.sliceOffSeparator(departmentNames.toString());
	}


	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getChild(java.lang.Object)
	 */
	public List<Department> getChild(Integer parentId) {
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addEqual("parent.departmentId", parentId);
		return this.find(hqlHelper);
	}
	
	public List<Department> getChildBytype(Integer parentId,String type) {
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addEqual("departmentType", type);
		hqlHelper.addEqual("parent.departmentId", parentId);
		return this.find(hqlHelper);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getDepartmentBySegmentId(java.lang.Object)
	 */
	public List<Department> getDepartmentBySegmentId(Integer departmentId) {
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addIn("departmentId", this.getChildDepartmentIdForSegment(departmentId));
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		return this.find(hqlHelper);
	}


	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getAllStationByDepartmentId(java.lang.Object)
	 */
	public List<Department> getAllStationByDepartmentId(Integer departmentId) {
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		if(departmentId != null)
			hqlHelper.addIn("departmentId", this.getChildDepartmentId(departmentId));
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addEqual("departmentType", CommonConstants.DEPT_TYPE_STATION);
		hqlHelper.addOrderBy("province,city", "asc");
		
		return this.find(hqlHelper);
	}



	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getGradeRegionCount()
	 */
	public int getGradeRegionCount(String grade,Integer departmentId,String createDate) {
		String hql=null;
		
		if(UserContext.getUser().getDepartmentType().equals(CommonConstants.DEPT_TYPE_HEAD)){
			hql="select count(*) from Department t where t.departmentType='C' and t.grade='"+grade+"' and t.parent.parent.departmentId="+departmentId;
		}else if(UserContext.getUser().getDepartmentType().equals(CommonConstants.DEPT_TYPE_STATION)){
			hql="select count(*) from Department t where t.departmentType='C' and t.grade='"+grade+"'  and t.parent.parent.departmentId="+departmentId+" and t.departmentId="+UserContext.getUser().getDepartmentId()+" ";
		}else{
			List<Integer> departlist = null;
			departlist = this.getChildDepartmentId(UserContext.getUser().getDepartmentId());
			hql="select count(*) from Department t where t.departmentType='C' and t.grade='"+grade+"' and t.parent.parent.departmentId="+departmentId+" and t.departmentId in ("+StringUtils.join(departlist,",") +") ";
		}
		if(createDate!=null){
			hql+=" and to_char(t.createDate,'yyyy')=to_char(sysdate,'yyyy') ";
		}
		hql+=" and t.deleted='N' ";
		return this.countByHQL(hql);
	}
	
	/**
	 * 根据省平台ID查询其所有下级部门（包含自身ID）
	 * @param departmentId
	 * @return
	 */
	public List<Department> getChildDepartmentIdForSeg(Integer departmentId,String type){
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addEqual("provincePlatformId", departmentId);
		hqlHelper.addEqual("departmentType", type);
		return this.find(hqlHelper);
	}



	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getParentDepartment(java.lang.Object)
	 */
	public Department getParentDepartment(Integer departmentId) {
		
		String sql="select t.department_id,t.department_name,t.department_code from V_base_department t where  t.department_type='B' start with t.department_id="+departmentId+" "
			+" connect by prior  t.parent_id=t.department_id ";
		List<Object[]> list=this.findBySQL(sql);
		if(list!=null && list.size()>0){
			Department department=new Department();
			Object[] ob=list.get(0);
			department.setDepartmentId(Integer.parseInt(ob[0].toString()));
			department.setDepartmentName(ob[1].toString());
			department.setDepartmentCode(ob[2]!=null?ob[2].toString():"");
			return department;
		}
		return null;
	}
	
	public Department getParentDepartment(String departmentType,
			Integer departmentId) {
		String sql="select t.department_id,t.department_name,t.department_code from V_base_department t where  t.department_type='"+departmentType+"' start with t.department_id="+departmentId+" "
		+" connect by prior  t.parent_id=t.department_id ";
	List<Object[]> list=this.findBySQL(sql);
	if(list!=null && list.size()>0){
		Department department=new Department();
		Object[] ob=list.get(0);
		department.setDepartmentId(Integer.parseInt(ob[0].toString()));
		department.setDepartmentName(ob[1].toString());
		if(ob[2]!=null){
			department.setDepartmentCode(ob[2].toString());
		}
		return department;
	}
	return null;
	}



	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getDepartmentLikeName(java.lang.String)
	 */
	public List<Department> getDepartmentLikeName(String name) {
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addLikeIgnoreCase("departmentName",name);
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
		return this.find(hqlHelper);
	}



	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.stationmangement.DepartmentDAO#getChildDepartmentIdAll(java.lang.Object)
	 */
	public List<Integer> getChildDepartmentIdAll(Integer departmentId) {
		List<Integer> equipmentIdList=new ArrayList<Integer>();
		String sql="select t.department_id from V_base_department t start with t.department_id=?"
			+" connect by prior t.department_id=t.parent_id";
		List list=this.findBySQL(sql,departmentId);
		for (Object obj : list) {
			equipmentIdList.add(Integer.valueOf(obj.toString()));
		}
		return equipmentIdList;
	}
	
	public List<Department> getAllStationByDepartmentIdAll(Integer departmentId) {
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		if(departmentId != null)
			hqlHelper.addIn("departmentId", this.getChildDepartmentIdAll(departmentId));
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addEqual("departmentType", CommonConstants.DEPT_TYPE_STATION);
		hqlHelper.addOrderBy("province,city", "asc");
		
		return this.find(hqlHelper);
	}


	public Department getDepartmentByHrId(Integer hrDeptId) {
		
		if(null==hrDeptId||hrDeptId==0){
			return null;
		}
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addEqual("hrDeptId", hrDeptId);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		return listFirstResult(this.find(hqlHelper));
	}



	public Department getDepartmentByHrIdAll(Integer hrDeptId) {
		if(null==hrDeptId||hrDeptId==0){
			return null;
		}
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		hqlHelper.addEqual("hrDeptId", hrDeptId);
		return listFirstResult(this.find(hqlHelper));
	}



	public List<Integer> getSAModeDepartmentIdAll(Integer departmentId) {
		List<Integer> equipmentIdList=new ArrayList<Integer>();
		String sql="select t.to_department_id from V_base_department_sa_mode t where t.department_id=?";
		List list=this.findBySQL(sql,departmentId);
		for (Object obj : list) {
			equipmentIdList.add(Integer.valueOf(obj.toString()));
		}
		return equipmentIdList;
	}

	public List<Department> getSAModeDepartmentAll(Integer departmentId) {
		HqlHelper hqlHelper=new HqlHelper(Department.class);
		List<Integer> list= getSAModeDepartmentIdAll(departmentId);
		if(list!=null && list.size()>0){
			hqlHelper.addIn("departmentId",list);
			return this.find(hqlHelper);
		}else{
			return null;
		}
		
	}

	public List<Object[]> getCurrAndParents(Integer departmentId) {
		String sql = "select t.department_id, t.department_name, t.department_type,t.parent_id from V_base_department t where t.department_type  in ('B','D','C') "
			+" start with t.department_id = "+departmentId+" connect by prior t.parent_id = t.department_id ";
		return this.findBySQL(sql);
	}

	public JSONObject getDepartmnetJson(Integer departmentId) {
		List<Object[]> result = getCurrAndParents(departmentId);
		JSONObject json = null;
		if (null != result && result.size() > 0) {
			json = new JSONObject();
			for (int i = 0; i < result.size(); i++) {
				if (result.get(i)[2].equals("B")) {
					json.element("department1", result.get(i)[1]);
				} else if (result.get(i)[2].equals("D")) {
					json.element("department2", result.get(i)[1]);
				} else if (result.get(i)[2].equals("C")) {
					json.element("department3", result.get(i)[1]);
				}
			}
		}
		return json;
	}



	public List<Department> getAllRegionByCuurUser() {
		CssUserBean bean = UserContext.getUser();

		HqlHelper hqlHelper = new HqlHelper(Department.class);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addEqual("departmentType", CommonConstants.DEPT_TYPE_REGION);
		if (bean.getDepartmentType().equals(CommonConstants.DEPT_TYPE_REGION)) {
			hqlHelper.addEqual("departmentId", bean.getDepartmentId());
		}
		hqlHelper.addOrderBy("departmentName", "desc");
		return this.find(hqlHelper);
	}
	
	
}
