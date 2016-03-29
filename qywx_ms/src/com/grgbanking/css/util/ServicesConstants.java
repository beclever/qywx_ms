package com.grgbanking.css.util;

/**
 * 服务管理相关常量定义
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-7-28 下午05:27:23
 */
public class ServicesConstants {
	/**
	 * 服务类型
	 */
	public final static String TASK_TYPE_REPAIR="WX";//维修
	public final static String TASK_TYPE_UPGRADE="SJ";//升级
	public final static String TASK_TYPE_MAINTAIN="BY";//保养
	public final static String TASK_TYPE_INSTALL_FZ="AZFZ";//安装辅助
	public final static String TASK_TYPE_INSTALL_KT="AZKT";//安装开通
	public final static String TASK_TYPE_MOVE_FZ="YJFZ";//移机辅助
	public final static String TASK_TYPE_MOVE_KT="YJKT";//移机开通
	public final static String TASK_TYPE_INSPECT="XJ";//巡检
	public final static String TASK_TYPE_ACCOUNT="ZW";//帐务处理
	public final static String TASK_TYPE_TRAINNING="PX";//客户培训
	public final static String TASK_TYPE_ASSISTANT="PH";//配合辅助
	public final static String TASK_TYPE_OTHER="QT";//其它
	
	/**
	 * 升级计划状态
	 */
	public static final String UPGRADE_PLAN_STATUS_NO_PUBLISH = "未发布";   //未发布
	public static final String UPGRADE_PLAN_STATUS_ING = "已发布";    //已发布
	public static final String UPGRADE_PLAN_STATUS_CANCEL = "已取消";    //已取消
	public static final String UPGRADE_PLAN_STATUS_COMP = "已完成";    //已发布
	public static final String UPGRADE_PLAN_STATUS_DNCOMP = "未完成";    //已取消
	public static final String UPGRADE_PLAN_STATUS_VALIDATION = "已验证";		//
	
	/**
	 * 电话处理单状态 
	 */
	public final static String STATUS_TEL_PROCESS_FINISHED="已完成";
	public final static String STATUS_TEL_PROCESS_FORWARD="转任务";
	
	/**
	 * 现场服务状态 (任务)
	 */
	public final static String STATUS_TASK_PROCESS_UNALLOCATED="未分配";
	public final static String STATUS_TASK_PROCESS_PENDING="待处理";
	public final static String STATUS_TASK_PROCESS_TREATMENT="处理中";
	public final static String STATUS_TASK_PROCESS_AUDIT="审核中";
	public final static String STATUS_TASK_PROCESS_COMPLETED="已完成";
	public final static String STATUS_TASK_PROCESS_CANCEL="已取消";
	
	/**
	 * 工单状态
	 */
	public final static String STATUS_WORKFORM_INPROCESS="处理中";
	public final static String STATUS_WORKFORM_DIRECTOR="主任审核";
	public final static String STATUS_WORKFORM_STOCK="库管员审核";
	public final static String STATUS_WORKFORM_BACK="已退回";
	public final static String STATUS_WORKFORM_COMPLETED="已完成";
	
	/**
	 * 备件批量维修工单
	 */
	public final static String STATUS_SWORKFORM_REGION="区域审核";
	public final static String STATUS_SWORKFORM_DIRECTOR="主任审核";
	public final static String STATUS_SWORKFORM_BACK="已退回";
	public final static String STATUS_SWORKFORM_UNCOMMIT="未提交";
	public final static String STATUS_SWORKFORM_COMPLETED="已完成";
	
	/**
	 * 现场服务 _任务来源
	 */
	public final static String SOURCE_TASK_CUSTOMER_REPAIR="客户报修";
	public final static String SOURCE_TASK_CUSTOMER_REPAIR_WX="微信报修";
	public final static String SOURCE_TASK_SCHEDULED_TASKS="计划任务";
	public final static String SOURCE_TASK_MONITORING_CENTER="监控中心";
	public final static String SOURCE_TASK_AUTO_UPGRADE="自动升级";
	
	//升级通告
	public final static String BULLETIN_UPGRADE_TYPE="1";
	
