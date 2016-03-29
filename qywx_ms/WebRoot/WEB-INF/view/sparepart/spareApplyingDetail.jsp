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
<script type="text/javascript">
	function applySpare() {
		alert("applyspare");
	}

	/**备件申请
	 * @param {} storeId   仓库ID
	 * @param {} borrowNum  借用数量
	 * @param {} materialCode  备件编码
	 * @param {} stockNum  库存数量
	 */
	function applySpare(storeId, borrowNum, materialCode, stockNum,
			borrowRemark) {
		if (null == borrowNum || "" == borrowNum) {
			alert("请填写借用数量！");
			return false;
		}
		if (parseInt(borrowNum) > parseInt(stockNum)) {
			alert("借用数量大于库存！");
			return false;
		}

		//验证是否是数字
		re = /^\d+\.?\d*$/
		var str = borrowNum;
		if (!re.test(str)) {
			len = str.length;
			str1 = str.substr(0, len - 1);
			alert("只能输入数字,请重新输入");
			obj.value = "";
			obj.focus();
			return false;
		}
		$.ajax({
			type : "post",
			url : "${ctx_path }/cp/sparepart/spareApply.do",
			dataType : "text",
			data : {
				borrownum : borrowNum,
				storeId : storeId,
				materialCode : materialCode,
				borrowRemark : borrowRemark
			},
			success : function(data) {
				if(data.indexOf("true")>=0){
					alert("申请成功！");
					window.location.href=basePath+"/cp/ouath/sparepart/applyingSpareList.do";
				}else{
					alert("申请失败！"+data);
				}
				//alert(data);
			},
			error : function(data) {
				//alert(xhr + type + exception);
				alert(data);
			}
		});
	}
	 
</script>
</head>
<body class="gdlb">

	<div class="gdlb_list">

		<section class="gdlb_info">
			仓库名称：${storeName} <br /> 物料名称：${materialName} <br />
			库存量：${stockNum}<br /> *借用数量:<input id="borrowNum" type="text" /><br />
			借用备注:<input id="borrowRemark" type="text" /><br />
		</section>
		<button onclick="applySpare()">借用</button>
		<input type="button" value="借用"
			onclick="applySpare('${storeId}',$('#borrowNum').val(),'${materialCode}','${stockNum}',$('#borrowRemark').val());">
	</div>

	<script src="${ctx_path }/pub/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>

	<script type="text/javascript">
		var basePath = '${ctx_path}';
	</script>
</body>
</html>