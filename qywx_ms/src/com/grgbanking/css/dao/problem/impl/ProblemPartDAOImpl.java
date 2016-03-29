package com.grgbanking.css.dao.problem.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.problem.ProblemPart;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.problem.ProblemPartDAO;


/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-26 下午02:27:11
 */
@Repository("problemPartDAO")
public class ProblemPartDAOImpl extends CssBaseDAOImpl<ProblemPart, Integer> implements
		ProblemPartDAO<ProblemPart, Integer> {

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.workform.ProblemPartDAO#getSelectIdByParent(java.lang.Integer)
	 */
	public List<Object[]> getSelectIdByParent(Integer id) {
		String sql="select * from V_services_problem_part t where t.parent_id=0 start with t.id="+id+" connect by prior t.parent_id=t.id ";
		return this.findBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.services.workform.ProblemPartDAO#getProblemPartModelNameList(java.lang.String)
	 */
	public List<Object[]> getProblemPartModelNameList(String modelName) {
		String sql="select distinct t.id,t.description,t.value,t.parent_id from V_services_problem_part t " +
				" left join V_equipment_model_problem_part p on t.id=p.problem_part_id " +
				" start with  p.model_name='"+modelName+"' connect by prior t.parent_id=t.id order by t.value ";
		return this.findBySQL(sql);
	}

	public List<Object[]> getProblemPartModelNameList(String modelName,
			Integer parentId,String description) {
		String str="1=1 ";//加查询显示最后一级
		if(parentId!=null && StringUtils.isBlank(description)){
			str+=" and t.parent_id="+parentId;
		}
		if(StringUtils.isNotBlank(description)){
			str+=" and (t.description like '%"+description+"%' or t.value like '%"+description+"%')  and level='1' ";
		}
		String sql="select distinct t.id,t.description,t.value,t.parent_id,level from V_services_problem_part t " +
		" left join V_equipment_model_problem_part p on t.id=p.problem_part_id " +
		" where "+str+" start with  p.model_name='"+modelName+"' connect by prior t.parent_id=t.id order by t.value ";
		return this.findBySQL(sql);
	}

}