	/**
	 * 每日信息事件状态
	 */
	public final static String STATUS_DAILY_EVENT_UNCOMMIT="未提交";
	public final static String STATUS_DAILY_EVENT_STATION_PROCESS="服务站处理中";
	public final static String STATUS_DAILY_EVENT_REGION_PROCESS="分子公司处理中";
	public final static String STATUS_DAILY_EVENT_HEAD_PROCESS="技术部处理中";
	public final static String STATUS_DAILY_EVENT_HEAD_PROCESS_2="研发处理中";
	public final static String STATUS_DAILY_EVENT_STATION_CONFIRM="服务站确认中";
	public final static String STATUS_DAILY_EVENT_COMPLETED="已完成";
	public final static String STATUS_DAILY_EVENT ="STATUS_DAILY_EVENT";
	
	public final static String STATUS_DAILY_EVENT_ACCOUNT_SUPPORT="技术支持处理";
	public final static String STATUS_DAILY_EVENT_ACCOUNT_MANAGER="片区经理/运维管理部处理";
	public final static String STATUS_DAILY_EVENT_ACCOUNT_BRANCH="分子公司总经理处理";
	public final static String STATUS_DAILY_EVENT_ACCOUNT_MANAGEMENT_TEST="技术中心技术部处理";
	public final static String STATUS_DAILY_EVENT_ACCOUNT_MANAGEMENT="技术中心技术部经理处理";
	public final static String STATUS_DAILY_EVENT_ACCOUNT_MANAGEMENT_MANAGER="技术中心主管领导处理";
	public final static String STATUS_DAILY_EVENT_ACCOUNT_COMPANY_MANAGER="总经理处理";
	public final static String STATUS_DAILY_EVENT_ACCOUNT_SUPPORT_VALUE="B";//技术支持处理
	public final static String STATUS_DAILY_EVENT_ACCOUNT_MANAGER_VALUE="E";//片区经理/运维管理部处理
	public final static String STATUS_DAILY_EVENT_ACCOUNT_BRANCH_VALUE="H";//分子公司总经理处理
	public final static String STATUS_DAILY_EVENT_ACCOUNT_MANAGEMENT_TEST_VALUE="J";//运维管理中心技术部
	public final static String STATUS_DAILY_EVENT_ACCOUNT_MANAGEMENT_VALUE="K";//运维管理中心技术部经理
	public final static String STATUS_DAILY_EVENT_ACCOUNT_MANAGEMENT_MANAGER_VALUE="M";//运维中心主管领导处理"
	public final static String STATUS_DAILY_EVENT_ACCOUNT_COMPANY_MANAGER_VALUE="P";//总经理处理
	
	public final static String DAILY_EVENT_TYPE_ALARM="LXGZ";//连续故障
	
	public final static String DAILY_EVENT_IS_ALARM_RISK_STATUS_NEED="1";//事件风险评估_需评估
	
	public final static String DAILY_EVENT_ALARM_RISK_STATUS_Y="2";//事件风险评估_风险可控
	public final static String DAILY_EVENT_ALARM_RISK_STATUS_UN="1";//事件风险评估_未评估
	public final static String DAILY_EVENT_ALARM_RISK_STATUS_N="3";//事件风险评估_风险不可控
	
