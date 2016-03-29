/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhang zhi
 * Date: 2010-4-19 上午11:06:19
 */
package com.grgbanking.css.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统共用常量类
 */
public class CommonConstants {

	/**
	 * '是','否'标识
	 */
	public static final String FLAG_Y = "Y";
	public static final String FLAG_N = "N";
	public static final String FLAG_Y_N = "YN";

	/**
	 * 广电银通部门ID不能变
	 */
	public static final String DEPARTMENT_ID_GRG = "3";

	/**
	 * 广电银通TREECODE，稽查功能中使用 com.grgbanking.soc.service.servicequality.
	 * inspect.impl.InspectPFormServiceImpl.
	 * findPformStationTreeCodeByProjectIdAndTreeCode()
	 */
	public static final String DEPARTMENT_TREECODE_GRG = "001";
	/**
	 * 分子公司目前使用的类型定义 com.grgbanking.soc.service.servicequality.inspect.impl.
	 * InspectProjectServiceImpl.getProjectScheduleByDeptTreeCode()
	 */
	public static final String DEPT_SUBSIDIARIES_TYPE = "B";
	/**
	 * 配件维修出入库（好）（坏）
	 */
	public static final int DEPARTMENT_ID_SPAREPART = 808;// 备件中心id
	public static final int DEPARTMENT_ID_REPAIR = 811;// 总部配件维修组id
	public static final int DEPARTMENT_ID_TECHONLOGY = 24;// 研发中心
	public static final int DEPARTMENT_ID_MANAGEMENT = 800;// 服务管理部
	public static final int DEPARTMENT_ID_MARKETING = 5311;// 服务市场部
	public static final int DEPARTMENT_ID_QUALITY = 801; // 服务质量部
	public static final int DEPARTMENT_ID_REGION_ZHENGZHOU = 11229;// 郑州备件分中心
	public static final int DEPARTMENT_ID_REGION_ZHENGZHOU_REPAIR = 11349;// 郑州备件维修组
	public static final int DEPARTMENT_ID_MANAGE_TECHNOLOGY = 11709;// 运维中心技术部
	public static final int DEPARTMENT_ID_SOUTH_BRANCH = 10;// 华南分公司
	public static final int DEPARTMENT_ID_MANAGE = 20;// 运维管理中心
	public static final int DEPARTMENT_ID_MANAGE_SHIPPED = 11710;// 运维中心运维管理部
	public static final int DEPARTMENT_ID_BRANCH_HUAXI_SOUTH = 13;// 华西分公司（南）
	public static final int DEPARTMENT_ID_BRANCH_HUAXI_NORTH = 12990;// 华西分公司（北）

	/**
	 * 访问资源类型（菜单，功能）
	 */
	public static final String RESOURCE_TYPE_MENU = "MENU";
	public static final String RESOURCE_TYPE_FUNCTION = "FUNCTION";

	/**
	 * 部门类型
	 */
	public static final String DEPT_TYPE_HEAD = "A";// 总部类型

	public static final String DEPT_TYPE_REGION = "B";// 区域类型

	public static final String DEPT_TYPE_SEGMENT = "D";// 片区类型

	public static final String DEPT_TYPE_STATION = "C";// 服务站类型

	public static final String DEPT_TYPE_SUBSIDIARIES = "E";// 子公司类型

	public static final String DEPT_TYPE_CN_STATION = "服务站";
	public static final String DEPT_TYPE_CN_SUPPORT_GROUPS = "技术支持组";
	public static final String DEPT_TYPE_CN_REGIONAL_ASSISTANT = "区域助理";
	public static final String DEPT_TYPE_CN_TECHNOLOGY = "技术部";
	public static final String DEPT_TYPE_CN_SERVICE_MANAGEMENT = "服务管理部";

	/**
	 * 公司行政等级数
	 * */
	public static final Integer DEPT_LEVEL = 4;// 4个等级：总部、区域/总部部门、片区、服务站

