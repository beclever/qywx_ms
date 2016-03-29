<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>工单列表</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaPublic.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/newWechat.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaMobile.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/iscroll.css" />
</head>
<body class="gdlb">
	<div class="subject">
		<div class="samian">
			<div class="wrapper" id="wrapper">
				<div id="scroller">
					<div id="pullDown">
						<span class="pullDownIcon"></span> <span class="pullDownLabel">上拉刷新...</span>
					</div>
					<div id="thelist">
						
						
					</div>
					<div id="pullUp">
							<span class="pullUpIcon"></span><span class="pullUpLabel">下拉获取更多...</span>
						</div>
				</div>
			</div>
			<input type="hidden" id="pageNum" value="1">
		</div>
	</div>
<script src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
<script src="${ctx_path }/pub/js/iscroll.js"></script>
<script src="${ctx_path }/pub/js/pageCommon.js"></script>
<script src="${ctx_path }/pub/js/layer/layer.js"></script>
<script src="${ctx_path }/pub/js/page/workorder/workorderList.js?${time}"></script>
<%@ include file="../include/_jsapi.jsp"%>
<script type="text/javascript">
var basePath = '${ctx_path}';
wx.ready(function () {
	wx.hideOptionMenu();
})
</script>
</body>

</html>

