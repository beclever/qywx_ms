/**
 * 
 */
package com.grgbanking.css.bean.work;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Copyright (c) 2011 GRG Banking Equipment Co.,Ltd.
 * 
 * @createDate 2012-10-15
 * @author luowei
 * @description 纸质工单详情表
 */
@Entity
@Table(name = "V_paper_form_detail")
public class PaperWorkFormDetail implements Serializable {

	private static final long serialVersionUID = -6081664502867940833L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_SEQ_PAPER_WORKFORM_DETAIL")
	@SequenceGenerator(name = "GENERATOR_SEQ_PAPER_WORKFORM_DETAIL", sequenceName = "SEQ_PAPER_WORKFORM_DETAIL", initialValue = 1, allocationSize = 1)
	@Column(name = "ID")
	private Long id;// 主键

	@Column(name = "FORM_ID")
	private String workFormId;// 工单主键

	@Column(name = "OWNER_USER_CODE")
	private String ownerUserCode;// 所属人

	@Column(name = "PAPER_FORM_CODE")
	private String paperWorkFormCode;// 纸质工单编号

	@Column(name = "USE_STATUS")
	private String useStatus;// 使用状态： 0-不可使用 1-可使用 2-已使用

	@Column(name = "ABANDONED_REASON")
	private String abandonedReson;// 废弃原因： 0-未废弃 1-丢失 2-污损

	@Column(name = "ABANDONED_APPLY_WORKFLOW_ID")
	private String paperWorkFormAbandoneId;//废弃流程主键号
	
	@Column(name = "CREATE_DATE")
	private Date createDate;//创建日期
	
	@Column(name = "USER_ID")
	private Integer userId;//用户主键
	@Column(name ="MODIFY_DATE") 
	private Date modifyDate;//工单使用日期
	@Column(name ="BINDING_SOURCE")
	private String bindingSource;//绑定/作废方式 ： 1手机 0客服系统

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkFormId() {
		return workFormId;
	}

	public void setWorkFormId(String workFormId) {
		this.workFormId = workFormId;
	}

	public String getOwnerUserCode() {
		return ownerUserCode;
	}

	public void setOwnerUserCode(String ownerUserCode) {
		this.ownerUserCode = ownerUserCode;
	}

	public String getPaperWorkFormCode() {
		return paperWorkFormCode;
	}

	public void setPaperWorkFormCode(String paperWorkFormCode) {
		this.paperWorkFormCode = paperWorkFormCode;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getAbandonedReson() {
		return abandonedReson;
	}

	public void setAbandonedReson(String abandonedReson) {
		this.abandonedReson = abandonedReson;
	}

	public String getPaperWorkFormAbandoneId() {
		return paperWorkFormAbandoneId;
	}

	public void setPaperWorkFormAbandoneId(String paperWorkFormAbandoneId) {
		this.paperWorkFormAbandoneId = paperWorkFormAbandoneId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getBindingSource() {
		return bindingSource;
	}

	public void setBindingSource(String bindingSource) {
		this.bindingSource = bindingSource;
	}
}
