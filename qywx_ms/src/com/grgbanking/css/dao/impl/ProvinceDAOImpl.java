package com.grgbanking.css.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.Province;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.ProvinceDAO;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.ServicesConstants;


/**
 * 省份数据访问对象 Product:Grgbanking Service Of Customer System. Version:2.0 Copyright
 * 2010 by Grgbanking All Rights Reserved. Author: zhangzhi Date: 2010-6-22
 * 下午05:03:40
 */
@Repository("provinceDAO")
public class ProvinceDAOImpl extends CssBaseDAOImpl<Province, Integer> implements
		ProvinceDAO<Province, Integer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.grgbanking.soc.dao.common.ProvinceDAO#getAllProvinceList()
	 */
	public List<Object[]> getAllProvinceListAlarmObject() {
		String sql = "select t.province_id,t.province_name,t.left,t.top,t.location,(select count(distinct e.equipment_id) from t_alarm_equipment a " +
				" left join V_EMS_EQUIPMENT_INFO e on e.equipment_id=a.equipment_id where e.province_id=t.province_id and e.equipment_id is not null and a.status<>'已关闭' and a.happen_times>4 ) quantity " +
				" from t_base_province t ";
		return this.findBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.common.ProvinceDAO#getAllProvinceListServicesObject()
	 */
	public List<Object[]> getAllProvinceListServicesObject() {
		String sql="select t.province_id,t.province_name,t.left,t.top,t.location," +
				"(select count(s.department_id) from T_SERVICES_DAILY_EVENT s " +
				" left join T_BASE_DEPARTMENT d on s.department_id=d.department_id where s.status not in('"+ServicesConstants.STATUS_DAILY_EVENT_UNCOMMIT+"','"+ServicesConstants.STATUS_DAILY_EVENT_COMPLETED+"') and s.process_level='1' and d.province=t.province_id) quantity, " +
				" (select count(s.department_id) from T_SERVICES_DAILY_EVENT s " +
				" left join T_BASE_DEPARTMENT d on s.department_id=d.department_id  where s.status not in('"+ServicesConstants.STATUS_DAILY_EVENT_UNCOMMIT+"','"+ServicesConstants.STATUS_DAILY_EVENT_COMPLETED+"') and s.process_level='2' and d.province=t.province_id) quantity2 " +
				" from t_base_province t ";
		return this.findBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.common.ProvinceDAO#getAllProvinceListPraiseComObject()
	 */
	public List<Object[]> getAllProvinceListPraiseComObject() {
		String sql="select t.province_id,t.province_name,t.left,t.top,t.location," +
				" (select count(s.complain_id) from T_QUALITY_COMPLAIN s left join T_BASE_DEPARTMENT d on s.department_id=d.department_id" +
				" where to_char(s.event_happen_date,'yyyy-MM')=to_char(sysdate,'yyyy-MM') and d.province=t.province_id ) quan," +
				" (select count(p.praise_id) from T_QUALITY_PRAISE p left join T_BASE_DEPARTMENT de on de.department_id=p.department_id " +
				" where to_char(p.create_date,'yyyy-MM')=to_char(sysdate,'yyyy-MM') and de.province=t.province_id ) quantity2" +
				" from t_base_province t ";
		return this.findBySQL(sql);
	}

}
