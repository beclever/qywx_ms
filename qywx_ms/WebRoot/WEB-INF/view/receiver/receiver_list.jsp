<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>客户联系人列表</title>
<script src="${ctx_path }/pub/js/iscroll.js"></script>
</head>
<body>
	<div class="subject" id="pageContent">
		<div class="samian">
			<div class="wrapper">
				<div id="thelist">
				<div class="float_top">
			         <a href="javascript:add('${equipmentId }')">
			         <i class="xj"><img src="${ctx_path }/pub/images/xj.png" /></i>
			         </a>
			         <span style="font-size: font-weight:400; font-size:1.8em">客户联系人列表</span>
			         <a href="javascript:closeWindow()"> <i class="de">
			          <img src="${ctx_path }/pub/images/de.png" /></i>
					</a>
		        </div>
		        <div class="ment_to_top">
					<c:choose>
					    <c:when test="${'0' == status}">
							${errMsg }
						</c:when>
						<c:when test="${empty listReceiver}">
							没有数据
						</c:when>
						<c:otherwise>
							<c:forEach items="${listReceiver }" var="receiver">
							<div id="customer_${receiver.equipmentContactId }">
								<div class="modal_list">
									<span style='line-height: 1.5em' onclick="selected('${receiver.contactName }','${receiver.mobliePhone }','${receiver.telephone }')">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${receiver.contactName }<br />
										手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：${receiver.mobliePhone }<br />
										固&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：${receiver.telephone }<br />
										性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：${receiver.sexStr }<br />
										管&nbsp;&nbsp;理&nbsp;&nbsp;员：${receiver.isManagerStr }<br /> 
										拒绝短信：${receiver.isRefuseSmsStr }<br />
										备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：${receiver.remark }<br />
									</span>
								</div>
								<div class="foot_link">
									<a href="javascript:edit('${receiver.equipmentContactId }')"><img src="${ctx_path }/pub/images/ed.png" /></a><a
										href="javascript:del('${receiver.equipmentContactId }')"><img src="${ctx_path }/pub/images/de2.png" /></a>
								</div>
							</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	/* $(function(){
		new iScroll("pageContent", {vScrollbar: false,hideScrollbar:false, useTransform: false});
	}); */
	var index = parent.layer.getFrameIndex(window.name);
		function closeWindow(){
			parent.layer.close(index);
		}
		function edit(id){
			location.href=basePath+"/cp/receiver/info.do?type=update&equipmentContactId="+id;
		}
		function add(id){
			location.href=basePath+"/cp/receiver/info.do?type=add&equipmentId="+id;
		}
		function del(id){
			//询问框
			layer.confirm('您确定删除该客户联系人？', {
				title: '温馨提示',
				closeBtn: false,
			    btn: ['确定','取消'], //按钮
			    shade: false //不显示遮罩
			}, function(){
				var delIndex = layer.load(2,{shade:[0.6,'#666']});//0.6透明度的灰色背景
				$.ajax({
					type : "post",
					url : basePath + "/cp/receiver/delete.do",
					dataType : "json",
					data : {
						equipmentContactId : id,
					},
					success : function(data) {
						layer.close(delIndex);
						if ("1" == data.status) {
							layer.msg("删除成功！");
							$("#customer_" + id).remove();
						} else {
							layer.alert("删除失败，" + data.errMsg,{closeBtn: false, title: '温馨提示'});
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						layer.close(delIndex);
						layer.alert("发生错误:【状态："+textStatus+"】" + errorThrown);
					}
				});
			}, function(){
			});
		}
		function selected(manName,mobile,telephone){
			parent.$("#manName").val(manName);
			parent.$("#mobile").val(mobile);
			parent.$("#telephone").val(telephone);
			parent.layer.close(index);
		}
	</script>
</body>
</html>