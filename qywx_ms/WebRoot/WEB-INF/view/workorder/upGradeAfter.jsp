<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>升级后</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    	<link rel="stylesheet" href="${ctx_path }/pub/css/newWechat.css" type="text/css">
	<link rel="stylesheet" href="${ctx_path }/pub/css/SaMobile.css" type="text/css">
  	<link rel="stylesheet" href="${ctx_path }/pub/css/SaPublic.css" type="text/css">
</head>

<body>
<div class="window_mian">
    <div class="workform_list">
        <label>操作系统:</label>
        <input type="hidden" value="${taskId}" name="taskId" id="taskId">
        <input type="hidden" value="${isDisable}" name="isDisable" id="isDisable">
        <input type="hidden" value="${poNumber}" name="poNumber" id="poNumber">
        <span>
	        <select id="operationSystem">
	        	<option>请选择</option>
	            <c:forEach items="${sjbean.taskAttributeOption.operationSystem.optionValue}" var="systembean">
	                <option <c:if test="${afterSjModel.operationSystemId==systembean.optionId}">selected=true</c:if>
	                        value="${systembean.optionId}">${systembean.optionName}</option>
	            </c:forEach>
	        </select>
        </span>
        <div class="clear"></div>
    </div>
    <div class="workform_list">
        <label>操作系统版本:</label>
        <span>
        <c:choose>
        	<c:when test="${afterSjModel.osVersion == null }">
        		<input value="${beforSjModel.osVersion}" type="text"  name="osVersion" id="osVersion">
        	</c:when>
        	<c:otherwise>
        		<input value="${afterSjModel.osVersion}" type="text"  name="osVersion" id="osVersion">
        	</c:otherwise>
        </c:choose>
        </span>
        <div class="clear"></div>
    </div>
    <div class="workform_list">
        <label>ATMC名称: </label>
        <span>
	        <select id="atmCName" class="taskOrder_inputwidth">
	            <option>请选择</option>
	            <c:forEach items="${sjbean.taskAttributeOption.ATMCName.optionValue}" var="systembean">
	
	                <option
	                        <c:if test="${afterSjModel.atmCNameId==systembean.optionId}">selected=true</c:if>
	                        value="${ systembean.optionId}">${ systembean.optionName}</option>
	
	            </c:forEach>
	        </select>
         </span>
		<div class="clear"></div>
    </div>
    <div class="workform_list">
        <label>ATMC版本:</label>
        <span>
        <c:choose>
        	<c:when test="${afterSjModel.atmCVersion==null }">
        	<input type="text" value="${beforSjModel.atmCVersion}" name="atmCVersion" id="atmCVersion">
        	</c:when>
        	<c:otherwise>
        	<input type="text" value="${afterSjModel.atmCVersion}" name="atmCVersion" id="atmCVersion">
        	</c:otherwise>
        </c:choose>
        </span>
		<div class="clear"></div>
    </div>
    <div class="workform_list">
        <label>跨平台sp版本:</label>
        <span>
        <c:choose>
        	<c:when test="${afterSjModel.osVersion==null }">
        	<input type="text" value="${beforSjModel.atmCSpVersion}" name="atmCSpVersion" id="atmCSpVersion">
        	</c:when>
        	<c:otherwise>
        	<input type="text" value="${afterSjModel.atmCSpVersion}" name="atmCSpVersion" id="atmCSpVersion">
        	</c:otherwise>
        </c:choose>
   		</span>
   		<div class="clear"></div>
    </div>
	<div class="box" align="center">
    	<c:if test="${isDisable =='N' }">
	        <input type="button" value="确定" class="window_btn" onclick="saveUpdate()"/>
    	</c:if>
	    <input type="button" value="关闭" class="window_btn" onclick="cancelUpdate()"/>
	</div>
	
</div>
	<script src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>
    <script type="text/javascript">
    var basePath = "${ctx_path}";
    var index = parent.layer.getFrameIndex(window.name);
    function saveUpdate() {
    	var taskId = $("#taskId").val();
    	var poNumber = $("#poNumber").val();
    	var operationSystem = $("#operationSystem option:selected").text();
    	var operationSystemId = $("#operationSystem option:selected").val();
    	var osVersion = $("#osVersion").val();
    	var atmCNameId = $("#atmCName option:selected").val();
    	var atmCName = $("#atmCName option:selected").text();
    	var atmCVersion = $("#atmCVersion").val();
    	var atmCSpVersion = $("#atmCSpVersion").val();
    	var indexLoading = layer.load(2,{shade:[0.6,'#666']});//0.6透明度的灰色背景
    	$.ajax({
    		type : "post",
    		url : basePath + "/cp/AfterGradeSave.do",
    		dataType : "json",
    		data : {
    			taskId : taskId,
    			operationSystem : operationSystem,
    			operationSystemId : operationSystemId,
    			osVersion : osVersion,
    			atmCName : atmCName,
    			atmCNameId : atmCNameId,
    			atmCVersion : atmCVersion,
    			atmCSpVersion : atmCSpVersion,
    			poNumber : poNumber
    		},
    		success : function(data) {
    			layer.close(indexLoading);
    			layer.msg(data.errmsg);
    			setTimeout(parent.layer.close(index), 1500);
    		},
    		error : function(XMLHttpRequest, textStatus, errorThrown) {
    			layer.alert("发生未知错误:" + errorThrown);
    			layer.close(indexLoading);
    		}
    	});
    }
    //取消升级
	function cancelUpdate(){
		parent.layer.close(index);
	}
    $(function(){
    	if($("#isDisable").val() == "Y"){
    		$("#operationSystem").prop('disabled', true);
    		$("#osVersion").attr("readonly","readonly");
    		$("#atmCName").prop('disabled', true);
    		$("#atmCVersion").attr("readonly","readonly");
    		$("#atmCSpVersion").attr("readonly","readonly");
    	}
    })
	</script>
</body>
</html>
