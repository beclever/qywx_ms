package com.grgbanking.webservice.service;

import javax.jws.WebService;

import com.grgbanking.core.entity.equipment.WsEquipmentImageBean;

@WebService
public interface WeiXinService {

	//设备图片上传接口
	public String doCreateEquipmentImages(WsEquipmentImageBean wsEquipmentImageBean);
	
	//设备图片删除接口
	public String doDeleteEquipmentImages(String id);
	
	//设备图片查看接口
	public WsEquipmentImageBean queryEquipmentImages(String serialNumber);
	
	//图片获取接口
	public String getPhoneImage(String id);
	
	//工单图片查看接口
    public WsEquipmentImageBean getImagesByWorkformId(String workformId); 
    
    //操作预警新增保存接口
    public String doSaveServicesWorkformWarning(String WorkFormWarning);
    
}
