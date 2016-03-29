$(function() {
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
        url : basePath+"/cp/workOrder/pageList.do",
		data:{pageNum:pageNum},
		dataType: 'json',
		cache:false,
		success : function(dataJson) {
			layer.closeAll('loading');//移除加载
			isLoding = false;
			loadingmore_stoploading();
			if(dataJson.errcode==1){
				if(dataJson.pageData!=null&&dataJson.pageData.length>0){
					$('#pageNum').val(pageNum/1+1);
					$(dataJson.pageData).each(function(i,data){
						var html = 
						"<div class=\"gdlb_list\">"+
						"<h3 class=\"font_black\">"+formatData(data.customerName)+"</h3>"+
						"<span class=\"gdlb_info\"> 工 单 号："+data.poNumber+"<br />"+
							"网点名称："+formatData(data.branchName)+"<br /> 任务等级："+formatData(data.taskLevel)+"<br /> 预约时间："+data.appointmentDate+""+
							"<br /> 状态："+ getStutas(data,data.acceptTime,data.leaveTime)+""+
							"<br />"+
						"</span> <a href =\""+basePath+"/cp/workOrder/detail.do?poNumber="+data.poNumber+"&workformId="+data.workformId+"&equipmentId="+formatData(data.equipmentId)+"\"><span class=\"query_arrow\"></span></a>"+
					"</div>";
						$('#thelist').append(html);
					});
				}else{
					layer.msg('暂时没有更多工单！');
				}
			}
			else{
				layer.msg(dataJson.errmsg);
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
