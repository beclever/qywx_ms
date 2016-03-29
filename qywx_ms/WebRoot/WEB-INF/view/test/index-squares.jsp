<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<link rel="stylesheet" type="text/css" href="${ctx_path }/pub/css/mp_style.css">
  </head>
  <body class="index-bg">
	<div id="menuIndex" class="index-mian">
		<div class="nav-box">
			<a href='${ctx_path }/cp/ouath/workOrderDirCheck/list.do' class='nav-content'>
				<img src='${ctx_path }/pub/images/icon/svg/sa-sh.svg' alt='主任审核'/><span>主任审核</span>
			</a>
		</div>
		<div class="nav-box">
			<a href='${ctx_path }/cp/ouath/worklibrarianscheck/list.do' class='nav-content'>
				<img src='${ctx_path }/pub/images/icon/svg/sa-bj-sp.svg' alt='库管员审核'/><span>库管员审核</span>
			</a>
		</div>
		<div class="nav-box">
			<a href='${ctx_path }/cp/ouath/employee/employeeList.do' class='nav-content'>
				<img src='${ctx_path }/pub/images/icon/svg/sa-tx.svg' alt='人员支援'/><span>人员支援</span>
			</a>
		</div>
		<div class="nav-box">
			<a href='${ctx_path }/cp/ouath/sendManage/createWorkOrder.do' class='nav-content'>
				<img src='${ctx_path }/pub/images/icon/svg/sa-pd.svg' alt='新建工单'/><span>新建工单</span>
			</a>
		</div>
		<div class="nav-box">
			<a href='${ctx_path }/cp/ouath/sendManage/waitOrderList.do' class='nav-content'>
				<img src='${ctx_path }/pub/images/icon/svg/sa-gd-ld.svg' alt='待派工单'/><span>待派工单</span>
			</a>
		</div>
		<div class="nav-box">
			<a href='${ctx_path }/cp/ouath/sendManage/sentOrderList.do' class='nav-content'>
				<img src='${ctx_path }/pub/images/icon/svg/sa-gd-cx.svg' alt=''/><span>已派工单</span>
			</a>
		</div>
	</div>
</body>
</html>
