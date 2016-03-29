package com.grgbanking.core.service.workorder;

import java.util.List;

import com.grgbanking.core.entity.user.SendOrderBean;
import com.grgbanking.core.entity.ws.WsPage;

/**
 * 
 * @ClassName: OrderBeanService
 * @Description: 录单管理Service
 * @author cflong
 * @date Copyright 2014 by Grgbanking All Rights Reserved.
 */
public interface SendOrderManagerService {

	public void doSaveIsUpdateOrderBean(SendOrderBean bean) throws Exception;

	@SuppressWarnings("rawtypes")
	public List findSendOrderBeanList(String userCode, final WsPage wsPage,
			String serialNumber, String branchName, String engineerUserCode)
			throws Exception;

	public SendOrderBean findSendOrderBean(String id) throws Exception;

}
