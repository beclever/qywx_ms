package com.grgbanking.css.dao.vequipment.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.equipment.EquipmentHistoryProblemBean;
import com.grgbanking.css.bean.vequipment.VEquipmentHistoryProblem;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.common.UserContext;
import com.grgbanking.css.dao.vequipment.VEquipmentHistoryProblemDao;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.HqlHelper;


@Repository("vEquipmentHistoryProblemDao")
public class VEquipmentHistoryProblemDaoImpl extends CssBaseDAOImpl<VEquipmentHistoryProblem, Integer> implements
		VEquipmentHistoryProblemDao<VEquipmentHistoryProblem, Integer> {

	public List<VEquipmentHistoryProblem> findByEquipmentId(Integer equipmentId, String status) {
		HqlHelper hqlHelper = new HqlHelper(VEquipmentHistoryProblem.class);
		hqlHelper.addEqual("equipmentId", equipmentId);
		hqlHelper.addEqual("status", status);
		return this.find(hqlHelper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.grgbanking.soc.dao.equipment.EquipmentHistoryProblemDAO#findByPoNumber
	 * (java.lang.String)
	 */
	public List<VEquipmentHistoryProblem> findByPoNumber(String poNumber) {
		HqlHelper hqlHelper = new HqlHelper(VEquipmentHistoryProblem.class);
		hqlHelper.addEqual("createPoNumber", poNumber);
		return this.find(hqlHelper);
	}

	/**
	 * 根据不同条件查询历史遗留问题
	 * 
	 * @param poNumber
	 * @return
	 */
	public List<Object[]> findByEquipmentHp(Page queryPage, EquipmentHistoryProblemBean ehpbean) {
		StringBuffer sqlm = new StringBuffer(
				"select k.equipment_id,z.department_name,k.problem_level,k.record_Person,k.record_Time,k.description,k.status from(select x.equipment_id,y.department_id,x.problem_level,x.record_Person,x.record_Time,x.description,x.status from(select a.equipment_id,a.problem_level,a.record_Person,a.record_Time,description,status from v_EQUIPMENT_HISTORY_PROBLEM a ");
		if (CommonConstants.DEPT_TYPE_HEAD.equals(UserContext.getUser().getDepartmentType())) {
			sqlm.append(" where 0=0");
		} else {
			sqlm.append(
					" where a.equipment_id in (select b.equipment_id from V_EQUIPMENT_INFO b where b.department_id in (select t.department_id from V_base_department t start with t.department_id= ")
					.append(UserContext.getUser().getDepartmentId()).append(" connect by prior t.department_id=t.parent_id))");
		}
		if (ehpbean.getEquipmentId() != null && !ehpbean.getEquipmentId().equals("")) {
			sqlm.append(" and a.equipment_id=").append(ehpbean.getEquipmentId());
		}
		if (ehpbean.getStatus() != null && !ehpbean.getStatus().equals("")) {
			sqlm.append(" and a.status ='").append(ehpbean.getStatus()).append("'");
		}
		if (ehpbean.getEndDate() != null && !ehpbean.getEndDate().equals("")) {
			sqlm.append(" and a.record_Time between to_date('").append(ehpbean.getStartDate()).append("','yyyy-MM-dd') and to_date('")
					.append(ehpbean.getEndDate()).append("','yyyy-MM-dd')");
		}
		sqlm.append(" )x,v_EQUIPMENT_INFO y where x.equipment_id=y.equipment_id(+))k, V_base_department z where k.department_id= z.department_id(+)");
		return this.findBySQL(sqlm.toString(), queryPage);
	}

}
