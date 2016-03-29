package com.grgbanking.core.dao;

import java.io.Serializable;

import com.grgbanking.core.entity.user.Order;

/**
 * 工单
 * 
 * @author w s h
 * 
 */
public interface OrderDao {
	/**
	 * 查找
	 * 
	 * @Title: findDevice
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param device
	 * @return
	 */
	public Order findOrderBypoNumber(Order order);

	/**
	 * 新增
	 * 
	 * @param order
	 * @return
	 */
	public Serializable insertOrder(Order order);

	/**
	 * 更新
	 * 
	 * @param order
	 * @return
	 */
	public void updateOrder(Order order);
}
