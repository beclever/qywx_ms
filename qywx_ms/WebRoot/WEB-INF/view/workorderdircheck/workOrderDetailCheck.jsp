<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>工单基本信息</title>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=pNwGHcggmOgWCnAspAkRLsyo"></script>
<script type="text/javascript">
	var taskJsonList = '${workdetail.taskJsonList}';
	//用于判断是否更新序列号的标记
	var workFormStatus = '${workdetail.workFormStatus}';
	var flags = '${flags}';
	// =========================================
	var pageLatitude = "";
	var pagelongitude = "";
	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				pageLatitude = position.coords.latitude;
				pagelongitude = position.coords.longitude;

			});
		} else {
			layer.alert("浏览器不支持");
		}
	}

	// ===========================================
	//联系人电话
	function changePhImg() {//没有号码的置灰色并移出事件
		var phoneNum = $("#atmManagerTel").text();
		if (phoneNum.trim().length == 0) {
			$("#phImg").attr("src", "images/phone_gray.png");
			$("#phImg").removeAttr("onclick");
		}
	}
	document.addEventListener("deviceready", changePhImg, false);

	//调度员电话
	function changeDispatchTelImg() {//没有号码的置灰色并移出事件
		var phoneNum = $("#dispatchTel").val();
		if (phoneNum.trim().length == 0) {
			$("#dispatchTelImg").attr("src", "images/phone_gray.png");
			$("#dispatchTelImg").removeAttr("onclick");
		}
	}
	document.addEventListener("deviceready", changeDispatchTelImg, false);

	function startMap2() {
		document.getElementById('showInfo').style.display="none";
		
		document.getElementById('gpsInfo').style.display="";
		var addrName = "${workdetail.installAddress}";//目标点地理名称 
		var latitude = $("#latitude").val();
		//目标点的纬度 
		var longitude = $("#longitude").val();
		//目标点的经度 
		if (null != addrName && "" != (addrName)) {
			//如果两个条件都成立，则执行最优选择 
			/**cordova.exec(function(msg) {
			}, function(msg) {
				layer.alert(msg)
			}, 'BaiduMapPlugin', 'ROUT_GPSLOCATION_NAME', [ addrName ]);**/
			//getLocation();
		window.location.href="<%=basePath%>qywx/jsp/web/outJsp/baiduPosition.jsp?addrName="+addrName;
		} else if (null != latitude && null != longitude) {
			
			/**cordova.exec(function(msg) {
			}, function(msg) {
				layer.alert(msg)
			}, 'BaiduMapPlugin', 'ROUT_GPSLOCATION_LATITUDE', [ latitude,
					longitude ]);**/
					//getLocation();
			window.location.href="<%=basePath%>qywx/jsp/web/outJsp/baiduPosition.jsp?longitude="
					+ longitude + "&latitude=" + latitude;
		} else {
			layer.alert("您的位置出错了...");
		}
	}
	// end
