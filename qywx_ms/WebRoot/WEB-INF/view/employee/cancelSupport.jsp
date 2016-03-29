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
	<title>取消支援</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link type="text/css" rel="stylesheet" href="${ctx_path }/pub/css/css.css">
	<link type="text/css" rel="stylesheet" href="${ctx_path }/pub/css/web-icons/web-icons.min.css">
	<%-- <jsp:include page="/pub/js/mobiscroll/mobiscroll_theme.jsp"></jsp:include> --%>
</head>
<body class="backcolor">
	<div class="subject" id="pageContent">
	<div class="list_content">
	<input type="hidden" name="departmentId" id="departmentId" value="${departmentId }"/>
	<input type="hidden" name="belongDepartmentId" id="belongDepartmentId" value="${belongDepartmentId }"/>
	<input type="hidden" name="departmentName" id="departmentName" value="${departmentName }"/>
	<input type="hidden" name="belongDepartmentName" id="belongDepartmentName" value="${belongDepartmentName }"/>
	<input type="hidden" name="supportedLoginName" id="supportedLoginName" value="${loginName }"/>
	<input type="hidden" name="supportedUserName" id="supportedUserName" value="${name }"/>
           <li>
           	<label>姓名：</label>
			<span>${name}</span>
           </li>
           <li>
           	<label>支援部门：</label>
			<span>
				${departmentName }
			</span>
           </li>
            <!-- <li>
           	<label>结束时间：</label>
			<span><input type="text" class="form-control" name="endDate" id="endDate" onfocus="showDateTime($(this));"></span>
           </li> -->
	  </div>
      <div class="subf"><input type="button" value="取消支援" onclick="saveSupport()" class="btn self-btn bg s_btn"></div>
	</div>
	<%@ include file="../include/_jsapi.jsp"%>
	<script type="text/javascript">
	var basePath = '${ctx_path}';
	wx.ready(function () {
		wx.hideOptionMenu();
	})
	$(function() {
		if($("#errcode").val() == "false"){
			layer.alert($("#errmsg").text(), {closeBtn: false});
		}
	});
	
	/* function chooseDepartment(){
		//支援人员登陆名
		var supportedLoginName = $("#supportedLoginName").val();
		var supportedUserName = $("#supportedUserName").val();
		//所属部门
		var belongDepartmentId = $("#belongDepartmentId").val();
		var belongDepartmentName = $("#belongDepartmentName").val();
		var departmentId = $("#departmentId").val();
		window.location.href = basePath + "/cp/employee/getDepartment.do?&supportedLoginName="+supportedLoginName
				+"&supportedUserName="+supportedUserName
				+"&belongDepartmentId="+belongDepartmentId
				+"&belongDepartmentName="+belongDepartmentName;
	} */
	
	function saveSupport(){
		var postData = {
				supportedLoginName: $("#supportedLoginName").val()
				//,endDate: $("#endDate").val(),
				
		}
		/* if(!postData.endDate){
			layer.alert("请选择结束时间！", {title: '温馨提示', closeBtn: false});
			return;
		} */
		
		layer.load(2,{ shade: [0.6,'#666666'] });//加载中...
		$.ajax({
			url : basePath + "/cp/employee/doCancelSupport.do",
			data : postData,
			type : "post",
			dataType : "json",
			beforeSend : function(x) {
			},
			success : function(result) {
				if (result.returnResult) {
					layer.open({
					    content: '提交成功！',
					    title: false,
					    closeBtn: false,
					    yes: function(index){
					        layer.close(index); //一般设定yes回调，必须进行手工关闭
					        window.location.href = basePath + "/cp/ouath/employee/employeeList.do";
					    }
					}); 
				} else {
					layer.closeAll('loading');
					layer.alert(result.errorMessage, {title: '温馨提示', closeBtn: false});
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				layer.closeAll('loading');
				layer.alert("提交失败：【"+textStatus+"】"+errorThrown, {title: '温馨提示', closeBtn: false});
			}
		});
	}
	</script>
</body>
</html>
