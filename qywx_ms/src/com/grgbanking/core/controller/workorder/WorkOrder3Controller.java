package com.grgbanking.core.controller.workorder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.grgbanking.common.utils.DateUtils;
import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.core.controller.BaseController;
import com.grgbanking.core.entity.ResultJson;
import com.grgbanking.core.entity.equipment.WsDeviceLocationBean;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.entity.workorder.WorkFormWarning;
import com.grgbanking.core.entity.ws.WsResultUploadWorkFormTimeBean;
import com.grgbanking.core.service.equipment.WeiXinImagesService;
import com.grgbanking.core.service.user.OrderService;
import com.grgbanking.core.service.workorder.WorkFormEngineService;
import com.grgbanking.core.service.workorder.WorkOrderService;

/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：qywx_ms_pro   
 *
 * 类描述：
 * 类名称：com.grgbanking.core.controller.workorder.WorkOrder3Controller     
 * 创建人：yt
 * 创建时间：2015年12月25日 上午11:47:35   
 * 修改人：
 * 修改时间：2015年12月25日 上午11:47:35   
 * 修改备注：   
 * @version   V1.0
 */
@Controller
public class WorkOrder3Controller extends BaseController {
    
    //工单接口
    @Resource(name = "workOrderService")
    private WorkOrderService workOrderService;
    
    @Resource(name = "workFormEngineService")
    private WorkFormEngineService workFormEngineService;

    // 本地工单服务接口
    @Resource(name = "orderService")
    private OrderService orderService;

    @Resource(name = "weiXinImagesService")
    private WeiXinImagesService imagesService;

