var alertTime1 = null;
window.onpopstate=function (){	
	/*var poNumber = $("#poNumber").val();
	var url= basePath+"/cp/workOrder/default.do?returnPageNum=1&random="+Math.random()+"&poNumber="+poNumber;
	window.history.replaceState("","",url);	*/
}
function handleproblembtn(index){
	var obj = $('#handleproblembtn'+index);
	var solve = '已解决';
	var deal = '处理';
	if(obj.attr('value')=="处理"){
		obj.attr('value',solve);
		$('#cancleproblembtn'+index).hide();
		$('#dealContent_span'+index).show();
		$('#dealContent'+index).removeAttr('readonly');
	}else{
		$('#status_label'+index).text(solve);
		$('#status'+index).attr('value',solve);
		obj.attr('value',deal).hide();
		$('#cancleproblembtn'+index).hide();
		$('#dealContent_span'+index).hide();
		$('#dealContent'+index).attr('readonly','readonly');
	}
}
function calcleproblembtn(index){
	document.getElementById("handleproblembtn"+index).value="处理";
	document.getElementById("cancleproblembtn"+index).style.display="none";
	document.getElementById("dealContent_span"+index).style.display="none";
}
$(function () {
	loadCity($("#YJKTprovinces"));
	loadCity($("#AZKTprovinces"));
	loadCity($("#AZFZprovinces"));
	loadCity($("#YJFZprovinces"));


    if ($("#XJtypes option:selected").val() == '客户要求巡检') {
        $("#xjdivcontent").show();
        $("#xjdivcontentemark").show();
    }
    loadDiv();
    
    // 初始化纸质工单
    initPaper();

    // 初始化左侧栏滑动
    pageActionOnload();

    // 初始化总耗时
    initTaskTotalTime();
    
    // 初始化二维码
    initQrCode();

    // ---------------------------点击事件--------------------

    // (其他)
    $("#QT").click(function () {

        ShowDivContent($(this));
        $("#QTDiv").show();
    });
    
    // (升级)
    /*
	 * $("#SJ").click(function () {
	 * 
	 * ShowDivContent($(this)); $("#SJDiv").show(); });
	 */

    // 移动开通
    $("#YJKT").click(function () {
        ShowDivContent($(this));
        $("#YJKTDiv").show();
    });

    // 客户培训

    $("#PX").click(function () {
        ShowDivContent($(this));
        $("#PXDiv").show();
    });


    // 账户处理
    $("#ZW").click(function () {
        ShowDivContent($(this));
        $("#ZWDiv").show();
    });


    // 移机辅助
    $("#YJFZ").click(function () {
        ShowDivContent($(this));
        $("#YJFZDiv").show();
    });

// 巡检
    $("#XJ").click(function () {
        ShowDivContent($(this));
        $("#XJDiv").show();
    });


    // 安装辅助
    $("#AZFZ").click(function () {
        ShowDivContent($(this));
        $("#AZFZDiv").show();
    });

    // 安装开通
    $("#AZKT").click(function () {
        ShowDivContent($(this));
        $("#AZKTDiv").show();
    });
    // 维修
    $("#WX").click(function () {
        ShowDivContent($(this));
        $("#WXDiv").show();
    });

    // 保养
    $("#BY").click(function () {
        ShowDivContent($(this));
        $("#BYDiv").show();
    });

    // 费用

    $("#FYINFO").click(function () {
        ShowDivContent($(this));
        $("#FYINFODiv").show();
    });


    // 遗留问题
    $("#YLWTINFO").click(function () {
        ShowDivContent($(this));
        $("#YLWTINFODiv").show();
    });
    
    // 纸质工单
    $("#ZZGDINFO").click(function () {
        ShowDivContent($(this));
        $("#ZZGDINFODiv").show();
    });
    
    // 二维码
    $("#EWMINFO").click(function () {
        ShowDivContent($(this));
        var workformId = $("#workformId").val();  // 工单ID
        $("#EWMINFODiv").show();
    });
    // 附件
    $("#IMGINFO").click(function () {
        ShowDivContent($(this));
        var workformId = $("#workformId").val();  // 工单ID
        $("#IMGINFODiv").show();
    });
    
    



    // -----------------------------------------------------模块配件----------------------------------------------

    // 其他（模块配件显示）
    $("#otherspare").click(function () {

        $("#other1").show();
        $("#other2").hide();
        $("#task_replace_button_other span").removeClass("task_replace_button_selected");
        $("#task_replace_button_other span").addClass("task_replace_button_unselected");
        $("#otherspare").addClass("task_replace_button_selected");
    })
    $("#otherelement").click(function () {

        $("#other1").hide();
        $("#other2").show();
        $("#task_replace_button_other span").removeClass("task_replace_button_selected");
        $("#task_replace_button_other span").addClass("task_replace_button_unselected");
        $("#otherelement").addClass("task_replace_button_selected");
    });


    // 客户培训（模块配件件显示）
    $("#clientspare").click(function () {

        $("#client1").show();
        $("#client2").hide();
        $("#task_replace_button_client span").removeClass("task_replace_button_selected");
        $("#task_replace_button_client span").addClass("task_replace_button_unselected");
        $("#clientspare").addClass("task_replace_button_selected");
    });

    $("#clientelement").click(function () {
        $("#client1").hide();
        $("#client2").show();
        $("#task_replace_button_client span").removeClass("task_replace_button_selected");
        $("#task_replace_button_client span").addClass("task_replace_button_unselected");
        $("#clientelement").addClass("task_replace_button_selected");

    });


    // 账务处理（模块配件件显示）
    $("#accountspare").click(function () {

        $("#account1").show();
        $("#account2").hide();
        $("#task_replace_button_account span").removeClass("task_replace_button_selected");
        $("#task_replace_button_account span").addClass("task_replace_button_unselected");
        $("#accountspare").addClass("task_replace_button_selected");
    });
    $("#accountelement").click(function () {

        $("#account1").hide();
        $("#account2").show();
        $("#task_replace_button_account span").removeClass("task_replace_button_selected");
        $("#task_replace_button_account span").addClass("task_replace_button_unselected");
        $("#accountelement").addClass("task_replace_button_selected");
    });
    //安装辅助移除
    $('#azfzTaskMove').click(function(){
        removeTaskFunc($("#AZFZtaskId").val());
    });
    //安装开通移除
    $('#azktTaskMove').click(function(){
        removeTaskFunc($("#AZKTtaskId").val());
    });
    //保养移除
    $('#byTaskMove').click(function(){
        removeTaskFunc($("#BYtaskId").val());
    });
    //财务处理移除
    $('#cwclTaskMove').click(function(){
        removeTaskFunc($("#CWCLtaskId").val());
    });
    //客户培训移除
    $('#khpxTaskMove').click(function(){
        removeTaskFunc($("#KHPXtaskId").val());
    });
    //其他移除
    $('#qtTaskMove').click(function(){
        removeTaskFunc($("#QTtaskId").val());
    });
    //升级移除
    /*$('#sjTaskMove').click(function(){
        removeTaskFunc($("#SJtaskId").val());
    });*/
    //维修移除
    $('#wxTaskMove').click(function(){
        removeTaskFunc($("#WXtaskId").val());
    });
    //移机辅助移除
    $('#yjfzTaskMove').click(function(){
        removeTaskFunc($("#YJFZtaskId").val());
    });
    //移机开通移除
    $('#yjktTaskMove').click(function(){
        removeTaskFunc($("#YJKTtaskId").val());
    });

    // 维修（模块配件件显示）
    $("#faultlocationpath").click(function () {
        $("#maintain1").show();
        $("#maintain2").hide();
        $("#maintain3").hide();
        $("#task_replace_button span").removeClass("task_replace_button_selected");
        $("#task_replace_button span").addClass("task_replace_button_unselected");
        $("#faultlocationpath").addClass("task_replace_button_selected");
    });

    $("#maintainspare").click(function () {

        $("#maintain1").hide();
        $("#maintain2").show();
        $("#maintain3").hide();
        $("#task_replace_button span").removeClass("task_replace_button_selected");
        $("#task_replace_button span").addClass("task_replace_button_unselected");
        $("#maintainspare").addClass("task_replace_button_selected");
    });
    $("#maintainelement").click(function () {
        $("#maintain1").hide();
        $("#maintain2").hide();
        $("#maintain3").show();
        $("#task_replace_button span").removeClass("task_replace_button_selected");
        $("#task_replace_button span").addClass("task_replace_button_unselected");
        $("#maintainelement").addClass("task_replace_button_selected");
    });

    // 保养（模块配件件显示）
    $("#goodmaintainspare").click(function () {
        $("#goodmaintain1").show();
        $("#goodmaintain2").hide();
        $("#task_replace_button_goodmaintain span").removeClass("task_replace_button_selected");
        $("#task_replace_button_goodmaintain span").addClass("task_replace_button_unselected");
        $("#goodmaintainspare").addClass("task_replace_button_selected");

    });
    $("#goodmaintainelement").click(function () {
        $("#goodmaintain1").hide();
        $("#goodmaintain2").show();
        $("#task_replace_button_goodmaintain span").removeClass("task_replace_button_selected");
        $("#task_replace_button_goodmaintain span").addClass("task_replace_button_unselected");
        $("#goodmaintainelement").addClass("task_replace_button_selected");
    });


    // 升级（模块配件件显示---动态显示，无法使用）
    $("#upgradespare").click(function () {

        $("#upgrade1").show();
        $("#upgrade2").hide();
    });

    $("#upgradeelement").click(function () {

        $("#upgrade1").hide();
        $("#upgrade2").show();
    });


    // 费用
    $("#chargeadd").click(function () {
        var feeId = $("#freeproject").val()
        var feeName = $("#freeproject option:selected").text();
        var freeamount = $("#freeamount").val();
        var result = "";
        if (feeId.trim() == "") result += "项目不能空";
        if (freeamount.trim() == ""){
        	result += (result.length > 0 ? "\n" : "") + "金额不能空";
        }else if(!(/^([1-9][\d]{0,7}|)([0-9]\.[\d]{1,2})?$/.test(freeamount.trim()))){
        	result += (result.length > 0 ? "\n" : "") + "金额格式不正确，请输入数字！";
        }
        if (result.length > 0) {
            layer.alert(result, {title: false, closeBtn: false });
            return;
        }
        var togetherPersonId = $("#freeperson").val();
        var togetherPersonName = $("#freeperson option:selected").text();
        var freeremark = $("#freeremark").val();
        var html = "<div class='work_task_inputcontent'>" +
            "<div class='work_task_inputBox_left lleft'>" +
            "<li><input type='hidden' value=" + feeId + " name='feeId'><input type='hidden' value=" + feeName + " name='feeName'>项目:" + feeName 
            + "</li><li><input type='hidden' value=" + togetherPersonId + " name='togetherPersonId'><input type='hidden' value=" 
            + togetherPersonName + " name='togetherPersonName'>姓名:" + togetherPersonName + "</li> <li><input type='hidden' value=" + freeamount 
            + " name='total'>金额:" + freeamount + "</li><li><input type='hidden'   name='feeremark'   value=" + freeremark + "  >备注:" + freeremark + "</li>"
            +"</div>" +
            "<span class='rright'><input type='image'src='"+basePath+"/pub/images/delete.png' onclick='WS(this)'/></span>" +// 删除按钮
            "<span class='rright'></span>" +
            "<div class='clear'></div>" +
            "</div>";
        $("#chargesdiv").append(html);
        $("#freeremark").val(" ");
        $("#freeamount").val(" ");
    });
$("#cancleproblembtn").click(function(){
	document.getElementById("handleproblembtn").value="处理";
	document.getElementById("cancleproblembtn").style.display="none";
	document.getElementById("dealContent_span").style.display="none";
});
$("#handleproblembtn").click(function(){
	var tag=eval(document.getElementById('handleproblembtn')).value; 
	if(tag=="处理"){
		document.getElementById("handleproblembtn").value="已解决";
		document.getElementById("cancleproblembtn").style.display="inline";
		document.getElementById("dealContent_span").style.display="block";
	}else{
		document.getElementById("status_label").innerText="已解决"
		document.getElementById("status").value="已解决";
		document.getElementById("handleproblembtn").value="处理";
		document.getElementById("handleproblembtn").style.display="none";
		document.getElementById("cancleproblembtn").style.display="none";
		document.getElementById("dealContent_span").style.display="none";
	}
	 
});
        // 遗留问题
    $("#historyProblemadd").click(function () {
        var historyproblemleveal = $("#historyproblemleveal").val();
        var hisproblemcontent = $("#historyproblemleveal option:selected").text();
        var historyproblemremark = $("#historyproblemremark").val();
        var historystatus = $("#historystatus").text();
        if(historyproblemremark ==''){
        	layer.alert("问题描述不能为空",{closeBtn: false });
        }else{
        	var html = "<div class='work_task_inputcontent'>" +
	            "<div class='work_task_inputBox_left lleft'>" +
	            "<li> " +
	            "<input type='hidden'  name='problemRemark' id='problemRemark'  value=" + historyproblemremark + ">   描述:" + historyproblemremark + "" +
	            "</li>" +
	            "<li>" +
	            "<input type='hidden'  name='levelId'  value=" + historyproblemleveal + "> " +
	            "<input type='hidden'  name='problemContent'  value=" + hisproblemcontent + ">   级别:" + hisproblemcontent + "" +
	            "</li>" +
	            " <li>" +
	            "<input type='hidden'  name='status'  value=" + historystatus + ">   状态:" + historystatus +
	            "</li>" +
	            "</div>" +
	            "<span class='work_task_inputcontent_img rright'></span>" +
	            "<span class='rright'><input type='image'src='"+basePath+"/pub/images/delete.png' onclick='WS(this)'/></span>" +// 删除按钮
	            "<div class='clear'></div>" +
	            "</div>";
	        $("#historydiv").append(html);
	       /*
			 * setTimeout(function () { // <-- Simulate network congestion, //
			 * remove setTimeout from production! workTaskScroll.refresh(); //
			 * Remember to refresh when contents // are loaded (ie: on ajax //
			 * completion) }, 100);
			 */
        }

    });

    //城市下拉检查
    $("#AZKTcitys").click(function(){
    	if(!$("#AZKTprovinces").val()){
    		layer.alert("请先选择省份，谢谢！", {title: '温馨提示', closeBtn: false});
    		return false;
    	}
    });
    $("#AZFZcitys").click(function(){
    	if(!$("#AZFZprovinces").val()){
    		layer.alert("请先选择省份，谢谢！", {title: '温馨提示', closeBtn: false});
    		return false;
    	}
    });
    $("#yjktcity").click(function(){
    	if(!$("#YJKTprovinces").val()){
    		layer.alert("请先选择省份，谢谢！", {title: '温馨提示', closeBtn: false});
    		return false;
    	}
    });
    $("#YJFZcitys").click(function(){
    	if(!$("#YJFZprovinces").val()){
    		layer.alert("请先选择省份，谢谢！", {title: '温馨提示', closeBtn: false});
    		return false;
    	}
    });
});


