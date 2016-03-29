<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>待派工单</title>
	<!-- 微信js -->
	<%@ include file="../include/_jsapi.jsp"%>
    <style type="text/css">
    .plan_list {
	  zoom: 1;
	  margin: 0em 0.5em;
	  padding: 0.5em 0em;
	  font-size: 1.2em;
	  display: block;
	  line-height: 150%;
	  position: relative;
	  border-bottom: 1px solid #e7e7e7;
	}
	.plan_list label {
	  width: 20%;
	  float: left;
	  color: #777777;
	  line-height: 150%;
	  text-align: center;
	  display: block;
	}
	.plan_list span {
	  width: 80%;
	  float: left;
	  color: #333;
	  display: block;
	  line-height: 120%;
	  vertical-align: middle;
	}
    </style>
	<script type="text/javascript">
		var htmls="<div class='window_title' align='center'>请选择任务类型</div>";
		htmls+="<div class='new_title02'>";
		htmls+="<c:forEach items='${ requestScope.taskTypeStr.taskTypeStr}' var='taskType'>";
		htmls+="<div class='plan_list' valign='middle'>";
		htmls+="<label style='width:15%; text-align: right;'><input type='checkbox' name='${ taskType.value}' value='${ taskType.value}'  /></label>";
		htmls+="<span style='width:85%' valign='middle' id='${ taskType.value}'>${taskType.text}</span>";
		htmls+="<div class='clear'></div>";
		htmls+="</div>";
		htmls+="</c:forEach>";
		htmls+="</div>";
		htmls+="<div class='box' align='center'>";
		htmls+="<input type='button' class='window_btn' value='  确定  ' onclick='updatePlan()' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		htmls+="<input type='button' class='window_btn' value='  取消  'onclick='cancelFault()'>";
		htmls+="</div>";
		
    	//控制开启时间：16：30分-次日9:00分
 	   function openTheIndexPage() {
     		var time='${presentTime}';
     		//手机配置，是否可以全天派单
     		var sendOrderStatus='${sendOrderStatus}';
     		if(sendOrderStatus == 'false'){
     			if(time >="09:00" && time <="16:30"){
         			layer.open({
     				    content: "现在时间是"+time+", 开启时间为：16：31分-次日9:00分,现在不是开启时间!",
     					closeBtn: false,
     				    yes: function(index){
     						wx.closeWindow();//调用微信JS接口
     				        layer.close(index); //一般设定yes回调，必须进行手工关闭
     				    }
     				});
         		} 
     		}
 	    };   
	    //加载完成后执行openTheIndexPage
	    document.addEventListener("WeixinJSBridgeReady", openTheIndexPage, false); 
	    
