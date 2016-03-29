package com.grgbanking.core.service.workorder;

import javax.jws.WebService;

/**
 * 二维码处理 date:2014.10.21
 * @author QLH
 */
@WebService
public interface SoftwareMediaWsService {
	public String gatherMedia(int formId, String media, String dataSource);
}