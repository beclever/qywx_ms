/**
 * WorkFormComments.java
 * Product:Grgbanking
 * Version:1.0
 * Copyright 2009 by DNE
 * All Rights Reserved.
 */
package com.grgbanking.css.bean.work;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @ClassName com.grgbanking.services.pojo.WorkFormComments
 * @Author Zhangzhi
 * @Version 1.0
 * @Date 2009-7-28 上午10:09:09
 */
@Entity
@Table(name = "V_SERVICES_WORKFORM_COMMENT")
public class WorkFormComment {
	private Integer commentsId;
	private String comment;
	private String commentUserName;
	private Integer commentUserId;
	private Date commentTime;
	private WorkForm workForm;
	
	@Id  
	@SequenceGenerator(name = "WORKITEM_COMMENT_ID_SEQ_GENERATOR", sequenceName = "SEQ_SERVICES_WORKFORM_COMMENT", initialValue = 1, allocationSize = 1)   
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WORKITEM_COMMENT_ID_SEQ_GENERATOR")   
	@Column(name="COMMENT_ID")
	public Integer getCommentsId() {
		return commentsId;
	}
	public void setCommentsId(Integer commentsId) {
		this.commentsId = commentsId;
	}
	
	@Column(name="REMARK")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Column(name="COMMENT_USER_NAME")
	public String getCommentUserName() {
		return commentUserName;
	}
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}
	@Column(name="COMMENT_USER_ID")
	public Integer getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(Integer commentUserId) {
		this.commentUserId = commentUserId;
	}
	
	@Column(name="COMMENT_TIME")
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.LAZY )
    @JoinColumn(name="WORK_ID")
	public WorkForm getWorkForm() {
		return workForm;
	}
	public void setWorkForm(WorkForm workForm) {
		this.workForm = workForm;
	}
	
}
