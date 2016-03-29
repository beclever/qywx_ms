window.onpopstate=function (){		
	var url= basePath+"/cp/workOrder/default.do?returnPageNum=1&random="+Math.random();

	//window.history.replaceState("","",url);	
}
// 工单提交
function workorderSend() {
	var tip = "";
	var timeSum = 0;
	var i = 0;
	var message="";
	var appendTip = function(taskName, msg) {
		if (msg.length == 0)
			return;
		tip += (tip.length > 0 ? '\n' : '') + (++i) + ')' + taskName + msg;
	}
	var isTaskFinish = function(name){
		if(!name) return true;
		var inputs = document.getElementsByName(name);
		if(!inputs||inputs.length==0) return true;
		var target=false;
		$(inputs).each(function(input){
		//Array.forEach(inputs,function(input){
			if(target) return;
			var name = input.value;
			if(!!name&&name=='是')target=input.checked;
		});
		return target;
	}
	$("#taskOrder_nav ul li a").each(function(index, data) {
		var myaTag = $(data).children()[0];
		var ids = data.id;
		if(ids=='EWMINFO'||ids=='ZZGDINFO'){
			return;
		}
		var times = ids == 'FYINFO' || ids == 'YLWTINFO' || ids == 'IMGINFO' ? null : $("#" + ids
				+ "time").val().trim();
		var remarks = ids == 'FYINFO' || ids == 'YLWTINFO' || ids == 'IMGINFO' ? null : $("#" + ids
				+ "workRemark").val().trim();
		var msg = '';
		taskName = $(myaTag).text(); 
		if(!isTaskFinish(stateAndInputs[ids])){
			/*msg='的完成情况，在选择“是”后才能提交';
			appendTip(taskName,msg);*/
//			var i = document.getElementById("historyproblemremark").value;
//			var u = document.getElementById("problemRemark");
//			//var pid = document.getElementById("historyProblemId").value;
//			if (i == "") {
//				if (u == null) {
//					message+="遗留问题的 问题描述不能为空";
//					return false ;
//				}
//			}
		}
		if (times == "") {
			msg = '的  耗时(分钟)不能为空';
			appendTip(taskName, msg);
		}
		if (remarks == "") {
			msg = '的 任务描述不能为空';
			appendTip(taskName, msg);
		}
		if (ids.indexOf('XJ') > -1) {
			var xJtypes = $("#XJtypes").val();
			if (xJtypes == ""){
				msg = '类型不能为空';
				appendTip(taskName, msg);
			}
		}
		if (ids == 'WX') {
			var maintain1problemPartCode = $('input[name="maintain1problemPartCode"]');
			if (maintain1problemPartCode == undefined
					|| maintain1problemPartCode.length == 0) {
				msg = '中的故障部位 信息不能为空';
				appendTip(taskName, msg);
			}
		}
		if (workFinishTime != '' && times != "" && times != null) {
			timeSum += parseFloat(times);
		}
		if(ids == "AZKT") {
			var equipmentChargeName = $("#" + ids + "equipmentChargeName").val();
			if(equipmentChargeName == "") {
				msg = "中的设备责任人 信息不能为空";
				appendTip(taskName, msg);
			}
		}

	});
	if (workFinishTime == '') {
		appendTip('', '工单点击完成后才可以提交');
	} else {
		var startTime = new Date(workStartTime.replace(/-/g, "/"));
		var endTime;
		if (workFinishTime == ''){
			 endTime = startTime;
		}else{
			endTime = new Date(workFinishTime.replace(/-/g, "/"));
		}
		var realMinute = Math.round((endTime - startTime)/60000);
		if (realMinute != Math.round(timeSum))
			appendTip('', '工所有任务总耗时(' + Math.round(timeSum) + '分)应等于实际耗费时('
							+ realMinute + '分)，请确认');
	}

	if (tip.length > 0) {
		layer.alert(tip, {title: '温馨提示', closeBtn: false});
		return;
	}
	if(message.length>0){
		layer.alert("遗留问题的 问题描述不能为空", {title: '温馨提示', closeBtn: false});
		return;
	}
	//判断纸质工单
	var paperCode =  document.getElementById("paperCode").value; 
	if (paperCode == "") {
		layer.alert("纸质工单号不能为空", {title: '温馨提示', closeBtn: false});
		return false ;
	}
	//判断纸质工单号是否绑定
	var paperStatus =  document.getElementById("paperStatus").value;
	if(paperStatus!="Y"){
		layer.alert("请确认纸质工单号", {title: '温馨提示', closeBtn: false});
		return false ;
	}
	//判断安装开通中
	var azktcity = document.getElementById("azktcity");
	if(azktcity){
		if(azktcity.value==""){
			layer.alert("安装开通 的省份不能为空", {title: '温馨提示', closeBtn: false});
			return false ;
		}
	}
	//判断安装辅助中
	var azfzcity = document.getElementById("azfzcity");
	if(azfzcity){
		if(azfzcity.value==""){
			layer.alert("安装辅助 的省份不能为空", {title: '温馨提示', closeBtn: false});
			return false ;
		}
	}
	
	var noBankChecked = true;
	
	//安装开通Tag必填项检查
	if($("#AZKT")){
		$.each($("#AZKTDiv .noBlank"),function(i, n){
			if(!$(n).val()){
				layer.alert("安装开通中有必填项未写，请检查！", {title: '温馨提示', closeBtn: false});
				noBankChecked = false;
			}
		});
	}
	//安装辅助Tag必填项检查
	if($("#AZFZ")){
		$.each($("#AZFZDiv .noBlank"),function(i, n){
			if(!$(n).val()){
				layer.alert("安装辅助中有必填项未写，请检查！", {title: '温馨提示', closeBtn: false});
				noBankChecked = false;
			}
		});
	}
	//移机开通Tag必填项检查
	if($("#YJKT")){
		$.each($("#YJKTDiv .noBlank"),function(i, n){
			if(!$(n).val()){
				layer.alert("移机开通中有必填项未写，请检查！", {title: '温馨提示', closeBtn: false});
				noBankChecked = false;
			}
		});
	}//移机辅助Tag必填项检查
	if($("#YJFZ")){
		$.each($("#YJFZDiv .noBlank"),function(i, n){
			if(!$(n).val()){
				layer.alert("移机辅助中有必填项未写，请检查！", {title: '温馨提示', closeBtn: false});
				noBankChecked = false;
			}
		});
	}
	if(noBankChecked){
		sendMessage();
	}

}
function sendMessage() {
	layer.load(2,{ shade: [0.6,'#666666'] });//加载中...
	$.ajax({
		url : basePath + "/cp/workOrder/workDetailOrderSubmit.do",
		data : $("#form1").serialize(),
		type : "post",
		dataType : "json",
		beforeSend : function(x) {
		},
		success : function(result) {
			if (result.errcode==1) {
				layer.open({
				    content: '提交成功！',
				    title: false,
				    closeBtn: false,
				    yes: function(index){
				        layer.close(index); //一般设定yes回调，必须进行手工关闭
				        window.location.href = basePath + "/cp/ouath/workOrder/default.do";
				    }
				}); 
			} else {
				layer.closeAll('loading');
				layer.alert(result.data, {title: '温馨提示', closeBtn: false});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.closeAll('loading');
			layer.alert("提交失败：【"+textStatus+"】"+errorThrown, {title: '温馨提示', closeBtn: false});
		}
	});
}