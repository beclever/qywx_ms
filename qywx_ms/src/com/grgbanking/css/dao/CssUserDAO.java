package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.bean.Department;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.bean.CssUserBean;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;


/**
 *用户数据访问对象
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-29 下午01:41:23
 */
public interface CssUserDAO<T, ID> extends CssBaseDAO<T, ID>{
	/**
	 * 根据用户登录名查询用户对象(登录名不区分大小写)
	 * @param loginName
	 * @return
	 */
	public CssUser getUserByLoginName(String loginName);
	
	public CssUser getUserById(Integer userId);
	
	List<CssUser> getUserList(Page queryPage,CssUserBean userBean);
	
	List<CssUser> getUserListBySupportDepartment(Page queryPage,CssUserBean userBean);
	
	List<CssUser> getTechSupportUserList(Page queryPage,CssUserBean userBean);
	
	List<CssUser> queryUserList(Page queryPage,CssUserBean userBean);
	
	/**
	 * 查询姓名是否重复
	 */
	public int getSelectUserName(String name,Integer userId);
	
	/**
	 * 查询用户名是否重复
	 */
	public int getSelectUserLongName(String LongName,Integer userId);
	public int getSelectUserCode(String code,Integer userId);
	
	/**
	 * 查询是否删除状态
	 */
	public boolean getSelectUserDel(Integer userId); 
	
	/**
	 * 查询用户部门编号
	 */
	public int getSelectUserDepartmentId(Integer departmentId);
	
	/**
	 * 查询用户部门编号
	 */
	public Department getUserDepartment(Integer userId);
	
	/**
	 * 权限管理  用户授权 使用的方法
	 * @param queryPage
	 * @param userBean
	 * @return
	 */
	public List<CssUser> getAllUserList(Page queryPage,CssUserBean userBean,CssUserBean user);
	
	/**
	 * 查找所有的用户
	 * @return
	 */
	public List<CssUser> getAllUser();
	
	/**
	 * 根据部门查询用户信息
	 * @param departmentId
	 * @return
	 */
	public List<CssUser> getUserListByDepartmentId(Integer departmentId);
	
	public List<CssUser> getUserListByDepartmentId(List<Integer> departmentId);
	
	/**
	 * 根据部门查询用户考核信息UserId不等于本身
	 * @param departmentId
	 * @param UserId
	 * @return
	 */
	public List<CssUser> getUserListbyDepartmentIdUserId(Integer departmentId,Integer UserId);

	/**
	 * 根据手机号码查询用户对象
	 * @param mobile
	 * @return
	 */
	public CssUser getUserByMobile(String mobile);
	
	
	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public CssUser getUserByChargeName(String name);

	/**
	 * 根据用户姓名或者登录名，和部门ID查询用户对象
	 * @param engineerNameOrLoginName
	 * @param departmentId
	 * @return
	 */
	public CssUser getUserByNameOrLoginNameAndDepartmentId(
			String engineerNameOrLoginName, Integer departmentId);

	/**
	 * 根据当前用的角色等级查找用户
	 * @param queryPage
	 * @param userBean
	 * @param systemUser
	 * @return
	 */
	public List<CssUser> getAllUserListByPermission(Page queryPage, CssUserBean userBean,CssUserBean systemUser);

	/**
	 * 根据部门查询服务站主任姓名
	 * @param departmentId
	 * @return
	 */
	public List<CssUser> queryStationDirector(Integer departmentId);
	
	/**
	 * 找出某个部门中某个角色的用户
	 * @param departmentId
	 * @param roleCode
	 * @return
	 */
	public List<Object[]> getUserByDeptIdAndRoleCode(Integer departmentId,String roleCode);
	
	public CssUser getUserByUserCode(String userCode);
	
	public List<Object[]> getSelectListH68N(Integer rowBegin,Integer rowEnd);
	public Integer getTotalListH68N();

	/**
	 * 判断工程师是否有配发的智能手机
	 * @return
	 */
	public boolean hasMoblie(String userName);
	
	public List<CssUser> getUserByLikeName(String name);
	

	/**
	 * 根据分子公司查找片区以下的的用户ID
	 * @return
	 */
	public Integer[] getUserIdByDepartmentTypeB(Integer departmentId);
	
	
	/**
	 * 根据总部查找片区以下的的用户ID
	 * @return
	 */
	public Integer[] getUserIdByDepartmentTypeA();
}
