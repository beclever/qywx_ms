package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.bean.work.WorkForm;
import com.grgbanking.css.bean.work.WorkFormBean;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;

/**
 * 工单DAO接口
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-19 下午03:58:02
 */
public interface WorkformDAO<T, ID> extends CssBaseDAO<T, ID> {

	/**
	 * 工单查询列表
	 * @param queryPage
	 * @param workformBean
	 * @return
	 */
	List<WorkForm> queryWorkFormList(Page queryPage, WorkFormBean workformBean);
	
	/**
	 * 工单查询列表 暂时调整只是所需的条件查询
	 * @param queryPage
	 * @param workformBean
	 * @return
	 */
	List<WorkForm> queryWorkFormListBySQL(Page queryPage,WorkFormBean workformBean);
	
	/**
	 * 工单查询列表 根据创建人查询
	 * @param queryPage
	 * @param workformBean
	 * @return
	 */
	List<WorkForm> queryWorkFormListByCreatUser(Page queryPage,WorkFormBean workformBean);
//	
//	/**
//	 * 根据状态查询工单数量(status1为null查询一个status,否则status or status1)
//	 * @param status
//	 * @param departmentId
//	 * @return
//	 */
//	public Integer getSumSelectWorkform(String status,String status1, Integer departmentId,String userId);
//
//
//	/**
//	 * 根据工单号查询工单
//	 * @param poNumber
//	 * @return
//	 */
//	public WorkForm getWorkformByPoNumber(String poNumber);
//
//	/**
//	 * 每日服务统计：根据日期，部门查询空工单数
//	 * @param departmentId
//	 * @param datestr
//	 * @return
//	 */
//	public int getEmptyWorkform(Integer departmentId, String datestr);
//
//	/**
//	 * 每日服务统计：根据日期，部门查询有效工单数
//	 * @param departmentId
//	 * @param datestr
//	 * @return
//	 */
//	int getEffectWorkform(Integer departmentId, String datestr);
//	
//	/**
//	 * 根据设备id查找到此设备关联的所有的工单，且工单的状态是非“已完成”
//	 * @param equipmentId
//	 * @return
//	 */
//	public List<WorkForm> getAllWorkformByEquipment(Integer equipmentId);
//	
//	/**
//	 * 工单查询，可找到子部门下的工单，在工单回访时要用到
//	 * @param queryPage
//	 * @param workformBean
//	 * @return
//	 */
//	public List<WorkForm> queryWorkFormListForRevisit(Page queryPage,WorkFormBean workformBean);
//
//	/**
//	 * 根据短信SMID查找工单对象
//	 * @param format
//	 * @param departmentId
//	 * @return
//	 */
//	WorkForm getWorkformBySMID(String format, Integer departmentId);
//
//	/**
//	 * 用于建行报告
//	 * @param equipmentId
//	 * @param days
//	 * @param workFinishDateStart
//	 * @param workFinishDateEnd
//	 * @return
//	 */
//	Object[] queryCCbankreportData(Integer equipmentId, int days,
//			String workFinishDateStart, String workFinishDateEnd);
//	
//	/**
//	 * 查询最后完成的工单
//	 * @param SerialNumber
//	 * @return
//	 */
//	public WorkForm getSerialNumberWorkform(String SerialNumber);
//	
//	
//	public List<Object[]> getSelectMovementStatistics(Integer rowBegin,Integer rowEnd,String type);
//	
//	public Integer getTotalMovementStatistics(String type);
//	
//	
//	public List<Object[]> getSelectMovementEquipment(Integer rowBegin,Integer rowEnd);
//	public Integer getTotalMovementEquipment();
//	
//	/**
//	 * 查询正在处理中的工单（工单完成时间为空，状态为处理中的工单）
//	 * @param serialNumber
//	 * @return
//	 */
//	public WorkForm getInprocessWorkformBySerialNumberForAOC(String serialNumber);
//	
//	public List<Object[]> getSelectWorkFormListH68N(Integer rowBegin,Integer rowEnd);
//	
//	public Integer getTotalWorkFormListH68N();
//	
//	/**
//	 * 日派单情况
//	 * @param departmentId
//	 * @return
//	 */
//	public List<Object[]> getTotalWorkFormPhone(Integer departmentId,String dateStart,String dateEnd);
//
//	/** 获取某分子公司下每天的工单总数
//	 * @author jeff steve . lin
//	 * @param deptId
//	 * @return
//	 */
//	public Integer getDailyWorkForm(Integer deptId);
//	
//	/**获取某分子公司每天录入的工单数
//	 * @author jeff steve . lin
//	 * @param deptId
//	 * @return
//	 */
//	public Integer getDailyEnterWorkForm(Integer deptId);
//	
//	/**获取某分子公司所有服务站的工程师人数（不包含分公司）
//	 * @author jeff steve . lin
//	 * @param deptId
//	 * @return
//	 */
//	public Integer getTotalEngineer(Integer deptId);
//	
//	/**获取某分子公司所有服务站每天服务的工程师人数（不包含分公司）
//	 * @author jeff steve . lin
//	 * @param deptId
//	 * @return
//	 */
//	public Integer getDailyServiceEngineer(Integer deptId);
//	
//	/**
//	 * 根据机芯类型统计设备数量
//	 * @param departmentId
//	 * @param materialName
//	 * @param taskType
//	 * @return
//	 */
//	public Integer sumWorkformNumByModel(Integer departmentId,String materialName,String taskType,String previousDate);
//	
//	/**
//	 * 根据设备安装日期统计工单总数
//	 * @param materialName
//	 * @param taskType
//	 * @param installDate
//	 * @return
//	 */
//	public Integer sumWorkformNumByInstallDate(String materialName,String taskType,String installDate,String currentDate);
//	
//	/**
//	 * 根据工单完成日期，获取工单总数(月份)
//	 * @param departmentId
//	 * @param finishMonth
//	 * @return
//	 */
//	public Integer countWorkformsByRegion(Integer departmentId,String finishMonth,String taskType);
//	
//	/**
//	 * 根据设备安装日期统计工单数
//	 * @param currentDate
//	 * @param installDate
//	 * @return
//	 */
//	public Integer countWorkformsByInstallDate(String currentDate,String installDate,String taskType);
//	
//	
//	/**
//	 * 统计服务工单审核情况
//	 * @param departmentId
//	 * @param fillDateStart
//	 * @param fillDateEnd
//	 * @return
//	 */
//	public Integer countWorkFormByFillDate(Integer departmentId,String fillDateStart,String fillDateEnd);
//	
//	/**
//	 * 应使用手机录单总数
//	 * @param departmentId
//	 * @return
//	 */
//	public Integer countWorkFormByPhone(Integer departmentId);
//	/**
//	 * 实际手机录单数
//	 * @param departmentId
//	 * @return
//	 */
//	public Integer countWorkFormByPhoneActual(Integer departmentId);
//	
//	/**
//	 * 及时录单数
//	 * @param departmentId
//	 * @return
//	 */
//	public Integer countWorkFormByFillDateTotal(Integer departmentId);
//	/**
//	 * 延迟录单明细
//	 * @return
//	 */
//	public List<Object[]> getDelayWorkFromBooks();
//	/**
//	 * 未使用手机录单明细
//	 * @return
//	 */
//	public List<Object[]> getUnPhoneWorkFromBooks();
//	/**
//	 * 每天录单的总工单数
//	 * @param deptId
//	 * @return
//	 */
//	public Integer geWorkFormCountFillDate(Integer deptId);
//	/**
//	 * 每日服务工单审核情况明细
//	 * @return
//	 */
//	public List<Object[]> getAuditDetaiWorkFromBooks(String fillDate);
//	
//	/**
//	 * 仓库管理员审核时，查询可以审核的服务站
//	 * @param departmentId
//	 * @return
//	 */
//	public List<Integer> getCheckWorkformDepartmentId(Integer departmentId);
//	
//	public List<Object[]> queryCheckWorkFormList(String statusDirector,String statusStorekeeper,Integer departmentId,List<Integer> departmentIds);
//	
//	public Integer getSumSelectCheckWorkform(String statusDirector,String statusStorekeeper,Integer departmentId,List<Integer> departmentIds);
//	
//	/**
//	 * 按设备的网点查询工单信息
//	 * @param branchName
//	 * @return
//	 */
//	public List<Object[]> queryWorkFormByBranchName(String branchName, Page queryPage,Integer customerId);
//	
//	/**
//	 * 工单满意度
//	 * @param page
//	 * @param workformBean
//	 * @return
//	 */
//	public List<Object[]> getWorkFormSatisfiedList(Page page,WorkFormBean workformBean);
//	
//	/**
//	 * 根据任务查询所有一周内未评价的工单
//	 * @param reportTelephone
//	 * @return
//	 */
//	public List<WorkForm> getAllWorkformByReportTelephone(Page queryPage,List<WorkTask> workTaskList);
	
}
