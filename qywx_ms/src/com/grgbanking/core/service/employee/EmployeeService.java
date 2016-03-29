package com.grgbanking.core.service.employee;

/**
 * 员工接口
 * @author txlong
 *
 */
public interface EmployeeService {
	/**
	 * 通过用户名查询本部门的所有人员
	 * @param name用户名
	 * @return json形式的人员信息
	 */
	public String getUserListByLoginName(String name);
}
