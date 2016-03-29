<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<title>备件申请</title>


<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaPublic.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/newWechat.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaMobile.css" />

<style>
.subject font {
	color: #333;
}

#wrapper {
	position:absolute; z-index:1;
	top:0em; bottom:2px; 
	width:100%;
	background:#aaa;
	overflow:auto;
}

.addspare{
    color: #fff;
	border:0px;
	padding:0.4em 0.4em;
	text-align:center;
	font-size:1em;
	background:#3694E1;
	-moz-border-radius:3px;
    -webkit-border-radius:3px;
    border-radius:3px;
    float:right;
    width:40px;
}

</style>

</head>
<body style="background: #ffffff;">
	
	<div class="mian02">
		<div class="query" style="background-color: #F6F6F6;">
			<div class="query_box03">
				<div class="query_box1">
					<input id='materialName' name='materialName' class="query-style" onpropertychange="this.style.color='black'" oninput="this.style.color='black'"
						type="text" placeholder="请输入名称或者编码查询" onkeyup="getDeleteBnt()" /> 
					<span id="spanMaterialName" style='display:none'><img src="${ctx_path }/pub/images/444.png" onclick="cleanMaterialName()"/></span>
				</div>
			</div>
			<div class="query_box_btn">
				<a class="btn2" onclick="querySpare();">查询</a>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	
	<div class="wrapper" id="wrapper" style="top: 4.6em;">
		<div style="background-color: #87CEFA;" >
			<div id="searchDesc" style="Vertical-align:middle;margin-left: 18px"></div>
		</div>
		<div >
		<div id="scroller">
			<div id="thelist"></div>
		</div>
		</div>
	</div>
	
	<script src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>
	<script src="${ctx_path }/pub/js/page/sparepart/spareApplyingList.js"></script>
	<%@ include file="../include/_jsapi.jsp"%>
	<script type="text/javascript">
		var basePath = '${ctx_path}';
		wx.ready(function() {
			wx.hideOptionMenu();
		})
	</script>
</body>
</html>