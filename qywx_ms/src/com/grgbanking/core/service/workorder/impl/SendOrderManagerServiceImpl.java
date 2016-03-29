package com.grgbanking.core.service.workorder.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grgbanking.core.dao.SendOrderManagerDao;
import com.grgbanking.core.entity.user.SendOrderBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.service.workorder.SendOrderManagerService;

/**
 * 
 * @ClassName: OrderBeanServiceImpl
 * @Description: 录单管理Service实现类
 * @author dlcai
 * @date 2014-8-27 上午10:14:26 Copyright 2014 by Grgbanking All Rights Reserved.
 */
@Service("sendOrderManagerService")
public class SendOrderManagerServiceImpl implements  SendOrderManagerService{
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	SendOrderManagerDao<SendOrderBean,String> sendOrderManagerDao;
	
	@Override
	public void doSaveIsUpdateOrderBean(SendOrderBean bean)
			throws Exception {
		sendOrderManagerDao.doSaveIsUpdateOrderBean(bean);
	}
	
	@SuppressWarnings("rawtypes")
	public List findSendOrderBeanList(String userCode,final WsPage wsPage,String serialNumber,
			String branchName, String engineerUserCode)
			throws Exception{
		return sendOrderManagerDao.findSendOrderBeanList(userCode, wsPage, serialNumber, branchName, engineerUserCode);
	}

	public SendOrderBean findSendOrderBean(String id)throws Exception{
		return sendOrderManagerDao.findSendOrderBean(id);
	}
	
	

}
