<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备遗留问题</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body class="history_bg">
    <!--顶部 -->
	  	  	  <c:forEach items="${problemlist}" var="bean" varStatus="sta">
		  	  	  <div class="history_box">
					<ul>
						<li>
	                       <label>级别：</label><span>
								<c:choose>
									<c:when test="${'1' == bean.levelId }">提醒</c:when>
									<c:when test="${'2' == bean.levelId }">警告</c:when>
									<c:when test="${'3' == bean.levelId }">严重</c:when>
									<c:otherwise>未知</c:otherwise>
								</c:choose>
	                       </span>
	                   </li>
	                   <li>
	                       <label>状态：</label><span>${bean.status }</span>
	                   </li>
	                   <li>
	                       <label>提交人：</label><span>${bean.recordPerson }</span>
	                   </li>
	                   <li>
	                       <label>问题描述：</label><span>${bean.description }</span>
	                   </li>
	                   <li>
	                       <label>创建时间：</label><span>${bean.recordTime }</span>
	                   </li>
	                   <li>
	                       <label>解决时间：</label><span>${bean.solveTime }</span>
	                   </li>
	                 </ul>
				  </div>
	  	  	  </c:forEach>
	  	 <script type="text/javascript">
			var basePath = '${ctx_path}';
		</script>
  </body>
</html>
