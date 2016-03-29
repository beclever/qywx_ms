package com.grgbanking.webservice.employee;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.grgbanking.webservice.bean.WsResultBean;

/**
 * 部门接口
 * 提供人员：开发二组 伍伟邦
 */
@WebService
@SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.RPC)
public interface SocEmployeeWsService {
	/**
	 * 通过部门ID查询本部门的所有人员
	 * @param departmentId部门ID
	 * @return json形式的人员信息
	 */
	public String getUserListByDepartmentId(Integer departmentId);

	/**
	 * 通过用户名查询本部门的所有人员
	 * @param name 用户名
	 * @return json形式的人员信息
	 */
	public String getUserListByLoginName(String name);

	public WsResultBean doEmployeeSupportCreate(String arg0, String arg1,
			String arg2, String arg3);

	public WsResultBean doEmployeeSupportCancel(String arg0, String arg1,
			String arg2, String arg3);
}
