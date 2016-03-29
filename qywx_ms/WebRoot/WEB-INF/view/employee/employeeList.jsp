<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>本服务站人员信息列表</title>
<style>
    	.loadingmore{
			text-align: center;
			color: #666;
			font-size: 14px;
			margin: 30px 0;
		}
    </style>
</head>
<body class="gdlb">
<div class="subject">
<div class="samian">
<div class="wrapper" id="wrapper">
<div id="scroller">
<div id="thelist">
	<c:if test="${true == result.returnResult}">
		<c:forEach items="${list }" var="data" varStatus="num">
			<div class="gdlb_list">
				<h3 class="font_black">${data.name }</h3>
				<span class="gdlb_info"> 
					 登录名：${data.loginName }<br />
					 工作部门：${data.departmentName }<br />
					组织：${data.company }<br /> 
					片区：${data.segment }<br />
					原属部门：${data.belongDepartmentName }<br />
				</span> 
				<a href ="${ctx_path }/cp/employee/employeeSupport.do?
				name=${data.name}
				&loginName=${data.loginName}
				&departmentId=${data.departmentId}
				&belongDepartmentId=${data.belongDepartmentId}
				&departmentName=${data.departmentName}
				&belongDepartmentName=${data.belongDepartmentName}">
				<span class="query_arrow"></span>
				</a>
			</div>
	 	</c:forEach>
 	</c:if>
</div>
<!-- <div id="loadingmore" class="loadingmore">
	<span style="position: relative;">点击加载更多</span>
</div> -->
</div>
</div>
<input type="hidden" id="pageNum" value="2">
</div>
</div>
<input type="hidden" id="errcode" value="${result.returnResult }"/>
<div style="display: none;" id="errmsg">${result.errorMessage }</div>
<c:if test="${true == result.returnResult && (null == result.returnMessage || 0 == fn:length(result.returnMessage)) }">
	<input type="hidden" id="hasData" value="0"/>
</c:if>
<%-- <script src="${ctx_path }/pub/js/page/workorderdircheck/workOrderListCheck.js?${time}"></script> --%>
<%@ include file="../include/_jsapi.jsp"%>
<script type="text/javascript">
var basePath = '${ctx_path}';
wx.ready(function () {
	wx.hideOptionMenu();
})
$(function() {
	if($("#hasData").val() == 0){
		layer.msg("暂无人员！");
	}
	if($("#errcode").val() == "false"){
		layer.alert($("#errmsg").text(), {closeBtn: false});
	}
});
</script>
</body>
</html>
