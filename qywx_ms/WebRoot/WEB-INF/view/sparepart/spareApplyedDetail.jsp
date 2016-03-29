<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>备件详情</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaPublic.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/newWechat.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaMobile.css" />
</head>
<body class="gdlb">
	
	<div class='nav_bottom'>
		<a class="nav_bottom_back" href="javascript:history.go(-1)"> 返回
		</a>
	</div>
	<div class="gdlb_list">
	
		<section class="gdlb_info">
			借用单号：${borrowId}<br />
			物料编号：${materialCode}<br />
			物料名称：	${materialName}<br />
			借用日期：	${borrowTime}<br />
			借用数量：${sparepartNum}<br />
			状&nbsp;&nbsp;态：已借用<br />
		</section>
	</div>

	<script src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>

	<script type="text/javascript">
		var basePath = '${ctx_path}';
	</script>
</body>
</html>