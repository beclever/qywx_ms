package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.bean.work.WorkFormComment;
import com.grgbanking.css.common.CssBaseDAO;


/**
 *	工单审批备注信息
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-23 下午04:54:21
 */
public interface WorkFormCommentDAO<T, ID> extends CssBaseDAO<T, ID> {

	/**
	 * 根据工单ID查询审批意见
	 * @param workFormId
	 * @return
	 */
	public List<WorkFormComment> getWorkformCommentListByWorkformID(Integer workFormId);
	
	/**
	 * 查找最新记录
	 * @param workFormId
	 * @param content
	 * @return
	 */
	public WorkFormComment getWorkformCommentByLikeContent(Integer workFormId,String content);

}