</script>
<jsp:include page="/pub/js/mobiscroll/mobiscroll_theme.jsp"></jsp:include>
</head>
<body>
	<input type="hidden" id="taskId" name="taskId" value="${result.taskInfo.taskId }">
	<!-- <input type="hidden" id="taskType" value=""> -->
	<input type="hidden" id="tempSerialNumber" value="">
	<input type="hidden" id="equipmentId" value="${result.taskInfo.equipmentId}">
	<input type="hidden" id="equipmentDeptId" value="${result.taskInfo.equipmentDeptId}">
	<%-- <div class="nav_bottom">
		<a class="nav_bottom_back" href="${ctx_path }/cp/ouath/sendManage/waitOrderList.do">
			<img src="${ctx_path }/pub/images/arrow_left.png"/>返回
	    </a>
	    <a class="nav_operation" onclick="doDelete()">删除
	    </a>
	</div> --%>
	<div class="mian02">
	    <div class="workform_list">
	        <label>设备序列号：</label>
	        <span>
	            <input type="text" name="serialNumber" id="serialNumber" style="color:gray;" value="${result.taskInfo.serialNumber}" readonly="readonly"  class="lleft input_style1"   />
	        </span>
	        <div class="clear"></div>
	    </div>
	    
	    <div class="workform_list">
	        <label>客户名称：</label>
	        <span>
	            <input type="text" name="customerName" id="customerName" style="color:gray;" value="${result.taskInfo.customerName}" readonly="readonly" class="lleft input_style1" />
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>网点名称：</label>
	        <span>
	            <textarea rows="3"  name="branchName" id="branchName" readonly="readonly" style="color:gray;width:100%;font-size:16px"  class="lleft input_style1">${result.taskInfo.branchName}</textarea>
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>设备地址：</label>
	        <span>
	            <textarea rows="3" name="installAddress" id="installAddress" readonly="readonly" style="color:gray;width:100%;font-size:16px" 
	             class="lleft input_style1">${result.taskInfo.installAddress}</textarea>
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list" style="display: none">
	        <label>任务类型：</label>
	        <span>
	            <input type="hidden" name="taskType" id="taskType" value="${result.taskInfo.taskType}" readonly="readonly"  class="lleft input_style1"   />
	        </span>
	        <div class="clear"></div>
	    </div>
	    
	    
	   <div class="workform_list" style="padding-bottom: 18px">
	        <label>报修人：</label>
	        <span >
 				<span style="position:absolute;overflow:hidden;height:33px;width: 62%;">
	 				<select  id="repairsManNameSel" onchange="loadPhone()"  style="width:100%;height:33px;">
	 					<option value="">请选择或输入</option>
		 				<c:forEach items="${result.equipmentContact}" var="bean">
							<option value='${bean.mobliePhone}' mobile='${bean.telephone}' id='${bean.equipmentContactId}'>${bean.contactName}</option>
			   		 	</c:forEach>
		   		 	</select>
 				</span>    
 				<span style="position:absolute;height:22px;margin-top: 1px;margin-left: 1px">  
                    <input type="text" name="repairsManName" id="repairsManName" value="${result.taskInfo.repairsManName}" placeholder="请选择或输入" style="width:80%;height:22px;border:0pt;">  
                </span>  
	        </span>
	        <div class="clear"></div>
	    </div> 
	    <div class="workform_list">
	        <label>报修人手机：</label>
	        <span>
	            <input type="text"  name="repairsMoblie" id="repairsMoblie" value="${result.taskInfo.repairsMoblie}" placeholder="请输入报修人手机" class="lleft input_style1"   />
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>报修人固话：</label>
	        <span>
	            <input type="text"  name="repairsTelephone" id="repairsTelephone" value="${result.taskInfo.repairsTelephone}" placeholder="请输入报修人电话" class="lleft input_style1"   />
	        </span>
	        <div class="clear"></div>
	    </div>
	    
	    <div class="workform_list" style="padding-bottom: 18px">
	        <label>接待人：</label>
	        <span >
 				<span style="position:absolute;overflow:hidden;height:33px;width: 62%;">
	 				<select  id="receiveManNameSel" onchange="loadReceive()" style="width:100%;height:33px;">
	 					<option value="">请选择或输入</option>
						<c:forEach items="${result.equipmentContact}" var="bean">
							<option value='${bean.mobliePhone}' mobile='${bean.telephone}' id='${bean.equipmentContactId}'>${bean.contactName}</option>
			   		 	</c:forEach>
		   		 	</select>
 				</span>    
 				<span style="position:absolute;height:22px;margin-top: 1px;margin-left: 1px">  
                    <input type="text" name="textfield" id="receiveManName" value="${result.taskInfo.receiveManName}" placeholder="请选择或输入" style="width:80%;height:22px;border:0pt;">  
                </span>  
	        </span>
	        <div class="clear"></div>
	    </div> 
	    <div class="workform_list">
	        <label>接待人手机：</label>
	        <span>
	            <input type="text"  name="receiveMoblie" id="receiveMoblie" value="${result.taskInfo.receiveMoblie}" placeholder="请输入接待人手机" class="lleft input_style1"   />
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>接待人固话：</label>
	        <span>
	            <input type="text"  name="receiveTelephone" id="receiveTelephone" value="${result.taskInfo.receiveTelephone}" placeholder="请输入接待人固话" class="lleft input_style1"   />
	        </span>
	        <div class="clear"></div>
	    </div>
	    
	    <div class="workform_list">
	        <label>报修时间：</label>
	        <span>
	 	        <input type="text" name="reportTime" id="reportTime" value="${result.taskInfo.reportTime}" placeholder="请输入报修时间" readonly="readonly" class="lleft input_style1" style="width:100%" onfocus="showAllDateTime($(this));"  /> 
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>预约上门时间：</label>
	        <span>
	            <input type="text" name="appointmentDate" id="appointmentDate"  value="${result.taskInfo.appointmentTime}" placeholder="请输入预约上门时间" readonly="readonly" class="lleft input_style1" style="width:100%" onfocus="showAllDateTime($(this));" />
	        </span>
	        <div class="clear"></div>
	    </div>
	    <!-- <div class="workform_list">
	        <label>任务类型：</label>
	        <span>
	            <input type="text" name="taskTypeName" id="taskTypeName" value="" onclick="mySelects()" placeholder="请选择任务类型" class="lleft input_style1" readOnly="true" />
	        </span>
	        <div class="clear"></div>
	    </div> -->
	    <div class="workform_list">
	   	 	<label>工程师：</label>
		    <select onchange="javascript:this.style.color = 'black';" name="engineerName" id="engineerName" value="${result.taskInfo.engineerId}" 
		            style="height:33px; width:62%" >
					<c:if test="${result.taskInfo.engineerId == '' || result.taskInfo.engineerId == null }">
						<option  value="">请选择工程师</option>
					</c:if>
					<c:forEach items="${result.engineerList}" var="bean">
	                <option <c:if test="${result.taskInfo.engineerId== beans.userId }">selected=true</c:if> data-name="${bean.name }" value="${bean.userId }">${bean.name }</option>
	            </c:forEach>
		    </select>
	    </div>
	    <div class="workform_list">
	   	 	<label>任务等级：</label>
		    <select onchange="javascript:this.style.color = 'black';" readonly="readonly" name="taskLevel" id="taskLevel" style="height:33px; width:62%" >		 
				<option value="常规" <c:if test="${result.taskInfo.taskLevel== '常规' }">selected=true</c:if>>常规</option>
				<option value="紧急" <c:if test="${result.taskInfo.taskLevel== '紧急' }">selected=true</c:if>>紧急</option>
		    </select>
	    </div>
	     <!-- <div class="workform_list">
	        <label>工程师电话：</label>
	        <span>
	            <input type="text" name="textfield" id="engineerPhoneNo" value="" placeholder="请输入工程师电话" class="lleft input_style1" readOnly="true" />
	        </span>
	        <div class="clear"></div>
	    </div> -->
	    <div class="workform_list">
	        <label>报修内容：</label>
	        <span>
 	            <textarea rows="3"  name="taskContent" id="taskContent" readonly="readonly" style="color:gray; width:100%;font-size:16px"  class="lleft input_style1" placeholder="请填写任务内容">${result.taskInfo.taskContent}</textarea>
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list" id="toDoTaskDiv" style="display: none;">
	        <label>未完成列表：</label>
	        <span>
	        	<input type="text" name="taskTypeList" id="taskTypeList" readonly="readonly"  placeholder="请点击右侧选择按钮" class="lleft input_style1"/>
 	            <input type="hidden" name="taskIds" id="taskIds"  value="" placeholder="请点击右侧选择按钮" class="lleft input_style1"   />
	        	<a class="btn1 right" id="btnTodoTask">选择</a>
	        </span>
	        <div class="clear"></div>
	    </div>
	    
	    
	    <div class="box" align="center">
		<input type="button" class="window_btn" style="font-size:18px" value="立即派单" onclick="sendOrder()">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" class="window_btn" style="font-size:18px" value="删除任务" onclick="doDelete()" />
		</div>
	</div>
