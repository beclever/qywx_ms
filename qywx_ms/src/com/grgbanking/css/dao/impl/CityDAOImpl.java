package com.grgbanking.css.dao.impl;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.City;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.CityDAO;

/**
 * 城市数据访问对象
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-6-22 下午05:05:44
 */
@Repository("cityDAO")
public class CityDAOImpl extends CssBaseDAOImpl<City, Integer> implements CityDAO<City, Integer> {

}
