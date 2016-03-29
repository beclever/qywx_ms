package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.bean.equipment.EquipmentHistoryProblem;
import com.grgbanking.css.bean.equipment.EquipmentHistoryProblemBean;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;
/**
 * 设备历史遗留问题DAO
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-23 下午04:15:20
 */
public interface EquipmentHistoryProblemDAO<T, ID> extends CssBaseDAO<T, ID> {

	/**
	 * 根据设备ID和状态查询历史遗留问题
	 * @param equipmentId
	 * @param status 
	 * @return
	 */
	public List<EquipmentHistoryProblem> findByEquipmentId(Integer equipmentId, String status);

	/**
	 * 根据工单号查询历史遗留问题
	 * @param poNumber
	 * @return
	 */
	public List<EquipmentHistoryProblem> findByPoNumber(String poNumber);
	
	/**
	 * 根据不同条件查询历史遗留问题
	 * @param poNumber
	 * @return
	 */
	public List<Object[]> findByEquipmentHp(Page queryPage, EquipmentHistoryProblemBean ehpbean);

}
