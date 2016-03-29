<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>故障描述</title>
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
    <!--   <div class="backimg">故障描述</div>-->
     <div class="mian02">
	     <form action="${ctx_path }/fault/faultRemark.do" method="post" id="form1">
		     <div class="query">
	             <div class="query_box">
	             	<div class="query_boxm1">
		             	<div class="input_in_addBtn_div"><input style="border:none;" name="keyword" type="text" value="${keyword}" id="keyword" size="15" /></div>
		            </div>
	             </div>
	             <div class="querysbar_img">
		  			<div class="querysbar_img2" onclick="searchFaultRemark(true);"></div>
		  		 </div>
	             <div class="clear"></div>
	         </div>
	         <%--<div class="query" style="padding:0.5em 0em 0.5em 0.3em;">
	             <div class="query_02">
	                 <input  name="keyword" type="text" value="${keyword}" id="keyword" class="query_text" />
	             </div>
	             <a class="query_03" onclick="searchFaultRemark()">&nbsp;</a>
	         </div> --%>
	     </form>
	     <c:if test="${problemlist != null}">
		     <c:forEach  items="${problemlist}" var="problembean">
		     	<div class="spare_wrapper_top">
			         <div id="info"  class="new_title" >
			         <div class="new_title_arrow">&nbsp;</div>
			             <a    href=
			                   <c:choose>
			                   <c:when test="${problembean.lastLayer==0 && keyword eq null}">
			                           "${ctx_path }/cp/fault/faultRemark.do?problemCodeId=${problembean.problemId}&problemPartId=${problemPartId}"
			             </c:when>
			             <c:otherwise>
			                 "javascript:void(saveProblemRemark('${problembean.description}','${problembean.problemId}'))"
			             </c:otherwise>
			             </c:choose>>
			             <samp>${problembean.problemCode},${problembean.description}</samp>
			             </a>
			         </div>
		         </div>
		     </c:forEach>
	     </c:if>
	    <c:if test="${problemlist == null}">
	    	<font size="3">没有数据!<a href="javascript:history.go(-1);">请返回</a></font>
	    </c:if> 
	</div>
	<script type="text/javascript" src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"  src="${ctx_path }/pub/js/page/faultCommon.js?v=2015072111"></script>             
    <script type="text/javascript">
    		var basePath = "${ctx_path}";
	      	var problemPartId ='${problemPartId}';
	      	var layer = '${layer}';
	      	
	      //故障描述查询
	      	function searchFaultRemark()
	      	{
	      	    keyword = $("#keyword").val();
	      	    window.location.href=basePath+'/cp/fault/faultRemark.do?keyword='+keyword+'&problemPartId='+problemPartId+'&problemCodeId='+layer;
	      	}
			//保存
	      	function saveProblemRemark(description,problemCodeId){
	      	    args1 = 'troubleCode=' + description;
	      	    args2 = 'problemCodeId=' + problemCodeId;
	      	    saveFaultInfos(args1, args2);
	      	}
    </script>
  </body>
</html>
