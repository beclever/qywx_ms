package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.bean.CrmCustomer;
import com.grgbanking.css.bean.CustomerBean;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;

public interface CrmCustomerInfoDAO<T, ID> extends CssBaseDAO<T, ID> {
	
	List<CrmCustomer> getCrmCustomerList(Page queryPage,CustomerBean customerBean);
	
	public List<CrmCustomer> getAllCustomer();
	
	public List<Integer> getCrmCustomerIds(String customerName);
	
	/**
	 * 查询所有根客户
	 * @return
	 */
	public List<CrmCustomer> getAllRootCustomer();
	
	/**
	 * 根据服务站ID查询对应客户
	 * @param departmentIdList
	 * @return
	 */
	public List<CrmCustomer> getCustomerByDepartment(List<Integer> departmentIdList);
	
	/**
	 * 根据区域和省份查找客户信息
	 * @param region 区域
	 * @param province 省份
	 * @return 
	 */
	public List<Object[]> getCustomerByRegionAndProvince(Integer region,Integer province);
	
	/**
	 * 根据客户编码查询信息
	 * @param customerCode
	 * @return
	 */
	public CrmCustomer getCustomerByCode(String customerCode);
	
	/**
	 * 根据客户查询上级客户
	 * @param custometId
	 * @param customerLevel
	 * @return
	 */
	public CrmCustomer getUppeCustomerById(Integer custometId,String customerLevel);
	
	public String getCurstomerNames(String customerIds);
	
	/**
	 * 分页查询客户信息，这个方法主要在客户回访信息管理页面显示客户信息
	 * @param queryPage
	 * @param customerBean
	 * @return
	 */
	public List<Object[]> getCustomerListForRevisit(Page queryPage,CustomerBean customerBean);
	
	/**
	 * 根据计划查询客户
	 * @param upgradeCode
	 * @param parentCustomerId(if parentCustomerId=0 parentCustomerId is null )
	 * @param statusArray
	 * @return
	 */
	public List<Object[]> getWorkTaskObjectCustomerList(String upgradeCode,Integer parentCustomerId,String[] statusArray);
	
	/**
	 * 根据客户统计计划数量
	 * @param upgradeCode
	 * @param customerId
	 * @param statusArray
	 * @return
	 */
	public List<Object[]> getWorkTaskCustomerCountList(String upgradeCode,Integer customerId,String[] statusArray);
	
	//回访满意度报表(客户)
	public List<Object[]> getCustomerForReportUpper(String customerlevel,Integer upperCustomerId,String startDate,String endDate,Integer regionId);
	public List<Object[]> getCustomerForReportByCustomerId(Integer customerId,String startDate,String endDate);
	
	
	/**
	 * 根据区域查找出相应的客户
	 * @param regionId
	 * @return
	 */
	public List<Object[]> getCustomerForReport(Integer regionId);
	
	public List<Object[]> getCustomerForReport2(Integer regionId,String startDate,String endDate);
	
	
	
	/** 根据客户查询下级客户
	 * @param customerId
	 * @param customerLevel
	 * @return List<Customer>
	 * @description
	 */
	public List<Integer> getChildCustomer(Integer customerId,String customerLevel);
	
	/**
	 * 设备运行综合报表 根据用户选择的客户ID,查询该客户所对应的三级客户ID
	 * @param customerId
	 * @return
	 */
	public List<Object[]> queryChildrenbyCustomerId(Integer customerId);
	public Integer[] getDepartmenidbyCustomerId(Integer[] sustomers);
	
	
	
	/**
	 * 根据服务站ID和名称查询对应客户
	 * @param departmentIdList
	 * @return
	 */
	public List<CrmCustomer> getCustomerByCustomerName(List<Integer> departmentIdList,String name);

}
