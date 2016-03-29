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
<title>移除任务</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<div class="window_mian">
		<input type="hidden" value="${workformId}" id="workformId"> <input
			type="hidden" value="${workTaskId}" id="workTaskId"> <input
			type="hidden" value="${poNumber}" id="poNumber">
		<div class="workform_list">
			<label>移除说明:</label> <span> <textarea id="remark"
					name="remark" class="work_task_order_row_textarea"></textarea>
			</span>
			<div class="clear"></div>
		</div>
		<div class="box" align="center">
			<input type="button" class="window_btn" value="确定"
				onclick="removeTask();" /> <input type="button" class="window_btn"
				value="取消" onclick="cancelRemoveTask();">
		</div>
	</div>
	<script type="text/javascript">
	
		var index = parent.layer.getFrameIndex(window.name);//得到当前iframe层的索引
		//移动工单确认键
		function removeTask() {
			var remark = $("#remark").val();
			if (remark == "") {
				layer.alert("移除说明 不能为空");
				return false;
			}
			layer.confirm('注意:是否确认移除？', {title: '温馨提示', closeBtn: false }, function(index) {
				doTaskRemove();
			});
		}

		//点击确认 时触发
		function doTaskRemove() {
			var workformId = $("#workformId").val();
			var workTaskId = $("#workTaskId").val();
			var workTaskType = $("#workTaskType").val();
			var poNumber = $("#poNumber").val();
			var remark = $("#remark").val();
			var index  = layer.load(2,{shade:[0.6,'#666']});// 0.6透明度的灰色背景
			$.ajax({
				type : "post",
				url : basePath + "/cp/removeTaskWork.do",
				dataType : "json",
				data : {
					workformId : workformId,
					workTaskId : workTaskId,
					workTaskType : workTaskType,
					poNumber : poNumber,
					remark : remark
				},
				beforeSend : function() {
				},
				complete : function() {
				},
				success : function(data) {
					layer.close(index);
					if (data.errcode == 1) {
						layer.open({
									content : '任务移除成功!',
									closeBtn : false,
									icon : 1,
									yes : function(index) {
										layer.close(index); //一般设定yes回调，必须进行手工关闭
										top.location.href = basePath+'/cp/workOrder/dealWorkOrder.do?detailType=1&poNumber='
												+ poNumber
												+ "&workTaskId="
												+ workTaskId
												+ "&typeInfo=taskOrderMove";
									}
								});
					} else {
						layer.alert("任务移除失败："+data.errmsg, { title: '温馨提示', closeBtn: false });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					layer.close(index);
					layer.alert(errorThrown);
				}
			});
		}
		//取消
		function cancelRemoveTask() {
			parent.layer.close(index);
		}
	</script>
</body>
</html>

