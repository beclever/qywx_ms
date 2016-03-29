package com.grgbanking.core.service.sparepart.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.grgbanking.common.consts.Consts;
import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.core.entity.stockbean.WsAcceptSparepartBean;
import com.grgbanking.core.entity.stockbean.WsAocBaseMaterialBean;
import com.grgbanking.core.entity.stockbean.WsAocBorrowApplyInfoParam;
import com.grgbanking.core.entity.stockbean.WsAocBorrowInfoBean;
import com.grgbanking.core.entity.stockbean.WsAocReviewBorrowBean;
import com.grgbanking.core.entity.stockbean.WsAocSparepartInfoBean;
import com.grgbanking.core.entity.stockbean.WsAocStockInfoBean;
import com.grgbanking.core.entity.stockbean.WsAocWorkformRepeatBean;
import com.grgbanking.core.entity.stockbean.WsBorrowDetailBean;
import com.grgbanking.core.entity.stockbean.WsSparepartParam;
import com.grgbanking.core.entity.ws.WsBorrowInfoBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultAocReviewBorrowBean;
import com.grgbanking.core.entity.ws.WsResultBean;
import com.grgbanking.core.entity.ws.WsUserInfoBean;
import com.grgbanking.core.exception.WsInterfaceException;
import com.grgbanking.core.service.WebService;
import com.grgbanking.webservice.sparepart.SpmSearchEngine;
import com.grgbanking.webservice.sparepart.SpmSparepartEngine;
import com.grgbanking.webservice.sparepart.WebInfoService;


