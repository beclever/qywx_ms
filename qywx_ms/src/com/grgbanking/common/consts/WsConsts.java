package com.grgbanking.common.consts;

import com.grgbanking.common.utils.PropUtils;

public class WsConsts {
	
	//请求发送端类型：PHONE-手机；CSS-客服系统；AFC-AFC系统
	public static String CLIENT_TYPE = "PHONE";
		
    // 工单
    public static String SocWorkformWsServiceUrl = PropUtils.get("SocWorkformWsServiceUrl");

    public static String SocWorkFormEngineUrl = PropUtils.get("SocWorkFormEngineUrl");

    // 纸质工单
    public final static String SocPaperWorkformEngineUrl = PropUtils.get("SocPaperWorkformEngineUrl");

    // 多媒体（二维码...）
    public final static String SoftwareMediaWsUrl = PropUtils.get("SoftwareMediaWsUrl");

    // 附件URL前缀
    public final static String AttachmentRootUrl = PropUtils.get("AttachmentRootUrl");
    
    // 工单附件服务器保存路径
    public final static String AttachmentRootPath = PropUtils.get("AttachmentRootPath");

    // 备件
    public static String SpmSparepartEngineUrl = PropUtils.get("SpmSparepartEngineUrl");

    public static String SpmSearchEngineUrl = PropUtils.get("SpmSearchEngineUrl");

    // 设备管理
	public static String SocEquipmentWsServiceUrl = PropUtils.get("SocEquipmentWsServiceUrl");
    
	// 设备联系人
 	public static final String EmsEquipmentContact = PropUtils.get("EmsEquipmentContact");
 	
 	// 设备图片上传
 	public static final String WeiXinService = PropUtils.get("WeiXinService");
 	
 	//设备责任人配置路径
 	public static final String EquipmentCharge = PropUtils.get("EquipmentCharge");
    
    //人员支援
    public static final String SocEmployeeSupportServiceUrl = PropUtils.get("SocEmployeeSupportServiceUrl");
    
    //员工
    public static final String SocEmployeeUrl = PropUtils.get("SocEmployeeUrl");
    
    //现场任务
    public static final String SocWorkTaskServiceUrl = PropUtils.get("SocWorkTaskServiceUrl");
    /**
     * 非AOC服务站主任角色Id
     */
    public static final String SERVICE_DIRECTOR  = PropUtils.get("SERVICE_DIRECTOR");
    /**
     * 非AOC服务站库管员角色Id
     */
    public static final String SERVICE_LIBRARY  = PropUtils.get("SERVICE_LIBRARY");
    
    //客服服务站工程师角色代码
    public static final String CssEngineerRole = PropUtils.get("CssEngineerRole");
    
    //客服服务站主任角色代码
    public static final String CssDirRole = PropUtils.get("CssDirRole");
    
    //客服服务站库管员角色代码
    public static final String CssLibRole = PropUtils.get("CssLibRole");
}
