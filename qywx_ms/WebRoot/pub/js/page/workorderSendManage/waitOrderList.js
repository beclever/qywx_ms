/** @include "getPageCommon.js"; */

/**
 * 初始化数据
 */
$(function() {
	pull("down");
});

function isSelectList(type){
	var serialNumber = $('#serialNumber').val();// 设备序列号
	if(serialNumber){
		var reg=/^[0-9]+$/;
		var result= reg.test(serialNumber);
		if(!result){
			layer.alert("设备序列号只能输入数字!");
			serialNumber.val("");
		}else{
			pull(type,"but");
		};
	}else{
		pull(type,"but");
	}
}

// 业务菜单返回导航菜单
function backNavMenu() {
	window.android_backNav.exitActivity();
}
/**
 * 获取备件信息
 * 
 * @param type
 *            1 上一页 ,2 为下一页 ,3 为首页 ,4 从手机读取的页码
 */
function pull(type) {
	var pageNum = $("#pageNum").val();
	var number = pageNum;// 记录页码用于超时或无工单时回复
	// 上一页
	if(type=="down"){
		if(pageNum<=1) {// 判断：如果是在第一页上翻页返回，不查询
			$("#pageNum").val(1);
			return;
		}
		pageNum = 1;//Number(pageNum) -1;
		$("#thelist").empty(); // 清空数据
	}
	// 下一页
	if(type=="up"){
		pageNum = Number(pageNum) + 1;
	}
	// 首页，点击查询显示用
	if(type==3){
		pageNum = 1;
		$("#pageNum").val(1);
		$("#thelist").empty(); // 清空数据
	}
	var serialNumber = $('#serialNumber').val();// 设备序列号
	var branchName = $('#branchName').val();// 网点名称
	//var workFormStatus = $('#equipmentWorkOrderStatus').val();// 工单状态
	//var taskStatus = $('#equipmentProgressStatus').val();// 任务完成状态
	var engineerUserCode = $('#equipmentEngineerName').val();// 工程师
	if (serialNumber != "" && isNaN(serialNumber)) {
		return;
	}
	layer.load(2,{ shade: [0.6,'#666666'] });//加载中...
	$.ajax({
		type : "post",
		url : basePath + "/cp/sendManage/pageWaitOrderlist.do",
		data : {
			pageNum : pageNum,
			serialNumber : serialNumber,
			branchName : branchName,
			engineerUserCode : engineerUserCode
		},
		dataType : 'json',
		cache : false,
		success : function(res) {
			layer.closeAll('loading');
			var html = "";
			onClickArea(false);
			if(res.returnResult){
				if(res.list!=null&&res.list.length>0){
					$('#pageNum').val(pageNum/1+1);
					$(res.list).each(function(i,data){
						var customerName = encodeURIComponent(data.customerName);
						var branchName = encodeURIComponent(data.branchName);
						var installAddress = encodeURIComponent(data.installAddress);
						var taskType = encodeURIComponent(data.taskType);
						var taskLevel = encodeURIComponent(data.taskLevel);
						var engineerName = encodeURIComponent(data.engineerName);
						var taskContent = encodeURIComponent(data.taskContent);
						var reportTime = encodeURIComponent(data.reportTime);
						var appointmentTime = encodeURIComponent(data.appointmentTime);
						var taskSource = encodeURIComponent(data.taskSource);
						var repairsManName = encodeURIComponent(data.repairsManName);
						var receiveManName = encodeURIComponent(data.receiveManName);
						
						var url = basePath + "/cp/sendManage/waitOrderDetail.do?customerName=" + customerName +
						"&branchName=" + branchName +
						"&installAddress=" + installAddress +
						"&equipmentId=" + data.equipmentId +
						"&equipmentDeptId=" + data.equipmentDeptId +
						"&serialNumber=" + data.serialNumber +
						"&taskId=" + data.taskId +
						"&taskType=" + taskType +
						"&taskLevel=" + taskLevel +
						"&engineerId=" + data.engineerId +
						"&engineerName=" + engineerName +
						"&taskContent=" + taskContent +
						"&reportTime=" + reportTime +
						"&appointmentTime=" + appointmentTime +
						"&taskSource=" + taskSource +
						"&repairsContactId=" + data.repairsContactId +
						"&repairsManName=" + repairsManName +
						"&repairsMoblie=" + data.repairsMoblie +
						"&repairsTelephone=" + data.repairsTelephone +
						"&receiveContactId=" + data.receiveContactId +
						"&receiveManName=" + receiveManName +
						"&receiveMoblie=" + data.receiveMoblie +
						"&receiveTelephone=" + data.receiveTelephone;
						html += 
						"<div class=\"gdlb_list\">"+
						"<h3 class=\"font_black\">"+getNoNullValue(data.branchName)+"</h3>"+
						"<span class=\"gdlb_info\">设备序列号："+data.serialNumber+"<br/>任务类型："+data.taskType + "<br />"+
							"客户名称："+getNoNullValue(data.customerName)+
							"<br /> 报修时间："+getNoNullValue(data.reportTime)+"<br /> 预约上门时间："+getNoNullValue(data.appointmentTime)+
							"<br />"+
						"</span> <a href =\""+url+"\"><span class=\"query_arrow\"></span></a>"+
					"</div>";
					});
					if(res.pageCount <= pageNum){
						$("#pullUp").hide();
						if(res.pageCount == pageNum){
							$('#thelist').append(html);
						}
						if(res.pageCount < pageNum){
							layer.msg('暂无更多工单！');
						}
						
					}else{
						$("#pullUp").show();
						$('#thelist').append(html);
					}
				}else{
					layer.msg('暂无工单！');
				}
			}else{
				layer.alert(res.errorMessage,{ closeBtn: false });
			}
			
			$("#pageNum").val(pageNum);
		
		},
		error : function(data) {
			layer.closeAll('loading');
			$("#pageNum").val(number);// 恢复页码
			layer.msg("暂无更多工单");
		}
	});
}

