<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>待派工单</title>
<link rel="stylesheet" href="${ctx_path }/pub/css/iscroll.css??datetime=20160308" />
<style type="text/css">
input[type="text"].query-style {
	width: 90%;
	height: 1.5em;
	margin-bottom: 0.2em;
	color: #000;
}

select {
	width: 92%;
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
					<input id='serialNumber' name='serialNumber' class="query-style" onkeyup="this.value=this.value.replace(/\D/g,'')"
						type="text"  placeholder="请输入设备序列号"/> 
						<span id="spanSerialNumber"style='display: none'>
						<img src="${ctx_path }/pub/images/444.png" onclick="cleanSerialNumber()" /></span>
				</div>
				<!-- 这里需要添加四个控件，并且当前div要隐藏，点击后显示 -->
				<div class="query_box1" id="showAtFirst"
					onclick="onClickArea(true);" align="center">
					<img src="${ctx_path }/pub/images/guand_11.png" height="15">
				</div>
				<div class="query_box1" id="hideAtFirst" style="display: none">
					<div class="query_box1">
						<input id='branchName' name='branchName' class="query-style"
							type="text" placeholder="请输入网点名称"/> <span id="spanBranchName"
							style='display: none'><img
							src="${ctx_path }/pub/images/444.png" onclick="cleanBranchName()" /></span>
					</div>
					<%-- <div class="query_box1">
						<select id="equipmentEngineerName" name="engineerName"
							class="query-style">
							<option value="">请选择工程师</option>
							<c:forEach items="${aocLoginNameList}" var="userInfo">
								<option value="${userInfo.userCode }">${userInfo.userName }</option>
							</c:forEach>
						</select>
					</div> --%>
					<div class="query_box1" onclick="onClickArea(false);"
						align="center">
						<img src="${ctx_path }/pub/images/guand_13.png" height="15">
					</div>
				</div>
			</div>
			<div class="query_box_btn" style="margin-right: .9em;">
				<a class="btn2" onclick="isSelectList('down')">查询</a>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<input type="hidden" value="1" name="pageNum" id="pageNum" />
	<div class="samian">
		<!-- <div id="wrapper" class="wrapper_top" style="top:11em;"> -->
		<div id="wrapper" style="top: 11em;">
			<div id="scroller">
				<div id="pullDown" style="display: none">
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
	<script src="${ctx_path }/pub/js/iscroll.js?datetime=20160308"></script>
	<script type="text/javascript"src="${ctx_path }/pub/js/pageCommon.js?datetime=20160308"></script>
	<script type="text/javascript">
	function isSelectList(type){
		var serialNumber = $('#serialNumber').val();// 设备序列号
		if(serialNumber){
			var reg=/^[0-9]+$/;
			var result= reg.test(serialNumber);
			if(!result){
				layer.alert("设备序列号只能输入数字!");
				serialNumber.val("");
			}else{
				pull(type,"but");
			};
		}else{
			pull(type,"but");
		}
	}

	// 业务菜单返回导航菜单
	function backNavMenu() {
		window.android_backNav.exitActivity();
	}
	/**
	 * 获取备件信息
	 * 
	 * @param type
	 *            1 上一页 ,2 为下一页 ,3 为首页 ,4 从手机读取的页码
	 */
	function pull(type) {
		var pageNum = $("#pageNum").val();
		var number = pageNum;// 记录页码用于超时或无工单时回复
		// 上一页
		if(type=="down"){
			if(pageNum<1) {// 判断：如果是在第一页上翻页返回，不查询
				$("#pageNum").val(1);
				return;
			}
			pageNum = 1;//Number(pageNum) -1;
			$("#thelist").empty(); // 清空数据
		}
		// 下一页
		if(type=="up"){
			pageNum = Number(pageNum) + 1;
		}
		// 首页，点击查询显示用
		if(type=="down"){
			pageNum = 1;
			$("#pageNum").val(1);
			$("#thelist").empty(); // 清空数据
		}
		var serialNumber = $('#serialNumber').val();// 设备序列号
		var branchName = $('#branchName').val();// 网点名称
		//var workFormStatus = $('#equipmentWorkOrderStatus').val();// 工单状态
		//var taskStatus = $('#equipmentProgressStatus').val();// 任务完成状态
		var engineerUserCode = $('#equipmentEngineerName').val();// 工程师
		if (serialNumber != "" && isNaN(serialNumber)) {
			return;
		}
		layer.load(2,{ shade: [0.6,'#666666'] });//加载中...
		$.ajax({
			type : "post",
			url : basePath + "/cp/sendManage/pageWaitOrderlist.do",
			data : {
				pageNum : pageNum,
				serialNumber : serialNumber,
				branchName : branchName,
				engineerUserCode : engineerUserCode
			},
			dataType : 'json',
			cache : false,
			success : function(res) {
				layer.closeAll('loading');
				var html = "";
				onClickArea(false);
				if(res.returnResult){
					if(res.list!=null&&res.list.length>0){
						$('#pageNum').val(pageNum/1+1);
						$(res.list).each(function(i,data){
							var customerName = encodeURIComponent(data.customerName);
							var branchName = encodeURIComponent(data.branchName);
							var installAddress = encodeURIComponent(data.installAddress);
							var taskType = encodeURIComponent(data.taskType);
							var taskLevel = encodeURIComponent(data.taskLevel);
							var engineerName = encodeURIComponent(data.engineerName);
							var taskContent = encodeURIComponent(data.taskContent);
							var reportTime = encodeURIComponent(data.reportTime);
							var appointmentTime = encodeURIComponent(data.appointmentTime);
							var taskSource = encodeURIComponent(data.taskSource);
							var repairsManName = encodeURIComponent(data.repairsManName);
							var receiveManName = encodeURIComponent(data.receiveManName);
							
							var url = basePath + "/cp/sendManage/waitOrderDetail.do?customerName=" + customerName +
							"&branchName=" + branchName +
							"&installAddress=" + installAddress +
							"&equipmentId=" + data.equipmentId +
							"&equipmentDeptId=" + data.equipmentDeptId +
							"&serialNumber=" + data.serialNumber +
							"&taskId=" + data.taskId +
							"&taskType=" + taskType +
							"&taskLevel=" + taskLevel +
							"&engineerId=" + data.engineerId +
							"&engineerName=" + engineerName +
							"&taskContent=" + taskContent +
							"&reportTime=" + reportTime +
							"&appointmentTime=" + appointmentTime +
							"&taskSource=" + taskSource +
							"&repairsContactId=" + data.repairsContactId +
							"&repairsManName=" + repairsManName +
							"&repairsMoblie=" + data.repairsMoblie +
							"&repairsTelephone=" + data.repairsTelephone +
							"&receiveContactId=" + data.receiveContactId +
							"&receiveManName=" + receiveManName +
							"&receiveMoblie=" + data.receiveMoblie +
							"&receiveTelephone=" + data.receiveTelephone;
							html += 
							"<div class=\"gdlb_list\">"+
							"<h3 class=\"font_black\">"+getNoNullValue(data.branchName)+"</h3>"+
							"<span class=\"gdlb_info\">设备序列号："+data.serialNumber+"<br/>任务类型："+data.taskType + "<br />"+
								"客户名称："+getNoNullValue(data.customerName)+
								"<br /> 报修时间："+getNoNullValue(data.reportTime)+"<br /> 预约上门时间："+getNoNullValue(data.appointmentTime)+
								"<br />"+
							"</span> <a href =\""+url+"\"><span class=\"query_arrow\"></span></a>"+
						"</div>";
						});
						if(res.pageCount <= pageNum){
							$("#pullUp").hide();
							if(res.pageCount == pageNum){
								$('#thelist').append(html);
							}
							if(res.pageCount < pageNum){
								layer.msg('暂无更多工单！');
							}
							
						}else{
							$("#pullUp").show();
							$('#thelist').append(html);
						}
					}else{
						layer.msg('暂无工单！');
					}
				}else{
					layer.alert(res.errorMessage,{ closeBtn: false });
				}
				
				$("#pageNum").val(pageNum);
			
			},
			error : function(data) {
				layer.closeAll('loading');
				$("#pageNum").val(number);// 恢复页码
				layer.msg("暂无更多工单");
			}
		});
	}

	// 当数据为空是，不显示
	function getNoNullValue(str) {
		var result = "";
		if (str != null) {
			result = str;
		}
		return result;
	}

	// 根据执行时间判断到哪里，如接受时间有接受时间，即 状态为"接受"
	function getStutas(datas, acceptTime, leaveTime) {
		var data = new Object();
		data = datas;
		var result = "未接受";
		// alert("workFormStatus="+data.workFormStatus);
		if (data.workFormStatus == "处理中") { // 处理中
			if (data.finishWorkTime != "") {
				result = "已完成";
			} else if (data.startWorkTime != "") {
				result = "已开始";
			} else if (data.arriveTime != "") {
				result = "已到达";
			} else if (leaveTime != "") {
				result = "已出发";
			} else if (acceptTime != "") {
				result = "已接受";
			}
		} else if (data.workFormStatus == "已退回") { // 已退回
			result = "已退回";
		}
		return result;
	}

	// 控制隐藏的查询条件显示
	function onClickArea(flg) {
		if(flg) { // 显示
			document.getElementById("showAtFirst").style.display="none";
			document.getElementById("hideAtFirst").style.display="inline";
			document.getElementById("wrapper").style.top="10.5em";
		} else { // 隐藏
			document.getElementById("showAtFirst").style.display="block";
			document.getElementById("hideAtFirst").style.display="none";
			document.getElementById("wrapper").style.top="7em";
		}
		
	}
	/**
	 * 初始化数据
	 */
	$(function() {
		pull("down");
	});

	</script>
</body>
</html>
