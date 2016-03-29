package com.grgbanking.css.dao.problem;

import java.util.List;

import com.grgbanking.css.bean.problem.ProblemCode;
import com.grgbanking.css.common.CssBaseDAO;


/**
 * 故障代码DAO
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-26 下午02:20:52
 */
public interface ProblemCodeDAO<T, ID> extends CssBaseDAO<T, ID> {

	/**
	 * 根据故障代码查询对象
	 * @param problemCode
	 * @return
	 */
	public ProblemCode getByCode(String problemCode);
	
	public List<Object[]> getgetProblemCodeList(Integer problemId);
	
	public List<Object[]> getgetProblemCodeList(Integer problemId,Integer parent_id,String description);

}
