   	
$(function(){

});
//详细页面返回待派工单列表
function backWaitList(){
	window.location.href = basePath+"/cp/ouath/sendManage/waitOrderList.do";
}

//删除工单
function doDelete(){
	layer.confirm('确认删除该任务吗？', {title: '温馨提示', closeBtn: false }, function(index) {
		layer.close(index);
		layer.load(2,{ shade: [0.6,'#666666'] });//加载中...
		var taskId = $("#taskId").val();
		$.ajax({
			url : basePath + "/cp/sendManage/deleteOrder.do",
			data : {taskId: taskId },
			type : "post",
			dataType : "json",
			success : function(result) {
				if (result.returnResult) {
					layer.open({
					    content: '删除成功！',
					    title: false,
					    closeBtn: false,
					    yes: function(index){
					        layer.close(index); //一般设定yes回调，必须进行手工关闭
					        window.location.href = basePath + "/cp/ouath/sendManage/waitOrderList.do";
					    }
					}); 
				} else {
					layer.closeAll('loading');
					layer.alert(result.errorMessage, {title: '温馨提示', closeBtn: false});
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				layer.closeAll('loading');
				layer.alert("删除失败：【"+textStatus+"】"+errorThrown, {title: '温馨提示', closeBtn: false});
			}
		});
	});
}

function engineerChange(data){
	var userCode = $("#engineerName").val();
	$("#engineerPhoneNo").val('');
	if(data){
		for(var i=0;i<data.length;i++){
			if(data[i].userCode==userCode){
				$("#engineerPhoneNo").val(data[i].mobilephone);
				break;
			}
		}
	}else{
		for(var i=0;i<userinfo.length;i++){
			if(userinfo[i].userCode==userCode){
				$("#engineerPhoneNo").val(userinfo[i].mobilephone);
				break;                   
			}
		}
	}
}
    	
//立即派单
function sendOrder(){
	layer.confirm('确认派单吗？', {title: '温馨提示', closeBtn: false }, function(index) {
		layer.close(index);

    	var url=basePath+"/cp/sendManage/sendDispatch.do";
    	saveAndSendOrder(url);
	});
}

//保存工单
function saveOrder(){
	var url=basePath+"/cp/sendManage/saveOrder.do";
	saveAndSendOrder(url);
}

//派单和保存工单的公用ajax
function saveAndSendOrder(url){
	
	if($("#repairsManName").val().trim() == ""){
		layer.alert("报修人不能为空",{icon:2,closeBtn:false});
		return false;
	}else if($("#repairsMoblie").val().trim() == "" && $("#repairsTelephone").val().trim() == ""){
		layer.alert("报修人手机或固话不能为空",{icon:2,closeBtn:false});
		return false;
	}else if($("#receiveManName").val().trim() == ""){
		layer.alert("接待人不能为空",{icon:2,closeBtn:false});
		return false;
	}else if($("#receiveMoblie").val().trim() == "" && $("#receiveTelephone").val().trim() == ""){
		layer.alert("接待人手机或固话不能为空",{icon:2,closeBtn:false});
		return false;
	}else if($("#reportTime").val().trim() == ""){
		layer.alert("报修时间不能为空",{icon:2,closeBtn:false});
		return false;
	}else if($("#appointmentDate").val().trim() == ""){
		layer.alert("预约上门时间不能为空",{icon:2,closeBtn:false});
		return false;
	}else if($("#engineerName").val().trim() == ""){
		layer.alert("请选择工程师",{icon:2,closeBtn:false});
		return false;
	}else if($("#taskLevel").val().trim() == ""){
		layer.alert("任务等级不能为空",{icon:2,closeBtn:false});
		return false;
	}else if($("#taskContent").val().trim() == ""){
		layer.alert("报修内容不能为空",{icon:2,closeBtn:false});
		return false;
	}
	if($("#taskContent").val().length>350){   
		layer.alert("任务内容字数超过350个,不能操作派单.",{icon:2});
		return false;
	}
	if($("#taskIds").val() == ""){
		layer.alert("请至少选择一个任务!.",{icon:2});
		return false;
	}
	saveAndSendOrderAjax(url);
	
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
	var taskId = $("#taskId").val();
	
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
      		deviceStatus:deviceStatus,
      		taskId: taskId,
      		taskIds:taskIds,
      		formType:"qywx"
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
						//wx.closeWindow();//调用微信JS接口
				        layer.close(index); //一般设定yes回调，必须进行手工关闭
				        window.location.href = basePath + "/cp/ouath/sendManage/waitOrderList.do";
				    }
				});
			}else{
				layer.alert("派单失败："+data.errorMessage,{icon:2,closeBtn:false});
			}
		},error:function(xhr,type,exception){layer.alert("[操作任务报错!]"+type+exception ,{icon:2});
			//wx.closeWindow();
		}
 	}); 
}
	
	
	//准备数据进行  	删除		操作
	function deleteOrderAjax(url){
		
		//获取要提交的信息
		var deviceStatus = $("#deviceStatus").val();
		var serialNumber = $("#serialNumber").val();
		var equipmentModel = $("#equipmentModel").val();
		var customsName = $("#customsName").val();
		var branchName = $("#branchName").val();
		var installAddress = $("#installAddress").val();
		var branchPrincipal = $("#branchPrincipal").val();
		var branchPrincipalTel = $("#branchPrincipalTel").val();
		var reportTime = $("#reportTime").val();
		var appointmentDate = $("#appointmentDate").val();
		var taskType = $("#taskType").val();
		var taskTypeName = $("#taskTypeName").val();
		var engineerId = $("#engineerName").val();
		var engineerName = $("#engineerName option:selected").text();
		var taskContent = $("#taskContent").val();
		var tempSerialNumber = $("#tempSerialNumber").val();
		var id = $("#id").val();
		
		//ajax
    	$.ajax({     
			type: "post",     
			url:url,
			dataType: "json",
	      	data:{'serialNumber': serialNumber,
	      		equipmentModel:equipmentModel,
	      		customsName:customsName,
	      		branchName:branchName,
	      		installAddress:installAddress,
	      		branchPrincipal:branchPrincipal,
	      		branchPrincipalTel:branchPrincipalTel,
	      		reportTime:reportTime,
	      		appointmentDate:appointmentDate,
	      		taskType:taskType,
	      		taskTypeName:taskTypeName,
	      		engineerId:engineerId,
	      		engineerName:engineerName,
	      		taskContent:taskContent,
	      		deviceStatus:deviceStatus,
	      		tempSerialNumber:tempSerialNumber,
	      		id:id,
	      		formType:"qywx"
	      	},
	      	
	      	beforeSend: function () {
	      		layer.load(1, {
	 				shade: [0.1,'#fff'] //0.1透明度的白色背景
				});
	            return true;
          	}, 
          	complete: function () {
          		layer.closeAll('loading'); //关闭加载层
         	}, 
         	
			success: function (data) { 
				if(data.isSave=="save"){
					layer.open({
					    content: '删除成功!',
					closeBtn: false,icon:1,
					    yes: function(index){
							backWaitList();
					        layer.close(index); //一般设定yes回调，必须进行手工关闭
					    }
					}); 
				}
			},error:function(xhr,type,exception){layer.alert(xhr+type+exception);}
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
	
	// 检查是否“YYYY-MM-DD hh:mm:ss”或“YYYY-MM-DD hh:mm”或“YYYY-MM-DDhh”或“YYYY-MM-DD”格式的时间输入
	function IsDateTime(d) {
		var r = d.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
		if (null != r) return true;
		r = d.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{0,2})$/);
		if (null != r) return true;
		r = d.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{0,2}):(\d{0,2})$/);
		if (null != r) return true;
		r = d.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{0,2}):(\d{0,2}):(\d{0,2})$/);
		return (null != r);
	}
	
	//选择联系人后修改联系人电话输入框
	function loadPhone(){
		$("#repairsManName").val($("#repairsManNameSel option:selected").text());
		$("#repairsMoblie").val($("#repairsManNameSel option:selected").attr("mobile"));
		$("#repairsContactId").val($("#repairsManNameSel option:selected").attr("id"));
		if($("#repairsManNameSel").val()!= 'undefined'){
    		$("#repairsTelephone").val($("#repairsManNameSel").val());
		}
	}
	
	//选择接待人后修改接待人人电话输入框
	function loadReceive(){
		$("#receiveManName").val($("#receiveManNameSel option:selected").text());
		$("#receiveMoblie").val($("#receiveManNameSel option:selected").attr("mobile"));
		$("#receiveContactId").val($("#receiveManNameSel option:selected").attr("id"));
		if($("#receiveManNameSel").val()!= 'undefined'){
    		$("#receiveTelephone").val($("#receiveManNameSel").val());
		}
	}
	
	//控制开启时间：16：30分-次日9:00分
   function openTheIndexPage() {
		var now=new Date();
		var hour=now.getHours();
		var minu=now.getMinutes(); //获取分钟 
		if(hour<10){
			hour='0'+hour;
		}
		if(minu<10){
			minu='0'+minu;
		}
		var time= hour+':'+minu;
		if(time >="09:00" && time <="16:30"){
			layer.alert("现在时间是"+time+". 开启时间为：16：30分-次日9:00分,现在不是开启时间!",{closeBtn: false});
			//return true;
			return false;
		} 
    }; 
    
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