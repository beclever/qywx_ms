wx.ready(function() {
	wx.hideOptionMenu();//隐藏微信菜单
});
// 网络环境
function getNetwork() {
	wx.getNetworkType({
		success : function(res) {
			var networkType = res.networkType; // 返回网络类型2g，3g，4g，wifi
			alert(networkType);
		}
	});
}

function goBack(){
	history.go(-1);
	return false;
}

//更新工单绑定的设备序列号
function search(){
	/*layer.confirm('更改后会导致该设备任务一起消失,是否更改？', {
		title: '温馨提示',
		closeBtn: false,
	    btn: ['确定','取消'], //按钮
	    shade: [0.6,'#666666'] //显示遮罩
	}, function(index){
		layer.close(index);
		updateSerialNumber();
	});*/
	updateSerialNumber();
}
function updateSerialNumber() {
	layer.load(2,{
	    shade: [0.6,'#666666'] //0.1透明度的白色背景
	});//加载中...
	$.ajax({
		type : "post",
		url : basePath + "/cp/workOrder/queryserialNumber.do",
		data : {
			serialNumber : $("#serialNumber").val(),
			workformId : $("#workformId").val()
		},
		dataType : 'json',
		cache : false,
		success : function(dataJson) {
			layer.closeAll('loading');//移除加载
			if (dataJson.errcode == 1) {
				$("#branchName").text(dataJson.data.branchName);
				$("#installAddress").text(dataJson.data.installAddress);
				$("#equipmentModel").val(dataJson.data.equipmentModel);
				$("#equipmentId").val(dataJson.data.equipmentId);
				$("#atmManager").val(dataJson.data.atmManager);
				$("#atmManagerTel").val(dataJson.data.atmManagerTel);
				layer.msg('更新成功！', {icon: 1});
			} else {
				layer.alert(dataJson.errmsg, {title: false, closeBtn: false });
			}
		},
		error : function(data) {
			layer.closeAll('loading');//移除加载
			layer.msg('系统繁忙，请重试');
		}
	});
}
//百度地图
/*function goBaiduMap() {

	var addrName = $("#installAddress").text();// 目标点地理名称
	var latitude = $("#latitude").val();
	// 目标点的纬度
	var longitude = $("#longitude").val();
	// 目标点的经度
	if (null != addrName && "" != (addrName)) {
		window.location.href = basePath + "/map/baiduMap.do?addrName="
				+ addrName;
	} else if (null != latitude && null != longitude) {
		window.location.href = basePath + "/map/baiduMap.do?longitude="
				+ longitude + "&latitude=" + latitude;
	} else {
		layer.msg("您的位置出错了...");
	}
}*/
//腾讯自带的api地图
function goBaiduMap() {
	if(!is_weixin())
	{
		alert("请使用微信浏览器！");
		return;
	}
	var u = navigator.userAgent, app = navigator.appVersion;
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	if(isiOS){
		layer.msg('IOS暂不支持此功能，给您造成的不便请谅解!');
		return;
	}
	var addrName = $("#installAddress").text();// 目标点地理名称
	var branchName = $("#branchName").text();// 目标点地理名称
	var latitude = $("#latitude").val();
	// 目标点的纬度
	var longitude = $("#longitude").val();
	wx.openLocation({
	    latitude: latitude, // 纬度，浮点数，范围为90 ~ -90
	    longitude: longitude, // 经度，浮点数，范围为180 ~ -180。
	    name: addrName, // 位置名
	    address: branchName, // 地址详情说明
	    scale: 14, // 地图缩放级别,整形值,范围从1~28。默认为最大
	    infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
	});
	
}

var alertTime1 = null;
$(document).ready(function() {
	wx.hideOptionMenu();
	$("#s_btn").click(function() {
		search();
	});
	$("#map_btn").click(function() {
		goBaiduMap();
	});
	

	$("#serialNumber").attr("readonly","readonly");
	
	//初始化样式
	loadCss();
	
	//绑定接待人事件
	/*$("#receiver").bind("click", function() {
		receiver();
	});*/
	
	//绑定录单事件
	$("#ludan_able").bind("click", function() {
		stopRecord();
	});//绑定录单事件
	$("#ludan_disable").bind("click", function() {
		if($("#isCanUpdateSerialNumber").val() == 1){
			layer.confirm('开始录单后序列号将不能再修改，您确定序列号正确吗？', {title: '温馨提示', closeBtn: false }, function(index) {
				layer.close(index);
				startRecord();
			});
		}else{
			startRecord();
		}
	});
});

