<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>退回原因</title>
<script type="text/javascript">
	var poNumber = "${workbean.poNumber}";
	var equipmentId = "${workbean.equipmentId}";
	var workformId = "${workbean.workformId}";
	//确定
	function orderReturnOK() {
		var result = $("#result_text").val();
		if (result.replace(/(^s*)|(s*$)/g, "").length == 0) {
			layer.alert("退回原因不能为空");
			return;
		}
		orderReturn(result);
	}
	//取消
	function orderReturnCancel() {
		var url = basePath+"/cp/workOrder/dealWorkOrder.do?detailType=2&equipmentId="+equipmentId+"&workformId="+workformId+"&poNumber="+poNumber;
		window.location.href=url;
	}
	// 工单退回
	function orderReturn(checkRemark){
		var timeArriveTime="${workbean.arriveTime}";
		var timeStartWorkTime="${workbean.startWorkTime}";
		var timeFinishWorkTime="${workbean.finishWorkTime}";
		layer.load(2,{ shade: [0.6,'#666666'] });//加载中...
		$.ajax({
		      type: "post",
		      url: basePath + "/cp/workOrderDirCheck/checkWorkformStepInStoreInfo.do",
		      dataType: "json",
		      data:{
		    	  poNumber:poNumber,
		      	  arriveTime:timeArriveTime,
		      	  workStartDate:timeStartWorkTime,
		      	  workFinishDate:timeFinishWorkTime,
		      	  buttonType:'back',
		      	  checkRemark:checkRemark,
		      	  targetStoreId:''},
		      success: function (msg) {
		        	layer.closeAll('loading');
		      	if (msg.returnResult) {
		              layer.open({
		            	    content: msg.returnMessage,
		            	closeBtn: false,icon:1,
		            	    yes: function(index){
		    		      		var url = basePath+"/cp/ouath/workOrderDirCheck/list.do";
		  		                window.location.href=url;
		            	        layer.close(index); //一般设定yes回调，必须进行手工关闭
		            	    }
		            	}); 
			}else {
				layer.alert(msg.errorMessage);
			}
		      },
		      error: function (XMLHttpRequest, textStatus, errorThrown) {
		        	layer.closeAll('loading');
		      	layer.alert("提交超时，工单退回失败；"+textStatus+";"+errorThrown);
		      } 
		  });
	}
</script>
</head>

<body>
	<div class="window_title">退回原因</div>
	<div style="width: 90%; margin: 4%">
		<span style="width: 100%;" align="center">
		<textarea name="paperRemark" cols="" rows="" class="work_task_order_row_textarea"
			id="result_text" style="width: 100%;" align="center"></textarea>
		</span>
	</div>
	<div class="box" align="center" style="margin-top: 20px">
		<input class="window_btn" type="button" value="确定" onclick="orderReturnOK()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="window_btn" type="button" value="取消" onclick="orderReturnCancel()">
	</div>
</body>
</html>
