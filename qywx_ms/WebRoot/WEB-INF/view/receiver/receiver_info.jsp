<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>客户联系人信息</title>
</head>
<body>
	<div class="subject" id="pageContent">
		<div class="samian">
			<div class="wrapper">
				<div id="thelist">
					<div class="modal-top">
						<a href="javascript:back('${equipmentId }')"> <i class="fh"><img
								src="${ctx_path }/pub/images/fh.png" /></i>
						</a><span style="font-size: font-weight:400; font-size: 1.8em">客户联系人</span>
						<a href="javascript:closeWindow()"> <i class="de"><img
								src="${ctx_path }/pub/images/de.png" /></i>
						</a>
					</div>
					<div class="modal_list modal_open">
						<input type="hidden" name="type" id="type" value="${type }"/>
						<input type="hidden" name="equipmentId" id="equipmentId" value="${equipmentId }"/>
						<input type="hidden" name="equipmentContactId" id="equipmentContactId" value="${contact.equipmentContactId }"/>
						<span class="gdlb_info xjkh">
							<div class="loading_title">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</div>
							<div class="sc_body">
								<input class="input_line" type="text" name="contactName" id="contactName" value="${contact.contactName }">
								<input type="button" class="s_btnbg"  onclick="$('#contactName').val('');">
							</div>
							<div class="loading_title">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</div>
							<div class="sc_body">
								<input class="input_line" type="text" name="mobliePhone" id="mobliePhone" value="${contact.mobliePhone }">
								<input type="button" class="s_btnbg"  onclick="$('#mobliePhone').val('');">
							</div>
							<div class="loading_title">固&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</div>
							<div class="sc_body">
								<input class="input_line" type="text" name="telephone" id="telephone" value="${contact.telephone }">
								<input type="button" class="s_btnbg"  onclick="$('#telephone').val('');">
							</div>
							<div class="loading_title">岗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</div>
							<div class="sc_body">
								<input class="input_line" type="text" name="positionVal" id="positionVal" value="${contact.positionVal }">
								<input type="button" class="s_btnbg user" onclick="selectPositionVal();"/>
							</div>
							性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：
							<input type="hidden" name="sex" id="sex" value="${contact.sex }"/>
							<c:choose>
								<c:when test="${'1' == contact.sex }">
									<a onclick="choose('1','sex','0');" class="check"><img id="sex_1" src="${ctx_path}/pub/images/checkbox_r.png" />男</a>
									<a onclick="choose('0','sex','1');" class="check"><img id="sex_0" src="${ctx_path}/pub/images/checkbox.png" />女</a>
								</c:when>
								<c:when test="${'0' == contact.sex }">
									<a onclick="choose('1','sex','0');" class="check"><img id="sex_1" src="${ctx_path}/pub/images/checkbox.png" />男</a>
									<a onclick="choose('0','sex','1');" class="check"><img id="sex_0" src="${ctx_path}/pub/images/checkbox_r.png" />女</a>
								</c:when>
								<c:otherwise>
									<a onclick="choose('1','sex','0');" class="check"><img id="sex_1" src="${ctx_path}/pub/images/checkbox.png" />男</a>
									<a onclick="choose('0','sex','1');" class="check"><img id="sex_0" src="${ctx_path}/pub/images/checkbox.png" />女</a>
								</c:otherwise>
							</c:choose>
							<br/>
							管&nbsp;&nbsp;理&nbsp;&nbsp;员：
							<input type="hidden" name="isManager" id="isManager" value="${contact.isManager }"/>
							<c:choose>
								<c:when test="${'Y' == contact.isManager }">
									<a onclick="choose('Y','isManager','N');" class="check"><img id="isManager_Y" src="${ctx_path}/pub/images/checkbox_r.png" />是</a>
									<a onclick="choose('N','isManager','Y');" class="check"><img id="isManager_N" src="${ctx_path}/pub/images/checkbox.png" />否</a>
								</c:when>
								<c:when test="${'N' == contact.isManager }">
									<a onclick="choose('Y','isManager','N');" class="check"><img id="isManager_Y" src="${ctx_path}/pub/images/checkbox.png" />是</a>
									<a onclick="choose('N','isManager','Y');" class="check"><img id="isManager_N" src="${ctx_path}/pub/images/checkbox_r.png" />否</a>
								</c:when>
								<c:otherwise>
									<a onclick="choose('Y','isManager','N');" class="check"><img id="isManager_Y" src="${ctx_path}/pub/images/checkbox.png" />是</a>
									<a onclick="choose('N','isManager','Y');" class="check"><img id="isManager_N" src="${ctx_path}/pub/images/checkbox.png" />否</a>
								</c:otherwise>
							</c:choose>
							<br/>
							拒绝短信：<input type="hidden" name="isRefuseSms" id="isRefuseSms" value="${contact.isRefuseSms }"/>
							<c:choose>
								<c:when test="${'Y' == contact.isRefuseSms }">
									<a onclick="choose('Y','isRefuseSms','N');" class="check"><img id="isRefuseSms_Y" src="${ctx_path}/pub/images/checkbox_r.png" />是</a>
									<a onclick="choose('N','isRefuseSms','Y');" class="check"><img id="isRefuseSms_N" src="${ctx_path}/pub/images/checkbox.png" />否</a>
								</c:when>
								<c:when test="${'N' == contact.isRefuseSms }">
									<a onclick="choose('Y','isRefuseSms','N');" class="check"><img id="isRefuseSms_Y" src="${ctx_path}/pub/images/checkbox.png" />是</a>
									<a onclick="choose('N','isRefuseSms','Y');" class="check"><img id="isRefuseSms_N" src="${ctx_path}/pub/images/checkbox_r.png" />否</a>
								</c:when>
								<c:otherwise>
									<a onclick="choose('Y','isRefuseSms','N');" class="check"><img id="isRefuseSms_Y" src="${ctx_path}/pub/images/checkbox.png" />是</a>
									<a onclick="choose('N','isRefuseSms','Y');" class="check"><img id="isRefuseSms_N" src="${ctx_path}/pub/images/checkbox.png" />否</a>
								</c:otherwise>
							</c:choose>
							<br/>
							<div class="loading_title">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</div>
							<div class="sc_body">
								<input class="input_line" type="text" name="remark" id="remark" value='${contact.remark }'>
								<input type="button" class="s_btnbg" onclick="$('#remark').val('');">
							</div>
						</span>
					</div>
					<a href="javascript:save()" class="modal_ok">确定</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var index = parent.layer.getFrameIndex(window.name);
	
		function closeWindow() {
			parent.layer.close(index);
		}
	
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
			}else if(0 != mobliePhone.val().length && !IsMobilePhone(mobliePhone.val())){
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
			var savaIndex = layer.load(2,{shade:[0.6,'#666']});//0.6透明度的灰色背景
			$.ajax({
				type : "post",
				url : basePath + "/cp/receiver/saveOrUpdate.do",
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
						    content: data.errMsg,
						    title: '温馨提示',
						    closeBtn: false,
						    yes: function(index){
						        layer.close(index); //一般设定yes回调，必须进行手工关闭
								location.href = basePath
										+ "/cp/receiver/list.do?equipmentId="
										+ equipmentId.val() + "&dataTime="
										+ new Date().getTime();
						    }
						});
					} else {
						layer.alert(data.errMsg);
					}
					layer.close(savaIndex);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					layer.close(savaIndex);
					layer.alert("发生错误:" + errorThrown);
				}
			});
		}
	
		function choose(val, id, unval) {
			$("#" + id).val(val);
			$("#" + id + "_" + val).attr("src", basePath + "/pub/images/checkbox_r.png");
			$("#" + id + "_" + unval).attr("src", basePath + "/pub/images/checkbox.png");
		}
	
		//检查是否移动号码
		function IsMobilePhone(phone) {
			// 首先手机号码是11位的
			if (null == phone || 11 != phone.length) return false;
			// 由13、14、15、17或18开始的号码
			var r = phone.match(/^13\d+$|^14\d+$|^15\d+$|^17\d+$|^18\d+$/g);
			return (r && r.length == 1);
		}
	
		function back(id){
			location.href = basePath + "/cp/receiver/list.do?equipmentId=" + id + "&dataTime=" + new Date().getTime();
		}
	
		var positionValIndex;
		function selectPositionVal(){
			var html = "<div class='customer_select_positionval'>" +
					"<ul><li onclick='selectPositionValToText($(this));'>行长</li>" +
					"<li onclick='selectPositionValToText($(this));'>经理</li>" +
					"<li onclick='selectPositionValToText($(this));'>主任</li>" +
					"<li onclick='selectPositionValToText($(this));'>专员</li></ul></div>";
			positionValIndex = layer.open({
		        type: 1,
		        title: false,
		        area: ['70%', '204px'], //宽高
		        content: html
			 });
		}
	
		function selectPositionValToText(the){
			var positionVal = the.html();
			$("#positionVal").val(positionVal);
			layer.close(positionValIndex);
		}
	</script>
</body>
</html>