    /**
     * 
     * @Title: uploadTime
     * @Description: TODO(时间上传，接受、出发、到达、完成等)
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/uploadTimeNew")
    public String uploadTimeTodo(HttpServletRequest request) {
        String latlonStr = request.getParameter("latlonStr");
        String installAddress = request.getParameter("installAddress");
        String workformId = request.getParameter("workformId");
        String equipmentId = request.getParameter("equipmentId");
        String timeType = request.getParameter("timeType");
        //设备经纬度
        String equipmentLatitude = request.getParameter("equipmentLatitude");
        String equipmentLongitude = request.getParameter("equipmentLongitude");
        
        WsDeviceLocationBean GPSInfo = new WsDeviceLocationBean("-1", "-1", "无");
        //操作结果（默认失败）
        JSONObject data = new JSONObject();
        ResultJson<JSONObject> jsonData = new ResultJson<JSONObject>(0, "", null);
        
        String longitude = "";
        String latitude = "";
        if (StringUtils.isNotEmpty(latlonStr)) {
            String[] latlonArray = latlonStr.split(",");
            latitude = latlonArray[0];
            longitude = latlonArray[1];
            GPSInfo.setLatitude(latitude);
            GPSInfo.setLongitude(longitude);
            
            //使用百度api，通过经纬度获取地址信息
            String param1 = "ak=eFfE8s23g6MlbiUBX7z2utg7&coords="+longitude+","+latitude;
            String url1 = "http://api.map.baidu.com/geoconv/v1/";
            String result1 = sendGetLocataion(url1, param1);
            if(StringUtils.isNotBlank(result1)){
                JSONObject o1 = JSON.parseObject(result1);
                com.alibaba.fastjson.JSONArray a1 = o1.getJSONArray("result");
                String x= "";
                String y = "";
                if(a1 != null && a1.size() > 0){
                    x = a1.getJSONObject(0).getString("x");
                    y = a1.getJSONObject(0).getString("y");
                    //转化为百度经纬度坐标
                    GPSInfo.setLatitude(x);
                    GPSInfo.setLongitude(y);
                    String url2 = "http://api.map.baidu.com/geocoder/v2/";
                    String param2 = "ak=eFfE8s23g6MlbiUBX7z2utg7&location="+y+","+x+"&output=json&pois=1&qq-pf-to=pcqq.temporaryc2c";
                    String result2 = sendGetLocataion(url2, param2);
                    if(!result2.equals("")){
                        JSONObject o2 =JSON.parseObject(result2).getJSONObject("result");
                        if(o2 != null){
                            installAddress = o2.getString("formatted_address");
                            if(StringUtils.isNotBlank(o2.getString("sematic_description"))){
                                installAddress += o2.getString("sematic_description");
                            }
                        }
                    }
                }
                
            }
        }
        if (StringUtils.isNotEmpty(installAddress)) {
            GPSInfo.setLocation(installAddress);
        }
        // 判断时间间隔
        if ("4".equals(timeType)) {// 完成时间类型为4
            String startTime = request.getParameter("startWorkTime");
            if (DateUtils.timeDifference(startTime) < 5) {
                jsonData = new ResultJson<JSONObject>(0, "请五分钟之后再操作", null);
                return JSON.toJSONString(jsonData);
            }
        }
        
        //调用预警接口返回结果
        String warnResult = "";
        //创建时间
        Date createDate = new Date();
        
        //到达
        if("2".equals(timeType)){
            /**
             * 操作预警
             */
            if (equipmentId != null && !equipmentId.equals("")) {
                Double long1 = Double.valueOf(longitude);// 经度
                Double lat1 = Double.valueOf(latitude);// 纬度
                Double long2 = -1.0;
                Double lat2 = -1.0;
                logger.info("操作预警-----工单设备ID=" + equipmentId);
                
                //如果设备经纬度为空，获取当前经纬度，如果当前经纬度获取失败（-1.0，-1.0）不进行修改设备经纬度操作
                //EquipmentInfo equipmentInfo = equipmentInfoMapper.selectByEquipmentId(equipmentId);
                if (equipmentLatitude != null && !equipmentLatitude.equals("") && equipmentLongitude != null && !equipmentLongitude.equals("")) {
                    long2 = Double.parseDouble(equipmentLongitude);// 经度
                    lat2 = Double.parseDouble(equipmentLatitude);// 纬度
                } else if (!longitude.equals("-1") && !latitude.equals("-1")) {
                    long2 = long1;// 经度
                    lat2 = lat1;// 纬度
                }

                Double distance = getDistance(long1, lat1, long2, lat2);// 到达坐标+等级+距离设备  公里
                logger.info("distance:"+distance);
                if (distance == 0.0) {
                    data.put("warn", "暂无设备经纬度，当前经纬度获取失败");
                } else if (1 <= distance && distance < 2) {// 1级
                    data.put("warn", "您当前的坐标与设备偏离超过" + distance + "公里，请确认是否在设备旁操作");
                    WorkFormWarning workFormWarning = new WorkFormWarning();
                    workFormWarning.setWorkformId(Long.valueOf(workformId));
                    workFormWarning.setWarningType("3");
                    workFormWarning.setSatisfactionType("1");
                    workFormWarning.setCreateDate(createDate);
                    workFormWarning.setWarningRecode("到达坐标:" + "1等级" + ",距离设备" + distance + "公里");
                    warnResult = imagesService.doSaveServicesWorkformWarning(JSON.toJSONString(workFormWarning));
                    logger.info(workFormWarning.getWarningRecode());
                } else if (2 <= distance && distance <= 4) {// 2级
                    data.put("warn", "您当前的坐标与设备偏离超过" + distance + "公里，请确认是否在设备旁操作");
                    WorkFormWarning workFormWarning = new WorkFormWarning();
                    workFormWarning.setWorkformId(Long.valueOf(workformId));
                    workFormWarning.setWarningType("3");
                    workFormWarning.setSatisfactionType("2");
                    workFormWarning.setCreateDate(createDate);
                    workFormWarning.setWarningRecode("到达坐标:" + "2等级" + ",距离设备" + distance + "公里");
                    warnResult = imagesService.doSaveServicesWorkformWarning(JSON.toJSONString(workFormWarning));
                } else if (distance > 4) {// 3级
                    data.put("warn", "您当前的坐标与设备偏离超过" + distance + "公里，请确认是否在设备旁操作");
                    WorkFormWarning workFormWarning = new WorkFormWarning();
                    workFormWarning.setWorkformId(Long.valueOf(workformId));
                    workFormWarning.setWarningType("3");
                    workFormWarning.setSatisfactionType("3");
                    workFormWarning.setCreateDate(createDate);
                    workFormWarning.setWarningRecode("到达坐标:" + "3等级" + ",距离设备" + distance + "公里");
                    warnResult = imagesService.doSaveServicesWorkformWarning(JSON.toJSONString(workFormWarning));
                } else {
                    //无预警信息
                    jsonData.setErrcode(1);
                }
            }
        }
        //完成
        if("4".equals(timeType)){
            /**
             * 操作预警
             */
            if (equipmentId != null && !equipmentId.equals("")) {
                Double long1 = Double.valueOf(longitude);// 经度
                Double lat1 = Double.valueOf(latitude);// 纬度
                Double long2 = -1.0;
                Double lat2 = -1.0;

                // /如果设备经纬度为空，获取当前经纬度，如果当前经纬度获取失败（-1.0，-1.0）不进行修改设备经纬度操作
                if (equipmentLatitude != null && !equipmentLatitude.equals("") && equipmentLongitude != null && !equipmentLongitude.equals("")) {
                    long2 = Double.parseDouble(equipmentLongitude);// 经度
                    lat2 = Double.parseDouble(equipmentLatitude);// 纬度
                } else {

                    if (!longitude.equals("-1") && !latitude.equals("-1")) {
                        long2 = long1;// 经度
                        lat2 = lat1;// 纬度
                    }
                }

                Double distance = getDistance(long1, lat1, long2, lat2);// 到达坐标+等级+距离设备公里
                if (distance == 0.0) {
                    data.put("warn", "暂无设备经纬度，当前经纬度获取失败");
                } else if (1 <= distance && distance < 2) {// 1级
                    data.put("warn", "您当前的坐标与设备偏离超过" + distance + "公里，请确认是否在设备旁操作");
                    WorkFormWarning workFormWarning = new WorkFormWarning();
                    workFormWarning.setWorkformId(Long.valueOf(workformId));
                    workFormWarning.setWarningType("4");
                    workFormWarning.setSatisfactionType("1");
                    workFormWarning.setCreateDate(createDate);
                    workFormWarning.setWarningRecode("完成坐标:" + "1等级" + ",距离设备" + distance + "公里");
                    warnResult = imagesService.doSaveServicesWorkformWarning(JSON.toJSONString(workFormWarning));
                } else if (2 <= distance && distance <= 4) {// 2级
                    data.put("warn", "您当前的坐标与设备偏离超过" + distance + "公里，请确认是否在设备旁操作");
                    WorkFormWarning workFormWarning = new WorkFormWarning();
                    workFormWarning.setWorkformId(Long.valueOf(workformId));
                    workFormWarning.setWarningType("4");
                    workFormWarning.setSatisfactionType("2");
                    workFormWarning.setCreateDate(createDate);
                    workFormWarning.setWarningRecode("完成坐标:" + "2等级" + ",距离设备" + distance + "公里");
                    warnResult = imagesService.doSaveServicesWorkformWarning(JSON.toJSONString(workFormWarning));
                } else if (distance > 4) {// 3级.
                    data.put("warn", "您当前的坐标与设备偏离超过" + distance + "公里，请确认是否在设备旁操作");
                    WorkFormWarning workFormWarning = new WorkFormWarning();
                    workFormWarning.setWorkformId(Long.valueOf(workformId));
                    workFormWarning.setWarningType("4");
                    workFormWarning.setSatisfactionType("3");
                    workFormWarning.setCreateDate(createDate);
                    workFormWarning.setWarningRecode("完成坐标:" + "3等级" + ",距离设备" + distance + "公里");
                    warnResult = imagesService.doSaveServicesWorkformWarning(JSON.toJSONString(workFormWarning));
                } else {
                    //无预警信息
                    jsonData.setErrcode(1);
                }
            }
        }

        WsResultUploadWorkFormTimeBean result = null;
        try {
            WxUser wxUser = LoginUser.getSessionUser();
            startTime = new Date();
            result = this.workOrderService.doUploadWorkFormTimeGPS(wxUser.getUserCode(), workformId, GPSInfo, timeType, 1L);
            logger.warn("{}调用上传时间接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
            data.put("time", result.getTime());
            jsonData.setErrcode(result.getStatus());
            jsonData.setErrmsg(result.getErrMsg());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用上传时间接口出错:" + e.toString());
            jsonData.setErrmsg("上传时间失败："+e.toString());
        }
        jsonData.setData(data);
        return JSON.toJSONString(jsonData);
    }
    
    private double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = lat1 * Math.PI / 180;
        double radLat2 = lat2 * Math.PI / 180;
        double a = radLat1 - radLat2;
        double b = lng1 * Math.PI / 180 - lng2 * Math.PI / 180;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;

        return s/1000;
    }
    
    //发送HTTP请求获取数据
    public String sendGetLocataion(String url, String param) {
        
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求百度API出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
