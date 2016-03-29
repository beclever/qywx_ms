package com.grgbanking.webservice.sparepart;

import java.util.List;

import javax.jws.WebService;

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



@WebService
public interface WebInfoService {
	/**
	 * 测试
	 * 2013.10.9
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	//	public String sysHello(String name);

	//备件查询(分页)
	List<WsAocStockInfoBean> aocFindStockInfo(String userId,List<Long> storeIdList,String materialParam, WsPage page,String clientType) throws Exception;

	//查询库存明细 (仅查好件)
	List<WsAocSparepartInfoBean> aocFindSparepartInfoList(String loginName, List<Long> storeIdList, String materialCode, String clientType) throws Exception;


	//备件申请
	public WsResultBean  allBorrowApply(String loginName,
			String borrowerLoginName, WsAocBorrowApplyInfoParam params, String operateType,
			String clientType) throws Exception;
	


	//备件替换
	List<WsAocWorkformRepeatBean> aocFindPendingWorkFormRepeatList(String loginName, String clientType) throws Exception;

	// 备件归还列表
	// 旧接口，生产在用1.0
	// List<WsAocBorrowInfoDetailBean> aocFindBorrowInfoDetailList(String loginName,WsPage page,String clientType );
	// 新接口，已经发布测试环境1.1
	//List<WsAocBorrowDetailBean> aocFindBorrowInfoDetailList(String loginName, WsPage page, String clientType);
	//备件归还列表(新)表2.0
	WsBorrowDetailBean findBorrowInfoDetailList(String loginName, WsPage page, String operateType, String clientType) throws Exception;

	//拒接/接收 单个/多个备件
	WsResultBean dealDockingSparepart(String loginName, List<WsSparepartParam> sparepartList, String dealType, String clientType) throws Exception;

	// 备件
	// WsResultBean aocReturnSparepart(String loginName, Long storeId, Long[] borrowIds, String clientType);
	// * 归还备件(新)
	WsResultBean aocReturnSparepart(String loginName, Long storeId, List<WsSparepartParam> sparepartList, String clientType) throws Exception;

	//待接收备件
	// List<WsAcceptSparepartBean>  findDockingSparepartList(String loginName,String clientType);
	// 待接收备件
	List<WsAcceptSparepartBean> findDockingSparepartList(String loginName, WsPage page, String clientType) throws Exception;

	// * 获取仓库用户列表
	List<WsUserInfoBean> findUserList(String loginName, String storeId, String clientType) throws Exception;

	//
	/**
	 * 查找工单零备件替换中的原模块(依据新零件物料查找通用件)
	 * @param materialCode
	 * @return
	 * @throws Exception 
	 */
	public List<WsAocBaseMaterialBean> aocFindThreeLevelSptForWorkForm(String materialCode, String clientType) throws Exception;


	//新物料（新零备件）
	public List<WsAocBorrowInfoBean> aocFindBorrowInfoList(String loginName, String[] moduleLevels, String clientType) throws Exception;


	 /**
     * 待审批借用备件信息(AOC)
     * @param loginName
     * @param clientType
     * @return
	 * @throws Exception 
     */
	public WsResultAocReviewBorrowBean allFindToReviewBorrowInfoList(String loginName,WsPage wspage,String operateType,String clientType) throws Exception;
	
	/**
     * 获取某条待审批借用备件信息(AOC)
     * @param loginName
     * @param clientType
     * @return
	 * @throws Exception 
     */
    public WsAocReviewBorrowBean aocFindToReviewBorrowInfo(String loginName, Long applyId, String clientType) throws Exception;

	/**
	 * 借出备件（AOC）
	 * @param loginName
	 * @param serialNumber
	 * @param note
	 * @param clientType
	 * @return
	 * @throws Exception 
	 */
	public WsResultBean allBorrowSparepartReview(String loginName,String applyId,String serialNumber,String note,String OperateType,String clientType) throws Exception;

	public WsResultBean dockingSparepart(String loginName, List<WsSparepartParam> sparepartList, String clientType, String targetLoginName) throws Exception;
	/**
    *
    * @param loginName
    * @param moduleLevels
    * @param clientType
    * @return
    * 获取用户借用(不含待归还)备件清单
    */
   public List<WsBorrowInfoBean> findBorrowInfoList(String loginName,String[] moduleLevels,String clientType) throws Exception;

}
