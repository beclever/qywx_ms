package com.grgbanking.css.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grgbanking.css.bean.equipment.EquipmentContact;
import com.grgbanking.css.bean.equipment.EquipmentContactBean;
import com.grgbanking.css.dao.EquipmentContactDAO;
import com.grgbanking.css.load.DataDictionaryLoad;
import com.grgbanking.css.service.EquipmentContactService;
import com.grgbanking.css.util.DataDictionaryConstants;

@Service("equipmentContactService")
public class EquipmentContactServiceImpl implements EquipmentContactService {

	@Autowired
	private EquipmentContactDAO<EquipmentContact, Integer> equipmentContactDAO;

	/*
	 * @Autowired private UserDAO<User, Integer> userDAO;
	 * 
	 * @Autowired private EquipmentDAO<EquipmentInfo, Integer> equipmentDAO;
	 */

	/*
	 * public String doSave(Integer equipmentId, String contactName, String
	 * mobliePhone, String createUserCode) { try { Boolean flag =
	 * SMSUtils.isMobileNO(mobliePhone); if (flag) { Integer cnt =
	 * validateMobliePhone(equipmentId, mobliePhone); if (cnt == 0) { User user
	 * = userDAO.getUserByUserCode(createUserCode); EquipmentContactBean bean =
	 * new EquipmentContactBean(); bean.setEquipmentId(equipmentId);
	 * bean.setContactName(contactName); bean.setMobliePhone(mobliePhone);
	 * bean.setCreateDate(new Date());
	 * bean.setIsManager(CommonConstants.FLAG_Y);
	 * bean.setIsRefuseSms(CommonConstants.FLAG_N);
	 * bean.setPositionVal(CommonConstants.EQUIPMENT_CONTACT_POSITION_1); if
	 * (null != user) bean.setCreateUserId(user.getUserId());
	 * doSaveOrUpdate(bean); return
	 * "{\"status\":\"success\",\"msg\":\"新建成功!\"}"; } } else { Integer cnt =
	 * validateTelephone(equipmentId, mobliePhone); if (cnt == 0 &&
	 * !mobliePhone.contains("-")) { // 对于区号没有有带"—"的电话号码或者移动电话： 对后面7位数字是否相同进行校验
	 * cnt = equipmentContactDAO.validateLike7Telephone(equipmentId,
	 * mobliePhone); } if (cnt == 0) { User user =
	 * userDAO.getUserByUserCode(createUserCode); EquipmentContactBean bean =
	 * new EquipmentContactBean(); bean.setEquipmentId(equipmentId);
	 * bean.setContactName(contactName); bean.setTelephone(mobliePhone);
	 * bean.setCreateDate(new Date());
	 * bean.setIsManager(CommonConstants.FLAG_Y);
	 * bean.setIsRefuseSms(CommonConstants.FLAG_N);
	 * bean.setPositionVal(CommonConstants.EQUIPMENT_CONTACT_POSITION_1); if
	 * (null != user) bean.setCreateUserId(user.getUserId());
	 * doSaveOrUpdate(bean); return
	 * "{\"status\":\"success\",\"msg\":\"新建成功!\"}"; } } } catch (Exception e) {
	 * log.error(ExceptionUtils.getFullStackTrace(e)); return
	 * "{\"status\":\"failure\",\"msg\":\"" + e.getMessage() + "\"}"; } return
	 * "{\"status\":\"success\",\"msg\":\"设备联系电话已存在!\"}"; }
	 */

