package com.grgbanking.webservice.sparepart;

import java.util.List;

import javax.jws.WebService;

import com.grgbanking.core.entity.stockbean.WsAcceptSparepartBean;
import com.grgbanking.core.entity.stockbean.WsAocBaseMaterialBean;
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



/**
 * 2013.10.14
 * 
 * @author yt
 * 
 *         function:库存查询变更了，更改新的接口aocFindStockInfo
 */

@WebService
public interface SpmSearchEngine {

	public final static String URL = "http://10.2.8.67:8080/spm/webservices/spmSearchEngine";

	// public final static String
	// URL2="http://10.2.15.1:8080/spm/webservices/spmSearchEngine";

	// test(lwen )

	// 备件查询(库存)
	// List<WsStockInfoBean> aocFindStockInfo(String userId,List<Long>
	// storeIdList,String materialCode, String materialName,String clientType);

	// 备件库存查询（分页）

	List<WsAocStockInfoBean> aocFindStockInfo(String userId,
			List<Long> storeIdList, String materialParam, WsPage page,
			String clientType);

	// 查询库存明细 (仅查好件)
	List<WsAocSparepartInfoBean> aocFindSparepartInfoList(String loginName,
			List<Long> storeIdList, String materialCode,  String clientType);

	// 归还列表

	// List<WsAocBorrowDetailBean> aocFindBorrowInfoDetailList(String
	// loginName,WsPage page,String clientType);

	WsBorrowDetailBean findBorrowInfoDetailList(String loginName,
			WsPage page, String clientType);

	// 备件替换
	List<WsAocWorkformRepeatBean> aocFindPendingWorkFormRepeatList(
			String loginName, String clientType);

	/**
	 * 查找工单零备件替换中的原模块(依据新零件物料查找通用件)
	 * 
	 * @param materialCode
	 * @return
	 */
	public List<WsAocBaseMaterialBean> aocFindThreeLevelSptForWorkForm(
			String materialCode, String clientType);

	/**
	 * 获取用户借用的备件 用于工单替换(新模块)
	 * 
	 * @param loginName
	 * @param clientType
	 *            String []moduleLevels={A,B} String []moduleLevels={C}//零备件
	 * 
	 * @return
	 */
	public List<WsAocBorrowInfoBean> aocFindBorrowInfoList(String loginName,
			String[] moduleLevels, String clientType);

	/**
    *
    * @param loginName
    * @param moduleLevels
    * @param clientType
    * @return
    * 获取用户借用(不含待归还)备件清单
    */
   public List<WsBorrowInfoBean> findBorrowInfoList(String loginName,String[] moduleLevels,String clientType);
	
	/**
	 * 获取某条待审批借用备件信息(AOC)
	 * 
	 * @param loginName
	 * @param clientType
	 * @return
	 */
	public WsAocReviewBorrowBean aocFindToReviewBorrowInfo(String loginName,
			Long applyId, String clientType);

	/**
	 * 待审批借用备件信息(AOC)
	 * 
	 * @param loginName
	 * @param clientType
	 * @return
	 */
	public WsResultAocReviewBorrowBean allFindToReviewBorrowInfoList(
			String loginName, WsPage page,String operateType,String clientType);

	/**
	 * 待接收备件
	 * 
	 * @param loginName
	 * @param clientType
	 * @return
	 */
	public List<WsAcceptSparepartBean> findDockingSparepartList(
			String loginName, String clientType);

	public List<WsUserInfoBean> findUserList(String loginName, String storeId,
			String clientType);

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
	 */
	public WsResultBean dockingSparepart(String loginName,
			List<WsSparepartParam> sparepartList, String clientType,
			String targetLoginName);

	/**
	 * 是否接收交接的备件
	 * 
	 * @param loginName
	 * @param sparepartList
	 *            待处理的备件列表
	 * @param dealType
	 *            1.接收 0拒接
	 * @param clientType
	 * @return
	 * @author sghong
	 */
	public WsResultBean dealDockingSparepart(String loginName,
			List<WsSparepartParam> sparepartList, String dealType,
			String clientType);
}