<script src="${ctx_path }/pub/js/showDialog.js"></script>
<script src="${ctx_path }/pub/js/page/workorderSendManage/waitOrderDetail.js?dateTime=201602261744"></script>
<script>
$(function(){
	/* var toDoWorkTaskResult = ${result.toDoWorkTaskResult};
	var toDoWorkTask = ${result.toDoWorkTask};
	console.log(toDoWorkTaskResult);
	console.log(toDoWorkTask); */
	var toDoWorkTaskResult = ${result.toDoWorkTaskResult};
	var toDoWorkTask = ${result.toDoWorkTask};
	if(toDoWorkTaskResult){
		$("#toDoTaskDiv").show();
		var tids= [];
		var ttypes = [];
	    $(toDoWorkTask).each(function (index, datas) {
	    	tids.push(datas.taskId);
	    	ttypes.push(datas.taskType);
	    });
	    $("#taskIds").val(tids.join(','));
	    $("#taskTypeList").val(ttypes.join(','));
	    $("#btnTodoTask").click(function(){
	    	var selectedTids = $("#taskIds").val().split(',');
	    	var htmls2="<div class='new_title02'>";
	    	$(toDoWorkTask).each(function (index, datas) {
			    htmls2+="<div class='plan_list'>";
			    htmls2+="<label style='width:100%; text-align: left;font-size:18px;'><input type='checkbox' ";
			    if(jQuery.inArray(''+datas.taskId, selectedTids) != -1){
			    	htmls2+="checked='checked' ";
			    }
			    htmls2+="name='taskId' value='"+datas.taskId+"' data-tp='"+datas.taskType+"'>"+"类型:"+datas.taskType+"<br/>升级项目:"+datas.upgradeName+"<br/>计划开始时间:"+datas.planStartDate+"<br/>计划完成时间:"+datas.planEndDate+"</input>&nbsp;&nbsp;&nbsp;&nbsp;</label>";
			    htmls2+="<div class='clear'></div>";
			    htmls2+="</div>";
	        });
		    htmls2+="</div>";
	    	var box = jQuery.showDialog({
		    	title : "未完成任务",
		  		msg : htmls2, 
		  		isFullHeight : true,
		  		hasDefault : true,
		  		handlerFeforeClose : function(){
		  			//layer.closeAll('page'); 
		  		},
		 		primary : function(){
		 			var str=[];
		 			var tp = [];
		 			$("input[name='taskId']:checkbox").each(function(){
		                if($(this).is(':checked')){
		                    str.push($(this).val());
		                    tp.push($(this).attr("data-tp"));
		                }
		            });
		            //alert(str.join(","));
		 			$("#taskIds").val(str.join(","));
		 			$("#taskTypeList").val(tp.join(","));
		  			if(box){
		  				box.data("close")();
		  			}
		 		} 
			});
	    });
	}else{
		$("#toDoTaskDiv").hide();
	}
})
</script>
</body>
</html>
