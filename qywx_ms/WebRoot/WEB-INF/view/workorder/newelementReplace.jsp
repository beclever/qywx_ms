<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新零件替换</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${ctx_path }/pub/css/newWechat.css" type="text/css">
	<link rel="stylesheet" href="${ctx_path }/pub/css/SaMobile.css" type="text/css">
  	<link rel="stylesheet" href="${ctx_path }/pub/css/SaPublic.css" type="text/css">
	<style>
	.subject font{color:#333;}
	</style>
  </head> 
  <body>
    <input type="hidden" value="${divname}" name="divname" id="divname">
    <c:if test="${brrowlist != null}">
	    <c:forEach items="${brrowlist}" var="bean">
	       <div class="spare_wrapper_top">
				<div id="info"  class="new_title" >
				   <div class='new_title_arrow'>&nbsp;</div>
						<a onclick='formSubmit(this)' id='form${ status.index}' href="/cp/element/elementsparepath.do?newSerialName=${bean.materialName }&flag=${flag }&newMaterialCode=${bean.materialCode}&quantity=1&divname=${divname}&newHardwareVersion=${bean.harewareVersion}&newSoftwareVersion=${bean.softwareVersion}&poNumber=${poNumber}">
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
