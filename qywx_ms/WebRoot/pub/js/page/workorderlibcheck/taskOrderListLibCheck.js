//2013.10.25

	//业务菜单返回导航菜单
	function backNavMenu(equipmentId,workformId,poNumber){
        //var url = basePath+"/cp/workOrder/dealWorkOrder.do?detailType=4&equipmentId="+equipmentId+"&workformId="+workformId+"&poNumber="+poNumber;
		var url = basePath+"/cp/ouath/worklibrarianscheck/list.do";
		window.location.href=url;
	}
	
	// 显示委托,回退按钮
	function show_img(){
			if($("#select_content_new").css("display")=='none'){
				document.getElementById("select_content_new").style.display="";
			}else{
				document.getElementById("select_content_new").style.display="none";
			}
			/* $("#select_content_new").fadeToggle(); */
		}
	// 审核通过确认
	function orderPassConfig(){
		document.getElementById("select_content_new").style.display="none";
		layer.confirm("确认审核通过该工单？", function(index){
			orderPass();
		    layer.close(index);
		});
	}
	// 审核通过
	function orderPass(){
		var timeAppointmentDate=$("#timeAppointmentDate").val();
		var timeArriveTime=$("#timeArriveTime").val();
		var timeStartWorkTime=$("#timeStartWorkTime").val();
		var timeFinishWorkTime=$("#timeFinishWorkTime").val();
		layer.load(2,{ shade: [0.6,'#666666'] });//加载中...
		  $.ajax({
		        type: "post",
		        url: basePath + "/cp/worklibrarianscheck/checkWorkformStepInStoreInfo.do",
		        dataType: "json",
		        data:{
		        	poNumber:poNumber,
		        	arriveTime:timeArriveTime,
		        	workStartDate:timeStartWorkTime,
		        	workFinishDate:timeFinishWorkTime,
		        	buttonType:'pass',
		        	checkRemark:'',
		        	targetStoreId:''},
		        success: function (msg) {
		        	layer.closeAll('loading');
		        	if (msg.returnResult) {
		                layer.open({
		                    content: msg.returnMessage,
		                closeBtn: false,icon:1,
		                    yes: function(index){
				        		var url = basePath+"/cp/ouath/worklibrarianscheck/list.do";
				                window.location.href=url;
		                        layer.close(index); //一般设定yes回调，必须进行手工关闭
		                    }
		                }); 
					}else {
						layer.alert(msg.errorMessage);
					}
		        	
		        },
		        error: function (XMLHttpRequest, textStatus, errorThrown) {
		        	layer.closeAll('loading');
		        	layer.alert("提交超时，工单审核失败；"+textStatus+";"+errorThrown);
		        } 
		    });
	}
	// 工单退回原因
	function orderReturnResult(){
		document.getElementById("select_content_new").style.display="none";
		var url = basePath+"/cp/worklibrarianscheck/orderReturn.do?formType=qywx&poNumber="+poNumber+"&equipmentId="+equipmentId+"&workformId="+workformId;
		window.location.href=url;
	}
	// 时间设置
	function showDatePicker(id) {
		// TODO  暂时不加时间校验
		/*id = document.getElementById(id).value +":00";
		var date = new Date(id);// 或者直接new Date();
	    return date.format("yyyy-MM-dd hh:mm:ss");*/
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

    // ---------------------------点击事件--------------------

    // (其他)
    $("#QT").click(function () {

        ShowDivContent($(this));
        $("#QTDiv").show();
    });

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
    
    // 时间
    $("#DATETIME").click(function () {
        ShowDivContent($(this));
        $("#DATETIMEDiv").show();
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


    // 巡检（模块配件件显示）
    $("#inspectionspare").click(function () {

        $("#inspection1").show();
        $("#inspection2").hide();
        $("#task_replace_button_inspection span").removeClass("task_replace_button_selected");
        $("#task_replace_button_inspection span").addClass("task_replace_button_unselected");
        $("#inspectionspare").addClass("task_replace_button_selected");
    });

    $("#inspectionelement").click(function () {
        $("#inspection1").hide();
        $("#inspection2").show();
        $("#task_replace_button_inspection span").removeClass("task_replace_button_selected");
        $("#task_replace_button_inspection span").addClass("task_replace_button_unselected");
        $("#inspectionelement").addClass("task_replace_button_selected");
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
        if (freeamount.trim() == "")result += (result.length > 0 ? "\n" : "") + "金额不能空";
        if (result.length > 0) {
            layer.alert(result);
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
            "<span class='rright'></span>" +
            "<div class='clear'></div>" +
            "</div>";
        $("#chargesdiv").append(html);
        $("#freeremark").val(" ");
        $("#freeamount").val(" ");
    });


        // 遗留问题
    $("#historyProblemadd").click(function () {
        var historyproblemleveal = $("#historyproblemleveal").val();
        var hisproblemcontent = $("#historyproblemleveal option:selected").text();
        var historyproblemremark = $("#historyproblemremark").val();
        var historystatus = $("#historystatus").text();
        if(historyproblemremark ==''){
        	layer.alert("问题描述不能为空");
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
	            "<div class='clear'></div>" +
	            "</div>";
	        $("#historydiv").append(html);
	        setTimeout(function () {	// <-- Simulate network congestion,
										// remove setTimeout from production!
	            workTaskScroll.refresh();	// Remember to refresh when contents
											// are loaded (ie: on ajax
											// completion)
	        }, 100);
        }

    });


});


// ------------------------------------------------------------


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
function xjselectInfo(obj) {
    var selectitem = obj.options.selectedIndex;
    if (selectitem == 2) {
        $("#xjdivcontent").show();
        $("#xjdivcontentemark").show();
    }
    else {
        $("#xjdivcontent").hide();
        $("#xjdivcontentemark").hide();
        $("#explain").val(" ");
    }

}
// WS(移除)
WS = function (obj) {
// $(obj).parent().parent().remove();
    $(obj).parent().remove();
// obj.Default();
// location.reload();
// location.reload();
// window.location.reload(); //刷新当前页面.
// parent.location.reload();
}

// 工单模块（移除）
WSSpare = function (obj) {
    $(obj).parent().remove();
}

// 数字验证
function onlyNumbers(obj) {
    // re = /^\d+\.?\d*$/
	var e=/^([1-9][\d]{0,7}|)([0-9]\.[\d]{1,2})?$/;
	// var a = new RegExp("^[1-9][0-9]*$");
	var c = obj.value;
	if (!e.test(c)) {
		layer.alert("只能输入非0正数,请重新输入");
		len = c.length;
		str1 = c.substr(0, len - 1);
		b.value = "";
		b.focus();
		return
	}
}

// --------------------------------弹出框----------------------------------

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
        + "</span></div>";
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
        + "</span><span><font color='blue'>数量:</font>" + nums + "</span><input class='logistics_close' type='image' src='."+basePath+"images/delete.png' onclick='WSSpare(this)'/></a></div>";
    $("#" + obj).append(htmlstr);
}


// 故障传值
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
        + "</span><span><font color='blue'>故障原因:</font>" + troubleReasonCode + "</span><span><font color='blue'>处理方法:</font>" + processCode + "</span><a class='logistics_close' onclick=WSSpare(this)><img src='"+basePath+"images/delete.png'/></a></div>";
    $("#" + obj).append(htmlstr);
}

