<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>已派工单详情</title>
<%@ include file="../include/_jsapi.jsp"%>
<style>
	.loading_title{
		margin-top: 5px;
	}
</style>
</head>
<body class="gdlb">
	<div class="nav_bottom">
		<a class="nav_bottom_back" onclick="goBack()">返回
	    </a>
	</div>
	<div class="subject">
		<div class="samian">
			<div class="wrapper">
				<div id="thelist">
					<div class="gdlb_list">
						<section class="gdlb_info">
							工单编号：${workdetail.poNumber }<br />
							工程师：${workdetail.engineerName }（${workdetail.engineerMobilephone}）<br />
							<div class="loading_title">序&nbsp;&nbsp;列&nbsp;&nbsp;号：</div>
							<div class="sc_body">
								<input class="input_line" type="text" id="serialNumber" style="font-size: 1.1em;"
									value="${workdetail.serialNumber }">
								<c:if test="${flag==1}">
									<span class="s_btnbg" id="s_btn"></span>
								</c:if>	
								<input type="hidden" value="${flag}" id="isCanUpdateSerialNumber"/>
							</div>
							<div class="loading_title">网点名称：</div>
							<c:if test="${workdetail.branchName == undefined}">
							   <br/>
							</c:if>
							<div class="sc_body">
								<span id="branchName">${workdetail.branchName }</span>
							</div>
							<div class="loading_title">安装地址：</div>
							<c:if test="${workdetail.installAddress == undefined}">
							   <br/>
							</c:if>
							<div class="sc_body padding30 sc_body_info">
							    <span id="map_btn" class="map_btnbg"></span>
								<span id="installAddress">${workdetail.installAddress }</span> 
							</div>
							报&nbsp;&nbsp;修&nbsp;&nbsp;人：${workdetail.repairsManName }<br />
							手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：${workdetail.repairsMoblie }<br />
							固 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：${workdetail.repairsTelephone }<br />
						</section>
					</div>
					<div class="gdlb_list">
						<section class="gdlb_info">
							<div class="loading_title">接&nbsp;&nbsp;待&nbsp;&nbsp;人：</div>
							<div class="sc_body">
								<input class="input_line" readonly="readonly" style="font-size: 1.1em;" type="text" id="manName" value="${workdetail.receiveManName }">
								<!-- <span class="s_user" id="receiver" ></span> -->
							</div>
							<div class="loading_title">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</div>
							<div class="sc_body">
								<input class="input_line" readonly="readonly" style="font-size: 1.1em;" id="mobile" type="text" value="${workdetail.receiveMoblie }">
							</div>
							<div class="loading_title">固&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</div>
							<div class="sc_body">
								<input class="input_line" readonly="readonly" style="font-size: 1.1em;" id="telephone" type="text" value="${workdetail.receiveTelephone }">
							</div>
							<div class="loading_title">预约时间：</div>
							<div class="sc_body">
							    <span id="appointmentDate">${workdetail.appointmentDate }</span>
							</div>
							任务描述：${workdetail.taskContent }<br />
						</section>
						<div class="gl_foot">
							<span id="accept" class="modal_ok" style="background: #4286f5; color: #fff;">接受</span>
						</div>
						<section class="gdlb_info gdlb_list2">
							<div class="loading_title">
								<img src="${ctx_path}/pub/images/ustar.png" class="ustar" />
							</div>
							<div class="sc_body">
								<span class="input_line" id="leaveText"></span><input
									type="submit" id="chufa" class="kaishi" value="出发">
							</div>
							<div class="loading_title">
								<img src="${ctx_path}/pub/images/car.png" class="ustar" />
							</div>
							<div class="sc_body">
								<span class="input_line" id="arriveText"></span><input
									type="submit" class="kaishi" id="daoda" value="到达">
							</div>
							<div class="loading_title">
								<img src="${ctx_path}/pub/images/ustar.png" class="ustar" />
							</div>
							<div class="sc_body">
								<span class="input_line" id="startText"></span><input
									type="submit" class="kaishi" id="kaishi" value="开始">
							</div>
							<div class="loading_title">
								<img src="${ctx_path}/pub/images/ustar.png" class="ustar" />
							</div>
							<div class="sc_body">
								<span class="input_line" id="finishText"></span><input
									type="submit" class="kaishi" id="wancheng" value="结束">
							</div>
							<input type="hidden" name="workformId" id="workformId"
								value="${workdetail.workformId }">
							<!-- ATM管理员 -->
							<input type="hidden" name="atmManager" id="atmManager" value="${workdetail.atmManager }">
							<input type="hidden" name="atmManagerTel" id="atmManagerTel" value="${workdetail.atmManagerTel }">
							
							<!-- 整机型号 -->
							<input type="hidden" name="equipmentModel" id="equipmentModel" value="${workdetail.equipmentModel }">
							
							<!-- 满意度 -->
							<input type="hidden" name="defaultId" id="defaultId" value="${workdetail.degreeSatisfaction.defaultId }">
							<input type="hidden" name="defaultName" id="defaultName" value="${workdetail.degreeSatisfaction.defaultName }">
							
							<input type="hidden" name="equipmentId" id="equipmentId" value="${workdetail.equipmentId }">
							<input type="hidden" name="poNumber" id="poNumber" value="${workdetail.poNumber }">
								 <input type="hidden"
								name="latitude" id="latitude"
								value="${workdetail.deviceLocation.latitude }"> <input
								type="hidden" name="longitude" id="longitude"
								value="${workdetail.deviceLocation.longitude}"> <input
								type="hidden" name="acceptTime" id="acceptTime"
								value="${workdetail.acceptTime }"> <input type="hidden"
								name="leaveTime" id="leaveTime" value="${workdetail.leaveTime }">
							<input type="hidden" name="arriveTime" id="arriveTime"
								value="${workdetail.arriveTime }"> <input type="hidden"
								name="startWorkTime" id="startWorkTime"
								value="${workdetail.startWorkTime }"> <input
								type="hidden" name="finishWorkTime" id="finishWorkTime"
								value="${workdetail.finishWorkTime }">
						</section>
					</div>
					<div class="gdlb_list">
					
						<section class="gdlb_info" style="color: red;">
							<div class="loading_title">预警信息：</div>
							<div class="sc_body">
								<span id="branchName">${warningCtrl }</span>
							</div>
						</section>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>
	<script src="${ctx_path }/pub/js/page/workorderSendManage/sentOrderDetail.js"></script>
</body>
</html>