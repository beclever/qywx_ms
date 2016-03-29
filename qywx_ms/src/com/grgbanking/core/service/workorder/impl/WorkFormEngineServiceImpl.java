package com.grgbanking.core.service.workorder.impl;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Service;

import com.grgbanking.common.consts.Consts;
import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.entity.workorder.WsWorkTaskBean;
import com.grgbanking.core.entity.ws.WsBorrowInfoBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultBean;
import com.grgbanking.core.entity.ws.WsResultPendingWorkFormBean;
import com.grgbanking.core.entity.ws.WsWorkFormBean;
import com.grgbanking.core.exception.WsInterfaceException;
import com.grgbanking.core.service.WebService;
import com.grgbanking.core.service.workorder.WorkFormEngineService;
import com.grgbanking.webservice.workorder.SocWorkFormEngine;

@Service(value = "workFormEngineService")
public class WorkFormEngineServiceImpl extends WebService<SocWorkFormEngine> implements WorkFormEngineService {

    public WorkFormEngineServiceImpl() {
        super(SocWorkFormEngine.class, WsConsts.SocWorkFormEngineUrl);
    }

    @Override
    public WsResultBean checkWorkformStepInStoreInfo(String poNumber, String arriveTime, String workStartDate,
            String workFinishDate, String buttonType, String checkUserCode, String checkRemark, String targetStoreId,
            String clientType) throws Exception {
        try {
            return this.service.checkWorkformStepInStoreInfo(poNumber, arriveTime, workStartDate, workFinishDate,
                    buttonType, checkUserCode, checkRemark, targetStoreId, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkFormEngineUrl, "checkWorkformStepInStoreInfo");
        }
    }
    @Override
    public WsResultBean checkWorkformStepInStoreInfoForCSS(String poNumber,String reportTime, String appointmentDate, String arriveTime, String workStartDate,
            String workFinishDate, String buttonType, String checkUserCode, String checkRemark, String targetStoreId,
            String clientType) throws Exception {
        try {
            return this.service.checkWorkformStepInStoreInfoForCSS(poNumber,reportTime, appointmentDate, arriveTime, workStartDate, workFinishDate,
                    buttonType, checkUserCode, checkRemark, targetStoreId, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkFormEngineUrl, "checkWorkformStepInStoreInfo");
        }
    }

    @Override
    /**
     * 添加任务
     * @param poNumber
     * @param wsWorkTaskBean
     * @param createUserCode操作人员
     * @param clientType 方式(SA/MP)
     * @return
     */
    public WsResultBean addWorkTaskInfo(String poNumber, List<WsWorkTaskBean> wsWorkTaskBean, String createUserCode,
            String clientType) throws Exception {
        try {
            return service.addWorkTaskInfo(poNumber, wsWorkTaskBean, createUserCode, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkFormEngineUrl, "addWorkTaskInfo");
        }
    }

    public WsResultBean createWorkformInfo(WsWorkFormBean wsWorkFormBean, String clientType) throws Exception {
        try {
            return service.createWorkformInfo(wsWorkFormBean, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkFormEngineUrl, "createWorkformInfo");
        }
    }

    public WsResultBean checkWorkFormInfo(String serialNumber, String type) throws Exception {
        try {
            return service.checkWorkFormInfo(serialNumber, type);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkFormEngineUrl, "checkWorkFormInfo");
        }
    }

    public WsResultBean deleteWorkTaskInfoByPoNumber(String poNumber, String createUserCode, String clientType)
            throws Exception {
        try {
            return service.deleteWorkTaskInfoByPoNumber(poNumber, createUserCode, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkFormEngineUrl, "deleteWorkTaskInfoByPoNumber");
        }
    }

    @Override
    public WsResultPendingWorkFormBean getPendingWorkForm(String loginCode, WsPage wsPage) throws Exception {
        try {
            return service.getPendingWorkForm(loginCode, wsPage, Consts.CREATE_TYPE);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkFormEngineUrl, "getPendingWorkForm");
        }
    }
    public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SocWorkFormEngine.class);
        factory.setAddress("http://10.2.15.244:8080/css/ws/socWorkFormEngine");
        SocWorkFormEngine services = (SocWorkFormEngine) factory.create();
        WsPage page = new WsPage();
        page.setRows(5);
        page.setPage(1);
        WsResultPendingWorkFormBean result = null;
        try {
            //result = services.getPendingWorkForm("drhua", page, "");
            
            WsResultBean  wsr = services.doDeleteTask("SHX0011511260030","42950908", "G0200203", "phone");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public WsResultBean createWorkform(WsWorkFormBean wsWorkFormBean) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean cancelWorkform(String poNumber) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean cancelWorkformInfo(String poNumber, String checkUserCode, String clientType) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean addWorkTask(String poNumber, List<WsWorkTaskBean> wsWorkTaskBean, String createUserCode)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean deleteWorkTask(String poNumber, List<WsWorkTaskBean> wsWorkTaskBean, String createUserCode)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean deleteWorkTaskInfo(String poNumber, List<WsWorkTaskBean> wsWorkTaskBean, String createUserCode,
            String clientType) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean updateWorkFormSptInfo(String workFormId, String sptContent, String taskContent)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean doUpdateAppointmentDate(String checkLongName, String workFormId, String appointmentDate)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean doUpdateAppointmentDateInfo(String checkLongName, String workFormId, String appointmentDate,
            String clientType) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean checkWorkformStepInStore(String poNumber, String arriveTime, String workStartDate,
            String workFinishDate, String buttonType, String checkUserCode, String checkRemark, String targetStoreId)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean backWorkform(String poNumber, String checkUserCode, String checkRemark) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean backWorkformInfo(String poNumber, String checkUserCode, String checkRemark, String clientType)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean doWriteWorkFormProcess(String poNumber, String writeUserCode, String writeMessage)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean doWriteWorkFormProcessInfo(String poNumber, String writeUserCode, String writeMessage,
            String clientType) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean addFollowEngineer(String poNumber, List<String> followEngineerCodeList) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean updateWorkformAdvice(Integer workformId, String advice) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String changeWorkform(String receiveStr) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteWorkform(String workformId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteTask(String taskId) throws Exception {
        try {
            return service.deleteTask(taskId);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkFormEngineUrl, "deleteTask");
        }
    }
    
    @Override
    public WsResultBean doDeleteTask(String poNumber,String taskId, String createUserCode, String clientType) throws Exception
    {
        try {
            return service.doDeleteTask(poNumber,taskId, createUserCode, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkFormEngineUrl, "doDeleteTask");
        }
    }

    @Override
    public List<WsBorrowInfoBean> findBorrowInfoList(String createUserCode, String[] moduleLevels, Integer workformId,
            String clientType) throws Exception {
        try {
            return service.findBorrowInfoList(createUserCode,moduleLevels, workformId, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkFormEngineUrl, "findBorrowInfoList");
        }
    }
}
