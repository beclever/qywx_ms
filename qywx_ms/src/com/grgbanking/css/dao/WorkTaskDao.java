package com.grgbanking.css.dao;

import java.util.Date;
import java.util.List;

import com.grgbanking.css.bean.equipment.EquipmentInfo;
import com.grgbanking.css.bean.work.WorkFormBean;
import com.grgbanking.css.bean.work.WorkFormRevisitPage;
import com.grgbanking.css.bean.work.WorkFormSearchPape;
import com.grgbanking.css.bean.work.WorkTask;
import com.grgbanking.css.bean.work.WorkTaskBean;
import com.grgbanking.css.bean.work.WorkTaskSearchPape;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;


/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: Administrator
 * Date: 2010-7-29 下午06:54:54
 */
public interface WorkTaskDao<T, ID> extends CssBaseDAO<T, ID> {
	
	/**
	 * 根据信息分页查询资源
	 * @param queryPage
	 * @param workTaskBean
	 * @return
	 */
	public List<WorkTask> getAllWorkTaskJsonList(Page queryPage,WorkTaskBean workTaskBean);
	public List<WorkTaskSearchPape> getAllWorkTaskJsonListBySQL(Page queryPage,WorkTaskBean workTaskBean);

	/**取消服务
	 * @param ids
	 */
	//public void updateCancelStatus(String code);

	/**计算所有对应编号的服务(任务),用于UpgradeService
	 * @param upgradeCode
	 * @param departmentId 
	 * @param equipmentStatusUse 
	 * @return
	 */
	public int countTask(String upgradeCode, String equipmentStatus, Integer departmentId);

	/**计算所有对应编号完成了的服务(任务),用于UpgradeService
	 * @param upgradeCode
	 * @return
	 */
	public int countTaskFinish(String upgradeCode, String equipmentStatus, Integer departmentId);

	/**根据服务(任务)所对应的设备,删除任务
	 * @param equipment
	 */
	//public void deleteTask(EquipmentInfo equipment);
	
	/**
	 * 以升级编号查询出指定服务列表
	 * @param upgradeCode
	 * @param upgradeCode 
	 */
	//public List<WorkTask> queryTaskEquipment(String upgradeCode, String status, String serialNumber);
	
	/**
	 * 根据设备序列号查询一个月是否有维修任务
	 * @param equipmentId
	 * @return
	 */
	public Integer getSelectReportTimeWXList(String equipmentId);
	
	/**
	 * 根据设备序列号查询最后一次任务的完成时间
	 * @param equipmentId
	 * @return
	 */
	public Date getSelectLastBYDate(String taskType,Integer equipmentId,String taskSource);
	
	/**
	 * 根据设备序列号查询保养计划时间
	 * @param serialNumber
	 * @return
	 */
	public List<WorkTask> getALLListWorktaskBYDate(String serialNumber);

	/**
	 * 根据设备序列号查询未完成任务（任务分配）
	 * @param serialNumber
	 * @return
	 */
	public List<WorkTask> queryUnProcessTaskForDispatch(String serialNumber);
	
	public List<Object[]> queryUnTaskForDispatch(String serialNumber);
	
	
	/**
	 * 查询未分配任务(服务站管理视图)
	 * @param serialNumber
	 * @return
	 */
	public List<WorkTask> queryUnAssignTaskForDispatch(String serialNumber) ;

	/**
	 * 工单查询
	 * @param queryPage
	 * @param workFormBean
	 * @return
	 */
	public List<WorkTask> queryWorkFormList(Page queryPage,WorkFormBean workFormBean);
	public List<WorkFormSearchPape> queryWorkFormListBYSQL(Page queryPage,WorkFormBean workFormBean);
	
	/**
	 *  根据状态和来源查询任务数量(taskSource为null则来源为非计划任务)
	 * @param status
	 * @param departmentId
	 * @return
	 */
	public Integer getSumSelectWorktask(String status,String taskSource,Integer departmentId,Integer userId);
	/**
	 * 未完成保养任务和未完成升级任务
	 * @param status
	 * @param status1
	 * @param taskType
	 * @param departmentId
	 * @return
	 */
	public Integer getSumSelectStatusWorktask(String status,String status1,String taskType,Integer departmentId);
	
	/**
	 * 首页显示计划任务
	 * @param deptId
	 * @param status
	 * @param taskSource
	 * @return
	 */
	public List<WorkTask> getAllWorkTaskList(Integer deptId,String status, String taskSource);
	
	/**
	 * 首页 未完成任务统计
	 * @param deptId
	 * @return
	 */
	public List<Object[]> getCountNotComplete(Integer deptId);

	/**
	 * @param equipmentId
	 * @return
	 */
	public int queryUnProcessTaskCountForStationView(Integer equipmentId);
	
	/**
	 * 取到各个工程师未完成的任务和工单数
	 * @param departmentId
	 * @return
	 */
	public List<Object[]> getUserListByDepartmentId(Integer departmentId);

