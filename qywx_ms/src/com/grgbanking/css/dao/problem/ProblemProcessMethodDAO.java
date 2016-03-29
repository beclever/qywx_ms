package com.grgbanking.css.dao.problem;

import java.util.List;

import com.grgbanking.css.common.CssBaseDAO;


/**
 * 处理方法DAO
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-26 下午02:23:41
 */
public interface ProblemProcessMethodDAO<T, ID> extends CssBaseDAO<T, ID> {
	
	public List<Object[]> getProblemProcessMethodList(Integer parentId,String remark);

}
