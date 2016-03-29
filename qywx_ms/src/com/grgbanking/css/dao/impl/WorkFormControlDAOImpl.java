package com.grgbanking.css.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.work.WorkFormControl;
import com.grgbanking.css.bean.work.WorkFormControlBean;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.WorkFormControlDAO;
import com.grgbanking.css.util.HqlHelper;


/**
 * 
 * 
 * 项目名称：MobileSocketServer 类名称：WorkFormControlDAOImpl 类描述： 读取工单时间点轨迹
 * 创建人：Administrator 创建时间：2011-12-27 上午09:23:27 修改人：Administrator
 * 修改时间：2011-12-27 上午09:23:27 修改备注：
 * 
 * @version
 * 
 */
@Repository("workFormControlDAO")
public class WorkFormControlDAOImpl extends CssBaseDAOImpl<WorkFormControl, Integer> implements WorkFormControlDAO<WorkFormControl, Integer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.grgbanking.soc.dao.offsetvalue.WorkFormControlDAO#getWorkFormContext
	 * (com.grgbanking.soc.entity.offsetvalue.WorkFormControl)
	 */
	public List<WorkFormControl> getWorkFormContext(WorkFormControlBean workFormControlBean) {
		HqlHelper hqlHelper = new HqlHelper(WorkFormControl.class);
		hqlHelper.addEqual("workFormId", workFormControlBean.getWorkFormId());
		return this.find(hqlHelper);
	}

//	public List<WorkFormControl> getWorkFormControl(Integer workFormId) {
//		HqlHelper hqlHelper = new HqlHelper(WorkFormControl.class);
//		hqlHelper.addEqual("workFormId", workFormId);
//		hqlHelper.addOrderBy("dateTime", "asc");
//		return this.find(hqlHelper);
//	}
//
//	public List<JSONObject> getWorkFormControl(String poNumber) {
//		String sql = "select decode(t.time_type, 5, 0, t.time_type) time_type,"
//				+ " decode(t.time_type, 5, '接受',1,'出发',2,'到达',3,'开始',4,'完成') time_time_desc, "
//				+ " to_char(t.date_time,'yyyy-mm-dd hh24:mi:ss') from t_workform_control t, t_services_workform a" 
//				+ " where t.workform_id = a.workform_id  and a.po_number = ?"
//				+ " order by decode(t.time_type, 5, 0, t.time_type)";
//		List<Object[]> list = this.findBySQL(sql, poNumber);
//		if (null != list && list.size() > 0) {
//			List<JSONObject> result = new ArrayList<JSONObject>();
//			for (int j = 0; j < list.size(); j++) {
//				JSONObject json = new JSONObject();
//				json.element("timeType", list.get(j)[0]);
//				json.element("timeTimeDesc", list.get(j)[1]);
//				json.element("dateTime", list.get(j)[2]);
//				result.add(json);
//			}
//			return result;
//		}
//		return null;
//	}
}