// ------------------------------------------------------------

//格式化数据：当参数为null，undefined时，返回空字符串
var formatData = function(p){
	if(p == "undefined"){
		return "";
	}
	return !p? "":p;
}

//解决单击引起双击问题
function preventDoubleClick(){
	if (alertTime1 == null){
		alertTime1 = new Date().getTime();
    }else{       
        var alertTime2 = new Date().getTime();
        if(alertTime2 - alertTime1 < 500){
        	alertTime1 = alertTime2;
            return false;
        }else{
        	alertTime1 = alertTime2;
        	return true;
        }
    }
}

//移除任务方法(参数：任务ID)
function removeTaskFunc(workTaskId){
	if (alertTime1 == null){
		alertTime1 = new Date().getTime();
    }else{       
        var alertTime2 = new Date().getTime();
        if(alertTime2 - alertTime1 < 500){
        	alertTime1 = alertTime2;
        	return;
        }else{
        	alertTime1 = alertTime2;
        }
    }
    var taskCount = $(".taskTypeBar").length;	// 获取除了'费用'和'遗留问题'之外的任务总数
    if (taskCount <= 1) {		// 判断如果个数少于1时不能移除
        layer.msg("除了'费用'和'遗留问题'之外最少保留一个任务单");
        return false;
    } else {
    	
    	var html = "<div class='window_mian'>"+
			"<div style='padding: .5em .1em; margin: .1em 0em;'>"+// class='workform_list'
			    "<input type='hidden' id='myWorkTaskId' value='"+workTaskId+"'/>"+
				"<span style='width: 100%'> <textarea id='myRemoveRemark'"+
						"name='remark' style='height: 60px; width: 95%' placeholder='移除说明'></textarea>"+
				"</span>"+
				"<div class='clear'></div>"+
				"</div><br/>"+
				"<div align='center'>"+
					"<input type='button' id='reMoveTaskBtn' style='background: #5FBFE7' value='确定'"+
						" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <input type='button' id='cancelRemoveTaskBtn' style='background: #A6BBCE'"+
						"value='取消'>"+
				"</div>"+
			"</div>"+
			"<div class='clear'></div>";
    	
    	layer.open({
    	    type: 1,
    	    title: '任务移除',
    	    shadeClose: false,
    	    closeBtn: false,
    	    shade: 0.2,
    	    scrollbar: false,
    	    area: ['85%', '180px'],
    	    content: html, 
    	    success: function(layero, index){//层弹出后的成功回调方法,两个参数，分别是当前层DOM当前层索引
    	    	
    	    	layero.focus();
    	    	$("#myRemoveRemark").focus();
    	    	$('#cancelRemoveTaskBtn').click(function(){
    	    		if(preventDoubleClick()){
    	    			$(this).focus();
    	    			layer.close(index);
    	    		}
                }); 
                $('#reMoveTaskBtn').click(function(){
                	if (alertTime1 == null){
                		alertTime1 = new Date().getTime();
                    }else{       
                        var alertTime2 = new Date().getTime();
                        if(alertTime2 - alertTime1 < 500){
                        	alertTime1 = alertTime2;
                        	return;
                        }else{
                        	alertTime1 = alertTime2;
                        }
                    }
                	var remark = $("#myRemoveRemark").val();
            		if (remark == "") {
            			layer.alert("移除说明 不能为空");
            			return false;
            		}
            		layer.confirm('注意:是否确认移除？', {title: '温馨提示', closeBtn: false }, function(index) {
            			var remark = $("#myRemoveRemark").val();
                		if (remark == "") {
                			layer.alert("移除说明 不能为空");
                			return false;
                		}
                		var workformId = $("#workformId").val();
                		var workTaskId = $('#myWorkTaskId').val();
                		var poNumber = $("#poNumber").val();
                		var index  = layer.load(2,{shade:[0.6,'#666']});// 0.6透明度的灰色背景
                		$.ajax({
                			type : "post",
                			url : basePath + "/cp/removeTaskWork.do",
                			dataType : "json",
                			data : {
                				workformId : workformId,
                				workTaskId : workTaskId,
                				poNumber : poNumber,
                				remark : remark
                			},
                			beforeSend : function() {
                			},
                			complete : function() {
                			},
                			success : function(data) {
                				layer.close(index);
                				if (data.errcode == 1) {
                					layer.open({
                								content : '任务移除成功!',
                								closeBtn : false,
                								icon : 1,
                								yes : function(index) {
                									layer.close(index); //一般设定yes回调，必须进行手工关闭
                									top.location.href = basePath+'/cp/workOrder/dealWorkOrder.do?detailType=1&poNumber='
                											+ poNumber
                											+ "&workTaskId="
                											+ workTaskId
                											+ "&typeInfo=taskOrderMove";
                								}
                							});
                				} else {
                					layer.alert("任务移除失败："+data.errmsg, { title: '温馨提示', closeBtn: false });
                				}
                			},
                			error : function(XMLHttpRequest, textStatus, errorThrown) {
                				layer.closeAll(); //疯狂模式，关闭所有层
                				layer.alert(errorThrown);
                			}
                		});
            		});
                });
    	    },
    	    end: function(){//层销毁后触发的回调
    	    	
    	    }
    	});
    }
}


