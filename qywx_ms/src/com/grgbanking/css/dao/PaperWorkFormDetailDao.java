/**
 * 
 */
package com.grgbanking.css.dao;

import java.util.List;
import java.util.Map;

import com.grgbanking.css.bean.work.PaperWorkFormBean;
import com.grgbanking.css.bean.work.PaperWorkFormDetail;
import com.grgbanking.css.common.CssBaseDAO;


/**
 * Copyright (c) 2011 GRG Banking Equipment Co.,Ltd. 
 * @createDate 2012-10-15
 * @author luowei 
 * @description 
 * 
 */
public interface PaperWorkFormDetailDao <T, ID> extends CssBaseDAO<T, ID> {

//	/**
//	 * @author:luowei
//	 * @createDate 2012-10-15
//	 * @param bean
//	 * @param queryPage
//	 * @return List<PaperWorkFormDetail>
//	 * @description
//	 * 查询纸质工单
//	 */
//	public List<Map> searchPaperWorkFormList(PaperWorkFormBean bean,
//			Page queryPage);
//	
//	/**
//	 * @author:luowei
//	 * @createDate 2012-10-17
//	 * @param bean
//	 * @param queryPage
//	 * @return List<Map>
//	 * @description
//	 * 查询申请废弃的纸质工单
//	 */
//	public List<Map> searchPaperWorkFormNotApplyAbandonedList(
//			PaperWorkFormBean bean, Page queryPage);
//
	/**
	 * @author:luowei
	 * @createDate 2012-10-17
	 * @param bean
	 * @param userId 
	 * @return String
	 * @description
	 * 查询下一个纸质工单编号
	 */
	public Map generateNextPaperWorkFormCode(PaperWorkFormBean bean, Integer userId);

	/**
	 * @author:luowei
	 * @createDate 2012-10-18
	 * @param paperFormCode
	 * @return PaperWorkFormDetail
	 * @description
	 * 根据纸质工单编号查找纸质工单
	 */
	public PaperWorkFormDetail findByPaperFormCode(String paperFormCode);
	
	/**
	 * 根据纸质工单编号与工单ID查找纸质工单
	 * @param paperFormCode
	 * @param workFormId
	 * @return
	 */
	public PaperWorkFormDetail findByWorkFormId(String paperFormCode, Long workFormId);

	/**
	 * @author:luowei
	 * @createDate 2012-10-18
	 * @param workFormId
	 * @return List<PaperWorkFormDetail>
	 * @description
	 * 根据工单查找
	 */
	public List<PaperWorkFormDetail> findByWorkFormId(String workFormId);
//
//	/**
//	 * @author:luowei
//	 * @createDate 2012-10-22
//	 * @param abandonedWorkflowId
//	 * @return String
//	 * @description
//	 * 根据废弃流程编号查找单张纸质工单编号
//	 */
//	public String findSinglePaperWorkFormCode(String abandonedWorkflowId);
//
//	/**
//	 * @author:luowei
//	 * @createDate 2012-10-22
//	 * @param bean
//	 * @param queryPage
//	 * @return List<Map>
//	 * @description
//	 * 查询纸质工单详情页面
//	 */
//	public List<Map> searchPaperWorkFormDetailList(PaperWorkFormBean bean,
//			Page queryPage);
//	
//	/**
//	 * @author:luowei
//	 * @param abandonedWorkflowId 
//	 * @return List<PaperWorkFormAbandoned>
//	 * @createDate 2012-10-22
//	 * @description
//	 * 查询纸质工单详情页面
//	 */
//	public List<PaperWorkFormDetail> searchPaperWorkFormList(String abandonedWorkflowId);
//
//	/**
//	 * @author:luowei
//	 * @param userId 
//	 * @createDate 2012-10-23
//	 * @return int
//	 * @description
//	 * 查询剩余工单数量
//	 */
//	public int querySelfPaperNum(Integer userId);
//	
//	/**
//	 * @author: hzhceng
//	 * @createDate 2012-10-15
//	 * @param bean
//	 * @param queryPage
//	 * @return List<PaperWorkFormDetail>
//	 * @description
//	 * 查询纸质工单
//	 */
//	public List<Map> searchPaperWorkFormQueryList(PaperWorkFormBean bean,
//			Page queryPage);
//	/**
//	 * @author: hzhceng
//	 * @createDate 2012-10-15
//	 * @param bean
//	 * @param queryPage
//	 * @return List<PaperWorkFormDetail>
//	 * @description
//	 * 纸质工单报表
//	 */
//	public List<Map> searchPaperWorkFormReportList(PaperWorkFormBean bean,
//			Page queryPage);
//	public List<Map> searchPaperWorkFormReportList(PaperWorkFormBean bean);
//	
//	public List<Map> searchProSatisfyReportList(PaperWorkFormBean bean,
//			Page queryPage);
//	public List<Map> searchCusSatisfyReportList(PaperWorkFormBean bean,
//			Page queryPage);
//
//	public List<PaperWorkFormDetail> findByAbandonedId(String abandonedWorkflowId);
}
