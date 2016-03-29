<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>客户联系人列表</title>
<style type="text/css">
	.float_top{
		border-bottom: 1px solid #4286f5;
	    text-align: center;
		position: fixed;
	    left: 0px;
	    width: 100%;
	    padding: 13px 0;
	    z-index: 9999;
	    top: 0px;
	    background-color: #fff;
	}
	.ment_to_top{
		margin-top: 58px;
	}
</style>
</head>
<body>
	<div class="subject" id="pageContent">
		<div class="samian" style="padding-top: 0px;">
			<div class="wrapper">
				<div id="thelist">
					<div class="float_top">
						 <i class="fh" onclick="backDetail();"><img style="margin-left: 1.2em;" src="${ctx_path }/pub/images/fh.png" /></i>
				         <span style="font-size: font-weight:400; font-size:1.8em">客户联系人列表</span>
				         <i class="de" onclick="add('${equipmentId }');"><img style="margin-right: 1.2em;" src="${ctx_path }/pub/images/xj.png" /></i>
			        </div>
			        <div class="ment_to_top">
						<c:choose>
							<c:when test="${'0' == result.status}">
								${result.errMsg }
							</c:when>
							<c:when test="${'1' == result.status}">
								<c:forEach items="${result.contactInfo }" var="contact">
									<div id="customer_${contact.equipmentContactId }">
										<div class="modal_list">
											<span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${contact.contactName }<br />
												手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：${contact.mobliePhone }<br />
												固&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：${contact.telephone }<br />
												岗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：${contact.positionVal }<br />
												性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：${contact.sex }<br />
												管&nbsp;&nbsp;理&nbsp;&nbsp;员：${contact.isManager }<br />
												拒绝短信：${contact.isRefuseSms }<br />
												备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：${contact.remark }<br />
											</span>
										</div>
										<div class="foot_link">
											<a onclick="edit('${contact.equipmentContactId }');"><img src="${ctx_path }/pub/images/ed.png" /></a>
											<a onclick="del('${contact.equipmentContactId }');"><img src="${ctx_path }/pub/images/de2.png" /></a>
										</div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								没有数据
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var serialNumber = "${serialNumber }";
	</script>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>
	<script src="${ctx_path }/pub/js/page/equipment/customerList.js"></script>
</body>
</html>