	/*
	 * public void doSaveOrUpdate(EquipmentContactBean bean) { EquipmentInfo
	 * equipmentInfo = null; //新增补充EquipmentId 等其它属性 if (null ==
	 * bean.getEquipmentId()) { equipmentInfo =
	 * equipmentDAO.getEquipmentBySerialNumber(bean.getSerialNumber());
	 * if(null==equipmentInfo){ throw new
	 * ServiceException("【"+bean.getSerialNumber()+"】找不到对应序列号的相关设备！"); }
	 * 
	 * bean.setEquipmentId(equipmentInfo.getEquipmentId());
	 * bean.setCreateDate(new Date()); if
	 * (StringUtils.isBlank(bean.getIsRefuseSms())) bean.setIsRefuseSms("N");
	 * UserBean user = UserContext.getUser(); if (null != user) {
	 * bean.setCreateUserId(user.getUserId()); }
	 * 
	 * //校验设备号码的唯一 Integer count = validateMobliePhone(bean.getEquipmentId(),
	 * bean.getMobliePhone()); if (count > 0) { throw new
	 * ServiceException("同一个设备，移动电话号码【"+bean.getMobliePhone()+"】重复，请修正！"); }
	 * validateTelephone(bean.getEquipmentId(), bean.getTelephone()); if (count
	 * > 0) { throw new
	 * ServiceException("同一个设备，固定电话号码【"+bean.getTelephone()+"】重复，请修正！"); }
	 * }else{ equipmentInfo= equipmentDAO.get(bean.getEquipmentId());
	 * 
	 * Integer count = validateMobliePhone(bean.getEquipmentId(),
	 * bean.getMobliePhone()); if (count > 1) { throw new
	 * ServiceException("同一个设备，移动电话号码【"+bean.getMobliePhone()+"】重复，请修正！"); }
	 * validateTelephone(bean.getEquipmentId(), bean.getTelephone()); if (count
	 * > 1) { throw new
	 * ServiceException("同一个设备，固定电话号码【"+bean.getTelephone()+"】重复，请修正！"); } }
	 * 
	 * StringBuffer log = new StringBuffer(); if
	 * (StringUtils.isBlank(bean.getIsManager())) { bean.setIsManager("N"); } if
	 * (null == bean.getEquipmentContactId()) { EquipmentContact
	 * equipmentContact = new EquipmentContact(); BeanUtils.copyProperties(bean,
	 * equipmentContact); log.append("新设备客户联系人【"); //
	 * log.append(JSONObject.fromObject(bean)); if
	 * (StringUtils.isNotBlank(bean.getContactName())) log.append("姓名:" +
	 * bean.getContactName() + ","); if
	 * (StringUtils.isNotBlank(bean.getMobliePhone())) log.append("手机:" +
	 * bean.getMobliePhone() + ","); if
	 * (StringUtils.isNotBlank(bean.getTelephone())) log.append("固话:" +
	 * bean.getTelephone() + ","); if
	 * (StringUtils.isNotBlank(bean.getPositionVal())) log.append("岗位:" +
	 * bean.getPositionVal() + ","); if
	 * (StringUtils.isNotBlank(bean.getIsManager())) log.append("当前管理员:" +
	 * bean.getIsManager() + ","); if
	 * (StringUtils.isNotBlank(bean.getIsRefuseSms())) log.append("拒绝短信:" +
	 * bean.getIsRefuseSms() + ","); if
	 * (StringUtils.isNotBlank(bean.getRemark())) log.append("备注:" +
	 * bean.getRemark() + ","); log.append("】");
	 * equipmentContact.setEquipment(equipmentInfo);
	 * equipmentContactDAO.save(equipmentContact);
	 * equipmentContactDAO.saveCtrlLog(log, bean.getEquipmentId()); } else {
	 * EquipmentContact contact =
	 * equipmentContactDAO.get(bean.getEquipmentContactId()); // String chang =
	 * CommonUtils.compare(contact, bean, null, null, // null);
	 *//**
	 * 修改日志
	 */
	/*
	 * if(CommonConstants.FLAG_N.equals(bean.getIsManager())){
	 * List<EquipmentContact> list =
	 * equipmentContactDAO.getEqtManager(contact.getEquipment
	 * ().getEquipmentId()); if(list.size()==1 &&
	 * bean.getEquipmentContactId().intValue
	 * ()==list.get(0).getEquipmentContactId().intValue()){ throw new
	 * ServiceException("操作失败，至少保留一位设备管理员！"); } } StringBuffer chang = new
	 * StringBuffer(); if (null != contact.getContactName() &&
	 * !contact.getContactName().equals(bean.getContactName()))
	 * chang.append("姓名：" + contact.getContactName() + "->" +
	 * bean.getContactName() + ","); if (null != contact.getMobliePhone() &&
	 * !contact.getMobliePhone().equals(bean.getMobliePhone()))
	 * chang.append("手机:" + contact.getMobliePhone() + "->" +
	 * bean.getMobliePhone() + ","); if (null != contact.getTelephone() &&
	 * !contact.getTelephone().equals(bean.getTelephone())) chang.append("固话：" +
	 * contact.getTelephone() + "->" + bean.getTelephone() + ","); if (null !=
	 * contact.getPositionVal() &&
	 * !contact.getPositionVal().equals(bean.getPositionVal()))
	 * chang.append("岗位：" + contact.getPositionVal() + "->" +
	 * bean.getPositionVal() + ","); if (null != contact.getIsManager() &&
	 * !contact.getIsManager().equals(bean.getIsManager()))
	 * chang.append("是否管理员：" + contact.getIsManager() + "->" +
	 * bean.getIsManager() + ","); if (null != contact.getIsRefuseSms() &&
	 * !contact.getIsRefuseSms().equals(bean.getIsRefuseSms()))
	 * chang.append("拒收：" + contact.getIsRefuseSms() + "->" +
	 * bean.getIsRefuseSms() + ","); if (null != contact.getRemark() &&
	 * !contact.getRemark().equals(bean.getRemark())) chang.append("备注：" +
	 * contact.getRemark() + "->" + bean.getRemark() + ",");
	 * 
	 * if (chang.length() > 0) { log.append("修改设备客户联系人姓名：" +
	 * contact.getContactName() + "【" + chang + "】");
	 * equipmentContactDAO.saveCtrlLog(log, bean.getEquipmentId()); }
	 * 
	 * BeanUtils.copyProperties(bean, contact);
	 * equipmentContactDAO.update(contact); }
	 * 
	 * }
	 */

