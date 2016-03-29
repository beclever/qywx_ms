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
@Entity
@Table(name = "V_WORKFORM_CONTROL")
public class WorkFormControl {
	private Integer Id;
	private Integer workFormId;//工单ID
	
	private String longitude;//经度
	
	private String latitude;//纬度
	
	private String workFormAddress;//地址
	
	private Date dateTime;//时间
	
	private Integer timeType;//时间类型（1.出发,2.达到现场时间,3.开始时间,4.完成时间）
	/* 主键ID */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WORKFORM_CONTROL")
	@SequenceGenerator(name = "SEQ_WORKFORM_CONTROL", sequenceName = "SEQ_WORKFORM_CONTROL", initialValue = 1, allocationSize = 1)
	@Column(name = "ID")
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
	@Column(name = "WORKFORM_ID")
	public Integer getWorkFormId() {
		return workFormId;
	}

	public void setWorkFormId(Integer workFormId) {
		this.workFormId = workFormId;
	}
	@Column(name = "LON")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name = "LAT")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name = "WORKFORM_ADDRESS")
	public String getWorkFormAddress() {
		return workFormAddress;
	}

	public void setWorkFormAddress(String workFormAddress) {
		this.workFormAddress = workFormAddress;
	}
	@Column(name = "DATE_TIME")
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	@Column(name = "TIME_TYPE")
	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

}
