package com.grgbanking.css.dao.problem;

import java.util.List;

import com.grgbanking.css.common.CssBaseDAO;


/**
 * 故障原因DAO
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-8-26 下午02:24:06
 */
public interface ProblemReasonDAO<T, ID> extends CssBaseDAO<T, ID> {
	
	public List<Object[]> getProblemReasonList(Integer parentId,String remark);

}
