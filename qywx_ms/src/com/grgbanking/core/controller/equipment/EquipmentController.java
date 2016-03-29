package com.grgbanking.core.controller.equipment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.common.utils.Wechat;
import com.grgbanking.core.controller.BaseController;
import com.grgbanking.core.entity.equipment.CssPhoneImageBean;
import com.grgbanking.core.entity.equipment.WsEquipmentConfigBean;
import com.grgbanking.core.entity.equipment.WsEquipmentHistoryProblemBean;
import com.grgbanking.core.entity.equipment.WsEquipmentImageBean;
import com.grgbanking.core.entity.equipment.WsEquipmentInfoBean;
import com.grgbanking.core.entity.equipment.WsEquipmentInfoDetailBean;
import com.grgbanking.core.entity.equipment.WsEquipmentInfoSaveBean;
import com.grgbanking.core.entity.equipment.WsHistoryServerInfoBean;
import com.grgbanking.core.entity.equipment.WsResultEquipmentBean;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultEquipmentConfigBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentDetailBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryProblemBean;
import com.grgbanking.core.entity.ws.WsResultEquipmentHistoryServerBean;
import com.grgbanking.core.service.equipment.WebEquipmentService;
import com.grgbanking.core.service.equipment.WeiXinImagesService;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.dao.CssUserDAO;
import com.grgbanking.webservice.bean.WsResultBean;

/**
 * Copyright (c) 2013 GRG Banking Equipment Co.,Ltd.
 * 
 * @createDate 2013-11-03
 * @author yjie
 * @description 设备管理类
 */

@Controller
public class EquipmentController extends BaseController {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private WebEquipmentService equipmentService;

	@Resource(name = "weiXinImagesService")
	private WeiXinImagesService imagesService;
    
    @Resource(name = "userDAO")
    private CssUserDAO<CssUser,Integer> userDAO;

