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
		<div class="samian" style="padding-top: 0px;">
			<div class="wrapper">
				<div id="thelist">
					<div class="modal-top">
						<i class="fh" onclick="back('${equipmentId }');"><img src="${ctx_path }/pub/images/fh.png" /></i>
						<span style="font-size: font-weight:400; font-size: 1.8em">客户联系人</span>
						<%-- <i class="de" onclick="closeWindow();"><img src="${ctx_path }/pub/images/de.png" /></i> --%>
					</div>
					<div class="modal_list">
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
					<a onclick="save();" class="modal_ok">确定</a>
				</div>
			</div>
		</div>
	</div>
	<script>
		var serialNumber = "${serialNumber }";
	</script>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>
	<script src="${ctx_path }/pub/js/page/equipment/customerInfo.js"></script>
</body>
</html>