/** @include "getPageCommon.js"; */

/**
 * 初始化数据
 */
$(function() {
	pull(3);
	pressSerialNumber();
	pressBranchName();
	pressInstallAddress();
	pressATMNumber();
});

function isSelectList(type){
	/*var serialNumber = $('#serialNumber').val();// 设备序列号
	if(serialNumber){
		var reg=/^[0-9]+$/;
		var result= reg.test(serialNumber);
		if(!result)
		{layer.alert("设备序列号只能输入数字!");
			serialNumber.val("");
		}else{*/
			pull(type,"but");
	/*	};
	}else{
		selectList(type,"but");
	}*/
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
var clicked = false;
function pull(type,str) {
	if(clicked){
		layer.msg("正在请求数据，请耐心等待");
		return 
	}
	var pageNum = $("#pageNum").val();
	var number = pageNum;// 记录页码用于超时或无工单时回复
	// 上一页
	if(type=="down"){
		if(pageNum<=1) {// 判断：如果是在第一页上翻页返回，不查询
			$("#pageNum").val(1);
			clicked = false;
			return
		}
		//pageNum = Number(pageNum) -1;
		pageNum = 1;
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
	var poNumber = $('#poNumber').val();// 设备序列号
	var branchName = $('#branchName').val();// 网点名称
	var workFormStatus = $('#equipmentWorkOrderStatus').val();// 工单状态
	var taskStatus = $('#equipmentProgressStatus').val();// 任务状态
	var engineerId = $('#equipmentEngineerName').val();// 工程师
	/*if (serialNumber != "" && isNaN(serialNumber)) {
		clicked = false;
		return
	}*/
	var index = layer.load(2,{shade:[0.6,'#666']});//0.6透明度的灰色背景
	$.ajax({
		type : "post",
		url : basePath + "/cp/sendManage/orderListByParam.do",
		data : {
			pageNum : pageNum,
			serialNumber : serialNumber,
			poNumber : poNumber,
			branchName : branchName,
			workFormStatus : workFormStatus,
			taskStatus : taskStatus,
			engineerId : engineerId
		},
		dataType : 'json',
		cache : false,
		beforeSend : function(x){clicked = true;},
		complete :function(){clicked = false;},
		success : function(dataResult) {
			layer.close(index);
			if(dataResult.returnResult){
				onClickArea(false);
				var html = "";
				$(dataResult.list).each(function(i, data) {
					if (null != data) {
						var url = basePath + "/cp/sendManage/sentOrderDetail.do?poNumber="+data.po_number+"&equipmentId=" + data.equipment_id
								+ "&workformId=" + data.workformId;
						if(data.warningCtrl){
							url += "&warningCtrl="+encodeURIComponent(encodeURIComponent(data.warningCtrl));
						}
						var displayWarn = "";
						if(data.warningCtrl){
							displayWarn = "操作预警";
						}
						
						html += "<div class='new_title02'>" + 
									"<div class='new_title02_arrow'>&nbsp;</div>" + 
									"<a href=" + url + ">" + 
										"<samp class='font_colour'>" + getNoNullValue(data.customer_name) + "</samp>" + 
											"<span>网点名称：" + getNoNullValue(data.eqtBranchName) + "<br/>" +
											"工单号：" + getNoNullValue(data.po_number) + "<br/>" + 
											"工单状态：" + getNoNullValue(data.status) + "<br/>" + 
											"设备序列号：" + getNoNullValue(data.serial_number) + "<br/>" +
											"工程师：" + getNoNullValue(data.engineer_name) + "<br/>" +
											"工单创建时间：" + getNoNullValue(data.create_date)+ "<br/>" +
											"预约时间：" + getNoNullValue(data.appointment_date)+ "<br/>" +
											"<span style='color:red;'>"+getNoNullValue(displayWarn)+"</span>"+
											"</span>" + 
										"</a>" + 
									"<div class='clear'>" +"</div>" + 
								"</div>";
					}
				});
				// 修改页数
				if(dataResult.list==null||dataResult.list.length==0){
					if(str=="but"){
						layer.msg("暂无数据");
					}else{
						layer.msg("暂无更多数据");
					}
				}
				$("#pageNum").val(pageNum);

				if(dataResult.pageCount <= pageNum){
					$("#pullUp").hide();
					if(dataResult.pageCount == pageNum){
						$('#thelist').append(html);
					}
					if(dataResult.pageCount < pageNum){
						layer.msg('暂无更多工单！');
					}
					
				}else{
					$("#pullUp").show();
					$('#thelist').append(html);
				}
			}else{
				layer.alert(dataResult.errorMessage);
			}
			},
			error : function(data) {
				layer.close(index);
				$("#pageNum").val(number);// 恢复页码
				layer.msg("暂无更多数据");
			}
	});
}

// 当数据为空是，不显示
function getNoNullValue(str) {
	var result = "";
	if (str) {
		result = str;
	}
	return result;
}

// 控制隐藏的查询条件显示
function onClickArea(flg) {
	if(flg) { // 显示
		document.getElementById("showAtFirst").style.display="none";
		document.getElementById("hideAtFirst").style.display="inline";
		document.getElementById("wrapper").style.top="21em";
		
	} else { // 隐藏
		document.getElementById("showAtFirst").style.display="block";
		document.getElementById("hideAtFirst").style.display="none";
		document.getElementById("wrapper").style.top="11em";
	}
	
}

//设备序列号 填写时将字体改为 黑色
function pressSerialNumber(){
    var serialNumber = $("#serialNumber").val();//设备序列号
    if(!serialNumber){
    	$("#spanSerialNumber").hide();
    }else{
    	$("#spanSerialNumber").show();
    }
}
	
//客户名称 填写时将字体改为 黑色
function pressCustomerName(){
	var customerName = $("#equipmentCustomerName").val();//客户名称
	
	if(!customerName){
		$("#spanCustomerName").hide();
	}else{
		$("#spanCustomerName").show();
	}
}

//网点名称 填写时将字体改为 黑色
function pressBranchName(){
    var branchName = $("#branchName").val();//网点名称

    if(!branchName){
    	$("#spanBranchName").hide();
    }else{
    	$("#spanBranchName").show();
    }
}

//安装地址 填写时将字体改为 黑色
function pressInstallAddress(){
    var installAddress = $("#equipmentInstallAddress").val();//安装地址

    if(!installAddress){
    	$("#spanInstallAddress").hide();
    }else{
    	$("#spanInstallAddress").show();
    }
}

//ATM号码 填写时将字体改为 黑色
function pressATMNumber(){
    var ATMNumber = $("#equipmentATMNumber").val();//ATM号

    if(!ATMNumber){
    	$("#spanATMNumber").hide();
    }else{
    	$("#spanATMNumber").show();
    }
}

//清除 设备序列号 条件
function cleanSerialNumber(){
	$("#equipmentSerialNumber").val("");
	$("#spanSerialNumber").hide();
}	

//清除 客户名称 条件
function cleanCustomerName(){
	$("#equipmentCustomerName").val("");
	$("#spanCustomerName").hide();
}

//清除 网点名称 条件
function cleanBranchName(){
	$("#equipmentBranchName").val("");
	$("#spanBranchName").hide();
}	

//清除 安装地址 条件
function cleanInstallAddress(){
	$("#equipmentInstallAddress").val("");
	$("#spanInstallAddress").hide();
}

//清除 ATM号 条件
function cleanATMNumber(){
	$("#equipmentATMNumber").val("");
	$("#spanATMNumber").hide();
}
