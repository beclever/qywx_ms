package com.grgbanking.core.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.grgbanking.core.entity.user.Order;

@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-hibernate.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
// defaultRollback=true不会改变数据库，false会改变数据库
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
//@Configuration(value = "classpath:applicationContext-springContext.xml")
//@ImportResource("classpath:applicationContext-Springmvc.xml")
public class OrderDaoTest {
    
    @Autowired
    private OrderDao orderDao;
    

    @Test
    public void testInsert() {
        
        
        Order order = new Order();
        order.setCreateDate(new Date());
        order.setUpDate(new Date());
        order.setLoginName("drhua");
        order.setPoNumber("test");
        order.setContent("test");
        
        try {
            orderDao.insertOrder(order);
            assertEquals(true, true);
        } catch (Exception e) {
            // TODO: handle exception
            assertEquals(true, false);
        }
    }
 
}
