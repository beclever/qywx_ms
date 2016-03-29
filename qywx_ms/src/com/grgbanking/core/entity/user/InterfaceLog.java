package com.grgbanking.core.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_MOBILE_INTERFACE_LOG")
public class InterfaceLog implements Serializable {
    /**
     * @Fields serialVersionUID : TODO(接口异常日志)
     */
    private static final long serialVersionUID = 8655644749496973342L;

    private long id;

    private String loginName;// 当前用户名

    private String requestJson;// 请求参数

    private String exceptionMsg;// 异常信息

    private Date createTime;// 发生时间

    private String wsInterfaceUrl;// 接口地址

    private String intfaceMethod;// 接口方法

    @Id
    @SequenceGenerator(name = "SEQ_MOBILE_INTERFACE_LOG_DATE", sequenceName = "SEQ_MOBILE_INTERFACE_LOG_DATE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOBILE_INTERFACE_LOG_DATE")
    @Column(name = "ID", nullable = false, unique = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "LOGIN_NAME")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "REQ_MSG")
    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    @Column(name = "RES_MSG")
    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "URL")
    public String getWsInterfaceUrl() {
        return wsInterfaceUrl;
    }

    public void setWsInterfaceUrl(String wsInterfaceUrl) {
        this.wsInterfaceUrl = wsInterfaceUrl;
    }

    @Column(name = "METHOD")
    public String getIntfaceMethod() {
        return intfaceMethod;
    }

    public void setIntfaceMethod(String intfaceMethod) {
        this.intfaceMethod = intfaceMethod;
    }

}
