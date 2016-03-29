package com.grgbanking.core.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.grgbanking.core.entity.user.BaseRoleUser;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.dao.CssUserDAO;

@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-hibernate.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
// defaultRollback=true不会改变数据库，false会改变数据库
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
//@Configuration(value = "classpath:applicationContext-springContext.xml")
//@ImportResource("classpath:applicationContext-Springmvc.xml")
public class BaseRoleUserTest {

    @Resource(name = "baseRoleUserDao")
    private BaseRoleUserDao baseRoleUserDao;

    @Autowired
    private CssUserDAO<CssUser, Integer> userDAO;
    

    @Test
    public void testGetUserByLoginName() {
        
        String loginName = "drhua";
        CssUser cssUser = userDAO.getUserByLoginName(loginName);
        BaseRoleUser baseRoleUser = new BaseRoleUser();
        baseRoleUser.setUserId(cssUser.getUserId().longValue());
        System.out.println("==========================");
        List<BaseRoleUser> roleUsers =  baseRoleUserDao.query(baseRoleUser);
        if(roleUsers !=null && roleUsers.size() > 0){
            for (int i = 0; i < roleUsers.size(); i++) {
                System.out.println(roleUsers.get(i).getRoleId());
            }
        }else{
            System.out.println("not found");
        }
    }
}
