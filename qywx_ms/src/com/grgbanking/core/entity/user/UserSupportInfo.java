package com.grgbanking.core.entity.user;

import java.util.Date;

/**
 * 人员支援信息
 * 版权所有：2016-GRGBANKING
 * 项目名称：qywx_ms_pro   
 *
 * 类描述：
 * 类名称：com.grgbanking.core.entity.user.UserSupportInfo     
 * 创建人：WSHUI
 * 创建时间：2016年1月14日 上午10:33:22   
 * 修改人：
 * 修改时间：2016年1月14日 上午10:33:22   
 * 修改备注：   
 * @version   V1.0
 */
public class UserSupportInfo {

    private Long userId;

    private String deleted;

    private String loginName;

    private String name;

    private String userCode;
    /**
     * 支援部门Id
     */
    private Long departmentId;
    
    private String departmentName;
    /**
     * 所属部门Id
     */
    private Long belongDepartmentId;
    
    private String belongDepartmentName;
    /**
     * 是否支援
     */
    private Boolean IsSupport;
    /**
     * 支援开始时间
     */
    private Date supportStartDate;
    
    /**
     * 组织
     */
    private String company;
    /**
     * 片区
     */
    private String segment;
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getDeleted() {
        return deleted;
    }
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public Long getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Long getBelongDepartmentId() {
        return belongDepartmentId;
    }
    public void setBelongDepartmentId(Long belongDepartmentId) {
        this.belongDepartmentId = belongDepartmentId;
    }
    public String getBelongDepartmentName() {
        return belongDepartmentName;
    }
    public void setBelongDepartmentName(String belongDepartmentName) {
        this.belongDepartmentName = belongDepartmentName;
    }
    public Boolean getIsSupport() {
        return IsSupport;
    }
    public void setIsSupport(Boolean isSupport) {
        IsSupport = isSupport;
    }
    public Date getSupportStartDate() {
        return supportStartDate;
    }
    public void setSupportStartDate(Date supportStartDate) {
        this.supportStartDate = supportStartDate;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getSegment() {
        return segment;
    }
    public void setSegment(String segment) {
        this.segment = segment;
    }

}
