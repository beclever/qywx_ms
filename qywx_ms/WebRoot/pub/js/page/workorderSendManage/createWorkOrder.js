//未完成任务列表
var tids=[];
var tTypes = [];
var toDoWorkTask = null;

$(function(){
});

var htmls211= "";

//加载序列号以及相关的信息
function loadOrder(){
	var serialNumber = $("#serialNumber").val();
	if( serialNumber== ""){
		layer.alert('请输入设备序列号.',{closeBtn: false});
		return;
	}else{
		layer.load(2,{ shade: [0.6,'#666666'] });//加载中...
		$.ajax({
			url : basePath + "/cp/sendManage/loadOrder.do",
			data : { serialNumber: serialNumber },
			type : "post",
			dataType : "json",
			success : function(data) {
				layer.closeAll('loading');
				//清空数据
				$("#equipmentModel").val("");
				$("#customerName").val("");
				$("#branchName").val("");
				$("#installAddress").val("");
				if(data.equipmentResult){
					//加载工程师下拉框
					if(data.returnResult){
			            var selsectobj = $("#engineerName");
			            selsectobj[0].options.length = 1;
						$(data.returnMessage).each(function (index, datas) {
					        selsectobj.append("<option data-name="+datas.name+" value=" + datas.userId + ">" + datas.name + "</option>");
			            }); 
					}else{
						layer.alert(data.errorMessage);
					}
					
					//防止多次加载序列号出现多个第一负责人的情况
		            $('#engineerName option').each(function () {
		            	var dataName=$(this).attr("data-name");
		            	$(this).text(dataName);
		            });  
		            
					//变黑色字体
					$("#engineerName")[0].style.color = 'black';
					
					var customerName=$("#customerName");
					var branchName=$("#branchName");
					var installAddress=$("#installAddress");
					customerName.val(data.equipment.customerName);
					branchName.val(data.equipment.branchName);
					installAddress.val(data.equipment.installAddress);
					$("#equipmentId").val(data.equipment.equipmentId);
					$("#equipmentDeptId").val(data.equipment.equipmentDeptId);
					
		            var selsectobj = $("#repairsManNameSel");
		            var selsectoReceive = $("#receiveManNameSel");
					//清除原来的下拉标签
		            //报修人
					var repairsManNameElement = document.getElementById("repairsManNameSel");
					repairsManNameElement.options.length = 0;
					//接待人
					var receiveManNameElement = document.getElementById("receiveManNameSel");
					receiveManNameElement.options.length = 0;
					$(data.equipmentContact).each(function (index, datas) {
						selsectobj.append("<option value='" + datas.telephone + "' mobile='"+datas.mobliePhone + "' id='"+datas.equipmentContactId+"'>" + datas.contactName + "</option>");
						selsectoReceive.append("<option value='" + datas.telephone + "' mobile='"+datas.mobliePhone+ "' id='"+datas.equipmentContactId+"'>" + datas.contactName + "</option>");
		            }); 
					
					if(data.equipmentContact[0]){
						$("#repairsContactId").val(data.equipmentContact[0].equipmentContactId);
						$("#repairsManName").val(data.equipmentContact[0].contactName);
			    		$("#repairsMoblie").val(data.equipmentContact[0].mobliePhone);
						$("#repairsTelephone").val(data.equipmentContact[0].telephone);
						$("#receiveContactId").val(data.equipmentContact[0].equipmentContactId);
						$("#receiveManName").val(data.equipmentContact[0].contactName);
			    		$("#receiveMoblie").val(data.equipmentContact[0].mobliePhone);
						$("#receiveTelephone").val(data.equipmentContact[0].telephone);
					}
					
					
					//未完成列表
					if(data.toDoWorkTaskResult && data.toDoWorkTask.length > 0){
						$("#toDoTaskDiv").show();
						//初始化
						toDoWorkTask = data.toDoWorkTask;
						tids = [];
						tTypes = [];
						
					    $(data.toDoWorkTask).each(function (index, datas) {
					    	tids.push(datas.taskId);
					    	tTypes.push(datas.taskType);
					    });
					    $("#taskIds").val(tids.join(','));
					    $("#taskTypeList").val(tTypes.join(','));
				    	
					}else{
						$("#toDoTaskDiv").hide();
					}
					
					
				}else{
					if(!data.returnResult){
						layer.alert(data.errorMessage,{icon:2});
					}
					layer.alert(data.equipmentMsg,{icon:2});
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				layer.closeAll('loading');
				layer.alert("加载失败：【"+textStatus+"】"+errorThrown, {title: '温馨提示', closeBtn: false});
			}
		});
	}

}

	function chooseFuc(){
		var selectedTids = $("#taskIds").val().split(',');
    	var selectedTtypes = $("#taskTypeList").val().split(',');
    	htmls211="<div class='new_title02'>";
    	$(toDoWorkTask).each(function (index, datas) {
    		htmls211+="<div class='plan_list'>";
    		htmls211+="<label style='width:100%; text-align: left;font-size:18px;'><input type='checkbox' ";
		    if(jQuery.inArray(''+datas.taskId, selectedTids) != -1){
		    	htmls211+="checked='checked' ";
		    }
		    htmls211+="name='taskId' value='"+datas.taskId+"' data-tp='"+datas.taskType+"'>"+"类型:"+datas.taskType+"<br/>升级项目:"+datas.upgradeName+"<br/>计划开始时间:"+datas.planStartDate+"<br/>计划完成时间:"+datas.planEndDate+"</input>&nbsp;&nbsp;&nbsp;&nbsp;</label>";
		    htmls211+="<div class='clear'></div>";
		    htmls211+="</div>";
        });
    	htmls211+="</div>";
    	var box = jQuery.showDialog({
	    	title : "未完成任务",
	  		msg : htmls211, 
	  		isFullHeight : true,
	  		hasDefault : true,
	  		handlerFeforeClose : function(){
	  			//layer.closeAll('page'); 
	  		},
	 		primary : function(){
	 			var str=[];
	 			var tp = [];
	 			$("input[name='taskId']:checkbox").each(function(){
	                if($(this).is(':checked')){
	                    str.push($(this).val());
	                    tp.push($(this).attr("data-tp"));
	                }
	            });
	            //alert(str.join(","));
	 			$("#taskIds").val(str.join(","));
	 			$("#taskTypeList").val(tp.join(","));
	  			if(box){
	  				box.data("close")();
	  				box = null;
	  			}
	 		} 
		});
	}
	
	//立即派单
	function sendOrder(){
		var url=basePath+"/cp/sendManage/sendOrder.do";
		saveAndSendOrder(url);
	}
	
	//保存工单
	function saveOrder(){
		var url=basePath+"/cp/sendManage/saveOrder.do";
		saveAndSendOrder(url);
	}
	
	//派单和保存工单的公用ajax
	function saveAndSendOrder(url){
		if($("#taskContent").val().length>350){   
			layer.alert("任务内容字数超过350个,不能操作保存和派单.",{icon:2});
			return false;
		}
		
		if($("#serialNumber").val() == ""){
			layer.alert("请输入序列号后点击加载",{closeBtn:false});
			return false;
		}else if($("#repairsManName").val() == ""){
			layer.alert("请输入或选择报修人",{closeBtn:false});
			return false;
		}else if($("#repairsMoblie").val() == "" && $("#repairsTelephone").val() == "" ){
			layer.alert("报修人手机或固话不能为空",{closeBtn:false});
			return false;
		}else if(($("#repairsMoblie").val() != "" && !validPhone($("#repairsMoblie").val()))
				|| ($("#repairsTelephone").val() != "" && !validPhone($("#repairsTelephone").val()))){
			layer.alert("请输入正确的报修人手机或固话",{closeBtn:false});
			return false;
		}else if($("#receiveManName").val() == ""){
			layer.alert("接待人不能为空",{icon:2,closeBtn:false});
			return false;
		}else if($("#receiveMoblie").val() == "" && $("#receiveTelephone").val() == ""){
			layer.alert("接待人手机或固话不能为空",{closeBtn:false});
			return false;
		}else if(($("#receiveMoblie").val() != "" && !validPhone($("#receiveMoblie").val())) || 
				($("#receiveTelephone").val() != "" && !validPhone($("#receiveTelephone").val()))){
			layer.alert("请输入正确的接待人手机或固话",{closeBtn:false});
		}else if($("#reportTime").val() == ""){
			layer.alert("请输入报修时间",{closeBtn:false});
			return false;
		}else if($("#appointmentDate").val() == ""){
			layer.alert("请输入预约上门时间",{closeBtn:false});
			return false;
		}else if($("#taskTypeName").val() == ""){
			layer.alert("请选择任务类型",{closeBtn:false});
			return false;
		}else if($("#engineerName").val() == ""){
			layer.alert("请选择工程师",{closeBtn:false});
			return false;
		}else if($("#taskLevel").val() == ""){
			layer.alert("任务等级不能为空",{closeBtn:false});
			return false;
		}else if($("#taskContent").val() == ""){
			layer.alert("报修内容不能为空",{icon:2,closeBtn:false});
			return false;
		}else{
			saveAndSendOrderAjax(url);
		}
    	
	}
	
	//准备数据进行保存
	function saveAndSendOrderAjax(url){
		
		//获取要提交的信息
		var deviceStatus = $("#deviceStatus").val();
		var serialNumber = $("#serialNumber").val();
		var equipmentId = $("#equipmentId").val();
		var equipmentDeptId = $("#equipmentDeptId").val();
		var equipmentModel = $("#equipmentModel").val();
		var customerName = $("#customerName").val();
		var branchName = $("#branchName").val();
		var installAddress = $("#installAddress").val();
		
		var repairsContactId = $("#repairsContactId").val();
		var repairsManName = $("#repairsManName").val();
		var repairsMoblie = $("#repairsMoblie").val();
		var repairsTelephone = $("#repairsTelephone").val();
		
		var receiveContactId = $("#receiveContactId").val();
		var receiveManName = $("#receiveManName").val();
		var receiveMoblie = $("#receiveMoblie").val();
		var receiveTelephone = $("#receiveTelephone").val();
		
		var reportTime = $("#reportTime").val();
		var appointmentDate = $("#appointmentDate").val();
		var taskType = $("#taskType").val();
		var taskTypeName = $("#taskTypeName").val();
		var engineerId = $("#engineerName").val();
		var engineerName = $("#engineerName option:selected").text();
		var taskLevel = $("#taskLevel").val();
		var taskContent = $("#taskContent").val();
		var taskIds = $("#taskIds").val();
		
		//ajax保存数据
    	$.ajax({     
			type: "post",     
			url:url,
			dataType: "json",
	      	data:{
	      		serialNumber: serialNumber,
	      		equipmentId:equipmentId,
	      		equipmentDeptId: equipmentDeptId,
	      		equipmentModel:equipmentModel,
	      		customerName:customerName,
	      		branchName:branchName,
	      		installAddress:installAddress,
	      		
	      		repairsContactId:repairsContactId,
	      		repairsManName:repairsManName,
	      		repairsMoblie:repairsMoblie,
	      		repairsTelephone:repairsTelephone,

	      		receiveContactId:receiveContactId,
	      		receiveManName:receiveManName,
	      		receiveMoblie:receiveMoblie,
	      		receiveTelephone:receiveTelephone,
	      		
	      		reportTime:reportTime,
	      		appointmentDate:appointmentDate,
	      		taskType:taskType,
	      		taskTypeName:taskTypeName,
	      		engineerId:engineerId,
	      		engineerName:engineerName,
	      		taskLevel:taskLevel,
	      		taskContent:taskContent,
	      		taskIds:taskIds
	      	},
	      	
	      	beforeSend: function () {
	      		layer.load(1, {
	 				shade: [0.6,'#666'] //0.1透明度的白色背景
				});
	            return true;
          	}, 
          	complete: function () {
          		layer.closeAll('loading'); //关闭加载层
         	}, 
         	
			success: function (data) { 
				if(data.returnResult){
					layer.open({
					    content: "派单成功!",
						closeBtn: false,
						icon:1,
					    yes: function(index){
							wx.closeWindow();//调用微信JS接口
					        layer.close(index); //一般设定yes回调，必须进行手工关闭
					    }
					});
				}else{
					layer.alert("派单失败："+data.errorMessage,{icon:2,closeBtn:false});
				}
			},error:function(xhr,type,exception){
				layer.alert("[操作工单报错!]"+type+exception ,{closeBtn: false});
			}
	 	}); 
	}
	
	//电话认证
	function validPhone(val) {
		val = val.replace("—","-");
//    		var pattern = /(^(^0\d{2}-\d{8}$)|(^0\d{3}-\d{7}$)|(^0\d{3}-\d{8}$)|(^\(0\d{2}\)-\d{8}$)|(^\(0\d{3}\)-\d{7}$)$)|(^1[3,5,8]\d{9}$)/;
		//手机电话都可以验证：12345678901、1234-12345678、1234-12345678-1234
		var pattern = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
		if (pattern.test(val)) {
			return true;
		} else {
			return false;
		}
	}
	
	//选择联系人后修改联系人电话输入框
	function loadPhone(){
		$("#repairsManName").val($("#repairsManNameSel option:selected").text());
		if($("#repairsManNameSel option:selected").attr("mobile") != 'undefined'){
			$("#repairsMoblie").val($("#repairsManNameSel option:selected").attr("mobile"));
		}else{
			$("#repairsMoblie").val('');
		}
		$("#repairsContactId").val($("#repairsManNameSel option:selected").attr("id"));
		if($("#repairsManNameSel").val()!= 'undefined'){
    		$("#repairsTelephone").val($("#repairsManNameSel").val());
		}else{
			$("#repairsTelephone").val('');
		}
	}
	
	//选择接待人后修改接待人人电话输入框
	function loadReceive(){
		$("#receiveManName").val($("#receiveManNameSel option:selected").text());
		if($("#receiveManNameSel option:selected").attr("mobile") != 'undefined'){
			$("#receiveMoblie").val($("#receiveManNameSel option:selected").attr("mobile"));
		}else{
			$("#receiveMoblie").val('');
		}
		
		$("#receiveContactId").val($("#receiveManNameSel option:selected").attr("id"));
		if($("#receiveManNameSel").val()!= 'undefined'){
    		$("#receiveTelephone").val($("#receiveManNameSel").val());
		}else{
			$("#receiveTelephone").val('');
		}
	}
	
	 //更新任务类型
    function updatePlan(){
    	
    	var arrVal= '';
    	var arrText= '';
    	var i=0;
    	var cb=$(":checkbox:checked");
    	if(cb.size()>0){ 
    		cb.each(function(){
    			arrVal +=$(this).val();
    			arrText +=$("#"+$(this).val()).text();
    			i++;
    			if(i<cb.size()){
    				arrVal+=',';
    				arrText+=',';
    			}
    		});  
    	}
    	$('#taskType').val(arrVal);
		$('#taskTypeName').val(arrText);
		
		layer.closeAll('page'); //关闭所有页面层
    	
    }
    //取消
    function cancelFault() {
    	layer.closeAll('page'); //关闭所有页面层
    }

  //页面层
  function mySelects(){
    layer.open({
        type: 1,
        title: false,
	    fix: false,
        //skin: 'layui-layer-rim', //加上边框
        area: ['75%', '88%'], //宽高
        content: htmls
    });
    
    var tt=$('#taskType').val();
	var arr=tt.split(',');
	$.each(arr,function(i,n){
	  $('input[name='+n+']').attr("checked","checked");
	});
  }