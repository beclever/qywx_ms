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

import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultFillWorkFormBean;
import com.grgbanking.css.bean.work.WorkTaskBean;
import com.grgbanking.webservice.bean.WorkFormMonitorBean;
import com.grgbanking.webservice.bean.WsResultBean;
import com.grgbanking.webservice.workorder.SocWorkFormWsService;
import com.grgbanking.webservice.workorder.SocWorkTaskService;

@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-hibernate.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
// defaultRollback=true不会改变数据库，false会改变数据库
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class SocWorkTaskServiceTest {

Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 服务站人员列表
     * @Title: testGetWormformCheckList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @Test
    public void testGetWormformCheckList(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkTaskService.class);
        String addr = "http://10.2.15.2:6702/css_wwb/ws/socWorkTaskService";
        factory.setAddress(addr);
        SocWorkTaskService services = (SocWorkTaskService) factory.create();
        String loginName = "drhua";
        WsPage wsPage = new WsPage();
        wsPage.setPage(1);
        wsPage.setRows(5);
        String clientType = "wechat";
        WorkTaskBean queryBean = new WorkTaskBean();
        String[] sa = {"未分配","待处理"};
        queryBean.setStatusArray(sa);
        WsResultBean resultBean = services.queryWorkTask(loginName, wsPage, queryBean, clientType);
        if(resultBean.getReturnResult()){
            System.out.println(resultBean.getReturnMessage());
        }
    }
    @Test
    public void getEquipmentInfo(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkTaskService.class);
        String addr = "http://10.2.15.2:6702/css_wwb/ws/socWorkTaskService";
        factory.setAddress(addr);
        SocWorkTaskService services = (SocWorkTaskService) factory.create();
        String loginName = "zchao";
        WsPage wsPage = new WsPage();
        wsPage.setPage(1);
        wsPage.setRows(5);
        String clientType = "wechat";
        WorkTaskBean queryBean = new WorkTaskBean();
        String[] sa = {"未分配","待处理"};
        queryBean.setStatusArray(sa);
        String serialNumber = "96079";//17739";//4986";
        WsResultBean resultBean = services.getEquipmentInfo(loginName, serialNumber, clientType);
        if(resultBean.getReturnResult()){
            System.out.println(resultBean.getReturnMessage());
//            {"branchName":"西安商行泾河工业园支行","installAddress":"西安市高陵泾河工业园马家湾车城花园","equipmentId":"54630","atmManagerTel":"029-86032558","equipmentDeptId":"268"}
        }
    }
    @Test
    public void getToDoWorkTask(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkTaskService.class);
        String addr = "http://10.2.15.2:6702/css_wwb/ws/socWorkTaskService";
        factory.setAddress(addr);
        SocWorkTaskService services = (SocWorkTaskService) factory.create();
        String loginName = "drhua";
        WsPage wsPage = new WsPage();
        wsPage.setPage(1);
        wsPage.setRows(5);
        String clientType = "wechat";
        WorkTaskBean queryBean = new WorkTaskBean();
        String[] sa = {"未分配","待处理"};
        queryBean.setStatusArray(sa);
        String serialNumber = "4011";
        WsResultBean resultBean = services.getToDoWorkTask(loginName, serialNumber, clientType);
        if(resultBean.getReturnResult()){
            System.out.println(resultBean.getReturnMessage());
            //[{"taskId":42959702,"taskType":"升级","upgradeName":"dfdfdf7868lin","appointmentTime":"","planStartDate":"2015-12-02","planEndDate":"2015-12-02","engineerName":"","serialNumber":"4011","status":"未分配"}, {"taskId":42959705,"taskType":"升级","upgradeName":"51","appointmentTime":"","planStartDate":"2015-12-02","planEndDate":"2015-12-11","engineerName":"","serialNumber":"4011","status":"未分配"}, {"taskId":42959708,"taskType":"升级","upgradeName":"lin234235435","appointmentTime":"","planStartDate":"2015-12-02","planEndDate":"2015-12-03","engineerName":"","serialNumber":"4011","status":"未分配"}, {"taskId":42959711,"taskType":"升级","upgradeName":"23423lin","appointmentTime":"","planStartDate":"2015-12-01","planEndDate":"2015-12-04","engineerName":"","serialNumber":"4011","status":"未分配"}, {"taskId":42959714,"taskType":"巡检","upgradeName":"lin32434535","appointmentTime":"","planStartDate":"2015-12-03","planEndDate":"2015-12-04","engineerName":"","serialNumber":"4011","status":"未分配"}, {"taskId":42959717,"taskType":"巡检","upgradeName":"3243253lin","appointmentTime":"","planStartDate":"2015-12-01","planEndDate":"2015-12-04","engineerName":"","serialNumber":"4011","status":"未分配"}, {"taskId":42959720,"taskType":"巡检","upgradeName":"3543lin","appointmentTime":"","planStartDate":"2015-12-01","planEndDate":"2015-12-03","engineerName":"","serialNumber":"4011","status":"未分配"}]
        }
    }
    @Test
    public void getEquipmentHistoryProblemList(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkTaskService.class);
        String addr = "http://10.2.15.2:6702/css_wwb/ws/socWorkTaskService";
        factory.setAddress(addr);
        SocWorkTaskService services = (SocWorkTaskService) factory.create();
        String loginName = "drhua";
        WsPage wsPage = new WsPage();
        wsPage.setPage(1);
        wsPage.setRows(5);
        String clientType = "wechat";
        WorkTaskBean queryBean = new WorkTaskBean();
        String[] sa = {"未分配","待处理"};
        queryBean.setStatusArray(sa);
        String serialNumber = "4011";
        WsResultBean resultBean = services.getEquipmentHistoryProblemList(loginName, serialNumber, clientType);
        if(resultBean.getReturnResult()){
            System.out.println(resultBean.getReturnMessage());
            //[{"description":"ghb","level":"1","recordPerson":"董荣华","recordTime":"2015-11-26","status":"未解决"}, {"description":"ghb","level":"1","recordPerson":"董荣华","recordTime":"2015-11-26","status":"未解决"}, {"description":"cghhhjjhh","level":"3","recordPerson":"董荣华","recordTime":"2015-11-26","status":"未解决"}]
        }
    }
    @Test
    public void getDepartmentUserList(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkTaskService.class);
        String addr = "http://10.2.15.2:6702/css_wwb/ws/socWorkTaskService";
        factory.setAddress(addr);
        SocWorkTaskService services = (SocWorkTaskService) factory.create();
        String loginName = "drhua";
        WsPage wsPage = new WsPage();
        wsPage.setPage(1);
        wsPage.setRows(5);
        String clientType = "wechat";
        WorkTaskBean queryBean = new WorkTaskBean();
        String[] sa = {"未分配","待处理"};
        queryBean.setStatusArray(sa);
        String serialNumber = "4011";
        WsResultBean resultBean = services.getDepartmentUserList(loginName, clientType);
        if(resultBean.getReturnResult()){
            System.out.println(resultBean.getReturnMessage());
            //[{"beforeSupportRoles":"","belongDepartmentId":0,"centerPlatformId":0,"createDate":null,"createUserId":0,"currentTime":{"date":26,"day":2,"hours":16,"minutes":2,"month":0,"seconds":20,"time":1453795340754,"timezoneOffset":-480,"year":116},"defaultStyle":"","deleted":"","departmentCode":"","departmentId":0,"departmentIds":[],"departmentName":"","departmentType":"","email":"","employeeNo":"","engineerGradeH68N":"","haveUsers":0,"loginIp":"","loginName":"","loginTime":null,"mobilephone":"","modifyDate":null,"modifyUserId":0,"name":"董荣华","numWF":"993","numWT":"13","parUserId":"","parentId":"","password":"","phone":"","provincePlatform":"","provincePlatformId":0,"roleNameString":"","roleString":"","sex":"","shortMessage":"","userCode":"","userId":1451,"version":0},{"beforeSupportRoles":"","belongDepartmentId":0,"centerPlatformId":0,"createDate":null,"createUserId":0,"currentTime":{"date":26,"day":2,"hours":16,"minutes":2,"month":0,"seconds":20,"time":1453795340766,"timezoneOffset":-480,"year":116},"defaultStyle":"","deleted":"","departmentCode":"","departmentId":0,"departmentIds":[],"departmentName":"","departmentType":"","email":"","employeeNo":"","engineerGradeH68N":"","haveUsers":0,"loginIp":"","loginName":"","loginTime":null,"mobilephone":"","modifyDate":null,"modifyUserId":0,"name":"张博2","numWF":"2","numWT":"1","parUserId":"","parentId":"","password":"","phone":"","provincePlatform":"","provincePlatformId":0,"roleNameString":"","roleString":"","sex":"","shortMessage":"","userCode":"","userId":73358,"version":0},{"beforeSupportRoles":"","belongDepartmentId":0,"centerPlatformId":0,"createDate":null,"createUserId":0,"currentTime":{"date":26,"day":2,"hours":16,"minutes":2,"month":0,"seconds":20,"time":1453795340766,"timezoneOffset":-480,"year":116},"defaultStyle":"","deleted":"","departmentCode":"","departmentId":0,"departmentIds":[],"departmentName":"","departmentType":"","email":"","employeeNo":"","engineerGradeH68N":"","haveUsers":0,"loginIp":"","loginName":"","loginTime":null,"mobilephone":"","modifyDate":null,"modifyUserId":0,"name":"郭震","numWF":"1","numWT":"2","parUserId":"","parentId":"","password":"","phone":"","provincePlatform":"","provincePlatformId":0,"roleNameString":"","roleString":"","sex":"","shortMessage":"","userCode":"","userId":65330,"version":0},{"beforeSupportRoles":"","belongDepartmentId":0,"centerPlatformId":0,"createDate":null,"createUserId":0,"currentTime":{"date":26,"day":2,"hours":16,"minutes":2,"month":0,"seconds":20,"time":1453795340767,"timezoneOffset":-480,"year":116},"defaultStyle":"","deleted":"","departmentCode":"","departmentId":0,"departmentIds":[],"departmentName":"","departmentType":"","email":"","employeeNo":"","engineerGradeH68N":"","haveUsers":0,"loginIp":"","loginName":"","loginTime":null,"mobilephone":"","modifyDate":null,"modifyUserId":0,"name":"张琳","numWF":"33","numWT":"1","parUserId":"","parentId":"","password":"","phone":"","provincePlatform":"","provincePlatformId":0,"roleNameString":"","roleString":"","sex":"","shortMessage":"","userCode":"","userId":67714,"version":0},{"beforeSupportRoles":"","belongDepartmentId":0,"centerPlatformId":0,"createDate":null,"createUserId":0,"currentTime":{"date":26,"day":2,"hours":16,"minutes":2,"month":0,"seconds":20,"time":1453795340768,"timezoneOffset":-480,"year":116},"defaultStyle":"","deleted":"","departmentCode":"","departmentId":0,"departmentIds":[],"departmentName":"","departmentType":"","email":"","employeeNo":"","engineerGradeH68N":"","haveUsers":0,"loginIp":"","loginName":"","loginTime":null,"mobilephone":"","modifyDate":null,"modifyUserId":0,"name":"张辉","numWF":"17","numWT":"2","parUserId":"","parentId":"","password":"","phone":"","provincePlatform":"","provincePlatformId":0,"roleNameString":"","roleString":"","sex":"","shortMessage":"","userCode":"","userId":-935,"version":0},{"beforeSupportRoles":"","belongDepartmentId":0,"centerPlatformId":0,"createDate":null,"createUserId":0,"currentTime":{"date":26,"day":2,"hours":16,"minutes":2,"month":0,"seconds":20,"time":1453795340769,"timezoneOffset":-480,"year":116},"defaultStyle":"","deleted":"","departmentCode":"","departmentId":0,"departmentIds":[],"departmentName":"","departmentType":"","email":"","employeeNo":"","engineerGradeH68N":"","haveUsers":0,"loginIp":"","loginName":"","loginTime":null,"mobilephone":"","modifyDate":null,"modifyUserId":0,"name":"董勇","numWF":"52","numWT":"2","parUserId":"","parentId":"","password":"","phone":"","provincePlatform":"","provincePlatformId":0,"roleNameString":"","roleString":"","sex":"","shortMessage":"","userCode":"","userId":-383,"version":0},{"beforeSupportRoles":"","belongDepartmentId":0,"centerPlatformId":0,"createDate":null,"createUserId":0,"currentTime":{"date":26,"day":2,"hours":16,"minutes":2,"month":0,"seconds":20,"time":1453795340769,"timezoneOffset":-480,"year":116},"defaultStyle":"","deleted":"","departmentCode":"","departmentId":0,"departmentIds":[],"departmentName":"","departmentType":"","email":"","employeeNo":"","engineerGradeH68N":"","haveUsers":0,"loginIp":"","loginName":"","loginTime":null,"mobilephone":"","modifyDate":null,"modifyUserId":0,"name":"张强强","numWF":"3","numWT":"1","parUserId":"","parentId":"","password":"","phone":"","provincePlatform":"","provincePlatformId":0,"roleNameString":"","roleString":"","sex":"","shortMessage":"","userCode":"","userId":63660,"version":0},{"beforeSupportRoles":"","belongDepartmentId":0,"centerPlatformId":0,"createDate":null,"createUserId":0,"currentTime":{"date":26,"day":2,"hours":16,"minutes":2,"month":0,"seconds":20,"time":1453795340770,"timezoneOffset":-480,"year":116},"defaultStyle":"","deleted":"","departmentCode":"","departmentId":0,"departmentIds":[],"departmentName":"","departmentType":"","email":"","employeeNo":"","engineerGradeH68N":"","haveUsers":0,"loginIp":"","loginName":"","loginTime":null,"mobilephone":"","modifyDate":null,"modifyUserId":0,"name":"董莹","numWF":"2","numWT":"0","parUserId":"","parentId":"","password":"","phone":"","provincePlatform":"","provincePlatformId":0,"roleNameString":"","roleString":"","sex":"","shortMessage":"","userCode":"","userId":67686,"version":0},{"beforeSupportRoles":"","belongDepartmentId":0,"centerPlatformId":0,"createDate":null,"createUserId":0,"currentTime":{"date":26,"day":2,"hours":16,"minutes":2,"month":0,"seconds":20,"time":1453795340770,"timezoneOffset":-480,"year":116},"defaultStyle":"","deleted":"","departmentCode":"","departmentId":0,"departmentIds":[],"departmentName":"","departmentType":"","email":"","employeeNo":"","engineerGradeH68N":"","haveUsers":0,"loginIp":"","loginName":"","loginTime":null,"mobilephone":"","modifyDate":null,"modifyUserId":0,"name":"赵亮5","numWF":"1","numWT":"2","parUserId":"","parentId":"","password":"","phone":"","provincePlatform":"","provincePlatformId":0,"roleNameString":"","roleString":"","sex":"","shortMessage":"","userCode":"","userId":73626,"version":0}]
        }
    }
    @Test
    public void doSaveWorktask(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkTaskService.class);
        String addr = "http://10.2.15.2:6702/css_wwb/ws/socWorkTaskService";
        factory.setAddress(addr);
        SocWorkTaskService services = (SocWorkTaskService) factory.create();
        String loginName = "drhua";
        String clientType = "wechat";
        String serialNumber = "17739";
        String[] taskIds = null;//{"42959702"};
        String needCreateWorkform = "Y";
        WorkTaskBean workTaskBean = new WorkTaskBean();
        
        workTaskBean.setEngineerId(1451);
        workTaskBean.setEngineerName("董荣华");
        workTaskBean.setSerialNumber(serialNumber);
        workTaskBean.setEquipmentId(60095);
        workTaskBean.setTaskType("WX,QT");
        //{"branchName":"西安商行泾河工业园支行","installAddress":"西安市高陵泾河工业园马家湾车城花园",
        //"equipmentId":"54630","atmManagerTel":"029-86032558","equipmentDeptId":"268"}
        workTaskBean.setBranchName("西安商行泾河工业园支行");
        workTaskBean.setInstallAddress("西安市高陵泾河工业园马家湾车城花园");
        workTaskBean.setEquipmentDeptId(268);
        //报修人接待人
