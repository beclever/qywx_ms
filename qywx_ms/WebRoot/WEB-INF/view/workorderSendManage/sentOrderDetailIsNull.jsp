<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>工单详情</title>
</head>
<body>
	<!--顶部 a href="#" onclick=history.back()-->
	<div class="nav_bottom">
		<a class="nav_bottom_back" href="<%=basePath %>action/sa/sendOrderManage/sentOrderList?formType=qywx">
			<img src="<%=basePath%>images/arrow_left.png"/>返回
	    </a>
	</div>
	<div class="mian02">
	    <div style="text-align: center;margin: 0 auto;font-size:14px;margin-top:200px;">
	        <label>${result }</label>
	        <a href="<%=basePath %>action/sa/sendOrderManage/sentOrderList?formType=qywx">返回</a>
	    </div>
	</div>
</body>
</html>