// 刷新窗口 移除传值
function loadWorkOrderMove(workTaskId) {
    var htmlstr = "<input type='hidden' value='" + workTaskId + "' name='removeWorkTaskId'/>";
    $("#form1").append(htmlstr);

    var form = $("#form1");
    form1.action = basePath + "action/sa/workOrder/saveTaskOfWorkForm";
    form1.method = 'post';
    form1.submit();
}
//----------------------巡检----------------------------------------------------------------------------------
//巡检模块(动态生成)
function sendXJ(id) {
	var $id= '#'+id;
	ShowDivContent($($id));
	$($id + "Div").show();
}
//(巡检)模块替换
function XJspareshow(taskid) {
  $("#inspection1" + taskid).show();
  $("#inspection2" + taskid).hide();

  $("#task_replace_button_inspection" + taskid + " span").removeClass("task_replace_button_selected");
  $("#task_replace_button_inspection" + taskid + " span").addClass("task_replace_button_unselected");
  $("#inspectionspare" + taskid).addClass("task_replace_button_selected");

}
//巡检-零件替换
function XJelementshow(taskid) {
  $("#inspection1" + taskid).hide();
  $("#inspection2" + taskid).show();
  $("#task_replace_button_inspection" + taskid + " span").removeClass("task_replace_button_selected");
  $("#task_replace_button_inspection" + taskid + " span").addClass("task_replace_button_unselected");
  $("#inspectionelement" + taskid).addClass("task_replace_button_selected");
}
//----------巡检--------
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

//升级(升级前弹出框)

function gradeBeforeDialog(url) {
	//window.location.href=url;
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
	//window.location.href=url;
	url = url + "&isDisable=Y";
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
            layer.alert("发生错误:" + errorThrown);
        }
    });
}
var taskContentScrollJson = {vScrollbar: true,hideScrollbar:false,  onBeforeScrollStart: function (e) {
    var nodeType = e.explicitOriginalTarget ? e.explicitOriginalTarget.nodeName.toLowerCase() : (e.target ? e.target.nodeName.toLowerCase() : '');
    if (nodeType != 'select' && nodeType != 'option' && nodeType != 'input' && nodeType != 'textarea') {
        // e.preventDefault();
    }
}, onRefresh: function () {
    document.getElementById('scoller').setAttribute('height', this.scrollerH);
}};
var workTaskScroll;
var taskContentScroll=[];
function pageActionOnload(){
	$(stateAndInputs).each(function(i,stateAndInput){
		if(stateAndInput.state==undefined||stateAndInput.state==null||stateAndInput.state.trim().length==0)
			stateAndInput.state='否';
		var gradTaskStateIndex = (stateAndInput.state=='否')?1:0;
		var inputs = document.getElementsByName(stateAndInput.inputName);
		inputs[gradTaskStateIndex].checked ="checked";
		stateAndInputs[stateAndInput.taskType] = stateAndInput.inputName;
	});
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

	// 显示进度条
	function showLoading(){
		window.android_backNav.showLoading();
	}
	// 关闭进度条
	function closeLoading(){
		window.android_backNav.closeLoading();
	}
	
	// 纸质工单传值
	function paperLocation(msg) {
		var msgArray = msg.split(",")
		var paperId = msgArray[0];
		var paperCode = msgArray[1];
		$("#paperId").val(paperId);
		$("#paperCode").val(paperCode);
	}

