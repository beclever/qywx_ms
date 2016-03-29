<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Expires" CONTENT="0"> 
<meta http-equiv="Cache-Control" CONTENT="no-cache"> 
<meta http-equiv="Pragma" CONTENT="no-cache">
<title>工单列表</title>
<%-- <link rel="stylesheet" type="text/css" href="${ctx_path }/pub/css/iscroll.css" /> --%>
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
					<!-- <div id="pullDown">
						<span class="pullDownIcon"></span>
						<span class="pullDownLabel">上拉刷新...</span>
					</div> -->
					<div id="thelist">
						<c:forEach items="${result.pageData }" var="data" varStatus="num">
							<div class="gdlb_list">
								<h3 class="font_black">${data.customerName }</h3>
								<span class="gdlb_info"> 工 单 号：${data.poNumber }<br />
									网点名称：${data.branchName }<br /> 任务等级：${data.taskLevel }<br /> 预约时间：${data.appointmentDate }
									<br /> 状态：${data.workFormStatusResult }
									<br />
								</span> <a href ="${ctx_path }/cp/workOrder/detail.do?poNumber=${data.poNumber }&workformId=${data.workformId }&equipmentId=${data.equipmentId }"><span class="query_arrow"></span></a>
							</div>
					 	</c:forEach>
					</div>
					<!-- <div id="pullUp">
						<span class="pullUpIcon"></span><span class="pullUpLabel">下拉获取更多...</span>
					</div> -->
					<div id="loadingmore" class="loadingmore">
						<span style="position: relative;">点击加载更多</span>
					</div>
				</div>
			</div>
			<input type="hidden" id="pageNum" value="2">
		</div>
	</div>
	<input type="hidden" id="errcode" value="${result.errcode }"/>
	<div style="display: none;" id="errmsg">${result.errmsg }</div>
	<c:if test="${1 == result.errcode && (null == result.pageData || 0 == fn:length(result.pageData)) }">
		<input type="hidden" id="hasData" value="0"/>
	</c:if>
<%-- <script src="${ctx_path }/pub/js/iscroll.js"></script>
<script src="${ctx_path }/pub/js/pageCommon.js"></script> --%>
<script src="${ctx_path }/pub/js/page/workorder/workorderList.js?${time}"></script>
<%@ include file="../include/_jsapi.jsp"%>
<script type="text/javascript">
var basePath = '${ctx_path}';
wx.ready(function () {
	wx.hideOptionMenu();
})
$(function() {
	if($("#hasData").val() == 0){
		layer.msg("暂无更多工单！");
	}
	if($("#errcode").val() == 0){
		layer.alert($("#errmsg").text(), {closeBtn: false});
	}
});
</script>
</body>

</html>

