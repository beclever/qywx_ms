package com.grgbanking.css.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.grgbanking.css.bean.UpgradePlanBean;
import com.grgbanking.css.bean.equipment.EquipmentBean;
import com.grgbanking.css.bean.equipment.EquipmentInfo;
import com.grgbanking.css.bean.equipment.EquipmentIntegrationBean;
import com.grgbanking.css.bean.equipment.EquipmentSearchPape;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;


/**
 * 设备管理数据访问层
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-7-17 下午08:29:35
 */
public interface EquipmentDAO<T, ID> extends CssBaseDAO<T, ID> {

	/**
	 * 分页查询设备信息
	 * @param queryPage
	 * @param equipmentBean
	 * @return
	 */
	public List<EquipmentInfo> queryEquipmentList(Page queryPage,EquipmentBean equipmentBean);
	public List<EquipmentSearchPape> queryEquipmentListNew(Page queryPage,EquipmentBean equipmentBean);

	/**
	 * 根据设备序列号查找设备
	 * @param serialNumber
	 * @return
	 */
	public EquipmentInfo getEquipmentBySerialNumber(String serialNumber);
	
	/**
	 * 根据设备序列号查找设备(有权限)
	 * @param serialNumber
	 * @param departmentId
	 * @return
	 */
	public EquipmentInfo getEquipmentBySerialNumberDepartmentName(String serialNumber,Integer departmentId);
	
	/**
	 * 根据用户查询设备列表
	 * @param id
	 * @return
	 */
	public List getEquipmentByCustomerId(Integer id); 
	
	/**
	 * 根据部门统计出部门下的在用设备数量
	 * @param id
	 * @return
	 */
	public Integer countEquipmentByDepmentId(Integer id);
	
	/**
	 * 根据设备序列号查询信息
	 * @param serialNumber
	 * @return EquipmentInfo
	 */
	public EquipmentInfo getEquipmentInfo(String serialNumber);
	
	/**
	 * 根据升级条件查询出设备列表 
	 * @param upgradePlan
	 * @return
	 */
	public List<EquipmentInfo> getEquipmentList(UpgradePlanBean upgradePlan);
	public List<Integer> getEquipmentIdList(UpgradePlanBean upgradePlan);
	
	/**
	 *  根据设备状态查询设备数量
	 * @param equipmentStatus
	 * @return
	 */
	public Integer getSumEquipmentStatus(String equipmentStatus,Integer departmentId,String departmentType,String manufacturer);
	
	
	/**
	 * 根据设备状态查询设备数量(manufacturer not in)
	 * @param equipmentStatus
	 * @param departmentId
	 * @param departmentType
	 * @param manufacturer
	 * @return
	 */
	public Integer getSumEquipmentStatusNoManufacturer(String equipmentStatus,Integer departmentId,String departmentType,String manufacturer);
	
	
	/**
	 * 根据设备类型查询设备数量
	 * @param equipmentType
	 * @return
	 */
	public Integer getSumEquipmentType(String equipmentType,Integer departmentId,String departmentType,String manufacturer);
	
	/**
	 * 根据设备类型查询设备数量(manufacturer not in)
	 * @param equipmentType
	 * @param departmentId
	 * @param departmentType
	 * @param manufacturer
	 * @return
	 */
	public Integer getSumEquipmentTypeNoManufacturer(String equipmentType,Integer departmentId,String departmentType,String manufacturer);
	
	/**
	 * 根据保修状态查询设备数量
	 * @param warrantyStatus
	 * @return
	 */
	public Integer getSumWarrantyStatus(String warrantyStatus,Integer departmentId,String departmentType,String manufacturer);
	
	/**
	 * 根据安装时间查询设备数量
	 * @param installDate
	 * @return
	 */
	public Integer getSumInstallDate(String installDate,Integer departmentId,String departmentType,String manufacturer);

	/**
	 * 根据服务站ID查询分组查询设备数量
	 * @param departmentId
	 * @return
	 */
	public List<Object[]> queryEquipmentCustomerChartObjects(
			Integer departmentId);


	/**
	 * 根据设备状态分组查询(查询设备快照表) （排除报废和退机设备）
	 * @param departmentId 部门
	 * @return 设备状态，数量
	 */
	public List<Object[]> getEquipmentSnapshotGroupByStatus(Integer departmentId);

	/**
	 * 根据设备类型分组查询(查询设备快照表) （排除报废和退机设备）
	 * @param departmentId 部门
	 * @return 设备类型，数量
	 */
	public List<Object[]> getEquipmentSnapshotGroupByEquipmentType(
			Integer departmentId);

	/**
	 * 根据设备维保状态分组查询(查询设备快照表) （排除报废和退机设备）
	 * @param departmentId 部门
	 * @return 设备维保状态，数量
	 */
	public List<Object[]> getEquipmentSnapshotGroupByWarrantyStatus(
			Integer departmentId);

