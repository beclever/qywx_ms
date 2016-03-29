package com.grgbanking.core.service.sparepart.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.entity.stockbean.WsAcceptSparepartBean;
import com.grgbanking.core.entity.stockbean.WsAocBaseMaterialBean;
import com.grgbanking.core.entity.stockbean.WsAocBorrowInfoBean;
import com.grgbanking.core.entity.stockbean.WsAocReviewBorrowBean;
import com.grgbanking.core.entity.stockbean.WsAocSparepartInfoBean;
import com.grgbanking.core.entity.stockbean.WsAocStockInfoBean;
import com.grgbanking.core.entity.stockbean.WsAocWorkformRepeatBean;
import com.grgbanking.core.entity.stockbean.WsBorrowDetailBean;
import com.grgbanking.core.entity.stockbean.WsSparepartParam;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultAocReviewBorrowBean;
import com.grgbanking.core.entity.ws.WsResultBean;
import com.grgbanking.core.entity.ws.WsUserInfoBean;
import com.grgbanking.core.exception.WsInterfaceException;
import com.grgbanking.core.service.WebService;
import com.grgbanking.core.service.sparepart.SparepartService;
import com.grgbanking.webservice.sparepart.WebInfoService;

@Service(value="sparepartService")
public class SparepartServiceImpl extends WebService<WebInfoService> implements SparepartService {
	
    public SparepartServiceImpl() {
        super(WebInfoService.class, WsConsts.SpmSearchEngineUrl);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<WsAocStockInfoBean> aocFindStockInfo(String userId, List<Long> storeIdList, String materialParam,
            WsPage page, String clientType) throws Exception{
        // TODO Auto-generated method stub
    	 try {
 	        List<WsAocStockInfoBean> result = service.aocFindStockInfo(userId,
 	                storeIdList, materialParam, page, clientType);
 	        return result;
         } catch (Exception e) {
             throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindStockInfo");
         }
    }

    @Override
    public List<WsAocSparepartInfoBean> aocFindSparepartInfoList(String loginName, List<Long> storeIdList,
            String materialCode, String clientType) {
        // TODO Auto-generated method stub
    	return null ;
    }

    @Override
    public WsBorrowDetailBean findBorrowInfoDetailList(String loginName, WsPage page, String operateType, String clientType) throws Exception{
        // TODO Auto-generated method stub
    	
    	try {
    		WsBorrowDetailBean result = service.findBorrowInfoDetailList(loginName, page,operateType, clientType);
 	        return result;                               
         } catch (Exception e) {
             throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindStockInfo");
         }
    }

    @Override
    public List<WsAocWorkformRepeatBean> aocFindPendingWorkFormRepeatList(String loginName, String clientType) throws Exception {
        try {
            List<WsAocWorkformRepeatBean> result = service.aocFindPendingWorkFormRepeatList(loginName, clientType);
            return result;                               
         } catch (Exception e) {
             throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindPendingWorkFormRepeatList");
         }
    }

    @Override
    public List<WsAocBaseMaterialBean> aocFindThreeLevelSptForWorkForm(String materialCode, String clientType) throws Exception {
        try {
            List<WsAocBaseMaterialBean> result = service.aocFindThreeLevelSptForWorkForm(materialCode, clientType);
            return result;                               
         } catch (Exception e) {
             throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindThreeLevelSptForWorkForm");
         }
    }

    @Override
    public List<WsAocBorrowInfoBean> aocFindBorrowInfoList(String loginName, String[] moduleLevels, String clientType) throws Exception {
        try {
            List<WsAocBorrowInfoBean> result = service.aocFindBorrowInfoList(loginName, moduleLevels, clientType);
            return result;                               
         } catch (Exception e) {
             throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindBorrowInfoList");
         }
    }

    @Override
    public WsAocReviewBorrowBean aocFindToReviewBorrowInfo(String loginName, Long applyId, String clientType) throws Exception {
        try {
            WsAocReviewBorrowBean result = service.aocFindToReviewBorrowInfo(loginName, applyId, clientType);
            return result;                               
         } catch (Exception e) {
             throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindToReviewBorrowInfo");
         }
    }

    @Override
    public WsResultAocReviewBorrowBean aocFindToReviewBorrowInfoList(String loginName, WsPage page, String clientType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WsAcceptSparepartBean> findDockingSparepartList(String loginName, String clientType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WsUserInfoBean> findUserList(String loginName, String storeId, String clientType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean dockingSparepart(String loginName, List<WsSparepartParam> sparepartList, String clientType,
            String targetLoginName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultBean dealDockingSparepart(String loginName, List<WsSparepartParam> sparepartList, String dealType,
            String clientType) {
        // TODO Auto-generated method stub
        return null;
    }

}
