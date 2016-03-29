<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="from"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<title><decorator:title default="" /></title>
<base href="<%=basePath%>">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=1.0, user-scalable=yes" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>pub/css/SaPublic.css?datetime=20160303">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>pub/css/newWechat.css?datetime=20160303">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>pub/css/SaMobile.css?datetime=20160303">
<script src="<%=basePath%>pub/js/jquery-1.8.3.min.js"></script>
	<script src="<%=basePath%>/pub/js/layer/layer.js"></script>
<script type="text/javascript">
		document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
			WeixinJSBridge.call('hideOptionMenu');
			var u = navigator.userAgent, app = navigator.appVersion;
			var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
			var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
			//wx.closeWindow();//调用微信JS接口
			if(isiOS){
				//动态加载 CSS 文件
				dynamicLoading.css("pub/css/IOSStyle.css");
			}else{
				dynamicLoading.css("pub/css/AndroidStyle.css");
			}
		});
	</script>

<script type="text/javascript">
  	var basePath = "${ctx_path}";
    //JavaScript动态加载CSS和JS文件
	var dynamicLoading = {
		    css: function(path){
				if(!path || path.length === 0){
					throw new Error('argument "path" is required !');
				}
				var head = document.getElementsByTagName('head')[0];
		        var link = document.createElement('link');
		        link.href = path;
		        link.rel = 'stylesheet';
		        link.type = 'text/css';
		        head.appendChild(link);
		    },
		    js: function(path){
				if(!path || path.length === 0){
					throw new Error('argument "path" is required !');
				}
				var head = document.getElementsByTagName('head')[0];
		        var script = document.createElement('script');
		        script.src = path;
		        script.type = 'text/javascript';
		        head.appendChild(script);
		    }
		};
 </script>
<decorator:head />
</head>
<body class="<decorator:getProperty property="body.class"/>"
	onload="<decorator:getProperty property="body.onload"/>"
	style="<decorator:getProperty property="body.style"/>">
	<div class="subject" id="pageContent">
		<decorator:body />
	</div>
</body>
</html>
