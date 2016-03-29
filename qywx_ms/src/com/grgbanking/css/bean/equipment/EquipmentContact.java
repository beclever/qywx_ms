package com.grgbanking.css.bean.equipment;

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

import com.grgbanking.css.util.CommonConstants;

/**
 * 设备联系人配置表
 * 
 * @author wwbang1
 * @d2014-5-4
 */
@Entity
@Table(name = "V_EMS_EQUIPMENT_CONTACT")
public class EquipmentContact implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	private Integer equipmentContactId;// 主键
	private EquipmentInfo equipment; // 对应设备ID
	private String contactName;// 联系人名称
	private String mobliePhone;// 移动电话
	private String telephone;// 固定电话号码
	private String positionVal;// 岗位职级
	private String isManager;// 是否当前管理员
	private String isRefuseSms;// 是否拒收短信
	private String deleted = CommonConstants.FLAG_N;// 删除标记
	private Integer createUserId;// 建立用户ID
	private Date createDate;// 建立时间
	private String remark;// 备注
	private String sex;//性别

	@Id
	@SequenceGenerator(name = "SEQ_EQUIPMENT_CONTACT_GENERATOR", sequenceName = "SEQ_EQUIPMENT_CONTACT", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EQUIPMENT_CONTACT_GENERATOR")
	@Column(name = "EQUIPMENT_CONTACT_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Integer getEquipmentContactId() {
		return equipmentContactId;
	}

	public void setEquipmentContactId(Integer equipmentContactId) {
		this.equipmentContactId = equipmentContactId;
	}

	@Column(name = "CONTACT_NAME")
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Column(name = "MOBLIE_PHONE")
	public String getMobliePhone() {
		return mobliePhone;
	}

	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "POSITION_VAL")
	public String getPositionVal() {
		return positionVal;
	}

	public void setPositionVal(String positionVal) {
		this.positionVal = positionVal;
	}

	@Column(name = "IS_MANAGER")
	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

	@Column(name = "IS_REFUSE_SMS")
	public String getIsRefuseSms() {
		return isRefuseSms;
	}

	public void setIsRefuseSms(String isRefuseSms) {
		this.isRefuseSms = isRefuseSms;
	}

	@Column(name = "DELETED")
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_USER_ID")
	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "EQUIPMENT_ID")
	public EquipmentInfo getEquipment() {
		return equipment;
	}

	public void setEquipment(EquipmentInfo equipment) {
		this.equipment = equipment;
	}

	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}