	/**
	 * 流水单号KEY
	 */
	public static final String PROCESS_NO_COMPLAIN = "PROCESS_NO_COMPLAIN"; // 投诉单号

	/**
	 * 流水单号KEY
	 */
	public static final String PROCESS_NO_NOTICE = "PROCESS_NO_NOTICE"; // 通告单号

	/**
	 * 流水号KEY
	 */
	public static final String PROCESS_NO_MARKET = "PROCESS_NO_MARKET";

	/**
	 * 流水号KEY
	 */
	public static final String PROCESS_NO_TELEPHONE = "PROCESS_NO_TELEPHONE";// 电话处理单号

	/**
	 * 流水号KEY
	 */
	public static final String PROCESS_NO_WORK_TASK = "PROCESS_NO_WORK_TASK";// 现场服务单号
	/**
	 * 车辆费用报销号KEY
	 */
	public static final String PROCESS_NO_VEHICLE_CLAIM = "PROCESS_NO_VEHICLE_CLAIM";

	/**
	 * 升级计划单号KEY
	 */
	public static final String PROCESS_NO_UPGRADE_PLAN = "PROCESS_NO_UPGRADE_PLAN";
	/**
	 * 保养计划单号KEY
	 */
	public static final String PROCESS_NO_UPGRADE_PLAN_BY = "PROCESS_NO_UPGRADE_PLAN_BY";
	
	/**
	 * 保养计划巡检单号KEY
	 */
	public static final String PROCESS_NO_UPGRADE_PLAN_XJ = "PROCESS_NO_UPGRADE_PLAN_XJ";

	/**
	 * 流水号KEY
	 */
	public static final String PROCESS_NO_DAILY_EVENT = "PROCESS_NO_DAILY_EVENT";// 每日事件

	/**
	 * 备件借用KEY
	 * */

	public static final String PROCESS_NO_SPAREPART_BORROW = "PROCESS_NO_SPAREPART_BORROW";

	/**
	 * 销售号KEY
	 */
	public static final String PROCESS_NO_SPAREPART_SALE = "PROCESS_NO_SPAREPART_SALE";

	/**
	 * 备件调度KEY
	 */
	public static final String PROCESS_NO_ATTEMPER = "PROCESS_NO_ATTEMPER";

	/**
	 * 备件调度KEY（在物流收货的时候，调度单重新生成一个备件中心调度单号）
	 */
	public static final String PROCESS_NO_ATTEMPER_FOR_LOGIS = "PROCESS_NO_ATTEMPER_FOR_LOGIS";

	/**
	 * 入库单号KEY
	 * */
	public static final String RROCESS_NO_WAREHOUSE_ENTRY = "RROCESS_NO_WAREHOUSE_ENTRY";

	/**
	 * 出库单号KEY
	 * */
	public static final String RROCESS_NO_WAREHOUSE_DELIVERY = "RROCESS_NO_WAREHOUSE_DELIVERY";

	/**
	 * 备件维修单号KEY
	 * */
	public static final String PROCESS_NO_SPAREPART_REPAIR = "PROCESS_NO_SPAREPART_REPAIR";

	/**
	 * 物流单号KEY
	 * */
	public static final String PROCESS_NO_SPAREPART_LOGISTICS = "PROCESS_NO_SPAREPART_LOGISTICS";

	/**
	 * 备件调整单号KEY
	 */
	public static final String PROCESS_NO_WAREHOUSE_ADUST = "PROCESS_NO_WAREHOUSE_ADUST";

	/**
	 * 临时采购单号
	 */
	public static final String PROCESS_NO_TEMP_PURCHASE = "PROCESS_NO_TEMP_PURCHASE";

	/**
	 * 备件报废单号
	 */
	public static final String PROCESS_NO_SPAREPART_SCRAP = "PROCESS_NO_SPAREPART_SCRAP";

