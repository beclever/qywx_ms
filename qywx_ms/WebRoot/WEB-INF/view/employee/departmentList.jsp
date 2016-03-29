<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>部门信息列表</title>
<link type="text/css" rel="stylesheet"
	href="${ctx_path }/pub/css/css.css">
<link type="text/css" rel="stylesheet"
	href="${ctx_path }/pub/css/web-icons/web-icons.min.css">
</head>
<body class="backcolor">
	<nav id="menu"
		class="mm-menu mm-widescreen mm-theme-white mm-effect-menu-slide mm-pagedim-black mm-offcanvas mm-hasnavbar-top-2 mm-hasnavbar-bottom-1 mm-parentselected mm-hasdividers">
	<div class="mm-panels">
		<div class="mm-panel mm-opened mm-current" id="mm-0">
			<ul class="listview-icons mm-listview">
				<c:forEach items="${list }" var="data" varStatus="num">
				<li onclick="doAction('${data.childNum}','${data.departmentId }','${data.departmentName}','${supportedLoginName }','${supportedUserName }','${belongDepartmentId}','${belongDepartmentName}')">
				<c:if test="${data.childNum != null  && data.childNum > 0}">
					<a class="mm-next mm-fullsubopen"
					   href="${ctx_path }/cp/employee/getDepartment.do?departmentId=${data.departmentId }&departmentName=${data.departmentName}&childNum=${data.childNum }&supportedLoginName=${supportedLoginName }&supportedUserName=${supportedUserName }&belongDepartmentId=${belongDepartmentId}&belongDepartmentName=${belongDepartmentName}"></a>
				</c:if>
				<span> 
					<i class="fa fa-graduation-cap"></i> 
					<c:if test="${data.childNum == null  || data.childNum <= 0}">
					<span class="checkbox-custom checkbox-primary checkbox-lg">
						<input type="checkbox" class="mailbox-checkbox selectable-item" onchange="pickDepartment('${data.departmentId}','${data.departmentName}')">
						<label for="mail_mid_1"></label>
					</span>
					</c:if>
					 ${data.departmentName}
				</span>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	</nav>
	<!--被支援人员登录名和姓名  -->
	<input type="hidden" name="supportedLoginName" id="supportedLoginName" value="${supportedLoginName }"/>
	<input type="hidden" name="supportedUserName" id="supportedUserName" value="${supportedUserName }"/>
	<!-- 原属部门 -->
	<input type="hidden" name="belongDepartmentId" id="belongDepartmentId" value="${belongDepartmentId }"/>
	<input type="hidden" name="belongDepartmentName" id="belongDepartmentName" value="${belongDepartmentName }"/>
	<input type="hidden" name="returnResult" id="returnResult" value="${resultBean.returnResult }"/>
	<c:if test="${true == resultBean.returnResult && (null == list || 0 == fn:length(list)) }">
		<div id="errorMessage" style="display: none;">${resultBean.errorMessage }</div>
		<input type="hidden" id="hasData" value="0"/>
	</c:if>

	<%@ include file="../include/_jsapi.jsp"%>
	<script type="text/javascript">
		var basePath = '${ctx_path}';
		wx.ready(function() {
			wx.hideOptionMenu();
		})
		$(function() {
			if($("#hasData").val() == 0){
				layer.msg("暂无部门！");
			}
			if ($("#returnResult").val() != "true") {
				layer.alert($("#errorMessage").text(), {
					closeBtn : false
				});
			}
		});
		
		function doAction(childNum, departmentId, departmentName, supportedLoginName, supportedUserName, belongDepartmentId, belongDepartmentName){
			if(childNum == "null" || childNum == "0"){
				pickDepartment(departmentId, departmentName);
			}else{
				window.location.href = basePath + "/cp/employee/getDepartment.do?departmentId="+departmentId 
						+"&departmentName="+departmentName
						+"&childNum="+childNum 
						+"&supportedLoginName="+supportedLoginName 
						+"&supportedUserName="+supportedUserName 
						+"&belongDepartmentId="+belongDepartmentId
						+"&belongDepartmentName="+belongDepartmentName;
			}
		}
		
		//选择部门
		function pickDepartment(departmentId, departmentName){

			//支援人员登陆名
			var supportedLoginName = $("#supportedLoginName").val();
			var supportedUserName = $("#supportedUserName").val();
			//所属部门
			var belongDepartmentId = $("#belongDepartmentId").val();
			var belongDepartmentName = $("#belongDepartmentName").val();
			window.location.href = basePath + "/cp/employee/employeeSupport.do?departmentId="+departmentId
					+ "&departmentName="+departmentName
					+ "&loginName="+supportedLoginName
					+"&name=" + supportedUserName
					+ "&belongDepartmentId="+belongDepartmentId
					+"&belongDepartmentName=" + belongDepartmentName;
		}
	</script>
</body>
</html>
