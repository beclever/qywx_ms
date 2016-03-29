package com.grgbanking.core.service.user;

import com.grgbanking.core.entity.user.Order;

/**
 * 工单
 * @author w s h
 *
 */
public interface OrderService {
    /**
     * 根据poNumber查找工单
     * @Title: findDevice
     * @Description: 
     * @param device
     * @return
     */
    public Order findOrderBypoNumber(String poNumber);
    /**
     * 新增
     * @param order
     * @return
     */
    public void saveOrder(Order order);
    /**
     * 更新
     * @param order
     * @return
     */
    public void updateOrder(Order order);
}