	public List<JSONObject> list(Integer equipmentId) {
		List<EquipmentContact> result = equipmentContactDAO.list(equipmentId);
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject json = null;
		if (null != result) {
			EquipmentContact contact = null;
			for (int i = 0; i < result.size(); i++) {
				contact = result.get(i);
				json = new JSONObject();
				json.element("equipmentContactId",
						contact.getEquipmentContactId());
				json.element("contactName", contact.getContactName());
				json.element("mobliePhone", contact.getMobliePhone());
				json.element("telephone", contact.getTelephone());
				json.element("positionVal", contact.getPositionVal());
				json.element("isManager", DataDictionaryLoad.getText(
						DataDictionaryConstants.COMMON_YES_NO,
						contact.getIsManager()));
				json.element("isRefuseSms", DataDictionaryLoad.getText(
						DataDictionaryConstants.COMMON_YES_NO,
						contact.getIsRefuseSms()));
				json.element("remark", contact.getRemark());
				list.add(json);
			}
		}
		return list;
	}

	public List<EquipmentContact> listById(Integer equipmentId) {
		return equipmentContactDAO.list(equipmentId);
	}

	/*
	 * public void doDetele(Integer[] equipmentContactIds) {
	 *//**
	 * 两个验证
	 */
	/*
	 * if (null != equipmentContactIds && equipmentContactIds.length > 0) {
	 * EquipmentContact contact = equipmentContactDAO.get(new
	 * Integer(equipmentContactIds[0])); Integer equipmentId =
	 * contact.getEquipment().getEquipmentId(); List<EquipmentContact> list =
	 * equipmentContactDAO.getEquipmentContact(equipmentId);
	 * List<EquipmentContact> listManager =
	 * equipmentContactDAO.getEqtManager(contact
	 * .getEquipment().getEquipmentId()); boolean falg = false;
	 * 
	 * for (int i = 0; i < listManager.size(); i++) { boolean falgTemp =
	 * false;//可以删除 contact = listManager.get(i); for (int j = 0; j <
	 * equipmentContactIds.length; j++) { if
	 * (contact.getEquipmentContactId().intValue() ==
	 * equipmentContactIds[j].intValue()) { falgTemp = true; continue; } } falg
	 * = falgTemp; if(!falg){ break; } } if (falg) { throw new
	 * ServiceException("操作失败，至少保留一位设备管理员！"); }
	 * 
	 * if (list.size() == 1 || equipmentContactIds.length == list.size()) {
	 * throw new ServiceException("操作失败，设备联系人至少保留一位联系人！"); } }
	 * 
	 * for (int i = 0; i < equipmentContactIds.length; i++) { EquipmentContact
	 * contact = equipmentContactDAO.get(new Integer(equipmentContactIds[i]));
	 * contact.setDeleted(CommonConstants.FLAG_Y);
	 * equipmentContactDAO.update(contact); StringBuffer log = new
	 * StringBuffer("删除设备客户【"
	 * +contact.getContactName()+","+contact.getMobliePhone
	 * ()+","+contact.getTelephone()+"】"); equipmentContactDAO.saveCtrlLog(log,
	 * contact.getEquipment().getEquipmentId()); } }
	 */

