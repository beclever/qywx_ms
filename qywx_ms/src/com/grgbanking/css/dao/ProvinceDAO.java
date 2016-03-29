package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.common.CssBaseDAO;


/**
 * 省份数据访问对象
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-6-22 下午05:02:09
 */
public interface ProvinceDAO<T, ID> extends CssBaseDAO<T, ID> {
	
	public List<Object[]> getAllProvinceListAlarmObject();
	
	public List<Object[]> getAllProvinceListServicesObject();
	
	public List<Object[]> getAllProvinceListPraiseComObject();

}
