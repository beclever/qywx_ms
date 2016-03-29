package com.grgbanking.css.dao.problem.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.problem.ProblemCode;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.problem.ProblemCodeDAO;
import com.grgbanking.css.util.HqlHelper;


/**
 * 
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-26 下午02:26:07
 */
@Repository("problemCodeDAO")
public class ProblemCodeDAOImpl extends CssBaseDAOImpl<ProblemCode, Integer> implements
		ProblemCodeDAO<ProblemCode, Integer> {
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.workform.ProblemCodeDAO#getByCode(java.lang.String)
	 */
	public ProblemCode getByCode(String problemCode) {
		HqlHelper hqlHelper=new HqlHelper(ProblemCode.class);
		hqlHelper.addEqual("problemCode", problemCode);
		return this.find(hqlHelper).get(0);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.workform.ProblemCodeDAO#getgetProblemCodeList(java.lang.Integer)
	 */
	public List<Object[]> getgetProblemCodeList(Integer problemId) {
		String sql="select distinct t.problem_code_id,t.problem_code,t.problem_remark,t.parent_id from v_services_problem_code t " +
				" left join v_problem_part_code p on t.problem_code_id=p.problem_code_id " +
				" start with  p.problem_part_id="+problemId+" connect by prior t.parent_id=t.problem_code_id order by t.problem_code ";
		return this.findBySQL(sql);
	}

	public List<Object[]> getgetProblemCodeList(Integer problemId,
			Integer parentId, String description) {
		String str="1=1 ";//加查询显示最后一级
		if(parentId!=null && StringUtils.isBlank(description)){
			str+=" and t.parent_id="+parentId;
		}
		if(StringUtils.isNotBlank(description)){
			str+=" and (t.problem_remark like '%"+description+"%' or t.problem_code like '%"+description+"%')  and level='1' ";
		}
		String sql="select distinct t.problem_code_id,t.problem_code,t.problem_remark,t.parent_id,level from v_services_problem_code t " +
		" left join v_problem_part_code p on t.problem_code_id=p.problem_code_id " +
		" where "+str+" start with  p.problem_part_id="+problemId+" connect by prior t.parent_id=t.problem_code_id order by t.problem_code ";
		return this.findBySQL(sql);
	}

}
