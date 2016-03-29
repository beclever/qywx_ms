package com.grgbanking.core.service.equipment.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.entity.ws.WsResultBean;
import com.grgbanking.core.service.equipment.CustomerService;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.bean.equipment.EquipmentContact;
import com.grgbanking.css.dao.CssUserDAO;
import com.grgbanking.css.service.EquipmentContactService;
import com.grgbanking.webservice.service.EmsEquipmentContactWsService;

/**
 * 类描述：客户联系人操作类 All Rights Reserved. Author: dlcai Date: 2014-5-12 下午10:48:29
 */

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	Logger logger = Logger.getLogger(getClass());

	@Resource(name = "equipmentContactService")
	private EquipmentContactService equipmentContactService;

	@Autowired
	private CssUserDAO<CssUser, Integer> userDAO;

	public String doCreateCustomer(String loginName,
			EquipmentContact equipmentContact) {
		JSONObject json = new JSONObject();
		try {
			if (StringUtils.isNotBlank(equipmentContact.getMobliePhone())) {
				int count = equipmentContactService.validateMobliePhone(
						equipmentContact.getEquipment().getEquipmentId(),
						equipmentContact.getMobliePhone());
				int count2 = equipmentContactService.validateContactName(
						equipmentContact.getEquipment().getEquipmentId(),
						equipmentContact.getContactName());

				if (count2 != 0) {
					json.element("status", "0");
					json.element("errMsg", "存在该联系人名称,请填写唯一名称");
					return json.toString();
				}

				if (count == 0) {// 不存在该手机号码
					String str = null;
					int counts = equipmentContactService
							.validateEquipmentId(equipmentContact
									.getEquipment().getEquipmentId());
					if (counts == 0
							&& equipmentContact.getIsManager().equals("N")) {
						str = "至少有一个人为当前管理员，请确认";
						json.element("status", "0");
						json.element("errMsg", str);
						return json.toString();
					}

					JSONObject jsonstr = new JSONObject();
					jsonstr.put("equipmentId", equipmentContact.getEquipment()
							.getEquipmentId());
					jsonstr.put("contactName",
							equipmentContact.getContactName());
					jsonstr.put("mobliePhone",
							equipmentContact.getMobliePhone());
					jsonstr.put("telephone", equipmentContact.getTelephone());
					jsonstr.put("positionVal",
							equipmentContact.getPositionVal());
					jsonstr.put("sex", equipmentContact.getSex());
					jsonstr.put("isManager", equipmentContact.getIsManager());
					jsonstr.put("isRefuseSms",
							equipmentContact.getIsRefuseSms());
					jsonstr.put("remark", equipmentContact.getRemark());
					JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
					factory.setServiceClass(EmsEquipmentContactWsService.class);
					factory.setAddress(WsConsts.EmsEquipmentContact);
					EmsEquipmentContactWsService emsEquipmentContactWsService = (EmsEquipmentContactWsService) factory
							.create();
					WsResultBean result = emsEquipmentContactWsService
							.doSaveEquipmentContact(loginName,
									jsonstr.toString(), WsConsts.CLIENT_TYPE);
					List<EquipmentContact> list = equipmentContactService
							.listById(equipmentContact.getEquipment()
									.getEquipmentId());
					EquipmentContact customer = new EquipmentContact();
					if (result.isReturnResult() && list != null) {
						customer = list.get(0);
						str = "添加客户联系人成功";
						json.element("equipmentcontactId",
								customer.getEquipmentContactId());
						json.element("status", "1");
						json.element("errMsg", str);
					} else {
						json.element("status", "0");
						json.element(
								"errMsg",
								"没有联系人信息" + result.getErrorMessage()
										+ result.getReturnMessage());
					}

				} else {
					json.element("status", "0");
					json.element("errMsg", "存在该手机号码,请填写唯一手机号码");
					return json.toString();
				}
			} else {
				String str = null;
				int count2 = equipmentContactService.validateContactName(
						equipmentContact.getEquipment().getEquipmentId(),
						equipmentContact.getContactName());
				CssUser user = userDAO.getUserById(equipmentContact
						.getCreateUserId());
				if (count2 != 0) {
					json.element("status", "0");
					json.element("errMsg", "存在该联系人名称,请填写唯一名称");
					return json.toString();
				}
				int counts = equipmentContactService
						.validateEquipmentId(equipmentContact.getEquipment()
								.getEquipmentId());
				if (counts == 0 && equipmentContact.getIsManager().equals("N")) {
					str = "至少有一个人为当前管理员，请确认";
					json.element("status", "0");
					json.element("errMsg", str);
					return json.toString();
				}
				JSONObject jsonstr = new JSONObject();
				jsonstr.put("equipmentId", equipmentContact.getEquipment()
						.getEquipmentId());
				jsonstr.put("contactName", equipmentContact.getContactName());
				jsonstr.put("mobliePhone", equipmentContact.getMobliePhone());
				jsonstr.put("telephone", equipmentContact.getTelephone());
				jsonstr.put("positionVal", equipmentContact.getPositionVal());
				jsonstr.put("sex", equipmentContact.getSex());
				jsonstr.put("isManager", equipmentContact.getIsManager());
				jsonstr.put("isRefuseSms", equipmentContact.getIsRefuseSms());
				jsonstr.put("remark", equipmentContact.getRemark());
				JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
				factory.setServiceClass(EmsEquipmentContactWsService.class);
				factory.setAddress(WsConsts.EmsEquipmentContact);
				EmsEquipmentContactWsService emsEquipmentContactWsService = (EmsEquipmentContactWsService) factory
						.create();
				WsResultBean result = emsEquipmentContactWsService
						.doSaveEquipmentContact(user.getLoginName(),
								jsonstr.toString(), WsConsts.CLIENT_TYPE);
				List<EquipmentContact> list = equipmentContactService
						.listById(equipmentContact.getEquipment()
								.getEquipmentId());
				EquipmentContact customer = new EquipmentContact();
				if (list != null) {
					customer = list.get(0);
				}
				if (result.isReturnResult()) {
					str = "添加客户联系人成功";
					json.element("equipmentcontactId",
							customer.getEquipmentContactId());
					json.element("status", "1");
					json.element("errMsg", str);
				} else {
					json.element("status", "0");
					json.element("errMsg",
							"添加客户联系人失败" + result.getErrorMessage() + "&"
									+ result.getReturnMessage());
				}

			}
		} catch (Exception e) {
			json.element("status", "0");
			json.element("errMsg", "添加客户联系人失败");
			logger.info(e.getCause());
			e.printStackTrace();
		}
		return json.toString();
	}

	/***
	 * 修改客服联系人
	 */
	public String updateByPrimaryKey(String loginName,
			EquipmentContact equipmentContact) {
		JSONObject json = new JSONObject();
		try {
			String str;
			int counts = equipmentContactService
					.validateEquipmentId(equipmentContact.getEquipment()
							.getEquipmentId());
			if (counts == 1 || counts == 0) {
				EquipmentContact equipment = equipmentContactService
						.getEquipmentContactManager(equipmentContact
								.getEquipment().getEquipmentId());// 是管理员的记录
				String e1 = String.valueOf(equipmentContact
						.getEquipmentContactId());
				String e2 = String.valueOf(equipment.getEquipmentContactId());

				if (e1.equals(e2)
						&& equipmentContact.getIsManager().equals("N")) {// 判断是不是同一条数据
					str = "至少有一个人为当前管理员，请确认";
					json.element("status", "0");
					json.element("errMsg", str);
					return json.toString();
				}
			}
			int count = equipmentContactService.validateMobliePhone(
					equipmentContact.getEquipment().getEquipmentId(),
					equipmentContact.getMobliePhone());
			int count2 = equipmentContactService.validateContactName(
					equipmentContact.getEquipment().getEquipmentId(),
					equipmentContact.getContactName());
			if (count == 1) {// 修改时存在一条手机号码
				EquipmentContact equipment = equipmentContactService
						.getEquipmentContactByMobliePhone(equipmentContact
								.getEquipment().getEquipmentId(),
								equipmentContact.getMobliePhone());// 是手机号码的记录
				String e1 = String.valueOf(equipmentContact
						.getEquipmentContactId());
				String e2 = String.valueOf(equipment.getEquipmentContactId());
				if (!e1.equals(e2)) {
					json.element("status", "0");
					json.element("errMsg", "存在该手机号码,请填写唯一手机号码");
					return json.toString();
				}
			}

			if (count2 == 1 || count2 >= 1) {// 名称
				EquipmentContact equipment = equipmentContactService
						.getEquipmentContactByContactName(equipmentContact
								.getEquipment().getEquipmentId(),
								equipmentContact.getContactName());
				String e1 = String.valueOf(equipmentContact
						.getEquipmentContactId());
				String e2 = String.valueOf(equipment.getEquipmentContactId());
				if (!e1.equals(e2)) {
					json.element("status", "0");
					json.element("errMsg", "存在该联系人名称,请填写唯一名称");
					return json.toString();
				}
			}

			str = "更新客户联系人成功";

			JSONObject jsonstr = new JSONObject();
			jsonstr.put("equipmentContactId",
					equipmentContact.getEquipmentContactId());
			jsonstr.put("equipmentId", equipmentContact.getEquipment()
					.getEquipmentId());
			jsonstr.put("contactName", equipmentContact.getContactName());
			jsonstr.put("mobliePhone", equipmentContact.getMobliePhone());
			jsonstr.put("telephone", equipmentContact.getTelephone());
			jsonstr.put("positionVal", equipmentContact.getPositionVal());
			jsonstr.put("sex", equipmentContact.getSex());
			jsonstr.put("isManager", equipmentContact.getIsManager());
			jsonstr.put("isRefuseSms", equipmentContact.getIsRefuseSms());
			jsonstr.put("remark", equipmentContact.getRemark());
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(EmsEquipmentContactWsService.class);
			factory.setAddress(WsConsts.EmsEquipmentContact);
			EmsEquipmentContactWsService emsEquipmentContactWsService = (EmsEquipmentContactWsService) factory
					.create();
			WsResultBean result = emsEquipmentContactWsService
					.doUpdateEquipmentContact(loginName, jsonstr.toString(),
							WsConsts.CLIENT_TYPE);
			if (result.isReturnResult()) {
				json.element("status", "1");
				json.element("errMsg", str);
			} else {
				json.element("status", "0");
				json.element(
						"errMsg",
						result.getErrorMessage() + "&"
								+ result.getReturnMessage());
			}

		} catch (Exception e) {
			json.element("status", "0");
			json.element("errMsg", "更新客户联系人失败");
			logger.info(e.getCause());
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * 删除
	 */
	public String doDeleteCustomer(String loginName, String param) {
		Long equipmentcontactId = Long.valueOf(param);
		JSONObject jsonObject = new JSONObject();

		logger.debug("删除客户联系人ID=" + equipmentcontactId);

		EquipmentContact equipment = equipmentContactService
				.getEquipmentContactById(Integer.valueOf(param));

		int count = equipmentContactService.validateContactId(
				Integer.valueOf(param), false);// 判断只有一条数据
		int counts = equipmentContactService.validateContactId(
				Integer.valueOf(param), true);// 至少有一个管理员

		if (count == 1) {
			jsonObject.element("status", "0");
			jsonObject.element("errMsg", "至少保留一条客户联系人记录");
			return jsonObject.toString();
		}

		if (counts == 1 && equipment.getIsManager().equals("Y")) {
			jsonObject.element("status", "0");
			jsonObject.element("errMsg", "至少有一个人为当前管理员");
			return jsonObject.toString();
		}
		Long[] l = new Long[1];
		l[0] = equipmentcontactId;
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(EmsEquipmentContactWsService.class);
		factory.setAddress(WsConsts.EmsEquipmentContact);
		EmsEquipmentContactWsService emsEquipmentContactWsService = (EmsEquipmentContactWsService) factory
				.create();
		WsResultBean result = emsEquipmentContactWsService
				.doDeteleEquipmentContact(loginName, l, WsConsts.CLIENT_TYPE);
		if (result.isReturnResult()) {
			jsonObject.element("status", "1");
			jsonObject.element("errMsg", "删除客户联系人成功");
		} else {
			jsonObject.element("status", "0");
			jsonObject.element("errMsg", "删除客户联系人失败" + result.getErrorMessage()
					+ "&" + result.getReturnMessage());
		}
		return jsonObject.toString();
	}

}