@Service("webInfoService")
public class WebInfoServiceImp extends WebService<SpmSearchEngine> implements
		WebInfoService {

	private SpmSparepartEngine spmSparepartEngineService;

	public WebInfoServiceImp() {
		super(SpmSearchEngine.class, WsConsts.SpmSearchEngineUrl);
		spmSparepartEngineService = (SpmSparepartEngine) initWebService(SpmSparepartEngine.class, WsConsts.SpmSparepartEngineUrl);
	}

	/**
	 * 备件查询接口
	 * @throws Exception 
	 */
	public List<WsAocStockInfoBean> aocFindStockInfo(String userId,
			List<Long> storeIdList, String materialParam, WsPage page,
			String clientType) throws Exception {
	    try {
	        List<WsAocStockInfoBean> result = service.aocFindStockInfo(userId,
	                storeIdList, materialParam, page, clientType);
	        return result;
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindStockInfo");
        }
	}

	/**
	 * 备件查询接口(仅查好件)
	 * @throws Exception 
	 */
	public List<WsAocSparepartInfoBean> aocFindSparepartInfoList(
			String loginName, List<Long> storeIdList, String materialCode,
			String clientType) throws Exception {
		try {
		    List<WsAocSparepartInfoBean> result = service.aocFindSparepartInfoList(
	                loginName, storeIdList, materialCode, clientType);
	        return result;
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindSparepartInfoList");
        }
	}

	/**
	 * 备件借用
	 * @throws Exception 
	 */
	
	public WsResultBean allBorrowApply(String loginName,
			String borrowerLoginName, WsAocBorrowApplyInfoParam params, String operateType,
			String clientType) throws Exception {
		try {
			List<WsAocBorrowApplyInfoParam> borrowApplyInfoList = new ArrayList<WsAocBorrowApplyInfoParam>();
			borrowApplyInfoList.add(params);
		    return spmSparepartEngineService.allBorrowApply(loginName,
	                borrowerLoginName, borrowApplyInfoList,operateType, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e,WsConsts.SpmSparepartEngineUrl, "aocBorrowApply");
        }
	}

	/**
	 * 借用明细
	 * @throws Exception 
	 */
	// public List<WsAocBorrowDetailBean> aocFindBorrowInfoDetailList(String
	// loginName,WsPage page, String clientType) {
	// return service.aocFindBorrowInfoDetailList(loginName, page,clientType);
	// }
	// 借用明细新
	public WsBorrowDetailBean findBorrowInfoDetailList(
			String loginName, WsPage page,  String operateType, String clientType) throws Exception {
		try {
		    return service.findBorrowInfoDetailList(loginName, page, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindBorrowInfoDetailList");
        }
	}

    @Override
    public List<WsBorrowInfoBean> findBorrowInfoList(String loginName, String[] moduleLevels, String clientType)
            throws Exception {
        try {
            return service.findBorrowInfoList(loginName, moduleLevels, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "findBorrowInfoList");
        }
    }

	public List<WsAocWorkformRepeatBean> aocFindPendingWorkFormRepeatList(
			String loginName, String clientType) throws Exception {
		try {
		    return service.aocFindPendingWorkFormRepeatList(loginName, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e,WsConsts.SpmSearchEngineUrl, "aocFindPendingWorkFormRepeatList");
        }
	}

	public List<WsAocBaseMaterialBean> aocFindThreeLevelSptForWorkForm(
			String materialCode, String clientType) throws Exception {
		try {
		    List<WsAocBaseMaterialBean> list = service
	                .aocFindThreeLevelSptForWorkForm(materialCode,
	                		Consts.CLIENT_PHONE);
	        return list;
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindThreeLevelSptForWorkForm");
        }
	}

	public List<WsAocBorrowInfoBean> aocFindBorrowInfoList(String loginName,
			String[] moduleLevels, String clientType) throws Exception {
		try {
		    List<WsAocBorrowInfoBean> brrowlist = service.aocFindBorrowInfoList(
	                loginName, moduleLevels, Consts.CLIENT_PHONE);
	        return brrowlist;
        } catch (Exception e) {
            throw new WsInterfaceException(e,WsConsts.SpmSearchEngineUrl, "aocFindBorrowInfoList");
        }
	}

	/**
	 * 待审批借用备件信息(AOC)
	 * 
	 * @param loginName
	 * @param clientType
	 * @return
	 * @throws Exception 
	 */
	public WsResultAocReviewBorrowBean allFindToReviewBorrowInfoList(
			String loginName, WsPage wspage,String operateType, String clientType) throws Exception {
		try {
		    WsResultAocReviewBorrowBean result = service
	                .allFindToReviewBorrowInfoList(loginName, wspage,operateType, clientType);
	        return result;
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindToReviewBorrowInfoList");
        }
	}

	/**
	 * 获取某条待审批借用备件信息(AOC)
	 * 
	 * @param loginName
	 * @param clientType
	 * @return
	 * @throws Exception 
	 */
	@Override
	public WsAocReviewBorrowBean aocFindToReviewBorrowInfo(String loginName,
			Long applyId, String clientType) throws Exception {
		try {
		    WsAocReviewBorrowBean result = service.aocFindToReviewBorrowInfo(
	                loginName, applyId, clientType);
	        return result;
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocFindToReviewBorrowInfo");
        }
	}

	/**
	 * 借出备件（AOC）
	 * 
	 * @param loginName
	 * @param serialNumber
	 * @param note
	 * @param clientType
	 * @return
	 * @throws Exception 
	 */
	@Override
	public WsResultBean allBorrowSparepartReview(String loginName,
			String applyId, String serialNumber, String note,String OperateType, String clientType) throws Exception {
		try {
		    WsResultBean result = spmSparepartEngineService
	                .allBorrowSparepartReview(loginName, applyId, serialNumber,
	                        note,OperateType, clientType);
	        return result;
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocBorrowSparepartReview");
        }
	}

	/**
	 * 待接收备件
	 * 
	 * @param loginName
	 * @param clientType
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<WsAcceptSparepartBean> findDockingSparepartList(
			String loginName, WsPage page, String clientType) throws Exception {
		try {
		    List<WsAcceptSparepartBean> result;
	        result = service.findDockingSparepartList(loginName, clientType);
	        return result;
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "findDockingSparepartList");
        }
	}

	/**
	 * 备件交接
	 * 
	 * @param loginName
	 *            登陆名
	 * @param sparepartList
	 *            待交接备件的列表
	 * @param clientType
	 *            发送端类型
	 * @param targetLoginName
	 *            目标人
	 * @return
	 * @author sghong
	 * @throws Exception 
	 */
	public WsResultBean dockingSparepart(String loginName,
			List<WsSparepartParam> sparepartList, String clientType,
			String targetLoginName) throws Exception {
		try {
		    return spmSparepartEngineService.dockingSparepart(loginName, sparepartList, clientType,
	                targetLoginName);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "dockingSparepart");
        }
//		return service.dockingSparepart(loginName, sparepartList, clientType,
//				targetLoginName);
	}

	/**
	 * 拒接/接收 单个/多个备件 loginName String 是 用户登录ID sparepartList 是 拒接备件列表 dealType
	 * String 是 1接收, 0拒接 clientType 是 请求发送端类型：PHONE-手机；CSS-客服系统；AFC-AFC系统
	 * 
	 * @return
	 * @throws Exception 
	 */
	@Override
	public WsResultBean dealDockingSparepart(String loginName,
			List<WsSparepartParam> sparepartList, String dealType,
			String clientType) throws Exception {
		 try {
		     return spmSparepartEngineService.dealDockingSparepart(loginName,
		             sparepartList, dealType, clientType);
	        } catch (Exception e) {
	            throw new WsInterfaceException(e,WsConsts.SpmSearchEngineUrl, "dealDockingSparepart");
	        }
//		return service.dealDockingSparepart(loginName, sparepartList, dealType,
//				clientType);
	}

	@Override
	public List<WsUserInfoBean> findUserList(String loginName, String storeId,
			String clientType) throws Exception {
		// return spmSparepartEngineService.findUserList(loginName, storeId,
		// clientType);
		try {
		    return service.findUserList(loginName, storeId, clientType);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "findUserList");
        }
	}

	@Override
	public WsResultBean aocReturnSparepart(String loginName, Long storeId,
			List<WsSparepartParam> sparepartList, String clientType) throws Exception {
		try {
		    WsResultBean result = spmSparepartEngineService.aocReturnSparepart(
	                loginName, storeId, sparepartList, clientType);
	        return result;
           } catch (Exception e) {
               throw new WsInterfaceException(e, WsConsts.SpmSearchEngineUrl, "aocReturnSparepart");
           }
	}
	

}
