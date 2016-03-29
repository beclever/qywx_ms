package com.grgbanking.core.controller.sparepart;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.grgbanking.common.consts.Consts;
import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.core.controller.BaseController;
import com.grgbanking.core.entity.stockbean.WsAocBorrowApplyInfoParam;
import com.grgbanking.core.entity.stockbean.WsAocSparepartInfoBean;
import com.grgbanking.core.entity.stockbean.WsAocStockInfoBean;
import com.grgbanking.core.entity.stockbean.WsBorrowDetailBean;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultAocReviewBorrowBean;
import com.grgbanking.core.entity.ws.WsResultBean;
import com.grgbanking.core.service.sparepart.SparepartService;
import com.grgbanking.webservice.sparepart.WebInfoService;

@Controller
public class SparepartController extends BaseController {

	@Resource(name = "sparepartService")
	private SparepartService sparepartService;

	@Autowired
	private WebInfoService service;

	@RequestMapping("/cp/sparepart/main")
	public ModelAndView managerSparepart(ModelAndView mv) {

		mv.setViewName("/sparepart/main");
		return mv;
	}

	@RequestMapping("/cp/ouath/sparepart/query")
	public ModelAndView querySparepart(HttpServletRequest req,
			HttpServletResponse res, ModelAndView mv, String materialName,
			Integer pageNum) {
		mv.setViewName("/sparepart/spareSearch");
		return mv;
	}
	

