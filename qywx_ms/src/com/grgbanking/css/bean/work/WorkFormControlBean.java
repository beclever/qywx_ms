package com.grgbanking.css.bean.work;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 查询工单任务轨迹
 * 
 * @ClassName com.grgbanking.entity.OffsetValue
 * @Author Administrator
 * @Version 1.0
 * @Date 2009-12-3 上午12:09:18
 */

public class WorkFormControlBean {
	private Integer Id;
	private Integer workFormId;//工单ID
	
	private String longitude;//经度
	
	private String latitude;//纬度
	
	private String workFormAddress;//地址
	
	private Date dateTime;//时间
	
	private Integer timeType;//时间类型（1.出发,2.达到现场时间,3.开始时间,4.完成时间）
	
	private String brankName;//网点
	
	private String bankName;//银行
	
	private String engineerName;//工程师
	
	

	public String getBrankName() {
		return brankName;
	}

	public void setBrankName(String brankName) {
		this.brankName = brankName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getWorkFormId() {
		return workFormId;
	}

	public void setWorkFormId(Integer workFormId) {
		this.workFormId = workFormId;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getWorkFormAddress() {
		return workFormAddress;
	}

	public void setWorkFormAddress(String workFormAddress) {
		this.workFormAddress = workFormAddress;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	
	
}
