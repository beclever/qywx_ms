<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>处理方法</title>
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
  	<!-- <div class="backimg">处理方法</div> -->
  	<div class="mian02">
	    <input type="hidden" value="${divname}" name="divname" id="divname">
	    <c:if test="${problemlist !=null}">
		    <c:forEach  items="${problemlist}" var="problembean">
		    	<div class="spare_wrapper_top">
			        <div id="info"  class="new_title" >
			        	<div class='new_title_arrow'>&nbsp;</div>
			            <a    href=
			                  <c:choose>
			                    <c:when test="${problembean.lastLayer==0}">
			                          "${ctx_path }/cp/fault/faultProcess.do?problemMethodId=${problembean.problemId}"
			                    </c:when>
			                    <c:otherwise>
			                        "javascript:void(saveProblemProcess('${problembean.description}','${problembean.problemId}'))"
			                    </c:otherwise>
			                </c:choose>>
			
			            <samp>${problembean.description}</samp>
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
  //处理方法
  	function searchProcess()
  	{
  		var form=Document().getElementById("form1");
  		form1.action=basePath+"/cp/fault/faultProcess.do";
  		form1.submit();
  	}

  	function saveProblemProcess(processCode,problemMethodId){
  	    args1 = 'processCode=' + processCode;
  	    args2 = 'problemMethodId=' + problemMethodId;
  	    saveFaultInfos(args1, args2);
  	    
  	}
  	</script>
  </body>
</html>
