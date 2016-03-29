package com.grgbanking.css.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grgbanking.css.bean.vequipment.VEquipmentContact;
import com.grgbanking.css.bean.vequipment.VEquipmentContactBean;
import com.grgbanking.css.dao.vequipment.VEquipmentContactDAO;
import com.grgbanking.css.service.VEquipmentContactService;


@Service("vEquipmentContactService")
public class VEquipmentContactServiceImpl implements VEquipmentContactService {
	
	@Autowired
	private VEquipmentContactDAO<VEquipmentContact,Integer> equipmentContactDAO;
	

	public List<VEquipmentContactBean> getEqtManager(Integer equipmentId) {
		List<VEquipmentContact> list = equipmentContactDAO.getEqtManager(equipmentId);
		if (null != list && list.size() > 0) {
			VEquipmentContactBean bean = null;
			List<VEquipmentContactBean> resultList = new ArrayList<VEquipmentContactBean>();
			VEquipmentContact contact = null;
			for (int i = 0; i < list.size(); i++) {
				bean = new VEquipmentContactBean();
				contact = list.get(i);
				BeanUtils.copyProperties(contact, bean);
				resultList.add(bean);
			}
			return resultList;
		} else {
			return null;
		}
	}
	
	
//	public boolean isRefuseSms(Integer equipmentId, String mobliePhone) {
//		VEquipmentContactBean bean = new VEquipmentContactBean();
//		bean.setEquipmentId(equipmentId);
//		bean.setIsRefuseSms("N");
//		bean.setDeleted("N");
//		bean.setMobliePhone(mobliePhone);
//	    List<VEquipmentContact> list =  equipmentContactDAO.getByBean(bean);
//	    if(list.size()>0){
//	    	return true;
//	    }else{
//	    	return false;
//	    }
//	}
//
//
//	public List<JSONObject> list(Integer equipmentId) {
//		List<VEquipmentContact> result = equipmentContactDAO.list(equipmentId);
//		List<JSONObject> list = new ArrayList<JSONObject>();
//		JSONObject json = null;
//		if (null != result) {
//			VEquipmentContact contact = null;
//			for (int i = 0; i < result.size(); i++) {
//				contact = result.get(i);
//				json = new JSONObject();
//				json.element("equipmentContactId", contact.getEquipmentContactId());
//				json.element("contactName", contact.getContactName());
//				json.element("mobliePhone", contact.getMobliePhone());
//				json.element("telephone", contact.getTelephone());
//				json.element("positionVal", contact.getPositionVal());
//				json.element("isManager", DataDictionaryLoad.getText(DataDictionaryConstants.COMMON_YES_NO, contact.getIsManager()));
//				json.element("isRefuseSms", DataDictionaryLoad.getText(DataDictionaryConstants.COMMON_YES_NO, contact.getIsRefuseSms()));
//				json.element("remark", contact.getRemark());
//				list.add(json);
//			}
//		}
//		return list;
//	}
//
//
//	public boolean validate(EquipmentContactBean equipmentContactBean) {
//		Integer i = equipmentContactDAO.validate(equipmentContactBean);
//		if (i == 0) {
//			return true;
//		}
//		return false;
//	}
//
//
//	public void doSaveOrUpdate(EquipmentContactBean bean) {
//		
//		
//		UserBean user = UserContext.getUser();
//		
//		JSONObject jsonOject=new JSONObject();
//		
//		if (null == bean.getEquipmentId()) {
//			jsonOject.element("serialNumber", bean.getSerialNumber());
//			if (StringUtils.isBlank(bean.getIsRefuseSms())){
//				jsonOject.element("isRefuseSms","N");
//			}
//		}else{
//			jsonOject.element("equipmentId", bean.getEquipmentId());
//			
//		}
//		if (StringUtils.isBlank(bean.getIsManager())) {
//			jsonOject.element("isManager","N");
//		}else{
//			jsonOject.element("isManager",bean.getIsManager());
//		}
//		if (null!= bean.getEquipmentContactId()) {
//			jsonOject.element("equipmentContactId",bean.getEquipmentContactId());
//		}
//		if (StringUtils.isNotBlank(bean.getContactName()))
//			jsonOject.element("contactName",bean.getContactName());
//		if (StringUtils.isNotBlank(bean.getMobliePhone()))
//			jsonOject.element("mobliePhone",bean.getMobliePhone());
//		if (StringUtils.isNotBlank(bean.getTelephone()))
//			jsonOject.element("telephone",bean.getTelephone());
//		if (StringUtils.isNotBlank(bean.getPositionVal()))
//			jsonOject.element("positionVal",bean.getPositionVal());
//		if (StringUtils.isNotBlank(bean.getSex()))
//			jsonOject.element("sex",bean.getSex());
//		if (StringUtils.isNotBlank(bean.getRemark()))
//			jsonOject.element("remark",bean.getRemark());
//		
//		if(null==jsonOject){
//			throw new ServiceException("相关信息不能为空！");
//		}
//		
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		factory.setServiceClass(EmsEquipmentContactWsService.class);
//		factory.setAddress(PropertiesUtils.getValue("ems_equipment_contact_webservice"));
//		EmsEquipmentContactWsService emsEquipmentContactWsService=(EmsEquipmentContactWsService)factory.create();
//		WsResultBean resultBean=null;
//		if (null == bean.getEquipmentContactId()) {
//			 resultBean=emsEquipmentContactWsService.doSaveEquipmentContact(user.getLoginName(), jsonOject.toString(), CommonConstants.SPM_CLIENT_TYPE_CSS);
//		}else{
//			 resultBean=emsEquipmentContactWsService.doUpdateEquipmentContact(user.getLoginName(), jsonOject.toString(), CommonConstants.SPM_CLIENT_TYPE_CSS);
//		}
//		if(null!=resultBean){
//			if(!resultBean.isReturnResult()){
//				throw new ServiceException(resultBean.getErrorMessage());
//			}
//		}else{
//			throw new ServiceException(CommonConstants.EMS_CLIENT_RESULT_MESSAGE);
//		}
//		
//		
//		
//		
//
//		
//	}
//	
//	public Integer validateMobliePhone(Integer equipmentId, String mobliePhone) {
//		return equipmentContactDAO.validateMobliePhone(equipmentId, mobliePhone);
//	}
//
//
//	public Integer validateTelephone(Integer equipmentId, String telephone) {
//		return equipmentContactDAO.validateTelephone(equipmentId, telephone);
//	}
//
//
//	public EquipmentContactBean getById(Integer equipmentContactId) {
//		EquipmentContactBean bean = new EquipmentContactBean();
//		VEquipmentContact contact = equipmentContactDAO.get(equipmentContactId);
//		BeanUtils.copyProperties(contact, bean);
//		bean.setEquipmentId(contact.getVequipmentId());
//		return bean;
//	}


}
