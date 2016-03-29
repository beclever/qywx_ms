<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>模块替换</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>

<body>
	<div class="window_mian">
		<input type="hidden" value="${serialNumber}" id="serialNumber">
		<input type="hidden" value="${divname}" id="divname"> <input
			type="hidden" value="${equipmentConfigId}" id="equipmentConfigId">
	        <input type="hidden" value="${workformId}" name="workformId" id="workformId">
		<div class="workform_list">
			<div class="taskOrder_add_btn">
				<label class="blue">原模块:</label>
				<input type="text" readonly="readonly" size="15" onclick="sendOldspare()" name="oldSerialName"
					value="${oldSerialName}" id="oldSerialName" taskName="原模块">
					
				<span><img src="${ctx_path}/pub/images/icon_window.png" /></span>
			</div>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">条码编码:</label> <span> <input type="text"
				name="oldSerialNumber" value="${oldSerialNumber }"
				id="oldSerialNumber" readonly="readonly">
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">物料编码:</label> <span> <input type="text"
				name="oldMaterialCode" value="${oldMaterialCode }"
				id="oldMaterialCode" readonly="readonly">
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">硬件版本:</label> <span> <input type="text"
				name="oldHardwareVersion" value="${oldHardwareVersion }"
				id="oldHardwareVersion" readonly="readonly" />
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">软件版本:</label> <span> <input type="text"
				name="oldSoftwareVersion" value="${oldSoftwareVersion }"
				id="oldSoftwareVersion" readonly="readonly" />
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<div class="taskOrder_add_btn">
				<label class="blue">新模块:</label>
				<input type="text" readonly="readonly" size="15" onclick="sendnewspare()" name="newSerialName" 
					value="${newSerialName}" id="newSerialName" taskName="新模块" />
					
				<span><img src="${ctx_path}/pub/images/icon_window.png" /></span>
			</div>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue"><font color="red">*</font>物料编码:</label> <span>
				<input type="text" name="newMaterialCode" value="${newMaterialCode}"
				id="newMaterialCode" readonly="readonly">
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">条码编码:</label> <span> <input type="text"
				name="newSerialNumber" value="${newSerialNumber}"
				id="newSerialNumber" readonly="readonly">
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">硬件版本:</label> <span> <input type="text"
				name="newHardwareVersion" value="${newHardwareVersion }"
				id="newHardwareVersion" />
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">软件版本:</label> <span> <input type="text"
				name="newSoftwareVersion" value="${newSoftwareVersion}"
				id="newSoftwareVersion" />
			</span>
			<div class="clear"></div>
		</div>
		<div class="box" align="center">
			<input class="window_btn" type="button" value="确定"
				onclick="sendWorkspareOK()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
				class="window_btn" type="button" value="取消"
				onclick="sendWorkspareCancel()">
		</div>
	</div>
	<script type="text/javascript">
  	var newModuleType = '${newModuleType}';
  	var oldModuleType = '${oldModuleType}';
  	 var index = parent.layer.getFrameIndex(window.name);
  	//取消
  	function sendWorkspareCancel(){
		  parent.layer.close(index);
	 }
  	//确定
	function sendWorkspareOK(){
		var divname = $("#divname").val();
		var oldSerialName = $("#oldSerialName").val();
		var oldSerialNumber = $("#oldSerialNumber").val();
		var oldMaterialCode = $("#oldMaterialCode").val();
		var oldHardwareVersion = $("#oldHardwareVersion").val();
		var oldSoftwareVersion = $("#oldSoftwareVersion").val();
		var equipmentConfigId = $("#equipmentConfigId").val();
		var newSerialName = $("#newSerialName").val();
		var newMaterialCode = $("#newMaterialCode").val();
		var newSerialNumber = $("#newSerialNumber").val();
		var newHardwareVersion = $("#newHardwareVersion").val();
		var newSoftwareVersion = $("#newSoftwareVersion").val();
		if (oldMaterialCode == "") {
			parent.layer.msg("原模块不能为空");
			return;
		}
		if (newMaterialCode == "") {
			parent.layer.msg("新模块不能为空");
			return;
		}
		newModuleType  = newModuleType.replace(/\s+/g,"");
		oldModuleType=oldModuleType.replace(/\s+/g,"");
		if (newModuleType != oldModuleType) {
			
			parent.layer.msg("物料类型不一致");
			return;
		}
		//传值
		parent.loadData(divname, oldSerialName, oldSerialNumber, oldMaterialCode, oldHardwareVersion, oldSoftwareVersion, equipmentConfigId, newSerialName, newMaterialCode, newSerialNumber, newHardwareVersion, newSoftwareVersion);
	    parent.layer.close(index);
	}
  	
  	
	
  	
	//选择旧模块（原有的模块）
	function sendOldspare() {
		var serialNumber = $("#serialNumber").val();
		var divname = $("#divname").val();
		var workformId = $('#workformId').val();
		window.location.href = basePath
				+ '/cp/module/oldsparepath.do?serialNumber=' + serialNumber
				+ "&divname=" + divname + "&workformId="+workformId;
	}
	//选择新模块（借用的模块）
	function sendnewspare() {

		var oldSerialName = $("#oldSerialName").val();
		var oldSerialNumber = $("#oldSerialNumber").val();
		var oldMaterialCode = $("#oldMaterialCode").val();
		var oldHardwareVersion = $("#oldHardwareVersion").val();
		var oldSoftwareVersion = $("#oldSoftwareVersion").val();
		var equipmentConfigId = $("#equipmentConfigId").val();
		var poNumber = $("#poNumber").val();
		var workformId = $('#workformId').val();
		var divname = $("#divname").val();
		var serialNumber = $("#serialNumber").val();
		if (oldSerialName == "") {
			$("#oldSerialName").val();
			parent.layer.msg('请先选择原模块');
			return;

		} else {
			//oldSerialName = encodeURI(encodeURI(oldSerialName));
			window.location.href = basePath
					+ '/cp/module/newsparepath.do?oldSerialName='
					+ oldSerialName + "&oldSerialNumber=" + oldSerialNumber
					+ "&oldMaterialCode=" + oldMaterialCode
					+ "&oldHardwareVersion=" + oldHardwareVersion
					+ "&oldSoftwareVersion=" + oldSoftwareVersion + "&divname="
					+ divname + "&equipmentConfigId=" + equipmentConfigId
					+ "&oldModuleType=" + oldModuleType+"&poNumber="+poNumber+"&workformId="+workformId+"&serialNumber="+serialNumber;
		}
	}
  </script>
</body>
</html>