	/**
	 * 批量备件维修工单号
	 */
	public static final String PROCESS_NO_SWORKFORM = "PROCESS_NO_SWORKFORM";

	public static final String PROCESS_NO_REVISIT = "PROCESS_NO_REVISIT";
	
	/**
	 * 软件试用单号
	 */
	public static final String PROCESS_NO_SOFTWARE_TRIAL = "PROCESS_NO_SOFTWARE_TRIAL";

	/**
	 * 流水号KEY
	 */
	public static final String PROCESS_NO_SERVICE_REPORT = "PROCESS_NO_SERVICE_REPORT";// 服务报告
	
	/**
	 * 流水号KEY
	 */
	public static final String PROCESS_NO_PHOTO_FORM = "PROCESS_NO_PHOTO_FORM";// 照片稽查单

	/**
	 * 流水号KEY 日开机率事件
	 */
	public static final String PROCESS_NO_DAILY_OPENRATE = "PROCESS_NO_DAILY_OPENRATE";// 
	
	
	/**
	 * 流水号KEY 服务管理>>ATM服务管理>>任务监控
	 */
	public static final String PROCESS_NO_MONITOR_CODE = "PROCESS_NO_MONITOR_CODE";
	
	/**
	 * 批量备件维修工单号
	 */
	public static final String PROCESS_NO_SUPPORT = "PROCESS_NO_SUPPORT";
	/**
	 * 中文字符
	 */
	public static final String YES = "是";
	public static final String NO = "否";
	public static final String IS_NULL = "无";
	public static final String MEN = "男";
	public static final String WOMEN = "女";
	public static final String MEN_S = "M";
	public static final String WOMEN_S = "W";

	/**
	 * 服务站主任角色代码
	 */
	public static final String ROLE_STATION_DIRECTOR = "ROLE_STATION_DIRECTOR";

	/**
	 * 工单号KEY
	 */
	public static final String PROCESS_NO_WORKFORM = "PROCESS_NO_WORKFORM";

	public static final String DAILY_EVENT = "DAILY_EVENT"; // 每日事件提醒

