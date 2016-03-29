<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<html>
<head>
<title>工单处理</title>
<!-- 微信js -->
<%@ include file="../include/_jsapi.jsp"%>

<script type="text/javascript" src="${ctx_path }/pub/js/page/workorder/taskOrderListSubmit.js"></script>
<script type="text/javascript" src="${ctx_path }/pub/js/iscroll.js"></script>

<!-- 附件处理 -->
<script src="${ctx_path }/pub/js/page/workorder/taskOrderImageHanddle.js"></script>
<link rel="stylesheet" href="${ctx_path }/pub/css/zyUpload.css" type="text/css">
<style type="text/css">
	#oldSerialNameDiv .spare_wrapper_top .new_title{
	  width: 93%;
	  float: left;
	  padding: 0.9em 0em;
	  display: block;
	  color: #333333;
	  border-bottom: 1px solid #E3E3E3;
	  text-decoration: none;
	  font-size: 1.4em;
      font-family: "Open Sans",Arial,"Hiragino Sans GB","Microsoft YaHei","微软雅黑","STHeiti","WenQuanYi Micro Hei",SimSun,sans-serif;
	}
	
	.task_replace .modal_ok{
		background: #4286f5;
		color: #fff;
		width: 33%; 
		margin:0px 0px 0px auto;
	}
</style>
<script type="text/javascript">
var basePath = '${ctx_path}';
var workStartTime = "${workbean.startWorkTime}";
var workFinishTime = "${workbean.finishWorkTime}";
var equipmentId = "${workbean.equipmentId}";
var type = "";
var stateAndInputs = [];
var qrCode = '${qrCode}';
var imgs = '${imgs}';
var imgList = eval('(' + imgs + ')');


function initTypeInfo(type, showinfo,isExistFault) {
	//故障部位
	if(isExistFault != null && isExistFault != undefined && isExistFault != "" && isExistFault != 0){
		var problemPartId = "${problemPartId}";
		var problemPartCode = "${problemPartCode}";
		var problemCodeId = "${problemCodeId}";
		var troubleCode = "${troubleCode}";
		var problemReasonId = "${problemReasonId}";
		var troubleReasonCode = "${troubleReasonCode}";
		var problemMethodId = "${problemMethodId}";
		var processCode = "${processCode}";
		var divname = "${divname}";
		loadFaultLocation(divname, problemPartId, problemPartCode,
				problemCodeId, troubleCode, problemReasonId,
				troubleReasonCode, problemMethodId, processCode);
	}
	if (showinfo != null && showinfo != "") {
		// 模块替换
		if (type == "workOrderspare") {
			// modify by Rache 20150604
			var argsArray = showinfo.split("-,-");
			var obj = argsArray[0];
			var oldSerialName = argsArray[1];
			var oldSerialNumber = argsArray[2];
			var oldMaterialCode = argsArray[3];
			var oldHardwareVersion = argsArray[4];
			var oldSoftwareVersion = argsArray[5];
			var equipmentConfigId = argsArray[6];
			var newSerialName = argsArray[7];
			var newMaterialCode = argsArray[8];
			var newSerialNumber = argsArray[9];
			var newHardwareVersion = argsArray[10];
			var newSoftwareVersion = argsArray[11];
			loadData(obj, oldSerialName, oldSerialNumber, oldMaterialCode,
					oldHardwareVersion, oldSoftwareVersion,
					equipmentConfigId, newSerialName, newMaterialCode,
					newSerialNumber, newHardwareVersion, newSoftwareVersion);
		}
		//零件替换信息加载
		if (type == "workOrderelement") {
			// modify by Rache 20150604
			var argsArray = showinfo.split("-,-");
			var newSerialName = argsArray[0];
			var newMaterialCode = argsArray[1];
			var flaginfo = argsArray[2];
			var obj = argsArray[3];
			var quantity = argsArray[4];
			var nums = argsArray[4];
			var oldSerialName = argsArray[6];
			var oldMaterialCode = argsArray[7];
			var newSoftwareVersion = argsArray[8];
			var newHardwareVersion = argsArray[9];
			var useMethod=argsArray[10];
			loadElementData(obj, newSerialName, oldSerialName, nums,
					newMaterialCode, oldMaterialCode, newSoftwareVersion,
					newHardwareVersion, useMethod);
		}
	}
}
$(function(){
	
	//组织预览html
	funDealtPreviewHtml(imgList);
	
	if (is_weixin()) {
		getLocation();
	}
});
</script>
<style type="text/css">
.header_title {
	top: 0px;
	left: 0px;
	position: fixed;
	width: 100%;
	z-index: 1200;
}

