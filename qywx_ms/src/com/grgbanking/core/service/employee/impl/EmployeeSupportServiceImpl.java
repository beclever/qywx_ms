package com.grgbanking.core.service.employee.impl;

import org.springframework.stereotype.Service;

import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.service.WebService;
import com.grgbanking.core.service.employee.EmployeeSupportService;
import com.grgbanking.css.common.Page;
import com.grgbanking.webservice.bean.WsResultBean;
import com.grgbanking.webservice.employee.SocEmployeeSupportService;

@Service(value = "employeeSupportService")
public class EmployeeSupportServiceImpl extends WebService<SocEmployeeSupportService> implements EmployeeSupportService{

    public EmployeeSupportServiceImpl() {
        super(SocEmployeeSupportService.class, WsConsts.SocEmployeeSupportServiceUrl);
    }

    @Override
    public WsResultBean getEmployeeList(Page queryPage, String userCode) {
        
        WsResultBean result = this.service.getEmployeeList(queryPage, userCode);
        return result;
    }

    @Override
    public WsResultBean doUpdateEmployeeStationHisSupport(String userCode, String departmentIdNew,
            String departmentNameNew, String userId, String startDate) {
        WsResultBean result = this.service.doUpdateEmployeeStationHisSupport(userCode, departmentIdNew, departmentNameNew, userId, startDate);
        return result;
    }
    
    @Override
    public WsResultBean doCancelEmployeeStationHisSupport(String loginName, String supportedLoginName, String endDate)
    {
        WsResultBean result = this.service.doCancelEmployeeStationHisSupport(loginName, supportedLoginName, endDate);
        return result;
    }

    @Override
    public WsResultBean getDepartments(String loginName, String departmentId) {
        WsResultBean result = this.service.getDepartments(loginName, departmentId);
        return result;
    }
}
