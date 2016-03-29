package com.grgbanking.common.utils;


public interface WechatAPIUtil {

	/** 获取accessToken */
	public final static String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORP_ID&corpsecret=SECRET_ID";
	/** 获取jsapi_ticket */
	public final static String JSAPI_TICKET_URL = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKEN";

	// 部门
	/** 创建部门信息 */
	public final static String CRE_DEPARTMENT_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN";
	/** 更新部门信息 */
	public final static String UPD_DEPARTMENT_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=ACCESS_TOKEN";
	/** 删除部门信息 */
	public final static String DEL_DEPARTMENT_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=ID";
	/** 获取部门列表 */
	public final static String GET_DEPARTMENT_LIST_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN";

	// 成员
	/** 创建成员 */
	public final static String CRE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";
	/** 更新成员 */
	public final static String UPD_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN";
	/** 删除成员 */
	public final static String DEL_USER_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USER_ID";
	/** 批量删除成员 */
	public final static String BATCH_DEL_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=ACCESS_TOKEN";
	/** 获取成员 */
	public final static String GET_USER_LIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USER_ID";
	/** 获取部门成员(简单) <p>fetch_child(1/0:是否递归获取子部门下面的成员)</p><p>status(0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加)<p> */
	public final static String GET_SIMP_USER_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD&status=STATUS";
	/** 获取部门成员(详情) <p>fetch_child(1/0:是否递归获取子部门下面的成员)</p><p>status(0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加)<p> */
	public final static String GET_DETL_USER_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD&status=STATUS";

	// 管理标签
	/** 创建标签 */
	public final static String CRE_TAG_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token=ACCESS_TOKEN";
	/** 更新标签名字 */
	public final static String UPD_TAG_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token=ACCESS_TOKEN";
	/** 删除标签 */
	public final static String DEL_TAG_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token=ACCESS_TOKEN&tagid=TAG_ID";
	/** 获取标签成员 */
	public final static String GET_TAG_USERS_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token=ACCESS_TOKEN&tagid=TAG_ID";
	/** 增加标签成员 */
	public final static String ADD_TAG_USERS_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?access_token=ACCESS_TOKEN";
	/** 删除标签成员 */
	public final static String DEL_TAG_USERS_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?access_token=ACCESS_TOKEN";
	/** 获取标签列表 */
	public final static String GET_TAG_LIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token=ACCESS_TOKEN";

	// 管理多媒体文件
	/** 上传媒体文件 */
	public final static String UPLOAD_MEDIA_URL = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	/** 获取媒体文件 */
	public final static String GET_MEDIA_URL = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	// 接收消息与事件 TODO http://qydev.weixin.qq.com/wiki/index.php?title=%E6%8E%A5%E6%94%B6%E6%B6%88%E6%81%AF%E4%B8%8E%E4%BA%8B%E4%BB%B6
	/** 二次验证 */
	public final static String USER_AUTHSUCC_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token=ACCESS_TOKEN&userid=USER_ID";
	
	// 发送消息 http://qydev.weixin.qq.com/wiki/index.php?title=%E5%8F%91%E9%80%81%E6%B6%88%E6%81%AF
	/** 发送文本 */ //需要管理员对应用有使用权限，对收件人touser、toparty、totag有查看权限，否则本次调用失败。
	public final static String SEND_TEXT_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
	// public final static String SEND_TEXT_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN&debug=1";

	// 自定义菜单
	/** 创建应用菜单 */
	public final static String CREAT_MENU_URL = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=AGENT_ID";
	/** 删除菜单 */
	public final static String DEL_MENU_URL = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN&agentid=AGENT_ID";
	/** 获取菜单列表 */
	public final static String GET_MENU_LIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN&agentid=AGENT_ID";
	
	// OAuth2验证接口
	/** 企业获取code */ //员工点击后，页面将跳转至 redirect_uri/?code=CODE&state=STATE，企业可根据code参数获得员工的userid。
	public final static String OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORP_ID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	/** 根据code获取成员信息 */
	public final static String OAUTH2_GET_USER_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE&agentid=AGENT_ID";

	/** 服务器IP获取 */
	public final static String SERVER_ID_URL = "https://qyapi.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";

	// grg4007002468@163.com grgbanking520 服务号
	// public final static String APP_ID = "wxfb1296b5748efb49"; // 微信服务号的AppId
	// public final static String SCRET_ID = "76****************************b3"; // 微信服务号的secret
	// public final static String URL = "http://localhost:8080/pdfDemo";
	// public final static String TOKEN = "rache";
	// public final static String ENCODING_AES_KEY = "rMbz1c3PWmcqteqJYS1rHLMWM8NO9WBtPANAHSw9WE4";

	// grg4007002468@126.com grgbanking520 订阅号
	// public final static String APP_ID = "wx1a4ef246f8880e71"; // 微信订阅号的AppId
	// public final static String SCRET_ID = "c50DIRJTcWjwdSAJEejZavd_HyFXabJKhc_I-zZ2OpSRqttmscpSC-hBsqdOlTyG"; // 微信服务号的secret
	// public final static String URL = "http://183.63.190.43/weixinServer/weixin/wei";
	// public final static String TOKEN = "weixin";

}