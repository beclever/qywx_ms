package com.grgbanking.css.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备管理常量类
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-7-22 下午05:19:45
 */
public class EquipmentConstants {
	
	/**
	 * 维保状态
	 */
	public static final String EQUIPMENT_WARRANTY_STATUS_INNER = "A"; //保内
	public static final String EQUIPMENT_WARRANTY_STATUS_EQUIPMENT = "F";  //备件支持
	public static final String EQUIPMENT_WARRANTY_STATUS_LOSE = "E";  //丢单
	public static final String EQUIPMENT_WARRANTY_STATUS_NOT = "C";  //未签保
	public static final String EQUIPMENT_WARRANTY_STATUS_ALREADY = "B";  //已签保
	public static final String EQUIPMENT_WARRANTY_STATUS_REFUSE = "D";  //银行不签
	
	/**
	 * 设备状态
	 */
	public static final String EQUIPMENT_STATUS_DISCARD = "4";  //报废
	public static final String EQUIPMENT_STATUS_WAIT = "2";    //待装
	public static final String EQUIPMENT_STATUS_DISABLE = "3";   //停用
	public static final String EQUIPMENT_STATUS_USE = "1";   //在用
	public static final String EQUIPMENT_STATUS_EXIT = "5";    //退机
	

	
	/**
	 * 验收报告状态
	 */
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_NOT = "1";    //未提交
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_GRGBANKING = "2";    //已提交运通
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_YINTONG = "3";    //不需签单
	//新增状态(jeff steve . lin)
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_NOTSIGN = "3";//不需签单
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_SIGN = "4";//统一签单
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_SALE= "5";//销售安排
	
	
	/**
	 * 验收报告状态
	 */
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_NOTEN = "未提交银通";    //未提交
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_GRGBANKINGEN = "已提交运通";    //已提交运通
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_YINTONGEN = "已提交银通";    //已提交银通
	//新增状态(jeff steve . lin)
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_NOTSIGNEN = "不需签单";//不需签单
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_SIGNEN = "统一签单";//统一签单
	public static final String EQUIPMENT_RECEIVE_REPORT_STATUS_SALEEN= "销售安排";//销售安排
	
	
	/**
	 * 设备销售属性
	 */
	public static final String EQUIPMENT_SALE_PROPERTY_OPERATION="营运";
	public static final String EQUIPMENT_SALE_PROPERTY_SALES="销售";
	public static final String EQUIPMENT_SALE_PROPERTY_BORROW="借用";
	public static final String EQUIPMENT_SALE_PROPERTY_SHOW="展机";
	public static final String EQUIPMENT_SALE_PROPERTY_GIFT="赠送";
	public static final String EQUIPMENT_SALE_PROPERTY_TEST="测试";
	public static final String EQUIPMENT_SALE_PROPERTY_TRY="试用";
	
	
	/**
	 * 获取设备当前配置标配列表
	 * @return
	 */
	public static List<String> getConfigList(){
		List<String> list=new ArrayList<String>();
		
		/*list.add("机芯=B000");
		list.add("工控机=D000");
		list.add("读卡器=E100");
		list.add("显示器=G100");
		list.add("触摸屏=G000");
		list.add("凭条打印机=F100");
		list.add("流水打印机=F200");
		list.add("存折打印机=F300");
		list.add("发票打印机=S100");
		list.add("闸门=H100");
		list.add("密码键盘=L100");
		list.add("功能键盘=L000");
		list.add("串口设备=I100");
		list.add("后台维护终端=J100");
		list.add("电源=K100");
		list.add("开关盒=K000");
		list.add("监控设备=O100");
		list.add("温控设备=Q100");
		list.add("安全保险设备=N100");
		list.add("密码锁=C100");
		list.add("外部电源=R100");
		list.add("功放盒=C500");
		list.add("状态指示器=W100");
		list.add("网络设备=C200");
		list.add("机芯管理模块=C300");*/
		
		list.add("机芯B000=B000");
		list.add("工控机D000=D000");
		list.add("读卡器E100=E100");
		list.add("显示器G100=G100");
		list.add("打印机类F500=F500");
		
		list.add("闸门H100=H100");
		list.add("客户键盘类L200=L200");
		list.add("串口设备I100=I100");
		list.add("后台维护终端J100=J100");
		list.add("电源K100=K100");
		
		list.add("监控设备O100=O100");
		list.add("温控设备Q100=Q100");
		list.add("音频控制类M100=M100");
		list.add("状态指示器W100=W100");
		list.add("保险柜N200=N200");
		
		list.add("密码锁C100=C100");
		list.add("密码防窥罩N300=N300");
		list.add("读卡器异型口E200=E200");
		//list.add("其它设备T100=T100");
		return list;
	}
	
