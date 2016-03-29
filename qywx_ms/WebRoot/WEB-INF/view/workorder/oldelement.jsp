<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>旧零件</title>
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
		var trans = function(materialName, flag, newSerialName, newMaterialCode, oldMaterialCode, quantity, divname, poNumber) {
			var materialName=encodeURIComponent(encodeURIComponent(materialName));
			var newSerialName=encodeURIComponent(encodeURIComponent(newSerialName));
			window.location.href=basePath+"/cp/element/elementsparepath.do?oldSerialName="+ materialName +"&flag="+flag 
					+"&newSerialName="+newSerialName+"&newMaterialCode="+newMaterialCode+"&oldMaterialCode="+oldMaterialCode+"&quantity="+quantity+"&divname="+divname +"&poNumber="+poNumber;
		}
	  </script>
	<input type="hidden" value="${divname}" name="divname">
	<form action="/cp/spare/query"  method="post" id="form1">
	   <c:if test="${oldelementlist != null}">
		   <c:forEach items="${oldelementlist}" var="bean">
		   		 <div class="spare_wrapper_top">
						<div id="info"  class="new_title" >
						   <div class='new_title_arrow'>&nbsp;</div>
			   				 <%-- <a onclick='formSubmit(this)' href="${ctx_path }/cp/element/elementsparepath.do?oldSerialName=${bean.materialName}&flag=${flag}&newSerialName=${newSerialName}&newMaterialCode=${newMaterialCode}&oldMaterialCode=${bean.materialCode}&quantity=${quantity}&divname=${divname}&poNumber=${poNumber}">
				    		 <samp>${bean.materialName }</samp>
			  				</a> --%>
			  				<a onclick="trans('${bean.materialName}', '${flag}', '${newSerialName}', '${newMaterialCode}', '${bean.materialCode}', '${quantity }', '${divname}', '${poNumber}')">
								<samp>${bean.materialName }</samp>
						   </a>
			  			</div>			
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${oldelementlist == null}">
			<font size="3">没有数据!<a href="javascript:history.go(-1);">请返回</a></font>
	    </c:if> 
	</form>
  </body>
</html>
