<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备配置信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <script type="text/javascript" src="${ctx_path }/pub/js/page/equipment/equipconfig.js"></script>
   <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
   <style type="css/text">
   	ul li{
   		line-height:30px;padding:3px;
   	}
   </style>
   <script type="text/javascript">
		document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
			WeixinJSBridge.call('hideOptionMenu');
		});
  	 </script>
  </head>
  
  <body class="history_bg">
  <!--顶部 -->
        <div class="equipmentconfig_box">
        <ul>
        <c:forEach items="${configlist}" var="parentbeans" varStatus="sta">
		<li><span style="color: #0E3995;font-weight: bold;">${sta.count }</span>&nbsp;${ parentbeans.sparepartName}
		        <c:if test="${parentbeans.children!=null}">
		             <ul>
		                <c:forEach items="${parentbeans.children}" var="childbean" varStatus="schild">
		                <li>
		               		&nbsp;&nbsp;<span style="color: #0E3995;font-weight: bold;">${sta.count }.${schild.count }</span>&nbsp;${childbean.sparepartName}	
		                         <ul>
		                         <c:forEach items="${childbean.children}" var="bean" varStatus="sschild">
		                          <li>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #0E3995;font-weight: bold;">${sta.count }.${schild.count }.${sschild.count }</span>&nbsp;${bean.sparepartName }</li>
		                         </c:forEach>
		                        
		                         </ul>
		                         
		                 </li>
		                </c:forEach>
		             </ul>
		        
		        </c:if>
		</li> 
		</c:forEach>	
		</ul>  
        </div>
        <script type="text/javascript">
			var basePath = '${ctx_path}';
		</script>
  </body>
</html>
