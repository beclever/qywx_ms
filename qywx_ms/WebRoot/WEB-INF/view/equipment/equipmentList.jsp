<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备列表</title>
    <link rel="stylesheet" href="${ctx_path }/pub/css/iscroll.css" />
    <style type="text/css">
	select {
		margin-bottom: 0.3em;
		height: 2em;
	}
	.query_box_btn a.btn2 {
  color: #fff;
  border: 0px;
  font-size: 1.2em;
  background: #3694E1;
  -moz-border-radius: 3px;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  width: 44%;
  float: left;
  margin: 0 25%;
  text-align: center;
  height: 23px;
}
.query_box_btn{float:none;margin-top: 0em;}
#equipmentCustomerName {
    color: #ccc;
    height: 1.5em;
    margin-bottom: 0.2em;
    width: 98%;
}
</style>
  </head>
  <body>
  	<div class="mian02">
		  	<table width="100%" border="0" >
	             <div class="query" style="background-color: #F6F6F6; height: 250px;">
					<div class="query_box03" style="width: 97%">
						<div class="query_box1">
							<input id='equipmentSerialNumber' name='serialNumber' style="color: #555;" type="text" placeholder="设备序列号" onkeyup="pressSerialNumber();" />
							<span id="spanSerialNumber" style='display:none'><img src="${ctx_path }/pub/images/444.png" onclick="cleanSerialNumber()"/></span>
						</div>
						<div class="query_box1">
							<input id='equipmentCustomerName' name='customerName' style="color: #555;" type="text" placeholder="客户名称" onkeyup="pressCustomerName();" />
							<span id="spanCustomerName" style='display:none'><img src="${ctx_path }/pub/images/444.png" onclick="cleanCustomerName()"/></span>
						</div>
						<div class="query_box1">
							<input id='equipmentBranchName' name='branchName' style="color: #555;" type="text" placeholder="网点名称" onkeyup="pressBranchName();" />
							<span id="spanBranchName" style='display:none'><img src="${ctx_path }/pub/images/444.png" onclick="cleanBranchName()"/></span>
						</div>
						<div class="query_box1">
							<input id='equipmentInstallAddress' name='installAddress' style="color: #555;" type="text" placeholder="安装地址" onkeyup="pressInstallAddress();" />
							<span id="spanInstallAddress" style='display:none'><img src="${ctx_path }/pub/images/444.png" onclick="cleanInstallAddress()" /></span>
						</div>
						<div class="query_box1">
							<input id='equipmentATMNumber' name='ATMNumber' type="text" style="color: #555;" placeholder="ATM号" onkeyup="pressATMNumber();" />
							<span id="spanATMNumber" style='display:none'><img src="${ctx_path }/pub/images/444.png" onclick="cleanATMNumber()" /></span>
						</div>
					</div>
					<div class="query_box_btn">
						<a class="btn2" onclick='selectSaEquipmentList(true)'>查询</a>
					</div>
					<div class="clear"></div>
 				</div>
             </table>    
                 <input type="hidden" value="1" name="pageNum" id="pageNum"/>
			     <div id="wrapper" style="top:22em;">
						 <div id="scroller">
						 	 <div id="thelist"></div>
							 <div id="pullUp">
									<span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载更多...</span>
							 </div>
					 	 </div>
					 </div>
				</div>
		<script type="text/javascript" src="${ctx_path }/pub/js/common.js"></script>
		<script type="text/javascript" src="${ctx_path }/pub/js/iscroll.js"></script>
		<script type="text/javascript" src="${ctx_path }/pub/js/page/equipment/equipmentList.js"></script>
  		<script type="text/javascript">
			var basePath = '${ctx_path}';
		</script>
  </body>
</html>
