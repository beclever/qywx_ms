package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.bean.work.WorkTaskContact;
import com.grgbanking.css.common.CssBaseDAO;


public interface WorkTaskContactDao<T, ID> extends CssBaseDAO<T, ID> {

	/**
	 * 更新任务联系人的工单ID
	 * 
	 * @param workformId
	 * @param taskId
	 */
	//public void updateWorkformIdByTaskId(Integer workformId, String[] taskIds);

	/**
	 * 
	 * @param workformId
	 * @return
	 */
	public List<WorkTaskContact> listByWorkformId(Integer workformId);

	/**
	 * 
	 * @param workformId
	 * @param taskContacType
	 * @return
	 */
	//public WorkTaskContact getByWorkformId(Integer workformId, String taskContacType);

	
	/**
	 * 返回接待人，没有接待人返回报修人
	 * @param workformId
	 * @return
	 */
	//public List<WorkTaskContact> getJDRByWorkformId(Integer workformId);
}