function loadDiv() {

    $("ul li").eq(0).addClass("selecttag");
    var showid = $("ul li").eq(0).children().attr("id")

    $("#" + showid + "Div").show();

}


function ShowDivContent(obj) {
    $(obj).parent().parent().children().each(function (index, data) {
            $(data).removeClass("selecttag");
        }
    );

    $(obj).parent().addClass("selecttag");

    $("#boxContent").children().each(function (index, data) {
        $(data).hide();
    });
}


// 隐藏事件(巡检)
function xjselectInfo(obj,taskId) {
    var selectitem = obj.options.selectedIndex;
    if (selectitem == 2) {
        $("#xjdivcontent" + taskId).show();
        $("#xjdivcontentemark" + taskId).show();
    }
    else {
        $("#xjdivcontent" + taskId).hide();
        $("#xjdivcontentemark" + taskId).hide();
        //$("#explain" + taskId).val(" ");
    }

}
// WS(移除)
WS = function (obj) {
    $(obj).parent().parent().remove();
    $(obj).parent().remove();
// obj.Default();
// location.reload();
// location.reload();
// window.location.reload(); //刷新当前页面.
// parent.location.reload();
}

// 工单模块（移除）
function WSSpare(obj) {
    $(obj).parent().remove();
}

// 数字验证
function onlyNumbers(obj) {
    // re = /^\d+\.?\d*$/
	var e=/^([1-9][\d]{0,7}|)([0-9]\.[\d]{1,2})?$/;
	// var a = new RegExp("^[1-9][0-9]*$");
	var c = obj.value;
	if (!e.test(c)) {
		layer.alert("只能输入非0正数,请重新输入",{title: '温馨提示', closeBtn: false });
		len = c.length;
		str1 = c.substr(0, len - 1);
		b.value = "";
		b.focus();
		return
	}
}

