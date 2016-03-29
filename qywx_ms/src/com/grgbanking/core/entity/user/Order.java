package com.grgbanking.core.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_AMS_ORDER", uniqueConstraints = @UniqueConstraint(columnNames = { "PONUMBER" }))
public class Order implements Serializable {
	/**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */ 
    private static final long serialVersionUID = 1L;
    /**
	 * 
	 */
	private Long id;
	private String poNumber;
	private String content;
	private Date createDate;
	private Date upDate;
	private String loginName;
	private String qrCode;
	private String attachment ; 
	

	@Id
	@SequenceGenerator(name = "SEQ_AMS_ORDER", sequenceName = "SEQ_AMS_ORDER")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AMS_ORDER")
	@Column(name = "ID", nullable = false, unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="PONUMBER")
	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	@Lob 
	@Basic(fetch = FetchType.EAGER) 
	@Column(name="CONTENT", columnDefinition="CLOB")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="UP_DATE")
	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}

	@Column(name="LOGIN_NAME")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name="QR_CODE")
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
	@Column(name="ATTACHMENT")
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	
}
