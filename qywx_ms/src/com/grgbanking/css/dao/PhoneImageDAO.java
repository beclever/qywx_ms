package com.grgbanking.css.dao;

import java.util.List;

import com.grgbanking.css.common.CssBaseDAO;
import com.grgbanking.css.common.PhoneImage;


/**
 * 
 * Product:Grgbanking Service Of Customer System. Version:2.0 Copyright 2010 by
 * Grgbanking All Rights Reserved. Author: jeff steve . lin Date: 2012-6-14
 * 上午11:38:33
 */
public interface PhoneImageDAO<T, ID> extends CssBaseDAO<T, ID> {

	public List<PhoneImage> getPhoneImages(Integer departId, Integer equipId, Integer workformId, Integer custId, Integer type);

	//public List<Map> queryWorkFormList(Page queryPage, WorkFormBean workFormBean);

	//public void deleteImagesByWorkformId(Integer workformId);
	
	/**
	 * 根据工单附件ID查询附件blob流
	 * @param phoneImageId
	 * @return InputStream
	 */
	//public InputStream executeBlobInputStream(Integer phoneImageId);
}
