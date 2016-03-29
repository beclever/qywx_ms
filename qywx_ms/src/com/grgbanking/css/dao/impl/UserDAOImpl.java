package com.grgbanking.css.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.grgbanking.common.utils.ArrayTransitionUtils;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.bean.CssUserBean;
import com.grgbanking.css.bean.Department;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.common.UserContext;
import com.grgbanking.css.dao.CssUserDAO;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.EquipmentConstants;
import com.grgbanking.css.util.HqlHelper;
import com.grgbanking.css.util.RoleConstants;


/**
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhang zhi
 * Date: 2010-4-20 下午09:52:44
 */
@Repository("userDAO")
public class UserDAOImpl extends CssBaseDAOImpl<CssUser, Integer> implements CssUserDAO<CssUser, Integer> {

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getUserByLoginName(java.lang.String)
	 */
	public CssUser getUserByLoginName(String loginName) {
		String hql="from CssUser t where upper(t.loginName)=? and t.deleted='"+CommonConstants.FLAG_N+"'";
		return this.get(hql,loginName.toUpperCase());
	}
	
	public CssUser getUserById(Integer userId) {
		String hql="from CssUser t where upper(t.userId)=? and t.deleted='"+CommonConstants.FLAG_N+"'";
		return this.get(hql,userId);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getUserList(com.grgbanking.framework.core.Page, com.grgbanking.soc.bean.system.UserBean)
	 */
	public List<CssUser> getUserList(Page queryPage, CssUserBean userBean) {
		HqlHelper or=new HqlHelper();
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		or.addLikeIgnoreCase("name",userBean.getName());
		
		or.addLikeIgnoreCase("loginName",userBean.getLoginName());
		//or.addEqual("employee.employeeNo",userBean.getEmployeeNo());
		hqlHelper.addLikeIgnoreCase("department.departmentName",userBean.getDepartmentName());
		if(userBean.getDepartmentId()!=null){
			hqlHelper.addIn("department.departmentId", this.getChildDepartmentId(userBean.getDepartmentId()));
		}
		//hqlHelper.addEqual("department.departmentId",userBean.getDepartmentId());
		hqlHelper.addEqual("sex", userBean.getSex());
		hqlHelper.addLike("mobilephone", userBean.getMobilephone());
		hqlHelper.addIn("engineerGradeH68N",userBean.getEngineerGradeH68N()!=null?userBean.getEngineerGradeH68N().split(","):null);
		if(userBean.getDeleted()==null)
		{
			hqlHelper.addEqualIgnoreCase("deleted","N");
		}
		else
		{
			hqlHelper.addEqualIgnoreCase("deleted",userBean.getDeleted());
		}
		hqlHelper.addOr(or);
		hqlHelper.setQueryPage(queryPage);
		return this.find(hqlHelper);
	}
	


	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getSelectUserName(java.lang.String, java.lang.Integer)
	 */
	public int getSelectUserName(String name, Integer userId) {
		String hql="select userId from CssUser where name='"+name+"'";
		if(userId!=0)
		{
			hql+=" and userId!="+userId;
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
	 * @see com.grgbanking.soc.dao.system.UserDAO#getSelectUserLongName(java.lang.String, java.lang.Integer)
	 */
	public int getSelectUserLongName(String LongName, Integer userId) {
		String hql="select u.loginName from CssUser u where loginName='"+LongName+"'";
		if(userId!=0)
		{
			hql+=" and userId!="+userId;
		}
		
		return this.find(hql).size();
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getSelectUserDel(java.lang.Integer)
	 */
	public boolean getSelectUserDel(Integer userId) {
		
		String hql="select u.deleted from CssUser u where u.deleted='Y' and userId="+userId;
		if(this.find(hql).size()>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getSelectUserDepartmentId(java.lang.Integer)
	 */
	public int getSelectUserDepartmentId(Integer departmentId) {
		
		String sql="select u.DEPARTMENT_ID from V_BASE_USER u where u.DEPARTMENT_ID="+departmentId;
		int num=this.executeSql(sql);
		return num;
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getAllUserList(com.grgbanking.framework.core.Page, com.grgbanking.soc.bean.system.UserBean)
	 */
	public List<CssUser> getAllUserList(Page queryPage, CssUserBean userBean,CssUserBean systemUser){
		//判断当前登录的用户的所属的部门类型
		if("A".equals(systemUser.getDepartmentType())){
			//如果部门类型为“A”，则为总部，可以查询到所有的用户
			//OR
			HqlHelper or=new HqlHelper();
			or.addLikeIgnoreCase("name", userBean.getName());
			or.addLikeIgnoreCase("loginName", userBean.getName());
			//or.addLikeIgnoreCase("department_name", userBean.getDepartmentName());
			//or.addLike("department", userBean.getDepartmentName());
			
			HqlHelper hqlHelper=new HqlHelper(CssUser.class);
			//hqlHelper.addEqual("department.departmentId", userBean.getDepartmentId());
			hqlHelper.addLikeIgnoreCase("department.departmentName", userBean.getDepartmentName());
			hqlHelper.addOr(or);
			hqlHelper.setQueryPage(queryPage);
			hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
			return this.find(hqlHelper);
		}
		else if("B".equals(systemUser.getDepartmentType())){
			//如果部门类型为“B”，则为区域，可以查询到区域和服务站的用户
			//OR
			HqlHelper or=new HqlHelper();
			or.addLikeIgnoreCase("name", userBean.getName());
			or.addLikeIgnoreCase("loginName", userBean.getName());
			//or.addLikeIgnoreCase("department_name", userBean.getDepartmentName());
			//or.addLike("department", userBean.getDepartmentName());
			
			HqlHelper child=new HqlHelper();
			child.addLike("department.departmentType", "B");
			child.addLike("department.departmentType", "C");
			
			HqlHelper hqlHelper=new HqlHelper(CssUser.class);
			
			//hqlHelper.addEqual("department.departmentType", "B or C");
			hqlHelper.addLikeIgnoreCase("department.departmentName", userBean.getDepartmentName());
			hqlHelper.addOr(or);
			hqlHelper.addOr(child);
			hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
			hqlHelper.setQueryPage(queryPage);
			return this.find(hqlHelper);
		}
		else{
			//其他则为服务站，只可以查询到服务站的用户
			//OR
			HqlHelper or=new HqlHelper();
			or.addLikeIgnoreCase("name", userBean.getName());
			or.addLikeIgnoreCase("loginName", userBean.getName());
			//or.addLikeIgnoreCase("department_name", userBean.getDepartmentName());
			//or.addLike("department", userBean.getDepartmentName());
			
			HqlHelper hqlHelper=new HqlHelper(CssUser.class);
			hqlHelper.addEqual("department.departmentType", systemUser.getDepartmentType());
			hqlHelper.addLikeIgnoreCase("department.departmentName", userBean.getDepartmentName());
			hqlHelper.addOr(or);
			hqlHelper.setQueryPage(queryPage);
			hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
			return this.find(hqlHelper);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getAllUser()
	 */
	public List<CssUser> getAllUser() {
		return this.find();
	}

	public Department getUserDepartment(Integer userId) {
		String hql="select department from CssUser o where o.userId="+userId;
		return (Department)getSession().createQuery(hql).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getUserListByDepartmentId(java.lang.Integer)
	 */
	public List<CssUser> getUserListByDepartmentId(Integer departmentId) {
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
		hqlHelper.addEqual("department.departmentId",departmentId);
		return this.find(hqlHelper);
	}
	
	public List<CssUser> getUserListbyDepartmentIdUserId(Integer departmentId,Integer UserId){
		String hql="from CssUser where deleted='N' and department.departmentId="+departmentId+" and userId!="+UserId;
		return this.find(hql);
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getUserByMobile(java.lang.String)
	 */
	public CssUser getUserByMobile(String mobile) {
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addLike("mobilephone", mobile.trim());
		List<CssUser> userList=this.find(hqlHelper);
		if(userList!=null&&userList.size()==1){
			return userList.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getUserByNameOrLoginNameAndDepartmentId(java.lang.String, java.lang.Integer)
	 */
	public CssUser getUserByNameOrLoginNameAndDepartmentId(String engineerNameOrLoginName, Integer departmentId) {
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addEqual("department.departmentId", departmentId);
		HqlHelper childHqlHelper=new HqlHelper();
		childHqlHelper.addEqual("name", engineerNameOrLoginName);
		childHqlHelper.addEqualIgnoreCase("loginName", engineerNameOrLoginName);
		hqlHelper.addOr(childHqlHelper);
		List<CssUser> userList=this.find(hqlHelper);
		if(userList!=null&&userList.size()==1){
			return userList.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getAllUserListByPermission(com.grgbanking.framework.core.Page, com.grgbanking.soc.bean.system.UserBean, com.grgbanking.soc.bean.system.UserBean)
	 */
	public List<CssUser> getAllUserListByPermission(Page queryPage,CssUserBean userBean, CssUserBean systemUser) {
		//判断当前登录的用户的所属的部门类型
		HqlHelper or=new HqlHelper();
		or.addLikeIgnoreCase("name", userBean.getName());
		or.addLikeIgnoreCase("loginName", userBean.getName());
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		if("A".equals(systemUser.getDepartmentType())){
			//如果部门类型为“A”，则为总部，可以查询到所有的用户
			hqlHelper.addLikeIgnoreCase("department.departmentName", userBean.getDepartmentName());
		}
		else if("B".equals(systemUser.getDepartmentType())){
			//如果部门类型为“B”，则为区域，可以查询到区域和服务站的用户
			hqlHelper.addLikeIgnoreCase("department.departmentName", userBean.getDepartmentName());
			List<Integer> deptIdList = this.getChildDepartmentId(systemUser.getDepartmentId());
			hqlHelper.addIn("department.departmentId", (deptIdList!=null&&deptIdList.size()>0?deptIdList:null));
		}
		else{
			//其他则为服务站，只可以查询到服务站的用户
			hqlHelper.addEqual("department.departmentId", systemUser.getDepartmentId());
		}
		hqlHelper.addOr(or);
		hqlHelper.setQueryPage(queryPage);
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
		return this.find(hqlHelper);
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#queryStationDirector(java.lang.Integer)
	 */
	public List<CssUser> queryStationDirector(Integer departmentId) {
		String sql="SELECT t.* from V_base_user t inner join V_base_role_user ru"
			+" on ru.user_id=t.user_id inner join V_base_role r on r.role_id=ru.role_id and r.code='"+RoleConstants.ROLE_STATION_DIRECTOR+"'"
			+" where t.department_id="+departmentId;
		List list=this.findEntityListBySQL(sql, CssUser.class, null);
		return list;
	}
	
	/*
	 * 找出某个部门中某个角色的用户
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getUserByDeptIdAndRoleCode(java.lang.Integer, java.lang.String)
	 */
	public List<Object[]> getUserByDeptIdAndRoleCode(Integer departmentId,String roleCode) {
        String sql="select u.user_id,u.name,u.mobilephone,u.department_id  from V_base_role t ,V_base_user u,V_base_role_user r "
        		+"where t.role_id=r.role_id and u.user_id=r.user_id  "
        		+"and u.department_id="+departmentId+" and t.code='"+roleCode+"'";
        return this.findBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getUserByUserCode(java.lang.String)
	 */
	public CssUser getUserByUserCode(String userCode) {
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		hqlHelper.addEqual("deleted", CommonConstants.FLAG_N);
		hqlHelper.addEqual("userCode", userCode.trim());
		List<CssUser> userList=this.find(hqlHelper);
		if(userList!=null&&userList.size()==1){
			return userList.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getSelectListH68N(java.lang.Integer, java.lang.Integer)
	 */
	public List<Object[]> getSelectListH68N(Integer rowBegin, Integer rowEnd) {
		String sql="";
		if(UserContext.getUser().getDepartmentType().equals("B")){
			sql=" select x.* from(select a.*,rownum nums from(  (" +
	        " select dname,equ,nvl(us,0),depart from (select d.department_name dname,count(e.equipment_id) equ, " +
	        " (select q.department_id from V_base_department q where q.department_type='B' start with q.department_id=d.department_id " +
	        "  connect by prior  q.parent_id=q.department_id) depart from  V_base_department d " +
	        " left join V_EMS_EQUIPMENT_INFO e  on d.department_id=e.department_id where e.equipment_status in(1,2) and e.equipment_model in ("+EquipmentConstants.EQUIPMENT_EQUIPMENT_MODEL_H68N_IN+") " +
	        " group by d.department_name,d.department_id ) x left join ( select d.department_name,count(u.user_id) us from  V_base_department d  " +
	        " left join V_base_user u on d.department_id=u.department_id where u.engineer_grade_h68n is not null  group by d.department_name " +
	        " ) y on x.dname=y.department_name where (nvl(us,0)/equ)<(1/15) and depart="+UserContext.getUser().getDepartmentId()+" order by us desc ) a ) )x where x.nums between "+rowBegin+" and "+rowEnd+" ";
		}else{
			sql=" select x.* from(select a.*,rownum nums from(  (" +
			" select dname,equ,nvl(us,0) from (select d.department_name dname,count(e.equipment_id) equ from  V_base_department d " +
			" left join V_EMS_EQUIPMENT_INFO e  on d.department_id=e.department_id where e.equipment_status in(1,2) and e.equipment_model in ("+EquipmentConstants.EQUIPMENT_EQUIPMENT_MODEL_H68N_IN+") " +
			" group by d.department_name ) x left join ( select d.department_name,count(u.user_id) us from  V_base_department d  " +
			" left join V_base_user u on d.department_id=u.department_id where u.engineer_grade_h68n is not null and u.deleted='N' group by d.department_name " +
			" ) y on x.dname=y.department_name where (nvl(us,0)/equ)<(1/15) order by us desc ) a ) )x where x.nums between "+rowBegin+" and "+rowEnd+" ";
		}
		return this.findBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getTotalListH68N()
	 */
	public Integer getTotalListH68N() {
		String sql="";
		if(UserContext.getUser().getDepartmentType().equals("B")){
			sql=" select count(*) from( select dname,equ,nvl(us,0),depart from (select d.department_name dname,count(e.equipment_id) equ,(select q.department_id from V_base_department q " +
				" where q.department_type='B' start with q.department_id=d.department_id connect by prior  q.parent_id=q.department_id) depart " +
				" from  V_base_department d left join V_EMS_EQUIPMENT_INFO e  on d.department_id=e.department_id where e.equipment_status in(1,2) and e.equipment_model in ("+EquipmentConstants.EQUIPMENT_EQUIPMENT_MODEL_H68N_IN+") " +
				" group by d.department_name,d.department_id ) x left join ( select d.department_name,count(u.user_id) us  from  V_base_department d " +
				" left join V_base_user u on d.department_id=u.department_id where u.engineer_grade_h68n is not null  group by d.department_name " +
				" ) y on x.dname=y.department_name where (nvl(us,0)/equ)<(1/15) and depart="+UserContext.getUser().getDepartmentId()+" ) ";
		}else{
			sql=" select count(*) from (select d.department_name dname,count(e.equipment_id) equ from  V_base_department d " +
			" left join V_EMS_EQUIPMENT_INFO e  on d.department_id=e.department_id where e.equipment_status in(1,2) and e.equipment_model in ("+EquipmentConstants.EQUIPMENT_EQUIPMENT_MODEL_H68N_IN+") " +
			" group by d.department_name ) x left join (select d.department_name,count(u.user_id) us from  V_base_department d " +
			" left join V_base_user u on d.department_id=u.department_id where u.engineer_grade_h68n is not null  group by d.department_name " +
			" ) y on x.dname=y.department_name where (nvl(us,0)/equ)<(1/15) ";
		}
		
		return this.countBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getSelectUserCode(java.lang.String, java.lang.Integer)
	 */
	public int getSelectUserCode(String code, Integer userId) {
		String hql="select u.userCode from CssUser u where userCode='"+code+"'";
		if(userId!=0)
		{
			hql+=" and userId!="+userId;
		}
		
		return this.find(hql).size();
	}

	
	public boolean hasMoblie(String userName) {
		String sql="select count(*) from v_ma_phone_info t where t.USER_NAME=?";
		Integer count=this.countBySQL(sql, userName);
		if(count>0){
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.system.UserDAO#getUserByChargeName(java.lang.String)
	 */
	public CssUser getUserByChargeName(String name) {
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		hqlHelper.addEqual("name", name);
		List<CssUser> userList=this.find(hqlHelper);
		if(userList!=null&&userList.size()==1){
			return userList.get(0);
		}else if(userList!=null&&userList.size()>1){
			for(CssUser us:userList){
				if(us.getDeleted().equals("N")){
					return us;
				}
			}
		}
		return null;
	}

	public List<CssUser> getTechSupportUserList(Page queryPage, CssUserBean userBean) {
		String sql = "select distinct ru.user_id from V_base_role r,V_base_role_user ru where r.role_id=ru.role_id and r.code in ('ROLE_TECHNICAL SUPPORT_ENGINEER','ROLE_REGION_TECHNICALSUPPORT_ENGINEER')";
		List<Map> tmp = findMapResultBySql(sql);
		List<Integer> array = new ArrayList<Integer>();
		for(Map obj : tmp){
			array.add(Integer.valueOf( obj.get("USER_ID").toString()));
		}
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		hqlHelper.addIn("department.departmentId", userBean.getDepartmentIds());
		hqlHelper.addLike("name", userBean.getName());
		if(userBean.getDepartmentId()!=null){
			hqlHelper.addIn("department.departmentId", this.getChildDepartmentId(userBean.getDepartmentId()));
		}
		hqlHelper.addEqual("deleted", "N");
		hqlHelper.addIn("userId", array);
		hqlHelper.setQueryPage(queryPage);
		return this.find(hqlHelper);
	}
	

	public List<CssUser> queryUserList(Page queryPage, CssUserBean userBean) {
		String sql = "select distinct ru.user_id from V_base_role r,V_base_role_user ru where r.role_id=ru.role_id ";
		if(StringUtils.isNotBlank(userBean.getRoleString())){
			String[] rolecodes = userBean.getRoleString().split(",");
			String rolecodestr = ArrayTransitionUtils.stringArrayToInString(rolecodes);
			sql +=" and r.code in ( "+rolecodestr+" )";
		}
		List<Map> tmp = findMapResultBySql(sql);
		List<Integer> array = new ArrayList<Integer>();
		for(Map obj : tmp){
			array.add(Integer.valueOf( obj.get("USER_ID").toString()));
		}
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		hqlHelper.addIn("department.departmentId", userBean.getDepartmentIds());
		hqlHelper.addLike("name", userBean.getName());
		if(userBean.getDepartmentId()!=null){
			hqlHelper.addIn("department.departmentId", this.getChildDepartmentId(userBean.getDepartmentId()));
		}
		hqlHelper.addEqual("deleted", "N");
		hqlHelper.addIn("userId", array);
		hqlHelper.setQueryPage(queryPage);
		return this.find(hqlHelper);
	}


	public List<CssUser> getUserByLikeName(String name) {
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		hqlHelper.addLikeIgnoreCase("name",name);
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
		return this.find(hqlHelper);
	}

	public List<CssUser> getUserListByDepartmentId(List<Integer> departmentId) {
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		hqlHelper.addEqual("deleted",CommonConstants.FLAG_N);
		hqlHelper.addIn("department.departmentId",departmentId);
		return this.find(hqlHelper);
	}
	
	
	/**
	 * 根据分子公司查找片区以下的的用户ID
	 * @return
	 */
	public Integer[] getUserIdByDepartmentTypeB(Integer departmentId) {
		String sql = "select u.user_id,u.name from V_base_user u where u.department_id in "
				+ " (select t.department_id from V_base_department t where t.department_type in ('B','C','D') "
				+ " start with t.department_id = "+departmentId+" connect by prior t.department_id = t.parent_id) "
				+ " and u.deleted = 'N' ";
		List<Object[]> list = this.findBySQL(sql);
		Integer[] ints = new Integer[list.size()];
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ints[i] = new Integer(list.get(i)[0].toString());
			}
		}
		return ints;
	}
	

	/**
	 * 根据总部查找片区以下的的用户ID
	 * @return
	 */
	public Integer[] getUserIdByDepartmentTypeA() {
		CssUserBean user = UserContext.getUser();
		if (user.getDepartmentType().equals(CommonConstants.DEPT_TYPE_HEAD)) {
			String sql = "select u.user_id,u.name from V_base_user u where u.department_id = 20 and u.deleted = 'N' ";
			List<Object[]> list = this.findBySQL(sql);
			Integer[] ints = new Integer[list.size()];
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ints[i] = new Integer(list.get(i)[0].toString());
				}
			}
			return ints;
		}
		return null;
	}

	public List<CssUser> getUserListBySupportDepartment(Page queryPage,
			CssUserBean userBean) {
		HqlHelper or=new HqlHelper();
		HqlHelper hqlHelper=new HqlHelper(CssUser.class);
		or.addLikeIgnoreCase("name",userBean.getName());
		
		or.addLikeIgnoreCase("loginName",userBean.getLoginName());
		//or.addEqual("employee.employeeNo",userBean.getEmployeeNo());
		hqlHelper.addLikeIgnoreCase("department.departmentName",userBean.getDepartmentName());
		if(userBean.getDepartmentId()!=null){
			HqlHelper ordep=new HqlHelper();
			ordep.addIn("department.departmentId", this.getChildDepartmentId(userBean.getDepartmentId()));
			ordep.addIn("belongDepartment.departmentId", this.getChildDepartmentId(userBean.getDepartmentId()));
			hqlHelper.addOr(ordep);
		}
		//hqlHelper.addEqual("department.departmentId",userBean.getDepartmentId());
		hqlHelper.addEqual("sex", userBean.getSex());
		hqlHelper.addLike("mobilephone", userBean.getMobilephone());
		hqlHelper.addIn("engineerGradeH68N",userBean.getEngineerGradeH68N()!=null?userBean.getEngineerGradeH68N().split(","):null);
		if(userBean.getDeleted()==null)
		{
			hqlHelper.addEqualIgnoreCase("deleted","N");
		}
		else
		{
			hqlHelper.addEqualIgnoreCase("deleted",userBean.getDeleted());
		}
		hqlHelper.addOr(or);
		hqlHelper.setQueryPage(queryPage);
		return this.find(hqlHelper);
	}
}