	@ResponseBody
	@RequestMapping(value = "/cp/sparepart/pageSparelistByParam")
	public String pagelistByParam(HttpServletRequest req,
			HttpServletResponse res, String materialName, Integer pageNum) {
		try {
			WxUser wxUser = LoginUser.getSessionUser();

			List<Long> list = new ArrayList<Long>();

			list.add((long) 0);

			List<WsAocStockInfoBean> wsAocStockInfoBeans = sparepartService
					.aocFindStockInfo(wxUser.getLoginName(), list,
							materialName, null, Consts.CLIENT_PHONE);
			
			if (wsAocStockInfoBeans != null) {
				return JSONObject.toJSONString(wsAocStockInfoBeans);
			}
			return "[]";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	
	@RequestMapping("cp/sparepart/sparepartDetail.do")
	public ModelAndView sparepartDetail(HttpServletRequest req,
			HttpServletResponse res){
		ModelAndView modelAndView = new ModelAndView(
				"/sparepart/spareSearchDetail");
		String hasSerialNumber = req.getParameter("hasSerialNumber"); // 是否管控条码：Y-管控；N-不管控
		String storeIdStr = req.getParameter("storeId"); // 是否管控条码：Y-管控；N-不管控
		Long storeId = Long.parseLong(storeIdStr) ;
		logger.info("hasSerialNumber="+hasSerialNumber);
		if (StringUtils.isNotEmpty(hasSerialNumber)
				&& StringUtils.isNotBlank(hasSerialNumber)
				&& hasSerialNumber.equals("Y")) {
			WxUser wxUser = LoginUser.getSessionUser();
			String loginName = wxUser.getLoginName();
			String materialCode = req.getParameter("materialCode");
			List<Long> list = new ArrayList<Long>();
			list.add(storeId);
			List<WsAocSparepartInfoBean> stockList;
			try {
				stockList = service
						.aocFindSparepartInfoList(loginName, list,
								materialCode, Consts.CLIENT_PHONE);
				modelAndView.addObject("stockList", stockList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return modelAndView;
	}
	
	
	@RequestMapping("/cp/ouath/sparepart/applyedPage")
	public ModelAndView applyedSparePage(ModelAndView mv) {
		mv.setViewName("/sparepart/spareApplyedList");
		return mv;
	}

	@ResponseBody
	@RequestMapping("/cp/sparepart/applyedList")
	public String applyedSparepartList(HttpServletRequest req,
			HttpServletResponse res, String pageNum, ModelAndView mv){

		WxUser wxUser = LoginUser.getSessionUser();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "1");
		resultMap.put("errMsg", "系统繁忙！");

		WsPage wspage = new WsPage();
		pageNum = "1";
		wspage.setRows(100);

		WsBorrowDetailBean remand = null;
		try {
			remand = service.findBorrowInfoDetailList(
					wxUser.getLoginName(), wspage,Consts.OPERATE_TYPE_B, Consts.CLIENT_PHONE);
			resultMap.put("status", "0");
			resultMap.put("errMsg", "");
			resultMap.put("data", remand);
			//resultMap.put("loginName", wxUser.getLoginName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("aocFindBorrowInfoDetailListNew出错");
			resultMap.put("status", "1");
			resultMap.put("errMsg", "aocFindBorrowInfoDetailList");
		}
		return JSONObject.toJSONString(resultMap);
	}

	@RequestMapping("/cp/ouath/sparepart/applyingSpareList")
	public ModelAndView applyingSpareList(HttpServletRequest req,
			HttpServletResponse res, ModelAndView mv) {
		mv.setViewName("/sparepart/spareApplyingList");
		return mv;
	}

	@RequestMapping("/cp/sparepart/applyedSpareDetail" )
	public ModelAndView applyedSpareDetail(HttpServletRequest req,
			HttpServletResponse res, ModelAndView mv) throws UnsupportedEncodingException  {
		//req.setCharacterEncoding("utf-8"); 
		String param = req.getParameter("data");
		JSONObject json = JSONObject.parseObject(param) ;
		mv.addObject("borrowId", json.getString("borrowId")) ;
		mv.addObject("materialCode", json.getString("materialCode")) ;
		mv.addObject("materialName", json.getString("materialName")) ;
		mv.addObject("sparepartNum", json.getString("sparepartNum")) ;
		mv.addObject("storeId", json.getString("storeId")) ;
		
		mv.addObject("storeName", json.getString("storeName")) ;
		mv.addObject("borrowName", json.getString("borrowName")) ;
		mv.addObject("serialNumber", json.getString("serialNumber")) ;
		mv.addObject("hasSerial", json.getString("hasSerial")) ;
		mv.addObject("borrowTime", json.getString("borrowTime")) ;
		mv.setViewName("/sparepart/spareApplyedDetail");
		return mv;
	}

	@ResponseBody
	@RequestMapping("/cp/sparepart/spareApply")
	public String applySpare(HttpServletRequest req, HttpServletResponse res) {

		String borrowNum = req.getParameter("borrownum");
		String storeId = req.getParameter("storeId");
		String materialCode = req.getParameter("materialCode");
		String borrowRemark = req.getParameter("borrowRemark");

		logger.info("borrowNum=" + borrowNum + " storeId=" + storeId
				+ "  materialCode=" + materialCode + "    borrowRemark="
				+ borrowRemark);

		res.setContentType("text/html;charset=utf-8");// 设置中文问题

		try {
			WsAocBorrowApplyInfoParam params = new WsAocBorrowApplyInfoParam();
			params.setBorrowNum(Integer.parseInt(borrowNum));
			params.setMaterialCode(materialCode);
			params.setStoreId(Long.parseLong(storeId));
			params.setBorrowRemark(borrowRemark);

			WxUser wxUser = LoginUser.getSessionUser();
			String loginName = wxUser.getLoginName();
			WsResultBean result = service.allBorrowApply(loginName, loginName,
					params,Consts.OPERATE_TYPE_B, Consts.CLIENT_PHONE);
			return result.getReturnResult() + "==" + result.getReturnMessage()
					+ result.getErrorMessage();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@RequestMapping("/cp/ouath/sparepart/approveSparePage")
	public ModelAndView applySpareDetail(ModelAndView mv) {
		mv.setViewName("/sparepart/spareApproveList");
		return mv;
	}

	@ResponseBody
	@RequestMapping("/cp/sparepart/approveSpareList")
	public String approveSpareList(HttpServletRequest req,
			HttpServletResponse res,Integer pageNum) {
		
		WxUser wxUser = LoginUser.getSessionUser();
		String loginName = wxUser.getLoginName();
		WsPage wspage = new WsPage();
		wspage.setRows(Consts.DEFAULT_ROWS);
		wspage.setPage(pageNum);

		WsResultAocReviewBorrowBean result = new WsResultAocReviewBorrowBean();
		try {
			logger.info("获取审批列表参数 "+"loginName="+loginName+"  wspage="+JSON.toJSONString(wspage)+"   operateType="+Consts.OPERATE_TYPE_B+"   clientType="+Consts.CLIENT_PHONE);
			result = service.allFindToReviewBorrowInfoList(loginName, wspage,Consts.OPERATE_TYPE_B,
					Consts.CLIENT_PHONE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(-1);
			result.setErrMsg(WsConsts.SpmSearchEngineUrl
					+ ",aocFindToReviewBorrowInfoList");
		}
		return JSONObject.toJSONString(result);
	}

	@RequestMapping("/cp/sparepart/approveSpareDetail")
	public ModelAndView approveSpareDetail(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView(
				"/sparepart/spareApproveDetail");
		
		String flag = "0" ; //标识该备件是否有条码
		String applyId = request.getParameter("applyId");
		String hasSerialNumber = request.getParameter("hasSerialNumber");
		String borrowNum = request.getParameter("borrowNum");
		String[] serialNumberList = null ;
		String serials = null ;
		if(hasSerialNumber.equalsIgnoreCase("Y")){
			serials = request.getParameter("serialNumbers");
			serialNumberList = serials.split(",") ;
			flag="1";
		}
		if(flag.equals("1")){
			modelAndView.addObject("serialNumberList", serialNumberList);
		}
		modelAndView.addObject("flag", flag);
		modelAndView.addObject("applyId",applyId);
		modelAndView.addObject("borrowNum",borrowNum);
		return modelAndView;
	}

	// 审批备件
	@ResponseBody
	@RequestMapping(value = "/cp/sparepart/approvalSpare")
	public Object approvalSpare(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		WxUser wxUser = LoginUser.getSessionUser();
		String loginName = wxUser.getLoginName();
		String applyId = request.getParameter("applyId");
		String flag = request.getParameter("flag");
		String applyNote = request.getParameter("applyNote");
		String[] strs = null ;
		String serialNumbers = "";
		if(flag.equals("1")){ //有条码
			String serialNumber = request.getParameter("serialNumber");
			String str = serialNumber.trim().replace(" ", "");
			strs = str.split("\n\t\t\n\t\t\t");
			for (int i = 0; i < strs.length; i++) {
				serialNumbers += strs[i] + ",";
			}
		}
		
		try {
			WsResultBean result = service.allBorrowSparepartReview(loginName, applyId, serialNumbers, applyNote,Consts.OPERATE_TYPE_B, Consts.CLIENT_PHONE);
			logger.debug("审批备件----------返回结果集=" + JSON.toJSONString(result) + "\n");
			String returnMessage = result.getReturnMessage()==null?"":result.getReturnMessage();
			JSONObject json = new JSONObject();
			json.put("returnResult", result.getReturnResult());
			json.put("returnMessage", returnMessage);
			json.put("errorMessage", result.getErrorMessage()==null?"接口错误！":result.getErrorMessage());
			return json.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
