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
<link rel="stylesheet" type="text/css"
	href="<%=basePath %>css/sa/css.css">
<script type="text/javascript">
  	var newModuleType = '${newModuleType}';
  	var oldModuleType = '${oldModuleType}';
  </script>
<script type="text/javascript" src="${ctx_path }/pub/js/page/workorder/workOrderspare.js"></script>
</head>

<body>
	<div class="window_mian">
		<input type="hidden" value="${serialNumber}" id="serialNumber">
		<input type="hidden" value="${poNumber}" id="poNumber">
		<input type="hidden" value="${divname}" id="divname"> 
		<input type="hidden" value="${equipmentConfigId}" id="equipmentConfigId">
	    <input type="hidden" value="${workformId}" name="workformId" id="workformId">
		<div class="workform_list">
			<div class="taskOrder_add_btn">
				<label class="blue">原模块:</label>
				<input type="text" readonly="true" size="15" onclick="sendOldspare()" name="oldSerialName"
					value="${oldSerialName}" id="oldSerialName" taskName="原模块">
					
				<span><img src="${ctx_path}/pub/images/icon_window.png" /></span>
			</div>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">条码编码:</label> <span> <input type="text"
				name="oldSerialNumber" value="${oldSerialNumber }"
				id="oldSerialNumber" readonly="true">
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">物料编码:</label> <span> <input type="text"
				name="oldMaterialCode" value="${oldMaterialCode }"
				id="oldMaterialCode" readonly="true">
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">硬件版本:</label> <span> <input type="text"
				name="oldHardwareVersion" value="${oldHardwareVersion }"
				id="oldHardwareVersion" readonly="true" />
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">软件版本:</label> <span> <input type="text"
				name="oldSoftwareVersion" value="${oldSoftwareVersion }"
				id="oldSoftwareVersion" readonly="true" />
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<div class="taskOrder_add_btn">
				<label class="blue">新模块:</label>
				<input type="text" readonly="true" size="15" onclick="sendnewspare()" name="newSerialName" 
					value="${newSerialName}" id="newSerialName" taskName="新模块" />
					
				<span><img src="${ctx_path}/pub/images/icon_window.png" /></span>
			</div>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue"><font color="red">*</font>物料编码:</label> <span>
				<input type="text" name="newMaterialCode" value="${newMaterialCode}"
				id="newMaterialCode" readonly="true">
			</span>
			<div class="clear"></div>
		</div>
		<div class="workform_list">
			<label class="blue">条码编码:</label> <span> <input type="text"
				name="newSerialNumber" value="${newSerialNumber}"
				id="newSerialNumber" readonly="true">
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
			<input class="window_btn" type="button"  style='background: #5FBFE7' value="确定" onclick="sendWorkspareOK()" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<input class="window_btn" type="button" value="取消" style='background: #A6BBCE' onclick="sendWorkspareCancel()">
		</div>
	</div>
</body>
</html>
