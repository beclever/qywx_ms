<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="javascript:void(0)" id="faltPart">添加故障部位</a>
<a href="javascript:void(0)" id="model">模块替换</a>
<a href="javascript:void(0)" id="sparepath">零件替换</a>
传来的：<span id="para"></span>
<script src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
<script src="${ctx_path }/pub/js/layer/layer.js"></script>
<script type="text/javascript">
var basePath = '${ctx_path}';
$(document).ready(function() {
	$("#faltPart").click(function() {
		layer.open({
		    type: 2,
		    title: '故障部位',
		    shadeClose: true,
		    shade: 0.2,
		    area: ['380px', '90%'],
		    content: 'http://localhost:8080/qywx_ms/cp/fault/faultIndex.do' //iframe的url
		}); 
	});
	
	$("#model").click(function() {
		layer.open({
		    type: 2,
		    title: '模块替换',
		    shadeClose: true,
		    shade: 0.2,
		    area: ['380px', '90%'],
		    content: 'http://localhost:8080/qywx_ms/cp/module/worksparepath.do' //iframe的url
		}); 
	});
});
</script>
</body>
</html>