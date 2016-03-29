package com.grgbanking.css.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.work.WorkTaskContact;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.WorkTaskContactDao;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.HqlHelper;


@Repository("workTaskContactDao")
public class WorkTaskContactDaoImpl extends CssBaseDAOImpl<WorkTaskContact, Integer> implements
		WorkTaskContactDao<WorkTaskContact, Integer> {

//	public void updateWorkformIdByTaskId(Integer workformId, String[] taskIds) {
//		String taskid = ArrayTransitionUtils.stringArrayToInString(taskIds);
//		String sql = "update t_services_task_contact t set t.workform_id = " + workformId + " where t.task_id in ("
//				+ taskid + " ) ";
//		this.executeSql(sql);
//	}

	public List<WorkTaskContact> listByWorkformId(Integer workformId) {
		HqlHelper helper = new HqlHelper(WorkTaskContact.class);
		helper.addEqual("workformId", workformId);
		helper.addEqual("deleted", CommonConstants.FLAG_N);
		return find(helper);
	}

//	public WorkTaskContact getByWorkformId(Integer workformId, String taskContacType) {
//		HqlHelper helper = new HqlHelper(WorkTaskContact.class);
//		helper.addEqual("workformId", workformId);
//		helper.addEqual("taskContactType", taskContacType);
//		helper.addEqual("deleted", CommonConstants.FLAG_N);
//		List<WorkTaskContact> list = find(helper);
//		if (null != list && list.size() > 0) {
//			return list.get(0);
//		}
//		return null;
//	}
//	/**
//	 * 返回接待人，没有接待人返回报修人
//	 * @param workformId
//	 * @return
//	 */
//	public List<WorkTaskContact> getJDRByWorkformId(Integer workformId) {
//		HqlHelper helper = new HqlHelper(WorkTaskContact.class);
//		helper.addEqual("workformId", workformId);
//		helper.addEqual("deleted", CommonConstants.FLAG_N);
//		helper.addOrderBy("taskContactType", "desc");
//		helper.addOrderBy("createDate", "desc");
//		return find(helper);
//	}
}