// 当数据为空是，不显示
function getNoNullValue(str) {
	var result = "";
	if (str != null) {
		result = str;
	}
	return result;
}

// 根据执行时间判断到哪里，如接受时间有接受时间，即 状态为"接受"
function getStutas(datas, acceptTime, leaveTime) {
	var data = new Object();
	data = datas;
	var result = "未接受";
	// alert("workFormStatus="+data.workFormStatus);
	if (data.workFormStatus == "处理中") { // 处理中
		if (data.finishWorkTime != "") {
			result = "已完成";
		} else if (data.startWorkTime != "") {
			result = "已开始";
		} else if (data.arriveTime != "") {
			result = "已到达";
		} else if (leaveTime != "") {
			result = "已出发";
		} else if (acceptTime != "") {
			result = "已接受";
		}
	} else if (data.workFormStatus == "已退回") { // 已退回
		result = "已退回";
	}
	return result;
}

// 控制隐藏的查询条件显示
function onClickArea(flg) {
	if(flg) { // 显示
		document.getElementById("showAtFirst").style.display="none";
		document.getElementById("hideAtFirst").style.display="inline";
		document.getElementById("wrapper").style.top="10.5em";
	} else { // 隐藏
		document.getElementById("showAtFirst").style.display="block";
		document.getElementById("hideAtFirst").style.display="none";
		document.getElementById("wrapper").style.top="7em";
	}
	
}