//加载样式
function loadCss() {
	var acceptTime = $("#acceptTime").val();
	var leaveTime = $("#leaveTime").val();
	var arriveTime = $("#arriveTime").val();
	var startWorkTime = $("#startWorkTime").val();
	var finishWorkTime = $("#finishWorkTime").val();
	if (acceptTime != "") {
		$("#accept").hide();
		$("#chufa").removeClass();
		if (leaveTime != "") {
			$("#chufa").addClass("chufa");
			$("#leaveText").text(leaveTime);
		} else {
			$("#chufa").addClass("kaishi");
			$("#leaveText").text("未开始");
		}
	}else{
		$("#accept").css("background-color","#aaa").css("border-color","#aaa");
		$("#accept").text("未接受");
	}
	if (leaveTime != "") {
		$("#daoda").removeClass();
		if (arriveTime != "") {
			$("#daoda").addClass("chufa");
			$("#arriveText").text(arriveTime);
		} else {
			$("#daoda").addClass("kaishi");
			$("#arriveText").text("未到达");
		}
	}
	if (arriveTime != "") {
		$("#kaishi").removeClass();
		if (startWorkTime != "") {
			$("#kaishi").addClass("chufa");
			$("#startText").text(startWorkTime);
		} else {
			$("#kaishi").addClass("kaishi");
			$("#startText").text("未开始");
		}
	}
	if (startWorkTime != "") {
		$("#ludan_disable").show();
		$("#ludan_able").hide();
		$("#wancheng").removeClass();
		if (finishWorkTime != "") {
			$("#wancheng").addClass("chufa");
			$("#finishText").text(finishWorkTime);
		} else {
			$("#wancheng").addClass("kaishi");
			$("#finishText").text("未完成");
		}
	} else {
		$("#ludan_disable").hide();
		$("#ludan_able").show();
	}
}

//判断是否微信浏览器
function is_weixin(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger") {
        return true;
    } else {
        return false;
    }
}

//微信获取经纬度并上传服务器
function uploadTime(timeType) {
	if(!is_weixin())
	{
		alert("请使用微信浏览器！");
		return;
	}
	layer.load(2,{
	    shade: [0.6,'#666666'] //0.1透明度的白色背景
	});//加载中...
	wx.getLocation({
		success : function(res) {
			latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
			longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
			var latlonStr = latitude + "," + longitude;
			uploadToServer(latlonStr, timeType);//获取位置上传服务端
		},
		cancel : function(res) {
			var latlonStr = "";
			uploadToServer(latlonStr, timeType);//获取位置上传服务端
		},
		fail: function(){
			layer.closeAll('loading');//关闭加载
			layer.alert("获取地理位置失败，系统繁忙！(请先确认您的GPS功能已打开)",{ closeBtn: false, title: "温馨提示"});
		}
	});
}

function uploadToServer(latlonStr, timeType) {
	var installAddress = $("#installAddress").text();
	var workformId = $("#workformId").val();
	var equipmentId = $("#equipmentId").val();
	var equipmentLatitude = $("#latitude").val();
	var equipmentLongitude = $("#longitude").val();
	$.ajax({
		type : "post",
		url : basePath + "/cp/workOrder/uploadTimeNew.do",
		data : {
			timeType : timeType,//1出发、2到达、3开始、4结束、5接受
			latlonStr : latlonStr,
			startWorkTime : $("#startWorkTime").val(),
			installAddress : installAddress,
			workformId:workformId,
			equipmentId: equipmentId,
			equipmentLatitude: equipmentLatitude,
			equipmentLongitude: equipmentLongitude
		},
		dataType : 'json',
		cache : false,
		success : function(dataJson) {
			layer.closeAll('loading');//关闭加载
			if (dataJson.errcode == 1) {
				if(timeType=="1"){
					//出发
					$("#leaveTime").val(dataJson.data.time);
					layer.msg("已出发");
				}else if(timeType=="2"){
					//到达
					$("#arriveTime").val(dataJson.data.time);
					layer.msg("已到达");
				}else if(timeType=="3"){
					//开始
					$("#startWorkTime").val(dataJson.data.time);
					layer.msg("已开始");
				}else if(timeType=="4"){
					//完成
					$("#finishWorkTime").val(dataJson.data.time);
					layer.msg("已完成");
				}else if(timeType=="5"){
					//接受
					$("#acceptTime").val(dataJson.data.time);
					layer.msg("已接受");
				}
				loadCss();
			} else {
				layer.msg(dataJson.errmsg);
			}
			//操作预警信息
			if(dataJson.data.warn){
				layer.alert(dataJson.data.warn, {closeBtn: false});
			}
		},
		error : function(data) {
			layer.closeAll('loading');
			layer.msg('系统繁忙，请重试！');
		}
	});
}

function preventDefault(e) { e.preventDefault(); };

function receiver(){
	if (alertTime1 == null){
		alertTime1 = new Date().getTime();
    }else{       
        var alertTime2 = new Date().getTime();
        if(alertTime2 - alertTime1 < 1000){
        	alertTime1 = alertTime2;
            return;
        }else{
        	alertTime1 = alertTime2;
        }
    }
	
	var equipmentId = $("#equipmentId").val();
	var url = basePath+"/cp/receiver/list.do?equipmentId="+equipmentId;
	layer.open({
	    type: 2,
	    title: false,
	    shadeClose: false,
	    closeBtn: false,
	    shade: 0.2,
	    area: ['90%', '90%'],
	    content: url, //iframe的url
	    success: function(layero, index){
	    	//禁用浏览器滚动条
//	    	$("body").eq(0).css("overflow","hidden");
	    	//禁用手机浏览器滚动条
	    	//parent.document.addEventListener('touchmove', preventDefault, false); 
	    	
	    	//new iScroll("pageContent", {vScrollbar: false,hideScrollbar:false, useTransform: false});
	    },
	    end: function(){
	    	//启用pc浏览器滚动条
//	    	$("body").eq(0).css("overflow","auto");
	    	//启用手机浏览器滚动条
	    	//parent.document.removeEventListener('touchmove', preventDefault, false);
	    }
	}); 
}