package com.grgbanking.css.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.work.WorkFormComment;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.WorkFormCommentDAO;
import com.grgbanking.css.util.HqlHelper;

/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-23 下午04:56:34
 */
@Repository("workFormCommentDAO")
public class WorkFormCommentDAOImpl extends CssBaseDAOImpl<WorkFormComment, Integer> implements
		WorkFormCommentDAO<WorkFormComment, Integer> {

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.workform.WorkFormCommentDAO#getWorkformCommentListByWorkformID(java.lang.Integer)
	 */
	public List<WorkFormComment> getWorkformCommentListByWorkformID(Integer workFormId) {
		HqlHelper hqlHelper=new HqlHelper(WorkFormComment.class);
		hqlHelper.addEqual("workForm.workFormId", workFormId);
		hqlHelper.addOrderBy("commentTime", "asc");
		return this.find(hqlHelper);
	}

	public WorkFormComment getWorkformCommentByLikeContent(Integer workFormId,
			String content) {
		HqlHelper hqlHelper=new HqlHelper(WorkFormComment.class);
		hqlHelper.addEqual("workForm.workFormId", workFormId);
		hqlHelper.addLike("comment",content);
		hqlHelper.addOrderBy("commentTime", "desc");
		List<WorkFormComment> list=this.find(hqlHelper);
		if(list!=null&& list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
