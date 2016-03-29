<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新零件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
	.subject font{color:#333;}
	</style>
  </head>
  
  <body>
  <script type="text/javascript">
	var trans = function(materialName, materialCode, harewareVersion, softwareVersion, sparepartNum, flag, divname, poNumber) {
		var newSerialName=encodeURIComponent(encodeURIComponent(materialName));
		window.location.href=basePath+"/cp/element/elementsparepath.do?newSerialName="+ newSerialName +"&flag="+flag 
				+"&newMaterialCode="+materialCode+"&quantity=1&divname="+divname+"&newHardwareVersion="+harewareVersion
				+"&newSoftwareVersion="+softwareVersion+"&sparepartNum="+sparepartNum+"&poNumber="+poNumber;
	}
  </script>
	<input type="hidden" value="${divname}" name="divname" id="divname">
	
	<c:if test="${brrowlist != null}">
	    <c:forEach items="${brrowlist}" var="bean">
	          <div class="spare_wrapper_top">
					<div id="info"  class="new_title" >
						<div class='new_title_arrow'>&nbsp;</div>  
						   <%-- <a id='form${ status.index}' href="${ctx_path }/cp/element/elementsparepath.do?newSerialName=${bean.materialName }&flag=${flag }&newMaterialCode=${bean.materialCode}&quantity=1&divname=${divname}&newHardwareVersion=${bean.harewareVersion}&newSoftwareVersion=${bean.softwareVersion}&sparepartNum=${bean.sparepartNum}&poNumber=${poNumber}">
								<samp>${bean.materialName }</samp>
						   </a> --%>
						   <a id='form${ status.index}' onclick="trans('${bean.materialName}', '${bean.materialCode}', '${bean.harewareVersion}', '${bean.softwareVersion}', '${bean.sparepartNum}', '${flag }', '${divname}', '${poNumber}')">
								<samp>${bean.materialName }</samp>
						   </a>
					</div>	
	    	</div>
	    </c:forEach>
    </c:if>
   	<c:if test="${brrowlist == null}">
    	<font size="3">没有数据!<a href="javascript:history.go(-1);">请返回</a></font>
    </c:if>
    
  </body>
</html>
