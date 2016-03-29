package com.grgbanking.css.bean.vequipment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.grgbanking.css.util.CommonConstants;

/**
 * 设备联系人配置表
 * @author yzhua
 *
 */
@Entity
@Table(name="v_ems_equipment_contact")
public class VEquipmentContact {
	
	private Integer equipmentContactId;// 主键
	private Integer vequipmentId; // 对应设备ID
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
	@Column(name = "EQUIPMENT_CONTACT_ID")
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

	@Column(name = "EQUIPMENT_ID")
	public Integer getVequipmentId() {
		return vequipmentId;
	}

	public void setVequipmentId(Integer vequipmentId) {
		this.vequipmentId = vequipmentId;
	}
	
	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


}
