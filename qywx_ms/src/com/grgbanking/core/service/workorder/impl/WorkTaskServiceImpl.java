package com.grgbanking.core.service.workorder.impl;

import org.springframework.stereotype.Service;

import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.service.WebService;
import com.grgbanking.core.service.workorder.WorkTaskService;
import com.grgbanking.css.bean.work.WorkTaskBean;
import com.grgbanking.webservice.bean.WorkFormMonitorBean;
import com.grgbanking.webservice.bean.WsResultBean;
import com.grgbanking.webservice.workorder.SocWorkTaskService;

@Service(value = "workTaskService")
public class WorkTaskServiceImpl extends WebService<SocWorkTaskService> implements WorkTaskService {

    public WorkTaskServiceImpl() {
        super(SocWorkTaskService.class, WsConsts.SocWorkTaskServiceUrl);
    }
    @Override
    public WsResultBean queryWorkTask(String loginName, WsPage wsPage, WorkTaskBean queryBean, String clientType) {
        WsResultBean wsResult = service.queryWorkTask(loginName, wsPage, queryBean, clientType);
        return wsResult;
    }
    @Override
    public WsResultBean getEquipmentInfo(String loginName, String serialNumber, String clientType) {
        WsResultBean wsResult = service.getEquipmentInfo(loginName, serialNumber, clientType);
        return wsResult;
    }
    @Override
    public WsResultBean getToDoWorkTask(String loginName, String serialNumber, String clientType) {
        WsResultBean wsResult = service.getToDoWorkTask(loginName, serialNumber, clientType);
        return wsResult;
    }
    @Override
    public WsResultBean getEquipmentHistoryProblemList(String loginName, String serialNumber, String clientType) {
        WsResultBean wsResult = service.getEquipmentHistoryProblemList(loginName, serialNumber, clientType);
        return wsResult;
    }
    @Override
    public WsResultBean getDepartmentUserList(String loginName, String clientType) {
        WsResultBean wsResult = service.getDepartmentUserList(loginName, clientType);
        return wsResult;
    }
    @Override
    public WsResultBean doSaveWorktask(String loginName, WorkTaskBean workTaskBean, String needCreateWorkform,
            String[] taskIds, String clientType) {
        WsResultBean wsResult = service.doSaveWorktask(loginName, workTaskBean, needCreateWorkform, taskIds, clientType);
        return wsResult;
    }
    @Override
    public WsResultBean getWorkformMonitorList(String loginName, WsPage wsPage, WorkFormMonitorBean queryBean) {
        WsResultBean wsResult = service.getWorkformMonitorList(loginName, wsPage, queryBean);
        return wsResult;
    }
    @Override
    public WsResultBean doUpdateWorkTask(String loginName, WorkTaskBean workTaskBean, String needCreateWorkform,
            String[] taskIds, String clientType) throws Exception {
        WsResultBean wsResult = service.doUpdateWorkTask(loginName, workTaskBean, needCreateWorkform, taskIds, clientType);
        return wsResult;
    }
    @Override
    public WsResultBean doSaveDispatch(String loginName, WorkTaskBean workTaskBean, String needCreateWorkform,
            String engineerId, String[] taskIds, String appointmentTime, String clientType) throws Exception {
        WsResultBean wsResult = service.doSaveDispatch(loginName, workTaskBean, needCreateWorkform, engineerId, taskIds, appointmentTime, clientType);
        return wsResult;
    }
    @Override
    public WsResultBean doDeletWorkTask(String loginName, Integer taskId, String clientType) {
        WsResultBean wsResult = service.doDeletWorkTask(loginName, taskId, clientType);
        return wsResult;
    }

}
