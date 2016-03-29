<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>上传图片</title>

<!-- 微信js -->
<%@ include file="../include/_jsapi.jsp"%>
<!-- 附件处理 -->
<link rel="stylesheet" href="${ctx_path }/pub/css/zyUpload.css" type="text/css">

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
				         <span style="font-size: font-weight:400; font-size:1.8em">图片上传</span>
				         <input type="button" value="添加图片" class="window_btn"  onclick="addImgs()" style="float:right; height: 30px;margin-right: 1.2em;border-radius:15px;"></input>
			        </div>
			        <div class="ment_to_top">
			        	<div class="tagContent selectTag flowmargin-top" id="IMGINFODiv">
							<div class="mian02">
								<input name="is_exist_img" type="hidden" id="is_exist_img" value="1"/>
								<div class="upload_box">
									<div class="upload_main single_main">
										<div id="preview" class="upload_preview">
											<div class="add_upload">
												<c:if test="${'true' == images.returnResult }">
													<c:forEach items="${images.phoneImageBeanList }" var="p">
														<div id="uploadList_${p.id }" class="upload_append_list">
															<div class="file_bar file_hover">
																<div style="padding:5px;">
														           <span class="file_del" onclick="deleteImage('${p.id }');" title="删除"></span>
																</div>
															</div>
															<div class="imgBox" style="width:90px;height:80px">
																<img id="uploadImage_${p.id }" class="upload_image" onclick="prviewImg($(this));" src="${rootUrl }${p.imagesUrl }" style="width:80px;height:80px" />
															</div>
														</div>
													</c:forEach>
												</c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var serialNumber = "${serialNumber }";
		var equipmentId = "${equipmentId }";
		var latitude = "";
		var longitude = "";
		var imageList = [];
		var rootUrl = "${rootUrl }";
	</script>
	<script src="${ctx_path }/pub/js/layer/layer.js"></script>
	<script src="${ctx_path }/pub/js/page/equipment/upload.js"></script>
	<script>
		$(function(){
			if (!is_weixin()) {
				layer.open({
					content : "请使用微信浏览器！",
					title : '温馨提示',
					closeBtn : false,
					yes : function(index) {
						layer.close(index); // 一般设定yes回调，必须进行手工关闭
						backDetail();
					}
				});
			}else{
				getLocation();
			}
			<c:if test="${'true' == images.returnResult }">
				<c:forEach items="${images.phoneImageBeanList }" var="p">
					var imageUrl = "${p.imagesUrl }";
					imageList.push(rootUrl + imageUrl);
				</c:forEach>
			</c:if>
		});
	</script>
</body>
</html>