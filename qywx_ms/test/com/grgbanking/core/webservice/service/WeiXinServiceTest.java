package com.grgbanking.core.webservice.service;

import java.util.Date;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.alibaba.fastjson.JSON;
import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.entity.workorder.WorkFormWarning;
import com.grgbanking.webservice.service.WeiXinService;


@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-hibernate.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
// defaultRollback=true不会改变数据库，false会改变数据库
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class WeiXinServiceTest {
    
    Logger logger = LoggerFactory.getLogger(getClass());
    
    @Test
    public void testSave() {
        
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(WeiXinService.class);
        factory.setAddress(WsConsts.WeiXinService);
        WeiXinService services = (WeiXinService) factory.create();
        WorkFormWarning workFormWarning = new WorkFormWarning();
        workFormWarning.setWorkformId(Long.valueOf("1809029"));//SHX0011512250004
        workFormWarning.setWarningType("3");
        workFormWarning.setSatisfactionType("2");
        workFormWarning.setCreateDate(new Date());
        workFormWarning.setWarningRecode("到达坐标:" + "2等级" + ",距离设备" + 2000 + "公里");
        String result = services.doSaveServicesWorkformWarning(JSON.toJSONString(workFormWarning));
        logger.info(result);
    }
}
