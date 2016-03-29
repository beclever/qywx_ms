package com.grgbanking.core.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 判断是objs中的元素不是null
	 * 
	 * @param compareModel
	 *            true and 模式，每个元素都不是null,false or 模式、只要有一个元素不是null
	 * @param objs
	 * @return
	 */
	protected boolean isNotNull(boolean compareModel, Object... objs) {
		for (Object obj : objs) {
			if (compareModel && obj == null)
				return false;
			else if (!compareModel && obj != null)
				return true;
		}
		return true;
	}

	/**
	 * 返回List的长度，判断list是否为空，为空则返回0
	 * 
	 * @return List长度
	 */
	@SuppressWarnings("all")
	public int getListSize(List list) {
		if (null != list) {
			return list.size();
		} else {
			return 0;
		}
	}
    
    //调用接口开始时间
	protected Date startTime;
    
    
	protected double getDiffTime(Date startTime){
        return (new Date().getTime() - startTime.getTime())/1000.0;
    }
}
