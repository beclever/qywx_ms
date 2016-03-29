package com.grgbanking.core.service.equipment.impl;

import org.springframework.stereotype.Service;

import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.entity.equipment.WsEquipmentImageBean;
import com.grgbanking.core.service.WebService;
import com.grgbanking.core.service.equipment.WeiXinImagesService;
import com.grgbanking.webservice.service.WeiXinService;

@Service("weiXinImagesService")
public class WeiXinImagesServiceImpl extends WebService<WeiXinService>
		implements WeiXinImagesService {

	public WeiXinImagesServiceImpl() {
		super(WeiXinService.class, WsConsts.WeiXinService);
	}

	@Override
	public String doCreateEquipmentImages(
			WsEquipmentImageBean wsEquipmentImageBean) {
		return service.doCreateEquipmentImages(wsEquipmentImageBean);
	}

	@Override
	public String deleteEquipmentImages(String id) {
		return service.doDeleteEquipmentImages(id);
	}

	@Override
	public WsEquipmentImageBean queryEquipmentImages(String serialNumber) {
		return service.queryEquipmentImages(serialNumber);
	}

	@Override
	public String getPhoneImage(String id) {
		return service.getPhoneImage(id);
	}

    @Override
    public WsEquipmentImageBean getImagesByWorkformId(String workformId) {
        return service.getImagesByWorkformId(workformId);
    }
    
    @Override
    public String doSaveServicesWorkformWarning(String WorkFormWarning){
        return service.doSaveServicesWorkformWarning(WorkFormWarning);
    }
}