<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>已派工单</title>
<link rel="stylesheet" href="${ctx_path }/pub/css/iscroll.css?v=20160304" />
<style type="text/css">
input[type="text"].query-style {
	width: 93%;
	height: 1.5em;
	margin-bottom: 0.2em;
	color: #000;
}

select {
	width: 93.5%;
	margin-bottom: 0.2em;
	color: #000;
	height: 2em;
	color: #000;
}
</style>
</head>
<body>
	<div class="mian02">
		<div class="query" style="background-color: #F6F6F6;">
			<div class="query_box03">
				<div class="query_box1">
					<input id='serialNumber' name='serialNumber' class="query-style"
						type="text"  placeholder="请输入设备序列号"/> 
						<span id="spanSerialNumber" style='display: none'>
						<img src="${ctx_path }/pub/images/444.png" onclick="cleanSerialNumber()" /></span>
				</div>
				<div class="query_box1">
					<input id='poNumber' name='poNumber' class="query-style"
						type="text"  placeholder="请输入工单号"/> 
						<span id="spanPoNumber" style='display: none'>
						<img src="${ctx_path }/pub/images/444.png" onclick="cleanPoNumber()" /></span>
				</div>
				<!-- 这里需要添加四个控件，并且当前div要隐藏，点击后显示 -->
				<div class="query_box1" id="showAtFirst"
					onclick="onClickArea(true);" align="center">
					<img src="${ctx_path }/pub/images/guand_11.png" height="20">
				</div>
				<div class="query_box1" id="hideAtFirst" style="display: none">
					<div class="query_box1">
						<input id='branchName' name='branchName' class="query-style"
							type="text" placeholder="请输入网点名称"/> <span id="spanBranchName"
							style='display: none'><img
							src="${ctx_path }/pub/images/444.png" onclick="cleanBranchName()" /></span>
					</div>
					<div class="query_box1">
						<select id="equipmentWorkOrderStatus" name="workOrderStatus"
							class="query-style" ><!-- placeholder="请选择工单状态" -->
							<option value="">请选择工单状态</option>
							<option value="处理中">处理中</option>
							<option value="主任审核">主任审核</option>
							<option value="库管员审核">库管员审核</option>
							<option value="已退回">已退回</option>
							<option value="已完成">已完成</option>
						</select> <span id="spanWorkOrderStatus" style='display: none'><img
							src="${ctx_path }/pub/images/444.png" onclick="cleanWorkOrderStatus()" /></span>
					</div>
					<div class="query_box1">
						<select id="equipmentEngineerName" name="engineerName"
							class="query-style">
							<option value="">请选择工程师</option>
							<c:forEach items="${engineerList}" var="userInfo">
								<option value="${userInfo.userId }">${userInfo.name }</option>
							</c:forEach>
						</select>
					</div>
					<%-- <div class="query_box1">
						<select id="equipmentProgressStatus" name="progressStatus"
							class="query-style">
							<option value="">请选择任务状态</option>
							<option value="已完成">已完成</option>
							<option value="未完成">未完成</option>
						</select> <span id="spanProgressStatus" style='display: none'><img
							src="${ctx_path }/pub/images/444.png" onclick="cleanProgressStatus()" /></span>
					</div> --%>
					<div class="query_box1" onclick="onClickArea(false);"
						align="center">
						<img src="${ctx_path }/pub/images/guand_13.png" height="20">
					</div>
				</div>
			</div>
			<div class="query_box_btn" id="btnQuery" style="padding-top: 2.3em;margin-right: .9em;">
				<a class="btn2" onclick="isSelectList(3)">查询</a>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<input type="hidden" value="1" name="pageNum" id="pageNum" />
	<div class="samian">
		<!-- <div id="wrapper" class="wrapper_top" style="top:11em;"> -->
		<div id="wrapper" style="top: 11em; overflow: hidden; left: 0px;" >
			<div id="scroller">
				<div id="pullDown">
					<span class="pullDownIcon"></span><span class="pullDownLabel">下拉刷新数据...</span>
				</div>
				<div id="thelist"></div>
				<div id="pullUp">
					<span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载更多...</span>
				</div>
			</div>
		</div>
	</div>
	<div style="padding-top: 15em; padding-left: 10.0em;">
		<c:if test="${result != null}">${result}</c:if>
	</div>
	<script src="${ctx_path }/pub/js/iscroll.js?v=20160304"></script>
	<script type="text/javascript"src="${ctx_path }/pub/js/pageCommon.js?v=20160304"></script>
	<script type="text/javascript"src="${ctx_path }/pub/js/page/workorderSendManage/sentOrderList.js?v=20160304"></script>
</body>
</html>
