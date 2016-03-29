package com.grgbanking.core.service.employee;

import com.grgbanking.css.common.Page;
import com.grgbanking.webservice.bean.WsResultBean;

/**
 * 人员支援接口
 * 版权所有：2016-GRGBANKING
 * 项目名称：qywx_ms_pro   
 *
 * 类描述：
 * 类名称：com.grgbanking.core.service.employee.EmployeeSupportService     
 * 创建人：yt
 * 创建时间：2016年1月18日 上午10:30:56   
 * 修改人：
 * 修改时间：2016年1月18日 上午10:30:56   
 * 修改备注：   
 * @version   V1.0
 */
public interface EmployeeSupportService {

    /**
     * 指定服务站的人员信息列表
     * @Title: getEmployeeList
     * @param queryPage
     * @param userCode
     * @return
     */
    public WsResultBean getEmployeeList(Page queryPage, String userCode);

    /**
     * 支援
     * @Title: doUpdateEmployeeStationHisSupport
     * @param loginName 操作人员登录名
     * @param departmentIdNew 支援部门Id
     * @param departmentNameNew 支援部门名称
     * @param supportedLoginName 被支援人员登录名
     * @param startDate 支援开始日期
     * @return
     */
    public WsResultBean doUpdateEmployeeStationHisSupport(String userCode, String departmentIdNew, String departmentNameNew, String userId, String startDate);
    
    /**
     * 取消支援
     * @Title: doCancelEmployeeStationHisSupport
     * @param loginName
     * @param supportedLoginName
     * @param endDate
     * @return
     */
    public WsResultBean doCancelEmployeeStationHisSupport(String loginName, String supportedLoginName, String endDate);
    
    public WsResultBean getDepartments(String loginName, String departmentId);
}
