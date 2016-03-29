package com.grgbanking.css.bean.work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.grgbanking.css.bean.Department;
import com.grgbanking.css.bean.equipment.EquipmentInfo;
import com.grgbanking.css.common.CssBaseEntity;
import com.grgbanking.css.util.CommonConstants;

/**
 *工单信息表
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: Administrator
 * Date: 2010-7-29 下午11:22:48
 */
@Entity
@Table(name = "V_SERVICES_WORKFORM")
public class WorkForm extends CssBaseEntity {
	
	/**
	 * 工单ID
	 */
	private Integer workFormId;
	/**
	 * PO单号
	 */
	private String poNumber;
	/**
	 * 设备ID
	 */
	private EquipmentInfo equipment;
	
	/**
	 * 完成工单的部门
	 */
	private Department department;
	
	/**
	 * 预约上门时间
	 */
	private Date appointmentDate;
	/**
	 * 到达现场时间
	 */
	private Date arriveTime;
	/**
	 * 工作开始时间
	 */
	private Date workStartDate;
	/**
	 * 工作完成时间
	 */
	private Date workFinishDate;
	/**
	 * 报修时间
	 */
	private Date reportTime;
	/**
	 * 报修电话
	 */
	private String reportTelephone;
	/**
	 * 总工作耗时
	 */
	private Integer costTime;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 工程师名称
	 */
	private String engineerName;
	/**
	 * 工程师ID
	 */
	private Integer engineerId;
	/**
	 * 跟随人员（用逗号隔开）
	 */
	private String followUserName;//同行人员姓名
	private String followUserId;//同行人员 ID
	
	private Date fillDate;//录单时间
	
	private Date lastAuditDate;//最后审核时间
	
	private String submitType;
	
	
	//现场任务
	private List<WorkTask> workTaskList;
	
	private String hasRevisit=CommonConstants.FLAG_N;  //是否回访
	private Date lastRevisitTime;  //回访工单的最后时间
	private List<WorkFormComment> commentList=new ArrayList<WorkFormComment>();//审批意见
	
	private String taskLevel;//任务等级
	
	//by luow 2012-10-18 新增客户满意度字段
	private String satisfiedType ;// 客户满意度
	
	private String satisfiedTypeSms ;// 客户短信满意度 by wwb 2014-06-24 
	//by luow 2013-02-25 新增扫描次数字段
	private Integer scanTimes;
	
	private String createType;
	
	private String sptRemark;//备件信息
	