</script>
</head>
<body>
	<!--顶部 -->
	<div class="nav_bottom">
		<a class="nav_operation" id="send4""> 详情 </a>
	</div>

	<div class="mian02" id="showInfo">
	    <!-- 隐藏域 -->
	    <input type="hidden" id="latitude" value="">
	    <input type="hidden" id="longitude" value="">
	    <input type="hidden" id="location" value="">
	    <div class="workform_list">
	        <label>工单编号：</label>
	        <span>${workdetail.poNumber} <input type="hidden" value="${workdetail.poNumber}" id="poNumber"/></span>
	        <div class="clear"></div>
	        <input type="hidden" id="workformId" value="${ workdetail.workformId}"/>
	        <input type="hidden" value="${workdetail.equipmentId }"  id="equipmentId"/>
	        <input type="hidden" value="${workdetail.dispatchTel }"  id="dispatchTel"/>
	    </div>
	    <div class="workform_list">
	        <label>序列号：</label>
	        <span>
	            <input type="text" name="textfield" class="lleft input_style1" value="${workdetail.serialNumber}" id="serialNumber" disabled="disabled"/>
	            <!-- 点击录单之后，返回不显示和工单状态为'已退回'的不显示 -->
	        	<c:if test="${flags!='1' && workdetail.workFormStatus !='已退回'}"><a class="btn1 right" id="search" disabled="disabled">更新</a></c:if>
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>整机型号：</label>
	        <span id="equipmentModel">${workdetail.equipmentModel } </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>网点名称：</label>
	        <span id="branchName">${workdetail.branchName}</span><div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>安装地址：</label>
	        <span id="installAddress">${workdetail.installAddress}</span>
	        <div class="show_icon_map" ><img onclick="startMap2();" src="<%=basePath%>/images/map.png"/></div>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>工程师：</label>
	        <span>${workdetail.engineerName}</span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>联系人：</label>
	        <span id="atmManager">${workdetail.atmManager}</span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>联系电话：</label>
	        <span id="atmManagerTel">${workdetail.atmManagerTel}</span>
	        <div class="clear"></div>
	        <div class="show_icon_phone"><a href="tel:${workdetail.atmManagerTel}" ><img id="phImg" src="images/phone.png" /></a></div>
	    </div>
	    <div class="workform_list">
	        <label>预约时间：</label>
	        <span>${workdetail.appointmentDate}</span>
	        <div class="clear"></div>
	    </div>
	    
	    <div class="workform_list">
	        <label>调度员：</label>
	        <span>${workdetail.dispatchName}</span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>调度员电话:</label>
	        <span>${workdetail.dispatchTel}</span>
	        <div class="clear"></div>
	        <div class="show_icon_phone"><a href="tel:${workdetail.dispatchTel}" ><img id="dispatchTelImg" src="images/phone.png" /></a></div>
	    </div>
	    <div class="workform_list">
	        <label>调度建议：</label>
	        <span>${workdetail.advice}</span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>任务内容：</label>
	        <span>
	            ${workdetail.taskContent}
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>所需备件：</label>
	        <span>
	            ${workdetail.sptRemark }
	        </span>
	        <div class="clear"></div>
	    </div>
	    <!-- <div class="workform_list">
			      <label>同行人员：<input type="hidden" value="${workdetail.togetherPerson.userId }" id="workpersons"/></label>
			      <span>
			     	<c:forEach var="perbean" items="${personlist}">
			    	 <input type="checkbox" name="lists" value="${perbean.userId }"/><label>${perbean.name}</label>
			      </c:forEach>
			      </span><div class="clear"></div> 
		      </div>	-->
	    <div class="workform_list">
	    <input type="hidden" value="${workdetail.togetherPerson.name }" id="workpersonName"/>
	        <label>同行人员：<input type="hidden" value="${workdetail.togetherPerson.userId }" id="workpersons"/></label>
	        <span>
	           ${workdetail.togetherPerson.name }
	        </span>
	        <div class="clear"></div>
	    </div>
	     <div class="workform_list">
	        <label>退回原因：</label>
	        <span id="atmManager">${workdetail.workformBackContent}</span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	       	<input type="hidden" id="uploadtimeUrl" name="uploadtimeUrl" value="<%=basePath%>action/client/uploadtime?formType=qywx" />
	        <div class="progress">
		        <ul>
		            <li><a  id="acceptTime1" ><font class="paddingLeft3px">接受</font></a></li><input type="hidden" value="${workdetail.acceptTime}" id="acceptTime" />
		            <li><a  id="leaveTime1"><font class="paddingLeft3px">出发</font></a></li><input type="hidden" value="${workdetail.leaveTime}" id="leaveTime" />
		            <li><a  id="arriveTime1"><font class="paddingLeft3px">到达</font></a></li><input type="hidden" value="${workdetail.arriveTime }" id="arriveTime" />
		            <li><a  id="startWorkTime1"><font class="paddingLeft3px">开始</font></a></li><input type="hidden" value="${workdetail.startWorkTime}" id="startWorkTime"  />
		            <li><a  id="finishWorkTime1"><font class="paddingLeft3px">完成</font></a></li><input type="hidden" value="${workdetail.finishWorkTime}" id="finishWorkTime" name="finishWorkTime"/>
		       		<div class="clear"></div>
		       </ul>
	       </div>
	        <div class="progress_content">
	        	<ul>
		            <li>${workdetail.acceptTime}</li>
		            <li>${workdetail.leaveTime }</li>
		            <li>${workdetail.arriveTime }</li>
		            <li>${workdetail.startWorkTime }</li>
		            <li>${workdetail.finishWorkTime }</li>
		            <div class="clear"></div>
		        </ul>   
	        </div>
	    </div>
	    <div class="workform_list">
	        <label><font color="red">*</font>满意度：</label>
	        <span>

	            <select id="satisfaction" disabled="disabled">
	                <option value="" disabled="disabled"></option>
	                <c:forEach items="${workdetail.degreeSatisfaction.optionValue}" var="beanoption">
	                    <option  <c:if test="${defaultId==beanoption.optionId}">selected=true</c:if>  value="${beanoption.optionId }">${beanoption.optionName}</option>
	                </c:forEach>
	            </select>
	        </span>
	        <div class="clear"></div>
	    </div>
	</div>
	<script type="text/javascript" src="<%=basePath%>qywx/js/sa/workOrderDetailDirectorCheck.js"></script>
	<div id="gpsInfo" style="display: none">
		<div id="status" style="text-align: center"></div>
		<div style="width: 600px; height: 480px; border: 1px solid gray; margin: 30px auto" id="container"></div>
	</div>
</body>
</html>
