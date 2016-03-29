<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>待审核工单列表</title>
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
							<c:forEach items="${result.pendingWorkform }" var="data" varStatus="num">
								<div class="gdlb_list">
									<h3 class="font_black">${data.customerName }</h3>
									<span class="gdlb_info"> 工 单 号：${data.poNumber }<br />设备序列号：${data.serialNumber}<br />
										网点名称：${data.branchName }<br /> 任务等级：${data.taskLevel }<br /> 预约时间：${data.appointmentDate }
										<br /> 状态：库管员审核
										<br />
									</span> <a href ="${ctx_path }/cp/workOrder/dealWorkOrder.do?poNumber=${data.poNumber }&workformId=${data.workformId }&equipmentId=${data.equipmentId }&detailType=4"><span class="query_arrow"></span></a>
								</div>
						 	</c:forEach>
						</div>
						<div id="loadingmore" class="loadingmore">
							<span style="position: relative;">点击加载更多</span>
						</div>
					</div>
				</div>
				<input type="hidden" id="pageNum" value="2">
			</div>
		</div>
		<input type="hidden" id="errcode" value="${result.status }"/>
		<div style="display: none;" id="errmsg">${result.errMsg }</div>
		<c:if test="${1 == result.status && (null == result.pendingWorkform || 0 == fn:length(result.pendingWorkform)) }">
			<input type="hidden" id="hasData" value="0"/>
		</c:if>
		<script src="${ctx_path }/pub/js/page/workorderlibcheck/workOrderListLibCheck.js?${time}"></script>
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
