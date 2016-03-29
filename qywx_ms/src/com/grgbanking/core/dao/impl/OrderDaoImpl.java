package com.grgbanking.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.grgbanking.core.dao.OrderDao;
import com.grgbanking.core.entity.user.Order;
import com.grgbanking.core.service.BaseService;

@Repository("orderDao")
public class OrderDaoImpl extends BaseService<Order, Long> implements OrderDao {

	@SuppressWarnings("unchecked")
	@Override
	public Order findOrderBypoNumber(Order order) {
		String hqlString = " from Order t where t.poNumber = '"
				+ order.getPoNumber() + "' ";
		List<Order> list = supperHibernateDao.findByHql(hqlString);
		Order orderBean = null;
		if (list != null && list.size() > 0)
			orderBean = list.get(0);
		return orderBean;
	}

	@Override
	public Serializable insertOrder(Order order) {
	    return supperHibernateDao.save(order);
	}

	@Override
	public void updateOrder(Order order) {
		supperHibernateDao.saveOrUpdate(order);
	}
}
