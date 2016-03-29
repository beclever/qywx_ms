package com.grgbanking.core.service.workorder.impl;

import org.springframework.stereotype.Service;

import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.entity.ws.ReturnData;
import com.grgbanking.core.service.WebService;
import com.grgbanking.core.service.workorder.IPaperService;


@Service("webPaperWorkOrderService")
public class PaperService  extends WebService<IPaperService> implements IPaperService{

    public PaperService(){
        super(IPaperService.class,WsConsts.SocPaperWorkformEngineUrl);
    }

	public ReturnData doBindPaperWorkform(String workformId,String paperCode,String loginName,String clientType) {
		ReturnData  result=service.doBindPaperWorkform(workformId,paperCode,loginName,clientType);
		return result;
	}

	@Override
	public ReturnData judgment(String workFormId, String loginName,
			String clientType) {
		ReturnData  result=service.judgment(workFormId,loginName,clientType);
		return result;
	}

	@Override
	public ReturnData generateNextPaperWorkFormCode(String paperCode,
			String loginName, String clientType) {
		ReturnData  result=service.generateNextPaperWorkFormCode(paperCode,loginName,clientType);
		return result;
	}

	@Override
	public ReturnData doApplyAbandonedToCheck(String paperformId,
			String reason, String note, String loginName, String clientType) {
		ReturnData  result=service.doApplyAbandonedToCheck(paperformId,reason,note,loginName,clientType);
		return result;
	}
	
	


}
