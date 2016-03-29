package com.grgbanking.core.entity.ws;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：AtmMendService
 * 
 * 类描述：webservice接口实体类 类名称：com.grgbanking.sys.bean.WsInterfaceBean 创建人：TXH
 * 创建时间：2015-5-18 下午5:27:15 修改人： 修改时间：2015-5-18 下午5:27:15 修改备注：
 * 
 * @version V1.0 
 */
@Entity
@Table(name = "T_MOBILE_INTERFACE_BASE_DATA")
public class WsInterfaceBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7470421854502267952L;

    private Long id;

    private String iSystem;// 接口系统模块

    private String iName;// 接口名称

    private String iUrl;// 接口链接

    private String contacts;// 负责人

    private String phone;// 联系手机

    private String fixedline;// 联系电话

    private String sign;

    @Id
    @SequenceGenerator(name = "SEQ_MOBILE_INTERFACE_DATE", sequenceName = "SEQ_MOBILE_INTERFACE_DATE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOBILE_INTERFACE_DATE")
    @Column(name = "ID", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "INTERFACE_SYSTEM")
    public String getiSystem() {
        return iSystem;
    }

    public void setiSystem(String iSystem) {
        this.iSystem = iSystem;
    }

    @Column(name = "INTERFACE_NAME")
    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    @Column(name = "INTERFACE_URL")
    public String getiUrl() {
        return iUrl;
    }

    public void setiUrl(String iUrl) {
        this.iUrl = iUrl;
    }

    @Column(name = "CONTACTS")
    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "FIXEDLINE")
    public String getFixedline() {
        return fixedline;
    }

    public void setFixedline(String fixedline) {
        this.fixedline = fixedline;
    }

    @Column(name = "INTERFACE_SIGN")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
