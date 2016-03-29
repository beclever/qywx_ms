<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>故障部位</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="${ctx_path }/pub/js/page/workorder/faultIndex.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/mootools_mini_core.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/mootools_mini_dom.js"></script>

	
</head>
<body>
	<!--<div class="window_title">故障部位</div>-->
	<div class="window_mian" id="box">
		<input type="hidden" value="${serialNumber}" id="serialNumber">
		<input type="hidden" value="${divname}" id="divname"> <input
			type="hidden" value="${equipmentModel }" id="equipmentModel">
			 <input type="hidden" value="${poNumber}" id="poNumber">
		<div class="workform_list">
			<div class="taskOrder_add_btn">
				<label class="blue"><font color="red">*</font>故障部位:</label> <input
					type="text" readonly="true" size="15" onclick="sendfaultlocate()"
					name="problemPartCode" value="${problemPartCode}"
					id="problemPartCode" taskName="故障部位"> <span><img src="${ctx_path }/pub/images/icon_window.png" /></span>
			</div>
			<div class="clear"></div>
			<input type="hidden" value="${problemPartId}" id="problemPartId">
		</div>
		<div class="workform_list">
			<div class="taskOrder_add_btn">
				<label class="blue"><font color="red">*</font>故障描述:</label> <input
					type="text" size="15" onclick="sendfaultremark()" readonly="true"
					name="troubleCode" value="${troubleCode}" id="troubleCode"
					taskName="故障描述"> <span><img src="${ctx_path }/pub/images/icon_window.png" /></span>
			</div>
			<div class="clear"></div>
			<input type="hidden" id="problemCodeId" value="${problemCodeId}">
		</div>
		<div class="workform_list">
			<div class="taskOrder_add_btn">
				<label class="blue"><font color="red">*</font>故障原因:</label> <input
					type="text" size="15" onclick="sendfaultreason()" readonly="true"
					name="troubleReasonCode" value="${troubleReasonCode}"
					id="troubleReasonCode" taskName="故障原因"> <span><img src="${ctx_path }/pub/images/icon_window.png" /></span>
			</div>
			<div class="clear"></div>
			<input type="hidden" id="problemReasonId" value="${problemReasonId}">
		</div>
		<div class="workform_list">
			<div class="taskOrder_add_btn">
				<label class="blue"><font color="red">*</font>处理方法:</label> <input
					type="text" size="15" onclick="sendProcess()" readonly="true"
					name="processCode" value="${processCode}" id="processCode"
					taskName="处理方法"> <span><img src="${ctx_path }/pub/images/icon_window.png" /></span>
			</div>
			<div class="clear"></div>
			<input type="hidden" id="problemMethodId" value="${problemMethodId}">
		</div>
		<div class="box" align="center">
			<input type="button" class="window_btn" style='background: #5FBFE7' value="确定"
				onclick="sendFaultOK()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="window_btn" style='background: #A6BBCE' value="取消"
				onclick="cancelFault()">
		</div>
	</div>


</body>
</html>
