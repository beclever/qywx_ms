/*window.onpopstate=function (){
	var url= basePath + "/cp/ouath/equipment/list.do";
	window.history.replaceState("","",url);	
};*/

//弹出框 
function ShowDIV(thisObjID) {
	$("#BgDiv").css({
		display : "block",
		height : $(document).height()
	});
	//var yscroll = document.documentElement.scrollTop;
	$("#" + thisObjID).css("top", "40px");

	$("#" + thisObjID).css("display", "block");
	document.documentElement.scrollTop = 0;
}
// 关闭弹出框
function closeDiv(thisObjID) {
	$("#BgDiv").css("display", "none");
	$("#" + thisObjID).css("display", "none");
}

// 保存设备详细信息
function saveEquimentDetail() {
	var serialNumber = $("#serialNumber").val(); // 设备序列号 String(Y)
	var equipmentId = $("#equipmentId").val();
	;// 设备ID String(N)
	var chief = $("#chief").val(); // 设备负责人 String(N)
	var equipmentArea = $("#equipmentArea").val(); // 设备位置
	var installAddress = $("#installAddress").val(); // 安装地址 String(N)
	var branchName = $("#branchName").val(); // 网点名称 String(Y)
	var branchPrincipal = $("#branchPrincipal").val(); // 网点负责人String(Y)
	var branchPrincipalTel = $("#branchPrincipalTel").val(); // 网点负责人电话String(Y)
	var installType = $("#installType").val(); // 安装类型 String(Y)
	var installModel = $("#installModel").val(); // 安装方式 String(Y)
	var atmManager = $("#atmManager").val(); // ATM管理员 String(Y)
	var atmManagerTel = $("#atmManagerTel").val(); // ATM管理员联系方式 String(Y)
	var ATMNumber = $("#ATMNumber").val(); // ATM号 String(Y)
	var bankNumber = $("#bankNumber").val(); // 银行号 String(Y)
	var bankTerminalNumber = $("#bankTerminalNumber").val(); // 支持终端号
																// String(Y)
	var netProtocol = $("#netProtocol").val(); // 网络连接协议 String(Y)
	var LocalIP = $("#LocalIP").val().trim(); // 本机IP地址 String(Y)
	var pip = $("#pip").val().trim(); // P端IP地址 String(Y)
	var gateway = $("#gateway").val().trim(); // 网关 String(N)
	var subnet_hide_address = $("#subnet_hide_address").val().trim(); // 子网掩码
																		// String(N)
	// 设备档案验证
	var tip = "";
	var i = 0;
	var appendTip = function(msg) {
		if (msg.length == 0)
			return;
		tip += (tip.length > 0 ? '\n' : '') + (++i) + ': ' + msg;
	};
	if ("" == ATMNumber) {
		appendTip("ATM号不能为空");
	}
	if ("" == branchName) {
		appendTip("网点名称不能为空");
	}
	/*if ("" == atmManager) {
		appendTip("ATM管理员不能为空");
	}
	if ("" == atmManagerTel) {
		appendTip("ATM管理员电话不能为空");
	}*/
	if ("" == installAddress) {
		appendTip("安装地址不能为空");
	}
	if (!ipCheck2(LocalIP)) {
		appendTip("本机IP地址格式不符合");
	}
	/*
	 * if(!ipCheck2(pip)){ appendTip("P端IP地址格式不符合"); }
	 */
	if (!ipCheck2(gateway)) {
		appendTip("网关格式不符合");
	}
	if (!ipCheck2(subnet_hide_address)) {
		appendTip("子网掩码格式不符合");
	}
	if (tip.length > 0) {
		layer.alert(tip);
		return;
	}
	$.ajax({
		type : "post",
		url : basePath + "/cp/equipment/doSaveEquipment.do",
		dataType : "text",
		data : {
			serialNumber : serialNumber,
			equipmentId : equipmentId,
			chief : chief,
			equipmentArea : equipmentArea,
			installAddress : installAddress,
			branchName : branchName,
			branchPrincipal : branchPrincipal,
			branchPrincipalTel : branchPrincipalTel,
			installType : installType,
			installModel : installModel,
			atmManager : atmManager,
			atmManagerTel : atmManagerTel,
			ATMNumber : ATMNumber,
			bankNumber : bankNumber,
			bankTerminalNumber : bankTerminalNumber,
			netProtocol : netProtocol,
			LocalIP : LocalIP,
			pip : pip,
			gateway : gateway,
			subnet_hide_address : subnet_hide_address
		},
		/*
		 * beforeSend: function () { cordova.exec(function () { }, function (e) { },
		 * "TipPlugin", "defaultSubmit", []); return true; }, complete: function () {
		 * cordova.exec(function () { }, function (e) { }, "TipPlugin",
		 * "defaultSubmit", []); },
		 */
		success : function(data) {
			var obj = eval('(' + data + ')');
			if (obj.returnResult == true) {
				layer.alert(obj.returnMessage);
			} else {
				layer.alert(obj.errorMessage);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("发生错误:" + errorThrown);
		}
	});

}

function ipCheck2(str) {
	if ("" == str)
		return true;
	var reg = /^([0,1]?\d{0,2}|2[0-4]\d|25[0-5])\.([0,1]?\d{0,2}|2[0-4]\d|25[0-5])\.([0,1]?\d{0,2}|2[0-4]\d|25[0-5])\.([0,1]?\d{0,2}|2[0-4]\d|25[0-5])$/;
	var arr = reg.test(str);
	if (!arr) {
		return false;
	} else {
		return true;
	}
}

function testinfo2(url) {
	// http://10.2.15.19:8080/grgb/action/sa/HelloWorld/detail
	layer.alert('dd');
	cordova.exec(function() {
	}, function(e) {
	}, "WindowPlugin", "openWindow", [ url ]);
}

function sendinfo(obj1, obj2) {
	layer.alert(obj1 + " " + obj2);
}

function send() {
	cordova.exec(function() {
	}, function(e) {
	}, "WindowPlugin", "closeWindow", [ 'sendinfo', '65', '8' ]);
}


function showCustomer(equipmentId,serialNumber){
	
	location.href = basePath+"/cp/equipment/getCustomer.do?equipmentId="+equipmentId +"&serialNumber="+ serialNumber +"&dataTime="+new Date().getTime();
	
	/*$("body").css("overflow","hidden");
	$("html").eq(0).css("overflow","hidden");
	$('body').bind("touchmove",function(e){    
        e.preventDefault();    
	});
	layer.open({
	    type: 2,
	    title: false,
	    shadeClose: false,
	    closeBtn: false,
	    shade: 0.2,
	    area: ['90%', '90%'],
	    content: basePath+"/cp/equipment/getCustomer.do?equipmentId="+equipmentId +"&dataTime="+new Date().getTime(),
	    end : function(){
	    	$("body").css("overflow","auto");
	    	$("html").eq(0).css("overflow","auto");
	    	$("body").unbind("touchmove");
	    }
	});*/
}

function toUpload(equipmentId,serialNumber){
	location.href = basePath+"/cp/equipment/upload.do?equipmentId="+equipmentId +"&serialNumber="+ serialNumber +"&dataTime="+new Date().getTime();
}