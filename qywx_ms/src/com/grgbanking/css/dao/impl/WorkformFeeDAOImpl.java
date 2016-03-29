package com.grgbanking.css.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.work.WorkFormFee;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.WorkformFeeDAO;
import com.grgbanking.css.util.HqlHelper;


/**
 * 工单费用
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-23 下午04:42:35
 */
@Repository("workformFeeDAO")
public class WorkformFeeDAOImpl extends CssBaseDAOImpl<WorkFormFee, Integer> implements
		WorkformFeeDAO<WorkFormFee, Integer> {

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.workform.WorkformFeeDAO#getWorkformFeeByWorkformID(java.lang.Integer)
	 */
	public List<WorkFormFee> getWorkformFeeByWorkformID(Integer workFormId) {
		HqlHelper hqlHelper=new HqlHelper(WorkFormFee.class);
		hqlHelper.addEqual("workForm.workFormId", workFormId);
		return this.find(hqlHelper);
	}
	/**
	 * 根据工单ID,用户ID查询某个人的费用列表
	 * @param workFormId
	 * @return
	 */
	public List<WorkFormFee> getWorkformFeeByWorkfIdUserId(Integer workFormId,Integer userId){
		HqlHelper hqlHelper=new HqlHelper(WorkFormFee.class);
		hqlHelper.addEqual("workForm.workFormId", workFormId);
		hqlHelper.addEqual("userId", userId);
		return this.find(hqlHelper);
	}
}
