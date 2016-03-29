package com.grgbanking.core.service.workorder.impl;

import org.springframework.stereotype.Service;

import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.service.WebService;
import com.grgbanking.core.service.workorder.SoftwareMediaWsService;
import com.grgbanking.core.service.workorder.WebSoftwareMediaWsService;

@Service("webSoftwareMediaWsService")
public class WebSoftwareMediaWsServiceImp extends WebService<SoftwareMediaWsService> implements WebSoftwareMediaWsService {

	public WebSoftwareMediaWsServiceImp() {
		super(SoftwareMediaWsService.class, WsConsts.SoftwareMediaWsUrl);
	}

	public String gatherMedia(int formId, String media, String dataSource) {
		System.out.println("sadsa:" + service.gatherMedia(formId, media, dataSource));
		return service.gatherMedia(formId, media, dataSource);
	}

}