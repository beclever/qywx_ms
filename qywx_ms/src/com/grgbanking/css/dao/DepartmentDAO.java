package com.grgbanking.css.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.grgbanking.css.bean.DepartmentBean;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;

/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: Administrator
 * Date: 2010-6-4 上午09:43:18
 */
public interface DepartmentDAO<Department, Integer> extends CssBaseDAO<Department,Integer> {
	
	/**
	 * 根据条件分页查询资源列表
	 * @param queryPage
	 * @param resourceBean
	 * @return
	 */
	List<Department> getDepartmentList(Page queryPage,DepartmentBean departmentBean);
	
	/**
	 * 查询部门名称是否重复
	 */
	public int getSelectDepartmentName(String departmentName,Integer departmentId);
	
	/**
	 * 查询部门编号是否重复
	 */
	public int getSelectDepartmentCode(String departmentCode,Integer departmentId);
	
	/**
	 * 判断是否有子部门
	 * @param departmentId
	 * @return
	 */
	public int getSelectParedId(Integer departmentId);
	
	/**
	 * 此方法得到所有的部门信息  并封装成list集合返回
	 * @return
	 */
	public List<Department> getAllDepartment();

	/**
	 * 查询所有区域对象
	 * @return
	 */
	public List<Department> getAllRegion();
	
	/**
	 * 查询所有区域对象及运维部门
	 * @return
	 */
	public List<Department> getAllRegionAndYw();
	
	/**
	 * 查询所有区域对象
	 * @return
	 */
	public List<Department> getAllRegionByCuurUser();

	/**
	 * 根据部门ID查询其所有下级部门(不包括自身)
	 * @param departmentId
	 * @return
	 */
	public List<Integer> getChildDepartmentId(Integer departmentId);
	/**
	 *  根据部门ID查询其所有下级部门(包括自身)
	 * @param departmentId
	 * @return
	 */
	public List<Integer> getChildDepartmentIdAll(Integer departmentId) ;

	/**
	 * 查询所有根部门
	 * @return
	 */
	List<Department> getAllRootDepartment();
	
	/**
	 * 根据部门名字查询部门
	 * @param name
	 * @return
	 */
	public Department getDepartmentByName(String name);
	public List<Department> getDepartmentLikeName(String name);
	
	public String getDepartmentNames(String departmentIds);
	
	/**
	 * @param parentId
	 * @return
	 */
	public List<Department> getChild(Integer parentId);
	
	public List<Department> getChildBytype(Integer parentId,String type);
	
	/**
	 * 根据服务站查询区域
	 * @param departmentId
	 * @return
	 */
	public Department getParentDepartment(Integer departmentId);
	public Department getParentDepartment(String departmentType,Integer departmentId);
	
	/**
	 * 根据省平台id查找其下的所有服务站，包括它本身
	 * @param departmentId
	 * @return
	 */
	public List<Department> getDepartmentBySegmentId(Integer departmentId);

	/**
	 * 根据部门查询其下面所有服务站
	 * @param departmentId
	 * @return
	 */
	public List<Department> getAllStationByDepartmentId(Integer departmentId);
	
	/**
	 * 区域网点数量
	 * @return
	 */
	public int getGradeRegionCount(String grade,Integer departmentId,String createDate);
	
	/**
	 * 根据省平台ID查询其所有下级部门（包含自身ID）
	 * @param departmentId
	 * @return
	 */
	public List<Department> getChildDepartmentIdForSeg(Integer departmentId,String type);

	public List<Map> findMapResultBySql(String sql);
	
	public List<Department> getAllStationByDepartmentIdAll(Integer departmentId);
	
	/**
	 * 通过Hr的部门id获取部门
	 * @param hrDeptId
	 * @return
	 */
	public Department getDepartmentByHrId(Integer hrDeptId);
	/**
	 * 通过Hr的部门id获取部门 含已被删除部门
	 * @param hrDeptId
	 * @return 
	 */
	public Department getDepartmentByHrIdAll(Integer hrDeptId);
	
	/**
	 * 根据部门ID查询SA模式下部门(
	 * @param departmentId
	 * @return
	 */
	public List<Integer> getSAModeDepartmentIdAll(Integer departmentId);
	
	public List<Department> getSAModeDepartmentAll(Integer departmentId);
	
	/**
	 * 返回服务站、片区、组织的JSONObject列表   department_type  in ('B','D','C')
	 * @param departmentId
	 * @return List<Object[]>
	 */
	public List<Object[]> getCurrAndParents(Integer departmentId);

	/**
	 * 得到机构的分子公司，片区，服务站的信息
	 * @param parseInt
	 * @return
	 */
	JSONObject getDepartmnetJson(Integer departmentId);

}