	/**
	 * 用于每日事件总部处理的时候，每个部门只能处理只所管理的事件
	 * @param departmentId
	 * @return
	 */
	public static String[] getEventTypeByDepartment(String departmentCode) {
		/*if(departmentCode.equals("D08")){//技术部(硬件，软件，综合，账务问题)
			return new String[]{"YJ","RJ","ZH","ZW"};
		}else if(departmentCode.equals("D09")){//备件中心(备件问题)
			return new String[]{"BJ"};
		}else if(departmentCode.equals("D02")){//服务质量部(市场及投诉),后期殷总要求看所有事件
			return new String[]{"YJ","RJ","ZH","ZW","BJ","SC","TS","XZ","QT"};
		}else if(departmentCode.equals("D03")){//总经办(行政问题)
			return new String[]{"XZ"};
		}else if(departmentCode.equals("D01")){//服务管理部(所有类型问题)
			return new String[]{"YJ","RJ","ZH","ZW","BJ","SC","TS","XZ","QT"};
		}
		else if(departmentCode.equals("1003")){//用于测试，admin用户可以处理使用问题
			return new String[]{"YJ","RJ","ZH","ZW","BJ","SC","TS","XZ","QT"};
		}else if(departmentCode.equals("D12")){//服务市场部(市场信息类)
			return new String[]{"SC"};
		}*/
		if(departmentCode.equals("D09")){//备件中心(备件问题)
			return new String[]{"BJ","GZ"};
		}else if(departmentCode.equals("YWZX3")){//运维管理中心 所有事件
			return new String[]{"YJ","RJ","RJ3","RJO","ZH","ZHQT","ZHYH","ZHDJ","ZW","BJ","SC","TS","XZ","QT","GZ","LXGZ"};
		}else if(departmentCode.equals("YWZX3_1")){//运维管理中心 (硬件，软件，综合，账务问题)YWZX3_1
			return new String[]{"YJ","RJ","RJ3","RJO","ZH","ZHQT","ZHYH","ZHDJ","ZW","GZ","LXGZ"};
		}else if(departmentCode.equals("YFZX5")){//研发中心(硬件，软件，综合，账务问题)
			return new String[]{"YJ","RJ","RJ3","RJO","ZH","ZHQT","ZHYH","ZHDJ","ZW"};
		}else if(departmentCode.equals("D03")){//总经办(行政问题)
			return new String[]{"XZ"};
		}else if(departmentCode.equals("ITKFB")){//IT开发部(所有类型问题)
			return new String[]{"YJ","RJ","RJ3","RJO","ZH","ZHQT","ZHYH","ZHDJ","ZW","BJ","SC","TS","XZ","QT","GZ","LXGZ"};
		}
		else if(departmentCode.equals("1003")){//用于测试，admin用户可以处理使用问题
			return new String[]{"YJ","RJ","RJ3","RJO","ZH","ZHQT","ZHYH","ZHDJ","ZW","BJ","SC","TS","XZ","QT","LXGZ"};
		}else if(departmentCode.equals("SWZCB")){//商务支持部(市场信息类)
			return new String[]{"SC"};
		}else if(departmentCode.equals("YWJS")){//运维管理中心 -技术部 (硬件，软件，综合，账务问题)
			return new String[]{"YJ","RJ","RJ3","RJO","ZH","ZHQT","ZHYH","ZHDJ","ZW","GZ","LXGZ"};
		}
		return null;
	}
	
	//工单处理：判断是一二级模块替换或零备件替换
	public final static String REPLACE_TYPE_MODULE="1";
	public final static String REPLACE_TYPE_SPAREPART="2";
	
	//设备历史遗留问题状态
	public final static String STATUS_SOLVED="已解决";
	public final static String STATUS_UNSOLVED="未解决";
	
	public final static String REPLACE_TYPE_ADD="新增";
	public final static String REPLACE_TYPE_EXCHANGE="替换";
	
	/**
	 * 工单费用状态
	 */
	public final static String STATUS_FEE_NO_CLAIM="未报销";
	public final static String STATUS_FEE_CLAIMED="已报销";
	
	/**
	 * 设备服务报警类型
	 */
	public final static String ALARM_TYPE_CONTINUOUS="连续故障报警";
	
	
	/**
	 * 设备服务报警状态
	 */
	public final static String STATUS_ALARM_PENDING="待处理";
	public final static String STATUS_ALARM_CLOSE="已关闭";
	public final static String STATUS_ALARM_STATION="服务站处理中";
	public final static String STATUS_ALARM_REGION="区域处理中";
	public final static String STATUS_ALARM_HEAD="总部处理中";
	public final static String STATUS_ALARM_INPRROCESS="处理中";
	
	/**
	 * 连接故障报警临界值配置
	 */
	public final static int ALARM_COUNT_STATION=3;//达到3次，报警给服务站。
	public final static int ALARM_COUNT_REGION=4;//达到4次，需要区域才可以关闭。
	public final static int ALARM_COUNT_HEAD=5;//达到5次，需要总部才可以关闭。
	
	/**
	 * 需排除的人为破坏因素
	 */
	public final static String PRBLEM_CODE_EXCLUDE="'FWB2','ECXX','ECX1','ECX2','ECX3','ECX4','ECX5','ECX6'";
	
	public final static String WORKFORM_TASK_LEVEL_EVER="常规";
	public final static String WORKFORM_TASK_LEVEL_URGENT="紧急";
	
	/**
	 * 服务保修类型
	 */
	public static final String SERVICE_WARRANTY_TYPE_INNER="1";//保内
	public static final String SERVICE_WARRANTY_TYPE_ALREADY = "2";  //已签保
	public static final String SERVICE_WARRANTY_TYPE_NOT = "3";  //未签保
	
	public static final String STATUS_ZW_DAILY_EVENT_100 = "100";//已完成
	public static final String STATUS_ZW_DAILY_EVENT_0 = "0";//保存
	
}
