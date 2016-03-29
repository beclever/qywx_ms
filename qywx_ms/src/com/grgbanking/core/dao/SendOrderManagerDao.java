package com.grgbanking.core.dao;

import java.util.List;

import com.grgbanking.core.entity.user.SendOrderBean;
import com.grgbanking.core.entity.ws.WsPage;

/**
 * 
 * @ClassName: IsUpdateDao
 * @Description: 录单管理Dao
 * @author cfl
 * @date Copyright 2014 by Grgbanking All Rights Reserved.
 * @param <T>
 * @param <ID>
 */
public interface SendOrderManagerDao<T, ID> {

	public void doSaveIsUpdateOrderBean(SendOrderBean Bean) throws Exception;

	@SuppressWarnings("rawtypes")
	public List findSendOrderBeanList(String userCode, final WsPage wsPage,
			String serialNumber, String branchName, String engineerUserCode)
			throws Exception;

	public SendOrderBean findSendOrderBean(String id) throws Exception;

}
