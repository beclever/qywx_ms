package com.grgbanking.css.dao.problem;

import java.util.List;

import com.grgbanking.css.common.CssBaseDAO;


/**
 * 故障部位DAO
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-26 下午02:23:03
 */
public interface ProblemPartDAO<T, ID> extends CssBaseDAO<T, ID> {
	
	/**
	 * 查询父故障部位
	 * @param id
	 * @return
	 */
	public List<Object[]> getSelectIdByParent(Integer id);
	
	public List<Object[]> getProblemPartModelNameList(String modelName);
	
	public List<Object[]> getProblemPartModelNameList(String modelName,Integer parent_id,String description);

}