/*$(function() {
	//pull("down");//初始化加载数据
});
//上下拉事件
function pull(type) {
	if (type == "up") {
		//layer.msg('上拉');
		var pageNum = $('#pageNum').val();
		getPageData(pageNum);
	} else if (type == "down") {
		var pageNum = 1;//页码置于1
		//layer.msg('下拉');
		$('#thelist').empty();
		getPageData(pageNum);
	}
}

//是否加载中
var isLoding;

//格式化数据：当参数为null，undefined时，返回空字符串
var formatData = function(p){
	return !p? "":p;
}
//获取分页数据
function getPageData(pageNum){
	layer.load(2,{ shade: [0.6,'#666666']});//加载中...
	isLoding = true;
	$.ajax( {  
        type : "post",  
        url : basePath+"/cp/sendManage/pageWaitOrderlist.do",
		data:{pageNum:pageNum},
		dataType: 'json',
		cache:false,
		success : function(dataJson) {
			layer.closeAll('loading');//移除加载
			isLoding = false;
			loadingmore_stoploading();
			if(dataJson!=null&&dataJson.length>0){
				$('#pageNum').val(pageNum/1+1);
				$(dataJson).each(function(i,data){
					var html = 
					"<div class=\"gdlb_list\">"+
					"<h3 class=\"font_black\">"+formatData(data.branchName)+"</h3>"+
					"<span class=\"gdlb_info\">"+data.taskType+"&nbsp;&nbsp;&nbsp;&nbsp;" + data.taskId + "<br />"+
						"网点名称："+formatData(data.branchName)+
						"<br /> 报修时间："+formatData(data.reportTime)+"<br /> 预约上门时间："+data.appointmentDate+""+
						"<br />"+
					"</span> <a href =\""+basePath+"/cp/workOrder/detail.do?poNumber="+data.poNumber+"&workformId="+data.workformId+"&equipmentId="+formatData(data.equipmentId)+"\"><span class=\"query_arrow\"></span></a>"+
				"</div>";
					$('#thelist').append(html);
				});
			}else{
				layer.msg('暂时没有更多工单！');
			}
    	},
		error:function(result){
			isLoding = false;
			loadingmore_stoploading();
			layer.closeAll('loading');//移除加载
			layer.msg('系统繁忙，请重试！');
		}
    });
}

//根据执行时间判断到哪里，如接受时间有接受时间，即 状态为"接受"
function getStutas(datas, acceptTime, leaveTime) {
	var data = new Object();
	data = datas;
	var result = "未接受";
	// alert("workFormStatus="+data.workFormStatus);
	if (data.workFormStatus == "处理中") { // 处理中
		if (data.finishWorkTime != "") {
			result = "已完成";
		} else if (data.startWorkTime != "") {
			result = "已开始";
		} else if (data.arriveTime != "") {
			result = "已到达";
		} else if (leaveTime != "") {
			result = "已出发";
		} else if (acceptTime != "") {
			result = "已接受";
		}
	} else if (data.workFormStatus == "已退回") { // 已退回
		result = "已退回";
	}
	return result;
}

var isuseAutoLoad = true;
// 自动加载更多延时
var autoloadtime = 300;
// 加载更多监听
var timerloadingmore = -1;

// 加载更多值没有更多了
function loadingmore_no() {
	$("#loadingmore").hide();
}

// 显示加载更多
function loadingmore_show() {
	$("#loadingmore").show();
}

// 更多加载中
function loadingmore_loading() {
	if (isLoding) {
		return;
	}
	clearTimeout(timerloadingmore);
	$("#loadingmore").find("span").text("更多加载中……");
	// 异步加载数据方法
	var pageNum = $('#pageNum').val();
	getPageData(pageNum);
}

// 点击加载更多
function loadingmore_stoploading() {
	$("#loadingmore").find("span").text("点击加载更多");
}

$(document).ready(function () {
    $(window).scroll(function () {
        // $(window).scrollTop()这个方法是当前滚动条滚动的距离
        // $(window).height()获取当前窗体的高度
        // $(document).height()获取当前文档的高度
        if (10+($(window).scrollTop()) >= ($(document).height() - $(window).height()) 
        		&& !($(document).height() <= $(window).height())) {
        	// 当底部基本距离+滚动的高度〉=文档的高度-窗体的高度时；
        	// 并且当前窗体高度大于或等于文本高度，不进行异步请求
            // 我们需要去异步加载数据了
        	if (!isLoding) {
        		clearTimeout(timerloadingmore);
        		timerloadingmore = setTimeout(function() {
        			if (!isLoding) {
        				loadingmore_loading();
        			}
        		}, autoloadtime);
        	}
        }
    });
});

// 初始化
$("#loadingmore").bind("click", loadingmore_loading);
*/