.header_tip {
	width: 100%;
	padding: 1em 0em;
	background: #f3f3f3;
	border-bottom: 1px solid #e4e4e4;
}

.backimg_box {
	color: #fff;
	font-size: 1.6em;
	background: white;
	border-top: 1px solid #77BEF9;
	border-bottom: 1px solid #1666A7;
	height: 2.3em;
	width: 100%;
	text-align: center;
	line-height: 220%;
}

.buttom_title {
	bottom: 0px;
	left: 0px;
	position: fixed;
	width: 100%;
	z-index: 1200;
}

.handlebutton {
	width: 100px;
	height: 32px;
	font-size: 8px;
}

.backimg_box a {
	display: block;
}

.button_buttom_nav {
	color: black;
	font-size: 0.9em;
	padding-left: 0.5em;
	margin-top: 0.08em;
}

.button_buttom_right {
	color: black;
	font-size: 0.9em;
	padding-right: 0.5em;
	margin-top: 0.08em;
}
</style>
</head>
<body onload="initTypeInfo('${typeInfo}','${showInfo}','${isExistFault}')">
	<form action="${ctx_path }/workOrder/workDetailSave" id="form1" enctype="multipart/form-data">
		<!--顶部 -->
		<div class="header_title">
			<div id="top" class="header_tip">
				<div style="display: inline; padding-left: 1em;" class="left">
					<label style="font-size: 1.4em; color: #e4481b;">剩余时间:</label> <span
						id="actualTotalTime" style="font-size: 1.4em; color: #e4481b;">0分</span>
				</div>
				<div style="display: inline; padding-right: 1em;" class="right">
					<label style="font-size: 1.4em; color: #333333;">实际耗时: </label> <span
						id="initTotalTime" style="font-size: 1.4em; color: #333333;">0分</span>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!-- 隐藏域 -->
		<input type="hidden" value="${ctx_path }" id="urls"> <input
			type="hidden" value="${serialNumber}" id="serialNumber"
			name="serialNumber"> <input type="hidden" value="${poNumber}"
			id="poNumber" name="poNumber"> <input type="hidden"
			value="${workbean.workformId}" id="workformId" name="workformId">
		<!-- 用于提示(完成时间减去开始时间)和所有任务单总工时的检测 -->
		<input type="hidden" value="${workbean.startWorkTime}"
			id="startWorkTime" name="startWorkTime" /> <input type="hidden"
			value="${workbean.finishWorkTime}" id="finishWorkTime"
			name="finishWorkTime" />

		<!-- 左边菜单栏 -->
		<div id="taskOrder_nav">
			<ul style="padding-top: 3em;padding-bottom:3em;">

				<li><a id="ZZGDINFO" href="javascript:void(0)"><span
						class="min_width">工单</span></a></li>
				<c:forEach items="${tasklist}" var="taskbean">
					<c:if test="${taskbean.taskType=='XJ' }">
						<li class="taskTypeBar">
							<a id="${taskbean.taskType}${taskbean.taskId}" onclick="sendXJ(this)"> 
							<span class="min_width">${taskbean.taskTypeText}</span>
							</a>
						</li>
					</c:if>
					<c:if test="${taskbean.taskType!='XJ' }">
						<li class="taskTypeBar"><a id="${taskbean.taskType}"> <span
							class="min_width">${taskbean.taskTypeText}</span>
					</a></li>
					</c:if>				
				</c:forEach>
				<c:forEach items="${gradelist}" var="gradebean" varStatus="sta">
					<li class="taskTypeBar"><a
						id="${gradebean.taskType}${gradebean.taskId}"
						onclick="sendgrade(this)"> <span class="min_width">${gradebean.taskTypeText
								}</span>
					</a></li>
				</c:forEach>
				<li><a id="FYINFO" href="javascript:void(0)"><span
						class="min_width">费用</span></a></li>
				<li><a id="YLWTINFO" href="javascript:void(0)"><span
						class="min_width">遗留问题</span></a></li>
				<li><a id="EWMINFO" href="javascript:void(0)"><span
						class="min_width">二维码</span></a></li>
				<li><a id="IMGINFO" href="javascript:void(0)"><span
						class="min_width">附件</span></a></li>			
			</ul>
		</div>
		<!-- 右边对应内容 -->
		<div id="scoller">
			<div id="boxContent" style="padding-top: 3em;">
				<c:forEach items="${tasklist}" var="taskbean">
					<!-- 其他 -->
					<c:if test="${taskbean.taskType=='QT'}">
					<input type="hidden" value="${taskbean.taskId}" id="QTtaskId">
						<div class="tagContent selectTag flowmargin-top"
							id="${taskbean.taskType}Div" style="display: none;">
							<%@ include file="task_qt.jsp"%>
						</div>
					</c:if>

					<!-- 移机开通 -->
					<c:if test="${taskbean.taskType=='YJKT'}">
					<input type="hidden" value="${taskbean.taskId}" id="YJKTtaskId">
						<div class="tagContent selectTag flowmargin-top"
							id="${taskbean.taskType}Div" style="display: none;">
							<%@ include file="task_yjkt.jsp"%>
						</div>
					</c:if>

					<!-- 客户培训 -->
					<c:if test="${taskbean.taskType=='PX'}">
					<input type="hidden" value="${taskbean.taskId}" id="KHPXtaskId">
						<div class="tagContent selectTag flowmargin-top"
							id="${taskbean.taskType}Div" style="display: none;">
							<%@ include file="task_khpx.jsp"%>
						</div>
					</c:if>

					<!-- 账务处理 -->
					<c:if test="${taskbean.taskType=='ZW'}">
					<input type="hidden" value="${taskbean.taskId}" id="CWCLtaskId">
						<div class="tagContent selectTag flowmargin-top"
							id="${taskbean.taskType}Div" style="display: none;">
							<%@ include file="task_cwcl.jsp"%>
						</div>
					</c:if>

					<!-- 移机辅助 -->
					<c:if test="${taskbean.taskType=='YJFZ'}">
					<input type="hidden" value="${taskbean.taskId}" id="YJFZtaskId">
						<div class="tagContent selectTag flowmargin-top"
							id="${taskbean.taskType}Div" style="display: none;">
							<%@ include file="task_yjfz.jsp"%>
						</div>
					</c:if>

					<!-- 巡检 -->
					<c:if test="${taskbean.taskType=='XJ'}">
					<input type="hidden" value="${taskbean.taskId}" id="XJtaskId${taskbean.taskId}">
						<div class="tagContent selectTag flowmargin-top"
							id="${taskbean.taskType}${taskbean.taskId}Div" style="display: none;">
							<%@ include file="task_xj.jsp"%>
						</div>
					</c:if>

					<!-- 安装辅助 -->
					<c:if test="${taskbean.taskType=='AZFZ'}">
					<input type="hidden" value="${taskbean.taskId}" id="AZFZtaskId">
						<div class="tagContent selectTag flowmargin-top"
							id="${taskbean.taskType}Div" style="display: none;">
							<%@ include file="task_azfz.jsp"%>
						</div>
					</c:if>

					<!-- 安装开通 -->
					<c:if test="${taskbean.taskType=='AZKT'}">
					<input type="hidden" value="${taskbean.taskId}" id="AZKTtaskId">
						<div class="tagContent selectTag flowmargin-top"
							id="${taskbean.taskType}Div" style="display: none;">
							<%@ include file="task_azkt.jsp"%>
						</div>
					</c:if>

					<!-- 维修 -->
					<c:if test="${taskbean.taskType=='WX'}">
					<input type="hidden" value="${taskbean.taskId}" id="WXtaskId">
						<div class="tagContent selectTag flowmargin-top"
							id="${taskbean.taskType}Div" style="display: none;">
							<%@ include file="task_wx.jsp"%>
						</div>
					</c:if>

					<!--保养 -->
					<c:if test="${taskbean.taskType=='BY'}">
						<!-- 隐藏域 用于计划任务保养任务移除的任务ID和任务内容 -->
						<input type="hidden" value="${taskbean.taskId}" id="${taskbean.taskType}taskId" name="BYtaskId">
						<input type="hidden" value="${taskbean.taskType}" id="${taskbean.taskType}taskType" name="BYtaskType">
						<div class="tagContent selectTag flowmargin-top"
							id="${taskbean.taskType}Div" style="display: none;">
							<%@ include file="task_by.jsp"%>
						</div>
					</c:if>
				</c:forEach>
				<!-- 纸质工单 -->
				<div class="tagContent selectTag flowmargin-top" id="ZZGDINFODiv"
					style="display: none;">
					<div class="mian02">
						<div class="workform_list">
						    <label> 客户名称:</label>
						    <span>
						        ${workbean.customerName}
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 设备序列号:</label>
						    <span>
						        ${workbean.serialNumber}
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 整机型号:</label>
						    <span>
						        ${workbean.equipmentModel}
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
							<label><font color="red">*</font>纸质工单:</label> <span> <input
								type="hidden" name="paperId" id="paperId"
								value="${workbean.iniPaperId}" /> <input type="hidden"
								name="paperStatus" id="paperStatus"
								value="${workbean.intPaperStatus}" /> <input type="text"
								name="paperCode" id="paperCode" readonly="readonly"
								value="${workbean.iniPaperCode}">
							</span>
							<div class="clear"></div>
						</div>
						<!-- <div class="box" style="padding:0.5em 10em; font-size:8px;float:right"> -->
						<div class="box" align="center">
							<input type="button" class="window_btn" id="confimPager"
								onclick="paperConfirm()" value="确认" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="window_btn" id="updatePager"
								onclick="pLoaction()" value="更改" />
						</div>
						<div class="workform_list">
						    <label><font color="red">*</font>满意度:</label>
						    <span>
							    <select class="taskOrder_inputwidth" id="degreeSatisfaction" name='degreeSatisfaction'>
										<c:forEach items="${workbean.degreeSatisfaction.optionValue}"
											var="beanoption">
											<option
												<c:if test="${workbean.degreeSatisfaction.defaultId==beanoption.optionId}">selected=true</c:if>
												value="${beanoption.optionId },${beanoption.optionName }">${beanoption.optionName}</option>
										</c:forEach>
								</select>
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label>同行人员:</label>
						    <span>
							    <select class="taskOrder_inputwidth" id="togetherPerson" name='togetherPerson' >
							    	<option value=""></option>
							    	<c:forEach items="${togetherPersonList}" var="item">
										<option
											<c:if test="${workbean.togetherPerson.userId==item.userId}">selected=true</c:if>
											value="${item.userId },${item.name }">${item.name}</option>
									</c:forEach>
								</select>
						    </span>
						    <div class="clear"></div>
						</div>
						<%-- <div class="workform_list">
						    <label> 管理员:</label>
						    <span>
						        ${workbean.atmManager}
						    </span>
						    <div class="clear"></div>
						</div> --%>
						<div class="workform_list">
						    <label> 安装地址:</label>
						    <span>
						        ${workbean.installAddress}
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 工程师:</label>
						    <span>
						        ${workbean.engineerName}
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 报修电话:</label>
						    <span>
						    	<c:if test="${workbean.repairsTelephone!=null && workbean.repairsTelephone!=''}">
						    		${workbean.repairsTelephone}
						    	</c:if>
						    	<c:if test="${workbean.repairsTelephone==null || workbean.repairsTelephone==''}">
						    		${workbean.repairsMoblie}
						    	</c:if>
						         <%-- <input type="text" name="reportTel" id="reportTel" value="${workbean.reportTel}"> --%>
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 报修时间:</label>
						    <span>
						        ${workbean.reportTime}
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 预约时间:</label>
						    <span>
						        ${workbean.appointmentDate}
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 到达时间:</label>
						    <span>
						        ${workbean.arriveTime}
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 开始时间:</label>
						    <span>
						        ${workbean.startWorkTime}
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 完成时间:</label>
						    <span>
						        ${workbean.finishWorkTime}
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 退回原因:</label>
						    <span>
						        ${workbean.workformBackContent}
						    </span>
						    <div class="clear"></div>
						</div>
					</div>
				</div>

				<!-- 遗留问题 -->
				<div class="tagContent selectTag flowmargin-top" id="YLWTINFODiv"
					style="display: none;">
					<div class="mian02">
						<div class="workform_list">
							<label><font color="red">*</font>问题描述:</label> <span> <input
								type="text" name="textfield2" id="historyproblemremark" />
							</span>
							<div class="clear"></div>
						</div>
						<div class="workform_list">
							<label>级 别：</label> <span> <select
								class="taskOrder_inputwidth" id="historyproblemleveal">
									<c:forEach items="${workbean.historyLevel.optionValue}"
										var="historybean">
										<option value="${historybean.optionId }">${historybean.optionName}</option>
									</c:forEach>
							</select>
							</span>
							<div class="clear"></div>
						</div>
						<div class="workform_list">
							<label>提交人：</label> <span> ${userName } </span>
							<div class="clear"></div>
						</div>
						<div class="workform_list">
							<label>状 态：</label> <span id="historystatus"> 未解决 </span>
							<div class="clear"></div>
						</div>
						<div class="work_task_inputAdd">
							<!-- <a class="btn1" onclick="addHistoryProblem()">添加1</a> 用超链接标签出现添加一次，产生两条记录的问题，现改用BUTTON -->
							<div class="box">
								<input type="button" id="historyProblemadd" value="添加" />
							</div>
							<div class="work_task_inputBox" id="historydiv">
								<c:forEach items="${workbean.equipmentHistoryProblemResultJson}"
									var="historybean" varStatus="status">
									<c:choose>
										<c:when test="${historybean.status eq '未解决' }">
											<div class='work_task_inputcontent'>
												<div class='task_box02'>
													<input type="hidden" id="historyProblemId"
														name="historyProblemId"
														value="${historybean.historyProblemId }" />
														<span>遗留问题${(status.index+1)}:</span>
														 <span>
														<label style="color:  blue;">描述: </label><input type="hidden"
														value="${historybean.problemRemark }" name="problemRemark"
														id="problemRemark" />${historybean.problemRemark }

													</span> <span><label style="color:  blue;">级别: </label><input type="hidden"
														value="${historybean.levelId}" name="levelId" /> <c:if
															test="${historybean.levelId == 1}">提醒</c:if> <c:if
															test="${historybean.levelId == 2}">警告</c:if> <c:if
															test="${historybean.levelId == 3}">严重</c:if> <input
														type="hidden" value="${historybean.problemContent}"
														name="problemContent" />

													</span> <span> <label style="color:  blue;">状态: </label><input type="hidden"
														value="${historybean.status }" name="status" id="status${status.index }" /><label
														id="status_label${status.index }">${historybean.status }</label>
													</span>
													<c:choose>
														<c:when
															test="${historybean.historyProblemId eq '' || historybean.historyProblemId eq null}">
														</c:when>
														<c:otherwise>
															<span id="dealContent_span${status.index }" style="display: none;">处理描述:<input
															type="text" value="${historybean.dealContent}"
																name="dealContent" id="dealContent${status.index }" class="dealContent"/></span>
															<div class="box" align="center">
																<input type="button" value="处理" class="window_btn" onclick="handleproblembtn(${status.index})" id="handleproblembtn${status.index }" style="font-size: 16px" /> 
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	<input type="button" value="取    消" class="window_btn" onclick="calcleproblembtn(${status.index})" id="cancleproblembtn${status.index }" style="font-size: 16px; display: none;" />
															</div>

														</c:otherwise>
													</c:choose>
												</div>
												<c:choose>
													<c:when
														test="${historybean.historyProblemId eq '' || historybean.historyProblemId eq null}">
														<%-- <input class="logistics_close" type='image' src='${ctx_path }/pub/images/delete.png' onclick='WS(this)' /> --%>
														<span class="logistics_close"><input type='image' src='${ctx_path }/pub/images/delete.png' onclick='WS(this)'/></span>
													</c:when>
													<c:otherwise>
													</c:otherwise>
												</c:choose>
												<div class="clear"></div>
											</div>
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>

								</c:forEach>
							</div>
						</div>
					</div>
				</div>

				<!-- 费用 -->
				<div class="tagContent selectTag flowmargin-top" id="FYINFODiv"
					style="display: none;">
					<div class="mian02">
						<div class="workform_list">
							<label><font color="red">*</font>费用项目:</label> <span> <select
								class="taskOrder_inputwidth" id="freeproject">
									<option value="">--请选择--</option>
									<c:forEach items="${workbean.feeType.optionValue}"
										var="feebean">
										<option value="${feebean.optionId }">${feebean.optionName}</option>
									</c:forEach>
							</select>
							</span>
							<div class="clear"></div>
						</div>
						<div class="workform_list">
							<label><font color="red">*</font>金额:</label> <span> <input
								type="number" name="freeamount" id="freeamount"  />
							</span>
							<div class="clear"></div>
						</div>
						<div class="workform_list">
							<label>姓名:</label> <span> <select
								class="taskOrder_inputwidth" id="freeperson">
									<c:forEach items="${workbean.followUser.optionValue}"
										var="flowbean">
										<option value="${flowbean.optionId }"
											<c:if test="${flowbean.optionName == workbean.engineerName}">selected="selected"</c:if>>
											${flowbean.optionName}</option>
									</c:forEach>
							</select>
							</span>
							<div class="clear"></div>
						</div>
						<div class="workform_list">
							<label>备注:</label> <span> <input type="text"
								name="textfield2" id="freeremark" />
							</span>
							<div class="clear"></div>
						</div>
						<div class="box">
							<input type="button" id="chargeadd" value="添加" /> 保存记录,请点击添加
						</div>
						<div class="work_task_inputBox" id="chargesdiv">
							<c:forEach items="${workbean.workFormFeeJson}" var="feebean">
								<div class='work_task_inputcontent'>
									<div class="task_box02">
										<span>
											<input type="hidden" name="feeId" value="${feebean.feeId }">
											<input type="hidden" name="feeName" value="${feebean.feeName }">项目:${feebean.feeName}
										</span>
										<span>
											<input type="hidden" name="togetherPersonId" value="${feebean.togetherPersonId }">
											<input type="hidden" name="togetherPersonName" value="${feebean.togetherPersonName }">
											姓名:${feebean.togetherPersonName }
										</span>
										<span>
											<input type="hidden" name="total" value="${feebean.total }">
											金额:${feebean.total }
										</span>
										<span>
											<input type="hidden" value="${feebean.remark }" name="feeremark">
											备注:${feebean.remark }
										</span>
									</div>
									<input class="logistics_close" type='image' src='${ctx_path }/pub/images/delete.png' onclick='WS(this)' />
									<div class="clear"></div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			
				<!-- 二維碼 -->
				<div class="tagContent selectTag flowmargin-top" id="EWMINFODiv"
					style="display: none;" align="center">
					<div class="mian02">
						<div class="workform_list" style="border: none;">
							<textarea
								style="width: 90%; height: 16em; background-color: #ffffff; text-align: center;"
								disabled="disabled" type="text" id="ewmMessage">请扫描二维码!</textarea>
						</div>

						<div class="box" align="center">
							<input type="button" value="扫描" class="window_btn"
								onclick="codeScan()"></input>
							<input type="button" value="上传" class="window_btn"
								onclick="codeSubmit()" style="background-color: #709E30; margin-left: 10px;"></input>
						</div>
						<div class="box" align="center" >
							<input type="button" value="测二维码"
								class="window_btn" onclick="codeScanTest()"
								style="background-color: #89C0ED;"></input>
						</div>
						
					</div>
				</div>
				
				<!-- 附件 -->
				<div class="tagContent selectTag flowmargin-top" id="IMGINFODiv"
					style="display: none;" align="center">
					<div class="mian02">
						<input name="is_exist_img" type="hidden" id="is_exist_img" value="1"/>
						<input type="button" value="添加图片" class="window_btn"  onclick="addImgs()" style="float:right; height: 30px;"></input>
						<div class="upload_box" style="display: none">
							<div class="upload_main single_main">
								<div id="preview" class="upload_preview">
									<div class="add_upload">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- 升级 -->
				<c:forEach items="${gradelist}" var="gradebean" varStatus="stas">
					<div class="tagContent selectTag flowmargin-top"
						id="${gradebean.taskType}${gradebean.taskId}Div"
						style="display: none;">
						<div class="mian02">
							<div class="workform_list">
								<label>完成情况：</label> 
								<span> 
								<input type="radio" name="${gradebean.taskType}finish${gradebean.taskId}" value="是" <c:if test="${gradebean.workIsFinish=='是' || gradebean.workIsFinish == null}"> checked="checked"</c:if> />是
								<input type="radio" name="${gradebean.taskType}finish${gradebean.taskId}" value="否" <c:if test="${gradebean.workIsFinish=='否'}"> checked="checked"</c:if> />否
								</span>
								<script type="text/javascript">
									stateAndInputs[stateAndInputs.length] = {
										inputName : '${gradebean.taskType}finish${gradebean.taskId}',
										state : '${gradebean.workIsFinish}',
										taskType : '${gradebean.taskType}${gradebean.taskId}'
									};
								</script>
								<div class="clear"></div>
							</div>
							<div class="workform_list">
								<label><font color="red">*</font>耗时(分钟):</label> <span> <input
									type="number" name="SJtime${gradebean.taskId}"
									id="SJ${gradebean.taskId}time" oninput="OnInput(event)"
									onpropertychange="OnPropChanged(event)"
									value="${gradebean.costTime}" />
								</span>
								<div class="clear"></div>
							</div>
							<div class="workform_list">
							   <label> 保修状态:</label> 
							   <span>${gradebean.taskAttributeOption.serverStatus.defaultName}</span>
							   <div class="clear"></div>
							</div>
							<div class="workform_list">
								<label>任务：</label> <span> ${gradebean.taskContent } </span>
								<div class="clear"></div>
							</div>
							<div class="workform_list">
								<label><font color="red">*</font>工作描述:</label> <span> <textarea
										name="SJworkRemark${gradebean.taskId}" cols="" rows=""
										class="work_task_order_row_textarea"
										id="SJ${gradebean.taskId}workRemark">${gradebean.workRemark}</textarea>
								</span>
								<div class="clear"></div>
							</div>
							<div class="box">
								<input name="" type="button" value="升级前" id="upgradebefore"
									onclick="gradeBeforeDialog('${ctx_path }/cp/upGrade/upGradeBefore.do?poNumber=${poNumber}&taskId=${gradebean.taskId}')" />
								<input name="" type="button" value="升级后" id="upgradeafter"
									onclick="gradeAfterDialog('${ctx_path }/cp/upGrade/upGradeAfter.do?poNumber=${poNumber}&taskId=${gradebean.taskId}')" />
							</div>
							<fieldset class="fieldset_style">
								<legend class="legend_font">
									<div id="task_replace_button_sj${gradebean.taskId}"
										class="task_replace">
										<span id="upgradespare${gradebean.taskId}"
											class="task_replace_button_unselected"
											onclick="upgradespareshow('${gradebean.taskId}')">模块替换</span>
										<span id="upgradeelement${gradebean.taskId}"
											class="task_replace_button_unselected"
											onclick="upgradeelementshow('${gradebean.taskId}')">零件替换</span>
										<c:if test="${gradebean.isRemove =='true'}">
											<span id="SJworkTaskMove${gradebean.taskId}"
												class="task_replace_button_unselected"
												onclick="SJworkTaskMoveShow('${gradebean.taskId}')">移除任务</span>
										</c:if>

										<!-- 隐藏域 用于计划任务任务移除的任务ID和任务内容 -->
										<input type="hidden" value="${gradebean.taskId}" id="SJtaskId${gradebean.taskId}"
											name="SJtaskId${gradebean.taskId}">
									</div>
								</legend>


								<div class="record_div" id="${gradebean.taskId}"
									style="display: none">
									<!--  modify by zt 20141224 .aim at controling the button  -->
									<c:choose>
										<c:when test="${null !=gIsEnable  && gIsEnable=='No'}">
										</c:when>
										<c:otherwise>
											<a class="logistics_add"
												onclick="oldspareDialog('${gradebean.taskId}')">
												<img class="logistics_add_icon"
												src="${ctx_path }/pub/images/icon_add.png" />添加
											</a>
											<!-- -source code is end======================= -->
										</c:otherwise>
									</c:choose>
									<!-- ======================================================end ================================= -->

									<c:forEach items="${gradebean.importModel}" var="taskspare">
										<div class='task_box02'>
											<span><font color='blue'>原物料:</font>${taskspare.oldSerialName}
												<input type='hidden' value="${taskspare.oldSerialName}"
												name="${gradebean.taskId}oldSerialName" /> <input
												type='hidden' value="${taskspare.oldSerialNumber}"
												name="${gradebean.taskId}oldSerialNumber" /> <input
												type='hidden' value="${taskspare.oldMaterialCode}"
												name="${gradebean.taskId}oldMaterialCode" /> <input
												type='hidden' value="${taskspare.oldHardwareVersion}"
												name="${gradebean.taskId}oldHardwareVersion" /> <input
												type='hidden' value="${taskspare.oldSoftwareVersion}"
												name="${gradebean.taskId}oldSoftwareVersion" /> <input
												type='hidden' value="${taskspare.equipmentConfigId}"
												name="${gradebean.taskId}equipmentConfigId" /> <input
												type='hidden' value="${taskspare.newSerialName}"
												name="${gradebean.taskId}newSerialName" /> <input
												type='hidden' value="${taskspare.newMaterialCode}"
												name="${gradebean.taskId}newMaterialCode" /> <input
												type='hidden' value="${taskspare.newSerialNumber}"
												name="${gradebean.taskId}newSerialNumber" /> <input
												type='hidden' value="${taskspare.newHardwareVersion}"
												name="${gradebean.taskId}newHardwareVersion" /> <input
												type='hidden' value="${taskspare.newSoftwareVersion}"
												name="${gradebean.taskId}newSoftwareVersion" /> </span> <span><font
												color='blue'>新物料:</font>${taskspare.newSerialName}</span>
											<!--  modify by zt 20141224 .aim at controling the button  -->
											<c:choose>
												<c:when test="${null !=gIsEnable  && gIsEnable=='No'}">
												</c:when>
												<c:otherwise>
													<input class="logistics_close" type='image'
														src='${ctx_path }/pub/images/delete.png' onclick='WSSpare(this)' />
													<!-- -source code is end======================= -->
												</c:otherwise>
											</c:choose>
											<!-- ======================================================end ================================= -->

										</div>
									</c:forEach>
								</div>
								<div class="record_div"
									id="${gradebean.taskId}upgradeElementDiv" style="display: none">
									<!--=====start===== modify by zt20141213 control the botton can not press  when the flow is return -->
									<c:choose>
										<c:when test="${null !=gIsEnable  && gIsEnable=='No'}">
										</c:when>
										<c:otherwise>
											<!--  ==============blow: the source code ==============-->
											<a class="logistics_add"
												onclick="newspareDialog('${gradebean.taskId}upgradeElementDiv')">
												<img class="logistics_add_icon"
												src="${ctx_path }/pub/images/icon_add.png" />添加
											</a>
											<!-- -source code is end======================= -->
										</c:otherwise>
									</c:choose>
									<!-- ======================================================end ================================= -->
									<c:forEach items="${gradebean.subModel}" var="taskelement">
										<div class='task_box02'>
											<span><font color='blue'>原物料:</font>${taskelement.oldSerialName}
												<input type='hidden' value="${taskelement.newSerialName}"
												name="${gradebean.taskId}upgradeElementDivnewSerialName" />
												<input type='hidden' value="${taskelement.oldSerialName}"
												name="${gradebean.taskId}upgradeElementDivoldSerialName" />
												<input type='hidden' value="${taskelement.quantity}"
												name="${gradebean.taskId}upgradeElementDivnums" /> <input
												type='hidden' value="${taskelement.newSerialNumber}"
												name="${gradebean.taskId}upgradeElementDivnewSerialNumber" />
												<input type='hidden' value="${taskelement.oldSerialNumber}"
												name="${gradebean.taskId}upgradeElementDivoldSerialNumber" />
												<input type='hidden'
												value="${taskelement.newSoftwareVersion}"
												name="${gradebean.taskId}upgradeElementDivnewSoftwareVersion" />
												<input type='hidden'
												value="${taskelement.newHardwareVersion}"
												name="${gradebean.taskId}upgradeElementDivnewHardwareVersion" />
												<input type='hidden' value="${taskelement.newMaterialCode}"
												name="${gradebean.taskId}upgradeElementDivnewMaterialCode" />
												<input type='hidden' value="${taskelement.oldMaterialCode}"
												name="${gradebean.taskId}upgradeElementDivoldMaterialCode" />
												<input type='hidden' value="${taskelement.useMethod}"
												name="${gradebean.taskId}upgradeElementDivuseMethod" /> </span> <span><font
												color='blue'>新物料:</font>${taskelement.newSerialName}</span> <span><font
												color='blue'>数量:</font>${taskelement.quantity}</span>

											<!--=====start===== modify by zt20141213 control the botton can not press  when the flow is return -->
											<c:choose>
												<c:when test="${null !=gIsEnable  && gIsEnable=='No'}">
												</c:when>
												<c:otherwise>
													<!--  ==============blow: the source code ==============-->
													<input class="logistics_close" type='image'
														src='${ctx_path }/pub/images/delete.png' onclick='WSSpare(this)' />
													<!-- -source code is end======================= -->
												</c:otherwise>
											</c:choose>
											<!-- ======================================================end ================================= -->
										</div>
									</c:forEach>
								</div>
							</fieldset>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="nav_bottom">
			<a class="nav_bottom_back" onclick="saveworkDetail();">保存 </a>
			<a class="nav_operation" onclick="workorderSend();"> 提交</a> 
		</div>
	</form>
<!-- 临时js -->
<script type="text/javascript">
var ref=function(){
	location.reload(true);
}
var aaa=function(){
	layer.alert("ttt", {closeBtn: true});
}
</script>
<script type="text/javascript" src="${ctx_path }/pub/js/page/workorder/taskOrderList.js?v=1.1"></script>
</body>
</html>