package com.grgbanking.core.controller.equipment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.core.controller.BaseController;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.service.equipment.CustomerService;
import com.grgbanking.css.bean.equipment.EquipmentContact;
import com.grgbanking.css.bean.equipment.EquipmentInfo;
import com.grgbanking.css.dao.EquipmentDAO;
import com.grgbanking.css.service.EquipmentContactService;

/**
 * 
 * 类描述：客户联系人相关操作类 Author: dlcai Date: 2014-5-12 下午5:19:38
 */
@Controller
public class CustomerController extends BaseController {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	CustomerService customerService;

	@Resource(name = "equipmentContactService")
	private EquipmentContactService equipmentContactService;

	@Autowired
	private EquipmentDAO<EquipmentInfo, Integer> equipmentDAO;

	/**
	 * 添加或修改客户联系人
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/cp/equipment/addCustomer")
	public String createOrupdateCustomer(HttpServletRequest request) {
		String result = null;// 返回结果
		JSONObject param = new JSONObject();
		WxUser wxUser = LoginUser.getSessionUser();
		String serialNumber = request.getParameter("serialNumber");
		String equipmentContactId = request.getParameter("equipmentContactId");
		String equipmentId = request.getParameter("equipmentId");// 设备ID
		String contactName = request.getParameter("contactName");// 联系人名称
		String mobliePhone = request.getParameter("mobliePhone");// 移动电话
		String telephone = request.getParameter("telephone");// 固定电话号码
		String positionVal = request.getParameter("positionVal");// 岗位职级
		String sex = request.getParameter("sex");
		String isManager = request.getParameter("isManager");
		String isRefuseSms = request.getParameter("isRefuseSms");// 是否拒收短信
		String remark = request.getParameter("remark");// 备注
		String type = request.getParameter("type");

		if ("add".equals(type)) {// 1为添加新客户联系人
			try {
				EquipmentContact equipmentContact = new EquipmentContact();
				EquipmentInfo equipment = equipmentDAO.get(Integer
						.valueOf(equipmentId));
				equipmentContact.setEquipment(equipment);
				equipmentContact.setContactName(contactName);
				equipmentContact.setMobliePhone(mobliePhone);
				equipmentContact.setTelephone(telephone);
				equipmentContact.setPositionVal(positionVal);
				equipmentContact.setSex(sex);
				equipmentContact.setIsManager(isManager);
				equipmentContact.setIsRefuseSms(isRefuseSms);
				equipmentContact.setRemark(remark);
				equipmentContact.setCreateUserId(Integer.valueOf(wxUser.getId()
						.toString()));
				equipmentContact.setCreateDate(new Date());
				equipmentContact.setDeleted("N");
				result = customerService.doCreateCustomer(
						wxUser.getLoginName(), equipmentContact);
			} catch (Exception e) {
				param.element("status", "0");
				param.element("errMsg", "添加客户联系人失败");
				result = param.toString();
			}
		} else if ("update".equals(type)) {// 2修改客户联系人
			try {
				EquipmentContact equipmentContact = equipmentContactService
						.getEquipmentContactById(Integer
								.valueOf(equipmentContactId));
				if (null != equipmentContact) {
					equipmentContact.setContactName(contactName);
					equipmentContact.setMobliePhone(mobliePhone);
					equipmentContact.setTelephone(telephone);
					equipmentContact.setPositionVal(positionVal);
					equipmentContact.setSex(sex);
					equipmentContact.setIsManager(isManager);
					equipmentContact.setIsRefuseSms(isRefuseSms);
					equipmentContact.setRemark(remark);
					equipmentContact.setCreateUserId(Integer.valueOf(wxUser
							.getId().toString()));
					equipmentContact.setCreateDate(new Date());
				}
				result = customerService.updateByPrimaryKey(
						wxUser.getLoginName(), equipmentContact);
			} catch (Exception e) {
				param.element("status", "0");
				param.element("errMsg", "修改客户联系人失败");
				result = param.toString();
			}
		} else {
			param.element("status", "0");
			param.element("errMsg", "网络异常，请关闭重新打开！");
			result = param.toString();
		}
		request.setAttribute("serialNumber", serialNumber);
		return result;
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(value = "/cp/equipment/deleteCustomer")
	public String deleteCustomer(HttpServletRequest request) {
		WxUser wxUser = LoginUser.getSessionUser();
		String equipmentContactId = request.getParameter("equipmentContactId");
		String result = customerService.doDeleteCustomer(wxUser.getLoginName(),
				equipmentContactId);
		return result;
	}

	/**
	 * 选择根据设备ID查询客户联系人
	 * 
	 */
	@RequestMapping(value = "/cp/equipment/getCustomer")
	public String getCustomerInfo(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		String equipmentId = request.getParameter("equipmentId");// 设备ID
		String serialNumber = request.getParameter("serialNumber");
		try {
			if (equipmentId != null && !equipmentId.equals("")) {
				List<EquipmentContact> equipmentContactInfo = equipmentContactService
						.listById(Integer.valueOf(equipmentId));
				ArrayList<JSONObject> contactResult = new ArrayList<JSONObject>();
				for (EquipmentContact contact : equipmentContactInfo) {
					JSONObject son = new JSONObject();
					if (contact != null) {
						if (StringUtils.isNotEmpty(String.valueOf(contact
								.getEquipmentContactId()))
								&& StringUtils.isNotBlank(String
										.valueOf(contact
												.getEquipmentContactId()))) {
							son.element("equipmentContactId", String
									.valueOf(contact.getEquipmentContactId()));
						} else {
							son.element("equipmentContactId", "");
						}
						if (null != contact.getEquipment()) {
							son.element("equipmentId", contact.getEquipment()
									.getEquipmentId());
						} else {
							son.element("equipmentId", "");
						}
						if (StringUtils.isNotBlank(contact.getContactName())) {
							son.element("contactName", contact.getContactName());
						} else {
							son.element("contactName", "");
						}
						if (StringUtils.isNotBlank(contact.getMobliePhone())) {
							son.element("mobliePhone", contact.getMobliePhone());
						} else {
							son.element("mobliePhone", "");
						}
						if (StringUtils.isNotBlank(contact.getTelephone())) {
							son.element("telephone", contact.getTelephone());
						} else {
							son.element("telephone", "");
						}
						if (StringUtils.isNotBlank(contact.getIsManager())) {
							if (contact.getIsManager().equals("Y")) {
								son.element("isManager", "是");
							} else {
								son.element("isManager", "否");
							}
						} else {
							son.element("isManager", "");
						}
						if (StringUtils.isNotBlank(contact.getIsRefuseSms())) {
							if (contact.getIsRefuseSms().equals("Y")) {
								son.element("isRefuseSms", "是");
							} else {
								son.element("isRefuseSms", "否");
							}
						} else {
							son.element("isRefuseSms", "");
						}
						if (StringUtils.isNotBlank(contact.getSex())) {
							if ("1".equals(contact.getSex())) {
								son.element("sex", "男");
							} else {
								son.element("sex", "女");
							}
						} else {
							son.element("sex", "");
						}
						if (StringUtils.isNotBlank(contact.getPositionVal())) {
							son.element("positionVal", contact.getPositionVal());
						} else {
							son.element("positionVal", "");
						}
						if (StringUtils.isNotBlank(contact.getRemark())) {
							son.element("remark", contact.getRemark());
						} else {
							son.element("remark", "");
						}
						if (StringUtils.isNotBlank(String.valueOf(contact
								.getCreateUserId()))) {
							son.element("createUserId",
									contact.getCreateUserId());
						} else {
							son.element("createUserId", "");
						}
						contactResult.add(son);
					}
				}
				jsonObject.element("status", "1");
				jsonObject.element("contactInfo", contactResult.toString());
			} else {
				jsonObject.element("status", "0");
				jsonObject.element("errMsg", "无相关联系人");
			}
		} catch (Exception e) {
			jsonObject.element("status", "0");
			jsonObject.element("errMsg", "查询出现异常");
			e.printStackTrace();
		}
		request.setAttribute("equipmentId", equipmentId);
		request.setAttribute("serialNumber", serialNumber);
		request.setAttribute("result", jsonObject);
		return "/equipment/customer_list";
	}

	/**
	 * 通过ID找客户联系人
	 * 
	 */
	@RequestMapping(value = "/cp/equipment/customerinfo")
	public String customerinfo(HttpServletRequest request) {
		String equipmentId = request.getParameter("equipmentId");
		String serialNumber = request.getParameter("serialNumber");
		String equipmentContactId = request.getParameter("equipmentContactId");
		String type = request.getParameter("type");
		if (StringUtils.isNotBlank(equipmentContactId)) {
			EquipmentContact contact = equipmentContactService
					.getEquipmentContactById(Integer
							.valueOf(equipmentContactId));
			if (null != contact) {
				equipmentId = String.valueOf(contact.getEquipment()
						.getEquipmentId());
			}
			request.setAttribute("contact", contact);
		}
		request.setAttribute("equipmentId", equipmentId);
		request.setAttribute("serialNumber", serialNumber);
		request.setAttribute("type", type);
		return "/equipment/customer_info";
	}

}