	/**
	 * 每日服务统计：根据部门ID和时间统计当天的服务量
	 * @param departmentId
	 * @param datestr
	 * @return
	 */
	public List<Object[]> queryWorktaskGroupbyTaskType(Integer departmentId,
			String datestr);
	
	/**
	 * 根据设备id查找到此设备关联的所有的任务，且任务的状态是非“已完成”的
	 * @param equipmentId
	 * @return
	 */
	public List<WorkTask> getALLWorktaskListByEquipment(Integer equipmentId);
	
	/**
	 * 找出部门的所有客户
	 * @param departmentId
	 * @return
	 */
	public List<Object[]> getCustomerByDepartmentId(Integer departmentId);
	
	/**
	 * 根据任务id集合查找出任务
	 * @param taskIds
	 * @return
	 */
	public List<WorkTask> getWorkTaskByIds(List<Integer> taskIds);

	/**
	 * 根据设备ID，判断此设备是否有未完成的维修任务
	 * @param equipmentId
	 * @return
	 */
	public int getNotCompletedWX(Integer equipmentId);

	/**
	 * 保养计划：根据设备最后保养日期，查询其计划截止日期
	 * @param lastMaintainDate
	 * @param equipmentId 
	 * @return
	 */
	public Date queryBYTaskEndDate(Date lastMaintainDate, Integer equipmentId);

	/**
	 * 保养计划：根据时间段，查询保养次数
	 * @param equipmentId
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public int queryThisYearPMtimes(Integer equipmentId, String fromDate,
			String toDate);

	/**
	 * 查询未完成的保养任务（未分配，待处理）
	 * @param serialNumber
	 * @return
	 */
	public boolean queryHasUnFinishBY(String serialNumber);
	
	/**
	 * 查询本年度信息
	 * @param equipmentId
	 * @param status
	 * @return
	 */
	public List<Object[]> getQueryYearEorkTask(Integer equipmentId,String taskType);
	
	/**
	 * 根据服务单号查询信息
	 * @param processNo
	 * @return
	 */
	public WorkTask getSelectProcessNo(String processNo);

	/**
	 * 用于短信查询未分配的工单
	 * @param format
	 * @param departmentId
	 * @return
	 */
	public List<WorkTask> unAsignTaskForSMS(String format, Integer departmentId);
	
	
	public List<WorkTask> getSelectEquipmentMovement(Integer equipmentId);
	
	public String getMobileDateString(Integer workId,Integer type);

	/**
	 * @author:luowei
	 * @createDate 2013-3-6
	 * @param equipmentId
	 * @return List<WorkTask>
	 * @description
	 * 查询设备是否有未完成的自动升级任务
	 */
	public boolean hasAutoUpgradeWorktask(Integer equipmentId);
	
	/**
	 * 
	 * @author:guabin 
	 * @createDate 2013-4-2
	 * @param bean
	 * @param page
	 * @return
	 * @description
	 * 查询回访工单
	 */
	public List<WorkFormRevisitPage> findWorkFormListForRevisit(WorkFormBean bean,Page page);
	
	/**
	 * @author:luowei
	 * @createDate 2013-4-12
	 * @param upgradeCode
	 * @return List<Integer>
	 * @description
	 * 查询升级计划对应的所有设备ID
	 */
	public List<Integer> queryEquipmentListByUpgradeCode(String upgradeCode);
	
	/**
	 * @author:luowei
	 * @createDate 2013-5-22
	 * @param taskIds
	 * @param materialCode
	 * @param userId
	 * @param serialNumber 
	 * @return Integer
	 * @description
	 * 查询已使用但未出库的备件数量
	 */
	public Integer findSparepartUseNumber (String taskIds,String materialCode,Integer userId,String serialNumber);
	
	/**
	 * @author:luowei
	 * @createDate 2013-5-23
	 * @param workformId
	 * @return List<Integer>
	 * @description
	 * 查询工单所有任务ID
	 */
	public List<Integer> findWorkformTaskIdList(Integer workformId);
	
	/**
	 * 
	 * @param workformId
	 * @return
	 */
	public List<WorkTask> findWorkformTaskList(Integer workformId);
	/**
	 * 根据服务站ID和日期，查询服务一个月服务的设备台数
	 * @param departmentId
	 * @param dateStr
	 * @param taskType
	 * @return
	 */
	public int queryEquipmentCount(Integer departmentId, String dateStr,String taskType);
	
	/**
	 * 查询设备未分配保养--用于更新合同信息删除保养
	 * @param equipmentId
	 * @param status
	 * @return
	 */
	public List<WorkTask> getEquipmentWorktaskBYList(Integer equipmentId,String status);
	
	/**
	 * 根据报修人电话查询
	 * @param equipmentId
	 * @param status
	 * @return
	 */
	public List<WorkTask> getWorktaskBYReportTelephone(String reportTelephone);
	
	/**
	 * 根据设备ID，判断此设备是否有已完成的安装开通任务
	 * @param equipmentId
	 * @return
	 */
	public int getCompletedAZKT(Integer equipmentId);

}
