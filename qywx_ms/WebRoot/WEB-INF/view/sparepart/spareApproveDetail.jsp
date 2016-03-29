<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>备件审批</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaPublic.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/newWechat.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx_path }/pub/css/SaMobile.css" />
<%@ include file="../include/_jsapi.jsp"%>	
<style type="text/css">

.content_list label{
    width:80%;
    float:left;
  /*  color:#0E3995;*/
  	color:#777;
    line-height:150%;
    text-align: left;
    display:block;
	}
</style>
	
<script type="text/javascript">
	window.onpopstate=function (){		
		var url= basePath+"/cp/sparepart/approveSparePage.do?returnPageNum=1&random="+Math.random();
		window.history.replaceState(url);	
	}
	function approvalSpare(applyId) {
		var serialNumber = $("#serial option:selected").text();
		var note = $("#note").val();
		var borrowNum = ${borrowNum};
		var flag = ${flag};
		
		if(flag=='1'&&$("#serial option:selected").length!=borrowNum){
			layer.alert("条码编码数量不正确！");
			return ;
		}
		
		var index  = layer.load(2,{shade:[0.6,'#666']});// 0.6透明度的灰色背景
 		$.ajax({     
			type: "post",     
			url:basePath+"/cp/sparepart/approvalSpare.do",
			dataType: "json",
			data:{
				applyId:applyId,
				flag:flag,
				applyNote:note,
				serialNumber:serialNumber
			},
			success: function (data) {
				layer.close(index);
				if(data.returnResult==true) {
					layer.msg("备件审批成功");
					var u = navigator.userAgent, app = navigator.appVersion;
					var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
					var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
					//wx.closeWindow();
					if(isiOS){
						//动态加载 CSS 文件
						wx.closeWindow();
					}else{
						window.location.href=basePath+"/cp/ouath/sparepart/approveSparePage.do";
					}
					
				}else{
					layer.alert("备件审批失败,"+"原因："+data.returnMessage+data.errorMessage);
				}
			},
			error:function(xhr,type,exception){
				layer.close(index);
				layer.alert("备件审批失败,"+"原因："+xhr+type+exception);
			}
		}); 
	}
	
</script>
	
</head>
<body class="gdlb">

	<!--顶部 -->
	<div class='nav_bottom'>
		<a class="nav_bottom_back" href="javascript:history.go(-1)"> 返回
		</a>
		<a class="nav_operation"
			onclick="approvalSpare('${applyId}')"> 确认</a>
	</div>

	<div class="mian02" style="vertical-align:middle;">
		<div class="content_list">
			<c:if test="${flag eq '1'}">
				<label>请选择条码编码${borrowNum}条:</label><br/>
				<span>
		            <select class="taskOrder_inputwidth" id="serial" multiple="true">
		                <c:forEach items="${serialNumberList}" var="sNumber">
		                    <option value="${sNumber}" >
		                    	${sNumber}
		                    </option>
		                </c:forEach>
		            </select>
		        </span>
			</c:if>
			
			<c:if test="${flag eq '0'}">
				<label>该设备没有条形编码</label><br/>
			</c:if>
		</div>
		
		<div class="content_list" >
			<label>备注:</label> <br/><textarea id="note" rows="4"></textarea>
		</div>
	</div>
	
	<script src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>	
	
	<script type="text/javascript">
		var basePath = '${ctx_path}';
	</script>
</body>
</html>