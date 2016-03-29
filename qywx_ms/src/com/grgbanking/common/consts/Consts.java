package com.grgbanking.common.consts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Consts {
    /**
     * 是否为集中模式，如果为集中则为SA，非集中为CSS
     */
    public static final String CREATE_TYPE="CSS";
    
    /**
     * 用户session
     */
    public static final String SESSION_USER = "sessionUser";
    /**
     * 默认显示数量
     */
    public static final Integer DEFAULT_ROWS = 5;
    
    
    
    public static final String OPERATE_TYPE_B = "B" ;
    
    /**
     * 手机客户端
     */
    public static final String CLIENT_PHONE = "PHONE" ;
    
    /**
     * 微信非集中式客户端
     */
    public static final String CLIENT_TYPE = "wechat";
    /**
     * 故障信息KEY
     */
    public static final String FAUL_TINFO_KEY = "workOrderFaultInfoKey";
    /**
     * 城市
     */
    public static final List<String> PROVINCE_LIST = new ArrayList<String>(Arrays.asList("黑龙江",
            "吉林", "辽宁", "内蒙古", "甘肃", "天津", "河北", "山东", "山西", "北京",
            "河南", "陕西", "江苏", "上海", "安徽", "浙江", "江西", "福建", "四川", "湖北",
            "重庆", "贵州", "云南", "广西", "广东", "海南", "青海", "新疆", "西藏", "湖南",
            "香港", "澳门", "台湾"));
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
    public final static String TASK_TYPE_OTHER="QT";//其它
}
