package com.grgbanking.css.bean.vequipment;

import java.util.Date;

import com.grgbanking.css.util.CommonConstants;

/**
 * 设备联系人配置表
 * @author yzhua
 *
 */
public class VEquipmentContactBean {
	
	private Integer equipmentContactId;// 主键
	private String[] equipmentContactIds;// 主键
	private Integer equipmentId; // 对应设备ID
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
	private String serialNumber;// 序列号
	private Integer departmentId;// 服务站ID
	private String departmentIds;
	private String equipmentType;//设备类型
	private String equipmentModel;// 设备型号
	private String equipmentStatus;// 设备状态
	private String saleProperty;// 销售属性
	private String warrantyStatus;// 保修类型（默认为免保，如果签了维保合同，则与维保合同关联）
	private String sex;
	public Integer getEquipmentContactId() {
		return equipmentContactId;
	}
	public void setEquipmentContactId(Integer equipmentContactId) {
		this.equipmentContactId = equipmentContactId;
	}
	public String[] getEquipmentContactIds() {
		return equipmentContactIds;
	}
	public void setEquipmentContactIds(String[] equipmentContactIds) {
		this.equipmentContactIds = equipmentContactIds;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getMobliePhone() {
		return mobliePhone;
	}
	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPositionVal() {
		return positionVal;
	}
	public void setPositionVal(String positionVal) {
		this.positionVal = positionVal;
	}
	public String getIsManager() {
		return isManager;
	}
	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}
	public String getIsRefuseSms() {
		return isRefuseSms;
	}
	public void setIsRefuseSms(String isRefuseSms) {
		this.isRefuseSms = isRefuseSms;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentIds() {
		return departmentIds;
	}
	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getEquipmentModel() {
		return equipmentModel;
	}
	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	public String getEquipmentStatus() {
		return equipmentStatus;
	}
	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	public String getSaleProperty() {
		return saleProperty;
	}
	public void setSaleProperty(String saleProperty) {
		this.saleProperty = saleProperty;
	}
	public String getWarrantyStatus() {
		return warrantyStatus;
	}
	public void setWarrantyStatus(String warrantyStatus) {
		this.warrantyStatus = warrantyStatus;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	

}
