package com.grgbanking.webservice.sparepart;

import java.util.List;

import javax.jws.WebService;

import com.grgbanking.core.entity.stockbean.WsAocBorrowApplyInfoParam;
import com.grgbanking.core.entity.stockbean.WsSparepartParam;
import com.grgbanking.core.entity.ws.WsResultBean;
import com.grgbanking.core.entity.ws.WsUserInfoBean;


/**
 * 备件申请
 * 
 * @author yt
 * 
 */

@WebService
public interface SpmSparepartEngine {

	final static String URL = "http://10.2.8.67:8080/spm/webservices/spmSparepartEngine";

	// final static String
	// URL2="http://10.2.15.1:8080/spm/webservices/spmSparepartEngine";

	// 备件申请
	public WsResultBean allBorrowApply(String loginName,
			String borrowerLoginName,
			List<WsAocBorrowApplyInfoParam> borrowApplyInfoList, String OperateType, String clientType);

	  /**
	   * 归还备件(新)
	   * @param loginName
	   * @param storeId 归还目标仓
	   * @param sparepartIds 归还备件ID数组
	   * @param clientType 请求发送端类型：PHONE-手机；CSS-客服系统；AFC-AFC系统；SA-SA系统
	   * @return
	   * 
	   */
	  public WsResultBean aocReturnSparepart(String loginName,Long storeId,List<WsSparepartParam> sparepartList, String clientType);

	/**
	 * 借出备件（AOC）
	 * 
	 * @param loginName
	 * @param serialNumber
	 * @param note
	 * @param clientType
	 * @return
	 */
	public WsResultBean allBorrowSparepartReview(String loginName,
			String applyId, String serialNumber, String note,String OperateType, String clientType);

	/**
	 * 拒接/接收 单个/多个备件 loginName String 是 用户登录ID sparepartList 是 拒接备件列表 dealType
	 * String 是 1接收, 0拒接 clientType 是 请求发送端类型：PHONE-手机；CSS-客服系统；AFC-AFC系统
	 * 
	 * @return
	 */

	WsResultBean dealDockingSparepart(String loginName,
			List<WsSparepartParam> sparepartList, String dealType,
			String clientType);

	/**
	 * 获取仓库用户列表
	 * 
	 * @param loginName
	 *            用户登录ID
	 * @param storeId
	 *            仓库id
	 * @param clientType
	 *            请求发送端类型：PHONE-手机；CSS-客服系统；AFC-AFC系统
	 * @return
	 */
	List<WsUserInfoBean> findUserList(String loginName, String storeId,
			String clientType);

	public WsResultBean dockingSparepart(String loginName,
			List<WsSparepartParam> sparepartList, String clientType,
			String targetLoginName);
	



}