	/**
	 * 跳转到设备列表页面
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/cp/ouath/equipment/list")
	public String list() {
		return "/equipment/equipmentList";
	}

	/**
	 * 设备列表 branchName(网点名称),installAddress(安装地址)
	 * 
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/cp/equipment/pagelist")
	public List<WsEquipmentInfoBean> pageList(HttpServletResponse response,
			String pageNum, String serialNumber, String customerName,
			String branchName, String installAddress, String ATMNumber) {
		WxUser wxUser = LoginUser.getSessionUser();
		response.setContentType("text/html;charset=utf-8");// 设置中文问题
		WsPage page = new WsPage(5, pageNum);
		List<WsEquipmentInfoBean> equipmentlist = new ArrayList<WsEquipmentInfoBean>();
		try {
			logger.debug("设备列表 页面，接口名称finddevices，参数:loginName="
					+ wxUser.getLoginName() + ",serialNumber=" + serialNumber
					+ ",customerName=" + customerName + ",branchName="
					+ branchName + ",installAddress=" + installAddress
					+ ",ATMNumber=" + ATMNumber + ",page=" + page);
			WsResultEquipmentBean result = equipmentService.finddevices(
					wxUser.getLoginName(), serialNumber, customerName,
					branchName, installAddress, ATMNumber, page);
			logger.debug("返回结果集=" + JSON.toJSONString(result) + "\n");
			if (null != result) {
				equipmentlist = result.getEquipmentInfo();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("设备查询列表出错：" + ex.getMessage());
		}
		return equipmentlist;
	}

	/**
	 * 设备详情 页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cp/equipment/detail")
	public ModelAndView Detail(String serialNumber) {
		ModelAndView modelAndView = new ModelAndView(
				"/equipment/equipmentDetail");
		try {
			// 获取当前人会话，拿到用户姓名和登录名
			WxUser wxUser = LoginUser.getSessionUser();
			WsResultEquipmentDetailBean result = equipmentService
					.getDeviceDetail(wxUser.getLoginName(), serialNumber);
			logger.debug("设备详情 页面,接口名称getDeviceDetail,参数:loginName="
					+ wxUser.getLoginName() + ",serialNumber=" + serialNumber);
			logger.debug("返回结果集=" + JSON.toJSONString(result) + "\n");
			if (result.getStatus() == 1) {

				WsEquipmentInfoDetailBean wsbean = result
						.getWsEquipmentInfoDetailBean();
				modelAndView.addObject("bean", wsbean);
				// 历史服务信息
				modelAndView.addObject("historysercount",
						getListSize(wsbean.getHistoryServerInfo()));
				// 遗留问题
				modelAndView.addObject("problemlistsize",
						getListSize(wsbean.geteHistoryProblem()));
				// 设备序列号
				modelAndView.addObject("serialNumber", serialNumber);
				// GPS地理位置
				modelAndView.addObject("gps",
						(wsbean.getDeviceLocation() != null ? 1 : 0));
				modelAndView.addObject("gpsbean", wsbean.getDeviceLocation());

			} else {
				logger.error("设备详情查询出错：" + result.getErrMsg());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("设备详情查询出错：" + ex.getMessage());
		}
		return modelAndView;
	}

	// 保存设备配置详细信息
	@ResponseBody
	@RequestMapping(value = "/cp/equipment/doSaveEquipment")
	public Object doSaveEquipment(HttpServletResponse response,
			WsEquipmentInfoSaveBean wsEquipment) {
		response.setContentType("text/html;charset=utf-8");// 设置中文问题
		response.setHeader("Content-type", "application/json");
		WxUser wxUser = LoginUser.getSessionUser();
		WsResultBean results = null;
		try {
			if (wxUser != null) {
				wsEquipment.setUserId(wxUser.getId()); // 设置用户ID
				wsEquipment.setUserName(wxUser.getName()); // 设置用户名
			}
			results = equipmentService.doUpdateEquipmentInfo(
					wxUser.getLoginName(), wsEquipment, "PHONE");
			logger.debug("保存设备配置详细信息页面 接口名称doUpdateEquipmentInfo 返回结果集："
					+ JSON.toJSONString(results));
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("调用 保存设备配置详细信息页面 接口名称doUpdateEquipmentInfo 时报错:"
					+ ex.getMessage());
			results = new WsResultBean();
			results.setReturnResult(false);
			results.setErrorMessage("保存设备配置信息" + ex.getMessage());
		}
		return JSONObject.fromObject(results);
	}

	/**
	 * 设备配置信息
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/cp/equipment/config")
	public String showconfig(HttpServletRequest request, String serialNumber) {
		// 获取当前人会话，拿到用户姓名和登录名
		WxUser wxUser = LoginUser.getSessionUser();
		try {
			// 设备配置信息
			logger.debug("设备配置信息 页面,接口名称getDeviceSparepartsInfo,参数:loginName="
					+ wxUser.getLoginName() + ",serialNumber=" + serialNumber);
			WsResultEquipmentConfigBean result = equipmentService
					.getDeviceSparepartsInfo(wxUser.getLoginName(),
							serialNumber);
			logger.debug("返回结果集=" + JSON.toJSONString(result) + "\n");
			if (result.getStatus() == 1) {
				List<WsEquipmentConfigBean> sparepartsInfo = result
						.getSparepartsInfo();
				request.setAttribute("configlist", sparepartsInfo);
			} else {
				logger.error("设备配置信息查询出错：" + result.getErrMsg());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("设备配置信息查询出错：" + ex.getMessage());
		}
		return "/equipment/equipmentconfig";
	}

	/**
	 * 历史信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cp/equipment/historyServer")
	public String historyServer(HttpServletRequest request, String serialNumber) {
		try {
			logger.debug("历史信息 页面,接口名称getDeviceHistoryServer,参数serialNumber="
					+ serialNumber);
			WsResultEquipmentHistoryServerBean result = equipmentService
					.getDeviceHistoryServer(serialNumber);
			logger.debug("返回结果集=" + JSON.toJSONString(result) + "\n");
			if (result.getStatus().equals(1)) {
				List<WsHistoryServerInfoBean> beanlist = result
						.getHistoryServerInfo();
				request.setAttribute("beanlist", beanlist);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("设备历史信息查询出错：" + ex.getMessage());
		}
		return "/equipment/history";
	}

	/**
	 * 遗留问题
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cp/equipment/historyproblem")
	public String problem(HttpServletRequest request, String serialNumber) {
		try {
			logger.debug("设备遗留问题 页面,接口名称getDeviceHistoryProblem,参数:serialNumber="
					+ serialNumber);
			WsResultEquipmentHistoryProblemBean bean = equipmentService
					.getDeviceHistoryProblem(serialNumber);
			logger.debug("返回结果集=" + JSON.toJSONString(bean) + "\n");
			if (bean.getStatus().equals(1)) {
				List<WsEquipmentHistoryProblemBean> lists = bean
						.geteHistoryProblem();
				request.setAttribute("problemlist", lists);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("设备遗留问题查询出错：" + ex.getMessage());
		}
		return "/equipment/historyProblem";
	}

	/**
	 * 上传图片
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/cp/equipment/upload")
	public String upload(HttpServletRequest request,
			HttpServletResponse response, String serialNumber)
			throws IOException {
		String op = request.getParameter("op");
		String equipmentId = request.getParameter("equipmentId");
		if ("save".equals(op)) {
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html;charset=utf-8");// 设置中文问题
			String result = "";
			// 获取当前人会话，拿到用户姓名和登录名
			WxUser wxUser = LoginUser.getSessionUser();
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			// String address = request.getParameter("address");
			String mediaId = request.getParameter("mediaId");
			String[] mIds = mediaId.split(",");
			WxCpService wxCpService = Wechat.getInstance().getWxCpService();
			List<CssPhoneImageBean> beanList = new ArrayList<CssPhoneImageBean>();
			List<byte[]> fileList = new ArrayList<byte[]>();
			List<String> urlList = new ArrayList<String>();
			FileInputStream input = null;
			try {
			    Integer cssUserId = 0;
                CssUser cssUser = userDAO.getUserByUserCode(wxUser.getUserCode());
                if(cssUser !=null){
                    cssUserId = cssUser.getUserId();
                }
				for (int i = 0; null != mIds && i < mIds.length; i++) {
					File file = wxCpService.mediaDownload(mIds[i]);
					if (null != file) {
						String path = downLoadFrom(file);
						urlList.add(path);
						CssPhoneImageBean imageBean = new CssPhoneImageBean();
						imageBean.setEquipmentId(Integer.valueOf(equipmentId));
						imageBean.setImagesUrl(path);
						imageBean.setLongitude(longitude);
						imageBean.setLatitude(latitude);
						imageBean.setCreateDate(new Date());
						imageBean.setUserId(cssUserId);
						imageBean.setImagesType(2);
						beanList.add(imageBean);
						input = new FileInputStream(file);
						int length = input.available();
						byte[] bt = new byte[length + 1024];
						int count = 0;
						int Len = 1024;
						int off = 0;
						while ((count = input.read(bt, off, Len)) != -1) {
							off = off + count;
						}
						byte[] buff = new byte[off];
						System.arraycopy(bt, 0, buff, 0, off);
						fileList.add(buff);
					}
				}
			} catch (WxErrorException e) {
				e.printStackTrace();
				result = "{\"returnResult\":\"false\",\"msg\":\"上传照片失败！\"}";
			} finally {
				input.close();
			}
			WsEquipmentImageBean eib = new WsEquipmentImageBean();
			eib.setPhoneImageBeanList(beanList);
			eib.setFiles(fileList);
			result = imagesService.doCreateEquipmentImages(eib);
			JSONObject json = JSONObject.fromObject(result);
			json.put("imagUrl", urlList);
			response.setHeader("Content-type", "application/json");
			pw.print(json.toString());
			pw.flush();
			pw.close();
			return null;
		}
		WsEquipmentImageBean images = imagesService
				.queryEquipmentImages(serialNumber);
		request.setAttribute("images", images);
		request.setAttribute("rootUrl", WsConsts.AttachmentRootUrl);
		request.setAttribute("serialNumber", serialNumber);
		request.setAttribute("equipmentId", equipmentId);
		return "/equipment/upload";
	}

	/**
	 * 删除图片
	 */
	@ResponseBody
	@RequestMapping(value = "/cp/equipment/deleteImage")
	public Object deleteAttachment(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String result = imagesService.getPhoneImage(id);
		JSONObject json = JSONObject.fromObject(result);
		if ("true".equals(json.getString("returnResult"))) {
			String path = json.getString("imagesUrl");
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
			result = imagesService.deleteEquipmentImages(id);
			json = JSONObject.fromObject(result);
			json.put("imageUrl", path);
		}
		return json;
	}

