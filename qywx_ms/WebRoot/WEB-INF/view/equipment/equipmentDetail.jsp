<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>设备详情</title>
    <script type="text/javascript">
	var basePath = '${ctx_path}';
    function changePhImg(){//没有号码的置灰色并移出事件
    	var phoneNum = $("#equipmentPhone").text();
    	if(phoneNum.trim().length==0){
    		$("#phImg").attr("src",basePath+"/pub/images/phone_gray.png");
    		$("#phImg").removeAttr("onclick");
    	}
    }
    document.addEventListener("DOMContentLoaded", changePhImg, false);
    document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    	WeixinJSBridge.call('hideOptionMenu');
    });
    </script>
</head>
<body>
<!--顶部 -->
<div class="mian02">
	<input type="hidden" id="serialNumber" name="serialNumber" value="${bean.serialNumber}" />
	<input type="hidden" id="equipmentId" name="equipmentId" value="${bean.equipmentId}" />
	<input type="hidden" id="chief" name="chief" value="${bean.chiefUserId}" />
    <div class="content_list">
        <label>设备序列号:</label>
        <span>
            ${bean.serialNumber}
        </span>
    </div>
    <div class="content_list">
        <label>设备类型:</label>
        <span>
            ${bean.equipmentType}
        </span>
    </div>
    <div class="content_list">
        <label>设备型号:</label>
        <span>
            ${bean.equipmentModel}
        </span>
    </div>
    <div class="content_list">
        <label>制造商:</label>
        <span>
            ${bean.manufcturer}
        </span>
    </div>
    <%-- <div class="content_list">
        <label>设备负责人:</label>
        <span>
            <select id="chief" name="chief" class="equipment_fromwidth" disabled="false">
                <c:forEach items="${bean.chiefList}" var="chiefBean">
           			<option value="${chiefBean.value }" <c:if test="${chiefBean.text eq  bean.chief }">selected="selected"</c:if> >${chiefBean.text}</option>
           		</c:forEach>
        	</select>	
        </span>
    </div> --%>
    <div class="content_list">
        <label>设备负责人:</label>
        <span>
        	${ bean.chief }
        </span>
    </div>
    <div class="content_list">
        <label>设备负责人电话:</label>
        <span>
           <span id="equipmentPhone" class="left"> ${bean.chiefPhone }  </span>
           <c:if test="${!empty bean.chiefPhone}">
	           <a href="tel:${bean.chiefPhone }"><img id="phImg"  src="${ctx_path }/pub/images/phone.png" /></a>
           </c:if>
        </span>
    </div>
    <div class="content_list">
        <label>安装地址:</label>
        <span>
            <textarea  id="installAddress" name="installAddress">${bean.installAddress }</textarea>
        </span>
    </div>
    <div class="content_list">
        <label>客户名称:</label>
        <span>
            ${bean.customsName }
        </span>
    </div>
    <div class="content_list">
        <label>安装开通日期 :</label>
        <span>
            ${bean.installDate }
        </span>
    </div>
    <div class="content_list">
        <label>安装类型:</label>
        <span>
        	<select id="installType" name="installType" class="equipment_fromwidth">
        		<c:forEach items="${bean.installTypeList}" var="typeBean">
           			<option value="${typeBean.value }" <c:if test="${typeBean.text eq  bean.installType }">selected="selected"</c:if> >${typeBean.text}</option>
           		</c:forEach>
        	</select>
        </span>
    </div>
    <div class="content_list">
        <label>安装方式 :</label>
        <span>
           <select id="installModel" name="installModel" class="equipment_fromwidth">
           		<c:forEach items="${bean.installModelList}" var="modelBean">
           			<option value="${modelBean.value }" <c:if test="${modelBean.text eq  bean.installModel }">selected="selected"</c:if> >${modelBean.text}</option>
           		</c:forEach>
        	</select>
        </span>
    </div>
    <div class="content_list">
        <label>设备状态:</label>
        <span>
        	${bean.deviceStatus }
        </span>
    </div>
    <div class="content_list">
        <label>设备位置:</label>
        <span>
        	<select id="equipmentArea" name="equipmentArea" class="equipment_fromwidth">
        		<c:forEach items="${bean.equipmentAreaList}" var="equipmentAreaBean">
           			<option value="${equipmentAreaBean.value }" <c:if test="${equipmentAreaBean.value eq  bean.equipmentArea }">selected="selected"</c:if> >${equipmentAreaBean.text}</option>
           		</c:forEach>
        	</select>
        </span>
    </div>
    <div class="content_list">
        <label>操作系统:</label>
        <span>
            ${bean.operationSystem}
        </span>
    </div>
    <div class="content_list">
        <label>操作系统版本:</label>
        <span>
            ${bean.osVersion }
        </span>
    </div>
    <div class="content_list">
        <label>ATMC名称:</label>
        <span>
            ${bean.ATMCName }
        </span>
    </div>
    <div class="content_list">
        <label>ATMC版本 :</label>
        <span>
            ${bean.ATMCVersion }
        </span>
    </div>
    <div class="content_list">
        <label>ATMC跨平台SP:</label>
        <span>
            ${bean.atmcSpVersion }
        </span>
    </div>
    <div class="content_list">
        <label>加密方式:</label>
        <span>
            ${bean.encryptModel }
        </span>
    </div>
    <div class="content_list">
        <label>ATM号 :</label>
        <span>
            <input type="text" style="height:100%" id="ATMNumber" name="ATMNumber" value="${bean.ATMNumber }" />
        </span>
    </div>
    <div class="content_list">
        <label>银行号:</label>
        <span>
            <input type="text"  id="bankNumber" name="bankNumber" value="${bean.bankNumber }" />
        </span>
    </div>
    <div class="content_list">
        <label>支持终端号:</label>
        <span>
            <input type="text"  id="bankTerminalNumber" name="bankTerminalNumber" value="${bean.bankTerminalNumber }" />
        </span>
    </div>
    <div class="content_list">
        <label>网点名称:</label>
        <span>
            <textarea type="text"  class="equipment_fromwidth" id="branchName" name="branchName" >${bean.branchName }</textarea>
        </span>
    </div>
   <%--  <div class="content_list">
        <label>网点负责人:</label>
        <span>
        </span>
    </div> --%>
    <input type="hidden"  id="branchPrincipal" name="branchPrincipal" value="${bean.branchPrincipal }" />
    <%-- <div class="content_list">
        <label>网点负责人电话:</label>
        <span>
        </span>
    </div> --%>
    <input type="hidden"  id="branchPrincipalTel" name="branchPrincipalTel" value="${bean.branchPrincipalTel }" />
   <%--  <div class="content_list">
        <label>管理员:</label>
        <span>
        </span>
    </div> --%>
    <input type="hidden"  id="atmManager" name="atmManager" value="${bean.atmManager }" />
  	 <%--  <div class="content_list">
        <label>管理员联系方式:</label>
        <span>
        </span>
    </div> --%>
    <input type="hidden"  id="atmManagerTel" name="atmManagerTel" value="${bean.atmManagerTel }" />
    <div class="content_list">
        <label>网络协议:</label>
        <span>
	        <select id="netProtocol" name="netProtocol" class="equipment_fromwidth">
	            <c:forEach items="${bean.netProtocolList}" var="netBean">
	         		<option value="${netBean.value }" <c:if test="${netBean.text eq  bean.netProtocol }">selected="selected"</c:if> >${netBean.text}</option>
	         	</c:forEach>
	        </select>
        </span>
    </div>
    <div class="content_list">
        <label>本机IP:</label>
        <span>
            <input type="text"  id="LocalIP" name="LocalIP" value="${bean.localIP} " />
        </span>
    </div>
    <div class="content_list">
        <label>P端IP:</label>
        <span>
        <input type="text"  id="pip" name="pip" value="${bean.pip}" />
        </span>
    </div>
    <div class="content_list">
        <label>子网掩码:</label>
        <span>
            <input type="text"  id="subnet_hide_address" name="subnet_hide_address" value="${bean.subnet_hide_address} " />
        </span>
    </div>
    <div class="content_list">
        <label>网关:</label>
        <span>
            <input type="text"  id="gateway" name="gateway" value="${bean.gateway }" />
        </span>
    </div>
    <div class="content_list">
        <label>参考费用:</label>
        <span>
            ${bean.consultWay }
        </span>
    </div>
    <div class="content_list">
        <label>参考线路:</label>
        <span>
            ${bean.referenceCharge }
        </span>
    </div>
    <div class="content_list">
        <label>历史服务:</label>
        <span>
        	<a class="lleft red" href="${ctx_path }/cp/equipment/historyServer.do?serialNumber=${bean.serialNumber}">[${historysercount }]</a>
            <a class="lleft list_width2" href="${ctx_path }/cp/equipment/historyServer.do?serialNumber=${bean.serialNumber}"><img src="${ctx_path }/pub/images/information.png" /></a>
        </span>
    </div>
    <div class="content_list">
        <label>遗留问题:</label>
        <span>
       		<a class="lleft red" href="${ctx_path }/cp/equipment/historyproblem.do?serialNumber=${bean.serialNumber}">[${problemlistsize }]</a>
            <a class="lleft list_width2"  href="${ctx_path }/cp/equipment/historyproblem.do?serialNumber=${bean.serialNumber}"><img src="${ctx_path }/pub/images/information.png" /></a>
        </span>
    </div>
    <div class="content_list">
        <label>设备配置:</label>
        <span>
        	<a class="lleft list_width2" href="${ctx_path }/cp/equipment/config.do?serialNumber=${serialNumber}"><img src="${ctx_path }/pub/images/information.png" /></a>
        </span>
    </div>
   </div> 
    <div class="backimg-bottom">		
    		<a class="bottom_btn" style="float: left;margin-left: 0.5em;" onclick="showCustomer('${bean.equipmentId}','${bean.serialNumber}');">客户联系人</a>
    		<a class="bottom_btn" style="float: none;display: inline-block;" onclick="toUpload('${bean.equipmentId}','${bean.serialNumber}');">上传图片</a>
            <a class="bottom_btn" onclick="saveEquimentDetail();">保存 </a>
	</div>
	
	<script src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx_path }/pub/js/page/equipment/equipmentDetail.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>		 
</body>
</html>
