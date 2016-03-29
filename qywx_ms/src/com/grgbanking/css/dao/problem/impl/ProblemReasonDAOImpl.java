package com.grgbanking.css.dao.problem.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.problem.ProblemReason;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.problem.ProblemReasonDAO;


/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-26 下午02:29:02
 */
@Repository("problemReasonDAO")
public class ProblemReasonDAOImpl extends CssBaseDAOImpl<ProblemReason, Integer> implements
		ProblemReasonDAO<ProblemReason, Integer> {

	public List<Object[]> getProblemReasonList(Integer parentId, String remark) {
		if(parentId==0){
			String sql="select distinct t.problem_reason_id,t.reason_code,t.reason_remark,t.parent_id,'2' from V_services_problem_reason t " +
			"  where t.parent_id=0 ";
			if(StringUtils.isNotBlank(remark)){
				sql+=" and (t.reason_remark like '%"+remark+"%' or t.reason_code like '%"+remark+"%')";
			}
			return this.findBySQL(sql);
		}
		String str=" where 1=1 ";
		if(parentId!=null){
			str+=" and t.parent_id="+parentId;
		}
		if(StringUtils.isNotBlank(remark)){
			str+=" and (t.reason_remark like '%"+remark+"%' or t.reason_code like '%"+remark+"%')";
		}
		String sql="select distinct t.problem_reason_id,t.reason_code,t.reason_remark,t.parent_id,level from V_services_problem_reason t " +
				" "+str+" start with t.parent_id="+parentId+" connect by prior t.parent_id=t.problem_reason_id";
		
		return this.findBySQL(sql);
	}

}