// 保存工单
function saveworkDetail() {
	layer.load(2,{
	    shade: [0.6,'#666666'] //0.1透明度的白色背景
	});//加载中...
	$.ajax({
		url : basePath + "/cp/workOrder/workDetailSave.do",
		data : $("#form1").serialize(),
		type : "post",
		dataType : "json",
		beforeSend : function(x) {
		},
		success : function(result) {
			if (result.errcode==1) {
				layer.open({
				    content: '保存成功！',
				    title: '温馨提示',
				    closeBtn: false,
				    yes: function(index){
				        layer.close(index); //一般设定yes回调，必须进行手工关闭
				        window.location.href = basePath + "/cp/workOrder/dealWorkOrder.do?detailType=1&poNumber=" + result.data;
				    }
				}); 
			} else {
				layer.closeAll('loading');
				layer.alert("保存失败：" + result.data);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.closeAll('loading');
			layer.alert("保存失败：【"+textStatus+"】"+errorThrown);
		}
	});
}

// --------------------------------弹出框----------------------------------

// 其他---- 弹出框（模块替换）(弃用）

function oldspareDialogOld(url) {
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
	url += "&workformId="+ $("#workformId").val();
	layer.open({
	    type: 2,
	    title: '模块替换',
	    shadeClose: false,
	    closeBtn: false,
	    shade: 0.2,
	    area: ['90%', '90%'],
	    content: url // iframe的url
	}); 
}
	
function oldspareDialog(divname){
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
	var url = basePath + "/cp/module/worksparepath.do?serialNumber="+$("#serialNumber").val()+"&divname="+divname+"&poNumber="+$("#poNumber").val()+"&workformId="+$("#workformId").val();
	
	layer.load(2,{
	    shade: [0.6,'#666666'] //0.1透明度的白色背景
	});//加载中...
	$.ajax({
		url : basePath + "/cp/workOrder/workDetailSave.do",
		data : $("#form1").serialize(),
		type : "post",
		dataType : "json",
		beforeSend : function(x) {
		},
		success : function(result) {
			if (result.errcode==1) {
				window.location.href = url;
				layer.closeAll('loading');
			} else {
				layer.closeAll('loading');
				layer.alert("请先填写完整并保存基本信息，保存失败：" + result.data);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.closeAll('loading');
			layer.alert("请先填写完整并保存基本信息，保存失败：【"+textStatus+"】"+errorThrown);
		}
	});
}
	// ============================================================
	

// 原模块传值
function loadData(obj, oldSerialName, oldSerialNumber, oldMaterialCode, oldHardwareVersion, oldSoftwareVersion, equipmentConfigId, newSerialName, newMaterialCode, newSerialNumber, newHardwareVersion, newSoftwareVersion) {
 var htmlstr = "<div class='task_box02'><span><font color='blue'>原物料:</font><input type='hidden' value='" + oldSerialName + "' name='" + obj + "oldSerialName'/>" +
     "<input type='hidden' value='" + oldSerialNumber + "' name='" + obj + "oldSerialNumber'/>" +
     "<input type='hidden' value='" + oldMaterialCode + "' name='" + obj + "oldMaterialCode'/>" +
     "<input type='hidden' value='" + oldHardwareVersion + "' name='" + obj + "oldHardwareVersion'/>" +
     "<input type='hidden' value='" + oldSoftwareVersion + "' name='" + obj + "oldSoftwareVersion'/>" +
     "<input type='hidden' value='" + equipmentConfigId + "' name='" + obj + "equipmentConfigId'/>" +
     "<input type='hidden' value='" + newSerialName + "' name='" + obj + "newSerialName'/>" +
     "<input type='hidden' value='" + newMaterialCode + "' name='" + obj + "newMaterialCode'/>" +
     "<input type='hidden' value='" + newSerialNumber + "' name='" + obj + "newSerialNumber'/>" +
     "<input type='hidden' value='" + newHardwareVersion + "' name='" + obj + "newHardwareVersion'/>" +
     "<input type='hidden' value='" + newSoftwareVersion + "' name='" + obj + "newSoftwareVersion'/>"
     + oldSerialName
     + "</span><span><font color='blue'>新物料:</font>"
     + newSerialName
     + "</span><span class='logistics_close' onclick='WSSpare(this)'><img src='"+basePath+"/pub/images/delete.png'/></span></div>";
 $("#" + obj).append(htmlstr);
}

// 零件传值
function loadElementData(obj, newSerialName, oldSerialName, nums, newMaterialCode, oldMaterialCode, newSoftwareVersion, newHardwareVersion, useMethod) {
 var htmlstr = "<div class='task_box02'><span><font color='blue'>原物料:</font>" +
     "<input type='hidden' value='" + newSerialName + "' name='" + obj + "newSerialName'/>" +
     "<input type='hidden' value='" + oldSerialName + "' name='" + obj + "oldSerialName'/>" +
     "<input type='hidden' value='" + nums + "' name='" + obj + "nums'/>" +
     "<input type='hidden' value='" + newMaterialCode + "' name='" + obj + "newMaterialCode'/>" +
     "<input type='hidden' value='" + oldMaterialCode + "' name='" + obj + "oldMaterialCode'/>" +
     "<input type='hidden' value='" + newSoftwareVersion + "' name='" + obj + "newSoftwareVersion'/>" +
     "<input type='hidden' value='" + newHardwareVersion + "' name='" + obj + "newHardwareVersion'/>" +
     "<input type='hidden' value='" + useMethod + "' name='" + obj + "useMethod'/>"

     + oldSerialName
     + "</span><span><font color='blue'>新物料:</font>"
     + newSerialName
     + "</span><span><font color='blue'>数量:</font>" + nums + "</span><span class='logistics_close' onclick='WSSpare(this)'><img src='"+basePath+"/pub/images/delete.png'/></span></div>";
 $("#" + obj).append(htmlstr);
}

//故障传值
function loadFaultLocation(obj, problemPartId, problemPartCode, problemCodeId, troubleCode, problemReasonId, troubleReasonCode, problemMethodId, processCode) {
    var htmlstr = "<div class='task_box02'><span><font color='blue'>故障部位:</font><input type='hidden' value='" + problemPartId + "' name='" + obj + "problemPartId'/>" +
        "<input type='hidden' value='" + problemPartCode + "' name='" + obj + "problemPartCode'/>" +
        "<input type='hidden' value='" + problemCodeId + "' name='" + obj + "problemCodeId'/>" +
        "<input type='hidden' value='" + troubleCode + "' name='" + obj + "troubleCode'/>" +
        "<input type='hidden' value='" + problemReasonId + "' name='" + obj + "problemReasonId'/>" +
        "<input type='hidden' value='" + troubleReasonCode + "' name='" + obj + "troubleReasonCode'/>" +
        "<input type='hidden' value='" + problemMethodId + "' name='" + obj + "problemMethodId'/>" +
        "<input type='hidden' value='" + processCode + "' name='" + obj + "processCode'/>"
        + problemPartCode
        + "</span><span><font color='blue'>故障描述:</font>"
        + troubleCode
        + "</span><span><font color='blue'>故障原因:</font>" + troubleReasonCode + "</span><span><font color='blue'>处理方法:</font>" + processCode + "</span><a class='logistics_close' onclick=WSSpare(this)><img src='"+basePath+"/pub/images/delete.png'/></a></div>";
    $("#" + obj).append(htmlstr);
}

// 其他---- 弹出框（零件替换）
function newspareDialogOld(url) {
	if (alertTime1 == null){
		 alertTime1 = new Date().getTime();
    }else{       
        var alertTime2 = new Date().getTime();
        if(alertTime2 - alertTime1 < 500){
        	alertTime1 = alertTime2;
            return;
        }else{
        	alertTime1 = alertTime2;
        }
    }
	url += "&workformId="+ $("#workformId").val();
	layer.open({
	    type: 2,
	    title: '零件替换',
	    shadeClose: false,
	    closeBtn: false,
	    shade: 0.2,
	    area: ['90%', '90%'],
	    content: url // iframe的url
	});

}

//弹出框（模块替换）
function oldspareDialogNew(divname) {
	//newSerialName, oldSerialName,newMaterialCode,oldMaterialCode,quantity, divname, newHardwareVersion,newSoftwareVersion, sparepartNum,workformId, flag
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
	 var html = "<div class='window_mian' style='line-height: 0.5em'>"+
	        "<input type='hidden' name='newModuleType' id='newModuleType'>"+
	        "<input type='hidden' name='oldModuleType' id='oldModuleType'>"+
	        "<input type='hidden' name='oldequipmentConfigId' id='oldequipmentConfigId'>"+
	        "<input type='hidden' name='divname' value='" +divname+"' id='divname'>"+
	        
            "<div class='workform_list' style='border-bottom: 0px;padding: 0.1em 0em;'>"+
            	"<div class='taskOrder_add_btn' id='oldSerialNameDivBtn'>"+
	                "<label class='blue'>原模块:</label>"+
//	                "<div id='oldSerialNameDiv' ></div>"+
	                "<input name='oldSerialName' class='taskOrder_inputwidth' placeholder='请选择' style='width: 50%;' readonly='readonly' id='oldSerialName' />" +
	                //"<select name='oldSerialName' style='width: 60%;' class='taskOrder_inputwidth' id='oldSerialName' ><option value=''></option></select>" +
	                "<span ><img src='"+basePath+"/pub/images/icon_window.png' /></span>"+
                "</div>"+
                "<div class='clear'></div>"+
            "</div>"+

            "<div class='workform_list' style='border-bottom: 0px; padding: 0.1em 0em;'>"+
				"<label class='blue'>条码编码:</label>"+
				"<span>"+
					"<input type='text' name='oldSerialNumber' class='input_text' readonly='readonly' id='oldSerialNumber'>"+
           		"</span>"+
           		"<div class='clear'></div>"+
            "</div>"+
            
            "<div class='workform_list' style='border-bottom: 0px; padding: 0.1em 0em;'>"+
				"<label class='blue'>物料编码:</label>"+
				"<span>"+
					"<input type='text' name='oldMaterialCode' class='input_text' readonly='readonly' id='oldMaterialCode'>"+
           		"</span>"+
           		"<div class='clear'></div>"+
            "</div>"+
            
            "<div class='workform_list' style='border-bottom: 0px; padding: 0.1em 0em;'>"+
				"<label class='blue'>硬件版本:</label>"+
				"<span>"+
					"<input type='text' name='oldHardwareVersion' class='input_text' readonly='readonly' id='oldHardwareVersion'>"+
	       		"</span>"+
	       		"<div class='clear'></div>"+
	        "</div>"+
	        
	        "<div class='workform_list' style='border-bottom: 0px; padding: 0.1em 0em;'>"+
				"<label class='blue'>软件版本:</label>"+
				"<span>"+
					"<input type='text' name='oldSoftwareVersion' class='input_text' readonly='readonly' id='oldSoftwareVersion'>"+
	       		"</span>"+
	       		"<div class='clear'></div>"+
	        "</div>"+
	
	        "<div class='workform_list' style='border-bottom: 0px; padding: 0.1em 0em;'>"+
	        	"<div class='taskOrder_add_btn'>"+
	                "<label class='blue'>新模块:</label>"+
	                "<select name='newSerialName' style='width: 60%;' class='taskOrder_inputwidth' id='newSerialName' ><option value=''></option></select>" +
	                //"<span ><img src='"+basePath+"/pub/images/icon_window.png' /></span>"+
	            "</div>"+
	            "<div class='clear'></div>"+
	        "</div>"+
	
	        "<div class='workform_list' style='border-bottom: 0px; padding: 0.1em 0em;'>"+
				"<label class='blue'>条码编码:</label>"+
				"<span>"+
					"<input type='text' name='newSerialNumber' class='input_text' readonly='readonly' id='newSerialNumber'>"+
	       		"</span>"+
	       		"<div class='clear'></div>"+
	        "</div>"+
	        
	        "<div class='workform_list' style='border-bottom: 0px; padding: 0.1em 0em;'>"+
				"<label class='blue'>物料编码:</label>"+
				"<span>"+
					"<input type='text' name='newMaterialCode' class='input_text' readonly='readonly' id='newMaterialCode'>"+
	       		"</span>"+
	       		"<div class='clear'></div>"+
	        "</div>"+
	        
	        "<div class='workform_list' style='border-bottom: 0px; padding: 0.1em 0em;'>"+
				"<label class='blue'>硬件版本:</label>"+
				"<span>"+
					"<input type='text' name='newHardwareVersion' class='input_text' readonly='readonly' id='newHardwareVersion'>"+
	       		"</span>"+
	       		"<div class='clear'></div>"+
	        "</div>"+
	        
	        "<div class='workform_list' style='border-bottom: 0px; padding: 0.1em 0em;'>"+
				"<label class='blue'>软件版本:</label>"+
				"<span>"+
					"<input type='text' name='newSoftwareVersion' class='input_text' readonly='readonly' id='newSoftwareVersion'>"+
	       		"</span>"+
	       		"<div class='clear'></div>"+
	        "</div>"+
	        
	        "<div align='center'>"+
	            "<input type='button' style='background: #5FBFE7' value='确定'  id='saveSpareBtn'/>&nbsp;&nbsp;&nbsp;"+
	            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' style='background: #A6BBCE' value='取消' id='cancelSpareBtn'/>"+
	        "</div>"+
	    "</div>";

	var subHtmlStr = "";
	layer.open({
	    type: 1,
	    title: false,//'模块替换',
	    shadeClose: false,
	    closeBtn: false,
	    shade: 0.2,
	    scrollbar: false,
	    area: ['85%', '98%'],
	    content: html, 
	    success: function(layero, index){//层弹出后的成功回调方法,两个参数，分别是当前层DOM当前层索引
	    	
	    	//初始化
	    	$("#showInfo").attr("checked","checked");
	    	
	    	//原物料初始化
	    	var oldIndex = layer.load(2,{ shade: [0.6,'#666666'] });//加载中..
    		var oldselectedItem = $('#oldSerialName').val();
    	    $.ajax({
    	        type: "post",
    	        url: basePath+"/cp/module/oldsparepath.do",
    	        dataType: "json",
    	        data: {serialNumber: $("#serialNumber").val()},
    	        success: function (list) {
    	        	layer.close(oldIndex);
    		    	subHtmlStr = "<div id='oldSerialNameDiv' >";
	        	    $(list).each(function (i, bean) {
	        	    	/*$('#oldSerialName').append("<option multiple='multiple' data-code='"+ bean.materialCode 
	        	    			+"' data-softwareVersion='"+ bean.softwareVersion 
	        	    			+"' data-hardwareVersion='"+ bean.hardwareVersion 
	        	    			+"' data-serialNumber='"+ bean.serialNumber 
	        	    			+"' data-moduleType='"+ bean.moduleType 
	        	    			+"' data-equipmentConfigId='"+ bean.equipmentConfigId 
	        	    			+ "' value='" + bean.materialName + "'>" + bean.materialName + "</option>");*/
	        	    	subHtmlStr += "<div class='spare_wrapper_top'>";
	        	    	subHtmlStr += "<div class='new_title oldselect' ";
	        	    	subHtmlStr += ("data-code='"+ bean.materialCode 
	        	    			+"' data-softwareVersion='"+ bean.softwareVersion 
	        	    			+"' data-hardwareVersion='"+ bean.hardwareVersion 
	        	    			+"' data-serialNumber='"+ bean.serialNumber 
	        	    			+"' data-moduleType='"+ bean.moduleType 
	        	    			+"' data-equipmentConfigId='"+ bean.equipmentConfigId + "'>"+ bean.materialName + "</div></div>");
	        	    	
	                });
	        	    subHtmlStr += "</div>";
    	        	// 则选中之前的
	        	    $('#oldSerialName').val(oldselectedItem);
    	        },
    	        error: function (XMLHttpRequest, textStatus, errorThrown) {
    	        	layer.close(newMNIndex);
    	            alert("发生错误:" + errorThrown);
    	        }
    	    });
    	    //点击原物料事件
	    	$('#oldSerialNameDivBtn').click(function(){
	    		layer.open({
	    		    type: 1,
	    		    title: false,//'模块替换',
	    		    shadeClose: false,
	    		    closeBtn: false,
	    		    shade: 0.2,
	    		    scrollbar: false,
	    		    area: ['75%', '90%'],
	    		    content: subHtmlStr,
	    		    success: function(layero, index){
	    		    	new iScroll("oldSerialNameDiv", {vScrollbar: true,hideScrollbar:false});
	    		    	$(".oldselect").click(function(){
	    		    		layer.close(index);
	    		    		var name = $(this).text();
	    		    		$('#oldSerialName').val(name);
	    		    		var code = $(this).attr("data-code");
	    		    		$('#oldMaterialCode').val(code);
	    		    		$('#oldSoftwareVersion').val(formatData($(this).attr("data-softwareVersion")));
	    		    		$('#oldHardwareVersion').val(formatData($(this).attr("data-hardwareVersion")));
	    		    		$('#oldSerialNumber').val(formatData($(this).attr("data-serialNumber")));
	    		    		$('#oldModuleType').val(formatData($(this).attr("data-moduleType")));
	    		    		$('#oldequipmentConfigId').val(formatData($(this).attr("data-equipmentConfigId")));
	    		    	});
	    		    }
	    		    
	    		});
	    	});
    	    
    	    //新物料初始化
	    	var newIndex = layer.load(2,{ shade: [0.6,'#666666'] });//加载中..
    		var newselectedItem = $('#newSerialName').val();
    	    $.ajax({
    	        type: "post",
    	        url: basePath+"/cp/module/newsparepath.do",
    	        dataType: "json",
    	        data: {workformId: $("#workformId").val()},
    	        success: function (list) {
    	        	layer.close(newIndex);
	        	    $(list).each(function (i, bean) {
	        	    	$('#newSerialName').append("<option data-code='"+ bean.materialCode 
	        	    			+"' data-softwareVersion='"+ bean.softwareVersion 
	        	    			+"' data-harewareVersion='"+ bean.harewareVersion 
	        	    			+"' data-serialNumber='"+ bean.serialNumber 
	        	    			+"' data-sparepartNum='"+ bean.sparepartNum 
	        	    			+"' data-moduleType='"+ bean.moduleType 
	        	    			+ "' value='" + bean.materialName + "'>" + bean.materialName + "</option>");
	                });
    	        	// 则选中之前的
	        	    $('#newSerialName').val(newselectedItem);
    	        },
    	        error: function (XMLHttpRequest, textStatus, errorThrown) {
    	        	layer.close(newMNIndex);
    	            alert("发生错误:" + errorThrown);
    	        }
    	    });
    	    
    	    
    	    
    	    //选择原物料事件
	    	$('#oldSerialName').change(function(){
	    		//设置原物料编码等
	    		var code = $(this).find("option:selected").attr("data-code");
	    		$('#oldMaterialCode').val(code);
	    		$('#oldSoftwareVersion').val(formatData($(this).find("option:selected").attr("data-softwareVersion")));
	    		$('#oldHardwareVersion').val(formatData($(this).find("option:selected").attr("data-hardwareVersion")));
	    		$('#oldSerialNumber').val(formatData($(this).find("option:selected").attr("data-serialNumber")));
	    		$('#oldModuleType').val(formatData($(this).find("option:selected").attr("data-moduleType")));
	    		$('#oldequipmentConfigId').val(formatData($(this).find("option:selected").attr("data-equipmentConfigId")));
	    	});
    	    
    	    //选择新物料事件
	    	$('#newSerialName').change(function(){
	    		
	    		//设置新物料编码等
	    		var code = $(this).find("option:selected").attr("data-code");
	    		$('#newMaterialCode').val(code);
	    		$('#newSoftwareVersion').val(formatData($(this).find("option:selected").attr("data-softwareVersion")));
	    		$('#newHardwareVersion').val(formatData($(this).find("option:selected").attr("data-harewareVersion")));
	    		$('#newSerialNumber').val(formatData($(this).find("option:selected").attr("data-serialNumber")));
	    		$('#newModuleType').val(formatData($(this).find("option:selected").attr("data-moduleType")));
	    	});
	    	
	    	//选择新物料事件
	    	/*$('#newSerialName').click(function(){
	    		if($('#oldSerialName').val() == ""){
	    			layer.alert("请先选择原物料", {title: '温馨提示', closeBtn: false });
	    			return false;
	    		}else{
	    			
	    		}
	    	});*/
	    	
	    	//取消按钮绑定点击
	    	$('#cancelSpareBtn').click(function(){
	    		layer.close(index);
	    	}); 
	    	
	    	//确定按钮绑定点击
	    	$('#saveSpareBtn').click(function(){
	    		var divname = $("#divname").val();
	    		var oldSerialName = $("#oldSerialName").val();
	    		var oldSerialNumber = $("#oldSerialNumber").val();
	    		var oldMaterialCode = $("#oldMaterialCode").val();
	    		var oldHardwareVersion = $("#oldHardwareVersion").val();
	    		var oldSoftwareVersion = $("#oldSoftwareVersion").val();
	    		var equipmentConfigId = $("#oldequipmentConfigId").val();
	    		var newSerialName = $("#newSerialName").val();
	    		var newMaterialCode = $("#newMaterialCode").val();
	    		var newSerialNumber = $("#newSerialNumber").val();
	    		var newHardwareVersion = $("#newHardwareVersion").val();
	    		var newSoftwareVersion = $("#newSoftwareVersion").val();
	    		
	    		var newModuleType = $("#newModuleType").val();
	    		var oldModuleType = $("#oldModuleType").val();
	    		if (oldMaterialCode == "") {
	    			parent.layer.msg("原模块不能为空");
	    			return;
	    		}
	    		if (newMaterialCode == "") {
	    			parent.layer.msg("新模块不能为空");
	    			return;
	    		}
	    		newModuleType  = newModuleType.replace(/\s+/g,"");
	    		oldModuleType=oldModuleType.replace(/\s+/g,"");
	    		if (newModuleType != oldModuleType) {
	    			
	    			parent.layer.msg("物料类型不一致");
	    			return;
	    		}
	    		//传值
	    		loadData(divname, oldSerialName, oldSerialNumber, oldMaterialCode, oldHardwareVersion, oldSoftwareVersion, equipmentConfigId, newSerialName, newMaterialCode, newSerialNumber, newHardwareVersion, newSoftwareVersion);
	    		layer.close(index);
	    	});
	    },
	    end: function(){//层销毁后触发的回调
	    	
	    }
	});

}

//弹出框（零件替换）
function newspareDialog(divname) {
	//newSerialName, oldSerialName,newMaterialCode,oldMaterialCode,quantity, divname, newHardwareVersion,newSoftwareVersion, sparepartNum,workformId, flag
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
	 var html = "<div class='window_mian'>"+
	        "<input type='hidden' name='newSoftwareVersion' id='newSoftwareVersion'>"+
	        "<input type='hidden' name='newHardwareVersion' id='newHardwareVersion'>"+
	        "<input type='hidden' name='sparepartNum' id='sparepartNum'>"+
	        "<input type='hidden' name='divname' value='" +divname+"' id='divname'>"+
	        "<div class='workform_list' id='newMatiralNameBtn'>"+
	           "<div class='taskOrder_add_btn'>"+
		           "<label class='blue' style='20%'>新物料:</label>"+
		           "<select name='newSerialName' style='width: 65%;' class='taskOrder_inputwidth' id='newSerialName'><option value=''></option></select>" +
		           //"<span ><img src='"+basePath+"/pub/images/icon_window.png' /></span>"+
	           "</div>"+
	           "<div class='clear'></div>"+
	        "</div>"+
	        "<div class='workform_list'>"+
	           "<label class='blue'><font color='red' style='20%'>*</font>物料编码:</label>"+
	           "<span>"+
	           		"<input type='text' name='newMaterialCode' readonly='readonly' id='newMaterialCode'>"+
	           "</span>"+
	           "<div class='clear'></div>"+
	        "</div>"+
	        "<div id='divcontent2' style='display: none'>"+
	            "<div class='workform_list'>"+
	            	"<div class='taskOrder_add_btn'>"+
		                "<label class='blue' style='20%'>原物料:</label>"+
		                "<select name='oldSerialName' style='width: 65%;' class='taskOrder_inputwidth' id='oldSerialName' ><option value=''></option></select>" +
		                //"<span ><img src='"+basePath+"/pub/images/icon_window.png' /></span>"+
	                "</div>"+
	                "<div class='clear'></div>"+
	            "</div>"+
	            "<div class='workform_list'>"+
					"<label class='blue' style='20%'>物料编码:</label>"+
					"<span>"+
						"<input type='text' name='oldMaterialCode' class='input_text' readonly='readonly' id='oldMaterialCode'>"+
	           		"</span>"+
	           		"<div class='clear'></div>"+
	            "</div>"+
	        "</div>"+
	
	        "<div class='workform_list'>"+
	            "<label class='blue' style='20%'>使用方法:</label> "+
	            "<span>"+
					"<input type='radio' value='新增' name='menthods' id='showInfo'' />新增"+
	            	"<input type='radio' value='替换' name='menthods' id='replaceInfo'' />替换"+
	            "</span>"+
	            "<div class='clear'></div>"+
	        "</div>"+
	        "<div class='workform_list'>"+
	            "<label class='blue' style='20%'>使用数量:</label>"+
	            "<span>"+
	            	"<input type='text' name='numbers' id='quantity' value='1'>"+
	            "</span>"+
	            "<div class='clear'></div>"+
	        "</div><br/>"+
	        "<div align='center'>"+
	            "<input type='button' style='background: #5FBFE7' value='确定'  id='saveElementBtn'/>&nbsp;&nbsp;&nbsp;"+
	            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' style='background: #A6BBCE' value='取消' id='cancelElementBtn'/>"+
	        "</div>"+
	    "</div>";
	
	layer.open({
	    type: 1,
	    title: '零件替换',
	    shadeClose: false,
	    closeBtn: false,
	    shade: 0.2,
	    scrollbar: false,
	    area: ['85%', '400px'],
	    content: html, 
	    success: function(layero, index){//层弹出后的成功回调方法,两个参数，分别是当前层DOM当前层索引
	    	
	    	//初始化
	    	$("#showInfo").attr("checked","checked");
	    	
	    	//新物料初始化
	    	var newMNIndex = layer.load(2,{ shade: [0.6,'#666666'] });//加载中..
	    	var divname = $("#divname").val();
    		var poNumber = $("#poNumber").val();
    		var workformId = $("#workformId").val();
    		var selectedItem = $('#newSerialName').val();
    		var url = basePath+"/cp/element/newelement.do" + '?flag=add&divname=' + divname + '&poNumber=' + poNumber + "&workformId=" + workformId;
    	    $.ajax({
    	        type: "post",
    	        url: url,
    	        dataType: "json",
    	        success: function (brrowlist) {
    	        	layer.close(newMNIndex);
	        	    $(brrowlist).each(function (i, bean) {
	        	    	$('#newSerialName').append("<option data-code='"+ bean.materialCode +"' data-softwareVersion='"+ bean.softwareVersion 
	        	    			+"' data-harewareVersion='"+ bean.harewareVersion 
	        	    			+"' data-sparepartNum='"+ bean.sparepartNum 
	        	    			+ "' value='" + bean.materialName + "'>" + bean.materialName + "</option>");
	                });
    	        	// 则选中之前的
	        	    $('#newSerialName').val(selectedItem);
    	        },
    	        error: function (XMLHttpRequest, textStatus, errorThrown) {
    	        	layer.close(newMNIndex);
    	            alert("发生错误:" + errorThrown);
    	        }
    	    });
    	    //选择新物料事件
	    	$('#newSerialName').change(function(){
	    		//设置新物料编码等
	    		var code = $(this).find("option:selected").attr("data-code");
	    		$('#newMaterialCode').val(code);
	    		$('#newSoftwareVersion').val($(this).find("option:selected").attr("data-softwareVersion"));
	    		$('#newHardwareVersion').val($(this).find("option:selected").attr("data-harewareVersion"));
	    		$('#sparepartNum').val($(this).find("option:selected").attr("data-sparepartNum"));
	    		//加载原物料信息
	    		if (code != "") {
	    			var selected = $('#oldSerialName').val();
	    			layer.load(2,{ shade: [0.6,'#666666'] })
	    			$.ajax({
	        	        type: "post",
	        	        url: basePath+'/cp/element/oldelement.do',
	        	        dataType: "json",
	        	        data: { newMaterialCode: code},
	        	        success: function (list) {
	        	        	$('#oldSerialName').html("<option value=''></option>");
	        	        	layer.closeAll('loading');
	    	        	    $(list).each(function (i, bean) {
	    	        	    	$('#oldSerialName').append("<option data-code='"+ bean.materialCode +"' value='" + bean.materialName + "'>" + bean.materialName + "</option>");
	    	                });
	        	        	// 则选中之前的
	    	        	    $('#oldSerialName').val(selected);
	        	        },
	        	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	        	        	layer.closeAll('loading');
	        	            alert("发生错误:" + errorThrown);
	        	        }
	        	    });
	    		}
	    	});
	    	//选择原物料事件
	    	$('#oldSerialName').change(function(){
	    		//设置物料编码
	    		var code = $(this).find("option:selected").attr("data-code");
	    		$('#oldMaterialCode').val(code);
	    	});
	    	
	    	//新增按钮绑定点击
	    	$('#showInfo').click(function(){
	    		$('#oldSerialName').find("option[value='']").attr("selected","selected"); // 原物料
	    		$("#oldMaterialCode").val(""); // 原物料编码
	    		$("#divcontent2").hide();
	    	});
	    	//替换按钮绑定点击
	    	$('#replaceInfo').click(function(){
	    		$("#divcontent2").show();
	    	});
	    	//使用数量绑定点击
	    	$('#quantity').keyup(function(){
	    		onlyNumbers(this);
	    	});
	    	
	    	//取消按钮绑定点击
	    	$('#cancelElementBtn').click(function(){
	    		layer.close(index);
	    	}); 
	    	
	    	//确定按钮绑定点击
	    	$('#saveElementBtn').click(function(){
	    		var newSerialName = $("#newSerialName").val();
	    		var newMaterialCode = $("#newMaterialCode").val();
	    		var menthods = $("input[name='menthods']:radio:checked").val();
	    		var divname = $("#divname").val();
	    		var quantity = $("#quantity").val();
	    		var sparepartNum = $("#sparepartNum").val();
	    		var oldSerialName = $("#oldSerialName").val();
	    		var oldMaterialCode = $("#oldMaterialCode").val();
	    		var newSoftwareVersion = $("#newSoftwareVersion").val();
	    		var newHardwareVersion = $("#newHardwareVersion").val();
	    		if (newMaterialCode == "" || (menthods == "替换" && oldMaterialCode == "")
	    				|| (parseInt(quantity) - parseInt(sparepartNum) > 0)) {
	    			if (newMaterialCode == "") {
	    				layer.alert("新物料不能为空", {title: '溫馨提示', closeBtn: false });
	    			}
	    			if (menthods == "替换" && oldMaterialCode == "") {
	    				layer.alert("原物料不能为空", {title: '溫馨提示', closeBtn: false });
	    			}
	    			if (parseInt(quantity) - parseInt(sparepartNum) > 0) {
	    				layer.alert("使用数量(" + quantity + ")不能大于物料的数量(" + sparepartNum + ")", {title: '溫馨提示', closeBtn: false });
	    			}
	    			return false;
	    		}
	    		if(parseInt(quantity) == ""){
	    			layer.alert("使用数量不能为空", {title: '溫馨提示', closeBtn: false });
	    			return false;
	    		}
	    		loadElementData(divname, newSerialName, oldSerialName, quantity, newMaterialCode, oldMaterialCode, newSoftwareVersion, newHardwareVersion, menthods);
	    		layer.close(index);
	    	});
	    },
	    end: function(){//层销毁后触发的回调
	    	
	    }
	});

}

// 故障部位弹出框
function faultLoactionNew(url) {
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
	    /* 自己的代码 */
	layer.open({
	    type: 2,
	    scrollbar: false,
	    title: '故障信息',
	    shadeClose: true,
	    closeBtn: false,
	    shade: 0.2,
	    area: ['90%', '90%'],
	    content: url // iframe的url
	}); 
	// ==================================
}
function faultLoaction(url) {
	url=url+"&dateTime="+new Date().getTime();
	
	layer.load(2,{
	    shade: [0.6,'#666666'] //0.1透明度的白色背景
	});//加载中...
	$.ajax({
		url : basePath + "/cp/workOrder/workDetailSave.do",
		data : $("#form1").serialize(),
		type : "post",
		dataType : "json",
		beforeSend : function(x) {
		},
		success : function(result) {
			if (result.errcode==1) {
				layer.closeAll('loading');
				window.location.href = url;
			} else {
				layer.closeAll('loading');
				layer.alert("请先填写完整并保存基本信息，保存失败：" + result.data);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.closeAll('loading');
			layer.alert("请先填写完整并保存基本信息，保存失败：【"+textStatus+"】"+errorThrown);
		}
	});
}

function pLoaction(){
	var paperId = $("#paperId").val();
	var paperCode = $("#paperCode").val();
	var poNumber = $("#poNumber").val();
	var url = basePath+'/cp/workOrder/faultPaper.do?poNumber='+poNumber+'&paperId='+paperId+'&paperCode='+paperCode;
	layer.open({
	    type: 2,
	    scrollbar: false,
	    title: '纸质工单',
	    shadeClose: true,
	    shade: 0.2,
	    area: ['90%', '90%'],
	    content: url // iframe的url
	}); 
}

// ----------巡检--------
function sendXJ(obj) {

	 ShowDivContent($(obj));
	 var ids = $(obj).attr("id");
	 $("#" + ids + "Div").show();
	}

// 巡检（模块配件件显示）
function XJspareshow(taskid) {

    $("#inspection1" + taskid).show();
    $("#inspection2" + taskid).hide();
    $("#task_replace_button_inspection" + taskid + " span").removeClass("task_replace_button_selected");
    $("#task_replace_button_inspection" + taskid + " span").addClass("task_replace_button_unselected");
    $("#inspectionspare" + taskid).addClass("task_replace_button_selected");
};

function XJelementshow(taskid) {
    $("#inspection1" + taskid).hide();
    $("#inspection2" + taskid).show();
    $("#task_replace_button_inspection" + taskid + " span").removeClass("task_replace_button_selected");
    $("#task_replace_button_inspection" + taskid + " span").addClass("task_replace_button_unselected");
    $("#inspectionelement" + taskid).addClass("task_replace_button_selected");
};
//巡检移除
/*$('#xjTaskMove').click(function(){
    removeTaskFunc($("#XJtaskId").val());
});*/
function removeXJtask(taskId){
	removeTaskFunc(taskId);
}

// ----------------------升级----------------------------------------------------------------------------------
// 升级模块(动态生成)
function sendgrade(obj) {

 ShowDivContent($(obj));
 var ids = $(obj).attr("id");
 $("#" + ids + "Div").show();
}


// (升级)模块替换
function upgradespareshow(taskid) {
 $("#" + taskid).show();
 $("#" + taskid + "upgradeElementDiv").hide();

 $("#task_replace_button_sj" + taskid + " span").removeClass("task_replace_button_selected");
 $("#task_replace_button_sj" + taskid + " span").addClass("task_replace_button_unselected");
 $("#upgradespare" + taskid).addClass("task_replace_button_selected");

}
// 升级-零件替换
function upgradeelementshow(taskid) {
 $("#" + taskid).hide();
 $("#" + taskid + "upgradeElementDiv").show();
 $("#task_replace_button_sj" + taskid + " span").removeClass("task_replace_button_selected");
 $("#task_replace_button_sj" + taskid + " span").addClass("task_replace_button_unselected");
 $("#upgradeelement" + taskid).addClass("task_replace_button_selected");
}


// 升级-任务移除
function SJworkTaskMoveShow(taskid) {
	removeTaskFunc(taskid);
 
}


// 升级(升级前弹出框)
function gradeBeforeDialog(url) {
	layer.open({
	    type: 2,
	    title: '升级前',
	    shadeClose: true,
	    closeBtn: false,
	    shade: 0.2,
	    area: ['90%', '90%'],
	    content: url // iframe的url
	}); 
}
// 升级（升级后弹出框）
function gradeAfterDialog(url) {
	layer.open({
	    type: 2,
	    title: '升级后',
	    shadeClose: true,
	    closeBtn: false,
	    shade: 0.2,
	    area: ['90%', '90%'],
	    content: url // iframe的url
	}); 
}
// --------------------------------------------ajax------------------------------

// 城市
function loadCity(obj) {
	// modify 20150813 txh 修复城市下拉
    var url = $("#urls").val();
    var provinceId = $(obj).val();
    var cityObj = $(obj).parent().parent().next().children().next().children();// 城市对象
    if (!provinceId){
    	cityObj.html('');
    	return;
    }
    var selectCity = cityObj.val();// 选中的城市
    $.ajax({
        type: "post",
        url: url + "/cp/city/cityInfo.do",
        dataType: "json",
        data: {provinceId: provinceId},
        success: function (data) {

            $(data).each(function (index, datas) {
            	cityObj.html('');
                $(datas.cityList).each(function (i, citys) {
                	cityObj.append("<option value=" + citys.defaultId + "," + citys.defaultName + ">" + citys.defaultName + "</option>");

                });

            });
        	// 则选中之前的
        	cityObj.val(selectCity);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("发生错误:" + errorThrown);
        }
    });
}

//同行人员
function loadTogetherPerson() {
    /*var url = $("#urls").val();
    var provinceId = $(obj).val();
    var cityObj = $(obj).parent().parent().next().children().next().children();// 城市对象
    if (!provinceId){
    	cityObj.html('');
    	return;
    }
    var selectCity = cityObj.val();// 选中的城市
*/    
	var cityObj = $('togetherPersonId');
	var selectCity = cityObj.val();// 选中的城市
    $.ajax({
        type: "post",
        url: basePath + "/cp/workOrder/getTogetherPerson.do",
        dataType: "json",
        data: {workformId: $("#workformId").val()},
        success: function (data) {

        	if(data.errcode == 1){
        		$(cityObj).html('');
        		$(data.data).each(function (index, datas) {
        			cityObj.append("<option value=" + datas.userId + "," + datas.name + ">" + datas.name + "</option>");
        			console.log("<option value=" + datas.userId + "," + datas.name + ">" + datas.name + "</option>");
                    /*$(datas.cityList).each(function (i, citys) {
                    	cityObj.append("<option value=" + citys.defaultId + "," + citys.defaultName + ">" + citys.defaultName + "</option>");

                    });*/

                });
            	// 则选中之前的
        		//$(cityObj).val(selectCity);
        	}else{
        		alert("获取同行人员失败："+ data.errmsg);
        	}
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("发生错误:" + errorThrown);
        }
    });
}

var taskContentScrollJson = {
		vScrollbar: true,
		hideScrollbar:false, 
		onBeforeScrollStart: function (e) {
			var nodeType = e.explicitOriginalTarget ? e.explicitOriginalTarget.nodeName.toLowerCase() : (e.target ? e.target.nodeName.toLowerCase() : '');
		    if (nodeType != 'select' && nodeType != 'option' && nodeType != 'input' && nodeType != 'textarea') {
		        // e.preventDefault();
		    }
		}, 
		onRefresh: function () {
		    document.getElementById('scoller').setAttribute('height', this.scrollerH);
		}
};
var workTaskScroll;
var taskContentScroll=[];
function pageActionOnload(){
    new iScroll("taskOrder_nav", {vScrollbar: false,hideScrollbar:false, useTransform: false});
    $('#taskOrder_nav').find("li > a").each(function(element){
        taskContentScroll.push(new iScroll($(this).attr("id")+'Div',taskContentScrollJson));
    });
}
// 获取纸质工单状态
function initPaper(){
    var paperStatus = $("#paperStatus").val();  // 工单ID
    if(paperStatus=="Y"){
    	// 纸质工单已确认，按钮不能点击
    	$("#confimPager").attr("disabled", true);
    	$("#confimPager").css("background-color", "#C7C7C7");
    	$("#updatePager").attr("disabled", true);
    	$("#updatePager").css("background-color", "#C7C7C7");
    }
}

// 纸质工单号确认
function paperConfirm(){
	var paperCode = $("#paperCode").val();
	if(paperCode==""){
		alert("无可用工单！");
	}else{
		/*
		 * if(confirm("确认纸质工单后不可修改，是否确认？")){ paperDoConfirm(); }
		 */
		// 询问框
		layer.confirm('确认纸质工单后不可修改，是否确认？', {
		    btn: ['确认','取消'], // 按钮
		    shade: [0.6,'#666666'] // 显示遮罩
		}, function(){
	        paperDoConfirm();
		}, function(){
		    
		});
	}
}

function paperDoConfirm(){
	// 正在加载效果层
    var index  = layer.load(2,{shade:[0.6,'#666666']});// 0.6透明度的灰色背景
	var workformId = $("#workformId").val();  // 工单ID
	var paperCode = $("#paperCode").val();
	 $.ajax({
	        type: "post",
	        url: basePath + "/cp/workOrder/paperConfirm.do",
	        dataType: "json",
	        data: {
	        	workformId: workformId,
	        	paperCode:paperCode
	        },
	        success: function (data) {
	        	layer.close(index);
	        	if(data.flag == true){
	        		layer.msg(data.msg, {icon: 1});
	        	}else{
	        		layer.alert(data.msg, {icon: 2});
	        	}
	        	if(data.flag==true){
	        		$("#confimPager").attr("disabled", true);
		        	$("#confimPager").css("background-color", "#C7C7C7");
		        	$("#updatePager").attr("disabled", true);
		        	$("#updatePager").css("background-color", "#C7C7C7");
		        	$("#paperStatus").val("Y");
		        	
	        	}else{
	        		$("#paperCode").focus();
	        	}
	        },
	        error: function (data) {
	        	layer.close(index);
	        	layer.alert('确认失败', {icon: 2});
	        }
	    });
}

// 二维码扫描
function codeScan() {
	if(!is_weixin())
	{
		alert("请使用微信浏览器！");
		return;
	}
	var workformId = $("#workformId").val();  // 工单ID
	// 删除替换phonegap代码
	wx.ready(function () {
		wx.scanQRCode({
			desc: 'scanQRCode desc',
			needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
			scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
			success: function (res) {
				var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
				result = encodeURI(result);
				setScanResult(result);
			},
			error: function (data) {
	        	alert("扫描失败");
	        }
		});
	});
}
// 二维码测试
function codeScanTest() {
	if(!is_weixin())
	{
		alert("请使用微信浏览器！");
		return;
	}
	var a = $("#workformId").val();
	// 删除替换phonegap代码
	wx.ready(function () {
		wx.scanQRCode({
			desc: 'scanQRCode desc',
			needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
			scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
			success: function (res) {
				var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
				alert(result);
			}
		});
	});
}

// 二维码的扫描结果会包含\r\n这些特殊字符，所以本代码会将二维码的扫描结果进行encode，在后台解析的时候进行decode
var twoDimensionCodeGlobal = [];
function initQrCode() {
	if(''==qrCode||'null'==qrCode) return ;
	a = eval("(" + qrCode+ ")").arr;
	for(var i=0;i<a.length;i++) {
		twoDimensionCodeGlobal.push(a[i].scanResult);
	}
	setScanDescribe(0,a.length,'');
}
/**
 * 把扫描的结果存入全局变量，并修改提示框内容
 * 
 * @param curNum
 *            当前扫描的二维码是第几个，0为初始化
 * @param size
 *            当前二维码数量
 * @param scanResult
 *            扫描结果，encode后的
 */
function setScanDescribe(curNum, size, scanResult) {
	var printMsg="";
	var tmpTDC = twoDimensionCodeGlobal;
	twoDimensionCodeGlobal=[];
	for(var i=0;i<size;i++) {
		if(i==curNum-1) { // 当前扫描的二维码
			twoDimensionCodeGlobal.push(scanResult);
			printMsg = printMsg+"第"+(i+1)+"个二维码扫描成功！\n";
		} else { // 其他二维码，通过全局变量判断是否为空，鉴于js数组特性，只能重新赋值
			if(tmpTDC[i]==undefined||""==tmpTDC[i]) {
				twoDimensionCodeGlobal.push("");
				printMsg = printMsg+"请扫描第"+(i+1)+"个二维码！\n";
			} else {
				twoDimensionCodeGlobal.push(tmpTDC[i]);
				printMsg = printMsg+"第"+(i+1)+"个二维码扫描成功！\n";
			}
		}
	}
	$("#ewmMessage").val(printMsg);
}
/**
 * encode后的二维码数据
 * 
 * @param scanResult
 */
function setScanResult(scanResult) {
	var twoDimensionCodeObj = new Object();
	twoDimensionCodeObj.scanResult=scanResult;
	twoDimensionCodeObj.arr=new Array();
	twoDimensionCodeObj.poNumber=$(poNumber).val();
	
	var twoDimensionCodeArr = [];
	var size = parseInt(decodeURI(scanResult.substr(0,2)));
	var curNum = parseInt(decodeURI(scanResult.substr(2,2)));
	for(var i=0;i<size;i++) {
		var o = new Object();
		if(i==curNum-1) {
			o.scanResult = scanResult;
		} else {
			o.scanResult = twoDimensionCodeGlobal[i]==undefined?"":twoDimensionCodeGlobal[i];
		}
		twoDimensionCodeObj.arr.push(o);
	}
	/*
	 * debugger; console.log(scanResult);
	 * console.log(JSON.stringify(twoDimensionCodeObj));
	 * console.log(JSON.stringify(twoDimensionCodeGlobal));
	 */
	$.ajax({
		type: "POST",
		url: basePath + "/cp/workOrder/scanResult.do",
		data:{
        	twoDimensionCodeArr : JSON.stringify(twoDimensionCodeObj)
        },
        success: function (msg) {
        	if(msg.flg){
        		setScanDescribe(curNum, size, scanResult);
        	}else{
        		$("#ewmMessage").val(msg.msg);
        	}
        },
        error: function (data) {
        	alert(JSON.stringify(msg));
        }
	 });
}
function testsetScanResult(){
	$.ajax({
		type: "POST",
		url: basePath + "/cp/workOrder/scanResult.do",
		// dataType: "json",
		data:{
        	twoDimensionCodeArr : "fdsfdsfs"
        },
        success: function (msg) {
        	if(msg.flg){
        		setScanDescribe(curNum, size, scanResult);
        	}else{
        		$("#ewmMessage").val(msg.msg);
        	}
        },
        error: function (data) {
        	alert("失败");
        }
	 });
}


// 二维码提交
function codeSubmit(){
	var twoDimensionCodeObj = new Object();
	twoDimensionCodeObj.twoDimensionCode= new Array() ;
	twoDimensionCodeObj.workformId=$("#workformId").val();
	for(var i=0;i<twoDimensionCodeGlobal.length;i++){
		var o = new Object();
		o.code = twoDimensionCodeGlobal[i];
		twoDimensionCodeObj.twoDimensionCode.push(o);
	}

    $.ajax({
        type: "post",
        url: basePath + "/cp/workOrder/checkTwoDimensionCode.do",
    	/* contentType:"application/json", */
        dataType: "json",
        data:{message:JSON.stringify(twoDimensionCodeObj)},
        success: function (msg) {
        	if(msg.status=="0"){
        		layer.alert(msg.SjMsg, {icon: 2});
        		$("#ewmMessage").val(msg.SjMsg);
        		twoDimensionCodeGlobal = [];
        	}else{
        		// alert(msg.errMsg);
        		layer.alert(msg.SjMsg);
        		$("#ewmMessage").val(msg.SjMsg);
        	}
        },
        error: function (data) {
        	if(data.status=="0"){
        		layer.alert("提交失败", {icon: 2});
        	}
        }
    });
}

	// 显示进度条
	function showLoading(){
		window.android_backNav.showLoading();
	}
	// 关闭进度条
	function closeLoading(){
		window.android_backNav.closeLoading();
	}
	
	
	// 实际工单总耗时
	function actualTaskTotalTime(obj) {
		  c=obj.value;
	    // re = /^\d+\.?\d*$/
		var e=/^[1-9]+[0-9]*]*$/;
		// var a = new RegExp("^[1-9][0-9]*$");
		if (!e.test(c)) {
			layer.alert("只能输入非0正数,请重新输入",{title: '温馨提示', closeBtn: false });
			len = c.length;
			str1 = c.substr(0, len - 1);
			$(obj).val("");
		}
		
		var timeSum=0;
		$("#taskOrder_nav ul li a").each(function(index, data) {
			var taskName = $(data).children()[0].innerText;
			var ids = data.id;
			if(ids=='EWMINFO'||ids=='ZZGDINFO' || ids=='IMGINFO'){
				return;
			}
			var times = ids == 'FYINFO' || ids == 'YLWTINFO' ? null : $("#" + ids
					+ "time").val().trim();
			if (times == "") {
				return;
			}
			if (times != "" && times != null) {
				if (isNaN(Math.round(times))) {
					layer.alert("只能输入非0正数,请重新输入",{title: '温馨提示', closeBtn: false });
					return;
				}
				timeSum += parseFloat(times);
			}
		});
		var startTime = new Date(workStartTime.replace(/-/g, "/"));
		var endTime;
		if (workFinishTime == ''){
			 endTime = startTime;
		}else{
			endTime = new Date(workFinishTime.replace(/-/g, "/"));
		}
		var realMinute = Math.round((endTime - startTime)/60000);
		if (realMinute < Math.round(timeSum)){
			if (workFinishTime != '') {
				$(obj).val("");
				layer.alert('工单所有任务总耗时为' + Math.round(timeSum) + '分,应小于等于实际耗费时间'
						+ realMinute + '分',{title: '温馨提示', closeBtn: false });
			}
			timeSum=0;
			$("#taskOrder_nav ul li a").each(function(index, data) {
				var taskName = $(data).children()[0].innerText;
				var ids = data.id;
				if(ids=='EWMINFO'||ids=='ZZGDINFO' || ids=='IMGINFO'){
					return;
				}
				var times = ids == 'FYINFO' || ids == 'YLWTINFO' ? null : $("#" + ids
						+ "time").val().trim();
				if (times == "") {
					return;
				}
				if (times != "" && times != null) {
					if (isNaN(Math.round(times))) {
						alert("输入的信息非数字，请重新输入");
						return;
					}
					timeSum += parseFloat(times);
				}
			});
		}
		$("#actualTotalTime").html(realMinute-Math.round(timeSum)+"分");
		
	}
	// 初始化总耗时
	function initTaskTotalTime() {
		var startTime = new Date(workStartTime.replace(/-/g, "/"));
		var endTime;
		if (workFinishTime == ''){
			 endTime = startTime;
		}else{
			endTime = new Date(workFinishTime.replace(/-/g, "/"));
		}
		var realMinute = (endTime - startTime)/60000;
	    $("#initTotalTime").html(Math.round(realMinute)+"分");
	    
	    var timeSum=0;
		$("#taskOrder_nav ul li a").each(function(index, data) {
			var taskName = $(data).children()[0].innerText;
			var ids = data.id;
			if(ids=='EWMINFO'||ids=='ZZGDINFO' || ids=='IMGINFO'){
				return;
			}
			var times = ids == 'FYINFO' || ids == 'YLWTINFO' ? null : ($("#" + ids+ "time").val() == undefined ? "" : $("#" + ids+ "time").val().trim());
			if (times == "") {
				return;
			}
			if (times != "" && times != null) {
				timeSum += parseFloat(times);
			}
		});
		$("#actualTotalTime").html(Math.round(realMinute-timeSum)+"分");
	}

	// Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
    function OnInput (event) {
    	actualTaskTotalTime(event.target);
    }
    // Internet Explorer
    function OnPropChanged (event) {
        if (event.propertyName.toLowerCase () == "value") {
            actualTaskTotalTime(event.srcElement);
        }
    }
