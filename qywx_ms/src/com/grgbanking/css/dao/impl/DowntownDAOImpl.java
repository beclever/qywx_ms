package com.grgbanking.css.dao.impl;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.bean.Downtown;
import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.dao.DowntownDAO;


/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: cxhui
 * Date: 2010-9-28 下午01:53:07
 */
@Repository("downtownDAO")
public class DowntownDAOImpl extends CssBaseDAOImpl<Downtown, Integer> implements
		DowntownDAO<Downtown, Integer>{

}
