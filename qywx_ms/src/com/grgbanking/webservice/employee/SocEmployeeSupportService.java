package com.grgbanking.webservice.employee;

import javax.jws.WebService;

import com.grgbanking.css.common.Page;
import com.grgbanking.webservice.bean.WsResultBean;

/**
 * 人员支援接口
 * 版权所有：2016-GRGBANKING
 * 项目名称：css_wsh   
 *
 * 类描述：
 * 类名称：com.grgbanking.webservice.service.SocEmployeeSupportService     
 * 创建人：WSH
 * 创建时间：2016年1月15日 下午4:02:03   
 * 修改人：
 * 修改时间：2016年1月15日 下午4:02:03   
 * 修改备注：   
 * @version   V1.0
 */
@WebService
public interface SocEmployeeSupportService {

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
    public WsResultBean doUpdateEmployeeStationHisSupport(String loginName, String departmentIdNew, String departmentNameNew, String supportedLoginName, String startDate);
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
