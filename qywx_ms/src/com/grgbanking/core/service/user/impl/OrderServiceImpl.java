package com.grgbanking.core.service.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grgbanking.core.dao.OrderDao;
import com.grgbanking.core.entity.user.Order;
import com.grgbanking.core.service.user.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Resource(name = "orderDao")
	private OrderDao orderDao;

	@Override
	public Order findOrderBypoNumber(String poNumber) {

		Order order = new Order();
		order.setPoNumber(poNumber);
		return orderDao.findOrderBypoNumber(order);
	}

	@Override
	public void saveOrder(Order order) {
		orderDao.insertOrder(order);
	}

	@Override
	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
	}
}
