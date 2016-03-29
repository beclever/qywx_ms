package com.grgbanking.core.webservice.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.grgbanking.css.common.Page;
import com.grgbanking.webservice.bean.WsResultBean;
import com.grgbanking.webservice.employee.SocEmployeeSupportService;

@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-hibernate.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
// defaultRollback=true不会改变数据库，false会改变数据库
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class SocEmployeeSupportServiceTest {

Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 服务站人员列表
     * @Title: testGetWormformCheckList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @Test
    public void testGetWormformCheckList(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocEmployeeSupportService.class);
        String addr = "http://10.2.15.2:6702/css_wsh/ws/socEmployeeSupportService";
        factory.setAddress(addr);
        SocEmployeeSupportService services = (SocEmployeeSupportService) factory.create();
//        String loginName = "drhua";
        Page queryPage = new Page();
        queryPage.setPage(1);
        queryPage.setRows(100);
        String userCode = "G0200203";
        String departmentId = "268";
        WsResultBean resultBean = services.getEmployeeList(queryPage, userCode);
        if(resultBean.getReturnResult()){
            System.out.println(resultBean.getReturnMessage());
        }
    }
    
    @Test
    public void getDepartments(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocEmployeeSupportService.class);
        String addr = "http://10.2.15.2:6702/css_wsh/ws/socEmployeeSupportService";
        factory.setAddress(addr);
        SocEmployeeSupportService services = (SocEmployeeSupportService) factory.create();

        String departmentId = "";//
        String loginName = "drhua";
        WsResultBean resultBean = services.getDepartments(loginName, departmentId);
        System.out.println(resultBean.toString());
    }
    
    @Test
    public void support(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocEmployeeSupportService.class);
        String addr = "http://10.2.15.2:6702/css_wwb/ws/socEmployeeSupportService";
        factory.setAddress(addr);
        SocEmployeeSupportService services = (SocEmployeeSupportService) factory.create();

        String loginName = "drhua";
        String departmentIdNew = "23582";
        String departmentNameNew = "西安阎良服务站";
        String supportedLoginName = "zhui2";//张琳 zlin8
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = format.format(new Date());
        System.out.println("1111111111111111");
        WsResultBean resultBean = services.doUpdateEmployeeStationHisSupport(loginName, departmentIdNew, departmentNameNew, supportedLoginName, startDate);
        System.out.println("22222222222222222");
        System.out.println(resultBean.toString());
    }
}
