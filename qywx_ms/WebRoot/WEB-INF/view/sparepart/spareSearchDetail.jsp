<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>条码信息</title>
		<link rel="stylesheet" type="text/css"
		href="${ctx_path }/pub/css/SaPublic.css" />
	</head>
	<body>
		<div class='nav_bottom'>
			<a class="nav_bottom_back" href="javascript:history.go(-1)"> 返回
			</a>
		</div>
		
		<c:if test="${stockList !=null}">
			<c:forEach items="${stockList}" var="stock">
				<div class="content_list">
					<label>条形编码：</label><span>${stock.serialNumber}</span>
				</div>

			</c:forEach>
		</c:if>
		<c:if test="${stockList ==null}">
			<div class="content_list">
				没有条形编码信息
			</div>
		</c:if>
	</body>
</html>
