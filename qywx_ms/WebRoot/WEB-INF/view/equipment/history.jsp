
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>历史服务信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body class="history_bg">
	  <c:forEach items="${beanlist}"  var="bean" varStatus="sta">
	 
	    <c:choose>
	    
	    <c:when test="${sta.count%2==0}">
			  <div class="history_box">
				<ul>
					<li>
						<label>完成时间：</label><span>${bean.finishTime }</span>
					</li>
                    <li>
                         <label>服务类型：</label><span>${bean.taskType}</span>
                    </li>
                    <li>
                         <label>工程师：</label><span>${bean.engineerName }</span>
                    </li>
                    <li>
                         <label>工作描述：</label><span>${bean.workContent }</span>
                    </li>
				</ul>
			  </div>
	    </c:when>
	    <c:otherwise>
	   
			  <div class="history_box">
                  <ul>
					<li>
                      	<label>完成时间：</label><span>${bean.finishTime }</span>
                  	</li>
                 	<li>
                      	<label>服务类型：</label><span>${bean.taskType}</span>
                  </li>
					<li>
						<label>工程师：</label><span>${bean.engineerName }</span>
                  </li>
					<li>
                      	<label>工作描述：</label><span>${bean.workContent }</span>
                   </li>
				 </ul>  
			  </div>
		 
	    </c:otherwise>
	    </c:choose>
	  </c:forEach>
	   <script type="text/javascript">
			var basePath = '${ctx_path}';
		</script>
  </body>
</html>
