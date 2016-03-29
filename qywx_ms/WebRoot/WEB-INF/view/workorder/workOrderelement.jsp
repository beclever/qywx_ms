<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>零件替换</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
	<input type="hidden" value="${divname}" name="divname" id="divname">
	<div class="window_mian">
	        <input type="hidden" value="${newSoftwareVersion}" name="newSoftwareVersion" id="newSoftwareVersion">
	        <input type="hidden" value="${newHardwareVersion}" name="newHardwareVersion" id="newHardwareVersion">
	        <input type="hidden" value="${sparepartNum}" name="sparepartNum" id="sparepartNum">
	        <input type="hidden" value="${workformId}" name="workformId" id="workformId">
	        <input type="hidden" value="${poNumber}" name="poNumber" id="poNumber">
	        <div class="workform_list" onclick="sendnewelement('${ctx_path}/cp/element/newelement.do')">
	           <div class="taskOrder_add_btn">
		           <label class="blue">新物料名称:</label>
		           <input type="text" size="15" name="newSerialName" value="${newSerialName}" id="newSerialName"/>
		           <span ><img src="${ctx_path}/pub/images/icon_window.png" /></span>
	           </div>
	           <div class="clear"></div>
	        </div>
	        <div class="workform_list">
	           <label class="blue"><font color="red">*</font>物料编码:</label>
	           <span>
	           		<input type="text" name="newMaterialCode"  id="newMaterialCode" value="${newMaterialCode}">
	           </span>
	           <div class="clear"></div>
	        </div>
	        <div id="divcontent2" style="display: none">
	            <div class="workform_list" onclick="sendOldelement()">
	            	<div class="taskOrder_add_btn">
		                <label class="blue">原物料名称:</label>
		                	<input type="text" size="15" name="oldSerialName" value="${oldSerialName}"  id="oldSerialName"/>
		                <span ><img src="${ctx_path}/pub/images/icon_window.png" /></span>
	                </div>
	                <div class="clear"></div>
	                <%--- <a  class="add_btn" onclick="sendOldelement()">选择</a>--%>
	            </div>
	            <div class="workform_list">
					<label class="blue">物料编码:</label>
					<span>
						<input type="text" name="oldMaterialCode" class="input_text" value="${oldMaterialCode}" id="oldMaterialCode">
	           		</span>
	           		<div class="clear"></div>
	            </div>
	        </div>
	
	        <div class="workform_list">
	            <label class="blue">使用方法:</label> 
	            <span>
					<input type="radio" value="新增" name="menthods" id="showInfo" <c:if test="${flag!='replace' }">checked="checked"</c:if>   onclick="showInfo()" />新增
	            	<input type="radio" value="替换" name="menthods" id="replaceInfo" <c:if test="${flag=='replace' }">checked="checked"</c:if> onclick="replaceInfo()" />替换
	            </span>
	            <div class="clear"></div>
	        </div>
	        <div class="workform_list">
	            <label class="blue">使用数量:</label>
	            <span>
	            	<input type="text" name="numbers"  value="${quantity}" id="quantity" onKeyUp="onlyNumbers(this)">
	            </span>
	            <div class="clear"></div>
	        </div>
	        <div class="box" align="center">
	            <input type="button" class="window_btn" value="确定"  onclick="sendSave()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            <input type="button" class="window_btn" value="取消" onclick="sendCancel()"/>
	        </div>
	    </div>
	<script src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>
	<script type="text/javascript">
	var index = parent.layer.getFrameIndex(window.name);
	$(document).ready(function(){
		loadcss();
		});
	// 新物料(新增零件)
	function sendnewelement(url) {
		var divname = $("#divname").val();
		var poNumber = $("#poNumber").val();
		var workformId = $("#workformId").val();
		window.location.href = url + '?flag=add&divname=' + divname + '&poNumber='
				+ poNumber + "&workformId=" + workformId;
	}

	// 原物料(零件)
	function sendOldelement() {
		var divname = $("#divname").val();
		var poNumber = $("#poNumber").val();
		var newMaterialCode = $("#newMaterialCode").val();
		var newSerialName = $("#newSerialName").val();
		var newSerialName=encodeURIComponent(encodeURIComponent(newSerialName));
		var quantity = $("#quantity").val();
		if (newMaterialCode == "") {
			parent.layer.msg('请先选择新物料');
			return false;
		} else {
			window.location.href = basePath+'/cp/element/oldelement.do?flag=replace&newSerialName='
					+ newSerialName
					+ "&newMaterialCode="
					+ newMaterialCode
					+ "&quantity=" + quantity + "&divname=" + divname+'&poNumber='+ poNumber;;
		}

	}
	// 新增(选项)
	function showInfo() {
		$("#oldSerialName").val(""); // 原物料
		$("#oldMaterialCode").val(""); // 原物料编码
		$("#divcontent2").hide();

	}

	// 替换(选项)
	function replaceInfo() {
		$("#divcontent2").show();
	}
	
	function onlyNumbers(obj) {
		// re = /^\d+\.?\d*$/
		var re = new RegExp("^[1-9][0-9]*$");
		var str = obj.value;
		if (!re.test(str)) {
			parent.layer.msg('请输入整数');
			len = str.length;
			str1 = str.substr(0, len - 1);
			obj.value = "";
			obj.focus();
			return;
		}
	}

	// 加载样式
	function loadcss() {
		var checkval = $("input:radio:checked").val();
		if (checkval == '替换') {
			$("#divcontent2").show();
		}
	}
	
	function sendSave(){
		var poNumber = $("#poNumber").val();
		var newSerialName = $("#newSerialName").val();
		var newMaterialCode = $("#newMaterialCode").val();
		var flaginfo = $("input:radio:checked").val();
		var divname = $("#divname").val();
		var quantity = $("#quantity").val();
		var sparepartNum = $("#sparepartNum").val();
		var oldSerialName = $("#oldSerialName").val();
		var oldMaterialCode = $("#oldMaterialCode").val();
		var newSoftwareVersion = $("#newSoftwareVersion").val();
		var newHardwareVersion = $("#newHardwareVersion").val();
		if (newMaterialCode == "" || (flaginfo == "替换" && oldMaterialCode == "")
				|| (parseInt(quantity) - parseInt(sparepartNum) > 0)) {
			if (newMaterialCode == "") {
				layer.alert("新物料不能为空", {title: '溫馨提示', closeBtn: false });
			}
			if (flaginfo == "替换" && oldMaterialCode == "") {
				layer.alert("原物料不能为空", {title: '溫馨提示', closeBtn: false });
			}
			if (parseInt(quantity) - parseInt(sparepartNum) > 0) {
				layer.alert("使用数量(" + quantity + ")不能大于物料的数量(" + sparepartNum + ")", {title: '溫馨提示', closeBtn: false });
			}
			return false;
		}
		//parent.loadElementData(divname, newSerialName, oldSerialName, quantity, newMaterialCode, oldMaterialCode, newSoftwareVersion, newHardwareVersion, flaginfo);
		//parent.layer.close(index);
		var useMethod = $('input:radio:checked').val();
		var argsInfo = newSerialName + "-,-" + newMaterialCode + "-,-" + flaginfo + "-,-"
				+ divname + "-,-" + quantity + "-,-" + sparepartNum + "-,-"
				+ oldSerialName + "-,-" + oldMaterialCode + "-,-" + newSoftwareVersion
				+ "-,-" + newHardwareVersion+"-,-"+useMethod;
		/*【YT2.503.197】清分机扎把机主控板-,-301010161-,-新增-,-[object%20HTMLInputElement]-,-1-,-1-,--,--,--,--,-新增  */
		window.location.href = basePath + '/cp/workOrder/dealWorkOrder.do?detailType=1&poNumber='
			+ poNumber+'&typeInfo=workOrderelement&showInfoargs='+argsInfo;
	}
	
	function sendCancel(){
		parent.layer.close(index);
	}
	
	</script>
</body>
</html>