	/*
	 * public void doUpdateContactIsManager(Integer equipmentId, String
	 * mobliePhone) { equipmentContactDAO.doUpdateContactIsManager(equipmentId,
	 * mobliePhone); }
	 */

	public EquipmentContactBean getById(Integer equipmentContactId) {
		EquipmentContactBean bean = new EquipmentContactBean();
		EquipmentContact contact = equipmentContactDAO.get(equipmentContactId);
		BeanUtils.copyProperties(contact, bean);
		bean.setEquipmentId(contact.getEquipment().getEquipmentId());
		return bean;
	}

	/*
	 * public boolean validate(EquipmentContactBean bean) { Integer i =
	 * equipmentContactDAO.validate(bean); if (i == 0) { return true; } return
	 * false; }
	 * 
	 * public List<EquipmentContactBean> getEqtManager(Integer equipmentId) {
	 * List<EquipmentContact> list =
	 * equipmentContactDAO.getEqtManager(equipmentId); if (null != list &&
	 * list.size() > 0) { EquipmentContactBean bean = null;
	 * List<EquipmentContactBean> resultList = new
	 * ArrayList<EquipmentContactBean>(); EquipmentContact contact = null; for
	 * (int i = 0; i < list.size(); i++) { bean = new EquipmentContactBean();
	 * contact = list.get(i); BeanUtils.copyProperties(contact, bean);
	 * resultList.add(bean); } return resultList; } else { return null; } }
	 * 
	 * public List<JSONObject> manager(Page page, EquipmentContactBean bean) {
	 * List<EquipmentContact> list = equipmentContactDAO.manager(page, bean);
	 * if(list.size()>5000){ throw new
	 * ServiceException("禁止单次导出超过5000的数据记录，请按服务站进行过滤导出！"); }
	 * ArrayList<JSONObject> result = new ArrayList<JSONObject>();
	 * EquipmentContact contact = null; JSONObject json = null; for (int i = 0;
	 * i < list.size(); i++) { json = new JSONObject(); contact = list.get(i);
	 * json.element("equipmentContactId", contact.getEquipmentContactId()); if
	 * (null != contact.getEquipment().getDepartment()) {
	 * json.element("departmentName",
	 * contact.getEquipment().getDepartment().getDepartmentName());
	 * json.element("pdepartmentName",
	 * contact.getEquipment().getDepartment().getParent().getDepartmentName());
	 * json.element("ppdepartmentName",
	 * contact.getEquipment().getDepartment().getParent
	 * ().getParent().getDepartmentName()); } json.element("serialNumber",
	 * contact.getEquipment().getSerialNumber()); json.element("contactName",
	 * contact.getContactName()); json.element("mobliePhone",
	 * contact.getMobliePhone()); json.element("telephone",
	 * contact.getTelephone()); json.element("isManager",
	 * DataDictionaryLoad.getText(DataDictionaryConstants.COMMON_YES_NO,
	 * contact.getIsManager())); json.element("isRefuseSms",
	 * DataDictionaryLoad.getText(DataDictionaryConstants.COMMON_YES_NO,
	 * contact.getIsRefuseSms())); json.element("positionVal",
	 * contact.getPositionVal()); json.element("sex",
	 * "0".equals(contact.getSex())?"女":"男"); json.element("remark",
	 * contact.getRemark()); result.add(json); } return result; }
	 * 
	 * @Override public void doUpdateContactIsRefuseSms(String[]
	 * equipmentContactIds, String isRefuseSms) { for (int i = 0; i <
	 * equipmentContactIds.length; i++) { EquipmentContact contact =
	 * equipmentContactDAO.get(new Integer(equipmentContactIds[i]));
	 * contact.setIsRefuseSms(isRefuseSms); contact.setCreateDate(new Date());
	 * equipmentContactDAO.update(contact); StringBuffer log = new
	 * StringBuffer("修改设备客户是否接收短信【"+contact.getContactName()+"】："+isRefuseSms);
	 * equipmentContactDAO.saveCtrlLog(log,
	 * contact.getEquipment().getEquipmentId()); } }
	 * 
	 * 
	 * public void doImport(Sheet sheet){ int rowCount = sheet.getRows();
	 * if(rowCount < 1){ throw new ServiceException("操作失败，excel中没有数据 "); }
	 * EquipmentContactBean bean = null; List<EquipmentContactBean> beans = new
	 * ArrayList<EquipmentContactBean>();
	 * 
	 * Map<String, String> signMap = new HashMap<String, String>(); for (int i =
	 * 1;i < rowCount; i++) { String equipmentContactId = sheet.getCell(0,
	 * i).getContents(); String serialNumber = sheet.getCell(4,
	 * i).getContents(); String contactName = sheet.getCell(5, i).getContents();
	 * String mobliePhone = sheet.getCell(6, i).getContents(); String telephone
	 * = sheet.getCell(7, i).getContents(); String isManager = sheet.getCell(8,
	 * i).getContents(); String isRefuseSms = sheet.getCell(9, i).getContents();
	 * String positionVal = sheet.getCell(10, i).getContents(); String remark =
	 * sheet.getCell(11, i).getContents();
	 * 
	 * if(StringUtils.isBlank(serialNumber)){ continue; } EquipmentInfo info =
	 * equipmentDAO.getEquipmentBySerialNumber(serialNumber); if(null == info){
	 * throw new ServiceException("操作失败，序列号： "+serialNumber+"找不到对应的设备！"); }
	 * 
	 * if (StringUtils.isBlank(equipmentContactId)) { if
	 * (signMap.containsKey(serialNumber + "_" + mobliePhone)) { throw new
	 * ServiceException("同一设备序列号： " + serialNumber + "，手机号码重复：" + mobliePhone);
	 * } signMap.put(serialNumber + "_" + mobliePhone, mobliePhone); } bean =
	 * new EquipmentContactBean();
	 * bean.setEquipmentContactId(NumberUtils.createInteger
	 * (equipmentContactId)); bean.setSerialNumber(serialNumber.trim());
	 * //bean.setEquipmentId(info.getEquipmentId());
	 * bean.setContactName(contactName); bean.setMobliePhone(mobliePhone);
	 * bean.setTelephone(telephone);
	 * bean.setIsManager(isManager.equals("是")?"Y":"N");
	 * 
	 * bean.setIsRefuseSms(isRefuseSms.equals("是")?"Y":"N");
	 * bean.setPositionVal(positionVal); bean.setRemark(remark);
	 * beans.add(bean); }
	 * 
	 * if(beans.size()>0){ doSaveOrUpdate(bean); }
	 * 
	 * }
	 * 
	 * @Override public boolean isRefuseSms(Integer equipmentId, String
	 * mobliePhone) { // TODO Auto-generated method stub EquipmentContactBean
	 * bean = new EquipmentContactBean(); bean.setEquipmentId(equipmentId);
	 * bean.setIsRefuseSms("N"); bean.setDeleted("N");
	 * bean.setMobliePhone(mobliePhone); List<EquipmentContact> list =
	 * equipmentContactDAO.getByBean(bean); if(list.size()>0){ return true;
	 * }else{ return false; } }
	 */

