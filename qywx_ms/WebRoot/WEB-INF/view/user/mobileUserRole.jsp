<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>温馨提示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body style="text-align: center;">
    <img src="${ctx_path }/pub/images/tips_icon.png" style="padding-top:20%;margin-bottom:20px;width:100px;height:130px"/> 
    <c:if test="${0 == result.errcode }">
    	<h1 style="color:#999;font-size:25px">您没有配置服务站主任权限，请联系开发部.</h1>
	</c:if>
	<input type="hidden" id="isDirector" value="${result.errcode }">
	<div style="display: none" id="dirUrl">${result.data }</div>
	
	<%@ include file="../include/_jsapi.jsp"%>
	<script type="text/javascript">
		var basePath = '${ctx_path}';
		wx.ready(function () {
			wx.hideOptionMenu();
		})
		$(function() {
			//具有服务站主任权限（非AOC）
			if($("#isDirector").val() == 1){
				window.location.href = $("#dirUrl").text();
			}
		});
	</script>
  </body>
</html>
