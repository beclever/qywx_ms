package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.bean.work.WorkFormControl;
import com.grgbanking.css.bean.work.WorkFormControlBean;
import com.grgbanking.css.common.CssBaseDAO;


public interface WorkFormControlDAO<T, ID> extends CssBaseDAO<T, ID> {

	/**
	 * 读取工单时间点轨迹
	 * 
	 * @param offsetValue
	 * @return
	 */
	public List<WorkFormControl> getWorkFormContext(WorkFormControlBean workFormControlBean);

	/**
	 * 读取工单时间点轨迹
	 * @param workFormId
	 * @return
	 */
	//public List<WorkFormControl> getWorkFormControl(Integer workFormId);
	
	
	/**
	 * 
	 * @param poNumber 工单号
	 * @return
	 */
	//public List<JSONObject> getWorkFormControl(String poNumber);

}
