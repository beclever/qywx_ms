package com.grgbanking.css.dao.vequipment;

import java.util.List;

import net.sf.json.JSONObject;

import com.grgbanking.css.bean.vequipment.VEquipmentInfo;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;


public interface VEquipmentInfoDao<T, ID> extends CssBaseDAO<T, ID> {
	
	/**
	 * 根据设备序列号查找设备
	 * @param serialNumber
	 * @return
	 */
	public VEquipmentInfo getEquipmentBySerialNumber(String serialNumber);
	
	
	/**
	 * 网点客户查询设备
	 * @param branchName
	 * @param queryPage
	 * @param customerId
	 * @return
	 */
//	public List<VEquipmentInfo> queryEquipmentByBranchName(String branchName, Page queryPage,Integer customerId);
//	
//	
//	/**
//	 * 回访计划设备查询
//	 * @return
//	 */
//	public List<Object[]> findEquipmentForRevisit();
//	
//	/**
//	 * 根据客户id与当前用户部门id查找出设备
//	 * @param customerId
//	 * @param deptId
//	 * @return
//	 */
//	public List<VEquipmentInfo> getEquipmentListByCustomerIdAndDeptId(Integer customerId,Integer deptId);
//	
//	/**
//	 * 每日服务统计：根据部门ID到设备快照表中查询其所有设备按状态分组数量
//	 * @param departmentId
//	 * @param datestr
//	 * @return
//	 */
//	public List<Object[]> queryEquipmentSnapshotCountGroupbyStatus(Integer departmentId, String datestr);
//	
//	/**
//	 * 根据ATM号查询设备
//	 * @param atmNumber
//	 * @return
//	 */
//	public List<VEquipmentInfo> queryEquipmentByAtmNumber(String atmNumber);
//	
//	
//
//	//微信查询设备信息
//	public List<Object[]> getEquipmentByWX(String mobliePhone,String serialNumber,String branchName,String departmentName,Page queryPage);
//	//微信查询设备状态
//	public JSONObject getEquipmentStatusById(String equipmentId);
//	
//
//
//	
//	public List<VEquipmentInfo> queryEquipmentByAtmNumberLikeCustomerCode(String atmNumber, String customerCode);
//
//	/**
//	 * 根据设备序列号查找设备(有权限)
//	 * @param serialNumber
//	 * @param departmentId
//	 * @return
//	 */
//	public VEquipmentInfo getEquipmentBySerialNumberDepartmentName(String serialNumber,Integer departmentId);
//	
//	/**
//	 * 删除指定状态，指定设备的保养任务
//	 * @param equipmentId
//	 * @param statusTaskProcessUnallocated
//	 */
//	public void deleteEquipmentBYtask(Integer equipmentId,String status,String remark);
//	
//	
//	public List<VEquipmentInfo> queryEquipmentBySerialNumberAtmNumber(String serialNumber,String atmNumber);
//	
//	/**
//	 *  根据设备状态查询设备数量
//	 * @param equipmentStatus
//	 * @return
//	 */
//	public Integer getSumEquipmentStatus(String equipmentStatus,Integer departmentId,String departmentType,String manufacturer);
//	
//	/**
//	 * 根据设备类型查询设备数量
//	 * @param equipmentType
//	 * @return
//	 */
//	public Integer getSumEquipmentType(String equipmentType,Integer departmentId,String departmentType,String manufacturer);
//	
//	/**
//	 * 根据保修状态查询设备数量
//	 * @param warrantyStatus
//	 * @return
//	 */
//	public Integer getSumWarrantyStatus(String warrantyStatus,Integer departmentId,String departmentType,String manufacturer);
//	
//	/**
//	 * 根据安装时间查询设备数量
//	 * @param installDate
//	 * @return
//	 */
//	public Integer getSumInstallDate(String installDate,Integer departmentId,String departmentType,String manufacturer);
//	
//	/**
//	 * 根据设备状态查询设备数量(manufacturer not in)
//	 * @param equipmentStatus
//	 * @param departmentId
//	 * @param departmentType
//	 * @param manufacturer
//	 * @return
//	 */
//	public Integer getSumEquipmentStatusNoManufacturer(String equipmentStatus,Integer departmentId,String departmentType,String manufacturer);
//	
//	/**
//	 * 根据设备类型查询设备数量(manufacturer not in)
//	 * @param equipmentType
//	 * @param departmentId
//	 * @param departmentType
//	 * @param manufacturer
//	 * @return
//	 */
//	public Integer getSumEquipmentTypeNoManufacturer(String equipmentType,Integer departmentId,String departmentType,String manufacturer);
}
