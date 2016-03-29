//保存
function sendFaultOK() {
	var updateReason = document.getElementById("updateReason").value;
	var paperRemark = document.getElementById("paperRemark").value;
	if (updateReason == "") {
		layer.alert("更改原因不能为空");
		return;
	}else if(paperRemark == ""){
		layer.alert("原因说明不能为空");
		return;
	}
	// 更新纸质工单
	updatePaper();
}
window.onpopstate=function (){
	
	/*var url= basePath+"/cp/workOrder/default.do?returnPageNum=1&random="+Math.random();

	window.history.replaceState("","",url);	*/
}
// 更新纸质工单
function updatePaper() {
	var paperId = $("#paperId").val(); // 纸质工单ID
	var paperCode = $("#paperCode").val();// 纸质code
	var updateReason = $("#updateReason").val();// 更新原因
	var paperRemark = $("#paperRemark").val();// 更新备注
	var poNumber = $("#poNumber").val();
	$.ajax({
		type : "post",
		url : basePath + "/cp/workOrder/paperUpdate.do",
		dataType : "json",
		data : {
			paperId : paperId,
			paperCode : paperCode,
			updateReason : updateReason,
			paperRemark : paperRemark
		},
		success : function(data) {
			if (data.flag == true) {
				//layer.msg("纸质工单更改成功", {icon: 1});
				layer.alert("纸质工单更改成功", {closeBtn: false});
				var args = [ 'paperLocation' ];
				args.push(data.msg);
				// ==================add by zt
				// 主要为纸质工单的页面显示====================
				var paperInfo = data.msg;
				var msgArray = paperInfo.split(",")
				var paperId = msgArray[0];
				var paperCode = msgArray[1];
				parent.location.href = basePath+'/cp/workOrder/dealWorkOrder.do?detailType=1&poNumber='
						+ poNumber
						+ "&paperIdInfou="
						+ paperId
						+ "&paperCodeInfou=" + paperCode;
			} else {
				layer.alert(data.msg, {icon: 2});
				//window.location.href = basePath+'/cp/workOrder/dealWorkOrder.do?detailType=1&poNumber='+ poNumber;
			}
		},
		error : function(data) {
			layer.alert(data.msg, {icon: 2});
			//window.location.href = basePath+'/cp/workOrder/dealWorkOrder.do?detailType=1&poNumber='+ poNumber;
		}
	});
}