	/**
	 * @param departmentId 部门
	 * @param equipmentType 设备类型
	 * @param equipmentStatus 设备状态
	 * @return  设备维保状态，数量
	 */
	public List<Object[]> getEquipmentSnapshotGroupByWarrantyStatus(
			Integer departmentId,String equipmentType, String equipmentStatus);

	/**
	 * 根据设备销售属性分组查询(查询设备快照表) （排除报废和退机设备）
	 * @param departmentId 部门
	 * @param manufacturer 制造商
	 * @param exportDate 日期
	 * @return 设备状态，数量
	 */
	public List<Object[]> getEquipmentSnapshotGroupBySaleProperty(
			Integer departmentId);

	/**
	 * 按直接客户分组，查询客户列表(退机，报废除外)
	 * @param departmentId
	 * @param equipmentStatus 
	 * @return
	 */
	public List<Object[]> queryCustomerObjectsGroupByCustomer(
			Integer departmentId, String equipmentStatus);

	/**
	 * 根据客户，服务站，查询设备开通率图表数据
	 * @param customerIid
	 * @param departmentId
	 * @param month yyyy-MM
	 * @return
	 */
	public double queryOpenRateFromEquipmentRunReportData(Integer customerIid,
			Integer departmentId, String month);

	/**
	 * 根据客户，服务站，查询设备响应时间图表数据
	 * @param customerIid
	 * @param departmentId
	 * @param formatDate
	 * @return
	 */
	public double queryResponseHoursFromEquipmentRunReportData(Integer customerIid,
			Integer departmentId, String formatDate);

	/**
	 * 根据客户，服务站，查询设备修复时间图表数据
	 * @param customerIid
	 * @param departmentId
	 * @param formatDate
	 * @return
	 */
	public double queryRepairHoursFromEquipmentRunReportData(Integer customerIid,
			Integer departmentId, String formatDate);

	/**
	 * 根据服务站ID查询设备总数（报废，退机除外）
	 * @param departmentId
	 * @return
	 */
	public List<EquipmentInfo> queryEquipmentListByStationId(Integer departmentId);

	/**
	 * 根据根客户查询所有设备
	 * @param customerId
	 * @return
	 */
	public int getEquipmentByRootCustomerId(Integer customerId);

	/**
	 * 每日服务统计：根据部门ID到设备快照表中查询其所有设备按状态分组数量
	 * @param departmentId
	 * @param datestr
	 * @return
	 */
	public List<Object[]> queryEquipmentSnapshotCountGroupbyStatus(
			Integer departmentId, String datestr);
	
	/**
	 * 根据客户id与当前用户部门id查找出设备
	 * @param customerId
	 * @param deptId
	 * @return
	 */
	public List<EquipmentInfo> getEquipmentListByCustomerIdAndDeptId(Integer customerId,Integer deptId);

	
	public List<Integer> getEquipmentListByDeptId(Integer deptId);
	/**
	 * 根据设备维保状态查其它机型机器数量
	 * @param departmentId
	 * @param value
	 * @return
	 */
	public List<Object[]> getOtherTypeEquipmentGroupByWarrantyStatus(
			Integer departmentId, String value);

	
	
	/**
	 * 定时生成保养任务：一、查询需要保养的设备I(无保修时间的设备)，每次只查5000条数据处理
	 * 1.保内，已签保设备
	 * 2.在用设备
	 * 3.保修日期为空
	 * 4.开通时间不为空
	 * 5.没有未完成的保养任务
	 * @param page 
	 * @return
	 */
	public List<Object[]> queryNeedGeneralBYTaskEquipment1(Page page);
	
	/**
	 * 定时生成保养任务：二、查询需要保养的设备(有保修时间的设备)，每次只查5000条数据处理
	 * 1.保内，已签保设备
	 * 2.在用设备
	 * 3.保修日期不为空
	 * 4.开通时间不为空
	 * 5.没有未完成的保养任务
	 * @return
	 */
	public List<Object[]> queryNeedGeneralBYTaskEquipment2(Page page);

	/**
	 * 设备运行综合报表,设备分布
	 * @param customerId
	 * @param integrationBean
	 * @return
	 */
	public List<Object[]> queryIntegrationEquipment(String customerId,
			EquipmentIntegrationBean integrationBean);
	
	//设备运行综合报表,设备分布按设备类型
	public List<Object[]> queryIntegrationEquipmentType(String customerId,
			EquipmentIntegrationBean integrationBean);

	/**
	 * 根据根据型号，月份，部门查询平均在用设备台数
	 * @param yearmonth （12-9-4去掉9250的设备）
	 * @param departmentId
	 * @param equipmentTypeArray
	 * @return
	 */
	public double queryAvgInusingEquipment(String yearmonth,Integer departmentId, String[] equipmentTypeArray);

