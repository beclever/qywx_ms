<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新模块</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style>
.subject font {
	color: #333;
}
</style>
<script type="text/javascript">
var trans = function(newSerialName,oldSerialName,args) {
	var newSerialName=encodeURIComponent(encodeURIComponent(newSerialName));
	var oldSerialName=encodeURIComponent(encodeURIComponent(oldSerialName));
	window.location.href=basePath+"/cp/module/worksparepath.do?newSerialName="+newSerialName + "&oldSerialName="+oldSerialName+args;
}
</script>
</head>

<body>
	<c:if test="${newsparelist != null}">
		<c:forEach items="${newsparelist}" var="bean">
			<div class="spare_wrapper_top">
				<div id="info" class="new_title">
					<div class='new_title_arrow'>&nbsp;</div>
					<a onclick="trans('${bean.materialName}', '${oldSerialName }', 
					'&oldSerialNumber=${oldSerialNumber}&oldMaterialCode=${oldMaterialCode}&oldHardwareVersion=${oldHardwareVersion}'+
					'&oldSoftwareVersion=${oldSoftwareVersion}&equipmentConfigId=${equipmentConfigId}'+
					'&newMaterialCode=${bean.materialCode}&newSerialNumber=${bean.serialNumber}'+
					'&newHardwareVersion=${bean.harewareVersion}&newSoftwareVersion=${bean.softwareVersion}'+
					'&divname=${divname}&newModuleType=${bean.moduleType}'+
					'&oldModuleType=${oldModuleType}&poNumber=${poNumber}'+'&workformId=${workformId }'+'&serialNumber=${serialNumber }' + '&poNumber=+${poNumber}' )">
						<samp>${bean.materialName}</samp>
					</a>
				</div>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${newsparelist == null}">
		<font size="3">没有数据!<a href="javascript:history.go(-1);">请返回</a></font>
	</c:if>
</body>
</html>
