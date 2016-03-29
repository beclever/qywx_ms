package com.grgbanking.core.service.employee.impl;

import org.springframework.stereotype.Service;

import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.service.WebService;
import com.grgbanking.core.service.employee.EmployeeService;
import com.grgbanking.webservice.employee.SocEmployeeWsService;

@Service("employeeService")
public class EmployeeServiceImpl extends WebService<SocEmployeeWsService> implements EmployeeService {

	public EmployeeServiceImpl() {
		super(SocEmployeeWsService.class, WsConsts.SocEmployeeUrl);
	}

	@Override
	public String getUserListByLoginName(String name) {
		return service.getUserListByLoginName(name);
	}

}
