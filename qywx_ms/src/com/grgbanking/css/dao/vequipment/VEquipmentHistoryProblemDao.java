package com.grgbanking.css.dao.vequipment;

import java.util.List;

import com.grgbanking.css.bean.equipment.EquipmentHistoryProblemBean;
import com.grgbanking.css.bean.vequipment.VEquipmentHistoryProblem;
import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.Page;


public interface VEquipmentHistoryProblemDao<T, ID> extends CssBaseDAO<T, ID> {

	/**
	 * 根据设备ID和状态查询历史遗留问题
	 * 
	 * @param equipmentId
	 * @param status
	 * @return
	 */
	public List<VEquipmentHistoryProblem> findByEquipmentId(Integer equipmentId, String status);

	/**
	 * 根据工单号查询历史遗留问题
	 * 
	 * @param poNumber
	 * @return
	 */
	public List<VEquipmentHistoryProblem> findByPoNumber(String poNumber);

	/**
	 * 根据不同条件查询历史遗留问题
	 * 
	 * @param poNumber
	 * @return
	 */
	public List<Object[]> findByEquipmentHp(Page queryPage, EquipmentHistoryProblemBean ehpbean);

}