	private String dispatchAdvice;// 调度建议
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERATOR_SEQ_SERVICES_WORKFORM")
	@SequenceGenerator(name = "GENERATOR_SEQ_SERVICES_WORKFORM", sequenceName = "SEQ_SERVICES_WORKFORM", initialValue = 1, allocationSize = 1)
	@Column(name ="WORKFORM_ID")
	public Integer getWorkFormId() {
		return workFormId;
	}
	public void setWorkFormId(Integer workFormId) {
		this.workFormId = workFormId;
	}
	@Column(name ="PO_NUMBER")
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "EQUIPMENT_ID")
	public EquipmentInfo getEquipment() {
		return equipment;
	}
	public void setEquipment(EquipmentInfo equipment) {
		this.equipment = equipment;
	}
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT_ID")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Column(name ="APPOINTMENT_DATE")
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	@Column(name ="ARRIVE_TIME")
	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	@Column(name ="WORK_START_DATE")
	public Date getWorkStartDate() {
		return workStartDate;
	}
	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}
	@Column(name ="WORK_FINISH_DATE")
	public Date getWorkFinishDate() {
		return workFinishDate;
	}
	public void setWorkFinishDate(Date workFinishDate) {
		this.workFinishDate = workFinishDate;
	}
	@Column(name ="REPORT_TIME")
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	@Column(name ="REPORT_TELEPHONE")
	public String getReportTelephone() {
		return reportTelephone;
	}
	public void setReportTelephone(String reportTelephone) {
		this.reportTelephone = reportTelephone;
	}
	@Column(name ="COST_TIME")
	public Integer getCostTime() {
		return costTime;
	}
	public void setCostTime(Integer costTime) {
		this.costTime = costTime;
	}
	@Column(name ="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name ="ENGINEER_NAME")
	public String getEngineerName() {
		return engineerName;
	}
	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	@Column(name ="ENGINEER_ID")
	public Integer getEngineerId() {
		return engineerId;
	}
	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}
	@Column(name ="FILL_DATE")
	public Date getFillDate() {
		return fillDate;
	}
	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKFORM_ID")
	public List<WorkTask> getWorkTaskList() {
		return workTaskList;
	}
	public void setWorkTaskList(List<WorkTask> workTaskList) {
		this.workTaskList = workTaskList;
	}
	
	@Column(name = "HAS_REVISIT")
	public String getHasRevisit() {
		return hasRevisit;
	}
	public void setHasRevisit(String hasRevisit) {
		this.hasRevisit = hasRevisit;
	}
	@Column(name = "LAST_REVISIT_TIME")
	public Date getLastRevisitTime() {
		return lastRevisitTime;
	}
	public void setLastRevisitTime(Date lastRevisitTime) {
		this.lastRevisitTime = lastRevisitTime;
	}
	@Column(name = "FOLLOW_USERNAME")
	public String getFollowUserName() {
		return followUserName;
	}
	public void setFollowUserName(String followUserName) {
		this.followUserName = followUserName;
	}
	@Column(name = "FOLLOW_USERID")
	public String getFollowUserId() {
		return followUserId;
	}
	public void setFollowUserId(String followUserId) {
		this.followUserId = followUserId;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.ALL,org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OrderBy("commentTime asc")
	@JoinColumn(name = "WORK_ID")
	public List<WorkFormComment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<WorkFormComment> commentList) {
		this.commentList = commentList;
	}
	@Column(name = "LAST_AUDIT_DATE")
	public Date getLastAuditDate() {
		return lastAuditDate;
	}
	public void setLastAuditDate(Date lastAuditDate) {
		this.lastAuditDate = lastAuditDate;
	}
	@Column(name = "TASK_LEVEL")
	public String getTaskLevel() {
		return taskLevel;
	}
	public void setTaskLevel(String taskLevel) {
		this.taskLevel = taskLevel;
	}
	@Column(name = "SATISFIED_TYPE")
	public String getSatisfiedType() {
		return satisfiedType;
	}
	public void setSatisfiedType(String satisfiedType) {
		this.satisfiedType = satisfiedType;
	}
	@Column(name = "SCAN_TIMES")
	public Integer getScanTimes() {
		return scanTimes;
	}
	public void setScanTimes(Integer scanTimes) {
		this.scanTimes = scanTimes;
	}
	@Column(name = "CREATE_TYPE")
	public String getCreateType() {
		return createType;
	}
	public void setCreateType(String createType) {
		this.createType = createType;
	}
	@Column(name = "SPT_REMARK")
	public String getSptRemark() {
		return sptRemark;
	}
	public void setSptRemark(String sptRemark) {
		this.sptRemark = sptRemark;
	}
	@Column(name = "DISPATCH_ADVICE")
	public String getDispatchAdvice() {
		return dispatchAdvice;
	}
	public void setDispatchAdvice(String dispatchAdvice) {
		this.dispatchAdvice = dispatchAdvice;
	}
	@Column(name = "SATISFIED_TYPE_SMS")
	public String getSatisfiedTypeSms() {
		return satisfiedTypeSms;
	}
	public void setSatisfiedTypeSms(String satisfiedTypeSms) {
		this.satisfiedTypeSms = satisfiedTypeSms;
	}
	@Column(name = "SUBMIT_TYPE")
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	 
}
