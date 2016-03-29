<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>故障部位信息</title>
    
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
  	<div class="mian02">
	    <form action="${ctx_path }/cp/fault/faultInfo" method="post" id="form1">
	      <div class="query">
             <div class="query_box">
             	<div class="query_boxm1">
	             	<div class="input_in_addBtn_div"><input style="border:none;" name="keyword" size="15" type="text" value="${keyword}" id="keyword" /></div>
	            </div>
             </div>
             <div class="querysbar_img">
	  			<div class="querysbar_img2" onclick="searchFaultInfo(true);"></div>
	  		 </div>
             <div class="clear"></div>
         </div>
	  </form>
	
		<c:if test="${problemlist !=null}">
	           <c:forEach  items="${problemlist}" var="problembean">
	           <div class="spare_wrapper_top">
					<div id="info"  class="new_title" >
					   <div class='new_title_arrow'>&nbsp;</div>
		               <a href=
		                   <c:choose>
		                       <c:when test="${problembean.lastLayer==0 && keyword eq null }">
		                           "${ctx_path }/cp/fault/faultInfo.do?problemPartId=${problembean.problemId}&problemPartCode=${problembean.description}&equipmentModel=${equipmentModel}"
		                       </c:when>
		                       <c:otherwise>                       		
	                               "javascript:void(saveProblemPart('${problembean.problemId}','${problembean.description}'))"
		                       </c:otherwise>
		                   </c:choose>
		               >
		                   <samp>${problembean.description}</samp>
		               </a>
		               
		           </div>
		         </div>
	           </c:forEach>
		</c:if>
	    <c:if test="${problemlist == null}">
	    	<font size="3">抱歉，暂无数据<a href="javascript:history.go(-1);">请返回</a></font>
	    </c:if>  
    </div>
    <script type="text/javascript" src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>     
    <script type="text/javascript"  src="${ctx_path }/pub/js/page/faultCommon.js"></script>             
	<script type="text/javascript">
	var basePath = "${ctx_path}";
	var equipmentModel ='${equipmentModel}';
	var layer = '${layer}';
	//查询
	function searchFaultInfo()
	{
		var keyword = $("#keyword").val();
	    window.location.href=basePath+'/cp/fault/faultInfo.do?keyword='+keyword+'&problemPartId='+layer+'&equipmentModel='+equipmentModel ;
	}
	//保存故障信息
	function saveProblemPart(problemPartId,problemPartCode){
	    args1 = 'problemPartId='+problemPartId;
	    args2 = 'problemPartCode='+problemPartCode;
	    saveFaultInfos(args1, args2);
	    
	}
	</script>
  </body>
</html>