//        workTaskBean.setRepairsContactId(20916);
        workTaskBean.setRepairsManName("周有涛");
        workTaskBean.setRepairsTelephone("029-86032558");
        workTaskBean.setRepairsMoblie("13977660588");
//        workTaskBean.setReceiveContactId(351827);
        workTaskBean.setReceiveManName("Ffg");
        workTaskBean.setReceiveMoblie("13677593316");
        workTaskBean.setReceiveTelephone("029-86032558");
        workTaskBean.setTaskContent("测试ssss");
        workTaskBean.setTaskLevel("常规");
        
        workTaskBean.setAppointmentTime(new Date());
        workTaskBean.setReportTime(new Date());
        workTaskBean.setCreateUserId(1451);
        try {
            WsResultBean resultBean = services.doSaveWorktask(loginName,workTaskBean, needCreateWorkform, taskIds, clientType);
            if(resultBean.getReturnResult()){
                System.out.println(resultBean.getReturnMessage());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("============="+e.getMessage()+"============");
        }
    }
    //已派工单
    @Test
    public void getWorkformMonitorList(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkTaskService.class);
        String addr = "http://10.2.15.2:6702/css_wwb/ws/socWorkTaskService";
        factory.setAddress(addr);
        SocWorkTaskService services = (SocWorkTaskService) factory.create();
        String loginName = "drhua";
        WsPage wsPage = new WsPage();
        wsPage.setPage(1);
        wsPage.setRows(50);
        String clientType = "wechat";
        WorkFormMonitorBean queryBean = new WorkFormMonitorBean();
        String[] sa = {"未分配","待处理"};
        String serialNumber = "4011";
        WsResultBean resultBean = services.getWorkformMonitorList(loginName, wsPage, queryBean);
        if(resultBean.getReturnResult()){
            System.out.println(resultBean.getReturnMessage());
        }
    }
    //待派工单
    @Test
    public void getWaitOrderList(){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkTaskService.class);
        String addr = "http://10.2.8.166:8087/css/ws/socWorkTaskService";
        factory.setAddress(addr);
        SocWorkTaskService services = (SocWorkTaskService) factory.create();
        String loginName = "drhua";
        WsPage wsPage = new WsPage();
        wsPage.setPage(1);
        wsPage.setRows(50);
        String clientType = "wechat";
        WorkTaskBean queryBean = new WorkTaskBean();
        String[] sa = {"未分配","待处理"};
        String serialNumber = "4011";
        queryBean.setSerialNumber(serialNumber);
        queryBean.setStatusArray(sa);
        WsResultBean resultBean = services.queryWorkTask(loginName, wsPage, queryBean, clientType);
        if(resultBean.getReturnResult()){
            System.out.println(resultBean.getReturnMessage());
        }
    }
    
    
    @Test
    public void get()
    {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkFormWsService.class);
        String addr = "http://10.2.15.2:6702/css_wwb/ws/socWorkFormWsService";
        factory.setAddress(addr);
        SocWorkFormWsService services = (SocWorkFormWsService) factory.create();
        String loginName = "drhua";
        String workformId = "3471960";
        String equipmentId="1211991";
//        WsResultFillWorkFormBean bean = services.getWorkFormBean(loginName, workformId);
        WsResultFillWorkFormBean bean = services.beginFillInWorkForm(loginName, equipmentId, workformId);
        System.out.println("fdsfdsfdsfdsf");
    }
}
