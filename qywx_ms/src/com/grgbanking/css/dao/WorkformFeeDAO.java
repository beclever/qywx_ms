package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.bean.work.WorkFormFee;
import com.grgbanking.css.common.CssBaseDAO;


/**
 * 工单费用DAO
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-23 下午04:42:03
 */
public interface WorkformFeeDAO<T, ID> extends CssBaseDAO<T, ID> {

	/**
	 * 根据工单ID查询工单费用列表
	 * @param workFormId
	 * @return
	 */
	public List<WorkFormFee> getWorkformFeeByWorkformID(Integer workFormId);
	
	/**
	 * 根据工单ID,用户ID查询某个人的费用列表
	 * @param workFormId
	 * @return
	 */
	public List<WorkFormFee> getWorkformFeeByWorkfIdUserId(Integer workFormId,Integer userId);

}
