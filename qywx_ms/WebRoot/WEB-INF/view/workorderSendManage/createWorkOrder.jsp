<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>新建工单</title>
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
		htmls+="<c:if test='${taskType.value != \"SJ\"}'>";
		htmls+="<div class='plan_list' valign='middle'>";
		htmls+="<label style='width:15%; text-align: right;'><input type='checkbox' name='${ taskType.value}' value='${ taskType.value}'  /></label>";
		htmls+="<span style='width:85%' valign='middle' id='${ taskType.value}'>${taskType.text}</span>";
		htmls+="<div class='clear'></div>";
		htmls+="</div>";
		htmls+="</c:if>";
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
	<input type="hidden" name="taskType" id="taskType" value="">
	<input type="hidden" name="equipmentId" id="equipmentId" value="">
	<input type="hidden" name="equipmentDeptId" id="equipmentDeptId" value="">
	
	<div class="mian02">
	    <div class="workform_list">
	        <label>设备序列号：</label>
	        <span>
	            <input type="text" name="textfield" id="serialNumber"  value="" placeholder="请输入设备序列号" class="lleft input_style1"   />
	        	<a class="btn1 right" onclick="loadOrder()" id="search">加载</a>
	        </span>
	        <div class="clear"></div>
	    </div>
	    
	    <div class="workform_list">
	        <label>客户名称：</label>
	        <span>
	            <textarea rows="2"  name="textfield" id="customerName" readonly="readonly" style="color:gray;width:100%;font-size:16px"  class="lleft input_style1" placeholder="请输入客户名称"></textarea>
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>网点名称：</label>
	        <span>
	            <textarea rows="3"  name="textfield" id="branchName" readonly="readonly" style="color:gray;width:100%;font-size:16px"  class="lleft input_style1" placeholder="请输入网点名称"></textarea>
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>设备地址：</label>
	        <span>
	            <textarea  rows="3" name="textfield" id="installAddress" readonly="readonly" style="color:gray;width:100%;font-size:16px"  class="lleft input_style1" placeholder="请输入设备地址"></textarea>
	        </span>
	        <div class="clear"></div>
	    </div>
	    
	    
	   <div class="workform_list" style="padding-bottom: 18px">
	        <label>报修人：</label>
	        <span >
 				<span style="position:absolute;overflow:hidden;height:33px;width: 62%;">
	 				<select  id="repairsManNameSel" onchange="loadPhone()"  style="width:100%;height:33px;">
		   		 	</select>
 				</span>    
 				<span style="position:absolute;height:22px;margin-top: 1px;margin-left: 1px">  
                    <input type="text" name="textfield" id="repairsManName" value="" placeholder="请选择或输入" style="width:80%;height:22px;border:0pt;">  
                </span>  
	        </span>
	        <div class="clear"></div>
	    </div> 
	    <div class="workform_list">
	        <label>报修人手机：</label>
	        <span>
	            <input type="text"  name="textfield" id="repairsMoblie" value="" placeholder="请输入报修人手机" class="lleft input_style1"   />
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>报修人固话：</label>
	        <span>
	            <input type="text"  name="textfield" id="repairsTelephone" value="" placeholder="请输入报修人电话" class="lleft input_style1"   />
	        </span>
	        <div class="clear"></div>
	    </div>
	    
	    <div class="workform_list" style="padding-bottom: 18px">
	        <label>接待人：</label>
	        <span >
 				<span style="position:absolute;overflow:hidden;height:33px;width: 62%;">
	 				<select  id="receiveManNameSel" onchange="loadReceive()"  style="width:100%;height:33px;">
		   		 	</select>
 				</span>    
 				<span style="position:absolute;height:22px;margin-top: 1px;margin-left: 1px">  
                    <input type="text" name="textfield" id="receiveManName" value="" placeholder="请选择或输入" style="width:80%;height:22px;border:0pt;">  
                </span>  
	        </span>
	        <div class="clear"></div>
	    </div> 
	    <div class="workform_list">
	        <label>接待人手机：</label>
	        <span>
	            <input type="text"  name="textfield" id="receiveMoblie" value="" placeholder="请输入接待人手机" class="lleft input_style1"   />
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>接待人固话：</label>
	        <span>
	            <input type="text"  name="textfield" id="receiveTelephone" value="" placeholder="请输入接待人固话" class="lleft input_style1"   />
	        </span>
	        <div class="clear"></div>
	    </div>
	    
	    <div class="workform_list">
	        <label>报修时间：</label>
	        <span>
	 	        <input type="text" name="reportTime" id="reportTime" value="" placeholder="请输入报修时间" readonly="readonly" class="lleft input_style1" style="width:100%" onfocus="showAllDateTime($(this));"  /> 
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>预约上门时间：</label>
	        <span>
	            <input type="text" name="appointmentDate" id="appointmentDate"  value="" placeholder="请输入预约上门时间" readonly="readonly" class="lleft input_style1" style="width:100%" onfocus="showAllDateTime($(this));" />
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	        <label>任务类型：</label>
	        <span>
	            <input type="text" name="taskTypeName" id="taskTypeName" value="" onclick="mySelects()" placeholder="请选择任务类型" class="lleft input_style1" readOnly="true" />
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list">
	   	 	<label>工程师：</label>
		    <select onchange="javascript:this.style.color = 'black';" name="" id="engineerName" style="color:gray; height:33px; width:62%" >
					<option  value="">请点击加载工程师</option>
		    </select>
	    </div>
	    <div class="workform_list">
	   	 	<label>任务等级：</label>
		    <select onchange="javascript:this.style.color = 'black';" name="taskLevel" id="taskLevel" style="height:33px; width:62%" >		 
				<option value="常规">常规</option>
				<option value="紧急">紧急</option>
		    </select>
	    </div>
	    <div class="workform_list">
	        <label>报修内容：</label>
	        <span>
 	            <textarea rows="3"  name="textfield" id="taskContent" style="width:100%;font-size:16px"  class="lleft input_style1" placeholder="请填写任务内容"></textarea>
	        </span>
	        <div class="clear"></div>
	    </div>
	    <div class="workform_list" id="toDoTaskDiv" style="display: none;">
	        <label>未完成任务列表:</label>
	        <span>
	            <input type="text" name="taskTypeList" id="taskTypeList" readonly="readonly"  placeholder="请点击右侧选择按钮" class="lleft input_style1"/>
 	            <input type="hidden" name="taskIds" id="taskIds"  value="" placeholder="请点击右侧选择按钮" class="lleft input_style1"   />
	        	<a class="btn1 right" id="btnTodoTask" onclick="chooseFuc();">选择</a>
	        </span>
	        <div class="clear"></div>
	    </div>
	    
	    <div class="box" align="center">
		<!-- <input type="button" class="window_btn" style="font-size:18px" value="保存工单"
			onclick="saveOrder()" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		<input type="button" class="window_btn" style="font-size:18px" value="立即派单"
			onclick="sendOrder()">
		</div>
	</div>
<script src="${ctx_path }/pub/js/showDialog.js?dateTime=20160304"></script>
<script src="${ctx_path }/pub/js/page/workorderSendManage/createWorkOrder.js?dateTime=20160326"></script>
</body>
</html>