	/**
	 * 每日提醒时间，角色关系常量
	 * */
	public static Map<String, String> getRemind() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(CommonConstants.DAILY_EVENT,
				"ROLE_STATION_PURCHASE,ROLE_ENGINEER,ROLE_STATION_DIRECTOR,ROLE_STATION_STOREKEEPER,ROLE_STATION_SPARE PART_REPAIRER");
		return map;
	}
	public static final String SPM_CLIENT_TYPE_CSS = "CSS";

	public static final String SPM_CLIENT_RETURN_N = "N";// 调用返回值：N-物料1不可替换物料2
	public static final String SPM_CLIENT_RETURN_Y = "Y";

	public static final String SPM_CLIENT_USE_A = "A";// 调用返回值：使用方式：A-只能消耗
	public static final String SPM_CLIENT_USE_B = "B";// 替换
	public static final String SPM_CLIENT_USE_C = "C";// 调用返回值：使用方式：C-又能消耗也能替换

	public static final String SPM_CLIENT_RESULT_MESSAGE = "调用备件系统异常";

	/**
	 * 服务报告需求的品牌
	 */
	public static final String SERVICE_REPORT_BRAND = "SERVICE_REPORT_BRAND";
	public static final String SERVICE_REPORT_STATUS = "SERVICE_REPORT_STATUS";
	public static final String SERVICE_REPORT_STATUS_2 = "SERVICE_REPORT_STATUS_2";
	public static final String SERVICE_REPORT_SRCTYPE_1 = "1"; //市流程
	public static final String SERVICE_REPORT_SRCTYPE_2 = "2"; //省流程
	/**
	 * 服务报告需求状态
	 */
	public static final String SERVICE_REPORT_STATUS_0 = "0";
	public static final String SERVICE_REPORT_STATUS_5 = "5";
	public static final String SERVICE_REPORT_STATUS_10 = "10";
	public static final String SERVICE_REPORT_STATUS_15 = "15";
	public static final String SERVICE_REPORT_STATUS_20 = "20";
	public static final String SERVICE_REPORT_STATUS_25 = "25";
	public static final String SERVICE_REPORT_STATUS_30 = "30";
	public static final String SERVICE_REPORT_STATUS_35 = "35";
	public static final String SERVICE_REPORT_STATUS_40 = "40";

	public static final String STRING_SOLIDUS = "/";
	
	/**清分机设备类型*/
	public static final String QINGFENG_EQUIPMENT_TYPE = "AB00";

	
	/**
	 * 短信功能开启的服务站开关
	 */
	public static final String SMS_START_UP_CONFIG = "SMS_START_UP_CONFIG";
	
	public static final String SERVICE_TASK_CONTACT_TYPE = "SERVICE_TASK_CONTACT_TYPE";

 
	/**
	 * 升级计划管理
	 */
	
	private static Map<String, String> map;
	public static String getUpgradeIssueCause(String key){
		return map.get(key);
	}
	static{
		map = new HashMap<String, String>();
		map.put("1", "问题维护");
		map.put("2", "客户需求");
		
	}
	// 报修人
	public static final String SERVICE_TASK_CONTACT_TYPE_REPAIRS = "1";
	// 接待人
	public static final String SERVICE_TASK_CONTACT_TYPE_RECEIVE = "2";
	// 
	
	//预警项目
	public static final String WORK_SERVICE_WARNING_TYPE="WORK_SERVICE_WARNING_TYPE";
	public static final String WORK_SERVICE_WARNING_TYPE_1="1";//响应时效
	public static final String WORK_SERVICE_WARNING_TYPE_2="2";//满意度
	public static final String WORK_SERVICE_WARNING_TYPE_3="3";//到达坐标
	public static final String WORK_SERVICE_WARNING_TYPE_4="4";//完成坐标
	
	//预警项目满意度
	public static final String WORK_SERVICE_SATISFACTION="WORK_SERVICE_SATISFACTION";
    public static final String WORK_SERVICE_SATISFACTION_1="1";	
    public static final String WORK_SERVICE_SATISFACTION_2="2";	
    public static final String WORK_SERVICE_SATISFACTION_3="3";	
    
    //不满意短信评价回访问题ID
    public static final String WORK_SERVICE_QUESTIONNAIRE_ID="WORK_SERVICE_QUESTIONNAIRE_ID";
    
    public static final String WORK_SERVICE_QUESTIONNAIRE_USERCODE="G0210451";
    public static final String WORK_SERVICE_QUESTIONNAIRE_USERNAME="傅道谊";
    public static final Integer WORK_SERVICE_QUESTIONNAIRE_PANDID=46761;
    
    //回访类型
    public static final String REVISIT_TYPE="REVISIT_TYPE";
    public static final String REVISIT_TYPE_4="4";//短信
    public static final String REVISIT_TYPE_1="1";//工單
    
    public static final String  WORK_SERVICE_SATISFACTION_TYPE = " WORK_SERVICE_SATISFACTION_TYPE";

    public static final String EQUIPMENT_CONTACT_POSITION_1 = "专员";
    
    /**
     * 每日事件帐务类处理状态
     */
    public static final String ZW_SERVICES_EVENT_STATUS="ZW_SERVICES_EVENT_STATUS";

    /**
    * 事件风险评估
    */
	public final static String SERVICE_EVENT_ALARM_RISK="SERVICE_EVENT_ALARM_RISK";
	 
	
	public final static String SERVICES_MONITOR_PROCESS_TYPE_XCFW="现场服务";
	
	/**
	 * 数据库前缀
	 */
	//public final static String prefix = CssConfigUtil.getProperty("jdbc.prefix");
}