	/**
	 * 输出图片
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	/*
	 * @RequestMapping(value = "/cp/equipment/image") String
	 * image(HttpServletRequest request, HttpServletResponse response) throws
	 * IOException { String id = request.getParameter("id"); byte[] buf =
	 * imagesService.getPhoneImage(id); if (null == buf) { return null; } int
	 * cacheAge = 7 * 24 * 3600; // 一周 // 缓存 if
	 * (!ServletUtils.cacheControl(request, response, cacheAge, 0)) { }
	 * response.addIntHeader("Content-Length", buf.length);
	 * response.addHeader("Content-Type", "image/sb"); try { OutputStream out =
	 * response.getOutputStream(); out.write(buf); } finally { } return null; }
	 */

	public String downLoadFrom(File file) {
		WxUser wxUser = LoginUser.getSessionUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		String dateStr = sdf.format(date);
		String path = "/upload/qywx_ms/equipment" + File.separator + dateStr;
		try {
			File fileDir = new File(path);
			// 判断路径是否存在
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			// 定义文件输出流
			FileOutputStream fos = null;
			// 定义输入流，用于接受 URL连接获取的输入流
			InputStream input = null;
			// 遍历传进来的URL列表，批量下载文件
			if (null != file) {
				// 要下载的文件后缀
				String contentType = new MimetypesFileTypeMap()
						.getContentType(file);
				String imgType = ".jpg";
				if ("image/jpeg".contains(contentType)) {
					imgType = ".jpg";
				} else if ("image/png".contains(contentType)) {
					imgType = ".png";
				} else if ("image/gif".contains(contentType)) {
					imgType = ".gif";
				} else if ("image/bmp".contains(contentType)) {
					imgType = ".bmp";
				}
				path = path + File.separator + wxUser.getId() + "-"
						+ new Date().getTime() + imgType;
				// 用于保存到数据库的附件信息
				fos = new FileOutputStream(path);
				long length = file.length();
				if (length > 0) {
					input = new FileInputStream(file);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = input.read(buffer)) > 0) // 循环读取文件到输出流
					{
						fos.write(buffer, 0, len); // 使用输出流输出文件。
					}
				}
			}
			if (fos != null) {
				fos.flush();
				fos.close();
			}
			if (input != null) {
				input.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("图片保存本地服务器失败！");
		}
		return path;
	}
}
