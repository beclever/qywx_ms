<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>升级前</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="${ctx_path }/pub/css/newWechat.css" type="text/css">
	<link rel="stylesheet" href="${ctx_path }/pub/css/SaMobile.css" type="text/css">
  	<link rel="stylesheet" href="${ctx_path }/pub/css/SaPublic.css" type="text/css">
  </head>
  
  <body>
  <div class="window_mian">
		<input type="hidden" value="${poNumber}" name="poNumber" id="poNumber">
       	<div class="workform_list">
	   		<label>操作系统：</label>
	   		<span>${beforSjModel.operationSystem }</span>
	   		<div class="clear"></div>
	   </div>
	   <div class="workform_list">
	   		<label>操作系统版本:</label>
	   		<span>${beforSjModel.osVersion }</span>
	   		<div class="clear"></div>
	   </div>
	   <div class="workform_list">
	   		<label>ATMC名称:</label>
	   		<span>${beforSjModel.atmCName }</span>
	   		<div class="clear"></div>
	   </div>
	   <div class="workform_list">
	   		<label>ATMC版本:</label>
	   		<span>${beforSjModel.atmCVersion }</span>
	   		<div class="clear"></div>
	   </div>
	    <div class="workform_list">
	   		<label>跨平台sp版本:</label>
	   		<span>${beforSjModel.atmCSpVersion}</span>
	   		<div class="clear"></div>
	   </div>
		<div class="box" align="center">
			<input type="button" value="关闭" class="window_btn" onclick="sendCancel()"/>
		</div>
	</div>
	<script src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>
    <script type="text/javascript">
    var basePath = "${ctx_path}";
    var index = parent.layer.getFrameIndex(window.name);
		function sendCancel(){
			parent.layer.close(index);
		}
	</script>
  </body>
</html>
