/*var index = parent.layer.getFrameIndex(window.name);
function closeWindow() {
	parent.layer.close(index);
}*/

window.onpopstate=function (){
	var url= basePath + "/cp/equipment/detail.do?serialNumber=" + serialNumber +"&dateTime=" + new Date().getTime();
	window.history.replaceState("","",url);	
};

function backDetail() {
	location.href = basePath + "/cp/equipment/detail.do?serialNumber=" + serialNumber +"&dateTime=" + new Date().getTime();
}
function edit(id) {
	location.href = basePath + "/cp/equipment/customerinfo.do?type=update&equipmentContactId=" + id + "&serialNumber="+ serialNumber + "&dataTime=" + new Date().getTime();
}
function add(id) {
	location.href = basePath + "/cp/equipment/customerinfo.do?type=add&equipmentId=" + id  + "&serialNumber="+ serialNumber + "&dataTime=" + new Date().getTime();
}
function del(id) {
	// 询问框
	layer.confirm('您确定删除该客户联系人？', {
		closeBtn : false,
		btn : [ '确定', '取消' ], // 按钮
		shade : false
	// 不显示遮罩
	}, function() {
		var delIndex = layer.load(2,{shade:[0.6,'#666']});//0.6透明度的灰色背景
		$.ajax({
			type : "post",
			url : basePath + "/cp/equipment/deleteCustomer.do",
			dataType : "json",
			data : {
				equipmentContactId : id,
			},
			success : function(data) {
				if ("1" == data.status) {
					layer.msg("删除成功！");
					$("#customer_" + id).remove();
				} else {
					layer.msg("删除失败，" + data.errMsg);
				}
				layer.close(delIndex);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert("发生错误:" + errorThrown);
				layer.close(delIndex);
			}
		});
	}, function() {
	});
}