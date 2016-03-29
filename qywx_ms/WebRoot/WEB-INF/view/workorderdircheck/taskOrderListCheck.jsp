<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>工单办理</title>
<!-- 微信js -->
<%@ include file="../include/_jsapi.jsp"%>
<script type="text/javascript">
	var workStartTime = "${workbean.startWorkTime}";
	var workFinishTime = "${workbean.finishWorkTime}";
	var equipmentId = "${workbean.equipmentId}";
	var workformId = "${workbean.workformId}";
	var poNumber = "${workbean.poNumber}";
	var type = '${type}';
	var stateAndInputs = [];
	
	$(function(){
		//附件信息
		var imgs = '${imgs}';
		var imgList = eval('(' + imgs + ')');
		//组织预览html
		funDealtPreviewHtml(imgList,false);
		//prviewImg(imgList); 
	});
</script>

<!-- 附件处理 -->
<script src="${ctx_path }/pub/js/page/workorder/taskOrderImageHanddle.js"></script>
<link rel="stylesheet" href="${ctx_path }/pub/css/zyUpload.css" type="text/css">

<style type="text/css">
a.select_new:link {
	background: "#3695E2" ) no-repeat center center;
	background-size: 90px 60px;
}

a.select_new:visited {
	background: "#3695E2" ) no-repeat center center;
	background-size: 90px 60px;
}

a.select_new:hover {
	background-size: 90px 60px;
}

