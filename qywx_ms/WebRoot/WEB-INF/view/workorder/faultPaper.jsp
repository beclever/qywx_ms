<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>纸质工单更改</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaPublic.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/newWechat.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaMobile.css" />
  </head>
  <body>
    <input type="hidden" value="${paperId}" id="paperId">
    <input type="hidden" value="${paperCode}" id="paperCode">
    <input type="hidden" value="${equipmentModel}" id="equipmentModel">
    <input type="hidden" value="${poNumber}" id="poNumber">
    <br/><br/>
	<div class="workform_list"> 
		<label><font color="red">*</font>更改原因:</label> 
		<span>
			<select class="taskOrder_inputwidth" id="updateReason">
				<option value="">--请选择原因--</option>
				<c:forEach items="${workbean.abandonReason.optionValue}" var="abandonBean">
					<option value="${abandonBean.optionId}">${abandonBean.optionName}</option>
				</c:forEach>
			</select>
		</span>
		<div class="clear"></div>
	</div>
	<div class="workform_list">
		<label><font color="red">*</font>原因说明:</label> 
		<span>
			<textarea name="paperRemark" cols="" rows=""
				class="work_task_order_row_textarea"
				id="paperRemark"></textarea>
		</span>
		<div class="clear"></div>
	</div>
	<div class="box" align="center">
		<input type="button" class="window_btn"  style='background: #5FBFE7' value="确定" onclick="sendFaultOK()" />
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="window_btn" type="button" value="取消" style='background: #A6BBCE' onclick="sendFaultCancel()">
	</div>
	<script type="text/javascript">
		var basePath = '${ctx_path}';
	</script>
    <script type="text/javascript" src="${ctx_path }/pub/js/page/workorder/faultPaper.js"></script>
	<!-- Web弹窗层 js -->
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>
	<script>
		var index = parent.layer.getFrameIndex(window.name);
	  	//取消
	  	function sendFaultCancel(){
			  parent.layer.close(index);
		 }
	</script>
</body>
</html>