	public Integer validateEquipmentId(Integer equipmentId) {
		return equipmentContactDAO.validateEquipmentId(equipmentId);
	}

	public Integer validateMobliePhone(Integer equipmentId, String mobliePhone) {
		return equipmentContactDAO
				.validateMobliePhone(equipmentId, mobliePhone);
	}

	public Integer validateTelephone(Integer equipmentId, String telephone) {
		return equipmentContactDAO.validateTelephone(equipmentId, telephone);
	}

	public Integer validateContactName(Integer equipmentId, String contactName) {
		return equipmentContactDAO
				.validateContactName(equipmentId, contactName);
	}

	@Override
	public EquipmentContact getEquipmentContactManager(Integer equipmentId) {
		return equipmentContactDAO.getEquipmentContactManager(equipmentId);
	}

	@Override
	public EquipmentContact getEquipmentContactByMobliePhone(
			Integer equipmentId, String mobliePhone) {
		return equipmentContactDAO.getEquipmentContactByMobliePhone(
				equipmentId, mobliePhone);
	}

	@Override
	public EquipmentContact getEquipmentContactByContactName(
			Integer equipmentId, String contactName) {
		return equipmentContactDAO.getEquipmentContactByContactName(
				equipmentId, contactName);
	}

	@Override
	public EquipmentContact getEquipmentContactById(Integer equipmentContactId) {
		return equipmentContactDAO.get(equipmentContactId);
	}

	@Override
	public Integer validateContactId(Integer equipmentcontactId,
			boolean isManager) {
		return equipmentContactDAO.validateContactId(equipmentcontactId, isManager);
	}

}