a.select_new:active {
	background: "#3695E2" no-repeat center center;
	background-size: 90px 60px;
}
.select_new{color:#333333;}
.select_content_new{border-left:#D0CEC7 1px solid;border-top:#D0CEC7 1px solid}
</style>
<jsp:include page="/pub/js/mobiscroll/mobiscroll_theme.jsp"></jsp:include>
</head>
<body>
	<!--底部 -->
	<div class="nav_bottom">
		<a class="nav_bottom_back" onclick="backNavMenu(equipmentId,workformId,poNumber);">返回</a>
		<div>
			<a class="right button_cz_right " onclick="show_img()" style="width: 90px"> 审核 </a>
			<div class="right select_content_new" id="select_content_new"
				align="center" style="width: 90px; background:#ffffff;position:absolute;right:0px;top:-91px;display:none">
				<a data-role="none" class="select_new" onclick="orderPassConfig();">
					<font size="2" style="font-size: small;">审核通过</font>
				</a>
				<div style="background:#D0CEC7;width:90px;height:1px"></div>
				<a data-role="none" class="select_new" onclick="orderReturnResult();">
					<font size="2" style="font-size: small;"> 工单退回</font>
				</a>
			</div>
		</div>
	</div>
	<!-- 隐藏域 -->
	<input type="hidden" value="${ctx_path }" id="urls">
	<input type="hidden" value="${serialNumber}" id="serialNumber"
		name="serialNumber">
	<input type="hidden" value="${poNumber}" id="poNumber" name="poNumber">
	<input type="hidden" value="${workbean.workformId}" id="workformId"
		name="workformId">
	<!-- 用于提示(完成时间减去开始时间)和所有任务单总工时的检测 -->
	<input type="hidden" value="${workbean.startWorkTime}"
		id="startWorkTime" name="startWorkTime" />
	<input type="hidden" value="${workbean.finishWorkTime}"
		id="finishWorkTime" name="finishWorkTime" />

	<!-- 左边菜单栏 -->
	<div id="taskOrder_nav">
		<ul style="padding-bottom:3em;">
			<li><a id="ZZGDINFO" href="javascript:void(0)"><span
					class="min_width">纸质工单</span></a></li>
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
		<div id="boxContent">
			<c:forEach items="${tasklist}" var="taskbean">
				<!-- 其他 -->
				<c:if test="${taskbean.taskType=='QT'}">
					<div class="tagContent selectTag flowmargin-top"
						id="${taskbean.taskType}Div" style="display: none;">
						<%@ include file="task_qt_check.jsp"%>
					</div>
				</c:if>

				<!-- 移机开通 -->
				<c:if test="${taskbean.taskType=='YJKT'}">
					<div class="tagContent selectTag flowmargin-top"
						id="${taskbean.taskType}Div" style="display: none;">
						<%@ include file="task_yjkt_check.jsp"%>
					</div>
				</c:if>

				<!-- 客户培训 -->
				<c:if test="${taskbean.taskType=='PX'}">
					<div class="tagContent selectTag flowmargin-top"
						id="${taskbean.taskType}Div" style="display: none;">
						<%@ include file="task_khpx_check.jsp"%>
					</div>
				</c:if>

				<!-- 账务处理 -->
				<c:if test="${taskbean.taskType=='ZW'}">
					<div class="tagContent selectTag flowmargin-top"
						id="${taskbean.taskType}Div" style="display: none;">
						<%@ include file="task_zwcl_check.jsp"%>
					</div>
				</c:if>

				<!-- 移机辅助 -->
				<c:if test="${taskbean.taskType=='YJFZ'}">
					<div class="tagContent selectTag flowmargin-top"
						id="${taskbean.taskType}Div" style="display: none;">
						<%@ include file="task_yjfz_check.jsp"%>
					</div>
				</c:if>

				<!-- 安装辅助 -->
				<c:if test="${taskbean.taskType=='AZFZ'}">
					<div class="tagContent selectTag flowmargin-top"
						id="${taskbean.taskType}Div" style="display: none;">
						<%@ include file="task_azfz_check.jsp"%>
					</div>
				</c:if>

				<!-- 安装开通 -->
				<c:if test="${taskbean.taskType=='AZKT'}">
					<div class="tagContent selectTag flowmargin-top"
						id="${taskbean.taskType}Div" style="display: none;">
						<%@ include file="task_azkt_check.jsp"%>
					</div>
				</c:if>

				<!-- 维修 -->
				<c:if test="${taskbean.taskType=='WX'}">
					<div class="tagContent selectTag flowmargin-top"
						id="${taskbean.taskType}Div" style="display: none;">
						<%@ include file="task_wx_check.jsp"%>
					</div>
				</c:if>
				<!-- 巡检 -->
				<c:if test="${taskbean.taskType=='XJ'}">
					<input type="hidden" value="${taskbean.taskId}" id="XJ${taskbean.taskId}taskId">
					<div class="tagContent selectTag flowmargin-top"
						id="${taskbean.taskType}${taskbean.taskId}Div" style="display: none;">
						<%@ include file="task_xj_check.jsp"%>
					</div>
				</c:if>

				<!--保养 -->
				<c:if test="${taskbean.taskType=='BY'}">
					<!-- 隐藏域 用于计划任务保养任务移除的任务ID和任务内容 -->
					<input type="hidden" value="${taskbean.taskId}"
						id="${taskbean.taskType}taskId" name="BYtaskId">
					<input type="hidden" value="${taskbean.taskType}"
						id="${taskbean.taskType}taskType" name="BYtaskType">

					<div class="tagContent selectTag flowmargin-top"
						id="${taskbean.taskType}Div" style="display: none;">
						<%@ include file="task_by_check.jsp"%>
					</div>
				</c:if>
			</c:forEach>
			<%-- <div class="tagContent selectTag flowmargin-top" id="DATETIMEDiv"
				style="display: none;">
				<div class="mian02">
					<div class="workform_list">

						<label>预约时间:</label> <span><input type="text"
							id="timeAppointmentDate" value="${workbean.appointmentDate}"
							onclick="showDatePicker('timeAppointmentDate');"
							disabled="disabled"> </span>
						<div class="clear" style="margin-top: 20px"></div>

						<label>到达时间:</label> <span><input type="text"
							id="timeArriveTime" value="${workbean.arriveTime}"
							onclick="showDatePicker('timeArriveTime');"> </span>
						<div class="clear" style="margin-top: 20px"></div>
						<label>开始时间:</label> <span><input type="text"
							onclick="showDatePicker('timeStartWorkTime');"
							id="timeStartWorkTime" value="${workbean.startWorkTime}">
						</span>
						<div class="clear" style="margin-top: 20px"></div>
						<label>完成时间:</label> <span><input type="text"
							onclick="showDatePicker('timeFinishWorkTime');"
							id="timeFinishWorkTime" value="${workbean.finishWorkTime}">
						</span>
						<div class="clear"></div>
					</div>

				</div>
			</div> --%>
			<!-- 纸质工单 -->
			<div class="tagContent selectTag flowmargin-top" id="ZZGDINFODiv"
				style="display: none;">
				<%-- <div class="mian02">
					<div class="workform_list">
						<label><font color="red">*</font>纸质工单:</label>
						<span>
							<label type="hidden" name="paperId" id="paperId" value="${workbean.iniPaperId}"></label>
							<input type="hidden" name="paperStatus" id="paperStatus" value="${workbean.intPaperStatus}" />
							<span name="paperCode" id="paperCode">${workbean.iniPaperCode}</span>
						</span>
						<div class="clear"></div>
					</div>
				</div> --%>
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
							<label><font color="red">*</font>纸质工单:</label> 
							<span> ${workbean.iniPaperCode}</span>
							<div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label><font color="red">*</font>满意度:</label>
						    <span>${workbean.degreeSatisfaction.defaultName }
							    <%-- <select class="taskOrder_inputwidth" id="degreeSatisfaction" name='degreeSatisfaction'>
										<c:forEach items="${workbean.degreeSatisfaction.optionValue}"
											var="beanoption">
											<option
												<c:if test="${workbean.degreeSatisfaction.defaultId==beanoption.optionId}">selected=true</c:if>
												value="${beanoption.optionId },${beanoption.optionName }">${beanoption.optionName}</option>
										</c:forEach>
								</select> --%>
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label>同行人员:</label>
						    <span>${workbean.togetherPerson.name}
							    <%-- <select class="taskOrder_inputwidth" id="togetherPerson" name='togetherPerson' >
							    	<option value=""></option>
							    	<c:forEach items="${togetherPersonList}" var="item">
										<option
											<c:if test="${workbean.togetherPerson.userId==item.userId}">selected=true</c:if>
											value="${item.userId },${item.name }">${item.name}</option>
									</c:forEach>
								</select> --%>
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
						<%-- <div class="workform_list">
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
						</div> --%>
						<%-- <div class="workform_list">
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
						</div> --%>
						<div class="workform_list" style="display: none">
						    <label> 出发时间:</label>
						    <span>
						        <input type="text" id="leaveTime" value="${workbean.leaveTime}">
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 报修时间:</label>
						    <span>
						        <input type="text" id="timeReportTime" value="${workbean.reportTime}" onfocus="showAllDateTime($(this));">
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 预约时间:</label>
						    <span>
						        <input type="text" onfocus="showAllDateTime($(this));" id="timeAppointmentDate" value="${workbean.appointmentDate}">
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 到达时间:</label>
						    <span>
						        <input type="text" id="timeArriveTime" value="${workbean.arriveTime}" onfocus="showAllDateTime($(this));">
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 开始时间:</label>
						    <span>
						        <input type="text" onfocus="showAllDateTime($(this));" id="timeStartWorkTime" value="${workbean.startWorkTime}">
						    </span>
						    <div class="clear"></div>
						</div>
						<div class="workform_list">
						    <label> 完成时间:</label>
						    <span>
						        <input type="text" onfocus="showAllDateTime($(this));" id="timeFinishWorkTime" value="${workbean.finishWorkTime}">
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

			<!-- 二維碼 -->
			<div class="tagContent selectTag flowmargin-top" id="EWMINFODiv"
				style="display: none;" align="center">
				<div class="mian02">
					<div class="workform_list" style="border: none;">
						<textarea
							style="width: 90%;height: 16em;background-color: #ffffff;text-align: center;"
							disabled="disabled" type="text" id="ewmMessage"></textarea>
						<!--   <input type="button" value="扫描上传" style="margin-top: 20px;padding-right: 10px;padding-left: 10px;padding-bottom: 5px;padding-top: 5px;" align="middle" onclick="codeScan()"></input>
		        <input type="button" value="测二维码" style="margin-top: 20px;margin-left: 10px;padding-right: 10px;padding-left: 10px;padding-bottom: 5px;padding-top: 5px;" align="middle" onclick="codeScanTest()"></input>
 -->
					</div>
				</div>
			</div>
			
			<!-- 附件 -->
			<div class="tagContent selectTag flowmargin-top" id="IMGINFODiv"
					style="display: none;" align="center">
					<div class="mian02">
						<input name="is_exist_img" type="hidden" id="is_exist_img" value="1"/>
								<div class="upload_box">
							<div class="upload_main single_main">
								<div id="preview" class="upload_preview">
								<div class="add_upload">
								</div>
								</div>
							</div>
							</div>
					</div>
				</div>

			<!-- 遗留问题 -->
			<div class="tagContent selectTag flowmargin-top" id="YLWTINFODiv"
				style="display: none;">
				<div class="mian02">
					<div class="work_task_inputAdd">
						<div class="work_task_inputBox" id="historydiv">
							<c:forEach items="${workbean.equipmentHistoryProblemResultJson}"
								var="historybean">
								<div class='work_task_inputcontent'>
									<div class='task_box02'>
										<input type="hidden" id="historyProblemId"
											name="historyProblemId"
											value="${historybean.historyProblemId }" /> <span>
											描述:<input type="hidden" value="${historybean.problemRemark }"
											name="problemRemark" id="problemRemark" />${historybean.problemRemark
											}
										</span> <span>级别: <input type="hidden"
											value="${historybean.levelId}" name="levelId" /> <c:if
												test="${historybean.levelId == 1}">提醒</c:if> <c:if
												test="${historybean.levelId == 2}">警告</c:if> <c:if
												test="${historybean.levelId == 3}">严重</c:if> <input
											type="hidden" value="${historybean.problemContent}"
											name="problemContent" />
										</span> <span> 状态:<input type="hidden"
											value="${historybean.status }" name="status" />${historybean.status
											}
										</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<!-- 费用 -->
			<div class="tagContent selectTag flowmargin-top" id="FYINFODiv"
				style="display: none;">
				<div class="mian02">
					<div class="work_task_inputBox" id="chargesdiv">
						<c:forEach items="${workbean.workFormFeeJson}" var="feebean">
							<div class='work_task_inputcontent'>
								<div class="task_box02">
									<span> <input type="hidden" name="feeId"
										value="${feebean.feeId }"> <input type="hidden"
										name="feeName" value="${feebean.feeName }">项目:${feebean.feeName
										}
									</span> <span> <input type="hidden" name="togetherPersonId"
										value="${feebean.togetherPersonId }"> <input
										type="hidden" name="togetherPersonName"
										value="${feebean.togetherPersonName }">
										姓名:${feebean.togetherPersonName }
									</span> <span> <input type="hidden" name="total"
										value="${feebean.total }">金额:${feebean.total }
									</span> <span> <input type="hidden" value="${feebean.remark }"
										name="feeremark"> 备注:${feebean.remark }
									</span>
								</div>
							</div>
						</c:forEach>
						<c:if test="${workbean.workFormFeeJson == null || 0 == fn:length(workbean.workFormFeeJson)}">
							<div>无费用信息</div>
						</c:if>
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
							<label>完成情况：</label> <span> <input type="radio"
								disabled="disabled"
								name="${gradebean.taskType}finish${gradebean.taskId}" value="是" <c:if test="${gradebean.workIsFinish=='是' || gradebean.workIsFinish == null}"> checked="checked"</c:if> >是
								<input type="radio"
								name="${gradebean.taskType}finish${gradebean.taskId}" value="否"
								disabled="disabled" <c:if test="${gradebean.workIsFinish=='否'}"> checked="checked"</c:if> >否
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
							<label><font color="red">*</font>耗时(分钟):</label> <span> ${gradebean.costTime}
							</span>
							<div class="clear"></div>
						</div>
						<div class="workform_list">
							<label>任务：</label> <span> ${gradebean.taskContent } </span>
							<div class="clear"></div>
						</div>
						<div class="workform_list">
							<label><font color="red">*</font>工作描述:</label> <span>${gradebean.workRemark}
							</span>
							<div class="clear"></div>
						</div>
						<div class="box">
							<input name="" type="button" value="升级前" id="upgradebefore"
									onclick="gradeBeforeDialog('${ctx_path }/cp/upGrade/upGradeBefore.do?poNumber=${poNumber}&taskId=${gradebean.taskId}')" />
								<input name="" type="button" value="升级后" id="upgradeafter"
										onclick="gradeAfterDialog('${ctx_path }/cp/upGrade/upGradeAfter.do?poNumber=${poNumber}&taskId=${gradebean.taskId}')" />
						</div>
						<!--
		                <input name=""  class="btn3 line_height2" type="button" value="模块替换" id="${gradebean.taskId}upgradespare" onclick="upgradespareshow(this)"/>
		                <input name=""  class="btn3 line_height2" type="button" value="零件替换" id="${gradebean.taskId}upgradeelement" onclick="upgradeelementshow(this)"/>
		                -->
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

									<!-- 隐藏域 用于计划任务任务移除的任务ID和任务内容 -->
									<input type="hidden" value="${gradebean.taskId}" id="SJtaskId"
										name="SJtaskId">
								</div>
							</legend>


							<div class="record_div" id="${gradebean.taskId}"
								style="display: none">
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
											color='blue'>新物料:</font>${taskspare.newSerialName}</span> <input
											class="logistics_close" type='image'
											src='${ctx_path }/pub/images/delete.png' onclick='WSSpare(this)' />
									</div>
								</c:forEach>
							</div>
							<div class="record_div" id="${gradebean.taskId}upgradeElementDiv"
								style="display: none">
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
											color='blue'>数量:</font>${taskelement.quantity}</span> <input
											class="logistics_close" type='image'
											src='${ctx_path }/pub/images/delete.png' onclick='WSSpare(this)' />
									</div>
								</c:forEach>
							</div>
						</fieldset>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script type="text/javascript" src="${ctx_path }/pub/js/iscroll.js"></script>
	<script type="text/javascript" src="${ctx_path }/pub/js/page/workorderdircheck/taskOrderListDirectorCheck.js?v=20160303"></script>
</body>
</html>