	/**
	 * 设备类型
	 */
	public static final String EQUIPMENT_TYPE_ATM="A100"; //取款机
	public static final String EQUIPMENT_TYPE_TELLERMACHINE="A300"; //存取款机
	public static final String EQUIPMENT_TYPE_OLDMODELS="OLD"; //老机型
	
	public static final String EQUIPMENT_SALE_PROPERTY="01";//销售
	public static final String EQUIPMENT_OPERATE_PROPERTY="02";//营运
	public static final String EQUIPMENT_USE_PROPERTY="03";//试用
	public static final String EQUIPMENT_TEST_PROPERTY="04";//测试
	public static final String EQUIPMENT_PRESENT_PROPERTY="05";//赠送
	public static final String EQUIPMENT_BORROW_PROPERTY="06";//借用
	public static final String EQUIPMENT_SHOW_PROPERTY="07";//展机
	public static final String EQUIPMENT_OPERATE_PROPERTY_MATURE="08";//营运到期
	
	/**
	 * 设备制造商
	 */
	public static final String MENUFACTURER_GRGBANKING="GRGBANKING";
	public static final String MENUFACTURER_NCR="NCR";
	public static final String MENUFACTURER_DIEBOLD="DIEBOLD";
	public static final String MENUFACTURER_WINCOR="WINCOR";
	public static final String MENUFACTURER_YH="怡化";
	public static final String MENUFACTURER_FUJITSU="FUJITSU";
	public static final String MENUFACTURER_OLIVETTI="好利获得";
	public static final String MENUFACTURER_OKI="OKI";
	public static final String MENUFACTURER_FUTOP="FUTOP";
	public static final String MENUFACTURER_HITACHI="HITACHI";
	
	/**
	 * AVS反馈信息状态
	 */
	public static final String EQUIPMENT_RUN_PROBLEM_STATUS_NONE="NONE";//未处理
	public static final String EQUIPMENT_RUN_PROBLEM_STATUS_CANCEL="CANCEL";//作废
	public static final String EQUIPMENT_RUN_PROBLEM_STATUS_FINISH="FINISH";//已完成
	public static final String EQUIPMENT_RUN_PROBLEM_STATUS_INPROCESS="INPROCESS";//处理中
	
	//清分设备-用于报表
	public static final String EQUIPMENT_EQUIPMENT_MODEL_CM_IN="'CM100V','CM200V','CM400'";
	//维修工单类型
	public static final String WORKFORM_WX_TYPE_9250="9250";
	public static final String WORKFORM_WX_TYPE_CM="cm";
	/**
	 * VTM设备机型
	 */
	public static final String EQUIPMENT_FLAG_VTM = "VTM";
	public static final String EQUIPMENT_TYPE_VTM = "AC00";
	
	/**
	 * h68n系列型号
	 */
	public static final String EQUIPMENT_EQUIPMENT_MODEL_H68N_IN="'H68N','H68NL','H68N-C','H68NL-C','H68NLR-C'";
	public static final String EQUIPMENT_EQUIPMENT_MODEL_H68N="H68N,H68NL,H68N-C,H68NL-C,H68NLR-C";
}
