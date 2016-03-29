package com.grgbanking.core.webservice.service;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.grgbanking.common.consts.Consts;
import com.grgbanking.core.entity.equipment.WsDeviceLocationBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultPendingWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultUploadWorkFormTimeBean;
import com.grgbanking.webservice.workorder.SocWorkFormWsService;

@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-hibernate.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
// defaultRollback=true不会改变数据库，false会改变数据库
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class SocWorkFormWsServiceTest {

Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 主任审核列表
     * @Title: testGetWormformCheckList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @Test
    public void testGetWormformCheckList(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkFormWsService.class);
        String addr = "http://10.2.15.2:6702/css_wsh/ws/socWorkFormWsService";
        factory.setAddress(addr);
        SocWorkFormWsService services = (SocWorkFormWsService) factory.create();
        String loginName = "drhua";
        String status = "主任审核";
        WsPage wsPage = new WsPage(Consts.DEFAULT_ROWS, "1");
        String clientType = "CSS";
        WsResultPendingWorkFormBean result = services.getCheckPendingWorkForm(loginName, status, wsPage, clientType);
        if(result != null && result.getPendingWorkform() != null)
        {
            for(int i=0;i<result.getPendingWorkform().size();i++){
                System.out.println(result.getPendingWorkform().get(i).getPoNumber());
            }
        }
//        logger.info(result.toString());
    }
    
    /**
     * 库管员审核列表
     * @Title: testGetWormformLibraryCheckList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @Test
    public void testGetWormformLibraryCheckList(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkFormWsService.class);
        String addr = "http://10.2.15.2:6702/css_wsh/ws/socWorkFormWsService";
        factory.setAddress(addr);
        SocWorkFormWsService services = (SocWorkFormWsService) factory.create();
        String loginName = "hsyan";
        String status = "库管员审核";
        WsPage wsPage = new WsPage(Consts.DEFAULT_ROWS, "1");
        String clientType = "CSS";
        WsResultPendingWorkFormBean result = services.getCheckPendingWorkForm(loginName, status, wsPage, clientType);
        if(result != null && result.getPendingWorkform() != null)
        {
            for(int i=0;i<result.getPendingWorkform().size();i++){
                System.out.println(result.getPendingWorkform().get(i).getPoNumber());
            }
        }
//        logger.info(result.toString());
    }
    
    @Test
    public void testUpload(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkFormWsService.class);
        String addr = "http://10.2.8.166:8087/css/ws/socWorkFormWsService";
        factory.setAddress(addr);
        SocWorkFormWsService services = (SocWorkFormWsService) factory.create();
        String loginName = "drhua";
        String status = "主任审核";
        WsPage wsPage = new WsPage(Consts.DEFAULT_ROWS, "1");
        String clientType = "CSS";
        WsDeviceLocationBean GPSInfo = new WsDeviceLocationBean("111", "88", "无");
        WsResultUploadWorkFormTimeBean result = services.doUploadWorkFormTimeGPS(loginName, "3946674", GPSInfo, "5", 01L);
        System.out.println(result);
//        logger.info(result.toString());
    }
}
