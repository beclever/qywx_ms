package com.grgbanking.core.service.workorder;

import javax.jws.WebService;

import com.grgbanking.core.entity.ws.ReturnData;

@WebService
public interface IPaperService {
	
	/**
	 * @author:luowei
	 * @createDate 2013-7-15
	 * @param workFormId
	 * @param loginName 
	 * @param clientType 
	 * @return ReturnData
	 * @description
	 * 查询工单是否绑定纸质工单,如果已绑定则返回纸质工单编号,否则标志为false
	 */
	public ReturnData judgment(String workFormId,String loginName,String clientType);
	
	/**
	 * @author:luowei
	 * @param paperCode 
	 * @param loginName 
	 * @param clientType 
	 * @createDate 2013-7-15
	 * @return ReturnData
	 * @description
	 * 查询下一张纸质工单编号：
	 * 1、如果paperCode为空则查询领用的第一张纸质工单
	 * 2、返回值为两个字符串，中间用逗号隔开，如 118352,2000002 第一个值表示纸质工单的ID，第二个值表示纸质工单CODE
	 */
	public ReturnData generateNextPaperWorkFormCode(String paperCode,String loginName,String clientType);
	
	/**
	 * @author:luowei
	 * @createDate 2013-7-15
	 * @param workformId
	 * @param paperCode
	 * @param loginName 
	 * @param clientType 
	 * @return ReturnData
	 * @description
	 * 绑定纸质工单
	 */
	public ReturnData doBindPaperWorkform(String workformId,String paperCode,String loginName,String clientType);
	
	/**
	 * @author:luowei
	 * @createDate 2013-7-15
	 * @param paperformId 纸质工单ID
	 * @param reason 作废原因
	 * @param note 作废说明
	 * @param loginName 用户名
	 * @param clientType 
	 * @return ReturnData
	 * @description
	 * 申请纸质工单作废
	 */
	public ReturnData doApplyAbandonedToCheck(String paperformId,String reason,String note,String loginName,String clientType);
}