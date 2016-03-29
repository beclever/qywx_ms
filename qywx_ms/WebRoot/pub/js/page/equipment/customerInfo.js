/*var index = parent.layer.getFrameIndex(window.name);
function closeWindow() {
	parent.layer.close(index);
}*/

/*window.onpopstate=function (){
 var url= basePath + "/cp/equipment/detail.do?serialNumber=" + serialNumber +"&dateTime=" + new Date().getTime();
 window.history.replaceState("","",url);	
 };*/

function save() {
	var type = $("#type");
	var equipmentId = $("#equipmentId");
	var equipmentContactId = $("#equipmentContactId");
	var contactName = $("#contactName");
	var mobliePhone = $("#mobliePhone");
	var telephone = $("#telephone");
	var positionVal = $("#positionVal");
	var sex = $("#sex");
	var isManager = $("#isManager");
	var isRefuseSms = $("#isRefuseSms");
	var remark = $("#remark");
	contactName.val(contactName.val().trim());
	mobliePhone.val(mobliePhone.val().trim());
	telephone.val(telephone.val().trim());
	positionVal.val(positionVal.val().trim());
	remark.val(remark.val().trim());
	if (0 == contactName.val().length) {
		layer.msg("请输入客户姓名！");
		contactName.focus();
		return;
	}
	if (0 == mobliePhone.val().length && 0 == telephone.val().length) {
		layer.msg("请输入手机或固话！");
		return;
	} else if (0 != mobliePhone.val().length
			&& !IsMobilePhone(mobliePhone.val())) {
		layer.msg("请输入正确格式的手机号码！");
		mobliePhone.focus();
		return;
	}
	if (0 == positionVal.val().length) {
		layer.msg("请输入岗位！");
		positionVal.focus();
		return;
	}
	if (0 == sex.val().length) {
		layer.msg("请选择性别！");
		return;
	}
	if (0 == isManager.val().length) {
		layer.msg("请选择是否为管理员！");
		return;
	}
	if (0 == isRefuseSms.val().length) {
		layer.msg("请选择是否拒绝短信！");
		return;
	}
	var savaIndex = layer.load(2, {
		shade : [ 0.6, '#666' ]
	});// 0.6透明度的灰色背景
	$.ajax({
		type : "post",
		url : basePath + "/cp/equipment/addCustomer.do",
		dataType : "json",
		data : {
			type : type.val(),
			equipmentId : equipmentId.val(),
			equipmentContactId : equipmentContactId.val(),
			contactName : contactName.val(),
			mobliePhone : mobliePhone.val(),
			telephone : telephone.val(),
			positionVal : positionVal.val(),
			sex : sex.val(),
			isManager : isManager.val(),
			isRefuseSms : isRefuseSms.val(),
			remark : remark.val()
		},
		success : function(data) {
			if ("1" == data.status) {
				layer.open({
					content : data.errMsg,
					title : '温馨提示',
					closeBtn : false,
					yes : function(index) {
						layer.close(index); // 一般设定yes回调，必须进行手工关闭
						location.href = basePath
								+ "/cp/equipment/getCustomer.do?equipmentId="
								+ equipmentId.val() + "&serialNumber="
								+ serialNumber + "&dataTime="
								+ new Date().getTime();
					}
				});
			} else {
				layer.alert(data.errMsg);
			}
			layer.close(savaIndex);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("发生错误:" + errorThrown);
			layer.close(savaIndex);
		}
	});
}

function choose(val, id, unval) {
	$("#" + id).val(val);
	$("#" + id + "_" + val).attr("src", basePath + "/pub/images/checkbox_r.png");
	$("#" + id + "_" + unval).attr("src", basePath + "/pub/images/checkbox.png");
}

// 检查是否移动号码
function IsMobilePhone(phone) {
	// 首先手机号码是11位的
	if (null == phone || 11 != phone.length)
		return false;
	// 由13、14、15、17或18开始的号码
	var r = phone.match(/^13\d+$|^14\d+$|^15\d+$|^17\d+$|^18\d+$/g);
	return (r && r.length == 1);
}

function back(id) {
	location.href = basePath + "/cp/equipment/getCustomer.do?equipmentId=" + id
			+ "&serialNumber=" + serialNumber + "&dataTime="
			+ new Date().getTime();
}

var positionValIndex;
function selectPositionVal() {
	var html = "<div class='customer_select_positionval'>"
			+ "<ul><li onclick='selectPositionValToText($(this));'>行长</li>"
			+ "<li onclick='selectPositionValToText($(this));'>经理</li>"
			+ "<li onclick='selectPositionValToText($(this));'>主任</li>"
			+ "<li onclick='selectPositionValToText($(this));'>专员</li></ul></div>";
	positionValIndex = layer.open({
		type : 1,
		title : false,
		area : [ '70%', '204px' ], // 宽高
		content : html
	});
}

function selectPositionValToText(the) {
	var positionVal = the.html();
	$("#positionVal").val(positionVal);
	layer.close(positionValIndex);
}