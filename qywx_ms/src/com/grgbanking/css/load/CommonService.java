package com.grgbanking.css.load;

import java.util.List;

import com.grgbanking.css.bean.City;
import com.grgbanking.css.bean.CssUserBean;
import com.grgbanking.css.bean.DataDictionary;
import com.grgbanking.css.bean.Department;
import com.grgbanking.css.bean.DepartmentBean;
import com.grgbanking.css.bean.Downtown;
import com.grgbanking.css.bean.Province;
import com.grgbanking.css.common.Page;

/**
 * 公用业务逻辑处理类
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-31 下午05:19:27
 */
public interface CommonService {
	
	/**
	 * 根据查询条件查出数据字典列表
	 * @param queryPage
	 * @param dataDictionaryBean
	 * @return
	 */
	public List<DataDictionary> findDataDictionary(Page queryPage , DataDictionary dataDictionaryBean);
	
	/**
	 * 此方法返回所有部门信息的一个JSON字符串
	 * @return
	 */
	public List<DepartmentBean> getAllDepartment();

	/**
	 * 查询所有省份
	 * @return
	 */
	public List<Province> findAllProvince();

	/**
	 * 查询所有城市
	 * @return
	 */
	public List<City> findAllCity();
	
	/**
	 * 返回所有的部门
	 * @return
	 */
	public List<DepartmentBean> getDepartmentList();

	/**
	 * 根据当前登录人所属部门查询区域服务站列表..
	 * @return
	 */
	public List<DepartmentBean> getRegionStation();
	
	/**
	 * 查找省平台下的所有服务站，包含省平台
	 * @return
	 */
	public List<DepartmentBean> getSegmentStation();

	/**
	 * 查询所有区域列表
	 * @return
	 */
	public List<Department> getRegionList();
	
	/**
	 * 查询对应用户的所有的设备序列号
	 * @param id
	 * @return
	 */
	public String getEquipmentSerial(Integer id);

	/**
	 * @param queryPage
	 * @param dataDictionaryBean
	 * @return
	 */
	public List<DataDictionary> getAllDataDictionaryList(Integer...integers);
	
	/**
	 * 根据查询条件查出区县列表
	 * @param queryPage
	 * @param dataDictionaryBean
	 * @return
	 */
	public List<Downtown> findAllDowntown();

	/**
	 * 查询所有部门
	 * @return
	 */
	public List<DepartmentBean> getAllDepartmentIgnore();
	
	/**
	 * 查询所有人员
	 * @return
	 */
	public List<CssUserBean> getAllUser();

	/**
	 * @param departmentId
	 * @return List<DepartmentBean>
	 * @description
	 * 根据父节点查找下面所有部门,包括其自身
	 */
	public List<DepartmentBean> findDepatmentByParent(Integer departmentId);
}