	/**
	 * 根据合同号模糊查询设备列表
	 * @param saleContractNo
	 * @return
	 */
	public List<EquipmentInfo> queryEquipmentByContractNo(String saleContractNo);
	/**
	 * 根据合同号精确查询设备列表
	 * @param saleContractNo
	 * @return
	 */
	public List<EquipmentInfo> queryEquipmentByAccurateContractNo(String saleContractNo,String equipmentModel);

	/**
	 * 删除指定状态，指定设备的保养任务
	 * @param equipmentId
	 * @param statusTaskProcessUnallocated
	 */
	//public void deleteEquipmentBYtask(Integer equipmentId,String status,String remark);
	
	/**
	 * 根据部门id以及设备类型查找旗下所有机器的数量(cfei)
	 * @param deptId
	 * @param equipmentType
	 * @return
	 */
	public Integer countEquipmentByDeptId(Integer deptId,String equipmentType);
	
	/**
	 * 查询设备数量
	 * @param equipmentModel
	 * @param warrantyStatus
	 * @param departmentId
	 * @return
	 */
	public Integer getCountByEquipment(String[] equipmentModelArray,String warrantyStatus,Integer departmentId);
	public Integer getCountByEquipment(String[] equipmentModelArray,String warrantyStatus,Integer departmentId,String installDateStr);
	
	/**
	 * 设备模块报表
	 * @param page
	 * @param departmentId
	 * @return
	 */
	public List<Object[]> getEquipmentTempList(Page page,Integer departmentId);
	
	/**
	 * 
	 * @param departmentId
	 * @param materialName
	 * @param taskType
	 * @return 根据机芯类型统计设备数量
	 */
	public Integer sumEquipmentNumByModel(Integer departmentId,String materialName,String previousMonth);
	
	/**
	 * 获取设备模块的数量
	 * @param departmentId
	 * @param materialName
	 * @param moduleType
	 * @return
	 */
	public Integer sumProblemEquipmentPartByModel(String material,Integer departmentId,String partCode,String previousDate);
	
	
	public Integer sumEquipmentNumByInstallDate(String materialName,String installDate,String previousMonth);
	
	
	public Integer sumProblemEquipmentPartByInstallDate(String material,String partCode,String installDate,String preMonth);
	
	
	/**
	 * 根据工单完成日期，获取设备总数(月份)
	 * @param departmentId
	 * @param finishDay
	 * @return
	 */
	public Integer countEquipmentsByRegion(Integer departmentId,String currentMonth);
	
	/**
	 * 根据设备安装日期统计设备总量
	 * @param currentDate
	 * @param installDate
	 * @return
	 */
	public Integer countEquipmentsByInstallDate(String previousDate,String installDate);
	
	/**
	 * @author:luowei
	 * @createDate 2013-4-1
	 * @param equipmentBean
	 * @param page
	 * @return List<Integer>
	 * @description
	 * 根据条件查询设备序列号与型号
	 */
	public List<Map> querySerialNumberAndEquipmentModelList(EquipmentBean equipmentBean,Page page);
	
	
	//每日服务信息报表 统计设备数量
	public Integer getSumEquipmentDailyServices(String equipmentStatus,Integer departmentId);
	
	public List<EquipmentInfo> queryEquipmentLikeSerialNumber(String serialNumber);
	public EquipmentInfo findEquipmentByRecordId(String configParentId);
	
	public List<Object[]> findEquipmentForRevisit();
	/**
	 * 根据ATM号查询设备
	 * @param atmNumber
	 * @return
	 */
	public List<EquipmentInfo> queryEquipmentByAtmNumber(String atmNumber);
	
	public List<EquipmentInfo> queryEquipmentByAtmNumberLikeCustomerCode(String atmNumber,String customerCode);
	
	public List<EquipmentInfo> queryEquipmentByBranchName(String branchName, Page queryPage,Integer customerId);
	
	/**
	 * 查询 没有销售合同号的设备档案列表
	 * 条件是无销售合同号、销售属性为"销售"，无维保合同号、制造商为"GRGBANKING"、设备状态为"在用"或"待装"
	 * @param queryPage
	 * @return
	 */
	public List<EquipmentInfo> queryNotAdviceContNO(Page queryPage);
	
	//查询维保信息
	public List<Object[]> getContractMaintainLastBySerialNumber(String serialNumber);
	
	
	public List<EquipmentInfo> queryEquipmentBySerialNumberAtmNumber(String serialNumber,String atmNumber);
	//微信查询设备信息
	public List<Object[]> getEquipmentByWX(String mobliePhone,String serialNumber,String branchName,String departmentName,Page queryPage);
	
	public JSONObject getEquipmentStatusById(String equipmentId);
	
}
